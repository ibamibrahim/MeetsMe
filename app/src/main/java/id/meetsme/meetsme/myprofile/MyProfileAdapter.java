package id.meetsme.meetsme.myprofile;

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

/**
 * Created by Ibam on 8/31/2017.
 */

public class MyProfileAdapter extends RecyclerView.Adapter<MyProfileAdapter.ViewHolder> {

    Context context;
    List<String> dataSet;


    public MyProfileAdapter(Context c) {
        this.context = c;
        dataSet = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_interest_list, parent, false);
        return new MyProfileAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String interest = dataSet.get(position);

        holder.interest.setText(interest);
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public List<String> getDataSet() {
        return dataSet;
    }

    public void setDataSet(List<String> dataSet) {
        this.dataSet = dataSet;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.interest)
        TextView interest;


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
