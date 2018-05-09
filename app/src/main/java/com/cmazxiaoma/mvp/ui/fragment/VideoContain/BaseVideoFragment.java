package com.cmazxiaoma.mvp.ui.fragment.VideoContain;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.cmazxiaoma.mvp.Config;
import com.cmazxiaoma.mvp.Utils.MyAnim;
import com.cmazxiaoma.mvp.Utils.UtilsLog;
import com.cmazxiaoma.mvp.base.BaseMvpFragment;
import com.cmazxiaoma.mvp.bean.VideoResult;
import com.cmazxiaoma.mvp.contract.VideoContract;
import com.cmazxiaoma.mvp.presenter.VideoPresenter;
import com.cmazxiaoma.mvp.ui.SecondActivity;
import com.cmazxiaoma.mvp.ui.adapter.VideoAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

/**
 * Description: 沉梦昂志
 * Data：2017/7/8-11:14
 * Author: xiaoma
 */

public abstract class BaseVideoFragment extends BaseMvpFragment<VideoContract.View,VideoPresenter>implements VideoContract.View{
   private VideoPresenter presenter;
    private Map<String,Integer> views;
    private List<VideoResult.ShowapiResBodyBean.PagebeanBean.ContentlistBean> mData;
    private int page=1;
    private LinearLayout newNotice;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ListView listView;
    private int flag=-1;//1为初始化数据，2为下拉刷新，3为上拉加载
    private VideoAdapter adapter;
    private SensorManager sensorManager;
    private JCVideoPlayer.JCAutoFullscreenListener sensorEventListener;
    private TranslateAnimation mShowAction,mHiddenAction;

    private BackHandlerInterface backHandlerInterface;

    public interface BackHandlerInterface{
         void setSelectedFragment(BaseVideoFragment fragment);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //回调函数赋值
        if(!(getActivity() instanceof  BackHandlerInterface)){
            throw  new ClassCastException("Hosting activity must implement BackHandlerInterface");
        }else{
            backHandlerInterface=(BackHandlerInterface)getActivity();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        //将自己的实例传出去
        backHandlerInterface.setSelectedFragment(this);
    }

    @Override
    public VideoPresenter createPresenter() {
        presenter=new VideoPresenter();
        return presenter;
    }

    @Override
    public int getLayoutId() {
        views=getViewsResId();
        return views.get("Layout");
    }

    public abstract  Map<String,Integer> getViewsResId();


    @Override
    public void initData() {
        mData=new ArrayList<>();
        flag=1;
        presenter.getVideoInfo(Config.SHOWAPI_BAISI_TYPE[3],""+page);
    }

    @Override
    public void initView(View view) {
        newNotice= (LinearLayout) view.findViewById(views.get("NewNotice"));
        swipeRefreshLayout= (SwipeRefreshLayout) view.findViewById(views.get("SwipeRefreshLayout"));
        listView= (ListView) view.findViewById(views.get("ListView"));
        newNotice.setVisibility(View.GONE);
        swipeRefreshLayout.setRefreshing(true);
        sensorManager= (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        sensorEventListener=new JCVideoPlayer.JCAutoFullscreenListener();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        flag = 2;
                        page++;
                        presenter.getVideoInfo(Config.SHOWAPI_BAISI_TYPE[3],""+page);
                    }
                },2000);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        Sensor accelerometerSensor=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(sensorEventListener,accelerometerSensor,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(sensorEventListener);
        JCVideoPlayer.releaseAllVideos();//释放所有的视频资源
    }

    @Override
    public void onSuccessed(VideoResult result) {
        switch (flag){
            case 1:
                for(VideoResult.ShowapiResBodyBean.PagebeanBean.ContentlistBean resultsBean:result.getShowapi_res_body().getPagebean().getContentlist()){
                    mData.add(resultsBean);
                }
                adapter=new VideoAdapter(getActivity(), mData);
                listView.setAdapter(adapter);
                adapter.setBackButtonClickListener(new VideoAdapter.OnVideoPlayBackButtonClickListener() {
                    @Override
                    public void onClick() {
                        UtilsLog.i("click backButton");
                        ActionBar actionBar = ((SecondActivity)getActivity()).getSupportActionBar();
                        if (actionBar != null) {
                            if (actionBar.isShowing()) {
                                actionBar.hide();
                            }
                        }
                    }
                });
                swipeRefreshLayout.setRefreshing(false);
                break;
            case 2:
                for(VideoResult.ShowapiResBodyBean.PagebeanBean.ContentlistBean resultsBean:result.getShowapi_res_body().getPagebean().getContentlist()){
                    mData.add(0,resultsBean);
                }
                swipeRefreshLayout.setRefreshing(false);
                adapter.notifyDataSetChanged();
                mShowAction= MyAnim.showAction();
                newNotice.startAnimation(mShowAction);
                newNotice.setVisibility(View.VISIBLE);
                mShowAction.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }
                    @Override
                    public void onAnimationEnd(Animation animation) {
                        SystemClock.sleep(100);
                        mHiddenAction =MyAnim.hideAction();
                        newNotice.startAnimation(mHiddenAction);
                        mHiddenAction.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {
                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                                newNotice.setVisibility(View.GONE);
                            }
                            @Override
                            public void onAnimationRepeat(Animation animation) {
                            }
                        });
                    }
                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }
                });
                break;
            case 3:
                break;
        }
    }

    @Override
    public void onFailed(Throwable e) {
    }

    public boolean onBackPressed(){
        if(JCVideoPlayer.backPress()){
            return true;
        }
        return  false;
    }
}
