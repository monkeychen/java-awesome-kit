package com.simiam.awekit.security.service;

import com.simiam.awekit.security.entity.User;

import java.util.List;

/**
 * <p>Title: SecurityService</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.SIMIAM Co., Ltd. (c) 2019</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2019/12/27 11:24 上午</p>
 */
public interface SecurityService {
    User getUserByName(String userName);

    List<User> listUser(User sample);

    boolean saveUser(User user);
}
