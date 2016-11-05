package com.fw.yydb.entiy;

import java.util.Date;

public class PeriodsDto {
	private Long periodsID;
	private Long productID;
	private String periodsCode;
	private Long status;
	private Date lotteryTime;
	private Long lotteryCode;
	private Date startTime;
	private Long numberAcount;
	private Date fullTime;
	private Long peopleCount;
	private String productName;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getPeriodsCode() {
		return periodsCode;
	}

	public void setPeriodsCode(String periodsCode) {
		this.periodsCode = periodsCode;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public Date getLotteryTime() {
		return lotteryTime;
	}

	public void setLotteryTime(Date lotteryTime) {
		this.lotteryTime = lotteryTime;
	}

	public Long getLotteryCode() {
		return lotteryCode;
	}

	public void setLotteryCode(Long lotteryCode) {
		this.lotteryCode = lotteryCode;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getFullTime() {
		return fullTime;
	}

	public void setFullTime(Date fullTime) {
		this.fullTime = fullTime;
	}

	public long getNumberAcount() {
		return numberAcount;
	}

	public void setNumberAcount(long numberAcount) {
		this.numberAcount = numberAcount;
	}

	public Long getPeriodsID() {
		return periodsID;
	}

	public void setPeriodsID(Long periodsID) {
		this.periodsID = periodsID;
	}

	public Long getProductID() {
		return productID;
	}

	public void setProductID(Long productID) {
		this.productID = productID;
	}

	public Long getPeopleCount() {
		return peopleCount;
	}

	public void setPeopleCount(Long peopleCount) {
		this.peopleCount = peopleCount;
	}

	public void setNumberAcount(Long numberAcount) {
		this.numberAcount = numberAcount;
	}

}
