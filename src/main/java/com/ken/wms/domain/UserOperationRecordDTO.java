package com.ken.wms.domain;


public class UserOperationRecordDTO {

    /**
     * 鎿嶄綔璁板綍ID
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
     * 鎿嶄綔鎵ц鐨勬椂闂�
     */
    private String operationTime;

    /**
     * 鎿嶄綔鎵ц鐨勭粨鏋�
     */
    private String operationResult;

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

    public String getOperationTime() {
        return operationTime;
    }

    public String getOperationResult() {
        return operationResult;
    }

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

    public void setOperationTime(String operationTime) {
        this.operationTime = operationTime;
    }

    public void setOperationResult(String operationResult) {
        this.operationResult = operationResult;
    }

    @Override
    public String toString() {
        return "UserOperationRecordDTO{" +
                "id=" + id +
                ", userID=" + userID +
                ", userName='" + userName + '\'' +
                ", operationName='" + operationName + '\'' +
                ", operationTime='" + operationTime + '\'' +
                ", operationResult='" + operationResult + '\'' +
                '}';
    }
}
