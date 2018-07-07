package com.ken.wms.dao;

import com.ken.wms.domain.Supplier;

import java.util.List;


public interface SupplierMapper {

	/**
	 * 閫夋嫨鍏ㄩ儴鐨� Supplier
	 * @return 杩斿洖鎵�鏈夌殑渚涘簲鍟�
	 */
	List<Supplier> selectAll();
	
	/**
	 * 閫夋嫨鎸囧畾 id 鐨� Supplier
	 * @param id 渚涘簲鍟咺D
	 * @return 杩斿洖鎸囧畾ID瀵瑰簲鐨勪緵搴斿晢
	 */
	Supplier selectById(Integer id);
	
	/**
	 * 閫夋嫨鎸囧畾 supplier name 鐨� Supplier
	 * @param supplierName 渚涘簲鍟嗗悕绉�
	 * @return 杩斿洖supplierName瀵瑰簲鐨勪緵搴斿晢
	 */
	Supplier selectBuName(String supplierName);
	
	/**
	 * 閫夋嫨鎸囧畾 supplier name 鐨� Supplier
	 * 涓� selectBuName 鏂规硶鐨勫尯鍒湪浜庢湰鏂规硶灏嗚繑鍥炵浉浼煎尮閰嶇殑缁撴灉
	 * @param supplierName 渚涘簲鍟嗗悕
	 * @return 杩斿洖鎵�鏈夋ā绯婂尮閰嶆寚瀹歴upplierName鐨勪緵搴斿晢
	 */
	List<Supplier> selectApproximateByName(String supplierName);
	
	/**
	 * 鎻掑叆 Supplier 鍒版暟鎹簱涓�
	 * 涓嶉渶瑕佹寚瀹� Supplier 鐨勪富閿紝閲囩敤鐨勬暟鎹簱 AI 鏂瑰紡
	 * @param supplier Supplier 瀹炰緥
	 */
	void insert(Supplier supplier);
	
	/**
	 * 鎵归噺鎻掑叆 Supplier 鍒版暟鎹簱涓�
	 * @param suppliers 瀛樻斁 Supplier 瀹炰緥鐨� Lists
	 */
	void insertBatch(List<Supplier> suppliers);
	
	/**
	 * 鏇存柊 Supplier 鍒版暟鎹簱
	 * 璇� Supplier 蹇呴』宸茬粡瀛樺湪浜庢暟鎹簱涓紝鍗冲凡缁忓垎閰嶄富閿紝鍚﹀垯灏嗘洿鏂板け璐�
	 * @param supplier Supplier 瀹炰緥
	 */
	void update(Supplier supplier);
	
	/**
	 * 鍒犻櫎鎸囧畾 id 鐨凷upplier
	 * @param id 渚涘簲鍟咺D
	 */
	void deleteById(Integer id);
	
	/**
	 * 鍒犻櫎鎸囧畾 supplierName 鐨� Supplier
	 * @param supplierName 渚涘簲鍟嗗悕绉�
	 */
	void deleteByName(String supplierName);
	
}
