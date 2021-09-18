 package com.iwilley.b1ec2.api.domain;
 
 import com.iwilley.b1ec2.api.internal.mapping.ApiField;
 import com.iwilley.b1ec2.api.internal.mapping.ApiListField;
 import java.util.ArrayList;
 import java.util.List;
 public class Warehouse
   extends B1EC2Object
 {
   private static final long serialVersionUID = -3713915138676331918L;
   @ApiField("WhsId")
   public int whsId;
   @ApiField("WhsCode")
   public String whsCode;
   @ApiField("WhsName")
   public String whsName;
   @ApiField("PickId")
   public Integer pickId;
   @ApiListField("WhsAreas")
   @ApiField("WhsArea")
   public List<WhsArea> whsAreas = new ArrayList<>();
 
   
   public int getWhsId() {
     return this.whsId;
   }
   
   public void setWhsId(int whsId) {
     this.whsId = whsId;
   }
   
   public String getWhsCode() {
     return this.whsCode;
   }
   
   public void setWhsCode(String whsCode) {
     this.whsCode = whsCode;
   }
   
   public String getWhsName() {
     return this.whsName;
   }
   
   public void setWhsName(String whsName) {
     this.whsName = whsName;
   }
   
   public List<WhsArea> getWhsAreas() {
     return this.whsAreas;
   }
   
   public void setWhsAreas(List<WhsArea> whsAreas) {
     this.whsAreas = whsAreas;
   }
   
   public Integer getPickId() {
     return this.pickId;
   }
   
   public void setPickId(Integer pickId) {
     this.pickId = pickId;
   }
 }


