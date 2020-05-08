package com.bw.movie.contract;

import com.bw.movie.base.IBaseView;
import com.bw.movie.bean.CommentBean;


public interface IHomeCommentContract {
    interface IView extends IBaseView{
        void onCommentSuccess(CommentBean commentBean);
        void onCommentFailure(String str);
    }
    interface IPresenter{
        void onComment(int movieid, String comment, double score);
    }
    interface IModel{
        void onComment(int movieid, String comment, double score, CommentICllBack commentICllBack);
        interface CommentICllBack{
            void onCommentSuccess(CommentBean commentBean);
            void onCommentFailure(String str);
        }
    }
}
