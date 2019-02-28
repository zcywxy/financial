package com.zhou.spring.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface ProjectDao {
	@Select("select * from projectlist")
	List<Map<String, Object>> getAllProjects();
	@Select("select * from projectlist where rate=${rate} and " +
			" repaytype=${repay}" +
			" and loanlength=${length}" +
			" and status=${status}")
	List<Map<String, Object>> getProjectsByCriteria(Map<String, Integer> param);
	@Select("select * from projectlist where pid=${projectid}")
	Map<String, String> getProjectsByID(@Param("projectid") String projectid);
}
