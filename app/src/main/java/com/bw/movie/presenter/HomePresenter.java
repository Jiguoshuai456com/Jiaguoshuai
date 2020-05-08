package com.bw.movie.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.IBaseView;
import com.bw.movie.bean.ComingSoonBean;
import com.bw.movie.bean.HotBean;
import com.bw.movie.bean.ReleaseBean;
import com.bw.movie.contract.HomeContract;
import com.bw.movie.model.HomeModel;


public class HomePresenter extends BasePresenter implements HomeContract.IPresenter {

    private HomeModel homeModel;

    public HomePresenter(IBaseView iBaseView) {
        super(iBaseView);
    }

    @Override
    protected void initModel() {

        homeModel = new HomeModel();
    }

    @Override
    public void getHot(int page, int count) {
        homeModel.getHot(page, count, new HomeContract.IModel.HotICllBack() {
            @Override
            public void onHotSuccess(HotBean hotBean) {
                IBaseView view = getView();
                if (view instanceof HomeContract.IView){
                    ((HomeContract.IView) view).onHotSuccess(hotBean);
                }
            }

            @Override
            public void onHotFailure(String str) {

                IBaseView view = getView();
                if (view instanceof HomeContract.IView){
                    ((HomeContract.IView) view).onHotFailure(str);
                }
            }
        });

    }

    @Override
    public void getComingSoon(int page, int count) {

        homeModel.getComingSoon(page, count, new HomeContract.IModel.ComingSoonICllBack() {
            @Override
            public void onComingSoonSuccess(ComingSoonBean comingSoonBean) {
                IBaseView view = getView();
                if (view instanceof HomeContract.IView){
                    ((HomeContract.IView) view).onComingSoonSuccess(comingSoonBean);
                }
            }

            @Override
            public void onComingSoonFailure(String str) {
                IBaseView view = getView();
                if (view instanceof HomeContract.IView){
                    ((HomeContract.IView) view).onComingSoonFailure(str);
                }
            }
        });
    }

    @Override
    public void getRelease(int page, int count) {

        homeModel.getRelease(page, count, new HomeContract.IModel.ReleaseICllBack() {
            @Override
            public void onReleaseSuccess(ReleaseBean releaseBean) {
                IBaseView view = getView();
                if (view instanceof HomeContract.IView){
                    ((HomeContract.IView) view).onReleaseSuccess(releaseBean);
                }
            }

            @Override
            public void onReleaseFailure(String str) {
                IBaseView view = getView();
                if (view instanceof HomeContract.IView){
                    ((HomeContract.IView) view).onReleaseFailure(str);
                }
            }
        });
    }
}
