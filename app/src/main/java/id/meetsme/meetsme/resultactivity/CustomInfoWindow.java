package id.meetsme.meetsme.resultactivity;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import id.meetsme.meetsme.R;
import id.meetsme.meetsme.services.models.response.DummyResultModel;

/**
 * Created by Ibam on 9/8/2017.
 */

class CustomInfoWindow implements GoogleMap.InfoWindowAdapter {

    private final View myContentsView;
    private DummyResultModel model;

    CustomInfoWindow(AppCompatActivity activity, DummyResultModel model) {
        myContentsView = activity.getLayoutInflater().inflate(R.layout.info_window_map, null);
        this.model = model;
    }

    @Override
    public View getInfoContents(Marker marker) {

        TextView name = ((TextView) myContentsView.findViewById(R.id.window_name));
        name.setText(model.getName());

        TextView occupation = ((TextView) myContentsView.findViewById(R.id.window_occup));
        occupation.setText(model.getOccupation());

        TextView interest = ((TextView) myContentsView.findViewById(R.id.window_interest));
        interest.setText(model.getInterest());

        return myContentsView;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        // TODO Auto-generated method stub
        return null;
    }

}