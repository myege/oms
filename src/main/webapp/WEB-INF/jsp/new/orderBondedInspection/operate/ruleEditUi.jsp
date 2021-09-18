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
<title>新增/修改</title>
<%@ include file="/static/common/head.txt"%>
</head>
<body class="white-bg">
	<div class="row">
	<form id="editform">
		<input name="id" id="id"  type="hidden"  value="${orderBondedRule.id }"/>
		<div class="col-xs-12">
			<label class="col-sm-3 control-label ">快递公司：</label>
			<input class="form-control bg-warning" type="text" id="carrier" name="carrier" value="${orderBondedRule.carrier}"/>
		</div>
			<div class="col-xs-12">
			<label class="col-sm-3 control-label ">省份：</label>
			<input class="form-control bg-warning" type="text" id="province" name="province" value="${orderBondedRule.province}"/>
		</div>
		<div class="col-xs-12">
			<label class="col-sm-3 control-label ">重量(g)：</label>
			<input class="form-control bg-warning" type="text" id="weight" name="weight" value="${orderBondedRule.weight}"/>
		</div>
		</form>
	</div>
<%@ include file="/static/common/footer.txt"%>
<script type="text/javascript">	
		$("#editform").validate({
			rules:{
				carrier:{
					required:true,
        		},
				province:{
					required:true,
				},
				weight:{
					required:true,
				}
			}
		})
		function submitHandler() {
				var editurl ='<%=basePath%>orderBondedRule/updateRule.do';
				if ($.validate.form()) {
					/* var id=$("#id").val();
					console.log(id == "");
					if(id == ""){
						$.operate.save(addurl, $('#editform').serialize());
					}else{ */
						$.operate.save(editurl, $('#editform').serialize());
					/* } */
	        		
				}
	    }
	</script>
</body>
</html>