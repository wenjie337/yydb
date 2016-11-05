package com.fw.yydb.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fw.yydb.entiy.JoinRecord;
import com.fw.yydb.mapper.JoinRecordMapper;


@Service("joinRecordSvc")
public class JoinRecordSvc {

    @Autowired
    private JoinRecordMapper joinRecordMapper;

    public List<JoinRecord> queryTopList(int rows, String userID) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rows", rows);
        map.put("userID", userID);
        return joinRecordMapper.queryTopList(map);
    }

    public List<JoinRecord> queryFlipPage(int min, int max, String queryTime, String userID) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("min", min);
        map.put("max", max);
        map.put("queryTime", queryTime);
        map.put("userID", userID);

        return joinRecordMapper.queryFlipPage(map);
    }
}
