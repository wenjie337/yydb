package com.fw.yydb.mapper;

import java.util.Map;

import com.fw.yydb.entiy.ProductDto;


/**
 * 
 * The class ProductMapper.
 *
 * Description:产品表
 *
 * @author: yaojiewen
 * @since: 2016年9月16日
 * @version: $Revision$ $Date$ $LastChangedBy$
 *
 */
public interface ProductMapper {

	/**
	 * 查找产品
	 * @param reqMap
	 * @return
	 */
	public ProductDto queryProduct(Map<String, Object> reqMap);

}
