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
<title>快递设置</title>
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
								<li class="active"><a href="#yto" data-toggle="tab">圆通</a></li>
								<li class=""><a href="#hto" data-toggle="tab">百世</a></li>
							</ul>
							<div class="tab-content">
							<!-- 圆通获取单号设置 -->
								<div class="tab-pane active" id="yto">
									 <form method="post" class="form-horizontal" id="ytoOptions">
										<div class="box-body">
											<div class="form-group">
												<label for="blogLocale"
													class="col-lg-1 col-sm-4 control-label">客户编码</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="yto_clientId"
														name="yto_clientId" value="${options.yto_clientId}">
												</div>
											</div>
											<div class="form-group">
												<label for="blogLocale"
													class="col-lg-1 col-sm-4 control-label">密钥</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="yto_partnerId"
														name="yto_partnerId" value="${options.yto_partnerId}">
												</div>
											</div>
											<div class="form-group">
												<label for="blogLocale"
													class="col-lg-1 col-sm-4 control-label">地址</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="yto_url"
														name="yto_url" value="${options.yto_url}">
												</div>
											</div>
											<div class="form-group">
												<label for="blogLocale"
													class="col-lg-1 col-sm-4 control-label">寄件人</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="yto_name"
														name="yto_name" value="${options.yto_name}">
												</div>
											</div>
											<div class="form-group">
												<label for="blogLocale"
													class="col-lg-1 col-sm-4 control-label">寄件电话</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="yto_phone"
														name="yto_phone" value="${options.yto_phone}">
												</div>
											</div>
											<div class="form-group">
												<label for="blogLocale"
													class="col-lg-1 col-sm-4 control-label">寄件邮编</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="yto_postcode"
														name="yto_postcode" value="${options.yto_postcode}">
												</div>
											</div>
											<div class="form-group">
												<label for="blogLocale"
													class="col-lg-1 col-sm-4 control-label">寄件人省</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="yto_prov"
														name="yto_prov" value="${options.yto_prov}">
												</div>
											</div>
											<div class="form-group">
												<label for="blogLocale"
													class="col-lg-1 col-sm-4 control-label">寄件人市</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="yto_city"
														name="yto_city" value="${options.yto_city}">
												</div>
											</div>
											<div class="form-group">
												<label for="blogLocale"
													class="col-lg-1 col-sm-4 control-label">寄件人地址</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="yto_address"
														name="yto_address" value="${options.yto_address}">
												</div>
											</div>
											<div class="box-footer col-lg-3">
												<button type="button" class="btn btn-primary col-lg-offset-1 col-md-offset-3"
													onclick="saveOptions('ytoOptions')">保存设置</button>
											</div>
										</div>
										</form> 
								</div>
								<!-- 百世获取单号设置 -->
								<div class="tab-pane" id="hto">
									<form method="post" class="form-horizontal" id="htoOptions">
										<div class="box-body">
											<div class="form-group">
												<label for="blogLocale"
													class="col-lg-1 col-sm-4 control-label">客户编码</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="hto_parnerid"
														name="hto_parnerid" value="${options.hto_parnerid}">
												</div>
											</div>
											<div class="form-group">
												<label for="blogLocale"
													class="col-lg-1 col-sm-4 control-label">密钥</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="hto_key"
														name="hto_key" value="${options.hto_key}">
												</div>
											</div>
											<div class="form-group">
												<label for="blogLocale"
													class="col-lg-1 col-sm-4 control-label">地址</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="hto_url"
														name="hto_url" value="${options.hto_url}">
												</div>
											</div>
											<div class="form-group">
												<label for="blogLocale"
													class="col-lg-1 col-sm-4 control-label">寄件人</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="hto_name"
														name="hto_name" value="${options.hto_name}">
												</div>
											</div>
											<div class="form-group">
												<label for="blogLocale"
													class="col-lg-1 col-sm-4 control-label">寄件电话</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="hto_phone"
														name="hto_phone" value="${options.hto_phone}">
												</div>
											</div>
											<div class="form-group">
												<label for="blogLocale"
													class="col-lg-1 col-sm-4 control-label">寄件邮编</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="hto_postcode"
														name="hto_postcode" value="${options.hto_postcode}">
												</div>
											</div>
											<div class="form-group">
												<label for="blogLocale"
													class="col-lg-1 col-sm-4 control-label">寄件人省</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="hto_prov"
														name="hto_prov" value="${options.hto_prov}">
												</div>
											</div>
											<div class="form-group">
												<label for="blogLocale"
													class="col-lg-1 col-sm-4 control-label">寄件人市</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="hto_city"
														name="hto_city" value="${options.hto_city}">
												</div>
											</div>
											<div class="form-group">
												<label for="blogLocale"
													class="col-lg-1 col-sm-4 control-label">寄件人区</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="hto_country"
														name="hto_country" value="${options.hto_country}">
												</div>
											</div>
											<div class="form-group">
												<label for="blogLocale"
													class="col-lg-1 col-sm-4 control-label">寄件人地址</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="hto_address"
														name="hto_address" value="${options.hto_address}">
												</div>
											</div>
											<div class="box-footer col-lg-3">
												<button type="button" class="btn btn-primary col-lg-offset-1 col-md-offset-3"
													onclick="saveOptions('htoOptions')">保存设置</button>
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
		//圆通字段验证
		  $("#ytoOptions").validate({
			rules:{
				yto_clientId:{
	    			required:true,
	    		},
	    		yto_partnerId:{
	    			required:true,
	    		},
	    		yto_url:{
	    			required:true,
	    		},
	    		yto_name:{
	    			required:true,
	    		},
	    		yto_phone:{
	    			required:true,
	    		},
	    		yto_postcode:{
	    			required:true,
	    		},
	    		yto_prov:{
	    			required:true,
	    		},
	    		yto_city:{
	    			required:true,
	    		},
	    		yto_address:{
	    			required:true,
	    		}
			}
		}) 
		//百世字段验证
		 $("#htoOptions").validate({
			rules:{
				hto_parnerid:{
	    			required:true,
	    		},
	    		hto_key:{
	    			required:true,
	    		},
	    		hto_url:{
	    			required:true,
	    		},
	    		hto_name:{
	    			required:true,
	    		},
	    		hto_phone:{
	    			required:true,
	    		},
	    		hto_postcode:{
	    			required:true,
	    		},
	    		hto_prov:{
	    			required:true,
	    		},
	    		hto_city:{
	    			required:true,
	    		},
	    		hto_country:{
	    			required:true,
	    		},
	    		hto_address:{
	    			required:true,
	    		}
			}
		}); 
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