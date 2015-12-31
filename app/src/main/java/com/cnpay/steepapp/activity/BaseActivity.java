package com.cnpay.steepapp.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cnpay.steepapp.R;

/**
 * @author zenghonghua
 * @包名:
 * @修改记录:
 * @公司:
 * @date 2015/12/29 0029
 */
public class BaseActivity extends AppCompatActivity {

    private ImageView iv_left;
    private TextView tv_right;
    private TextView tv_center;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 初始化控件
     *
     * @param layout
     */
    private void init(LinearLayout layout) {
        iv_left = (ImageView) layout.findViewById(R.id.base_iv_left);
        tv_center = (TextView) layout.findViewById(R.id.base_tv_center);
        tv_right = (TextView) layout.findViewById(R.id.base_tv_right);
        /**返回按钮*/
        iv_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * 设置标题
     *
     * @param layout
     * @param centerTitle   中间标题
     * @param rightTitle    右侧标题,不能为空,可以设置为转Sting的大招 ""
     * @param rightGotoClass    点击右侧需要进入的activity
     */
    public void setTitle(LinearLayout layout, String centerTitle, String rightTitle, final Class rightGotoClass) {
        init(layout);
        tv_center.setText(centerTitle);
        tv_right.setText(rightTitle);
        if (rightGotoClass != null) {
            tv_right.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(), rightGotoClass));
                }
            });
        }
    }

    /**
     * 设置状态栏和app标题栏颜色一致
     */
    protected void setImmergeState() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏,如果有虚拟键的手机下面的这行注释掉,要不然虚拟键也会透明,如果是下面有一排RadioGroup的App,效果感人
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

            LinearLayout linear_bar = (LinearLayout) findViewById(R.id.linear_bar);
            linear_bar.setVisibility(View.VISIBLE);
            int statusHeight = getStatusBarHeight();
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) linear_bar.getLayoutParams();

            params.height = statusHeight;
            linear_bar.setLayoutParams(params);
        }
    }

    /**
     * 用于获取状态栏的高度。 使用Resource对象获取
     *
     * @return 返回状态栏高度的像素值。
     */
    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen",
                "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

}
