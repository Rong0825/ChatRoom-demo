package com.java.char_room.client.entity;

import lombok.Data;

import java.util.Set;

//实体层
@Data
public class User {
    //用包装类是因为Integer默认值为null,int 是0;
    private Integer id;
    private String userName;
    private String password;
    private  String brief;
    private Set<String> userNames;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public Set<String> getUserNames() {
        return userNames;
    }

    public void setUserNames(Set<String> userNames) {
        this.userNames = userNames;
    }
}