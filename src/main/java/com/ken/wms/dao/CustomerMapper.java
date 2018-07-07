package com.ken.wms.dao;

import com.ken.wms.domain.Customer;

import java.util.List;


public interface CustomerMapper {

	/**
	 * 閫夋嫨鎵�鏈夌殑 Customer
	 * @return 杩斿洖鎵�鏈夌殑 Customer
	 */
	List<Customer> selectAll();
	
	/**
	 * 閫夋嫨鎸囧畾 id 鐨� Supplier
	 * @param id Customer鐨処D
	 * @return 杩斿洖鎸囧畾ID瀵瑰簲鐨凜ustomer
	 */
	Customer selectById(Integer id);
	
	/**
	 * 閫夋嫨鎸囧畾 Customer name 鐨� customer
	 * @param customerName 瀹㈡埛鐨勫悕绉�
	 * @return 杩斿洖鎸囧畾CustomerName瀵瑰簲鐨凜ustomer
	 */
	Customer selectByName(String customerName);
	
	/**
	 * 閫夋嫨鎸囧畾 customer name 鐨� Customer
	 * 涓� selectByName 鏂规硶鐨勫尯鍒湪浜庢湰鏂规硶灏嗚繑鍥炵浉浼煎尮閰嶇殑缁撴灉
	 * @param customerName Customer 渚涘簲鍟嗗悕
	 * @return 杩斿洖妯＄硦鍖归厤鎸囧畾customerName 瀵瑰簲鐨凜ustomer
	 */
	List<Customer> selectApproximateByName(String customerName);
	
	/**
	 * 鎻掑叆 Customer 鍒版暟鎹簱涓�
	 * 涓嶉渶瑕佹寚瀹� Customer 鐨勪富閿紝閲囩敤鐨勬暟鎹簱 AI 鏂瑰紡
	 * @param customer Customer 瀹炰緥
	 */
	void insert(Customer customer);
	
	/**
	 * 鎵归噺鎻掑叆 Customer 鍒版暟鎹簱涓�
	 * @param customers 瀛樻斁 Customer 瀹炰緥鐨� List
	 */
	void insertBatch(List<Customer> customers);
	
	/**
	 * 鏇存柊 Customer 鍒版暟鎹簱
	 * 璇� Customer 蹇呴』宸茬粡瀛樺湪浜庢暟鎹簱涓紝鍗冲凡缁忓垎閰嶄富閿紝鍚﹀垯灏嗘洿鏂板け璐�
	 * @param customer customer 瀹炰緥
	 */
	void update(Customer customer);
	
	/**
	 * 鍒犻櫎鎸囧畾 id 鐨� customer
	 * @param id customer ID
	 */
	void deleteById(Integer id);
	
	/**
	 * 鍒犻櫎鎸囧畾 customerName 鐨� customer
	 * @param customerName 瀹㈡埛鍚嶇О
	 */
	void deleteByName(String customerName);
}
