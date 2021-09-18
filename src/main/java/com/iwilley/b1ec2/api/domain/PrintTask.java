 package com.iwilley.b1ec2.api.domain;
 
 import com.iwilley.b1ec2.api.internal.mapping.ApiField;
 import java.util.Date;
 public class PrintTask
   extends B1EC2Object
 {
   private static final long serialVersionUID = 5545089335432769507L;
   @ApiField("TaskId")
   public int taskId;
   @ApiField("TaskStatus")
   public int taskStatus;
   @ApiField("PrintType")
   public int printType;
   @ApiField("ExpressId")
   public int expressId;
   @ApiField("FileName")
   public String fileName;
   @ApiField("FileSize")
   public int fileSize;
   @ApiField("Memo")
   public String memo;
   @ApiField("Creator")
   public String Creator;
   @ApiField("CreatedTime")
   public Date CreatedTime;
   @ApiField("LastModifiedUser")
   public String lastModifiedUser;
   @ApiField("LastModifiedTime")
   public Date lastModifiedTime;
   
   public int getTaskId() {
     return this.taskId;
   }
   
   public void setTaskId(int taskId) {
     this.taskId = taskId;
   }
   
   public int getTaskStatus() {
     return this.taskStatus;
   }
   
   public void setTaskStatus(int taskStatus) {
     this.taskStatus = taskStatus;
   }
   
   public int getPrintType() {
     return this.printType;
   }
   
   public void setPrintType(int printType) {
     this.printType = printType;
   }
   
   public int getExpressId() {
     return this.expressId;
   }
   
   public void setExpressId(int expressId) {
     this.expressId = expressId;
   }
   
   public String getFileName() {
     return this.fileName;
   }
   
   public void setFileName(String fileName) {
     this.fileName = fileName;
   }
   
   public int getFileSize() {
     return this.fileSize;
   }
   
   public void setFileSize(int fileSize) {
     this.fileSize = fileSize;
   }
   
   public String getMemo() {
     return this.memo;
   }
   
   public void setMemo(String memo) {
     this.memo = memo;
   }
   
   public String getCreator() {
     return this.Creator;
   }
   
   public void setCreator(String creator) {
     this.Creator = creator;
   }
   
   public Date getCreatedTime() {
     return this.CreatedTime;
   }
   
   public void setCreatedTime(Date createdTime) {
     this.CreatedTime = createdTime;
   }
   
   public String getLastModifiedUser() {
     return this.lastModifiedUser;
   }
   
   public void setLastModifiedUser(String lastModifiedUser) {
     this.lastModifiedUser = lastModifiedUser;
   }
   
   public Date getLastModifiedTime() {
     return this.lastModifiedTime;
   }
   
   public void setLastModifiedTime(Date lastModifiedTime) {
     this.lastModifiedTime = lastModifiedTime;
   }
 }


