package com.fw.yydb.mapper;

import java.util.List;

import com.fw.yydb.entiy.NumberA;
import com.fw.yydb.entiy.OrderDto;


public interface NumberAMapper {

    void addNumberATop(List<OrderDto> orderList);

    List<NumberA> queryNumberA(Long periodsId);
}
