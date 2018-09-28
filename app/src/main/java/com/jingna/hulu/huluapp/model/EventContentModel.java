package com.jingna.hulu.huluapp.model;

import java.util.List;

/**
 * Created by Administrator on 2018/9/27.
 */

public class EventContentModel {

    /**
     * status : SUCCESS
     * errorCode : null
     * errorTitle : null
     * errorMsg : null
     * data : [{"id":13,"createBy":"43","createDate":1538122084000,"updateTime":null,"eventTitle":"订","solveBy":null,"isSolve":0,"solveContent":null,"solveTime":null,"num1":"6","num2":"[[126.63221,45.742475]]","num3":"测试护路员01","num4":null,"num5":null,"eventContent":"特勒","eventRecordings":"/upload/c8ce9956490d4c9d95e0e64f7fe248da.mp3","eventType":1,"eventPic":"/upload/c57f022de05644a1b756964da82670bf.jpeg","assortmentType":1,"typeName":"事件分类2","phonenum":"18545554088","eventId":"13","peopleName":"测试护路员01","lmTitle":"911"}]
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
         * id : 13
         * createBy : 43
         * createDate : 1538122084000
         * updateTime : null
         * eventTitle : 订
         * solveBy : null
         * isSolve : 0
         * solveContent : null
         * solveTime : null
         * num1 : 6
         * num2 : [[126.63221,45.742475]]
         * num3 : 测试护路员01
         * num4 : null
         * num5 : null
         * eventContent : 特勒
         * eventRecordings : /upload/c8ce9956490d4c9d95e0e64f7fe248da.mp3
         * eventType : 1
         * eventPic : /upload/c57f022de05644a1b756964da82670bf.jpeg
         * assortmentType : 1
         * typeName : 事件分类2
         * phonenum : 18545554088
         * eventId : 13
         * peopleName : 测试护路员01
         * lmTitle : 911
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
        private String eventRecordings;
        private int eventType;
        private String eventPic;
        private int assortmentType;
        private String typeName;
        private String phonenum;
        private String eventId;
        private String peopleName;
        private String lmTitle;

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

        public String getEventRecordings() {
            return eventRecordings;
        }

        public void setEventRecordings(String eventRecordings) {
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

        public String getLmTitle() {
            return lmTitle;
        }

        public void setLmTitle(String lmTitle) {
            this.lmTitle = lmTitle;
        }
    }
}
