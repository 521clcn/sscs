package com.sxg.cms.dao;

import java.util.List;

import com.sxg.cms.entity.Module;

public interface ModuleDao {
	public void save(Module user); 
	public List<Module> list(); 
	public Module findById(String id); 
	
}
