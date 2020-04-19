package com.dati.shiroFilter;

import com.alibaba.fastjson.JSON;
import com.dati.Utils.ResultUtils;
import com.dati.exception.UserExcepResponse;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthenticatFilter extends BasicHttpAuthenticationFilter {
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        Subject subject = getSubject(request, response);
        if (subject.isAuthenticated()){
            return true;
        }
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse response1 = WebUtils.toHttp(response);
        UserExcepResponse userExcepResponse = new UserExcepResponse(501,"你还没有登录..", false);
        response1.setContentType("application/json;charset=UTF-8");
        response1.getWriter().write(JSON.toJSONString(userExcepResponse));
        return false;
    }
}
