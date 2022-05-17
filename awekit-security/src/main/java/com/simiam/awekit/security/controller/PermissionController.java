package com.simiam.awekit.security.controller;

import com.simiam.awekit.BaseConstants;
import com.simiam.awekit.api.ResponseBody;
import com.simiam.awekit.api.ResponseCode;
import com.simiam.awekit.security.entity.Permission;
import com.simiam.awekit.security.repository.PermissionRepository;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

/**
 * <p>Title: PermissionController</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.CMCC Co., Ltd. (c) 2021</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2021/12/10 11:19</p>
 */
@RestController("permissionController")
@RequestMapping("/api/permission")
public class PermissionController extends AbstractSecurityController {
    private static final Logger logger = LoggerFactory.getLogger(PermissionController.class);

    @Autowired
    private PermissionRepository permissionRepository;

    @GetMapping(path = "/list")
    public ResponseBody<Map<String, Object>> listPermission(Permission sample) {
        ResponseBody<Map<String, Object>> responseBody = newResponseBody();
        Map<String, Object> dataMap = responseBody.getData();
        if (sample.getName() == null) {
            sample.setName("");
        }
        if (sample.getCnName() == null) {
            sample.setCnName("");
        }
        Pageable pageable = PageRequest.of(sample.getPageNum() - 1, sample.getPageSize(), Sort.by(createSortOrder(sample, "cnName")));
        Page<Permission> page = permissionRepository.findAll((root, query, builder) ->
                builder.or(
                        builder.like(root.get("name"), "%" + sample.getName() + "%"),
                        builder.like(root.get("cnName"), "%" + sample.getCnName() + "%")
                ), pageable
        );
        dataMap.put(BaseConstants.ROW_LIST, page.getContent());
        dataMap.put(BaseConstants.ROW_COUNT, page.getTotalElements());
        return responseBody;
    }

    @PostMapping(path = "/add")
    public ResponseBody<Map<String, Object>> addPermission(@RequestBody Permission permission) {
        ResponseBody<Map<String, Object>> responseBody = new ResponseBody<>(ResponseCode.CODE_OK, ResponseCode.MSG_OK);
        Map<String, Object> dataMap = Maps.newHashMap();
        responseBody.setData(dataMap);
        Optional<Permission> optional = permissionRepository.findOne((root, query, builder) -> builder.equal(root.get("name"), permission.getName()));
        Permission existPerm = optional.orElse(null);
        if (existPerm != null) {
            responseBody.setCode(ResponseCode.CODE_BUSINESS_ERROR);
            responseBody.setMessage("权限【" + existPerm.getName() + "】已存在，请输入其他权限名！");
            dataMap.put(BaseConstants.RESULT, false);
            return responseBody;
        }
        permissionRepository.save(permission);
        dataMap.put(BaseConstants.RESULT, true);
        return responseBody;
    }

    @PostMapping(path = "/update")
    public ResponseBody<Map<String, Object>> updatePermission(@RequestBody Permission permission) {
        ResponseBody<Map<String, Object>> responseBody = new ResponseBody<>(ResponseCode.CODE_OK, ResponseCode.MSG_OK);
        Map<String, Object> dataMap = Maps.newHashMap();
        responseBody.setData(dataMap);
        permissionRepository.save(permission);
        dataMap.put(BaseConstants.RESULT, true);
        return responseBody;
    }

    @PostMapping(path = {"/delete"})
    public ResponseBody<Map<String, Object>> deletePermission(Long id) {
        ResponseBody<Map<String, Object>> responseBody = newResponseBody();
        try {
            permissionRepository.deleteById(id);
        } catch (Exception e) {
            logger.error("Fail to delete Permission[id={}]", id, e);
            responseBody = new ResponseBody<>(ResponseCode.CODE_BUSINESS_ERROR, ResponseCode.MSG_BUSINESS_ERROR);
        }
        return responseBody;
    }
}
