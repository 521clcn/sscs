package com.sxg.cms.dao;

import java.util.List;

import com.sxg.cms.entity.News;
import com.sxg.cms.entity.User;

public interface NewsDao {
	public void save(News user); 
	public List<News> list(String accessid,String pageIndex); 
	public News findById(String id); 
	public List<News> adminList(User user,Integer startPage,Integer pageSize); 
	public Integer countNews(User user);
	public void release(String id); 
	public void delete(String id); 
	
}
