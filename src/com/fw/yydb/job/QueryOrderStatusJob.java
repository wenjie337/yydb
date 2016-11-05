package com.fw.yydb.job;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fw.yydb.entiy.OrderDto;
import com.fw.yydb.mapper.OrderMapper;
import com.fw.yydb.utils.Config;


/**
 * 
 * The class QueryMobileOrderStatusJob.
 *
 * Description:查询订单状态job
 *
 * @author: yaojiewen
 * @since: 2016年9月7日
 * @version: $Revision$ $Date$ $LastChangedBy$
 *
 */
@Service
public class QueryOrderStatusJob {

//	private static final Logger LOGGER = LoggerFactory.getLogger(QueryOrderStatusJob.class);
//	@Autowired
//	private Config config;
//
//	private static ExecutorService fixedThreadPool;
//	@Autowired
//	private OrderMapper orderMapper;
//
//	//50秒执行一次
//	//	@Scheduled(cron = "0/50 * * * * ?")
//	public void start() {
//		LOGGER.info("QueryMobileOrderStatusJob 启动");
//		fixedThreadPool = getExecutorService();
//
//		List<OrderDto> orderList = orderMapper.queryOrderJob();
//		if (orderList != null && orderList.size() > 0) {
//			for (OrderDto order : orderList) {
//				fixedThreadPool.execute(new Runnable() {
//
//					public void run() {
//						try {
////							Map<String, Object> reqMap = new HashMap<String, Object>();
////							reqMap.put("orderNo", order.getNocardOrderSerial());
////							Map<String, Object> resultMap = noCardService.queryOrder(reqMap);
////							//如果状态有变化，才更新
////							if (!order.getOrderStatus().equals(resultMap.get("orderStatus"))) {
////								resultMap.put("orderSerial", order.getOrderSerial());
////								orderMapper.updateOrder(resultMap);
////							}
//						} catch (Exception e) {
//							LOGGER.error("query nocard order error!", e);
//						}
//					}
//				});
//			}
//		}
//	}
//
//	private ExecutorService getExecutorService() {
//		if (fixedThreadPool == null) {
//			return Executors.newFixedThreadPool(20);
//		}
//		return fixedThreadPool;
//	}

}
