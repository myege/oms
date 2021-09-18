 package com.zt.ewtp;
 
 import com.gvt.apollo.ApolloSdk;
 import com.gvt.apollo.utils.SecurityUtils;
 import java.security.InvalidKeyException;
 import java.security.NoSuchAlgorithmException;
 import java.security.SignatureException;
 import java.security.spec.InvalidKeySpecException;
 public class ApolloSdkTest
 {
   private static String personPriKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIRfVeYKYQzV5yDd7LnOlobo74nKWinCvBAQ9qtuVeSZCeJxK/DFYj1Wdgga722VqXbZ4i5C2IUazXlloKaPmnFxwZzAUmI17CvdbtDh/ur5ItnDMZnQUkUFxn9dTR0EiqCEmjqGBN9o+qQeL/3ZcKgdbtt8rqP6Pt08qM3lUZFfAgMBAAECgYBolG1n5jFQk7ob5Fk/XvDbuzJsWTssnRY5Vz2aqPwhM6t0hFbjzP3VWfa8ZpNcr44IQRGJ3PP1DPzi+SCYFwI7h3cjLAWXNQluR6PHgOoeKiH0WmUDDwbSDvxPCW4oCsotCy+db1cHFl/XyES4FBrs+x/g7guDX5mqM58+Di2X4QJBAPq4914mavHIH/YgYjMrtdx7tREJQabHxZRbmgdHvoxVR6n8jfWS/8jYQEfUf+ermg0tlh3t5hvDAMHb2XhBze8CQQCHKKHjGFN5UX4lTq8N04iM5HW8Y/SQgt0KCm2JkvN7BUZQ6hmFhUcY8XcvClwMG5+4xEEsPWyp5XKEdF/pn+ORAkEA1FrdiWzYn7h0+a4r/lNDUV+l2KwSYwRJIIMFTq1BgvKoJB24zwqWgrJGn5AoVTxO2mKGBwt8Hn9noMpowyZZGQJAX4wv9ZTq3eboYINhaUrTS5buTIH1EHwSuthoW0tRaPRvox/7btKrUkzRTqXqMH0OytWipR2/RdP4wv5qF4R6oQJAFw+cHi3/8hFtOGgUNuP+kjC3QJq5/nE2sJuyBO42jW4ne6nzxP/k+nQbQB6Rvt3TOwunmGaNLDMCEw15UgLwfw==";
   private static String personPubKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCjywAJeHN5qNngZSYom4wP15ILyvTd/3c6mcgzbV8ACX2qGzAfy/K9lqfeZYuCxqfid9v7NtPxjwi6mMSpc04nsWd3ke+ekX8vVYyUgE83Ywj2L3fIXCYYanKYt9WM7h/WB07YFLrMXLSIzE46u8xGKxgezoC3deGC6DxjPkwTQQIDAQAB";
 
 
   
   public static void main(String[] args) {
     ApolloSdk apolloSdk = new ApolloSdk();
     String json = "{\"appid\":\"ab6391387ed0410ea5f342bac2fe86b5\",\"sign\":\"c+qkLbeFfPFQIhFVj1xrsJZnLmPncBc545NUkoPB1lPMDIRsIwwc8prZcaT2ug+5nnQb36CXU7hAq2TzrnWr6g6NEi4sW9HGXUojUMe4/+MTBrskygxz//kBNLNppIazB/yl/Fg6N1I6l3orDNHHlIFgDV2CizanDPzSPJFsilk=\",\"version\":\"1.0.0\",\"port\":\"2200\",\"patternType\":\"CC\",\"logisticsSubjectDTO\":{\"logisticsNo\":\"WSESZ00012614\",\"domesticTrackNo\":null,\"packetWeight\":0.09,\"recZip\":null,\"receiverAddress\":\"辽宁省大连市普兰店区塞纳名郡\",\"receiverCity\":\"大连市\",\"receiverCountry\":\"中国\",\"receiverCounty\":\"普兰店区\",\"receiverIdNumber\":\"220121198810153522\",\"receiverIdType\":\"1\",\"receiverName\":\"陈小魏\",\"receiverPhone\":\"18840989495\",\"receiverProvince\":\"辽宁省\",\"logisticsName\":\"威盛国际速递\",\"senderAddr\":\"USA CA Commerce 100 Citadel Outlet, #408\",\"senderCity\":\"Commerce\",\"senderCompanyName\":\"Vitamin World\",\"senderCountry\":\"USA\",\"senderName\":\"Petty\",\"senderProvince\":\"CA\",\"senderTel\":\"(323) 721-1197\",\"senderZip\":\"90091\"},\"orderSubjectDTO\":{\"acturalpaid\":39.9,\"callBackUrl\":\"\",\"acturalpaidForeign\":null,\"allCargoTotalPrice\":39.9,\"allCargoTotalTax\":0.0,\"buyerIdNumber\":\"220121198810153522\",\"buyerIdType\":\"1\",\"buyerName\":\"陈小魏\",\"buyerRegNo\":\"8618840989495\",\"buyerTelephone\":\"8618840989495\",\"cargoDescript\":\"儿童纯天然无氟牙膏75g \",\"currency\":\"142\",\"freight\":0.0,\"orderNo\":\"89536773285847077\",\"goodsNumber\":1,\"orderType\":null,\"payMethod\":\"ALIPAY\",\"payTime\":\"2020/02/16 12:40:57\",\"farmTax\":null,\"cargoesList\":[{\"barCode\":\"9415991240617\",\"cargoTotalTax\":0.0,\"country\":null,\"currency\":\"142\",\"foreignPayPrice\":39.9,\"gnum\":null,\"itemDescribe\":null,\"itemName\":\"儿童纯天然无氟牙膏75g\",\"hscode\":null,\"codeTs\":null,\"price\":2.5,\"weight\":0.09,\"qty\":1,\"totalPrice\":2.5,\"spt01\":\"75g-75g_管\",\"itemLink\":null}]},\"paymentSubjectDTO\":{\"currency\":\"CNY\",\"tradeNo\":\"fea2230f-94cc-4370-87a4-faad555bc65a\",\"merchantOrderNo\":\"2020021622001422291420702843\",\"businessId\":\"2020021622001422291420702843\",\"money\":39.9,\"isSendPayment\":true,\"payCompanyCode\":null,\"payCompanyName\":null,\"payChannel\":null,\"payMerchantNo\":\"2020021622001422291420702843\"}}";
     try {
       System.out.println(apolloSdk.wrapSign(SecurityUtils.getPriKey(personPriKey), json));
     
     }
     catch (InvalidKeyException|NoSuchAlgorithmException|InvalidKeySpecException|SignatureException|javax.crypto.NoSuchPaddingException|javax.crypto.BadPaddingException|javax.crypto.IllegalBlockSizeException|java.io.UnsupportedEncodingException e) {
       
       e.printStackTrace();
     } 
     
     String json3 = "{\"appid\":\"rr333sss7788\",\"member\":{\"memberId\":\"11\",\"memberName\":\"张三\"},\"shopLocation\":\"深圳南山新区西丽街道\",\"shopName\":\"apos门店\",\"sign\":\"ezgovCuHJPZ9OH3ixCdZ3Ob2vaEe7tR0nK3l8UUWprFSOucgXuyJrKkFsMjDyJbzNGjRPjhMxAYvzGUig98xSYwGi6JlYXD7hXrAXXbvDWyuTUdCcfbENq8SCvrWOkkrSsCmvdO8PYzPm+KcoTaJ3cDBoVDW58/BS7qQKBntocU=\"}";
     String json2 = "{\"addrCo\":\"浙江省义乌市北苑街道机场路596号一楼\",\"appid\":\"rr333sss7788\",\"sign\":\"QRx1jkPmzp6gFTYHnsuE+TgtRpCL0pJi105caNodJcPxyJTQ1auVM8PgD2mqBtkOLuvBfZciADxauCSWKZ0aTS7xBe/8VVVe06/9IqTwEMXHjdOoXEZQEeWIpR0SHNw9Vrjge7qEfzIm31RuvaZ021bWKvEtyeduXa5lw3wipVs=\",\"version\":\"1.0.0\"}";
     try {
       System.out.println(apolloSdk.validateSign(SecurityUtils.getPubKey(personPubKey), json2));
     } catch (InvalidKeyException e) {
       
       e.printStackTrace();
     } catch (InvalidKeySpecException e) {
       
       e.printStackTrace();
     } catch (NoSuchAlgorithmException e) {
       
       e.printStackTrace();
     } catch (SignatureException e) {
       
       e.printStackTrace();
     } 
   }
 }


