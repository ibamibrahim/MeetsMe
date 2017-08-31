package id.meetsme.meetsme.register;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.meetsme.meetsme.BaseActivity;
import id.meetsme.meetsme.R;
import id.meetsme.meetsme.createprofile.CreateProfileActivity;
import id.meetsme.meetsme.login.LoginActivity;

/**
 * Created by Ibam on 8/28/2017.
 */

public class RegisterActivity extends BaseActivity implements RegisterContract.View {
    RegisterPresenter mPresenter;

    @BindView(R.id.sign_up_button)
    TextView signUpButton;

    @BindView(R.id.prompt_sign_in)
    TextView promptSignIn;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.sign_up_button)
    public void signUp() {
        Intent intent = new Intent(this, CreateProfileActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.prompt_sign_in)
    public void signIn() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void setPresenter(RegisterContract.Presenter presenter) {
        this.mPresenter = (RegisterPresenter) presenter;
    }
}
