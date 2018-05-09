package com.cmazxiaoma.mvp.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * Description: 沉梦昂志
 * Data：2017/5/11-0:33
 * Author: xiaoma
 */

public class FragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> mlist;

    public FragmentAdapter(FragmentManager fm,List<Fragment> mlist) {
        super(fm);
        this.mlist=mlist;
    }

    @Override
    public Fragment getItem(int position) {
        return mlist.get(position);
    }

    @Override
    public int getCount() {
        return mlist.size();
    }


    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public void finishUpdate(ViewGroup container) {
        super.finishUpdate(container);
    }
}
