package com.ken.wms.domain;


public class StockRecordDTO {

    /**
     * 璁板綍ID
     */
    private Integer recordID;

    /**
     * 璁板綍鐨勭被鍨嬶紙鍑哄簱/鍏ュ簱锛�
     */
    private String type;

    /**
     * 渚涘簲鍟嗭紙鍏ュ簱锛夋垨瀹㈡埛锛堝嚭搴擄級鍚嶇О
     */
    private String supplierOrCustomerName;

    /**
     * 鍟嗗搧鍚嶇О
     */
    private String goodsName;

    /**
     * 鍑哄簱鎴栧叆搴撲粨搴揑D
     */
    private Integer repositoryID;

    /**
     * 鍑哄簱鎴栧叆搴撴暟閲�
     */
    private long number;

    /**
     * 鍑哄簱鎴栧叆搴撴椂闂�
     */
    private String time;

    /**
     * 鍑哄簱鎴栧叆搴撶粡鎵嬩汉
     */
    private String personInCharge;


    public Integer getRecordID() {
        return recordID;
    }

    public String getType() {
        return type;
    }

    public String getSupplierOrCustomerName() {
        return supplierOrCustomerName;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public Integer getRepositoryID() {
        return repositoryID;
    }

    public long getNumber() {
        return number;
    }

    public String getTime() {
        return time;
    }

    public String getPersonInCharge() {
        return personInCharge;
    }

    public void setRecordID(Integer recordID) {
        this.recordID = recordID;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSupplierOrCustomerName(String supplierOrCustomerName) {
        this.supplierOrCustomerName = supplierOrCustomerName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public void setRepositoryID(Integer repositoryID) {
        this.repositoryID = repositoryID;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setPersonInCharge(String personInCharge) {
        this.personInCharge = personInCharge;
    }

    @Override
    public String toString() {
        return "StockRecordDTO{" +
                "recordID=" + recordID +
                ", type='" + type + '\'' +
                ", supplierOrCustomerName='" + supplierOrCustomerName + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", repositoryID=" + repositoryID +
                ", number=" + number +
                ", time=" + time +
                ", personInCharge='" + personInCharge + '\'' +
                '}';
    }
}
