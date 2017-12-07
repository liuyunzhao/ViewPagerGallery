package com.cn.lyz.viewpagegallery.viewpage_all_response;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.cn.lyz.viewpagegallery.R;

import java.util.ArrayList;

/**
 * Created by yunzhao.liu on 2017/12/6
 * ViewPager全屏响应
 */
public class VpAllResponseActivity extends AppCompatActivity {

    private RelativeLayout mRoot;
    private ViewPagerAllResponse mVp;
    ArrayList<Page> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vp_all_response);
        initView();
        initClick();
        initData();
    }

    private void initView() {
        mRoot = findViewById(R.id.root);
        mVp = findViewById(R.id.vp);
    }

    private void initClick() {
        mRoot.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return mVp.dispatchTouchEvent(event);
            }
        });
    }

    private void initData() {
        Page page = null;
        for (int i = 0; i < 15; i++) {
            page = new Page();
            page.setPageNum(i);
            list.add(page);
        }

        //设置可滑动Viewpager的范围
        RelativeLayout.LayoutParams rll = (RelativeLayout.LayoutParams) mRoot.getLayoutParams();
        rll.width = ScreenUtils.getScreenWidth(this);
        rll.height = ScreenUtils.getScreenHeight(this) / 2;
        mRoot.setLayoutParams(rll);

        //设置Viewpager大小为屏幕的一半
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) mVp.getLayoutParams();
        lp.width = ScreenUtils.getScreenWidth(this) / 2;
        lp.height = ScreenUtils.getScreenHeight(this) / 2;
        mVp.setLayoutParams(lp);

        ViewpagerAdapter adapter = new ViewpagerAdapter(this, mVp, list);
        mVp.setAdapter(adapter);
        //设置缓存数为展示的数目
        mVp.setOffscreenPageLimit(list.size());
        //设置切换动画
        mVp.setPageTransformer(true, new ZoomOutPageTransformer());
        //设置Page间间距
        mVp.setPageMargin(0);

        //注释：设置不设置setPageMargin大小两个page也是有间距的
        //间距大小=page原大小*（1-缩放倍数）/2
    }
}
