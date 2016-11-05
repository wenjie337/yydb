package com.fw.yydb.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fw.yydb.constants.ParamConstant;
import com.fw.yydb.constants.ResultCodeConstant;
import com.fw.yydb.entiy.CartDto;
import com.fw.yydb.entiy.ProductDto;
import com.fw.yydb.service.CartService;
import com.fw.yydb.service.ProductService;
import com.fw.yydb.utils.CommonUtil;
import com.fw.yydb.utils.Errorcode;
/**
 * 购物车
 * @author wen
 *
 */
@Controller
@RequestMapping("cart")
public class CartController {
	private static final Logger LOGGER = LoggerFactory.getLogger(CartController.class);
	@Autowired
	private Errorcode errorcode;
	@Autowired
	private CartService cartService;
	@Autowired
	private ProductService productService;
	
	/**
	 * 添加商品
	 * @param reqMap
	 * @param request
	 * @return
	 */
	@RequestMapping("addCart.json")
	public @ResponseBody Map<String, Object> addCart(@RequestBody Map<String, Object> reqMap, HttpServletRequest request) {
		Map<String, Object> respMap = new HashMap<String, Object>();
		String[] params = { "productId","userId","amount"};
		try {
			if (!CommonUtil.validateParam(reqMap, params)) {
				respMap.put(ParamConstant.RESULT_CODE, ResultCodeConstant.PARAM_INVALID);
				respMap.put(ParamConstant.RESULT_DESC, errorcode.getValue(ResultCodeConstant.PARAM_INVALID));
				return respMap;
			}
			ProductDto product = productService.queryProduct(reqMap);
			if(product != null){
				reqMap.put("price", product.getPrice());
				cartService.addCart(reqMap);
				respMap.put(ParamConstant.RESULT_CODE, ResultCodeConstant.SUCCESS);
			}else{
				respMap.put(ParamConstant.RESULT_CODE, ResultCodeConstant.PRODUCT_DONE);
			}
			
		} catch (Exception e) {
			LOGGER.error("addCart has error", e);
			respMap.put(ParamConstant.RESULT_CODE, ResultCodeConstant.UNKNOW_ERROR);
			respMap.put(ParamConstant.RESULT_DESC, errorcode.getValue(ResultCodeConstant.UNKNOW_ERROR));
		}

		return respMap;
	}
	/**
	 * 修改商品
	 * @param reqMap
	 * @param request
	 * @return
	 */
	@RequestMapping("updateCart.json")
	public @ResponseBody Map<String, Object> updateCart(@RequestBody Map<String, Object> reqMap, HttpServletRequest request) {
		Map<String, Object> respMap = new HashMap<String, Object>();
		String[] params = { "productId","userId","amount"};
		try {
			if (!CommonUtil.validateParam(reqMap, params)) {
				respMap.put(ParamConstant.RESULT_CODE, ResultCodeConstant.PARAM_INVALID);
				respMap.put(ParamConstant.RESULT_DESC, errorcode.getValue(ResultCodeConstant.PARAM_INVALID));
				return respMap;
			}
			cartService.updateCart(reqMap);
			respMap.put(ParamConstant.RESULT_CODE, ResultCodeConstant.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("updateCart has error", e);
			respMap.put(ParamConstant.RESULT_CODE, ResultCodeConstant.UNKNOW_ERROR);
			respMap.put(ParamConstant.RESULT_DESC, errorcode.getValue(ResultCodeConstant.UNKNOW_ERROR));
		}

		return respMap;
	} 
	/**
	 * 查询购物车
	 * @param reqMap
	 * @param request
	 * @return
	 */
	@RequestMapping("queryCart.json")
	public @ResponseBody Map<String, Object> queryCart(@RequestBody Map<String, Object> reqMap, HttpServletRequest request) {
		Map<String, Object> respMap = new HashMap<String, Object>();
		String[] params = {"userId"};
		try {
			if (!CommonUtil.validateParam(reqMap, params)) {
				respMap.put(ParamConstant.RESULT_CODE, ResultCodeConstant.PARAM_INVALID);
				respMap.put(ParamConstant.RESULT_DESC, errorcode.getValue(ResultCodeConstant.PARAM_INVALID));
				return respMap;
			}
			List<CartDto> cartList = cartService.queryCart(reqMap);
			respMap.put(ParamConstant.PARTICIPATE_LIST, cartList);
			respMap.put(ParamConstant.RESULT_CODE, ResultCodeConstant.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("queryCart has error", e);
			respMap.put(ParamConstant.RESULT_CODE, ResultCodeConstant.UNKNOW_ERROR);
			respMap.put(ParamConstant.RESULT_DESC, errorcode.getValue(ResultCodeConstant.UNKNOW_ERROR));
		}

		return respMap;
	} 
	
	/**
	 * 删除购物车
	 * @param reqMap
	 * @param request
	 * @return
	 */
	@RequestMapping("delCart.json")
	public @ResponseBody Map<String, Object> delCart(@RequestBody Map<String, Object> reqMap, HttpServletRequest request) {
		Map<String, Object> respMap = new HashMap<String, Object>();
		String[] params = { "productId","userId"};
		try {
			if (!CommonUtil.validateParam(reqMap, params)) {
				respMap.put(ParamConstant.RESULT_CODE, ResultCodeConstant.PARAM_INVALID);
				respMap.put(ParamConstant.RESULT_DESC, errorcode.getValue(ResultCodeConstant.PARAM_INVALID));
				return respMap;
			}
			cartService.deleteCart(reqMap);
			respMap.put(ParamConstant.RESULT_CODE, ResultCodeConstant.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("delCart has error", e);
			respMap.put(ParamConstant.RESULT_CODE, ResultCodeConstant.UNKNOW_ERROR);
			respMap.put(ParamConstant.RESULT_DESC, errorcode.getValue(ResultCodeConstant.UNKNOW_ERROR));
		}

		return respMap;
	} 
	
	@RequestMapping("clearCart.json")
	public @ResponseBody Map<String, Object> clearCart(@RequestBody Map<String, Object> reqMap, HttpServletRequest request) {
		Map<String, Object> respMap = new HashMap<String, Object>();
		String[] params = { "userId"};
		try {
			if (!CommonUtil.validateParam(reqMap, params)) {
				respMap.put(ParamConstant.RESULT_CODE, ResultCodeConstant.PARAM_INVALID);
				respMap.put(ParamConstant.RESULT_DESC, errorcode.getValue(ResultCodeConstant.PARAM_INVALID));
				return respMap;
			}
			cartService.clearCart(reqMap);
			respMap.put(ParamConstant.RESULT_CODE, ResultCodeConstant.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("clearCart has error", e);
			respMap.put(ParamConstant.RESULT_CODE, ResultCodeConstant.UNKNOW_ERROR);
			respMap.put(ParamConstant.RESULT_DESC, errorcode.getValue(ResultCodeConstant.UNKNOW_ERROR));
		}

		return respMap;
	} 
}
