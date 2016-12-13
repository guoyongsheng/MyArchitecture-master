package com.mycompany.myarchitecture.entity;

import java.io.Serializable;

/**
 * Created by lenovo on 2016/8/22.
 */
public class UserEntity extends ResponseInfo implements Serializable {
    private UserInfo respStatus;

    public UserInfo getRespStatus() {
        return respStatus;
    }

    public void setRespStatus(UserInfo respStatus) {
        this.respStatus = respStatus;
    }
}
