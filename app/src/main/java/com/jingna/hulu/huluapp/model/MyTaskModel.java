package com.jingna.hulu.huluapp.model;

import java.util.List;

/**
 * Created by Administrator on 2018/9/29.
 */

public class MyTaskModel {

    /**
     * status : SUCCESS
     * errorCode : null
     * errorTitle : null
     * errorMsg : null
     * data : {"platformLineManages":[{"id":21,"createBy":"rexyu","createDate":1537246082000,"updateTime":1538104642000,"lmTitle":"wht线路","lmContent":"[[125.928015,46.116208],[125.871673,46.139394],[125.806133,46.165766],[125.75784,46.177748]]","userBy":"22","num1":null,"num2":null,"num3":null,"num4":null,"num5":null,"peopleName":null}],"roadprotectionLoggers":[{"id":null,"rploggerId":null,"coordinate":"[126.630684,45.742531]","createTime":null,"num1":null,"num2":null,"num3":null,"num4":null,"num5":null},{"id":null,"rploggerId":null,"coordinate":"[126.630684,45.742531]","createTime":null,"num1":null,"num2":null,"num3":null,"num4":null,"num5":null},{"id":null,"rploggerId":null,"coordinate":"[126.630684,45.742531]","createTime":null,"num1":null,"num2":null,"num3":null,"num4":null,"num5":null},{"id":null,"rploggerId":null,"coordinate":"[126.630684,45.742531]","createTime":null,"num1":null,"num2":null,"num3":null,"num4":null,"num5":null},{"id":null,"rploggerId":null,"coordinate":"[126.630684,45.742531]","createTime":null,"num1":null,"num2":null,"num3":null,"num4":null,"num5":null},{"id":null,"rploggerId":null,"coordinate":"[126.630684,45.742531]","createTime":null,"num1":null,"num2":null,"num3":null,"num4":null,"num5":null},{"id":null,"rploggerId":null,"coordinate":"[126.630684,45.742531]","createTime":null,"num1":null,"num2":null,"num3":null,"num4":null,"num5":null},{"id":null,"rploggerId":null,"coordinate":"[126.630684,45.742531]","createTime":null,"num1":null,"num2":null,"num3":null,"num4":null,"num5":null},{"id":null,"rploggerId":null,"coordinate":"[126.630684,45.742531]","createTime":null,"num1":null,"num2":null,"num3":null,"num4":null,"num5":null},{"id":null,"rploggerId":null,"coordinate":"[126.630684,45.742531]","createTime":null,"num1":null,"num2":null,"num3":null,"num4":null,"num5":null},{"id":null,"rploggerId":null,"coordinate":"[126.630684,45.742531]","createTime":null,"num1":null,"num2":null,"num3":null,"num4":null,"num5":null},{"id":null,"rploggerId":null,"coordinate":"[126.630684,45.742531]","createTime":null,"num1":null,"num2":null,"num3":null,"num4":null,"num5":null},{"id":null,"rploggerId":null,"coordinate":"[126.630983,45.74231]","createTime":null,"num1":null,"num2":null,"num3":null,"num4":null,"num5":null},{"id":null,"rploggerId":null,"coordinate":"[126.630983,45.74231]","createTime":null,"num1":null,"num2":null,"num3":null,"num4":null,"num5":null},{"id":null,"rploggerId":null,"coordinate":"[126.630983,45.74231]","createTime":null,"num1":null,"num2":null,"num3":null,"num4":null,"num5":null},{"id":null,"rploggerId":null,"coordinate":"线路坐标11","createTime":null,"num1":null,"num2":null,"num3":null,"num4":null,"num5":null},{"id":null,"rploggerId":null,"coordinate":"结束坐标点","createTime":null,"num1":null,"num2":null,"num3":null,"num4":null,"num5":null}],"platformSolves":[{"id":26,"createBy":"测试02","createDate":1537241166000,"updateTime":1537243109000,"lpTitle":"测试隐患1","lpContent":"测试隐患内容1","lpLm":"18","lpCoordinate":"[126.631855,45.735349]","lpUser":"22","isSolve":1,"solveContent":"测试隐患1已处置","solveTime":1537255994000,"num1":"7","num2":"/upload/f9ecc06af9af4a249561203028394c83.jpg","num3":"/upload/d1e3848318bb4d8ebc6851cd65999481.jpeg","num4":"0","num5":null,"typeName":"隐患分类1","lmTitle":null,"lmContent":null},{"id":27,"createBy":"测试02","createDate":1537243185000,"updateTime":null,"lpTitle":"测试隐患2","lpContent":"测试隐患内容2","lpLm":"18","lpCoordinate":"[126.624453,45.738318]","lpUser":"22","isSolve":0,"solveContent":null,"solveTime":null,"num1":"8","num2":null,"num3":null,"num4":"0","num5":null,"typeName":null,"lmTitle":null,"lmContent":null}]}
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
        private List<PlatformLineManagesBean> platformLineManages;
        private List<RoadprotectionLoggersBean> roadprotectionLoggers;
        private List<PlatformSolvesBean> platformSolves;

        public List<PlatformLineManagesBean> getPlatformLineManages() {
            return platformLineManages;
        }

        public void setPlatformLineManages(List<PlatformLineManagesBean> platformLineManages) {
            this.platformLineManages = platformLineManages;
        }

        public List<RoadprotectionLoggersBean> getRoadprotectionLoggers() {
            return roadprotectionLoggers;
        }

        public void setRoadprotectionLoggers(List<RoadprotectionLoggersBean> roadprotectionLoggers) {
            this.roadprotectionLoggers = roadprotectionLoggers;
        }

        public List<PlatformSolvesBean> getPlatformSolves() {
            return platformSolves;
        }

        public void setPlatformSolves(List<PlatformSolvesBean> platformSolves) {
            this.platformSolves = platformSolves;
        }

        public static class PlatformLineManagesBean {
            /**
             * id : 21
             * createBy : rexyu
             * createDate : 1537246082000
             * updateTime : 1538104642000
             * lmTitle : wht线路
             * lmContent : [[125.928015,46.116208],[125.871673,46.139394],[125.806133,46.165766],[125.75784,46.177748]]
             * userBy : 22
             * num1 : null
             * num2 : null
             * num3 : null
             * num4 : null
             * num5 : null
             * peopleName : null
             */

