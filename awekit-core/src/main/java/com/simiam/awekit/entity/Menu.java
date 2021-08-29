package com.simiam.awekit.entity;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * <p>Title: Menu</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.CMCC Co., Ltd. (c) 2019</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2019/2/14 16:49</p>
 */
public class Menu {
    private Integer menuId;

    private String name;

    private String cnName;

    private String url;

    private Integer level = 1;

    private String parentName;

    private String iconPath;

    private Integer sort;

    private Boolean enable = true;

    private String app;

    private String selectedClass = "";

    private String subMenuListStyle = "display: none;";

    private List<Menu> subMenuList = Lists.newArrayList();

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public List<Menu> getSubMenuList() {
        return subMenuList;
    }

    public void setSubMenuList(List<Menu> subMenuList) {
        this.subMenuList = subMenuList;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getSelectedClass() {
        return selectedClass;
    }

    public void setSelectedClass(String selectedClass) {
        this.selectedClass = selectedClass;
    }

    public String getSubMenuListStyle() {
        return subMenuListStyle;
    }

    public void setSubMenuListStyle(String subMenuListStyle) {
        this.subMenuListStyle = subMenuListStyle;
    }
}
