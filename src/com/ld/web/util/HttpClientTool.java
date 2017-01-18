package com.ld.web.util;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

/**
 * 
 *<p>Title: HttpClientTool</p>
 *<p>Copyright: Copyright (c) 2016</p>
 *<p>Description: </p>
 *
 *@author LD
 *
 *@date 2016-11-25
 */
public class HttpClientTool {

    private static final Logger logger = Logger.getLogger(HttpClientTool.class);

    private static final HttpClientTool INSTANCE = new HttpClientTool();

    private final int CONNECT_TIME_OUT = 10000;
    private final int SOCKET_TIME_OUT = 15000;
    private final int CONNECTION_REQUEST_TIMEOUT = 5000;

    /**
     * Merge mainUrl and suffixUrl
     * 
     * @param mainUrl
     * @param suffixUrl
     * @return
     */
    public String mergeUrl(String mainUrl, String suffixUrl) {
        mainUrl = mainUrl.endsWith("/") || mainUrl.endsWith("!") || mainUrl.endsWith("=") ? mainUrl : mainUrl + "/";
        suffixUrl = suffixUrl.startsWith("/") ? suffixUrl.substring(1) : suffixUrl;
        return mainUrl + suffixUrl;
    }

    /**
     * Http Post
     * 
     * @param url
     * @param header
     * @param params
     * @throws Exception
     * @return
     */
    public String post(String url, RequestConfig config, Map<String, String> header, Map<String, String> params) throws Exception {
        logger.info(String.format("Httpclient send data: %s", JsonMapper.getInstance().toJson(params)));

        CloseableHttpClient httpclient = url.startsWith("https") ? createSSLClientDefault() : HttpClients.createDefault();

        try {
            HttpPost httppost = new HttpPost(url);

            if (null != config) {
                httppost.setConfig(config);
            }

            if (null != header && !header.isEmpty()) {
                for (Entry<String, String> entry : header.entrySet()) {
                    httppost.addHeader(entry.getKey(), entry.getValue());
                }
            }
            if (null != params && !params.isEmpty()) {
                httppost.setEntity(new UrlEncodedFormEntity(getNameValuePair(params), "utf-8"));
            }

            HttpResponse response = httpclient.execute(httppost);
            if (null != response && response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = response.getEntity();
                String resp = null != entity ? EntityUtils.toString(entity) : null;
                logger.info(String.format("Httpclient response data: %s", resp));
                return resp;
            }
            return null;
        } catch (Exception e) {
            logger.error(String.format("Httpclient post exception: %s", e.getMessage()), e);
            throw new Exception(e);
        } finally {
            closeClient(httpclient);
        }
    }

    /**
     * Http Post
     * 
     * @param url
     * @param data
     * @throws Exception
     * @return
     */
    public String post(String url, RequestConfig config, String data) throws Exception {
        logger.info(String.format("Httpclient send json: %s", data));

        CloseableHttpClient httpclient = url.startsWith("https") ? createSSLClientDefault() : HttpClients.createDefault();

        try {
            HttpPost httppost = new HttpPost(url);

            if (null != config) {
                httppost.setConfig(config);
            }

            if (!StringUtil.isEmpty(data)) {
                StringEntity entity = new StringEntity(data, "utf-8");
                entity.setContentType("application/x-www-form-urlencoded;charset=utf-8");
                httppost.setEntity(entity);
            }

            HttpResponse response = httpclient.execute(httppost);
            if (null != response && response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = response.getEntity();
                String resp = null != entity ? EntityUtils.toString(entity) : null;
                logger.info(String.format("Httpclient response data: %s", resp));
                return resp;
            }
            return null;
        } catch (Exception e) {
            logger.error(String.format("Httpclient post exception: %s", e.getMessage()), e);
            throw new Exception(e);
        } finally {
            closeClient(httpclient);
        }
    }

    public String buildSoapRequestData(String methodName, Map<String, Object> params) {
        String temp = "&&&&&";

        StringBuffer sb = new StringBuffer();
        sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
        sb.append("<soap:Envelope xmlns:xsd=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">");
        sb.append("<soap:Body>" + temp + "</soap:Body>" + "</soap:Envelope>");

        String core = "<" + methodName + ">";
        for (Entry<String, Object> entry : params.entrySet()) {
            core += "<" + entry.getKey() + ">" + entry.getValue() + "</" + entry.getKey() + ">";
        }
        core += "</" + methodName + ">";

        String data = sb.toString();
        data = data.replaceAll(temp, core);
        return data;
    }

    /**
     * Convert Map<String, String> to List<NameValuePair>
     * 
     * @param params
     * @return
     */
    private List<NameValuePair> getNameValuePair(Map<String, String> params) {
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        for (Entry<String, String> entry : params.entrySet()) {
            list.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        return list;
    }

    /**
     * Close httpclient
     * 
     * @param httpclient
     */
    private void closeClient(CloseableHttpClient httpclient) {
        if (null != httpclient) {
            try {
                httpclient.close();
            } catch (IOException e) {
                logger.error(String.format("Httpclient close exception: %s", e.getMessage()), e);
            }
        }
    }

    /**
     * Set request config timeout
     * 
     * @return
     */
    public RequestConfig getDefaultTimeoutConfig() {

        return RequestConfig.custom()
                .setSocketTimeout(SOCKET_TIME_OUT)
                .setConnectTimeout(CONNECT_TIME_OUT)
                .setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT).build();
    }

    /**
     * Create ssl client
     * 
     * @return
     */
    public CloseableHttpClient createSSLClientDefault() {
        try {
            SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, new TrustStrategy() {
                @Override
                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }

            }).build();

            HostnameVerifier allowAll = new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            };

            return HttpClients.custom().setSSLSocketFactory(new SSLConnectionSocketFactory(sslContext, allowAll)).build();
        } catch (Exception e) {
            logger.error(String.format("Create ssl client error: %s", e.getMessage()), e);
        }
        return HttpClients.createDefault();
    }

    public static HttpClientTool getInstance() {
        return INSTANCE;
    }

    private HttpClientTool() {
    }
}
