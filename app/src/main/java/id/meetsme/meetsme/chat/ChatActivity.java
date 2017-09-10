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
import butterknife.OnClick;
import id.meetsme.meetsme.BaseActivity;
import id.meetsme.meetsme.R;
import id.meetsme.meetsme.helper.Helper;
import id.meetsme.meetsme.services.LocalServices;
import id.meetsme.meetsme.services.models.chat.MessageModel;
import id.meetsme.meetsme.services.models.response.login.LoginResponseModel;

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

    private void getIntentData() {
        Intent intent = getIntent();
        user_id = intent.getIntExtra("user_id", -1);
        username = intent.getStringExtra("name");
        occupation = intent.getStringExtra("occupation");
    }

    @OnClick(R.id.chat_send)
    public void sendMessage() {
        String message = chatInput.getText().toString();
        int recipient_id = user_id;
        int sender_id = LocalServices.getUserId(getApplicationContext());
        chatInput.setText("");
        sendMessage(message, recipient_id, sender_id);
    }

    private void sendMessage(String message, int recipient_id, int sender_id) {
        mPresenter.sendMessage(sender_id, recipient_id, message, getApplicationContext());
        String userDetail = LocalServices.getUserDetail(getApplicationContext());
        LoginResponseModel user = Helper.jsonToObject(userDetail, LoginResponseModel.class);
        //addMessage(new MessageModel(user.getUser().getUser().getFirstName(), message));
    }

    @Override
    public void updateMessage(List<MessageModel> modelList) {
        adapter.setDataSet(modelList);
        adapter.notifyDataSetChanged();
    }

    private void addMessage(MessageModel newMessage) {
        adapter.addData(newMessage);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void notifyAdapter() {
        adapter.notifyDataSetChanged();
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
