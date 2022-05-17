package com.simiam.awekit.security.entity;

import com.google.common.base.MoreObjects;
import com.simiam.awekit.entity.AbstractEntity;

import javax.persistence.*;

/**
 * <p>Title: Setting</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.CMCC Co., Ltd. (c) 2019</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2019/12/26 4:33 下午</p>
 */
@Entity
@Table(name = "sys_setting")
public class Setting extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Long id;

    private String groupName;

    private String paramName;

    private String paramLabel;

    private String paramVal;

    private String paramType;

    private Integer sort;

    private String descr;

    private Boolean enable = true;

    public Setting() {
    }

    public Setting(String paramName, String paramLabel, String paramVal) {
        this.paramName = paramName;
        this.paramLabel = paramLabel;
        this.paramVal = paramVal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getParamLabel() {
        return paramLabel;
    }

    public void setParamLabel(String paramLabel) {
        this.paramLabel = paramLabel;
    }

    public String getParamVal() {
        return paramVal;
    }

    public void setParamVal(String paramVal) {
        this.paramVal = paramVal;
    }

    public String getParamType() {
        return paramType;
    }

    public void setParamType(String paramType) {
        this.paramType = paramType;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("groupName", groupName)
                .add("paramName", paramName)
                .add("paramVal", paramVal)
                .add("sort", sort)
                .add("enable", enable)
                .toString();
    }
}
