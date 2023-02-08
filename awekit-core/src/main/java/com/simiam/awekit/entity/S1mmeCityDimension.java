package com.simiam.awekit.entity;

/**
 * <p>Title: S1mmeCityDimension</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.SIMIAM Co., Ltd. (c) 2018</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2018/11/21 18:59</p>
 */
public class S1mmeCityDimension extends S1mmeDimension {
    private Integer cityId;

    public S1mmeCityDimension() {
        setIfaceType(XdrIfaceType.S1MME);
        setDimType(DimensionType.CITY);
    }

    @Override
    public String getPrimary() {
        return getStartTime() + "-" + getCityId();
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }
}
