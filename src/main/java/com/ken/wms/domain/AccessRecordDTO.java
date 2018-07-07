package com.ken.wms.domain;


public class AccessRecordDTO {

    /**
     * 鐧诲叆鐧诲嚭璁板綍ID
     */
    private Integer id;

    /**
     * 鐧婚檰鐢ㄦ埛ID
     */
    private Integer userID;

    /**
     * 鐧婚檰鐢ㄦ埛鍚�
     */
    private String userName;

    /**
     * 鐧诲叆鎴栫櫥鍑烘椂闂�
     */
    private String accessTime;

    /**
     * 鐢ㄦ埛鐧诲叆鎴栫櫥鍑哄搴旂殑IP鍦板潃
     */
    private String accessIP;

    /**
     * 璁板綍绫诲瀷锛岀櫥鍏ユ垨鐧诲嚭
     */
    private String accessType;

    public Integer getId() {
        return id;
    }

    public Integer getUserID() {
        return userID;
    }

    public String getUserName() {
        return userName;
    }

    public String getAccessTime() {
        return accessTime;
    }

    public String getAccessIP() {
        return accessIP;
    }

    public String getAccessType() {
        return accessType;
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

    public void setAccessTime(String accessTime) {
        this.accessTime = accessTime;
    }

    public void setAccessIP(String accessIP) {
        this.accessIP = accessIP;
    }

    public void setAccessType(String accessType) {
        this.accessType = accessType;
    }

    @Override
    public String toString() {
        return "AccessRecordDTO{" +
                "id=" + id +
                ", userID=" + userID +
                ", userName='" + userName + '\'' +
                ", accessTime='" + accessTime + '\'' +
                ", accessIP='" + accessIP + '\'' +
                ", accessType='" + accessType + '\'' +
                '}';
    }
}
