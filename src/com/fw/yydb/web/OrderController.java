package com.fw.yydb.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fw.yydb.constants.Constants;
import com.fw.yydb.constants.ParamConstant;
import com.fw.yydb.constants.ResultCodeConstant;
import com.fw.yydb.entiy.OrderDto;
import com.fw.yydb.service.OrderService;
import com.fw.yydb.utils.CommonUtil;
import com.fw.yydb.utils.Errorcode;

/**
 * 
 * The class OrderController.
 *
 * Description:订单
 *
 * @author: yaojiewen
 * @since: 2016年9月16日
 * @version: $Revision$ $Date$ $LastChangedBy$
 *
 */
@Controller
@RequestMapping("order")
public class OrderController {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);
	@Autowired
	private Errorcode errorcode;
	@Autowired
	private OrderService orderService;

	/**
	 * 生成订单
	 * @param reqMap
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/createOrder.json")
	public @ResponseBody Map<String, Object> createOrder(@RequestBody Map<String, Object> reqMap,
			HttpServletRequest request) {
		Map<String, Object> respMap = new HashMap<String, Object>();
		respMap.put(ParamConstant.RESULT_CODE, ResultCodeConstant.SUCCESS);
		String[] params = { "productId", "userId", "num" };
		try {
			if (!CommonUtil.validateParam(reqMap, params)) {
				respMap.put(ParamConstant.RESULT_CODE, ResultCodeConstant.PARAM_INVALID);
				respMap.put(ParamConstant.RESULT_DESC,
						errorcode.getValue(ResultCodeConstant.PARAM_INVALID));
				return respMap;
			}
			orderService.createOrder(reqMap, respMap, request);

		} catch (Exception e) {
			LOGGER.error("系统异常", e);
			respMap.put(ParamConstant.RESULT_CODE, ResultCodeConstant.UNKNOW_ERROR);
			respMap.put(ParamConstant.RESULT_DESC,
					errorcode.getValue(ResultCodeConstant.UNKNOW_ERROR));
		}
		return respMap;
	}

	/**
	 * 支付成功更新订单状态
	 * @param reqMap
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/updateOrder.json")
	public @ResponseBody Map<String, Object> queryOrder(@RequestBody Map<String, Object> reqMap,
			HttpServletRequest request) {
		Map<String, Object> respMap = new HashMap<String, Object>();
		respMap.put(ParamConstant.RESULT_CODE, ResultCodeConstant.SUCCESS);
		String[] params = { "orderSerial" };
		try {
			if (!CommonUtil.validateParam(reqMap, params)) {
				respMap.put(ParamConstant.RESULT_CODE, ResultCodeConstant.PARAM_INVALID);
				respMap.put(ParamConstant.RESULT_DESC,
						errorcode.getValue(ResultCodeConstant.PARAM_INVALID));
				return respMap;
			}
			OrderDto orderDto = orderService.queryOrder(reqMap);
			if (orderDto == null) {
				respMap.put(ParamConstant.RESULT_CODE, ResultCodeConstant.ORDER_SERIAL_IS_NULL);
				respMap.put(ParamConstant.RESULT_DESC,
						errorcode.getValue(ResultCodeConstant.ORDER_SERIAL_IS_NULL));
				return respMap;
			}
			//如果 支付中或者待支付，查询无卡订单状态
			if (Constants.ORDER_STATUS.TO_BE_PAY == orderDto.getOrderStatus()) {
				//更新订单
				orderService.executeOrder(reqMap, respMap);
			}
		} catch (Exception e) {
			LOGGER.error("系统异常", e);
			respMap.put(ParamConstant.RESULT_CODE, ResultCodeConstant.UNKNOW_ERROR);
			respMap.put(ParamConstant.RESULT_DESC,
					errorcode.getValue(ResultCodeConstant.UNKNOW_ERROR));
		}
		return respMap;
	}
}
