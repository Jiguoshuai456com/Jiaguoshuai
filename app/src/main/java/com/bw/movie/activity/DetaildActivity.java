package com.bw.movie.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.CancelFollowMovieBean;
import com.bw.movie.bean.FindMoviesDetailBean;
import com.bw.movie.bean.FollowMovieBean;
import com.bw.movie.contract.IHomeMoviesDetailContract;
import com.bw.movie.fragment.details.CinecismFragment;
import com.bw.movie.fragment.details.HeraldFragment;
import com.bw.movie.fragment.details.IntroduceFragment;
import com.bw.movie.fragment.details.StillFragment;
import com.bw.movie.presenter.IHomeDetailPresenter;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

public class DetaildActivity extends BaseActivity implements IHomeMoviesDetailContract.IView {

    @BindView(R.id.iv_background)
    SimpleDraweeView ivBackground;
    @BindView(R.id.tv_pingfen)
    TextView tvPingfen;
    @BindView(R.id.tv_pinglun)
    TextView tvPinglun;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_shangying)
    TextView tvShangying;
    @BindView(R.id.iv_xin)
    ImageView ivXin;
    @BindView(R.id.tv_guanzhu)
    TextView tvGuanzhu;

    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.tv_topname)
    TextView tv_topname;
    ArrayList<String> tabs = new ArrayList<>();
    ArrayList<Fragment> list = new ArrayList<>();
    boolean isFollow = false;
    @BindView(R.id.tv_yingping)
    TextView tvYingping;
    @BindView(R.id.tv_xunzuo)
    TextView tvXunzuo;
    @BindView(R.id.tv_yingping1)
    TextView tvYingping1;
    @BindView(R.id.tv_xunzuo1)
    TextView tvXunzuo1;
    private int moviedId;
    private FindMoviesDetailBean.ResultBean result;


    @Override
    protected int getLayout() {
        return R.layout.activity_details;

    }

    @Override
    protected BasePresenter initPresenter() {

        return new IHomeDetailPresenter(this);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

        Intent intent = getIntent();
        moviedId = intent.getIntExtra("moviedId", 0);

        EventBus.getDefault().postSticky(moviedId);
        Log.i("moviedid", moviedId + "");

//        SPUtils.putString(App.getAppContext(),SPUtils.NAME,"movieid",moviedId+"");


        BasePresenter presenter = getPresenter();
        if (presenter instanceof IHomeDetailPresenter) {
            ((IHomeDetailPresenter) presenter).getFindMoviesDetail(moviedId);
        }
        tabs.add("介绍");
        tabs.add("预告");
        tabs.add("剧照");
        tabs.add("影评");

        tab.setTag(tab.newTab().setText(tabs.get(0)));
        tab.setTag(tab.newTab().setText(tabs.get(1)));
        tab.setTag(tab.newTab().setText(tabs.get(2)));

        IntroduceFragment introduceFragment = new IntroduceFragment();
        HeraldFragment heraldFragment = new HeraldFragment();
        StillFragment stillFragment = new StillFragment();
        CinecismFragment cinecismFragment = new CinecismFragment();
        list.add(introduceFragment);
        list.add(heraldFragment);
        list.add(stillFragment);
        list.add(cinecismFragment);
        MyFragmentViewPager fragmentViewPager = new MyFragmentViewPager(getSupportFragmentManager());
        vp.setAdapter(fragmentViewPager);
        tab.setupWithViewPager(vp);
        vp.setOffscreenPageLimit(4);


    }

    @Override
    public void onSuccess(FindMoviesDetailBean findMoviesDetailBean) {

        Log.i("xxxss", findMoviesDetailBean.getMessage());
        result = findMoviesDetailBean.getResult();
        Log.i("img", result.getImageUrl());
//        Glide.with(DetaildActivity.this).load(result.getImageUrl()).into(ivBackground);

        GenericDraweeHierarchyBuilder genericDraweeHierarchyBuilder = new GenericDraweeHierarchyBuilder(getResources());
        genericDraweeHierarchyBuilder.setFailureImage(R.mipmap.ic_launcher);
        genericDraweeHierarchyBuilder.setPlaceholderImage(R.mipmap.ic_launcher);
        ivBackground.setImageURI(result.getImageUrl());
        tvPinglun.setText(result.getCommentNum() + "万条");
        tvPingfen.setText(result.getScore() + "分");
        tvName.setText(result.getName());
        tvType.setText(result.getMovieType());
        tvTime.setText(result.getDuration() + "");
        long releaseTime = result.getReleaseTime();
        String date = new SimpleDateFormat("yy--MM--dd").format(
                new Date(releaseTime));
        tvShangying.setText("20" + date + "  " + result.getPlaceOrigin() + "上映");

        tv_topname.setText(result.getName());

        Log.i("topname", result.getName());
        ivXin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isFollow == false) {
                    tvGuanzhu.setText("已关注");
                    ivXin.setImageResource(R.mipmap.emptyheart);
                    BasePresenter presenter = getPresenter();
                    if (presenter instanceof IHomeDetailPresenter) {
                        ((IHomeDetailPresenter) presenter).getFollow(moviedId);
                    }
                    result.setWhetherFollow(1);
                    isFollow = true;
                } else {
                    tvGuanzhu.setText("未关注");
                    ivXin.setImageResource(R.mipmap.xxxxxx);
                    BasePresenter presenter = getPresenter();
                    if (presenter instanceof IHomeDetailPresenter) {
                        ((IHomeDetailPresenter) presenter).getCance(moviedId);
                    }
                    result.setWhetherFollow(2);
                    isFollow = false;
                }
            }
        });

        EventBus.getDefault().postSticky(result);

    }

    @Override
    public void onFailure(String str) {

        Log.i("xx", str);

    }


    @Override
    public void onFollowSuccess(FollowMovieBean followMovieBean) {

        Log.i("followMovieBean", followMovieBean.getMessage());

    }

    @Override
    public void onFollowFailure(String str) {

    }

    @Override
    public void onCanceSuccess(CancelFollowMovieBean cancelFollowMovieBean) {
        Log.i("cancelFollowMovieBean", cancelFollowMovieBean.getMessage());
    }

    @Override
    public void onCanceFailure(String str) {

    }

    @OnClick({R.id.tv_yingping, R.id.tv_xunzuo, R.id.tv_yingping1, R.id.tv_xunzuo1})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_yingping:

                Intent intent = new Intent(DetaildActivity.this, CommentActivity.class);
                intent.putExtra("id",result.getMovieId());
                intent.putExtra("name",result.getName());
                startActivity(intent);
                break;
            case R.id.tv_xunzuo:

                Intent intent2 = new Intent(DetaildActivity.this,SelectActivity.class);

                intent2.putExtra("id",moviedId);
                intent2.putExtra("time",result.getDuration()+"");
                intent2.putExtra("score",result.getScore()+"");
                intent2.putExtra("dao",result.getMovieActor().get(0).getName());
                intent2.putExtra("ima",result.getShortFilmList().get(0).getVideoUrl());
                intent2.putExtra("name",result.getName());


                startActivity(intent2);


                break;
            case R.id.tv_yingping1:
                Intent intent1 = new Intent(DetaildActivity.this, CommentActivity.class);
                intent1.putExtra("id",result.getMovieId());
                intent1.putExtra("name",result.getName());
                startActivity(intent1);
                break;
            case R.id.tv_xunzuo1:

                Intent intent3 = new Intent(DetaildActivity.this,SelectActivity.class);

                intent3.putExtra("id",moviedId);
                intent3.putExtra("time",result.getDuration()+"");
                intent3.putExtra("score",result.getScore()+"");
                intent3.putExtra("dao",result.getMovieActor().get(0).getName());
                intent3.putExtra("ima",result.getShortFilmList().get(0).getVideoUrl());
                intent3.putExtra("name",result.getName());
                startActivity(intent3);

                break;
        }
    }

    public class MyFragmentViewPager extends FragmentPagerAdapter {
        public MyFragmentViewPager(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabs.get(position);
        }
    }

}
