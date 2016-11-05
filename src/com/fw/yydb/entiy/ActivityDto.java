package com.fw.yydb.entiy;

/**
 * 活动
 * @author wen
 *
 */
public class ActivityDto {

	/**
	 * 活动ID
	 */
	private Long activityId;
	/**
	 * 产品ID
	 */
	private Long productId;
	/**
	 * 活动编码
	 */
	private String activityCode;
	/**
	 * 价格（分）
	 */
	private Long price;
	/**
	 * 所需人次
	 */
	private Long peopleCount;
	/**
	 * 创建时间
	 */
	private String createTime;
	/**
	 * 开始时间
	 */
	private String startTime;
	/**
	 * 满额时间
	 */
	private String fullTime;
	/**
	 * 结束时间
	 */
	private String endTime;
	/**
	 * 开奖时间
	 */
	private String lotteryTime;
	/**
	 * 开奖期号
	 */
	private String lotteryCode;

	/**
	 * A值之和
	 */
	private Long numberAcount;
	/**
	 * 状态0未开始1进行中2待抽奖3结束
	 */
	private int status;
	/**
	 * 产品名称
	 */
	private String productName;
	/**
	 * 当前参与人数
	 */
	private Long currCount;
	/**
	 * 剩余参与人次
	 */
	private Long lastCount;
	/**
	 * banner地址
	 */
	private String bannerImg;
	/**
	 * 详情地址
	 */
	private String detailImg;
	/**
	 * 当前用户参与次数
	 */
	private Long currUserJoinNum;

	/**
	 * 单次最大参与数
	 */
	private Integer maxCount;

	public Integer getMaxCount() {
		return maxCount;
	}

	public void setMaxCount(Integer maxCount) {
		this.maxCount = maxCount;
	}

	public Long getCurrUserJoinNum() {
		return currUserJoinNum;
	}

	public void setCurrUserJoinNum(Long currUserJoinNum) {
		this.currUserJoinNum = currUserJoinNum;
	}

	public String getBannerImg() {
		return bannerImg;
	}

	public void setBannerImg(String bannerImg) {
		this.bannerImg = bannerImg;
	}

	public String getDetailImg() {
		return detailImg;
	}

	public void setDetailImg(String detailImg) {
		this.detailImg = detailImg;
	}

	public Long getNumberAcount() {
		return numberAcount;
	}

	public void setNumberAcount(Long numberAcount) {
		this.numberAcount = numberAcount;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Long getCurrCount() {
		return currCount;
	}

	public void setCurrCount(Long currCount) {
		this.currCount = currCount;
	}

	public Long getLastCount() {
		return lastCount;
	}

	public void setLastCount(Long lastCount) {
		this.lastCount = lastCount;
	}

	public Long getActivityId() {
		return activityId;
	}

	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getActivityCode() {
		return activityCode;
	}

	public void setActivityCode(String activityCode) {
		this.activityCode = activityCode;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Long getPeopleCount() {
		return peopleCount;
	}

	public void setPeopleCount(Long peopleCount) {
		this.peopleCount = peopleCount;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getFullTime() {
		return fullTime;
	}

	public void setFullTime(String fullTime) {
		this.fullTime = fullTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getLotteryTime() {
		return lotteryTime;
	}

	public void setLotteryTime(String lotteryTime) {
		this.lotteryTime = lotteryTime;
	}

	public String getLotteryCode() {
		return lotteryCode;
	}

	public void setLotteryCode(String lotteryCode) {
		this.lotteryCode = lotteryCode;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
