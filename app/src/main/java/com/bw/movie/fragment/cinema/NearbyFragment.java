package com.bw.movie.fragment.cinema;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bw.movie.R;
import com.bw.movie.adapter.cinema.CinemaAdapter1;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.NearbyBean;
import com.bw.movie.contract.IHomeNearContract;
import com.bw.movie.fragment.details.StillFragment;
import com.bw.movie.presenter.IHomeNearbyPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;


public class NearbyFragment extends BaseFragment implements IHomeNearContract.IView {
    @BindView(R.id.rv)
    RecyclerView rv;
    Unbinder unbinder;

    @Override
    protected BasePresenter initPresenter() {
        return new IHomeNearbyPresenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.nearby_fragment;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {
        BasePresenter presenter = getPresenter();
        if (presenter instanceof IHomeNearbyPresenter){
            ((IHomeNearbyPresenter) presenter).getNearby("116.30551391385724","40.04571807462411",1,10);
        }

    }

    @Override
    public void onNearbySuccess(NearbyBean nearbyBean) {

        List<NearbyBean.ResultBean> result = nearbyBean.getResult();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        rv.setLayoutManager(linearLayoutManager);
        CinemaAdapter1 cinemaAdapter1 = new CinemaAdapter1(getContext(), result);
        rv.setAdapter(cinemaAdapter1);
        rv.addItemDecoration(new StillFragment.SpaceItemDecoration(20));

    }

    @Override
    public void onNearbyFailure(String str) {

    }
}
