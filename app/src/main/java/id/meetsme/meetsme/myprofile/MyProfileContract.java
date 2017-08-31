package id.meetsme.meetsme.myprofile;

import java.util.List;

import id.meetsme.meetsme.BasePresenter;
import id.meetsme.meetsme.BaseView;
import id.meetsme.meetsme.services.models.MessageListModel;

/**
 * Created by Ibam on 8/28/2017.
 */

public interface MyProfileContract {

    interface View extends BaseView<Presenter> {
        void updateInterestList(List<String> list);
    }

    interface Presenter extends BasePresenter {
        void loadInterests();
    }
}
