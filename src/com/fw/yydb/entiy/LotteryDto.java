package com.fw.yydb.entiy;

import java.util.Date;

public class LotteryDto {
	private Long lotteryID;
	private String userID;
	private Long activityID;
	private Long productID;
	private String productName;
	private String activityCode;
	private String prizeNumber;
	private Date lotteryTime;
	private Long status;
	public Long getLotteryID() {
		return lotteryID;
	}
	public void setLotteryID(Long lotteryID) {
		this.lotteryID = lotteryID;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public Long getActivityID() {
		return activityID;
	}
	public void setActivityID(Long activityID) {
		this.activityID = activityID;
	}
	public Long getProductID() {
		return productID;
	}
	public void setProductID(Long productID) {
		this.productID = productID;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getActivityCode() {
		return activityCode;
	}
	public void setActivityCode(String activityCode) {
		this.activityCode = activityCode;
	}
	public String getPrizeNumber() {
		return prizeNumber;
	}
	public void setPrizeNumber(String prizeNumber) {
		this.prizeNumber = prizeNumber;
	}
	public Date getLotteryTime() {
		return lotteryTime;
	}
	public void setLotteryTime(Date lotteryTime) {
		this.lotteryTime = lotteryTime;
	}
	public Long getStatus() {
		return status;
	}
	public void setStatus(Long status) {
		this.status = status;
	}


}
