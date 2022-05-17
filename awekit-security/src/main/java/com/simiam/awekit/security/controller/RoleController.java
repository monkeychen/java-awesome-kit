package com.simiam.awekit.security.controller;

import com.simiam.awekit.BaseConstants;
import com.simiam.awekit.api.ResponseBody;
import com.simiam.awekit.api.ResponseCode;
import com.simiam.awekit.security.entity.Role;
import com.simiam.awekit.security.repository.RoleRepository;
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
 * <p>Title: RoleController</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.CMCC Co., Ltd. (c) 2021</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2021/12/10 11:19</p>
 */
@RestController("roleController")
@RequestMapping("/api/role")
public class RoleController extends AbstractSecurityController {
    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping(path = "/list")
    public ResponseBody<Map<String, Object>> listRole(Role sample) {
        ResponseBody<Map<String, Object>> responseBody = newResponseBody();
        Map<String, Object> dataMap = responseBody.getData();
        if (sample.getName() == null) {
            sample.setName("");
        }
        if (sample.getCnName() == null) {
            sample.setCnName("");
        }
        Pageable pageable = PageRequest.of(sample.getPageNum() - 1, sample.getPageSize(), Sort.by(createSortOrder(sample, "cnName")));
        Page<Role> page = roleRepository.findAll((root, query, builder) ->
                builder.or(
                        builder.like(root.get("name"), "%" + sample.getName() + "%"),
                        builder.like(root.get("cnName"), "%" + sample.getCnName() + "%")
                ), pageable
        );
        dataMap.put(BaseConstants.ROW_LIST, page.getContent());
        dataMap.put(BaseConstants.ROW_COUNT, page.getTotalElements());
        return responseBody;
    }

    @GetMapping(path = "/listall")
    public ResponseBody<Map<String, Object>> listAllRole(Role sample) {
        ResponseBody<Map<String, Object>> responseBody = newResponseBody();
        Map<String, Object> dataMap = responseBody.getData();
        if (sample.getName() == null) {
            sample.setName("");
        }
        if (sample.getCnName() == null) {
            sample.setCnName("");
        }
        List<Role> departmentList = roleRepository.findAll((root, query, builder) ->
                builder.or(
                        builder.like(root.get("name"), "%" + sample.getName() + "%"),
                        builder.like(root.get("cnName"), "%" + sample.getCnName() + "%")
                )
        );
        dataMap.put(BaseConstants.ROW_LIST, departmentList);
        dataMap.put(BaseConstants.ROW_COUNT, departmentList.size());
        return responseBody;
    }

    @PostMapping(path = "/add")
    public ResponseBody<Map<String, Object>> addRole(@RequestBody Role role) {
        ResponseBody<Map<String, Object>> responseBody = new ResponseBody<>(ResponseCode.CODE_OK, ResponseCode.MSG_OK);
        Map<String, Object> dataMap = Maps.newHashMap();
        responseBody.setData(dataMap);
        Optional<Role> optional = roleRepository.findOne((root, query, builder) -> builder.equal(root.get("name"), role.getName()));
        Role existRole = optional.orElse(null);
        if (existRole != null) {
            responseBody.setCode(ResponseCode.CODE_BUSINESS_ERROR);
            responseBody.setMessage("角色【" + existRole.getName() + "】已存在，请输入其他角色名！");
            dataMap.put(BaseConstants.RESULT, false);
            return responseBody;
        }
        roleRepository.save(role);
        dataMap.put(BaseConstants.RESULT, true);
        return responseBody;
    }

    @PostMapping(path = "/update")
    public ResponseBody<Map<String, Object>> updateRole(@RequestBody Role role) {
        ResponseBody<Map<String, Object>> responseBody = new ResponseBody<>(ResponseCode.CODE_OK, ResponseCode.MSG_OK);
        Map<String, Object> dataMap = Maps.newHashMap();
        responseBody.setData(dataMap);
        roleRepository.save(role);
        dataMap.put(BaseConstants.RESULT, true);
        return responseBody;
    }

    @PostMapping(path = {"/delete"})
    public ResponseBody<Map<String, Object>> deleteRole(Long id) {
        ResponseBody<Map<String, Object>> responseBody = newResponseBody();
        try {
            roleRepository.deleteById(id);
        } catch (Exception e) {
            logger.error("Fail to delete Role[id={}]", id, e);
            responseBody = new ResponseBody<>(ResponseCode.CODE_BUSINESS_ERROR, ResponseCode.MSG_BUSINESS_ERROR);
        }
        return responseBody;
    }
}
