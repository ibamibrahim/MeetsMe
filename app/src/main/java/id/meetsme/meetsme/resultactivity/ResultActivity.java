package id.meetsme.meetsme.resultactivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.ButterKnife;
import id.meetsme.meetsme.BaseActivity;
import id.meetsme.meetsme.R;
import id.meetsme.meetsme.chat.ChatActivity;
import id.meetsme.meetsme.helper.Helper;
import id.meetsme.meetsme.services.RealmServices;
import id.meetsme.meetsme.services.models.MapsModel;
import id.meetsme.meetsme.services.models.response.matchmaking.Datum;
import id.meetsme.meetsme.services.models.response.matchmaking.MatchMakingResponse;

/**
 * Created by Ibam on 8/28/2017.
 */

public class ResultActivity extends BaseActivity implements ResultContract.View,
        OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {
    private static final String TAG = "ResultActivity";
    ResultPresenter mPresenter;
    MapFragment mapFragment;
    MatchMakingResponse response;
    List<MapsModel> peopleList;
    double currentPosLat;
    double currentPosLon;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        ButterKnife.bind(this);
        mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        initPresenter();
        getMatchmakingIntent();
        isLoggedIn();
    }

    private void getMatchmakingIntent() {
        Intent intent = getIntent();
        String resultJson = intent.getStringExtra("result");
        peopleList = getPeopleList(resultJson);
        Log.i(TAG, "getMatchmakingIntent: " + Helper.objectToJson(peopleList));
    }

    private List<MapsModel> getPeopleList(String responseStr) {
        List<MapsModel> result = new ArrayList<>();
        response = Helper.jsonToObject(responseStr, MatchMakingResponse.class);
        currentPosLat = response.getLocationLat();
        currentPosLon = response.getLocationLon();
        List<Datum> rawList = response.getData();

        for (Datum datum : rawList) {
            int userId = datum.getUser().getId();

            //cek apakah udah pernah ada usernya di result
            if (isContain(result, userId)) {
                int index = getIndex(result, userId);
                MapsModel alreadyInResult = result.get(index);
                // kalo sebelumnya udah ada dan lebih kecil interestnya, ganti interest tersebut
                if (alreadyInResult.getWeight() < datum.getWeight()) {
                    MapsModel betterInterest = new MapsModel(datum.getUser().getId(), randomName(),
                            datum.getUser().getOccupation(), datum.getPair().getInterest(), datum
                            .getWeight());

                    betterInterest.setLat((String) datum.getUser().getLocationLat());
                    betterInterest.setLon((String) (datum.getUser().getLocationLon()));

                    result.set(index, betterInterest);
                }
            } else {
                // kalo belum ada, langsung tambahin ke resultnya
                MapsModel newInterest = new MapsModel(datum.getUser().getId(), randomName(),
                        datum.getUser().getOccupation(), datum.getPair().getInterest(), datum
                        .getWeight());
                newInterest.setLat((String) datum.getUser().getLocationLat());
                newInterest.setLon((String) (datum.getUser().getLocationLon()));
                result.add(newInterest);
            }
        }

        return result;
    }

    private boolean isContain(List<MapsModel> result, int Id) {
        for (MapsModel model : result) {
            if (model.getId() == Id) {
                return true;
            }
        }
        return false;
    }

    private String randomName() {
        String[] arrNames = getResources().getStringArray(R.array.anonymous_name);
        Random generator = new Random();
        int randomIndex = generator.nextInt(arrNames.length);
        return arrNames[randomIndex];
    }

    private int getIndex(List<MapsModel> result, int userId) {
        for (int i = 0; i < result.size(); i++) {
            if (result.get(i).getId() == userId) {
                return i;
            }
        }
        return -1;
    }

    public void initPresenter() {
        mPresenter = new ResultPresenter();
        mPresenter.setView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.clearLocation(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {

        CameraPosition googlePlex = CameraPosition.builder()
                .target(new LatLng(currentPosLat, currentPosLon))
                .zoom(16)
                .bearing(0)
                .tilt(45)
                .build();

        map.addMarker(new MarkerOptions()
                .position(new LatLng(currentPosLat, currentPosLon))
                .title("You are here")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.user_mapsmarker)));

        for (int i = 0; i < peopleList.size(); i++) {
            MapsModel model = peopleList.get(i);
            if (model.getLat() != null) {
                double lat = Double.parseDouble(model.getLat());
                double lon = Double.parseDouble(model.getLon());
                Marker marker = map.addMarker(new MarkerOptions()
                        .position(new LatLng(lat, lon))
                        .title(model.getName())
                        .snippet("A " + model.getOccupation() + "." + "\n" + "He/She love " +
                                model.getMatchedInterest())
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.user_mapsmarker)));
                marker.setTag(model);
            }
        }

        map.setInfoWindowAdapter(new CustomInfoWindow());
        map.setOnInfoWindowClickListener(this);
        map.animateCamera(CameraUpdateFactory.newCameraPosition(googlePlex), 1000, null);
        //map.setInfoWindowAdapter(new CustomInfoWindow(this, null));
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        MapsModel model = (MapsModel) marker.getTag();
        if (model != null) {
            //showToast(model.getId() + "");
            RealmServices.createChatRoom(model.getId(), model.getName(), "test");
            Intent intent = new Intent(this, ChatActivity.class);
            intent.putExtra("user_id", model.getId());
            intent.putExtra("name", model.getName());
            intent.putExtra("occupation", model.getOccupation());
            startActivity(intent);
        }
    }

    @Override
    public void setPresenter(ResultContract.Presenter presenter) {

    }

    private class CustomInfoWindow implements GoogleMap.InfoWindowAdapter {

        @Override
        public View getInfoWindow(Marker arg0) {
            return null;
        }

        @Override
        public View getInfoContents(Marker marker) {

            LinearLayout info = new LinearLayout(getApplicationContext());
            info.setOrientation(LinearLayout.VERTICAL);

            TextView title = new TextView(getApplicationContext());
            title.setTextColor(Color.BLACK);
            title.setGravity(Gravity.CENTER);
            title.setTypeface(null, Typeface.BOLD);
            title.setText(marker.getTitle());

            TextView snippet = new TextView(getApplicationContext());
            snippet.setTextColor(Color.GRAY);
            snippet.setText(marker.getSnippet());

            info.addView(title);
            info.addView(snippet);

            return info;
        }
    }
}
