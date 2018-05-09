package com.cmazxiaoma.mvp.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;

import com.cmazxiaoma.mvp.R;
import com.cmazxiaoma.mvp.Utils.UtilsLog;
import com.cmazxiaoma.mvp.base.BaseMultiRecyclerAdapter;
import com.cmazxiaoma.mvp.base.BaseViewHolder;
import com.cmazxiaoma.mvp.bean.NewResult;

import java.util.List;

import static com.cmazxiaoma.mvp.Config.TYPE_FOOT;
import static com.cmazxiaoma.mvp.Config.TYPE_HEADER;
import static com.cmazxiaoma.mvp.Config.TYPE_ITEM_IMAGE_1;
import static com.cmazxiaoma.mvp.Config.TYPE_ITEM_IMAGE_2;
import static com.cmazxiaoma.mvp.Config.TYPE_ITEM_IMAGE_3;

/**
 * Description: 沉梦昂志
 * Data：2017/5/13-12:35
 * Author: xiaoma
 */

public class NewsAdapter extends BaseMultiRecyclerAdapter<NewResult.ResultBean.DataBean>{
    private onHeaderView listener;

    public void setListener(onHeaderView listener) {
        this.listener = listener;
    }

    public interface onHeaderView{
        void onHeaderViewClick();
    }
    public NewsAdapter(Context mcontext,onHeaderView listenr,List<NewResult.ResultBean.DataBean> mData) {
        super(mcontext, mData);
        this.listener=listenr;
    }

    //在 onCreateViewHolder里，一定要传入viewtype
    @Override
    public void bindTheData(BaseViewHolder holder, NewResult.ResultBean.DataBean itemData,boolean textViewChangeColor) {
        UtilsLog.i("textViewColor="+textViewChangeColor);
        switch (holder.getViewType()){
            case TYPE_ITEM_IMAGE_1:
                if(textViewChangeColor){
                    holder.setText(R.id.item_news_one_title,itemData.getTitle(),"#999999");
                }else{
                    holder.setText(R.id.item_news_one_title,itemData.getTitle(),"#000000");
                }
                holder.setText(R.id.item_news_one_author,itemData.getAuthor_name());
                holder.setImage(R.id.item_news_one_img1,itemData.getThumbnail_pic_s());
                break;
            case TYPE_ITEM_IMAGE_2:
                if(textViewChangeColor){
                    holder.setText(R.id.item_news_two_title,itemData.getTitle(),"#999999");
                }else{
                    holder.setText(R.id.item_news_two_title,itemData.getTitle(),"#000000");
                }
                holder.setText(R.id.item_news_two_author,itemData.getAuthor_name());
                holder.setImage(R.id.item_news_two_img1,itemData.getThumbnail_pic_s());
                holder.setImage(R.id.item_news_two_img2,itemData.getThumbnail_pic_s02());
                break;
            case TYPE_ITEM_IMAGE_3:
                if(textViewChangeColor){
                    holder.setText(R.id.item_news_three_title,itemData.getTitle(),"#999999");
                }else{
                    holder.setText(R.id.item_news_three_title,itemData.getTitle(),"#000000");
                }
                holder.setText(R.id.item_news_three_title,itemData.getTitle());
                holder.setText(R.id.item_news_three_author,itemData.getAuthor_name());
                holder.setImage(R.id.item_news_three_img1,itemData.getThumbnail_pic_s());
                holder.setImage(R.id.item_news_three_img2,itemData.getThumbnail_pic_s02());
                holder.setImage(R.id.item_news_three_img3,itemData.getThumbnail_pic_s03());
                break;
            case TYPE_HEADER:
                holder.getConvertView().setVisibility(View.VISIBLE);
                holder.setOnClickListener(R.id.item_header_button, R.id.item_header_img, R.id.item_header_lin, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onHeaderViewClick();
                    }
                });
                break;
        }
    }

    @Override
    public int getLayoutId(int itemType) {
        int layoutId=-1;
        switch (itemType){
            case TYPE_ITEM_IMAGE_1:
                layoutId=R.layout.item_news_one;
                break;
            case TYPE_ITEM_IMAGE_2:
                layoutId=R.layout.item_news_two ;
                break;
            case TYPE_ITEM_IMAGE_3:
                layoutId=R.layout.item_news_three;
                break;
            case TYPE_FOOT:
                layoutId=R.layout.item_foot;
                break;
            case TYPE_HEADER:
                layoutId=R.layout.item_header;
                break;
        }
        return layoutId;
    }


    @Override
    public int getItemViewType(int position, NewResult.ResultBean.DataBean dataBean) {
        if(super.mHeaderPostion==-1){
            if(position+1==getItemCount()){
                return TYPE_FOOT;
            }else{
                if(!TextUtils.isEmpty(dataBean.getThumbnail_pic_s03())){
                    return TYPE_ITEM_IMAGE_3;
                }else if (!TextUtils.isEmpty(dataBean.getThumbnail_pic_s02())){
                    return TYPE_ITEM_IMAGE_2;
                }else{
                    return TYPE_ITEM_IMAGE_1;
                }
                 }
        }else{
            if(position==super.mHeaderPostion){
                return TYPE_HEADER;
            }else if(position+1==getItemCount()){
                return TYPE_FOOT;
            }else{
                if(!TextUtils.isEmpty(dataBean.getThumbnail_pic_s03())){
                    return TYPE_ITEM_IMAGE_3;
                }else if (!TextUtils.isEmpty(dataBean.getThumbnail_pic_s02())){
                    return TYPE_ITEM_IMAGE_2;
                }else{
                    return TYPE_ITEM_IMAGE_1;
                }
            }
        }
    }
    @Override
    public int getItemCount() {
        if(super.mHeaderPostion==-1){
            return super.getItemCount()+1;
        }else{
            return super.getItemCount()+2;
        }
    }
}
