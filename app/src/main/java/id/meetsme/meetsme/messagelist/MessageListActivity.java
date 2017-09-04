package id.meetsme.meetsme.messagelist;

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
import id.meetsme.meetsme.services.models.response.MessageListModel;

/**
 * Created by Ibam on 8/31/2017.
 */

public class MessageListActivity extends BaseFragment implements MessageListContract.View {

    MessageListPresenter mPresenter;
    MessageListAdapter adapter;
    LinearLayoutManager layoutManager;

    @BindView(R.id.chat_list_recycler_view)
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_message_list, container, false);
        ButterKnife.bind(this, view);

        initPresenter();
        initRecyclerView();

        mPresenter.loadMessage();

        return view;
    }

    public void initPresenter() {
        mPresenter = new MessageListPresenter();
        mPresenter.setView(this);
    }

    public void initRecyclerView() {
        adapter = new MessageListAdapter(getContext());
        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void updateMessageList(List<MessageListModel> list) {
        adapter.setDataSet(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setPresenter(MessageListContract.Presenter presenter) {

    }


}
