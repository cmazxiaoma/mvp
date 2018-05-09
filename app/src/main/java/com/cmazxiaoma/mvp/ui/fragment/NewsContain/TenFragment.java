package com.cmazxiaoma.mvp.ui.fragment.NewsContain;

import com.cmazxiaoma.mvp.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: 沉梦昂志
 * Data：2017/5/13-19:58
 * Author: xiaoma
 * 财经
 */

public class TenFragment extends  BaseNewsFragment {
    @Override
    public String getDataType() {
        return "caijing";
    }
    @Override
    public Map<String, Integer> getViewResId() {
        Map<String,Integer> map=new HashMap<>();
        map.put("Layout",R.layout.f_news_ten);
        map.put("RecyclerView",R.id.f_news_ten_recyclerView);
        map.put("SwipeRefreshLayout",R.id.f_news_ten_swipefreshlayout);
        map.put("NewsNotice",R.id.f_news_ten_updateNotice);
        return map;
    }

}
