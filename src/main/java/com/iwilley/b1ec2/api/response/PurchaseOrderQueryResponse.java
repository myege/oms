 package com.iwilley.b1ec2.api.response;
 
 import com.iwilley.b1ec2.api.B1EC2Response;
 import com.iwilley.b1ec2.api.domain.PurchaseOrder;
 import com.iwilley.b1ec2.api.internal.mapping.ApiField;
 import com.iwilley.b1ec2.api.internal.mapping.ApiListField;
 import java.util.List;
 public class PurchaseOrderQueryResponse
   extends B1EC2Response
 {
   private static final long serialVersionUID = -1265532223384793579L;
   @ApiField("TotalResults")
   public int totalResults;
   @ApiListField("PurchaseOrders")
   @ApiField("PurchaseOrder")
   private List<PurchaseOrder> purchaseOrders;
   
   public int getTotalResults() {
     return this.totalResults;
   }
   
   public void setTotalResults(int totalResults) {
     this.totalResults = totalResults;
   }
   
   public List<PurchaseOrder> getPurchaseOrders() {
     return this.purchaseOrders;
   }
   
   public void setPurchaseOrders(List<PurchaseOrder> purchaseOrders) {
     this.purchaseOrders = purchaseOrders;
   }
 }


