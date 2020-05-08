package com.bw.movie.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.IBaseView;
import com.bw.movie.bean.ComingSoonBean;
import com.bw.movie.bean.HotBean;
import com.bw.movie.bean.ReleaseBean;
import com.bw.movie.contract.IHomeMoreContract;
import com.bw.movie.model.IHomeMoreModel;


public class IHomeMorePresenter extends BasePresenter implements IHomeMoreContract.IPresenter {

    private IHomeMoreModel moreModel;

    public IHomeMorePresenter(IBaseView iBaseView) {
        super(iBaseView);
    }

    @Override
    protected void initModel() {

        moreModel = new IHomeMoreModel();
    }

    @Override
    public void getHot(int page, int count) {

        moreModel.getHot(page, count, new IHomeMoreContract.IModel.HotICllBack() {
            @Override
            public void onHotSuccess(HotBean hotBean) {
                IBaseView view = getView();
                if (view instanceof IHomeMoreContract.IView){
                    ((IHomeMoreContract.IView) view).onHotSuccess(hotBean);
                }
            }

            @Override
            public void onHotFailure(String str) {
                IBaseView view = getView();
                if (view instanceof IHomeMoreContract.IView){
                    ((IHomeMoreContract.IView) view).onHotFailure(str);
                }
            }
        });
    }

    @Override
    public void getComingSoon(int page, int count) {

        moreModel.getComingSoon(page, count, new IHomeMoreContract.IModel.ComingSoonICllBack() {
            @Override
            public void onComingSoonSuccess(ComingSoonBean comingSoonBean) {
                IBaseView view = getView();
                if (view instanceof IHomeMoreContract.IView){
                    ((IHomeMoreContract.IView) view).onComingSoonSuccess(comingSoonBean);
                }
            }

            @Override
            public void onComingSoonFailure(String str) {
                IBaseView view = getView();
                if (view instanceof IHomeMoreContract.IView){
                    ((IHomeMoreContract.IView) view).onComingSoonFailure(str);
                }
            }
        });
    }

    @Override
    public void getRelease(int page, int count) {

        moreModel.getRelease(page, count, new IHomeMoreContract.IModel.ReleaseICllBack() {
            @Override
            public void onReleaseSuccess(ReleaseBean releaseBean) {
                IBaseView view = getView();
                if (view instanceof IHomeMoreContract.IView){
                    ((IHomeMoreContract.IView) view).onReleaseSuccess(releaseBean);
                }
            }

            @Override
            public void onReleaseFailure(String str) {
                IBaseView view = getView();
                if (view instanceof IHomeMoreContract.IView){
                    ((IHomeMoreContract.IView) view).onReleaseFailure(str);
                }
            }
        });
    }
}
