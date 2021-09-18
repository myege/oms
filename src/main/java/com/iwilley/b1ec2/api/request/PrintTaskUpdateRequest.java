 package com.iwilley.b1ec2.api.request;
 
 import com.iwilley.b1ec2.api.B1EC2Request;
 import com.iwilley.b1ec2.api.internal.util.B1EC2HashMap;
 import com.iwilley.b1ec2.api.response.PrintTaskUpdateResponse;
 import java.util.Map;
 public class PrintTaskUpdateRequest
   implements B1EC2Request<PrintTaskUpdateResponse>
 {
   public Integer taskId;
   public Integer taskStatus;
   public String memo;
   
   public String getApiMethodName() {
     return "B1EC2.PrintTask.Update";
   }
 
   
   public Map<String, String> GetParameters() {
     B1EC2HashMap parameters = new B1EC2HashMap();
     parameters.put("TaskId", this.taskId);
     parameters.put("TaskStatus", this.taskStatus);
     parameters.put("Memo", this.memo);
     return (Map<String, String>)parameters;
   }
 
   
   public Class<PrintTaskUpdateResponse> getResponseClass() {
     return PrintTaskUpdateResponse.class;
   }
   
   public Integer getTaskId() {
     return this.taskId;
   }
   
   public Integer getTaskStatus() {
     return this.taskStatus;
   }
   
   public String getMemo() {
     return this.memo;
   }
   
   public void setTaskId(Integer taskId) {
     this.taskId = taskId;
   }
   
   public void setTaskStatus(Integer taskStatus) {
     this.taskStatus = taskStatus;
   }
   
   public void setMemo(String memo) {
     this.memo = memo;
   }
 }


