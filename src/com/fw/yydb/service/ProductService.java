package com.fw.yydb.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fw.yydb.entiy.ProductDto;
import com.fw.yydb.mapper.ProductMapper;

@Service
public class ProductService {

	@Autowired
	private ProductMapper productMapper;

	public ProductDto queryProduct(Map<String, Object> map) {
		return productMapper.queryProduct(map);
	}

}
