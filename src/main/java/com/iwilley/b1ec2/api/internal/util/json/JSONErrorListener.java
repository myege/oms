package com.iwilley.b1ec2.api.internal.util.json;

public interface JSONErrorListener {
  void start(String paramString);
  
  void error(String paramString, int paramInt);
  
  void end();
}


