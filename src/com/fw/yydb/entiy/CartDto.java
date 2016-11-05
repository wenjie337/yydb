package com.fw.yydb.entiy;
/**
 * 购物车
 * @author wen
 *
 */
public class CartDto {

	/**
	 * 购物车ID
	 */
	private Long cartId;
	/**
	 * 产品ID
	 */
	private Long productId;
	/**
	 * 用户ID
	 */
	private String userId;
	/**
	 * 商品数量 
	 */
	private Long amount;
	/**
	 * 创建时间
	 */
	private String createTime;
	/**
	 * 单价：分
	 */
	private Integer price;
	
	
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Long getCartId() {
		return cartId;
	}
	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
	
	
}
