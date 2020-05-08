package com.bw.movie.model;

import com.bw.movie.bean.RegisterBean;
import com.bw.movie.bean.SendBean;
import com.bw.movie.contract.IHomeRegisterContract;
import com.bw.movie.utils.RetUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class IHomeRegisterModel implements IHomeRegisterContract.IModel {
    @Override
    public void getRegister(String name, String pwd, String email, String code, final RegisterICllBack registerICllBack) {
        RetUtils.getInstance().getApis().getRegisterBean(name, pwd, email, code)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegisterBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RegisterBean registerBean) {
                        if (registerICllBack!=null){
                            registerICllBack.onSuccess(registerBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        if (registerICllBack!=null){
                            registerICllBack.onFailure(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getSend(String email, final SendICllBack sendICllBack) {
        RetUtils.getInstance().getApis().getSendBean(email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SendBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SendBean sendBean) {
                        if (sendICllBack!=null){
                            sendICllBack.onSendSuccess(sendBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        if (sendICllBack!=null){
                            sendICllBack.onSendFailure(e.getMessage());
                        }

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
