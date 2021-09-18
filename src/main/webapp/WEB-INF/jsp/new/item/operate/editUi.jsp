<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增/修改</title>
<%@ include file="/static/common/head.txt"%>
</head>
<body class="white-bg">
	<div class="row">
	<form id="editform">
		<input name="id" id="id"  type="hidden"  value="${item.id }"/>
		<div class="col-xs-6">
			<label class="col-sm-3 control-label ">商品SKU：</label>
			<input class="form-control bg-warning" type="text" id="itemSKU" name="itemSKU" value="${item.itemSKU }"/>
		</div>
		<div  class="col-xs-6">
			<label class="col-sm-3 control-label">商品条码：</label>
			<input class="form-control" type="text" name="itemcode" id="itemcode" value="${item.itemcode }">
		</div>
		<div class="col-xs-12">
			<label class="col-sm-3 control-label">商品名称：</label>
			<input class="form-control" type="text" name="itemName" id="itemName" value="${item.itemName }">
		</div>
		<div class="col-xs-6">
			<label class="col-sm-3 control-label">商品规格：</label>
			<input class="form-control" type="text" name="itemSpec" id="itemSpec" value="${item.itemSpec }" >
		</div>
		<div  class="col-xs-6">
			<label class="col-sm-3 control-label">单位：</label>
			<input class="form-control" type="text" name="unitDesc" id="unitDesc" value="${item.unitDesc }">
		</div>
		<div class="col-xs-6">
			<label class="col-sm-3 control-label">商家编码：</label>
			<input class="form-control" type="text" name="companyCode" id="companyCode" value="${item.companyCode }">
		</div>
		<div  class="col-xs-6">
			<label class="col-sm-3 control-label">海关备案编号：</label>
			<input class="form-control" type="text" name="hscode" id="hscode" value="${item.hscode }">
		</div>
		<div class="col-xs-6">
			<label class="col-sm-3 control-label">第一计量单位：</label>
			<input class="form-control" type="text" name="oneUnitDesc" id="oneUnitDesc" value="${item.oneUnitDesc }">
		</div>
		<div  class="col-xs-6">
			<label class="col-sm-3 control-label">第二计量单位：</label>
			<input class="form-control" type="text" name="twoUnitDesc" id="twoUnitDesc" value="${item.twoUnitDesc }">
		</div>
		<div class="col-xs-12">
			<label class="col-sm-3 control-label">产品国检备案编号：</label>
			<input class="form-control" type="text" name="productRecordNo" id="productRecordNo" value="${item.productRecordNo }">
		</div>
		<div class="col-xs-6">
			<label class="col-sm-3 control-label">重量(kg)：</label>
			<input class="form-control" type="text" name="unitWeight" id="unitWeight" value="${item.unitWeight }" >
		</div>
		<div  class="col-xs-6">
			<label class="col-sm-3 control-label">税     率：</label>
			<input class="form-control" type="text" name="taxRate" id="taxRate" value="${item.taxRate }">
		</div>
		<div class="col-xs-6">
			<label class="col-sm-3 control-label">原产国编码：</label>
			<input class="form-control" type="text" name="country" id="country" value="${item.country }">
		</div>
		<div  class="col-xs-6">
			<label class="col-sm-3 control-label">金二序号：</label>
			<input class="form-control" type="text" name="internalNumber" id="internalNumber" value="${item.internalNumber }" >
		</div>
		</form>
	</div>
<%@ include file="/static/common/footer.txt"%>
<script type="text/javascript">	
		$("#editform").validate({
			rules:{
				itemSKU:{
        			required:true,
        		},
        		itemcode:{
        			required:true,
        		},
        		itemName:{
        			required:true,
        		},
        		itemSpec:{
        			required:true,
        		},
        		unitDesc:{
        			required:true,
        		},
        		companyCode:{
        			required:true,
        		},
        		hscode:{
        			required:true,
        		},
        		oneUnitDesc:{
        			required:true,
        		},
        		productRecordNo:{
        			required:true,
        		},
        		unitWeight:{
        			required:true,
        		},
        		taxRate:{
        			required:true,
        		},
        		country:{
        			required:true,
        		},
        		internalNumber:{
        			required:true,
        		}
			}
		})
		function submitHandler() {
			
				var editurl ='<%=path%>/item/edit.do';
				//var addurl = '<%=path%>/item/save.do';
				if ($.validate.form()) {
					/* var id=$("#id").val();
					console.log(id == "");
					if(id == ""){
						$.operate.save(addurl, $('#editform').serialize());
					}else{ */
						$.operate.save(editurl, $('#editform').serialize());
					/* } */
	        		
				}
	    }
	</script>
</body>
</html>