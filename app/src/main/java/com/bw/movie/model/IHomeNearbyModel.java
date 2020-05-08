package com.bw.movie.model;

import com.bw.movie.bean.NearbyBean;
import com.bw.movie.contract.IHomeNearContract;
import com.bw.movie.utils.RetUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class IHomeNearbyModel implements IHomeNearContract.IModel {
    @Override
    public void getNearby(String longitude, String latitude, int page, int count, final NearbyICllBack nearbyICllBack) {
        RetUtils.getInstance().getApis().getNearbyBean(longitude, latitude, page, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NearbyBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(NearbyBean nearbyBean) {

                        nearbyICllBack.onNearbySuccess(nearbyBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                        e.printStackTrace();
                        nearbyICllBack.onNearbyFailure(e.getMessage());

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
