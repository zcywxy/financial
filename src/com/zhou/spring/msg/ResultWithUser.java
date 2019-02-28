package com.zhou.spring.msg;

import java.util.Map;

public class ResultWithUser extends Result{
	public ResultWithUser() {
		// TODO Auto-generated constructor stub
	}
	public ResultWithUser(boolean result, String msg,Map<String, Object> userinfo) {
		super.result = result;
		super.msg = msg;
		this.userinfo=userinfo;
	}
	Map<String, Object> userinfo;
	
	public Map<String, Object> getUserinfo() {
		return userinfo;
	}
	public void setUserinfo(Map<String, Object> userinfo) {
		this.userinfo = userinfo;
	}


}
