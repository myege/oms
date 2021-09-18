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
	<form id="editform">
		<div class="col-xs-12">
			<label class="col-sm-3 control-label ">换单厂家：</label>
			<select id="serachname" name="serachname" class="selectpicker show-tick form-control" data-max-options="3" data-live-search="false">
										<option value="">--请选择查询条件--</option>
										<c:forEach var="user" items="${user }">
											<option value="${user.id}">${user.name}</option>
										</c:forEach>
			</select>
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
				var editurl ='<%=basePath%>orderMail/deliveChange.do';
				if ($.validate.form()) {
					$.operate.save_OrderMail(editurl, $('#editform').serialize());
				}
	    }
	</script>
</body>
</html>