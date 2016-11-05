package com.fw.yydb.entiy;

/**
 * 
 * The class ProductDto.
 *
 * Description:产品
 *
 * @author: yaojiewen
 * @since: 2016年9月16日
 * @version: $Revision$ $Date$ $LastChangedBy$
 *
 */
public class ProductDto {

	/**
	 * 产品ID
	 */
	private Long productId;
	/**
	 * 产品名称
	 */
	private String productName;
	/**
	 * 产品标题
	 */
	private String productTitle;
	/**
	 * 产品描述 
	 */
	private String productDesc;
	/**
	 * 所需人次
	 */
	private Long peopleCount;
	/**
	 * 状态0上架1下架
	 */
	private Long status;
	/**
	 * 产品单价
	 */
	private Long price;
	/**
	 * 产品标号
	 */
	private String productNum;
	/**
	 * 单次最大购买数
	 */
	private Long maxCount;
	/**
	 * 活动ID
	 */
	private Long activityId;

	public Long getActivityId() {
		return activityId;
	}

	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}

	public Long getMaxCount() {
		return maxCount;
	}

	public void setMaxCount(Long maxCount) {
		this.maxCount = maxCount;
	}

	public String getProductNum() {
		return productNum;
	}

	public void setProductNum(String productNum) {
		this.productNum = productNum;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
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

	public Long getPeopleCount() {
		return peopleCount;
	}

	public void setPeopleCount(Long peopleCount) {
		this.peopleCount = peopleCount;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

}
