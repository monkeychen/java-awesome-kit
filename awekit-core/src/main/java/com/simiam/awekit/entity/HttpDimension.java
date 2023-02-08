package com.simiam.awekit.entity;

/**
 * <p>Title: HttpDimension</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.SIMIAM Co., Ltd. (c) 2018</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2018/11/21 18:54</p>
 */
public class HttpDimension extends Dimension {
    private Long httpCnt = 0L;

    private Long httpSucCnt = 0L;

    private Double httpSucRate = 1D;

    private Long tcpCoreCnt = 0L;

    private Long tcpCoreSucCnt = 0L;

    private Double tcpCoreSucRate = 0D;

    private Long upBytes = 0L;

    private Long downBytes = 0L;

    private Double upGiBytes = 0D;

    private Double downGiBytes = 0D;

    private Double upMegaBytes = 0D;

    private Double downMegaBytes = 0D;

    private Long httpDelayCnt = 0L;

    private Double httpSpeedKbps = 0D;

    private Double httpAvgSpeedKbps = 0D;

    private Long firstPkgDelayMs = 0L;

    private Long firstPkgDelayCnt = 0L;

    private Double avgFirstPkgDelayMs = 0D;

    private Double lgPkgSpeedKbps = 0D;

    private Long lgPkgSpeedCnt = 0L;

    private Double lgPkgAvgSpeedKbps = 0D;

    private Long smPkgDelayMs = 0L;

    private Long smPkgDelayCnt = 0L;

    private Double smPkgAvgDelayMs = 0D;

    private Long imsiCnt = 0L;

    @Override
    public void done() {
        if (httpCnt != null && httpCnt != 0 && httpSucCnt != null) {
            httpSucRate = httpSucCnt * 1.0d / httpCnt;
        }

        if (tcpCoreCnt != null && tcpCoreCnt != 0 && tcpCoreSucCnt != null) {
            tcpCoreSucRate = tcpCoreSucCnt * 1.0d / tcpCoreCnt;
        }

        if (httpDelayCnt != null && httpDelayCnt != 0 && httpSpeedKbps != null) {
            httpAvgSpeedKbps = httpSpeedKbps * 1.0d / httpDelayCnt;
        }

        if (firstPkgDelayCnt != null && firstPkgDelayCnt != 0 && firstPkgDelayMs != null) {
            avgFirstPkgDelayMs = firstPkgDelayMs * 1.0d / firstPkgDelayCnt;
        }

        if (lgPkgSpeedCnt != null && lgPkgSpeedCnt != 0 && lgPkgSpeedKbps != null) {
            lgPkgAvgSpeedKbps = lgPkgSpeedKbps * 1.0d / lgPkgSpeedCnt;
        }

        if (smPkgDelayCnt != null && smPkgDelayCnt != 0 && smPkgDelayMs != null) {
            smPkgAvgDelayMs = smPkgDelayMs * 1.0d / smPkgDelayCnt;
        }

        if (upBytes != null) {
            upMegaBytes = upBytes * 1.0d / 1024 / 1024;
            upGiBytes = upBytes * 1.0d / 1024 / 1024 / 1024;
        }

        if (downBytes != null) {
            downMegaBytes = downBytes * 1.0d / 1024 / 1024;
            downGiBytes = downBytes * 1.0d / 1024 / 1024 / 1024;
        }

        setIfaceType(XdrIfaceType.HTTP);
    }

    @Override
    public String getPrimary() {
        return "";
    }

    public Long getHttpCnt() {
        return httpCnt;
    }

    public void setHttpCnt(Long httpCnt) {
        this.httpCnt = httpCnt;
    }

    public Long getHttpSucCnt() {
        return httpSucCnt;
    }

    public void setHttpSucCnt(Long httpSucCnt) {
        this.httpSucCnt = httpSucCnt;
    }

    public Double getHttpSucRate() {
        return httpSucRate;
    }

    public void setHttpSucRate(Double httpSucRate) {
        this.httpSucRate = httpSucRate;
    }

    public Long getTcpCoreCnt() {
        return tcpCoreCnt;
    }

    public void setTcpCoreCnt(Long tcpCoreCnt) {
        this.tcpCoreCnt = tcpCoreCnt;
    }

    public Long getTcpCoreSucCnt() {
        return tcpCoreSucCnt;
    }

    public void setTcpCoreSucCnt(Long tcpCoreSucCnt) {
        this.tcpCoreSucCnt = tcpCoreSucCnt;
    }

