package com.fw.yydb.utils;

import java.util.Calendar;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang.StringUtils;

/**
 * 常用工具类
 * The class CommonUtil.
 *
 * Description:
 *
 * @author: yaojiewen
 * @since: 2016年9月15日
 * @version: $Revision$ $Date$ $LastChangedBy$
 *
 */
public class CommonUtil {

	public static final int ORDER_SERIAL_RANDOM_LEN = 3;

	/**
	 * 校验请求参数
	 * @param reqMap
	 * @return
	 */
	public static boolean validateParam(Map<String, Object> reqMap, String[] params) {
		if (params == null || params.length == 0) {
			return false;
		}
		for (String param : params) {
			if (reqMap.get(param) == null || "".equals(param.trim())) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 获取订单号
	 * @return
	 */
	public static String generateOrderSerial() {
		String suffix = generateNumber(ORDER_SERIAL_RANDOM_LEN);
		StringBuffer sb = new StringBuffer();
		long naTime = System.nanoTime();
		if (naTime < 0) {
			sb.append(String.valueOf(naTime).substring(1)).append(suffix);
		} else {
			sb.append(System.nanoTime() + StringUtils.EMPTY).append(suffix);
		}
		String result = sb.toString();
		if (result.length() < 18) {
			StringBuilder fix = new StringBuilder();
			for (int i = 0; i < 18 - result.length(); i++) {
				fix.append("0");
			}
			result = result + fix.toString();
		} else if (result.length() > 18) {
			result = result.substring(0, 18);
		}
		return result;
	}

	/**
	 * 生成随机数字
	 * @param codeLenth
	 * @return
	 */
	public static String generateNumber(int codeLength) {
		final int maxNum = 10;
		int i;
		int count = 0;
		char[] str = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		StringBuffer sb = new StringBuffer();
		Random r = new Random();
		while (count < codeLength) {
			i = Math.abs(r.nextInt(maxNum));
			if (i >= 0 && i < str.length) {
				sb.append(str[i]);
				count++;
			}
		}
		return sb.toString();
	}

	/**
	 * 获取下期编码
	 * @param currPeriodCode
	 * @return
	 */
	public static String getActivityNumber(String currActivityCode) {
		if (StringUtils.isBlank(currActivityCode)) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		int year = Integer.parseInt(currActivityCode.substring(0, 4));
		int month = Integer.parseInt(currActivityCode.substring(4, 6));

		Calendar calendar = Calendar.getInstance();
		int currYear = calendar.get(Calendar.YEAR);
		int currMonth = calendar.get(Calendar.MONTH) + 1;
		if (currYear == year && currMonth == month) {
			sb.append(Long.parseLong(currActivityCode) + 1);
		} else if (currYear == year && currMonth > month) {
			sb.append(currYear);
			if (currMonth < 10) {
				sb.append("0");
			}
			sb.append(currMonth).append(currActivityCode.substring(6)).toString();
		} else if (currYear > year) {
			sb.append(currYear).append("010001");
		}
		return sb.toString();
	}
}
