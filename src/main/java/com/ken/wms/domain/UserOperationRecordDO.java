package com.ken.wms.domain;

import java.util.Date;

public class UserOperationRecordDO {

    /**
     * 璁板綍ID
     */
    private Integer id;

    /**
     * 鎵ц鎿嶄綔鐨勭敤鎴稩D
     */
    private Integer userID;

    /**
     * 鎵ц鎿嶄綔鐨勭敤鎴峰悕
     */
    private String userName;

    /**
     * 鎿嶄綔鐨勫悕绉�
     */
    private String operationName;

    /**
     * 鎵ц鎿嶄綔鐨勬椂闂�
     */
    private Date operationTime;

    /**
     * 鎵ц鎿嶄綔缁撴灉
     */
    private String operationResult;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public void setOperationTime(Date operationTime) {
        this.operationTime = operationTime;
    }

    public void setOperationResult(String operationResult) {
        this.operationResult = operationResult;
    }

    public Integer getId() {
        return id;
    }

    public Integer getUserID() {
        return userID;
    }

    public String getUserName() {
        return userName;
    }

    public String getOperationName() {
        return operationName;
    }

    public Date getOperationTime() {
        return operationTime;
    }

    public String getOperationResult() {
        return operationResult;
    }

    @Override
    public String toString() {
        return "UserOperationRecordDO{" +
                "id=" + id +
                ", userID=" + userID +
                ", userName='" + userName + '\'' +
                ", operationName='" + operationName + '\'' +
                ", operationTime=" + operationTime +
                ", operationResult='" + operationResult + '\'' +
                '}';
    }
}
