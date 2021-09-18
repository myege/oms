 package com.iwilley.b1ec2.api.domain;
 
 import com.iwilley.b1ec2.api.internal.mapping.ApiField;
 import com.iwilley.b1ec2.api.internal.mapping.ApiListField;
 import java.util.ArrayList;
 import java.util.Date;
 import java.util.List;
 import javax.xml.crypto.Data;
 public class StockTransfer
   extends B1EC2Object
 {
   private static final long serialVersionUID = 1L;
   @ApiField("TransferId")
   public int transferId;
   @ApiField("StockTransferNo")
   public String stockTransferNo;
   @ApiField("StockTransferType")
   public int stockTransferType;
   @ApiField("StockTransferStatus")
   public int stockTransferStatus;
   @ApiField("FromWhsId")
   public int fromWhsId;
   @ApiField("Warehouse")
   public Warehouse warehouseFrom;
   @ApiField("FromWhsAreaId")
   public int fromWhsAreaId;
   @ApiField("ToWhsId")
   public int toWhsId;
   @ApiField("Warehouse")
   public Warehouse warehouseTo;
   @ApiField("ToWhsAreaId")
   public int toWhsAreaId;
   @ApiField("PostDate")
   public Data postDate;
   @ApiField("CreateUser")
   public String createUser;
   @ApiField("CreateTime")
   public Date createTime;
   @ApiField("Memo")
   public String memo;
   @ApiListField("Lines")
   @ApiField("StockTransferLine")
   public List<StockTransferLine> lines;
   
   public int getTransferId() {
     return this.transferId;
   }
   
   public void setTransferId(int transferId) {
     this.transferId = transferId;
   }
   
   public String getStockTransferNo() {
     return this.stockTransferNo;
   }
   
   public void setStockTransferNo(String stockTransferNo) {
     this.stockTransferNo = stockTransferNo;
   }
   
   public int getStockTransferType() {
     return this.stockTransferType;
   }
   
   public void setStockTransferType(int stockTransferType) {
     this.stockTransferType = stockTransferType;
   }
   
   public int getStockTransferStatus() {
     return this.stockTransferStatus;
   }
   
   public void setStockTransferStatus(int stockTransferStatus) {
     this.stockTransferStatus = stockTransferStatus;
   }
   
   public int getFromWhsId() {
     return this.fromWhsId;
   }
   
   public void setFromWhsId(int fromWhsId) {
     this.fromWhsId = fromWhsId;
   }
   
   public Warehouse getWarehouseFrom() {
     return this.warehouseFrom;
   }
   
   public void setWarehouseFrom(Warehouse warehouseFrom) {
     this.warehouseFrom = warehouseFrom;
   }
   
   public int getFromWhsAreaId() {
     return this.fromWhsAreaId;
   }
   
   public void setFromWhsAreaId(int fromWhsAreaId) {
     this.fromWhsAreaId = fromWhsAreaId;
   }
   
   public int getToWhsId() {
     return this.toWhsId;
   }
   
   public void setToWhsId(int toWhsId) {
     this.toWhsId = toWhsId;
   }
   
   public Warehouse getWarehouseTo() {
     return this.warehouseTo;
   }
   
   public void setWarehouseTo(Warehouse warehouseTo) {
     this.warehouseTo = warehouseTo;
   }
   
   public int getToWhsAreaId() {
     return this.toWhsAreaId;
   }
   
   public void setToWhsAreaId(int toWhsAreaId) {
     this.toWhsAreaId = toWhsAreaId;
   }
   
   public Data getPostDate() {
     return this.postDate;
   }
   
   public void setPostDate(Data postDate) {
     this.postDate = postDate;
   }
   
   public String getCreateUser() {
     return this.createUser;
   }
   
   public void setCreateUser(String createUser) {
     this.createUser = createUser;
   }
   
   public Date getCreateTime() {
     return this.createTime;
   }
   
   public void setCreateTime(Date createTime) {
     this.createTime = createTime;
   }
   
   public String getMemo() {
     return this.memo;
   }
   
   public void setMemo(String memo) {
     this.memo = memo;
   }
   
   public List<StockTransferLine> getLines() {
     return this.lines;
   }
   
   public void setLines(List<StockTransferLine> lines) {
     this.lines = lines;
   }
   
   public StockTransfer() {
     this.lines = new ArrayList<>();
   }
 }


