package com.simiam.awekit.util;

import com.google.common.io.Resources;
import org.apache.commons.lang3.SystemUtils;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * <p>Title: SigarUtils</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.CMCC Co., Ltd. (c) 2020</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2020/7/31 11:45 上午</p>
 */
public abstract class SigarUtils {
    private static final Logger logger = LoggerFactory.getLogger(SigarUtils.class);

    public final static Sigar sigar = initSigar();

    private static Sigar initSigar() {
        String path = System.getProperty("java.library.path");
        try {
            String file = Resources.getResource("sigar/lib/.sigar_shellrc").getFile();
            File classPath = new File(file).getParentFile();

            if (SystemUtils.IS_OS_WINDOWS) {
                path += ";" + classPath.getCanonicalPath();
            } else {
                path += ":" + classPath.getCanonicalPath();
            }
            System.setProperty("java.library.path", path);
            logger.info("java.library.path = {}", path);
            return new Sigar();
        } catch (Exception e) {
            logger.error("Fail to initialize Sigar instance[java.library.path = {}]", path, e);
        }
        return null;
    }

    public static void main(String[] args) throws SigarException {
        Mem mem = SigarUtils.sigar.getMem();
        System.out.println(mem.getFreePercent());
    }
}
