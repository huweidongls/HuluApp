package com.jingna.hulu.huluapp.model;

import java.util.List;

/**
 * Created by Administrator on 2018/9/15.
 */

public class GetOneModel {

    /**
     * status : SUCCESS
     * errorCode : null
     * errorTitle : null
     * errorMsg : null
     * data : [[{"id":36,"roleName":"测试领导","remark":"123456","num1":null,"num2":null,"num3":null,"num4":null,"num5":null,"jurisdictionName":"系统管理","jurisdictionId":1,"jurisdictionCode":"system","parentId":0},{"id":36,"roleName":"测试领导","remark":"123456","num1":null,"num2":null,"num3":null,"num4":null,"num5":null,"jurisdictionName":"呼叫管理","jurisdictionId":2,"jurisdictionCode":"call","parentId":0},{"id":36,"roleName":"测试领导","remark":"123456","num1":null,"num2":null,"num3":null,"num4":null,"num5":null,"jurisdictionName":"事件管理","jurisdictionId":3,"jurisdictionCode":"event","parentId":0},{"id":36,"roleName":"测试领导","remark":"123456","num1":null,"num2":null,"num3":null,"num4":null,"num5":null,"jurisdictionName":"平台管理","jurisdictionId":4,"jurisdictionCode":"platform","parentId":0},{"id":36,"roleName":"测试领导","remark":"123456","num1":null,"num2":null,"num3":null,"num4":null,"num5":null,"jurisdictionName":"角色管理","jurisdictionId":5,"jurisdictionCode":"page/system/role_list.html","parentId":1},{"id":36,"roleName":"测试领导","remark":"123456","num1":null,"num2":null,"num3":null,"num4":null,"num5":null,"jurisdictionName":"用户管理","jurisdictionId":6,"jurisdictionCode":"page/system/user_list.html","parentId":1},{"id":36,"roleName":"测试领导","remark":"123456","num1":null,"num2":null,"num3":null,"num4":null,"num5":null,"jurisdictionName":"线路管理","jurisdictionId":7,"jurisdictionCode":"page/system/line_list.html","parentId":1},{"id":36,"roleName":"测试领导","remark":"123456","num1":null,"num2":null,"num3":null,"num4":null,"num5":null,"jurisdictionName":"领导端","jurisdictionId":24,"jurisdictionCode":"/","parentId":0},{"id":36,"roleName":"测试领导","remark":"123456","num1":null,"num2":null,"num3":null,"num4":null,"num5":null,"jurisdictionName":"分类管理","jurisdictionId":9,"jurisdictionCode":"page/system/type_list.html","parentId":1},{"id":36,"roleName":"测试领导","remark":"123456","num1":null,"num2":null,"num3":null,"num4":null,"num5":null,"jurisdictionName":"部门管理","jurisdictionId":10,"jurisdictionCode":"page/system/branch_list.html","parentId":1},{"id":36,"roleName":"测试领导","remark":"123456","num1":null,"num2":null,"num3":null,"num4":null,"num5":null,"jurisdictionName":"护路日志","jurisdictionId":11,"jurisdictionCode":"page/system/hulu_log_list.html","parentId":1},{"id":36,"roleName":"测试领导","remark":"123456","num1":null,"num2":null,"num3":null,"num4":null,"num5":null,"jurisdictionName":"线路隐患","jurisdictionId":12,"jurisdictionCode":"page/system/line_danger.html","parentId":1},{"id":36,"roleName":"测试领导","remark":"123456","num1":null,"num2":null,"num3":null,"num4":null,"num5":null,"jurisdictionName":"账户设置","jurisdictionId":13,"jurisdictionCode":"page/system/account_settings.html","parentId":1},{"id":36,"roleName":"测试领导","remark":"123456","num1":null,"num2":null,"num3":null,"num4":null,"num5":null,"jurisdictionName":"呼叫列表","jurisdictionId":14,"jurisdictionCode":"/","parentId":2},{"id":36,"roleName":"测试领导","remark":"123456","num1":null,"num2":null,"num3":null,"num4":null,"num5":null,"jurisdictionName":"呼叫记录","jurisdictionId":15,"jurisdictionCode":"/","parentId":2},{"id":36,"roleName":"测试领导","remark":"123456","num1":null,"num2":null,"num3":null,"num4":null,"num5":null,"jurisdictionName":"事件列表","jurisdictionId":16,"jurisdictionCode":"page/event/event_list.html","parentId":3},{"id":36,"roleName":"测试领导","remark":"123456","num1":null,"num2":null,"num3":null,"num4":null,"num5":null,"jurisdictionName":"历史事件","jurisdictionId":17,"jurisdictionCode":"page/event/history_event_list.html","parentId":3},{"id":36,"roleName":"测试领导","remark":"123456","num1":null,"num2":null,"num3":null,"num4":null,"num5":null,"jurisdictionName":"轮播图管理","jurisdictionId":18,"jurisdictionCode":"page/platform/banner_list.html","parentId":4},{"id":36,"roleName":"测试领导","remark":"123456","num1":null,"num2":null,"num3":null,"num4":null,"num5":null,"jurisdictionName":"新闻列表","jurisdictionId":19,"jurisdictionCode":"page/platform/news_list.html","parentId":4},{"id":36,"roleName":"测试领导","remark":"123456","num1":null,"num2":null,"num3":null,"num4":null,"num5":null,"jurisdictionName":"电话管理","jurisdictionId":20,"jurisdictionCode":"page/platform/tel_list.html","parentId":4},{"id":36,"roleName":"测试领导","remark":"123456","num1":null,"num2":null,"num3":null,"num4":null,"num5":null,"jurisdictionName":"反馈管理","jurisdictionId":21,"jurisdictionCode":"page/platform/feedback_list.html","parentId":4},{"id":36,"roleName":"测试领导","remark":"123456","num1":null,"num2":null,"num3":null,"num4":null,"num5":null,"jurisdictionName":"关于我们","jurisdictionId":22,"jurisdictionCode":"page/platform/about_us.html","parentId":4},{"id":36,"roleName":"测试领导","remark":"123456","num1":null,"num2":null,"num3":null,"num4":null,"num5":null,"jurisdictionName":"版本管理","jurisdictionId":23,"jurisdictionCode":"page/platform/version_list.html","parentId":4}]]
     * totalPage : null
     * totalCount : null
     */

