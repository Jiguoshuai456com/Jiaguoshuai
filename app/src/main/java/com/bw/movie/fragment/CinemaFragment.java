package com.bw.movie.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.amap.api.maps2d.MapView;
import com.bw.movie.R;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.fragment.cinema.AreaFragment;
import com.bw.movie.fragment.cinema.NearbyFragment;
import com.bw.movie.fragment.cinema.RecommendFragment;
import java.util.ArrayList;
import butterknife.BindView;


public class CinemaFragment extends BaseFragment {
    @BindView(R.id.map)
    MapView map;
    @BindView(R.id.iv_wei)
    TextView ivWei;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.vp)
    ViewPager vp;
    private ArrayList<String> strings;
    private ArrayList<Fragment> fragments;

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.cinema_fragment;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {
        strings = new ArrayList<>();
        fragments = new ArrayList<>();

        strings.add("推荐影院");
        strings.add("附近影院");
        strings.add("海淀区");

        fragments.add(new RecommendFragment());
        fragments.add(new NearbyFragment());
        fragments.add(new AreaFragment());

        tab.addTab(tab.newTab().setText(strings.get(0)));
        tab.addTab(tab.newTab().setText(strings.get(1)));
        tab.addTab(tab.newTab().setText(strings.get(2)));

        MyAdapter1 myAdapter1 = new MyAdapter1(getChildFragmentManager());
        vp.setAdapter(myAdapter1);
        tab.setupWithViewPager(vp);


    }

    private class MyAdapter1 extends FragmentPagerAdapter {
        public MyAdapter1(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return fragments.get(i);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return strings.get(position);
        }
    }
}
