package com.sxg.cms.filter;

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

import org.apache.log4j.Logger;

import com.sxg.cms.entity.User;

public class LoginAccessFilter implements Filter{
	private final Logger logger = Logger.getLogger(this.getClass());
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;  
        HttpServletResponse resp = (HttpServletResponse) response;  
        String requestPath = req.getServletPath();
//        String path = req.getContextPath();  
        String basePath = req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort();  
//        if(basePath.indexOf("cs.ssdjz.com.cn")!=-1 || basePath.indexOf("sscsjjh.com")!=-1) {
//        	basePath = req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort();  
//        }
        
        if(requestPath.equals("/admin/login.html")) {
    		chain.doFilter(req, resp);  
    		return;
        }
        
        HttpSession session = req.getSession();  
        logger.info("-------------------------filter session "+session);
        String username = (String) session.getAttribute("username");  
        
        logger.info("-------------------------filter username "+username);
        User user = (User) session.getAttribute("user");  
        if (username == null || "".equals(username)) {  
            resp.sendRedirect(basePath+"/admin/login.html");  
        }else { 
        	if(requestPath.equals("/admin/user.html")&&!"0".equals(user.getType())) {
        		resp.sendRedirect(basePath+"/admin/login.html");  
            }else {
            	chain.doFilter(req, resp);  
            }
            
        }  
	}
	
	public void init(FilterConfig filterConfig) throws ServletException {}
	
	public void destroy() {}

}
