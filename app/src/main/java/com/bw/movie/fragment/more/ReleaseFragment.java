package com.bw.movie.fragment.more;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.adapter.more.ReleaseAdapter;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.ComingSoonBean;
import com.bw.movie.bean.HotBean;
import com.bw.movie.bean.ReleaseBean;
import com.bw.movie.contract.IHomeMoreContract;
import com.bw.movie.presenter.IHomeMorePresenter;
import com.bw.movie.utils.RetUtils;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @ProjectName: wutenglongApp2
 * @Package: com.bw.movie.fragment.morefragment
 * @ClassName: HotFragment
 * @Description: java类作用描述
 * @CreateDate: 2020/4/21 22:44
 * @UpdateUser: 武腾龙
 * @UpdateDate: 2020/4/21 22:44
 */
public class ReleaseFragment extends BaseFragment implements IHomeMoreContract.IView {
    @BindView(R.id.xrv)
    XRecyclerView xrv;
    Unbinder unbinder;
    ArrayList<ReleaseBean.ResultBean> list = new ArrayList<>();
    int page = 1;
    int count = 5;
    @Override
    protected BasePresenter initPresenter() {
        return new IHomeMorePresenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.more_releasefragment;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {
        if (RetUtils.getInstance().isNetWork(getContext())){
            BasePresenter presenter = getPresenter();
            if (presenter instanceof IHomeMorePresenter){
                ((IHomeMorePresenter) presenter).getRelease(page,count);

            }
            xrv.setPullRefreshEnabled(true);
            xrv.setLoadingMoreEnabled(true);
            xrv.setLoadingListener(new XRecyclerView.LoadingListener() {
                @Override
                public void onRefresh() {
                    list.clear();
                    page = 1;
                    BasePresenter presenter = getPresenter();
                    if (presenter instanceof IHomeMorePresenter){
                        ((IHomeMorePresenter) presenter).getRelease(page,count);
                    }
                    xrv.refreshComplete();
                }

                @Override
                public void onLoadMore() {
                    page++;
                    BasePresenter presenter = getPresenter();
                    if (presenter instanceof IHomeMorePresenter){
                        ((IHomeMorePresenter) presenter).getRelease(page,count);

                    }
                    xrv.refreshComplete();
                }
            });
        }else {
            Toast.makeText(getContext(), "请检查网络", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onHotSuccess(HotBean hotBean) {

    }

    @Override
    public void onComingSoonSuccess(ComingSoonBean comingSoonBean) {

    }

    @Override
    public void onReleaseSuccess(ReleaseBean releaseBean) {

        List<ReleaseBean.ResultBean> result = releaseBean.getResult();
        if (result!=null){
            list.addAll(result);
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, true);
        xrv.setLayoutManager(linearLayoutManager);
        ReleaseAdapter moreReleaseAdapter = new ReleaseAdapter(getContext(), list);
        xrv.setAdapter(moreReleaseAdapter);
    }

    @Override
    public void onHotFailure(String str) {

    }

    @Override
    public void onComingSoonFailure(String str) {

    }

    @Override
    public void onReleaseFailure(String str) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
