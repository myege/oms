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
<title>浙电API</title>
<%@ include file="/static/common/head.txt"%>
</head>
<body>
<body class="gray-bg">
	<div class="container-div">
		<div class="row">
			<div class="col-sm-12 search-collapse">
				<!-- tab选项卡 -->
				<section class="content container-fluid" id="">
				<div class="row">
					<div class="col-md-12">
						<div class="nav-tabs-custom">
							<ul class="nav nav-tabs">
								<li class="active"><a href="#bill" data-toggle="tab">清单</a></li>
								<li class=""><a href="#order" data-toggle="tab">订单</a></li>
							</ul>
							<div class="tab-content">
							<!-- 清单设置 -->
								<div class="tab-pane active" id="bill">
									 <form method="post" class="form-horizontal" id="billOptions">
										<div class="box-body">
										<div class="form-group">
												<label for="blogLocale"
													class="col-lg-1 col-sm-4 control-label">URL</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="bill_url"
														name="bill_url" value="${options.bill_url}">
												</div>
											</div>
											<div class="form-group">
												<label for="blogLocale"
													class="col-lg-1 col-sm-4 control-label">企业名称</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="bill_enterprise_name"
														name="bill_enterprise_name" value="${options.bill_enterprise_name}">
												</div>
											</div>
											<div class="form-group">
												<label for="blogLocale"
													class="col-lg-1 col-sm-4 control-label">海关十位数编码</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="bill_customs_code"
														name="bill_customs_code" value="${options.bill_customs_code}">
												</div>
											</div>
											<div class="form-group">
												<label for="blogLocale"
													class="col-lg-1 col-sm-4 control-label">DXPID</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="bill_DXPID"
														name="bill_DXPID" value="${options.bill_DXPID}">
												</div>
											</div>
											<div class="form-group">
												<label for="blogLocale"
													class="col-lg-1 col-sm-4 control-label">AES密钥</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="bill_ase_secretkey"
														name="bill_ase_secretkey" value="${options.bill_ase_secretkey}">
												</div>
											</div>
											<div class="form-group">
												<label for="blogLocale"
													class="col-lg-1 col-sm-4 control-label">企业RSA私钥</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="bill_enterprise_rsa_privatekey"
														name="bill_enterprise_rsa_privatekey" value="${options.bill_enterprise_rsa_privatekey}">
												</div>
											</div>
											<div class="form-group">
												<label for="blogLocale"
													class="col-lg-1 col-sm-4 control-label">企业RSA公钥</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="bill_enterprise_rsa_publickey"
														name="bill_enterprise_rsa_publickey" value="${options.bill_enterprise_rsa_publickey}">
												</div>
											</div>
											<div class="form-group">
												<label for="blogLocale"
													class="col-lg-1 col-sm-4 control-label">回执解析密钥</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="bill_receipt_parsing_secretkey"
														name="bill_receipt_parsing_secretkey" value="${options.bill_receipt_parsing_secretkey}">
												</div>
											</div>
											<div class="box-footer col-lg-3">
												<button type="button" class="btn btn-primary col-lg-offset-1 col-md-offset-3"
													onclick="saveOptions('billOptions')">保存设置</button>
											</div>
										</div>
										</form> 
								</div>
								<!-- 订单设置 -->
								<div class="tab-pane" id="order">
									<form method="post" class="form-horizontal" id="orderOptions">
										<div class="box-body">
										<div class="form-group">
												<label for="blogLocale"
													class="col-lg-1 col-sm-4 control-label">URL</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="order_url"
														name="order_url" value="${options.order_url}">
												</div>
											</div>
											<div class="form-group">
												<label for="blogLocale"
													class="col-lg-1 col-sm-4 control-label">企业名称</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="order_enterprise_name"
														name="order_enterprise_name" value="${options.order_enterprise_name}">
												</div>
											</div>
											<div class="form-group">
												<label for="blogLocale"
													class="col-lg-1 col-sm-4 control-label">海关十位数编码</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="order_customs_code"
														name="order_customs_code" value="${options.order_customs_code}">
												</div>
											</div>
											<div class="form-group">
												<label for="blogLocale"
													class="col-lg-1 col-sm-4 control-label">DXPID</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="order_DXPID"
														name="order_DXPID" value="${options.order_DXPID}">
												</div>
											</div>
											<div class="form-group">
												<label for="blogLocale"
													class="col-lg-1 col-sm-4 control-label">AES密钥</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="order_ase_secretkey"
														name="order_ase_secretkey" value="${options.order_ase_secretkey}">
												</div>
											</div>
											<div class="form-group">
												<label for="blogLocale"
													class="col-lg-1 col-sm-4 control-label">企业RSA私钥</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="order_enterprise_rsa_privatekey"
														name="order_enterprise_rsa_privatekey" value="${options.order_enterprise_rsa_privatekey}">
												</div>
											</div>
											<div class="form-group">
												<label for="blogLocale"
													class="col-lg-1 col-sm-4 control-label">企业RSA公钥</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="order_enterprise_rsa_publickey"
														name="order_enterprise_rsa_publickey" value="${options.order_enterprise_rsa_publickey}">
												</div>
											</div>
											<div class="form-group">
												<label for="blogLocale"
													class="col-lg-1 col-sm-4 control-label">回执解析密钥</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="order_receipt_parsing_secretkey"
														name="order_receipt_parsing_secretkey" value="${options.order_receipt_parsing_secretkey}">
												</div>
											</div>
											<div class="box-footer col-lg-3">
												<button type="button" class="btn btn-primary col-lg-offset-1 col-md-offset-3"
													onclick="saveOptions('orderOptions')">保存设置</button>
											</div>
										</div>
										</form> 
								</div>
							</div>
						</div>
					</div>
				</div>
				</section>
			</div>
		</div>
	</div>
	<%@ include file="/static/common/footer.txt"%>
	<script type="text/javascript">
	$(function(){
		//字段验证
		  $("#billOptions").validate({
			rules:{
				bill_url:{
	    			required:true,
	    		},
				bill_enterprise_name:{
	    			required:true,
	    		},
	    		bill_customs_code:{
	    			required:true,
	    		},
	    		bill_DXPID:{
	    			required:true,
	    		},
	    		bill_ase_secretkey:{
	    			required:true,
	    		},
	    		bill_enterprise_rsa_privatekey:{
	    			required:true,
	    		},
	    		bill_enterprise_rsa_publickey:{
	    			required:true,
	    		},
	    		bill_receipt_parsing_secretkey:{
	    			required:true,
	    		}
			}
		}) 
		//字段验证
		 $("#orderOptions").validate({
				rules:{
					order_url:{
		    			required:true,
		    		},
					order_enterprise_name:{
		    			required:true,
		    		},
		    		order_customs_code:{
		    			required:true,
		    		},
		    		order_DXPID:{
		    			required:true,
		    		},
		    		order_ase_secretkey:{
		    			required:true,
		    		},
		    		order_enterprise_rsa_privatekey:{
		    			required:true,
		    		},
		    		order_enterprise_rsa_publickey:{
		    			required:true,
		    		},
		    		order_receipt_parsing_secretkey:{
		    			required:true,
		    		}
				}
			}) 
	});
	
	//保存设置项
	function saveOptions(id) {
		if ($("#"+id).valid()) {
			var from = $("#" + id).serialize();
		   $.ajax({
				type : "post",
				url : "<%=path%>/expressoptions/save.do",
				data : from,
				dataType : "json",
				success : function(data) {
					if(data.code>=0){
						layer.msg(data.msg, {icon: 1});
					}else{
						layer.msg(data.msg, {icon: 2});
					}
				}
			});
		}
	}
	</script>
</body>
</html>