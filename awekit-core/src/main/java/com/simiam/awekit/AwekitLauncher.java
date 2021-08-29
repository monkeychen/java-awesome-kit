package com.simiam.awekit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * <p>Title: AwekitLauncher</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.CMCC Co., Ltd. (c) 2018</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2019/4/24 上午10:49</p>
 */
@SpringBootApplication
@EnableScheduling
public class AwekitLauncher {
    private static final Logger logger = LoggerFactory.getLogger(AwekitLauncher.class);

    public static void main(String[] args) {
        logger.info("Startup AwekitLauncher Application ... ");
        SpringApplication.run(AwekitLauncher.class);
    }
}
