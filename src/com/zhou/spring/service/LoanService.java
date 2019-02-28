package com.zhou.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhou.spring.dao.LoanDao;
import com.zhou.spring.msg.Result;
import com.zhou.spring.pojo.LoanInfo;
@Service
@Transactional
public class LoanService {
	@Autowired
	private LoanDao loandao;
	public LoanService() {
		
	}
	//添加贷款记录
	public Result addLoan(LoanInfo loaninfo){
		int saveLoan = loandao.saveLoan(loaninfo);
		if(saveLoan==1){
			return new Result(true,"贷款已经申请，请等待审核！");
		}else return new Result(false,"贷款添加失败！");
	}
	//贷款记录查询
	
	//
}
