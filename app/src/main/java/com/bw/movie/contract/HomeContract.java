package com.bw.movie.contract;

import com.bw.movie.base.IBaseView;
import com.bw.movie.bean.ComingSoonBean;
import com.bw.movie.bean.HotBean;
import com.bw.movie.bean.ReleaseBean;


public interface HomeContract {
    interface IView extends IBaseView{
        void onHotSuccess(HotBean hotBean);
        void onComingSoonSuccess(ComingSoonBean comingSoonBean);
        void onReleaseSuccess(ReleaseBean releaseBean);
        void onHotFailure(String str);
        void onComingSoonFailure(String str);
        void onReleaseFailure(String str);
    }
    interface IPresenter{
        void getHot(int page, int count);
        void getComingSoon(int page, int count);
        void getRelease(int page, int count);
    }
    interface IModel{
        void getHot(int page, int count, HotICllBack hotICllBack);
        void getComingSoon(int page, int count, ComingSoonICllBack comingSoonICllBack);
        void getRelease(int page, int count, ReleaseICllBack releaseICllBack);
        interface HotICllBack{
            void onHotSuccess(HotBean hotBean);
            void onHotFailure(String str);
        }
        interface ComingSoonICllBack{
            void onComingSoonSuccess(ComingSoonBean comingSoonBean);
            void onComingSoonFailure(String str);
        }
        interface ReleaseICllBack{
            void onReleaseSuccess(ReleaseBean releaseBean);
            void onReleaseFailure(String str);
        }
    }
}
