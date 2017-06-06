package com.luo.demo.gankio.bean;

import java.util.List;

/**
 * Project: Gankio
 * Author：Mr.Luo
 * Date: 2017-05-21 18:09
 * Email：175262808@qq.com
 * TODO:拓展资源
 * FIXME:
 */

public class Expand {
    /**
     * error : false
     * results : [{"_id":"59143d68421aa90c7a8b2b04","createdAt":"2017-05-11T18:31:04.55Z","desc":"图片择优（选择最清楚的图片) 没有使用第三方库","images":["http://img.gank.io/7d32ef8d-3d6c-437d-9b2e-368f879ebe9d"],"publishedAt":"2017-05-12T13:44:54.673Z","source":"web","type":"拓展资源","url":"https://github.com/Niekon/FuzzyDetection","used":true,"who":"Niekon"},{"_id":"59149846421aa90c7a8b2b08","createdAt":"2017-05-12T00:58:46.470Z","desc":"sketch 里用于生成页面关系图的插件","images":["http://img.gank.io/e37f2ead-2bdd-4610-bbc1-332e426bbaa4"],"publishedAt":"2017-05-12T13:44:54.673Z","source":"chrome","type":"拓展资源","url":"https://github.com/abynim/UserFlows","used":true,"who":"jk2K"},{"_id":"59152679421aa90c7fefdd98","createdAt":"2017-05-12T11:05:29.693Z","desc":"Web 安全资料合集，快收藏起来先。","publishedAt":"2017-05-12T13:44:54.673Z","source":"chrome","type":"拓展资源","url":"https://github.com/qazbnm456/awesome-web-security","used":true,"who":"web"},{"_id":"5913ce8c421aa90c83a51416","createdAt":"2017-05-11T10:38:04.182Z","desc":"晋升 Python 数据科学家之路。","images":["http://img.gank.io/c0dad69e-b583-4e2a-b56d-fb5a835e0f82"],"publishedAt":"2017-05-11T12:03:09.581Z","source":"chrome","type":"拓展资源","url":"https://github.com/jakevdp/PythonDataScienceHandbook","used":true,"who":"PTH"},{"_id":"5913cf02421aa90c83a51418","createdAt":"2017-05-11T10:40:02.449Z","desc":"开源的类 PostMan，API 管理工具，强烈推荐。","images":["http://img.gank.io/6919ba82-dc5d-4932-b51a-1a8e609c3977"],"publishedAt":"2017-05-11T12:03:09.581Z","source":"chrome","type":"拓展资源","url":"https://github.com/getinsomnia/insomnia","used":true,"who":"代码家"},{"_id":"590fdcf3421aa90c7a8b2ad9","createdAt":"2017-05-08T10:50:27.453Z","desc":"那些赚钱了的独立开发者项目聚合，向赚钱的项目学习。","publishedAt":"2017-05-08T11:22:01.540Z","source":"chrome","type":"拓展资源","url":"https://github.com/mezod/awesome-indie","used":true,"who":"代码家"},{"_id":"590be499421aa90c83a513d9","createdAt":"2017-05-05T10:34:01.541Z","desc":"跟着这个教程，自己写个 Linux。","publishedAt":"2017-05-05T11:56:35.629Z","source":"chrome","type":"拓展资源","url":"https://github.com/MichielDerhaeg/build-linux","used":true,"who":"代码家"},{"_id":"590be8da421aa90c7d49ad42","createdAt":"2017-05-05T10:52:10.346Z","desc":"一个 Fake Wifi 攻击方案","images":["http://img.gank.io/c929dab7-9c23-4fbc-b32e-2c6b69267cdd"],"publishedAt":"2017-05-05T11:56:35.629Z","source":"chrome","type":"拓展资源","url":"https://github.com/wifiphisher/wifiphisher","used":true,"who":"代码家"},{"_id":"59094b91421aa90c83a513c2","createdAt":"2017-05-03T11:16:33.307Z","desc":"全新的 SSH Key 管理工具，结合移动端，保证安全性和快速认证优势。","publishedAt":"2017-05-03T12:00:31.516Z","source":"chrome","type":"拓展资源","url":"https://krypt.co/","used":true,"who":"代码家"},{"_id":"59075ca6421aa90c7fefdd16","createdAt":"2017-05-02T00:04:54.622Z","desc":"教你入门强化学习（Reinforcement Learning），并利用 TensorFlow 教会电脑学会玩游戏（CartPole，FlappyBird等等）。","images":["http://img.gank.io/d7b62bb0-30f8-4bc7-87ad-1cdd528223e2"],"publishedAt":"2017-05-02T12:00:17.802Z","source":"web","type":"拓展资源","url":"https://lufficc.com/blog/reinforcement-learning-and-implementation","used":true,"who":null}]
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
         * _id : 59143d68421aa90c7a8b2b04
         * createdAt : 2017-05-11T18:31:04.55Z
         * desc : 图片择优（选择最清楚的图片) 没有使用第三方库
         * images : ["http://img.gank.io/7d32ef8d-3d6c-437d-9b2e-368f879ebe9d"]
         * publishedAt : 2017-05-12T13:44:54.673Z
         * source : web
         * type : 拓展资源
         * url : https://github.com/Niekon/FuzzyDetection
         * used : true
         * who : Niekon
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
