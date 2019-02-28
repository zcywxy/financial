package com.zhou.spring.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserDao{
	@Insert("insert into user(tel,password,createtime) values(#{tel},#{password},now())")
	public int saveUser(Map<String, String> userinfo);
	@Select("select * from user where tel=#{tel}")
	public Map<String, Object> getUser(Map<String, String> userinfo);
	@Update("update user set balance=${all} where tel=${tel}")
	public int addMoney(@Param("all")float all, @Param("tel") String tel);
}
