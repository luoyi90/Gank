package com.luo.demo.gankio.bean;

import java.io.Serializable;

/**
 * 包名:  com.luo.demo.gankio.bean
 * 作者:  Mr.Luo
 * 时间:  2017/8/9 16:05
 * 描述:  TODO
 * 联系:  175262808@qq.com
 */

public class EveryDay implements Serializable {

    private static final long serialVersionUID = 4335782683279324708L;

    private String img;
    private History.ResultsBean b;

    public History.ResultsBean getB() {
        return b;
    }

    public void setB(History.ResultsBean b) {
        this.b = b;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
