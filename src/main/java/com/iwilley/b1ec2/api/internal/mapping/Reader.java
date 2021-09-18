package com.iwilley.b1ec2.api.internal.mapping;

import com.iwilley.b1ec2.api.ApiException;
import java.util.List;

public interface Reader {
  boolean hasReturnField(Object paramObject);
  
  Object getPrimitiveObject(Object paramObject);
  
  Object getObject(Object paramObject, Class<?> paramClass) throws ApiException;
  
  List<?> getListObjects(Object paramObject1, Object paramObject2, Class<?> paramClass) throws ApiException;
}


