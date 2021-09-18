 package com.iwilley.b1ec2.api;
 
 import com.iwilley.b1ec2.api.internal.util.B1EC2HashMap;
 import com.iwilley.b1ec2.api.internal.util.Base64;
 import com.iwilley.b1ec2.api.internal.util.StringUtils;
 import com.iwilley.b1ec2.api.internal.util.WebUtils;
 import com.iwilley.b1ec2.api.parser.B1EC2JsonParser;
 import java.io.IOException;
 import java.util.Map;
 
 
 public class B1EC2Client
 {
   private static final String COMPANY = "Company";
   private static final String LOGIN_NAME = "LoginName";
   private static final String PASSWORD = "Password";
   private static final String METHOD = "Method";
   private static final String VERSION = "Version";
   private int connectTimeout = 3000;
   private int readTimeout = 15000;
   
   private String url;
   private String company;
   private String loginName;
   private String password;
   private String proxyHostName = null;
   private int proxyPort;
   
   public B1EC2Client(String url, String company, String loginName, String password) {
     this.url = url;
     this.company = company;
     this.loginName = loginName;
     this.password = password;
   }
   
   public B1EC2Client(String url, String company, String loginName, String password, String proxyHostName, int proxyPort) {
     this.url = url;
     this.company = company;
     this.loginName = loginName;
     this.password = password;
     this.proxyHostName = proxyHostName;
     this.proxyPort = proxyPort;
   }
   
   public <T extends B1EC2Response> T execute(B1EC2Request<T> request) throws ApiException {
     String body = doPost(request);
     if (StringUtils.isEmpty(body)) {
       return null;
     }
     
     B1EC2JsonParser b1EC2JsonParser = new B1EC2JsonParser(request.getResponseClass());
     B1EC2Response b1EC2Response = b1EC2JsonParser.parse(body);
     b1EC2Response.setBody(body);
     return (T)b1EC2Response;
   }
   
   private <T extends B1EC2Response> String doPost(B1EC2Request<T> request) throws ApiException {
     try {
       B1EC2HashMap headerMap = new B1EC2HashMap();
       headerMap.put("Company", Base64.encodeToString(this.company.getBytes("UTF-8"), false));
       headerMap.put("LoginName", Base64.encodeToString(this.loginName.getBytes("UTF-8"), false));
       headerMap.put("Password", Base64.encodeToString(this.password.getBytes("UTF-8"), false));
       headerMap.put("Method", request.getApiMethodName());
       headerMap.put("Version", "1.0");
       
       B1EC2HashMap parameters = new B1EC2HashMap(request.GetParameters());
       return WebUtils.doPost(this.url, this.proxyHostName, this.proxyPort, (Map)parameters, "UTF-8", this.connectTimeout, this.readTimeout, (Map)headerMap);
     } catch (IOException e) {
       throw new ApiException(e);
     } 
   }
 }


