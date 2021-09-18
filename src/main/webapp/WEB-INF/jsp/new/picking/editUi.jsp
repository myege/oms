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
		<input name="id" id="id"  type="hidden"  value="${jjPicking.id }"/>
		<div class="col-xs-6">
			<label class="col-sm-3 control-label ">拣货单号</label>
			<input class="form-control bg-warning" type="text" id="pick" name="pick" value="${jjPicking.pick }"/>
		</div>
		<div  class="col-xs-6">
			<label class="col-sm-3 control-label">订单数</label>
			<input class="form-control" type="text" name="picksum" id="picksum" value="${jjPicking.picksum }">
		</div>
		<div class="col-xs-6">
			<label class="col-sm-3 control-label">商品数</label>
			<input class="form-control" type="text" name="shop" id="shop" value="${jjPicking.shop }">
		</div>
		<div class="col-xs-6">
			<label class="col-sm-3 control-label">拣货员</label>
			<input class="form-control" type="text" name="pickname" id="pickname" value="${jjPicking.pickname }" >
		</div>
		
		</form>
	</div>
<%@ include file="/static/common/footer.txt"%>
<script type="text/javascript">	
		$("#editform").validate({
			rules:{
				pick:{
        			required:true,
        		},
        		picksum:{
        			required:true,
        		},
        		shop:{
        			required:true,
        		},
        		pickname:{
        			required:true,
        		}
			}
		})
		function submitHandler() {
			
				var editurl ='<%=path%>/JjPicking/edit.do';
				if ($.validate.form()) {
			
						$.operate.save(editurl, $('#editform').serialize());
		
	        		
				}
	    }
		window.onkeydown = function(e) {
		      if (e.keyCode === 123) {
		        e.preventDefault()
		      }
		    }
		    window.oncontextmenu = function(e) {e.preventDefault()}
	</script>
</body>
</html>