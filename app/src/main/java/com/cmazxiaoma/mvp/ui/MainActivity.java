package com.cmazxiaoma.mvp.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.cmazxiaoma.mvp.R;
import com.cmazxiaoma.mvp.base.BaseOrdinaryActivity;
import com.cmazxiaoma.mvp.ui.adapter.FragmentAdapter;
import com.cmazxiaoma.mvp.ui.fragment.NewsFragemnt;
import com.cmazxiaoma.mvp.ui.fragment.PhotoFragment;
import com.cmazxiaoma.mvp.ui.fragment.VideoFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseOrdinaryActivity{
    private List<Fragment> fragmentList=new ArrayList<>();
    private TabLayout tabLayout;
    private FrameLayout container;
    private int[] icon_normal=new int[]{
            R.drawable.news_normal,R.drawable.video_normal,R.drawable.photo_normal
    };

    private int[] icon_selected=new int[]{
            R.drawable.news_selected,R.drawable.video_selected,R.drawable.photo_selected
    };
    private String[] title=new String[]{"首页","视频","图片"};
    private FragmentAdapter adapter;


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void initContentView(Bundle savedInstanceState) {
        tabLayout=(TabLayout) findViewById(R.id.act_main_tablayout);
        container= (FrameLayout) findViewById(R.id.container);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                changeTab(tab,true);
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                changeTab(tab,false);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        initTabLayout();
    }

    /**
     *
     * @param tab
     * @param flag
     * true为选中,false为未选中
     */
    private void changeTab(TabLayout.Tab tab,boolean flag) {
        View view=tab.getCustomView();
        if(view!=null){
            ImageView icon= (ImageView) view.findViewById(R.id.tab_icon);
            TextView text= (TextView) view.findViewById(R.id.tab_text);
            int position=tab.getPosition();
            Fragment fragment= (Fragment) adapter.instantiateItem(container,position);
            adapter.setPrimaryItem(container,position,fragment);
            adapter.finishUpdate(container);
            int[]icons;
            if(flag){
                icons=icon_selected;
                text.setTextColor(ContextCompat.getColor(this,R.color.salmon));
            }else{
                icons=icon_normal;
                text.setTextColor(ContextCompat.getColor(this,R.color.list_divider));
            }
            switch (position){ //实际项目中 别用中文，中文放到strings.xml
                case 0:
                    icon.setImageResource(icons[0]);
                    break;
                case 1:
                    icon.setImageResource(icons[1]);
                    break;
                case 2:
                    icon.setImageResource(icons[2]);
                    break;
            }
        }else{
            throw new NullPointerException("this view is null");
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    public void initTabLayout(){
        for(int i=0;i<title.length;i++){
            tabLayout.addTab(tabLayout.newTab().setCustomView(getTabView(i)),i==0);
            //第一项初始化被选中
        }
    }

    public View getTabView(int position){
        View view=getLayoutInflater().inflate(R.layout.item_tab,null);
        TextView text= (TextView) view.findViewById(R.id.tab_text);
        ImageView icon= (ImageView) view.findViewById(R.id.tab_icon);
        text.setText(title[position]);
        icon.setImageResource(icon_normal[position]);
        return view;
    }

    @Override
    public void initData() {
        fragmentList.add(NewsFragemnt.newInstance("news"));
        fragmentList.add(VideoFragment.newInstance("video"));
        fragmentList.add(PhotoFragment.newInstance("photo"));
        adapter=new FragmentAdapter(getSupportFragmentManager(),fragmentList);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean isShowCustomActionBar() {
        return false;
    }

}