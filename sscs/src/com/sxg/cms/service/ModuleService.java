package com.sxg.cms.service;

import java.util.List;

import com.sxg.cms.entity.Module;

public interface ModuleService {
	public void save(Module user); 
	public List<Module> list(); 
	public Module findById(String id); 
}
