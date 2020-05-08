package com.bw.movie.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

//import com.bawei.wutenglongapp.R;
import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.RegisterBean;
import com.bw.movie.bean.SendBean;
import com.bw.movie.contract.IHomeRegisterContract;
import com.bw.movie.presenter.IHomeRegisterPresenter;
import com.bw.movie.utils.EncryptUtil;
import com.bw.movie.utils.RetUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisteredActivity extends BaseActivity implements IHomeRegisterContract.IView {


    @BindView(R.id.et_check)
    EditText etCheck;
    @BindView(R.id.bt_gain)
    Button btGain;
    @BindView(R.id.tv_zhuce)
    TextView tvZhuce;
    @BindView(R.id.bt_reg)
    Button btReg;
    @BindView(R.id.iv_shape)
    ImageView ivShape;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.iv_group)
    ImageView ivGroup;
    @BindView(R.id.iv_eye)
    ImageView ivEye;

    private String email;
    private String name;
    private String pwd;
    private String check;
    private String encrypt;

    @Override
    protected int getLayout() {
        return R.layout.activity_registered;
    }

    @Override
    protected BasePresenter initPresenter() {
        return new IHomeRegisterPresenter(this);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        if (RetUtils.getInstance().isNetWork(this)){

        }else {
            Toast.makeText(this, "请检查网络", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSuccess(RegisterBean registerBean) {

        Log.i("xxx", registerBean.getMessage() + "注册");
        startActivity(new Intent(RegisteredActivity.this,LoginActivity.class));
        finish();
    }

    @Override
    public void onSendSuccess(SendBean sendBean) {
        Log.i("xxx", sendBean.getStatus() + "验证");

    }

    @Override
    public void onFailure(String str) {

    }

    @Override
    public void onSendFailure(String str) {

    }

    @OnClick({R.id.bt_gain, R.id.tv_zhuce, R.id.bt_reg})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_gain:
                email = etEmail.getText().toString();
                BasePresenter presenter = getPresenter();
                if (presenter instanceof IHomeRegisterPresenter){
                    ((IHomeRegisterPresenter) presenter).getSend(email);
                }
                Toast.makeText(this, "发送验证码成功", Toast.LENGTH_SHORT).show();
//                Log.i("xxx","获取");
                break;
            case R.id.tv_zhuce:

                startActivity(new Intent(RegisteredActivity.this,LoginActivity.class));
                finish();
                break;
            case R.id.bt_reg:
                name = etName.getText().toString();
                pwd = etPwd.getText().toString();
                encrypt = EncryptUtil.encrypt(pwd);
                check = etCheck.getText().toString();
                BasePresenter presenter1 = getPresenter();
                if (presenter1 instanceof IHomeRegisterPresenter){
                    ((IHomeRegisterPresenter) presenter1).getRegister(name,encrypt,email,check);
                }
                Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();


                break;
        }
    }
}
