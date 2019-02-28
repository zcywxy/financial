package com.zhou.springmvc.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhou.spring.dao.ProjectDao;
import com.zhou.spring.msg.Result;
import com.zhou.spring.msg.SearchCriteria;
@Controller
@RequestMapping("project")
public class ProjectController {
	private ModelAndView modelAndView=new ModelAndView();;
	private String viewName;
	private String msg;
	@Autowired 
	private ProjectDao projectdao;
	@RequestMapping("/list")
	public @ResponseBody List<Map<String,Object>> list(@RequestBody String[] criteria) throws IOException{
		Map<String, Integer> params=new HashMap<String, Integer>();
		int sign=0;
		for (int i = 0; i < criteria.length-1; i++) {
			if("status1".equals(criteria[i])){
				params.put("status", 1);
				sign++;
			}else if("status2".equals(criteria[i])){
				params.put("status", 2);
			}else if("status3".equals(criteria[i])){
				params.put("status", 3);
			}else if("status4".equals(criteria[i])){				
				params.put("status", 4);
			}
			
			if("rate1".equals(criteria[i])){
				params.put("rate", 1);
				sign++;
			}else if("rate2".equals(criteria[i])){
				params.put("rate", 2);
			}else if("rate3".equals(criteria[i])){
				params.put("rate", 3);
			}else if("rate4".equals(criteria[i])){				
				params.put("rate", 4);
			}
			
			if("length1".equals(criteria[i])){
				params.put("length", 1);
				sign++;
			}else if("length2".equals(criteria[i])){
				params.put("length", 2);
			}else if("length3".equals(criteria[i])){
				params.put("length", 3);
			}else if("length4".equals(criteria[i])){				
				params.put("length", 4);
			}
			
			if("repay1".equals(criteria[i])){
				params.put("repay", 1);
				sign++;
			}else if("repay2".equals(criteria[i])){
				params.put("repay", 2);
			}else if("repay3".equals(criteria[i])){
				params.put("repay", 3);
			}
			
			System.out.println(criteria[i]);
		}
		//这里无法对页面传来的最大页数进行检测，可能会造成查询失败
		System.out.println(criteria[criteria.length-1]);
		int pagenow=Integer.parseInt(criteria[criteria.length-1]);
		List<Map<String, Object>> projects ;
		if(sign!=4){
			PageHelper.startPage(pagenow, 5);
			projects = projectdao.getProjectsByCriteria(params);		
			PageInfo<Map<String, Object>> page=new PageInfo<Map<String, Object>>(projects);
			System.out.println("总"+page.getPages()+"页。");
		//封装页数数据
			Map<String, Object> pageinfo=new HashMap<String,Object>();
			pageinfo.put("totalpage",page.getPages());
			projects.add(pageinfo);
		}else{
			PageHelper.startPage(pagenow, 5);
			projects= projectdao.getAllProjects();		
			PageInfo<Map<String, Object>> page=new PageInfo<Map<String, Object>>(projects);
			System.out.println("总"+page.getPages()+"页。");
		//封装页数数据
			Map<String, Object> pageinfo=new HashMap<String,Object>();
			pageinfo.put("totalpage",page.getPages());
			projects.add(pageinfo);
		}
		return projects;
	}
	@RequestMapping("/touzi")
	public ModelAndView touzi(@RequestParam String projectid,HttpSession session){
		Map<String, String> project=null;
		if(session.getAttribute("user")!=null&&projectid!=null){
			System.out.println("项目ID："+projectid);
			project = projectdao.getProjectsByID(projectid);
			viewName="touzi";
		}else{
			viewName="log";
			msg="您尚未登录，请先登录！";
		}	
		modelAndView.setViewName(viewName);
		modelAndView.addObject("msg", msg);
		modelAndView.addObject("project", project);
		return modelAndView;
	}
}
