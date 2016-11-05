package com.fw.yydb.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fw.yydb.constants.Constants;
import com.fw.yydb.entiy.ActivityDto;
import com.fw.yydb.entiy.LotteryTimeDto;
import com.fw.yydb.entiy.OrderDto;
import com.fw.yydb.entiy.ProductDto;
import com.fw.yydb.mapper.ActivityMapper;
import com.fw.yydb.mapper.AttendedMapper;
import com.fw.yydb.mapper.LotteryTimeMapper;
import com.fw.yydb.mapper.NumberAMapper;
import com.fw.yydb.mapper.OrderMapper;
import com.fw.yydb.mapper.ProductMapper;
import com.fw.yydb.utils.CacheUtil;
import com.fw.yydb.utils.CommonUtil;
import com.fw.yydb.utils.Config;
import com.fw.yydb.utils.DateUtils;

/**
 * 活动
 * @author wen
 *
 */
@Service
public class ActivityService {

	@Autowired
	private AttendedService attendedService;
	@Autowired
	private ActivityMapper activityMapper;
	@Autowired
	private AttendedMapper attendedMapper;
	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private LotteryTimeMapper lotteryTimeMapper;
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private NumberAMapper numberAMapper;
	@Autowired
	private Config config;

	private final String START_PERIODS = "001";

	/**
	 * 查询当前进行中的活动
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public List<ActivityDto> queryProgressList(int pageNum, int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		int startRows = (pageNum - 1) * pageSize;
		int endRows = pageNum * pageSize;
		map.put("status", 1);
		map.put("startRows", startRows);
		map.put("endRows", endRows);
		return activityMapper.queryList(map);
	}

	/**
	 * 查询活动详情
	 * @param userId
	 * @param productId
	 * @param periodsId
	 * @return
	 */
	public ActivityDto queryActivityDetail(String userId, Long activityId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("activityId", activityId);
		map.put("userId", userId);
		return activityMapper.queryActivity(map);
	}

	/**
	 * 获取A值
	 * 
	 * @param periodsID
	 * @return
	 */
	public long numberA(Long periodsID) {

		List<OrderDto> orderList = orderMapper.queryTopList();
		long sum = 0;
		if (orderList != null && orderList.size() > 0) {
			for (OrderDto orderDto : orderList) {
				sum += orderDto.getSubTime();
				orderDto.setActivityId(periodsID);
			}
			numberAMapper.addNumberATop(orderList);
		}
		return sum;
	}

	/**
	 * 获取下一期时时彩的期号,获取机器的当前时间;
	 * 
	 * @return
	 */
	public String nextActivity() {
		Date date = new Date();
		String nextDay = DateUtils.dayOf(DateUtils.beforeNaturalDay(date, 0));
		String day = DateUtils.dayOf(date);

		// 明天0点的毫秒数
		long tomorrow = DateUtils.nextDaysMillis(date, 1);

		// 当前时间加上开奖时间间隔获取下一期的期号
		String nextPeriods = START_PERIODS;
		long currentTimeMillis = System.currentTimeMillis();
		long millis = currentTimeMillis + config.getLotteryInterval();

		String hour = DateUtils.hourOf(DateUtils.getDay(millis));
		LotteryTimeDto dto = lotteryTimeMapper.findlotteryTime(hour);
		if (null != dto) {
			nextPeriods = dto.getPeriodsCode();
		}

		// 判断开奖日期是否跨天
		if (millis >= tomorrow) {
			return nextDay + nextPeriods;
		}

		return day + nextPeriods;
	}

	/**
	 * 当已购人数已满
	 * @param periodsId
	 * @param status
	 */
	@Transactional(value = "yydb")
	public ActivityDto fullActivity(Long productId, Long activityId) {
		//如果等于总人次，则将当前期数状态修改为待开奖
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("status", Constants.ACTIVITY_STATUS.TO_BE_LOTTERY);
		paramMap.put("activityId", activityId);
		paramMap.put("fullTime", DateUtils.print(new Date()));
		paramMap.put("numberAcount", numberA(activityId));
		paramMap.put("lotteryCode", nextActivity());
		activityMapper.updateActivity(paramMap);

		//查询未开始的活动
		Map<String, Object> paramMap1 = new HashMap<String, Object>();
		paramMap1.put("productId", productId);
		paramMap1.put("status", Constants.ACTIVITY_STATUS.NOT_START);
		//如果有下一期
		ActivityDto nextDto = activityMapper.queryActivity(paramMap1);
		if (nextDto != null) {
			//将下一期活动状态修改为进行中
			updateNextActivity(nextDto.getActivityId());

			//将新一期已参加人数置为0
			String nextKey = CacheUtil.getCurrentJoinNumKey(nextDto.getActivityId());
			CacheUtil.put(nextKey, 0);
			//新一期生成所有可用key
			attendedService.initAllNumber(nextDto.getActivityId(), nextDto.getPeopleCount());
		}

		ProductDto proDto = productMapper.queryProduct(paramMap1);
		//如果产品未下架，新增一个状态为未开始的活动
		if (proDto.getStatus() == 0L) {
			initActivity(productId, Long.valueOf(nextDto.getActivityCode()));
		}

		//清除已满额的key
		String currKey = CacheUtil.getCurrentJoinNumKey(activityId);
		CacheUtil.deleteKey(currKey);

		return nextDto;

	}

	/**
	 * 更新抽奖活动状态进行中
	 * @param periodsId
	 * @param status
	 */
	public void updateStatusToProcessing(String periodsId) {
		//如果等于总人次，则将当前期数状态修改为待开奖
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("status", Constants.ACTIVITY_STATUS.PROCESSING);
		paramMap.put("periodsId", periodsId);
		paramMap.put("fullTime", DateUtils.print(new Date()));
		activityMapper.updateActivity(paramMap);
	}

	/**
	 * 更新下一期信息
	 * @param productId
	 */
	public void updateNextActivity(Long activityId) {

		//获取下一期信息，并将下一期状态修改为进行中
		Map<String, Object> paramMap2 = new HashMap<String, Object>();
		paramMap2.put("status", Constants.ACTIVITY_STATUS.PROCESSING);
		paramMap2.put("activityId", activityId);
		paramMap2.put("startTime", DateUtils.print(new Date()));
		activityMapper.updateActivity(paramMap2);
	}

	/**
	 * 判断是否有下一期
	 * @return
	 */
	public boolean isNextActivity(Long productId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("productId", productId);
		map.put("status", Constants.ACTIVITY_STATUS.NOT_START);
		//获取下一期信息
		ActivityDto perDto = activityMapper.queryActivity(map);
		if (perDto != null) {
			return true;
		}
		return false;
	}

	/**
	 * 初始化商品抽奖活动
	 * @param productId
	 */
	public void initActivity(Long productId, long activityCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("productId", productId);
		map.put("activityCode", CommonUtil.getActivityNumber(activityCode + ""));
		activityMapper.addActivity(map);

	}

}
