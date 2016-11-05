package com.fw.yydb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fw.yydb.mapper.CommonMapper;


@Service("commonDBSvc")
public class CommonDBSvc {

    @Autowired
    private CommonMapper commonMapper;

    public String queryCurrentDBTime() {
        return commonMapper.getQueryTime();
    }
}
