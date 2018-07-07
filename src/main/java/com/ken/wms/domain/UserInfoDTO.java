package com.ken.wms.domain;

import java.util.ArrayList;
import java.util.List;


public class UserInfoDTO {

    /**
     * 鐢ㄦ埛ID
     */
    private Integer userID;

    /**
     * 鐢ㄦ埛鍚�
     */
    private String userName;

    /**
     * 鐢ㄦ埛瀵嗙爜锛堝凡鍔犲瘑锛�
     */
    private String password;

    /**
     * 鏄惁涓哄垵娆＄櫥闄�
     */
    private boolean firstLogin;

    /**
     * 鐢ㄦ埛瑙掕壊
     */
    private List<String> role = new ArrayList<>();

    /**
     * 鐢ㄦ埛璐︽埛灞炴�х殑 getter 浠ュ強 setter
     */

    public String getUserName() {
        return userName;
    }

    public boolean getFirstLogin() {
        return firstLogin;
    }

    public void setFirstLogin(boolean firstLogin) {
        this.firstLogin = firstLogin;
    }

    public List<String> getRole() {
        return role;
    }

    public Integer getUserID() {
        return userID;
    }

    public String getPassword() {
        return password;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(List<String> role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserInfoDTO{" +
                "userID=" + userID +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", firstLogin=" + firstLogin +
                ", role=" + role +
                '}';
    }
}
