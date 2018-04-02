package com.news.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

/**
 * Created by zhongziming on 2017/5/5.
 */
@Entity
public class Admin implements Serializable{
    private static final long serializableVersion = 4648132165498113131L;
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
    @GeneratedValue(generator = "myUUID")
    @GenericGenerator(name="myUUID",strategy = "uuid")
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
