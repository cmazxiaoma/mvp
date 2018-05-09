package com.cmazxiaoma.mvp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.cmazxiaoma.mvp.R;
import com.cmazxiaoma.mvp.Utils.MyAnim;
import com.cmazxiaoma.mvp.Utils.UtilsLog;
import com.cmazxiaoma.mvp.base.BaseOrdinaryActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Description: 沉梦昂志
 * Data：2017/5/13-15:32
 * Author: xiaoma
 */

public class NewDetailActivity extends BaseOrdinaryActivity{
    private WebView mWebView;
    private ProgressBar mProgressBar;
    private String url;


    @Override
    public boolean isShowCustomActionBar() {
        return true;
    }

    @Override
    public void initContentView(Bundle savedInstanceState) {
        mProgressBar= (ProgressBar) findViewById(R.id.act_newdetail_progressbar);
        mWebView= (WebView) findViewById(R.id.act_newdetail_webview);
        WebSettings settings=mWebView.getSettings();
        settings.setSupportZoom(true);//支持缩放
        settings.setBuiltInZoomControls(true);   //缩放控制
        TranslateAnimation showAction=MyAnim.showAction();
        mProgressBar.startAnimation(showAction);
        mProgressBar.setVisibility(View.VISIBLE);
        mWebView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view,newProgress);
                if(newProgress==100){
                    TranslateAnimation hideAction= MyAnim.hideAction();
                    mProgressBar.startAnimation(hideAction);
                    hideAction.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            mProgressBar.setVisibility(View.GONE);
                        }
                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });

                }
            }
        });
        mWebView.loadUrl(url);
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(url);
                return true;
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_newdetail;
    }

    @Override
    public void initData() {
        Intent intent=getIntent();
        url=intent.getStringExtra("url");
        UtilsLog.i("url="+url);
    }

}
