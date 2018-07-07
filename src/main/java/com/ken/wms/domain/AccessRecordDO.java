package com.ken.wms.domain;

import java.util.Date;


public class AccessRecordDO {

    /**
     * 鐧诲叆鐧诲嚭璁板綍ID
     * 浠呭綋璇ヨ褰曚粠鏁版嵁搴撳彇鍑烘椂鏈夋晥
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
     * 璁板綍绫诲瀷锛岀櫥鍏ユ垨鐧诲嚭
     */
    private String accessType;

    /**
     * 鐧诲叆鎴栫櫥鍑烘椂闂�
     */
    private Date accessTime;

    /**
     * 鐢ㄦ埛鐧诲叆鎴栫櫥鍑哄搴旂殑IP鍦板潃
     */
    private String accessIP;
    
	public Date getAccessTime() {
		return accessTime;
	}

	public void setAccessTime(Date accessTime) {
		this.accessTime = accessTime;
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

    public void setAccessType(String accessType) {
        this.accessType = accessType;
    }

    public void setAccessIP(String accessIP) {
        this.accessIP = accessIP;
    }

    @Override
    public String toString() {
        return "AccessRecordDO{" +
                "id=" + id +
                ", userID=" + userID +
                ", userName='" + userName + '\'' +
                ", accessType='" + accessType + '\'' +
                ", accessTime=" + accessTime +
                ", accessIP='" + accessIP + '\'' +
                '}';
    }
}
