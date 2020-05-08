package com.bw.movie.adapter.datails;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.bean.FindMoviesDetailBean;
//import com.bw.movie.bean.MovieDataBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;


public class PerformerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List< FindMoviesDetailBean.ResultBean.MovieActorBean> list;

    public PerformerAdapter(Context context, List< FindMoviesDetailBean.ResultBean.MovieActorBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_yanyuan, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder)holder).tv.setText(list.get(position).getName());
        String photo = list.get(position).getPhoto();
        Uri uri = Uri.parse(photo);
        ((ViewHolder)holder).iv.setImageURI(uri);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView tv;
        private final SimpleDraweeView iv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
            tv = itemView.findViewById(R.id.tv);
        }
    }
}
