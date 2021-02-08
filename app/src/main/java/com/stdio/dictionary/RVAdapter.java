package com.stdio.dictionary;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.DataViewHolder> {

    public static class DataViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;

        DataViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.text);
        }
    }

    List<DataModel> dataList;
    Context mContext;

    RVAdapter(List<DataModel> dataList, Context context) {
        this.dataList = dataList;
        this.mContext = context;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public DataViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_item, viewGroup, false);
        DataViewHolder pvh = new DataViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(DataViewHolder dataViewHolder, final int position) {
        dataViewHolder.tvTitle.setText(dataList.get(position).title);
        dataViewHolder.tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DetailActivity.name = dataList.get(position).title;
                DetailActivity.description = dataList.get(position).description;
                mContext.startActivity(new Intent(mContext, DetailActivity.class));
            }
        });
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}