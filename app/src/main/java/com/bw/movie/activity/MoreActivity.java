package com.bw.movie.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.fragment.more.ComingSoonFragment;
import com.bw.movie.fragment.more.HotFragment;
import com.bw.movie.fragment.more.ReleaseFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class MoreActivity extends BaseActivity {


    @BindView(R.id.tab)
    TabLayout tb;
    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.iv_shape)
    ImageView ivShape;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    private ArrayList<String> tab = new ArrayList<>();
    private ArrayList<Fragment> list = new ArrayList<>();

    @Override
    protected int getLayout() {
        return R.layout.activity_more;
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
        tab.add("正在热映");
        tab.add("即将上映");
        tab.add("热门电影");
        list.add(new HotFragment());
        list.add(new ComingSoonFragment());
        list.add(new ReleaseFragment());
        tb.addTab(tb.newTab().setText(tab.get(0)));
        tb.addTab(tb.newTab().setText(tab.get(1)));
        tb.addTab(tb.newTab().setText(tab.get(2)));
        MyAdapter myAdapter = new MyAdapter(getSupportFragmentManager());
        vp.setAdapter(myAdapter);
        tb.setupWithViewPager(vp);
    }
    @OnClick({R.id.iv_shape, R.id.iv_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_shape:
                break;
            case R.id.iv_search:
                Intent intent = new Intent(MoreActivity.this, SearchActivity.class);
                startActivity(intent);
                break;
        }
    }

    class MyAdapter extends FragmentPagerAdapter {
        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return list.get(i);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return tab.get(position);
        }
    }
}
