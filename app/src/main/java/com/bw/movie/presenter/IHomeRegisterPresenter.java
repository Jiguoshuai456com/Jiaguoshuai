package com.bw.movie.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.IBaseView;
import com.bw.movie.bean.RegisterBean;
import com.bw.movie.bean.SendBean;
import com.bw.movie.contract.IHomeRegisterContract;
import com.bw.movie.model.IHomeRegisterModel;


public class IHomeRegisterPresenter extends BasePresenter implements IHomeRegisterContract.IPresneter {

    private IHomeRegisterModel mRegisterModel;

    public IHomeRegisterPresenter(IBaseView iBaseView) {
        super(iBaseView);
    }

    @Override
    protected void initModel() {
        mRegisterModel = new IHomeRegisterModel();
    }

    @Override
    public void getRegister(String name, String pwd, String email, String code) {
        mRegisterModel.getRegister(name, pwd, email, code, new IHomeRegisterContract.IModel.RegisterICllBack() {
            @Override
            public void onSuccess(RegisterBean registerBean) {
                IBaseView view = getView();
                if (view instanceof IHomeRegisterContract.IView){
                    ((IHomeRegisterContract.IView) view).onSuccess(registerBean);
                }
            }

            @Override
            public void onFailure(String str) {

                IBaseView view = getView();
                if (view instanceof IHomeRegisterContract.IView){
                    ((IHomeRegisterContract.IView) view).onFailure(str);
                }
            }
        });
    }

    @Override
    public void getSend(String email) {
        mRegisterModel.getSend(email, new IHomeRegisterContract.IModel.SendICllBack() {
            @Override
            public void onSendSuccess(SendBean sendBean) {
                IBaseView view = getView();
                if (view instanceof IHomeRegisterContract.IView){
                    ((IHomeRegisterContract.IView) view).onSendSuccess(sendBean);
                }
            }

            @Override
            public void onSendFailure(String str) {
                IBaseView view = getView();
                if (view instanceof IHomeRegisterContract.IView){
                    ((IHomeRegisterContract.IView) view).onSendFailure(str);
                }
            }
        });
    }
}
