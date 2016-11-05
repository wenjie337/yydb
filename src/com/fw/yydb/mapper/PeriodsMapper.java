package com.fw.yydb.mapper;

import java.util.List;
import java.util.Map;

import com.fw.yydb.entiy.PeriodsDto;


public interface PeriodsMapper {

	/**
	 * 根据状态查询活动
	 * @param map
	 * @return
	 */
	public List<PeriodsDto> queryListByStatus(Map<String, Object> map);

	/**
	 * 查询下一期信息
	 * @param map
	 * @return
	 */
	PeriodsDto queryPeriods(Map<String, Object> map);

	/**
	 * 更新
	 * @param map
	 */
	void updatePeriod(Map<String, Object> map);

	/**
	 * 新增
	 * @param map
	 */
	void addPeriod(Map<String, Object> map);
	
	List<PeriodsDto> findLottery();
}


