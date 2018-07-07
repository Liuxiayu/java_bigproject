package com.ken.wms.security.service.Interface;

import com.ken.wms.exception.UserAccountServiceException;

import java.util.Map;


public interface AccountService {

	/**
	 * 瀵嗙爜鏇存敼
	 * @param userID 鐢ㄦ埛ID
	 * @param passwordInfo 鏇存敼鐨勫瘑鐮佷俊鎭�
	 */
	public void passwordModify(Integer userID, Map<String, Object> passwordInfo) throws UserAccountServiceException;
}
