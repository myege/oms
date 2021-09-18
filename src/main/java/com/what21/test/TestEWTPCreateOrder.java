 package com.what21.test;
 
 import com.alibaba.fastjson.JSONArray;
 import com.alibaba.fastjson.JSONObject;
 import com.zt.kjybd.PushtoWQ;
 public class TestEWTPCreateOrder
 {
   public static void main(String[] args) {
     String orderNo = "dd000004";
     JSONObject orderJson = new JSONObject();
     orderJson.put("fromto", "vip001");
     orderJson.put("orderNo", orderNo);
     orderJson.put("ebpCode", "电商平台编码");
     orderJson.put("ebcCode", "电商企业编码");
     orderJson.put("ebpName", "电商平台名称");
     orderJson.put("ebcName", "电商企业名称");
     orderJson.put("payCode", "支付企业代码");
     orderJson.put("payName", "支付企业名称");
     orderJson.put("payTransactionId", "支付流水号");
     orderJson.put("buyerIdNumber", "订购人证件号码");
     orderJson.put("buyerName", "姓名");
     orderJson.put("buyerTelephone", "订购人电话");
     orderJson.put("consigneeTelephone", "收件人电话");
     orderJson.put("consignee", "收货人姓名");
     orderJson.put("consigneeAddress", "收货地址");
     orderJson.put("consigneeProvince", "收货省");
     orderJson.put("consigneeCity", "收货市");
     orderJson.put("consigneeCounty", "收货区");
     orderJson.put("logisticsNo", "");
     orderJson.put("logisticsName", "物流企业代码");
     orderJson.put("logisticsCode", "物流平台代码");
     orderJson.put("billNo", "88858695412");
     orderJson.put("voyageNo", "VA/558");
     orderJson.put("tradeMode", "B");
     orderJson.put("trafMode", "5");
     orderJson.put("country", "133");
     orderJson.put("wrapType", "2");
     orderJson.put("goodsValue", "100");
     orderJson.put("freight", "0");
     orderJson.put("insuredFee", "0");
     orderJson.put("grossWeight", "1.02");
     orderJson.put("netWeight", "1");
     
     orderJson.put("sendName", "wangs");
     orderJson.put("sendCountry", "jpan");
     orderJson.put("sendCity", "jud");
     orderJson.put("sendAddress", "csasdasdh akjsda&*(^%$+++");
     orderJson.put("sendTelNo", "15365420-w");
     
     JSONArray jsonArray = new JSONArray();
     
     JSONObject detailDtoList = new JSONObject();
     detailDtoList.put("orderNo", orderNo);
     detailDtoList.put("gname", "商品撒阿克苏打+ask的 按属地");
     detailDtoList.put("itemNo", "630407178691");
     detailDtoList.put("price", Double.valueOf(99.03D));
     detailDtoList.put("qty", Integer.valueOf(1));
     detailDtoList.put("totalPrice", Double.valueOf(99.03D));
     detailDtoList.put("unit", Integer.valueOf(122));
     detailDtoList.put("ItemWeight", Double.valueOf(0.5D));
     detailDtoList.put("country", Integer.valueOf(133));
     jsonArray.add(detailDtoList);
     orderJson.put("detailDtoList", jsonArray);
     
     String parm = orderJson.toJSONString();
     String url = "http://59.111.89.9:8066/hcerp/receiveEwtpData/CreateOrder.do";
     String resout3 = PushtoWQ.sendPost2(url, parm);
   }
 }


