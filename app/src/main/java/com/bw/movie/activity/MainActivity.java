package com.bw.movie.activity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

//import com.bawei.wutenglongapp.R;
import com.bw.movie.R;
import com.bw.movie.fragment.CinemaFragment;
import com.bw.movie.fragment.HomeFragment;
import com.bw.movie.fragment.MyFragment;

import java.util.ArrayList;

/**
 *
 */
public class MainActivity extends AppCompatActivity {

    private ViewPager vp;
    private TabLayout tb;
    private ArrayList<String> tab= new ArrayList<>();
    private ArrayList<Fragment> list= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vp = findViewById(R.id.vp);
        tb = findViewById(R.id.tab);
        tab.add("电影");
        tab.add("影院");
        tab.add("我的");
        tb.addTab(tb.newTab().setText(""));
        tb.addTab(tb.newTab().setText(""));
        tb.addTab(tb.newTab().setText(""));
        list.add(new HomeFragment());
        list.add(new CinemaFragment());
        list.add(new MyFragment());
        MyAdapter myAdapter = new MyAdapter(getSupportFragmentManager());
        vp.setAdapter(myAdapter);
        tb.setupWithViewPager(vp);
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
