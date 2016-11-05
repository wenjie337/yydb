package com.fw.yydb.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fw.yydb.entiy.CartDto;
import com.fw.yydb.entiy.ProductDto;
import com.fw.yydb.mapper.CartMapper;
import com.fw.yydb.mapper.ProductMapper;
/**
 * 购物车
 * @author wen
 *
 */
@Service
public class CartService {
	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private CartMapper cartMapper;
	
	public List<CartDto> queryCart(Map<String,Object> map){
		
		return cartMapper.queryCart(map);
	}
	/**
	 * 添加购物车
	 */
	public void addCart(Map<String,Object> map){
		ProductDto product = productMapper.queryProduct(map);
		if(product != null){
			map.put("price", product.getPrice());
			cartMapper.addCart(map);
		}else{
			
		}
	}
	
	/**
	 * 更新购物车
	 * @param map
	 */
	public void updateCart(Map<String,Object> map){
		cartMapper.updateCart(map);
	}
	/**
	 * 删除商品
	 * @param map
	 */
	public void deleteCart(Map<String,Object> map){
		
		cartMapper.deleteCart(map);
	}
	
	/**
	 * 清空购物车
	 * @param map
	 */
	public void clearCart(Map<String,Object> map){
		
		cartMapper.clearCart(map);
	}
}
