package com.cn.lyz.viewpagegallery.viewpage_all_response;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Created by yunzhao.liu on 2017/7/10
 */

public class ScreenUtils {
    private static int SCREEN_WIDTH = 0;
    private static int SCREEN_HEIGHT = 0;

    /**
     * 获取屏幕高度
     */
    public static int getScreenWidth(Context context) {
        return getScreenWidth(context, false);
    }

    public static int getScreenWidth(Context context, boolean cached) {
        if (!cached && SCREEN_WIDTH > 0) return SCREEN_WIDTH;
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        if (cached) {
            return dm.widthPixels;
        }
        SCREEN_WIDTH = dm.widthPixels;
        return SCREEN_WIDTH;
    }

    /**
     * 获取屏幕宽度
     */
    public static int getScreenHeight(Context context) {
        return getScreenHeight(context, false);
    }

    public static int getScreenHeight(Context context, boolean cached) {
        if (!cached && SCREEN_HEIGHT > 0) return SCREEN_HEIGHT;
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        if (cached) {
            return dm.heightPixels;
        }
        SCREEN_HEIGHT = dm.heightPixels;
        return SCREEN_HEIGHT;
    }

    public static int dpToPxInt(Context context, float dp) {
        return (int) (dpToPx(context, dp) + 0.5f);
    }

    public static float dpToPx(Context context, float dp) {
        if (context == null) {
            return -1;
        }
        return dp * context.getResources().getDisplayMetrics().density;
    }
}
