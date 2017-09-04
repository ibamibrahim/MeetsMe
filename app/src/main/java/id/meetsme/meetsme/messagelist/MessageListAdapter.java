package id.meetsme.meetsme.messagelist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.meetsme.meetsme.R;
import id.meetsme.meetsme.services.models.response.MessageListModel;

/**
 * Created by Ibam on 8/31/2017.
 */

public class MessageListAdapter extends RecyclerView.Adapter<MessageListAdapter.ViewHolder> {

    Context context;
    List<MessageListModel> dataSet;


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
        MessageListModel model = dataSet.get(position);

        holder.senderName.setText(model.getName());
        holder.chatCount.setText(model.getUnread_count());
        holder.lastMessage.setText(model.getLast_message());
        holder.timeStamp.setText(model.getTime_stamp());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public List<MessageListModel> getDataSet() {
        return dataSet;
    }

    public void setDataSet(List<MessageListModel> dataSet) {
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
        }
    }

    public class CallListener implements View.OnClickListener {

        Context context;
        java.lang.String number;

        public CallListener(Context context, java.lang.String number) {
            this.context = context;
            this.number = number;
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {

            }
        }
    }
}
