package com.sxg.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sxg.cms.dao.ModuleDao;
import com.sxg.cms.entity.Module;
import com.sxg.cms.service.ModuleService;

@Service("moduleService")
public class ModuleServiceImpl implements ModuleService {

	@Autowired
    private ModuleDao ModuleDao;

	@Override
	public List<Module> list() {
		return ModuleDao.list();
	}

	@Override
	public void save(Module Module) {
		ModuleDao.save(Module);
		
	}

	@Override
	public Module findById(String id) {
		return ModuleDao.findById(id);
	}
	


}
