package com.github.gitfy.gitfyapi.util;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.*;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * Http 工具类
 *
 * @author SukiEva
 */
@Slf4j
public class HttpClientUtil {
    private static final String DEFAULT_ENCODING = "UTF-8";

    private static final int DEFAULT_CONNECT_TIMEOUT = 1000 * 6;

    private static final int DEFAULT_READ_TIMEOUT = 1000 * 6;

    private static final int DEFAULT_CONNECT_REQUEST_TIMEOUT = 1000 * 10;

    /**
     * 最大连接数
     */
    private static final int MAX_TOTAL = 64;

    /**
     * 路由并发数
     */
    private static final int MAX_PER_ROUTE = 32;


    private static final RequestConfig REQUEST_CONFIG = RequestConfig.custom()
            .setSocketTimeout(DEFAULT_READ_TIMEOUT)
            .setConnectTimeout(DEFAULT_CONNECT_TIMEOUT)
            .setConnectionRequestTimeout(DEFAULT_CONNECT_REQUEST_TIMEOUT)
            .build();

    private static CloseableHttpClient httpClient;

    static {
        try {
            val sslContext = new SSLContextBuilder().loadTrustMaterial(null, (chain, authType) -> true).build();
            val sslSocketFactory = new SSLConnectionSocketFactory(sslContext,
                    NoopHostnameVerifier.INSTANCE);
            // 设置http和https协议对应的处理socket链接工厂的对象
            val socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create().
                    register("http", PlainConnectionSocketFactory.getSocketFactory())
                    .register("https", sslSocketFactory)
                    .build();
            // 创建http连接池
            val connectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
            connectionManager.setMaxTotal(MAX_TOTAL);
            connectionManager.setDefaultMaxPerRoute(MAX_PER_ROUTE);
            httpClient = HttpClients.custom()
                    .setDefaultRequestConfig(REQUEST_CONFIG)
                    .setConnectionManager(connectionManager)
                    .build();
        } catch (NoSuchAlgorithmException | KeyStoreException | KeyManagementException exception) {
            log.error("Fail to create customed http client: {}", exception.getMessage());
            httpClient = HttpClients.createDefault();
        }
    }

    private static class Holder {
        private static final HttpClientUtil INSTANCE = new HttpClientUtil();
    }

    /**
     * Singleton HttpClientUtil
     *
     * @return HttpClientUtil
     */
    public static HttpClientUtil create() {
        return Holder.INSTANCE;
    }

    /**
     * Get 请求
     *
     * @param url    请求地址
     * @param params 请求参数
     * @param header 请求头
     * @return String
     */
    public String doGet(String url, Map<String, Object> params, Map<String, String> header) {
        return doRequest(HttpGet.METHOD_NAME, url, params, header);
    }

    /**
     * Post 请求
     *
     * @param url    请求地址
     * @param params 请求参数
     * @param header 请求头
     * @return String
     */
    public String doPost(String url, Map<String, Object> params, Map<String, String> header) {
        return doRequest(HttpPost.METHOD_NAME, url, params, header);
    }

    private String doRequest(String method, String url, Map<String, Object> params,
                             Map<String, String> header) {
        val request = buildRequest(method, url, params, header);
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            if (response != null && response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                return EntityUtils.toString(response.getEntity(), DEFAULT_ENCODING);
            }
        } catch (IOException exception) {
            log.error("HttpClient do{}: {}", method, exception.getMessage());
        }
        return "";
    }

    private HttpUriRequest buildRequest(String method, String url, Map<String, Object> params,
                                        Map<String, String> header) {
        val requestBuilder = RequestBuilder.create(method);
        requestBuilder.setUri(url);
        requestBuilder.setConfig(REQUEST_CONFIG);
        // 设置请求参数
        if (params != null) {
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                requestBuilder.addParameter(entry.getKey(), String.valueOf(entry.getValue()));
            }
        }
        // 设置请求头
        if (header != null) {
            for (Map.Entry<String, String> entry : header.entrySet()) {
                requestBuilder.setHeader(entry.getKey(), entry.getValue());
            }
        }
        return requestBuilder.build();
    }
}
