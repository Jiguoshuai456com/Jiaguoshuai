package com.bw.movie.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.IBaseView;
import com.bw.movie.bean.InfoByRegionBean;
import com.bw.movie.contract.IHomeRegionContract;
import com.bw.movie.model.IHomeRegionModel;


public class IHomeRegionPresenter extends BasePresenter implements IHomeRegionContract.IPresenter {

    private IHomeRegionModel infoByRegionModel;

    public IHomeRegionPresenter(IBaseView iBaseView) {
        super(iBaseView);
    }

    @Override
    protected void initModel() {

        infoByRegionModel = new IHomeRegionModel();
    }

    @Override
    public void getInfoByRegion(int movieId, int regionId, int page, int count) {

        infoByRegionModel.getInfoByRegion(movieId, regionId, page, count, new IHomeRegionContract.IModel.InfoByRegionICllBack() {
            @Override
            public void onInfoByRegionSuccess(InfoByRegionBean infoByRegionBean) {
                IBaseView view = getView();
                if (view instanceof IHomeRegionContract.IView){
                    ((IHomeRegionContract.IView) view).onInfoByRegionSuccess(infoByRegionBean);
                }
            }

            @Override
            public void onInfoByRegionFailure(String str) {
                IBaseView view = getView();
                if (view instanceof IHomeRegionContract.IView){
                    ((IHomeRegionContract.IView) view).onInfoByRegionFailure(str);
                }

            }
        });
    }
}
