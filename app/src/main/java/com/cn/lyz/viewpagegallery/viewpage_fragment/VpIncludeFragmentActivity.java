package com.cn.lyz.viewpagegallery.viewpage_fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.cn.lyz.viewpagegallery.R;
import com.cn.lyz.viewpagegallery.indicator.LinePageIndicator;
import com.cn.lyz.viewpagegallery.viewpage_all_response.ScreenUtils;
import com.cn.lyz.viewpagegallery.viewpage_all_response.ZoomOutPageTransformer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yunzhao.liu on 2017/12/6
 * ViewPager包含Fragment全屏响应
 */

public class VpIncludeFragmentActivity extends AppCompatActivity {
    private ViewPager mVp;
    private LinePageIndicator mIndicator;
    private StatePagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vp_include_fragment);
        initView();
        initData();
    }

    private void initView() {
        mVp = findViewById(R.id.vp);
        mIndicator = findViewById(R.id.indicator);
    }

    private void initData() {
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mVp.getLayoutParams();
        lp.width = ScreenUtils.getScreenWidth(this);
        lp.height = ScreenUtils.getScreenHeight(this) / 2;
        mVp.setLayoutParams(lp);
        setVpFragment();
    }

    private void setVpFragment() {
        List<PageFragment> fragments = new ArrayList<>();
        int size = 5;
        for (int i = 0; i < size; i++) {
            PageFragment instance = PageFragment.getInstance(i);
            fragments.add(instance);
        }

        //设置底部线条的宽度
        int lineAllWidth = ScreenUtils.dpToPxInt(this, 30);
        int surplus = ScreenUtils.getScreenWidth(this) - size * lineAllWidth - 100;
        if (surplus < 0) {
            mIndicator.setVisibility(View.GONE);
        } else {
            mIndicator.setVisibility(View.VISIBLE);
        }
        setHDAdapter(fragments);
    }

    private void setHDAdapter(List<PageFragment> fragments) {
        if (mAdapter == null) {
            mAdapter = new StatePagerAdapter(getSupportFragmentManager(), fragments);
        } else {
            mAdapter.setUpdatePagePicData(fragments);
        }

        //这是默认两页之间的距离
        int screenWidth = ScreenUtils.getScreenWidth(this);
        double v = screenWidth/2/2+(screenWidth/2/2+screenWidth/2*0.2/2);//因为缩放了0.8倍
//        double v = screenWidth / 2 + screenWidth / 2 * 0.2/2;//简化代码

        //这是设置的两页之间的距离
        int distance = (int) (v-100);
        mVp.setAdapter(mAdapter);
        //设置缓存数为展示的数目
        mVp.setOffscreenPageLimit(fragments.size());
        //设置切换动画
        mVp.setPageTransformer(true, new ZoomOutPageTransformer());
        //这里要给负数
        mVp.setPageMargin(-distance);
        //设置Page间间距
        mIndicator.setViewPager(mVp, 0);
    }
}
