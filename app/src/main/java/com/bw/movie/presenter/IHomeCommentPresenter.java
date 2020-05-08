package com.bw.movie.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.IBaseView;
import com.bw.movie.bean.CommentBean;
import com.bw.movie.contract.IHomeCommentContract;
import com.bw.movie.model.IHomeCommentModel;


public class IHomeCommentPresenter extends BasePresenter implements IHomeCommentContract.IPresenter {

    private IHomeCommentModel commentModel;

    public IHomeCommentPresenter(IBaseView iBaseView) {
        super(iBaseView);
    }

    @Override
    protected void initModel() {

        commentModel = new IHomeCommentModel();
    }

    @Override
    public void onComment(int movieid, String comment, double score) {

        commentModel.onComment(movieid, comment, score, new IHomeCommentContract.IModel.CommentICllBack() {
            @Override
            public void onCommentSuccess(CommentBean commentBean) {
                IBaseView view = getView();
                if (view instanceof IHomeCommentContract.IView){
                    ((IHomeCommentContract.IView) view).onCommentSuccess(commentBean);
                }
            }

            @Override
            public void onCommentFailure(String str) {
                IBaseView view = getView();
                if (view instanceof IHomeCommentContract.IView){
                    ((IHomeCommentContract.IView) view).onCommentFailure(str);
                }

            }
        });
    }
}
