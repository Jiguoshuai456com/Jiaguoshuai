package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.bw.movie.activity.PayActivity;
import com.bw.movie.bean.ScheduleBean;

import java.util.List;


public class PayAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<ScheduleBean.ResultBean> result;
    public PayAdapter(Context context, List<ScheduleBean.ResultBean> result) {
        this.context = context;
        this.result = result;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return result.size();
    }
}
