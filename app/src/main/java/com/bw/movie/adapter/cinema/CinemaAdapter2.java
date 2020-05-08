package com.bw.movie.adapter.cinema;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.bean.RecommendBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CinemaAdapter2 extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<RecommendBean.ResultBean> result;


    public CinemaAdapter2(Context context, List<RecommendBean.ResultBean> result) {
        this.context = context;
        this.result = result;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.item_recommend_adapter, null);
        return new RecommendViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        RecommendBean.ResultBean resultBean = result.get(i);
        ((RecommendViewHolder)viewHolder).tvName.setText(resultBean.getName());
        Glide.with(context).load(resultBean.getLogo()).into(((RecommendViewHolder) viewHolder).iv);
        ((RecommendViewHolder) viewHolder).tvDizhi.setText(resultBean.getAddress());
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    class RecommendViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv)
        ImageView iv;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_dizhi)
        TextView tvDizhi;
        public RecommendViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
