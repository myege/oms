 package com.iwilley.b1ec2.api.response;
 
 import com.iwilley.b1ec2.api.B1EC2Response;
 import com.iwilley.b1ec2.api.domain.PrintTask;
 import com.iwilley.b1ec2.api.internal.mapping.ApiField;
 import com.iwilley.b1ec2.api.internal.mapping.ApiListField;
 import java.util.List;
 
 
 public class PrintTaskQueryResponse
   extends B1EC2Response
 {
   private static final long serialVersionUID = 5323766697528631343L;
   @ApiListField("PrintTasks")
   @ApiField("PrintTask")
   private List<PrintTask> printTasks;
   
   public List<PrintTask> getPrintTasks() {
     return this.printTasks;
   }
   
   public void setPrintTasks(List<PrintTask> printTasks) {
     this.printTasks = printTasks;
   }
 }


