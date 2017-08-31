package id.meetsme.meetsme.myprofile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.meetsme.meetsme.BaseFragment;
import id.meetsme.meetsme.R;

/**
 * Created by Ibam on 8/31/2017.
 */

public class MyProfileActivity extends BaseFragment implements MyProfileContract.View {

    MyProfilePresenter mPresenter;
    MyProfileAdapter adapter;
    LinearLayoutManager layoutManager;

    @BindView(R.id.interest_recycler_view)
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_my_profile, container, false);
        ButterKnife.bind(this, view);

        initPresenter();
        initRecyclerView();
        mPresenter.loadInterests();
        return view;
    }

    public void initPresenter() {
        mPresenter = new MyProfilePresenter();
        mPresenter.setView(this);
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

    @Override
    public void setPresenter(MyProfileContract.Presenter presenter) {

    }
}
