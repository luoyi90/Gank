package com.luo.demo.gankio.bean;

import java.util.List;

/**
 * Project: Gankio
 * Author：Mr.Luo
 * Date: 2017-05-21 18:10
 * Email：175262808@qq.com
 * TODO:休息视频
 * FIXME:
 */

public class Video {

    /**
     * error : false
     * results : [{"_id":"59172946421aa91c8e7b1eb1","createdAt":"2017-05-13T23:41:58.32Z","desc":"【牛叔】日本学生的励志故事《垫底辣妹》看完之后你哭了没","publishedAt":"2017-05-16T12:10:38.580Z","source":"chrome","type":"休息视频","url":"http://www.bilibili.com/video/av9266813/","used":true,"who":"LHF"},{"_id":"59184d17421aa91c92f77672","createdAt":"2017-05-14T20:27:03.737Z","desc":"这是一个没有几句台词的短片，看完却让人深深地感动，没有因为贫穷而贪婪，没有因为富足而骄奢","publishedAt":"2017-05-15T12:03:44.165Z","source":"chrome","type":"休息视频","url":"http://www.miaopai.com/show/d-~57G~wdfxRzXA9bcwLhLAME0D9rHz7.htm","used":true,"who":"lxxself"},{"_id":"591471a0421aa90c83a5141f","createdAt":"2017-05-11T22:13:52.592Z","desc":"《银河护卫队2》8个知识点+深度彩蛋解析（无剧透）","publishedAt":"2017-05-12T13:44:54.673Z","source":"chrome","type":"休息视频","url":"http://www.bilibili.com/video/av10476695/","used":true,"who":"LHF"},{"_id":"591067c5421aa90c83a513fa","createdAt":"2017-05-08T20:42:45.664Z","desc":"在地下，拥有258平方公里的城区，上万居民，是世界10大奇景","publishedAt":"2017-05-11T12:03:09.581Z","source":"chrome","type":"休息视频","url":"http://www.bilibili.com/video/av10408797/","used":true,"who":"LHF"},{"_id":"590f2b98421aa90c7a8b2ad2","createdAt":"2017-05-07T22:13:44.239Z","desc":"横扫阿凡达韩国票房纪录 海战史诗巨制 《鸣梁海战》","publishedAt":"2017-05-10T11:56:10.18Z","source":"chrome","type":"休息视频","url":"http://www.bilibili.com/video/av10379658/","used":true,"who":"LHF"},{"_id":"59106976421aa90c7fefdd68","createdAt":"2017-05-08T20:49:58.915Z","desc":"【问舰】痞子英雄成长记！漫威经典电影《银河护卫队》","publishedAt":"2017-05-09T12:13:25.467Z","source":"chrome","type":"休息视频","url":"http://www.bilibili.com/video/av10396774/","used":true,"who":"LHF"},{"_id":"590f2780421aa90c7fefdd58","createdAt":"2017-05-07T21:56:16.410Z","desc":"【阿斗】神秘金字塔惊现世界，暗藏可怕生物！速看恐怖片《夺命金字塔》","publishedAt":"2017-05-08T11:22:01.540Z","source":"chrome","type":"休息视频","url":"http://www.bilibili.com/video/av10389462/","used":true,"who":"LHF"},{"_id":"5909cde7421aa90c83a513cc","createdAt":"2017-05-03T20:32:39.81Z","desc":"银河护卫队 树人格鲁特和火箭浣熊的初次相识【xx说漫画】","publishedAt":"2017-05-05T11:56:35.629Z","source":"chrome","type":"休息视频","url":"http://www.bilibili.com/video/av10277266/","used":true,"who":"LHF"},{"_id":"5909cdf4421aa90c83a513ce","createdAt":"2017-05-03T20:32:52.865Z","desc":"这利器河水直接喝，杀死99%细菌，不买矿泉水","publishedAt":"2017-05-04T11:43:26.66Z","source":"chrome","type":"休息视频","url":"http://www.bilibili.com/video/av10277813/","used":true,"who":"LHF"},{"_id":"5904b390421aa90c7d49ad11","createdAt":"2017-04-29T23:38:56.892Z","desc":"DC排名第一的漫画《守望者》导读","publishedAt":"2017-05-03T12:00:31.516Z","source":"chrome","type":"休息视频","url":"http://www.bilibili.com/video/av10174694/","used":true,"who":"LHF"}]
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
         * _id : 59172946421aa91c8e7b1eb1
         * createdAt : 2017-05-13T23:41:58.32Z
         * desc : 【牛叔】日本学生的励志故事《垫底辣妹》看完之后你哭了没
         * publishedAt : 2017-05-16T12:10:38.580Z
         * source : chrome
         * type : 休息视频
         * url : http://www.bilibili.com/video/av9266813/
         * used : true
         * who : LHF
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
    }*/
}
