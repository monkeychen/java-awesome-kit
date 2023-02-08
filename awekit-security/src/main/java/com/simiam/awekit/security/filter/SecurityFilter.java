package com.simiam.awekit.security.filter;

import com.simiam.awekit.security.shiro.ShiroConfiguration;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * <p>Title: SecurityFilter</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.SIMIAM Co., Ltd. (c) 2019</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2019/12/20 8:48 上午</p>
 */
@WebFilter(urlPatterns = {"/**"})
public class SecurityFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(SecurityFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        Subject subject = SecurityUtils.getSubject();
        if (subject == null) {
            logger.trace("The subject is null, so ignored!");
            return;
        }
        request.setAttribute(ShiroConfiguration.SECURITY_TOKEN, subject.getSession().getId());
    }
}
