package com.ken.wms.domain;


public class User {

	private Integer id;// 鐢ㄦ埛ID
	private String userName;// 鐢ㄦ埛鍚�
	private String password;// 鐢ㄦ埛瀵嗙爜

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", password=" + password + "]";
	}

}
