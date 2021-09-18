 package com.iwilley.b1ec2.api.response;
 
 import com.iwilley.b1ec2.api.B1EC2Response;
 import com.iwilley.b1ec2.api.domain.ItemInfo;
 import com.iwilley.b1ec2.api.internal.mapping.ApiField;
 import com.iwilley.b1ec2.api.internal.mapping.ApiListField;
 import java.util.List;
 public class ItemInfoQueryResponse
   extends B1EC2Response
 {
   private static final long serialVersionUID = 108820312694268821L;
   @ApiField("TotalResults")
   public int totalResults;
   @ApiListField("ItemInfos")
   @ApiField("ItemInfo")
   private List<ItemInfo> itemInfos;
   
   public int getTotalResults() {
     return this.totalResults;
   }
   
   public void setTotalResults(int totalResults) {
     this.totalResults = totalResults;
   }
   
   public List<ItemInfo> getItemInfos() {
     return this.itemInfos;
   }
   
   public void setItemInfos(List<ItemInfo> itemInfos) {
     this.itemInfos = itemInfos;
   }
 }


