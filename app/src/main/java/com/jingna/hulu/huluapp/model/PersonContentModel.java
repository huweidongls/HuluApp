package com.jingna.hulu.huluapp.model;

/**
 * Created by Administrator on 2018/9/29.
 */

public class PersonContentModel {

    /**
     * status : SUCCESS
     * errorCode : null
     * errorTitle : null
     * errorMsg : null
     * data : {"id":43,"userName":"jingna01","password":"1","userPic":"/upload/161203677b5748c69c0c0514d22360af.jpg","peopleName":"测试护路员01","isDelete":0,"joinTime":1537315200000,"phonenum":null,"address":"哈尔滨","sex":1,"lastTime":null,"createDate":null,"updateDate":1538115705000,"loginStutas":1,"createBy":"无敌管理员","userAge":"16","status":1,"num1":null,"num2":null,"num3":null,"num4":null,"num5":"18545554088","roleName":"APP护路员","roleId":44}
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
         * id : 43
         * userName : jingna01
         * password : 1
         * userPic : /upload/161203677b5748c69c0c0514d22360af.jpg
         * peopleName : 测试护路员01
         * isDelete : 0
         * joinTime : 1537315200000
         * phonenum : null
         * address : 哈尔滨
         * sex : 1
         * lastTime : null
         * createDate : null
         * updateDate : 1538115705000
         * loginStutas : 1
         * createBy : 无敌管理员
         * userAge : 16
         * status : 1
         * num1 : null
         * num2 : null
         * num3 : null
         * num4 : null
         * num5 : 18545554088
         * roleName : APP护路员
         * roleId : 44
         */

        private int id;
        private String userName;
        private String password;
        private String userPic;
        private String peopleName;
        private int isDelete;
        private long joinTime;
        private Object phonenum;
        private String address;
        private int sex;
        private Object lastTime;
        private Object createDate;
        private long updateDate;
        private int loginStutas;
        private String createBy;
        private String userAge;
        private int status;
        private Object num1;
        private Object num2;
        private Object num3;
        private Object num4;
        private String num5;
        private String roleName;
        private int roleId;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getUserPic() {
            return userPic;
        }

        public void setUserPic(String userPic) {
            this.userPic = userPic;
        }

        public String getPeopleName() {
            return peopleName;
        }

        public void setPeopleName(String peopleName) {
            this.peopleName = peopleName;
        }

        public int getIsDelete() {
            return isDelete;
        }

        public void setIsDelete(int isDelete) {
            this.isDelete = isDelete;
        }

        public long getJoinTime() {
            return joinTime;
        }

        public void setJoinTime(long joinTime) {
            this.joinTime = joinTime;
        }

        public Object getPhonenum() {
            return phonenum;
        }

        public void setPhonenum(Object phonenum) {
            this.phonenum = phonenum;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public Object getLastTime() {
            return lastTime;
        }

        public void setLastTime(Object lastTime) {
            this.lastTime = lastTime;
        }

        public Object getCreateDate() {
            return createDate;
        }

        public void setCreateDate(Object createDate) {
            this.createDate = createDate;
        }

        public long getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(long updateDate) {
            this.updateDate = updateDate;
        }

        public int getLoginStutas() {
            return loginStutas;
        }

        public void setLoginStutas(int loginStutas) {
            this.loginStutas = loginStutas;
        }

        public String getCreateBy() {
            return createBy;
        }

        public void setCreateBy(String createBy) {
            this.createBy = createBy;
        }

        public String getUserAge() {
            return userAge;
        }

        public void setUserAge(String userAge) {
            this.userAge = userAge;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
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

        public String getNum5() {
            return num5;
        }

        public void setNum5(String num5) {
            this.num5 = num5;
        }

        public String getRoleName() {
            return roleName;
        }

        public void setRoleName(String roleName) {
            this.roleName = roleName;
        }

        public int getRoleId() {
            return roleId;
        }

        public void setRoleId(int roleId) {
            this.roleId = roleId;
        }
    }
}
