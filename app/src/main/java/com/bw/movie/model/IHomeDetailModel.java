package com.bw.movie.model;

import com.bw.movie.bean.CancelFollowMovieBean;
import com.bw.movie.bean.FindMoviesDetailBean;
import com.bw.movie.bean.FollowMovieBean;
import com.bw.movie.contract.IHomeMoviesDetailContract;
import com.bw.movie.utils.RetUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class IHomeDetailModel implements IHomeMoviesDetailContract.IModel {


    @Override
    public void getFindMoviesDetail(int movieId, final FindICllBack findICllBack) {
        RetUtils.getInstance().getApis().getFindMoviesDetailBean(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FindMoviesDetailBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FindMoviesDetailBean findMoviesDetailBean) {

                        findICllBack.onSuccess(findMoviesDetailBean);
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

    @Override
    public void getFollow(int moveid, final FollowICllBack followICllBack) {

            RetUtils.getInstance().getApis().getFollowMovieBean(moveid)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<FollowMovieBean>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(FollowMovieBean followMovieBean) {

                            followICllBack.onFollowSuccess(followMovieBean);
                        }

                        @Override
                        public void onError(Throwable e) {
                            e.printStackTrace();
                            followICllBack.onFollowFailure(e.getMessage());

                        }

                        @Override
                        public void onComplete() {

                        }
                    });

    }

    @Override
    public void geCance(int moveid, final CanceICllBack CanceICllBack) {
        RetUtils.getInstance().getApis().getCancelFollowMovieBean(moveid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CancelFollowMovieBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CancelFollowMovieBean cancelFollowMovieBean) {

                        CanceICllBack.onFollowSuccess(cancelFollowMovieBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        CanceICllBack.onFollowFailure(e.getMessage());

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
