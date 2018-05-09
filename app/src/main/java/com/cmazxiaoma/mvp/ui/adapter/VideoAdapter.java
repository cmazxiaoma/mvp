package com.cmazxiaoma.mvp.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cmazxiaoma.mvp.R;
import com.cmazxiaoma.mvp.Utils.MyPicasso;
import com.cmazxiaoma.mvp.Utils.UtilsLog;
import com.cmazxiaoma.mvp.bean.NewResult;
import com.cmazxiaoma.mvp.bean.VideoResult;
import com.lzy.widget.CircleImageView;

import org.w3c.dom.Text;

import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCMediaManager;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Description: 沉梦昂志
 * Data：2017/7/8-17:20
 * Author: xiaoma
 */

public class VideoAdapter extends BaseAdapter {
    private List<VideoResult.ShowapiResBodyBean.PagebeanBean.ContentlistBean> mData;
    private Context mContext;
    private OnVideoPlayBackButtonClickListener backButtonClickListener;

    public interface OnVideoPlayBackButtonClickListener {
        void onClick();
    }

    public void setBackButtonClickListener(OnVideoPlayBackButtonClickListener backButtonClickListener) {
        this.backButtonClickListener = backButtonClickListener;
    }

    public VideoAdapter(Context mContext, List<VideoResult.ShowapiResBodyBean.PagebeanBean.ContentlistBean> mData) {
        this.mContext=mContext;

        if(mData!=null){
            this.mData=mData;
        }else{
            throw new  IllegalArgumentException("mData不能空,参数错误");
        }
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView==null){
            viewHolder=new ViewHolder();
            convertView= LayoutInflater.from(mContext).inflate(R.layout.item_video,parent,false);
            viewHolder.jcVideoPlayerStandard= (JCVideoPlayerStandard) convertView.findViewById(R.id.item_video_videoPlayer);
            viewHolder.publishTime= (TextView) convertView.findViewById(R.id.item_video_publishTime);
            viewHolder.desc= (TextView) convertView.findViewById(R.id.item_video_desc);
            viewHolder.name= (TextView) convertView.findViewById(R.id.item_video_name);
            viewHolder.love= (TextView) convertView.findViewById(R.id.item_video_love);
            viewHolder.hate= (TextView) convertView.findViewById(R.id.item_video_hate);
            viewHolder.headImg= (CircleImageView) convertView.findViewById(R.id.item_video_headImg);
            convertView.setTag(viewHolder);
        } else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        VideoResult.ShowapiResBodyBean.PagebeanBean.ContentlistBean data=mData.get(position);
        viewHolder.publishTime.setText(data.getCreate_time());
        String desc=data.getText().trim().replace("\n","");
        viewHolder.desc.setText(desc);
        viewHolder.love.setText(data.getLove());
        viewHolder.hate.setText(data.getHate());
        String author=data.getName();
        viewHolder.name.setText(author);
        String videoUrl=data.getVideo_uri();
        String imgUrl=data.getProfile_image();
        viewHolder.jcVideoPlayerStandard.setUp(videoUrl,JCVideoPlayer.SCREEN_LAYOUT_LIST, author);
        viewHolder.jcVideoPlayerStandard.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (backButtonClickListener != null) {
                    backButtonClickListener.onClick();
                }
            }
        });
        viewHolder.jcVideoPlayerStandard.fullscreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (backButtonClickListener != null) {
                    backButtonClickListener.onClick();
                }
            }
        });
        MyPicasso.loadDefaultImg(mContext,imgUrl,viewHolder.headImg);
        MyPicasso.loadCropSquareImg(mContext,imgUrl,viewHolder.jcVideoPlayerStandard.thumbImageView);
        return convertView;
    }

    private static  class ViewHolder {
        public  JCVideoPlayerStandard jcVideoPlayerStandard;
        public TextView publishTime,desc,name,love,hate;
        public CircleImageView headImg;
    }
}
