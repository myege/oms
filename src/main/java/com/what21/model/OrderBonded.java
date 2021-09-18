 package com.what21.model;
 
 import java.util.Date;
 import java.util.List;
 public class OrderBonded
 {
   private int id;
   private String receiveMan;
   private String receiveManPhone;
   private String receiveManAddress;
   private String receiveProvince;
   private String receiveCity;
   private String receiveCounty;
   private String txLogisticID;
   private String itemName;
   private Double itemWeight;
   private Integer itemCount;
   private String carrier;
   private String mailNo;
   private String markDestination;
   private String billProvideSiteName;
   private String billProvideSiteCode;
   private Double worth;
   private String preEntryNumber;
   private String tradeCountry;
   private String buyerIdNumber;
   private String buyerName;
   private Double feeAmount;
   private Double insureAmount;
   private String payCompany;
   private String payNumber = "";
 
 
   
   private String ordercode;
 
 
   
   private String pc;
 
 
   
   private int isPushToWms;
 
 
   
   private int ispost;
 
 
   
   private int isPushDd;
 
 
   
   private int isPushQd;
 
   
   private int isCustoms;
 
   
   private int auditstatus;
 
   
   private int userId;
 
   
   private String sendWarehouse;
 
   
   private String merchantNum;
 
   
   private List<OrderBondedSku> obsList;
 
   
   private String sendName;
 
   
   private String sendTel;
 
   
   private String sendAddress;
 
   
   private Integer skuCount;
 
   
   private Integer area;
 
   
   private Date createTime;
 
   
   private String gylFinanceNumber;
 
   
   private String invtNo;
 
   
   private String customsTax;
 
   
   private String valueAddedTax;
 
   
   private String consumptionTax;
 
   
   private Integer returncode;
 
   
   private String returnInfo;
 
   
   private int kucstate;
 
   
   private Integer mark;
 
   
   private String vipts;
 
   
   private String tjSj;
 
   
   private String disbursed;
 
   
   private String statushc;
 
 
   
   public Integer getMark() {
     return this.mark;
   }
   
   public void setMark(Integer mark) {
     this.mark = mark;
   }
   
   public String getVipts() {
     return this.vipts;
   }
   
   public void setVipts(String vipts) {
     this.vipts = vipts;
   }
 
   
   public String getTjSj() {
     return this.tjSj;
   }
   
   public void setTjSj(String tjSj) {
     this.tjSj = tjSj;
   }
   
   public String getDisbursed() {
     return this.disbursed;
   }
   
   public void setDisbursed(String disbursed) {
     this.disbursed = disbursed;
   }
   
   public int getKucstate() {
     return this.kucstate;
   }
   
   public void setKucstate(int kucstate) {
     this.kucstate = kucstate;
   }
   
   public Integer getReturncode() {
     return this.returncode;
   }
   
   public void setReturncode(Integer returncode) {
     this.returncode = returncode;
   }
   
   public String getReturnInfo() {
     return this.returnInfo;
   }
   
   public void setReturnInfo(String returnInfo) {
     this.returnInfo = returnInfo;
   }
   
   public String getInvtNo() {
     return this.invtNo;
   }
   
   public void setInvtNo(String invtNo) {
     this.invtNo = invtNo;
   }
   
   public String getCustomsTax() {
     return this.customsTax;
   }
   
   public void setCustomsTax(String customsTax) {
     this.customsTax = customsTax;
   }
   
   public String getValueAddedTax() {
     return this.valueAddedTax;
   }
   
   public void setValueAddedTax(String valueAddedTax) {
     this.valueAddedTax = valueAddedTax;
   }
   
   public String getConsumptionTax() {
     return this.consumptionTax;
   }
   
   public void setConsumptionTax(String consumptionTax) {
     this.consumptionTax = consumptionTax;
   }
   
   public String getGylFinanceNumber() {
     return this.gylFinanceNumber;
   }
   
   public void setGylFinanceNumber(String gylFinanceNumber) {
     this.gylFinanceNumber = gylFinanceNumber;
   }
   
   public Date getCreateTime() {
     return this.createTime;
   }
   
   public void setCreateTime(Date createTime) {
     this.createTime = createTime;
   }
 
 
   
   public String getStatushc() {
     return this.statushc;
   }
   
   public void setStatushc(String statushc) {
     this.statushc = statushc;
   }
   
   public int getIsPushDd() {
     return this.isPushDd;
   }
   
   public void setIsPushDd(int isPushDd) {
     this.isPushDd = isPushDd;
   }
   
   public int getIsPushQd() {
     return this.isPushQd;
   }
   
   public void setIsPushQd(int isPushQd) {
     this.isPushQd = isPushQd;
   }
   
   public Integer getArea() {
     return this.area;
   }
   
   public void setArea(Integer area) {
     this.area = area;
   }
   
   public Integer getSkuCount() {
     return this.skuCount;
   }
   
   public void setSkuCount(Integer skuCount) {
     this.skuCount = skuCount;
   }
   
   public List<OrderBondedSku> getObsList() {
     return this.obsList;
   }
   
   public void setObsList(List<OrderBondedSku> obsList) {
     this.obsList = obsList;
   }
   
   public String getSendWarehouse() {
     return this.sendWarehouse;
   }
   
   public void setSendWarehouse(String sendWarehouse) {
     this.sendWarehouse = sendWarehouse;
   }
   
   public String getMerchantNum() {
     return this.merchantNum;
   }
   
   public void setMerchantNum(String merchantNum) {
     this.merchantNum = merchantNum;
   }
   
   public int getUserId() {
     return this.userId;
   }
   
   public void setUserId(int userId) {
     this.userId = userId;
   }
   
   public int getIspost() {
     return this.ispost;
   }
   
   public void setIspost(int ispost) {
     this.ispost = ispost;
   }
   
   public int getAuditstatus() {
     return this.auditstatus;
   }
   
   public void setAuditstatus(int auditstatus) {
     this.auditstatus = auditstatus;
   }
   
   public int getId() {
     return this.id;
   }
   
   public void setId(int id) {
     this.id = id;
   }
   
   public String getReceiveMan() {
     return this.receiveMan;
   }
   
   public void setReceiveMan(String receiveMan) {
     this.receiveMan = receiveMan;
   }
   
   public String getReceiveManPhone() {
     return this.receiveManPhone;
   }
   
   public void setReceiveManPhone(String receiveManPhone) {
     this.receiveManPhone = receiveManPhone;
   }
   
   public String getReceiveManAddress() {
     return this.receiveManAddress;
   }
   
   public void setReceiveManAddress(String receiveManAddress) {
     this.receiveManAddress = receiveManAddress;
   }
   
   public String getReceiveProvince() {
     return this.receiveProvince;
   }
   
   public void setReceiveProvince(String receiveProvince) {
     this.receiveProvince = receiveProvince;
   }
   
   public String getReceiveCity() {
     return this.receiveCity;
   }
   
   public void setReceiveCity(String receiveCity) {
     this.receiveCity = receiveCity;
   }
   
   public String getReceiveCounty() {
     return this.receiveCounty;
   }
   
   public void setReceiveCounty(String receiveCounty) {
     this.receiveCounty = receiveCounty;
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
   
   public Integer getItemCount() {
     return this.itemCount;
   }
   
   public void setItemCount(Integer itemCount) {
     this.itemCount = itemCount;
   }
   
   public String getCarrier() {
     return this.carrier;
   }
   
   public void setCarrier(String carrier) {
     this.carrier = carrier;
   }
   
   public String getMailNo() {
     return this.mailNo;
   }
   
   public void setMailNo(String mailNo) {
     this.mailNo = mailNo;
   }
   
   public String getMarkDestination() {
     return this.markDestination;
   }
   
   public void setMarkDestination(String markDestination) {
     this.markDestination = markDestination;
   }
   
   public String getBillProvideSiteName() {
     return this.billProvideSiteName;
   }
   
   public void setBillProvideSiteName(String billProvideSiteName) {
     this.billProvideSiteName = billProvideSiteName;
   }
   
   public String getBillProvideSiteCode() {
     return this.billProvideSiteCode;
   }
   
   public void setBillProvideSiteCode(String billProvideSiteCode) {
     this.billProvideSiteCode = billProvideSiteCode;
   }
   
   public Double getWorth() {
     return this.worth;
   }
   
   public void setWorth(Double worth) {
     this.worth = worth;
   }
   
   public String getPreEntryNumber() {
     return this.preEntryNumber;
   }
   
   public void setPreEntryNumber(String preEntryNumber) {
     this.preEntryNumber = preEntryNumber;
   }
   
   public String getTradeCountry() {
     return this.tradeCountry;
   }
   
   public void setTradeCountry(String tradeCountry) {
     this.tradeCountry = tradeCountry;
   }
   
   public String getBuyerIdNumber() {
     return this.buyerIdNumber;
   }
   
   public void setBuyerIdNumber(String buyerIdNumber) {
     this.buyerIdNumber = buyerIdNumber;
   }
   
   public String getBuyerName() {
     return this.buyerName;
   }
   
   public void setBuyerName(String buyerName) {
     this.buyerName = buyerName;
   }
   
   public Double getFeeAmount() {
     return this.feeAmount;
   }
   
   public void setFeeAmount(Double feeAmount) {
     this.feeAmount = feeAmount;
   }
   
   public Double getInsureAmount() {
     return this.insureAmount;
   }
   
   public void setInsureAmount(Double insureAmount) {
     this.insureAmount = insureAmount;
   }
   
   public String getPayNumber() {
     return this.payNumber;
   }
   
   public void setPayNumber(String payNumber) {
     this.payNumber = payNumber;
   }
   
   public String getOrdercode() {
     return this.ordercode;
   }
   
   public void setOrdercode(String ordercode) {
     this.ordercode = ordercode;
   }
   
   public String getPc() {
     return this.pc;
   }
   
   public void setPc(String pc) {
     this.pc = pc;
   }
   
   public int getIsCustoms() {
     return this.isCustoms;
   }
   
   public void setIsCustoms(int isCustoms) {
     this.isCustoms = isCustoms;
   }
   
   public String getSendName() {
     return this.sendName;
   }
   
   public void setSendName(String sendName) {
     this.sendName = sendName;
   }
   
   public String getSendTel() {
     return this.sendTel;
   }
   
   public void setSendTel(String sendTel) {
     this.sendTel = sendTel;
   }
   
   public String getSendAddress() {
     return this.sendAddress;
   }
   
   public void setSendAddress(String sendAddress) {
     this.sendAddress = sendAddress;
   }
   
   public int getIsPushToWms() {
     return this.isPushToWms;
   }
   
   public void setIsPushToWms(int isPushToWms) {
     this.isPushToWms = isPushToWms;
   }
   
   public String getPayCompany() {
     return this.payCompany;
   }
   
   public void setPayCompany(String payCompany) {
     this.payCompany = payCompany;
   }
 }


