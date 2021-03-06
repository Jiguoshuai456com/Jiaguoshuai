package com.bw.movie.adapter.more;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.bean.ComingSoonResultBean;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ProjectName: wutenglongApp2
 * @Package: com.bw.movie.adapter.moreadapter
 * @ClassName: HotAdapter
 * @Description: java类作用描述
 * @CreateDate: 2020/4/21 23:37
 * @UpdateUser: 武腾龙
 * @UpdateDate: 2020/4/21 23:37
 */
public class ComingSoonAdapter extends XRecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<ComingSoonResultBean> result;


    public ComingSoonAdapter(Context context, List<ComingSoonResultBean> result) {
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

        ComingSoonResultBean resultBean = result.get(i);
//        Glide.with(context).load(resultBean.getImageUrl()).into(((MoreHotViewHolder)viewHolder).iv);
        ((MoreHotViewHolder) viewHolder).iv.setImageURI(resultBean.getImageUrl());
        ((MoreHotViewHolder) viewHolder).tvName.setText(resultBean.getName());
        String date = new SimpleDateFormat("MM月dd日").format(
                new java.util.Date(resultBean.getReleaseTime()));
        ((MoreHotViewHolder) viewHolder).tvDaoyan.setText(date+"上映");
        ((MoreHotViewHolder) viewHolder).tvZhuyan.setText(resultBean.getWantSeeNum()+"想看");
//        ((MoreHotViewHolder) viewHolder).tvPingfen.setText("评分："+resultBean.getScore()+"分");
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
