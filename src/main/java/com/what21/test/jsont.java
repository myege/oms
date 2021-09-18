 package com.what21.test;
 
 import com.alibaba.fastjson.JSONArray;
 import com.alibaba.fastjson.JSONObject;
 public class jsont
 {
   public static void main(String[] args) {
     JSONObject jsonObject = new JSONObject();
     
     jsonObject.put("emsNo", "L2923B18A027");
     jsonObject.put("orderNo", "42871885805469");
     jsonObject.put("logisticsNo", "5530296924566");
     jsonObject.put("logisticsCode", "3120980110");
     jsonObject.put("logisticsName", "申通快递有限公司");
     jsonObject.put("copNo", "YWBS39155ZJ7970O3N");
     
     jsonObject.put("assureCode", "3318W6K011");
     jsonObject.put("agentCode", "3318W6K011");
     jsonObject.put("agentName", "义乌海仓供应链管理有限公司");
     jsonObject.put("trafMode", "4");
     jsonObject.put("country", "142");
     jsonObject.put("insuredFee", "0");
     jsonObject.put("grossWeight", "0.31");
     jsonObject.put("netWeight", "0.3");
     
     jsonObject.put("portCode", "111");
     
     JSONArray orderJSONArray = new JSONArray();
     JSONObject jsonObjectlisy = new JSONObject();
     jsonObjectlisy.put("gnum", "1");
     jsonObjectlisy.put("itemSku", "1111");
     jsonObjectlisy.put("itemRecordNo", "339");
     jsonObjectlisy.put("itemNo", "KKS8000500009673");
     jsonObjectlisy.put("gname", "费列罗/FERRERO ROCHER 巧克力T24粒（300g/盒）");
     jsonObjectlisy.put("gcode", "1806310000");
     jsonObjectlisy.put("hsCode", "1806310000");
     jsonObjectlisy.put("gmodel", "300g/盒");
     jsonObjectlisy.put("barCode", "KKS8000500009673");
     jsonObjectlisy.put("country", "307");
     
     jsonObjectlisy.put("currency", "142");
     jsonObjectlisy.put("price", "139");
     jsonObjectlisy.put("qty", "1");
     jsonObjectlisy.put("qty1", "0.3");
     
     jsonObjectlisy.put("totalPrice", "139");
     jsonObjectlisy.put("unit", "140");
     jsonObjectlisy.put("unit1", "035");
 
     
     orderJSONArray.add(jsonObjectlisy);
     jsonObject.put("InventoryList", orderJSONArray);
     String url = "http://apollo.astraea.com.au/cds/ywTaobao/hc/orderCallBack";
     System.out.println(jsonObject.toJSONString());
   }
 }


