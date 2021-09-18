 package com.iwilley.b1ec2.api.response;
 
 import com.iwilley.b1ec2.api.B1EC2Response;
 import com.iwilley.b1ec2.api.domain.RefundReason;
 import com.iwilley.b1ec2.api.internal.mapping.ApiField;
 import com.iwilley.b1ec2.api.internal.mapping.ApiListField;
 import java.util.List;
 
 
 public class RefundReasonQueryResponse
   extends B1EC2Response
 {
   private static final long serialVersionUID = 1681860172988485216L;
   @ApiListField("RefundReasons")
   @ApiField("RefundReason")
   private List<RefundReason> refundReasons;
   
   public List<RefundReason> getRefundReasons() {
     return this.refundReasons;
   }
   
   public void setRefundReasons(List<RefundReason> refundReasons) {
     this.refundReasons = refundReasons;
   }
 }


