package com.fw.yydb.entiy;

import java.util.ArrayList;
import java.util.List;

public class ActivityProductDetail {

    private Long productId;
    private Long activityId;
    private String productName;
    private String productTitle;
    private String productDesc;
    private String activtyCode;
    private Integer activityStatus;
    private Integer peopleCount;
    private Integer currCount;
    private Integer lastCount;
    private Integer currUserCount;
    private List<Integer> currUserNumbers = new ArrayList<Integer>();
    private Integer price;
    private String winName;
    private String winUserID;
    private Integer winJoinCount;
    private Integer luckNum;
    private String lotteryTime;
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Long getActivityId() {
		return activityId;
	}
	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductTitle() {
		return productTitle;
	}
	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	public String getActivtyCode() {
		return activtyCode;
	}
	public void setActivtyCode(String activtyCode) {
		this.activtyCode = activtyCode;
	}
	public Integer getActivityStatus() {
		return activityStatus;
	}
	public void setActivityStatus(Integer activityStatus) {
		this.activityStatus = activityStatus;
	}
	public Integer getPeopleCount() {
		return peopleCount;
	}
	public void setPeopleCount(Integer peopleCount) {
		this.peopleCount = peopleCount;
	}
	public Integer getCurrCount() {
		return currCount;
	}
	public void setCurrCount(Integer currCount) {
		this.currCount = currCount;
	}
	public Integer getLastCount() {
		return lastCount;
	}
	public void setLastCount(Integer lastCount) {
		this.lastCount = lastCount;
	}
	public Integer getCurrUserCount() {
		return currUserCount;
	}
	public void setCurrUserCount(Integer currUserCount) {
		this.currUserCount = currUserCount;
	}
	public List<Integer> getCurrUserNumbers() {
		return currUserNumbers;
	}
	public void setCurrUserNumbers(List<Integer> currUserNumbers) {
		this.currUserNumbers = currUserNumbers;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public String getWinName() {
		return winName;
	}
	public void setWinName(String winName) {
		this.winName = winName;
	}
	public String getWinUserID() {
		return winUserID;
	}
	public void setWinUserID(String winUserID) {
		this.winUserID = winUserID;
	}
	public Integer getWinJoinCount() {
		return winJoinCount;
	}
	public void setWinJoinCount(Integer winJoinCount) {
		this.winJoinCount = winJoinCount;
	}
	public Integer getLuckNum() {
		return luckNum;
	}
	public void setLuckNum(Integer luckNum) {
		this.luckNum = luckNum;
	}
	public String getLotteryTime() {
		return lotteryTime;
	}
	public void setLotteryTime(String lotteryTime) {
		this.lotteryTime = lotteryTime;
	}

    
}
