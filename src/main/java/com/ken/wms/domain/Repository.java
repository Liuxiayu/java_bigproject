package com.ken.wms.domain;


public class Repository {

	private Integer id;// 浠撳簱ID
	private String address;// 浠撳簱鍦板潃
	private String status;// 浠撳簱鐘舵��
	private String area;// 浠撳簱闈㈢Н
	private String desc;// 浠撳簱鎻忚堪
	private Integer adminID;//浠撳簱绠＄悊鍛業D
	private String adminName; //浠撳簱绠＄悊鍛樺悕瀛�

	public Integer getAdminID() {
		return adminID;
	}

	public void setAdminID(Integer adminID) {
		this.adminID = adminID;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Repository [id=" + id + ", address=" + address + ", status=" + status + ", area=" + area + ", desc="
				+ desc + ", adminID=" + adminID + ", adminName=" + adminName + "]";
	}

}
