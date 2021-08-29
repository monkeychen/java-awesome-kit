package com.simiam.awekit.entity;

import com.google.common.base.MoreObjects;

/**
 * <p>Title: Dimension</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.CMCC Co., Ltd. (c) 2018</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2018/11/21 18:53</p>
 */
public abstract class Dimension {
    protected XdrIfaceType ifaceType;

    protected DimensionType dimType;

    protected Long startTime;

    private TimeType timeType = TimeType.MINUTE_5;

    private Object pk;

    public abstract void done();

    public abstract String getPrimary();

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public XdrIfaceType getIfaceType() {
        return ifaceType;
    }

    public void setIfaceType(XdrIfaceType ifaceType) {
        this.ifaceType = ifaceType;
    }

    public DimensionType getDimType() {
        return dimType;
    }

    public void setDimType(DimensionType dimType) {
        this.dimType = dimType;
    }

    public TimeType getTimeType() {
        return timeType;
    }

    public void setTimeType(TimeType timeType) {
        this.timeType = timeType;
    }

    public Object getPk() {
        return pk;
    }

    public void setPk(Object pk) {
        this.pk = pk;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("ifaceType", ifaceType)
                .add("dimType", dimType)
                .add("startTime", startTime)
                .add("timeType", timeType)
                .add("pk", pk)
                .toString();
    }
}
