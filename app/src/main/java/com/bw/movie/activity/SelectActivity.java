package com.bw.movie.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.VideoView;

import com.bw.movie.R;
import com.bw.movie.adapter.SelectAdapter;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.InfoByRegionBean;
import com.bw.movie.contract.IHomeRegionContract;
import com.bw.movie.fragment.details.StillFragment;
import com.bw.movie.presenter.IHomeRegionPresenter;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;

public class SelectActivity extends BaseActivity implements IHomeRegionContract.IView {


    @BindView(R.id.tv_pingfen)
    TextView tvScore;
    @BindView(R.id.tv_direct)
    TextView tvDirect;
    @BindView(R.id.iv)
    VideoView iv;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.rv)
    RecyclerView rv;
    @Override
    protected int getLayout() {
        return R.layout.activity_select;
    }

    @Override
    protected BasePresenter initPresenter() {
        return new IHomeRegionPresenter(this);
    }

    @Override
    protected void initView() {

    }
    @Override
    protected void initData() {
        Intent intent = getIntent();
        String ima = intent.getStringExtra("ima");
        String score = intent.getStringExtra("score");
        String dao = intent.getStringExtra("dao");
        String name = intent.getStringExtra("name");
        String time = intent.getStringExtra("time");
        int id = intent.getIntExtra("id", 0);
        EventBus.getDefault().post(id);
        tvName.setText(name);
        tvScore.setText(score+"分");
        tvTime.setText(time);
        tvDirect.setText(dao);
        Uri uri = Uri.parse(ima);
        iv.setVideoURI(uri);
        iv.start();
        BasePresenter presenter = getPresenter();
        if (presenter instanceof IHomeRegionPresenter){
            ((IHomeRegionPresenter) presenter).getInfoByRegion(id,1,1,10);
        }
    }

    @Override
    public void onInfoByRegionSuccess(InfoByRegionBean infoByRegionBean) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SelectActivity.this, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(linearLayoutManager);
        List<InfoByRegionBean.ResultBean> result = infoByRegionBean.getResult();
        SelectAdapter selectAdapter = new SelectAdapter(this,result);
        rv.setAdapter(selectAdapter);
        rv.addItemDecoration(new StillFragment.SpaceItemDecoration(20));
    }

    @Override
    public void onInfoByRegionFailure(String str) {

    }
}
