package id.meetsme.meetsme.main;

import android.content.Context;

import id.meetsme.meetsme.BasePresenter;
import id.meetsme.meetsme.BaseView;

/**
 * Created by Ibam on 8/28/2017.
 */

public interface MainActivityContract {

    interface View extends BaseView<Presenter> {
        void matchmakingResult(boolean status, String message, Object matchingList);
    }

    interface Presenter extends BasePresenter {
        void matchmaking(double longitude, double latitude, Context context);
    }
}
