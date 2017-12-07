package com.cn.lyz.viewpagegallery.viewpage_middle_response;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;

import com.cn.lyz.viewpagegallery.R;
import com.cn.lyz.viewpagegallery.viewpage_all_response.Page;
import com.cn.lyz.viewpagegallery.viewpage_all_response.ScreenUtils;
import com.cn.lyz.viewpagegallery.viewpage_all_response.ZoomOutPageTransformer;

import java.util.ArrayList;

/**
 * Created by yunzhao.liu on 2017/12/6
 * ViewPager只中间页响应
 */
public class VpMiddleResponseActivity extends AppCompatActivity {

    private RelativeLayout mRoot;
    private ViewPager mVp;
    ArrayList<Page> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vp_middle_respinse);
        initView();
        initData();
    }

    private void initView() {
        mRoot = findViewById(R.id.root);
        mVp = findViewById(R.id.vp);
    }

    private void initData() {
        Page page = null;
        for (int i = 0; i < 15; i++) {
            page = new Page();
            page.setPageNum(i);
            list.add(page);
        }

        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) mVp.getLayoutParams();
        lp.width = ScreenUtils.getScreenWidth(this) / 2;
        lp.height = ScreenUtils.getScreenHeight(this) / 2;
        mVp.setLayoutParams(lp);

        ViewpagerMiddleAdapter adapter = new ViewpagerMiddleAdapter(this, mVp, list);
        mVp.setAdapter(adapter);
        //设置缓存数为展示的数目
        mVp.setOffscreenPageLimit(list.size());
        //设置切换动画
        mVp.setPageTransformer(true, new ZoomOutPageTransformer());
        //设置Page间间距
        mVp.setPageMargin(0);
    }
}
