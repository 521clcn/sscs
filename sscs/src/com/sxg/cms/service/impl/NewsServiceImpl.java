package com.sxg.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sxg.cms.dao.NewsDao;
import com.sxg.cms.entity.News;
import com.sxg.cms.entity.User;
import com.sxg.cms.service.NewsService;

@Service("newsService")
public class NewsServiceImpl implements NewsService {

	@Autowired
    private NewsDao newsDao;

	@Override
	public List<News> list(String accessid,String pageIndex) {
		return newsDao.list(accessid,pageIndex);
	}

	@Override
	public void save(News news) {
		newsDao.save(news);
		
	}

	@Override
	public News findById(String id) {
		return newsDao.findById(id);
	}
	
	@Override
	public List<News> adminList(User user,Integer startPage,Integer pageSize) {
		return newsDao.adminList(user,startPage, pageSize);
	}

	@Override
	public void updateRrelease(String id) {
		newsDao.release(id);
		
	}

	@Override
	public void delete(String id) {
		newsDao.delete(id);
		
	}

	@Override
	public Integer countNews(User user) {
		return newsDao.countNews(user);
	}

}
