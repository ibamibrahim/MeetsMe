package id.meetsme.meetsme.login;

import android.content.Context;
import android.util.Log;

import java.io.IOException;

import id.meetsme.meetsme.services.LocalServices;
import id.meetsme.meetsme.services.RemoteServices;
import id.meetsme.meetsme.services.models.response.login.LoginResponseModel;
import retrofit2.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Ibam on 8/28/2017.
 */

public class LoginPresenter implements LoginContract.Presenter {

    LoginContract.View mView;

    @Override
    public void setView(Object view) {
        this.mView = (LoginContract.View) view;
    }

    @Override
    public void unsetView(Object view) {

    }

    @Override
    public void login(String email, String password, final Context context) {
        RemoteServices remoteServices = new RemoteServices();
        remoteServices.login(email, password)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<Response<LoginResponseModel>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.loginStatus(false, e.getMessage(), null);
                    }

                    @Override
                    public void onNext(Response<LoginResponseModel> response) {
                        LoginResponseModel loginResponse = response.body();

                        // if failed
                        try {
                            Log.d("LoginPresenter", response.isSuccessful() + " " + response
                                    .errorBody().string());
                        } catch (Exception e){
                            e.printStackTrace();
                        }

                        if(loginResponse.getUser() != null){
                            mView.loginStatus(true, "Succesfully login!", loginResponse);
                            saveTokenAndUsername(loginResponse.getToken(), loginResponse.getUser
                                    ().getUsername(), context);
                        } else {
                            try {
                                mView.loginStatus(false, response.errorBody().string(), null);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
    }

    private void saveTokenAndUsername(String token, String username, Context context){
        token = "JWT " + token;
        LocalServices.saveToken(context, token);
        LocalServices.saveUsername(context, username);
        Log.d("LoginPresenter", token);
    }
}
