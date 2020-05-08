package com.bw.movie.contract;

import com.bw.movie.base.IBaseView;
import com.bw.movie.bean.CancelFollowMovieBean;
import com.bw.movie.bean.FindMoviesDetailBean;
import com.bw.movie.bean.FollowMovieBean;


public interface IHomeMoviesDetailContract {
    interface IView extends IBaseView{
        void onSuccess(FindMoviesDetailBean findMoviesDetailBean);
        void onFailure(String str);
        void onFollowSuccess(FollowMovieBean followMovieBean);
        void onFollowFailure(String str);
        void onCanceSuccess(CancelFollowMovieBean cancelFollowMovieBean);
        void onCanceFailure(String str);
    }
    interface IPresenter{
        void getFindMoviesDetail(int movieId);
        void getFollow(int moveid);
        void getCance(int moveid);

    }
    interface IModel{
        void getFindMoviesDetail(int movieId, FindICllBack findICllBack);
        interface FindICllBack{
            void onSuccess(FindMoviesDetailBean findMoviesDetailBean);
            void onFailure(String str);
        }
        void getFollow(int moveid, FollowICllBack followICllBack);
        interface FollowICllBack{
            void onFollowSuccess(FollowMovieBean followMovieBean);
            void onFollowFailure(String str);
        }
        void geCance(int moveid, CanceICllBack CanceICllBack);
        interface CanceICllBack{
            void onFollowSuccess(CancelFollowMovieBean cancelFollowMovieBean);
            void onFollowFailure(String str);
        }
    }
}
