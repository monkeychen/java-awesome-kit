package com.simiam.awekit.web;

import com.google.common.base.MoreObjects;

import java.util.List;

/**
 * <p>Title: PageResponse</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.SIMIAM Co., Ltd. (c) 2018</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2018/10/20 16:23</p>
 */
public class PageResponse<T> {
    private long total;

    private List<T> rows;

    public PageResponse() {
    }

    public PageResponse(long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("total", total)
                .add("rows", rows)
                .toString();
    }
}
