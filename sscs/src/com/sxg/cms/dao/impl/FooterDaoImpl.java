package com.sxg.cms.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.sxg.cms.dao.FooterDao;
import com.sxg.cms.entity.Footer;

@Repository("footerDao")
public class FooterDaoImpl extends HibernateDaoSupport implements FooterDao {

	@Autowired
	public void setSessionFacotry(SessionFactory sessionFacotry) {
		super.setSessionFactory(sessionFacotry);
	}

	@Override
	public void save(Footer footer) {
		super.getHibernateTemplate().saveOrUpdate(footer);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Footer list() {
		String hql = "from Footer";
		List<Footer> list = (List<Footer>) super.getHibernateTemplate().find(hql);
		if(list.size()==1) {
			return list.get(0);
		}
		return null;
	}


}
