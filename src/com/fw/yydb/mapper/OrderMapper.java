package com.fw.yydb.mapper;

import java.util.List;
import java.util.Map;

import com.fw.yydb.entiy.OrderDto;


public interface OrderMapper {

	/**
	 * 生成订单
	 * @param reqMap
	 */
	public void addOrder(Map<String, Object> reqMap);

	/**
	 * 更新订单状态
	 * @param reqMap
	 */
	public void updateOrder(Map<String, Object> reqMap);

	/**
	 * 查询订单状态
	 * @param reqMap
	 * @return
	 */
	public OrderDto queryOrder(Map<String, Object> reqMap);

	public List<OrderDto> queryOrderJob();

	/**
	 * 查询最近50条
	 * @return
	 */
	public List<OrderDto> queryTopList();
	
	
}
