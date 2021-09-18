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
<title>推送回执状态</title>
<%@ include file="/static/common/head.txt"%>
</head>
<body class="white-bg">
	<div class="row">
	<form id="editform">
		<div class="col-xs-12">
			<label class="col-sm-3 control-label ">提运单号：</label>
			<input class="form-control bg-warning" type="text" id="totalMailNo_cz" name="totalMailNo_cz"/>
		</div>
		</form>
	</div>
<%@ include file="/static/common/footer.txt"%>
<script type="text/javascript">	
		$("#editform").validate({
			rules:{
				totalMailNo_cz:{
        			required:true,
        		}
			}
		})
		function submitHandler() {
				var editurl ='<%=basePath%>orderMail/pushCzStatus.do';
				if ($.validate.form()) {
					$.operate.save_OrderMail(editurl, $('#editform').serialize());
				}
	    }
	</script>
</body>
</html>