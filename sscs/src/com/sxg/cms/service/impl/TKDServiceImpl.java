package com.sxg.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sxg.cms.dao.TKDDao;
import com.sxg.cms.entity.TKD;
import com.sxg.cms.service.TKDService;

@Service("tkdService")
public class TKDServiceImpl implements TKDService {

	@Autowired
    private TKDDao tkdDao;

	@Override
	public void save(TKD tkd) {
		tkdDao.save(tkd);
	}

	@Override
	public List<TKD> list() {
		return tkdDao.list();
	}

	@Override
	public TKD findById(String id) {
		return tkdDao.findById(id);
	}
	
	@Override
	public TKD findByUrl(String url) {
		return tkdDao.findByUrl(url);
	}

}
