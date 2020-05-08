package com.bw.movie.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.activity.DetaildActivity;
import com.bw.movie.bean.ComingSoonResultBean;
import com.bw.movie.bean.ReserveBean;
import com.bw.movie.utils.RetUtils;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class ComingSoonAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<ComingSoonResultBean> result;

    public ComingSoonAdapter(Context context, List<ComingSoonResultBean> result) {
        this.context = context;
        this.result = result;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.item_comingsoon_adapter, null);
        ComViewHolder hotViewHolder = new ComViewHolder(view);
        return hotViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, int i) {

        final ComingSoonResultBean resultBean = result.get(i);
//        Glide.with(context).load(resultBean.getImageUrl()).into(((ComViewHolder)viewHolder).iv);
        ((ComViewHolder) viewHolder).iv.setImageURI(resultBean.getImageUrl());
//        ((ComViewHolder) viewHolder).tv_data.setText(resultBean.getReleaseTime()+"日上映");
        String date = new SimpleDateFormat("MM月dd日").format(
                new java.util.Date(result.get(i).getReleaseTime()));
        ((ComViewHolder)viewHolder).tv_data.setText(date+"上映");
//        Log.i("xx",resultBean.getName());
        ((ComViewHolder) viewHolder).tv_name.setText(resultBean.getName());
        ((ComViewHolder) viewHolder).tv.setText(resultBean.getWantSeeNum()+"人想看");
//        final int movieId = resultBean.getMovieId();
        final int movieId = new Long(result.get(i).getMovieId()).intValue();
        ((ComViewHolder) viewHolder).re.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetaildActivity.class);
                intent.putExtra("moviedId",movieId);
                context.startActivity(intent);
            }
        });

        ((ComViewHolder) viewHolder).tvShopp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RetUtils.getInstance().getApis().getReserveBean(movieId)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<ReserveBean>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(ReserveBean reserveBean) {

                                ((ComViewHolder) viewHolder).tvShopp.setText("已预约");
                                Resources resources = context.getResources();
                                Drawable dimension = resources.getDrawable(R.color.desert);
                                ((ComViewHolder) viewHolder).tvShopp.setBackgroundDrawable(dimension);
                            }

                            @Override
                            public void onError(Throwable e) {
                                Toast.makeText(context, "预约失败", Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            }
        });
    }

    @Override
    public int getItemCount() {

        return result.size();
    }

    class ComViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv)
        SimpleDraweeView iv;
        @BindView(R.id.tv)
        TextView tv;
        @BindView(R.id.tv_shopp)
        TextView tvShopp;
        @BindView(R.id.tv_name)
        TextView tv_name;
        @BindView(R.id.tv_data)
        TextView tv_data;
        @BindView(R.id.re)
        RelativeLayout re;
        public ComViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
