package com.bw.movie.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.adapter.PayAdapter;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.ScheduleBean;
import com.bw.movie.utils.RetUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PayActivity extends BaseActivity {

    @BindView(R.id.tv_xuanze)
    TextView tvXuanze;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.bt)
    Button bt;
    private int cinemaId;

    @Override
    protected int getLayout() {
        return R.layout.activity_pay;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

        Intent intent = getIntent();
        cinemaId = intent.getIntExtra("id", 0);


        if (!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getId(Integer integer){

        RetUtils.getInstance().getApis().getScheduleBean(integer,cinemaId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ScheduleBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ScheduleBean scheduleBean) {

                        List<ScheduleBean.ResultBean> result = scheduleBean.getResult();
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(PayActivity.this, LinearLayoutManager.HORIZONTAL, false);
                        rv.setLayoutManager(linearLayoutManager);

                        PayAdapter payAdapter = new PayAdapter(PayActivity.this,result);
                        rv.setAdapter(payAdapter);

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.bt)
    public void onViewClicked() {

    }
}
