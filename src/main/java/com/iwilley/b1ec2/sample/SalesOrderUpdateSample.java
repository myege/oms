 package com.iwilley.b1ec2.sample;
 
 import com.iwilley.b1ec2.api.ApiException;
 import com.iwilley.b1ec2.api.B1EC2Client;
 import com.iwilley.b1ec2.api.B1EC2Request;
 import com.iwilley.b1ec2.api.request.SalesOrderUpdateRequest;
 import com.iwilley.b1ec2.api.response.SalesOrderUpdateResponse;
 import java.text.ParseException;
 import java.util.Date;
 
 public class SalesOrderUpdateSample
 {
   public static void main(String[] args) throws ApiException, ParseException {
     B1EC2Client client = new B1EC2Client("http://v2298.ants-city.com/rest", "正琪物流", 
         "乐仓电子商务", "lecang");
     
     SalesOrderUpdateRequest request = new SalesOrderUpdateRequest();
     request.setOrderId(Integer.valueOf(63));
     request.setUserDefinedField1("test1");
     request.setUserDefinedField8(new Date());
     request.setUserDefinedField11("�������һ���Զ����ֶ�");
     
     SalesOrderUpdateResponse response = (SalesOrderUpdateResponse)client.execute((B1EC2Request)request);
     System.out.println(response.getBody());
   }
 }


