package com.fw.yydb.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DateUtils {

	public static final String SLASH_DATE_FORMAT = "yyyy/MM/dd HH24:mm:ss";
	public static final String DASH_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String MILLISECOND_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
	public static final String TIME_DATE_FORMAT = "HHmmss";
	public static final String NON_DELIMITER_DATE_FORMAT = "yyyyMMddHHmmss";
	public static final String NOW_DATE_FORMAT = "yyyyMMdd";
	public static final String NOW_NON_DATE_FORMAT = "yyMMdd";

	public static Boolean in(Date checkedDate, Date beginDate, Date endDate) {
		return (beginDate == null || !checkedDate.before(beginDate))
				&& (endDate == null || !checkedDate.after(endDate));
	}

	public static String getTimeStr(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(TIME_DATE_FORMAT);
		return sdf.format(date);
	}

	public static String getNowTimeDdStr() {
		SimpleDateFormat sdf = new SimpleDateFormat(NOW_DATE_FORMAT);
		return sdf.format(new Date());
	}

	public static String getNowTimeStr() {
		SimpleDateFormat sdf = new SimpleDateFormat(NOW_NON_DATE_FORMAT);
		return sdf.format(new Date());
	}

	public static Date from(String dateString, String format) {
		DateFormat formatter = new SimpleDateFormat(format);
		Date date = null;
		try {
			date = formatter.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static Date from(String dateString) {
		DateFormat formatter = new SimpleDateFormat(SLASH_DATE_FORMAT);
		Date date = null;
		try {
			date = formatter.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static Date afterDays(Date date, int days) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, days);
		return c.getTime();
	}
	
	/**
	 * 明天0点的毫秒数
	 * @param date
	 * @param days
	 * @return
	 */
	public static long nextDaysMillis(Date date, int days) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, days);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.MILLISECOND, 0);
		c.set(Calendar.SECOND, 0);
		return c.getTimeInMillis();
	}
	
	public static Date getDay(long millis) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(millis);
		return c.getTime();
	}

	public static String print(Date date) {
		return print(date, DASH_DATE_FORMAT);
	}

	public static String print(Date date, String dateFormat) {
		SimpleDateFormat format = new SimpleDateFormat(dateFormat);
		return format.format(date);
	}

	public static String beautify(Date date) {
		return print(date, MILLISECOND_DATE_FORMAT);
	}

	public static String printBeginOfDay(Date date) {
		return print(date, "yyyy-MM-dd");
	}

	public static String monthOf(Date date) {
		return print(date, "yyyyMM");
	}

	public static String weekOf(Date date) {
		return print(date, "xxxxww");
	}

	public static String dayOf(Date date) {
		return print(date, "yyyyMMdd");
	}

	public static String hourOf(Date date) {
		return print(date, "HH:mm:ss");
	}

	public static void main(String[] args) {
		System.out.println(beforeNaturalDay(new Date(), 0));
	}

	public static Date beforeNaturalDay(Date now, Integer duration) {
		Calendar c = Calendar.getInstance();
		c.setTime(now);
		int beforeDate = -duration + 1;
		c.add(Calendar.DATE, beforeDate);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);

		return c.getTime();
	}

	public static long formatHour(String time) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, Integer.valueOf(time.substring(0, 2)));
		c.set(Calendar.MINUTE, Integer.valueOf(time.substring(2, 4)));
		return c.getTimeInMillis();
	}
	
	public static long nextDayZeroHour(Date now) {
		Calendar c = Calendar.getInstance();
		c.setTime(now);
		int beforeDate = -1;
		c.add(Calendar.DATE, beforeDate);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		return c.getTimeInMillis();
	}

	public static Date beforeNaturalMonths(Date now, Integer duration) {
		Calendar c = Calendar.getInstance();
		c.setTime(now);
		int beforeMonth = -duration + 1;
		c.add(Calendar.MONTH, beforeMonth);
		c.set(Calendar.DATE, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);

		return c.getTime();
	}


	public static int getTodaySecondsRemain() {
		Calendar calendar = Calendar.getInstance();
		Long currentSeconds = calendar.getTimeInMillis() / 1000;
		calendar.add(Calendar.DATE, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		long currentEndSeconds = calendar.getTimeInMillis() / 1000;

		Long secondsRemain = currentEndSeconds - currentSeconds;

		return secondsRemain.intValue();
	}
}
