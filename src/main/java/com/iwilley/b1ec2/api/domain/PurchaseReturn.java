 package com.iwilley.b1ec2.api.domain;
 
 import com.iwilley.b1ec2.api.internal.mapping.ApiField;
 import com.iwilley.b1ec2.api.internal.mapping.ApiListField;
 import java.util.ArrayList;
 import java.util.Date;
 import java.util.List;
 public class PurchaseReturn
   extends B1EC2Object
 {
   private static final long serialVersionUID = 2282158723466756631L;
   @ApiField("ReturnId")
   public int returnId;
   @ApiField("PurchaseReturnNo")
   public String purchaseReturnNo;
   @ApiField("SupplierId")
   public int supplierId;
   @ApiField("Supplier")
   public Supplier supplier;
   @ApiField("WhsId")
   public int whsId;
   @ApiField("Warehouse")
   public Warehouse warehouse;
   @ApiField("PurchaseReturnStatus")
   public int purchaseReturnStatus;
   @ApiField("PostDate")
   public Date postDate;
   @ApiField("ReopenPurchaseOrder")
   public Boolean reopenPurchaseOrder;
   @ApiField("CreateUser")
   public String createUser;
   @ApiField("CreateTime")
   public Date createTime;
   @ApiField("LastModifiedTime")
   public Date lastModifiedTime;
   @ApiField("LastModifiedUser")
   public String lastModifiedUser;
   @ApiField("Memo")
   public String memo;
   @ApiField("OpenTotal")
   public double openTotal;
   @ApiField("ReturnTotal")
   public double returnTotal;
   @ApiListField("Lines")
   @ApiField("PurchaseReturnLine")
   public List<PurchaseReturnLine> lines;
   
   public int getReturnId() {
     return this.returnId;
   }
   
   public String getPurchaseReturnNo() {
     return this.purchaseReturnNo;
   }
   
   public int getSupplierId() {
     return this.supplierId;
   }
   
   public Supplier getSupplier() {
     return this.supplier;
   }
   
   public int getWhsId() {
     return this.whsId;
   }
   
   public Warehouse getWarehouse() {
     return this.warehouse;
   }
   
   public int getPurchaseReturnStatus() {
     return this.purchaseReturnStatus;
   }
   
   public Date getPostDate() {
     return this.postDate;
   }
   
   public Boolean getReopenPurchaseOrder() {
     return this.reopenPurchaseOrder;
   }
   
   public String getCreateUser() {
     return this.createUser;
   }
   
   public Date getCreateTime() {
     return this.createTime;
   }
   
   public Date getLastModifiedTime() {
     return this.lastModifiedTime;
   }
   
   public String getLastModifiedUser() {
     return this.lastModifiedUser;
   }
   
   public String getMemo() {
     return this.memo;
   }
   
   public double getOpenTotal() {
     return this.openTotal;
   }
   
   public double getReturnTotal() {
     return this.returnTotal;
   }
   
   public List<PurchaseReturnLine> getLines() {
     return this.lines;
   }
   
   public void setReturnId(int returnId) {
     this.returnId = returnId;
   }
   
   public void setPurchaseReturnNo(String purchaseReturnNo) {
     this.purchaseReturnNo = purchaseReturnNo;
   }
   
   public void setSupplierId(int supplierId) {
     this.supplierId = supplierId;
   }
   
   public void setSupplier(Supplier supplier) {
     this.supplier = supplier;
   }
   
   public void setWhsId(int whsId) {
     this.whsId = whsId;
   }
   
   public void setWarehouse(Warehouse warehouse) {
     this.warehouse = warehouse;
   }
   
   public void setPurchaseReturnStatus(int purchaseReturnStatus) {
     this.purchaseReturnStatus = purchaseReturnStatus;
   }
   
   public void setPostDate(Date postDate) {
     this.postDate = postDate;
   }
   
   public void setReopenPurchaseOrder(Boolean reopenPurchaseOrder) {
     this.reopenPurchaseOrder = reopenPurchaseOrder;
   }
   
   public void setCreateUser(String createUser) {
     this.createUser = createUser;
   }
   
   public void setCreateTime(Date createTime) {
     this.createTime = createTime;
   }
   
   public void setLastModifiedTime(Date lastModifiedTime) {
     this.lastModifiedTime = lastModifiedTime;
   }
   
   public void setLastModifiedUser(String lastModifiedUser) {
     this.lastModifiedUser = lastModifiedUser;
   }
   
   public void setMemo(String memo) {
     this.memo = memo;
   }
   
   public void setOpenTotal(double openTotal) {
     this.openTotal = openTotal;
   }
   
   public void setReturnTotal(double returnTotal) {
     this.returnTotal = returnTotal;
   }
   
   public void setLines(List<PurchaseReturnLine> lines) {
     this.lines = lines;
   }
   
   public PurchaseReturn() {
     this.lines = new ArrayList<>();
   }
 }


