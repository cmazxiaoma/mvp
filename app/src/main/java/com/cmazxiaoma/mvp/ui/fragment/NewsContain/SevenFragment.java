package com.cmazxiaoma.mvp.ui.fragment.NewsContain;

import com.cmazxiaoma.mvp.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: 沉梦昂志
 * Data：2017/5/13-19:57
 * Author: xiaoma
 */

public class SevenFragment extends  BaseNewsFragment {
    @Override
    public String getDataType() {
        return "guonei";
    }
    @Override
    public Map<String, Integer> getViewResId() {
        Map<String,Integer> map=new HashMap<>();
        map.put("Layout",R.layout.f_news_seven);
        map.put("RecyclerView",R.id.f_news_seven_recyclerView);
        map.put("SwipeRefreshLayout",R.id.f_news_seven_swipefreshlayout);
        map.put("NewsNotice",R.id.f_news_seven_updateNotice);
        return map;
    }
}
