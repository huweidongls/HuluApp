package com.jingna.hulu.huluapp.model;

import java.util.List;

/**
 * Created by Administrator on 2018/9/15.
 */

public class TelModel {

    /**
     * status : SUCCESS
     * errorCode : null
     * errorTitle : null
     * errorMsg : null
     * data : [{"id":1,"createBy":"rexyu","createDate":1536308456000,"updateDate":1536550280000,"telType":1,"telDept":"01","telName":"01","telNumber":"18545554087","num1":"string","num2":"string","num3":"string","num4":"string","num5":"string"},{"id":2,"createBy":"rexyu","createDate":1536308456000,"updateDate":1536549807000,"telType":2,"telDept":"02","telName":"02","telNumber":"18545554088","num1":"string","num2":"string","num3":"string","num4":"string","num5":"string"},{"id":3,"createBy":"rexyu","createDate":1536308456000,"updateDate":1536550651000,"telType":3,"telDept":"03","telName":"03","telNumber":"18545554089","num1":"string","num2":"string","num3":"string","num4":"string","num5":"string"}]
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
         * createBy : rexyu
         * createDate : 1536308456000
         * updateDate : 1536550280000
         * telType : 1
         * telDept : 01
         * telName : 01
         * telNumber : 18545554087
         * num1 : string
         * num2 : string
         * num3 : string
         * num4 : string
         * num5 : string
         */

        private int id;
        private String createBy;
        private long createDate;
        private long updateDate;
        private int telType;
        private String telDept;
        private String telName;
        private String telNumber;
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

        public int getTelType() {
            return telType;
        }

        public void setTelType(int telType) {
            this.telType = telType;
        }

        public String getTelDept() {
            return telDept;
        }

        public void setTelDept(String telDept) {
            this.telDept = telDept;
        }

        public String getTelName() {
            return telName;
        }

        public void setTelName(String telName) {
            this.telName = telName;
        }

        public String getTelNumber() {
            return telNumber;
        }

        public void setTelNumber(String telNumber) {
            this.telNumber = telNumber;
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
