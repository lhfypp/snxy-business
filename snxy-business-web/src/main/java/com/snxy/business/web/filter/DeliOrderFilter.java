package com.snxy.business.web.filter;


import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
@Configuration
@WebFilter(urlPatterns = "/delivery/seller/order/list")
public class DeliOrderFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

            filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {

    }
}

