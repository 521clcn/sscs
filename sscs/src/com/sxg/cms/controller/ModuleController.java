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

import com.sxg.cms.entity.Module;
import com.sxg.cms.entity.User;
import com.sxg.cms.service.ModuleService;

@Controller
public class ModuleController {

	@Autowired
	private ModuleService moduleService;
	
	@ResponseBody
	@RequestMapping(value = "/module/list")
	public Map<String,Object> list() {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			List <Module> list = moduleService.list();
			result.put("suc", "yes");
			result.put("data", list);

		} catch (Exception e) {
			result.put("suc", "no");
			result.put("msg", "error");
		}
		return result;

	}
	
	@ResponseBody
	@RequestMapping(value = "/module/findById")
	public Map<String,Object> findById(@RequestParam("accessid") String accessid) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			Module module = moduleService.findById(accessid);
			result.put("suc", "yes");
			result.put("data", module);

		} catch (Exception e) {
			result.put("suc", "no");
			result.put("msg", "error");
		}
		return result;

	}
	
	@RequestMapping(value = "/admin/saveModule", method = { RequestMethod.POST })
	public String save(HttpServletRequest request,@RequestParam("imageFile") MultipartFile imageFile,
			@RequestParam(value="id",required=false) String id,@RequestParam("title") String title,
			@RequestParam("content") String content,@RequestParam("accessid") String accessid,
			@RequestParam("series") String series) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {	     
			String imagePath = "resource/"+UUID.randomUUID().toString()+".png";
			String path = request.getServletContext().getRealPath("/");
			File vedioPath = new File(path+imagePath);
			imageFile.transferTo(vedioPath);
			User user = (User) request.getSession().getAttribute("user");
			
			Module module = new Module();
			if(id!=null&&id.length()>0) {
				module.setId(id);
			}
			module.setTitle(title);
			module.setContent(content);
			module.setPicPath(imagePath);
			module.setAccessid(accessid);
			module.setCreatedTime(new Date());
			module.setCreater(user.getShowname());
			module.setSeries(series);

			moduleService.save(module);
			
			result.put("suc", "yes");
			result.put("msg", "保存成功");

		} catch (Exception e) {
			result.put("suc", "no");
			result.put("msg", e.getMessage());
			e.printStackTrace();
		}
		return "homeModule";
	}
	
	@RequestMapping(value = "/admin/background", method = { RequestMethod.POST })
	public String background(HttpServletRequest request,@RequestParam("imageFile") MultipartFile imageFile,
			@RequestParam("accessid") String accessid) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {	
			String picPath = "";
			if("1".equals(accessid)) {
				picPath="home-bg1";
			}else if("2".equals(accessid)) {
				picPath="home-bg2";
			}else if("3".equals(accessid)) {
				picPath="home-bg3";
			}else if("4".equals(accessid)) {
				picPath="home-bg4";
			}
			String imagePath = "resource/"+ picPath +".png";
			String path = request.getServletContext().getRealPath("/");
			File pagePath = new File(path+imagePath);
			if(pagePath.exists()){
				pagePath.delete();
			}
			imageFile.transferTo(pagePath);
			
			result.put("suc", "yes");
			result.put("msg", "保存成功");

		} catch (Exception e) {
			result.put("suc", "no");
			result.put("msg", e.getMessage());
			e.printStackTrace();
		}
		return "homeBackground";
	}
	
	@RequestMapping(value = "/admin/twomark", method = { RequestMethod.POST })
	public String twomark(HttpServletRequest request,@RequestParam("imageFile") MultipartFile imageFile) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {	
			String imagePath = "resource/twomark.png";
			String path = request.getServletContext().getRealPath("/");
			File pagePath = new File(path+imagePath);
			if(pagePath.exists()){
				pagePath.delete();
			}
			imageFile.transferTo(pagePath);
			
			result.put("suc", "yes");
			result.put("msg", "保存成功");

		} catch (Exception e) {
			result.put("suc", "no");
			result.put("msg", e.getMessage());
			e.printStackTrace();
		}
		return "twomark";
	}

}