 package com.iwilley.b1ec2.api.response;
 
 import com.iwilley.b1ec2.api.B1EC2Response;
 import com.iwilley.b1ec2.api.domain.Category;
 import com.iwilley.b1ec2.api.internal.mapping.ApiField;
 import com.iwilley.b1ec2.api.internal.mapping.ApiListField;
 import java.util.List;
 
 public class CategoryQueryResponse
   extends B1EC2Response
 {
   private static final long serialVersionUID = 6241000724590992928L;
   @ApiListField("Categories")
   @ApiField("Category")
   private List<Category> categories;
   
   public List<Category> getCategories() {
     return this.categories;
   }
   
   public void setCategories(List<Category> categories) {
     this.categories = categories;
   }
 }


