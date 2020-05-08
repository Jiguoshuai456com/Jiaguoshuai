package com.bw.movie.contract;

import com.bw.movie.base.IBaseView;
import com.bw.movie.bean.InfoByRegionBean;


public interface IHomeRegionContract {
    interface IView extends IBaseView{
        void onInfoByRegionSuccess(InfoByRegionBean infoByRegionBean);
        void onInfoByRegionFailure(String str);
    }
    interface IPresenter{
        void getInfoByRegion(int movieId, int regionId, int page, int count);
    }
    interface IModel{
        void getInfoByRegion(int movieId, int regionId, int page, int count, InfoByRegionICllBack infoByRegionICllBack);

        interface InfoByRegionICllBack{
            void onInfoByRegionSuccess(InfoByRegionBean infoByRegionBean);
            void onInfoByRegionFailure(String str);
        }
    }
}
