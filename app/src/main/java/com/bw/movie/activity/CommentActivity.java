package com.bw.movie.activity;

import android.content.Intent;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.CommentBean;
import com.bw.movie.contract.IHomeCommentContract;
import com.bw.movie.presenter.IHomeCommentPresenter;

import butterknife.BindView;
import butterknife.OnClick;

public class CommentActivity extends BaseActivity implements IHomeCommentContract.IView {

    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.fen)
    TextView fen;
    @BindView(R.id.rb)
    RatingBar rb;
    @BindView(R.id.tv)
    EditText tv;
    @BindView(R.id.bu)
    Button bu;
    private int id;
    double score;


    @Override
    protected int getLayout() {
        return R.layout.activity_comment;
    }

    @Override
    protected BasePresenter initPresenter() {
        return new IHomeCommentPresenter(this);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        String name1 = intent.getStringExtra("name");
        name.setText(name1);

        rb.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                fen.setText("我的评分（"+rating+"）");
                score = rating;
            }
        });


    }

    @OnClick(R.id.bu)
    public void onViewClicked() {
        String string1 = tv.getText().toString();
        BasePresenter presenter = getPresenter();
        if (presenter instanceof IHomeCommentPresenter){
            ((IHomeCommentPresenter) presenter).onComment(id,string1,score);
        }

    }

    @Override
    public void onCommentSuccess(CommentBean commentBean) {
        Log.i("commentBean",commentBean.getMessage());
        Toast.makeText(this, ""+commentBean.getMessage(), Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, "评论成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCommentFailure(String str) {
        Log.i("commentBean",str);

    }
}
