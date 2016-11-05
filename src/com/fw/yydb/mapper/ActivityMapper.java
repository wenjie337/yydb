package com.fw.yydb.mapper;

import java.util.List;
import java.util.Map;

import com.fw.yydb.entiy.ActivityDto;

/**
 * 活动
 * @author wen
 *
 */
public interface ActivityMapper {

	/**
	 * 查询列表
	 * @param map
	 * @return
	 */
	public List<ActivityDto> queryList(Map<String, Object> map);

	/**
	 * 查询活动详情
	 * @param map
	 * @return
	 */
	public ActivityDto queryActivity(Map<String, Object> map);

	/**
	 * 更新
	 */
	public void updateActivity(Map<String, Object> map);

	/**
	 * 根据状态查询活动
	 * @param map
	 * @return
	 */
	public List<ActivityDto> queryListByStatus(Map<String, Object> map);

	/**
	 * 新增活动
	 * @param map
	 */
	public void addActivity(Map<String, Object> map);

	/**
	 * 查询待抽奖活动
	 * @return
	 */
	public List<ActivityDto> findLottery();
}
