package com.fw.yydb.entiy;

/**
 * 所有错误码详情
 * @title ErrorCodeDetail.java
 * @package com.iboxpay.fullpay.domain
 * @author niejing@iboxpay.com
 * @time 2013-11-8 下午4:07:58
 */
public class ErrorCode {

	private String errorCode;
	private String errorDesc;
	private String remark;

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorDesc() {
		return errorDesc;
	}

	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
