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
<title>理货单获取</title>
<%@ include file="/static/common/head.txt"%>
</head>
<body class="white-bg">
	<div class="row">
	<form id="editform">
		<div class="col-xs-12">
			<label class="col-sm-3 control-label ">京东采购单</label>
			<input class="form-control bg-warning" type="text" id="totalMailNo" name="totalMailNo"/>
		</div>
		<div class="col-xs-12">
			<label class="col-sm-3 control-label ">服务商编号</label>
			<input class="form-control bg-warning" type="text" id="totalMailNoNew" name="totalMailNoNew"/>
		</div>
		
	</form>
	</div>
<%@ include file="/static/common/footer.txt"%>
<script type="text/javascript">	
		$("#editform").validate({
			rules:{
				totalMailNo_cz:{
        			required:true,
        		},
        		cpNo_cz:{
        			required:true,
        		}
			}
		})
		
		<%-- 		var editurl ='<%=basePath%>item/getjdlhd.do'; --%>
		function submitHandler() {
			var editurl ='<%=basePath%>jdwl/getjdlhd.do';
			if ($.validate.form()) {
				$.operate.save_OrderMail_2(editurl, $('#editform').serialize());
			}
    
		}
	
	</script>
</body>
</html>