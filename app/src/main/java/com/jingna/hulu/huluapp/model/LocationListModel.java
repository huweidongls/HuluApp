package com.jingna.hulu.huluapp.model;

import java.util.List;

/**
 * Created by Administrator on 2018/9/17.
 */

public class LocationListModel {

    /**
     * status : SUCCESS
     * errorCode : null
     * errorTitle : null
     * errorMsg : null
     * data : {"platformSolves":[],"events":[],"info":[{"id":27,"rploggerId":"20","coordinate":"[126.632205,45.742451]","createTime":1537159035000,"num1":null,"num2":null,"num3":null,"num4":null,"num5":null},{"id":28,"rploggerId":"20","coordinate":"[126.632204,45.742451]","createTime":1537159043000,"num1":null,"num2":null,"num3":null,"num4":null,"num5":null},{"id":29,"rploggerId":"20","coordinate":"[126.632204,45.742451]","createTime":1537159051000,"num1":null,"num2":null,"num3":null,"num4":null,"num5":null},{"id":30,"rploggerId":"20","coordinate":"[126.632204,45.742451]","createTime":1537159059000,"num1":null,"num2":null,"num3":null,"num4":null,"num5":null},{"id":31,"rploggerId":"20","coordinate":"[126.632205,45.742451]","createTime":1537159067000,"num1":null,"num2":null,"num3":null,"num4":null,"num5":null},{"id":32,"rploggerId":"20","coordinate":"126.632205,45.742451","createTime":1537159069000,"num1":null,"num2":null,"num3":null,"num4":null,"num5":null}]}
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
        private List<?> platformSolves;
        private List<?> events;
        private List<InfoBean> info;

        public List<?> getPlatformSolves() {
            return platformSolves;
        }

        public void setPlatformSolves(List<?> platformSolves) {
            this.platformSolves = platformSolves;
        }

        public List<?> getEvents() {
            return events;
        }

        public void setEvents(List<?> events) {
            this.events = events;
        }

        public List<InfoBean> getInfo() {
            return info;
        }

        public void setInfo(List<InfoBean> info) {
            this.info = info;
        }

        public static class InfoBean {
            /**
             * id : 27
             * rploggerId : 20
             * coordinate : [126.632205,45.742451]
             * createTime : 1537159035000
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
