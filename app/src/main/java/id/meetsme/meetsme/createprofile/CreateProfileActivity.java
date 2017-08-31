package id.meetsme.meetsme.createprofile;

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

public class CreateProfileActivity extends BaseActivity implements CreateProfileContract.View{
    CreateProfilePresenter mPresenter;

    @BindView(R.id.join_now_button)
    TextView joinNowButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.join_now_button)
    public void joinNow(){
        Intent intent = new Intent(this, NavigationActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void setPresenter(CreateProfileContract.Presenter presenter) {
        this.mPresenter = (CreateProfilePresenter) presenter;
    }
}
