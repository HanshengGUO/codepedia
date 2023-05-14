package com.hsguo.codepedia.req;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class UserSaveReq {
    private Long id;

    @NotNull(message = "[Login Name] cannot be null!")
    private String loginName;

    @NotNull(message = "[Name] cannot be null!")
    private String name;

    @NotNull(message = "[Password] cannot be null!")
    @Length(min = 6, max = 20, message = "Password should have length of 6 - 20!")
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        sb.append(", id=").append(id);
        sb.append(", loginName=").append(loginName);
        sb.append(", name=").append(name);
        sb.append(", password=").append(password);
        sb.append("]");
        return sb.toString();
    }
}