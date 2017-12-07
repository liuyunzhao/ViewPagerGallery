package com.cn.lyz.viewpagegallery.viewpage_fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by yunzhao.liu on 2017/7/17
 */

public class StatePagerAdapter extends FragmentStatePagerAdapter {

    private List<PageFragment> mList;

    public StatePagerAdapter(FragmentManager fm, List<PageFragment> list) {
        super(fm);
        this.mList = list;
    }

    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    /**
     * 更新数据
     */
    public void setUpdatePagePicData(List<PageFragment> list) {
        this.mList = list;
    }

}
