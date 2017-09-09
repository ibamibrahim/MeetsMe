package id.meetsme.meetsme.myprofile;

import android.content.Context;

import java.util.List;

import id.meetsme.meetsme.BasePresenter;
import id.meetsme.meetsme.BaseView;

/**
 * Created by Ibam on 8/28/2017.
 */

public interface MyProfileContract {

    interface View extends BaseView<Presenter> {
        void updateInterestList(List<String> list);
        void updateProfile(String a, String b, String c);
    }

    interface Presenter extends BasePresenter {
        void loadUserDetail(Context context);
    }
}
