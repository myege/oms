 package com.iwilley.b1ec2.api.internal.util.json;
 
 public class StdoutStreamErrorListener
   extends BufferErrorListener
 {
   public void end() {
     System.out.print(this.buffer.toString());
   }
 }


