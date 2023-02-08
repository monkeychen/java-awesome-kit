package com.simiam.awekit.security.service;

import com.simiam.awekit.BaseConstants;
import com.simiam.awekit.Awekit;
import com.simiam.awekit.security.entity.Role;
import com.simiam.awekit.security.entity.User;
import com.simiam.awekit.security.repository.DepartmentRepository;
import com.simiam.awekit.security.repository.RoleRepository;
import com.simiam.awekit.security.repository.UserRepository;
import com.simiam.awekit.security.util.SecurityUtils;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

/**
 * <p>Title: SecurityServiceImpl</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.SIMIAM Co., Ltd. (c) 2019</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2019/12/18 8:41 上午</p>
 */
@Service("securityService")
public class SecurityServiceImpl implements SecurityService {
    private static final Logger logger = LoggerFactory.getLogger(SecurityServiceImpl.class);

    public static final Long NORMAL_USER = 4L;

    public static final Long SP_USER = 7L;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public User getUserByName(String userName) {
        if (userName == null || userName.isEmpty()) {
            return null;
        }
        return userRepository.getFirstByUsername(userName);
    }

    @Override
    public List<User> listUser(User sample) {
        return userRepository.findAll(Example.of(sample));
    }

    @Override
    public boolean saveUser(User user) {
        if (user == null) {
            return false;
        }
        if (Strings.isNullOrEmpty(user.getRealName())) {
            user.setRealName(user.getUsername());
        }
        if (Strings.isNullOrEmpty(user.getAvatar())) {
            user.setAvatar("images/user.png");
        }
        if (user.getCityId() == null) {
            user.setCityId(590);
        }
        if (Strings.isNullOrEmpty(user.getIntroduction())) {
            user.setIntroduction(user.getUsername());
        }
        if (Strings.isNullOrEmpty(user.getDepartmentName())) {
            user.setDepartmentName(BaseConstants.NO_DEPARTMENT);
            user.setDepartmentId(1L);
        }
        if (user.getRegisterTime() == null) {
            user.setRegisterTime(new Date());
        }
        if (user.getDepartment() == null) {
            user.setDepartment(departmentRepository.getOne(user.getDepartmentId()));
        }
        String salt = SecurityUtils.createSalt();
        user.setSalt(salt);
        int hashIterations = Awekit.getEnvProperty("security.hash.iteration.count", 1);
        String encryptPwd = SecurityUtils.encryptWithSalt(user.getPassword(), salt, hashIterations);
        user.setPassword(encryptPwd);
        user.setConfirmPassword(encryptPwd);
        List<Role> roleList = user.getRoleList();
        if (CollectionUtils.isEmpty(roleList)) {
            roleList = Lists.newArrayList();
            user.setRoleList(roleList);
            Long roleId = "特殊".equals(user.getDepartmentName()) ? SP_USER : NORMAL_USER;
            Role targetRole = roleRepository.getOne(roleId);
            roleList.add(targetRole);
        }
        userRepository.save(user);
        return true;
    }
}
