 package com.iwilley.b1ec2.sample;
 
 import com.iwilley.b1ec2.api.ApiException;
 import com.iwilley.b1ec2.api.B1EC2Client;
 import com.iwilley.b1ec2.api.B1EC2Request;
 import com.iwilley.b1ec2.api.domain.AfterSaleServiceExchangeAndRedeliveryItemLine;
 import com.iwilley.b1ec2.api.domain.AfterSaleServiceReturnItemLine;
 import com.iwilley.b1ec2.api.request.AfterSalesServiceCreateRequest;
 import com.iwilley.b1ec2.api.response.AfterSalesServiceCreateResponse;
 import java.util.ArrayList;
 import java.util.List;
 
 public class AfterSalesServiceCreateSample {
   public static void main(String[] args) throws ApiException {
     B1EC2Client client = new B1EC2Client("http://v2298.ants-city.com/rest", "正琪物流", 
         "乐仓电子商务", "lecang");
     AfterSalesServiceCreateRequest request = new AfterSalesServiceCreateRequest();
     
     request.setOrderNo("20150505000799");
     request.setRefundMemo("java test");
     request.setType(30);
     request.setReceiverName("����ɮ");
     request.setReceiverState("����");
     request.setReceiverCity("������");
     request.setReceiverDistrict("������");
     request.setReceiverAddress("�½ֿ�����4-103");
     request.setReceiverMobile("18614088260");
     request.setUserDefinedField1("userDefinedField1");
     request.setUserDefinedField2("userDefinedField2");
     request.setUserDefinedField3("userDefinedField3");
     request.setUserDefinedField4("userDefinedField4");
     
     List<AfterSaleServiceReturnItemLine> returnLines = new ArrayList<>();
     AfterSaleServiceReturnItemLine afterSaleServiceReturnItemLine1 = new AfterSaleServiceReturnItemLine();
     afterSaleServiceReturnItemLine1.setOrderLineNum(0);
     afterSaleServiceReturnItemLine1.setQuantity(1);
     AfterSaleServiceReturnItemLine afterSaleServiceReturnItemLine2 = new AfterSaleServiceReturnItemLine();
     afterSaleServiceReturnItemLine2.setOrderLineNum(1);
     afterSaleServiceReturnItemLine2.setQuantity(2);
     returnLines.add(afterSaleServiceReturnItemLine1);
     returnLines.add(afterSaleServiceReturnItemLine2);
     request.setReturnItemLines(returnLines);
     List<AfterSaleServiceExchangeAndRedeliveryItemLine> exchangeAndRedeliveryItemLines = new ArrayList<>();
     AfterSaleServiceExchangeAndRedeliveryItemLine afterSaleServiceExchangeAndRedeliveryItemLine2 = new AfterSaleServiceExchangeAndRedeliveryItemLine();
     afterSaleServiceExchangeAndRedeliveryItemLine2.setOrderLineNum(0);
     afterSaleServiceExchangeAndRedeliveryItemLine2.setQuantity(1);
     exchangeAndRedeliveryItemLines.add(afterSaleServiceExchangeAndRedeliveryItemLine2);
     request.setExchangeAndRedeliveryItemLines(exchangeAndRedeliveryItemLines);
     
     AfterSalesServiceCreateResponse response = (AfterSalesServiceCreateResponse)client.execute((B1EC2Request)request);
     System.out.println(response.getBody());
     System.out.println("errorCode:" + response.getErrorCode() + ",errorMessage" + response.getErrorMsg());
   }
 }


