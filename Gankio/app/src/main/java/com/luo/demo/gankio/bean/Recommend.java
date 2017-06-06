package com.luo.demo.gankio.bean;

import java.util.List;

/**
 * 包名:  com.luo.demo.gankio.bean
 * 作者:  Mr.Luo
 * 时间:  2017/6/6 16:57
 * 描述:  TODO
 * 联系:  175262808@qq.com
 */

public class Recommend {
    /**
     * error : false
     * results : [{"_id":"592fde42421aa92c7be61b89","createdAt":"2017-06-01T17:28:34.410Z","desc":"统计语言模型浅谈","publishedAt":"2017-06-02T12:26:37.346Z","source":"chrome","type":"瞎推荐","url":"https://zhuanlan.zhihu.com/p/27201427","used":true,"who":"王下邀月熊"},{"_id":"592d62dd421aa92c769a8be0","createdAt":"2017-05-30T20:17:33.245Z","desc":"熊写代码这三年：阅读写作与技术成长","publishedAt":"2017-06-01T14:35:22.88Z","source":"chrome","type":"瞎推荐","url":"https://zhuanlan.zhihu.com/p/27169766","used":true,"who":"王下邀月熊"},{"_id":"592edea1421aa92c7be61b7a","createdAt":"2017-05-31T23:17:53.854Z","desc":"前端每周清单第 15 期：Node.js v8.0发布，从React迁移到 Vue，前端开发的未来","publishedAt":"2017-06-01T14:35:22.88Z","source":"chrome","type":"瞎推荐","url":"https://zhuanlan.zhihu.com/p/27187783","used":true,"who":"王下邀月熊"},{"_id":"591b0bbb421aa92c769a8b69","createdAt":"2017-05-16T22:24:59.87Z","desc":"测试中 Fakes、Mocks 以及 Stubs 概念明晰","publishedAt":"2017-05-23T11:14:05.141Z","source":"chrome","type":"瞎推荐","url":"https://zhuanlan.zhihu.com/p/26942686","used":true,"who":"王下邀月熊"},{"_id":"591842e1421aa91c8e7b1eb9","createdAt":"2017-05-14T19:43:29.648Z","desc":"Emoji 大全","publishedAt":"2017-05-15T12:03:44.165Z","source":"chrome","type":"瞎推荐","url":"http://www.unicode.org/emoji/charts/full-emoji-list.html#1f608","used":true,"who":"jp"},{"_id":"59191156421aa92c769a8b56","createdAt":"2017-05-15T10:24:22.680Z","desc":"首次现身中国的CTB-Locker\u201c比特币敲诈者\u201d病毒分析","images":["http://img.gank.io/abb77fe9-14c3-4688-a57b-e16b2264a019"],"publishedAt":"2017-05-15T12:03:44.165Z","source":"chrome","type":"瞎推荐","url":"http://blogs.360.cn/blog/ctb-locker/","used":true,"who":"LHF"},{"_id":"5913ced0421aa90c7fefdd89","createdAt":"2017-05-11T10:39:12.387Z","desc":"微软发布正式版 Visual Studio for Mac，快来尝鲜吧。","publishedAt":"2017-05-11T12:03:09.581Z","source":"chrome","type":"瞎推荐","url":"https://www.visualstudio.com/zh-hans/vs/visual-studio-mac/?rr=https%3A%2F%2Fnews.ycombinator.com%2F","used":true,"who":"代码家"},{"_id":"59102835421aa90c83a513f7","createdAt":"2017-05-08T16:11:33.849Z","desc":"泛编程语言学习知识结构：JavaScript、Java、Python、Go","publishedAt":"2017-05-10T11:56:10.18Z","source":"chrome","type":"瞎推荐","url":"https://zhuanlan.zhihu.com/p/26781450#tipjar","used":true,"who":"王下邀月熊"},{"_id":"591269a4421aa90c7a8b2aee","createdAt":"2017-05-10T09:15:16.408Z","desc":"Mirror - 基于 issues 的博客工具","images":["http://img.gank.io/8adea828-9198-4506-836c-cae967d8c38e"],"publishedAt":"2017-05-10T11:56:10.18Z","source":"web","type":"瞎推荐","url":"https://github.com/LoeiFy/Mirror","used":true,"who":"JackHang"},{"_id":"59128095421aa90c7a8b2af0","createdAt":"2017-05-10T10:53:09.37Z","desc":"给你的 Kindle 装上 Debian 操作系统。","publishedAt":"2017-05-10T11:56:10.18Z","source":"chrome","type":"瞎推荐","url":"https://github.com/DylanHamer/DebianKindle","used":true,"who":"代码家"}]
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

    /*public static class ResultsBean {
        *//**
         * _id : 592fde42421aa92c7be61b89
         * createdAt : 2017-06-01T17:28:34.410Z
         * desc : 统计语言模型浅谈
         * publishedAt : 2017-06-02T12:26:37.346Z
         * source : chrome
         * type : 瞎推荐
         * url : https://zhuanlan.zhihu.com/p/27201427
         * used : true
         * who : 王下邀月熊
         * images : ["http://img.gank.io/abb77fe9-14c3-4688-a57b-e16b2264a019"]
         *//*

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
    }*/
}
