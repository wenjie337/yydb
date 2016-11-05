package com.fw.yydb.constants;

public interface ResultCodeConstant {

	// 成功
	String SUCCESS = "1";
	String SUCCESS_MSG = "成功";

	//未登录
	String NOT_LOGIN = "02";
	String NOT_LOGIN_MSG = "请登录";
	//参与次数为空
	String PEOPLE_COUNT_IS_NULL = "03";
	String PEOPLE_COUNT_IS_NULL_MSG = "参与次数为空";
	//参与小于等于0
	String PEOPLE_COUNT_GREATER_ZERO = "04";
	String PEOPLE_COUNT_GREATER_ZERO_MSG = "参与小于等于0";
	//产品下架
	String PRODUCT_DONE = "05";
	String PRODUCT_DONE_MSG = "产品已下架";
	//订单号为空
	String ORDER_SERIAL_IS_NULL = "06";
	String ORDER_SERIAL_IS_NULL_MSG = "订单号不存在";
	//活动未开始或已结束
	String PERIOD_IS_NOT_START = "07";
	String PERIOD_IS_NOT_START_MSG = "活动未开始或已结束";
	//token为空
	String TOKEN_IS_NULL = "08";
	String TOKEN_IS_NULL_MSG = "TOKEN为空";
	//超过产品单次购买的最大次数
	String PRODUCT_OVER_FLOW = "09";
	String PRODUCT_OVER_FLOW_MSG = "超过产品单次购买最大次数";
	//无卡下单异常
	String NOCARD_CREATE_ORDER_ERR = "10";
	String NOCARD_CREATE_ORDER_ERR_MSG = "无卡下单异常";
	//没有获取到token对应的用户登录信息
	String TOKEN_IS_ERR = "11";
	String TOKEN_IS_ERR_MSG = "没有获取到token对应的用户登录信息，请重新登录";
	//参数校验失败
	String PARAM_INVALID = "12";
	String PARAM_INVALID_MSG = "参数校验失败";
	//未知错误
	String UNKNOW_ERROR = "99";
	String UNKNOW_ERROR_MSG = "未知错误";

}
