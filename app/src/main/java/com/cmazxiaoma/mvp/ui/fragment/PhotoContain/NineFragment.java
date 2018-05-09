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

public class NineFragment extends BasePhotoFragment{
    @Override
    public boolean isRecommend() {
        return false;
    }
    @Override
    public Map<String, Integer> getViewResdId() {
        Map<String,Integer> map=new HashMap<>();
        map.put("Layout", R.layout.f_photo_nine);
        map.put("RecyclerView",R.id.f_photo_nine_recyclerView);
        map.put("SwipeRefreshLayout",R.id.f_photo_nine_swipefreshlayout);
        map.put("NewsNotice",R.id.f_photo_nine_updateNotice);
        return map;
    }

    @Override
    public String getDataType() {
        return girls[9];
    }

}
