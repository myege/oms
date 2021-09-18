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
<title>京东API</title>
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
								<li class="active"><a href="#jd" data-toggle="tab">京东API</a></li>
							</ul>
							<div class="tab-content">
							<!-- 京东API设置 -->
								<div class="tab-pane active" id="jd">
									 <form method="post" class="form-horizontal" id="jdOptions">
										<div class="box-body">
											<div class="form-group">
												<label for="blogLocale"
													class="col-lg-2 col-sm-4 control-label">URL</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="jd_url"
														name="jd_url" value="${options.jd_url}">
												</div>
											</div>
											<div class="form-group">
												<label for="blogLocale"
													class="col-lg-2 col-sm-4 control-label">批量查询方法名称</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="jd_batch_query_method_name"
														name="jd_batch_query_method_name" value="${options.jd_batch_query_method_name}">
												</div>
											</div>
											<div class="form-group">
												<label for="blogLocale"
													class="col-lg-2 col-sm-4 control-label">查询指定订单方法名称</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="jd_query_the_name_of_the_specified_order_method"
														name="jd_query_the_name_of_the_specified_order_method" value="${options.jd_query_the_name_of_the_specified_order_method}">
												</div>
											</div>
											<div class="form-group">
												<label for="blogLocale"
													class="col-lg-2 col-sm-4 control-label">服务商回调方法名称</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="jd_service_provider_callback_method_name"
														name="jd_service_provider_callback_method_name" value="${options.jd_service_provider_callback_method_name}">
												</div>
											</div>
											<div class="form-group">
												<label for="blogLocale"
													class="col-lg-2 col-sm-4 control-label">清关回调方法名称</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="jd_name_of_customs_clearance_callback_method"
														name="jd_name_of_customs_clearance_callback_method" value="${options.jd_name_of_customs_clearance_callback_method}">
												</div>
											</div>
											<div class="form-group">
												<label for="blogLocale"
													class="col-lg-2 col-sm-4 control-label">订单是否有效方法名称</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="jd_order_validity_method_name"
														name="jd_order_validity_method_name" value="${options.jd_order_validity_method_name}">
												</div>
											</div>
											<div class="form-group">
												<label for="blogLocale"
													class="col-lg-2 col-sm-4 control-label">批量查询方法名称</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="jd_batch_query_method_name2"
														name="jd_batch_query_method_name2" value="${options.jd_batch_query_method_name2}">
												</div>
											</div>
											<div class="form-group">
												<label for="blogLocale"
													class="col-lg-2 col-sm-4 control-label">批量查询方法名称</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="jd_batch_query_method_name3"
														name="jd_batch_query_method_name3" value="${options.jd_batch_query_method_name3}">
												</div>
											</div>
											<div class="form-group">
												<label for="blogLocale"
													class="col-lg-2 col-sm-4 control-label">appKey</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="jd_appKey"
														name="jd_appKey" value="${options.jd_appKey}">
												</div>
											</div>
											<div class="form-group">
												<label for="blogLocale"
													class="col-lg-2 col-sm-4 control-label">accessToken</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="jd_accessToken"
														name="jd_accessToken" value="${options.jd_accessToken}">
												</div>
											</div>
											<div class="form-group">
												<label for="blogLocale"
													class="col-lg-2 col-sm-4 control-label">app_secret</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="jd_app_secret"
														name="jd_app_secret" value="${options.jd_app_secret}">
												</div>
											</div>
											<div class="box-footer col-lg-3">
												<button type="button" class="btn btn-primary col-lg-offset-1 col-md-offset-3"
													onclick="saveOptions('jdOptions')">保存设置</button>
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
		 $("#jdOptions").validate({
				rules:{
					jd_url:{
		    			required:true,
		    		},
		    		jd_batch_query_method_name:{
		    			required:true,
		    		},
		    		jd_query_the_name_of_the_specified_order_method:{
		    			required:true,
		    		},
		    		jd_service_provider_callback_method_name:{
		    			required:true,
		    		},
		    		jd_name_of_customs_clearance_callback_method:{
		    			required:true,
		    		},
		    		jd_order_validity_method_name:{
		    			required:true,
		    		},
		    		jd_batch_query_method_name2:{
		    			required:true,
		    		},
		    		jd_batch_query_method_name3:{
		    			required:true,
		    		},
		    		jd_appKey:{
		    			required:true,
		    		},
		    		jd_accessToken:{
		    			required:true,
		    		},
		    		jd_app_secret:{
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