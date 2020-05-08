package com.bw.movie.contract;

import com.bw.movie.base.IBaseView;
import com.bw.movie.bean.FindAllMovieCommentBean;


public interface IHomeAllMovieContract {
    interface IView extends IBaseView{
        void onCommentSuccess(FindAllMovieCommentBean findAllMovieCommentBean);
        void onCommentFailure(String str);
    }
    interface IPresenter{
        void  get1Comment(int moveid, int page, int count);
    }
    interface IModel{
        void  getComment(int moveid, int page, int count, CommentICllBack commentICllBack);
        interface CommentICllBack{
            void onCommentSuccess(FindAllMovieCommentBean findAllMovieCommentBean);
            void onCommentFailure(String str);
        }
    }
}
