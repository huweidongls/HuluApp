package com.jingna.hulu.huluapp.model;

import java.util.List;

/**
 * Created by Administrator on 2018/10/12.
 */

public class BumenSonModel {

    /**
     * status : SUCCESS
     * errorCode : null
     * errorTitle : null
     * errorMsg : null
     * data : [{"id":11,"departmentName":"四平护路办/二级","pid":"10","createDate":1538219645000,"updateTime":1538960702000,"isDelete":"0","num1":null,"num2":null,"num3":null,"num4":null,"num5":null,"pdepartmentName":null}]
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
         * id : 11
         * departmentName : 四平护路办/二级
         * pid : 10
         * createDate : 1538219645000
         * updateTime : 1538960702000
         * isDelete : 0
         * num1 : null
         * num2 : null
         * num3 : null
         * num4 : null
         * num5 : null
         * pdepartmentName : null
         */

        private int id;
        private String departmentName;
        private String pid;
        private long createDate;
        private long updateTime;
        private String isDelete;
        private Object num1;
        private Object num2;
        private Object num3;
        private Object num4;
        private Object num5;
        private Object pdepartmentName;

        public DataBean(int id, String departmentName) {
            this.id = id;
            this.departmentName = departmentName;
            this.pid = "10";
            this.createDate = 15382196;
            this.updateTime = 153821000;
            this.isDelete = "0";
            this.num1 = null;
            this.num2 = null;
            this.num3 = null;
            this.num4 = null;
            this.num5 = null;
            this.pdepartmentName = null;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getDepartmentName() {
            return departmentName;
        }

        public void setDepartmentName(String departmentName) {
            this.departmentName = departmentName;
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public long getCreateDate() {
            return createDate;
        }

        public void setCreateDate(long createDate) {
            this.createDate = createDate;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public String getIsDelete() {
            return isDelete;
        }

        public void setIsDelete(String isDelete) {
            this.isDelete = isDelete;
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

        public Object getPdepartmentName() {
            return pdepartmentName;
        }

        public void setPdepartmentName(Object pdepartmentName) {
            this.pdepartmentName = pdepartmentName;
        }
    }
}
