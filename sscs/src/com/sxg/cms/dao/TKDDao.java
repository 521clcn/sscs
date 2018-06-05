package com.sxg.cms.dao;

import java.util.List;

import com.sxg.cms.entity.TKD;

public interface TKDDao {
	public void save(TKD tkd); 
	public List<TKD> list();
	public TKD findById(String id);
	public TKD findByUrl(String url);
	
}
