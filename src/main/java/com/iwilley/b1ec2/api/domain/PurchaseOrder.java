 package com.iwilley.b1ec2.api.domain;
 
 import com.iwilley.b1ec2.api.internal.mapping.ApiField;
 import com.iwilley.b1ec2.api.internal.mapping.ApiListField;
 import java.util.ArrayList;
 import java.util.Date;
 import java.util.List;
 public class PurchaseOrder
   extends B1EC2Object
 {
   private static final long serialVersionUID = -4990754001555463688L;
   @ApiField("OrderId")
   public int orderId;
   @ApiField("Version")
   public int version;
   @ApiField("OrderNo")
   public String orderNo;
   @ApiField("OrderDate")
   public Date orderDate;
   @ApiField("CreateTime")
   public Date createTime;
   @ApiField("SupplierId")
   public int supplierId;
   @ApiField("Supplier")
   public Supplier supplier;
   @ApiField("WhsId")
   public int whsId;
   @ApiField("Warehouse")
   public Warehouse warehouse;
   @ApiField("OrderStatus")
   public int orderStatus;
   @ApiField("TotalQuantity")
   public int totalQuantity;
   @ApiField("TotalCloseQty")
   public int totalCloseQty;
   @ApiField("ExpectedReceiptDate")
   public Date expectedReceiptDate;
   @ApiField("Memo")
   public String memo;
   @ApiField("LastModifiedTime")
   public Date lastModifiedTime;
   @ApiField("LastModifiedUser")
   public String lastModifiedUser;
   @ApiField("CreateUser")
   public String createUser;
   @ApiListField("Lines")
   @ApiField("PurchaseOrderLine")
   public List<PurchaseOrderLine> lines;
   @ApiField("UserDefinedField1")
   public String userDefinedField1;
   @ApiField("UserDefinedField2")
   public String userDefinedField2;
   @ApiField("UserDefinedField3")
   public String userDefinedField3;
   @ApiField("UserDefinedField4")
   public String userDefinedField4;
   @ApiField("UserDefinedField5")
   public String userDefinedField5;
   @ApiField("UserDefinedField6")
   public Double userDefinedField6;
   @ApiField("UserDefinedField7")
   public Double userDefinedField7;
   @ApiField("UserDefinedField8")
   public Date userDefinedField8;
   
   public String getUserDefinedField1() {
     return this.userDefinedField1;
   }
   
   public void setUserDefinedField1(String userDefinedField1) {
     this.userDefinedField1 = userDefinedField1;
   }
   
   public String getUserDefinedField2() {
     return this.userDefinedField2;
   }
   
   public void setUserDefinedField2(String userDefinedField2) {
     this.userDefinedField2 = userDefinedField2;
   }
   
   public String getUserDefinedField3() {
     return this.userDefinedField3;
   }
   
   public void setUserDefinedField3(String userDefinedField3) {
     this.userDefinedField3 = userDefinedField3;
   }
   
   public String getUserDefinedField4() {
     return this.userDefinedField4;
   }
   
   public void setUserDefinedField4(String userDefinedField4) {
     this.userDefinedField4 = userDefinedField4;
   }
   
   public String getUserDefinedField5() {
     return this.userDefinedField5;
   }
   
   public void setUserDefinedField5(String userDefinedField5) {
     this.userDefinedField5 = userDefinedField5;
   }
   
   public Double getUserDefinedField6() {
     return this.userDefinedField6;
   }
   
   public void setUserDefinedField6(Double userDefinedField6) {
     this.userDefinedField6 = userDefinedField6;
   }
   
   public Double getUserDefinedField7() {
     return this.userDefinedField7;
   }
   
   public void setUserDefinedField7(Double userDefinedField7) {
     this.userDefinedField7 = userDefinedField7;
   }
   
   public Date getUserDefinedField8() {
     return this.userDefinedField8;
   }
   
   public void setUserDefinedField8(Date userDefinedField8) {
     this.userDefinedField8 = userDefinedField8;
   }
 
   
   public int getOrderId() {
     return this.orderId;
   }
   
   public void setOrderId(int orderId) {
     this.orderId = orderId;
   }
   
   public int getVersion() {
     return this.version;
   }
   
   public void setVersion(int version) {
     this.version = version;
   }
   
   public String getOrderNo() {
     return this.orderNo;
   }
   
   public void setOrderNo(String orderNo) {
     this.orderNo = orderNo;
   }
   
   public Date getOrderDate() {
     return this.orderDate;
   }
   
   public void setOrderDate(Date orderDate) {
     this.orderDate = orderDate;
   }
   
   public Date getCreateTime() {
     return this.createTime;
   }
   
   public void setCreateTime(Date createTime) {
     this.createTime = createTime;
   }
   
   public int getSupplierId() {
     return this.supplierId;
   }
   
   public void setSupplierId(int supplierId) {
     this.supplierId = supplierId;
   }
   
   public Supplier getSupplier() {
     return this.supplier;
   }
   
   public void setSupplier(Supplier supplier) {
     this.supplier = supplier;
   }
   
   public int getWhsId() {
     return this.whsId;
   }
   
   public void setWhsId(int whsId) {
     this.whsId = whsId;
   }
   
   public Warehouse getWarehouse() {
     return this.warehouse;
   }
   
   public void setWarehouse(Warehouse warehouse) {
     this.warehouse = warehouse;
   }
   
   public int getOrderStatus() {
     return this.orderStatus;
   }
   
   public void setOrderStatus(int orderStatus) {
     this.orderStatus = orderStatus;
   }
   
   public int getTotalQuantity() {
     return this.totalQuantity;
   }
   
   public void setTotalQuantity(int totalQuantity) {
     this.totalQuantity = totalQuantity;
   }
   
   public int getTotalCloseQty() {
     return this.totalCloseQty;
   }
   
   public void setTotalCloseQty(int totalCloseQty) {
     this.totalCloseQty = totalCloseQty;
   }
   
   public Date getLastModifiedTime() {
     return this.lastModifiedTime;
   }
   
   public void setLastModifiedTime(Date lastModifiedTime) {
     this.lastModifiedTime = lastModifiedTime;
   }
   
   public Date getExpectedReceiptDate() {
     return this.expectedReceiptDate;
   }
   
   public void setExpectedReceiptDate(Date expectedReceiptDate) {
     this.expectedReceiptDate = expectedReceiptDate;
   }
   
   public String getMemo() {
     return this.memo;
   }
   
   public void setMemo(String memo) {
     this.memo = memo;
   }
   
   public String getLastModifiedUser() {
     return this.lastModifiedUser;
   }
   
   public void setLastModifiedUser(String lastModifiedUser) {
     this.lastModifiedUser = lastModifiedUser;
   }
   
   public String getCreateUser() {
     return this.createUser;
   }
   
   public void setCreateUser(String createUser) {
     this.createUser = createUser;
   }
   
   public List<PurchaseOrderLine> getLines() {
     return this.lines;
   }
   
   public void setLines(List<PurchaseOrderLine> lines) {
     this.lines = lines;
   }
 
   
   public PurchaseOrder() {
     this.lines = new ArrayList<>();
   }
 }


