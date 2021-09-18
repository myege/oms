 package com.iwilley.b1ec2.api.response;
 
 import com.iwilley.b1ec2.api.B1EC2Response;
 import com.iwilley.b1ec2.api.domain.Shop;
 import com.iwilley.b1ec2.api.internal.mapping.ApiField;
 import com.iwilley.b1ec2.api.internal.mapping.ApiListField;
 import java.util.List;
 
 
 public class ShopQueryResponse
   extends B1EC2Response
 {
   private static final long serialVersionUID = -3657766846272461800L;
   @ApiListField("Shops")
   @ApiField("Shop")
   private List<Shop> shops;
   
   public List<Shop> getShops() {
     return this.shops;
   }
   
   public void setShops(List<Shop> shops) {
     this.shops = shops;
   }
 }


