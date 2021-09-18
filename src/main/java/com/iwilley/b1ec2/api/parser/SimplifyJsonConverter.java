 package com.iwilley.b1ec2.api.parser;
 
 import com.iwilley.b1ec2.api.ApiException;
 import com.iwilley.b1ec2.api.B1EC2Response;
 import com.iwilley.b1ec2.api.internal.mapping.Converter;
 import com.iwilley.b1ec2.api.internal.mapping.Converters;
 import com.iwilley.b1ec2.api.internal.mapping.Reader;
 import com.iwilley.b1ec2.api.internal.util.json.ExceptionErrorListener;
 import com.iwilley.b1ec2.api.internal.util.json.JSONErrorListener;
 import com.iwilley.b1ec2.api.internal.util.json.JSONValidatingReader;
 import java.util.ArrayList;
 import java.util.List;
 import java.util.Map;
 public class SimplifyJsonConverter
   implements Converter
 {
   public <T extends B1EC2Response> T toResponse(String rsp, Class<T> clazz) throws ApiException {
     JSONValidatingReader jSONValidatingReader = new JSONValidatingReader((JSONErrorListener)new ExceptionErrorListener());
     Object rootObj = jSONValidatingReader.read(rsp);
     if (rootObj instanceof Map) {
       return (T)fromJson((Map<?, ?>)rootObj, clazz);
     }
     return null;
   }
   
   public <T> T fromJson(final Map<?, ?> json, Class<T> clazz) throws ApiException {
     return (T)Converters.convert(clazz, new Reader()
         {
           public boolean hasReturnField(Object name) {
             return json.containsKey(name);
           }
 
           
           public Object getPrimitiveObject(Object name) {
             return json.get(name);
           }
 
           
           public Object getObject(Object name, Class<?> type) throws ApiException {
             Object tmp = json.get(name);
             if (tmp instanceof Map) {
               Map<?, ?> map = (Map<?, ?>)tmp;
               return SimplifyJsonConverter.this.fromJson(map, type);
             } 
             return null;
           }
 
 
           
           public List<?> getListObjects(Object listName, Object itemName, Class<?> subType) throws ApiException {
             List<Object> listObjs = null;
             
             Object jsonList = json.get(listName);
             if (jsonList instanceof List) {
               listObjs = new ArrayList();
               List<?> listObj = (List)jsonList;
               for (Object tmp : listObj) {
                 if (tmp instanceof Map) {
                   Map<?, ?> subMap = (Map<?, ?>)tmp;
                   Object subObj = SimplifyJsonConverter.this.fromJson(subMap, subType);
                   if (subObj != null)
                     listObjs.add(subObj);  continue;
                 } 
                 if (!(tmp instanceof List))
                 {
                   
                   listObjs.add(tmp);
                 }
               } 
             } 
             
             return listObjs;
           }
         });
   }
 }


