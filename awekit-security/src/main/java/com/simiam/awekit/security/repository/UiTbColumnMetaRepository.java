package com.simiam.awekit.security.repository;

import com.simiam.awekit.security.entity.UiTbColumnMeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * <p>Title: UiTbColumnMetaRepository</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.SIMIAM Co., Ltd. (c) 2020</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2020/3/31 10:44 上午</p>
 */
public interface UiTbColumnMetaRepository extends JpaRepository<UiTbColumnMeta, Long>, JpaSpecificationExecutor<UiTbColumnMeta> {

    List<UiTbColumnMeta> findByEnable(Boolean enable);

    List<UiTbColumnMeta> findByModuleNameAndEnable(String moduleName, Boolean enable);

    List<UiTbColumnMeta> findByModuleNameAndGroupNameAndEnable(String moduleName, String groupName, Boolean enable);
}
