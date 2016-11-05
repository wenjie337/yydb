package com.fw.yydb.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fw.yydb.entiy.JoinOrder;
import com.fw.yydb.mapper.JoinOrderMapper;


@Service("joinOrderSvc")
public class JoinOrderSvc {

    @Autowired
    private JoinOrderMapper joinOrderMapper;

    public List<JoinOrder> queryTopList(int rows, Long periodsId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rows", rows);
        map.put("periodsID", periodsId);
        return joinOrderMapper.queryTopList(map);
    }

    public List<JoinOrder> queryFlipPage(int min, int max, String queryTime, Long periodsId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("min", min);
        map.put("max", max);
        map.put("queryTime", queryTime);
        map.put("periodsID", periodsId);

        return joinOrderMapper.queryFlipPage(map);
    }

}
