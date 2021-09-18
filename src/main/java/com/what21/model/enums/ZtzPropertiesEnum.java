 package com.what21.model.enums;
 
 
 
 public enum ZtzPropertiesEnum
 {
   YTO_CLIENTID(
     
     "yto_clientId"),
   YTO_PARTNERID(
     "yto_partnerId"),
   YTO_URL(
     "yto_url"),
   YTO_NAME(
     "yto_name"),
   YTO_PHONE(
     "yto_phone"),
   YTO_POSTCODE(
     "yto_postcode"),
   YTO_PROV(
     "yto_prov"),
   YTO_CITY(
     "yto_city"),
   YTO_ADDRESS(
     "yto_address"),
   
   HTO_PARNERID(
 
     
     "hto_parnerid"),
   HTO_KEY(
     "hto_key"),
   HTO_URL(
     "hto_url"),
   HTO_NAME(
     "hto_name"),
   HTO_PHONE(
     "hto_phone"),
   HTO_POSTCODE(
     "hto_postcode"),
   HTO_PROV(
     "hto_prov"),
   HTO_CITY(
     "hto_city"),
   HTO_COUNTRY(
     "hto_country"),
   HTO_ADDRESS(
     "hto_address"),
   
   BILL_URL(
 
     
     "bill_url"),
   BILL_ENTERPRISE_NAME(
     "bill_enterprise_name"),
   BILL_CUSTOMS_CODE(
     "bill_customs_code"),
   BILL_DXPID(
     "bill_DXPID"),
   BILL_ASE_SECRETKEY(
     "bill_ase_secretkey"),
   BILL_ENTERPRISE_RSA_PRIVATEKEY(
     "bill_enterprise_rsa_privatekey"),
   BILL_ENTERPRISE_RSA_PUBLICKEY(
     "bill_enterprise_rsa_publickey"),
   BILL_RECEIPT_PARSING_SECRETKEY(
     "bill_receipt_parsing_secretkey"),
   
   ORDER_URL(
 
     
     "order_url"),
   ORDER_ENTERPRISE_NAME(
     "order_enterprise_name"),
   ORDER_CUSTOMS_CODE(
     "order_customs_code"),
   ORDER_DXPID(
     "order_DXPID"),
   ORDER_ASE_SECRETKEY(
     "order_ase_secretkey"),
   ORDER_ENTERPRISE_RSA_PRIVATEKEY(
     "order_enterprise_rsa_privatekey"),
   ORDER_ENTERPRISE_RSA_PUBLICKEY(
     "order_enterprise_rsa_publickey"),
   ORDER_RECEIPT_PARSING_SECRETKEY(
     "order_receipt_parsing_secretkey"),
   
   JD_URL(
 
     
     "jd_url"),
   JD_BATCH_QUERY_METHOD_NAME(
     "jd_batch_query_method_name"),
   JD_QUERY_THE_NAME_OF_THE_SPECIFIED_ORDER_METHOD(
     "jd_query_the_name_of_the_specified_order_method"),
   JD_SERVICE_PROVIDER_CALLBACK_METHOD_NAME(
     "jd_service_provider_callback_method_name"),
   JD_NAME_OF_CUSTOMS_CLEARANCE_CALLBACK_METHOD(
     "jd_name_of_customs_clearance_callback_method"),
   JD_ORDER_VALIDITY_METHOD_NAME(
     "jd_order_validity_method_name"),
   JD_BATCH_QUERY_METHOD_NAME2(
     "jd_batch_query_method_name2"),
   JD_BATCH_QUERY_METHOD_NAME3(
     "jd_batch_query_method_name3"),
   JD_APPKEY(
     "jd_appKey"),
   JD_ACCESSTOKEN(
     "jd_accessToken"),
   JD_APP_SECRET(
     "jd_app_secret"),
   YC_URL(
 
     
     "yc_url"),
   YC_COMPANY(
     "yc_company"),
   YC_LOGIN_NAME(
     "yc_login_name"),
   YC_PASSWORD(
     "yc_password"),
   
   CKDM(
     
     "ck_dm"),
   
   CKDMURL("ck_dmurl"),
   
   PFTAES("pftaes"),
   MERCHANTID("merchantId"),
   CUSTOM("custom"),
 
 
   
   BFBMERCHANTID("bfbmerchantid"),
   
   BFBMERCHANTPATH("bfbmerchantpath"),
   
   yhqx("yh_qx");
   
   private String value;
 
   
   ZtzPropertiesEnum(String value) {
     this.value = value;
   }
   
   public String getValue() {
     return this.value;
   }
   
   public void setValue(String value) {
     this.value = value;
   }
 }


