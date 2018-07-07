package com.ken.wms.security.filter;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.Deque;
import java.util.LinkedList;


public class KickoutSessionControlFilter extends AccessControlFilter {

    /**
     * 鐢ㄦ埛韪㈠嚭鍚庤烦杞湴鍧�
     */
    private String kickOutUrl;

    /**
     * 鏄惁韪㈠嚭涔嬪悗鐧婚檰鐨勭敤鎴�
     */
    private boolean kickOutAfter;

    /**
     * 鍚屼竴璐﹀彿鏈�澶х櫥闄嗘暟鐩�
     */
    private int maxSessionNum;

    private SessionManager sessionManager;
    private Cache<String, Deque<Serializable>> cache;

    public void setKickOutUrl(String kickOutUrl) {
        this.kickOutUrl = kickOutUrl;
    }

    public void setKickOutAfter(boolean kickOutAfter) {
        this.kickOutAfter = kickOutAfter;
    }

    public void setMaxSessionNum(int maxSessionNum) {
        this.maxSessionNum = maxSessionNum;
    }

    public void setSessionManager(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    public void setCacheManager(CacheManager cacheManager) {
        this.cache = cacheManager.getCache("sessionCache");
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        return false;
    }

    /**
     * 琛ㄧず璁块棶鎷掔粷鏃舵槸鍚﹁嚜宸卞鐞嗭紝濡傛灉杩斿洖true琛ㄧず鑷繁涓嶅鐞嗕笖缁х画鎷︽埅鍣ㄩ摼鎵ц锛�
     * 杩斿洖false琛ㄧず鑷繁宸茬粡澶勭悊浜嗭紙姣斿閲嶅畾鍚戝埌鍙︿竴涓〉闈級銆�
     * 鏍规嵁 isAccessAllowed 鏂规硶鐨勮繑鍥炲��
     *
     * @param servletRequest  request
     * @param servletResponse response
     * @return 杩斿洖鏄惁宸茬粡澶勭悊璁块棶鎷掔粷
     * @throws Exception exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        // 濡傛灉鐢ㄦ埛杩樻病鏈夌櫥闄嗗垯缁х画鍚庣画鐨勬祦绋�
        Subject subject = getSubject(servletRequest, servletResponse);
        if (!subject.isAuthenticated() && !subject.isRemembered())
            return true;

        // 鍒ゆ柇褰撳墠鐢ㄦ埛鐧婚檰鏁伴噺鏄惁瓒呭嚭
        Session session = subject.getSession();
        String userName = (String) subject.getPrincipal();
        Serializable sessionId = session.getId();

        // 鍒濆鍖栫敤鎴风殑鐧婚檰闃熷垪锛屽皢鐢ㄦ埛鐨勯槦鍒楁斁鍏ュ埌缂撳瓨涓�
        Deque<Serializable> deque = cache.get(userName);
        if (deque == null) {
            deque = new LinkedList<>();
            cache.put(userName, deque);
        }

        // 濡傛灉闃熷垪涓病鏈夋鐢ㄦ埛鐨� sessionId 涓旂敤鎴锋病鏈夎韪㈠嚭锛屽垯鏀惧叆闃熷垪
        if (!deque.contains(sessionId) && session.getAttribute("kickOut") == null) {
            deque.push(sessionId);
        }

        // 鑻ラ槦鍒椾腑鐨� sessionId 鏄惁瓒呭嚭鏈�澶т細璇濇暟鐩紝 鍒欒涪鍑虹敤鎴�
        while (deque.size() > maxSessionNum) {
            Serializable kickOutSessionId;
            if (kickOutAfter) {
                kickOutSessionId = deque.removeFirst();
            } else {
                kickOutSessionId = deque.removeLast();
            }

            // 璁剧疆 sessionId 瀵瑰簲鐨� session 涓殑瀛楁锛岃〃绀鸿鐢ㄦ埛宸茬粡琚涪鍑�
            try {
                Session kickOutSession = sessionManager.getSession(new DefaultSessionKey(kickOutSessionId));
                if (kickOutSession != null) {
                    kickOutSession.setAttribute("kickOut", true);
                }
            } catch (Exception e) {
                // do logging
                e.printStackTrace();
            }
        }

        // 濡傛灉褰撳墠鐧婚檰鐢ㄦ埛琚涪鍑猴紝鍒欓��鍑哄苟璺宠浆
        if (session.getAttribute("kickOut") != null && Boolean.TRUE.equals(session.getAttribute("kickOut"))) {
            try {
                // 鐧诲嚭
                subject.logout();

                // 鏍规嵁璇锋眰绫诲瀷浣滃嚭澶勭悊
                HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
                HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
                if (!"XMLHttpRequest".equalsIgnoreCase(httpServletRequest.getHeader("X-Requested-with"))) {
                    // 鏅�氳姹�
                    WebUtils.issueRedirect(httpServletRequest, httpServletResponse, kickOutUrl);
                } else {
                    // ajax 璇锋眰
                    httpServletResponse.setStatus(430);
                }

            } catch (Exception e) {
                // do nothing
            }
            return false;
        }

        return true;
    }
}
