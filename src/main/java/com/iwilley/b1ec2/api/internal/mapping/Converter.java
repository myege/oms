package com.iwilley.b1ec2.api.internal.mapping;

import com.iwilley.b1ec2.api.ApiException;

public interface Converter {
  <T extends com.iwilley.b1ec2.api.B1EC2Response> T toResponse(String paramString, Class<T> paramClass) throws ApiException;
}


