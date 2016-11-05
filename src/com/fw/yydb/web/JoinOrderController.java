package com.fw.yydb.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class JoinOrderController {

//	private static final Logger LOGGER = LoggerFactory.getLogger(JoinOrderController.class);
//
//	@Autowired
//	private JoinOrderSvc joinOrderSvc;
//	@Autowired
//	private ErrorCodeConfig errorcodeConf;
//	@Autowired
//	private YydbDisConfig yydbDisConfig;
//
//	@Autowired
//	private CommonDBSvc commonDBSvc;
//
//	/**
//	 * 查询参与订单记录
//	 * @param reqMap
//	 * @return
//	 */
//	@RequestMapping(value = "/v1/queryJoinOrder.json", method = RequestMethod.POST)
//	public @ResponseBody Map<String, Object> queryJoinOrder(
//			@RequestBody Map<String, Object> reqMap, HttpServletRequest request) {
//		Map<String, Object> respMap = new HashMap<String, Object>();
//		String[] params = { "periodsId" };
//		try {
//			String queryTime = (String) reqMap.get("queryTime");
//
//			if (!CommonUtil.validateParam(reqMap, params)) {
//				respMap.put(ParamConstant.RESULT_CODE, ResultCodeConstant.PARAM_INVALID);
//				respMap.put(ParamConstant.RESULT_DESC, errorcodeConf.getQB_160012());
//				return respMap;
//			}
//
//			Long periodsId = Long.parseLong(reqMap.get("periodsId").toString());
//			int number = yydbDisConfig.getPeriodProductPageNum();
//			List<JoinOrder> list = null;
//			if (StringUtils.isEmpty(queryTime)) {
//				list = joinOrderSvc.queryTopList(number, periodsId);
//				queryTime = commonDBSvc.queryCurrentDBTime();
//				respMap.put("queryTime", queryTime);
//				respMap.put("page", 1);
//			} else {
//				Integer pageNo =
//						reqMap.get("pageNo") == null ? 1 : Integer.parseInt(reqMap.get("pageNo")
//								.toString());
//				int min = (pageNo - 1) * number;
//				int max = pageNo * number;
//				list = joinOrderSvc.queryFlipPage(min, max, queryTime, periodsId);
//				respMap.put("queryTime", queryTime);
//				respMap.put("page", pageNo);
//			}
//			respMap.put(ParamConstant.RESULT_CODE, ResultCodeConstant.SUCCESS);
//			respMap.put(ParamConstant.PARTICIPATE_LIST, list);
//		} catch (Exception e) {
//			LOGGER.error("queryJoinOrder has error", e);
//			respMap.put(ParamConstant.RESULT_CODE, ResultCodeConstant.UNKNOW_ERROR);
//			respMap.put(ParamConstant.RESULT_DESC, errorcodeConf.getQB_160099());
//		}
//
//		return respMap;
//	}
}
