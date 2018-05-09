package com.cmazxiaoma.mvp.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.widget.FrameLayout;

import com.cmazxiaoma.mvp.R;
import com.cmazxiaoma.mvp.Utils.UtilsLog;
import com.cmazxiaoma.mvp.base.BaseOrdinaryActivity;
import com.cmazxiaoma.mvp.bean.TabEntity;
import com.cmazxiaoma.mvp.ui.fragment.NewsFragemnt;
import com.cmazxiaoma.mvp.ui.fragment.PhotoFragment;
import com.cmazxiaoma.mvp.ui.fragment.VideoContain.BaseVideoFragment;
import com.cmazxiaoma.mvp.ui.fragment.VideoFragment;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: 沉梦昂志
 * Data：2017/5/11-17:16
 * Author: xiaoma
 */

public class SecondActivity extends BaseOrdinaryActivity implements BaseVideoFragment.BackHandlerInterface{
    private ArrayList<Fragment> fragmentList;
    private ArrayList<CustomTabEntity> tabList;
    private FrameLayout frameLayout;
    private CommonTabLayout tabLayout;
    private int[] icon_normal=new int[]{
            R.drawable.news_normal,R.drawable.video_normal,R.drawable.photo_normal
    };

    private int[] icon_selected=new int[]{
            R.drawable.news_selected,R.drawable.video_selected,R.drawable.photo_selected
    };
    private String[] title=new String[]{"首页","视频","图片"};
    private BaseVideoFragment selectedFragment;

    @Override
    public boolean isShowCustomActionBar() {
        return false;
    }

    @Override
    public void initContentView(Bundle savedInstanceState) {
        frameLayout= (FrameLayout) findViewById(R.id.act_sec_container);
        tabLayout= (CommonTabLayout) findViewById(R.id.act_sec_tablayout);
        tabLayout.setTabData(tabList,this,R.id.act_sec_container,fragmentList);
        tabLayout.showMsg(0,99);
        tabLayout.showMsg(1,99);
        tabLayout.showMsg(2,99);
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                tabLayout.hideMsg(position);
            }
            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_second;
    }

    @Override
    public void initData() {
        fragmentList=new ArrayList<>();
        tabList=new ArrayList<>();
        fragmentList.add(NewsFragemnt.newInstance("news"));
        fragmentList.add(VideoFragment.newInstance("video"));
        fragmentList.add(PhotoFragment.newInstance("photo"));
        //设置tab的标题，正常图标，选中图标
        for(int i=0;i<title.length;i++){
            tabList.add(new TabEntity(title[i],icon_normal[i],icon_selected[i]));
        }
    }

    @Override
    public void setSelectedFragment(BaseVideoFragment fragment) {
        this.selectedFragment=fragment;
    }

    @Override
    public void onBackPressed() {
        if(selectedFragment!=null&&selectedFragment.onBackPressed()) {
            UtilsLog.i("you pressed back keyword");
            ActionBar actionBar=getSupportActionBar();
            if(actionBar!=null){
               boolean show= actionBar.isShowing();
                UtilsLog.i("actionBar show的状态="+show);
                if(show){
                    actionBar.hide();
                }
            }
            return;
        }
        UtilsLog.i("cnm");
        super.onBackPressed();
    }
}
