 package com.what21.model;
 
 import java.util.List;
 
 
 
 public class EchattsResult
 {
   private List<EchattsBonded> bondeds;
   private List<EchattsBonded> mails;
   
   public EchattsResult(List<EchattsBonded> bondeds, List<EchattsBonded> mails) {
     this.bondeds = bondeds;
     this.mails = mails;
   }
 
   
   public List<EchattsBonded> getBondeds() {
     return this.bondeds;
   }
   public void setBondeds(List<EchattsBonded> bondeds) {
     this.bondeds = bondeds;
   }
   public List<EchattsBonded> getMails() {
     return this.mails;
   }
   public void setMails(List<EchattsBonded> mails) {
     this.mails = mails;
   }
 }


