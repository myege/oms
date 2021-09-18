 package com.iwilley.b1ec2.api.response;
 
 import com.iwilley.b1ec2.api.B1EC2Response;
 import com.iwilley.b1ec2.api.domain.FinJournal;
 import com.iwilley.b1ec2.api.internal.mapping.ApiField;
 import com.iwilley.b1ec2.api.internal.mapping.ApiListField;
 import java.util.List;
 public class FinJournalQueryResponse
   extends B1EC2Response
 {
   private static final long serialVersionUID = 4420761874054016003L;
   @ApiField("TotalResults")
   public int totalResults;
   @ApiListField("FinJournals")
   @ApiField("FinJournal")
   private List<FinJournal> finJournals;
   
   public int getTotalResults() {
     return this.totalResults;
   }
   
   public void setTotalResults(int totalResults) {
     this.totalResults = totalResults;
   }
   
   public List<FinJournal> getFinJournals() {
     return this.finJournals;
   }
   
   public void setFinJournals(List<FinJournal> finJournals) {
     this.finJournals = finJournals;
   }
 }


