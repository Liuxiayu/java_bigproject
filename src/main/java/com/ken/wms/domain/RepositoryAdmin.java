package com.ken.wms.domain;

import java.sql.Date;

public class RepositoryAdmin {

	private Integer id;// 浠撳簱绠＄悊鍛業D
	private String name;// 濮撳悕
	private String sex;// 鎬у埆
	private String tel;// 鑱旂郴鐢佃瘽
	private String address;// 鍦板潃
	private Date birth;// 鍑虹敓鏃ユ湡
	private Integer repositoryBelongID;// 鎵�灞炰粨搴揑D

	
	public Integer getRepositoryBelongID() {
		return repositoryBelongID;
	}

	public void setRepositoryBelongID(Integer repositoryBelongID) {
		this.repositoryBelongID = repositoryBelongID;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	@Override
	public String toString() {
		return "RepositoryAdmin [id=" + id + ", name=" + name + ", sex=" + sex + ", tel=" + tel + ", address=" + address
				+ ", birth=" + birth + ", repositoryBelongID=" + repositoryBelongID + "]";
	}

}
