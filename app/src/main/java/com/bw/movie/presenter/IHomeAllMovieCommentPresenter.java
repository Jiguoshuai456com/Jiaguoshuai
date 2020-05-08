package com.bw.movie.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.IBaseView;
import com.bw.movie.bean.FindAllMovieCommentBean;
import com.bw.movie.contract.IHomeAllMovieContract;
import com.bw.movie.model.IHomeAllMovieModel;


public class IHomeAllMovieCommentPresenter extends BasePresenter implements IHomeAllMovieContract.IPresenter {

    private IHomeAllMovieModel findAllMovieCommentModel;

    public IHomeAllMovieCommentPresenter(IBaseView iBaseView) {
        super(iBaseView);
    }

    @Override
    protected void initModel() {

        findAllMovieCommentModel = new IHomeAllMovieModel();
    }

    @Override
    public void get1Comment(int moveid, int page, int count) {
        findAllMovieCommentModel.getComment(moveid, page, count, new IHomeAllMovieContract.IModel.CommentICllBack() {
            @Override
            public void onCommentSuccess(FindAllMovieCommentBean findAllMovieCommentBean) {
                IBaseView view = getView();
                if (view instanceof IHomeAllMovieContract.IView){
                    ((IHomeAllMovieContract.IView) view).onCommentSuccess(findAllMovieCommentBean);
                }
            }

            @Override
            public void onCommentFailure(String str) {
                IBaseView view = getView();
                if (view instanceof IHomeAllMovieContract.IView){
                    ((IHomeAllMovieContract.IView) view).onCommentFailure(str);
                }
            }
        });
    }
}
