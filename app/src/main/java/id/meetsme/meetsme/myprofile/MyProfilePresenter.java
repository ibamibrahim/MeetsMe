package id.meetsme.meetsme.myprofile;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * Created by Ibam on 8/28/2017.
 */

public class MyProfilePresenter implements MyProfileContract.Presenter {

    MyProfileContract.View mView;

    @Override
    public void setView(Object view) {
        this.mView = (MyProfileContract.View) view;
    }

    @Override
    public void unsetView(Object view) {

    }

    @Override
    public void loadInterests() {
        Random random = new Random();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(randomString(9));
        }
        mView.updateInterestList(list);
    }

    private java.lang.String randomString(int length) {
        return UUID.randomUUID().toString().substring(0, length);
    }
}
