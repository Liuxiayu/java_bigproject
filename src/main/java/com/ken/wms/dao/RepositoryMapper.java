package com.ken.wms.dao;

import com.ken.wms.domain.Repository;

import java.util.List;


public interface RepositoryMapper {

	/**
	 * 閫夋嫨鍏ㄩ儴鐨� Repository 璁板綍
	 * @return 杩斿洖鍏ㄩ儴鐨� Repository
	 */
	List<Repository> selectAll();
	
	/**
	 * 閫夋嫨鍏ㄩ儴鐨勬湭鍒嗛厤鐨� repository 璁板綍
	 * @return 杩斿洖鎵�鏈夋湭鍒嗛厤鐨� Repository
	 */
	List<Repository> selectUnassign();
	
	/**
	 * 閫夋嫨鎸囧畾 Repository ID 鐨� Repository 璁板綍
	 * @param repositoryID 浠撳簱ID
	 * @return 杩斿洖鎸囧畾鐨凴epository
	 */
	Repository selectByID(Integer repositoryID);
	
	/**
	 * 閫夋嫨鎸囧畾 repository Address 鐨� repository 璁板綍
	 * @param address 浠撳簱鍦板潃
	 * @return 杩斿洖鎸囧畾鐨凴epository 
	 */
	List<Repository> selectByAddress(String address);
	
	/**
	 * 鎻掑叆涓�鏉℃柊鐨� Repository 璁板綍
	 * @param repository 浠撳簱淇℃伅
	 */
	void insert(Repository repository);
	
	/**
	 * 鎵归噺鎻掑叆 Repository 璁板綍
	 * @param repositories 瀛樻湁鑻ュ共鏉¤褰曠殑 List
	 */
	void insertbatch(List<Repository> repositories);
	
	/**
	 * 鏇存柊 Repository 璁板綍
	 * @param repository 浠撳簱淇℃伅
	 */
	void update(Repository repository);
	
	/**
	 * 鍒犻櫎鎸囧畾 Repository ID 鐨� Repository 璁板綍
	 * @param repositoryID 浠撳簱ID
	 */
	void deleteByID(Integer repositoryID);
}
