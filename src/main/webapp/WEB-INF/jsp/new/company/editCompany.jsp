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
<title>编辑商家</title>
<%@ include file="/static/common/head.txt"%>
</head>
<body class="">
	<div class="row">
	<form id="editform">
		<input name="id" id="id"  type="hidden"  value="${company.id }"/>
		 <div class="col-xs-6">
			<label class="col-sm-3 control-label ">推送主体编码：</label>
			<input class="form-control bg-warning" type="text" id="companytszt" name="companytszt" value="${company.companytszt }"/>
		</div>
		<div  class="col-xs-6">
			<label class="col-sm-3 control-label">公司编码：</label>
			<input class="form-control" type="text" name="companybm" id="companybm" value="${company.companybm }">
		</div>
		<div class="col-xs-6">
			<label class="col-sm-3 control-label">电商平台名称：</label>
			<input class="form-control" type="text" name="companyName" id="companyName" value="${company.companyName }">
		</div>
		<div class="col-xs-6">
			<label class="col-sm-3 control-label">电商平台代码：</label>
			<input class="form-control" type="text" name="companyCode" id="companyCode" value="${company.companyCode }" >
		</div>
		<div  class="col-xs-6">
			<label class="col-sm-3 control-label">支付公司编码：</label>
			<input class="form-control" type="text" name="payCompanyCode" id="payCompanyCode" value="${company.payCompanyCode }">
		</div>
		<div class="col-xs-6">
			<label class="col-sm-3 control-label">电商企业名称：</label>
			<input class="form-control" type="text" name="eCommerceName" id="eCommerceName" value="${company.eCommerceName }">
		</div>
		<div  class="col-xs-6">
			<label class="col-sm-3 control-label">电商企业编码：</label>
			<input class="form-control" type="text" name="eCommerceCode" id="eCommerceCode" value="${company.eCommerceCode }">
		</div>
		<div class="col-xs-6">
			<label class="col-sm-3 control-label">物流企业名称：</label>
			<input class="form-control" type="text" name="logisCompanyName" id="logisCompanyName" value="${company.logisCompanyName }">
		</div>
		<div  class="col-xs-6">
			<label class="col-sm-3 control-label">物流企业编号：</label>
			<input class="form-control" type="text" name="logisCompanyCode" id="logisCompanyCode" value="${company.logisCompanyCode }">
		</div>
		<div class="col-xs-6">
			<label class="col-sm-3 control-label">申报企业名称：</label>
			<input class="form-control" type="text" name="declareCompanyName" id="declareCompanyName" value="${company.declareCompanyName }">
		</div>
		<div class="col-xs-6">
			<label class="col-sm-3 control-label">申报企业代码：</label>
			<input class="form-control" type="text" name="declareCompanyCode" id="declareCompanyCode" value="${company.declareCompanyCode }" >
		</div>
		<div  class="col-xs-6">
			<label class="col-sm-3 control-label">申请单编号：</label>
			<input class="form-control" type="text" name="applicationFormNo" id="applicationFormNo" value="${company.applicationFormNo }">
		</div>
		<div class="col-xs-6">
			<label class="col-sm-3 control-label">区内企业代码：</label>
			<input class="form-control" type="text" name="internalAreaCompanyNo" id="internalAreaCompanyNo" value="${company.internalAreaCompanyNo }">
		</div>
		<div  class="col-xs-6">
			<label class="col-sm-3 control-label">区内企业名称：</label>
			<input class="form-control" type="text" name="internalAreaCompanyName" id="internalAreaCompanyName" value="${company.internalAreaCompanyName }" >
		</div>
		<div  class="col-xs-6">
			<label class="col-sm-3 control-label">担保企业编号：</label>
			<input class="form-control" type="text" name="assureCode" id="assureCode" value="${company.assureCode }" >
		</div>
		<div  class="col-xs-6">
			<label class="col-sm-3 control-label">监管场所代码：</label>
			<input class="form-control" type="text" name="customsField" id="customsField" value="${company.customsField }" >
		</div>
		<div  class="col-xs-6">
			<label class="col-sm-3 control-label">申报地海关代码：</label>
			<input class="form-control" type="text" name="declPort" id="declPort" value="${company.declPort }" >
		</div>
		<div  class="col-xs-6">
			<label class="col-sm-3 control-label">账册编号：</label>
			<input class="form-control" type="text" name="accountBookNo" id="accountBookNo" value="${company.accountBookNo }" >
		</div>
		<div  class="col-xs-6">
			<label class="col-sm-3 control-label">起运国：</label>
			<input class="form-control" type="text" name="country" id="country" value="${company.country}" >
		</div>
		<div  class="col-xs-6">
			<label class="col-sm-3 control-label">发件人：</label>
			<input class="form-control" type="text" name="sendName" id="sendName" value="${company.sendName }" >
		</div>
		<div  class="col-xs-6">
			<label class="col-sm-3 control-label">发件人电话：</label>
			<input class="form-control" type="text" name="sendTel" id="sendTel" value="${company.sendTel }" >
		</div>
		<div  class="col-xs-6">
			<label class="col-sm-3 control-label">发件人地址：</label>
			<input class="form-control" type="text" name="sendAddress" id="sendAddress" value="${company.sendAddress }" >
		</div>
		</form>
	</div>
<%@ include file="/static/common/footer.txt"%>
<script type="text/javascript">	
		$("#editform").validate({
			rules:{
				companytszt:{
        			required:true,
        		},
        		companybm:{
        			required:true,
        		},
        		companyName:{
        			required:true,
        		},
        		companyCode:{
        			required:true,
        		},
        		payCompanyCode:{
        			required:true,
        		},
        		eCommerceName:{
        			required:true,
        		},
        		eCommerceCode:{
        			required:true,
        		},
        		logisCompanyName:{
        			required:true,
        		},
        		declareCompanyName:{
        			required:true,
        		},
        		declareCompanyCode:{
        			required:true,
        		},
        		internalAreaCompanyNo:{
        			required:true,
        		},
        		internalAreaCompanyName:{
        			required:true,
        		},
        		assureCode:{
        			required:true,
        		},
        		customsField:{
        			required:true,
        		},
        		declPort:{
        			required:true,
        		},
        		accountBookNo:{
        			required:true,
        		},
        		country:{
        			required:true,
        		},
        		sendName:{
        			required:true,
        		},
        		sendTel:{
        			required:true,
        		},
        		sendAddress:{
        			required:true,
        		}
			}
		})
		function submitHandler() {
			var editurl ='<%=path%>/company/edit.do';
			if ($.validate.form()) {
					$.operate.save(editurl, $('#editform').serialize());
			}
	    }
	</script>
</body>
</html>