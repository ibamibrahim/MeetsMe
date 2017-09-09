package id.meetsme.meetsme.createprofile;

import android.content.Context;

import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;

import id.meetsme.meetsme.services.LocalServices;
import id.meetsme.meetsme.services.RemoteServices;
import id.meetsme.meetsme.services.models.response.AddUserInterestResponse;
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
        this.mView = (CreateProfileContract.View) view;
    }

    @Override
    public void unsetView(Object view) {

    }

    @Override
    public void createProfile(String sex, String occup, final String interest, String birthday, Context context) {
        final String token = LocalServices.getToken(context);
        final int userId = LocalServices.getUserId(context);
        FirebaseApp.initializeApp(context);
        final String device_token = FirebaseInstanceId.getInstance().getToken();
        final RemoteServices remoteServices = new RemoteServices();

        remoteServices.createProfile(token, userId, sex, occup, interest, birthday, device_token)
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

                        EditProfResponseModel model = response.body();

                        remoteServices.addInterest(token, userId, interest).observeOn(AndroidSchedulers.mainThread())
                                .subscribeOn(Schedulers.io())
                                .subscribe(new Subscriber<Response<AddUserInterestResponse>>() {
                                    @Override
                                    public void onCompleted() {

                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        mView.createProfileStatus(false, e.getMessage(), null);
                                    }

                                    @Override
                                    public void onNext(Response<AddUserInterestResponse> stringResponse) {
                                        mView.createProfileStatus(true, "Succesfully " +
                                                "registered!", null);
                                    }
                                });
                    }
                });

    }
}
