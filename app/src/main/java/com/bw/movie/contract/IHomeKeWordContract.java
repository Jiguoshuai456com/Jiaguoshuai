package com.bw.movie.contract;

import com.bw.movie.base.IBaseView;
import com.bw.movie.bean.FindMoviewByKeWordBean;


public interface IHomeKeWordContract {
    interface IView extends IBaseView{
        void onSuccess(FindMoviewByKeWordBean findMoviewByKeWordBean);
        void onFailure(String str);
    }
    interface IPresenter{
        void onSuccess(String keyword, int page, int count);
    }
    interface IModel{
        void onSuccess(String keyword, int page, int count, FindICllBack findICllBack);

        interface FindICllBack{
            void onSuccess(FindMoviewByKeWordBean findMoviewByKeWordBean);
            void onFailure(String str);
        }
    }
}
