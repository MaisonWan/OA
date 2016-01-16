/**
 * 
 */
package com.icss.hit.component;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Administrator
 *
 */
public class StrutsActionFilter implements Filter{

   
    public void init(FilterConfig config) throws ServletException { 

    } 
  
    public void destroy() { 

    } 
  
    public void doFilter(ServletRequest request, ServletResponse response, 
                FilterChain chain) throws IOException, ServletException {
    	
    	HttpServletRequest re = (HttpServletRequest)request;
        HttpServletResponse rp = (HttpServletResponse)response;
        String url = re.getRequestURI();
        
        HttpSession session = re.getSession();
        // ÅÅ³ýµÇÂ½ÑéÖ¤Ò³
        if(url.indexOf("Login.do") > -1 
        		|| url.indexOf("login.jsp") > -1 
        		|| url.indexOf("newFolder.jsp") > -1
        		|| url.indexOf("progressbar.jsp") > -1
        		|| url.indexOf("progressdetail.jsp") > -1){
        	chain.doFilter(request, response);
        }
        else if( url.indexOf(".do") > -1 && session.getAttribute("UserId") == null ){
        	request.getRequestDispatcher("login.jsp").forward(request, response);
        }
        else if(url.indexOf(".jsp") > -1 ){
        	request.getRequestDispatcher("login.jsp").forward(request, response);
        }else{
        	chain.doFilter(request, response); 
        }
    }
}
