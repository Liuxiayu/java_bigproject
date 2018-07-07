package com.ken.wms.dao;


import com.ken.wms.domain.Goods;

import java.util.List;


public interface GoodsMapper {

	/**
	 * 閫夋嫨鎵�鏈夌殑 Goods
	 * @return 杩斿洖鎵�鏈夌殑Goods
	 */
	List<Goods> selectAll();
	
	/**
	 * 閫夋嫨鎸囧畾 id 鐨� Goods
	 * @param id 璐х墿鐨処D
	 * @return 杩斿洖鎵цID瀵瑰簲鐨凣oods
	 */
	Goods selectById(Integer id);
	
	/**
	 * 閫夋嫨鎸囧畾 Goods name 鐨� Goods
	 * @param goodsName 璐х墿鐨勫悕绉�
	 * @return 杩斿洖鎸囧畾GoodsName瀵瑰簲鐨勮揣鐗�
	 */
	Goods selectByName(String goodsName);
	
	/**
	 * 閫夋嫨鍒跺畾 goods name 鐨� goods
	 * 妯＄硦鍖归厤
	 * @param goodsName 璐х墿寰峰悕绉�
	 * @return 杩斿洖妯＄硦鍖归厤鎸囧畾goodsName鐨勮揣鐗�
	 */
	List<Goods> selectApproximateByName(String goodsName);
	
	/**
	 * 鎻掑叆涓�鏉℃柊鐨勮褰曞埌鏁版嵁搴�
	 * @param goods 璐х墿淇℃伅
	 */
	void insert(Goods goods);
	
	/**
	 * 鎵归噺鎻掑叆鏂扮殑璁板綍鍒版暟鎹簱涓�
	 * @param goods 瀛樻斁 goods 淇℃伅鐨� List
	 */
	void insertBatch(List<Goods> goods);
	
	/**
	 * 鏇存柊 Goods 鍒版暟鎹簱涓�
	 * 璇� Customer 蹇呴』宸茬粡瀛樺湪浜庢暟鎹簱涓紝鍗冲凡缁忓垎閰嶄富閿紝鍚﹀垯灏嗘洿鏂板け璐�
	 * @param goods 璐х墿淇℃伅
	 */
	void update(Goods goods);
	
	/**
	 * 鍒犻櫎鎸囧畾 id 鐨� goods
	 * @param id 璐х墿ID
	 */
	void deleteById(Integer id);
	
	/**
	 * 鍒犻櫎鎸囧畾 goods name 鐨� goods
	 * @param goodsName 璐х墿鐨勫悕绉�
	 */
	void deleteByName(String goodsName);
}
