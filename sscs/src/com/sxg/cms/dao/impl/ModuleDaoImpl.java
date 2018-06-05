package com.sxg.cms.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.sxg.cms.dao.ModuleDao;
import com.sxg.cms.entity.Module;

@Repository("ModuleDao")
public class ModuleDaoImpl extends HibernateDaoSupport implements ModuleDao {

	@Autowired
	public void setSessionFacotry(SessionFactory sessionFacotry) {
		super.setSessionFactory(sessionFacotry);
	}

	@Override
	public void save(Module Module) {
		super.getHibernateTemplate().saveOrUpdate(Module);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Module> list() {
		String hql = "from Module order by series ASC ";
		List<Module> list = (List<Module>) super.getHibernateTemplate().find(hql);
		
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Module findById(String id) {
		String hql = "from Module where accessid =?";
		List<Module> list = (List<Module>)  super.getHibernateTemplate().find(hql,id);
		if(list.size()==1) {
			return list.get(0);
		}else {
			return null;
		}
	}

}
