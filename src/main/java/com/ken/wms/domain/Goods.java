package com.ken.wms.domain;


public class Goods {

	private Integer id;// 璐х墿ID
	private String name;// 璐х墿鍚�
	private String type;// 璐х墿绫诲瀷
	private String size;// 璐х墿瑙勬牸
	private Double value;// 璐х墿浠峰��

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Goods [id=" + id + ", name=" + name + ", type=" + type + ", size=" + size + ", value=" + value + "]";
	}

}
