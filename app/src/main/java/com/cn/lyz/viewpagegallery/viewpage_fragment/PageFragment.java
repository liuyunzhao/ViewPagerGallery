package com.cn.lyz.viewpagegallery.viewpage_fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cn.lyz.viewpagegallery.R;
import com.cn.lyz.viewpagegallery.viewpage_all_response.ScreenUtils;

/**
 * Created by yunzhao.liu on 2017/12/6
 */

public class PageFragment extends Fragment {
    private int position;
    private TextView mTv;
    private RelativeLayout mRoot;

    public static PageFragment getInstance(int position) {
        PageFragment fragment = new PageFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        position = getArguments().getInt("position", 0);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page_layout, container, false);
        mRoot = view.findViewById(R.id.root);
        mTv = view.findViewById(R.id.position);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) mTv.getLayoutParams();

        lp.width = ScreenUtils.getScreenWidth(getActivity()) / 2;
        lp.height = ScreenUtils.getScreenHeight(getActivity()) / 2;
        mTv.setLayoutParams(lp);
        mTv.setText(position + "");
        //不要直接给根布局mRoot设置点击事件，因为有时即便点击了两侧page但事件响应的为中间page
        //当然可以使用ViewPagerAllResponse来代替
        mTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), position+"", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
