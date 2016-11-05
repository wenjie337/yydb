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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ComputeController {
//
//	private static final Logger LOGGER = LoggerFactory.getLogger(ComputeController.class);
//	@Autowired
//	private ErrorCodeConfig errorcodeConf;
//	@Autowired
//	private ComputeDetailSvc computeDetailSvc;
//
//	/**
//	 * 查询参与订单记录
//	 * @param reqMap
//	 * @return
//	 */
//	@RequestMapping(value = "/v1/queryComputeDetail.json", method = RequestMethod.POST)
//	public @ResponseBody Map<String, Object> queryComputeDetail(
//			@RequestBody Map<String, Object> reqMap, HttpServletRequest request) {
//		Map<String, Object> respMap = new HashMap<String, Object>();
//		String[] params = { "periodsId" };
//		try {
//
//			if (!CommonUtil.validateParam(reqMap, params)) {
//				respMap.put(ParamConstant.RESULT_CODE, ResultCodeConstant.PARAM_INVALID);
//				respMap.put(ParamConstant.RESULT_DESC, errorcodeConf.getQB_160012());
//				return respMap;
//			}
//			Long periodsId = Long.parseLong(reqMap.get("periodsId").toString());
//
//			ComputeDetail detail = computeDetailSvc.queryComputeDetail(periodsId);
//			respMap.put(ParamConstant.RESULT_CODE, ResultCodeConstant.SUCCESS);
//			respMap.put(ParamConstant.COMPUTE_DETAIL, detail);
//		} catch (Exception e) {
//			LOGGER.error("queryComputeDetail has error", e);
//			respMap.put(ParamConstant.RESULT_CODE, ResultCodeConstant.UNKNOW_ERROR);
//			respMap.put(ParamConstant.RESULT_DESC, errorcodeConf.getQB_160099());
//		}
//
//		return respMap;
//	}
}
