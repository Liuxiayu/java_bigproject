package com.ken.wms.security.service.Impl;

import com.ken.wms.dao.RolesMapper;
import com.ken.wms.dao.UserInfoMapper;
import com.ken.wms.dao.UserPermissionMapper;
import com.ken.wms.domain.RoleDO;
import com.ken.wms.domain.UserInfoDO;
import com.ken.wms.domain.UserInfoDTO;
import com.ken.wms.exception.UserInfoServiceException;
import com.ken.wms.security.service.Interface.UserInfoService;
import com.ken.wms.security.util.EncryptingModel;
import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private UserPermissionMapper userPermissionMapper;
    @Autowired
    private EncryptingModel encryptingModel;
    @Autowired
    private RolesMapper rolesMapper;
    @Autowired
    UserInfoService userInfoService;

    /**
     * 鑾峰彇鎸囧畾鐢ㄦ埛ID瀵瑰簲鐨勭敤鎴疯处鎴蜂俊鎭�
     *
     * @param userID 鐢ㄦ埛ID
     * @return 杩斿洖鐢ㄦ埛璐︽埛淇℃伅
     */
    @Override
    public UserInfoDTO getUserInfo(Integer userID) throws UserInfoServiceException {
        if (userID == null)
            return null;

        try {
            // 鑾峰彇鐢ㄦ埛淇℃伅
            UserInfoDO userInfoDO = userInfoMapper.selectByUserID(userID);
            // 鑾峰彇鐢ㄦ埛瑙掕壊淇℃伅
            List<RoleDO> roles = userPermissionMapper.selectUserRole(userID);

            return assembleUserInfo(userInfoDO, roles);
        } catch (PersistenceException e) {
            throw new UserInfoServiceException(e);
        }
    }

    /**
     * 鑾峰彇鎸囧畾 userName 瀵瑰簲鐨勭敤鎴疯处鎴蜂俊鎭�
     *
     * @param userName 鐢ㄦ埛鍚�
     * @return 杩斿洖鐢ㄦ埛璐︽埛淇℃伅
     */
    @Override
    public UserInfoDTO getUserInfo(String userName) throws UserInfoServiceException {
        if (userName == null)
            return null;

        try {
            // 鑾峰彇鐢ㄦ埛淇℃伅
            UserInfoDO userInfoDO = userInfoMapper.selectByName(userName);
            // 鑾峰彇鐢ㄦ埛瑙掕壊淇℃伅
            if (userInfoDO != null) {
                List<RoleDO> roles = userPermissionMapper.selectUserRole(userInfoDO.getUserID());
                return assembleUserInfo(userInfoDO, roles);
            } else
                return null;
        } catch (PersistenceException e) {
            throw new UserInfoServiceException(e);
        }
    }

    /**
     * 鑾峰彇鎵�鏈夌敤鎴疯处鎴蜂俊鎭�
     *
     * @return 杩斿洖鎵�鏈夌殑鐢ㄦ埛璐︽埛淇℃伅
     */
    @Override
    public List<UserInfoDTO> getAllUserInfo() throws UserInfoServiceException {
        // 淇濆瓨鎵�鏈夌敤鎴风殑 UserInfoDTO 瀵硅薄
        List<UserInfoDTO> userInfoDTOS = null;

        // 鑾峰彇鎵�鏈夌敤鎴风殑璐︽埛淇℃伅锛堜笉鍖呭惈瑙掕壊淇℃伅锛�
        try {
            List<UserInfoDO> userInfoDOS = userInfoMapper.selectAll();
            if (userInfoDOS != null) {
                List<RoleDO> roles;
                UserInfoDTO userInfoDTO;
                userInfoDTOS = new ArrayList<>(userInfoDOS.size());
                for (UserInfoDO userInfoDO : userInfoDOS) {
                    // 鑾峰彇鐢ㄦ埛瀵瑰簲鐨勮鑹蹭俊鎭�
                    roles = userPermissionMapper.selectUserRole(userInfoDO.getUserID());
                    userInfoDTO = assembleUserInfo(userInfoDO, roles);
                    userInfoDTOS.add(userInfoDTO);
                }
            }

            return userInfoDTOS;
        } catch (PersistenceException e) {
            throw new UserInfoServiceException(e);
        }
    }

    /**
     * 鏇存柊鐢ㄦ埛鐨勮处鎴蜂俊鎭�
     *
     * @param userInfoDTO 鐢ㄦ埛璐︽埛淇℃伅
     */
    @Override
    public void updateUserInfo(UserInfoDTO userInfoDTO) throws UserInfoServiceException {
        if (userInfoDTO != null) {
            try {
                // 鏇存柊 UserDo 瀵硅薄淇℃伅
                Integer userID = userInfoDTO.getUserID();
                String userName = userInfoDTO.getUserName();
                String password = userInfoDTO.getPassword();
                if (userID != null && userName != null && password != null) {
                    UserInfoDO userInfoDO = new UserInfoDO();
                    userInfoDO.setUserID(userID);
                    userInfoDO.setUserName(userName);
                    userInfoDO.setPassword(password);
                    userInfoDO.setFirstLogin(userInfoDTO.getFirstLogin() ? 1 : 0);

                    // update
                    userInfoMapper.update(userInfoDO);
                }

                // 鏇存柊瑙掕壊淇℃伅
            } catch (PersistenceException e) {
                throw new UserInfoServiceException(e);
            }
        }

    }

    /**
     * 鍒犻櫎鎸囧畾 userID 鐨勭敤鎴疯处鎴蜂俊鎭�
     *
     * @param userID 鎸囧畾鐨勭敤鎴稩D
     */
    @Override
    public void deleteUserInfo(Integer userID) throws UserInfoServiceException {
        if (userID == null)
            return;

        try {
            // 鍒犻櫎鐢ㄦ埛瑙掕壊淇℃伅
            userPermissionMapper.deleteByUserID(userID);

            // 鍒犻櫎鐢ㄦ埛淇℃伅
            userInfoMapper.deleteById(userID);
        } catch (PersistenceException e) {
            throw new UserInfoServiceException(e);
        }

    }

    /**
     * 娣诲姞涓�鏉＄敤鎴疯处鎴蜂俊鎭�
     *
     * @param userInfoDTO 闇�瑕佹坊鍔犵殑鐢ㄦ埛璐︽埛淇℃伅
     */
    @Override
    public boolean insertUserInfo(UserInfoDTO userInfoDTO) throws UserInfoServiceException {
        if (userInfoDTO == null)
            return false;

        // 妫�鏌ユ暟鎹槸鍚︽湁鏁�
        Integer userID = userInfoDTO.getUserID();
        String userName = userInfoDTO.getUserName();
        String password = userInfoDTO.getPassword();
        if (userName == null || password == null)
            return false;

        try {
            // 瀵瑰瘑鐮佽繘琛屽姞瀵�
            String tempStr = encryptingModel.MD5(password);
            String encryptPassword = encryptingModel.MD5(tempStr + userID.toString());

            // 鍒涘缓鐢ㄦ埛淇℃伅鏁版嵁瀹炰綋
            UserInfoDO userInfoDO = new UserInfoDO();
            userInfoDO.setUserID(userID);
            userInfoDO.setUserName(userName);
            userInfoDO.setPassword(encryptPassword);
            userInfoDO.setFirstLogin(1);

            // 鎸佷箙鍖栫敤鎴蜂俊鎭�
            userInfoMapper.insert(userInfoDO);

            // 鑾峰彇鐢ㄦ埛瑙掕壊淇℃伅
            List<String> roles = userInfoDTO.getRole();
            Integer roleID;

            // 鎸佷箙鍖栫敤鎴疯鑹蹭俊鎭�
            for (String role : roles) {
                roleID = rolesMapper.getRoleID(role);
                if (roleID != null)
                    userPermissionMapper.insert(userID, roleID);
                else
                    throw new UserInfoServiceException("The role of userInfo unavailable");
            }

            return true;

        } catch (NoSuchAlgorithmException | PersistenceException e) {
            throw new UserInfoServiceException(e);
        }
    }

    /**
     * 缁勮 UserInfoDTO 瀵硅薄锛屽寘鎷敤鎴疯处鎴蜂俊鎭拰瑙掕壊淇℃伅
     *
     * @param userInfoDO 鐢ㄦ埛璐︽埛淇℃伅
     * @param roles      鐢ㄦ埛瑙掕壊淇℃伅
     * @return 杩斿洖缁勮鍚庣殑UserInfoDTO
     */
    private UserInfoDTO assembleUserInfo(UserInfoDO userInfoDO, List<RoleDO> roles) {
        UserInfoDTO userInfoDTO = null;
        if (userInfoDO != null && roles != null) {
            userInfoDTO = new UserInfoDTO();
            userInfoDTO.setUserID(userInfoDO.getUserID());
            userInfoDTO.setUserName(userInfoDO.getUserName());
            userInfoDTO.setPassword(userInfoDO.getPassword());
            userInfoDTO.setFirstLogin(userInfoDO.getFirstLogin() == 1);

            for (RoleDO role : roles) {
                userInfoDTO.getRole().add(role.getRoleName());
            }
        }
        return userInfoDTO;
    }

    /**
     * 鑾峰彇鐢ㄦ埛鐨勬潈闄愯鑹�
     *
     * @param userID 鐢ㄦ埛 ID
     * @return 杩斿洖涓�涓繚瀛樻湁鐢ㄦ埛瑙掕壊鐨� Set锛岃嫢璇ョ敤鎴锋病鏈変换浣曡鑹诧紝鍒欒繑鍥炰竴涓笉鍖呭惈浠讳綍鍏冪礌鐨� Set
     */
    @Override
    public Set<String> getUserRoles(Integer userID) throws UserInfoServiceException {
        // 鑾峰彇鐢ㄦ埛淇℃伅
        UserInfoDTO userInfo = getUserInfo(userID);

        // 杩斿洖鐢ㄦ埛鐨勮鑹�
        if (userInfo != null) {
            return new HashSet<>(userInfo.getRole());
        } else {
            return new HashSet<>();
        }
    }

}
