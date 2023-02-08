package com.simiam.awekit.util;

import com.simiam.awekit.entity.ExcelWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * <p>Title: DataImportUtil</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.SIMIAM Co., Ltd. (c) 2020</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2020/4/13 11:11 上午</p>
 */
public abstract class DataImportUtil {
    private static final Logger logger = LoggerFactory.getLogger(DataImportUtil.class);

    public static ExcelWrapper toExcelEntity(File excelFile) {
        ExcelWrapper excelEntity = null;
        try {
            excelEntity = new ExcelWrapper(excelFile);
        } catch (Exception e) {
            logger.error("Fail to convert to ExcelEntity from file[{}]!", excelFile.getAbsolutePath(), e);
        }
        return excelEntity;
    }
}
