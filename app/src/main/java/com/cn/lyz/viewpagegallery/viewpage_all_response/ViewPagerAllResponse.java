package com.cn.lyz.viewpagegallery.viewpage_all_response;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by yunzhao.liu on 2017/12/6
 */
public class ViewPagerAllResponse extends ViewPager {

    private final static float DISTANCE = 10;//默认距离
    private float downX;
    private float downY;
    private float upX;
    private float upY;

    public ViewPagerAllResponse(Context context) {
        this(context, null);
    }

    public ViewPagerAllResponse(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            downX = ev.getX();
            downY = ev.getY();
        } else if (ev.getAction() == MotionEvent.ACTION_UP) {
            upX = ev.getX();
            upY = ev.getY();
            //滑动时进入
            if (Math.abs(upX - downX) > DISTANCE || Math.abs(upY - downY) > DISTANCE) {
                return super.dispatchTouchEvent(ev);
            }
            //点击时进入
            View view = clickPageOnScreen(ev);
            if (view != null) {
                int index = (Integer) view.getTag();
                if (getCurrentItem() != index) {
                    setCurrentItem(index);
                    return true;
                }
            } else {
                return true;
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 是否点击到两边的View
     * 点到View则返回该View,否则返回null
     */
    private View clickPageOnScreen(MotionEvent ev) {
        int childCount = getChildCount();
        int currentIndex = getCurrentItem();
        int[] location = new int[2];
        float x = ev.getRawX();

        for (int i = 0; i < childCount; i++) {
            View v = getChildAt(i);
            int position = (Integer) v.getTag();
            v.getLocationOnScreen(location);
            int minX = location[0];
            int maxX = location[0] + v.getWidth();

            if (position < currentIndex) {
                //maxX-view宽度*View缩放了宽度的一半
                maxX -= v.getWidth() * (1 - ZoomOutPageTransformer.MIN_SCALE) * 0.5;
                minX -= v.getWidth() * (1 - ZoomOutPageTransformer.MIN_SCALE) * 0.5;
            }

            if ((x > minX && x < maxX)) {
                return v;
            }
        }
        return null;
    }

    /**
     * 解决当手指在第1页左边空白区域上下滑动时，不让page的点击事件触发
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downY = ev.getY();
                break;
            case MotionEvent.ACTION_UP:
                upY = ev.getY();
                if (Math.abs(upY - downY) > DISTANCE) {
                    return true;//拦截事件 传递给自己的onTouchEvent处理
                }
                break;
            default:
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downY = ev.getY();
                break;
            case MotionEvent.ACTION_UP:
                upY = ev.getY();
                if (Math.abs(upY - downY) > DISTANCE) {
                    return super.onTouchEvent(ev);//不消费事件，事件继续往上传递
                }
                break;
            default:
                break;
        }
        return super.onTouchEvent(ev);
    }
}
