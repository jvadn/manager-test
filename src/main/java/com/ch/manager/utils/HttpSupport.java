package com.ch.manager.utils;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * 用于支持http的接口调用 post，get
 *
 * @author lifetime
 */
public abstract class HttpSupport {

    private static final int                          connectTimeout           = 5000;  // http连接的最大建立时间
    private static final int                          socketTimeout            = 30000; // 数据包的最大响应时间
    private static final int                          connectionRequestTimeout = 2000;  // 请求连接的超时时间
    private static PoolingHttpClientConnectionManager MANAGER;

    static {
        MANAGER = new PoolingHttpClientConnectionManager();
        MANAGER.setMaxTotal(100);
        MANAGER.setDefaultMaxPerRoute(20);
    }

    public static String post(String url, Map<String, Object> params) throws Exception {
        HttpPost httpPost = new HttpPost(url);
        if (params != null) {
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            for (String key : params.keySet()) {
                nameValuePairs.add(new BasicNameValuePair(key, String.valueOf(params.get(key))));
            }
            try {
                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        HttpEntity entity = null;
        String responseContent = null;
        try {
            httpClient = HttpClients.custom()
                .setConnectionManager(MANAGER)
                .setDefaultRequestConfig(RequestConfig.custom()
                    .setConnectionRequestTimeout(connectionRequestTimeout)
                    .setConnectTimeout(connectTimeout)
                    .setSocketTimeout(socketTimeout)
                    .build())
                .build();
            response = httpClient.execute(httpPost);
            entity = response.getEntity();
            responseContent = EntityUtils.toString(entity, "UTF-8");
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return responseContent;
    }

    public static String post(String url) throws Exception {
        return post(url, null);
    }

    public static String get(String url) throws Exception {
        return Request.Get(url)
            .connectTimeout(connectTimeout)
            .socketTimeout(socketTimeout)
            .execute()
            .returnContent()
            .asString(Charset.forName("UTF-8"));
    }

    /**
     * @param url url里带不带？都可以
     * @param params
     * @return
     * @throws Exception
     */
    public static String get(String url, Map<String, Object> params) throws Exception {
        StringBuffer urlPara = new StringBuffer();
        if (params != null) {
            int index = 0;
            for (Iterator<Entry<String, Object>> it = params.entrySet().iterator(); it.hasNext();) {
                Entry<String, Object> entry = it.next();
                if (index == 0) {
                    urlPara.append(entry.getKey()).append("=").append(
                        URLEncoder.encode(String.valueOf(entry.getValue()), "UTF-8"));
                } else {
                    urlPara.append("&").append(entry.getKey()).append("=").append(
                        URLEncoder.encode(String.valueOf(entry.getValue()), "UTF-8"));
                }
                index++;
            }
            if (url.indexOf("?") != -1) {
                url += urlPara.toString();
            } else {
                url = url + "?" + urlPara.toString();
            }
        }
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        HttpEntity entity = null;
        String responseContent = null;
        try {
            HttpGet httpGet = new HttpGet(url);
            httpClient = HttpClients.custom()
                .setConnectionManager(MANAGER)
                .setDefaultRequestConfig(RequestConfig.custom()
                    .setConnectionRequestTimeout(connectionRequestTimeout)
                    .setConnectTimeout(connectTimeout)
                    .setSocketTimeout(socketTimeout)
                    .build())
                .build();
            response = httpClient.execute(httpGet);
            entity = response.getEntity();
            responseContent = EntityUtils.toString(entity, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return responseContent;
    }

    /**
     * @param url
     * @param json
     * @return
     * @throws Exception
     */
    public static String postJson(String url, String json) throws Exception {
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        HttpEntity entity = null;
        String responseContent = null;
        try {
            httpPost.addHeader("Content-type", "application/json; charset=utf-8");
            httpPost.setHeader("Accept", "application/json");
            httpPost.setEntity(new StringEntity(json, Charset.forName("UTF-8")));
            httpClient = HttpClients.custom()
                .setConnectionManager(MANAGER)
                .setDefaultRequestConfig(RequestConfig.custom()
                    .setConnectionRequestTimeout(connectionRequestTimeout)
                    .setConnectTimeout(connectTimeout)
                    .setSocketTimeout(100000)
                    .build())
                .build();
            response = httpClient.execute(httpPost);
            entity = response.getEntity();
            responseContent = EntityUtils.toString(entity, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return responseContent;
    }

    /**
     * form表单的提交方式，支持文件
     *
     * @param url
     * @param params
     * @return
     * @throws Exception
     */
    public static String postFile(String url, Map<String, Object> params) throws Exception {
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        for (Iterator<Entry<String, Object>> it = params.entrySet().iterator(); it.hasNext();) {
            Entry<String, Object> entry = it.next();
            if (entry.getValue() instanceof File) {
                builder.addBinaryBody(entry.getKey(), (File) entry.getValue());
            } else if (entry.getValue() instanceof byte[]) {
                builder.addBinaryBody(entry.getKey(), (byte[]) entry.getValue());
            } else {
                builder.addTextBody(entry.getKey(), String.valueOf(entry.getValue()));
            }
        }
        return Request.Post(url)
            .connectTimeout(connectTimeout)
            .socketTimeout(socketTimeout)
            .body(builder.build())
            .execute()
            .returnContent()
            .asString(Charset.forName("UTF-8"));
    }

}
