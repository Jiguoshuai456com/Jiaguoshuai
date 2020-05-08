package com.bw.movie.fragment.details;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.R;
import com.bw.movie.adapter.datails.CinecismAdapter;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.FindAllMovieCommentBean;
import com.bw.movie.contract.IHomeAllMovieContract;
import com.bw.movie.presenter.IHomeAllMovieCommentPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class CinecismFragment extends BaseFragment implements IHomeAllMovieContract.IView {
    @BindView(R.id.rv)
    RecyclerView rv;
    Unbinder unbinder;

    @Override
    protected BasePresenter initPresenter() {
        return new IHomeAllMovieCommentPresenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.item_cinecism;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {
//        Toast.makeText(getContext(), "22222222222222222222222", Toast.LENGTH_SHORT).show();

        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void getData(Integer integer) {

        BasePresenter presenter = getPresenter();
        if (presenter instanceof IHomeAllMovieCommentPresenter){
            Log.i("aaa","22222222");
            ((IHomeAllMovieCommentPresenter) presenter).get1Comment(integer,1,5);
        }
    }

    @Override
    public void onCommentSuccess(FindAllMovieCommentBean findAllMovieCommentBean) {

        Log.i("findAllMovieCommentBean", findAllMovieCommentBean.getMessage());

        List<FindAllMovieCommentBean.ResultBean> result = findAllMovieCommentBean.getResult();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(linearLayoutManager);

        CinecismAdapter cinecismAdapter = new CinecismAdapter(getContext(),result);
        rv.setAdapter(cinecismAdapter);

    }

    @Override
    public void onCommentFailure(String str) {
        Log.i("findAllMovieCommentBean", str);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
