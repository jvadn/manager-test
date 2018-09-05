package com.ch.manager.utils;

import java.util.Map;

public class HttpUtils {

    /**
     * GET 方式调用 默认UTF8编码
     *
     * @param url
     * @return
     */
    public static String URLGet(String url) {
        try {
            return HttpSupport.get(url);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * GET 方式调用 默认UTF8编码
     *
     * @param url
     * @return
     */
    public static String URLGet(String url, Map<String, Object> params) {
        try {
            return HttpSupport.get(url, params);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * POST 方式调用 默认UTF8编码
     *
     * @param url
     * @param params 请求参数
     * @return
     */
    public static String URLPost(String url, Map<String, Object> params) {
        try {
            return HttpSupport.post(url, params);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * POST 方式调用接口，参数已JSON字符串格式传入
     *
     * @param url
     * @param jsonData 参数json字符串
     * @param enc 编码
     * @return
     */
    public static String URLPostJSON(String url, String json, String enc) {
        try {
            return HttpSupport.postJson(url, json);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String postJSON(String url, String json) {
        try {
            return HttpSupport.postJson(url, json);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
