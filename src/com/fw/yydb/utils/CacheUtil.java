package com.fw.yydb.utils;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.mapdb.DBMaker;
import org.mapdb.HTreeMap;
/**
 * 缓存工具类
 * @author wen
 *
 */
public class CacheUtil {
	
	private static HTreeMap<String,String> ht = DBMaker
            .memoryDirectDB()    //use off-heap memory, on-heap is `.memoryDB()`
            .transactionDisable()   //better performance
            .make()
            .hashMapCreate("cache")
            .expireMaxSize(100)
            .expireAfterWrite(1, TimeUnit.HOURS)
            .make();
	/**
	 * 获取活动编码key
	 * @param activityId
	 * @param next
	 */
	public static String getInvolvementNumKey(Long activityId,Long next){
		StringBuilder sb = new StringBuilder();
		sb.append("invo_").append(activityId).append("_").append(next);
		return sb.toString();
	}
	/**
	 * 获取参加人数活动key
	 * @param activityId
	 * @return
	 */
	public static String getCurrentJoinNumKey(Long activityId){
		StringBuilder sb = new StringBuilder();
		sb.append("join_").append(activityId);
		return sb.toString();
	}
	/**
	 * 赋值
	 * @param key
	 * @param value
	 */
	public static void put(String key,Object value){
		ht.put(key, String.valueOf(value));
	}
	/**
	 * 取值
	 * @param key
	 * @return
	 */
	public static String get(String key){
		if(StringUtils.isBlank(key)){
			return "";
		}
		return ht.get(key);
	}
	/**
	 * 删除key
	 * @param key
	 */
	public static void deleteKey(String key){
		if(StringUtils.isBlank(key)){
			return;
		}
		ht.remove(key);
	}
}
