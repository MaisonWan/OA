package com.icss.hit.component;

import java.io.IOException;   
import javax.servlet.Filter;   
import javax.servlet.FilterChain;   
import javax.servlet.FilterConfig;   
import javax.servlet.ServletRequest;   
import javax.servlet.ServletResponse;   
import javax.servlet.ServletException;   
import javax.servlet.http.*;
  
public class EncodingFilter implements Filter{   
  
    @SuppressWarnings("unused")
	private FilterConfig config = null;    
    private String targetEncoding = "UTF-8"; 
    
  public void init(FilterConfig config) throws ServletException { 
    this.config = config; 
    this.targetEncoding = config.getInitParameter("encoding"); 
  } 
  
  public void destroy() { 
    config = null; 
    targetEncoding = null; 
  } 
  
  public void doFilter(ServletRequest request, ServletResponse response, 
                FilterChain chain) throws IOException, ServletException { 
 
    request.setCharacterEncoding(targetEncoding); 
/*    
    HttpServletRequest re = (HttpServletRequest)request;
    HttpServletResponse rp = (HttpServletResponse)response;
    String url = re.getRequestURI();
    
    HttpSession session = re.getSession();
    if( url.indexOf("login.jsp") == -1 && url.indexOf("img") == -1 && url.indexOf("login.do") == -1 &&
    		url.indexOf("Register.do") == -1 && url.indexOf("servlet") == -1 && url.indexOf("registConfirm.do") == -1 && 
    		url.indexOf("Images") == -1 && url.indexOf("css") == -1 && session.getAttribute("UserName") == null ){
    	request.getRequestDispatcher("login.jsp").forward(request, response);
    }*/
    //else{
    	
    	chain.doFilter(request, response); 
    //}
  } 
}