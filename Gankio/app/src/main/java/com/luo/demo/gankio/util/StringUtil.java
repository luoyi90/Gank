package com.luo.demo.gankio.util;

import com.luo.demo.gankio.R;

/**
 * 包名:  com.luo.demo.gankio.util
 * 作者:  Mr.Luo
 * 时间:  2017/5/24 15:50
 * 描述:  TODO
 * 联系:  175262808@qq.com
 */

public class StringUtil {

    private static final String GITHUB = "github";
    private static final String WEIXIN = "weixin";
    private static final String JIANSHU = "jianshu";

    public static Object[] getCategory(String str) {
        Object[] obj = new Object[2];
        if (str.contains(GITHUB)) {
            obj[0] = "github";
            obj[1] = R.color.black;
        } else if (str.contains(WEIXIN)) {
            obj[0] = "微信分享";
            obj[1] = R.color.springgreen;
        } else if (str.contains(JIANSHU)) {
            obj[0] = "简书";
            obj[1] = R.color.tomato;
        } else {
            obj[0] = "文章";
            obj[1] = R.color.brown;
        }
        return obj;
    }

    public static String getTime(String time) {
        String rTime = null;
        Log.d("time : " + time);
        String temp = TimeUtils.getFormatDate(time);
        Log.d("temp : " + temp);
        int hour = (int) TimeUtils.getTimeSpanByNow(temp, TimeConstants.HOUR);
        Log.d("hour : " + hour);
        if (hour < 24) {
            rTime = hour + "小时前";
        } else {
            rTime = hour / 24 + "天前";
        }
        return rTime;
    }
}
