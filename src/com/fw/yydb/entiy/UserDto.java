package com.fw.yydb.entiy;

/**
 * 
 * The class UserDto.
 *
 * Description:用户信息
 *
 * @author: yaojiewen
 * @since: 2016年9月15日
 * @version: $Revision$ $Date$ $LastChangedBy$
 *
 */
public class UserDto {

	/**
	 * 用户ID
	 */
	private String userId;
	/**
	 * 用户昵称
	 */
	private String nickName;
	/**
	 * 收货手机号码
	 */
	private String mobile;
	/**
	 * 收货地址
	 */
	private String address;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
