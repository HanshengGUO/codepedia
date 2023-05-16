package com.hsguo.codepedia.exception;

public enum BusinessExceptionCode {

    USER_LOGIN_NAME_EXIST("Login Name exists"),
    LOGIN_USER_ERROR("Usernare or password is not correct"),
    VOTE_REPEAT("You have already voted today"),
    ;

    private String desc;

    BusinessExceptionCode(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
