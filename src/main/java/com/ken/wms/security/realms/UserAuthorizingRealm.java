package com.ken.wms.security.realms;

import com.ken.wms.common.service.Interface.RepositoryAdminManageService;
import com.ken.wms.common.service.Interface.SystemLogService;
import com.ken.wms.domain.RepositoryAdmin;
import com.ken.wms.domain.UserInfoDTO;
import com.ken.wms.exception.RepositoryAdminManageServiceException;
import com.ken.wms.exception.UserInfoServiceException;
import com.ken.wms.security.service.Interface.UserInfoService;
import com.ken.wms.security.util.EncryptingModel;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class UserAuthorizingRealm extends AuthorizingRealm {

    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private EncryptingModel encryptingModel;
    @Autowired
    private RepositoryAdminManageService repositoryAdminManageService;
    @Autowired
    private SystemLogService systemLogService;

    /**
     * 瀵圭敤鎴疯繘琛岃鑹叉巿鏉�
     *
     * @param principalCollection 鐢ㄦ埛淇℃伅
     * @return 杩斿洖鐢ㄦ埛鎺堟潈淇℃伅
     */
    @SuppressWarnings("unchecked")
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 鍒涘缓瀛樻斁鐢ㄦ埛瑙掕壊鐨� Set
        Set<String> roles = new HashSet<>();

        //鑾峰彇鐢ㄦ埛瑙掕壊
        Object principal = principalCollection.getPrimaryPrincipal();
        if (principal instanceof String) {
            String userID = (String) principal;
            if (StringUtils.isNumeric(userID)) {
                try {
                    UserInfoDTO userInfo = userInfoService.getUserInfo(Integer.valueOf(userID));
                    if (userInfo != null) {
                        // 璁剧疆鐢ㄦ埛瑙掕壊
                        roles.addAll(userInfo.getRole());
                    }
                } catch (UserInfoServiceException e) {
                    // do logger
                }
            }
        }

        return new SimpleAuthorizationInfo(roles);
    }

    /**
     * 瀵圭敤鎴疯繘琛岃璇�
     *
     * @param authenticationToken 鐢ㄦ埛鍑瘉
     * @return 杩斿洖鐢ㄦ埛鐨勮璇佷俊鎭�
     * @throws AuthenticationException 鐢ㄦ埛璁よ瘉寮傚父淇℃伅
     */
    @SuppressWarnings("unchecked")
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws
            AuthenticationException {

        String realmName = getName();
        String credentials = "";

        // 鑾峰彇鐢ㄦ埛鍚嶅搴旂殑鐢ㄦ埛璐︽埛淇℃伅
        try {
            UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
            String principal = usernamePasswordToken.getUsername();

            if (!StringUtils.isNumeric(principal))
                throw new AuthenticationException();
            Integer userID = Integer.valueOf(principal);

            UserInfoDTO userInfoDTO = userInfoService.getUserInfo(userID);

            if (userInfoDTO != null) {
                Subject currentSubject = SecurityUtils.getSubject();
                Session session = currentSubject.getSession();

                // 璁剧疆閮ㄥ垎鐢ㄦ埛淇℃伅鍒� Session
                session.setAttribute("userID", userID);
                session.setAttribute("userName", userInfoDTO.getUserName());
                session.setAttribute("firstLogin", userInfoDTO.getFirstLogin());
                List<RepositoryAdmin> repositoryAdmin = (List<RepositoryAdmin>) repositoryAdminManageService.selectByID(userInfoDTO.getUserID()).get("data");
                session.setAttribute("repositoryBelong", "none");
                if (!repositoryAdmin.isEmpty()){
                    Integer repositoryBelong = repositoryAdmin.get(0).getRepositoryBelongID();
                    if (repositoryBelong != null)
                        session.setAttribute("repositoryBelong", repositoryBelong);
                }


                // 缁撳悎楠岃瘉鐮佸瀵嗙爜杩涜澶勭悊
                String checkCode = (String) session.getAttribute("checkCode");
                String password = userInfoDTO.getPassword();
                if (checkCode != null && password != null) {
                    checkCode = checkCode.toUpperCase();
                    credentials = encryptingModel.MD5(password + checkCode);
                    System.out.println("credentials---"+credentials);
                }
            }
            return new SimpleAuthenticationInfo(principal, credentials, realmName);

        } catch (UserInfoServiceException | RepositoryAdminManageServiceException | NoSuchAlgorithmException e) {
            throw new AuthenticationException();
        }
    }
}
