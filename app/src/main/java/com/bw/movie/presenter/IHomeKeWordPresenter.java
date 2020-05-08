package com.bw.movie.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.IBaseView;
import com.bw.movie.bean.FindMoviewByKeWordBean;
import com.bw.movie.contract.IHomeKeWordContract;
import com.bw.movie.model.IHomeByKeWordModel;


public class IHomeKeWordPresenter extends BasePresenter implements IHomeKeWordContract.IPresenter {

    private IHomeByKeWordModel findMoviewByKeWordModel;

    public IHomeKeWordPresenter(IBaseView iBaseView) {
        super(iBaseView);
    }

    @Override
    protected void initModel() {
        findMoviewByKeWordModel = new IHomeByKeWordModel();
    }

    @Override
    public void onSuccess(String keyword, int page, int count) {
        findMoviewByKeWordModel.onSuccess(keyword, page, count, new IHomeKeWordContract.IModel.FindICllBack() {
            @Override
            public void onSuccess(FindMoviewByKeWordBean findMoviewByKeWordBean) {
                IBaseView view = getView();
                if (view instanceof IHomeKeWordContract.IView){
                    ((IHomeKeWordContract.IView) view).onSuccess(findMoviewByKeWordBean);
                }
            }

            @Override
            public void onFailure(String str) {
                IBaseView view = getView();
                if (view instanceof IHomeKeWordContract.IView){
                    ((IHomeKeWordContract.IView) view).onFailure(str);
                }
            }
        });
    }
}
