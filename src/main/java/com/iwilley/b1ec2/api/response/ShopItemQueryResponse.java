 package com.iwilley.b1ec2.api.response;
 
 import com.iwilley.b1ec2.api.B1EC2Response;
 import com.iwilley.b1ec2.api.domain.ShopItem;
 import com.iwilley.b1ec2.api.internal.mapping.ApiField;
 import com.iwilley.b1ec2.api.internal.mapping.ApiListField;
 import java.util.List;
 public class ShopItemQueryResponse
   extends B1EC2Response
 {
   private static final long serialVersionUID = -7621957028104042379L;
   @ApiField("TotalResults")
   public int totalResults;
   @ApiListField("ShopItems")
   @ApiField("ShopItem")
   private List<ShopItem> shopItems;
   
   public int getTotalResults() {
     return this.totalResults;
   }
   
   public void setTotalResults(int totalResults) {
     this.totalResults = totalResults;
   }
   
   public List<ShopItem> getShopItems() {
     return this.shopItems;
   }
   
   public void setShopItems(List<ShopItem> shopItems) {
     this.shopItems = shopItems;
   }
 }


