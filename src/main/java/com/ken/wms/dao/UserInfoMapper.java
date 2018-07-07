package com.ken.wms.dao;

import com.ken.wms.domain.UserInfoDO;

import java.util.List;


public interface UserInfoMapper {

    /**
     * 閫夋嫨鎸囧畾 id 鐨� user 淇℃伅
     *
     * @param userID 鐢ㄦ埛ID
     * @return 杩斿洖鎸囧畾 userID 瀵瑰簲鐨� user 淇℃伅
     */
    UserInfoDO selectByUserID(Integer userID);

    /**
     * 閫夋嫨鎸囧畾 userName 鐨� user 淇℃伅
     *
     * @param userName 鐢ㄦ埛鍚�
     * @return 杩斿洖鎸囧畾 userName 瀵瑰簲鐨� user 淇℃伅
     */
    UserInfoDO selectByName(String userName);

    /**
     * 閫夋嫨鍏ㄩ儴鐨� user 淇℃伅
     *
     * @return 杩斿洖鎵�鏈夌殑 user 淇℃伅
     */
    List<UserInfoDO> selectAll();


    /**
     * 鏇存柊 user 瀵硅薄淇℃伅
     *
     * @param user 鏇存柊 user 瀵硅薄淇℃伅
     */
    void update(UserInfoDO user);

    /**
     * 鍒犻櫎鎸囧畾 id 鐨剈ser 淇℃伅
     *
     * @param id 鐢ㄦ埛ID
     */
    void deleteById(Integer id);

    /**
     * 鎻掑叆涓�涓� user 瀵硅薄淇℃伅
     * 涓嶉渶鎸囧畾瀵硅薄鐨勪富閿甶d锛屾暟鎹簱鑷姩鐢熸垚
     *
     * @param user 闇�瑕佹彃鍏ョ殑鐢ㄦ埛淇℃伅
     */
    void insert(UserInfoDO user);

}
