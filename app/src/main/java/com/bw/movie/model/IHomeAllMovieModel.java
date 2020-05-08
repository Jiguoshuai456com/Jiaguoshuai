package com.bw.movie.model;

import com.bw.movie.bean.FindAllMovieCommentBean;
import com.bw.movie.contract.IHomeAllMovieContract;
import com.bw.movie.utils.RetUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class IHomeAllMovieModel implements IHomeAllMovieContract.IModel {
    @Override
    public void getComment(int moveid, int page, int count, final CommentICllBack commentICllBack) {
        RetUtils.getInstance().getApis().getFindAllMovieCommentBean(moveid,page,count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FindAllMovieCommentBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FindAllMovieCommentBean findAllMovieCommentBean) {

                        commentICllBack.onCommentSuccess(findAllMovieCommentBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        commentICllBack.onCommentFailure(e.getMessage());

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
