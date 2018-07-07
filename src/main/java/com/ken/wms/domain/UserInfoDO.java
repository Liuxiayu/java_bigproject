package com.ken.wms.domain;


public class UserInfoDO {

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
    private int firstLogin;

    /**
     * 鐢ㄦ埛璐︽埛灞炴�х殑 getter 浠ュ強 setter
     */

    public String getUserName() {
        return userName;
    }

    public int getFirstLogin() {
        return firstLogin;
    }

    public void setFirstLogin(int firstLogin) {
        this.firstLogin = firstLogin;
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

    @Override
    public String toString() {
        return "UserInfoDO{" +
                "userID=" + userID +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", firstLogin=" + firstLogin +
                '}';
    }
}
