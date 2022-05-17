package com.simiam.awekit.security.entity;

import com.google.common.base.Joiner;
import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;
import com.simiam.awekit.entity.AbstractEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * <p>Title: User</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.CMCC Co., Ltd. (c) 2019</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2019/12/17 5:52 下午</p>
 */
@Entity
@Table(name = "sys_user")
public class User extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Long id;

    @Column(name = "user_name")
    private String username;

    @Column(name = "real_name")
    private String realName;

    @Column(name = "user_password")
    private String password;

    @Transient
    private String confirmPassword;

    @Column(name = "city_id")
    private Integer cityId;

    @Column(name = "salt")
    private String salt;

    @Column(name = "introduction")
    private String introduction;

    @Column(name = "avatar")
    private String avatar;

    @Transient
    private String inviteCode;

    @ManyToMany
    @JoinTable(name = "sys_user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roleList = Lists.newArrayList();

    @Column(name = "department")
    private String departmentName;

    @Transient
    private Long departmentId = 1L;

    @Column(name = "register_time")
    private Date registerTime;

    @OneToOne
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private Department department;

    public User() {
    }

    public String getRoleName() {
        if (roleList == null) {
            return "";
        }
        List<String> roles = Lists.newArrayList();
        for (Role role : roleList) {
            roles.add(role.getCnName());
        }
        return Joiner.on(",").skipNulls().join(roles);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("username", username)
                .add("realName", realName)
                .add("password", "******")
                .add("cityId", cityId)
                .add("salt", "******")
                .add("roleList", roleList)
                .toString();
    }
}
