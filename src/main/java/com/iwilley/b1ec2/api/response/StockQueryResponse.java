 package com.iwilley.b1ec2.api.response;
 
 import com.iwilley.b1ec2.api.B1EC2Response;
 import com.iwilley.b1ec2.api.domain.Stock;
 import com.iwilley.b1ec2.api.internal.mapping.ApiField;
 import com.iwilley.b1ec2.api.internal.mapping.ApiListField;
 import java.util.List;
 public class StockQueryResponse
   extends B1EC2Response
 {
   private static final long serialVersionUID = 8029484955811037946L;
   @ApiListField("Stocks")
   @ApiField("Stock")
   private List<Stock> stocks;
   
   public List<Stock> getStocks() {
     return this.stocks;
   }
   public void setStocks(List<Stock> stocks) {
     this.stocks = stocks;
   }
 }


