package id.meetsme.meetsme.resultactivity;

import android.content.Context;
import android.support.annotation.Nullable;

import id.meetsme.meetsme.BasePresenter;
import id.meetsme.meetsme.BaseView;
import id.meetsme.meetsme.services.models.response.login.LoginResponseModel;

/**
 * Created by Ibam on 8/28/2017.
 */

public interface ResultContract {

    interface View extends BaseView<Presenter> {
    }

    interface Presenter extends BasePresenter {
    }
}
