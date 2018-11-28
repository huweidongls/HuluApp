package com.jingna.hulu.huluapp.model;

/**
 * Created by Administrator on 2018/11/28.
 */

public class DetailsDangerModel {

    /**
     * status : SUCCESS
     * errorCode : null
     * errorTitle : null
     * errorMsg : null
     * data : {"id":11,"createBy":"后台_黑龙江_01","createDate":1543372870000,"updateTime":null,"lpTitle":"test","lpContent":"01","lpLm":"2","lpCoordinate":"[126.63198,45.743219]","lpUser":"null","isSolve":0,"solveContent":null,"solveTime":null,"num1":"13","num2":null,"num3":null,"num4":"0","num5":null,"typeName":null,"lmTitle":null,"lmContent":null,"amount":null}
     * totalPage : null
     * totalCount : null
     */

    private String status;
    private Object errorCode;
    private Object errorTitle;
    private Object errorMsg;
    private DataBean data;
    private Object totalPage;
    private Object totalCount;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
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

    public static class DataBean {
        /**
         * id : 11
         * createBy : 后台_黑龙江_01
         * createDate : 1543372870000
         * updateTime : null
         * lpTitle : test
         * lpContent : 01
         * lpLm : 2
         * lpCoordinate : [126.63198,45.743219]
         * lpUser : null
         * isSolve : 0
         * solveContent : null
         * solveTime : null
         * num1 : 13
         * num2 : null
         * num3 : null
         * num4 : 0
         * num5 : null
         * typeName : null
         * lmTitle : null
         * lmContent : null
         * amount : null
         */

        private int id;
        private String createBy;
        private long createDate;
        private Object updateTime;
        private String lpTitle;
        private String lpContent;
        private String lpLm;
        private String lpCoordinate;
        private String lpUser;
        private int isSolve;
        private String solveContent;
        private Object solveTime;
        private String num1;
        private String num2;
        private String num3;
        private String num4;
        private Object num5;
        private String typeName;
        private Object lmTitle;
        private Object lmContent;
        private Object amount;

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

        public String getLpTitle() {
            return lpTitle;
        }

        public void setLpTitle(String lpTitle) {
            this.lpTitle = lpTitle;
        }

        public String getLpContent() {
            return lpContent;
        }

        public void setLpContent(String lpContent) {
            this.lpContent = lpContent;
        }

        public String getLpLm() {
            return lpLm;
        }

        public void setLpLm(String lpLm) {
            this.lpLm = lpLm;
        }

        public String getLpCoordinate() {
            return lpCoordinate;
        }

        public void setLpCoordinate(String lpCoordinate) {
            this.lpCoordinate = lpCoordinate;
        }

        public String getLpUser() {
            return lpUser;
        }

        public void setLpUser(String lpUser) {
            this.lpUser = lpUser;
        }

        public int getIsSolve() {
            return isSolve;
        }

        public void setIsSolve(int isSolve) {
            this.isSolve = isSolve;
        }

        public String getSolveContent() {
            return solveContent;
        }

        public void setSolveContent(String solveContent) {
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

        public String getNum4() {
            return num4;
        }

        public void setNum4(String num4) {
            this.num4 = num4;
        }

        public Object getNum5() {
            return num5;
        }

        public void setNum5(Object num5) {
            this.num5 = num5;
        }

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

        public Object getLmTitle() {
            return lmTitle;
        }

        public void setLmTitle(Object lmTitle) {
            this.lmTitle = lmTitle;
        }

        public Object getLmContent() {
            return lmContent;
        }

        public void setLmContent(Object lmContent) {
            this.lmContent = lmContent;
        }

        public Object getAmount() {
            return amount;
        }

        public void setAmount(Object amount) {
            this.amount = amount;
        }
    }
}
