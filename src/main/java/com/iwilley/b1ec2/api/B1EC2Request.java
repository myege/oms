package com.iwilley.b1ec2.api;

import java.util.Map;

public interface B1EC2Request<T extends B1EC2Response> {
  String getApiMethodName();
  
  Map<String, String> GetParameters();
  
  Class<T> getResponseClass();
}


