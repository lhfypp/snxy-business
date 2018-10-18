package com.snxy.business.web.filter;


import com.snxy.common.util.StringUtil;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
@WebFilter(urlPatterns = "/delivery/seller/order/list")
public class DeliOrderFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
            String userId=servletRequest.getParameter("userId");
            if(StringUtil.isBlank(userId)){
                servletRequest.getRequestDispatcher("/failed").forward(servletRequest, servletResponse);
            }else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
    }

    @Override
    public void destroy() {

    }
}

