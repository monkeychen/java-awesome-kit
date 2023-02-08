package com.simiam.awekit.entity;

import com.google.common.base.Splitter;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * <p>Title: HttpAppDimension</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.SIMIAM Co., Ltd. (c) 2018</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2018/11/21 19:09</p>
 */
public class HttpAppDimension extends HttpCityDimension {
    private Integer appClassId;

    private Integer appSubClassId;

    private String appClassName;

    private String appSubClassName;

    public HttpAppDimension() {
        setIfaceType(XdrIfaceType.HTTP);
        setDimType(DimensionType.APP);
    }

    public Integer getAppClassId() {
        return appClassId;
    }

    public void setAppClassId(Integer appClassId) {
        this.appClassId = appClassId;
    }

    public Integer getAppSubClassId() {
        return appSubClassId;
    }

    public void setAppSubClassId(Integer appSubClassId) {
        this.appSubClassId = appSubClassId;
    }

    public String getAppClassName() {
        return appClassName;
    }

    public void setAppClassName(String appClassName) {
        this.appClassName = appClassName;
    }

    public String getAppSubClassName() {
        return appSubClassName;
    }

    public void setAppSubClassName(String appSubClassName) {
        this.appSubClassName = appSubClassName;
    }

    @Override
    public void setPk(Object pk) {
        super.setPk(pk);
        List<String> list = Splitter.on("_").splitToList(pk.toString());
        if (CollectionUtils.isEmpty(list) || list.size() < 2) {
            return;
        }
        appClassId = Integer.parseInt(list.get(0));
        appSubClassId = Integer.parseInt(list.get(1));
    }
}
