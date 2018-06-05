package com.sxg.cms.controller;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.sxg.cms.entity.News;
import com.sxg.cms.entity.User;
import com.sxg.cms.service.NewsService;

@Controller
public class NewsController {

	@Autowired
	private NewsService newsService;
	
	@ResponseBody
	@RequestMapping(value = "/news/list")
	public Map<String,Object> list(@RequestParam("accessid") String accessid,
			@RequestParam(value = "pageIndex",required = false) String pageIndex) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			List <News> list = newsService.list(accessid, pageIndex);
			result.put("suc", "yes");
			result.put("data", list);

		} catch (Exception e) {
			result.put("suc", "no");
			result.put("msg", "error");
		}
		return result;

	}
	
	@ResponseBody
	@RequestMapping(value = "/news/findById")
	public Map<String,Object> findById(@RequestParam("id") String id) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			News news = newsService.findById(id);
			result.put("suc", "yes");
			result.put("data", news);

		} catch (Exception e) {
			result.put("suc", "no");
			result.put("msg", "error");
		}
		return result;

	}
	
	@RequestMapping(value = "/admin/save", method = { RequestMethod.POST })
	public String save(HttpServletRequest request,@RequestParam("imageFile") MultipartFile imageFile,
			@RequestParam(value="id",required=false) String id,@RequestParam("title") String title,
			@RequestParam("content") String content,@RequestParam("accessid") String accessid) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {	     
			String imagePath = "resource/"+UUID.randomUUID().toString()+".png";
			String path = request.getServletContext().getRealPath("/");
			File vedioPath = new File(path+imagePath);
			imageFile.transferTo(vedioPath);
			User user = (User) request.getSession().getAttribute("user");
			
			News news = new News();
			news.setTitle(title);
			news.setContent(content);
			news.setPicPath(imagePath);
			news.setAccessid(accessid);
			news.setCreatedTime(new Date());
			news.setCreater(user.getShowname());
			news.setUserid(user.getId());

			newsService.save(news);
			
			result.put("suc", "yes");
			result.put("msg", "保存成功");

		} catch (Exception e) {
			result.put("suc", "no");
			result.put("msg", e.getMessage());
			e.printStackTrace();
		}
		return "newsList";
	}
	
	@ResponseBody
	@RequestMapping(value = "/news/admin/list")
	public Map<String,Object> adminList(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			User user = (User) request.getSession().getAttribute("user");
			List <News> list = newsService.adminList(user);
			result.put("suc", "yes");
			result.put("data", list);

		} catch (Exception e) {
			result.put("suc", "no");
			result.put("msg", "error");
		}
		return result;

	}
	
	@ResponseBody
	@RequestMapping(value = "/news/release")
	public Map<String,Object> release(@RequestParam("id") String id) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			newsService.updateRrelease(id);
			result.put("suc", "yes");

		} catch (Exception e) {
			result.put("suc", "no");
			result.put("msg", "error");
			e.printStackTrace();
		}
		return result;

	}
	
	@ResponseBody
	@RequestMapping(value = "/news/delete")
	public Map<String,Object> delete(@RequestParam("id") String id) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			newsService.delete(id);
			result.put("suc", "yes");

		} catch (Exception e) {
			result.put("suc", "no");
			result.put("msg", "error");
		}
		return result;

	}

}