package com.jingna.hulu.huluapp.model;

import java.util.List;

/**
 * Created by Administrator on 2018/9/15.
 */

public class QueryListModel {

    /**
     * status : SUCCESS
     * errorCode : null
     * errorTitle : null
     * errorMsg : null
     * data : [{"id":1,"assortmentType":1,"typeName":"事件分类1","createDate":1536570775000,"createBy":"rexyu","num1":null,"num2":null,"num3":null,"num4":null,"num5":null},{"id":3,"assortmentType":1,"typeName":"小垃圾","createDate":1536724490000,"createBy":null,"num1":null,"num2":null,"num3":null,"num4":null,"num5":null},{"id":4,"assortmentType":1,"typeName":"????2","createDate":1536737083000,"createBy":"rexyu","num1":null,"num2":null,"num3":null,"num4":null,"num5":null}]
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
         * assortmentType : 1
         * typeName : 事件分类1
         * createDate : 1536570775000
         * createBy : rexyu
         * num1 : null
         * num2 : null
         * num3 : null
         * num4 : null
         * num5 : null
         */

        private int id;
        private int assortmentType;
        private String typeName;
        private long createDate;
        private String createBy;
        private Object num1;
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

        public long getCreateDate() {
            return createDate;
        }

        public void setCreateDate(long createDate) {
            this.createDate = createDate;
        }

        public String getCreateBy() {
            return createBy;
        }

        public void setCreateBy(String createBy) {
            this.createBy = createBy;
        }

        public Object getNum1() {
            return num1;
        }

        public void setNum1(Object num1) {
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
