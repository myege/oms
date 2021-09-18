 package com.iwilley.b1ec2.api.domain;
 public class ShopOrderCreateLine
   extends B1EC2Object
 {
   private static final long serialVersionUID = 5031294517950609621L;
   public String shopLineNo;
   public String outerId;
   public int quantity;
   public double price;
   public String lineUdf1;
   public String lineUdf2;
   public String itemName;
   public String skuName;
   public double lineTotal;
   public double lineCustomTax;
   
   public String getShopLineNo() {
     return this.shopLineNo;
   }
   
   public void setShopLineNo(String shopLineNo) {
     this.shopLineNo = shopLineNo;
   }
   
   public String getOuterId() {
     return this.outerId;
   }
   
   public void setOuterId(String outerId) {
     this.outerId = outerId;
   }
   
   public int getQuantity() {
     return this.quantity;
   }
   
   public void setQuantity(int quantity) {
     this.quantity = quantity;
   }
   
   public double getPrice() {
     return this.price;
   }
   
   public void setPrice(double price) {
     this.price = price;
   }
   
   public String getLineUdf1() {
     return this.lineUdf1;
   }
   
   public void setLineUdf1(String lineUdf1) {
     this.lineUdf1 = lineUdf1;
   }
   
   public String getLineUdf2() {
     return this.lineUdf2;
   }
   
   public void setLineUdf2(String lineUdf2) {
     this.lineUdf2 = lineUdf2;
   }
   public String getitemName() {
     return this.itemName;
   }
   public void setitemName(String itemName) {
     this.itemName = itemName;
   }
   
   public String getskuName() {
     return this.skuName;
   }
   public void setskuName(String skuName) {
     this.skuName = skuName;
   }
   public double getLineTotal() {
     return this.lineTotal;
   }
   
   public void setLineTotal(double lineTotal) {
     this.lineTotal = lineTotal;
   }
   
   public double getLineCustomTax() {
     return this.lineCustomTax;
   }
   
   public void setLineCustomTax(double lineCustomTax) {
     this.lineCustomTax = lineCustomTax;
   }
 }


