package com.bw.movie.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.activity.PayActivity;
import com.bw.movie.bean.InfoByRegionBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SelectAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<InfoByRegionBean.ResultBean> result;


    public SelectAdapter(Context context, List<InfoByRegionBean.ResultBean> result) {
        this.context = context;
        this.result = result;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = View.inflate(context, R.layout.select_adapter, null);
        return new SelectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        final InfoByRegionBean.ResultBean resultBean = result.get(i);
        ((SelectViewHolder)viewHolder).tvDizhi.setText(resultBean.getAddress());
        ((SelectViewHolder) viewHolder).tvName.setText(resultBean.getName());
        Glide.with(context).load(resultBean.getLogo()).into(((SelectViewHolder) viewHolder).iv);
        ((SelectViewHolder) viewHolder).ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, PayActivity.class);

                intent.putExtra("id",resultBean.getCinemaId());

                context.startActivity(intent);

            }
        });

    }
    @Override
    public int getItemCount() {
        return result.size();
    }

    class SelectViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv)
        ImageView iv;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_dizhi)
        TextView tvDizhi;
        @BindView(R.id.ll)
        LinearLayout ll;
        public SelectViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
