package com.clt.framework.filter;



import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by liujinbang on 15/9/15.
 */
public class FrameworkFilter implements Filter {

    org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(FrameworkFilter.class);
    private  String  encodeString = "UTF-8";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
           try {
               encodeString=filterConfig.getInitParameter("encoding");
           }catch (Exception e){

               logger.error("there is no 'encoding' properity in filter setting!");
               e.printStackTrace();
             //  throw  new FrameworkException("init framework deault encoding error;");
           }

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
            /**
             * 配置全局字符编码方式
             */
        servletRequest.setCharacterEncoding(encodeString);

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;


       // System.out.println(request.getRequestURI());

        filterChain.doFilter(request, response);


    }

    @Override
    public void destroy() {

    }
}



