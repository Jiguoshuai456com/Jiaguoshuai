package com.bw.movie.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.bw.movie.R;
import com.bw.movie.adapter.SearchAdapter;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.FindMoviewByKeWordBean;
import com.bw.movie.contract.IHomeKeWordContract;
import com.bw.movie.presenter.IHomeKeWordPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends BaseActivity implements IHomeKeWordContract.IView {


    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.shape)
    ImageView shape;
    @BindView(R.id.et_search)
    EditText etSearch;
    @Override
    protected int getLayout() {
        return R.layout.activity_search;
    }

    @Override
    protected BasePresenter initPresenter() {
        return new IHomeKeWordPresenter(this);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

        etSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String string = etSearch.getText().toString();
                BasePresenter presenter = getPresenter();
                if (presenter instanceof IHomeKeWordPresenter){
                    ((IHomeKeWordPresenter) presenter).onSuccess(string,1,5);
                }
            }
        });

    }

    @Override
    public void onSuccess(FindMoviewByKeWordBean findMoviewByKeWordBean) {
        List<FindMoviewByKeWordBean.ResultBean> result = findMoviewByKeWordBean.getResult();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SearchActivity.this, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(linearLayoutManager);
        SearchAdapter searchAdapter = new SearchAdapter(SearchActivity.this, result);
        rv.setAdapter(searchAdapter);
    }

    @Override
    public void onFailure(String str) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
