package com.simiam.awekit.util;

import org.apache.commons.lang3.BooleanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * <p>Title: JavaScriptUtils</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.SIMIAM Co., Ltd. (c) 2019</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2019/7/12 12:01</p>
 */
public abstract class JavaScriptUtils {
    private static final Logger logger = LoggerFactory.getLogger(JavaScriptUtils.class);

    public static final ScriptEngineManager SCRIPT_ENGINE_MANAGER = new ScriptEngineManager();

    public static final ScriptEngine SCRIPT_ENGINE = SCRIPT_ENGINE_MANAGER.getEngineByName("nashorn");

    public static Object evalExpression(String express) {
        try {
            return SCRIPT_ENGINE.eval(express);
        } catch (ScriptException e) {
            logger.error("Fail to eval JavaScript Expression: {}!", express);
        }
        return null;
    }

    public static boolean evalBoolExpression(String express) {
        try {
            Object result = SCRIPT_ENGINE.eval(express);
            if (result != null) {
                return BooleanUtils.toBoolean(result.toString());
            }
        } catch (Exception e) {
            logger.error("Fail to eval JavaScript Expression: {}!", express);
        }
        return false;
    }
}
