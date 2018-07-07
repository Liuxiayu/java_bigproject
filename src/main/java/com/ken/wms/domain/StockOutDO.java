package com.ken.wms.domain;


import java.util.Date;


public class StockOutDO {

    /**
     * 鍑哄簱璁板綍ID
     */
    private Integer id;

    /**
     * 瀹㈡埛ID
     */
    private Integer customerID;

    /**
     * 瀹㈡埛鍚嶇О
     */
    private String customerName;

    /**
     * 鍟嗗搧ID
     */
    private Integer goodID;

    /**
     * 鍟嗗搧鍚嶇О
     */
    private String goodName;

    /**
     * 鍑哄簱浠撳簱ID
     */
    private Integer repositoryID;

    /**
     * 鍟嗗搧鍑哄簱鏁伴噺
     */
    private long number;

    /**
     * 鍑哄簱鏃ユ湡
     */
    private Date time;

    /**
     * 鍑哄簱缁忔墜浜�
     */
    private String personInCharge;// 缁忔墜浜�

    public Integer getRepositoryID() {
        return repositoryID;
    }

    public void setRepositoryID(Integer repositoryID) {
        this.repositoryID = repositoryID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Integer getGoodID() {
        return goodID;
    }

    public void setGoodID(Integer goodID) {
        this.goodID = goodID;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getPersonInCharge() {
        return personInCharge;
    }

    public void setPersonInCharge(String personInCharge) {
        this.personInCharge = personInCharge;
    }

    @Override
    public String toString() {
        return "StockOutDO [id=" + id + ", customerID=" + customerID + ", customerName=" + customerName + ", goodID="
                + goodID + ", goodName=" + goodName + ", repositoryID=" + repositoryID + ", number=" + number
                + ", time=" + time + ", personInCharge=" + personInCharge + "]";
    }

}
