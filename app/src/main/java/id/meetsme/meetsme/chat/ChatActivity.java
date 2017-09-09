package id.meetsme.meetsme.chat;

import android.os.Bundle;
import android.support.annotation.Nullable;

import butterknife.ButterKnife;
import id.meetsme.meetsme.BaseActivity;
import id.meetsme.meetsme.R;

/**
 * Created by Ibam on 8/28/2017.
 */

public class ChatActivity extends BaseActivity implements ChatContract.View {
    ChatPresenter mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);
        isLoggedIn();
    }

    @Override
    public void setPresenter(ChatContract.Presenter presenter) {
        this.mPresenter = (ChatPresenter) presenter;
    }
}
