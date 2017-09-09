package id.meetsme.meetsme.main;

import android.content.Context;

import java.io.IOException;

import id.meetsme.meetsme.services.LocalServices;
import id.meetsme.meetsme.services.RemoteServices;
import id.meetsme.meetsme.services.models.response.MatchMakingResponse;
import retrofit2.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Ibam on 8/28/2017.
 */

public class MainActivityPresenter implements MainActivityContract.Presenter {

    MainActivityContract.View mView;

    @Override
    public void setView(Object view) {
        mView = (MainActivityContract.View) view;
    }

    @Override
    public void unsetView(Object view) {

    }

    @Override
    public void matchmaking(double longitude, double latitude, Context context) {
        String token = LocalServices.getToken(context);
        String userId = LocalServices.getUserId(context) + "";
        String interest = LocalServices.getUserInterest(context);

        final RemoteServices remoteServices = new RemoteServices();
        remoteServices.matchMaking(token, userId, interest, latitude, longitude)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<Response<MatchMakingResponse>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.matchmakingResult(false, e.getMessage(), null);
                    }

                    @Override
                    public void onNext(Response<MatchMakingResponse> response) {
                        if (response.isSuccessful()) {
                            mView.matchmakingResult(true, null, response.body());
                        } else {
                            String error = "";
                            try {
                                error = response.errorBody().string();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            mView.matchmakingResult(false, error, null);
                        }
                    }
                });

    }
}
