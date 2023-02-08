package com.simiam.awekit;

import org.junit.Test;

import java.util.UUID;

/**
 * <p>Title: CommonTest</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.SIMIAM Co., Ltd. (c) 2020</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2020/1/26 2:03 下午</p>
 */
public class CommonTest {

    @Test
    public void testUuid() {
        System.out.println(UUID.randomUUID().toString());
    }
}
