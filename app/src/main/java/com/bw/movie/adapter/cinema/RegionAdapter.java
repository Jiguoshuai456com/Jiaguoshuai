package com.bw.movie.adapter.cinema;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.bean.RegionBean;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RegionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<RegionBean.ResultBean> result;


    public RegionAdapter(Context context, List<RegionBean.ResultBean> result) {
        this.context = context;
        this.result = result;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.item_region_adapter, null);
        return new RegionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        final RegionBean.ResultBean resultBean = result.get(i);
        ((RegionViewHolder)viewHolder).tvName.setText(resultBean.getRegionName());
        ((RegionViewHolder) viewHolder).ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(resultBean.getRegionId());
            }
        });

    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    class RegionViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.ll)
        LinearLayout ll;
        public RegionViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
