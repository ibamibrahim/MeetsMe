package id.meetsme.meetsme.chat;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.meetsme.meetsme.BaseActivity;
import id.meetsme.meetsme.R;
import id.meetsme.meetsme.services.models.chat.MessageModel;

/**
 * Created by Ibam on 8/28/2017.
 */

public class ChatActivity extends BaseActivity implements ChatContract.View {
    ChatPresenter mPresenter;
    int user_id;
    String username;
    String occupation;

    @BindView(R.id.chat_name)
    TextView chatName;
    @BindView(R.id.chat_occupation)
    TextView chatOccupation;
    @BindView(R.id.chat_input)
    EditText chatInput;
    @BindView(R.id.chat_send)
    ImageView chatSend;
    @BindView(R.id.chat_recyclerview)
    RecyclerView recyclerView;

    ChatAdapter adapter;
    LinearLayoutManager layoutManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);

        isLoggedIn();
        getIntentData();
        initUI();
        initPresenter();
        mPresenter.loadMessage(getApplicationContext(), user_id);
    }

    private void initUI() {
        chatName.setText(username);
        chatOccupation.setText(occupation);
        initRecyclerView();
    }

    private void initPresenter() {
        mPresenter = new ChatPresenter();
        mPresenter.setView(this);
    }

    private void initRecyclerView() {
        adapter = new ChatAdapter(getApplicationContext());
        layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void updateMessage(List<MessageModel> modelList) {
        adapter.setDataSet(modelList);
        adapter.notifyDataSetChanged();
    }

    private void getIntentData() {
        Intent intent = getIntent();
        user_id = intent.getIntExtra("user_id", -1);
        username = intent.getStringExtra("username");
        occupation = intent.getStringExtra("occupation");
    }

    @Override
    public void hideDialog() {
        super.hideDialog();
    }

    @Override
    public void setPresenter(ChatContract.Presenter presenter) {
        this.mPresenter = (ChatPresenter) presenter;
    }
}
