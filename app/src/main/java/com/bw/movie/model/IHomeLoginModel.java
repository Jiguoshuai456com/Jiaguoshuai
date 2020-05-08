package com.bw.movie.model;

import com.bw.movie.bean.LoginBean;
import com.bw.movie.contract.IHomeLoginContract;
import com.bw.movie.utils.RetUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class IHomeLoginModel implements IHomeLoginContract.IModel {
    @Override
    public void getRegister(String email, String pwd, final LoginICllBack loginICllBack) {
        RetUtils.getInstance().getApis().getLoginBean(email, pwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginBean loginBean) {

                        if (loginICllBack!=null){
                            loginICllBack.onSuccess(loginBean);
                        }

                    }

                    @Override
                    public void onError(Throwable e) {

                        e.printStackTrace();
                        if (loginICllBack!=null){
                            loginICllBack.onFailure(e.getMessage());
                        }

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
