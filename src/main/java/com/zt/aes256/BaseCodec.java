 package com.zt.aes256;
 
 import java.nio.ByteBuffer;
 import java.nio.charset.Charset;
 import java.util.Random;
 
 public abstract class BaseCodec {
   protected static final Charset CHARSET = Charset.forName("UTF-8");
   
   public abstract byte[] encrypt(byte[] paramArrayOfbyte);
   
   public abstract byte[] decrypt(byte[] paramArrayOfbyte);
   
   public final byte[] encrypt(byte data) {
     return encrypt(new byte[] { data });
   }
   
   public final byte[] encrypt(short data) {
     ByteBuffer buffer = ByteBuffer.allocate(2);
     buffer.putShort(data);
     return encrypt(buffer.array());
   }
   
   public final byte[] encrypt(int data) {
     ByteBuffer buffer = ByteBuffer.allocate(4);
     buffer.putInt(data);
     return encrypt(buffer.array());
   }
   
   public final byte[] encrypt(long data) {
     ByteBuffer buffer = ByteBuffer.allocate(8);
     buffer.putLong(data);
     return encrypt(buffer.array());
   }
   
   public final byte[] encrypt(float data) {
     ByteBuffer buffer = ByteBuffer.allocate(4);
     buffer.putFloat(data);
     return encrypt(buffer.array());
   }
   
   public final byte[] encrypt(double data) {
     ByteBuffer buffer = ByteBuffer.allocate(8);
     buffer.putDouble(data);
     return encrypt(buffer.array());
   }
   
   public final byte[] encrypt(String data) {
     return encrypt(data.getBytes(CHARSET));
   }
   
   public String encryptToStringFromString(String data) {
     return new String(encrypt(data), CHARSET);
   }
   
   public String encryptToString(byte[] data) {
     return new String(encrypt(data), CHARSET);
   }
   
   public final byte[] encrypt(boolean data) {
     byte val = (byte)(new Random()).nextInt(126);
     return encrypt((byte)(data ? (val + 1) : (-val - 1)));
   }
   
   public final byte decryptByte(byte[] value) {
     ByteBuffer buffer = ByteBuffer.wrap(decrypt(value));
     return buffer.get();
   }
   
   public byte[] decryptByteFromString(String value) {
     return decrypt(value.getBytes(CHARSET));
   }
   
   public final short decryptShort(byte[] value) {
     ByteBuffer buffer = ByteBuffer.wrap(decrypt(value));
     return buffer.getShort();
   }
   
   public final int decryptInt(byte[] value) {
     ByteBuffer buffer = ByteBuffer.wrap(decrypt(value));
     return buffer.getInt();
   }
   
   public final long decryptLong(byte[] value) {
     ByteBuffer buffer = ByteBuffer.wrap(decrypt(value));
     return buffer.getLong();
   }
   
   public final float decryptFloat(byte[] value) {
     ByteBuffer buffer = ByteBuffer.wrap(decrypt(value));
     return buffer.getFloat();
   }
   
   public final double decryptDouble(byte[] value) {
     ByteBuffer buffer = ByteBuffer.wrap(decrypt(value));
     return buffer.getDouble();
   }
   
   public final String decryptString(byte[] value) {
     return new String(decrypt(value), CHARSET);
   }
   
   public String decryptStringFromString(String value) {
     return decryptString(value.getBytes(CHARSET));
   }
   
   public final boolean decryptBoolean(byte[] value) {
     return (decryptByte(value) > 0);
   }
 }


