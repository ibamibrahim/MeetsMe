package id.meetsme.meetsme.resultactivity;

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

public class ResultPresenter implements ResultContract.Presenter {

    ResultContract.View mView;

    @Override
    public void setView(Object view) {
        this.mView = (ResultContract.View) view;
    }

    @Override
    public void unsetView(Object view) {

    }
}
