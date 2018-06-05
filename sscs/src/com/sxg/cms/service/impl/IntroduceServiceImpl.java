package com.sxg.cms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sxg.cms.dao.IntroduceDao;
import com.sxg.cms.entity.Introduce;
import com.sxg.cms.service.IntroduceService;

@Service("introduceService")
public class IntroduceServiceImpl implements IntroduceService {

	@Autowired
    private IntroduceDao introduceDao;

	@Override
	public Introduce list(String type,String accessid) {
		return introduceDao.list(type,accessid);
	}

	@Override
	public void save(Introduce introduce) {
		introduceDao.save(introduce);
		
	}

}
