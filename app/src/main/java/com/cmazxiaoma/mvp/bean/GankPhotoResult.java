package com.cmazxiaoma.mvp.bean;

import java.util.List;

/**
 * Description: 沉梦昂志
 * Data：2017/5/18-11:24
 * Author: xiaoma
 */

public class GankPhotoResult {
    /**
     * count : 10
     * error : false
     * results : [{"desc":"7.22","ganhuo_id":"56cc6d1d421aa95caa707777","publishedAt":"2015-07-22T03:59:19.558000","readability":"","type":"福利","url":"http://ww2.sinaimg.cn/large/610dc034gw1eubet6h43qj20qo0hsadh.jpg","who":"daimajia"},{"desc":"9.9","ganhuo_id":"56cc6d1d421aa95caa707875","publishedAt":"2015-09-09T04:10:30.719000","readability":"","type":"福利","url":"http://ww3.sinaimg.cn/large/7a8aed7bgw1evvxu1plowj20qo0hsgoz.jpg","who":"张涵宇"},{"desc":"9.22-可爱型！！！","ganhuo_id":"56cc6d1d421aa95caa7078d2","publishedAt":"2015-09-22T03:53:01.583000","readability":"","type":"福利","url":"http://ww3.sinaimg.cn/large/7a8aed7bgw1ewb2ytx5okj20go0p0jva.jpg","who":"张涵宇"},{"desc":"9.21","ganhuo_id":"56cc6d1d421aa95caa7078c6","publishedAt":"2015-09-21T03:38:47.257000","readability":"","type":"福利","url":"http://ww4.sinaimg.cn/large/7a8aed7bgw1ew9t261psfj20p00gxq4r.jpg","who":"张涵宇"},{"desc":"10.15","ganhuo_id":"56cc6d22421aa95caa70792e","publishedAt":"2015-10-15T11:01:18.105000","readability":"","type":"福利","url":"http://ww3.sinaimg.cn/large/7a8aed7bgw1ex0xcgp67kj20xc18g43b.jpg","who":"张涵宇"},{"desc":"10.10","ganhuo_id":"56cc6d22421aa95caa70790e","publishedAt":"2015-10-12T03:29:37.492000","readability":"","type":"福利","url":"http://ww1.sinaimg.cn/large/610dc034gw1ewy18hibdij20dw0kuq5z.jpg","who":"daimajia"},{"desc":"11.12","ganhuo_id":"56cc6d23421aa95caa707994","publishedAt":"2015-11-12T13:46:23.466000","readability":"","type":"福利","url":"http://ww2.sinaimg.cn/large/7a8aed7bjw1exy13si92lj20v218g10h.jpg","who":"张涵宇"},{"desc":"7.13\u2014\u2014（1）","ganhuo_id":"56cc6d23421aa95caa707ad1","publishedAt":"2015-07-13T03:33:29.504000","readability":"","type":"福利","url":"http://ww2.sinaimg.cn/large/7a8aed7bgw1eu0w2kjjr9j20hs0qoq6w.jpg","who":"张涵宇"},{"desc":"7.1","ganhuo_id":"56cc6d23421aa95caa707b39","publishedAt":"2015-07-01T03:59:29.034000","readability":"","type":"福利","url":"http://ww1.sinaimg.cn/large/7a8aed7bgw1etn2gzjoegj20gz0p9400.jpg","who":"张涵宇"},{"desc":"7.2","ganhuo_id":"56cc6d23421aa95caa707b36","publishedAt":"2015-07-01T03:59:28.731000","readability":"","type":"福利","url":"http://ww2.sinaimg.cn/large/610dc034jw1etn2pltc7mj20f90mwmye.jpg","who":"代码家"}]
     */

    private int count;
    private boolean error;
    private List<ResultsBean> results;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

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
         * desc : 7.22
         * ganhuo_id : 56cc6d1d421aa95caa707777
         * publishedAt : 2015-07-22T03:59:19.558000
         * readability :
         * type : 福利
         * url : http://ww2.sinaimg.cn/large/610dc034gw1eubet6h43qj20qo0hsadh.jpg
         * who : daimajia
         */

        private String desc;
        private String ganhuo_id;
        private String publishedAt;
        private String readability;
        private String type;
        private String url;
        private String who;

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getGanhuo_id() {
            return ganhuo_id;
        }

        public void setGanhuo_id(String ganhuo_id) {
            this.ganhuo_id = ganhuo_id;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getReadability() {
            return readability;
        }

        public void setReadability(String readability) {
            this.readability = readability;
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

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }
    }
}
