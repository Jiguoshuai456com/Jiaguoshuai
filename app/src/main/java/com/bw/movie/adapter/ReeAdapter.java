package com.bw.movie.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.activity.DetaildActivity;
import com.bw.movie.bean.HotBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ReeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<HotBean.ResultBean> result;


    public ReeAdapter(Context context, List<HotBean.ResultBean> result) {

        this.context = context;
        this.result = result;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = View.inflate(context, R.layout.item_release_adapter, null);
        ReleaseViewHolder releaseViewHolder = new ReleaseViewHolder(view);
        return releaseViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, int i) {

//        ReleaseBean.ResultBean resultBean = result.get(i);

        HotBean.ResultBean resultBean = result.get(i);
        String horizontalImage = resultBean.getHorizontalImage();
//        Glide.with(context).load(horizontalImage).into(((ReleaseViewHolder)viewHolder).iv);
        ((ReleaseViewHolder) viewHolder).iv.setImageURI(resultBean.getHorizontalImage());
        ((ReleaseViewHolder) viewHolder).tvName.setText(resultBean.getName());
        ((ReleaseViewHolder) viewHolder).tv.setText(resultBean.getScore()+"分");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, true);
        ((ReleaseViewHolder) viewHolder).rv.setLayoutManager(linearLayoutManager);
        ReeAdapter2 releaseAdapter2 = new ReeAdapter2(context, result);
        ((ReleaseViewHolder) viewHolder).rv.setAdapter(releaseAdapter2);
        final int movieId = resultBean.getMovieId();
        ((ReleaseViewHolder) viewHolder).ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ReleaseViewHolder) viewHolder).ll.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, DetaildActivity.class);
                        intent.putExtra("moviedId",movieId);
                        context.startActivity(intent);
                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    class ReleaseViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv)
        SimpleDraweeView iv;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv)
        TextView tv;
        @BindView(R.id.tv_shopp)
        TextView tvShopp;
        @BindView(R.id.rv)
        RecyclerView rv;
        @BindView(R.id.ll)
        LinearLayout ll;
        public ReleaseViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
