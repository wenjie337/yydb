package com.fw.yydb.lottery.ipml;

import java.util.regex.Pattern;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fw.yydb.lottery.ILottery;
import com.fw.yydb.utils.Config;
import com.fw.yydb.utils.DateUtils;

@Component
public class BaiduLottery implements ILottery {

	private static final Logger logger = LoggerFactory
			.getLogger(BaiduLottery.class);

	@Autowired
	private Config config;

	private OkHttpClient client = new OkHttpClient();

	public void take() {
		logger.info("BaiduLottery.take start...");
		String date = DateUtils.getNowTimeDdStr();
		Request request = new Request.Builder()
				.url(config.getValue("BaiduURL")).build();
		try (Response response = client.newCall(request).execute()) {
			if (null == response) {
				logger.info("BaiduLottery.take response is null.");
				logger.info("BaiduLottery.take end.");
				return;
			}
			String baiduBody = response.body().string();
			JSONObject object = JSON.parseObject(baiduBody);
			if (null == object) {
				logger.info("BaiduLottery.take response baiduBody is null.");
				logger.info("BaiduLottery.take end.");
				return;
			}
			JSONArray list = object.getJSONArray("data");
			for (int i = 0; i < list.size(); i++) {
				JSONObject entityObj = list.getJSONObject(i);
				String phase = entityObj.getString("phase");
				if ("20160915031".equals(phase)) {
					JSONObject obj = entityObj.getJSONObject("result");
					JSONArray ss = obj.getJSONArray("result");
					for (int j = 0; j < ss.size(); j++) {
						JSONObject oo = ss.getJSONObject(i);
						String regEx = "[^0-9]";
						Pattern p = Pattern.compile(regEx);
						java.util.regex.Matcher m = p.matcher(oo
								.getString("data"));
						logger.info("BaiduLottery.take " + phase + " is "
								+ m.replaceAll("").trim());
					}

				}

			}
		} catch (Exception e) {
			logger.error("BaiduLottery.take Error.", e);
		}

		logger.info("BaiduLottery.take end.");
	}

	public static void main(String[] args) {
		System.out.println("111a".matches("^\\d+$"));
	}
}
