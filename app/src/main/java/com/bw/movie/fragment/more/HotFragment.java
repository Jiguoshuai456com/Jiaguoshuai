package com.bw.movie.fragment.more;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.adapter.more.HotAdapter;
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

/**
 * @ProjectName: wutenglongApp2
 * @Package: com.bw.movie.fragment.morefragment
 * @ClassName: HotFragment
 * @Description: java类作用描述
 * @CreateDate: 2020/4/21 22:44
 * @UpdateUser: 武腾龙
 * @UpdateDate: 2020/4/21 22:44
 */
public class HotFragment extends BaseFragment implements IHomeMoreContract.IView {


    @BindView(R.id.xrv)
    XRecyclerView xrv;
    int page = 1;
    int count = 5;
    List<HotBean.ResultBean> list = new ArrayList<>();

    @Override
    protected BasePresenter initPresenter() {
        return new IHomeMorePresenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.more_hotfragment;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {

        if (RetUtils.getInstance().isNetWork(getContext())){
            BasePresenter presenter = getPresenter();
            if (presenter instanceof IHomeMorePresenter){
                ((IHomeMorePresenter) presenter).getHot(page,count);
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
                        ((IHomeMorePresenter) presenter).getHot(page,count);
                    }
                    xrv.refreshComplete();
                }

                @Override
                public void onLoadMore() {
                    page++;
                    BasePresenter presenter = getPresenter();
                    if (presenter instanceof IHomeMorePresenter){
                        ((IHomeMorePresenter) presenter).getHot(page,count);
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
        List<HotBean.ResultBean> result = hotBean.getResult();
        if (result!=null){
            list.addAll(result);

        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, true);
        xrv.setLayoutManager(linearLayoutManager);

        HotAdapter moreHotAdapter = new HotAdapter(getContext(),list);
        xrv.setAdapter(moreHotAdapter);
    }

    @Override
    public void onComingSoonSuccess(ComingSoonBean comingSoonBean) {

    }

    @Override
    public void onReleaseSuccess(ReleaseBean releaseBean) {

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
}
