package com.agile.paperless.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: WuYL
 * @Description: 用于验证用户登录的前置网管
 * @Date: Create in 2018/5/22 17:20
 * @Modified By:
 * @Copyright: 2018 www.agilestarcn.com Inc. All rights reserved.
 */
public class AuthenPreFilter extends ZuulFilter{
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
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        String method = request.getMethod();
        System.out.println(method);
        if ("options".equalsIgnoreCase(method)){
            return false;
        }
        String token = request.getHeader("W-Token");
        String requestURI = request.getRequestURI();
        System.out.println("--------requestURI = " + requestURI);
        if ("/user/authen/login".equalsIgnoreCase(requestURI)){
            currentContext.setSendZuulResponse(true);
            return true;
        }
//        if (StringUtil.isNotEmpty(token)){
//            String user = redisClient.getStr(token);
//            if (StringUtil.isNotEmpty(user)){
//                //用户登录,，可以继续访问
//                currentContext.setSendZuulResponse(true);
//                return true;
//            }
//        }
        System.out.println("用户未登录");
//        currentContext.setSendZuulResponse(false);
//        currentContext.setResponseBody(GsonUtil.toJson(ResultUtil.error(ResultState.RESULT_UNLOGIN.code(), ResultState.RESULT_UNLOGIN.message())));
        return false;
    }

    @Override
    public Object run() {
        return null;
    }
}
