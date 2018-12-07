package com.lanmei.cict.ui.login;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lanmei.cict.R;
import com.lanmei.cict.event.RegisterEvent;
import com.lanmei.cict.utils.CommonUtils;
import com.xson.common.app.BaseActivity;
import com.xson.common.helper.HttpClient;
import com.xson.common.helper.SharedAccount;
import com.xson.common.utils.CodeCountDownTimer;
import com.xson.common.utils.StringUtils;
import com.xson.common.utils.UIHelper;
import com.xson.common.widget.CenterTitleToolbar;
import com.xson.common.widget.DrawClickableEditText;

import org.greenrobot.eventbus.EventBus;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 注册、忘记密码、重设密码
 */
public class RegisterActivity extends BaseActivity implements Toolbar.OnMenuItemClickListener {

    @InjectView(R.id.toolbar)
    CenterTitleToolbar toolbar;
    @InjectView(R.id.ll_name)
    LinearLayout llName;
    @InjectView(R.id.user_name_et)
    DrawClickableEditText userNameEt;//
    @InjectView(R.id.phone_et)
    DrawClickableEditText phoneEt;
    @InjectView(R.id.code_et)
    DrawClickableEditText codeEt;
    @InjectView(R.id.obtainCode_bt)
    Button obtainCodeBt;
    @InjectView(R.id.showPwd_iv)
    ImageView showPwdIv;
    @InjectView(R.id.showPwd_again_iv)
    ImageView showPwdAgainIv;
    @InjectView(R.id.pwd_et)
    DrawClickableEditText pwdEt;
    @InjectView(R.id.pwd_again_et)
    DrawClickableEditText pwdAgainEt;
    @InjectView(R.id.register_bt)
    Button button;

    Boolean isRegister;

    private CodeCountDownTimer mCountDownTimer;//获取验证码倒计时


    @Override
    public int getContentViewId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initAllMembersView(Bundle savedInstanceState) {
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();

        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.mipmap.back);

        //初始化倒计时
        initCountDownTimer();
        isRegister = StringUtils.isSame(getIntent().getStringExtra("value"), CommonUtils.isOne);
        if (isRegister) {
            actionbar.setTitle(R.string.register);
        } else {
            llName.setVisibility(View.GONE);
            actionbar.setTitle("Retrieve password");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_register, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_login:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initCountDownTimer() {
        //初始化倒计时
        mCountDownTimer = new CodeCountDownTimer(this, 60 * 1000, 1000, obtainCodeBt);
    }

    private void loadObtainCode(String phone) {

    }


    //注册或找回密码、修改密码
    private void registerOrRetrievePwd(String userName, String phone, String code, String pwd, String phone1) {
        HttpClient httpClient = HttpClient.newInstance(this);
        if (isRegister) {
            register(userName, phone, pwd, code, httpClient, phone1);
        } else {
            forgotPwd(phone, pwd, code, httpClient);
        }
    }

    private void forgotPwd(String phone, String pwd, String code, HttpClient httpClient) {

    }


    //注册
    private void register(String userName, final String phone, String pwd, String code, HttpClient httpClient, String phone1) {
    }


    private boolean isShowPwd = false;//是否显示密码
    private boolean isShowPwdAgain = false;//是否再次显示密码


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        onBackPressed();
        return true;
    }


    @OnClick({R.id.showPwd_iv,R.id.showPwd_again_iv, R.id.register_bt, R.id.obtainCode_bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.showPwd_iv:
                showPwd(isShowPwd,pwdEt,showPwdIv,1);
                break;
            case R.id.showPwd_again_iv:
                showPwd(isShowPwdAgain,pwdAgainEt,showPwdAgainIv,2);
                break;
            case R.id.register_bt://注册
                if (isRegister){
                    loadRegister();
                }else {

                }
                break;
            case R.id.obtainCode_bt://获取验证码
                String mobile = CommonUtils.getStringByEditText(phoneEt);//电话号码
                if (StringUtils.isEmpty(mobile)) {
                    UIHelper.ToastMessage(this, R.string.input_phone_number);
                    return;
                }
//                if (!StringUtils.isMobile(phone)) {
//                    Toast.makeText(this, R.string.not_mobile_format, Toast.LENGTH_SHORT).show();
//                    return;
//                }
                codeEt.setText(CommonUtils.generateNumberString(6,false));
                mCountDownTimer.start();
//                loadObtainCode(phone);
                break;
        }
    }

    private void showPwd(boolean isShow,DrawClickableEditText editText,ImageView imageView,int type){
        if (!isShow) {//再次显示密码
            editText.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            imageView.setImageResource(R.mipmap.pwd_on);
        } else {//隐藏密码
            editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            imageView.setImageResource(R.mipmap.pwd_off);
        }
        if (type == 1){
            isShowPwd = !isShow;
        }else {
            isShowPwdAgain = !isShow;
        }
    }

    private void loadRegister() {
        String userName = CommonUtils.getStringByEditText(userNameEt);//姓名
        if (StringUtils.isEmpty(userName)) {
            UIHelper.ToastMessage(this, "Please enter your name");
            return;
        }
      String  mobile = CommonUtils.getStringByEditText(phoneEt);//电话号码
        if (StringUtils.isEmpty(mobile)) {
            UIHelper.ToastMessage(this, R.string.input_phone_number);
            return;
        }

        String code = CommonUtils.getStringByEditText(codeEt);//
        if (StringUtils.isEmpty(code)) {
            UIHelper.ToastMessage(this, "Please enter the verification code");
            return;
        }
        String pwd = CommonUtils.getStringByEditText(pwdEt);//
        if (StringUtils.isEmpty(pwd) || pwd.length() < 6) {
            UIHelper.ToastMessage(this, R.string.input_password_count);
            return;
        }
        String pwdAgain = CommonUtils.getStringByEditText(pwdAgainEt);//
        if (StringUtils.isEmpty(pwdAgain)) {
            UIHelper.ToastMessage(this, "Please enter your 6-18 bit password again");
            return;
        }
        if (!StringUtils.isSame(pwd, pwdAgain)) {
            UIHelper.ToastMessage(this, "The passwords do not match");
            return;
        }
        SharedAccount.getInstance(this).saveMobile(mobile);
        SharedAccount.getInstance(this).saveName(userName);
        SharedAccount.getInstance(this).savePwd(pwd);
        EventBus.getDefault().post(new RegisterEvent(mobile));
        finish();
//        registerOrRetrievePwd(userName, phone, code, pwd, phone1);
    }

}
