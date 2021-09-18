 package com.iwilley.b1ec2.api.response;
 
 import com.iwilley.b1ec2.api.B1EC2Response;
 import com.iwilley.b1ec2.api.domain.StockTransfer;
 import com.iwilley.b1ec2.api.internal.mapping.ApiField;
 import com.iwilley.b1ec2.api.internal.mapping.ApiListField;
 import java.util.List;
 public class StockTransferQueryResponse
   extends B1EC2Response
 {
   private static final long serialVersionUID = -8235625657666488303L;
   @ApiField("TotalResults")
   public int totalResults;
   @ApiListField("StockTransfers")
   @ApiField("StockTransfer")
   private List<StockTransfer> stockTransfers;
   
   public int getTotalResults() {
     return this.totalResults;
   }
   
   public void setTotalResults(int totalResults) {
     this.totalResults = totalResults;
   }
   
   public List<StockTransfer> getStockTransfers() {
     return this.stockTransfers;
   }
   
   public void setStockTransfers(List<StockTransfer> stockTransfers) {
     this.stockTransfers = stockTransfers;
   }
 }