    private String status;
    private Object errorCode;
    private Object errorTitle;
    private Object errorMsg;
    private Object totalPage;
    private Object totalCount;
    private List<List<DataBean>> data;

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

    public List<List<DataBean>> getData() {
        return data;
    }

    public void setData(List<List<DataBean>> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 36
         * roleName : 测试领导
         * remark : 123456
         * num1 : null
         * num2 : null
         * num3 : null
         * num4 : null
         * num5 : null
         * jurisdictionName : 系统管理
         * jurisdictionId : 1
         * jurisdictionCode : system
         * parentId : 0
         */

        private int id;
        private String roleName;
        private String remark;
        private Object num1;
        private Object num2;
        private Object num3;
        private Object num4;
        private Object num5;
        private String jurisdictionName;
        private int jurisdictionId;
        private String jurisdictionCode;
        private int parentId;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getRoleName() {
            return roleName;
        }

        public void setRoleName(String roleName) {
            this.roleName = roleName;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
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

        public String getJurisdictionName() {
            return jurisdictionName;
        }

        public void setJurisdictionName(String jurisdictionName) {
            this.jurisdictionName = jurisdictionName;
        }

        public int getJurisdictionId() {
            return jurisdictionId;
        }

        public void setJurisdictionId(int jurisdictionId) {
            this.jurisdictionId = jurisdictionId;
        }

        public String getJurisdictionCode() {
            return jurisdictionCode;
        }

        public void setJurisdictionCode(String jurisdictionCode) {
            this.jurisdictionCode = jurisdictionCode;
        }

        public int getParentId() {
            return parentId;
        }

        public void setParentId(int parentId) {
            this.parentId = parentId;
        }
    }
}
