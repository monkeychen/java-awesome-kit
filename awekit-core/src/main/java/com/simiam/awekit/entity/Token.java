package com.simiam.awekit.entity;

import java.util.Date;

/**
 * <p>Title: Token</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.SIMIAM Co., Ltd. (c) 2021</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2021/12/4 11:14 下午</p>
 */
public class Token {

    private String accessToken;

    private String refreshToken;

    private Long expiredSecond;

    private Date lastRefreshTime;

    public Token(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Token(String accessToken, String refreshToken, Long expiredSecond) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expiredSecond = expiredSecond;
        this.lastRefreshTime = new Date();
    }

    public boolean isExpired() {
        Date now = new Date();
        return (now.getTime() / 1000) >= (this.lastRefreshTime.getTime() / 1000 + expiredSecond);
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Long getExpiredSecond() {
        return expiredSecond;
    }

    public void setExpiredSecond(Long expiredSecond) {
        this.expiredSecond = expiredSecond;
    }

    public Date getLastRefreshTime() {
        return lastRefreshTime;
    }

    public void setLastRefreshTime(Date lastRefreshTime) {
        this.lastRefreshTime = lastRefreshTime;
    }
}
