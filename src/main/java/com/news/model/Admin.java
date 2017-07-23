package com.news.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by zhongziming on 2017/5/5.
 */
@Entity
public class Admin {
    private int id;
    private String email;
    private String adminName;
    private String password;
    public Admin(){}
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

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
