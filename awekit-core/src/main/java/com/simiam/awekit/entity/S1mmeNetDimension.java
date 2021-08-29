package com.simiam.awekit.entity;

/**
 * <p>Title: S1mmeNetDimension</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.CMCC Co., Ltd. (c) 2018</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2018/11/21 19:03</p>
 */
public class S1mmeNetDimension extends S1mmeCityDimension {
    private String netIp;

    public S1mmeNetDimension() {
        setIfaceType(XdrIfaceType.S1MME);
        setDimType(DimensionType.NET);
    }

    @Override
    public String getPrimary() {
        return getStartTime() + "-" + getCityId() + "-" + getNetIp();
    }

    public String getNetIp() {
        return netIp;
    }

    public void setNetIp(String netIp) {
        this.netIp = netIp;
    }

    @Override
    public void setPk(Object pk) {
        super.setPk(pk);
        this.netIp = (String) pk;
    }
}
