package com.hsguo.codepedia.req;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class UserResetPasswordReq {
    private Long id;

    @NotNull(message = "[Password] cannot be null!")
    @Length(min = 6, max = 32, message = "Password should have length of 6 - 32!")
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        sb.append(", password=").append(password);
        sb.append("]");
        return sb.toString();
    }
}