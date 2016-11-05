package com.fw.yydb.entiy;

public class JoinOrder {

    private String userId;
    private String nickName;
    private Integer participateCount;
    private String participateTime;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getParticipateCount() {
        return participateCount;
    }

    public void setParticipateCount(Integer participateCount) {
        this.participateCount = participateCount;
    }

    public String getParticipateTime() {
        return participateTime;
    }

    public void setParticipateTime(String participateTime) {
        this.participateTime = participateTime;
    }
}
