 package com.what21.util;
 
 import java.io.File;
 import java.io.FileInputStream;
 import java.io.FileNotFoundException;
 import java.io.IOException;
 import java.io.InputStream;
 import java.util.Properties;
 import org.apache.commons.lang.StringUtils;
 
 
 
 public class BfbConfig
 {
   public static final String ConfigFileName = "8f8merchant.properties";
   private String requestUrl;
   private String useropenserviceUrl;
   private String merchantId;
   private String signType;
   private String version;
   private String merchantCertPath;
   private String merchantCertPass;
   private String bfbServerCertPath;
   private String checkFileDir;
   private String batchBackFileDir;
   private String charset;
   private static BfbConfig config;
   private Properties properties;
   
   public static BfbConfig getConfig() {
     if (config == null) {
       config = new BfbConfig();
     }
     return config;
   }
   
   public void loadPropertiesFromPath(String rootPath) {
     File file = new File(String.valueOf(rootPath) + File.separator + 
         "8f8merchant.properties");
     InputStream in = null;
     if (file.exists()) {
       try {
         in = new FileInputStream(file);
         this.properties = new Properties();
         this.properties.load(in);
         loadProperties(this.properties);
         
         if (in != null)
           try {
             in.close();
           } catch (IOException e) {
             e.printStackTrace();
           }  
       } catch (FileNotFoundException e) {
         e.printStackTrace();
         
         if (in != null)
           try {
             in.close();
           } catch (IOException e1) {
             e1.printStackTrace();
           }  
       } catch (IOException e) {
         e.printStackTrace();
         
         if (in != null)
           try {
             in.close();
           } catch (IOException e1) {
             e1.printStackTrace();
           }  
       } finally {
         if (in != null) {
           try {
             in.close();
           } catch (IOException e) {
             e.printStackTrace();
           } 
         }
       } 
     } else {
       System.out.println(String.valueOf(rootPath) + "8f8merchant.properties" + 
           "������,���ز���ʧ��");
     } 
   }
   
   public void loadPropertiesFromSrc() {
     InputStream in = null;
     
     try {
       in = BfbConfig.class.getClassLoader().getResourceAsStream("8f8merchant.properties");
       
       if (in != null) {
         this.properties = new Properties();
         try {
           this.properties.load(in);
         } catch (IOException e) {
           throw e;
         } 
       } 
       loadProperties(this.properties);
       
       if (in != null) {
         try {
           in.close();
         } catch (IOException e) {
           e.printStackTrace();
         } 
       }
     } catch (IOException e) {
       
       e.printStackTrace();
       
       if (in != null) {
         try {
           in.close();
         } catch (IOException e1) {
           e1.printStackTrace();
         } 
       }
     } finally {
       
       if (in != null)
         try {
           in.close();
         } catch (IOException e) {
           e.printStackTrace();
         }  
     } 
   }
   
   public void loadProperties(Properties pro) {
     String value = pro.getProperty("sdk.requestUrl");
     if (!StringUtils.isBlank(value)) {
       this.requestUrl = value.trim();
     }
     
     value = pro.getProperty("sdk.useropenserviceUrl");
     if (!StringUtils.isBlank(value)) {
       this.useropenserviceUrl = value.trim();
     }
     
     value = pro.getProperty("sdk.merchantId");
     if (!StringUtils.isBlank(value)) {
       this.merchantId = value.trim();
     }
     
     value = pro.getProperty("sdk.signType");
     if (!StringUtils.isBlank(value)) {
       this.signType = value.trim();
     }
     
     value = pro.getProperty("sdk.version");
     if (!StringUtils.isBlank(value)) {
       this.version = value.trim();
     }
     
     value = pro.getProperty("sdk.merchantCertPath");
     if (!StringUtils.isBlank(value)) {
       this.merchantCertPath = value.trim();
     }
     
     value = pro.getProperty("sdk.merchantCertPass");
     if (!StringUtils.isBlank(value)) {
       this.merchantCertPass = value.trim();
     }
     
     value = pro.getProperty("sdk.bfbServerCertPath");
     if (!StringUtils.isBlank(value)) {
       this.bfbServerCertPath = value.trim();
     }
     
     value = pro.getProperty("sdk.checkFileDir");
     if (!StringUtils.isBlank(value)) {
       this.checkFileDir = value.trim();
     }
     
     value = pro.getProperty("sdk.batchBackFileDir");
     if (!StringUtils.isBlank(value)) {
       this.batchBackFileDir = value.trim();
     }
   }
   
   public String getRequestUrl() {
     return this.requestUrl;
   }
   
   public void setRequestUrl(String requestUrl) {
     this.requestUrl = requestUrl;
   }
   
   public String getUseropenserviceUrl() {
     return this.useropenserviceUrl;
   }
   
   public void setUseropenserviceUrl(String useropenserviceUrl) {
     this.useropenserviceUrl = useropenserviceUrl;
   }
   
   public String getMerchantId() {
     return this.merchantId;
   }
   
   public void setMerchantId(String merchantId) {
     this.merchantId = merchantId;
   }
   
   public String getSignType() {
     return this.signType;
   }
   
   public void setSignType(String signType) {
     this.signType = signType;
   }
   
   public String getVersion() {
     return this.version;
   }
   
   public void setVersion(String version) {
     this.version = version;
   }
   
   public String getMerchantCertPath() {
     return this.merchantCertPath;
   }
   
   public void setMerchantCertPath(String merchantCertPath) {
     this.merchantCertPath = merchantCertPath;
   }
   
   public String getMerchantCertPass() {
     return this.merchantCertPass;
   }
   
   public void setMerchantCertPass(String merchantCertPass) {
     this.merchantCertPass = merchantCertPass;
   }
   
   public String getCharset() {
     return this.charset;
   }
   
   public void setCharset(String charset) {
     this.charset = charset;
   }
   
   public String getCheckFileDir() {
     return this.checkFileDir;
   }
   
   public void setCheckFileDir(String checkFileDir) {
     this.checkFileDir = checkFileDir;
   }
   
   public String getBatchBackFileDir() {
     return this.batchBackFileDir;
   }
   
   public void setBatchBackFileDir(String batchBackFileDir) {
     this.batchBackFileDir = batchBackFileDir;
   }
   
   public Properties getProperties() {
     return this.properties;
   }
   
   public void setProperties(Properties properties) {
     this.properties = properties;
   }
   
   public String getBfbServerCertPath() {
     return this.bfbServerCertPath;
   }
   
   public void setBfbServerCertPath(String bfbServerCertPath) {
     this.bfbServerCertPath = bfbServerCertPath;
   }
   
   public static void main(String[] args) {}
 }


