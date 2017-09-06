package id.meetsme.meetsme.createprofile;

import android.content.Context;

import id.meetsme.meetsme.BasePresenter;
import id.meetsme.meetsme.BaseView;
import id.meetsme.meetsme.services.models.response.editprof.EditProfResponseModel;

/**
 * Created by Ibam on 8/28/2017.
 */

public interface CreateProfileContract {

    interface View extends BaseView<Presenter> {
        void createProfileStatus(boolean status, String message, EditProfResponseModel model);
    }

    interface Presenter extends BasePresenter {
        void createProfile(String sex, String occup, String interest, String birthday, Context context);
    }
}
