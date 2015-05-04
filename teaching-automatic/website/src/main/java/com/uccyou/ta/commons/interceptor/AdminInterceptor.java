package com.uccyou.ta.commons.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.uccyou.core.service.EndPoint;
import com.uccyou.ta.commons.constants.SessionConstants;

public class AdminInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Boolean online = (Boolean) session.getAttribute(SessionConstants.ONLINE);
        Integer adminId = (Integer) session.getAttribute(SessionConstants.ADMIN_ID);
        if (online != null && adminId != null && online) {
            return super.preHandle(request, response, handler);
        } else {
            response.sendRedirect(EndPoint.WEBSITE.getEndpoint() + "/homepage");
            return false;
        }
        
    }

}
