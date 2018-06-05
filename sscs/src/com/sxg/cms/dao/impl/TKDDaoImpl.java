package com.sxg.cms.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.sxg.cms.dao.TKDDao;
import com.sxg.cms.entity.TKD;

@Repository("tkdDao")
public class TKDDaoImpl extends HibernateDaoSupport implements TKDDao {

	@Autowired
	public void setSessionFacotry(SessionFactory sessionFacotry) {
		super.setSessionFactory(sessionFacotry);
	}

	@Override
	public void save(TKD tkd) {
		super.getHibernateTemplate().saveOrUpdate(tkd);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TKD> list() {
		String hql = "from TKD";
		List<TKD> list = (List<TKD>) super.getHibernateTemplate().find(hql);
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public TKD findById(String id) {
		String hql = "from TKD where id=?";
		List<TKD> list = (List<TKD>) super.getHibernateTemplate().find(hql,id);
		if(list.size()==1) {
			return list.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public TKD findByUrl(String url) {
		String hql = "from TKD where modelurl=?";
		List<TKD> list = (List<TKD>) super.getHibernateTemplate().find(hql,url);
		if(list.size()==1) {
			return list.get(0);
		}
		return null;
	}


}
