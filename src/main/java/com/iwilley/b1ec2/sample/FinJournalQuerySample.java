 package com.iwilley.b1ec2.sample;
 
 import com.iwilley.b1ec2.api.ApiException;
 import com.iwilley.b1ec2.api.B1EC2Client;
 import com.iwilley.b1ec2.api.B1EC2Request;
 import com.iwilley.b1ec2.api.domain.FinJournal;
 import com.iwilley.b1ec2.api.request.FinJournalQueryRequest;
 import com.iwilley.b1ec2.api.response.FinJournalQueryResponse;
 import java.text.DateFormat;
 import java.text.ParseException;
 import java.text.SimpleDateFormat;
 import java.util.Iterator;
 
 public class FinJournalQuerySample {
   public static void main(String[] args) throws ApiException, ParseException {
     B1EC2Client client = new B1EC2Client("http://v2298.ants-city.com/rest", "正琪物流", 
         "乐仓电子商务", "lecang");
     
     int pageSize = 5;
     DateFormat format = new SimpleDateFormat(
         "yyyy-MM-dd HH:mm:ss");
     
     FinJournalQueryRequest request = new FinJournalQueryRequest();
     request.setStartTime(format.parse("2014-07-22 00:00:00"));
     request.setEndTime(format.parse("2015-08-16 00:00:00"));
     request.setPageSize(Integer.valueOf(pageSize));
     
     FinJournalQueryResponse response = (FinJournalQueryResponse)client.execute((B1EC2Request)request);
     System.out.println(response.getBody());
     System.out.println("�����:" + response.getTotalResults());
     
     if (response.getErrorCode() == 0 && response.getTotalResults() > 0) {
       
       int totalPages = (int)Math.ceil(response
           .getTotalResults() / pageSize);
       totalPages = (totalPages > 5) ? 5 : totalPages;
       
       for (int i = totalPages; i >= 1; i--) {
         request.setPageNum(Integer.valueOf(i));
         response = (FinJournalQueryResponse)client.execute((B1EC2Request)request);
         System.out.println("����ҳ��:" + i + "/" + totalPages);
 
         
         Iterator<FinJournal> iterator = response.getFinJournals().iterator(); while (iterator.hasNext()) { FinJournal finJournal = iterator.next();
           System.out.println("������Ϣ:" + 
               finJournal.getFinJournalId() + "," + 
               finJournal.getAccountCode() + "," + 
               finJournal.getFinAccount().getAccountName()); }
 
         
         System.out.println();
       } 
     } 
   }
 }


