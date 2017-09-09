package id.meetsme.meetsme.myprofile;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import id.meetsme.meetsme.helper.Helper;
import id.meetsme.meetsme.services.LocalServices;
import id.meetsme.meetsme.services.models.response.login.LoginResponseModel;

/**
 * Created by Ibam on 8/28/2017.
 */

public class MyProfilePresenter implements MyProfileContract.Presenter {

    MyProfileContract.View mView;

    String[] interestDefault;


    @Override
    public void setView(Object view) {
        this.mView = (MyProfileContract.View) view;
    }

    @Override
    public void unsetView(Object view) {

    }

    @Override
    public void loadUserDetail(Context context) {

        initArray();

        List<String> interestList = new ArrayList<>();
        String interest = LocalServices.getUserInterest(context);
        String[] arrInterest = interest.split(",");
        for (int i = 0; i < arrInterest.length; i++) {
            int interestIndex = Integer.parseInt(arrInterest[i]);
            interestList.add(interestDefault[interestIndex]);
        }
        mView.updateInterestList(interestList);

        LoginResponseModel user = Helper.jsonToObject(LocalServices.getUserDetail(context),
                LoginResponseModel.class);

        String name = user.getUser().getUser().getFirstName();
        String city = "Jakarta";
        String occupation = user.getUser().getOccupation();
        mView.updateProfile(name, occupation, city);
    }

    private void initArray() {
        interestDefault = new String[]{"Football", "Taekwondo", "Formula One", "Basketball", "Badminton", "Teaching", "Education", "Environment", "Renewable", "Energy", "Artificial Intelligence", "Public Speaking", "Politic"};
    }

    private java.lang.String randomString(int length) {
        return UUID.randomUUID().toString().substring(0, length);
    }
}
