 package com.iwilley.b1ec2.api.request;
 
 import com.iwilley.b1ec2.api.B1EC2Request;
 import com.iwilley.b1ec2.api.internal.util.B1EC2HashMap;
 import com.iwilley.b1ec2.api.response.StockTransferQueryResponse;
 import java.util.Date;
 import java.util.Map;
 public class StockTransferQueryRequest
   implements B1EC2Request<StockTransferQueryResponse>
 {
   public Date startTime;
   public Date endTime;
   public Integer pageNum;
   public Integer pageSize;
   public String stockTransferNo;
   public Integer stockTransferType;
   public Integer stockTransferStatus;
   
   public String getApiMethodName() {
     return "B1EC2.StockTransfer.Query";
   }
 
   
   public Map<String, String> GetParameters() {
     B1EC2HashMap parameters = new B1EC2HashMap();
     parameters.put("StartTime", this.startTime);
     parameters.put("EndTime", this.endTime);
     parameters.put("PageNum", this.pageNum);
     parameters.put("PageSize", this.pageSize);
     parameters.put("StockTransferNo", this.stockTransferNo);
     parameters.put("StockTransferType", this.stockTransferType);
     parameters.put("StockTransferStatus", this.stockTransferStatus);
     return (Map<String, String>)parameters;
   }
 
   
   public Class<StockTransferQueryResponse> getResponseClass() {
     return StockTransferQueryResponse.class;
   }
   
   public Date getStartTime() {
     return this.startTime;
   }
   
   public void setStartTime(Date startTime) {
     this.startTime = startTime;
   }
   
   public Date getEndTime() {
     return this.endTime;
   }
   
   public void setEndTime(Date endTime) {
     this.endTime = endTime;
   }
   
   public Integer getPageNum() {
     return this.pageNum;
   }
   
   public void setPageNum(Integer pageNum) {
     this.pageNum = pageNum;
   }
   
   public Integer getPageSize() {
     return this.pageSize;
   }
   
   public void setPageSize(Integer pageSize) {
     this.pageSize = pageSize;
   }
   
   public String getStockTransferNo() {
     return this.stockTransferNo;
   }
   
   public void setStockTransferNo(String stockTransferNo) {
     this.stockTransferNo = stockTransferNo;
   }
   
   public Integer getStockTransferType() {
     return this.stockTransferType;
   }
   
   public void setStockTransferType(Integer stockTransferType) {
     this.stockTransferType = stockTransferType;
   }
   
   public Integer getStockTransferStatus() {
     return this.stockTransferStatus;
   }
   
   public void setStockTransferStatus(Integer stockTransferStatus) {
     this.stockTransferStatus = stockTransferStatus;
   }
 }


