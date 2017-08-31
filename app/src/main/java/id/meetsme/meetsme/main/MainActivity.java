package id.meetsme.meetsme.main;

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

public class MainActivity extends BaseFragment implements MainActivityContract.View{
    MainActivityPresenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void setPresenter(MainActivityContract.Presenter presenter) {
        this.mPresenter = (MainActivityPresenter) presenter;
    }
}
