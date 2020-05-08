package com.bw.movie.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

//import com.bawei.wutenglongapp.R;
import com.bw.movie.R;
import com.bw.movie.base.App;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.LoginBean;
import com.bw.movie.contract.IHomeLoginContract;
import com.bw.movie.presenter.IHomeLoginPresenter;
import com.bw.movie.utils.EncryptUtil;
import com.bw.movie.utils.SPUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements IHomeLoginContract.IView {


    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.bt_forget)
    Button btForget;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    @BindView(R.id.bt_login)
    Button btLogin;
    @BindView(R.id.bt_wx)
    ImageButton btWx;
    @BindView(R.id.iv_left)
    ImageView ivShape;

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected BasePresenter initPresenter() {
        return new IHomeLoginPresenter(this);
    }

    @Override
    protected void initView() {


    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.bt_forget, R.id.tv_register, R.id.bt_login, R.id.bt_wx})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_forget:

                break;
            case R.id.tv_register:
                startActivity(new Intent(LoginActivity.this, RegisteredActivity.class));
                finish();
                break;
            case R.id.bt_login:
                String s = etEmail.getText().toString();
                String encrypt = EncryptUtil.encrypt(etPwd.getText().toString());
                BasePresenter presenter = getPresenter();
                if (presenter instanceof IHomeLoginPresenter){
                    ((IHomeLoginPresenter) presenter).getRegister(s,encrypt);
                }

                break;
            case R.id.bt_wx:

                break;
        }
    }

    @Override
    public void onSuccess(LoginBean loginBean) {
        LoginBean.ResultBean result = loginBean.getResult();
        int userId = result.getUserId();
        String sessionId = result.getSessionId();
        //存入SP
        SPUtils.putString(App.getAppContext(),SPUtils.NAME,SPUtils.USERID,String.valueOf(userId));
        SPUtils.putString(App.getAppContext(),SPUtils.NAME,SPUtils.SESSIONID,sessionId);

        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onFailure(String str) {

    }
}
