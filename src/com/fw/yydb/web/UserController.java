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

import com.fw.yydb.constants.ParamConstant;
import com.fw.yydb.constants.ResultCodeConstant;
import com.fw.yydb.entiy.UserDto;
import com.fw.yydb.service.UserService;
import com.fw.yydb.utils.CommonUtil;
import com.fw.yydb.utils.Errorcode;

/**
 * 
 * The class UserController.
 *
 * Description:用户信息接口
 *
 * @author: yaojiewen
 * @since: 2016年9月15日
 * @version: $Revision$ $Date$ $LastChangedBy$
 *
 */
@Controller("userController")
@RequestMapping("user")
public class UserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private Errorcode errorcode;
	@Autowired
	private UserService userService;

	/**
	 * 提交用户信息
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/updateUser.json")
	public @ResponseBody Map<String, Object> updateUser(@RequestBody Map<String, Object> reqMap,
			HttpServletRequest request) {
		Map<String, Object> respMap = new HashMap<String, Object>();
		String[] params = { "userId" };
		try {
			if (!CommonUtil.validateParam(reqMap, params)) {
				respMap.put(ParamConstant.RESULT_CODE, ResultCodeConstant.PARAM_INVALID);
				respMap.put(ParamConstant.RESULT_DESC,
						errorcode.getValue(ResultCodeConstant.PARAM_INVALID));
				return respMap;
			}
			userService.saveOrUpdate(reqMap);
			respMap.put(ParamConstant.RESULT_CODE, ResultCodeConstant.SUCCESS);
			respMap.put(ParamConstant.RESULT_DESC, errorcode.getValue(ResultCodeConstant.SUCCESS));

		} catch (Exception e) {
			LOGGER.error("系统异常", e);
			respMap.put(ParamConstant.RESULT_CODE, ResultCodeConstant.UNKNOW_ERROR);
			respMap.put(ParamConstant.RESULT_DESC,
					errorcode.getValue(ResultCodeConstant.UNKNOW_ERROR));
		}

		return respMap;
	}

	/**
	 * 查询用户信息
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/queryUser.json")
	public @ResponseBody Map<String, Object> queryUser(@RequestBody Map<String, Object> reqMap,
			HttpServletRequest request) {
		Map<String, Object> respMap = new HashMap<String, Object>();
		String[] params = { "userId" };
		try {
			if (!CommonUtil.validateParam(reqMap, params)) {
				respMap.put(ParamConstant.RESULT_CODE, ResultCodeConstant.PARAM_INVALID);
				respMap.put(ParamConstant.RESULT_DESC,
						errorcode.getValue(ResultCodeConstant.PARAM_INVALID));
				return respMap;
			}
			UserDto userDto = userService.queryUser(reqMap);
			respMap.put("userId", userDto.getUserId());
			respMap.put("mobile", userDto.getMobile());
			respMap.put("nickName", userDto.getNickName());
			respMap.put("address", userDto.getAddress());
			respMap.put(ParamConstant.RESULT_CODE, ResultCodeConstant.SUCCESS);
			respMap.put(ParamConstant.RESULT_DESC, errorcode.getValue(ResultCodeConstant.SUCCESS));

		} catch (Exception e) {
			LOGGER.error("系统异常", e);
			respMap.put(ParamConstant.RESULT_CODE, ResultCodeConstant.UNKNOW_ERROR);
			respMap.put(ParamConstant.RESULT_DESC,
					errorcode.getValue(ResultCodeConstant.UNKNOW_ERROR));
		}

		return respMap;
	}
}
