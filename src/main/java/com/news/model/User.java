package com.news.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by zhongziming on 2017/5/5.
 */
@Entity
public class User {
    private int id;
    private String email;
    private String qq;
    private String phoneNumber;
    private String userName;
    private String password;

    public User() {
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getQq() {

        return qq;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }


    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

}
