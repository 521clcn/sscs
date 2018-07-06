package com.sxg.cms.service;

import java.util.List;

import com.sxg.cms.entity.News;
import com.sxg.cms.entity.User;

public interface NewsService {
	public void save(News user); 
	public List<News> list(String accessid,String pageIndex); 
	public News findById(String id); 
	public List<News> adminList(User user,Integer startPage,Integer pageSize); 
	public Integer countNews(User user);
	public void updateRrelease(String id); 
	public void delete(String id); 
}
