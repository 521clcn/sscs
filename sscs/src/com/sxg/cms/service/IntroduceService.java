package com.sxg.cms.service;

import com.sxg.cms.entity.Introduce;

public interface IntroduceService {
	public void save(Introduce introduce); 
	public Introduce list(String type,String accessid); 
}
