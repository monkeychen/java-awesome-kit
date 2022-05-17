package com.simiam.awekit.entity;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Transient;
import java.util.Date;

/**
 * <p>Title: AbstractEntity</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.CMCC Co., Ltd. (c) 2019</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2019/12/17 5:53 下午</p>
 */
public abstract class AbstractEntity {
    private static final Logger logger = LoggerFactory.getLogger(AbstractEntity.class);

    @Transient
    protected String orderByClause;

    @Transient
    protected String matchKey;

    @Transient
    protected String customWhereClause;

    @Transient
    protected Date beginTime;

    @Transient
    protected Date endTime;

    /**
     * 页码
     */
    @Transient
    protected int pageNum;

    /**
     * 每页显示记录数
     */
    @Transient
    protected int pageSize;

    @Transient
    protected String sortColumn;

    @Transient
    protected String sortOrder = "asc";

    public AbstractEntity assignProperty(String fieldName, Object fieldValue) {
        try {
            BeanUtils.setProperty(this, fieldName, fieldValue);
        } catch (Exception e) {
            logger.error("Fail to set property[name = {}, val = {}]", fieldName, fieldValue, e);
        }
        return this;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getMatchKey() {
        return matchKey;
    }

    public void setMatchKey(String matchKey) {
        this.matchKey = matchKey;
    }

    public String getCustomWhereClause() {
        return customWhereClause;
    }

    public void setCustomWhereClause(String customWhereClause) {
        this.customWhereClause = customWhereClause;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getSortColumn() {
        return sortColumn;
    }

    public void setSortColumn(String sortColumn) {
        this.sortColumn = sortColumn;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }
}
