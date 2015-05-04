package com.uccyou.ta.commons.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.uccyou.core.service.EndPoint;
import com.uccyou.ta.commons.constants.SessionConstants;

public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Boolean online = (Boolean) session.getAttribute(SessionConstants.ONLINE);
        if (online == null || !online) {
            response.sendRedirect(EndPoint.WEBSITE.getEndpoint() + "/");
            return false;
        }
        return super.preHandle(request, response, handler);
    }

}
