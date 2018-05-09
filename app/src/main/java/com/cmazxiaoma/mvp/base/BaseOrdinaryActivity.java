package com.cmazxiaoma.mvp.base;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;

import com.cmazxiaoma.mvp.R;

/**
 * Description: 沉梦昂志
 * Data：2017/5/10-15:44
 * Author: xiaoma
 */

public abstract class BaseOrdinaryActivity extends AppCompatActivity{
    private Button rightArrow,share;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initData();
        getWindow().setFormat(PixelFormat.RGBA_8888);
        initContentView(savedInstanceState);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setElevation(0);
            getSupportActionBar().hide();
            if(isShowCustomActionBar()){
                getSupportActionBar().setDisplayShowTitleEnabled(false);
                getSupportActionBar().setDisplayShowCustomEnabled(true);
                View view=getLayoutInflater().inflate(R.layout.actionbar_share,null);
                initActionBarView(view);
                getSupportActionBar().setCustomView(view,new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                ActionBar.LayoutParams lp= (ActionBar.LayoutParams) view.getLayoutParams();
                lp.gravity= Gravity.HORIZONTAL_GRAVITY_MASK|Gravity.CENTER_HORIZONTAL;
                getSupportActionBar().setCustomView(view,lp);
                getSupportActionBar().show();
            }
        }
    }


    public void startActivity(Class<?> clz,Bundle bundle){
        Intent intent=new Intent(this,clz);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void initActionBarView(View view){
        rightArrow= (Button) view.findViewById(R.id.actionbar_share_leftArrow);
        share= (Button) view.findViewById(R.id.actionbar_share_share);
        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    public abstract boolean isShowCustomActionBar();
    public abstract void initContentView(Bundle savedInstanceState);
    public abstract  int getLayoutId();
    public abstract void initData();
}
