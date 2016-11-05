package com.fw.yydb.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fw.yydb.entiy.ActivityProductDetail;
import com.fw.yydb.mapper.AttendedMapper;

@Service("periodProductSvc")
public class PeriodProductSvc {

//	@Autowired
//	private PeriodProductMapper periodProductMapper;
//	@Autowired
//	private AttendedMapper attendedMapper;
//
//	public List<ActivityProduct> queryTopList(int rows) {
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("rows", rows);
//		return periodProductMapper.queryTopList(map);
//	}
//
//	public List<ActivityProduct> queryFlipPage(int min, int max, String queryTime) {
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("min", min);
//		map.put("max", max);
//		map.put("queryTime", queryTime);
//
//		return periodProductMapper.queryFlipPage(map);
//	}
//
//	public ActivityProductDetail queryPeriodProductDetail(String userId, Long productId,
//			Long periodsId) {
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("periodsID", periodsId);
//		map.put("productID", productId);
//
//		ActivityProductDetail ppd = periodProductMapper.queryPeriodProductDetail(map);
//		if (ppd != null) {
//			map.put("userId", userId);
//			List<Integer> attendList = attendedMapper.queryCurrUserNums(map);
//			ppd.setCurrUserNumbers(attendList);
//		}
//		return ppd;
//	}
}
