package id.meetsme.meetsme.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.meetsme.meetsme.BaseActivity;
import id.meetsme.meetsme.R;
import id.meetsme.meetsme.navigationactivity.NavigationActivity;
import id.meetsme.meetsme.services.models.response.login.LoginResponseModel;

/**
 * Created by Ibam on 8/28/2017.
 */

public class LoginActivity extends BaseActivity implements LoginContract.View {
    private static final String TAG = "LoginActivity";
    LoginPresenter mPresenter;
    @BindView(R.id.login_button)
    TextView loginButton;
    @BindView(R.id.login_email)
    EditText inputEmail;
    @BindView(R.id.login_password)
    EditText inputPasword;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        initPresenter();
    }

    public void initPresenter() {
        mPresenter = new LoginPresenter();
        mPresenter.setView(this);
    }

    @OnClick(R.id.login_button)
    public void login() {
        String email = inputEmail.getText().toString();
        String password = inputPasword.getText().toString();
        Log.i(TAG, "login: Login with email");
        loginWithEmailAndPass(email, password);
    }

    private void loginWithEmailAndPass(String email, String password) {
        showProgressDialog("Loading...");
        mPresenter.login(email, password, getApplicationContext());
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        this.mPresenter = (LoginPresenter) presenter;
    }

    @Override
    public void loginStatus(boolean status, String message, @Nullable LoginResponseModel responseModel) {
        hideDialog();
        if (status) {
            Intent intent = new Intent(this, NavigationActivity.class);
            startActivity(intent);
            finish();
        } else {
            showToast("Login Failed! " + message);
        }
    }
}
