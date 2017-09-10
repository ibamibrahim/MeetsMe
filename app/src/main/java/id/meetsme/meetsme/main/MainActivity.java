package id.meetsme.meetsme.main;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import id.meetsme.meetsme.BaseFragment;
import id.meetsme.meetsme.R;
import id.meetsme.meetsme.helper.Helper;
import id.meetsme.meetsme.resultactivity.ResultActivity;
import id.meetsme.meetsme.services.models.response.matchmaking.MatchMakingResponse;

import static android.content.Context.LOCATION_SERVICE;

/**
 * Created by Ibam on 8/28/2017.
 */

public class MainActivity extends BaseFragment implements MainActivityContract.View {
    private static final String TAG = "MainActivity";
    MainActivityPresenter mPresenter;
    LocationManager mLocationManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main, container, false);
        ButterKnife.bind(this, view);
        initPresenter();

        return view;
    }

    private void initPresenter() {
        mPresenter = new MainActivityPresenter();
        mPresenter.setView(this);
    }

    @OnClick(R.id.main_bigbutton)
    public void getMatching() {
        LocationManager lm = (LocationManager) getActivity().getSystemService(
                LOCATION_SERVICE);
        Location location = getLastKnownLocation();
        double longitude = location.getLongitude();
        double latitude = location.getLatitude();
        matchmaking(longitude, latitude);
    }

    private void matchmaking(double longitude, double lat) {
        showProgressDialog("Finding your next best buddies..");
        mPresenter.matchmaking(longitude, lat, getContext());
    }

    @Override
    public void matchmakingResult(boolean status, String message, MatchMakingResponse matchingList) {
        hideDialog();
        if (status) {
            String matchingListJson = Helper.objectToJson(matchingList);
            Intent intent = new Intent(getContext(), ResultActivity.class);
            intent.putExtra("result", matchingListJson);
            Log.i(TAG, "matchmakingResult: " + matchingListJson);
            startActivity(intent);
        } else {
            Log.i(TAG, "matchmakingResult: " + message);
            showToast(message);
        }
    }

    private Location getLastKnownLocation() {
        mLocationManager = (LocationManager) getActivity().getSystemService
                (LOCATION_SERVICE);
        List<String> providers = mLocationManager.getProviders(true);
        Location bestLocation = null;
        for (String provider : providers) {
            if (ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            }
            Location l = mLocationManager.getLastKnownLocation(provider);

            if (l == null) {
                continue;
            }
            if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {
                // Found best last known location: %s", l);
                bestLocation = l;
            }
        }


        return bestLocation;
    }


    @Override
    public void setPresenter(MainActivityContract.Presenter presenter) {
        this.mPresenter = (MainActivityPresenter) presenter;
    }
}
