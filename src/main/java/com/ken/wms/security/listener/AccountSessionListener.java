package com.ken.wms.security.listener;

import com.ken.wms.common.service.Interface.SystemLogService;
import com.ken.wms.dao.AccessRecordMapper;
import com.ken.wms.domain.AccessRecordDO;
import com.ken.wms.exception.SystemLogServiceException;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Date;


@Component
public class AccountSessionListener implements HttpSessionListener, ApplicationContextAware{

    @Autowired
    private SystemLogService systemLogService;

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        // 褰搒ession琚垱寤烘椂
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        // 鑾峰彇鐢ㄦ埛鐨剆ession
        HttpSession session = se.getSession();

        // 鍒ゆ柇鏄惁涓哄凡缁忕櫥闄嗙殑鐢ㄦ埛
        // 鍒ゆ柇渚濇嵁鏄痠sAuthentication鐨勫��
        try {
            String isAuthenticate = (String) session.getAttribute("isAuthenticate");
            if (isAuthenticate != null && isAuthenticate.equals("true")) {
                Integer userID = (Integer) session.getAttribute("userID");
                String userName = (String) session.getAttribute("userName");
                String accessIP = "-";
                systemLogService.insertAccessRecord(userID, userName, accessIP, SystemLogService.ACCESS_TYPE_LOGOUT);
            }
        } catch (SystemLogServiceException e) {
            // do log
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (applicationContext instanceof WebApplicationContext){
            ((WebApplicationContext)applicationContext).getServletContext().addListener(this);
        }else{
            throw new RuntimeException();
        }
    }
}
