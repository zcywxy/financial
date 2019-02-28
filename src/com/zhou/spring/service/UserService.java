package com.zhou.spring.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhou.spring.dao.UserDao;
import com.zhou.spring.msg.Result;
import com.zhou.spring.msg.ResultWithUser;
@Service
@Transactional
public class UserService {
	
	@Autowired
	private UserDao userdao;
	public Result regist(Map<String, String> userinfo) {
		//调用dao层的方法
		//验证用户是否已存在
		Map<String, Object> user = userdao.getUser(userinfo);
		if(user!=null){
			return new Result(false,"用户已存在");
		}else{
			//回传值
			int result=userdao.saveUser(userinfo);
			if(result==1){
				return new Result(true,"注册成功！");
			}else{				
				return new Result(false,"服务器出问题了，请联系管理员！");
			}
		}
			
		
	}
/*	public Result login(Map<String, String> userinfo) {
		Map<String, String> user = userdao.getUser(userinfo);
		if(user!=null){
			if(user.get("password").equals(userinfo.get("password"))){				
				return new Result(true,"登录成功");
			}
			return new Result(false,"登录失败，请核对信息后重新输入");
		}
		return new Result(false,"登录失败，请核对信息后重新输入");
	}*/
	public ResultWithUser login(Map<String, String> userinfo) {
		Map<String, Object> user = userdao.getUser(userinfo);
		if(user!=null){
			if(user.get("password").equals(userinfo.get("password"))){				
				return new ResultWithUser(true,"登录成功",user);
			}
			return new ResultWithUser(false,"登录失败，请核对信息后重新输入",null);
		}
		return new ResultWithUser(false,"登录失败，请核对信息后重新输入",null);
	}
	
	public ResultWithUser getUser(Map<String, String> userinfo) {
		Map<String, Object> user = userdao.getUser(userinfo);
		if(user!=null){
			if(user.get("password").equals(userinfo.get("password"))){				
				return new ResultWithUser(true,"登录成功",user);
			}
			return new ResultWithUser(false,"登录失败，请核对信息后重新输入",null);
		}
		return new ResultWithUser(false,"登录失败，请核对信息后重新输入",null);
	}
	public ResultWithUser addMoney(float all,String tel) {
		int effect = userdao.addMoney(all,tel);
		if(effect!=1){		
			return new ResultWithUser(true,"添加成功！",null);
		}
		return new ResultWithUser(false,"添加失败，请核对信息并联系管理员！",null);
	}
	
}
