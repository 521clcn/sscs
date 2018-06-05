package com.sxg.cms.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.sxg.cms.dao.NewsDao;
import com.sxg.cms.entity.News;
import com.sxg.cms.entity.User;

@Repository("newsDao")
public class NewsDaoImpl extends HibernateDaoSupport implements NewsDao {

	@Autowired
	public void setSessionFacotry(SessionFactory sessionFacotry) {
		super.setSessionFactory(sessionFacotry);
	}

	@Override
	public void save(News news) {
		super.getHibernateTemplate().saveOrUpdate(news);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<News> list(String accessid,String pageIndex) {
		String hql = "from News";
		List<News> list = new ArrayList<News>();
		if("page4".equals(accessid)) {
			hql = "from News where accessid = '2' and status='1' order by releaseTime desc ";
			HibernateTemplate template = super.getHibernateTemplate();
			template.setMaxResults(10);
			list = (List<News>) template.find(hql);
		}else {
			hql = "from News where accessid = ? and status='1' order by releaseTime desc ";
			HibernateTemplate template = super.getHibernateTemplate();
			template.setMaxResults(80);
			list = (List<News>) template.find(hql,accessid);
		}
		
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public News findById(String id) {
		String hql = "from News where id =?";
		List<News> list = (List<News>)  super.getHibernateTemplate().find(hql,id);
		if(list.size()==1) {
			return list.get(0);
		}else {
			return null;
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<News> adminList(User user) {
		String type = user.getType();
		if("0".equals(type)) {
			String hql = "from News order by status ASC, userid ASC, releaseTime DESC";
			List<News> list = (List<News>) super.getHibernateTemplate().find(hql);
			return list;
		}else {
			String userId = user.getId();
			String hql = "from News where userid = ? order by status ASC, releaseTime DESC";
			List<News> list = (List<News>) super.getHibernateTemplate().find(hql,userId);
			return list;
		}
		
	}

	@Override
	public void release(String id) {
		String hql = "update News set status='1', releaseTime=? where id=?";
		super.getHibernateTemplate().bulkUpdate(hql, new Object[] {new Date(),id});
		
	}

	@Override
	public void delete(String id) {
		News news = new News();
		news.setId(id);
		super.getHibernateTemplate().delete(news);
		
	}

}
