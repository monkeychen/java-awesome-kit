package com.simiam.awekit.web.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>Title: ResponseHeaderFilter</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.CMCC Co., Ltd. (c) 2019</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2019/3/27 17:36</p>
 */
@WebFilter(urlPatterns = {"/js/*", "/css/*"})
public class ResponseHeaderFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(ResponseHeaderFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String url = httpServletRequest.getServletPath();
        if (url.contains("jquery-")) {
            chain.doFilter(request, response);
            return;
        }
//        logger.debug("Http request path = {}", url);
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Pragma", "No-Cache");
        httpServletResponse.setHeader("Cache-Control", "No-Cache");
        httpServletResponse.setDateHeader("Expires", 0);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
