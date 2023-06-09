package com.hsguo.codepedia.req;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

public class UserLoginReq {
    @NotEmpty(message = "[Login Name] cannot be null!")
    private String loginName;

    @NotEmpty(message = "[Password] cannot be null!")
    @Length(min = 6, max = 32, message = "Password is not correct!")
    private String password;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", loginName=").append(loginName);
        sb.append(", password=").append(password);
        sb.append("]");
        return sb.toString();
    }
}