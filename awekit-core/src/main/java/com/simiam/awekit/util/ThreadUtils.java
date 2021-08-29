package com.simiam.awekit.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Title: ThreadUtils</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.CMCC Co., Ltd. (c) 2019</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2019/10/15 11:37</p>
 */
public abstract class ThreadUtils {
    private static final Logger logger = LoggerFactory.getLogger(ThreadUtils.class);

    public static void sleep(long mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            logger.error("Fail to execute sleep method!", e);
        }
    }
}
