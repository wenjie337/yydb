package com.fw.yydb.lottery.ipml;

import java.net.URL;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fw.yydb.entiy.AlwaysMapperDto;
import com.fw.yydb.lottery.ILottery;
import com.fw.yydb.mapper.AlwaysMapper;
import com.fw.yydb.utils.Config;
import com.fw.yydb.utils.StringHelper;


@Service
@Transactional
public class WangYiLottery implements ILottery {

	private static final Logger logger = LoggerFactory.getLogger(WangYiLottery.class);

	@Autowired
	private Config config;

	@Autowired
	private AlwaysMapper alwaysMapper;

	//	@Scheduled(cron = "0 0/1 0-2,10-23  * * ? ")
	public void take() {
		logger.info("WangYiLottery.take start...");
		try {
			// 判断当天第一期是否存在
			String periodsCode = null;
			long count = alwaysMapper.countByPeriods(StringHelper.getMinPeriods());
			if (count != 0) {
				AlwaysMapperDto alwaysMapperDto =
						alwaysMapper.getMaxByPeriods(StringHelper.getMinPeriods());
				periodsCode = String.valueOf(alwaysMapperDto.getLotteryCode() + 1);
			} else {
				periodsCode = String.valueOf(StringHelper.getMinPeriods() + 1);
			}

			String dataPeriod =
					String.valueOf(periodsCode).substring(periodsCode.length() - 9,
							periodsCode.length());
			prizeCodeList(dataPeriod);
		} catch (Exception e) {
			logger.error("WangYiLottery.take Error.", e);
		}
		logger.info("WangYiLottery.take end.");
	}

	private void prizeCodeList(String dataPeriod) throws Exception {
		URL url = new URL(config.getValue("WangYiURL"));
		Document doc = Jsoup.parse(url, config.getWyTimeOut());
		Element element = doc.select("td[data-period=" + dataPeriod + "]").first();
		String prizeCode = StringUtils.deleteWhitespace(element.attr("data-win-number"));
		AlwaysMapperDto dto = null;
		while (!StringUtils.isEmpty(prizeCode) && prizeCode.matches("^\\d+$")) {
			dto = new AlwaysMapperDto();
			dto.setLotteryCode(Long.valueOf("20" + dataPeriod));
			dto.setPrizeCode(prizeCode);
			dto.setSource(2L);
			alwaysMapper.addAlways(dto);
			dataPeriod = String.valueOf(Long.valueOf(dataPeriod) + 1);
			element = doc.select("td[data-period=" + dataPeriod + "]").first();
			prizeCode = StringUtils.deleteWhitespace(element.attr("data-win-number"));
		}
	}
}
