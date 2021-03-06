package com.jingna.hulu.huluapp.model;

import java.util.List;

/**
 * Created by Administrator on 2018/9/16.
 */

public class LineDangerModel {

    /**
     * status : SUCCESS
     * errorCode : null
     * errorTitle : null
     * errorMsg : null
     * data : [{"id":10,"createBy":"值班人","createDate":1543305344000,"updateTime":null,"lpTitle":"防护栅栏出现丢失","lpContent":"防护栅栏出现丢失，请确认是否丢失","lpLm":"4","lpCoordinate":"[126.632304,45.760298]","lpUser":"111","isSolve":1,"solveContent":"没丢，挺好的","solveTime":1543307985000,"num1":"12","num2":"/upload/9b6fdbeb49d14b8f80d4698ce94a4eb1.jpeg","num3":"/upload/05021762e3ea43f196a14f3784ea1fdf.jpeg","num4":"0","num5":null,"typeName":"防护栅栏隐患","lmTitle":"哈站-哈西站10-25KM","lmContent":"[[126.638251,45.766894],[126.637497,45.766215],[126.636598,45.764933],[126.635556,45.763826],[126.634514,45.762694],[126.633616,45.761864],[126.632394,45.760431],[126.631747,45.759425],[126.630598,45.758066],[126.629807,45.756809],[126.628478,45.755174],[126.627759,45.753765],[126.627687,45.752105],[126.627723,45.752457]]","amount":null}]
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
         * createBy : 值班人
         * createDate : 1543305344000
         * updateTime : null
         * lpTitle : 防护栅栏出现丢失
         * lpContent : 防护栅栏出现丢失，请确认是否丢失
         * lpLm : 4
         * lpCoordinate : [126.632304,45.760298]
         * lpUser : 111
         * isSolve : 1
         * solveContent : 没丢，挺好的
         * solveTime : 1543307985000
         * num1 : 12
         * num2 : /upload/9b6fdbeb49d14b8f80d4698ce94a4eb1.jpeg
         * num3 : /upload/05021762e3ea43f196a14f3784ea1fdf.jpeg
         * num4 : 0
         * num5 : null
         * typeName : 防护栅栏隐患
         * lmTitle : 哈站-哈西站10-25KM
         * lmContent : [[126.638251,45.766894],[126.637497,45.766215],[126.636598,45.764933],[126.635556,45.763826],[126.634514,45.762694],[126.633616,45.761864],[126.632394,45.760431],[126.631747,45.759425],[126.630598,45.758066],[126.629807,45.756809],[126.628478,45.755174],[126.627759,45.753765],[126.627687,45.752105],[126.627723,45.752457]]
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
        private long solveTime;
        private String num1;
        private String num2;
        private String num3;
        private String num4;
        private Object num5;
        private String typeName;
        private String lmTitle;
        private String lmContent;
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

        public long getSolveTime() {
            return solveTime;
        }

        public void setSolveTime(long solveTime) {
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

        public String getLmTitle() {
            return lmTitle;
        }

        public void setLmTitle(String lmTitle) {
            this.lmTitle = lmTitle;
        }

        public String getLmContent() {
            return lmContent;
        }

        public void setLmContent(String lmContent) {
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
