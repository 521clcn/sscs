package com.sxg.cms.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.sxg.cms.dao.IntroduceDao;
import com.sxg.cms.entity.Introduce;

@Repository("introduceDao")
public class IntroduceDaoImpl extends HibernateDaoSupport implements IntroduceDao {

	@Autowired
	public void setSessionFacotry(SessionFactory sessionFacotry) {
		super.setSessionFactory(sessionFacotry);
	}

	@Override
	public void save(Introduce introduce) {
		super.getHibernateTemplate().saveOrUpdate(introduce);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Introduce list(String type,String accessid) {
		String hql = "from Introduce where type =? and accessid=?";
		List<Introduce> list = (List<Introduce>) super.getHibernateTemplate().find(hql,new Object[] {type,accessid});
		if(list.size()==1) {
			return list.get(0);
		}else {
			return null;
		}
	}


}
