insert into sys_department (id, name, leaf_flag, parent_name, parent_path, sort, level, department_type)
values (1, '未分配', 1, 'XX公司', '/XX公司', 999, 1, 1),
       (2, 'XX公司', 0, '', '/', 1, 0, 1);

insert into sys_user (id, user_name, real_name, user_password, city_id, salt, avatar, department, department_id)
values
       (1, 'admin', '系统管理员', '61df093bd4ec0a7454b7d71b318f766b', 590, md5('money'), 'images/user.png', '未分配', 1),
       (2, 'test', '测试用户', '61df093bd4ec0a7454b7d71b318f766b', 590, md5('money'), 'images/user.png', 'XX部门', 2);

insert into sys_role (id, name, cn_name, descr)
values
       (1, 'admin', '管理员', ''),
       (2, 'developer', '开发者', ''),
       (3, 'manager', '经理', ''),
       (4, 'normal_user', '用户', ''),
       (5, 'editor', '编辑', ''),
       (6, 'audit', '审计', '');

insert into sys_permission (id, name, cn_name, descr)
values
       (1, 'all', '所有权限', '该权限只能赋给系统管理员'),
       (2, 'user:mgmt', '用户模块管理', '用户管理权限：增、删、改、查'),
       (3, 'user:query', '用户模块查询', '用户查询权限：只能查询用户信息');

insert into sys_user_role (user_id, role_id) VALUES (1, 1), (2, 2);
insert into sys_role_permission(role_id, permission_id) values (1, 1), (3, 3);

insert into sys_setting (group_name, param_name, param_label, param_val, sort)
values ('boolean_label', 'true', '是', '1', 1),
       ('boolean_label', 'false', '否', '0', 2),
       ('db_data_type', 'integer', 'Integer', 'integer', 1),
       ('db_data_type', 'bigint', 'Bigint', 'bigint', 2),
       ('db_data_type', 'float', 'Float', 'float', 3),
       ('db_data_type', 'double', 'Double', 'double', 4),
       ('db_data_type', 'char', 'Char', 'char', 5),
       ('db_data_type', 'varchar', 'Varchar', 'varchar', 6),
       ('db_data_type', 'numeric', 'Numeric', 'numeric', 7),
       ('db_data_type', 'date', 'Date', 'date', 8),
       ('db_data_type', 'datetime', 'Datetime', 'datetime', 9),
       ('db_data_type', 'timestamp', 'Timestamp', 'timestamp', 10);

DELETE FROM ui_tb_column_meta WHERE module_name = 'system';
INSERT INTO ui_tb_column_meta
(column_name, column_label, field_name, module_name, group_name, group_label, header_align, align, width, min_width,
 fixed, show_overflow_tooltip, enable, sort, formatter, scope_flag, form_ele_type, form_rule, extra, readonly_flag, unit)
VALUES ('user_name', '用户账号', 'username', 'system', 'user', '用户管理', 'center', 'center', '0', '120', '0', '1', '1', '1', '', '11', 'text', 'defRule', 'sortable', '0', ''),
       ('real_name', '用户姓名', 'realName', 'system', 'user', '用户管理', 'center', 'center', '0', '120', '0', '1', '1', '2', '', '11', 'text', 'defRule', '', '0', ''),
       ('city_id', '归属地市', 'cityId', 'system', 'user', '用户管理', 'center', 'center', '100', '100', '0', '1', '1', '3', 'cityId', '11', 'text', 'defRule', 'sortable', '0', ''),
       ('department', '归属科室', 'departmentName', 'system', 'user', '用户管理', 'center', 'left', '0', '200', '0', '1', '1', '4', '', '11', 'text', 'defRule', '', '0', ''),
       ('register_time', '注册时间', 'registerTime', 'system', 'user', '用户管理', 'center', 'center', '0', '150', '0', '1', '1', '6', 'std-format', '11', 'datetime', 'defRule', 'sortable', '0', ''),
       ('introduction', '简介', 'introduction', 'system', 'user', '用户管理', 'center', 'center', '0', '200', '0', '1', '1', '7', '', '11', 'text', 'defRule', '', '0', ''),
       ('role_name', '用户角色', 'roleName', 'system', 'user', '用户管理', 'center', 'center', '0', '120', '0', '1', '1', '5', '', '11', 'text', 'defRule', '', '0', ''),
       ('name', '角色英文名', 'name', 'system', 'role', '角色管理', 'center', 'center', '200', '120', '0', '1', '1', '1', '', '12', 'text', 'defRule', 'sortable', '0', ''),
       ('cn_name', '角色中文名', 'cnName', 'system', 'role', '角色管理', 'center', 'center', '200', '120', '0', '1', '1', '2', '', '12', 'text', 'defRule', 'sortable', '0', ''),
       ('descr', '角色描述', 'descr', 'system', 'role', '角色管理', 'center', 'center', '0', '250', '0', '1', '1', '3', '', '12', 'text', 'defRule', '', '0', ''),
       ('name', '权限英文名', 'name', 'system', 'permission', '权限管理', 'center', 'center', '0', '120', '0', '1', '1', '1', '', '13', 'text', 'defRule', 'sortable', '0', ''),
       ('cn_name', '权限中文名', 'cnName', 'system', 'permission', '权限管理', 'center', 'center', '0', '120', '0', '1', '1', '2', '', '13', 'text', 'defRule', 'sortable', '0', ''),
       ('descr', '权限描述', 'descr', 'system', 'permission', '权限管理', 'center', 'center', '0', '250', '0', '1', '1', '3', '', '13', 'text', 'defRule', '', '0', ''),
       ('name', '部门/科室名称', 'name', 'system', 'dept', '部门管理', 'center', 'center', '0', '150', '0', '1', '1', '1', '', '14', 'text', 'defRule', 'sortable', '0', ''),
       ('parent_path', '上级部门', 'parentPath', 'system', 'dept', '部门管理', 'center', 'left', '0', '300', '0', '1', '1', '2', '', '14', 'text', 'defRule', 'sortable', '0', ''),
       ('dept_type_name', '单位类型', 'deptTypeName', 'system', 'dept', '部门管理', 'center', 'center', '0', '100', '0', '1', '1', '3', '', '14', 'text', 'defRule', 'sortable', '0', ''),
       ('enable', '是否启用', 'enable', 'system', 'dept', '部门管理', 'center', 'center', '0', '100', '0', '1', '1', '4', '', '14', 'bool', 'defRule', 'sortable', '0', '');

