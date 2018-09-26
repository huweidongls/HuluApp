package com.jingna.hulu.huluapp.model;

import java.util.List;

/**
 * Created by Administrator on 2018/9/26.
 */

public class EventListModel {

    /**
     * status : SUCCESS
     * errorCode : null
     * errorTitle : null
     * errorMsg : null
     * data : [{"id":1,"createBy":"22","createDate":1537247704000,"updateTime":null,"eventTitle":"测试事件1","solveBy":null,"isSolve":0,"solveContent":null,"solveTime":null,"num1":"5","num2":"[[126.631228,45.742087]]","num3":"于忠","num4":null,"num5":null,"eventContent":"测试事件内容1","eventRecordings":null,"eventType":1,"eventPic":"/upload/fa18ed77c7b44e59807bd9fa9d061081.jpeg","assortmentType":1,"typeName":"事件分类1","phonenum":"13936129596","eventId":"1","peopleName":"于忠"},{"id":2,"createBy":"38","createDate":1537268619000,"updateTime":null,"eventTitle":"测试918","solveBy":null,"isSolve":0,"solveContent":null,"solveTime":null,"num1":"5","num2":"[[126.633103,45.746534]]","num3":"dvd","num4":null,"num5":null,"eventContent":"918测试","eventRecordings":null,"eventType":1,"eventPic":"/upload/9e3affa597dc47789f7f5f3d7d17baf9.jpeg","assortmentType":1,"typeName":"事件分类1","phonenum":"","eventId":"2","peopleName":"dvd"},{"id":3,"createBy":"37","createDate":1537268828000,"updateTime":null,"eventTitle":"事件1907","solveBy":null,"isSolve":0,"solveContent":null,"solveTime":null,"num1":"5","num2":"[[126.632241,45.742321]]","num3":"齐留柱","num4":null,"num5":null,"eventContent":"哈哈唉","eventRecordings":null,"eventType":1,"eventPic":"/upload/935e23875e81461ba8df8b4496d195e7.jpeg,/upload/7e5f9fa9e695484fb2d72d8438d584eb.jpeg","assortmentType":1,"typeName":"事件分类1","phonenum":"17164565456","eventId":"3","peopleName":"齐留柱"}]
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
         * id : 1
         * createBy : 22
         * createDate : 1537247704000
         * updateTime : null
         * eventTitle : 测试事件1
         * solveBy : null
         * isSolve : 0
         * solveContent : null
         * solveTime : null
         * num1 : 5
         * num2 : [[126.631228,45.742087]]
         * num3 : 于忠
         * num4 : null
         * num5 : null
         * eventContent : 测试事件内容1
         * eventRecordings : null
         * eventType : 1
         * eventPic : /upload/fa18ed77c7b44e59807bd9fa9d061081.jpeg
         * assortmentType : 1
         * typeName : 事件分类1
         * phonenum : 13936129596
         * eventId : 1
         * peopleName : 于忠
         */

        private int id;
        private String createBy;
        private long createDate;
        private Object updateTime;
        private String eventTitle;
        private Object solveBy;
        private int isSolve;
        private Object solveContent;
        private Object solveTime;
        private String num1;
        private String num2;
        private String num3;
        private Object num4;
        private Object num5;
        private String eventContent;
        private Object eventRecordings;
        private int eventType;
        private String eventPic;
        private int assortmentType;
        private String typeName;
        private String phonenum;
        private String eventId;
        private String peopleName;

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

        public long getCreateDate() {
            return createDate;
        }

        public void setCreateDate(long createDate) {
            this.createDate = createDate;
        }

        public Object getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Object updateTime) {
            this.updateTime = updateTime;
        }

        public String getEventTitle() {
            return eventTitle;
        }

        public void setEventTitle(String eventTitle) {
            this.eventTitle = eventTitle;
        }

        public Object getSolveBy() {
            return solveBy;
        }

        public void setSolveBy(Object solveBy) {
            this.solveBy = solveBy;
        }

        public int getIsSolve() {
            return isSolve;
        }

        public void setIsSolve(int isSolve) {
            this.isSolve = isSolve;
        }

        public Object getSolveContent() {
            return solveContent;
        }

        public void setSolveContent(Object solveContent) {
            this.solveContent = solveContent;
        }

        public Object getSolveTime() {
            return solveTime;
        }

        public void setSolveTime(Object solveTime) {
            this.solveTime = solveTime;
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

        public String getEventContent() {
            return eventContent;
        }

        public void setEventContent(String eventContent) {
            this.eventContent = eventContent;
        }

        public Object getEventRecordings() {
            return eventRecordings;
        }

        public void setEventRecordings(Object eventRecordings) {
            this.eventRecordings = eventRecordings;
        }

        public int getEventType() {
            return eventType;
        }

        public void setEventType(int eventType) {
            this.eventType = eventType;
        }

        public String getEventPic() {
            return eventPic;
        }

        public void setEventPic(String eventPic) {
            this.eventPic = eventPic;
        }

        public int getAssortmentType() {
            return assortmentType;
        }

        public void setAssortmentType(int assortmentType) {
            this.assortmentType = assortmentType;
        }

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

        public String getPhonenum() {
            return phonenum;
        }

        public void setPhonenum(String phonenum) {
            this.phonenum = phonenum;
        }

        public String getEventId() {
            return eventId;
        }

        public void setEventId(String eventId) {
            this.eventId = eventId;
        }

        public String getPeopleName() {
            return peopleName;
        }

        public void setPeopleName(String peopleName) {
            this.peopleName = peopleName;
        }
    }
}
