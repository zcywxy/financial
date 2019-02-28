package com.zhou.springmvc.controller;

import java.math.BigDecimal;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.zhou.spring.msg.Result;
import com.zhou.spring.msg.ResultWithUser;
import com.zhou.spring.service.UserService;

@Controller
@RequestMapping("user")
public class UserController extends Test {
	private ModelAndView modelAndView;
	private String viewName;
	private String msg;
	@Autowired
	private UserService userservice;
	@RequestMapping("/regist")
	public ModelAndView regist(@RequestParam Map<String, String> userinfo,HttpSession session){
		//调用service层的注册方法
		modelAndView=new ModelAndView();
		Result result = userservice.regist(userinfo);
		if(result.isResult()){
			viewName="myAccount";
			session.setAttribute("user", userinfo);
		}else{
			viewName="reg";
		}		
		msg=result.getMsg();
		modelAndView.setViewName(viewName);
		modelAndView.addObject("msg", msg);
		return modelAndView;
	}
	@RequestMapping("/login")
	//这里的用户登录请求需要优化
	public ModelAndView login(@RequestParam Map<String, String> userinfo,HttpSession session){
		//调用service层的注册方法
		modelAndView=new ModelAndView();
		ResultWithUser ru=userservice.login(userinfo);
		if(ru.isResult()){
			session.setAttribute("user", ru.getUserinfo());
			viewName="myAccount";
		}else{
			viewName="log";
		}
		msg=ru.getMsg();
		modelAndView.setViewName(viewName);
		modelAndView.addObject("msg", msg);
		return modelAndView;
	}
	@RequestMapping("/{page}")
	public ModelAndView reg(@PathVariable String page,HttpSession session){
		modelAndView=new ModelAndView();
		if(session.getAttribute("user")!=null){
			viewName=page;
		}else{
			viewName="log";
			msg="您尚未登录，请先登录！";
		}		
		modelAndView.setViewName(viewName);
		modelAndView.addObject("msg", msg);
		return modelAndView;
	}
	@SuppressWarnings("unchecked")
	@RequestMapping("/myAccount")
	public ModelAndView myAccount(HttpSession session){
		modelAndView=new ModelAndView();
		ResultWithUser user = null;
		Map<String, Object> userinfo=null;
		if(session.getAttribute("user")!=null){
			user=userservice.getUser((Map<String, String>)session.getAttribute("user"));
			userinfo= user.getUserinfo();
			viewName="myAccount";
		}else{
			viewName="log";
			msg="您尚未登录，请先登录！";
		}		
		modelAndView.setViewName(viewName);
		modelAndView.addObject("user", userinfo);
		return modelAndView;
	}
	@SuppressWarnings("unchecked")
	@RequestMapping("/addmoney")
	public ModelAndView addmoney(@RequestParam int money,HttpSession session){
		modelAndView=new ModelAndView();
		Map<String, Object> userinfo=null;
		if(session.getAttribute("user")!=null){
			ResultWithUser user=userservice.getUser((Map<String, String>)session.getAttribute("user"));
			userinfo= user.getUserinfo();
			//user=(Map<String, Object>) session.getAttribute("user");
			String tel =(String) userinfo.get("tel");
			BigDecimal moneyleft = (BigDecimal)userinfo.get("balance");
			float all = money + moneyleft.floatValue();
			userservice.addMoney(all,tel);
			userinfo.put("balance", all+"");
			session.setAttribute("user", userinfo);
			viewName="myAccount";
			msg="增加成功！";
		}else{
			viewName="log";
			msg="您尚未登录，请先登录！";
		}
		modelAndView.setViewName(viewName);
		modelAndView.addObject("amsg", msg);
		return modelAndView;
	}
	@SuppressWarnings("unchecked")
	@RequestMapping("/wyhk")
	public ModelAndView wyhk(HttpSession session){
		modelAndView=new ModelAndView();
		ResultWithUser user = null;
		Map<String, Object> userinfo=null;
		if(session.getAttribute("user")!=null){
			user=userservice.getUser((Map<String, String>)session.getAttribute("user"));
			userinfo= user.getUserinfo();
			viewName="wyhk";
		}else{
			viewName="log";
			msg="您尚未登录，请先登录！";
		}		
		modelAndView.setViewName(viewName);
		modelAndView.addObject("user", userinfo);
		return modelAndView;
	}
	@RequestMapping("/index")
	public String index(){
		return "index";
	}
	@RequestMapping("/reg")
	public String reg(){
		return "reg";
	}
	@RequestMapping("/logout")
	public String logout(HttpSession session){
		session.removeAttribute("user");
		return "index";
	}
}
