package com.app.qunadai.bean;

/**
 * Created by wayne on 2017/5/13.
 */

public class PersonInfo {


    /**
     * msg : 成功
     * code : 000
     * detail : 成功
     * content : {"userCreditInfo":{"id":"a9584159-6cf1-45f7-99f4-653b52924766","createdTime":1492854423000,"updatedTime":1492856521000,"name":"美美","loanAmount":"5000","loanDeadLine":"2年","educationLevel":"本科","householdIncome":"6000","employmentStatus":"员工","maritalStatus":"002","habitualResidence":" 上海 上海 浦东新区"}}
     */

    private String msg;
    private String code;
    private String detail;
    private ContentBean content;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public ContentBean getContent() {
        return content;
    }

    public void setContent(ContentBean content) {
        this.content = content;
    }

    public static class ContentBean {
        /**
         * userCreditInfo : {"id":"a9584159-6cf1-45f7-99f4-653b52924766","createdTime":1492854423000,"updatedTime":1492856521000,"name":"美美","loanAmount":"5000","loanDeadLine":"2年","educationLevel":"本科","householdIncome":"6000","employmentStatus":"员工","maritalStatus":"002","habitualResidence":" 上海 上海 浦东新区"}
         */

        private UserCreditInfoBean userCreditInfo;

        public UserCreditInfoBean getUserCreditInfo() {
            return userCreditInfo;
        }

        public void setUserCreditInfo(UserCreditInfoBean userCreditInfo) {
            this.userCreditInfo = userCreditInfo;
        }

        public static class UserCreditInfoBean {
            /**
             * id : a9584159-6cf1-45f7-99f4-653b52924766
             * createdTime : 1492854423000
             * updatedTime : 1492856521000
             * name : 美美
             * loanAmount : 5000
             * loanDeadLine : 2年
             * educationLevel : 本科
             * householdIncome : 6000
             * employmentStatus : 员工
             * maritalStatus : 002
             * habitualResidence :  上海 上海 浦东新区
             */

            private String id;
            private long createdTime;
            private long updatedTime;
            private String name;
            private String loanAmount;
            private String loanDeadLine;
            private String educationLevel;
            private String householdIncome;
            private String employmentStatus;
            private String maritalStatus;
            private String habitualResidence;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public long getCreatedTime() {
                return createdTime;
            }

            public void setCreatedTime(long createdTime) {
                this.createdTime = createdTime;
            }

            public long getUpdatedTime() {
                return updatedTime;
            }

            public void setUpdatedTime(long updatedTime) {
                this.updatedTime = updatedTime;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getLoanAmount() {
                return loanAmount;
            }

            public void setLoanAmount(String loanAmount) {
                this.loanAmount = loanAmount;
            }

            public String getLoanDeadLine() {
                return loanDeadLine;
            }

            public void setLoanDeadLine(String loanDeadLine) {
                this.loanDeadLine = loanDeadLine;
            }

            public String getEducationLevel() {
                return educationLevel;
            }

            public void setEducationLevel(String educationLevel) {
                this.educationLevel = educationLevel;
            }

            public String getHouseholdIncome() {
                return householdIncome;
            }

            public void setHouseholdIncome(String householdIncome) {
                this.householdIncome = householdIncome;
            }

            public String getEmploymentStatus() {
                return employmentStatus;
            }

            public void setEmploymentStatus(String employmentStatus) {
                this.employmentStatus = employmentStatus;
            }

            public String getMaritalStatus() {
                return maritalStatus;
            }

            public void setMaritalStatus(String maritalStatus) {
                this.maritalStatus = maritalStatus;
            }

            public String getHabitualResidence() {
                return habitualResidence;
            }

            public void setHabitualResidence(String habitualResidence) {
                this.habitualResidence = habitualResidence;
            }
        }
    }
}
