package com.cmazxiaoma.mvp.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: 沉梦昂志
 * Data：2017/5/13-21:23
 * Author: xiaoma
 */

public class FragmentInnerAdapter extends FragmentPagerAdapter{
    private ArrayList<Fragment> mlist;
    private String[] mTitle;

    public FragmentInnerAdapter(FragmentManager fm, String[] mtitle,ArrayList<Fragment> mlist) {
        super(fm);
        this.mlist=mlist;
        this.mTitle=mtitle;
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
    public CharSequence getPageTitle(int position) {
        return mTitle[position];
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
    }
}
