package com.ken.wms.security.service.Impl;


import com.ken.wms.domain.UserInfoDTO;
import com.ken.wms.exception.UserAccountServiceException;
import com.ken.wms.exception.UserInfoServiceException;
import com.ken.wms.security.service.Interface.AccountService;
import com.ken.wms.security.service.Interface.UserInfoService;
import com.ken.wms.security.util.EncryptingModel;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.Map;


@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private EncryptingModel encryptingModel;


    private static final String OLD_PASSWORD = "oldPassword";
    private static final String NEW_PASSWORD = "newPassword";
    private static final String REPEAT_PASSWORD = "rePassword";

    /**
     * 瀵嗙爜鏇存敼
     */
    @Override
    public void passwordModify(Integer userID, Map<String, Object> passwordInfo) throws UserAccountServiceException {

        if (passwordInfo == null)
            throw new UserAccountServiceException(UserAccountServiceException.PASSWORD_ERROR);

        // 鑾峰彇鏇存敼瀵嗙爜淇℃伅
        String rePassword = (String) passwordInfo.get(REPEAT_PASSWORD);
        String newPassword = (String) passwordInfo.get(NEW_PASSWORD);
        String oldPassword = (String) passwordInfo.get(OLD_PASSWORD);
        if (rePassword == null || newPassword == null || oldPassword == null)
            throw new UserAccountServiceException(UserAccountServiceException.PASSWORD_ERROR);

        try {
            // 鑾峰彇鐢ㄦ埛鐨勮处鎴蜂俊鎭�
            UserInfoDTO user = userInfoService.getUserInfo(userID);
            if (user == null) {
                throw new UserAccountServiceException(UserAccountServiceException.PASSWORD_ERROR);
            }

            // 鏂板瘑鐮佷竴鑷存�ч獙璇�
            if (!newPassword.equals(rePassword)) {
                throw new UserAccountServiceException(UserAccountServiceException.PASSWORD_UNMATCH);
            }

            // 鍘熷瘑鐮佹纭�ч獙璇�
            String password;
            password = encryptingModel.MD5(oldPassword + userID);
            if (!password.equals(user.getPassword()))
                throw new UserAccountServiceException(UserAccountServiceException.PASSWORD_ERROR);

            // 鑾峰緱鏂扮殑瀵嗙爜骞跺姞瀵�
            password = encryptingModel.MD5(newPassword + userID);

            // 楠岃瘉鎴愬姛鍚庢洿鏂版暟鎹簱
            user.setPassword(password);
            user.setFirstLogin(false);
            userInfoService.updateUserInfo(user);

            // 鏇存柊瀵嗙爜淇敼淇℃伅(鏄惁涓哄垵娆′慨鏀瑰瘑鐮�)
            Subject currentSubject = SecurityUtils.getSubject();
            Session session = currentSubject.getSession();
            session.setAttribute("firstLogin", false);

        } catch (NoSuchAlgorithmException | NullPointerException | UserInfoServiceException e) {
            throw new UserAccountServiceException(UserAccountServiceException.PASSWORD_ERROR);
        }

    }

}
