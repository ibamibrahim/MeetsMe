package id.meetsme.meetsme.createprofile;

import android.content.Context;
import android.util.Log;

import java.io.IOException;

import id.meetsme.meetsme.services.LocalServices;
import id.meetsme.meetsme.services.RemoteServices;
import id.meetsme.meetsme.services.models.response.editprof.EditProfResponseModel;
import retrofit2.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Ibam on 8/28/2017.
 */

public class CreateProfilePresenter implements CreateProfileContract.Presenter {

    CreateProfileContract.View mView;

    @Override
    public void setView(Object view) {
        this.mView = (CreateProfileContract.View) mView;
    }

    @Override
    public void unsetView(Object view) {

    }

    @Override
    public void createProfile(String sex, String occup, String interest, String birthday, Context context) {
        String token = LocalServices.getToken(context);
        int userId = LocalServices.getUserId(context);

        RemoteServices remoteServices = new RemoteServices();

        remoteServices.createProfile(token, userId, sex, occup, interest, birthday)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<Response<EditProfResponseModel>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.createProfileStatus(false, e.getMessage(), null);
                    }

                    @Override
                    public void onNext(Response<EditProfResponseModel> response) {
                        EditProfResponseModel editResponse = response.body();

                        // if failed
                        try {
                            Log.d("RegisterPresenter", response.isSuccessful() + " " + response
                                    .errorBody().string());
                        } catch (Exception e) {
                            e.printStackTrace();
                            mView.createProfileStatus(false, e.getMessage().toString(), null);
                        }

                        if (editResponse != null) {
                            mView.createProfileStatus(true, "Succesfully registered!", editResponse);
                        } else {
                            try {
                                mView.createProfileStatus(false, response.errorBody().string(), null);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });

    }
}
