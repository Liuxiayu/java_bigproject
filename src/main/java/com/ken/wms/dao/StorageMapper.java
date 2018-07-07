package com.ken.wms.dao;

import com.ken.wms.domain.Storage;
import org.apache.ibatis.annotations.Param;


import java.util.List;

public interface StorageMapper {

	/**
	 * 閫夋嫨鎵�鏈夌殑搴撳瓨淇℃伅
	 * @return 杩斿洖鎵�鏈夌殑搴撳瓨淇℃伅
	 */
	List<Storage> selectAllAndRepositoryID(@Param("repositoryID") Integer repositoryID);
	
	/**
	 * 閫夋嫨鎸囧畾璐х墿ID鍜屼粨搴揑D鐨勫簱瀛樹俊鎭�
	 * @param goodsID 璐х墿ID
	 * @param repositoryID 搴撳瓨ID
	 * @return 杩斿洖鎵�鏈夋寚瀹氳揣鐗㊣D鍜屼粨搴揑D鐨勫簱瀛樹俊鎭�
	 */
	List<Storage> selectByGoodsIDAndRepositoryID(@Param("goodsID") Integer goodsID,
												 @Param("repositoryID") Integer repositoryID);
	
	/**
	 * 閫夋嫨鎸囧畾璐х墿鍚嶇殑搴撳瓨淇℃伅
	 * @param goodsName 璐х墿鍚嶇О
	 * @return 杩斿洖鎵�鏈夋寚瀹氳揣鐗╁悕绉扮殑搴撳瓨淇℃伅
	 */
	List<Storage> selectByGoodsNameAndRepositoryID(@Param("goodsName") String goodsName,
												   @Param("repositoryID") Integer repositoryID);
	
	/**
	 * 閫夋嫨鎸囧畾璐х墿绫诲瀷鐨勫簱瀛樹俊鎭�
	 * @param goodsType 璐х墿绫诲瀷
	 * @return 杩斿洖鎵�鏈夋寚瀹氳揣鐗╃被鍨嬬殑搴撳瓨淇℃伅
	 */
	List<Storage> selectByGoodsTypeAndRepositoryID(@Param("goodsType") String goodsType,
												   @Param("repositoryID") Integer repositoryID);
	
	/**
	 * 鏇存柊搴撳瓨淇℃伅
	 * 璇ュ簱瀛樹俊鎭繀闇�宸茬粡瀛樺湪浜庢暟鎹簱褰撲腑锛屽惁鍒欐洿鏂版棤鏁�
	 * @param storage 搴撳瓨淇℃伅
	 */
	void update(Storage storage);
	
	/**
	 * 鎻掑叆鏂扮殑搴撳瓨淇℃伅
	 * @param storage 搴撳瓨淇℃伅
	 */
	void insert(Storage storage);
	
	/**
	 * 鎵归噺瀵煎叆搴撳瓨淇℃伅
	 * @param storages 鑻ュ共鏉″簱瀛樹俊鎭�
	 */
	void insertBatch(List<Storage> storages);
	
	/**
	 * 鍒犻櫎鎸囧畾璐х墿ID鐨勫簱瀛樹俊鎭�
	 * @param goodsID 璐х墿ID
	 */
	void deleteByGoodsID(Integer goodsID);
	
	/**
	 * 鍒犻櫎鎸囧畾浠撳簱鐨勫簱瀛樹俊鎭�
	 * @param repositoryID 浠撳簱ID
	 */
	void deleteByRepositoryID(Integer repositoryID);
	
	/**
	 * 鍒犻櫎鎸囧畾浠撳簱涓殑鎸囧畾璐х墿鐨勫簱瀛樹俊鎭�
	 * @param goodsID 璐х墿ID
	 * @param repositoryID 浠撳簱ID
	 */
	void deleteByRepositoryIDAndGoodsID(@Param("goodsID") Integer goodsID, @Param("repositoryID") Integer repositoryID);
}
