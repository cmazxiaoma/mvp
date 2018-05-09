package com.cmazxiaoma.mvp.bean;

import com.flyco.tablayout.listener.CustomTabEntity;

/**
 * Description: 沉梦昂志
 * Data：2017/5/11-17:36
 * Author: xiaoma
 */

public class TabEntity implements CustomTabEntity{
    public String tabTitle;
    public int icon_normal;
    public int icon_selected;

    public TabEntity(String title, int icon_normal, int icon_selected) {
        this.tabTitle = title;
        this.icon_normal = icon_normal;
        this.icon_selected = icon_selected;
    }

    @Override
    public String getTabTitle() {
        return tabTitle;
    }

    @Override
    public int getTabSelectedIcon() {
        return icon_selected;
    }

    @Override
    public int getTabUnselectedIcon() {
        return icon_normal;
    }
}
