package com.ken.wms.dao;

import com.ken.wms.domain.RoleDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface UserPermissionMapper {

    /**
     * 涓烘寚瀹� userID 鐢ㄦ埛鎸囨淳鎸囧畾 roleID 鐨勮鑹�
     * @param userID 鐢ㄦ埛ID
     * @param roleID 瑙掕壊ID
     */
	void insert(@Param("userID") Integer userID, @Param("roleID") Integer roleID);

    /**
     * 鍒犻櫎鎸囧畾鐢ㄦ埛鐨勮鑹�
     * @param userID 鐢ㄦ埛ID
     */
	void deleteByUserID(Integer userID);

    /**
     * 鑾峰彇鎸囧畾 userID 瀵瑰簲鐢ㄦ埛鎷ユ湁鐨勮鑹�
     * @param userID 鐢ㄦ埛ID
     * @return 杩斿洖 userID 鎸囧畾鐢ㄦ埛鐨勮鑹�
     */
    List<RoleDO> selectUserRole(Integer userID);
}
