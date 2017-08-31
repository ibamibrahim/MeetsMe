package id.meetsme.meetsme.chat;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import id.meetsme.meetsme.BaseActivity;
import id.meetsme.meetsme.BaseFragment;
import id.meetsme.meetsme.R;

/**
 * Created by Ibam on 8/28/2017.
 */

public class ChatActivity extends BaseFragment implements ChatContract.View{
    ChatPresenter mPresenter;

    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_chat, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void setPresenter(ChatContract.Presenter presenter) {
        this.mPresenter = (ChatPresenter) presenter;
    }
}
