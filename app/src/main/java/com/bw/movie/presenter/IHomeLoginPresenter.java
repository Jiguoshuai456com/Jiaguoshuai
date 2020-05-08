package com.bw.movie.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.IBaseView;
import com.bw.movie.bean.LoginBean;
import com.bw.movie.contract.IHomeLoginContract;
import com.bw.movie.model.IHomeLoginModel;


public class IHomeLoginPresenter extends BasePresenter implements IHomeLoginContract.IPresneter {

    private IHomeLoginModel loginModel;

    public IHomeLoginPresenter(IBaseView iBaseView) {
        super(iBaseView);
    }

    @Override
    protected void initModel() {
        loginModel = new IHomeLoginModel();
    }

    @Override
    public void getRegister(String email, String pwd) {

        loginModel.getRegister(email, pwd, new IHomeLoginContract.IModel.LoginICllBack() {
            @Override
            public void onSuccess(LoginBean loginBean) {
                IBaseView view = getView();
                if (view instanceof IHomeLoginContract.IView){
                    ((IHomeLoginContract.IView) view).onSuccess(loginBean);
                }
            }

            @Override
            public void onFailure(String str) {

                IBaseView view = getView();
                if (view instanceof IHomeLoginContract.IView){
                    ((IHomeLoginContract.IView) view).onFailure(str);
                }
            }
        });
    }

}
