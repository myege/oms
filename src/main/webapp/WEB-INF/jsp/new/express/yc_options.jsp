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
<title>拼多多授权</title>
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
								<li class="active"><a href="#yc" data-toggle="tab">拼多多授权API</a></li>
							</ul>
							<div class="tab-content">
							<!-- 清单设置 -->
								<div class="tab-pane active" id="yc">
									 <form method="post" class="form-horizontal" id="ycOptions">
										<div class="box-body">
											<div class="form-group">
												<label for="blogLocale"
													class="col-lg-1 col-sm-4 control-label">client_id</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="yc_url"
														name="yc_url" value="${options.yc_url}">
												</div>
											</div>
											<div class="form-group">
												<label for="blogLocale"
													class="col-lg-1 col-sm-4 control-label">client_secret</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="yc_company"
														name="yc_company" value="${options.yc_company}">
												</div>
											</div>
											<div class="form-group">
												<label for="blogLocale"
													class="col-lg-1 col-sm-4 control-label">code</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="yc_company"
														name="yc_company" value="4150633ff32b4f6395d8aa408d8d546795309c31">
												</div>
											</div>
											
											<div class="form-group">
												<label for="blogLocale"
													class="col-lg-1 col-sm-4 control-label">access_token</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="yc_login_name"
														name="yc_login_name" value="${options.yc_login_name}">
												</div>
											</div>
											<div class="form-group">
												<label for="blogLocale"
													class="col-lg-1 col-sm-4 control-label">refresh_token</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="yc_password"
														name="yc_password" value="${options.yc_password}">
												</div>
											</div>
											<div class="form-group">
												<label for="blogLocale"
													class="col-lg-1 col-sm-4 control-label">expires_in</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="yc_password"
														name="yc_password" value="80253">
												</div>
											</div>
											<div class="form-group">
												<label for="blogLocale"
													class="col-lg-1 col-sm-4 control-label">owner_id</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="yc_password"
														name="yc_password" value="361267312">
												</div>
											</div>
											<div class="form-group">
												<label for="blogLocale"
													class="col-lg-1 col-sm-4 control-label">owner_name</label>
												<div class="col-lg-4 col-sm-8">
													<input type="text" class="form-control" id="yc_password"
														name="yc_password" value="pdd36126731221">
												</div>
											</div>
											<div class="box-footer col-lg-3">
												<button type="button" class="btn btn-primary col-lg-offset-1 col-md-offset-3"
													onclick="saveOptions('ycOptions')">保存设置</button>
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
		 $("#ycOptions").validate({
				rules:{
					yc_url:{
		    			required:true,
		    		},
		    		yc_company:{
		    			required:true,
		    		},
		    		yc_login_name:{
		    			required:true,
		    		},
		    		yc_password:{
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