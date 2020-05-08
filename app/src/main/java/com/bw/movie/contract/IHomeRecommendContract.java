package com.bw.movie.contract;

import com.bw.movie.base.IBaseView;
import com.bw.movie.bean.RecommendBean;


public interface IHomeRecommendContract {
    interface IView extends IBaseView{
        void onRecommendSuccess(RecommendBean recommendBean);
        void onRecommendFailure(String str);
    }
    interface IPresenter{
        void getRecommend(int page, int count);
    }
    interface IModel{
        void getRecommend(int page, int count, RecommendICllBack recommendICllBack);
        interface RecommendICllBack{
            void onRecommendSuccess(RecommendBean recommendBean);
            void onRecommendFailure(String str);
        }
    }
}
