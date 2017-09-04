package id.meetsme.meetsme.login;

import android.content.Context;
import android.support.annotation.Nullable;

import id.meetsme.meetsme.BasePresenter;
import id.meetsme.meetsme.BaseView;
import id.meetsme.meetsme.services.models.response.login.LoginResponseModel;

/**
 * Created by Ibam on 8/28/2017.
 */

public interface LoginContract {

    interface View extends BaseView<Presenter> {
        void loginStatus(boolean status, String message, @Nullable LoginResponseModel responseModel);
    }

    interface Presenter extends BasePresenter {
        void login(String email, String password, Context context);
    }
}
