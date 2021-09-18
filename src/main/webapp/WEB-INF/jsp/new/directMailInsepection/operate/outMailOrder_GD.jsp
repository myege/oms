<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>推送换单</title>
<%@ include file="/static/common/head.txt"%>
</head>
<body class="white-bg">
	<div class="row">
	<form id="editform" action="<%=basePath%>orderMail/export_Gd.do">
		<div class="col-xs-12">
			<label class="col-sm-3 control-label ">提运单号：</label>
			<input class="form-control bg-warning" type="text" id="totalMailNo" name="totalMailNo"/>
		</div>
		</form>
	</div>
<%@ include file="/static/common/footer.txt"%>
<script type="text/javascript">	
		$("#editform").validate({
			rules:{
				totalMailNo:{
        			required:true,
        		}
			}
		})
		function submitHandler(index) {
				if ($.validate.form()) {
					var v = document.getElementById("totalMailNo");
    				v.value = $("#totalMailNo").val();
    				//提交表单可以这样  
    				var f = document.getElementById("editform");
    				f.submit(); 
				}
	    }
	</script>
</body>
</html>