 package com.what21.model;
 
 import java.math.BigDecimal;
 public class OrderMailSku
 {
   private int id;
   private String itemsku;
   private String txLogisticID;
   private String itemName;
   private Double itemWeight;
   private Integer itemCount;
   private Double unitPrice;
   private Double allPrice;
   private String merchart;
   private String hz;
   private BigDecimal excise;
   
   public Integer getItemCount() {
     return this.itemCount;
   }
   private BigDecimal vat; private BigDecimal consolidatedTax; private String batchNumber; private String itemClass; private String unitname; private String standard; private BigDecimal imt; private Double ae; private Double orderMailSkuxy; private Double orderMailSkudy; private String url;
   public void setItemCount(Integer itemCount) {
     this.itemCount = itemCount;
   }
   
   public String getUnitname() {
     return this.unitname;
   }
   
   public void setUnitname(String unitname) {
     this.unitname = unitname;
   }
   
   public String getStandard() {
     return this.standard;
   }
   
   public void setStandard(String standard) {
     this.standard = standard;
   }
   
   public String getItemClass() {
     return this.itemClass;
   }
   
   public void setItemClass(String itemClass) {
     this.itemClass = itemClass;
   }
 
   
   public BigDecimal getImt() {
     return this.imt;
   }
   
   public void setImt(BigDecimal imt) {
     this.imt = imt;
   }
   
   public Double getOrderMailSkuxy() {
     return this.orderMailSkuxy;
   }
   
   public void setOrderMailSkuxy(Double orderMailSkuxy) {
     this.orderMailSkuxy = orderMailSkuxy;
   }
   
   public Double getOrderMailSkudy() {
     return this.orderMailSkudy;
   }
   
   public void setOrderMailSkudy(Double orderMailSkudy) {
     this.orderMailSkudy = orderMailSkudy;
   }
   
   public Double getAe() {
     return this.ae;
   }
   
   public void setAe(Double ae) {
     this.ae = ae;
   }
   
   public String getBatchNumber() {
     return this.batchNumber;
   }
   
   public void setBatchNumber(String batchNumber) {
     this.batchNumber = batchNumber;
   }
   
   public BigDecimal getExcise() {
     return this.excise;
   }
   
   public void setExcise(BigDecimal excise) {
     this.excise = excise;
   }
   
   public BigDecimal getVat() {
     return this.vat;
   }
   
   public void setVat(BigDecimal vat) {
     this.vat = vat;
   }
   
   public BigDecimal getConsolidatedTax() {
     return this.consolidatedTax;
   }
   
   public void setConsolidatedTax(BigDecimal consolidatedTax) {
     this.consolidatedTax = consolidatedTax;
   }
   
   public String getHz() {
     return this.hz;
   }
   
   public void setHz(String hz) {
     this.hz = hz;
   }
   
   public String getItemsku() {
     return this.itemsku;
   }
   
   public void setItemsku(String itemsku) {
     this.itemsku = itemsku;
   }
   
   public String getMerchart() {
     return this.merchart;
   }
   
   public void setMerchart(String merchart) {
     this.merchart = merchart;
   }
   
   public int getId() {
     return this.id;
   }
   public void setId(int id) {
     this.id = id;
   }
   
   public String getTxLogisticID() {
     return this.txLogisticID;
   }
   
   public void setTxLogisticID(String txLogisticID) {
     this.txLogisticID = txLogisticID;
   }
   
   public String getItemName() {
     return this.itemName;
   }
   
   public void setItemName(String itemName) {
     this.itemName = itemName;
   }
   
   public Double getItemWeight() {
     return this.itemWeight;
   }
 
   
   public void setItemWeight(Double itemWeight) {
     this.itemWeight = itemWeight;
   }
   
   public Double getUnitPrice() {
     return this.unitPrice;
   }
   
   public void setUnitPrice(Double unitPrice) {
     this.unitPrice = unitPrice;
   }
   
   public Double getAllPrice() {
     return this.allPrice;
   }
   
   public void setAllPrice(Double allPrice) {
     this.allPrice = allPrice;
   }
 
 
 
   
   public String getUrl() {
     return this.url;
   }
   
   public void setUrl(String url) {
     this.url = url;
   }
 }


