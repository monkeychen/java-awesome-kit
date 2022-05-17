package com.simiam.awekit.security.repository;

import com.simiam.awekit.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * <p>Title: UserRepository</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.CMCC Co., Ltd. (c) 2021</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2021/12/8 10:44</p>
 */
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    User getFirstByUsername(String userName);
}
