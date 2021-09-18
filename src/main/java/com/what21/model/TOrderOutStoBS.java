 package com.what21.model;
 
 import java.util.Date;
 public class TOrderOutStoBS
 {
   private Integer id;
   private String sitecode;
   private String sitename;
   private String tradeno;
   private String waybillno;
   private String logisticid;
   private Date orderdate;
   private String ordersource;
   private String volume;
   private String length;
   private String height;
   private String weight;
   private String width;
   private String senderprov;
   private String senderprovcode;
   private String sendercity;
   private String sendercitycode;
   private String senderarea;
   private String senderareacode;
   private String senderaddress;
   private String sendertown;
   private String sendertowncode;
   private String sendermobile;
   private String sendername;
   private String senderphone;
   private String senderpostcode;
   private String receiverprov;
   private String receiverprovcode;
   private String receivercity;
   private String receivercitycode;
   private String receiverarea;
   private String receiverareacode;
   private String receivertown;
   private String receivertowncode;
   private String receiveraddress;
   private String receivermobile;
   private String receivername;
   private String receiverphone;
   private String receiverpostcode;
   private String billno;
   private String voyageno;
   private String declport;
   private Double grossweight;
   private Double netweight;
   private Double insureamount;
   private Double feeamount;
   private String recipientcountry;
   private Integer packno;
   private String goosinfo;
   private String packageinfo;
   private String currcode;
   private String trafmode;
   private String trafname;
   private String itemcode;
   private Integer userid;
   private String createtime;
   private Integer status;
   
   public Integer getId() {
     return this.id;
   }
   
   public void setId(Integer id) {
     this.id = id;
   }
   
   public String getSitecode() {
     return this.sitecode;
   }
   
   public void setSitecode(String sitecode) {
     this.sitecode = (sitecode == null) ? null : sitecode.trim();
   }
   
   public String getSitename() {
     return this.sitename;
   }
   
   public void setSitename(String sitename) {
     this.sitename = (sitename == null) ? null : sitename.trim();
   }
   
   public String getTradeno() {
     return this.tradeno;
   }
   
   public void setTradeno(String tradeno) {
     this.tradeno = (tradeno == null) ? null : tradeno.trim();
   }
   
   public String getWaybillno() {
     return this.waybillno;
   }
   
   public void setWaybillno(String waybillno) {
     this.waybillno = (waybillno == null) ? null : waybillno.trim();
   }
   
   public String getLogisticid() {
     return this.logisticid;
   }
   
   public void setLogisticid(String logisticid) {
     this.logisticid = (logisticid == null) ? null : logisticid.trim();
   }
   
   public Date getOrderdate() {
     return this.orderdate;
   }
   
   public void setOrderdate(Date orderdate) {
     this.orderdate = orderdate;
   }
   
   public String getOrdersource() {
     return this.ordersource;
   }
   
   public void setOrdersource(String ordersource) {
     this.ordersource = (ordersource == null) ? null : ordersource.trim();
   }
   
   public String getVolume() {
     return this.volume;
   }
   
   public void setVolume(String volume) {
     this.volume = (volume == null) ? null : volume.trim();
   }
   
   public String getLength() {
     return this.length;
   }
   
   public void setLength(String length) {
     this.length = (length == null) ? null : length.trim();
   }
   
   public String getHeight() {
     return this.height;
   }
   
   public void setHeight(String height) {
     this.height = (height == null) ? null : height.trim();
   }
   
   public String getWeight() {
     return this.weight;
   }
   
   public void setWeight(String weight) {
     this.weight = (weight == null) ? null : weight.trim();
   }
   
   public String getWidth() {
     return this.width;
   }
   
   public void setWidth(String width) {
     this.width = (width == null) ? null : width.trim();
   }
   
   public String getSenderprov() {
     return this.senderprov;
   }
   
   public void setSenderprov(String senderprov) {
     this.senderprov = (senderprov == null) ? null : senderprov.trim();
   }
   
   public String getSenderprovcode() {
     return this.senderprovcode;
   }
   
   public void setSenderprovcode(String senderprovcode) {
     this.senderprovcode = (senderprovcode == null) ? null : senderprovcode.trim();
   }
   
   public String getSendercity() {
     return this.sendercity;
   }
   
   public void setSendercity(String sendercity) {
     this.sendercity = (sendercity == null) ? null : sendercity.trim();
   }
   
   public String getSendercitycode() {
     return this.sendercitycode;
   }
   
   public void setSendercitycode(String sendercitycode) {
     this.sendercitycode = (sendercitycode == null) ? null : sendercitycode.trim();
   }
   
   public String getSenderarea() {
     return this.senderarea;
   }
   
   public void setSenderarea(String senderarea) {
     this.senderarea = (senderarea == null) ? null : senderarea.trim();
   }
   
   public String getSenderareacode() {
     return this.senderareacode;
   }
   
   public void setSenderareacode(String senderareacode) {
     this.senderareacode = (senderareacode == null) ? null : senderareacode.trim();
   }
   
   public String getSenderaddress() {
     return this.senderaddress;
   }
   
   public void setSenderaddress(String senderaddress) {
     this.senderaddress = (senderaddress == null) ? null : senderaddress.trim();
   }
   
   public String getSendertown() {
     return this.sendertown;
   }
   
   public void setSendertown(String sendertown) {
     this.sendertown = (sendertown == null) ? null : sendertown.trim();
   }
   
   public String getSendertowncode() {
     return this.sendertowncode;
   }
   
   public void setSendertowncode(String sendertowncode) {
     this.sendertowncode = (sendertowncode == null) ? null : sendertowncode.trim();
   }
   
   public String getSendermobile() {
     return this.sendermobile;
   }
   
   public void setSendermobile(String sendermobile) {
     this.sendermobile = (sendermobile == null) ? null : sendermobile.trim();
   }
   
   public String getSendername() {
     return this.sendername;
   }
   
   public void setSendername(String sendername) {
     this.sendername = (sendername == null) ? null : sendername.trim();
   }
   
   public String getSenderphone() {
     return this.senderphone;
   }
   
   public void setSenderphone(String senderphone) {
     this.senderphone = (senderphone == null) ? null : senderphone.trim();
   }
   
   public String getSenderpostcode() {
     return this.senderpostcode;
   }
   
   public void setSenderpostcode(String senderpostcode) {
     this.senderpostcode = (senderpostcode == null) ? null : senderpostcode.trim();
   }
   
   public String getReceiverprov() {
     return this.receiverprov;
   }
   
   public void setReceiverprov(String receiverprov) {
     this.receiverprov = (receiverprov == null) ? null : receiverprov.trim();
   }
   
   public String getReceiverprovcode() {
     return this.receiverprovcode;
   }
   
   public void setReceiverprovcode(String receiverprovcode) {
     this.receiverprovcode = (receiverprovcode == null) ? null : receiverprovcode.trim();
   }
   
   public String getReceivercity() {
     return this.receivercity;
   }
   
   public void setReceivercity(String receivercity) {
     this.receivercity = (receivercity == null) ? null : receivercity.trim();
   }
   
   public String getReceivercitycode() {
     return this.receivercitycode;
   }
   
   public void setReceivercitycode(String receivercitycode) {
     this.receivercitycode = (receivercitycode == null) ? null : receivercitycode.trim();
   }
   
   public String getReceiverarea() {
     return this.receiverarea;
   }
   
   public void setReceiverarea(String receiverarea) {
     this.receiverarea = (receiverarea == null) ? null : receiverarea.trim();
   }
   
   public String getReceiverareacode() {
     return this.receiverareacode;
   }
   
   public void setReceiverareacode(String receiverareacode) {
     this.receiverareacode = (receiverareacode == null) ? null : receiverareacode.trim();
   }
   
   public String getReceivertown() {
     return this.receivertown;
   }
   
   public void setReceivertown(String receivertown) {
     this.receivertown = (receivertown == null) ? null : receivertown.trim();
   }
   
   public String getReceivertowncode() {
     return this.receivertowncode;
   }
   
   public void setReceivertowncode(String receivertowncode) {
     this.receivertowncode = (receivertowncode == null) ? null : receivertowncode.trim();
   }
   
   public String getReceiveraddress() {
     return this.receiveraddress;
   }
   
   public void setReceiveraddress(String receiveraddress) {
     this.receiveraddress = (receiveraddress == null) ? null : receiveraddress.trim();
   }
   
   public String getReceivermobile() {
     return this.receivermobile;
   }
   
   public void setReceivermobile(String receivermobile) {
     this.receivermobile = (receivermobile == null) ? null : receivermobile.trim();
   }
   
   public String getReceivername() {
     return this.receivername;
   }
   
   public void setReceivername(String receivername) {
     this.receivername = (receivername == null) ? null : receivername.trim();
   }
   
   public String getReceiverphone() {
     return this.receiverphone;
   }
   
   public void setReceiverphone(String receiverphone) {
     this.receiverphone = (receiverphone == null) ? null : receiverphone.trim();
   }
   
   public String getReceiverpostcode() {
     return this.receiverpostcode;
   }
   
   public void setReceiverpostcode(String receiverpostcode) {
     this.receiverpostcode = (receiverpostcode == null) ? null : receiverpostcode.trim();
   }
   
   public String getBillno() {
     return this.billno;
   }
   
   public void setBillno(String billno) {
     this.billno = (billno == null) ? null : billno.trim();
   }
   
   public String getVoyageno() {
     return this.voyageno;
   }
   
   public void setVoyageno(String voyageno) {
     this.voyageno = (voyageno == null) ? null : voyageno.trim();
   }
   
   public String getDeclport() {
     return this.declport;
   }
   
   public void setDeclport(String declport) {
     this.declport = (declport == null) ? null : declport.trim();
   }
   
   public Double getGrossweight() {
     return this.grossweight;
   }
   
   public void setGrossweight(Double grossweight) {
     this.grossweight = grossweight;
   }
   
   public Double getNetweight() {
     return this.netweight;
   }
   
   public void setNetweight(Double netweight) {
     this.netweight = netweight;
   }
   
   public Double getInsureamount() {
     return this.insureamount;
   }
   
   public void setInsureamount(Double insureamount) {
     this.insureamount = insureamount;
   }
   
   public Double getFeeamount() {
     return this.feeamount;
   }
   
   public void setFeeamount(Double feeamount) {
     this.feeamount = feeamount;
   }
   
   public String getRecipientcountry() {
     return this.recipientcountry;
   }
   
   public void setRecipientcountry(String recipientcountry) {
     this.recipientcountry = (recipientcountry == null) ? null : recipientcountry.trim();
   }
   
   public Integer getPackno() {
     return this.packno;
   }
   
   public void setPackno(Integer packno) {
     this.packno = packno;
   }
   
   public String getGoosinfo() {
     return this.goosinfo;
   }
   
   public void setGoosinfo(String goosinfo) {
     this.goosinfo = (goosinfo == null) ? null : goosinfo.trim();
   }
   
   public String getPackageinfo() {
     return this.packageinfo;
   }
   
   public void setPackageinfo(String packageinfo) {
     this.packageinfo = (packageinfo == null) ? null : packageinfo.trim();
   }
   
   public String getCurrcode() {
     return this.currcode;
   }
   
   public void setCurrcode(String currcode) {
     this.currcode = (currcode == null) ? null : currcode.trim();
   }
   
   public String getTrafmode() {
     return this.trafmode;
   }
   
   public void setTrafmode(String trafmode) {
     this.trafmode = (trafmode == null) ? null : trafmode.trim();
   }
   
   public String getTrafname() {
     return this.trafname;
   }
   
   public void setTrafname(String trafname) {
     this.trafname = (trafname == null) ? null : trafname.trim();
   }
   
   public String getItemcode() {
     return this.itemcode;
   }
   
   public void setItemcode(String itemcode) {
     this.itemcode = (itemcode == null) ? null : itemcode.trim();
   }
   
   public Integer getUserid() {
     return this.userid;
   }
   
   public void setUserid(Integer userid) {
     this.userid = userid;
   }
   
   public String getCreatetime() {
     return this.createtime;
   }
   
   public void setCreatetime(String createtime) {
     this.createtime = (createtime == null) ? null : createtime.trim();
   }
   
   public Integer getStatus() {
     return this.status;
   }
   
   public void setStatus(Integer status) {
     this.status = status;
   }
 }


