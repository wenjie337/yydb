package com.fw.yydb.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fw.yydb.constants.Constants;
import com.fw.yydb.entiy.JoinRecord;
import com.fw.yydb.mapper.AttendedMapper;
import com.fw.yydb.utils.CacheUtil;

/**
 * 
 * The class AttendedService.
 *
 * Description:参与活动
 *
 * @author: yaojiewen
 * @since: 2016年9月19日
 * @version: $Revision$ $Date$ $LastChangedBy$
 *
 */
@Service
public class AttendedService {

	@Autowired
	private AttendedMapper attendedMapper;

	/**
	 * 参与活动
	 * 
	 * @param map
	 */
	public void addAttended(Map<String, Object> map) {
		Long activityId = (Long) map.get("activityId");
		int range = (int) map.get("range");
		int num = (int) map.get("num");
		// 生成抽奖号码
		List<Long> numberList = initCurrLotteryList(activityId, range, num);
		if (numberList != null && numberList.size() > 0) {
			map.put("numberList", numberList);
			attendedMapper.addAttended(map);
		}

		updateCache(activityId, num);
	}

	/**
	 * 修改redis中数量
	 * 
	 * @param productId
	 * @param periodsId
	 * @param num
	 */
	public void updateCache(Long activityId, int num) {
		// 修改缓存中已参与人次
		String key = CacheUtil.getCurrentJoinNumKey(activityId);
		int value = Integer.parseInt(CacheUtil.get(key));
		CacheUtil.put(key, value + num);
	}

	/**
	 * 生成当前商品当前期数的所有号码，并放入redis
	 * 
	 * @param periodsId
	 * @param productId
	 * @param range
	 */
	public void initAllNumber(Long activityId, Long range) {
		for (int i = 0; i < range; i++) {
			Long next = i + Constants.START_NUMBER;
			String key = CacheUtil.getInvolvementNumKey(activityId, next);
			CacheUtil.put(key, next);
		}
	}

	/**
	 * 生成参与抽奖号码
	 * 
	 * @param orderDto
	 * @param range
	 * @return
	 */
	private List<Long> initCurrLotteryList(Long activityId, int range, int num) {
		List<Long> numberList = new ArrayList<Long>();
		int i = 0;
		while (i < num) {
			Random random = new Random();
			Long next = random.nextInt(range) + Constants.START_NUMBER;
			if (!numberList.contains(next)) {
				String key = CacheUtil.getInvolvementNumKey(activityId, next);
				String value = CacheUtil.get(key);
				if (!StringUtils.isBlank(value)) {
					CacheUtil.deleteKey(key);
					numberList.add(next);
					i++;
				}
			}
		}
		return numberList;
	}

	public List<Integer> queryCurrUserNums(String userID, Long activityId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("activityId", activityId);
		map.put("userID", userID);

		return attendedMapper.queryCurrUserNums(map);
	}

	/**
	 * 查询参与详情
	 * @param activityId
	 * @param queryTime
	 * @return
	 */
	public List<JoinRecord> queryJoinRecord(int pageNo, int pageSize, Map<String, Object> map) {
		map.put("startRows", (pageNo - 1) * pageSize);
		map.put("endRows", pageNo * pageSize);
		return attendedMapper.queryJoinRecord(map);
	}

	/**
	 * 我的参与记录
	 * @param activityId
	 * @param queryTime
	 * @return
	 */
	public List<JoinRecord> myJoinRecord(int pageNo, int pageSize, Map<String, Object> map) {
		map.put("startRows", (pageNo - 1) * pageSize);
		map.put("endRows", pageNo * pageSize);
		return attendedMapper.queryJoinRecord(map);
	}

}
