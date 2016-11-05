package com.fw.yydb.entiy;

/**
 * 
 * The class OrderDto.
 *
 * Description:订单
 *
 * @author: yaojiewen
 * @since: 2016年9月16日
 * @version: $Revision$ $Date$ $LastChangedBy$
 *
 */
public class OrderDto {

	private String orderId;
	/**
	 * 昵称
	 */
	private String nickName;
	/**
	 * 订单流水号
	 */
	private String orderSerial;

	/**
	 * 订单标题
	 */
	private String orderTitle;
	/**
	 * 订单金额
	 */
	private Long orderMoney;
	/**
	 * 支付金额
	 */
	private Long payMoney;
	/**
	 * 用户ID
	 */
	private String userId;
	/**
	 * 商品ID
	 */
	private Long productId;
	/**
	 * 活动ID
	 */
	private Long activityId;
	/**
	 * 购买数量
	 */
	private Long amount;
	/**
	 * 订单生成时间
	 */
	private String createTime;
	/**
	 * 支付时间
	 */
	private String payTime;
	/**
	 * 订单状态 0待支付1支付中2支付成功3支付失败
	 */
	private Long orderStatus;
	
	private Long subTime;
	
	
	
	public Long getSubTime() {
		return subTime;
	}
	public void setSubTime(Long subTime) {
		this.subTime = subTime;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getOrderSerial() {
		return orderSerial;
	}
	public void setOrderSerial(String orderSerial) {
		this.orderSerial = orderSerial;
	}
	public String getOrderTitle() {
		return orderTitle;
	}
	public void setOrderTitle(String orderTitle) {
		this.orderTitle = orderTitle;
	}
	public Long getOrderMoney() {
		return orderMoney;
	}
	public void setOrderMoney(Long orderMoney) {
		this.orderMoney = orderMoney;
	}
	public Long getPayMoney() {
		return payMoney;
	}
	public void setPayMoney(Long payMoney) {
		this.payMoney = payMoney;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
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
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getPayTime() {
		return payTime;
	}
	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}
	public Long getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(Long orderStatus) {
		this.orderStatus = orderStatus;
	}
	


}
