<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增/修改</title>
<%@ include file="/static/common/head.txt"%>
</head>
<body class="white-bg">
<div class="col-sm-12 select-table table-striped">
	 <form method="post" class="form-horizontal" id="fiveWebFrom">
	 <input type="text" hidden="" name="begCode" value="5">
	<div class="box-body">
		<div class="form-group">
			<label for="blogLocale"
				class="col-lg-1 col-sm-4 control-label">第一个标签内容</label>
			<div class="col-lg-4 col-sm-8">
				<input type="text" class="form-control" id="qualityText1"
					name="qualityText1" value="${qualityText1}">
			</div>
		</div>
		<div class="form-group">
			<label for="blogLocale"
				class="col-lg-1 col-sm-4 control-label">第一个标签内容</label>
			<div class="col-lg-4 col-sm-8">
				<input type="text" class="form-control" id="qualityText2"
					name="qualityText2" value="${qualityText2}">
			</div>
		</div>
		<div class="form-group">
			<label for="blogLocale"
				class="col-lg-1 col-sm-4 control-label">第一个标签内容</label>
			<div class="col-lg-4 col-sm-8">
				<input type="text" class="form-control" id="qualityText3"
					name="qualityText3" value="${qualityText3}">
			</div>
		</div>
		<div class="box-footer col-lg-3">
			<button type="button" class="btn btn-primary col-lg-offset-1 col-md-offset-3"
				onclick="editImgText()">保存设置</button>
		</div>
	</div>
</form> 
</div>				
<%@ include file="/static/common/footer.txt"%>

<script>   
function editImgText(id) {
	var editurl ='<%=path%>/Welcome/editTowFont.do';
	$.ajax({
		url:editurl,
		type:'post',
		data:$('#'+id).serialize(),
		success:function(r){
			if(r=="ok"){
				layer.msg("保存成功", {
      			  icon: 1,
      			  time: 700 //2秒关闭（如果不配置，默认是3秒）
      			}, function(index){
      				layer.close(index); 
      			
      			
      	})
			}
		}
	})
}
	
</script>
</body>
</html>