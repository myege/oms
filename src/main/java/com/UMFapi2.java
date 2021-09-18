 package com;
 
 import com.umf.base.ReqUMF;
 import com.umf.base.rest.APIContext;
 import com.umf.base.util.RSAUtils;
 import com.umf.base.util.RestUtil;
 import com.what21.util.umf.Base64;
 import java.io.ByteArrayInputStream;
 import java.io.FileInputStream;
 import java.io.IOException;
 import java.io.InputStream;
 import java.security.Key;
 import java.security.KeyFactory;
 import java.security.PrivateKey;
 import java.security.Signature;
 import java.security.cert.CertificateFactory;
 import java.security.cert.X509Certificate;
 import java.security.spec.PKCS8EncodedKeySpec;
 import java.security.spec.X509EncodedKeySpec;
 import java.util.HashMap;
 import java.util.Map;
 import javax.crypto.Cipher;
 public class UMFapi2
 {
   private static String crtPath = "C://cert_2d59.crt";
   private static String p8Path = "C://52489_.key.p8";
   private static String UMFp8Path = "C://52489_.key.p8";
   
   public static void main(String[] args) throws Exception {
     APIContext apiContext = new APIContext();
     String clientId = "44b5779d729403d3ebf395db11c337c03ef46eb5";
     String clientSecret = "2e14f672e9f097608398a0b637d13023c3594a07";
     String oauthUrl = "https://fx.soopay.net/cberest/v1/oauth/authorize";
     String url = "https://fx.soopay.net/cberest/v1/payments/customs_download?customs_date=20191111";
     apiContext.setClientId(clientId);
     apiContext.setClientSecret(clientSecret);
     apiContext.setOauthUrl(oauthUrl);
     String token = ReqUMF.getOauth(apiContext);
     token = token.split(",")[0].split(":")[1];
     token = token.substring(1, token.length() - 1);
     System.out.println("token1=" + token);
     apiContext.setToken(token);
     apiContext.setCrtPath(crtPath);
     apiContext.setP8Path(p8Path);
     apiContext.setUMFp8Path(UMFp8Path);
     apiContext.setUrl(url);
 
 
     
     String response = get(apiContext);
     System.out.println("-----" + response);
   }
   
   public static String get(APIContext APIcontext) throws Exception {
     String url = APIcontext.getUrl();
     
     String token = APIcontext.getToken();
     
     Map<Object, Object> postMap = new HashMap<>();
     postMap.put("Content-Type", "application/json");
     postMap.put("url", url);
     postMap.put("type", "GET");
     token = "Bearer" + token;
     postMap.put("Authorization", token);
     System.out.println("【umf-rest-api-sdk】" + postMap);
     String reBody = RestUtil.httpRestString(postMap);
     return reBody;
   }
 
   
   public static String postRefund(APIContext APIcontext, String json) throws Exception {
     String url = APIcontext.getUrl();
     String token = APIcontext.getToken();
     String crtPath = APIcontext.getCrtPath();
     String p8Path = APIcontext.getP8Path();
     String UMFp8Path = APIcontext.getUMFp8Path();
     String RSACharset = APIcontext.getRSACharset();
     if (RSACharset == null || "".equals(RSACharset)) {
       RSACharset = "UTF-8";
     }
     RSAUtils rsaUtils = new RSAUtils(crtPath, p8Path, UMFp8Path, RSACharset);
     Map<Object, Object> postMap = new HashMap<>();
     postMap.put("Content-Type", "application/json");
     postMap.put("url", url);
     postMap.put("type", "POST");
     postMap.put("json", json);
     token = "Bearer" + token;
     postMap.put("Authorization", token);
     String signature = createSign(json, 1, "rest");
     postMap.put("Signature", signature);
     System.out.println("【umf-rest-api-sdk】" + postMap);
     String reBody = RestUtil.httpRestString(postMap);
     return reBody;
   }
 
 
   
   public static String Sensitivity(String reqStr, String RSAChaeset) throws Exception {
     byte[] certFile = new byte[20480];
     InputStream in_cert = null;
     String values = reqStr;
     
     try {
       in_cert = new FileInputStream(crtPath);
       
       in_cert.read(certFile);
     } catch (Exception localException) {
       if (in_cert != null)
         try {
           in_cert.close();
         } catch (IOException e) {
           e.printStackTrace();
         }  
       throw new Exception(localException);
     } finally {
       if (in_cert != null) {
         try {
           in_cert.close();
         } catch (IOException e) {
           e.printStackTrace();
         } 
       }
     } 
     X509Certificate cert = genCertificate(certFile);
     
     byte[] keyBytes = cert.getPublicKey().getEncoded();
     X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
     KeyFactory keyFactory = KeyFactory.getInstance("RSA");
     Key publicKey = keyFactory.generatePublic(x509KeySpec);
     
     Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
     cipher.init(1, publicKey);
     values = new String(Base64.encode(cipher.doFinal(values.getBytes(RSAChaeset))));
     return values;
   }
   
   public static X509Certificate genCertificate(byte[] certData) {
     ByteArrayInputStream bais = new ByteArrayInputStream(certData);
     X509Certificate cert = null;
     try {
       CertificateFactory e = CertificateFactory.getInstance("X.509");
       return (X509Certificate)e.generateCertificate(bais);
     } catch (Exception exception) {
       
       return null;
     } 
   }
   public static String createSign(String inStr, int type, String signProject) {
     String returnSign = null;
     InputStream in = null;
     byte[] key = (byte[])null;
     String path = null;
     if (type == 1) {
       path = p8Path;
     } else if (type == 2) {
       path = UMFp8Path;
     } 
     try {
       key = new byte[4096];
       
       in = new FileInputStream(path);
       in.read(key);
       PrivateKey pk = genPrivateKey(key);
       byte[] signData = sign(pk, inStr.getBytes("UTF-8"), signProject);
       returnSign = Base64.encode(signData);
     } catch (Exception localException) {
       localException.printStackTrace();
       try {
         if (in != null)
           in.close(); 
       } catch (IOException localIOException) {
         localIOException.printStackTrace();
       } 
       
       try {
         if (in != null) {
           in.close();
         }
       } catch (IOException localIOException1) {
         localIOException1.printStackTrace();
       } 
     } finally {
 
       
       try {
         
         if (in != null) {
           in.close();
         }
       } catch (IOException localIOException1) {
         localIOException1.printStackTrace();
       } 
     } 
     return returnSign;
   }
   
   public static PrivateKey genPrivateKey(byte[] key) {
     PrivateKey pk = null;
     try {
       PKCS8EncodedKeySpec e = new PKCS8EncodedKeySpec(key);
       KeyFactory kf = KeyFactory.getInstance("RSA");
       return kf.generatePrivate(e);
     } catch (Exception exception) {
       
       return null;
     } 
   } public static byte[] sign(PrivateKey pk, byte[] data, String signProject) {
     byte[] sb = (byte[])null;
     try {
       String sha = null;
       if ("rest".equals(signProject)) {
         sha = "SHA256withRSA";
       } else if ("spay".equals(signProject)) {
         sha = "SHA1withRSA";
       } 
       Signature e = Signature.getInstance(sha);
       e.initSign(pk);
       e.update(data);
       return e.sign();
     } catch (Exception exception) {
       
       return null;
     } 
   }
 }


