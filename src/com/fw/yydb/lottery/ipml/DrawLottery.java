package com.fw.yydb.lottery.ipml;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fw.yydb.constants.Constants;
import com.fw.yydb.entiy.ActivityDto;
import com.fw.yydb.entiy.AlwaysMapperDto;
import com.fw.yydb.entiy.AttendedDto;
import com.fw.yydb.entiy.LotteryDto;
import com.fw.yydb.mapper.ActivityMapper;
import com.fw.yydb.mapper.AlwaysMapper;
import com.fw.yydb.mapper.AttendedMapper;
import com.fw.yydb.mapper.LotteryMapper;


/**
 * 抽奖
 * 
 * @author 钟煜
 *
 */
@Service
@Transactional
public class DrawLottery {

	private static final Logger logger = LoggerFactory.getLogger(DrawLottery.class);

	@Autowired
	private ActivityMapper activityMapper;

	@Autowired
	private AlwaysMapper alwaysMapper;

	@Autowired
	private LotteryMapper lotteryMapper;

	@Autowired
	private AttendedMapper attendedMapper;

	//	@Scheduled(cron = "0/5 * 0-2,10-23  * * ? ")
	public void draw() {
		logger.info("DrawLottery.draw start...");
		Long exceptionPeriodsID = null;
		try {
			List<ActivityDto> list = activityMapper.findLottery();
			if (null == list || list.isEmpty()) {
				return;
			}
			for (ActivityDto actDto : list) {
				AlwaysMapperDto alwaysMapperDto = alwaysMapper.getActivity(actDto.getLotteryCode());
				exceptionPeriodsID = actDto.getActivityId();

				if (null == alwaysMapperDto) {
					return;
				}
				long numberA = actDto.getNumberAcount();
				Long prizeCode = Long.valueOf(alwaysMapperDto.getPrizeCode());
				Long prizeNumber = ((numberA + prizeCode) % actDto.getPeopleCount()) + 10000001;

				Map<String, Object> map = new HashMap<String, Object>();
				map.put("productId", actDto.getProductId());
				map.put("activityId", actDto.getActivityId());
				map.put("involvementCode", prizeNumber);

				List<AttendedDto> attList = attendedMapper.queryListByActivityId(map);
				if (null == attList || attList.isEmpty()) {
					return;
				}

				// 插入中奖记录
				AttendedDto attendedDto = attList.get(0);
				LotteryDto lotteryDto = new LotteryDto();
				lotteryDto.setUserID(attendedDto.getUserID());
				lotteryDto.setActivityCode(actDto.getActivityCode());
				lotteryDto.setActivityID(actDto.getActivityId());
				lotteryDto.setProductID(actDto.getProductId());
				lotteryDto.setProductName(actDto.getProductName());
				lotteryDto.setPrizeNumber(String.valueOf(prizeNumber));
				lotteryMapper.addLottery(lotteryDto);

				// 标识开奖已经结束
				Map<String, Object> map1 = new HashMap<String, Object>();
				map1.put("status", Constants.ACTIVITY_STATUS.END);
				map1.put("activityId", actDto.getActivityId());
				map1.put("lotteryTime", new Date());
				activityMapper.updateActivity(map1);
			}

		} catch (Exception e) {
			if (null != exceptionPeriodsID) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("status", Constants.ACTIVITY_STATUS.EXCEPTION);
				map.put("activityId", exceptionPeriodsID);
				activityMapper.updateActivity(map);
			}

			logger.error("DrawLottery.draw is error.", e);
		}
		logger.info("DrawLottery.draw end.");

	}

}
