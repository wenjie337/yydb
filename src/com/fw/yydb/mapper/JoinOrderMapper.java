package com.fw.yydb.mapper;

import java.util.List;
import java.util.Map;

import com.fw.yydb.entiy.JoinOrder;


public interface JoinOrderMapper {

    List<JoinOrder> queryTopList(Map<String, Object> map);

    List<JoinOrder> queryFlipPage(Map<String, Object> map);

}
