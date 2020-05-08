package com.bw.movie.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

public abstract class BaseFragment<P extends BasePresenter> extends Fragment {
    P mPresenter;

    public boolean isChang;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.i("xxx","onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = initPresenter();
    }
    @Override
    public void onStart() {
        super.onStart();
        Log.i("xxx","onStart");
    }
    @Override
    public void onResume() {
        super.onResume();
        Log.i("xxx","onResume");
    }
    @Override
    public void onPause() {
        super.onPause();
        Log.i("xxx","onPause");
    }
    @Override
    public void onStop() {
        super.onStop();
        Log.i("xxx","onStop");
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i("xxx","onDestroyView");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(),getLayout(),null);
        initView(view);
        ButterKnife.bind(this,view);
        isChang = true;
        doNetWork();
        return view;
    }
    private void doNetWork(){
        if(getUserVisibleHint()){
            initData();
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
    }

    protected P getPresenter(){
        return mPresenter;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter!=null){
            mPresenter.detachView();
            mPresenter = null;
        }
    }
    protected abstract P initPresenter();
    protected abstract int getLayout();
    protected abstract void initView(View view);
    protected abstract void initData();
    /**
     * fragment 提供的回调，回调当天fragment是否对用用户可见
     * 他是在当这个 fragment 是否对用户的可见发生变化的时候
     * @param isVisibleToUser false对用户不可见， true对用户可见
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isChang && isVisibleToUser){
            initData();
        }
    }
}
