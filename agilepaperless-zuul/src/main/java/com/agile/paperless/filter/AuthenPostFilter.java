package com.agile.paperless.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.util.StreamUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * @Author: WuYL
 * @Description:
 * @Date: Create in 2018/5/22 17:20
 * @Modified By:
 * @Copyright: 2018 www.agilestarcn.com Inc. All rights reserved.
 */
public class AuthenPostFilter extends ZuulFilter{
    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.SEND_RESPONSE_FILTER_ORDER - 2;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        HttpServletResponse response = currentContext.getResponse();
        String method = request.getMethod();
        InputStream dataStream = null;
        try {
            dataStream = currentContext.getResponseDataStream();
            String bodyData = StreamUtils.copyToString(dataStream, Charset.forName("UTF-8"));
//                if (StringUtil.isNotEmpty(bodyData)){
//                    ObjectResult result = GsonUtil.fromJson(bodyData, ObjectResult.class);
//                    response.setHeader("Access-Control-Allow-Origin", "*");
//                    response.addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, W-Token");
//                    response.addHeader("Access-Control-Allow-Origin", "*");
//                    currentContext.setResponseBody(bodyData);
//                }
            response.addHeader("Access-Control-Allow-Origin", "*");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(null != dataStream){
                try {
                    dataStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return false;
    }

    @Override
    public Object run() {
        return null;
    }
}
