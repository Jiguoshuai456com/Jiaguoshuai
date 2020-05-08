package com.bw.movie.model;

import com.bw.movie.bean.RecommendBean;
import com.bw.movie.contract.IHomeRecommendContract;
import com.bw.movie.utils.RetUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class IHomeRecommendModel implements IHomeRecommendContract.IModel {
    @Override
    public void getRecommend(int page, int count, final RecommendICllBack recommendICllBack) {
        RetUtils.getInstance().getApis().getRecommendBean(page, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RecommendBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RecommendBean recommendBean) {

                        recommendICllBack.onRecommendSuccess(recommendBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        recommendICllBack.onRecommendFailure(e.getMessage());

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
