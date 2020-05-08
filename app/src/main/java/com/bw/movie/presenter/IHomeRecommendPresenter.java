package com.bw.movie.presenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.base.IBaseView;
import com.bw.movie.bean.RecommendBean;
import com.bw.movie.contract.IHomeRecommendContract;
import com.bw.movie.model.IHomeRecommendModel;


public class IHomeRecommendPresenter extends BasePresenter implements IHomeRecommendContract.IPresenter {

    private IHomeRecommendModel recommendModel;

    public IHomeRecommendPresenter(IBaseView iBaseView) {
        super(iBaseView);
    }

    @Override
    protected void initModel() {
        recommendModel = new IHomeRecommendModel();

    }

    @Override
    public void getRecommend(int page, int count) {

        recommendModel.getRecommend(page, count, new IHomeRecommendContract.IModel.RecommendICllBack() {
            @Override
            public void onRecommendSuccess(RecommendBean recommendBean) {
                IBaseView view = getView();
                if (view instanceof IHomeRecommendContract.IView){
                    ((IHomeRecommendContract.IView) view).onRecommendSuccess(recommendBean);
                }
            }

            @Override
            public void onRecommendFailure(String str) {
                IBaseView view = getView();
                if (view instanceof IHomeRecommendContract.IView){
                    ((IHomeRecommendContract.IView) view).onRecommendFailure(str);
                }
            }
        });
    }
}
