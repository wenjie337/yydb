package com.fw.yydb.web;

import java.util.ArrayList;
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
public class JoinRecordController {
//
//	private static final Logger LOGGER = LoggerFactory.getLogger(JoinRecordController.class);
//
//	@Autowired
//	private JoinRecordSvc joinRecordSvc;
//
//	@Autowired
//	private YydbDisConfig yydbDisConfig;
//	@Autowired
//	private ErrorCodeConfig errorcodeConf;
//
//	@Autowired
//	private CommonDBSvc commonDBSvc;
//
//	/**
//	 * 我的参与记录
//	 * 
//	 * @param model
//	 * @param request
//	 * @param response
//	 * @return
//	 */
//	@RequestMapping(value = "/v1/queryJoinRecord.json", method = RequestMethod.POST)
//	public @ResponseBody Map<String, Object> queryPeriodProduct(
//			@RequestBody Map<String, Object> reqMap, HttpServletRequest request) {
//		Map<String, Object> respMap = new HashMap<String, Object>();
//		try {
//			String queryTime = (String) reqMap.get("queryTime");
//			String userID = (String) reqMap.get("userId");
//			int number = yydbDisConfig.getPeriodProductPageNum();
//			List<JoinRecord> list = null;
//			if (StringUtils.isEmpty(queryTime)) {
//				list = joinRecordSvc.queryTopList(number, userID);
//				queryTime = commonDBSvc.queryCurrentDBTime();
//				respMap.put("queryTime", queryTime);
//				respMap.put("page", 1);
//			} else {
//				Integer pageNo =
//						reqMap.get("pageNo") == null ? null : Integer.parseInt(reqMap.get("pageNo")
//								.toString());
//				int min = (pageNo - 1) * number;
//				int max = pageNo * number;
//				list = joinRecordSvc.queryFlipPage(min, max, queryTime, userID);
//				respMap.put("queryTime", queryTime);
//				respMap.put("page", pageNo);
//			}
//			respMap.put(ParamConstant.RESULT_CODE, ResultCodeConstant.SUCCESS);
//			respMap.put(ParamConstant.PRODUCT_LIST, buildResponse(list));
//		} catch (Exception e) {
//			LOGGER.error("queryPeriodProduct has error", e);
//			respMap.put(ParamConstant.RESULT_CODE, ResultCodeConstant.UNKNOW_ERROR);
//			respMap.put(ParamConstant.RESULT_DESC, errorcodeConf.getQB_160099());
//		}
//
//		return respMap;
//	}
//
//	private List<JoinRecord> buildResponse(List<JoinRecord> list) {
//		if (list == null || list.isEmpty()) {
//			return null;
//		}
//		List<JoinRecord> respList = new ArrayList<JoinRecord>();
//		String joinNum = null;
//		for (JoinRecord record : list) {
//			RedisObject ro =
//					RedisUtility.fetch(RedisHelper.getCurrentJoinNumKey(record.getProductId(),
//							record.getPeriodsId()));
//			if (ro.getValue() != null) {
//				joinNum = String.valueOf(ro.getValue());
//			}
//			if (StringUtils.isBlank(joinNum)) {
//				joinNum = "0";
//			}
//			record.setCurrCount(Long.parseLong(joinNum));
//			record.setLastCount(record.getPeopleCount() - Integer.parseInt(joinNum));
//			respList.add(record);
//		}
//
//		return respList;
//	}
}
