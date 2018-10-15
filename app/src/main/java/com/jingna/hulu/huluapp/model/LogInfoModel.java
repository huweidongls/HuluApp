package com.jingna.hulu.huluapp.model;

import java.util.List;

/**
 * Created by Administrator on 2018/10/15.
 */

public class LogInfoModel {

    /**
     * status : SUCCESS
     * errorCode : null
     * errorTitle : null
     * errorMsg : null
     * data : [{"id":4,"createBy":"护路_道里区","createDate":1539151554000,"endTime":1539151627000,"eventNum":0,"solveNum":0,"lmBy":"1","num1":"2","num2":"43","num3":null,"num4":null,"num5":null,"lmTitle":"道里区1号线路","telephome":"18545554088"},{"id":5,"createBy":"护路_道里区","createDate":1539151836000,"endTime":1539151926000,"eventNum":1,"solveNum":0,"lmBy":"1","num1":"2","num2":"43","num3":null,"num4":null,"num5":null,"lmTitle":"道里区1号线路","telephome":"18545554088"},{"id":6,"createBy":"护路_道里区","createDate":1539154530000,"endTime":1539154545000,"eventNum":0,"solveNum":0,"lmBy":"1","num1":"2","num2":"43","num3":null,"num4":null,"num5":null,"lmTitle":"道里区1号线路","telephome":"18545554088"},{"id":7,"createBy":"护路_道里区","createDate":1539155320000,"endTime":1539155324000,"eventNum":0,"solveNum":0,"lmBy":"1","num1":"2","num2":"43","num3":null,"num4":null,"num5":null,"lmTitle":"道里区1号线路","telephome":"18545554088"},{"id":8,"createBy":"护路_道里区","createDate":1539161968000,"endTime":1539161972000,"eventNum":1,"solveNum":0,"lmBy":"1","num1":"2","num2":"43","num3":null,"num4":null,"num5":null,"lmTitle":"道里区1号线路","telephome":"18545554088"},{"id":9,"createBy":"护路_道里区","createDate":1539162016000,"endTime":1539162059000,"eventNum":3,"solveNum":0,"lmBy":"1","num1":"2","num2":"43","num3":null,"num4":null,"num5":null,"lmTitle":"道里区1号线路","telephome":"18545554088"},{"id":10,"createBy":"护路_道里区","createDate":1539224918000,"endTime":1539224924000,"eventNum":0,"solveNum":0,"lmBy":"1","num1":"2","num2":"43","num3":null,"num4":null,"num5":null,"lmTitle":"道里区1号线路","telephome":"18545554088"},{"id":11,"createBy":"护路_道里区","createDate":1539226470000,"endTime":1539226474000,"eventNum":0,"solveNum":0,"lmBy":"1","num1":"2","num2":"43","num3":null,"num4":null,"num5":null,"lmTitle":"道里区1号线路","telephome":"18545554088"},{"id":18,"createBy":"护路_道里区","createDate":1539246750000,"endTime":1539246799000,"eventNum":1,"solveNum":0,"lmBy":"1","num1":"2","num2":"43","num3":null,"num4":null,"num5":null,"lmTitle":"道里区1号线路","telephome":"18545554088"},{"id":19,"createBy":"护路_道里区","createDate":1539327561000,"endTime":1539331263000,"eventNum":2,"solveNum":0,"lmBy":"1","num1":"2","num2":"50","num3":null,"num4":null,"num5":null,"lmTitle":"道里区1号线路","telephome":"1111111111111"},{"id":12,"createBy":"吉林","createDate":1539228008000,"endTime":1539228016000,"eventNum":0,"solveNum":0,"lmBy":"2","num1":"2","num2":"46","num3":null,"num4":null,"num5":null,"lmTitle":"吉林线路1","telephome":"18545554088"},{"id":13,"createBy":"护路_大庆","createDate":1539228544000,"endTime":1539228606000,"eventNum":0,"solveNum":0,"lmBy":"5","num1":"2","num2":"51","num3":null,"num4":null,"num5":null,"lmTitle":"大庆线路1","telephome":"123"},{"id":14,"createBy":"护路_大庆","createDate":1539228682000,"endTime":1539228698000,"eventNum":0,"solveNum":0,"lmBy":"5","num1":"2","num2":"51","num3":null,"num4":null,"num5":null,"lmTitle":"大庆线路1","telephome":"123"},{"id":15,"createBy":"护路_大庆","createDate":1539242212000,"endTime":null,"eventNum":0,"solveNum":0,"lmBy":"5","num1":"1","num2":"51","num3":null,"num4":null,"num5":null,"lmTitle":"大庆线路1","telephome":"123"},{"id":16,"createBy":"护路_大庆","createDate":1539242294000,"endTime":1539242330000,"eventNum":0,"solveNum":0,"lmBy":"5","num1":"2","num2":"51","num3":null,"num4":null,"num5":null,"lmTitle":"大庆线路1","telephome":"123"},{"id":17,"createBy":"护路_大庆","createDate":1539245686000,"endTime":1539245714000,"eventNum":0,"solveNum":0,"lmBy":"5","num1":"2","num2":"51","num3":null,"num4":null,"num5":null,"lmTitle":"大庆线路1","telephome":"123"}]
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
         * id : 4
         * createBy : 护路_道里区
         * createDate : 1539151554000
         * endTime : 1539151627000
         * eventNum : 0
         * solveNum : 0
         * lmBy : 1
         * num1 : 2
         * num2 : 43
         * num3 : null
         * num4 : null
         * num5 : null
         * lmTitle : 道里区1号线路
         * telephome : 18545554088
         */

        private int id;
        private String createBy;
        private long createDate;
        private long endTime;
        private int eventNum;
        private int solveNum;
        private String lmBy;
        private String num1;
        private String num2;
        private Object num3;
        private Object num4;
        private Object num5;
        private String lmTitle;
        private String telephome;

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

        public long getEndTime() {
            return endTime;
        }

        public void setEndTime(long endTime) {
            this.endTime = endTime;
        }

        public int getEventNum() {
            return eventNum;
        }

        public void setEventNum(int eventNum) {
            this.eventNum = eventNum;
        }

        public int getSolveNum() {
            return solveNum;
        }

        public void setSolveNum(int solveNum) {
            this.solveNum = solveNum;
        }

        public String getLmBy() {
            return lmBy;
        }

        public void setLmBy(String lmBy) {
            this.lmBy = lmBy;
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

        public String getLmTitle() {
            return lmTitle;
        }

        public void setLmTitle(String lmTitle) {
            this.lmTitle = lmTitle;
        }

        public String getTelephome() {
            return telephome;
        }

        public void setTelephome(String telephome) {
            this.telephome = telephome;
        }
    }
}
