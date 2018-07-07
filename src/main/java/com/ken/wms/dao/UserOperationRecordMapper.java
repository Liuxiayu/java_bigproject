package com.ken.wms.dao;

import com.ken.wms.domain.UserOperationRecordDO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;


public interface UserOperationRecordMapper {

    /**
     * 閫夋嫨鎸囧畾鐢ㄦ埛ID锛屾垨鏃堕棿鑼冨洿鐨勭敤鎴锋搷浣滆褰�
     *
     * @param userID    鎸囧畾鐨勭敤鎴稩D
     * @param startDate 璁板綍鐨勮捣濮嬫棩鏈�
     * @param endDate   璁板綍鐨勭粨鏉熸棩鏈�
     * @return 杩斿洖鎵�鏈夌鍚堟潯浠剁殑璁板綍
     */
    List<UserOperationRecordDO> selectUserOperationRecord(@Param("userID") Integer userID,
                                                          @Param("startDate") Date startDate,
                                                          @Param("endDate") Date endDate);

    /**
     * 鎻掑叆鐢ㄦ埛鎿嶄綔璁板綍
     *
     * @param userOperationRecordDO 鐢ㄦ埛鎿嶄綔璁板綍
     */
    void insertUserOperationRecord(UserOperationRecordDO userOperationRecordDO);
}
