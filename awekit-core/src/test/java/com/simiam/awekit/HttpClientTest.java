package com.simiam.awekit;

import com.alibaba.fastjson.JSON;
import com.simiam.awekit.util.HttpClientUtils;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * <p>Title: HttpClientTest</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.CMCC Co., Ltd. (c) 2019</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2019/8/5 11:13</p>
 */
public class HttpClientTest {
    @Test
    public void testHttpPost() throws IOException {
        String uri = "http://localhost/endtoend/user/token.json";

        JSON json = HttpClientUtils.httpJsonPost(uri);
        System.out.println(json.toString());
    }

    @Test
    public void testHttpGet() throws IOException {
        String uri = "http://localhost/endtoend/uidcheck.json?uid=nHb5yPX0jZ7ISEqpG7xTjOaF4XBoiJRLyVfIC405jDw";
        String uri2 = "http://localhost/endtoend/uidcheck.json";

        JSON json = HttpClientUtils.httpJsonGet(uri);
        System.out.println(json.toString());

        json = HttpClientUtils.httpJsonGet(uri2);
        System.out.println(json.toString());
    }

    @Test
    public void testWebPage() {
        String result = HttpClientUtils.httpGet("http://wwww.baidu.com",
                response -> EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8));
        System.out.println(result);
    }
}
