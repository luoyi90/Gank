package com.luo.demo.gankio.bean;

import java.util.List;

/**
 * Project: Gankio
 * Author：Mr.Luo
 * Date: 2017-05-21 18:05
 * Email：175262808@qq.com
 * TODO:ios
 * FIXME:
 */

public class IOS {

    /**
     * error : false
     * results : [{"_id":"5918eac7421aa91c82fa666d","createdAt":"2017-05-15T07:39:51.279Z","desc":"IOS Banner 通知效果","images":["http://img.gank.io/5700a25d-e2b8-456e-a3ba-7736eb0ccbed"],"publishedAt":"2017-05-16T12:10:38.580Z","source":"chrome","type":"IOS","url":"https://github.com/Daltron/NotificationBanner","used":true,"who":"Ola"},{"_id":"591a61b0421aa92c7be61b05","createdAt":"2017-05-16T10:19:28.326Z","desc":"这个倒计时效果，可以的","images":["http://img.gank.io/4f70f367-bd33-4e53-9485-60edaec60f73"],"publishedAt":"2017-05-16T12:10:38.580Z","source":"chrome","type":"IOS","url":"https://github.com/rsrbk/SRCountdownTimer","used":true,"who":"Allen"},{"_id":"591a61f2421aa92c73b64706","createdAt":"2017-05-16T10:20:34.725Z","desc":"@Swift 2017 活动现场相关资源合集","publishedAt":"2017-05-16T12:10:38.580Z","source":"chrome","type":"IOS","url":"https://github.com/atConf/atswift-2017-resources","used":true,"who":"Allen"},{"_id":"591857e1421aa91c92f77673","createdAt":"2017-05-14T21:13:05.282Z","desc":"iOS平台的爆炸式菜单按钮2.0.0版本","publishedAt":"2017-05-15T12:03:44.165Z","source":"web","type":"IOS","url":"https://github.com/Nightonke/VHBoomMenuButton ","used":true,"who":"Weiping Huang"},{"_id":"5918e981421aa91c8da340d4","createdAt":"2017-05-15T07:34:25.701Z","desc":"基于 RxSwift 实现的 State 状态管理工具。","images":["http://img.gank.io/fbaa3bd1-a372-4eb1-8e40-cffeedc38a9f"],"publishedAt":"2017-05-15T12:03:44.165Z","source":"chrome","type":"IOS","url":"https://github.com/kzaher/RxFeedback","used":true,"who":"Allen"},{"_id":"5918e9f7421aa91c92f77677","createdAt":"2017-05-15T07:36:23.488Z","desc":"React Native 日历组件，清新、漂亮。","images":["http://img.gank.io/a85956f6-f82b-4d4a-8226-01fb259a60c0","http://img.gank.io/ca7d2399-a1d8-4280-978b-8cb0a5cedd2b"],"publishedAt":"2017-05-15T12:03:44.165Z","source":"chrome","type":"IOS","url":"https://github.com/wix/react-native-calendars","used":true,"who":"代码家"},{"_id":"5918eaa3421aa91c8da340d5","createdAt":"2017-05-15T07:39:15.514Z","desc":"又一个漂亮实用的图片、视频选择器。","images":["http://img.gank.io/fde6b50e-7d7e-4fc7-8894-929dd32cc35b"],"publishedAt":"2017-05-15T12:03:44.165Z","source":"chrome","type":"IOS","url":"https://github.com/tilltue/TLPhotoPicker","used":true,"who":"Ola"},{"_id":"591521d2421aa90c7d49ad8f","createdAt":"2017-05-12T10:45:38.781Z","desc":"IOS 类似 Excel 的 SpreadSheet 组件效果。","images":["http://img.gank.io/88e1376d-244e-47ac-948d-b7adb0c9266f"],"publishedAt":"2017-05-12T13:44:54.673Z","source":"chrome","type":"IOS","url":"https://github.com/kishikawakatsumi/SpreadsheetView","used":true,"who":"代码家"},{"_id":"59153267421aa90c7d49ad92","createdAt":"2017-05-12T11:56:23.861Z","desc":"IOS 独立开发者福音，App ICON 生成工具，自动生成所有尺寸的 Icon。","images":["http://img.gank.io/8cf5d984-d3f4-46e2-9345-cfcc1369730f"],"publishedAt":"2017-05-12T13:44:54.673Z","source":"chrome","type":"IOS","url":"https://github.com/Nonchalant/AppIcon","used":true,"who":"allen"},{"_id":"5915336f421aa90c83a51423","createdAt":"2017-05-12T12:00:47.696Z","desc":"支持 App 分组的 Mac Docker Bar。","publishedAt":"2017-05-12T13:44:54.673Z","source":"chrome","type":"IOS","url":"https://github.com/DeromirNeves/VerticalBar","used":true,"who":"allen"}]
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
         * _id : 5918eac7421aa91c82fa666d
         * createdAt : 2017-05-15T07:39:51.279Z
         * desc : IOS Banner 通知效果
         * images : ["http://img.gank.io/5700a25d-e2b8-456e-a3ba-7736eb0ccbed"]
         * publishedAt : 2017-05-16T12:10:38.580Z
         * source : chrome
         * type : IOS
         * url : https://github.com/Daltron/NotificationBanner
         * used : true
         * who : Ola
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
