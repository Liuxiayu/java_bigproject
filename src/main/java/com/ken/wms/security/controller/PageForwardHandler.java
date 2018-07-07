package com.ken.wms.security.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("/")
@Controller
public class PageForwardHandler {

    /**
     * 鍐呴儴閲嶅畾鍚戝埌鐧婚檰椤甸潰
     *
     * @return 璺宠浆鐨� jsp
     */
    @RequestMapping("login")
    public String loginPageForward() {
        // 鍒ゆ柇浣嗛挶鐢ㄦ埛鏄惁宸茬粡鐧婚檰
        Subject currentSubject = SecurityUtils.getSubject();
        if (!currentSubject.isAuthenticated())
            return "login";
        else
            return "mainPage";
    }

    /**
     * 鍐呴儴閲嶅畾鍚戝埌涓婚〉闈�
     *
     * @return 璺宠浆鐨� jsp
     */
    @RequestMapping("")
    public String showLoginView() {
        return "mainPage";
    }
}
