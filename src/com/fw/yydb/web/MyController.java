package com.fw.yydb.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fw.yydb.constants.ParamConstant;
import com.fw.yydb.constants.ResultCodeConstant;
import com.fw.yydb.entiy.JoinRecord;
import com.fw.yydb.service.AttendedService;
import com.fw.yydb.utils.CommonUtil;
import com.fw.yydb.utils.Config;
import com.fw.yydb.utils.Errorcode;

/**
 * 我
 * @author wen
 *
 */
@Controller
@RequestMapping("my")
public class MyController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MyController.class);
	@Autowired
	private Config config;
	@Autowired
	private Errorcode errorcode;
	@Autowired
	private AttendedService attendedService;
	/**
	 * 我的参与记录
	 * @param reqMap
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/joinRecord.json", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> myJoinRecord(@RequestBody Map<String, Object> reqMap, HttpServletRequest request) {
		Map<String, Object> respMap = new HashMap<String, Object>();
		String[] params = { "userId" };
		try {
			if (!CommonUtil.validateParam(reqMap, params)) {
				respMap.put(ParamConstant.RESULT_CODE, ResultCodeConstant.PARAM_INVALID);
				respMap.put(ParamConstant.RESULT_DESC, errorcode.getValue(ResultCodeConstant.PARAM_INVALID));
				return respMap;
			}
			String pageNumStr= String.valueOf(reqMap.get("pageNum"));
			String pageSizeStr= String.valueOf(reqMap.get("pageSize"));
			int pageNum = 1;
			int pageSize = config.getActivityPageSize();
			if(!StringUtils.isBlank(pageNumStr)){
				pageNum = Integer.parseInt(pageNumStr);
			}
			if(!StringUtils.isBlank(pageSizeStr)){
				pageSize = Integer.parseInt(pageSizeStr);
			}
			
			List<JoinRecord> joinList = attendedService.myJoinRecord(pageNum,pageSize,reqMap);
			respMap.put(ParamConstant.PARTICIPATE_LIST, joinList);
			respMap.put(ParamConstant.RESULT_CODE, ResultCodeConstant.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("myJoinRecord has error", e);
			respMap.put(ParamConstant.RESULT_CODE, ResultCodeConstant.UNKNOW_ERROR);
			respMap.put(ParamConstant.RESULT_DESC, errorcode.getValue(ResultCodeConstant.UNKNOW_ERROR));
		}

		return respMap;
	}
}
