package com.jingna.hulu.huluapp.model;

import java.util.List;

/**
 * Created by Administrator on 2018/9/16.
 */

public class MyTaskDangerEventListModel {

    /**
     * status : SUCCESS
     * errorCode : null
     * errorTitle : null
     * errorMsg : null
     * data : [{"id":15,"createBy":"垃圾","createDate":1537085906000,"updateTime":null,"lpTitle":"测试标题","lpContent":"测试数据","lpLm":"2","lpCoordinate":"[126.668362,45.745916]","lpUser":"20","isSolve":0,"solveContent":"恶魔","solveTime":1537086134000,"num1":"2","num2":"/upload/9c59e22eeda148a09b32784e723ba518.jpg,/upload/b66ceb38c0ff4af5be0cb662dfe0f89f.jpg","num3":"/upload/fffccea4309641c39b8545bfe8436c0a.jpeg,/upload/22872aecd3fb49bf9cb26fe90e5e018c.jpeg,/upload/d1df6b6b7773498b9b416c975643e669.jpeg,/upload/f027417bcec346898700a8ac8eeb67c3.jpeg","num4":null,"num5":null,"typeName":"隐患分类1","lmTitle":"666","lmContent":"[[126.664769,45.754721],[126.670087,45.749841],[126.665416,45.754218],[126.666135,45.753463],[126.667284,45.75296],[126.668147,45.752507],[126.669009,45.751954],[126.669584,45.751199]]"},{"id":16,"createBy":"垃圾","createDate":1537085931000,"updateTime":null,"lpTitle":"测试标题02","lpContent":"测试数据隐患内容2","lpLm":"2","lpCoordinate":"[126.670518,45.744759]","lpUser":"20","isSolve":0,"solveContent":"北京","solveTime":1537086222000,"num1":"2","num2":"/upload/891b3d5abb354facbc16feb7bc892848.png","num3":"/upload/e1ec09221a124e16964f3bce43899bb7.png","num4":null,"num5":null,"typeName":"隐患分类1","lmTitle":"666","lmContent":"[[126.664769,45.754721],[126.670087,45.749841],[126.665416,45.754218],[126.666135,45.753463],[126.667284,45.75296],[126.668147,45.752507],[126.669009,45.751954],[126.669584,45.751199]]"}]
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
         * id : 15
         * createBy : 垃圾
         * createDate : 1537085906000
         * updateTime : null
         * lpTitle : 测试标题
         * lpContent : 测试数据
         * lpLm : 2
         * lpCoordinate : [126.668362,45.745916]
         * lpUser : 20
         * isSolve : 0
         * solveContent : 恶魔
         * solveTime : 1537086134000
         * num1 : 2
         * num2 : /upload/9c59e22eeda148a09b32784e723ba518.jpg,/upload/b66ceb38c0ff4af5be0cb662dfe0f89f.jpg
         * num3 : /upload/fffccea4309641c39b8545bfe8436c0a.jpeg,/upload/22872aecd3fb49bf9cb26fe90e5e018c.jpeg,/upload/d1df6b6b7773498b9b416c975643e669.jpeg,/upload/f027417bcec346898700a8ac8eeb67c3.jpeg
         * num4 : null
         * num5 : null
         * typeName : 隐患分类1
         * lmTitle : 666
         * lmContent : [[126.664769,45.754721],[126.670087,45.749841],[126.665416,45.754218],[126.666135,45.753463],[126.667284,45.75296],[126.668147,45.752507],[126.669009,45.751954],[126.669584,45.751199]]
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
        private Object num4;
        private Object num5;
        private String typeName;
        private String lmTitle;
        private String lmContent;

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
    }
}
