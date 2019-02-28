package com.zhou.spring.msg;

public class Result {
	public Result() {
	}
	public Result(boolean result, String msg) {
		this.result = result;
		this.msg = msg;
	}
	protected boolean result;
	protected String msg;
	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
