package com.cmazxiaoma.mvp.ui.adapter;

import android.content.Context;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.ViewGroup;

import com.cmazxiaoma.mvp.R;
import com.cmazxiaoma.mvp.Utils.UtilsLog;
import com.cmazxiaoma.mvp.base.BaseMultiRecyclerAdapter;
import com.cmazxiaoma.mvp.base.BaseViewHolder;
import com.cmazxiaoma.mvp.bean.GankPhotoResult;
import com.cmazxiaoma.mvp.bean.PhotoResult;

import java.util.List;

import static com.cmazxiaoma.mvp.Config.TYPE_FOOT;
import static com.cmazxiaoma.mvp.Config.TYPE_ITEM;

/**
 * Description: 沉梦昂志
 * Data：2017/5/18-13:28
 * Author: xiaoma
 */

public class GankPhotoAdapter extends BaseMultiRecyclerAdapter<GankPhotoResult.ResultsBean>{
    public GankPhotoAdapter(Context mcontext, List<GankPhotoResult.ResultsBean> mData) {
        super(mcontext, mData);
    }

    @Override
    public void bindTheData(BaseViewHolder holder, GankPhotoResult.ResultsBean itemData,boolean textViewChangeColor) {
        holder.setImage(R.id.item_photo_img_reco_img,itemData.getUrl());
    }

    @Override
    public void onViewAttachedToWindow(BaseViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        if(holder.getViewType()==TYPE_FOOT){
            ViewGroup.LayoutParams lp=holder.getConvertView().getLayoutParams();
            if(lp!=null&& lp instanceof StaggeredGridLayoutManager.LayoutParams&&holder.getLayoutPosition()+1==getItemCount()){
                StaggeredGridLayoutManager.LayoutParams p= (StaggeredGridLayoutManager.LayoutParams) lp;
                p.setFullSpan(true);
            }
        }
    }

    @Override
    public int getLayoutId(int itemType) {
        int layoutId=-1;
        switch (itemType){
            case TYPE_ITEM:
                layoutId= R.layout.item_photo_img_reco;
                break;
            case TYPE_FOOT:
                layoutId=R.layout.item_foot;
                break;
        }
        return layoutId;
    }

    @Override
    public int getItemViewType(int position, GankPhotoResult.ResultsBean resultsBean) {
        if(position+1==getItemCount()){
            return TYPE_FOOT;
        }else{
            return TYPE_ITEM;
        }
    }

    @Override
    public int getItemCount() {
        return super.getItemCount()+1;
    }
}
