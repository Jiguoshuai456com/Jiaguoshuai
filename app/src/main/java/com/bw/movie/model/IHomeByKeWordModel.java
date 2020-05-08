package com.bw.movie.model;

import com.bw.movie.bean.FindMoviewByKeWordBean;
import com.bw.movie.contract.IHomeKeWordContract;
import com.bw.movie.utils.RetUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class IHomeByKeWordModel implements IHomeKeWordContract.IModel {
    @Override
    public void onSuccess(String keyword, int page, int count, final FindICllBack findICllBack) {
        RetUtils.getInstance().getApis().getFindMoviewByKeWordBean(keyword, page, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FindMoviewByKeWordBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FindMoviewByKeWordBean findMoviewByKeWordBean) {

                        findICllBack.onSuccess(findMoviewByKeWordBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                        e.printStackTrace();
                        findICllBack.onFailure(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
