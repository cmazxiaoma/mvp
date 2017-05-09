package com.cmazxiaoma.mvp.bean;

import java.util.List;

/**
 * Description: 沉梦昂志
 * Data：2017/5/7-10:52
 * Author: xiaoma
 */

public class GankResult {

    /**
     * error : false
     * results : [{"_id":"590af3b3421aa90c7fefdd3c","createdAt":"2017-05-04T17:26:11.264Z","desc":"Android自定义动画酷炫的提交按钮","publishedAt":"2017-05-05T11:56:35.629Z","source":"web","type":"Android","url":"http://url.cn/48GSjkM","used":true,"who":"陈宇明"},{"_id":"590b23cb421aa90c7fefdd3e","createdAt":"2017-05-04T20:51:23.101Z","desc":"Android Dagger2: Critical things to know before you implement","publishedAt":"2017-05-05T11:56:35.629Z","source":"web","type":"Android","url":"https://blog.mindorks.com/android-dagger2-critical-things-to-know-before-you-implement-275663aecc3e","used":true,"who":"AMIT SHEKHAR"},{"_id":"590be7d5421aa90c7d49ad3f","createdAt":"2017-05-05T10:47:49.687Z","desc":"Android 室内场景构建组件，帮你快速的完成室内场景 View 的展示","images":["http://img.gank.io/40be7210-4720-4f08-9d0b-8793bbdde0bc"],"publishedAt":"2017-05-05T11:56:35.629Z","source":"chrome","type":"Android","url":"https://github.com/karonl/InDoorSurfaceView","used":true,"who":"代码家"},{"_id":"5909a86d421aa90c7d49ad30","createdAt":"2017-05-03T17:52:45.723Z","desc":"你一定会用到的RxJava常用操作符","publishedAt":"2017-05-04T11:43:26.66Z","source":"web","type":"Android","url":"http://url.cn/48DFizd","used":true,"who":"陈宇明"},{"_id":"5909a98f421aa90c7a8b2ab5","createdAt":"2017-05-03T17:57:35.642Z","desc":"深入理解MessageQueue","publishedAt":"2017-05-04T11:43:26.66Z","source":"web","type":"Android","url":"https://pqpo.me/2017/05/03/learn-messagequeue/","used":true,"who":"Linmin Qiu"},{"_id":"590a9c23421aa90c7fefdd37","createdAt":"2017-05-04T11:12:35.278Z","desc":"Android 晃动检测小工具。","publishedAt":"2017-05-04T11:43:26.66Z","source":"chrome","type":"Android","url":"https://github.com/safetysystemtechnology/android-shake-detector","used":true,"who":"代码家"},{"_id":"590859ce421aa90c7d49ad1f","createdAt":"2017-05-02T18:05:02.18Z","desc":"Android 多状态加载布局的开发 Tips","publishedAt":"2017-05-03T12:00:31.516Z","source":"chrome","type":"Android","url":"http://gudong.name/2017/04/26/loading_layout_practice.html","used":true,"who":"咕咚"},{"_id":"59088f4f421aa90c7a8b2aa7","createdAt":"2017-05-02T21:53:19.611Z","desc":"loading button","images":["http://img.gank.io/d426bc04-06e0-4574-911b-b609c7deb077"],"publishedAt":"2017-05-03T12:00:31.516Z","source":"chrome","type":"Android","url":"https://github.com/leandroBorgesFerreira/LoadingButtonAndroid","used":true,"who":"Jason"},{"_id":"5908a423421aa90c7fefdd27","createdAt":"2017-05-02T23:22:11.39Z","desc":"轻松实现炫酷动画，让 app 加载动画像加载图片一样简单。","images":["http://img.gank.io/13c4b927-fca6-4025-b781-4048b4e93b8a"],"publishedAt":"2017-05-03T12:00:31.516Z","source":"web","type":"Android","url":"http://www.liujun.info/2017/04/25/Lottie库实现直播礼物动画/","used":true,"who":"Jun Liu"},{"_id":"59092501421aa90c83a513bd","createdAt":"2017-05-03T08:32:01.0Z","desc":"带有进度动画的Android自定义提交按钮。","images":["http://img.gank.io/88bd8d9d-f9f0-4fdc-9dd2-22348f450620","http://img.gank.io/642a380c-f624-416c-a18e-9550e1dd61e6"],"publishedAt":"2017-05-03T12:00:31.516Z","source":"web","type":"Android","url":"https://github.com/Someonewow/SubmitButton","used":true,"who":"Unstoppable"}]
     */

    private boolean error;
    private List<ResultsBean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * _id : 590af3b3421aa90c7fefdd3c
         * createdAt : 2017-05-04T17:26:11.264Z
         * desc : Android自定义动画酷炫的提交按钮
         * publishedAt : 2017-05-05T11:56:35.629Z
         * source : web
         * type : Android
         * url : http://url.cn/48GSjkM
         * used : true
         * who : 陈宇明
         * images : ["http://img.gank.io/40be7210-4720-4f08-9d0b-8793bbdde0bc"]
         */

        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;
        private List<String> images;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }
}
