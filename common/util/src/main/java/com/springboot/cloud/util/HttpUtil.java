package com.springboot.cloud.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnRouteParams;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HttpUtil {

    private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    /**
     * get请求
     *
     * @param
     *
     * @throws Exception
     */
    public static String httpGet(String url,boolean isProxy) throws Exception {
        String resStr = null;
        CloseableHttpClient httpclient = null;
        try {
            // 设置为get取连接的方式.
            HttpGet httpGet = new HttpGet(url);
            httpclient = createDefault(isProxy,httpGet);
            // 设置要访问的HttpHost,即是目标站点的HttpHost
            HttpHost target = new HttpHost(url, 80);
            // 得到返回的response.
            HttpResponse response = httpclient.execute(target,httpGet);
            // 得到返回的client里面的实体对象信息.
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                resStr = EntityUtils.toString(entity,"UTF-8");
            }
            logger.info("http get :" + response.getStatusLine() + resStr);
            return resStr;
        } catch (Exception e) {
            logger.error("http get exception", e);
            throw e;
        } finally {
            // 关闭请求
            httpclient.getConnectionManager().shutdown();
        }
    }

    private static CloseableHttpClient createDefault(boolean isProxy,HttpGet httpGet) {
        if(isProxy){
            HttpHost proxy = new HttpHost("10.191.113.100", 8002, "http");

            RequestConfig config = RequestConfig.custom().setProxy(proxy).build();

            httpGet.setConfig(config);

            CredentialsProvider provider = new BasicCredentialsProvider();

            provider.setCredentials(new AuthScope(proxy), new UsernamePasswordCredentials("XXX", "XXX"));

            return HttpClients.custom().setDefaultCredentialsProvider(provider).build();
        }else{
            return HttpClients.createDefault();
        }
    }

    /**
     * post请求
     *
     * @param  url，请求参数，代理相关信息
     * @throws Exception
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static String httpPost(Map<String, String> params,String url) throws Exception {
        String resStr = null;
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpPost httpost = new HttpPost(url);
            // 添加参数
            List nvps = new ArrayList();
            if (params != null && !params.isEmpty()) {
                Set<Map.Entry<String, String>> set = params.entrySet();
                for (Map.Entry<String, String> entry : set) {
                    nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                }
            }
            if(nvps.size() > 0){
                httpost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
            }
            logger.info("----------testForPostDate------"+new Date());
            HttpResponse response = httpclient.execute(httpost);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                resStr = EntityUtils.toString(entity, "UTF-8");
            }
            logger.info("http URI:" + httpost.getURI().toString());
            logger.info("http post :" + response.getStatusLine() + resStr);
            return resStr;
        } catch (Exception e) {
            logger.error("http post exception", e);
            throw e;
        } finally {
            // 关闭请求
            httpclient.getConnectionManager().shutdown();
        }
    }


}
