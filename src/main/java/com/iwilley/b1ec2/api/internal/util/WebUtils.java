 package com.iwilley.b1ec2.api.internal.util;
 
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.InputStreamReader;
 import java.io.OutputStream;
 import java.io.Reader;
 import java.net.HttpURLConnection;
 import java.net.InetSocketAddress;
 import java.net.Proxy;
 import java.net.URL;
 import java.net.URLDecoder;
 import java.net.URLEncoder;
 import java.security.SecureRandom;
 import java.security.cert.CertificateException;
 import java.security.cert.X509Certificate;
 import java.util.HashMap;
 import java.util.Map;
 import java.util.Set;
 import javax.net.ssl.HostnameVerifier;
 import javax.net.ssl.HttpsURLConnection;
 import javax.net.ssl.SSLContext;
 import javax.net.ssl.SSLSession;
 import javax.net.ssl.TrustManager;
 import javax.net.ssl.X509TrustManager;
 public abstract class WebUtils
 {
   private static final String DEFAULT_CHARSET = "UTF-8";
   private static final String METHOD_POST = "POST";
   private static final String METHOD_GET = "GET";
   
   private static class DefaultTrustManager
     implements X509TrustManager
   {
     private DefaultTrustManager() {}
     
     public X509Certificate[] getAcceptedIssuers() {
       return null;
     }
     
     public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {}
     
     public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {}
   }
   
   public static String doPost(String url, String proxyHostName, int proxyPort, Map<String, String> params, int connectTimeout, int readTimeout) throws IOException {
     return doPost(url, proxyHostName, proxyPort, params, "UTF-8", connectTimeout, readTimeout);
   }
   
   public static String doPost(String url, String proxyHostName, int proxyPort, Map<String, String> params, String charset, int connectTimeout, int readTimeout) throws IOException {
     return doPost(url, proxyHostName, proxyPort, params, charset, connectTimeout, readTimeout, null);
   }
 
   
   public static String doPost(String url, String proxyHostName, int proxyPort, Map<String, String> params, String charset, int connectTimeout, int readTimeout, Map<String, String> headerMap) throws IOException {
     String ctype = "application/x-www-form-urlencoded;charset=" + charset;
     String query = buildQuery(params, charset);
     byte[] content = new byte[0];
     if (query != null) {
       content = query.getBytes(charset);
     }
     return _doPost(url, proxyHostName, proxyPort, ctype, content, connectTimeout, readTimeout, headerMap);
   }
 
   
   private static String _doPost(String url, String proxyHostName, int proxyPort, String ctype, byte[] content, int connectTimeout, int readTimeout, Map<String, String> headerMap) throws IOException {
     HttpURLConnection conn = null;
     
     OutputStream out = null;
     String rsp = null;
     try {
       conn = getConnection(new URL(url), proxyHostName, proxyPort, "POST", ctype, headerMap);
       conn.setConnectTimeout(connectTimeout);
       conn.setReadTimeout(readTimeout);
       
       out = conn.getOutputStream();
       out.write(content);
       rsp = getResponseAsString(conn);
     } finally {
       if (out != null) {
         out.close();
       }
       if (conn != null) {
         conn.disconnect();
       }
     } 
     
     return rsp;
   }
   
   public static String doGet(String url, String proxyHostName, int proxyPort, Map<String, String> params) throws IOException {
     return doGet(url, proxyHostName, proxyPort, params, "UTF-8");
   }
   
   public static String doGet(String url, String proxyHostName, int proxyPort, Map<String, String> params, String charset) throws IOException {
     HttpURLConnection conn = null;
     String rsp = null;
     
     try {
       String ctype = "application/x-www-form-urlencoded;charset=" + charset;
       String query = buildQuery(params, charset);
       
       conn = getConnection(buildGetUrl(url, query), proxyHostName, proxyPort, "GET", ctype, null);
       rsp = getResponseAsString(conn);
     } finally {
       
       if (conn != null) {
         conn.disconnect();
       }
     } 
     
     return rsp;
   }
   
   private static HttpURLConnection getConnection(URL url, String proxyHostName, int proxyPort, String method, String ctype, Map<String, String> headerMap) throws IOException {
     HttpURLConnection conn = null;
     HttpsURLConnection connHttps = null;
     Proxy proxy = null;
     if (proxyHostName != null)
       proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHostName, proxyPort)); 
     if ("https".equals(url.getProtocol())) {
       SSLContext ctx = null;
       try {
         ctx = SSLContext.getInstance("TLS");
         ctx.init(new javax.net.ssl.KeyManager[0], new TrustManager[] { new DefaultTrustManager() }, new SecureRandom());
       } catch (Exception e) {
         throw new IOException(e);
       } 
       if (proxyHostName != null) {
         connHttps = (HttpsURLConnection)url.openConnection(proxy);
       } else {
         connHttps = (HttpsURLConnection)url.openConnection();
       }  connHttps.setSSLSocketFactory(ctx.getSocketFactory());
       connHttps.setHostnameVerifier(new HostnameVerifier()
           {
             public boolean verify(String hostname, SSLSession session) {
               return true;
             }
           });
       conn = connHttps;
     }
     else if (proxyHostName != null) {
       conn = (HttpURLConnection)url.openConnection(proxy);
     } else {
       conn = (HttpURLConnection)url.openConnection();
     } 
     
     conn.setRequestMethod(method);
     conn.setDoInput(true);
     conn.setDoOutput(true);
     conn.setRequestProperty("Accept", "text/xml,text/javascript,text/html");
     conn.setRequestProperty("User-Agent", "top-sdk-java");
     conn.setRequestProperty("Content-Type", ctype);
     if (headerMap != null) {
       for (Map.Entry<String, String> entry : headerMap.entrySet()) {
         conn.setRequestProperty(entry.getKey(), entry.getValue());
       }
     }
     return conn;
   }
   
   private static URL buildGetUrl(String strUrl, String query) throws IOException {
     URL url = new URL(strUrl);
     if (StringUtils.isEmpty(query)) {
       return url;
     }
     
     if (StringUtils.isEmpty(url.getQuery())) {
       if (strUrl.endsWith("?")) {
         strUrl = String.valueOf(strUrl) + query;
       } else {
         strUrl = String.valueOf(strUrl) + "?" + query;
       }
     
     } else if (strUrl.endsWith("&")) {
       strUrl = String.valueOf(strUrl) + query;
     } else {
       strUrl = String.valueOf(strUrl) + "&" + query;
     } 
 
     
     return new URL(strUrl);
   }
   
   public static String buildQuery(Map<String, String> params, String charset) throws IOException {
     if (params == null || params.isEmpty()) {
       return null;
     }
     
     StringBuilder query = new StringBuilder();
     Set<Map.Entry<String, String>> entries = params.entrySet();
     boolean hasParam = false;
     
     for (Map.Entry<String, String> entry : entries) {
       String name = entry.getKey();
       String value = entry.getValue();
       
       if (StringUtils.areNotEmpty(new String[] { name, value })) {
         if (hasParam) {
           query.append("&");
         } else {
           hasParam = true;
         } 
         
         query.append(name).append("=").append(URLEncoder.encode(value, charset));
       } 
     } 
     
     return query.toString();
   }
   
   protected static String getResponseAsString(HttpURLConnection conn) throws IOException {
     String charset = getResponseCharset(conn.getContentType());
     InputStream es = conn.getErrorStream();
     if (es == null) {
       return getStreamAsString(conn.getInputStream(), charset);
     }
     String msg = getStreamAsString(es, charset);
     if (StringUtils.isEmpty(msg)) {
       throw new IOException(String.valueOf(conn.getResponseCode()) + ":" + conn.getResponseMessage());
     }
     throw new IOException(msg);
   }
 
 
   
   private static String getStreamAsString(InputStream stream, String charset) throws IOException {
     try {
       Reader reader = new InputStreamReader(stream, charset);
       StringBuilder response = new StringBuilder();
       
       char[] buff = new char[1024];
       int read = 0;
       while ((read = reader.read(buff)) > 0) {
         response.append(buff, 0, read);
       }
       
       return response.toString();
     } finally {
       if (stream != null) {
         stream.close();
       }
     } 
   }
   
   private static String getResponseCharset(String ctype) {
     String charset = "UTF-8";
     
     if (!StringUtils.isEmpty(ctype)) {
       String[] params = ctype.split(";"); byte b; int i; String[] arrayOfString1;
       for (i = (arrayOfString1 = params).length, b = 0; b < i; ) { String param = arrayOfString1[b];
         param = param.trim();
         if (param.startsWith("charset")) {
           String[] pair = param.split("=", 2);
           if (pair.length == 2 && 
             !StringUtils.isEmpty(pair[1])) {
             charset = pair[1].trim();
           }
           
           break;
         } 
         b++; }
     
     } 
     return charset;
   }
   
   public static String decode(String value) {
     return decode(value, "UTF-8");
   }
   
   public static String encode(String value) {
     return encode(value, "UTF-8");
   }
   
   public static String decode(String value, String charset) {
     String result = null;
     if (!StringUtils.isEmpty(value)) {
       try {
         result = URLDecoder.decode(value, charset);
       } catch (IOException e) {
         throw new RuntimeException(e);
       } 
     }
     return result;
   }
   
   public static String encode(String value, String charset) {
     String result = null;
     if (!StringUtils.isEmpty(value)) {
       try {
         result = URLEncoder.encode(value, charset);
       } catch (IOException e) {
         throw new RuntimeException(e);
       } 
     }
     return result;
   }
   
   public static Map<String, String> splitUrlQuery(String query) {
     Map<String, String> result = new HashMap<>();
     
     String[] pairs = query.split("&");
     if (pairs != null && pairs.length > 0) {
       byte b; int i; String[] arrayOfString; for (i = (arrayOfString = pairs).length, b = 0; b < i; ) { String pair = arrayOfString[b];
         String[] param = pair.split("=", 2);
         if (param != null && param.length == 2) {
           result.put(param[0], param[1]);
         }
         b++; }
     
     } 
     return result;
   }
 }


