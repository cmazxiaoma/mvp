package com.cmazxiaoma.mvp.bean;

import java.util.List;

/**
 * Description: 沉梦昂志
 * Data：2017/5/17-14:06
 * Author: xiaoma
 */

public class ImageEvent {
    private int currentPosition;
    private List<String> imageUrls;
    public ImageEvent(int currentPosition, List<String> imageUrls) {
        this.currentPosition = currentPosition;
        this.imageUrls = imageUrls;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }
}
