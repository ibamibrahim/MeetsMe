package id.meetsme.meetsme.resultactivity;

import android.content.Context;
import android.util.Log;

import id.meetsme.meetsme.services.LocalServices;
import id.meetsme.meetsme.services.RemoteServices;
import retrofit2.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Ibam on 8/28/2017.
 */

public class ResultPresenter implements ResultContract.Presenter {

    private static final String TAG = "ResultPresenter";
    ResultContract.View mView;

    @Override
    public void setView(Object view) {
        this.mView = (ResultContract.View) view;
    }

    @Override
    public void unsetView(Object view) {
    }

    @Override
    public void clearLocation(Context context) {
        String userId = LocalServices.getUserId(context) + "";
        String token = LocalServices.getToken(context);
        RemoteServices rm = new RemoteServices();
        rm.clearLocation(token, userId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<Response<String>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Response response) {
                        Log.i(TAG, "onNext: " + response.isSuccessful());
                    }
                });
    }
}
