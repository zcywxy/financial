package com.zhou.springmvc.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zhou.spring.msg.Result;
import com.zhou.spring.pojo.LoanInfo;
import com.zhou.spring.service.LoanService;

@Controller
@RequestMapping("user")
public class LoanController {
	private ModelAndView modelAndView=new ModelAndView();;
	private String lviewName;
	private String lmsg;
	private String sign;
	@Autowired
	private LoanService loanservice;
	@RequestMapping("/applyloan") 
	public ModelAndView applyloan(LoanInfo loaninfo,HttpSession session){
		//添加贷款记录
		if(session.getAttribute("user")!=null){
			lviewName="loan";
			if(loaninfo!=null){
				Result addLoanResult = loanservice.addLoan(loaninfo);
				lmsg=addLoanResult.getMsg();
				if(addLoanResult.isResult()){
					sign="true";
				}else{
					sign="false";
				}
				
			}
		}else{
			lviewName="log";
			lmsg="您尚未登录，请先登录！";
		}		
		modelAndView.setViewName(lviewName);
		modelAndView.addObject("lmsg", lmsg);
		modelAndView.addObject("sign", sign);
		return modelAndView;
	}
}
