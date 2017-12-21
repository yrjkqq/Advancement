package com.cdsxt.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Description:
 * <br>Program name: LoveApartment
 * <br>Date: on 15:35 2017/12/3
 *
 * @author yuang
 * @version 1.0
 */
@WebFilter(filterName = "EncodingFilter")
public class EncodingFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        request.setCharacterEncoding("utf-8");
        response.setHeader("content-type", "text/html;charset=utf-8");
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
