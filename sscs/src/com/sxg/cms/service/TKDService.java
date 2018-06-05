package com.sxg.cms.service;

import java.util.List;

import com.sxg.cms.entity.TKD;

public interface TKDService {
	public void save(TKD tkd); 
	public List<TKD> list();
	public TKD findById(String id);
	public TKD findByUrl(String url);
	
}
