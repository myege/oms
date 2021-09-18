 package com.iwilley.b1ec2.api;
 
 import com.iwilley.b1ec2.api.internal.mapping.ApiField;
 import java.io.Serializable;
 public abstract class B1EC2Response
   implements Serializable
 {
   private static final long serialVersionUID = -7409053753353171766L;
   @ApiField("ErrorCode")
   private int errorCode;
   @ApiField("ErrorMsg")
   private String errorMsg;
   private String body;
   
   public int getErrorCode() {
     return this.errorCode;
   }
   
   public void setErrorCode(int errorCode) {
     this.errorCode = errorCode;
   }
   
   public String getErrorMsg() {
     return this.errorMsg;
   }
   
   public void setErrorMsg(String errorMsg) {
     this.errorMsg = errorMsg;
   }
   
   public String getBody() {
     return this.body;
   }
   
   public void setBody(String body) {
     this.body = body;
   }
 }


