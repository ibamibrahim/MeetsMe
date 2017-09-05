package id.meetsme.meetsme.register;

import android.content.Context;

import id.meetsme.meetsme.BasePresenter;
import id.meetsme.meetsme.BaseView;
import id.meetsme.meetsme.services.models.response.register.RegisterResponseModel;

/**
 * Created by Ibam on 8/28/2017.
 */

public interface RegisterContract {

    interface View extends BaseView<Presenter> {
        void signUpStatus(boolean status, String message, RegisterResponseModel model);
    }

    interface Presenter extends BasePresenter {
        void signUp(String username, String email, String password, String fullName, Context
                context);
    }
}
