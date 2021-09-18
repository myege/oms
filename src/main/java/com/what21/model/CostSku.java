 package com.what21.model;
 
 import java.math.BigDecimal;
 public class CostSku
 {
   private int id;
   private int costId;
   private Double freightPrice;
   private Double freightNumber;
   private Double freightMoney;
   private String freightRemark;
   private Double operationPrice;
   private int operationNumber;
   private Double operationMoney;
   private String operationRemark;
   private Double groundPrice;
   private Double groundNumber;
   private Double groundMoney;
   private String groundRemark;
   private Double platformPrice;
   private Double platformNumber;
   private Double platformMoney;
   private String platformRemark;
   private String taxRemark;
   private Double total;
   private Double exs;
   private Double vas;
   private BigDecimal cons;
   private BigDecimal ico;
   private BigDecimal icou;
   private BigDecimal wh;
   
   public String getTaxRemark() {
     return this.taxRemark;
   }
   
   public void setTaxRemark(String taxRemark) {
     this.taxRemark = taxRemark;
   }
   
   public BigDecimal getWh() {
     return this.wh;
   }
 
 
   
   public BigDecimal getIco() {
     return this.ico;
   }
   
   public void setIco(BigDecimal ico) {
     this.ico = ico;
   }
   
   public BigDecimal getIcou() {
     return this.icou;
   }
   
   public void setIcou(BigDecimal icou) {
     this.icou = icou;
   }
   
   public void setWh(BigDecimal allPrice) {
     this.wh = allPrice;
   }
   
   public Double getExs() {
     return this.exs;
   }
   
   public void setExs(Double exs) {
     this.exs = exs;
   }
   
   public Double getVas() {
     return this.vas;
   }
   
   public void setVas(Double vas) {
     this.vas = vas;
   }
   
   public BigDecimal getCons() {
     return this.cons;
   }
   
   public void setCons(BigDecimal bigDecimal) {
     this.cons = bigDecimal;
   }
   
   public int getId() {
     return this.id;
   }
   
   public void setId(int id) {
     this.id = id;
   }
   
   public int getCostId() {
     return this.costId;
   }
   
   public void setCostId(int costId) {
     this.costId = costId;
   }
   
   public Double getFreightPrice() {
     return this.freightPrice;
   }
   
   public void setFreightPrice(Double freightPrice) {
     this.freightPrice = freightPrice;
   }
   
   public Double getFreightNumber() {
     return this.freightNumber;
   }
   
   public void setFreightNumber(Double freightNumber) {
     this.freightNumber = freightNumber;
   }
   
   public Double getFreightMoney() {
     return this.freightMoney;
   }
   
   public void setFreightMoney(Double freightMoney) {
     this.freightMoney = freightMoney;
   }
   
   public String getFreightRemark() {
     return this.freightRemark;
   }
   
   public void setFreightRemark(String freightRemark) {
     this.freightRemark = freightRemark;
   }
   
   public Double getOperationPrice() {
     return this.operationPrice;
   }
   
   public void setOperationPrice(Double operationPrice) {
     this.operationPrice = operationPrice;
   }
   
   public int getOperationNumber() {
     return this.operationNumber;
   }
   
   public void setOperationNumber(int operationNumber) {
     this.operationNumber = operationNumber;
   }
   
   public Double getOperationMoney() {
     return this.operationMoney;
   }
   
   public void setOperationMoney(Double operationMoney) {
     this.operationMoney = operationMoney;
   }
   
   public String getOperationRemark() {
     return this.operationRemark;
   }
   
   public void setOperationRemark(String operationRemark) {
     this.operationRemark = operationRemark;
   }
   
   public Double getGroundPrice() {
     return this.groundPrice;
   }
   
   public void setGroundPrice(Double groundPrice) {
     this.groundPrice = groundPrice;
   }
   
   public Double getGroundNumber() {
     return this.groundNumber;
   }
   
   public void setGroundNumber(Double groundNumber) {
     this.groundNumber = groundNumber;
   }
   
   public Double getGroundMoney() {
     return this.groundMoney;
   }
   
   public void setGroundMoney(Double groundMoney) {
     this.groundMoney = groundMoney;
   }
   
   public String getGroundRemark() {
     return this.groundRemark;
   }
   
   public void setGroundRemark(String groundRemark) {
     this.groundRemark = groundRemark;
   }
 
   
   public Double getPlatformPrice() {
     return this.platformPrice;
   }
   
   public void setPlatformPrice(Double platformPrice) {
     this.platformPrice = platformPrice;
   }
   
   public Double getPlatformNumber() {
     return this.platformNumber;
   }
   
   public void setPlatformNumber(Double platformNumber) {
     this.platformNumber = platformNumber;
   }
   
   public Double getPlatformMoney() {
     return this.platformMoney;
   }
   
   public void setPlatformMoney(Double platformMoney) {
     this.platformMoney = platformMoney;
   }
   
   public String getPlatformRemark() {
     return this.platformRemark;
   }
   
   public void setPlatformRemark(String platformRemark) {
     this.platformRemark = platformRemark;
   }
   
   public Double getTotal() {
     return this.total;
   }
   
   public void setTotal(Double total) {
     this.total = total;
   }
 }


