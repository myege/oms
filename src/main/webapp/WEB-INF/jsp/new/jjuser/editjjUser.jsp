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
<%@ include file="/static/common/head.txt"%>
<title>编辑拣货员</title>
</head>
<body>
<div class="row">
	<form id="editform">
		<input name="id" id="id"  type="hidden"  value="${jjUser.id }"/>
		 <div class="col-xs-12">
			<label class="col-sm-3 control-label ">拣货员名称：</label>
			<input class="form-control bg-warning" type="text" id="name" name="name" value="${jjUser.name }"/>
		</div>
		<div  class="col-xs-12">
			<label class="col-sm-3 control-label">类型：</label>
			<input class="form-control" type="text" name="type" id="type" value="${jjUser.type }">
		</div>
		</form>
	</div>
<%@ include file="/static/common/footer.txt"%>
<script type="text/javascript">	
		$("#editform").validate({
			rules:{
				name:{
        			required:true,
        		},
        		type:{
        			required:true,
        		}
			}
		})
		function submitHandler() {
			var editurl ='<%=path%>/jjUser/edit.do';
			if ($.validate.form()) {
					$.operate.save(editurl, $('#editform').serialize());
			}
	    }
	</script>
</body>
</html>