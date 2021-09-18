 package com.what21.util;
 
 import com.bfb.cap.sdk.CAP12CertTool;
 import com.bfb.cap.sdk.HexStringByte;
 import com.bfb.cap.sdk.SecureUtil;
 import com.bfb.cap.sdk.SecurityException;
 import java.io.ByteArrayInputStream;
 import java.io.File;
 import java.io.FileInputStream;
 import java.io.FileNotFoundException;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.UnsupportedEncodingException;
 import java.security.InvalidKeyException;
 import java.security.NoSuchAlgorithmException;
 import java.security.NoSuchProviderException;
 import java.security.PrivateKey;
 import java.security.Provider;
 import java.security.PublicKey;
 import java.security.Signature;
 import java.security.SignatureException;
 import java.security.cert.CertificateException;
 import java.security.cert.CertificateExpiredException;
 import java.security.cert.CertificateFactory;
 import java.security.cert.CertificateNotYetValidException;
 import java.security.cert.X509Certificate;
 import java.util.HashMap;
 import java.util.Iterator;
 import java.util.Map;
 import java.util.TreeMap;
 import javax.crypto.Cipher;
 import org.apache.commons.lang.StringUtils;
 import org.bouncycastle.jce.provider.BouncyCastleProvider;
 import org.bouncycastle.util.encoders.Base64;
 public class RSASignUtil
 {
   private String certFilePath = null;
   private String password = null;
   private String bfbServerCertPath = null;
   
   public RSASignUtil(String certFilePath, String password) {
     this.certFilePath = certFilePath;
     this.password = password;
   }
 
   
   public RSASignUtil(String bfbServerCertPath) {
     this.bfbServerCertPath = bfbServerCertPath;
   }
   
   public RSASignUtil() {}
   
   public String sign(String indata, String encoding) throws SecurityException {
     String singData = null;
     
     if (isEmpty(encoding)) {
       encoding = "GBK";
     }
     
     try {
       CAP12CertTool c = new CAP12CertTool(this.certFilePath, this.password);
       
       byte[] si = null;
       try {
         si = c.getSignData(indata.getBytes("UTF-8"));
       } catch (UnsupportedEncodingException e) {
         
         e.printStackTrace();
       } 
       
       singData = HexStringByte.byteToHex(si);
     
     }
     catch (FileNotFoundException e) {
       
       e.printStackTrace();
     } catch (SecurityException e) {
       
       e.printStackTrace();
     } 
     
     return singData.toLowerCase();
   }
   
   public boolean verifyBySoft(String strSignData, String stOriData, String encoding) {
     boolean res = false;
     try {
       byte[] signData = SecureUtil.base64Decode(strSignData.getBytes(encoding));
       byte[] oridata = SecureUtil.sha1X16(stOriData, encoding);
       X509Certificate serverCert = getCertfromPath(this.bfbServerCertPath);
       PublicKey publicKey = serverCert.getPublicKey();
       Signature st = Signature.getInstance("SHA1withRSA");
       st.initVerify(publicKey);
       st.update(oridata);
       res = st.verify(signData);
     } catch (InvalidKeyException e) {
       
       e.printStackTrace();
     } catch (NoSuchAlgorithmException e) {
       
       e.printStackTrace();
     } catch (SignatureException e) {
       
       e.printStackTrace();
     } catch (SecurityException e) {
       
       e.printStackTrace();
     } catch (UnsupportedEncodingException e) {
       
       e.printStackTrace();
     } catch (IOException e) {
       
       e.printStackTrace();
     } 
     return res;
   }
 
 
 
   
   public X509Certificate getCertfromPath(String crt_path) throws SecurityException {
     X509Certificate cert = null;
     InputStream inStream = null;
     try {
       inStream = new FileInputStream(new File(crt_path));
       CertificateFactory cf = CertificateFactory.getInstance("X.509");
       cert = (X509Certificate)cf.generateCertificate(inStream);
     } catch (CertificateException e) {
       throw new SecurityException(e.getMessage());
     } catch (FileNotFoundException e) {
       throw new SecurityException(e.getMessage());
     } 
     return cert;
   }
 
   
   public static PublicKey getPublicKeyfromPath(String svrCertpath) throws SecurityException {
     X509Certificate cert = null;
     InputStream inStream = null;
     try {
       inStream = new FileInputStream(new File(svrCertpath));
       CertificateFactory cf = CertificateFactory.getInstance("X.509");
       cert = (X509Certificate)cf.generateCertificate(inStream);
     }
     catch (CertificateException e) {
       throw new SecurityException(e.getMessage());
     } catch (FileNotFoundException e) {
       throw new SecurityException(e.getMessage());
     } 
     return cert.getPublicKey();
   }
 
   
   public boolean verifyCert(X509Certificate userCert, X509Certificate rootCert) throws SecurityException {
     boolean res = false;
     try {
       PublicKey rootKey = rootCert.getPublicKey();
       userCert.checkValidity();
       userCert.verify(rootKey);
       res = true;
       if (!userCert.getIssuerDN().equals(rootCert.getSubjectDN()))
         res = false; 
     } catch (CertificateExpiredException e) {
       throw new SecurityException(e.getMessage());
     } catch (CertificateNotYetValidException e) {
       throw new SecurityException(e.getMessage());
     } catch (InvalidKeyException e) {
       throw new SecurityException(e.getMessage());
     } catch (CertificateException e) {
       throw new SecurityException(e.getMessage());
     } catch (NoSuchAlgorithmException e) {
       throw new SecurityException(e.getMessage());
     } catch (NoSuchProviderException e) {
       throw new SecurityException(e.getMessage());
     } catch (SignatureException e) {
       throw new SecurityException(e.getMessage());
     } 
     
     return res;
   }
 
   
   private X509Certificate getCertFromHexString(String hexCert) throws SecurityException {
     ByteArrayInputStream bIn = null;
     X509Certificate certobj = null;
     try {
       byte[] cert = HexStringByte.hexToByte(hexCert.getBytes());
       CertificateFactory fact = CertificateFactory.getInstance("X.509");
       bIn = new ByteArrayInputStream(cert);
       certobj = (X509Certificate)fact.generateCertificate(bIn);
       bIn.close();
       bIn = null;
     } catch (CertificateException e) {
       e.printStackTrace();
     } catch (IOException e) {
       e.printStackTrace();
     } finally {
       try {
         if (bIn != null)
           bIn.close(); 
       } catch (IOException iOException) {}
     } 
     
     return certobj;
   }
   
   public static byte[] checkPEM(byte[] paramArrayOfByte) {
     String str1 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789/+= \r\n-";
     for (int i = 0; i < paramArrayOfByte.length; i++) {
       if (str1.indexOf(paramArrayOfByte[i]) == -1)
         return null; 
     }  StringBuffer localStringBuffer = new StringBuffer(
         paramArrayOfByte.length);
     String str2 = new String(paramArrayOfByte);
     for (int j = 0; j < str2.length(); j++) {
       if (str2.charAt(j) != ' ' && str2.charAt(j) != '\r' && 
         str2.charAt(j) != '\n')
         localStringBuffer.append(str2.charAt(j)); 
     }  return localStringBuffer.toString().getBytes();
   }
   
   public String getFormValue(String respMsg, String name) {
     String[] resArr = StringUtils.split(respMsg, "&");
     
     Map<Object, Object> resMap = new HashMap<>();
     for (int i = 0; i < resArr.length; i++) {
       String data = resArr[i];
       int index = StringUtils.indexOf(data, '=');
       String nm = StringUtils.substring(data, 0, index);
       String val = StringUtils.substring(data, index + 1);
       resMap.put(nm, val);
     } 
     
     return ((String)resMap.get(name) == null) ? "" : (String)resMap
       .get(name);
   }
   
   public String getValue(String respMsg, String name) {
     String[] resArr = StringUtils.split(respMsg, "&");
     
     Map<Object, Object> resMap = new HashMap<>();
     for (int i = 0; i < resArr.length; i++) {
       String data = resArr[i];
       int index = StringUtils.indexOf(data, '=');
       String nm = StringUtils.substring(data, 0, index);
       String val = StringUtils.substring(data, index + 1);
       resMap.put(nm, val);
     } 
     return ((String)resMap.get(name) == null) ? "" : (String)resMap
       .get(name);
   }
   
   public Map coverString2Map(String respMsg) {
     String[] resArr = StringUtils.split(respMsg, "&");
     
     Map<Object, Object> resMap = new HashMap<>();
     for (int i = 0; i < resArr.length; i++) {
       String data = resArr[i];
       int index = StringUtils.indexOf(data, '=');
       String nm = StringUtils.substring(data, 0, index);
       String val = StringUtils.substring(data, index + 1);
       resMap.put(nm, val);
     } 
     return resMap;
   }
   
   public static String coverMap2String(Map<String, String> data) {
     TreeMap<Object, Object> tree = new TreeMap<>();
     Iterator<Map.Entry<String,String>> it = data.entrySet().iterator();
     while (it.hasNext()) {
       Map.Entry en = it.next();
       String value = "";
       if (!"signature".equals(((String)en.getKey()).trim())) {
         tree.put(en.getKey(), en.getValue());
       }
     } 
			  Iterator<Map.Entry<Object,Object>> it1=null;
     it1 = tree.entrySet().iterator();
     StringBuffer sf = new StringBuffer();
     while (it1.hasNext()) {
       Map.Entry en = it1.next();
       sf.append((String)en.getKey() + 
           "=" + (String)en.getValue() + "&");
     } 
     
     return sf.substring(0, sf.length() - 1);
   }
   
   public static String coverMapStringForCFM(Map<String, String> data) {
     TreeMap<Object, Object> tree = new TreeMap<>();
     Iterator<Map.Entry<String,String>> it = data.entrySet().iterator();
     while (it.hasNext()) {
       Map.Entry en = it.next();
       String value = "";
       if (!"signature".equals(((String)en.getKey()).trim())) {
         tree.put(en.getKey(), en.getValue());
       }
     } 
			  Iterator<Map.Entry<Object,Object>> it1=null;
     it1 = tree.entrySet().iterator();
     StringBuffer sf = new StringBuffer();
     while (it1.hasNext()) {
       Map.Entry en = it1.next();
       sf.append((String)en.getKey() + 
           "=" + (String)en.getValue() + "&");
     } 
     
     return sf.substring(0, sf.length() - 1);
   }
 
   
   public static String encryptData(String dataString, String encoding, String svrCertPath) {
     byte[] data = null;
     try {
       data = encryptedPin(getPublicKeyfromPath(svrCertPath), 
           dataString.getBytes(encoding));
       return new String(base64Encode(data), encoding);
     } catch (Exception e) {
       e.printStackTrace();
       
       return "";
     } 
   }
   
   public static byte[] encryptedPin(PublicKey publicKey, byte[] plainPin) throws Exception {
     try {
       Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding", 
           (Provider)new BouncyCastleProvider());
       
       cipher.init(1, publicKey);
       int blockSize = cipher.getBlockSize();
       int outputSize = cipher.getOutputSize(plainPin.length);
       int leavedSize = plainPin.length % blockSize;
       int blocksSize = (leavedSize != 0) ? (plainPin.length / blockSize + 1) : (
         plainPin.length / blockSize);
       
       byte[] raw = new byte[outputSize * blocksSize];
       int i = 0;
       while (plainPin.length - i * blockSize > 0) {
         if (plainPin.length - i * blockSize > blockSize) {
           cipher.doFinal(plainPin, i * blockSize, blockSize, raw, i * 
               outputSize);
         } else {
           cipher.doFinal(plainPin, i * blockSize, plainPin.length - i * 
               blockSize, raw, i * outputSize);
         } 
         
         i++;
       } 
       return raw;
     } catch (Exception e) {
       throw new Exception(e.getMessage());
     } 
   }
   
   public static byte[] base64Encode(byte[] inputByte) throws IOException {
     return Base64.encode(inputByte);
   }
   
   public static boolean isEmpty(String s) {
     return !(s != null && !"".equals(s.trim()));
   }
   
   public String capSign2(String stringData, String encoding) {
     if (isEmpty(encoding)) {
       encoding = "GBK";
     }
     
     System.out.println(
         "报文签名之前的字符串(不含signature域)=[" + stringData + 
         "]");
     
     byte[] byteSign = null;
     String stringSign = null;
     try {
       byte[] signDigest = SecureUtil.sha1X16(stringData, encoding);
       System.out.println("SHA1->16进制转换后的摘要=[" + 
           new String(signDigest) + "]");
       
       CAP12CertTool c = new CAP12CertTool(this.certFilePath, 
           this.password);
       byte[] signRes = SecureUtil.signBySoft(c.getPrivateKey(), 
           signDigest);
       byteSign = SecureUtil.base64Encode(signRes);
       
       stringSign = new String(byteSign);
       System.out.println("报文签名之后的字符串=[" + 
           stringSign + "]");
 
 
       
       System.out.println("signature:" + stringSign);
     }
     catch (Exception e) {
       e.printStackTrace();
     } 
     return stringSign;
   }
   
   public String capSign(String stringData, String encoding) {
     if (isEmpty(encoding)) {
       encoding = "GBK";
     }
     
     System.out.println(
         "报文签名之前的字符串(不含signature域)=[" + stringData + 
         "]");
     
     byte[] byteSign = null;
     String stringSign = null;
     
     try {
       byte[] signDigest = stringData.getBytes();
       
       System.out.println("SHA1->16进制转换后的摘要=[" + 
           new String(signDigest) + "]");
       
       CAP12CertTool c = new CAP12CertTool(this.certFilePath, 
           this.password);
       byte[] signRes = SecureUtil.signBySoft(c.getPrivateKey(), 
           signDigest);
 
 
 
       
       StringBuilder hex = new StringBuilder("");
       for (int i = 0; i < signRes.length; i++) {
         int v = signRes[i] & 0xFF;
         String hv = Integer.toHexString(v);
         if (hv.length() < 2) {
           hex.append(0);
         }
         hex.append(hv);
       } 
 
       
       stringSign = hex.toString().toLowerCase();
       System.out.println("报文签名之后的字符串=[" + 
           stringSign + "]");
 
 
       
       System.out.println("signature:" + stringSign);
     }
     catch (Exception e) {
       e.printStackTrace();
     } 
     return stringSign;
   }
 
 
   
   public String capSignForCFM(String stringData, String encoding, PrivateKey privateKey) {
     if (isEmpty(encoding)) {
       encoding = "GBK";
     }
     
     System.out.println(
         "报文签名之前的字符串(不含signature域)=[" + stringData + 
         "]");
     
     byte[] byteSign = null;
     String stringSign = null;
     
     try {
       byte[] signDigest = stringData.getBytes();
       System.out.println("SHA1->16进制转换后的摘要=[" + 
           new String(signDigest) + "]");
 
 
       
       byte[] signRes = SecureUtil.signBySoft(privateKey, 
           signDigest);
 
       
       StringBuilder hex = new StringBuilder("");
       for (int i = 0; i < signRes.length; i++) {
         int v = signRes[i] & 0xFF;
         String hv = Integer.toHexString(v);
         if (hv.length() < 2) {
           hex.append(0);
         }
         hex.append(hv);
       } 
 
       
       stringSign = hex.toString().toLowerCase();
       System.out.println("报文签名之后的字符串=[" + 
           stringSign + "]");
 
 
       
       System.out.println("signature:" + stringSign);
     }
     catch (Exception e) {
       e.printStackTrace();
     } 
     return stringSign;
   }
 }


