package com.fw.yydb.mapper;

import java.util.List;
import java.util.Map;

import com.fw.yydb.entiy.JoinRecord;


public interface JoinRecordMapper {

    List<JoinRecord> queryTopList(Map<String, Object> map);

    List<JoinRecord> queryFlipPage(Map<String, Object> map);
}
