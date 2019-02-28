package com.zhou.spring.dao;

import org.apache.ibatis.annotations.Insert;

import com.zhou.spring.pojo.LoanInfo;

public interface LoanDao {
	@Insert("insert into loan(uid,region,loanamount,loanlength,loantype,loanuse,status) " +
			"values(#{uid},#{region},#{loanamount},#{loanlength},#{loantype},#{loanuse},1)")
	public int saveLoan(LoanInfo loaninfo);
	@Insert("insert into loan(uid,region,loanamount,loanlength,loantype,loanuse,status) " +
			"values(#{uid},#{region},#{loanamount},#{loanlength},#{loantype},#{loanuse},1)")
	public int getLoan();
}
