package com.simiam.awekit.entity;

/**
 * <p>Title: HttpNetDimension</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.CMCC Co., Ltd. (c) 2018</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2018/11/21 19:11</p>
 */
public class HttpNetDimension extends HttpDimension {
    private String netIp;

    public HttpNetDimension() {
        setIfaceType(XdrIfaceType.HTTP);
        setDimType(DimensionType.NET);
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
