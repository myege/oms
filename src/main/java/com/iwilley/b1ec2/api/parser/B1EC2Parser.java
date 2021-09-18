package com.iwilley.b1ec2.api.parser;

import com.iwilley.b1ec2.api.ApiException;

public interface B1EC2Parser<T extends com.iwilley.b1ec2.api.B1EC2Response> {
  T parse(String paramString) throws ApiException;
  
  Class<T> getResponseClass() throws ApiException;
}


