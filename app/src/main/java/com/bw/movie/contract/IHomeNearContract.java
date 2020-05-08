package com.bw.movie.contract;

import com.bw.movie.base.IBaseView;
import com.bw.movie.bean.NearbyBean;


public interface IHomeNearContract {
    interface IView extends IBaseView{
        void onNearbySuccess(NearbyBean nearbyBean);
        void onNearbyFailure(String str);
    }
    interface IPresenter{
        void getNearby(String longitude, String latitude, int page, int count);
    }
    interface IModel{
        void getNearby(String longitude, String latitude, int page, int count, NearbyICllBack nearbyICllBack);
        interface NearbyICllBack{
            void onNearbySuccess(NearbyBean nearbyBean);
            void onNearbyFailure(String str);
        }
    }
}
