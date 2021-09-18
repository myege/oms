 package com.iwilley.b1ec2.api.internal.util;
 
 import java.text.DateFormat;
 import java.text.SimpleDateFormat;
 import java.util.Date;
 import java.util.HashMap;
 import java.util.Map;
 import java.util.TimeZone;
 public class B1EC2HashMap
   extends HashMap<String, String>
 {
   private static final long serialVersionUID = -1277791390393392630L;
   
   public B1EC2HashMap() {}
   
   public B1EC2HashMap(Map<? extends String, ? extends String> m) {
     super((m == null) ? new HashMap<>() : m);
   }
 
   
   public String put(String key, Object value) {
     String strValue;
     if (value == null) {
       strValue = null;
     } else if (value instanceof String) {
       strValue = (String)value;
     } else if (value instanceof Integer) {
       strValue = ((Integer)value).toString();
     } else if (value instanceof Long) {
       strValue = ((Long)value).toString();
     } else if (value instanceof Float) {
       strValue = ((Float)value).toString();
     } else if (value instanceof Double) {
       strValue = ((Double)value).toString();
     } else if (value instanceof Boolean) {
       strValue = ((Boolean)value).toString();
     } else if (value instanceof Date) {
       DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       format.setTimeZone(TimeZone.getTimeZone("GMT+8"));
       strValue = format.format((Date)value);
     } else {
       strValue = value.toString();
     } 
     
     return put(key, strValue);
   }
 
   
   public String put(String key, String value) {
     if (StringUtils.areNotEmpty(new String[] { key, value })) {
       return super.put(key, value);
     }
     return null;
   }
 }


