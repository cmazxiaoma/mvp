package com.cmazxiaoma.mvp.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.cmazxiaoma.mvp.Utils.UtilsLog;

import java.util.List;

import static com.cmazxiaoma.mvp.Config.TYPE_EMPTY;
import static com.cmazxiaoma.mvp.Config.TYPE_FOOT;
import static com.cmazxiaoma.mvp.Config.TYPE_HEADER;
import static com.cmazxiaoma.mvp.Config.TYPE_ITEM;

/**
 * Description: 沉梦昂志
 * Data：2017/4/9-15:02
 * Author: xiaoma
 */

public abstract class BaseMultiRecyclerAdapter<D> extends BaseRecyclerAdapter<D> implements MultiItemTypeSupport<D>{
    private List<D>mData;
    private Context mContext;
    public int mHeaderPostion=-1;

    public BaseMultiRecyclerAdapter(Context mcontext, List<D> mData) {
        super(mcontext, mData,-1);
        this.mContext=mcontext;
        if(mData==null){
            throw new IllegalArgumentException("mData mayday be null");
        }
        this.mData=mData;

    }

    @Override
    public int getItemViewType(int position) {
//        UtilsLog.i("positoion="+position);
        if(mHeaderPostion==-1){
            if(position==mData.size()){
                return getItemViewType(position,null);
            }else {
                return getItemViewType(position,mData.get(position));
            }
        }else{
            if(position<mHeaderPostion){
                return getItemViewType(position,mData.get(position));
            }else if(position==mHeaderPostion){
                return getItemViewType(position,null);
            }else{
                if(position==mData.size()+1){
                    return  getItemViewType(position,null);
                }else{
                    return getItemViewType(position,mData.get(position-1));
                }
            }
        }
    }

    public void addHeaderView(int mHeaderPostion){
        this.mHeaderPostion=mHeaderPostion;
    }
    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutId=getLayoutId(viewType);
        BaseViewHolder holder=BaseViewHolder.get(mContext,parent,layoutId,viewType);
        return holder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        if(holder!=null){
            if(mHeaderPostion==-1){
                if(getItemViewType(position)!=TYPE_FOOT){
                    super.onBindViewHolder(holder,position);
                }
            }else{
                if(getItemViewType(position)!=TYPE_FOOT){
                    if(getItemViewType(position)==TYPE_HEADER){
                        super.onBindViewHolder(holder,TYPE_EMPTY);
                    }else{
                        if(position<mHeaderPostion){
                            super.onBindViewHolder(holder,position);
                        }else if(position>mHeaderPostion){
                            super.onBindViewHolder(holder,position-1);
                        }
                    }
                }
            }
        }
    }
}
