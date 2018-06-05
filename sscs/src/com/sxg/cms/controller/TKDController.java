package com.sxg.cms.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sxg.cms.entity.TKD;
import com.sxg.cms.service.TKDService;

@Controller
public class TKDController {

	@Autowired
	private TKDService tkdService;
	
	@RequestMapping(value = "/admin/tkd")
	public String save(@RequestParam(value="id",required=false) String id,
			@RequestParam(value="modelname",required=false) String modelname,
			@RequestParam(value="modelurl",required=false) String modelurl,
			@RequestParam(value="title",required=false) String title,
			@RequestParam(value="keywords",required=false) String keywords,
			@RequestParam(value="description",required=false) String description) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			TKD tkd = new TKD();
			if(id!=null&&id.length()>0) {
				tkd.setId(id);
			}
			tkd.setModelname(modelname);
			tkd.setModelurl(modelurl);
			tkd.setTitle(title);
			tkd.setKeywords(keywords);
			tkd.setDescription(description);
			tkd.setModifytime(new Date());
			
			tkdService.save(tkd);
	
			result.put("suc", "yes");
			result.put("msg", "success");

		} catch (Exception e) {
			result.put("suc", "no");
			result.put("msg", "error");
		}
		return "tkdList";

	}
	
	@ResponseBody
	@RequestMapping(value = "/tkd/list")
	public Map<String,Object> list() {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			result.put("suc", "yes");
			result.put("data", tkdService.list());

		} catch (Exception e) {
			result.put("suc", "no");
			result.put("msg", "error");
		}
		return result;

	}
	
	@ResponseBody
	@RequestMapping(value = "/tkd/findById")
	public Map<String,Object> findById(@RequestParam("id") String id) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			result.put("suc", "yes");
			result.put("data", tkdService.findById(id));

		} catch (Exception e) {
			result.put("suc", "no");
			result.put("msg", "error");
		}
		return result;

	}
	
	
	@ResponseBody
	@RequestMapping(value = "/tkd/findByUrl")
	public Map<String,Object> findByUrl(@RequestParam("model") String url) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			result.put("suc", "yes");
			result.put("data", tkdService.findByUrl(url));

		} catch (Exception e) {
			result.put("suc", "no");
			result.put("msg", "error");
		}
		return result;

	}
	
}