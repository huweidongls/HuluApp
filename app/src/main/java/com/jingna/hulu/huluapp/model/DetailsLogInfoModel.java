package com.jingna.hulu.huluapp.model;

import java.util.List;

/**
 * Created by Administrator on 2018/10/16.
 */

public class DetailsLogInfoModel {

    /**
     * status : SUCCESS
     * errorCode : null
     * errorTitle : null
     * errorMsg : null
     * data : {"platformSolves":[{"id":11,"createBy":"后台_黑龙江_01","createDate":1539673370000,"updateTime":null,"lpTitle":"小垃圾","lpContent":"小垃圾","lpLm":"6","lpCoordinate":"[126.618273,45.715314]","lpUser":"43","isSolve":1,"solveContent":"诺基亚了","solveTime":1539673425000,"num1":"1","num2":"/upload/1e22e096cb524d3f858fca632d3c078f.png","num3":"/upload/6bb1ac04a65643f7894b0c2d00c26329.png","num4":"0","num5":null,"typeName":"哈尔滨测试分类","lmTitle":"10.16 test","lmContent":"[[126.619926,45.709072],[126.616764,45.721155]]","amount":null}],"events":[{"id":8,"createBy":"43","createDate":1539673404000,"updateTime":null,"eventTitle":"明年","solveBy":null,"isSolve":0,"solveContent":null,"solveTime":null,"num1":"2","num2":"[[126.632277,45.742498]]","num3":"护路_道里区","num4":null,"num5":null,"eventContent":"饿了哦可以了","eventRecordings":"/upload/2899a3d08b314007b09740b1da00f3b7.mp3","eventType":1,"eventPic":"/upload/1e717113e7e7456f9ccb49c12ebd23fc.png","assortmentType":1,"typeName":"哈尔滨测试时间分类","phonenum":"18545554088","eventId":"8","peopleName":"护路_道里区","lmTitle":"10.16 test","leaderName":"43","time":null,"lmContent":"[[126.619926,45.709072],[126.616764,45.721155]]"}],"info":[{"id":1020,"rploggerId":"22","coordinate":"[126.632277,45.742498]","createTime":1539673378000,"num1":null,"num2":null,"num3":null,"num4":null,"num5":null},{"id":1021,"rploggerId":"22","coordinate":"[126.632277,45.742498]","createTime":1539673386000,"num1":null,"num2":null,"num3":null,"num4":null,"num5":null},{"id":1022,"rploggerId":"22","coordinate":"[126.632277,45.742498]","createTime":1539673386000,"num1":null,"num2":null,"num3":null,"num4":null,"num5":null},{"id":1023,"rploggerId":"22","coordinate":"[126.632277,45.742498]","createTime":1539673398000,"num1":null,"num2":null,"num3":null,"num4":null,"num5":null},{"id":1024,"rploggerId":"22","coordinate":"[126.632277,45.742498]","createTime":1539673405000,"num1":null,"num2":null,"num3":null,"num4":null,"num5":null},{"id":1025,"rploggerId":"22","coordinate":"[126.632277,45.742498]","createTime":1539673406000,"num1":null,"num2":null,"num3":null,"num4":null,"num5":null},{"id":1026,"rploggerId":"22","coordinate":"[126.632277,45.742498]","createTime":1539673418000,"num1":null,"num2":null,"num3":null,"num4":null,"num5":null},{"id":1027,"rploggerId":"22","coordinate":"[126.632277,45.742498]","createTime":1539673425000,"num1":null,"num2":null,"num3":null,"num4":null,"num5":null},{"id":1028,"rploggerId":"22","coordinate":"[126.632277,45.742498]","createTime":1539673426000,"num1":null,"num2":null,"num3":null,"num4":null,"num5":null},{"id":1029,"rploggerId":"22","coordinate":"[126.632277,45.742498]","createTime":1539673430000,"num1":null,"num2":null,"num3":null,"num4":null,"num5":null}]}
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
        private List<PlatformSolvesBean> platformSolves;
        private List<EventsBean> events;
        private List<InfoBean> info;

        public List<PlatformSolvesBean> getPlatformSolves() {
            return platformSolves;
        }

        public void setPlatformSolves(List<PlatformSolvesBean> platformSolves) {
            this.platformSolves = platformSolves;
        }

        public List<EventsBean> getEvents() {
            return events;
        }

        public void setEvents(List<EventsBean> events) {
            this.events = events;
        }

        public List<InfoBean> getInfo() {
            return info;
        }

        public void setInfo(List<InfoBean> info) {
            this.info = info;
        }

        public static class PlatformSolvesBean {
            /**
             * id : 11
             * createBy : 后台_黑龙江_01
             * createDate : 1539673370000
             * updateTime : null
             * lpTitle : 小垃圾
             * lpContent : 小垃圾
             * lpLm : 6
             * lpCoordinate : [126.618273,45.715314]
             * lpUser : 43
             * isSolve : 1
             * solveContent : 诺基亚了
             * solveTime : 1539673425000
             * num1 : 1
             * num2 : /upload/1e22e096cb524d3f858fca632d3c078f.png
             * num3 : /upload/6bb1ac04a65643f7894b0c2d00c26329.png
             * num4 : 0
             * num5 : null
             * typeName : 哈尔滨测试分类
             * lmTitle : 10.16 test
             * lmContent : [[126.619926,45.709072],[126.616764,45.721155]]
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

        public static class EventsBean {
            /**
             * id : 8
             * createBy : 43
             * createDate : 1539673404000
             * updateTime : null
             * eventTitle : 明年
             * solveBy : null
             * isSolve : 0
             * solveContent : null
             * solveTime : null
             * num1 : 2
             * num2 : [[126.632277,45.742498]]
             * num3 : 护路_道里区
             * num4 : null
             * num5 : null
             * eventContent : 饿了哦可以了
             * eventRecordings : /upload/2899a3d08b314007b09740b1da00f3b7.mp3
             * eventType : 1
             * eventPic : /upload/1e717113e7e7456f9ccb49c12ebd23fc.png
             * assortmentType : 1
             * typeName : 哈尔滨测试时间分类
             * phonenum : 18545554088
             * eventId : 8
             * peopleName : 护路_道里区
             * lmTitle : 10.16 test
             * leaderName : 43
             * time : null
             * lmContent : [[126.619926,45.709072],[126.616764,45.721155]]
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
            private String leaderName;
            private Object time;
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

            public String getLeaderName() {
                return leaderName;
            }

            public void setLeaderName(String leaderName) {
                this.leaderName = leaderName;
            }

            public Object getTime() {
                return time;
            }

            public void setTime(Object time) {
                this.time = time;
            }

            public String getLmContent() {
                return lmContent;
            }

            public void setLmContent(String lmContent) {
                this.lmContent = lmContent;
            }
        }

        public static class InfoBean {
            /**
             * id : 1020
             * rploggerId : 22
             * coordinate : [126.632277,45.742498]
             * createTime : 1539673378000
             * num1 : null
             * num2 : null
             * num3 : null
             * num4 : null
             * num5 : null
             */

            private int id;
            private String rploggerId;
            private String coordinate;
            private long createTime;
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

            public String getRploggerId() {
                return rploggerId;
            }

            public void setRploggerId(String rploggerId) {
                this.rploggerId = rploggerId;
            }

            public String getCoordinate() {
                return coordinate;
            }

            public void setCoordinate(String coordinate) {
                this.coordinate = coordinate;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
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
    }
}
