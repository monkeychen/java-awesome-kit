package com.simiam.awekit.security.entity;

import com.google.common.base.Joiner;
import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;
import com.simiam.awekit.entity.AbstractEntity;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.util.List;

/**
 * <p>Title: Department</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.SIMIAM Co., Ltd. (c) 2020</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2020/3/17 9:48 下午</p>
 */
@Entity
@Table(name = "sys_department")
public class Department extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Long id;

    private String name;

    private Boolean leafFlag;

    private String parentName;

    private String parentPath;

    /**
     * 1：移动系统，2：合作伙伴
     */
    private Integer departmentType;

    private Integer sort;

    private Boolean enable = true;

    private Integer level;

    public Department() {

    }

    public Department(String name) {
        this.name = name;
    }

    public Department(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * @param partList 组织路径列表，从大到小：["公司", "部门", "科室"]
     * @return Department列表
     */
    public static List<Department> convertFromList(List<String> partList) {
        List<Department> departmentList = Lists.newArrayList();
        if (CollectionUtils.isEmpty(partList)) {
            return departmentList;
        }
        for (int i = 0; i < partList.size(); i++) {
            String part = partList.get(i);
            Department department = new Department(part);
            if (i == 0) {
                department.parentName = "";
            } else {
                department.parentName = partList.get(i - 1);
            }
            department.level = i;
            department.leafFlag = false;
            department.parentPath = "/" + Joiner.on("/").skipNulls().join(partList.subList(0, i));
            department.departmentType = 1;
            department.sort = 0;
            departmentList.add(department);
        }
        return departmentList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getLeafFlag() {
        return leafFlag;
    }

    public void setLeafFlag(Boolean leafFlag) {
        this.leafFlag = leafFlag;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getParentPath() {
        return parentPath;
    }

    public void setParentPath(String parentPath) {
        this.parentPath = parentPath;
    }

    public Integer getDepartmentType() {
        return departmentType;
    }

    public void setDepartmentType(Integer departmentType) {
        this.departmentType = departmentType;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getFullName() {
        return parentPath + "/" + name;
    }

    public String getDeptTypeName() {
        if (departmentType == 2) {
            return "合作伙伴";
        }
        return "移动系统";
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("name", name)
                .add("parentName", parentName)
                .add("parentPath", parentPath)
                .add("level", level)
                .toString();
    }
}
