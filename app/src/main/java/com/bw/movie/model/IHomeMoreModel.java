package com.bw.movie.model;

import com.bw.movie.bean.ComingSoonBean;
import com.bw.movie.bean.HotBean;
import com.bw.movie.bean.ReleaseBean;
import com.bw.movie.contract.IHomeMoreContract;
import com.bw.movie.utils.RetUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class IHomeMoreModel implements IHomeMoreContract.IModel {
    @Override
    public void getHot(int page, int count, final HotICllBack hotICllBack) {
        RetUtils.getInstance().getApis().getHotBean(page, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HotBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HotBean hotBean) {

                        hotICllBack.onHotSuccess(hotBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        hotICllBack.onHotFailure(e.getMessage());

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getComingSoon(int page, int count, final ComingSoonICllBack comingSoonICllBack) {

        RetUtils.getInstance().getApis().getComingSoonBean(page, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ComingSoonBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ComingSoonBean comingSoonBean) {

                        comingSoonICllBack.onComingSoonSuccess(comingSoonBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        comingSoonICllBack.onComingSoonFailure(e.getMessage());

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getRelease(int page, int count, final ReleaseICllBack releaseICllBack) {

        RetUtils.getInstance().getApis().getReleaseBean(page, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ReleaseBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ReleaseBean releaseBean) {

                        releaseICllBack.onReleaseSuccess(releaseBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        releaseICllBack.onReleaseFailure(e.getMessage());

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
