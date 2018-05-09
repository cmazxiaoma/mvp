package com.cmazxiaoma.mvp.ui.fragment.VideoContain;

import com.cmazxiaoma.mvp.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: 沉梦昂志
 * Data：2017/7/8-12:52
 * Author: xiaoma
 */

public class OneFragment extends  BaseVideoFragment{
    @Override
    public Map<String, Integer> getViewsResId() {
        Map<String,Integer> map=new HashMap<>();
        map.put("Layout", R.layout.f_video_one);
        map.put("SwipeRefreshLayout",R.id.f_video_one_swipefreshlayout);
        map.put("NewNotice",R.id.f_video_one_updateNotice);
        map.put("ListView",R.id.f_video_one_listView);
        return map;
    }
}
