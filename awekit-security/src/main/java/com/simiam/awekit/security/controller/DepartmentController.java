package com.simiam.awekit.security.controller;

import com.simiam.awekit.BaseConstants;
import com.simiam.awekit.api.ResponseBody;
import com.simiam.awekit.api.ResponseCode;
import com.simiam.awekit.security.entity.Department;
import com.simiam.awekit.security.repository.DepartmentRepository;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
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
 * <p>Title: DepartmentController</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.CMCC Co., Ltd. (c) 2021</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2021/12/10 11:19</p>
 */
@RestController("departmentController")
@RequestMapping("/api/department")
public class DepartmentController extends AbstractSecurityController {
    private static final Logger logger = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    private DepartmentRepository departmentRepository;

    @GetMapping(path = "/list")
    public ResponseBody<Map<String, Object>> listDepartment(Department sample) {
        ResponseBody<Map<String, Object>> responseBody = newResponseBody();
        Map<String, Object> dataMap = responseBody.getData();
        if (sample.getName() == null) {
            sample.setName("");
        }
        if (sample.getParentPath() == null) {
            sample.setParentPath("");
        }
        Pageable pageable = PageRequest.of(sample.getPageNum() - 1, sample.getPageSize(), Sort.by(createSortOrder(sample, "name")));
        Page<Department> page = departmentRepository.findAll((root, query, builder) ->
                builder.or(
                        builder.like(root.get("name"), "%" + sample.getName() + "%"),
                        builder.like(root.get("parentPath"), "%" + sample.getParentPath() + "%")
                ), pageable
        );
        dataMap.put(BaseConstants.ROW_LIST, page.getContent());
        dataMap.put(BaseConstants.ROW_COUNT, page.getTotalElements());
        return responseBody;
    }

    @GetMapping(path = "/listall")
    public ResponseBody<Map<String, Object>> listAllDepartment(Department sample) {
        ResponseBody<Map<String, Object>> responseBody = newResponseBody();
        Map<String, Object> dataMap = responseBody.getData();
        if (sample.getName() == null) {
            sample.setName("");
        }
        if (sample.getParentPath() == null) {
            sample.setParentPath("");
        }
        List<Department> departmentList = departmentRepository.findAll((root, query, builder) ->
                builder.or(
                        builder.like(root.get("name"), "%" + sample.getName() + "%"),
                        builder.like(root.get("parentPath"), "%" + sample.getParentPath() + "%")
                ), Sort.by(createSortOrder(sample, "sort"))
        );
        dataMap.put(BaseConstants.ROW_LIST, departmentList);
        dataMap.put(BaseConstants.ROW_COUNT, departmentList.size());
        return responseBody;
    }

    @PostMapping(path = "/add")
    public ResponseBody<Map<String, Object>> addDepartment(@RequestBody Department dept) {
        ResponseBody<Map<String, Object>> responseBody = new ResponseBody<>(ResponseCode.CODE_OK, ResponseCode.MSG_OK);
        Map<String, Object> dataMap = Maps.newHashMap();
        responseBody.setData(dataMap);
        Optional<Department> optional = departmentRepository.findOne((root, query, builder) ->
                builder.and(
                        builder.equal(root.get("name"), dept.getName()),
                        builder.equal(root.get("parentPath"), dept.getParentPath())
                )
        );
        Department existEntity = optional.orElse(null);
        if (existEntity != null) {
            responseBody.setCode(ResponseCode.CODE_BUSINESS_ERROR);
            responseBody.setMessage("部门【" + existEntity.getParentPath() + "/" + existEntity.getName() + "】已存在，请输入其他名字！");
            dataMap.put(BaseConstants.RESULT, false);
            return responseBody;
        }
        if (dept.getSort() == null) {
            long sortVal = departmentRepository.count() + 1;
            dept.setSort((int) sortVal);
        }
        dept.setLeafFlag(true);
        String parentPath = dept.getParentPath();
        if (Strings.isNullOrEmpty(parentPath)) {
            responseBody.setCode(ResponseCode.CODE_BUSINESS_ERROR);
            responseBody.setMessage("未发现上级部门信息，请重新选择上级部门！");
            dataMap.put(BaseConstants.RESULT, false);
            return responseBody;
        } else {
            List<String> pathList = Splitter.on("/").splitToList(parentPath);
            dept.setLevel(pathList.size());
            if (pathList.size() > 0) {
                dept.setParentName(pathList.get(pathList.size() - 1));
            } else {
                dept.setParentName(BaseConstants.NO_DEPARTMENT);
            }
        }
        departmentRepository.save(dept);
        dataMap.put(BaseConstants.RESULT, true);
        return responseBody;
    }

    @PostMapping(path = "/update")
    public ResponseBody<Map<String, Object>> updateDepartment(@RequestBody Department department) {
        ResponseBody<Map<String, Object>> responseBody = new ResponseBody<>(ResponseCode.CODE_OK, ResponseCode.MSG_OK);
        Map<String, Object> dataMap = Maps.newHashMap();
        responseBody.setData(dataMap);
        departmentRepository.save(department);
        dataMap.put(BaseConstants.RESULT, true);
        return responseBody;
    }

    @PostMapping(path = {"/delete"})
    public ResponseBody<Map<String, Object>> deleteDepartment(Long id) {
        ResponseBody<Map<String, Object>> responseBody = newResponseBody();
        try {
            departmentRepository.deleteById(id);
        } catch (Exception e) {
            logger.error("Fail to delete Department[id={}]", id, e);
            responseBody = new ResponseBody<>(ResponseCode.CODE_BUSINESS_ERROR, ResponseCode.MSG_BUSINESS_ERROR);
        }
        return responseBody;
    }
}