    public Double getTcpCoreSucRate() {
        return tcpCoreSucRate;
    }

    public void setTcpCoreSucRate(Double tcpCoreSucRate) {
        this.tcpCoreSucRate = tcpCoreSucRate;
    }

    public Long getUpBytes() {
        return upBytes;
    }

    public void setUpBytes(Long upBytes) {
        this.upBytes = upBytes;
    }

    public Long getDownBytes() {
        return downBytes;
    }

    public void setDownBytes(Long downBytes) {
        this.downBytes = downBytes;
    }

    public Double getUpGiBytes() {
        return upGiBytes;
    }

    public void setUpGiBytes(Double upGiBytes) {
        this.upGiBytes = upGiBytes;
    }

    public Double getDownGiBytes() {
        return downGiBytes;
    }

    public void setDownGiBytes(Double downGiBytes) {
        this.downGiBytes = downGiBytes;
    }

    public Long getHttpDelayCnt() {
        return httpDelayCnt;
    }

    public void setHttpDelayCnt(Long httpDelayCnt) {
        this.httpDelayCnt = httpDelayCnt;
    }

    public Double getHttpSpeedKbps() {
        return httpSpeedKbps;
    }

    public void setHttpSpeedKbps(Double httpSpeedKbps) {
        this.httpSpeedKbps = httpSpeedKbps;
    }

    public Double getHttpAvgSpeedKbps() {
        return httpAvgSpeedKbps;
    }

    public void setHttpAvgSpeedKbps(Double httpAvgSpeedKbps) {
        this.httpAvgSpeedKbps = httpAvgSpeedKbps;
    }

    public Long getFirstPkgDelayMs() {
        return firstPkgDelayMs;
    }

    public void setFirstPkgDelayMs(Long firstPkgDelayMs) {
        this.firstPkgDelayMs = firstPkgDelayMs;
    }

    public Long getFirstPkgDelayCnt() {
        return firstPkgDelayCnt;
    }

    public void setFirstPkgDelayCnt(Long firstPkgDelayCnt) {
        this.firstPkgDelayCnt = firstPkgDelayCnt;
    }

    public Long getImsiCnt() {
        return imsiCnt;
    }

    public void setImsiCnt(Long imsiCnt) {
        this.imsiCnt = imsiCnt;
    }

    public Double getAvgFirstPkgDelayMs() {
        return avgFirstPkgDelayMs;
    }

    public void setAvgFirstPkgDelayMs(Double avgFirstPkgDelayMs) {
        this.avgFirstPkgDelayMs = avgFirstPkgDelayMs;
    }

    public Double getLgPkgSpeedKbps() {
        return lgPkgSpeedKbps;
    }

    public void setLgPkgSpeedKbps(Double lgPkgSpeedKbps) {
        this.lgPkgSpeedKbps = lgPkgSpeedKbps;
    }

    public Long getLgPkgSpeedCnt() {
        return lgPkgSpeedCnt;
    }

    public void setLgPkgSpeedCnt(Long lgPkgSpeedCnt) {
        this.lgPkgSpeedCnt = lgPkgSpeedCnt;
    }

    public Double getLgPkgAvgSpeedKbps() {
        return lgPkgAvgSpeedKbps;
    }

    public void setLgPkgAvgSpeedKbps(Double lgPkgAvgSpeedKbps) {
        this.lgPkgAvgSpeedKbps = lgPkgAvgSpeedKbps;
    }

    public Long getSmPkgDelayMs() {
        return smPkgDelayMs;
    }

    public void setSmPkgDelayMs(Long smPkgDelayMs) {
        this.smPkgDelayMs = smPkgDelayMs;
    }

    public Long getSmPkgDelayCnt() {
        return smPkgDelayCnt;
    }

    public void setSmPkgDelayCnt(Long smPkgDelayCnt) {
        this.smPkgDelayCnt = smPkgDelayCnt;
    }

    public Double getSmPkgAvgDelayMs() {
        return smPkgAvgDelayMs;
    }

    public void setSmPkgAvgDelayMs(Double smPkgAvgDelayMs) {
        this.smPkgAvgDelayMs = smPkgAvgDelayMs;
    }

    public Double getUpMegaBytes() {
        return upMegaBytes;
    }

    public void setUpMegaBytes(Double upMegaBytes) {
        this.upMegaBytes = upMegaBytes;
    }

    public Double getDownMegaBytes() {
        return downMegaBytes;
    }

    public void setDownMegaBytes(Double downMegaBytes) {
        this.downMegaBytes = downMegaBytes;
    }
}
