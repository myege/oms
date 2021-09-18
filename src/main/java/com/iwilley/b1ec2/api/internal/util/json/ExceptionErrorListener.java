 package com.iwilley.b1ec2.api.internal.util.json;
 
 public class ExceptionErrorListener
   extends BufferErrorListener
 {
   public void error(String type, int col) {
     super.error(type, col);
     throw new IllegalArgumentException(this.buffer.toString());
   }
 }


