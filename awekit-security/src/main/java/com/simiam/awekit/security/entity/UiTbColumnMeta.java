package com.simiam.awekit.security.entity;

import com.google.common.base.MoreObjects;

import javax.persistence.*;

/**
 * <p>Title: UiTbColumnMeta</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.SIMIAM Co., Ltd. (c) 2020</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2020/3/31 10:23 上午</p>
 */
@Entity
@Table(name = "ui_tb_column_meta")
public class UiTbColumnMeta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "column_name")
    private String columnName;

    @Column(name = "column_label")
    private String columnLabel;

    @Column(name = "field_name")
    private String fieldName;

    @Column(name = "module_name")
    private String moduleName;

    @Column(name = "group_name")
    private String groupName;

    @Column(name = "group_label")
    private String groupLabel;

    @Column(name = "header_align")
    private String headerAlign;

    @Column(name = "align")
    private String align;

    @Column(name = "width")
    private Integer width;

    @Column(name = "min_width")
    private Integer minWidth;

    @Column(name = "fixed")
    private Boolean fixed;

    @Column(name = "show_overflow_tooltip")
    private Boolean showOverflowTooltip;

    @Column(name = "enable")
    private Boolean enable;

    @Column(name = "sort")
    private Integer sort;

    @Column(name = "formatter")
    private String formatter;

    @Column(name = "scope_flag")
    private Integer scopeFlag;

    @Column(name = "form_ele_type")
    private String formEleType;

    @Column(name = "form_rule")
    private String formRule;

    @Column(name = "extra")
    private String extra;

    @Column(name = "readonly_flag")
    private Boolean readonlyFlag;

    @Column(name = "unit")
    private String unit;

    @Transient
    private Boolean sortable = false;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnLabel() {
        return columnLabel;
    }

    public void setColumnLabel(String columnLabel) {
        this.columnLabel = columnLabel;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupLabel() {
        return groupLabel;
    }

    public void setGroupLabel(String groupLabel) {
        this.groupLabel = groupLabel;
    }

    public String getHeaderAlign() {
        return headerAlign;
    }

    public void setHeaderAlign(String headerAlign) {
        this.headerAlign = headerAlign;
    }

    public String getAlign() {
        return align;
    }

    public void setAlign(String align) {
        this.align = align;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getMinWidth() {
        return minWidth;
    }

    public void setMinWidth(Integer minWidth) {
        this.minWidth = minWidth;
    }

    public Boolean getFixed() {
        return fixed;
    }

    public void setFixed(Boolean fixed) {
        this.fixed = fixed;
    }

    public Boolean getShowOverflowTooltip() {
        return showOverflowTooltip;
    }

    public void setShowOverflowTooltip(Boolean showOverflowTooltip) {
        this.showOverflowTooltip = showOverflowTooltip;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getFormatter() {
        return formatter;
    }

    public void setFormatter(String formatter) {
        this.formatter = formatter;
    }

    public Integer getScopeFlag() {
        return scopeFlag;
    }

    public void setScopeFlag(Integer scopeFlag) {
        this.scopeFlag = scopeFlag;
    }

    public String getFormEleType() {
        return formEleType;
    }

    public void setFormEleType(String formEleType) {
        this.formEleType = formEleType;
    }

    public String getFormRule() {
        return formRule;
    }

    public void setFormRule(String formRule) {
        this.formRule = formRule;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public Boolean getReadonlyFlag() {
        return readonlyFlag;
    }

    public void setReadonlyFlag(Boolean readonlyFlag) {
        this.readonlyFlag = readonlyFlag;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Boolean getSortable() {
        return sortable;
    }

    public void setSortable(Boolean sortable) {
        this.sortable = sortable;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("columnName", columnName)
                .add("columnLabel", columnLabel)
                .add("fieldName", fieldName)
                .add("moduleName", moduleName)
                .add("groupName", groupName)
                .add("groupLabel", groupLabel)
                .add("headerAlign", headerAlign)
                .add("align", align)
                .add("width", width)
                .add("minWidth", minWidth)
                .add("fixed", fixed)
                .add("showOverflowTooltip", showOverflowTooltip)
                .add("enable", enable)
                .add("sort", sort)
                .toString();
    }
}
