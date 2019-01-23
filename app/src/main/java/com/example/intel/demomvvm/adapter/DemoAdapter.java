package com.example.intel.demomvvm.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.intel.demomvvm.R;
import com.example.intel.demomvvm.model.RowsBean;

import java.util.ArrayList;

public class DemoAdapter extends RecyclerView.Adapter<DemoAdapter.DemoViewHolder> {


    Context context;
    ArrayList<RowsBean> rowsBeans;

    public DemoAdapter(Context context) {

        this.context = context;
        rowsBeans = new ArrayList<>();
    }

    @NonNull
    @Override
    public DemoViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, viewGroup, false);
        return new DemoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DemoViewHolder viewHolder, int i) {

        viewHolder.bind(rowsBeans.get(i));
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
    }

    @Override
    public int getItemCount() {
        return rowsBeans.size();
    }

    public void setDatas(ArrayList<RowsBean> modelDemos) {
        this.rowsBeans = new ArrayList<>();
        this.rowsBeans = modelDemos;
        notifyDataSetChanged();

    }

    public class DemoViewHolder extends RecyclerView.ViewHolder {

        private final TextView txtTitle, txtDescription;

        public DemoViewHolder(View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtDescription = itemView.findViewById(R.id.txtDescription);
        }

        void bind(RowsBean fact) {

            if (TextUtils.isEmpty(fact.getTitle())) {
                txtTitle.setText(context.getString(R.string.no_title));
            } else {
                txtTitle.setText(fact.getTitle());
            }

            // description
            if (!TextUtils.isEmpty(fact.getDescription())) {
                //txtDescription.setVisibility(View.VISIBLE);
                txtDescription.setText(fact.getDescription());
            } else {
                txtDescription.setText(context.getString(R.string.no_description));

                // txtDescription.setVisibility(View.GONE);
            }



        }
    }
}
