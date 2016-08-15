package com.clt.framework.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
/**
 * songjt20151217
 * 防止sql注入，自定义filter 
 */ 
public class SqlInjectFilter implements Filter {
	 Logger logger = Logger.getLogger(SqlInjectFilter.class);
	 private static List<String> invalidsql = new ArrayList<String>(); 
	    private static String error = "/error.jsp"; 
	    private static boolean debug = false; 
	     
	    public void destroy() { 
	         
	    } 
	    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, ServletException { 
	        HttpServletRequest request = (HttpServletRequest)req; 
	        HttpServletResponse response = (HttpServletResponse)res; 	    	
/*	        if(debug){ 
	            System.out.println("prevent sql inject filter works"); 
	        } */
			String contentType=req.getContentType();
			if(contentType == null || contentType.startsWith("multipart/form-data")){
				filterChain.doFilter(req,res);	
			}else{
		        Map<String, String[]> params = request.getParameterMap(); 
		        Set<String> keys = params.keySet(); 
		        for(String key : keys){ 
		            String value = request.getParameter(key); 
/*		            if(debug){ 
		                System.out.println("process params <key, value>: <"+key+", "+value+">"); 
		            } */
		            for(String word : invalidsql){ 
		                if(word.equalsIgnoreCase(value) || value.contains(word)){
		                	
/*		                    if(value.contains("<")){ 
		                        value = value.replace("<", "＜"); 
		                    } 
		                    if(value.contains(">")){ 
		                        value = value.replace(">", "＞"); 
		                    } 		                    
		                    if(value.contains("'")){ 
		                        value = value.replace("'", "‘"); 
		                    } 
		                    if(value.contains("\"")){ 
		                        value = value.replace("\"", "“"); 
		                    } 
		                    if(value.contains("\\")){ 
		                        value = value.replace("\\", "＼"); 
		                    } 
		                    if(value.contains("#")){ 
		                        value = value.replace("#", "＃"); 
		                    } 		                    
		                    if(value.contains("(")){ 
		                    	value = value.replace("(", "（"); 
		                    } 		                    
		                    if(value.contains(")")){ 
		                    	value = value.replace(")", "）"); 
		                    } 	*/	                    
/*		                    request.getSession().setAttribute("sqlInjectError", "the request parameter \""+value+"\" contains keyword: \""+word+"\""); */
		                    request.getSession().setAttribute("sqlInjectError", "the request parameter  contains keyword: \""+word+"\""); 
		                    response.sendRedirect(request.getContextPath()+error); 
		                    return; 
		                } 
		            } 
		        } 
		        filterChain.doFilter(req, res); 
			}
	    } 
	    public void init(FilterConfig conf) throws ServletException { 
	        String sql = conf.getInitParameter("invalidsql"); 
	        String errorpage = conf.getInitParameter("error"); 
	        String de = conf.getInitParameter("debug"); 
	        if(errorpage != null){ 
	            error = errorpage; 
	        } 
	        if(sql != null){ 
	            invalidsql = Arrays.asList(sql.split("@")); 
	        } 
	        if(de != null && Boolean.parseBoolean(de)){ 
	            debug = true; 
	            System.out.println("PreventSQLInject Filter staring..."); 
	            System.out.println("print filter details"); 
	            System.out.println("invalid words as fllows (split with blank):"); 
	            for(String s : invalidsql){ 
	                System.out.print(s+" "); 
	            } 
	            System.out.println(); 
	            System.out.println("error page as fllows"); 
	            System.out.println(error); 
	            System.out.println(); 
	        } 
	    } 

}
