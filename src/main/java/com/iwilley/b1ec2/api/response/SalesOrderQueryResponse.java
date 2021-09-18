 package com.iwilley.b1ec2.api.response;
 
 import com.iwilley.b1ec2.api.B1EC2Response;
 import com.iwilley.b1ec2.api.domain.SalesOrder;
 import com.iwilley.b1ec2.api.internal.mapping.ApiField;
 import com.iwilley.b1ec2.api.internal.mapping.ApiListField;
 import java.util.List;
 public class SalesOrderQueryResponse
   extends B1EC2Response
 {
   private static final long serialVersionUID = -1265532223384793579L;
   @ApiField("TotalResults")
   public int totalResults;
   @ApiListField("SalesOrders")
   @ApiField("SalesOrder")
   private List<SalesOrder> salesOrders;
   
   public int getTotalResults() {
     return this.totalResults;
   }
   
   public void setTotalResults(int totalResults) {
     this.totalResults = totalResults;
   }
   
   public List<SalesOrder> getSalesOrders() {
     return this.salesOrders;
   }
   
   public void setSalesOrders(List<SalesOrder> salesOrders) {
     this.salesOrders = salesOrders;
   }
 }


