package com.ken.wms.util.aop;

import com.ken.wms.common.service.Interface.SystemLogService;
import com.ken.wms.exception.SystemLogServiceException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;


public class UserOperationLogging {

    @Autowired
    private SystemLogService systemLogService;

    /**
     * 璁板綍鐢ㄦ埛鎿嶄綔
     *
     * @param joinPoint 鍒囧叆鐐逛俊鎭�
     */
    public void loggingUserOperation(JoinPoint joinPoint, Object returnValue, UserOperation userOperation){

        if (userOperation != null) {
            // 鑾峰彇 annotation 鐨勫��
            String userOperationValue = userOperation.value();

            // 鑾峰彇璋冪敤鐨勬柟娉曞悕
            String methodName = joinPoint.getSignature().getName();

            // 鑾峰彇闄� import* export* 澶栫殑鏂规硶鐨勮繑鍥炲��
            String invokedResult = "-";
            if (!methodName.matches("^import\\w*") && !methodName.matches("^export\\w*")){
                if (returnValue instanceof Boolean) {
                    boolean result = (boolean) returnValue;
                    invokedResult = result ? "鎴愬姛" : "澶辫触";
                }
            }

            // 鑾峰彇鐢ㄦ埛淇℃伅
            Subject currentSubject = SecurityUtils.getSubject();
            Session session = currentSubject.getSession();
            Integer userID = (Integer) session.getAttribute("userID");
            String userName = (String) session.getAttribute("userName");

            // 鎻掑叆璁板綍
            try{
                systemLogService.insertUserOperationRecord(userID, userName, userOperationValue, invokedResult);
            } catch (SystemLogServiceException e) {
                // do log
            }
        }
    }
}
