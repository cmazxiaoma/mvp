package com.cmazxiaoma.mvp.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;


import com.cmazxiaoma.mvp.Utils.UtilsLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static com.cmazxiaoma.mvp.Config.TYPE_EMPTY;
import static com.cmazxiaoma.mvp.Config.TYPE_FOOT;


/**
 * Description: 沉梦昂志
 * Data：2017/4/8-21:36
 * Author: xiaoma
 * D代表数据源，VH代表视图管理者
 */

public abstract class  BaseRecyclerAdapter<D> extends RecyclerView.Adapter<BaseViewHolder>{
    private Context mcontext;
    private List<D> mData;
    public int mLayoutResId;
    private OnRecyclerViewItemClickListener<D> onItemClickListener;
    private OnRecyclerViewItemLongClickListener<D> onItemLongClickListener;
    private  List<Integer> ClickPositions;

    public void setOnItemLongClickListener(OnRecyclerViewItemLongClickListener<D> onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener<D> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    public BaseRecyclerAdapter(Context mcontext,List<D> mData, int mLayoutResId) {
        this.mcontext=mcontext;
        this.mData=mData==null?new LinkedList<D>():mData;
        if(mLayoutResId!=0){
            this.mLayoutResId=mLayoutResId;
        }else{
            throw new NullPointerException("请设置资源id");
        }
        ClickPositions=new ArrayList<>();
    }


    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseViewHolder holder= BaseViewHolder.get(mcontext,parent,mLayoutResId,viewType);
        return holder;
    }

    public void updateClickPostion( ){
        for(int i=0;i<ClickPositions.size();i++){
            ClickPositions.set(i,ClickPositions.get(i)+30);
        }
    }

    @Override
    public void onBindViewHolder(final BaseViewHolder  holder, final int position) {
        boolean textViewChangeColor=false;
//        UtilsLog.i("position="+position);
        if(position!=TYPE_EMPTY){
            final D itemData=mData.get(position);
            for(int i=0;i<ClickPositions.size();i++){
                if(ClickPositions.get(i)==position){
                    textViewChangeColor=true;
                }
            }
            bindTheData(holder,itemData,textViewChangeColor);
            holder.getConvertView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onItemClickListener!=null){
                        if(!isExistClickPostion(position)){
                            ClickPositions.add(position);
                        }
                        notifyItemChanged(position);
                        onItemClickListener.onItemClick(holder.getConvertView(),holder.getAdapterPosition(),itemData);
                    }
                }
            });

            holder.getConvertView().setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if(onItemLongClickListener!=null){
                        onItemLongClickListener.onItemLongClick(holder.getConvertView(),holder.getAdapterPosition(),itemData);
                    }
                    return false;
                }
            });
        }else{
            bindTheData(holder,null,false);
        }
    }

    public boolean isExistClickPostion(int postion){
        boolean flag=false;
        for(int i=0;i<ClickPositions.size();i++){
            if(ClickPositions.get(i)==postion){
                flag=true;
                break;
            }
        }
        return flag;
    }
    @Override
    public int getItemCount() {
        return mData.size();
    }

    public abstract void bindTheData(BaseViewHolder  holder,D itemData,boolean textViewChangeColor);

    public interface OnRecyclerViewItemClickListener<D>{
        void onItemClick(View view, int position, D itemData);
    }

    public interface OnRecyclerViewItemLongClickListener<D>{
       void onItemLongClick(View view, int position, D itemData);
    }
}

