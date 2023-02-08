package com.simiam.awekit.entity;

/**
 * <p>Title: HttpEciDimension</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.SIMIAM Co., Ltd. (c) 2018</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2018/11/21 19:03</p>
 */
public class HttpEciDimension extends HttpCityDimension {
    private Long eci;

    private String cellName;

    private Long tac;

    private String eNodebName;

    private String vendorName;

    public HttpEciDimension() {
        setIfaceType(XdrIfaceType.HTTP);
        setDimType(DimensionType.ECI);
    }

    @Override
    public String getPrimary() {
        return getStartTime() + "-" + getCityId() + "-" + getEci();
    }

    public Long getEci() {
        return eci;
    }

    public void setEci(Long eci) {
        this.eci = eci;
    }

    public String getCellName() {
        return cellName;
    }

    public void setCellName(String cellName) {
        this.cellName = cellName;
    }

    public Long getTac() {
        return tac;
    }

    public void setTac(Long tac) {
        this.tac = tac;
    }

    public String geteNodebName() {
        return eNodebName;
    }

    public void seteNodebName(String eNodebName) {
        this.eNodebName = eNodebName;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    @Override
    public void setPk(Object pk) {
        super.setPk(pk);
        this.eci = (Long) pk;
    }
}
