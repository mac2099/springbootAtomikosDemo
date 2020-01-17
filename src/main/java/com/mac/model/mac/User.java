package com.mac.model.mac;

import java.util.Date;

/**
 *
 * This class corresponds to the database table user
 *
 * @mbggenerated do_not_delete_during_merge
 */
public class User {
    /**
     * id
     */
    private Integer id;

    /**
     * user_name
     */
    private String userName;

    /**
     * password
     */
    private String password;

    /**
     * create_time
     */
    private Date createTime;

    /**
     * update_time
     */
    private Date updateTime;

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}