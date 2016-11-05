package com.fw.yydb.entiy;

import java.util.Date;

public class AlwaysMapperDto {
	private Long id;
	private Long lotteryCode;
	private String prizeCode;
	private Date lotteryTime;
	private Long source;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getLotteryCode() {
		return lotteryCode;
	}

	public void setLotteryCode(Long lotteryCode) {
		this.lotteryCode = lotteryCode;
	}

	public String getPrizeCode() {
		return prizeCode;
	}

	public void setPrizeCode(String prizeCode) {
		this.prizeCode = prizeCode;
	}

	public Date getLotteryTime() {
		return lotteryTime;
	}

	public void setLotteryTime(Date lotteryTime) {
		this.lotteryTime = lotteryTime;
	}

	public Long getSource() {
		return source;
	}

	public void setSource(Long source) {
		this.source = source;
	}

}
