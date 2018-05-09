package com.cmazxiaoma.mvp.ui.fragment.PhotoContain;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;

import com.cmazxiaoma.mvp.R;
import com.cmazxiaoma.mvp.Utils.Common;
import com.cmazxiaoma.mvp.Utils.MyAnim;
import com.cmazxiaoma.mvp.Utils.UtilsLog;
import com.cmazxiaoma.mvp.application.BaseApplication;
import com.cmazxiaoma.mvp.base.BaseMvpFragment;
import com.cmazxiaoma.mvp.base.BaseRecyclerAdapter;
import com.cmazxiaoma.mvp.bean.GankPhotoResult;
import com.cmazxiaoma.mvp.bean.ImageEvent;
import com.cmazxiaoma.mvp.bean.NewResult;
import com.cmazxiaoma.mvp.bean.PhotoResult;
import com.cmazxiaoma.mvp.contract.PhotoContract;
import com.cmazxiaoma.mvp.presenter.PhotoPresenter;
import com.cmazxiaoma.mvp.ui.NewDetailActivity;
import com.cmazxiaoma.mvp.ui.PhotoDetailActivity;
import com.cmazxiaoma.mvp.ui.adapter.GankPhotoAdapter;
import com.cmazxiaoma.mvp.ui.adapter.PhotoAdapter;
import com.cmazxiaoma.mvp.ui.adapter.SpacesItemDecoration;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Description: 沉梦昂志
 * Data：2017/5/16-8:47
 * Author: xiaoma
 */

public abstract class BasePhotoFragment extends BaseMvpFragment<PhotoContract.View,PhotoPresenter> implements PhotoContract.View{
    private PhotoPresenter presenter;
    private TranslateAnimation mShowAction,mHiddenAction;
    private Map<String,Integer> mView;
    private int lastVisiableItemPosition;
    private LinearLayout newsNotice;
    private SwipeRefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    private List<PhotoResult.ImgsBean> mData;
    private List<GankPhotoResult.ResultsBean> gankData;
    private WrapContentStaggeredGridLayoutManager sgl;
    private List<String> imageUrls;
    private PhotoAdapter adapter;
    private GankPhotoAdapter gankPhotoAdapter;
    private int flag=-1;
    private int startIndex=0;
    private int page=1;
    @Override
    public PhotoPresenter createPresenter() {
        presenter=new PhotoPresenter();
        return presenter;
    }
    public abstract boolean isRecommend();
    @Override
    public int getLayoutId() {
        mView=getViewResdId();
        return mView.get("Layout");
    }
    public abstract Map<String,Integer>  getViewResdId();

    @Override
    public void initData() {
        mData=new ArrayList<>();
        gankData=new ArrayList<>();
        imageUrls=new ArrayList<>();
        flag=1;
        if(isRecommend()){
            presenter.getGankPhotoInfo("10",page+"");
        }else{
            presenter.getPhotoInfo(getDataType(),startIndex+"","10");
        }
    }
    public abstract String getDataType();

    @Override
    public void initView(View view) {
        recyclerView= (RecyclerView) view.findViewById(mView.get("RecyclerView"));
        refreshLayout= (SwipeRefreshLayout) view.findViewById(mView.get("SwipeRefreshLayout"));
        refreshLayout.setRefreshing(true);
        newsNotice= (LinearLayout) view.findViewById(mView.get("NewsNotice"));
        newsNotice.setVisibility(View.GONE);   refreshLayout.setColorSchemeColors(ContextCompat.getColor(BaseApplication.getContext(), R.color.lightsalmon),ContextCompat.getColor(BaseApplication.getContext(),R.color.lightpink),ContextCompat.getColor(BaseApplication.getContext(),R.color.sandybrown),ContextCompat.getColor(BaseApplication.getContext(),R.color.salmon));
        recyclerView.setHasFixedSize(true);//避免资源浪费
        sgl=new WrapContentStaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);//瀑布流
        sgl.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        recyclerView.setLayoutManager(sgl);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if(isRecommend()){
                    if(newState==RecyclerView.SCROLL_STATE_IDLE&&lastVisiableItemPosition+1==gankPhotoAdapter.getItemCount()){
                        loadMoreData();
                    }
                }else{
                    if(newState==RecyclerView.SCROLL_STATE_IDLE&&lastVisiableItemPosition+1==adapter.getItemCount()){
                        loadMoreData();
                    }
                }
            }
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int[] positions=sgl.findLastVisibleItemPositions(new int[sgl.getSpanCount()]);
                lastVisiableItemPosition= Common.getMax(positions);
