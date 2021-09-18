 package com.iwilley.b1ec2.api.internal.mapping;
 
 import com.iwilley.b1ec2.api.ApiException;
 import com.iwilley.b1ec2.api.B1EC2Response;
 import com.iwilley.b1ec2.api.internal.util.StringUtils;
 import java.beans.BeanInfo;
 import java.beans.Introspector;
 import java.beans.PropertyDescriptor;
 import java.lang.reflect.Field;
 import java.lang.reflect.Method;
 import java.lang.reflect.ParameterizedType;
 import java.lang.reflect.Type;
 import java.text.DateFormat;
 import java.text.ParseException;
 import java.text.SimpleDateFormat;
 import java.util.Date;
 import java.util.HashSet;
 import java.util.List;
 import java.util.Map;
 import java.util.Set;
 import java.util.TimeZone;
 import java.util.concurrent.ConcurrentHashMap;
 public class Converters
 {
   public static boolean isCheckJsonType = false;
   private static final Set<String> baseFields = new HashSet<>();
   private static final Map<String, Field> fieldCache = new ConcurrentHashMap<>();
   
   static {
     baseFields.add("errorCode");
     baseFields.add("errorMsg");
     baseFields.add("body");
   }
   
   public static <T> T convert(Class<T> clazz, Reader reader) throws ApiException {
     T rsp = null;
     
     try {
       rsp = clazz.newInstance();
       BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
       PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors(); byte b; int i;
       PropertyDescriptor[] arrayOfPropertyDescriptor1;
       for (i = (arrayOfPropertyDescriptor1 = pds).length, b = 0; b < i; ) { PropertyDescriptor pd = arrayOfPropertyDescriptor1[b];
         Method method = pd.getWriteMethod();
         if (method != null) {
           Field field;
 
           
           String itemName = pd.getName();
           String listName = null;
 
           
           if (baseFields.contains(itemName) && B1EC2Response.class.isAssignableFrom(clazz)) {
             field = getField(B1EC2Response.class, pd);
           } else {
             field = getField(clazz, pd);
           } 
           
           ApiField jsonField = field.<ApiField>getAnnotation(ApiField.class);
           if (jsonField != null) {
             itemName = jsonField.value();
           }
           ApiListField jsonListField = field.<ApiListField>getAnnotation(ApiListField.class);
           if (jsonListField != null) {
             listName = jsonListField.value();
           }
           
           if (reader.hasReturnField(itemName) || (
             listName != null && reader.hasReturnField(listName))) {
 
 
 
             
             Class<?> typeClass = field.getType();
             
             if (String.class.isAssignableFrom(typeClass))
             { Object value = reader.getPrimitiveObject(itemName);
               if (value instanceof String) {
                 method.invoke(rsp, new Object[] { value.toString() });
               } else {
                 if (isCheckJsonType && value != null) {
                   throw new ApiException(String.valueOf(itemName) + " is not a String");
                 }
                 if (value != null) {
                   method.invoke(rsp, new Object[] { value.toString() });
                 } else {
                   method.invoke(rsp, new Object[] { "" });
                 } 
               }  }
             else if (long.class.isAssignableFrom(typeClass) || Long.class.isAssignableFrom(typeClass))
             { Object value = reader.getPrimitiveObject(itemName);
               if (value instanceof Long) {
                 method.invoke(rsp, new Object[] { value });
               } else {
                 if (isCheckJsonType && value != null) {
                   throw new ApiException(String.valueOf(itemName) + " is not a Number(Long)");
                 }
                 if (StringUtils.isNumeric(value)) {
                   method.invoke(rsp, new Object[] { Long.valueOf(value.toString()) });
                 }
               }  }
             else if (int.class.isAssignableFrom(typeClass) || Integer.class.isAssignableFrom(typeClass))
             { Object value = reader.getPrimitiveObject(itemName);
               if (value instanceof Integer) {
                 method.invoke(rsp, new Object[] { value });
               } else {
                 if (isCheckJsonType && value != null) {
                   throw new ApiException(String.valueOf(itemName) + " is not a Number(Integer)");
                 }
                 if (StringUtils.isNumeric(value)) {
                   method.invoke(rsp, new Object[] { Integer.valueOf(value.toString()) });
                 }
               }  }
             else if (boolean.class.isAssignableFrom(typeClass) || Boolean.class.isAssignableFrom(typeClass))
             { Object value = reader.getPrimitiveObject(itemName);
               if (value instanceof Boolean) {
                 method.invoke(rsp, new Object[] { value });
               } else {
                 if (isCheckJsonType && value != null) {
                   throw new ApiException(String.valueOf(itemName) + " is not a Boolean");
                 }
                 if (value != null) {
                   method.invoke(rsp, new Object[] { Boolean.valueOf(value.toString()) });
                 }
               }  }
             else if (double.class.isAssignableFrom(typeClass) || Double.class.isAssignableFrom(typeClass))
             { Object value = reader.getPrimitiveObject(itemName);
               if (value instanceof Double) {
                 method.invoke(rsp, new Object[] { value });
               }
               else if (isCheckJsonType && value != null) {
                 throw new ApiException(String.valueOf(itemName) + " is not a Double");
               }
                }
             else if (Number.class.isAssignableFrom(typeClass))
             { Object value = reader.getPrimitiveObject(itemName);
               if (value instanceof Number) {
                 method.invoke(rsp, new Object[] { value });
               }
               else if (isCheckJsonType && value != null) {
                 throw new ApiException(String.valueOf(itemName) + " is not a Number");
               }
                }
             else if (Date.class.isAssignableFrom(typeClass))
             { Object value = reader.getPrimitiveObject(itemName);
               if (value instanceof String) {
                 Date dateValue;
                 try {
                   DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                   format.setTimeZone(TimeZone.getTimeZone("GMT+8"));
                   dateValue = format.parse(value.toString());
                 } catch (ParseException e) {
                   DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                   format.setTimeZone(TimeZone.getTimeZone("GMT+8"));
                   dateValue = format.parse(value.toString());
                 } 
                 method.invoke(rsp, new Object[] { dateValue });
               }  }
             else if (List.class.isAssignableFrom(typeClass))
             { Type fieldType = field.getGenericType();
               if (fieldType instanceof ParameterizedType) {
                 ParameterizedType paramType = (ParameterizedType)fieldType;
                 Type[] genericTypes = paramType.getActualTypeArguments();
                 if (genericTypes != null && genericTypes.length > 0 && 
                   genericTypes[0] instanceof Class) {
                   Class<?> subType = (Class)genericTypes[0];
                   List<?> listObjs = reader.getListObjects(listName, itemName, subType);
                   if (listObjs != null) {
                     method.invoke(rsp, new Object[] { listObjs });
                   }
                 } 
               }  }
             else
             
             { Object obj = reader.getObject(itemName, typeClass);
               if (obj != null)
                 method.invoke(rsp, new Object[] { obj });  } 
           } 
         }  b++; }
     
     } catch (Exception e) {
       throw new ApiException(e);
     } 
     
     return rsp;
   }
   
   private static Field getField(Class<?> clazz, PropertyDescriptor pd) throws Exception {
     String key = String.valueOf(clazz.getName()) + "_" + pd.getName();
     Field field = fieldCache.get(key);
     if (field == null) {
       field = clazz.getDeclaredField(pd.getName());
       fieldCache.put(key, field);
     } 
     return field;
   }
 }


