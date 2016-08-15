package com.clt.framework.filter;

/**
 * 防止脚本攻击，自定义filter 
 */ 
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class XssFilter implements Filter {
	
	private String filterChar;
	private String replaceChar;
	private String splitChar;
	FilterConfig filterConfig = null;
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterChar=filterConfig.getInitParameter("FilterChar");
		this.replaceChar=filterConfig.getInitParameter("ReplaceChar");
		this.splitChar=filterConfig.getInitParameter("SplitChar");
		this.filterConfig = filterConfig;
	}

	public void destroy() {
		this.filterConfig = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException {		
/*		String temp = ((HttpServletRequest)request).getRequestURI();  
		if (temp.endsWith("/personal/intention2.do") ||temp.endsWith("personal/myPublish.do") ||temp.endsWith("personal/contact.do")){
			chain.doFilter(request,response);	
		}else{

		}*/
		
		String contentType=request.getContentType();
		if(contentType == null || contentType.startsWith("multipart/form-data")){
			chain.doFilter(request,response);	
		}else{
			//目前会导致controler中如果加了后一句则报406，,produces="text/html;charset=UTF-8"
			chain.doFilter(new XssHttpServletRequestWrapper((HttpServletRequest) request,filterChar,replaceChar,splitChar), response);
		}
		
		
	}
}
