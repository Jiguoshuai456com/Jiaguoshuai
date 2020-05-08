package com.bw.movie.fragment.details;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.adapter.datails.PrensonAdapter;
import com.bw.movie.adapter.datails.PerformerAdapter;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.FindMoviesDetailBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;


public class IntroduceFragment extends BaseFragment {
    @BindView(R.id.jian)
    TextView jian;
    @BindView(R.id.daosize)
    TextView daosize;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.yansize)
    TextView yansize;
    @BindView(R.id.rv1)
    RecyclerView rv1;

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.item_introduce;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {
        if (!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }

    }
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void getData( FindMoviesDetailBean.ResultBean bean){
        String summary = bean.getSummary();
        jian.setText(summary);
        List< FindMoviesDetailBean.ResultBean.MovieDirectorBean> movieDirector = bean.getMovieDirector();
        daosize.setText("导演（"+movieDirector.size()+"）");
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rv.setLayoutManager(manager);
        PrensonAdapter yanAdapter = new PrensonAdapter(getContext(), movieDirector);
        rv.setAdapter(yanAdapter);
        List< FindMoviesDetailBean.ResultBean.MovieActorBean> movieActor = bean.getMovieActor();
        yansize.setText("演员（"+movieActor.size()+"）");
        LinearLayoutManager manager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rv1.setLayoutManager(manager1);
        PerformerAdapter yuanAdapter = new PerformerAdapter(getContext(), movieActor);
        rv1.setAdapter(yuanAdapter);

        Log.i("name",bean.getName());
    }

}
