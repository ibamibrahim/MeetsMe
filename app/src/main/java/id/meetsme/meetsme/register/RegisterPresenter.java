package id.meetsme.meetsme.register;

import android.content.Context;
import android.util.Log;

import java.io.IOException;

import id.meetsme.meetsme.services.LocalServices;
import id.meetsme.meetsme.services.RemoteServices;
import id.meetsme.meetsme.services.models.response.register.RegisterResponseModel;
import retrofit2.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Ibam on 8/28/2017.
 */

public class RegisterPresenter implements RegisterContract.Presenter {

    private static final String TAG = "RegisterPresenter";
    private RegisterContract.View mView;

    @Override
    public void setView(Object view) {
        mView = (RegisterContract.View) view;
    }

    @Override
    public void unsetView(Object view) {

    }

    @Override
    public void signUp(String username, String email, String password, String fullName, final Context
            context) {
        RemoteServices remoteServices = new RemoteServices();
        remoteServices.register(username, email, password, fullName)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<Response<RegisterResponseModel>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.signUpStatus(false, e.getMessage(), null);
                    }

                    @Override
                    public void onNext(Response<RegisterResponseModel> response) {
                        RegisterResponseModel registerResponse = response.body();

                        // if failed
                        try {
                            Log.d("RegisterPresenter", response.isSuccessful() + " " + response
                                    .errorBody().string());
                        } catch (Exception e) {
                            e.printStackTrace();
                            mView.signUpStatus(false, e.getMessage().toString(), null);
                        }

                        if (registerResponse != null) {
                            saveUserIdAndToken(registerResponse.getUser_profile_id(),
                                    registerResponse.getToken(), context);
                            mView.signUpStatus(true, "Succesfully registered!", registerResponse);
                        } else {
                            try {
                                mView.signUpStatus(false, response.errorBody().string(), null);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
    }

    private void saveUserIdAndToken(String user_id, String token, Context context) {
        token = "JWT " + token;
        LocalServices.saveUserId(context, user_id);
        LocalServices.saveToken(context, token);
        Log.i(TAG, "saveUserIdAndToken: done");
    }
}
