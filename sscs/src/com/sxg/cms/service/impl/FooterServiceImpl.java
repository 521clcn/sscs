package com.sxg.cms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sxg.cms.dao.FooterDao;
import com.sxg.cms.entity.Footer;
import com.sxg.cms.service.FooterService;

@Service("footerService")
public class FooterServiceImpl implements FooterService {

	@Autowired
    private FooterDao footerDao;

	@Override
	public Footer list() {
		return footerDao.list();
	}

	@Override
	public void save(Footer footer) {
		footerDao.save(footer);
		
	}

}
