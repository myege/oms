 package com.iwilley.b1ec2.api.response;
 
 import com.iwilley.b1ec2.api.B1EC2Response;
 import com.iwilley.b1ec2.api.domain.AfterSaleService;
 import com.iwilley.b1ec2.api.internal.mapping.ApiField;
 import com.iwilley.b1ec2.api.internal.mapping.ApiListField;
 import java.util.List;
 public class AfterSalesServiceQueryResponse
   extends B1EC2Response
 {
   private static final long serialVersionUID = -7467665837693450654L;
   @ApiField("TotalResults")
   public int totalResults;
   @ApiListField("AfterSaleServices")
   @ApiField("AfterSaleService")
   private List<AfterSaleService> afterSaleServices;
   
   public int getTotalResults() {
     return this.totalResults;
   }
   
   public void setTotalResults(int totalResults) {
     this.totalResults = totalResults;
   }
   
   public List<AfterSaleService> getAfterSaleServices() {
     return this.afterSaleServices;
   }
   
   public void setAfterSaleServices(List<AfterSaleService> afterSaleServices) {
     this.afterSaleServices = afterSaleServices;
   }
 }


