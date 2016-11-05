package com.fw.yydb.listener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import com.fw.yydb.entiy.ActivityDto;
import com.fw.yydb.entiy.AttendedDto;
import com.fw.yydb.mapper.AttendedMapper;
import com.fw.yydb.service.ActivityService;
import com.fw.yydb.service.AttendedService;
import com.fw.yydb.utils.CacheUtil;


/**
 * 
 * The class PeriodConfig.
 *
 * Description:服务启动时判断redis中是否有进行中的活动key
 *
 * @author: yaojiewen
 * @since: 2016年9月19日
 * @version: $Revision$ $Date$ $LastChangedBy$
 *
 */
@Configuration
public class PeriodListener implements ApplicationListener<ContextRefreshedEvent> {

	private static final Logger LOGGER = LoggerFactory.getLogger(PeriodListener.class);
	@Autowired
	private ActivityService activityService;
	@Autowired
	private AttendedService attendedService;
	@Autowired
	private AttendedMapper attendedMapper;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// TODO
		if (event.getApplicationContext().getParent() == null
				&& "Root WebApplicationContext".equals(event.getApplicationContext()
						.getDisplayName())) {
			LOGGER.info("init PeriodListener...");

			//查询所有进行中的活动
			List<ActivityDto> activityList = activityService.queryProgressList(1,5000);
			if (activityList != null && activityList.size() > 0) {
				for (ActivityDto dto : activityList) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("productId", dto.getProductId());
					map.put("activityId", dto.getActivityId());
					//生成所有的key
					attendedService.initAllNumber(dto.getActivityId(),dto.getPeopleCount());
					//查询当前活动已参与记录
					List<AttendedDto> attList = attendedMapper.queryListByActivityId(map);
					if (attList != null && attList.size() > 0) {
						//从缓存中移除已存在的key
						for (AttendedDto attDto : attList) {
							String key = CacheUtil.getInvolvementNumKey(attDto.getActivityId(), attDto.getInvolvementCode());
							CacheUtil.deleteKey(key);
						}
					}
					//存储当前数量
					String key = CacheUtil.getCurrentJoinNumKey(dto.getActivityId());
					CacheUtil.put(key, attList.size());
				}

			}

		}

	}
}
