 package com.what21.model;
 
 import java.math.BigDecimal;
 import java.util.Date;
 import java.util.List;
 public class OrderMail
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
   private Double price;
   private Double worth;
   private String preEntryNumber;
   private String tradeCountry;
   private String buyerIdNumber;
   private String buyerName;
   private Double feeAmount;
   private Double insureAmount;
   private String payNumber;
   private String ordercode;
   private String pc;
   private int isPushDd;
   private int isPushQd;
   private int ispost;
   private int isPushCz;
   private int isCustoms;
   private int auditstatus;
   private int userId;
   private String sendWarehouse;
   private String merchantNum;
   private List<OrderMailSku> obsList;
   private String sendName;
   private String sendTel;
   private String sendAddress;
   private String totalMailNo;
   private String receivecode;
   private String receiveCountr;
   private String classname;
   private String unitname;
   private String parcelnumber;
   private String standard;
   private String hs;
   private String fightNumber;
   private int status;
   private String statusname;
   private String destinationPort;
   private String batchNumber;
   private Double itemWeightorder;
   private Integer itemCountorder;
   private Double unitPriceorder;
   private Double allPriceorder;
   private String itemnamexq;
   private Double itemWeightorders;
   private Integer itemCountorders;
   private Double unitPriceorders;
   private Double allPriceorders;
   private String itemnamexqs;
   private Date createTime;
   private String invtNo;
   private String customsTax;
   private String valueAddedTax;
   private String consumptionTax;
   private int isPrint;
   private Integer returncode;
   private String returnInfo;
   private String totalMailNoNew;
   private int it;
   private BigDecimal dy;
   private BigDecimal xy;
   private BigDecimal ohr;
   private BigDecimal ojr;
   private Double si;
   private Double sis;
   private Double prices;
   private Double allp;
   private Double icount;
   private int itcount;
   private Double exs;
   private Double vas;
   private Double cons;
   private BigDecimal allPrice;
   private BigDecimal excise;
   private BigDecimal vat;
   private BigDecimal consolidatedTax;
   private Double unitPrice;
   
   public String getTotalMailNoNew() {
     return this.totalMailNoNew;
   }
   
   public void setTotalMailNoNew(String totalMailNoNew) {
     this.totalMailNoNew = totalMailNoNew;
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
   
   public int getIsPrint() {
     return this.isPrint;
   }
   
   public void setIsPrint(int isPrint) {
     this.isPrint = isPrint;
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
   
   public Date getCreateTime() {
     return this.createTime;
   }
   
   public void setCreateTime(Date createTime) {
     this.createTime = createTime;
   }
   
   public BigDecimal getOjr() {
     return this.ojr;
   }
   
   public void setOjr(BigDecimal ojr) {
     this.ojr = ojr;
   }
   
   public BigDecimal getOhr() {
     return this.ohr;
   }
   
   public void setOhr(BigDecimal ohr) {
     this.ohr = ohr;
   }
   
   public BigDecimal getDy() {
     return this.dy;
   }
   
   public void setDy(BigDecimal dy) {
     this.dy = dy;
   }
   
   public BigDecimal getXy() {
     return this.xy;
   }
   
   public void setXy(BigDecimal xy) {
     this.xy = xy;
   }
   
   public int getIt() {
     return this.it;
   }
   
   public void setIt(int it) {
     this.it = it;
   }
   
   public Double getItemWeightorders() {
     return this.itemWeightorders;
   }
   
   public void setItemWeightorders(Double itemWeightorders) {
     this.itemWeightorders = itemWeightorders;
   }
   
   public Integer getItemCountorders() {
     return this.itemCountorders;
   }
   
   public void setItemCountorders(Integer itemCountorders) {
     this.itemCountorders = itemCountorders;
   }
   
   public Double getUnitPriceorders() {
     return this.unitPriceorders;
   }
   
   public void setUnitPriceorders(Double unitPriceorders) {
     this.unitPriceorders = unitPriceorders;
   }
   
   public Double getAllPriceorders() {
     return this.allPriceorders;
   }
   
   public void setAllPriceorders(Double allPriceorders) {
     this.allPriceorders = allPriceorders;
   }
   
   public String getItemnamexqs() {
     return this.itemnamexqs;
   }
   
   public void setItemnamexqs(String itemnamexqs) {
     this.itemnamexqs = itemnamexqs;
   }
   
   public String getItemnamexq() {
     return this.itemnamexq;
   }
   
   public void setItemnamexq(String itemnamexq) {
     this.itemnamexq = itemnamexq;
   }
   
   public Double getSis() {
     return this.sis;
   }
   
   public void setSis(Double sis) {
     this.sis = sis;
   }
   
   public Double getSi() {
     return this.si;
   }
   
   public void setSi(Double si) {
     this.si = si;
   }
   
   public Double getItemWeightorder() {
     return this.itemWeightorder;
   }
   
   public void setItemWeightorder(Double itemWeightorder) {
     this.itemWeightorder = itemWeightorder;
   }
   
   public Integer getItemCountorder() {
     return this.itemCountorder;
   }
   
   public void setItemCountorder(Integer itemCountorder) {
     this.itemCountorder = itemCountorder;
   }
   
   public Double getUnitPriceorder() {
     return this.unitPriceorder;
   }
   
   public void setUnitPriceorder(Double unitPriceorder) {
     this.unitPriceorder = unitPriceorder;
   }
   
   public Double getAllPriceorder() {
     return this.allPriceorder;
   }
   
   public void setAllPriceorder(Double allPriceorder) {
     this.allPriceorder = allPriceorder;
   }
 
   
   public String getBatchNumber() {
     return this.batchNumber;
   }
   
   public void setBatchNumber(String batchNumber) {
     this.batchNumber = batchNumber;
   }
   
   public Double getIcount() {
     return this.icount;
   }
   
   public void setIcount(Double icount) {
     this.icount = icount;
   }
 
   
   public int getItcount() {
     return this.itcount;
   }
   
   public void setItcount(int itcount) {
     this.itcount = itcount;
   }
   
   public Double getAllp() {
     return this.allp;
   }
   
   public void setAllp(Double allp) {
     this.allp = allp;
   }
   
   public Double getPrices() {
     return this.prices;
   }
   
   public void setPrices(Double prices) {
     this.prices = prices;
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
   
   public Double getCons() {
     return this.cons;
   }
   
   public void setCons(Double cons) {
     this.cons = cons;
   }
   
   public BigDecimal getAllPrice() {
     return this.allPrice;
   }
   
   public void setAllPrice(BigDecimal allPrice) {
     this.allPrice = allPrice;
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
   
   public String getDestinationPort() {
     return this.destinationPort;
   }
   
   public void setDestinationPort(String destinationPort) {
     this.destinationPort = destinationPort;
   }
   
   public String getStandard() {
     return this.standard;
   }
   
   public void setStandard(String standard) {
     this.standard = standard;
   }
   
   public Double getPrice() {
     return this.price;
   }
   
   public void setPrice(Double price) {
     this.price = price;
   }
   
   public String getReceivecode() {
     return this.receivecode;
   }
   
   public void setReceivecode(String receivecode) {
     this.receivecode = receivecode;
   }
   
   public String getReceiveCountr() {
     return this.receiveCountr;
   }
   
   public void setReceiveCountr(String receiveCountry) {
     this.receiveCountr = receiveCountry;
   }
   
   public String getClassname() {
     return this.classname;
   }
   
   public void setClassname(String classname) {
     this.classname = classname;
   }
   
   public String getUnitname() {
     return this.unitname;
   }
   
   public void setUnitname(String unitname) {
     this.unitname = unitname;
   }
   
   public String getParcelnumber() {
     return this.parcelnumber;
   }
   
   public void setParcelnumber(String parcelnumber) {
     this.parcelnumber = parcelnumber;
   }
   
   public String getHs() {
     return this.hs;
   }
   
   public void setHs(String hs) {
     this.hs = hs;
   }
   
   public int getStatus() {
     return this.status;
   }
   
   public void setStatus(int status) {
     this.status = status;
   }
   
   public String getStatusname() {
     return this.statusname;
   }
   
   public void setStatusname(String statusname) {
     this.statusname = statusname;
   }
   
   public List<OrderMailSku> getObsList() {
     return this.obsList;
   }
   
   public void setObsList(List<OrderMailSku> obsList) {
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
   
   public String getTotalMailNo() {
     return this.totalMailNo;
   }
   
   public void setTotalMailNo(String totalMailNo) {
     this.totalMailNo = totalMailNo;
   }
   
   public String getFightNumber() {
     return this.fightNumber;
   }
   
   public void setFightNumber(String fightNumber) {
     this.fightNumber = fightNumber;
   }
   
   public int getIsPushCz() {
     return this.isPushCz;
   }
   
   public void setIsPushCz(int isPushCz) {
     this.isPushCz = isPushCz;
   }
   
   public Double getUnitPrice() {
     return this.unitPrice;
   }
   
   public void setUnitPrice(Double unitPrice) {
     this.unitPrice = unitPrice;
   }
 }


