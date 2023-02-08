package com.simiam.awekit.lambda;

import com.simiam.awekit.entity.IndicatorModel;

import java.util.List;

/**
 * <p>Title: IndicatorParser</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.SIMIAM Co., Ltd. (c) 2019</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2019/4/28 16:57</p>
 */
public interface IndicatorParser {
    List<IndicatorModel> parse(List<IndicatorModel> indicatorModelList);
}
