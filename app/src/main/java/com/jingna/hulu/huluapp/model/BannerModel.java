package com.jingna.hulu.huluapp.model;

import java.util.List;

/**
 * Created by Administrator on 2018/10/26.
 */

public class BannerModel {

    /**
     * status : SUCCESS
     * errorCode : null
     * errorTitle : null
     * errorMsg : null
     * data : [{"id":10,"createBy":null,"bannerTitle":"321312","bannerUrl":"bilibili.com","bannerWeight":1,"createDate":1540471360000,"updateDate":null,"isShow":1,"num1":"/upload/b1ce933b7a5542cebc6a9fa7edc3e9f7.jpg","num2":null,"num3":null,"num4":null,"num5":null},{"id":12,"createBy":null,"bannerTitle":"测试轮播图","bannerUrl":"http://hl.5ijiaoyu.cn/hulu_h5/news.html?newsId=1","bannerWeight":2,"createDate":1540549636000,"updateDate":1540549830000,"isShow":1,"num1":"/upload/72fbdb4b8a2145f4b5747a863cc00105.jpg","num2":null,"num3":null,"num4":null,"num5":null},{"id":9,"createBy":null,"bannerTitle":"123123","bannerUrl":"http://hl.5ijiaoyu.cn/hulu_h5/news.html?newsId=2","bannerWeight":1,"createDate":1540470750000,"updateDate":1540523636000,"isShow":0,"num1":"/upload/b96fa1ec740447e4a873609d5ae56db3.jpg","num2":null,"num3":null,"num4":null,"num5":null},{"id":11,"createBy":null,"bannerTitle":"蒙奇·D·路飞","bannerUrl":"rexyu.com","bannerWeight":1,"createDate":1540471400000,"updateDate":null,"isShow":0,"num1":"/upload/078d3d671bb842fd923ad6531e4ef18e.jpg","num2":null,"num3":null,"num4":null,"num5":null}]
     * totalPage : null
     * totalCount : null
     */

    private String status;
    private Object errorCode;
    private Object errorTitle;
    private Object errorMsg;
    private Object totalPage;
    private Object totalCount;
    private List<DataBean> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Object errorCode) {
        this.errorCode = errorCode;
    }

    public Object getErrorTitle() {
        return errorTitle;
    }

    public void setErrorTitle(Object errorTitle) {
        this.errorTitle = errorTitle;
    }

    public Object getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(Object errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Object getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Object totalPage) {
        this.totalPage = totalPage;
    }

    public Object getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Object totalCount) {
        this.totalCount = totalCount;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 10
         * createBy : null
         * bannerTitle : 321312
         * bannerUrl : bilibili.com
         * bannerWeight : 1
         * createDate : 1540471360000
         * updateDate : null
         * isShow : 1
         * num1 : /upload/b1ce933b7a5542cebc6a9fa7edc3e9f7.jpg
         * num2 : null
         * num3 : null
         * num4 : null
         * num5 : null
         */

        private int id;
        private Object createBy;
        private String bannerTitle;
        private String bannerUrl;
        private int bannerWeight;
        private long createDate;
        private Object updateDate;
        private int isShow;
        private String num1;
        private Object num2;
        private Object num3;
        private Object num4;
        private Object num5;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Object getCreateBy() {
            return createBy;
        }

        public void setCreateBy(Object createBy) {
            this.createBy = createBy;
        }

        public String getBannerTitle() {
            return bannerTitle;
        }

        public void setBannerTitle(String bannerTitle) {
            this.bannerTitle = bannerTitle;
        }

        public String getBannerUrl() {
            return bannerUrl;
        }

        public void setBannerUrl(String bannerUrl) {
            this.bannerUrl = bannerUrl;
        }

        public int getBannerWeight() {
            return bannerWeight;
        }

        public void setBannerWeight(int bannerWeight) {
            this.bannerWeight = bannerWeight;
        }

        public long getCreateDate() {
            return createDate;
        }

        public void setCreateDate(long createDate) {
            this.createDate = createDate;
        }

        public Object getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(Object updateDate) {
            this.updateDate = updateDate;
        }

        public int getIsShow() {
            return isShow;
        }

        public void setIsShow(int isShow) {
            this.isShow = isShow;
        }

        public String getNum1() {
            return num1;
        }

        public void setNum1(String num1) {
            this.num1 = num1;
        }

        public Object getNum2() {
            return num2;
        }

        public void setNum2(Object num2) {
            this.num2 = num2;
        }

        public Object getNum3() {
            return num3;
        }

        public void setNum3(Object num3) {
            this.num3 = num3;
        }

        public Object getNum4() {
            return num4;
        }

        public void setNum4(Object num4) {
            this.num4 = num4;
        }

        public Object getNum5() {
            return num5;
        }

        public void setNum5(Object num5) {
            this.num5 = num5;
        }
    }
}
