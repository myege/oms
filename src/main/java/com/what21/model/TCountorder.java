 package com.what21.model;
 public class TCountorder
 {
   private Integer id;
   private String startdate;
   private String businessno;
   private String allmailno;
   private String mailno;
   private String origin;
   private String flow;
   private String sum;
   private String weigth;
   private String bulk;
   private String beizu;
   private String company;
   private String paydate;
   private String state;
   private String createData;
   
   public String getCreateData() {
     return this.createData;
   }
   
   public void setCreateData(String createData) {
     this.createData = createData;
   }
   
   public String getState() {
     return this.state;
   }
   
   public void setState(String state) {
     this.state = state;
   }
   
   public String getCompany() {
     return this.company;
   }
   
   public void setCompany(String company) {
     this.company = company;
   }
   
   public String getPaydate() {
     return this.paydate;
   }
   
   public void setPaydate(String paydate) {
     this.paydate = paydate;
   }
   
   public Integer getId() {
     return this.id;
   }
   
   public void setId(Integer id) {
     this.id = id;
   }
   
   public String getStartdate() {
     return this.startdate;
   }
   
   public void setStartdate(String startdate) {
     this.startdate = (startdate == null) ? null : startdate.trim();
   }
   
   public String getBusinessno() {
     return this.businessno;
   }
   
   public void setBusinessno(String businessno) {
     this.businessno = (businessno == null) ? null : businessno.trim();
   }
   
   public String getAllmailno() {
     return this.allmailno;
   }
   
   public void setAllmailno(String allmailno) {
     this.allmailno = (allmailno == null) ? null : allmailno.trim();
   }
   
   public String getMailno() {
     return this.mailno;
   }
   
   public void setMailno(String mailno) {
     this.mailno = (mailno == null) ? null : mailno.trim();
   }
   
   public String getOrigin() {
     return this.origin;
   }
   
   public void setOrigin(String origin) {
     this.origin = (origin == null) ? null : origin.trim();
   }
   
   public String getFlow() {
     return this.flow;
   }
   
   public void setFlow(String flow) {
     this.flow = (flow == null) ? null : flow.trim();
   }
   
   public String getSum() {
     return this.sum;
   }
   
   public void setSum(String sum) {
     this.sum = (sum == null) ? null : sum.trim();
   }
   
   public String getWeigth() {
     return this.weigth;
   }
   
   public void setWeigth(String weigth) {
     this.weigth = (weigth == null) ? null : weigth.trim();
   }
   
   public String getBulk() {
     return this.bulk;
   }
   
   public void setBulk(String bulk) {
     this.bulk = (bulk == null) ? null : bulk.trim();
   }
   
   public String getBeizu() {
     return this.beizu;
   }
   
   public void setBeizu(String beizu) {
     this.beizu = (beizu == null) ? null : beizu.trim();
   }
 }


