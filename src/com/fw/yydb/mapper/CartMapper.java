package com.fw.yydb.mapper;

import java.util.List;
import java.util.Map;

import com.fw.yydb.entiy.CartDto;

public interface CartMapper {

	/**
	 * 添加商品
	 * @param map
	 */
	public void addCart(Map<String,Object> map);
	/**
	 * 修改购物车
	 * @param map
	 */
	public void updateCart(Map<String,Object> map);
	/**
	 * 删除购物车
	 * @param map
	 */
	public void deleteCart(Map<String,Object> map);
	
	/**
	 * 清空购物车
	 * @param map
	 */
	public void clearCart(Map<String,Object> map);
	/**
	 * 查询购物车
	 * @param map
	 */
	public List<CartDto> queryCart(Map<String,Object> map);
}
