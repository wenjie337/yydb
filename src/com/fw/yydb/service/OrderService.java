package com.fw.yydb.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fw.yydb.constants.Constants;
import com.fw.yydb.constants.ParamConstant;
import com.fw.yydb.constants.ResultCodeConstant;
import com.fw.yydb.entiy.ActivityDto;
import com.fw.yydb.entiy.OrderDto;
import com.fw.yydb.entiy.UserDto;
import com.fw.yydb.mapper.ActivityMapper;
import com.fw.yydb.mapper.AttendedMapper;
import com.fw.yydb.mapper.OrderMapper;
import com.fw.yydb.mapper.ProductMapper;
import com.fw.yydb.utils.CacheUtil;
import com.fw.yydb.utils.CommonUtil;
import com.fw.yydb.utils.Config;
import com.fw.yydb.utils.Errorcode;

@Service("orderService")
public class OrderService {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);
	@Autowired
	private Errorcode errorcode;
	@Autowired
	private Config config;
	@Autowired
	private UserService userService;
	@Autowired
	private ActivityService activityService;
	@Autowired
	private AttendedService attendedService;
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private AttendedMapper attendedMapper;
	@Autowired
	private ActivityMapper activityMapper;

	/**
	 * 生成订单
	 * @param reqMap
	 */
	//	@Transactional
	//	@SuppressWarnings("unchecked")
	//	public void createOrder(Map<String, Object> reqMap, Map<String, Object> respMap,
	//			HttpServletRequest request) {
	//		List<Map<String, Object>> productList = (List<Map<String, Object>>) reqMap.get("proList");
	//		if (productList != null && productList.size() > 0) {
	//			StringBuilder sb = new StringBuilder();
	//			int len = productList.size();
	//			for (int i = 0; i < len; i++) {
	//				sb.append(productList.get(i).get("productId"));
	//				if (i < len - 1) {
	//					sb.append(",");
	//				}
	//			}
	//			Map<String, Object> map = new HashMap<String, Object>();
	//			map.put("productId", sb.toString());
	//			map.put("status", Constants.ACTIVITY_STATUS.PROCESSING);
	//			map.put("startRows", 1);
	//			map.put("endRows", 100);
	//
	//			int partCount = (Integer) reqMap.get("num");
	//			//查询当前商品正在进行中的活动
	//			List<ActivityDto> actList = activityMapper.queryList(map);
	//			if (actList != null && actList.size() > 0) {
	//				actList.forEach(dto -> {
	//					Map<String, Object> dtoMap = new HashMap<String, Object>();
	//					//判断当前商品是否可以生成订单
	//					if (validateOrder(partCount, dtoMap, dto)) {
	//						UserDto userDto = userService.queryUser(reqMap);
	//						if (userDto == null) {
	//							reqMap.put("nickName", userService.getUserName(request));
	//						}
	//
	//						//生成唯一订单号
	//						//暂时不支持购物车功能，只生成一个订单，数据结构支持购物车，但下订单时，暂时只能下一个商品
	//						String orderSerial = CommonUtil.generateOrderSerial();
	//						reqMap.put("orderSerial", orderSerial);
	//						reqMap.put("orderTitle", getOrderTitle(dto.getProductName(), partCount));
	//						reqMap.put("orderMoney", partCount * dto.getPrice());
	//						reqMap.put("payMoney", partCount * dto.getPrice());
	//						reqMap.put("productId", dto.getProductId());
	//						reqMap.put("orderStatus", Constants.ORDER_STATUS.TO_BE_PAY);
	//						reqMap.put("amount", partCount);
	//						//生成订单
	//						orderMapper.addOrder(reqMap);
	//					}
	//
	//					List<Map<String, Object>> tempList =
	//							(List<Map<String, Object>>) respMap.get("proList");
	//					if (tempList == null) {
	//						tempList = new ArrayList<Map<String, Object>>();
	//					}
	//					tempList.add(dtoMap);
	//				});
	//			}
	//		}
	//	}

	/**
	 * 生成订单
	 * @param reqMap
	 * @param respMap
	 * @param request
	 */
	public void createOrder(Map<String, Object> reqMap, Map<String, Object> respMap,
			HttpServletRequest request) {
		ActivityDto dto = activityMapper.queryActivity(reqMap);
		int partCount = (Integer) reqMap.get("num");
		if (validateOrder(partCount, respMap, dto)) {
			UserDto userDto = userService.queryUser(reqMap);
			if (userDto == null) {
				reqMap.put("nickName", userService.getUserName(request));
			}
			//生成唯一订单号
			String orderSerial = CommonUtil.generateOrderSerial();
			reqMap.put("orderSerial", orderSerial);
			reqMap.put("orderTitle", getOrderTitle(dto.getProductName(), partCount));
			reqMap.put("orderMoney", partCount * dto.getPrice());
			reqMap.put("payMoney", partCount * dto.getPrice());
			reqMap.put("productId", dto.getProductId());
			reqMap.put("orderStatus", Constants.ORDER_STATUS.TO_BE_PAY);
			reqMap.put("amount", partCount);
			//生成订单
			orderMapper.addOrder(reqMap);
		}
	}

	/**
	 * 判断是否可以生成订单
	 * @param reqMap
	 * @param respMap
	 * @return
	 */
	private boolean validateOrder(int partCount, Map<String, Object> respMap, ActivityDto dto) {

		if (dto == null) {
			return false;
		}
		if (respMap == null) {
			respMap = new HashMap<String, Object>();
		}
		respMap.put("productId", dto.getProductId());
		respMap.put(ParamConstant.RESULT_CODE, ResultCodeConstant.SUCCESS);
		//获取当前活动已参与人次
		long overCount = getOverCount(dto.getActivityId());
		if (overCount == -1) {
			//如果当前活动已结束，并且商品没有下一期
			if (!activityService.isNextActivity(dto.getProductId())) {
				LOGGER.info("产品ID=" + dto.getProductId() + "，  的活动未开始 或已结束 ");
				respMap.put(ParamConstant.RESULT_CODE, ResultCodeConstant.PERIOD_IS_NOT_START);
				respMap.put(ParamConstant.RESULT_DESC,
						errorcode.getValue(ResultCodeConstant.PERIOD_IS_NOT_START));
				return false;
			}
		}
		//待参加人次
		if (partCount > dto.getMaxCount()) {
			LOGGER.info("产品ID=" + dto.getProductId() + "，  超过单次最大购买限制  ");
			respMap.put(ParamConstant.RESULT_CODE, ResultCodeConstant.PRODUCT_OVER_FLOW);
			respMap.put(ParamConstant.RESULT_DESC,
					errorcode.getValue(ResultCodeConstant.PRODUCT_OVER_FLOW));
			return false;
		}
		//如果已参与人次+待参加人次>总人次，则判断是否有下期，如果有，拆分订单，如果没有，返回错误码
		if (overCount + partCount > dto.getPeopleCount()) {
			//是否下一期信息
			if (!activityService.isNextActivity(dto.getProductId())) {
				LOGGER.info("产品ID=" + dto.getProductId() + "，  超过剩余可购买数量 ");
				//如果没有下一期，则返回
				respMap.put(ParamConstant.RESULT_CODE, ResultCodeConstant.PRODUCT_DONE);
				respMap.put(ParamConstant.RESULT_DESC,
						errorcode.getValue(ResultCodeConstant.PRODUCT_DONE));
				return false;
			}
		}

		return true;
	}

	/**
	 * 获取订单标题
	 * @param productName
	 * @param partCount
	 * @return
	 */
	private String getOrderTitle(String productName, int partCount) {
		StringBuilder sb = new StringBuilder();
		sb.append("").append(" ").append(productName).append(partCount).append("人次");
		return sb.toString();
	}

	/**
	 * 获取当前参与人次
	 * @return
	 */
	private int getOverCount(Long activityId) {
		String key = CacheUtil.getCurrentJoinNumKey(activityId);
		String value = CacheUtil.get(key);
		if (StringUtils.isNotBlank(value)) {
			return Integer.parseInt(value);
		}
		return -1;
	}

	/**
	 * 查询订单
	 * @param reqMap
	 */
	@Transactional
	public OrderDto queryOrder(Map<String, Object> reqMap) {
		return orderMapper.queryOrder(reqMap);
	}

	/**
	 * 处理订单
	 * @param orderDto
	 */
	public void executeOrder(Map<String, Object> reqMap, Map<String, Object> respMap) {
		String status = (String) reqMap.get("status");
		if ("1".equals(status)) {
			reqMap.put("status", Constants.ORDER_STATUS.PAY_SUCCESS);
		} else {
			reqMap.put("status", Constants.ORDER_STATUS.PAY_FAIL);
		}
		//更新订单状态
		orderMapper.updateOrder(reqMap);
		if ("1".equals(status)) {
			addAttended(reqMap.get("orderSerial").toString(), respMap);
		}
	}

	/**
	 * 插入参与记录
	 * @param orderDto
	 */
	@Transactional(value = "yydb")
	public void addAttended(String orderSerial, Map<String, Object> respMap) {
		Map<String, Object> reqMap = new HashMap<String, Object>();
		//查询订单信息
		reqMap.put("orderSerial", orderSerial);
		OrderDto orderDto = orderMapper.queryOrder(reqMap);
		//查询当前商品正在进行的期数信息
		reqMap.put("productId", orderDto.getProductId());
		reqMap.put("status", Constants.ACTIVITY_STATUS.PROCESSING);
		ActivityDto actDto = activityMapper.queryActivity(reqMap);
		//总所需人次
		int range = actDto.getPeopleCount().intValue();
		//获取当前已参与人次
		String key = CacheUtil.getCurrentJoinNumKey(actDto.getActivityId());
		String value = CacheUtil.get(key);

		if (StringUtils.isNotBlank(value)) {
			reqMap.put("activityId", actDto.getActivityId());
			reqMap.put("userId", orderDto.getUserId());
			reqMap.put("nickName", orderDto.getNickName());
			reqMap.put("range", range);
			//当前已参与人次
			int currCount = Integer.parseInt(value);
			//当期活动剩余人次
			int overCount = range - currCount;
			try {
				//如果已参与人次+当前订单参与人次<总人次，直接插入参与记录表
				if (orderDto.getAmount() < overCount) {
					//生成插入抽奖号码
					reqMap.put("num", orderDto.getAmount().intValue());
					attendedService.addAttended(reqMap);

				} else if (orderDto.getAmount() == overCount) {
					//生成插入抽奖号码
					reqMap.put("num", orderDto.getAmount().intValue());
					attendedService.addAttended(reqMap);
					//当前期数人次已满，更新状态
					activityService.fullActivity(actDto.getProductId(), actDto.getActivityId());
				} else {
					//如果超出总人次
					//没有下一期，发起退款流程
					if (!activityService.isNextActivity(orderDto.getProductId())) {
						Map<String, Object> resverMap = new HashMap<String, Object>();
						resverMap.put("orderSerial", orderDto.getOrderSerial());
						return;
					}

					//生成插入抽奖号码
					reqMap.put("num", overCount);
					attendedService.addAttended(reqMap);
					//当前期数人次已满，更新状态
					ActivityDto nextPerDto =
							activityService.fullActivity(actDto.getProductId(),
									actDto.getActivityId());

					reqMap.put("num", orderDto.getAmount().intValue() - overCount);
					reqMap.put("activityId", nextPerDto.getActivityId());
					//将本订单剩余人次加入下一期
					attendedService.addAttended(reqMap);

				}
			} catch (Exception e) {
				LOGGER.error("生成参与记录异常", e);
				//回滚缓存中数量
				attendedService.updateCache(actDto.getActivityId(), currCount);
				e.printStackTrace();
			}
		}

	}

}
