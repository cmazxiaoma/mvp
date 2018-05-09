package com.cmazxiaoma.mvp.base;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cmazxiaoma.mvp.Utils.MyPicasso;

import static com.cmazxiaoma.mvp.Config.TYPE_ITEM_IMAGE_1;
import static com.cmazxiaoma.mvp.Config.TYPE_ITEM_IMAGE_2;
import static com.cmazxiaoma.mvp.Config.TYPE_ITEM_IMAGE_3;

/**
 * Description: 沉梦昂志
 * Data：2017/4/9-11:20
 * Author: xiaoma
 */

public class BaseViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> mViews;
    private View mConvertView;
    private Context mContext;
    private int mViewType;

    public BaseViewHolder(Context context, View itemView, ViewGroup parent,int viewType) {
        super(itemView);
        mContext=context;
        mConvertView=itemView;
        mViews=new SparseArray<>();
        mViewType=viewType;
    }

    public static BaseViewHolder get(Context mcontext, ViewGroup parent, int layoutId,int viewType){
        View view= LayoutInflater.from(mcontext).inflate(layoutId,null);
        view.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        BaseViewHolder holder=new BaseViewHolder(mcontext,view,parent,viewType);
        return holder;
    }


    public <T extends View> T findViewById(int viewId){
        View view=mViews.get(viewId);
        if(view==null){
            view=mConvertView.findViewById(viewId);
            mViews.put(viewId,view);
        }
        return (T)view;
    }

    public BaseViewHolder setText(int viewId, CharSequence c,String colorId){
        TextView view=findViewById(viewId);
        view.setTextColor(Color.parseColor(colorId));
        view.setText(c);
        return this;
    }

    public BaseViewHolder setText(int viewId, CharSequence c){
        TextView view=findViewById(viewId);
        view.setText(c);
        return this;
    }



    public BaseViewHolder setImage(int viewId,String url){
        ImageView imageView=findViewById(viewId);
        switch (mViewType){
            case TYPE_ITEM_IMAGE_1:
                MyPicasso.loadDefaultImg(mContext,url,imageView);
                break;
            case TYPE_ITEM_IMAGE_2:
                MyPicasso.loadImageHolder(mContext,url,400,300,imageView);
                break;
            case TYPE_ITEM_IMAGE_3:
                MyPicasso.loadImageHolder(mContext,url,400,300,imageView);
                break;
            default:
                MyPicasso.loadImageHolder(mContext,url,367,462,imageView);
                break;
        }
        return this;
    }

    public BaseViewHolder setImage(int viewId,String url,int width,int height){
        ImageView imageView=findViewById(viewId);
        MyPicasso.loadImageHolder(mContext,url,width,height,imageView);
        return this;
    }


    public View getConvertView(){
        return mConvertView;
    }

    public int getViewType(){
        return mViewType;
    }
    /**
     * 点击事件
     */
    public BaseViewHolder setOnClickListener(int viewId1,int viewId2,int viewId3, View.OnClickListener listener){
        View view1=findViewById(viewId1);
        View view2=findViewById(viewId2);
        View view3=findViewById(viewId3);
        view1.setOnClickListener(listener);
        view2.setOnClickListener(listener);
        view3.setOnClickListener(listener);
        return this;
    }
}
