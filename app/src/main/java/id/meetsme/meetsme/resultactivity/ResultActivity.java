package id.meetsme.meetsme.resultactivity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import butterknife.ButterKnife;
import id.meetsme.meetsme.BaseActivity;
import id.meetsme.meetsme.R;
import id.meetsme.meetsme.services.models.response.DummyResultModel;

/**
 * Created by Ibam on 8/28/2017.
 */

public class ResultActivity extends BaseActivity implements ResultContract.View, OnMapReadyCallback {
    private static final String TAG = "ResultActivity";
    ResultPresenter mPresenter;
    MapFragment mapFragment;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        ButterKnife.bind(this);
        mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        initPresenter();
    }

    public void initPresenter() {
        mPresenter = new ResultPresenter();
        mPresenter.setView(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {

        double[] dummyLat = {37.4219999, 37.4629101, 37.3092293};
        double[] dummyLong = {-122.0862462, -122.2449094, -122.1136845};
        String[] dummyName = {"Agus", "Budi", "Anton"};
        String[] dummySnippet = {"Software Engineer at BukaLapak", "QA at Kaskus", "CEO of " +
                "Ruangguru.com"};
        String[] dummyInterest = {"Berkuda", "Memancing", "Memanah"};

        CameraPosition googlePlex = CameraPosition.builder()
                .target(new LatLng(37.4219999, -122.0862462))
                .zoom(16)
                .bearing(0)
                .tilt(45)
                .build();

        for (int i = 0; i < 3; i++) {
            DummyResultModel model = new DummyResultModel(dummyName[i], dummySnippet[i],
                    dummyInterest[i]);
            map.addMarker(new MarkerOptions()
                    .position(new LatLng(dummyLat[i], dummyLong[i]))
                    .title(dummyName[i])
                    .snippet(dummySnippet[i])
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.user_mapsmarker)));
        }


        map.animateCamera(CameraUpdateFactory.newCameraPosition(googlePlex), 1000, null);
        //map.setInfoWindowAdapter(new CustomInfoWindow(this, null));
    }

    @Override
    public void setPresenter(ResultContract.Presenter presenter) {

    }
}
