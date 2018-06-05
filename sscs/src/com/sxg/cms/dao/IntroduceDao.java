package com.sxg.cms.dao;

import com.sxg.cms.entity.Introduce;

public interface IntroduceDao {
	public void save(Introduce user); 
	public Introduce list(String type,String accessid); 

}
