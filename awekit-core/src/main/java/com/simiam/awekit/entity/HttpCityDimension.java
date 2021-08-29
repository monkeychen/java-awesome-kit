package com.simiam.awekit.entity;

/**
 * <p>Title: HttpCityDimension</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.CMCC Co., Ltd. (c) 2018</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2018/11/21 18:59</p>
 */
public class HttpCityDimension extends HttpDimension {
    private Integer cityId;

    public HttpCityDimension() {
        setIfaceType(XdrIfaceType.HTTP);
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
