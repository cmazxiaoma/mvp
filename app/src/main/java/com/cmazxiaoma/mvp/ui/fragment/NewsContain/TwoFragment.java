package com.cmazxiaoma.mvp.ui.fragment.NewsContain;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.cmazxiaoma.mvp.R;
import com.cmazxiaoma.mvp.Utils.UtilsLog;
import com.cmazxiaoma.mvp.base.BaseMvpFragment;
import com.cmazxiaoma.mvp.base.BaseOrdinaryFragment;
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

/**
 * Description: 沉梦昂志
 * Data：2017/5/11-18:52
 * Author: xiaoma
 * 娱乐
 */

public class TwoFragment extends BaseNewsFragment{

    @Override
    public String getDataType() {
        return "yule";
    }

    @Override
    public Map<String, Integer> getViewResId() {
        Map<String,Integer> map=new HashMap<>();
        map.put("Layout",R.layout.f_news_two);
        map.put("RecyclerView",R.id.f_news_two_recyclerView);
        map.put("SwipeRefreshLayout",R.id.f_news_two_swipefreshlayout);
        map.put("NewsNotice",R.id.f_news_two_updateNotice);
        return map;
    }
}
