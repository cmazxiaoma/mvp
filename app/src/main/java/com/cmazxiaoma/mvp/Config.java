package com.cmazxiaoma.mvp;

import android.os.Environment;

/**
 * Description: 沉梦昂志
 * Data：2017/5/11-22:04
 * Author: xiaoma
 */

public class Config {
    public static final String NEWS_APPKEY="bea539e2326d79cc08e9b0c8223e9978";
    public static final String FILE_PATH = Environment.getExternalStorageDirectory()+"/com.cmazxiaoma.mvp/image";
    public static final int TYPE_ITEM=0;
    public static final int TYPE_ITEM_IMAGE_3=3;
    public static final int TYPE_ITEM_IMAGE_2=2;
    public static final int TYPE_ITEM_IMAGE_1=1;
    public static final int TYPE_HEADER=4;
    public static final int TYPE_FOOT=-1;
    public static final int TYPE_EMPTY=Integer.MAX_VALUE;
    public static final String[] girls=new String[]{"推荐","清纯","萝莉","古典","素颜","校花","制服","诱惑","丝袜","模特","长腿"};
    public static final String BUGLY_APPID="82ab2ad137";
    public static final String[] videos=new String[]{"推荐","测试2","测试3"};
    public static final String SHOWAPI_SECRET="77fb57dbddd84ea9a4c1116b23cd62c9";
    public static final String SHOWAPI_APPID="41904";
    //10是图片,29是段子,31是声音,41是视频
    public static final String[] SHOWAPI_BAISI_TYPE=new String[]{"10","29","31","41"};
}
