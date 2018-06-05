package com.sxg.cms.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sxg.cms.entity.Footer;
import com.sxg.cms.service.FooterService;

@Controller
public class FooterController {

	@Autowired
	private FooterService footerService;
	
	@RequestMapping(value = "/admin/footer")
	public String save(@RequestParam(value="id",required=false) String id,
			@RequestParam(value="address",required=false) String address,
			@RequestParam(value="contract",required=false) String contract,
			@RequestParam(value="email",required=false) String email,
			@RequestParam(value="copyright",required=false) String copyright) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			Footer footer = footerService.list();
			if(footer==null) {
				footer = new Footer();
			}
			footer.setAddress(address);
			footer.setContract(contract);
			footer.setEmail(email);
			footer.setCopyright(copyright);
			
			footerService.save(footer);
			
			result.put("suc", "yes");
			result.put("msg", "success");

		} catch (Exception e) {
			result.put("suc", "no");
			result.put("msg", "error");
		}
		return "address";

	}
	
	@ResponseBody
	@RequestMapping(value = "/footer/list")
	public Map<String,Object> list() {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			result.put("suc", "yes");
			result.put("data", footerService.list());

		} catch (Exception e) {
			result.put("suc", "no");
			result.put("msg", "error");
		}
		return result;

	}
	
}