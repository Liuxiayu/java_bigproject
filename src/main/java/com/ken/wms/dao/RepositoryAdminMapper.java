package com.ken.wms.dao;

import com.ken.wms.domain.RepositoryAdmin;

import java.util.List;


public interface RepositoryAdminMapper {

	/**
	 * 閫夋嫨鎸囧畾 ID 鐨勪粨搴撶鐞嗗憳淇℃伅
	 * @param id 浠撳簱绠＄悊鍛業D
	 * @return 杩斿洖鎸囧畾 ID 鐨勪粨搴撶鐞嗗憳淇℃伅
	 */
	RepositoryAdmin selectByID(Integer id);
	
	/**
	 * 閫夋嫨鎸囧畾 name 鐨勪粨搴撶鐞嗗憳淇℃伅銆�
	 * 鏀寔妯＄硦鏌ユ壘
	 * @param name 浠撳簱绠＄悊鍛樺悕瀛�
	 * @return 杩斿洖鑻ュ共鏉℃寚瀹� name 鐨勪粨搴撶鐞嗗憳淇℃伅
	 */
	List<RepositoryAdmin> selectByName(String name);
	
	/**
	 * 閫夋嫨鎵�鏈夌殑浠撳簱绠＄悊鍛樹俊鎭�
	 * @return 杩斿洖鎵�鏈夌殑浠撳簱绠＄悊鍛樹俊鎭�
	 */
	List<RepositoryAdmin> selectAll();
	
	/**
	 * 閫夋嫨宸叉寚娲炬寚瀹� repositoryID 鐨勪粨搴撶鐞嗗憳淇℃伅
	 * @param repositoryID 鎸囨淳鐨勪粨搴揑D
	 * @return 杩斿洖宸叉寚娲炬寚瀹� repositoryID 鐨勪粨搴撶鐞嗗憳淇℃伅
	 */
	RepositoryAdmin selectByRepositoryID(Integer repositoryID);
	
	/**
	 * 鎻掑叆涓�鏉′粨搴撶鐞嗗憳淇℃伅
	 * @param repositoryAdmin 浠撳簱绠＄悊鍛樹俊鎭�
	 */
	void insert(RepositoryAdmin repositoryAdmin);
	
	/**
	 * 鎵归噺鎻掑叆浠撳簱绠＄悊鍛樹俊鎭�
	 * @param repositoryAdmins 瀛樻斁鑻ュ共鏉′粨搴撶鐞嗗憳淇℃伅鐨� List
	 */
	void insertBatch(List<RepositoryAdmin> repositoryAdmins);
	
	/**
	 * 鏇存柊浠撳簱绠＄悊鍛樹俊鎭�
	 * @param repositoryAdmin 浠撳簱绠＄悊鍛樹俊鎭�
	 */
	void update(RepositoryAdmin repositoryAdmin);
	
	/**
	 * 鍒犻櫎鎸囧畾 ID 鐨勪粨搴撶鐞嗗憳淇℃伅
	 * @param id 浠撳簱绠＄悊鍛� ID
	 */
	void deleteByID(Integer id);
}
