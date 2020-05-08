package com.bw.movie.adapter.datails;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.bean.FindAllMovieCommentBean;
import com.bw.movie.view.RatingBar;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CinecismAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<FindAllMovieCommentBean.ResultBean> result;


    public CinecismAdapter(Context context, List<FindAllMovieCommentBean.ResultBean> result) {
        this.context = context;
        this.result = result;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.comment_adapter, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        FindAllMovieCommentBean.ResultBean resultBean = result.get(i);
        ((ViewHolder)viewHolder).tvName.setText(resultBean.getCommentUserName());

        ((ViewHolder) viewHolder).sdv.setImageURI(resultBean.getCommentHeadPic());

        String date = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(
                new java.util.Date(resultBean.getCommentTime()));
        ((ViewHolder) viewHolder).tvTime.setText(date);

        ((ViewHolder) viewHolder).tvFenshu.setText(resultBean.getScore()+"分");

        ((ViewHolder) viewHolder).tvPinglun.setText(resultBean.getCommentContent());

        ((ViewHolder) viewHolder).tvDianzan.setText(resultBean.getGreatNum()+"人点赞");

        ((ViewHolder) viewHolder).tvHuifu.setText(resultBean.getReplyNum()+"人回复了");

//        ((ViewHolder) viewHolder).sdvTouxiang.setImageURI();



    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.sdv)
        SimpleDraweeView sdv;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_dianzan)
        TextView tvDianzan;
        @BindView(R.id.xingxing)
        RatingBar xingxing;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_pinglun)
        TextView tvPinglun;
        @BindView(R.id.sdv_zan)
        ImageView sdvZan;
        @BindView(R.id.tv_huifu)
        TextView tvHuifu;
        @BindView(R.id.tv_fenshu)
        TextView tvFenshu;
        @BindView(R.id.sdv_touxiang)
        SimpleDraweeView sdvTouxiang;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
