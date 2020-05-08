package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.bean.FindMoviewByKeWordBean;
import com.bw.movie.bean.HotBean;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SearchAdapter extends XRecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<FindMoviewByKeWordBean.ResultBean> result;


    public SearchAdapter(Context context,  List<FindMoviewByKeWordBean.ResultBean> result) {
        this.context = context;
        this.result = result;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.more_hot_adapter, null);
        MoreHotViewHolder moreHotViewHolder = new MoreHotViewHolder(view);
        return moreHotViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

//        HotBean.ResultBean resultBean = result.get(i);
        FindMoviewByKeWordBean.ResultBean resultBean = result.get(i);
//        Glide.with(context).load(resultBean.getImageUrl()).into(((MoreHotViewHolder)viewHolder).iv);
        ((MoreHotViewHolder) viewHolder).iv.setImageURI(resultBean.getImageUrl());
        ((MoreHotViewHolder) viewHolder).tvName.setText(resultBean.getName());
        ((MoreHotViewHolder) viewHolder).tvDaoyan.setText("导演："+resultBean.getDirector());
        ((MoreHotViewHolder) viewHolder).tvZhuyan.setText("主演："+resultBean.getStarring());
        ((MoreHotViewHolder) viewHolder).tvPingfen.setText("评分："+resultBean.getScore()+"分");
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    class MoreHotViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv)
        SimpleDraweeView iv;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_daoyan)
        TextView tvDaoyan;
        @BindView(R.id.tv_zhuyan)
        TextView tvZhuyan;
        @BindView(R.id.tv_pingfen)
        TextView tvPingfen;
        @BindView(R.id.tv_shopp)
        TextView tvShopp;
        public MoreHotViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
