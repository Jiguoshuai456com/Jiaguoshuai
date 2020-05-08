package com.bw.movie.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.IBaseView;
import com.bw.movie.bean.CancelFollowMovieBean;
import com.bw.movie.bean.FindMoviesDetailBean;
import com.bw.movie.bean.FollowMovieBean;
import com.bw.movie.contract.IHomeMoviesDetailContract;
import com.bw.movie.model.IHomeDetailModel;


public class IHomeDetailPresenter extends BasePresenter implements IHomeMoviesDetailContract.IPresenter {

    private IHomeDetailModel findMoviesDetailModel;

    public IHomeDetailPresenter(IBaseView iBaseView) {
        super(iBaseView);
    }

    @Override
    protected void initModel() {

        findMoviesDetailModel = new IHomeDetailModel();
    }

    @Override
    public void getFindMoviesDetail(int movieId) {
        findMoviesDetailModel.getFindMoviesDetail(movieId, new IHomeMoviesDetailContract.IModel.FindICllBack() {
            @Override
            public void onSuccess(FindMoviesDetailBean findMoviesDetailBean) {
                IBaseView view = getView();
                if (view instanceof IHomeMoviesDetailContract.IView){
                    ((IHomeMoviesDetailContract.IView) view).onSuccess(findMoviesDetailBean);
                }
            }

            @Override
            public void onFailure(String str) {
                IBaseView view = getView();
                if (view instanceof IHomeMoviesDetailContract.IView){
                    ((IHomeMoviesDetailContract.IView) view).onFailure(str);
                }
            }
        });

    }

    @Override
    public void getFollow(int moveid) {
        findMoviesDetailModel.getFollow(moveid, new IHomeMoviesDetailContract.IModel.FollowICllBack() {
            @Override
            public void onFollowSuccess(FollowMovieBean followMovieBean) {
                IBaseView view = getView();
                if (view instanceof IHomeMoviesDetailContract.IView){
                    ((IHomeMoviesDetailContract.IView) view).onFollowSuccess(followMovieBean);
                }
            }

            @Override
            public void onFollowFailure(String str) {
                IBaseView view = getView();
                if (view instanceof IHomeMoviesDetailContract.IView){
                    ((IHomeMoviesDetailContract.IView) view).onFollowFailure(str);
                }
            }
        });
    }

    @Override
    public void getCance(int moveid) {
        findMoviesDetailModel.geCance(moveid, new IHomeMoviesDetailContract.IModel.CanceICllBack() {
            @Override
            public void onFollowSuccess(CancelFollowMovieBean cancelFollowMovieBean) {
                IBaseView view = getView();
                if (view instanceof IHomeMoviesDetailContract.IView){
                    ((IHomeMoviesDetailContract.IView) view).onCanceSuccess(cancelFollowMovieBean);
                }
            }

            @Override
            public void onFollowFailure(String str) {
                IBaseView view = getView();
                if (view instanceof IHomeMoviesDetailContract.IView){
                    ((IHomeMoviesDetailContract.IView) view).onCanceFailure(str);
                }
            }
        });
    }
}
