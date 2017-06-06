package com.luo.demo.gankio.bean;

import java.util.List;

/**
 * Project: Gankio
 * Author：Mr.Luo
 * Date: 2017-05-21 18:11
 * Email：175262808@qq.com
 * TODO: 前端
 * FIXME:
 */

public class JS {


    /**
     * error : false
     * results : [{"_id":"5919cd83421aa92c769a8b61","createdAt":"2017-05-15T23:47:15.588Z","desc":"前端每周清单第 13 期：Webpack CLI 发布、Angular 5 可期待的新特性、解密现代浏览器引擎构建之道","publishedAt":"2017-05-16T12:10:38.580Z","source":"chrome","type":"前端","url":"https://zhuanlan.zhihu.com/p/26920959","used":true,"who":"王下邀月熊"},{"_id":"5918e8d5421aa91c8e7b1ec1","createdAt":"2017-05-15T07:31:33.476Z","desc":"可能是目前最漂亮的一款富文本编辑器，支持主题切换，支持所有平台。","publishedAt":"2017-05-15T12:03:44.165Z","source":"chrome","type":"前端","url":"https://github.com/n457/Uncolored","used":true,"who":"代码家"},{"_id":"5918eb5b421aa91c8e7b1ec3","createdAt":"2017-05-15T07:42:19.586Z","desc":"用 JS 实现的 Png 图片压缩工具，效果赛过 TinyPNG 喔~","images":["http://img.gank.io/02c7c126-de84-4a07-8833-2e97b11b10b2"],"publishedAt":"2017-05-15T12:03:44.165Z","source":"chrome","type":"前端","url":"https://blog.photopea.com/png-minifier-inside-photopea.html#examples","used":true,"who":"Allen"},{"_id":"591526d0421aa90c7fefdd99","createdAt":"2017-05-12T11:06:56.489Z","desc":"从没见过这么浮夸的动画效果，出乎你意料","publishedAt":"2017-05-12T13:44:54.673Z","source":"chrome","type":"前端","url":"http://tholman.com/obnoxious/","used":true,"who":"OK"},{"_id":"5913cd3f421aa90c7a8b2afd","createdAt":"2017-05-11T10:32:31.922Z","desc":"Vue 漂亮的 Tab 组件效果。","publishedAt":"2017-05-11T12:03:09.581Z","source":"chrome","type":"前端","url":"http://vue-tabs-component.spatie.be/#first-tab","used":true,"who":"TCO"},{"_id":"59116ffa421aa90c83a51406","createdAt":"2017-05-09T15:30:02.154Z","desc":"GUI 应用程序架构的十年变迁：MVC、MVP、MVVM、Unidirectional、Clean","publishedAt":"2017-05-10T11:56:10.18Z","source":"chrome","type":"前端","url":"https://zhuanlan.zhihu.com/p/26799645","used":true,"who":"王下邀月熊"},{"_id":"5911c41d421aa90c7a8b2aeb","createdAt":"2017-05-09T21:29:01.655Z","desc":"使用 Headless Chrome 进行页面渲染","publishedAt":"2017-05-10T11:56:10.18Z","source":"chrome","type":"前端","url":"https://zhuanlan.zhihu.com/p/26810049?refer=wxyyxc1992","used":true,"who":"王下邀月熊"},{"_id":"590cd3d8421aa90c83a513e6","createdAt":"2017-05-06T03:34:48.414Z","desc":"基于 WebGL 的滤镜系统","images":["http://img.gank.io/378948a3-ae96-42f4-8069-83263bc87000"],"publishedAt":"2017-05-08T11:22:01.540Z","source":"web","type":"前端","url":"https://redknotmiaoyuqiao.github.io/RedWebImage/index.html","used":true,"who":"YuqiaoMiao"},{"_id":"590f0be3421aa90c7d49ad54","createdAt":"2017-05-07T19:58:27.176Z","desc":"[Vue]精致完整的Vue豆瓣项目，Awesome douban DEMO created with Vue2.x + Vuex + Vue-router + vue-resource ","images":["http://img.gank.io/db3fc835-fcf4-4258-a7ac-4d0bc47105f8"],"publishedAt":"2017-05-08T11:22:01.540Z","source":"web","type":"前端","url":"https://github.com/jeneser/douban","used":true,"who":"yazhe wang"},{"_id":"590898fa421aa90c83a513bc","createdAt":"2017-05-02T22:34:34.139Z","desc":"前端每周清单第 11 期：Angular 4.1支持TypeScript 2.3，Vue 2.3优化服务端渲染，优秀React界面框架合集","publishedAt":"2017-05-03T12:00:31.516Z","source":"chrome","type":"前端","url":"https://zhuanlan.zhihu.com/p/26682332","used":true,"who":"王下邀月熊"}]
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
         * _id : 5919cd83421aa92c769a8b61
         * createdAt : 2017-05-15T23:47:15.588Z
         * desc : 前端每周清单第 13 期：Webpack CLI 发布、Angular 5 可期待的新特性、解密现代浏览器引擎构建之道
         * publishedAt : 2017-05-16T12:10:38.580Z
         * source : chrome
         * type : 前端
         * url : https://zhuanlan.zhihu.com/p/26920959
         * used : true
         * who : 王下邀月熊
         * images : ["http://img.gank.io/02c7c126-de84-4a07-8833-2e97b11b10b2"]
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
