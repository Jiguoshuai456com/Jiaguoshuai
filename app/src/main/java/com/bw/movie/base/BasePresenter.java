package com.bw.movie.base;

import java.lang.ref.WeakReference;


public abstract class BasePresenter<V extends IBaseView> {

    private  WeakReference<V> mWeakReference;

    public BasePresenter(V v) {
        mWeakReference = new WeakReference<>(v);
        initModel();
    }
    protected abstract void initModel();
    protected V getView(){
        if (mWeakReference!=null){
            return mWeakReference.get();
        }
        return null;
    }

    protected void detachView(){
        if (mWeakReference!=null){
            mWeakReference.clear();
            mWeakReference = null;
        }
    }

}
