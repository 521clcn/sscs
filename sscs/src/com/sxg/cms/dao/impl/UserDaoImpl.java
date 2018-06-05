package com.sxg.cms.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.sxg.cms.dao.UserDao;
import com.sxg.cms.entity.User;

@Repository("userDao")
public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

	@Autowired
	public void setSessionFacotry(SessionFactory sessionFacotry) {
		super.setSessionFactory(sessionFacotry);
	}

	@SuppressWarnings("unchecked")
	@Override
	public User login(String name, String password) {
		String hql = "from User u where u.username=? and u.password=?";
		List<User> list = (List<User>) super.getHibernateTemplate().find(hql, new Object[] { name, password });
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public void save(User user) {
		super.getHibernateTemplate().saveOrUpdate(user);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> list() {
		String hql = "from User";
		List<User> list = (List<User>) super.getHibernateTemplate().find(hql);
		return list;
	}

	@Override
	public void delete(String id) {
		User user = new User();
		user.setId(id);
		super.getHibernateTemplate().delete(user);
		
	}

}
