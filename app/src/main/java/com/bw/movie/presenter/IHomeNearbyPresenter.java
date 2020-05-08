package com.bw.movie.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.IBaseView;
import com.bw.movie.bean.NearbyBean;
import com.bw.movie.contract.IHomeNearContract;
import com.bw.movie.model.IHomeNearbyModel;


public class IHomeNearbyPresenter extends BasePresenter implements IHomeNearContract.IPresenter {

    private IHomeNearbyModel nearbyModel;

    public IHomeNearbyPresenter(IBaseView iBaseView) {
        super(iBaseView);
    }

    @Override
    protected void initModel() {

        nearbyModel = new IHomeNearbyModel();
    }

    @Override
    public void getNearby(String longitude, String latitude, int page, int count) {

        nearbyModel.getNearby(longitude, latitude, page, count, new IHomeNearContract.IModel.NearbyICllBack() {
            @Override
            public void onNearbySuccess(NearbyBean nearbyBean) {
                IBaseView view = getView();
                if (view instanceof IHomeNearContract.IView){
                    ((IHomeNearContract.IView) view).onNearbySuccess(nearbyBean);
                }
            }

            @Override
            public void onNearbyFailure(String str) {
                IBaseView view = getView();
                if (view instanceof IHomeNearContract.IView){
                    ((IHomeNearContract.IView) view).onNearbyFailure(str);
                }
            }
        });
    }
}
