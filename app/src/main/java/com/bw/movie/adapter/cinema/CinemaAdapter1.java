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
import com.bw.movie.bean.NearbyBean;
import com.bw.movie.bean.RecommendBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CinemaAdapter1 extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<NearbyBean.ResultBean> result;


    public CinemaAdapter1(Context context, List<NearbyBean.ResultBean> result) {
        this.context = context;
        this.result = result;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.item_recommend_adapter, null);
        return new NearbyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        NearbyBean.ResultBean resultBean = result.get(i);
        ((NearbyViewHolder)viewHolder).tvName.setText(resultBean.getName());
        Glide.with(context).load(resultBean.getLogo()).into(((NearbyViewHolder) viewHolder).iv);
        ((NearbyViewHolder) viewHolder).tvDizhi.setText(resultBean.getAddress());
        int distance = resultBean.getDistance();
        distance/=1000.0;
        ((NearbyViewHolder)viewHolder).tvKm.setText(distance+"km");
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    class NearbyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv)
        ImageView iv;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_dizhi)
        TextView tvDizhi;
        @BindView(R.id.tv_km)
        TextView tvKm;
        public NearbyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
