package com.sxg.cms.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.sxg.cms.dao.NewsDao;
import com.sxg.cms.entity.News;

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
			template.setMaxResults(9);
			list = (List<News>) template.find(hql);
			
			template.setMaxResults(0);
		}else {
			hql = "from News where accessid = ? and status='1' order by releaseTime desc";
//			HibernateTemplate template = super.getHibernateTemplate();
			int beginIndex = new Integer(pageIndex)*10;
			Session session = super.getSessionFactory().getCurrentSession();
			Query query = session.createQuery(hql);
			query.setFirstResult(beginIndex);
			query.setMaxResults(10);		
			query.setParameter(0, accessid);
//			query.getResultList();
			
			list = query.getResultList();			
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
	public List<News> adminList(String accessid,Integer startPage,Integer pageSize) {
		String hql = "from News where accessid = ? order by status ASC, releaseTime DESC";
		
		startPage = startPage-1;
		int beginIndex = startPage*pageSize;
		
		Session session = super.getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		query.setFirstResult(beginIndex);
		query.setMaxResults(pageSize);		
		query.setParameter(0, accessid);
			
		List<News> list = query.getResultList();	
			
		return list;
	}
	
	@Override
	public Integer countNews(String accessid) {		
		String hql = "select count(*) from News where accessid = ?";			
		Long count =  (Long) getHibernateTemplate().iterate(hql,accessid).next();
		return count.intValue();		
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
