package com.cmazxiaoma.mvp.ui.fragment.PhotoContain;

import com.cmazxiaoma.mvp.R;

import java.util.HashMap;
import java.util.Map;

import static com.cmazxiaoma.mvp.Config.girls;

/**
 * Description: 沉梦昂志
 * Data：2017/5/16-8:47
 * Author: xiaoma
 */

public class SevenFragment extends BasePhotoFragment{
    @Override
    public boolean isRecommend() {
        return false;
    }

    @Override
    public Map<String, Integer> getViewResdId() {
        Map<String,Integer> map=new HashMap<>();
        map.put("Layout", R.layout.f_photo_seven);
        map.put("RecyclerView",R.id.f_photo_seven_recyclerView);
        map.put("SwipeRefreshLayout",R.id.f_photo_seven_swipefreshlayout);
        map.put("NewsNotice",R.id.f_photo_seven_updateNotice);
        return map;
    }

    @Override
    public String getDataType() {
        return girls[7];
    }

}
