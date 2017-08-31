package id.meetsme.meetsme.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.meetsme.meetsme.BaseActivity;
import id.meetsme.meetsme.R;
import id.meetsme.meetsme.navigationactivity.NavigationActivity;

/**
 * Created by Ibam on 8/28/2017.
 */

public class LoginActivity extends BaseActivity implements LoginContract.View{
    LoginPresenter mPresenter;

    @BindView(R.id.login_button)
    TextView loginButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.login_button)
    public void login(){
        Intent intent = new Intent(this, NavigationActivity.class);
        startActivity(intent);
    }


    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        this.mPresenter = (LoginPresenter) presenter;
    }
}
