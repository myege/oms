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
<title>导出</title>
<%@ include file="/static/common/head.txt"%>
</head>
<body class="white-bg">
	<div class="row">
	<form id="editform" action="<%=basePath%>orderBonded/exportOrderBonded.do">
		<div class="col-xs-5">
			<input type="text" id="startTime" name="startTime"	class="form-control" placeholder="开始时间"/>
		</div>
		<div class="col-xs-2">
							<li><span>至</span></li>
		</div>
		<div class="col-xs-5">
			<input type="text" id="endTime" name="endTime"	class="form-control" placeholder="结束时间" />
		</div>
		</form>
	</div>
<%@ include file="/static/common/footer.txt"%>
<script type="text/javascript">	
		$(function(){
			$.operate.time("startTime");
 			$.operate.time("endTime");
		})
		$("#editform").validate({
			rules:{
				startTime:{
        			required:true,
        		}
			}
		})
		function submitHandler() {
				if ($.validate.form()) {
					var v = document.getElementById("startTime");
    				v.value = $("#startTime").val();
    				var v1 = document.getElementById("endTime");
    				v1.value = $("#endTime").val();
    				//提交表单可以这样  
    				var f = document.getElementById("editform");
    				f.submit(); 
				}
	    }
	</script>
</body>
</html>