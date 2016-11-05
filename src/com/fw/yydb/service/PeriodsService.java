package com.fw.yydb.service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fw.yydb.constants.Constants;
import com.fw.yydb.entiy.LotteryTimeDto;
import com.fw.yydb.entiy.OrderDto;
import com.fw.yydb.entiy.PeriodsDto;
import com.fw.yydb.entiy.ProductDto;
import com.fw.yydb.mapper.LotteryTimeMapper;
import com.fw.yydb.mapper.NumberAMapper;
import com.fw.yydb.mapper.OrderMapper;
import com.fw.yydb.mapper.PeriodsMapper;
import com.fw.yydb.mapper.ProductMapper;
import com.fw.yydb.utils.DateUtils;


@Transactional
@Service
public class PeriodsService {

//	@Autowired
//	private AttendedService attendedService;
//	@Autowired
//	private PeriodsMapper periodsMapper;
//	@Autowired
//	private ProductMapper productMapper;
//	@Autowired
//	private LotteryTimeMapper lotteryTimeMapper;
//	@Autowired
//	private OrderMapper orderMapper;
//
//	@Autowired
//	private NumberAMapper numberAMapper;
//
//	private final String START_PERIODS = "001";
//
//	/**
//	 * 获取A值
//	 * 
//	 * @param periodsID
//	 * @return
//	 */
//	public long numberA(Long periodsID) {
//
//		List<OrderDto> orderList = orderMapper.queryTopList();
//		long sum = 0;
//		if (orderList != null && orderList.size() > 0) {
//			for (OrderDto orderDto : orderList) {
//				sum += orderDto.getSubTime();
//				orderDto.setActivityId(periodsID);
//			}
//			numberAMapper.addNumberATop(orderList);
//		}
//		return sum;
//	}
//
//	/**
//	 * 获取下一期时时彩的期号,获取机器的当前时间;
//	 * 
//	 * @return
//	 */
//	public String nextActivity() {
//		Date date = new Date();
//		String nextDay = DateUtils.dayOf(DateUtils.beforeNaturalDay(date, 0));
//		String day = DateUtils.dayOf(date);
//
//		// 明天0点的毫秒数
//		long tomorrow = DateUtils.nextDaysMillis(date, 1);
//
//		// 当前时间加上开奖时间间隔获取下一期的期号
//		String nextPeriods = START_PERIODS;
//		long currentTimeMillis = System.currentTimeMillis();
//		long millis = currentTimeMillis; //+ yydbDisConfig.getLotteryInterval();
//
//		String hour = DateUtils.hourOf(DateUtils.getDay(millis));
//		LotteryTimeDto dto = lotteryTimeMapper.findlotteryTime(hour);
//		if (null != dto) {
//			nextPeriods = dto.getPeriodsCode();
//		}
//
//		// 判断开奖日期是否跨天
//		if (millis >= tomorrow) {
//			return nextDay + nextPeriods;
//		}
//
//		return day + nextPeriods;
//	}
//
//	/**
//	 * 当已购人数已满
//	 * @param periodsId
//	 * @param status
//	 */
//	@Transactional(value = "yydb")
//	public PeriodsDto fullActivity(Long productId, Long activityId) {
//		//如果等于总人次，则将当前期数状态修改为待开奖
//		Map<String, Object> paramMap = new HashMap<String, Object>();
//		paramMap.put("status", Constants.ACTIVITY_STATUS.TO_BE_LOTTERY);
//		paramMap.put("activityId", activityId);
//		paramMap.put("fullTime", DateUtils.print(new Date()));
//		paramMap.put("numberAcount", numberA(activityId));
//		paramMap.put("lotteryCode", nextPeriods());
//		periodsMapper.updatePeriod(paramMap);
//
//		//查询未开始的活动
//		Map<String, Object> paramMap1 = new HashMap<String, Object>();
//		paramMap1.put("productId", productId);
//		paramMap1.put("status", Constants.ACTIVITY_STATUS.NOT_START);
//		//如果有下一期
//		PeriodsDto nextPerDto = periodsMapper.queryPeriods(paramMap1);
//		if (nextPerDto != null) {
//			//将下一期活动状态修改为进行中
//			updateNextPeriod(nextPerDto.getPeriodsID());
//
//			//将新一期已参加人数置为0
//			String nextKey = RedisHelper.getCurrentJoinNumKey(productId, nextPerDto.getPeriodsID());
//			RedisUtility.store(nextKey, 0);
//			//新一期生成所有可用key
//			attendedService.initAllNumber(productId, nextPerDto.getPeriodsID(),
//					nextPerDto.getPeopleCount());
//		}
//
//		ProductDto proDto = productMapper.queryProduct(paramMap1);
//		//如果产品未下架，新增一个状态为未开始的活动
//		if (proDto.getStatus() == 0L) {
//			initPeriod(productId, Long.valueOf(nextPerDto.getPeriodsCode()));
//		}
//
//		//清除已满额的key
//		String currKey = RedisHelper.getCurrentJoinNumKey(productId, periodsId);
//		RedisUtility.deleteObject(currKey);
//
//		return nextPerDto;
//
//	}
//
//	/**
//	 * 更新抽奖活动状态进行中
//	 * @param periodsId
//	 * @param status
//	 */
//	public void updateStatusToProcessing(String periodsId) {
//		//如果等于总人次，则将当前期数状态修改为待开奖
//		Map<String, Object> paramMap = new HashMap<String, Object>();
//		paramMap.put("status", Constants.PERIODS_STATUS.PROCESSING);
//		paramMap.put("periodsId", periodsId);
//		paramMap.put("fullTime", DateUtils.print(new Date()));
//		periodsMapper.updatePeriod(paramMap);
//	}
//
//	/**
//	 * 更新下一期信息
//	 * @param productId
//	 */
//	public void updateNextPeriod(Long periodsId) {
//
//		//获取下一期信息，并将下一期状态修改为进行中
//		Map<String, Object> paramMap2 = new HashMap<String, Object>();
//		paramMap2.put("status", Constants.PERIODS_STATUS.PROCESSING);
//		paramMap2.put("periodsId", periodsId);
//		paramMap2.put("startTime", DateUtils.print(new Date()));
//		periodsMapper.updatePeriod(paramMap2);
//	}
//
//	/**
//	 * 判断是否有下一期
//	 * @return
//	 */
//	public boolean isNextPeriod(Long productId) {
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("productId", productId);
//		map.put("status", Constants.PERIODS_STATUS.NOT_START);
//		//获取下一期信息
//		PeriodsDto perDto = periodsMapper.queryPeriods(map);
//		if (perDto != null) {
//			return true;
//		}
//		return false;
//	}
//
//	/**
//	 * 初始化商品抽奖活动
//	 * @param productId
//	 */
//	public void initPeriod(Long productId, long periodsCode) {
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("productId", productId);
//		map.put("periodsCode", getPeriodNumber(periodsCode + ""));
//		periodsMapper.addPeriod(map);
//
//	}
//
//	/**
//	 * 获取下期编码
//	 * @param currPeriodCode
//	 * @return
//	 */
//	private String getPeriodNumber(String currPeriodCode) {
//		if (StringUtils.isBlank(currPeriodCode)) {
//			return "";
//		}
//		StringBuilder sb = new StringBuilder();
//		int year = Integer.parseInt(currPeriodCode.substring(0, 4));
//		int month = Integer.parseInt(currPeriodCode.substring(4, 6));
//
//		Calendar calendar = Calendar.getInstance();
//		int currYear = calendar.get(Calendar.YEAR);
//		int currMonth = calendar.get(Calendar.MONTH) + 1;
//		if (currYear == year && currMonth == month) {
//			sb.append(Long.parseLong(currPeriodCode) + 1);
//		} else if (currYear == year && currMonth > month) {
//			sb.append(currYear);
//			if (currMonth < 10) {
//				sb.append("0");
//			}
//			sb.append(currMonth).append(currPeriodCode.substring(6)).toString();
//		} else if (currYear > year) {
//			sb.append(currYear).append("010001");
//		}
//		return sb.toString();
//	}
}
