package com.simiam.awekit.entity;

/**
 * <p>Title: S1mmeDimension</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.CMCC Co., Ltd. (c) 2018</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2018/12/4 16:54</p>
 */
public class S1mmeDimension extends Dimension {

    private Long attachCnt = 0L;

    private Long attachTotalCnt = 0L;

    private Long attachSucCnt = 0L;

    private Double attachSucRate = 1D;

    private Long attachTotalDelayMs = 0L;

    private Double attachAvgDelayMs = 0D;

    private Long epsTotalCnt = 0L;

    private Long epsSucCnt = 0L;

    private Double epsSucRate = 1D;

    private Long epsTotalDelayMs = 0L;

    private Double epsAvgDelayMs = 0D;

    @Override
    public void done() {
        if (attachTotalCnt != null && attachTotalCnt != 0 && attachSucCnt != null) {
            attachSucRate = attachSucCnt * 1.0d / attachTotalCnt;
        }
        if (attachSucCnt != null && attachSucCnt != 0 && attachTotalDelayMs != null) {
            attachAvgDelayMs = attachTotalDelayMs * 1.0d / attachSucCnt;
        }
        if (epsTotalCnt != null && epsTotalCnt != 0 && epsSucCnt != null) {
            epsSucRate = epsSucCnt * 1.0d / epsTotalCnt;
        }
        if (epsSucCnt != null && epsSucCnt != 0 && epsTotalDelayMs != null) {
            epsAvgDelayMs = epsTotalDelayMs * 1.0d / epsSucCnt;
        }
        setIfaceType(XdrIfaceType.S1MME);
    }

    @Override
    public String getPrimary() {
        return "";
    }

    public Long getAttachCnt() {
        return attachCnt;
    }

    public void setAttachCnt(Long attachCnt) {
        this.attachCnt = attachCnt;
    }

    public Long getAttachTotalCnt() {
        return attachTotalCnt;
    }

    public void setAttachTotalCnt(Long attachTotalCnt) {
        this.attachTotalCnt = attachTotalCnt;
    }

    public Long getAttachSucCnt() {
        return attachSucCnt;
    }

    public void setAttachSucCnt(Long attachSucCnt) {
        this.attachSucCnt = attachSucCnt;
    }

    public Double getAttachSucRate() {
        return attachSucRate;
    }

    public void setAttachSucRate(Double attachSucRate) {
        this.attachSucRate = attachSucRate;
    }

    public Long getAttachTotalDelayMs() {
        return attachTotalDelayMs;
    }

    public void setAttachTotalDelayMs(Long attachTotalDelayMs) {
        this.attachTotalDelayMs = attachTotalDelayMs;
    }

    public Double getAttachAvgDelayMs() {
        return attachAvgDelayMs;
    }

    public void setAttachAvgDelayMs(Double attachAvgDelayMs) {
        this.attachAvgDelayMs = attachAvgDelayMs;
    }

    public Long getEpsTotalCnt() {
        return epsTotalCnt;
    }

    public void setEpsTotalCnt(Long epsTotalCnt) {
        this.epsTotalCnt = epsTotalCnt;
    }

    public Long getEpsSucCnt() {
        return epsSucCnt;
    }

    public void setEpsSucCnt(Long epsSucCnt) {
        this.epsSucCnt = epsSucCnt;
    }

    public Double getEpsSucRate() {
        return epsSucRate;
    }

    public void setEpsSucRate(Double epsSucRate) {
        this.epsSucRate = epsSucRate;
    }

    public Long getEpsTotalDelayMs() {
        return epsTotalDelayMs;
    }

    public void setEpsTotalDelayMs(Long epsTotalDelayMs) {
        this.epsTotalDelayMs = epsTotalDelayMs;
    }

    public Double getEpsAvgDelayMs() {
        return epsAvgDelayMs;
    }

    public void setEpsAvgDelayMs(Double epsAvgDelayMs) {
        this.epsAvgDelayMs = epsAvgDelayMs;
    }
}
