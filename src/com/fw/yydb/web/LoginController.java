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

/**
 * 
 * The class LoginController.
 *
 * Description:登录校验 
 *
 * @author: yaojiewen
 * @since: 2016年9月22日
 * @version: $Revision$ $Date$ $LastChangedBy$
 *
 */
@Controller
public class LoginController {

//	private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
//	@Autowired
//	private ErrorCodeConfig errorcodeConf;
//	@Autowired
//	private YydbDisConfig yydbDisConfig;
//	@Autowired
//	private UcTokenApi ucTokenApi;
//
//	@AnonymousAccess
//	@RequestMapping("/v1/login.json")
//	public @ResponseBody Map<String, Object> login(@RequestBody Map<String, Object> reqMap,
//			HttpServletRequest request) {
//		Map<String, Object> respMap = new HashMap<String, Object>();
//		respMap.put(ParamConstant.RESULT_CODE, ResultCodeConstant.SUCCESS);
//		respMap.put(ParamConstant.RESULT_DESC, errorcodeConf.getQB_160001());
//		try {
//
//			if (reqMap.get("token") == null || "".equals(reqMap.get("token"))) {
//				respMap.put(ParamConstant.RESULT_CODE, ResultCodeConstant.PARAM_INVALID);
//				respMap.put(ParamConstant.RESULT_DESC, errorcodeConf.getQB_160012());
//				return respMap;
//			}
//			GenericRequestDto dto = new GenericRequestDto();
//			dto.put("token", reqMap.get("token"));
//			GenericResponseDto respDto = ucTokenApi.authenticate(dto);
//			if (respDto.isOk()) {
//				String key = RedisHelper.getTokenKey(request.getSession().getId());
//				RedisUtility.store(key, respDto, yydbDisConfig.getTokenInvalide());
//			} else {
//				respMap.put(ParamConstant.RESULT_CODE, ResultCodeConstant.TOKEN_IS_ERR);
//				respMap.put(ParamConstant.RESULT_DESC, errorcodeConf.getQB_160011());
//				return respMap;
//			}
//		} catch (Exception e) {
//			LOGGER.error("系统异常", e);
//			respMap.put(ParamConstant.RESULT_CODE, ResultCodeConstant.UNKNOW_ERROR);
//			respMap.put(ParamConstant.RESULT_DESC, errorcodeConf.getQB_160099());
//		}
//		return respMap;
//	}
}
