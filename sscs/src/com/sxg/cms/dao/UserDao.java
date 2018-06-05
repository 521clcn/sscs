package com.sxg.cms.dao;

import java.util.List;

import com.sxg.cms.entity.User;

public interface UserDao {
	public User login(String name,String password); 
	public void save(User user); 
	public List<User> list(); 
	public void delete(String id); 

}
