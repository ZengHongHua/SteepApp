package com.cnpay.steepapp.util;

import android.content.Context;
import android.view.WindowManager;

/**
 * @author zenghonghua
 * @包名: com.cnpay.steepapp.util
 * @修改记录:
 * @公司: 深圳华夏通宝信息技术有限公司
 * @date 2015/12/31 0031
 */
public class AppUtil {

    /**
     * 获取屏幕分辨率
     * @param context
     * @return
     */
    public static int[] getScreenDispaly(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int width = windowManager.getDefaultDisplay().getWidth();// 手机屏幕的宽度
        int height = windowManager.getDefaultDisplay().getHeight();// 手机屏幕的高度
        int result[] = { width, height };
        return result;
    }
}
