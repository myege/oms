 package com.what21.util;
 
 import java.security.KeyManagementException;
 import java.security.NoSuchAlgorithmException;
 import java.security.SecureRandom;
 import java.security.cert.CertificateException;
 import java.security.cert.X509Certificate;
 import javax.net.ssl.SSLContext;
 import javax.net.ssl.TrustManager;
 import javax.net.ssl.X509TrustManager;
 
 public class SSLUtils {
   public static SSLContext createSslContext() throws NoSuchAlgorithmException, KeyManagementException {
     SSLContext sc = SSLContext.getInstance("SSL");
     sc.init(null, new TrustManager[] { new X509TrustManager()
           {
             public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {}
             
             public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {}
             
             public X509Certificate[] getAcceptedIssuers() {
               return new X509Certificate[0];
             }
           },   }, new SecureRandom());
     
     return sc;
   }
 }


