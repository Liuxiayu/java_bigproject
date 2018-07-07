package com.ken.wms.security.service.Interface;

import com.ken.wms.domain.UserInfoDTO;
import com.ken.wms.exception.UserInfoServiceException;

import java.util.List;
import java.util.Set;


public interface UserInfoService {

    /**
     * 鑾峰彇鎸囧畾鐢ㄦ埛ID瀵瑰簲鐨勭敤鎴疯处鎴蜂俊鎭�
     * @param userID 鐢ㄦ埛ID
     * @return 杩斿洖鐢ㄦ埛璐︽埛淇℃伅
     */
    UserInfoDTO getUserInfo(Integer userID) throws UserInfoServiceException;

    /**
     * 鑾峰彇鎸囧畾 userName 瀵瑰簲鐨勭敤鎴疯处鎴蜂俊鎭�
     * @param userName 鐢ㄦ埛鍚�
     * @return 杩斿洖鐢ㄦ埛璐︽埛淇℃伅
     */
    UserInfoDTO getUserInfo(String userName) throws UserInfoServiceException;

    /**
     * 鑾峰彇鎵�鏈夌敤鎴疯处鎴蜂俊鎭�
     * @return 杩斿洖鎵�鏈夌殑鐢ㄦ埛璐︽埛淇℃伅
     */
    List<UserInfoDTO> getAllUserInfo() throws UserInfoServiceException;

    /**
     * 鏇存柊鐢ㄦ埛鐨勮处鎴蜂俊鎭�
     * @param userInfoDTO 鐢ㄦ埛璐︽埛淇℃伅
     */
    void updateUserInfo(UserInfoDTO userInfoDTO) throws UserInfoServiceException;

    /**
     * 鍒犻櫎鎸囧畾 userID 鐨勭敤鎴疯处鎴蜂俊鎭�
     * @param userID 鎸囧畾鐨勭敤鎴稩D
     */
    void deleteUserInfo(Integer userID) throws UserInfoServiceException;

    /**
     * 娣诲姞涓�鏉＄敤鎴疯处鎴蜂俊鎭�
     * @param userInfoDTO 闇�瑕佹坊鍔犵殑鐢ㄦ埛璐︽埛淇℃伅
     */
    boolean insertUserInfo(UserInfoDTO userInfoDTO) throws UserInfoServiceException;

    /**
     * 鑾峰彇鐢ㄦ埛鐨勬潈闄愯鑹�
     * @param userID 鐢ㄦ埛 ID
     * @return 杩斿洖涓�涓繚瀛樻湁鐢ㄦ埛瑙掕壊鐨� Set锛岃嫢璇ョ敤鎴锋病鏈変换浣曡鑹诧紝鍒欒繑鍥炰竴涓笉鍖呭惈浠讳綍鍏冪礌鐨� Set
     */
    Set<String> getUserRoles(Integer userID) throws UserInfoServiceException;
}
