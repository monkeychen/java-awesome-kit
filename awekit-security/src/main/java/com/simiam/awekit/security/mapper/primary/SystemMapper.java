package com.simiam.awekit.security.mapper.primary;

import com.simiam.awekit.security.entity.Setting;

import java.util.List;

/**
 * <p>Title: SystemMapper</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.CMCC Co., Ltd. (c) 2019</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2019/12/26 4:10 下午</p>
 */
public interface SystemMapper {

    List<Setting> listSetting(Setting sample);

}
