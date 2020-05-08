package com.bw.movie.contract;


import com.bw.movie.base.IBaseView;
import com.bw.movie.bean.RegisterBean;
import com.bw.movie.bean.SendBean;


public interface IHomeRegisterContract {
    interface IView extends IBaseView {
        void onSuccess(RegisterBean registerBean);
        void onSendSuccess(SendBean sendBean);

        void onFailure(String str);
        void onSendFailure(String str);
    }
    interface IPresneter{
        void getRegister(String name, String pwd, String email, String code);
        void getSend(String email);
    }
    interface IModel{
        void getRegister(String name, String pwd, String email, String code, RegisterICllBack registerICllBack);
        void getSend(String email, SendICllBack sendICllBack);

        interface RegisterICllBack{
            void onSuccess(RegisterBean registerBean);
            void onFailure(String str);
        }
        interface SendICllBack{
            void onSendSuccess(SendBean sendBean);
            void onSendFailure(String str);

        }

    }
}
