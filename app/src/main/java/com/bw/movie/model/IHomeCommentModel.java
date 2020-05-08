package com.bw.movie.model;

import com.bw.movie.bean.CommentBean;
import com.bw.movie.contract.IHomeCommentContract;
import com.bw.movie.utils.RetUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class IHomeCommentModel implements IHomeCommentContract.IModel {

    @Override
    public void onComment(int movieid, String comment, double score, final CommentICllBack commentICllBack) {
        RetUtils.getInstance().getApis().getCommentBean(movieid, comment, score)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CommentBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CommentBean commentBean) {

                        commentICllBack.onCommentSuccess(commentBean);

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
