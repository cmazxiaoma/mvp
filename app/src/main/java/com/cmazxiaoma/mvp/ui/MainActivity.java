package com.cmazxiaoma.mvp.ui;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.cmazxiaoma.mvp.R;
import com.cmazxiaoma.mvp.Utils.UtilsLog;
import com.cmazxiaoma.mvp.base.BaseMvpActivity;
import com.cmazxiaoma.mvp.bean.GankResult;
import com.cmazxiaoma.mvp.contract.TestContract;
import com.cmazxiaoma.mvp.presenter.TestPresenter;

import junit.framework.Test;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseMvpActivity<TestContract.View,TestPresenter> implements TestContract.View{
    @BindView(R.id.test)
    Button test;
    private TestPresenter presenter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    public Dialog showDialog() {
        Dialog dialog= ProgressDialog.show(this,null,"wait");
        dialog.show();
        return dialog;
    }

    @Override
    public void Success(GankResult result) {
        UtilsLog.i("result="+result);
    }

    @Override
    public void Failed(Throwable e) {
        UtilsLog.i("错误="+e);
    }

    @OnClick(R.id.test)
    public void onViewClicked() {
        presenter.getInfo("1");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public TestPresenter createPresenter() {
        presenter=new TestPresenter();
        return presenter;
    }
}