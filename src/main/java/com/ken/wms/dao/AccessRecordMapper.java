package com.ken.wms.dao;

import com.ken.wms.domain.AccessRecordDO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;


public interface AccessRecordMapper {

    /**
     * 鎻掑叆涓�鏉＄敤鎴风敤鎴风櫥鍏ョ櫥鍑鸿褰�
     *
     * @param accessRecordDO 鐢ㄦ埛鐧诲叆鐧诲嚭璁板綍
     */
    void insertAccessRecord(AccessRecordDO accessRecordDO);

    /**
     * 閫夋嫨鎸囧畾鐢ㄦ埛ID銆佽褰曠被鍨嬨�佹椂闂磋寖鍥寸殑鐧诲叆鐧诲嚭璁板綍
     *
     * @param userID     鐢ㄦ埛ID
     * @param accessType 璁板綍绫诲瀷锛堢櫥鍏ャ�佺櫥鍑烘垨鎵�鏈夛級
     * @param startDate  璁板綍鐨勮捣濮嬫棩鏈�
     * @param endDate    璁板綍鐨勭粨鏉熸棩鏈�
     * @return 杩斿洖鎵�鏈夌鍚堟潯浠剁殑璁板綍
     */
    List<AccessRecordDO> selectAccessRecords(@Param("userID") Integer userID,
                                             @Param("accessType") String accessType,
                                             @Param("startDate") Date startDate,
                                             @Param("endDate") Date endDate);
}
