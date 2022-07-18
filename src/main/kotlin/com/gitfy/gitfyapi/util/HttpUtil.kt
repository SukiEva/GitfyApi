package com.gitfy.gitfyapi.util

import org.apache.http.HttpStatus
import org.apache.http.NameValuePair
import org.apache.http.client.config.RequestConfig
import org.apache.http.client.entity.UrlEncodedFormEntity
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase
import org.apache.http.client.methods.HttpGet
import org.apache.http.client.methods.HttpPost
import org.apache.http.client.methods.HttpRequestBase
import org.apache.http.client.utils.URIBuilder
import org.apache.http.config.RegistryBuilder
import org.apache.http.conn.socket.ConnectionSocketFactory
import org.apache.http.conn.socket.PlainConnectionSocketFactory
import org.apache.http.conn.ssl.NoopHostnameVerifier
import org.apache.http.conn.ssl.SSLConnectionSocketFactory
import org.apache.http.impl.client.BasicCookieStore
import org.apache.http.impl.client.CloseableHttpClient
import org.apache.http.impl.client.HttpClientBuilder
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager
import org.apache.http.message.BasicNameValuePair
import org.apache.http.ssl.SSLContextBuilder
import org.apache.http.util.EntityUtils
import org.slf4j.LoggerFactory
import java.io.UnsupportedEncodingException

/**
 * Http 工具单例类
 */
object HttpUtil {

    private val LOG = LoggerFactory.getLogger(HttpUtil::class.java)

    private const val DEFAULT_ENCODING = "UTF-8"

    private const val DEFAULT_CONNECT_TIMEOUT = 1000 * 6

    private const val DEFAULT_READ_TIMEOUT = 1000 * 6

    private const val DEFAULT_CONNECT_REQUEST_TIMEOUT = 1000 * 10

    private const val MAX_TOTAL = 64

    private const val MAX_PER_ROUTE = 32

    private const val USER_AGENT =
        "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36"

    private var requestConfig: RequestConfig =
        RequestConfig.custom().setSocketTimeout(DEFAULT_READ_TIMEOUT)
            .setConnectTimeout(DEFAULT_CONNECT_TIMEOUT)
            .setConnectionRequestTimeout(DEFAULT_CONNECT_REQUEST_TIMEOUT).build()
    private lateinit var httpClient: CloseableHttpClient

    /**
     * 初始化配置，允许绕过证书访问
     */
    init {
        try {
            val httpBuilder = HttpClientBuilder.create().apply {
                setDefaultRequestConfig(requestConfig)
                setConnectionManager(
                    // http连接池
                    PoolingHttpClientConnectionManager(
                        //设置协议http和https对应的处理socket链接工厂的对象
                        RegistryBuilder.create<ConnectionSocketFactory>()
                            .register("http", PlainConnectionSocketFactory()).register(
                                "https", SSLConnectionSocketFactory(
                                    SSLContextBuilder().loadTrustMaterial(
                                        null
                                    ) { _, _ -> true }.build(),
                                    NoopHostnameVerifier.INSTANCE
                                )
                            ).build()
                    ).apply {
                        // 设置最大连接数
                        maxTotal = MAX_TOTAL
                        // 设置路由并发数
                        defaultMaxPerRoute = MAX_PER_ROUTE
                    })
                setDefaultCookieStore(BasicCookieStore())
            }
            httpClient = httpBuilder.build()
        } catch (e: Exception) {
            LOG.error("Create HttpClient Error:", e)
        }
    }

    /**
     * Do get
     *
     * @param url 请求url
     * @param params 请求url附加参数
     * @param headers 请求头
     */
    fun doGet(
        url: String,
        params: Map<String, String>? = null,
        headers: Map<String, String>? = null
    ): String? {
        val uriBuilder = URIBuilder(url).apply {
            params?.let {
                it.forEach { (key, value) ->
                    this.addParameter(key, value)
                }
            }
        }.build()
        val httpGet = HttpGet(uriBuilder)
        setHeader(headers, httpGet)
        try {
            return doRequest(httpGet)
        } catch (e: Exception) {
            LOG.error("DoGet Error:", e)
        } finally {
            httpGet.releaseConnection()
        }
        return null
    }

    /**
     * Do post
     *
     * @param url 请求url
     * @param params 请求体参数
     * @param headers 请求头
     */
    fun doPost(
        url: String,
        params: Map<String, String>? = null,
        headers: Map<String, String>? = null
    ): String? {
        val httpPost = HttpPost(url).apply { this.config = requestConfig }
        setHeader(headers, httpPost)
        setPostBody(params, httpPost)
        try {
            return doRequest(httpPost)
        } catch (e: Exception) {
            LOG.error("DoPost Error:", e)
        } finally {
            httpPost.releaseConnection()
        }
        return null
    }


    private fun doRequest(httpMethod: HttpRequestBase): String? {
        try {
            val httpResponse = httpClient.execute(httpMethod)
            if (httpResponse != null && httpResponse.statusLine.statusCode == HttpStatus.SC_OK) {
                return EntityUtils.toString(httpResponse.entity, DEFAULT_ENCODING)
            }
        } catch (e: Exception) {
            LOG.error("HttpRequest Error:", e)
            e.printStackTrace()
        }
        return null
    }


    private fun setPostBody(
        params: Map<String, String>?, httpMethod: HttpEntityEnclosingRequestBase
    ) {
        val parameters: MutableList<NameValuePair> = mutableListOf()
        params?.let {
            it.forEach { (key, value) ->
                parameters.add(BasicNameValuePair(key, value))
            }
        }
        var entity: UrlEncodedFormEntity? = null
        try {
            entity = UrlEncodedFormEntity(parameters, DEFAULT_ENCODING)
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }
        httpMethod.entity = entity
    }

    private fun setHeader(
        headers: Map<String, String>? = mapOf("USER-AGENT" to USER_AGENT),
        httpMethod: HttpRequestBase
    ) {
        headers?.let {
            httpMethod.setHeader("User-Agent", USER_AGENT)
            it.forEach { (key, value) ->
                httpMethod.setHeader(key, value)
            }
        }
    }


}