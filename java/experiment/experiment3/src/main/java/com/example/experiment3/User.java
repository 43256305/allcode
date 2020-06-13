package com.example.experiment3;

import javax.validation.constraints.Pattern;

/**
 * @program: experiment3
 * @description:
 * @author: xjh
 * @create: 2020-04-22 13:59
 **/
public class User {
    @Pattern(regexp="[a-z]{3}",message="请输入3个字母")
    private String account;
    @Pattern(regexp="[0-9|]{6}",message="请输入6位数字")
    private String password;
    public String getAccount() {
        return account;
    }
    public void setAccount(String account) {
        this.account = account;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

}
