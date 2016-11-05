package com.fw.yydb.mapper;

import com.fw.yydb.entiy.AlwaysMapperDto;

public interface AlwaysMapper {
	AlwaysMapperDto getMaxByPeriods(Long lotteryCode);

	long countByPeriods(Long lotteryCode);

	AlwaysMapperDto getActivity(String lotteryCode);

	void addAlways(AlwaysMapperDto dto);
}
