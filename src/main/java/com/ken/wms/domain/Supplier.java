package com.ken.wms.domain;


public class Supplier {

	private Integer id;// 渚涘簲鍟咺D
	private String name;// 渚涘簲鍟嗗悕
	private String personInCharge;// 璐熻矗浜�
	private String tel;// 鑱旂郴鐢佃瘽
	private String email;// 鐢靛瓙閭欢
	private String address;// 渚涘簲鍟嗗湴鍧�

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

	public String getPersonInCharge() {
		return personInCharge;
	}

	public void setPersonInCharge(String personInCharge) {
		this.personInCharge = personInCharge;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Supplier [id=" + id + ", name=" + name + ", personInCharge=" + personInCharge + ", tel=" + tel
				+ ", email=" + email + ", address=" + address + "]";
	}

}
