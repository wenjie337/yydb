package com.fw.yydb.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fw.yydb.entiy.UserDto;
import com.fw.yydb.mapper.UserMapper;

/**
 * 
 * The class UserService.
 *
 * Description:
 *
 * @author: yaojiewen
 * @since: 2016年9月15日
 * @version: $Revision$ $Date$ $LastChangedBy$
 *
 */
@Service("userService")
public class UserService {

	@Autowired
	private UserMapper userMapper;

	/**
	 * 保存更新用户信息
	 * @param userDto
	 */
	public void saveOrUpdate(Map<String, Object> userDto) {
		userMapper.saveOrUpdate(userDto);
	}

	/**
	 * 查询用户信息
	 * @param userId
	 * @return
	 */
	public UserDto queryUser(Map<String, Object> userDto) {
		return userMapper.queryUser(userDto);
	}

	/**
	 * 获取用户名称
	 * @param request
	 * @return
	 */
	public String getUserName(HttpServletRequest request) {
//		String key = RedisHelper.getTokenKey(request.getSession().getId());
//		RedisObject ro = RedisUtility.fetch(key);
//		if (ro.getValue() != null) {
//			GenericResponseDto respDto = (GenericResponseDto) ro.getValue();
//			String userName = respDto.getStringData("userName");
//			if (StringUtils.isNotEmpty(userName)) {
//				return getCryptUserName(userName);
//			}
//		}
		return "";
	}

	/**
	 * 身份证姓名首个字去敏
	 * @param userName
	 * @return
	 */
	private String getCryptUserName(String userName) {
		if (StringUtils.isEmpty(userName)) {
			return null;
		}

		if (userName.indexOf("*") >= 0) {
			return userName;
		}

		int len = userName.trim().length();
		char[] cryptNameArr = new char[len];
		cryptNameArr[0] = '*';
		System.arraycopy(userName.toCharArray(), 1, cryptNameArr, 1, len - 1);

		return new String(cryptNameArr);
	}
}
