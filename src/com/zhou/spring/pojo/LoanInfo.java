package com.zhou.spring.pojo;

public class LoanInfo {
	private String realname;
	private String tel;
	private int uid;
	private String region;
	private int loanamount;
	private int loanlength;
	private int loantype;
	private int loanuse;
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		System.out.println(uid);
		this.uid = uid;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public int getLoanamount() {
		return loanamount;
	}
	public void setLoanamount(int loanamount) {
		this.loanamount = loanamount;
	}
	public int getLoanlength() {
		return loanlength;
	}
	public void setLoanlength(int loanlength) {
		this.loanlength = loanlength;
	}
	public int getLoantype() {
		return loantype;
	}
	public void setLoantype(int loantype) {
		this.loantype = loantype;
	}
	public int getLoanuse() {
		return loanuse;
	}
	public void setLoanuse(int loanuse) {
		this.loanuse = loanuse;
	}
	@Override
	public String toString() {
		return this.realname+" "+this.loanamount+" "+this.loanlength+" "+this.loantype+" "+this.loanuse+" "+this.region+" "+this.tel;
	}
}
