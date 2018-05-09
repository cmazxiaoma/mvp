package com.cmazxiaoma.mvp.ui;

import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ImageView;

import com.cmazxiaoma.mvp.R;
import com.cmazxiaoma.mvp.Utils.Common;
import com.cmazxiaoma.mvp.Utils.MyPicasso;
import com.cmazxiaoma.mvp.Utils.UtilsLog;
import com.cmazxiaoma.mvp.base.BaseOrdinaryActivity;
import com.cmazxiaoma.mvp.bean.ImageEvent;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.github.chrisbanes.photoview.PhotoView;
import com.github.chrisbanes.photoview.PhotoViewAttacher;
import com.jakewharton.rxbinding2.view.RxView;
import com.squareup.picasso.Callback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Description: 沉梦昂志
 * Data：2017/5/17-13:16
 * Author: xiaoma
 */

public class PhotoDetailActivity extends BaseOrdinaryActivity {
    @BindView(R.id.act_photodetail_share)
    Button share;
    @BindView(R.id.act_photodetail_num)
    Button show;
    private List<String> imageUrls;
    private int currentPosition;//这是当前项索引
    private PhotoViewPager viewPager;
    private Context mContext;
    private PhotoViewAttacher photoViewAttacher;
    private PagerAdapter adapter;
    private SparseArray<Bitmap> view;
    private Bitmap currentBitmap;
    private FloatingActionsMenu menu;
    private FloatingActionButton download,wallpaper;
    private WallpaperManager wallpaperManager;
    @Override
    public boolean isShowCustomActionBar() {
        return false;
    }

    @Override
    public void initContentView(Bundle savedInstanceState) {
        viewPager = (PhotoViewPager) findViewById(R.id.act_photodetail_viewpager);
        menu= (FloatingActionsMenu) findViewById(R.id.act_photodetail_operate);
        download= (FloatingActionButton) findViewById(R.id.act_photodetail_download);
        wallpaper= (FloatingActionButton) findViewById(R.id.act_photodetail_wallpaper);
        wallpaperManager=WallpaperManager.getInstance(this);

        RxView.clicks(download)
                .throttleFirst(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        new Thread(){
                            @Override
                            public void run() {
                                final boolean reuslt=Common.saveImg2Local(currentBitmap);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Common.Toast(reuslt?"保存到本地成功":"保存到本地失败");
                                    }
                                });
                            }
                        }.start();
                    }
                });

        RxView.clicks(wallpaper)
                .throttleFirst(1,TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        new Thread(){
                            @Override
                            public void run() {
                                try {
                                    wallpaperManager.setBitmap(currentBitmap);
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Common.Toast("设置壁纸成功");
                                        }
                                    });
                                }catch (IOException e){
                                    e.printStackTrace();
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Common.Toast("设置壁纸失败");
                                        }
                                    });
                                }
                            }
                        }.start();
                    }
                });
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_photodetail;
    }

    @Override
    public void initData() {

    }

    //沉浸式
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void receiveImg(ImageEvent event) {
        view=new SparseArray<>();
        imageUrls = event.getImageUrls();
        currentPosition = event.getCurrentPosition();
        show.setText(currentPosition + 1 + "/" + imageUrls.size());
        adapter = new PagerAdapter() {
            @Override
            public int getCount() {
                return imageUrls.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, final int position) {
                final PhotoView photoView = new PhotoView(mContext);
                photoView.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                photoView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                MyPicasso.loadDefaultImg(mContext, imageUrls.get(position), photoView, new Callback() {
                    @Override
                    public void onSuccess() {
                        UtilsLog.i("position="+position);
                        BitmapDrawable bitmapDrawable= (BitmapDrawable) photoView.getDrawable();
                        Bitmap bitmap=bitmapDrawable.getBitmap();
                        view.put(position,bitmap);
                        if(position==currentPosition) {
                            //一开始进入照片详情页时，当前的bitmap
                            currentBitmap = view.get(currentPosition);
                            UtilsLog.i("currentBitmap="+currentBitmap);
                        }
                        photoViewAttacher = new PhotoViewAttacher(photoView);
                        photoViewAttacher.update();
                    }
                    @Override
                    public void onError() {
                    }
                });
                container.addView(photoView);
                return photoView;
            }
            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }
        };
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(currentPosition);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {
                show.setText(position + 1 + "/" + imageUrls.size());
                currentBitmap=null;
                currentBitmap=view.get(position);
                UtilsLog.i("position="+position+",currentBitmap="+currentBitmap.toString());
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    @Subscribe
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        EventBus.getDefault().removeStickyEvent(ImageEvent.class);
    }
}
