package com.simiam.awekit.web.echarts;

import com.google.common.collect.Lists;

import java.io.Serializable;
import java.util.List;

/**
 * <p>Title: AxisX</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.SIMIAM Co., Ltd. (c) 2018</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2018/10/31 10:24</p>
 */
public class AxisY implements Serializable {
    private static final long serialVersionUID = 2860504623175398279L;

    private String name;

    /**
     * 坐标轴类型。
     * 可选：
     * 'value'： 数值轴，适用于连续数据。<br>
     * 'category'： 类目轴，适用于离散的类目数据，为该类型时必须通过 data 设置类目数据。<br>
     * 'time'： 时间轴，适用于连续的时序数据，与数值轴相比时间轴带有时间的格式化，在刻度计算上也有所不同，例如会根据跨度的范围来决定使用月，星期，日还是小时范围的刻度。<br>
     * 'log'： 对数轴。适用于对数数据。<br>
     */
    private String type = "value";

    private int gridIndex = 0;

    /**
     * 可选值：left,right
     */
    private String position = "left";

    private int offset = 0;

    private List<String> dataList = Lists.newArrayList();

    public AxisY() {
    }

    public AxisY(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public AxisY(String name, String type, List<String> dataList) {
        this.name = name;
        this.type = type;
        this.dataList = dataList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getGridIndex() {
        return gridIndex;
    }

    public void setGridIndex(int gridIndex) {
        this.gridIndex = gridIndex;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public List<String> getDataList() {
        return dataList;
    }

    public void setDataList(List<String> dataList) {
        this.dataList = dataList;
    }
}
