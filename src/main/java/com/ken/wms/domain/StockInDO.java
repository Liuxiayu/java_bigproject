package com.ken.wms.domain;


import java.util.Date;


public class StockInDO {

    /**
     * 鍏ュ簱璁板綍
     */
    private Integer id;

    /**
     * 渚涘簲鍟咺D
     */
    private Integer supplierID;

    /**
     * 渚涘簲鍟嗗悕绉�
     */
    private String supplierName;

    /**
     * 鍟嗗搧ID
     */
    private Integer goodID;

    /**
     * 鍟嗗搧鍚嶇О
     */
    private String goodName;

    /**
     * 鍏ュ簱浠撳簱ID
     */
    private Integer repositoryID;

    /**
     * 鍏ュ簱鏁伴噺
     */
    private long number;

    /**
     * 鍏ュ簱鏃ユ湡
     */
    private Date time;

    /**
     * 鍏ュ簱缁忔墜浜�
     */
    private String personInCharge;

    public Integer getRepositoryID() {
        return repositoryID;
    }

    public void setRepositoryID(Integer repositoryID) {
        this.repositoryID = repositoryID;
    }

    public Integer getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(Integer supplierID) {
        this.supplierID = supplierID;
    }

    public Integer getGoodID() {
        return goodID;
    }

    public void setGoodID(Integer goodID) {
        this.goodID = goodID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
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
        return "StockInDO [id=" + id + ", supplierID=" + supplierID + ", supplierName=" + supplierName + ", goodID="
                + goodID + ", goodName=" + goodName + ", repositoryID=" + repositoryID + ", number=" + number
                + ", time=" + time + ", personInCharge=" + personInCharge + "]";
    }

}
