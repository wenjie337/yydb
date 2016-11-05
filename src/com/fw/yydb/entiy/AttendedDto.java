package com.fw.yydb.entiy;

import java.util.Date;

public class AttendedDto {

	private Long activityId;
	private Long involvementCode;
	private String userID;
	private Long isPrize;
	private Date createTime;


	public Long getActivityId() {
		return activityId;
	}

	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}

	public Long getInvolvementCode() {
		return involvementCode;
	}

	public void setInvolvementCode(Long involvementCode) {
		this.involvementCode = involvementCode;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public Long getIsPrize() {
		return isPrize;
	}

	public void setIsPrize(Long isPrize) {
		this.isPrize = isPrize;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
