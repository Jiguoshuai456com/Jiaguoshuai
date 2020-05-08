package com.bw.movie.contract;


import com.bw.movie.base.IBaseView;
import com.bw.movie.bean.LoginBean;


public interface IHomeLoginContract {
    interface IView extends IBaseView {
        void onSuccess(LoginBean loginBean);
        void onFailure(String str);
    }
    interface IPresneter{
        void getRegister(String email, String pwd);
    }
    interface IModel{
        void getRegister(String email, String pwd, LoginICllBack loginICllBack);
        interface LoginICllBack{
            void onSuccess(LoginBean loginBean);
            void onFailure(String str);
        }
    }
}
