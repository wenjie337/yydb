package com.fw.yydb.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fw.yydb.annotation.AnonymousAccess;
import com.fw.yydb.constants.ParamConstant;
import com.fw.yydb.constants.ResultCodeConstant;
import com.fw.yydb.entiy.ActivityDto;
import com.fw.yydb.entiy.JoinRecord;
import com.fw.yydb.service.ActivityService;
import com.fw.yydb.service.AttendedService;
import com.fw.yydb.utils.CacheUtil;
import com.fw.yydb.utils.CommonUtil;
import com.fw.yydb.utils.Config;
import com.fw.yydb.utils.Errorcode;

/**
 * 活动
 * @author wen
 *
 */
@Controller
public class ActivityController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ActivityController.class);
	@Autowired
	private Config config;
	@Autowired
	private Errorcode errorcode;
	@Autowired
	private ActivityService activityService;
	@Autowired
	private AttendedService attendedService;

	/**
	 * 查询活动列表
	 * @param reqMap
	 * @return
	 */
	@AnonymousAccess
	@RequestMapping(value = "/queryActivityList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryActivityList(@RequestBody Map<String, Object> reqMap,
			HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> respMap = new HashMap<String, Object>();
		try {
			int pageNum = 1;
			int pageSize = config.getActivityPageSize();
			if (reqMap.get("pageNum") != null
					&& StringUtils.isNotBlank(reqMap.get("pageNum").toString())) {
				pageNum = Integer.parseInt(reqMap.get("pageNum").toString());
			}
			if (reqMap.get("pageSize") != null
					&& StringUtils.isNotBlank(reqMap.get("pageSize").toString())) {
				pageSize = Integer.parseInt(reqMap.get("pageSize").toString());
			}
			List<ActivityDto> list = activityService.queryProgressList(pageNum, pageSize);
			if (list != null && list.size() > 0) {
				for (ActivityDto dto : list) {
					String currKey = CacheUtil.getCurrentJoinNumKey(dto.getActivityId());
					String currCount = CacheUtil.get(currKey);
					if (StringUtils.isNotEmpty(currCount)) {
						dto.setCurrCount(Long.parseLong(currCount));
					} else {
						dto.setCurrCount(0L);
					}
				}
			}
			respMap.put(ParamConstant.RESULT_CODE, ResultCodeConstant.SUCCESS);
			respMap.put(ParamConstant.PRODUCT_LIST, buildResponse(list));
		} catch (Exception e) {
			LOGGER.error("queryPeriodProduct has error", e);
			respMap.put(ParamConstant.RESULT_CODE, ResultCodeConstant.UNKNOW_ERROR);
			respMap.put(ParamConstant.RESULT_DESC,
					errorcode.getValue(ResultCodeConstant.UNKNOW_ERROR));
		}

		return respMap;
	}

	/**
	 * 查询活动详情
	 * @param reqMap
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryActivityDetail", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> queryProductDetail(
			@RequestBody Map<String, Object> reqMap, HttpServletRequest request) {
		Map<String, Object> respMap = new HashMap<String, Object>();
		String[] params = { "activityId" };
		try {
			if (!CommonUtil.validateParam(reqMap, params)) {
				respMap.put(ParamConstant.RESULT_CODE, ResultCodeConstant.PARAM_INVALID);
				respMap.put(ParamConstant.RESULT_DESC,
						errorcode.getValue(ResultCodeConstant.PARAM_INVALID));
				return respMap;
			}
			String userId = "111111";
			Long activityId = Long.parseLong(reqMap.get("activityId").toString());
			ActivityDto detail = activityService.queryActivityDetail(userId, activityId);
			if (detail != null) {
				String currCount =
						CacheUtil.get(CacheUtil.getCurrentJoinNumKey(detail.getActivityId()));
				if (StringUtils.isBlank(currCount)) {
					currCount = "0";
				}
				detail.setCurrCount(Long.parseLong(currCount));
				detail.setLastCount(detail.getPeopleCount() - Long.parseLong(currCount));
			}
			respMap.put(ParamConstant.PRODUCT_DETAIL, detail);
			respMap.put(ParamConstant.RESULT_CODE, ResultCodeConstant.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("queryPeriodProduct has error", e);
			respMap.put(ParamConstant.RESULT_CODE, ResultCodeConstant.UNKNOW_ERROR);
			respMap.put(ParamConstant.RESULT_DESC,
					errorcode.getValue(ResultCodeConstant.UNKNOW_ERROR));
		}

		return respMap;
	}

	/**
	 * 查询活动参与详情
	 * @param reqMap
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryJoinRecord.json", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> queryJoinRecord(
			@RequestBody Map<String, Object> reqMap, HttpServletRequest request) {
		Map<String, Object> respMap = new HashMap<String, Object>();
		String[] params = { "activityId", "queryTime" };
		try {
			if (!CommonUtil.validateParam(reqMap, params)) {
				respMap.put(ParamConstant.RESULT_CODE, ResultCodeConstant.PARAM_INVALID);
				respMap.put(ParamConstant.RESULT_DESC,
						errorcode.getValue(ResultCodeConstant.PARAM_INVALID));
				return respMap;
			}
			int pageNum = 1;
			int pageSize = Integer.parseInt(config.getValue("activity.page.size"));
			if (reqMap.get("pageNum") != null
					&& StringUtils.isNotBlank(reqMap.get("pageNum").toString())) {
				pageNum = Integer.parseInt(reqMap.get("pageNum").toString());
			}
			if (reqMap.get("pageSize") != null
					&& StringUtils.isNotBlank(reqMap.get("pageSize").toString())) {
				pageSize = Integer.parseInt(reqMap.get("pageSize").toString());
			}

			List<JoinRecord> joinList = attendedService.queryJoinRecord(pageNum, pageSize, reqMap);
			respMap.put(ParamConstant.PARTICIPATE_LIST, joinList);
			respMap.put(ParamConstant.RESULT_CODE, ResultCodeConstant.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("queryPeriodProduct has error", e);
			respMap.put(ParamConstant.RESULT_CODE, ResultCodeConstant.UNKNOW_ERROR);
			respMap.put(ParamConstant.RESULT_DESC,
					errorcode.getValue(ResultCodeConstant.UNKNOW_ERROR));
		}

		return respMap;
	}

	/**
	 * 设置活动参与人次与剩余人次
	 * @param list
	 * @return
	 */
	private List<ActivityDto> buildResponse(List<ActivityDto> list) {
		if (list == null || list.isEmpty()) {
			return null;
		}
		List<ActivityDto> respList = new ArrayList<ActivityDto>();
		for (ActivityDto ap : list) {
			String joinNum = CacheUtil.get(CacheUtil.getCurrentJoinNumKey(ap.getActivityId()));
			if (StringUtils.isBlank(joinNum)) {
				joinNum = "0";
			}
			ap.setCurrCount(Long.parseLong(joinNum));
			ap.setLastCount(ap.getPeopleCount() - Integer.parseInt(joinNum));
			respList.add(ap);
		}

		return respList;
	}
}
