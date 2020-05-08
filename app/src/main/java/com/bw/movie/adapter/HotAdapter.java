package com.bw.movie.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.activity.DetaildActivity;
import com.bw.movie.bean.ReleaseBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HotAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<ReleaseBean.ResultBean> result;

    public HotAdapter(Context context,  List<ReleaseBean.ResultBean> result) {
        this.context = context;
        this.result = result;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.item_hot_adapter, null);
        HotViewHolder hotViewHolder = new HotViewHolder(view);
        return hotViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

//        HotBean.ResultBean resultBean = result.get(i);
        ReleaseBean.ResultBean resultBean = result.get(i);
//        Glide.with(context).load(resultBean.getImageUrl()).into(((HotViewHolder)viewHolder).iv);
        ((HotViewHolder) viewHolder).iv.setImageURI(resultBean.getImageUrl());
        ((HotViewHolder) viewHolder).tv.setText(resultBean.getScore()+"分");
        Log.i("xx",resultBean.getName());
        ((HotViewHolder) viewHolder).tv_name.setText(resultBean.getName());
        final int movieId = resultBean.getMovieId();
        ((HotViewHolder) viewHolder).ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetaildActivity.class);
                intent.putExtra("moviedId",movieId);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {

        return result.size();
    }

    class HotViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv)
        SimpleDraweeView iv;
        @BindView(R.id.tv)
        TextView tv;
        @BindView(R.id.tv_shopp)
        TextView tvShopp;
        @BindView(R.id.tv_name)
        TextView tv_name;
        @BindView(R.id.ll)
        LinearLayout ll;
        public HotViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
