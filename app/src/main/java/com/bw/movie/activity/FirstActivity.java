package com.bw.movie.activity;

import android.content.Intent;
import android.widget.ImageView;


import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;

import butterknife.BindView;
import butterknife.OnClick;

public class FirstActivity extends BaseActivity {


    @BindView(R.id.iv_guide)
    ImageView ivGuide;

    @Override
    protected int getLayout() {
        return R.layout.activity_guide;
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

    }

    @OnClick(R.id.iv_guide)
    public void onViewClicked() {
        //跳转到登录页面
        startActivity(new Intent(FirstActivity.this, LoginActivity.class));
        //跳转完之后关闭
        finish();
    }
}
