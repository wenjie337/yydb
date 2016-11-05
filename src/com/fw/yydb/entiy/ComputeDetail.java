package com.fw.yydb.entiy;

import java.util.ArrayList;
import java.util.List;

public class ComputeDetail {

    private Long numberA;
    private Long numberB;
    private Long lotteryCode;
    private Integer luckNum;
    private List<NumberA> list = new ArrayList<NumberA>();

    public Long getNumberA() {
        return numberA;
    }

    public void setNumberA(Long numberA) {
        this.numberA = numberA;
    }

    public Long getNumberB() {
        return numberB;
    }

    public void setNumberB(Long numberB) {
        this.numberB = numberB;
    }

    public Long getLotteryCode() {
        return lotteryCode;
    }

    public void setLotteryCode(Long lotteryCode) {
        this.lotteryCode = lotteryCode;
    }

    public Integer getLuckNum() {
        return luckNum;
    }

    public void setLuckNum(Integer luckNum) {
        this.luckNum = luckNum;
    }

    public List<NumberA> getList() {
        return list;
    }

    public void setList(List<NumberA> list) {
        this.list = list;
    }
}
