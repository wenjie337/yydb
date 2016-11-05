package com.fw.yydb.mapper;

import java.util.List;
import java.util.Map;

import com.fw.yydb.entiy.AttendedDto;
import com.fw.yydb.entiy.JoinRecord;


/**
 * 
 * The class AttendedMapper.
 *
 * Description:
 *
 * @author: yaojiewen
 * @since: 2016年9月16日
 * @version: $Revision$ $Date$ $LastChangedBy$
 *
 */
public interface AttendedMapper {

    /**
     * 插入数据
     */
    public void addAttended(Map<String, Object> map);

    public void updatePrize(AttendedDto dto);

    public List<AttendedDto> queryListByActivityId(Map<String, Object> map);

    List<Integer> queryCurrUserNums(Map<String, Object> map);
    
    /**
	 * 查询参与详情
	 * @param map
	 * @return
	 */
	public List<JoinRecord> queryJoinRecord(Map<String,Object> map);
	/**
	 * 我的参与记录
	 * @param map
	 * @return
	 */
	public List<JoinRecord> myJoinRecord(Map<String,Object> map);
}
