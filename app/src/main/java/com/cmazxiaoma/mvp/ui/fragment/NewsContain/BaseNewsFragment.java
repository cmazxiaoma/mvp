package com.cmazxiaoma.mvp.ui.fragment.NewsContain;

import android.app.Application;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;

import com.cmazxiaoma.mvp.R;
import com.cmazxiaoma.mvp.Utils.MyAnim;
import com.cmazxiaoma.mvp.Utils.UtilsLog;
import com.cmazxiaoma.mvp.application.BaseApplication;
import com.cmazxiaoma.mvp.base.BaseMvpFragment;
import com.cmazxiaoma.mvp.base.BaseRecyclerAdapter;
import com.cmazxiaoma.mvp.bean.NewResult;
import com.cmazxiaoma.mvp.contract.NewsContract;
import com.cmazxiaoma.mvp.presenter.NewsPresenter;
import com.cmazxiaoma.mvp.ui.NewDetailActivity;
import com.cmazxiaoma.mvp.ui.adapter.NewsAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.cmazxiaoma.mvp.Config.TYPE_EMPTY;

/**
 * Description: 沉梦昂志
 * Data：2017/5/13-19:32
 * Author: xiaoma
 */

public abstract class BaseNewsFragment extends BaseMvpFragment<NewsContract.View,NewsPresenter> implements NewsContract.View{
    private NewsPresenter presenter;
    private RecyclerView recyclerView;
    private NewsAdapter adapter;
    private List<NewResult.ResultBean.DataBean> mData;
    private SwipeRefreshLayout refreshLayout;
    private int flag=-1;//1为初始化数据，2为下拉刷新，3为上拉加载
    private LinearLayoutManager ll;
    private int lastVisiableItemPosition;
    private LinearLayout newsNotice;
    private TranslateAnimation mShowAction,mHiddenAction;
    private Map<String,Integer> mView;

    @Override
    public int getLayoutId() {
        mView=getViewResId();
        return mView.get("Layout");
    }
    @Override
    public void initData() {
        mData=new ArrayList<>();
        flag=1;
        presenter.getNewsInfo(getDataType());
    }
    public abstract String getDataType();

    @Override
    public void initView(View view)
    {
        recyclerView= (RecyclerView) view.findViewById(mView.get("RecyclerView"));
        refreshLayout= (SwipeRefreshLayout) view.findViewById(mView.get("SwipeRefreshLayout"));
        refreshLayout.setRefreshing(true);
        newsNotice= (LinearLayout) view.findViewById(mView.get("NewsNotice"));
        newsNotice.setVisibility(View.GONE);
        refreshLayout.setColorSchemeColors(ContextCompat.getColor(BaseApplication.getContext(),R.color.lightsalmon),ContextCompat.getColor(BaseApplication.getContext(),R.color.lightpink),ContextCompat.getColor(BaseApplication.getContext(),R.color.sandybrown),ContextCompat.getColor(BaseApplication.getContext(),R.color.salmon));
        recyclerView.setHasFixedSize(true);//避免资源浪费
        ll=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(ll);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                try {
                    if(newState == RecyclerView.SCROLL_STATE_IDLE && lastVisiableItemPosition + 1 == adapter.getItemCount()){
                        loadMoreData();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisiableItemPosition = ll.findLastVisibleItemPosition();
            }
        });

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        flag = 2;
                        presenter.getNewsInfo(getDataType());
                    }
                },2000);
            }
        });
    }

    private void loadMoreData() {
        flag = 3;
        presenter.getNewsInfo(getDataType());
    }

    public abstract Map<String,Integer> getViewResId();

    @Override
    public NewsPresenter createPresenter() {
        presenter=new NewsPresenter();
        return presenter;
    }

    @Override
    public void onSuccessed(NewResult newResult) {
        switch (flag){
            case 1:
                if (newResult != null) {
                    if (newResult.getResult() != null) {
                        UtilsLog.i("newResult=" + newResult);
                        List<NewResult.ResultBean.DataBean> datas = newResult.getResult().getData();
                        if (datas != null && !datas.isEmpty()) {
                            for(NewResult.ResultBean.DataBean data : datas){
                                mData.add(data);
                            }
                        }
                        adapter=new NewsAdapter(getActivity(), new NewsAdapter.onHeaderView() {
                            @Override
                            public void onHeaderViewClick() {
                                recyclerView.scrollToPosition(0);
                                refreshLayout.setRefreshing(true);
                                refreshLayout.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        flag = 2;
                                        presenter.getNewsInfo(getDataType());
                                    }
                                },2000);
                            }
                        },mData);
                        recyclerView.setAdapter(adapter);
                        adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnRecyclerViewItemClickListener<NewResult.ResultBean.DataBean>() {
                            @Override
                            public void onItemClick(View view, int position, NewResult.ResultBean.DataBean itemData) {
                                Bundle bundle=new Bundle();
                                bundle.putString("url", itemData.getUrl());
                                startActivity(NewDetailActivity.class, bundle);
                            }
                        });
                    } else {
                        refreshLayout.setRefreshing(false);
                    }
                } else {
                    refreshLayout.setRefreshing(false);
                }
                break;
            case 2:
                if (newResult != null) {
                    if (newResult.getResult() != null) {
                        List<NewResult.ResultBean.DataBean> datas = newResult.getResult().getData();
                        if (datas != null && !datas.isEmpty()) {
                            for(NewResult.ResultBean.DataBean data : datas){
                                mData.add(0, data);
                            }
                            refreshLayout.setRefreshing(false);
                            adapter.addHeaderView(30);
                            adapter.updateClickPostion();
                            adapter.notifyItemRangeChanged(0, mData.size());
                            recyclerView.scrollToPosition(0);
                            mShowAction= MyAnim.showAction();
                            newsNotice.startAnimation(mShowAction);
                            newsNotice.setVisibility(View.VISIBLE);
                            mShowAction.setAnimationListener(new Animation.AnimationListener() {
                                @Override
                                public void onAnimationStart(Animation animation) {}

                                @Override
                                public void onAnimationEnd(Animation animation) {
                                    SystemClock.sleep(500);
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
                                public void onAnimationRepeat(Animation animation) {}
                            });
                        }
                    } else {
                        refreshLayout.setRefreshing(false);
                    }
                } else {
                    refreshLayout.setRefreshing(false);
                }
                break;
            case 3:
                if (newResult != null) {
                    if (newResult.getResult() != null) {
                        List<NewResult.ResultBean.DataBean> datas = newResult.getResult().getData();
                        if (datas != null && !datas.isEmpty()) {
                            for(NewResult.ResultBean.DataBean data : datas){
                                mData.add(mData.size(), data);
                            }
                            adapter.notifyItemInserted(mData.size());
                        }
                    }
                }
                break;
        }
    }

    @Override
    public void onFailed(Throwable e) {
        UtilsLog.i("e="+e);
    }

//    @Override
//    public void onPause() {
//        super.onPause();
//        UtilsLog.i("onPause");
//    }
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        UtilsLog.i("onStop");
//    }
//    @Override
//    public void onResume() {
//        super.onResume();
//        UtilsLog.i("onResume");
//    }
}