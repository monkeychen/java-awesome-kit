package com.simiam.awekit.security.service;

import com.google.common.collect.Maps;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

/**
 * <p>Title: UploadFileValidator</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.CMCC Co., Ltd. (c) 2021</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2021/10/10 12:42 上午</p>
 */
public interface UploadFileValidator {

    default boolean needValidate() {
        return true;
    }

    default Map<String, Object> validate(String filePath) throws FileNotFoundException {
        return validate(new FileInputStream(filePath));
    }

    default Map<String, Object> validate(InputStream inputStream) {
        return Maps.newHashMap();
    }

    int order();
}
