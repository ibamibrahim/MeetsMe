package id.meetsme.meetsme.myprofile;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.meetsme.meetsme.BaseFragment;
import id.meetsme.meetsme.R;
import id.meetsme.meetsme.register.RegisterActivity;
import id.meetsme.meetsme.services.LocalServices;

/**
 * Created by Ibam on 8/31/2017.
 */

public class MyProfileActivity extends BaseFragment implements MyProfileContract.View {

    MyProfilePresenter mPresenter;
    MyProfileAdapter adapter;
    LinearLayoutManager layoutManager;

    @BindView(R.id.interest_recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.profile_city)
    TextView profileCity;
    @BindView(R.id.profile_job)
    TextView profileJob;
    @BindView(R.id.profile_name)
    TextView profileName;
    @BindView(R.id.profile_logout)
    TextView logOutButton;

    String[] interestList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_my_profile, container, false);
        ButterKnife.bind(this, view);

        initPresenter();
        initRecyclerView();
        initArray();
        mPresenter.loadUserDetail(getActivity());

        return view;
    }

    private void initArray() {
        interestList = new String[]{"Football", "Taekwondo", "Formula One", "Basketball", "Badminton", "Teaching", "Education", "Environment", "Renewable", "Energy", "Artificial Intelligence", "Public Speaking", "Politic"};
    }

    public void initPresenter() {
        mPresenter = new MyProfilePresenter();
        mPresenter.setView(this);
    }

    @OnClick(R.id.profile_logout)
    public void logOut() {
        LocalServices.clearLocalData(getContext());
        Intent intent = new Intent(getContext(), RegisterActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void initRecyclerView() {
        adapter = new MyProfileAdapter(getContext());
        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void updateInterestList(List<String> list) {
        adapter.setDataSet(list);
        adapter.notifyDataSetChanged();
    }

    public void updateProfile(String name, String occupation, String city) {
        profileName.setText(name);
        profileJob.setText(occupation);
        profileCity.setText(city);
    }

    @Override
    public void setPresenter(MyProfileContract.Presenter presenter) {

    }
}
