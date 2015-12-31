package com.cnpay.steepapp.activity;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cnpay.steepapp.R;
import com.cnpay.steepapp.util.SPUtil;
import com.cnpay.steepapp.view.GestureContentView;
import com.cnpay.steepapp.view.GestureDrawline;

/**
 * 检验手势密码
 *
 * @author zenghonghua
 * @包名: com.cnpay.steepapp.activity
 * @修改记录:
 * @公司: 深圳华夏通宝信息技术有限公司
 * @date 2015/12/31 0031
 */
public class GestureVerifyActivity extends BaseActivity implements View.OnClickListener {

    /** 手机号码*/
    public static final String PARAM_PHONE_NUMBER = "PARAM_PHONE_NUMBER";
    /** 意图 */
    public static final String PARAM_INTENT_CODE = "PARAM_INTENT_CODE";

    private ImageView mImgUserLogo;
    private TextView mTextPhoneNumber;

    private TextView mTextTip;          //提示是否错误

    private FrameLayout mGestureContainer;      //容器

    private GestureContentView mGestureContentView;

    private TextView mTextForget;
    private TextView mTextOther;
    private String mParamPhoneNumber;
    private long mExitTime = 0;
    private int mParamIntentCode;
    private String password;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture_verify);
        setImmergeState();
        setUpViews();
        setUpListeners();
    }


    private void setUpViews() {
        //设置标题
        LinearLayout layout = (LinearLayout) findViewById(R.id.base_title);
        setTitle(layout,"检验密码","",null);

        mImgUserLogo = (ImageView) findViewById(R.id.user_logo);
        mTextPhoneNumber = (TextView) findViewById(R.id.text_phone_number);

        mTextTip = (TextView) findViewById(R.id.text_tip);
        mGestureContainer = (FrameLayout) findViewById(R.id.gesture_container);

        mTextForget = (TextView) findViewById(R.id.text_forget_gesture);
        mTextOther = (TextView) findViewById(R.id.text_other_account);

        password = SPUtil.getString(this, "password", "");  //获取设置好的密码
        if(password.length()<4) {
            password = "1235789";   //没设置过就设置默认密码
        }

        // 初始化一个显示各个点的viewGroup
        mGestureContentView = new GestureContentView(this, true, password,
                new GestureDrawline.GestureCallBack() {

                    @Override
                    public void onGestureCodeInput(String inputCode) {

                    }

                    @Override
                    public void checkedSuccess() {
                        mGestureContentView.clearDrawlineState(0L);
                        Toast.makeText(GestureVerifyActivity.this, "密码正确", Toast.LENGTH_SHORT).show();
                        GestureVerifyActivity.this.finish();
                    }

                    @Override
                    public void checkedFail() {
                        mGestureContentView.clearDrawlineState(1300L);
                        mTextTip.setVisibility(View.VISIBLE);
                        mTextTip.setText(Html.fromHtml("<font color='#c70c1e'>密码错误</font>"));
                        // 左右移动动画
                        Animation shakeAnimation = AnimationUtils.loadAnimation(GestureVerifyActivity.this, R.anim.shake);
                        mTextTip.startAnimation(shakeAnimation);
                    }
                });

        // 设置手势解锁显示到哪个布局里面
        mGestureContentView.setParentView(mGestureContainer);
    }

    private void setUpListeners() {
        mTextForget.setOnClickListener(this);
        mTextOther.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
        }
    }

}
