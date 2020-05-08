package com.bw.movie.model;

import com.bw.movie.bean.InfoByRegionBean;
import com.bw.movie.contract.IHomeRegionContract;
import com.bw.movie.utils.RetUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class IHomeRegionModel implements IHomeRegionContract.IModel {
    @Override
    public void getInfoByRegion(int movieId, int regionId, int page, int count, final InfoByRegionICllBack infoByRegionICllBack) {
        RetUtils.getInstance().getApis().getInfoByRegionBean(movieId, regionId, page, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<InfoByRegionBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(InfoByRegionBean infoByRegionBean) {

                        infoByRegionICllBack.onInfoByRegionSuccess(infoByRegionBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        infoByRegionICllBack.onInfoByRegionFailure(e.getMessage());

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
