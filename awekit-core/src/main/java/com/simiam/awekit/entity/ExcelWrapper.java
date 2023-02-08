package com.simiam.awekit.entity;

import com.simiam.awekit.Awekit;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

/**
 * <p>Title: ExcelWrapper</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.SIMIAM Co., Ltd. (c) 2020</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2020/4/13 11:14 上午</p>
 */
public class ExcelWrapper {
    private Workbook workbook;

    private Map<Integer, String> indexSheetNameMap = Maps.newHashMap();

    private Map<String, Integer> sheetNameIndexMap = Maps.newHashMap();

    private Map<Integer, Sheet> indexSheetMap = Maps.newHashMap();

    public ExcelWrapper(File excelFile) throws IOException, InvalidFormatException {
        this.workbook = WorkbookFactory.create(excelFile);
        parse();
    }

    private void parse() {
        workbook.forEach(sheet -> {
            String sheetName = sheet.getSheetName();
            if (Strings.isNullOrEmpty(sheetName)) {
                return;
            }
            sheetName = sheetName.trim().replaceAll(Awekit.getEnvProperty("poi.excel.sheet.name.replace.regex", "表结构"), "");
            sheetName = sheetName.toLowerCase();
            Integer sheetIndex = workbook.getSheetIndex(sheet);
            indexSheetNameMap.put(sheetIndex, sheetName);
            sheetNameIndexMap.put(sheetName, sheetIndex);
            indexSheetMap.put(sheetIndex, sheet);
        });
    }

    public void closeWorkbook(Logger logger) {
        if (workbook != null) {
            try {
                workbook.close();
            } catch (IOException e) {
                logger.error("Fail to close workbook!", e);
            }
        }
    }

    public Optional<String> getSheetName(Integer sheetIndex) {
        return Optional.ofNullable(indexSheetNameMap.get(sheetIndex));
    }

    public Optional<? extends Sheet> getSheet(Integer sheetIndex) {
        Sheet sheet = indexSheetMap.get(sheetIndex);
        return Optional.ofNullable(sheet);
    }

    public Optional<Integer> getSheetIndex(String sheetName) {
        return Optional.ofNullable(sheetNameIndexMap.get(sheetName));
    }

    public Workbook getWorkbook() {
        return workbook;
    }

    public void setWorkbook(Workbook workbook) {
        this.workbook = workbook;
    }

    public Map<Integer, String> getIndexSheetNameMap() {
        return indexSheetNameMap;
    }

    public void setIndexSheetNameMap(Map<Integer, String> indexSheetNameMap) {
        this.indexSheetNameMap = indexSheetNameMap;
    }

    public Map<String, Integer> getSheetNameIndexMap() {
        return sheetNameIndexMap;
    }

    public void setSheetNameIndexMap(Map<String, Integer> sheetNameIndexMap) {
        this.sheetNameIndexMap = sheetNameIndexMap;
    }

    public Map<Integer, Sheet> getIndexSheetMap() {
        return indexSheetMap;
    }

    public void setIndexSheetMap(Map<Integer, Sheet> indexSheetMap) {
        this.indexSheetMap = indexSheetMap;
    }
}