//                UtilsLog.i("lastVisiableItemPosition="+lastVisiableItemPosition);
            }
        });

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        flag = 2;
                        if(isRecommend()){
                            page++;
                            presenter.getGankPhotoInfo("10",page+"");
                        }else{
                            startIndex+=10;
                            presenter.getPhotoInfo(getDataType(),startIndex+"","10");
                        }
                    }
                },2000);
            }
        });
    }
    public void loadMoreData(){
        flag=3;
        if(isRecommend()){
            page++;
            presenter.getGankPhotoInfo("10",page+"");
        }else{
            startIndex+=10;
            presenter.getPhotoInfo(getDataType(),startIndex+"","10");
        }
    }

    @Override
    public void onSuccessed(Object result) {
        if(result instanceof PhotoResult){
            PhotoResult newResult=(PhotoResult)result;
            switch (flag){
                case 1:
                    for(PhotoResult.ImgsBean imgsBean:newResult.getImgs()){
                        if(!TextUtils.isEmpty(imgsBean.getImageUrl())){
                            mData.add(imgsBean);
                            imageUrls.add(imgsBean.getImageUrl());
                        }
                    }
                    adapter=new PhotoAdapter(getActivity(),mData);
                    recyclerView.setAdapter(adapter);
                    recyclerView.addItemDecoration(new SpacesItemDecoration(5));
                    refreshLayout.setRefreshing(false);
                    adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnRecyclerViewItemClickListener<PhotoResult.ImgsBean>() {
                        @Override
                        public void onItemClick(View view, int position, PhotoResult.ImgsBean itemData) {
                            EventBus.getDefault().postSticky(new ImageEvent(position,imageUrls));
                            UtilsLog.i("跳转了，position="+position+",width="+itemData.getImageWidth()+",height="+itemData.getImageHeight());
                            startActiviity(PhotoDetailActivity.class);
                        }
                    });
                    break;
                case 2:
                    for(PhotoResult.ImgsBean imgsBean:newResult.getImgs()){
                        if(!TextUtils.isEmpty(imgsBean.getImageUrl())){
                            mData.add(0,imgsBean);
                            imageUrls.add(0,imgsBean.getImageUrl());
                        }
                    }
                    refreshLayout.setRefreshing(false);
                    adapter.notifyItemRangeChanged(0,mData.size());
                    recyclerView.scrollToPosition(0);
                    mShowAction= MyAnim.showAction();
                    newsNotice.startAnimation(mShowAction);
                    newsNotice.setVisibility(View.VISIBLE);
                    mShowAction.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                        }
                        @Override
                        public void onAnimationEnd(Animation animation) {
                            SystemClock.sleep(100);
                            mHiddenAction =MyAnim.hideAction();
                            newsNotice.startAnimation(mHiddenAction);
                            mHiddenAction.setAnimationListener(new Animation.AnimationListener() {
                                @Override
                                public void onAnimationStart(Animation animation) {
                                }

                                @Override
                                public void onAnimationEnd(Animation animation) {
                                    newsNotice.setVisibility(View.GONE);
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
                    for(PhotoResult.ImgsBean imgsBean:newResult.getImgs()){
                        if(!TextUtils.isEmpty(imgsBean.getImageUrl())){
                            mData.add(mData.size(),imgsBean);
                            imageUrls.add(imageUrls.size(),imgsBean.getImageUrl());
                        }
                    }
                    //notifyDataSetChanged后，并不能马上获取Adapter中的position，要等布局结束之后才能获取
                    //notifyItemInserted后，Layout不能马上获取新的position，因为布局还没更新(需要<16ms的时间刷新布局）
                    //所以只能获取旧的，但是Adapter中能获取最新的position
                    adapter.notifyItemInserted(mData.size());
                    break;
            }
        }else if(result instanceof GankPhotoResult){
            GankPhotoResult newResult= (GankPhotoResult) result;
            switch (flag){
                case 1:
                    for(GankPhotoResult.ResultsBean gankPhotoResult:newResult.getResults()){
                        if(!TextUtils.isEmpty(gankPhotoResult.getUrl())){
                            gankData.add(gankPhotoResult);
                            imageUrls.add(gankPhotoResult.getUrl());
                        }
                    }
                    gankPhotoAdapter=new GankPhotoAdapter(getActivity(),gankData);
                    recyclerView.setAdapter(gankPhotoAdapter);
                    recyclerView.addItemDecoration(new SpacesItemDecoration(5));
                    refreshLayout.setRefreshing(false);
                    gankPhotoAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnRecyclerViewItemClickListener<GankPhotoResult.ResultsBean>() {
                        @Override
                        public void onItemClick(View view, int position, GankPhotoResult.ResultsBean itemData) {
                            UtilsLog.i("你点击了的position="+position);
                            EventBus.getDefault().postSticky(new ImageEvent(position,imageUrls));
                            startActiviity(PhotoDetailActivity.class);
                        }
                    });
                    break;
                case 2:
                    for(GankPhotoResult.ResultsBean gankPhotoResult:newResult.getResults()){
                        if(!TextUtils.isEmpty(gankPhotoResult.getUrl())){
                            gankData.add(0,gankPhotoResult);
                            imageUrls.add(0,gankPhotoResult.getUrl());
                        }
                    }
                    refreshLayout.setRefreshing(false);
                    gankPhotoAdapter.notifyItemRangeChanged(0,gankData.size());
                    recyclerView.scrollToPosition(0);
                    mShowAction= MyAnim.showAction();
                    newsNotice.startAnimation(mShowAction);
                    newsNotice.setVisibility(View.VISIBLE);
                    mShowAction.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                        }
                        @Override
                        public void onAnimationEnd(Animation animation) {
                            SystemClock.sleep(100);
                            mHiddenAction =MyAnim.hideAction();
                            newsNotice.startAnimation(mHiddenAction);
                            mHiddenAction.setAnimationListener(new Animation.AnimationListener() {
                                @Override
                                public void onAnimationStart(Animation animation) {
                                }
                                @Override
                                public void onAnimationEnd(Animation animation) {
                                    newsNotice.setVisibility(View.GONE);
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
                    for(GankPhotoResult.ResultsBean gankPhotoResult:newResult.getResults()){
                        if(!TextUtils.isEmpty(gankPhotoResult.getUrl())){
                            gankData.add(gankData.size(),gankPhotoResult);
                            imageUrls.add(imageUrls.size(),gankPhotoResult.getUrl());
                        }
                    }
                    gankPhotoAdapter.notifyItemInserted(gankData.size());
                    break;
            }
        }
    }
    @Override
    public void onFailed(Throwable e) {
        UtilsLog.i("e="+e);
    }
}