            private int id;
            private String createBy;
            private long createDate;
            private long updateTime;
            private String lmTitle;
            private String lmContent;
            private String userBy;
            private Object num1;
            private Object num2;
            private Object num3;
            private Object num4;
            private Object num5;
            private Object peopleName;

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

            public long getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(long updateTime) {
                this.updateTime = updateTime;
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

            public String getUserBy() {
                return userBy;
            }

            public void setUserBy(String userBy) {
                this.userBy = userBy;
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

            public Object getPeopleName() {
                return peopleName;
            }

            public void setPeopleName(Object peopleName) {
                this.peopleName = peopleName;
            }
        }

        public static class RoadprotectionLoggersBean {
            /**
             * id : null
             * rploggerId : null
             * coordinate : [126.630684,45.742531]
             * createTime : null
             * num1 : null
             * num2 : null
             * num3 : null
             * num4 : null
             * num5 : null
             */

            private Object id;
            private Object rploggerId;
            private String coordinate;
            private Object createTime;
            private Object num1;
            private Object num2;
            private Object num3;
            private Object num4;
            private Object num5;

            public Object getId() {
                return id;
            }

            public void setId(Object id) {
                this.id = id;
            }

            public Object getRploggerId() {
                return rploggerId;
            }

            public void setRploggerId(Object rploggerId) {
                this.rploggerId = rploggerId;
            }

            public String getCoordinate() {
                return coordinate;
            }

            public void setCoordinate(String coordinate) {
                this.coordinate = coordinate;
            }

            public Object getCreateTime() {
                return createTime;
            }

            public void setCreateTime(Object createTime) {
                this.createTime = createTime;
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

        public static class PlatformSolvesBean {
            /**
             * id : 26
             * createBy : 测试02
             * createDate : 1537241166000
             * updateTime : 1537243109000
             * lpTitle : 测试隐患1
             * lpContent : 测试隐患内容1
             * lpLm : 18
             * lpCoordinate : [126.631855,45.735349]
             * lpUser : 22
             * isSolve : 1
             * solveContent : 测试隐患1已处置
             * solveTime : 1537255994000
             * num1 : 7
             * num2 : /upload/f9ecc06af9af4a249561203028394c83.jpg
             * num3 : /upload/d1e3848318bb4d8ebc6851cd65999481.jpeg
             * num4 : 0
             * num5 : null
             * typeName : 隐患分类1
             * lmTitle : null
             * lmContent : null
             */

            private int id;
            private String createBy;
            private long createDate;
            private long updateTime;
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
            private Object lmTitle;
            private Object lmContent;

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

            public long getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(long updateTime) {
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
        }
    }
}
