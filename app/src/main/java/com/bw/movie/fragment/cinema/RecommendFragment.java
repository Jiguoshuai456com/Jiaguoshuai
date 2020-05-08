package com.bw.movie.fragment.cinema;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bw.movie.R;
import com.bw.movie.adapter.cinema.CinemaAdapter2;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.RecommendBean;
import com.bw.movie.contract.IHomeRecommendContract;
import com.bw.movie.fragment.details.StillFragment;
import com.bw.movie.presenter.IHomeRecommendPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;


public class RecommendFragment extends BaseFragment implements IHomeRecommendContract.IView {
    @BindView(R.id.rv)
    RecyclerView rv;
    Unbinder unbinder;

    @Override
    protected BasePresenter initPresenter() {
        return new IHomeRecommendPresenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.recommend_fragment;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {

        BasePresenter presenter = getPresenter();
        if (presenter instanceof IHomeRecommendPresenter){
            ((IHomeRecommendPresenter) presenter).getRecommend(1,10);
        }

    }

    @Override
    public void onRecommendSuccess(RecommendBean recommendBean) {
        List<RecommendBean.ResultBean> result = recommendBean.getResult();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(linearLayoutManager);

        CinemaAdapter2 cinemaAdapter2 = new CinemaAdapter2(getContext(),result);
        rv.setAdapter(cinemaAdapter2);

        rv.addItemDecoration(new StillFragment.SpaceItemDecoration(30));


    }

    @Override
    public void onRecommendFailure(String str) {

    }
}
