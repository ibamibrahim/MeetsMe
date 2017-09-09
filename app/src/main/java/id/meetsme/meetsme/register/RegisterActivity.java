package id.meetsme.meetsme.register;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.meetsme.meetsme.BaseActivity;
import id.meetsme.meetsme.R;
import id.meetsme.meetsme.createprofile.CreateProfileActivity;
import id.meetsme.meetsme.login.LoginActivity;
import id.meetsme.meetsme.navigationactivity.NavigationActivity;
import id.meetsme.meetsme.services.LocalServices;
import id.meetsme.meetsme.services.models.response.register.RegisterResponseModel;

/**
 * Created by Ibam on 8/28/2017.
 */

public class RegisterActivity extends BaseActivity implements RegisterContract.View {

    private static final String TAG = "RegisterActivity";
    RegisterPresenter mPresenter;

    @BindView(R.id.sign_up_button)
    TextView signUpButton;

    @BindView(R.id.prompt_sign_in)
    TextView promptSignIn;

    @BindView(R.id.register_email)
    EditText inputEmail;

    @BindView(R.id.register_name)
    EditText inputName;

    @BindView(R.id.register_password)
    EditText inputPassword;

    @BindView(R.id.register_username)
    EditText inputUsername;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        ButterKnife.bind(this);

        isLogIn();
        initPresenter();
        initDummyData();

    }

    private void isLogIn() {
        String userDetail = LocalServices.getUserDetail(getApplicationContext());
        if (userDetail != null) {
            Intent intent = new Intent(this, NavigationActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void initDummyData() {
        inputEmail.setText("ibrahim52@ui.ac.id");
        inputName.setText(randomString(8));
        inputPassword.setText("ibamibamkeren");
        inputUsername.setText(randomString(6));
    }

    private java.lang.String randomString(int length) {
        return UUID.randomUUID().toString().substring(0, length);
    }

    private void initPresenter() {
        mPresenter = new RegisterPresenter();
        mPresenter.setView(this);
    }

    @OnClick(R.id.sign_up_button)
    public void signUp() {
        String email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();
        String fullName = inputName.getText().toString();
        String username = inputUsername.getText().toString();
        Log.i(TAG, "signUp: signUp with email and password");

        signUpWithEmail(username, email, password, fullName);
    }

    private void signUpWithEmail(String username, String email, String password, String fullName) {
        showProgressDialog("Loading...");
        mPresenter.signUp(username, email, password, fullName, getApplicationContext());
    }

    public void signUpStatus(boolean status, String message, RegisterResponseModel model) {
        if (status) {
            Intent intent = new Intent(this, CreateProfileActivity.class);
            startActivity(intent);
            finish();
        } else {
            showToast(message);
        }
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
