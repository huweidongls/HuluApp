package com.jingna.hulu.huluapp.model;

import java.util.List;

/**
 * Created by Administrator on 2018/9/15.
 */

public class NewsModel {

    /**
     * status : SUCCESS
     * errorCode : null
     * errorTitle : null
     * errorMsg : null
     * data : [{"id":5,"createBy":"1235","newsTitle":"1235","newsPic":"1235","newsContent":"1235","createDate":1535699676000,"updateDate":1537002557000,"newsWeight":1,"newsAuthor":"rexyu","subtitle":"string","num1":"string","num2":"string","num3":"string","num4":"string","num5":"string"},{"id":6,"createBy":"1236","newsTitle":"1236","newsPic":"1236","newsContent":"1236","createDate":1535699676000,"updateDate":1535699285000,"newsWeight":0,"newsAuthor":"string","subtitle":"string","num1":"string","num2":"string","num3":"string","num4":"string","num5":"string"},{"id":7,"createBy":"1237","newsTitle":"1237","newsPic":"1237","newsContent":"1237","createDate":1535699676000,"updateDate":1535699285000,"newsWeight":0,"newsAuthor":"string","subtitle":"string","num1":"string","num2":"string","num3":"string","num4":"string","num5":"string"},{"id":8,"createBy":"1238","newsTitle":"1238","newsPic":"1238","newsContent":"1238","createDate":1535699676000,"updateDate":1535699285000,"newsWeight":0,"newsAuthor":"string","subtitle":"string","num1":"string","num2":"string","num3":"string","num4":"string","num5":"string"}]
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
         * id : 5
         * createBy : 1235
         * newsTitle : 1235
         * newsPic : 1235
         * newsContent : 1235
         * createDate : 1535699676000
         * updateDate : 1537002557000
         * newsWeight : 1
         * newsAuthor : rexyu
         * subtitle : string
         * num1 : string
         * num2 : string
         * num3 : string
         * num4 : string
         * num5 : string
         */

        private int id;
        private String createBy;
        private String newsTitle;
        private String newsPic;
        private String newsContent;
        private long createDate;
        private long updateDate;
        private int newsWeight;
        private String newsAuthor;
        private String subtitle;
        private String num1;
        private String num2;
        private String num3;
        private String num4;
        private String num5;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCreateBy() {
            return createBy;
        }

        public void setCreateBy(String createBy) {
            this.createBy = createBy;
        }

        public String getNewsTitle() {
            return newsTitle;
        }

        public void setNewsTitle(String newsTitle) {
            this.newsTitle = newsTitle;
        }

        public String getNewsPic() {
            return newsPic;
        }

        public void setNewsPic(String newsPic) {
            this.newsPic = newsPic;
        }

        public String getNewsContent() {
            return newsContent;
        }

        public void setNewsContent(String newsContent) {
            this.newsContent = newsContent;
        }

        public long getCreateDate() {
            return createDate;
        }

        public void setCreateDate(long createDate) {
            this.createDate = createDate;
        }

        public long getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(long updateDate) {
            this.updateDate = updateDate;
        }

        public int getNewsWeight() {
            return newsWeight;
        }

        public void setNewsWeight(int newsWeight) {
            this.newsWeight = newsWeight;
        }

        public String getNewsAuthor() {
            return newsAuthor;
        }

        public void setNewsAuthor(String newsAuthor) {
            this.newsAuthor = newsAuthor;
        }

        public String getSubtitle() {
            return subtitle;
        }

        public void setSubtitle(String subtitle) {
            this.subtitle = subtitle;
        }

        public String getNum1() {
            return num1;
        }

        public void setNum1(String num1) {
            this.num1 = num1;
        }

        public String getNum2() {
            return num2;
        }

        public void setNum2(String num2) {
            this.num2 = num2;
        }

        public String getNum3() {
            return num3;
        }

        public void setNum3(String num3) {
            this.num3 = num3;
        }

        public String getNum4() {
            return num4;
        }

        public void setNum4(String num4) {
            this.num4 = num4;
        }

        public String getNum5() {
            return num5;
        }

        public void setNum5(String num5) {
            this.num5 = num5;
        }
    }
}
