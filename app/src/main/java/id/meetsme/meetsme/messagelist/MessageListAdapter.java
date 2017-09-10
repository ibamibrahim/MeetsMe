package id.meetsme.meetsme.messagelist;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.meetsme.meetsme.R;
import id.meetsme.meetsme.chat.ChatActivity;
import id.meetsme.meetsme.services.models.chat.ChatModel;

/**
 * Created by Ibam on 8/31/2017.
 */

public class MessageListAdapter extends RecyclerView.Adapter<MessageListAdapter.ViewHolder> {

    Context context;
    List<ChatModel> dataSet;

    public MessageListAdapter(Context c) {
        this.context = c;
        dataSet = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_chat_list, parent, false);
        return new MessageListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ChatModel model = dataSet.get(position);

        holder.senderName.setText(model.getUsername());
        holder.chatCount.setText("0");
        holder.lastMessage.setText(model.getLastMessage());
        holder.timeStamp.setText(model.getLastTimStamp());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public List<ChatModel> getDataSet() {
        return dataSet;
    }

    public void setDataSet(List<ChatModel> dataSet) {
        this.dataSet = dataSet;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.sender_name)
        public TextView senderName;

        @BindView(R.id.last_message)
        public TextView lastMessage;

        @BindView(R.id.time_stamp)
        public TextView timeStamp;

        @BindView(R.id.chat_count)
        public TextView chatCount;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View itemView) {
                    if (getAdapterPosition() != RecyclerView.NO_POSITION) {
                        Context c = itemView.getContext();
                        ChatModel model = dataSet.get(getAdapterPosition());
                        Intent intent = new Intent(c, ChatActivity.class);
                        intent.putExtra("user_id", model.getUser_id());
                        intent.putExtra("name", model.getUsername());
                        intent.putExtra("occupation", "Software Engineer");
                        Toast.makeText(c, model.getUser_id() + "", Toast.LENGTH_SHORT).show();
                        c.startActivity(intent);
                    }
                }
            });
        }
    }

}
