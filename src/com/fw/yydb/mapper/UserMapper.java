package com.fw.yydb.mapper;

import java.util.Map;

import com.fw.yydb.entiy.UserDto;

/**
 * 
 * The class UserMapper.
 *
 * Description:用户信息
 *
 * @author: yaojiewen
 * @since: 2016年9月15日
 * @version: $Revision$ $Date$ $LastChangedBy$
 *
 */
public interface UserMapper {

	/**
	 * 新增更新用户
	 * @param reqMap
	 */
	public void saveOrUpdate(Map<String, Object> userDto);

	/**
	 * 查找用户
	 * @param reqMap
	 * @return
	 */
	public UserDto queryUser(Map<String, Object> userDto);
}
