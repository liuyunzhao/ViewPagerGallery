package com.cn.lyz.viewpagegallery;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.cn.lyz.viewpagegallery.viewpage_all_response.VpAllResponseActivity;
import com.cn.lyz.viewpagegallery.viewpage_fragment.VpIncludeFragmentActivity;
import com.cn.lyz.viewpagegallery.viewpage_middle_response.VpMiddleResponseActivity;

public class MainActivity extends AppCompatActivity {

    private Button mBtn1;
    private Button mBtn2;
    private Button mBtn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initClick();
    }

    private void initView() {
        mBtn1 = findViewById(R.id.btn1);
        mBtn2 = findViewById(R.id.btn2);
        mBtn3 = findViewById(R.id.btn3);
    }

    private void initClick() {
        mBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, VpAllResponseActivity.class));
            }
        });

        mBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, VpIncludeFragmentActivity.class));

            }
        });

        mBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, VpMiddleResponseActivity.class));

            }
        });
    }
}
