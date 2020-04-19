package com.dati.Filter;

import com.alibaba.fastjson.JSON;
import com.dati.Utils.ResultUtils;
import com.dati.exception.UserExcepResponse;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Configuration
public class LoginFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.PRE_DECORATION_FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        if (StringUtils.isEmpty(request.getHeader("Authorization"))
                && StringUtils.isEmpty(request.getRequestedSessionId())){
                UserExcepResponse userExcepResponse = new UserExcepResponse(501, "你还没登录..", false);
                String s = JSON.toJSONString(userExcepResponse);
                context.setSendZuulResponse(false);
                context.getResponse().setCharacterEncoding("UTF-8");
                context.getResponse().setContentType("application/json;charset=UTF-8");
                context.setResponseBody(s);
                return context;
        }
        context.setSendZuulResponse(true);
     return context;
}
}
