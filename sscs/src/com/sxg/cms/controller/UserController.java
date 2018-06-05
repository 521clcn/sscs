package com.sxg.cms.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.sxg.cms.entity.User;
import com.sxg.cms.service.UserService;

@Controller

@RequestMapping("/user")

public class UserController {
	private final Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/login")
	public void login(HttpServletRequest request, HttpServletResponse response, 
			@RequestParam("username") String username, @RequestParam("password") String password) {
		Map<String, Object> result = new HashMap<String, Object>();
		String path = request.getContextPath();  
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
		try {
			logger.info("-------------------------login begin");
			User user = userService.login(username, password);
			logger.info("-------------------------login user "+user);
			if (user != null) {
				logger.info("-------------------------login request "+request);
				HttpSession session = request.getSession();
				logger.info("-------------------------login session "+session);
				
		        String requestPath = request.getServletPath();
		        
		        if(basePath.indexOf("sx.ssdjz.com.cn")!=-1) {
		        	basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();  
		        }
		        logger.info("-------------------------filter basePath "+basePath);
		        logger.info("-------------------------filter requestPath "+requestPath);
		        logger.info("-------------------------filter Path "+basePath+requestPath);
		        
				session.setAttribute("user", user);
				session.setAttribute("username", user.getUsername());
//				response.sendRedirect("../../admin/main.html");
				response.sendRedirect(basePath+"/admin/main.html");
				
				logger.info("-------------------------login end ");
			}else {
				logger.info("-------------------------login error ");
				response.sendRedirect(basePath+"/login.jsp?msg=error");
			}
			result.put("suc", "yes");
			result.put("data", user);

		} catch (Exception e) {
			result.put("suc", "no");
			result.put("msg", "error");
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/list")
	public Map<String,Object> list() {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			List <User> list = userService.list();
			result.put("suc", "yes");
			result.put("data", list);

		} catch (Exception e) {
			result.put("suc", "no");
			result.put("msg", "error");
		}
		return result;

	}
	
	@ResponseBody
	@RequestMapping(value = "/user")
	public Map<String,Object> user(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			HttpSession session = request.getSession();
			result.put("suc", "yes");
			result.put("data", session.getAttribute("user"));

		} catch (Exception e) {
			result.put("suc", "no");
			result.put("msg", "error");
		}
		return result;

	}
	
	@ResponseBody
	@RequestMapping(value = "/save")
	public Map<String,Object> save(@RequestParam(value="id",required=false) String id,
			@RequestParam("username") String username,
			@RequestParam("password") String password,
			@RequestParam("showname") String showname,
			@RequestParam("accessid") String accessid) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			userService.save(id,username, password,showname,accessid);
			result.put("suc", "yes");
			result.put("msg", "success");

		} catch (Exception e) {
			result.put("suc", "no");
			result.put("msg", "error");
		}
		return result;

	}
	
	@ResponseBody
	@RequestMapping(value = "/delete")
	public Map<String,Object> delete(@RequestParam("id") String id) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			userService.delete(id);
			result.put("suc", "yes");
			result.put("msg", "success");

		} catch (Exception e) {
			result.put("suc", "no");
			result.put("msg", "error");
		}
		return result;

	}
	
	@ResponseBody
	@RequestMapping(value = "/password")
	public Map<String,Object> password(HttpServletRequest request,@RequestParam("password") String password) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			user.setPassword(password);
			userService.save(user);
			result.put("suc", "yes");
			result.put("msg", "success");

		} catch (Exception e) {
			result.put("suc", "no");
			result.put("msg", "error");
		}
		return result;

	}
	
	@ResponseBody
	@RequestMapping(value = "/upload", method = { RequestMethod.POST })
	public Map<String, Object> upload(HttpServletRequest request,HttpServletResponse response, @RequestParam("vedioFile") MultipartFile vedioFile) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {	     
			String path = request.getServletContext().getRealPath("/");
			File vedioPath = new File(path+"resource/vedio.mp4");
			if(vedioPath.exists()){
				vedioPath.delete();
			}
			vedioFile.transferTo(vedioPath);

			result.put("suc", "yes");
			result.put("msg", "导入成功");

		} catch (Exception e) {
			result.put("suc", "no");
			result.put("msg", e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping(value = "/logout")
	public void logout(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			HttpSession session = request.getSession();
			session.removeAttribute("user");
			session.removeAttribute("username");
			session.invalidate();
			
			response.sendRedirect("../login.jsp");
			result.put("suc", "yes");
		} catch (Exception e) {
			result.put("suc", "no");
			result.put("msg", e.getMessage());
		}
	}
}