package com.simiam.awekit.security.controller;

import com.simiam.awekit.BaseConstants;
import com.simiam.awekit.Awekit;
import com.simiam.awekit.api.ResponseBody;
import com.simiam.awekit.api.ResponseCode;
import com.simiam.awekit.security.entity.User;
import com.simiam.awekit.security.repository.UserRepository;
import com.simiam.awekit.security.service.SecurityService;
import com.simiam.awekit.security.util.SecurityUtils;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <p>Title: UserController</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.CMCC Co., Ltd. (c) 2019</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2019/12/20 9:20 下午</p>
 */
@RestController
@RequestMapping("/api/user")
public class UserController extends AbstractSecurityController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SecurityService securityService;

    @RequestMapping(path = "/info")
    public ResponseBody<Map<String, Object>> getLoginUserInfo() {
        ResponseBody<Map<String, Object>> responseBody = newResponseBody();
        Map<String, Object> dataMap = responseBody.getData();
        User user = getCurrentUser();
        if (user != null) {
            putUserInfoToResponseData(user, dataMap);
        }
        logger.debug("/api/user/info => {}", responseBody);
        return responseBody;
    }

    @PostMapping(path = "/register")
    public ResponseBody<Map<String, Object>> registerUser(@RequestBody User user) {
        ResponseBody<Map<String, Object>> responseBody = new ResponseBody<>(ResponseCode.CODE_OK, ResponseCode.MSG_OK);
        Map<String, Object> dataMap = Maps.newHashMap();
        responseBody.setData(dataMap);
        String storedInviteCode = Awekit.getEnvProperty("dss.register.invite.code", "Cmcc@2020");
        if (!storedInviteCode.equals(user.getInviteCode())) {
            responseBody.setCode(ResponseCode.CODE_BUSINESS_ERROR);
            responseBody.setMessage("邀请码不正确，请联系管理员！");
            return responseBody;
        }
        if (securityService.getUserByName(user.getUsername()) != null) {
            responseBody.setCode(ResponseCode.CODE_BUSINESS_ERROR);
            responseBody.setMessage("用户名【" + user.getUsername() + "】已存在，请输入其他用户名！");
            return responseBody;
        }
        boolean isOk = securityService.saveUser(user);
        dataMap.put(BaseConstants.RESULT, isOk);
        dataMap.put(BaseConstants.USERNAME, user.getUsername());
        dataMap.put(BaseConstants.REAL_NAME, user.getRealName());
        logger.debug("/api/user/register => {}", responseBody);
        return responseBody;
    }

    @GetMapping(path = {"", "/"})
    public ResponseBody<Map<String, Object>> oldListUser(User sample) {
        ResponseBody<Map<String, Object>> responseBody = newResponseBody();
        Map<String, Object> dataMap = responseBody.getData();
        List<User> userList = securityService.listUser(sample);
        dataMap.put(BaseConstants.LIST, desensitizeUserList(userList));
        return responseBody;
    }

    @GetMapping(path = "/list")
    public ResponseBody<Map<String, Object>> listUser(User sample) {
        ResponseBody<Map<String, Object>> responseBody = newResponseBody();
        Map<String, Object> dataMap = responseBody.getData();
        if (sample.getUsername() == null) {
            sample.setUsername("");
        }
        if (sample.getRealName() == null) {
            sample.setRealName("");
        }
        Pageable pageable = PageRequest.of(sample.getPageNum() - 1, sample.getPageSize(), Sort.by(createSortOrder(sample, "username")));
        Page<User> page = userRepository.findAll((root, query, builder) ->
                        builder.or(
                                builder.like(root.get("username"), "%" + sample.getUsername() + "%"),
                                builder.like(root.get("realName"), "%" + sample.getRealName() + "%")
                        )
                , pageable);
        dataMap.put(BaseConstants.ROW_LIST, desensitizeUserList(page.getContent()));
        dataMap.put(BaseConstants.ROW_COUNT, page.getTotalElements());
        return responseBody;
    }

    @PostMapping(path = "/add")
    public ResponseBody<Map<String, Object>> addUser(@RequestBody User user) {
        ResponseBody<Map<String, Object>> responseBody = new ResponseBody<>(ResponseCode.CODE_OK, ResponseCode.MSG_OK);
        Map<String, Object> dataMap = Maps.newHashMap();
        responseBody.setData(dataMap);
        User existUser = userRepository.getFirstByUsername(user.getUsername());
        if (existUser != null) {
            responseBody.setCode(ResponseCode.CODE_BUSINESS_ERROR);
            responseBody.setMessage("用户名【" + user.getUsername() + "】已存在，请输入其他用户名！");
            dataMap.put(BaseConstants.RESULT, false);
            return responseBody;
        }
        boolean isOk = securityService.saveUser(user);
        dataMap.put(BaseConstants.RESULT, isOk);
        dataMap.put(BaseConstants.USERNAME, user.getUsername());
        dataMap.put(BaseConstants.REAL_NAME, user.getRealName());
        logger.debug("/api/user/add => {}", responseBody);
        return responseBody;
    }

    @PostMapping(path = "/update")
    public ResponseBody<Map<String, Object>> updateUser(@RequestBody User user) {
        ResponseBody<Map<String, Object>> responseBody = new ResponseBody<>(ResponseCode.CODE_OK, ResponseCode.MSG_OK);
        Map<String, Object> dataMap = Maps.newHashMap();
        responseBody.setData(dataMap);
        if (BaseConstants.MASK_CODE.equals(user.getPassword())) {
            Optional<User> optional = userRepository.findById(user.getId());
            if (optional.isPresent()) {
                User existUser = optional.get();
                user.setPassword(existUser.getPassword());
                user.setSalt(existUser.getSalt());
            }
        } else {
            String salt = SecurityUtils.createSalt();
            user.setSalt(salt);
            int hashIterations = Awekit.getEnvProperty("security.hash.iteration.count", 1);
            String encryptPwd = SecurityUtils.encryptWithSalt(SecurityUtils.md5(user.getPassword()), salt, hashIterations);
            user.setPassword(encryptPwd);
            user.setConfirmPassword(encryptPwd);
        }
        userRepository.save(user);
        dataMap.put(BaseConstants.RESULT, true);
        dataMap.put(BaseConstants.USERNAME, user.getUsername());
        dataMap.put(BaseConstants.REAL_NAME, user.getRealName());
        logger.debug("/api/user/add => {}", responseBody);
        return responseBody;
    }

    @PostMapping(path = {"/delete"})
    public ResponseBody<Map<String, Object>> deleteUser(Long id) {
        ResponseBody<Map<String, Object>> responseBody = newResponseBody();
        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            logger.error("Fail to delete User[id={}]", id, e);
            responseBody = new ResponseBody<>(ResponseCode.CODE_BUSINESS_ERROR, ResponseCode.MSG_BUSINESS_ERROR);
        }
        return responseBody;
    }
}
