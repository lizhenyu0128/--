package azstudio.top.config;


import ch.qos.logback.core.net.SyslogOutputStream;
import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.util.HashMap;
import java.util.Map;


/**
 * Author:
 * Data:2019-04-23 11:40
 * Description:<>
 */

public final class wxLogin {

    private final static String appid = "1";
    private final static String secret = "2";
    private final static String grant_type = "authorization_code";
    //private final  static  String

    public static Map<String, Object> getjscode2session(String js_code) {
        Map<String, Object> res = new HashMap<String, Object>();
        HttpClient httpclient = HttpClientBuilder.create().build();
        String uri = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appid + "&secret=" + secret + "&js_code=" + js_code + "&grant_type=" + grant_type;
        System.out.println(uri);
        HttpGet httpget = new HttpGet(uri);
        String json = null;
        HttpResponse response = null;
        try {
            response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                json = EntityUtils.toString(entity, "UTF-8").trim();
                res = (Map<String, Object>) JSON.parse(json);
            }

        } catch (Exception e) {
            res.put("\"errcode\"", -1);
        }
        return res;
    }
}
