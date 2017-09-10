package id.meetsme.meetsme.chat;

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
import id.meetsme.meetsme.services.models.chat.MessageModel;

/**
 * Created by Ibam on 9/10/2017.
 */

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {
    Context context;
    List<MessageModel> dataSet;


    public ChatAdapter(Context c) {
        this.context = c;
        dataSet = new ArrayList<>();
    }

    @Override
    public ChatAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_message, parent, false);
        return new ChatAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ChatAdapter.ViewHolder holder, int position) {
        MessageModel model = dataSet.get(position);
        holder.messageContent.setText(model.getContent());
        holder.messageSender.setText(model.getSender());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public List<MessageModel> getDataSet() {
        return dataSet;
    }

    public void setDataSet(List<MessageModel> dataSet) {
        this.dataSet = dataSet;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.message_sender)
        public TextView messageSender;
        @BindView(R.id.message_content)
        public TextView messageContent;


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
