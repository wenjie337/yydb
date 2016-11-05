package com.fw.yydb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fw.yydb.entiy.ComputeDetail;
import com.fw.yydb.mapper.ComputeDetailMapper;
import com.fw.yydb.mapper.NumberAMapper;


@Service
public class ComputeDetailSvc {

    @Autowired
    private ComputeDetailMapper computeDetailMapper;

    @Autowired
    private NumberAMapper numberAMapper;

    public ComputeDetail queryComputeDetail(Long periodsId) {
        ComputeDetail computeDetail = computeDetailMapper.queryComputeDetail(periodsId);
        if (computeDetail == null) {
            return null;
        }
        computeDetail.setList(numberAMapper.queryNumberA(periodsId));

        return computeDetail;
    }
}
