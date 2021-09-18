<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>派单</title>
<%@ include file="/static/common/head.txt"%>
</head>
<body class="gray-bg">
	<div class="container-div">
		<div class="row">
			<div class="col-sm-12">
				<form method="post" class="form-horizontal" id="editform">
					<div class="box-body">
						<div class="form-group">
							<label class="col-sm-3 control-label">拣货员：</label>
							<div class="col-lg-4 col-sm-9">
								<select name="pickname" id="pickname" class="form-control">
									<c:forEach items="${lists}" var="list">
										<option value="${list.name}">${list.name}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label ">拣货单号：</label>
							<div class="col-lg-4 col-sm-9">
					 			  <input class="form-control" type="text" name="pick" id="pick" value="">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label ">订单数：</label>
							<div class="col-lg-4 col-sm-9">
								
					 			  <input class="form-control" type="text" name="picksum" id="picksum" value="">
								
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label ">商品数：</label>
							<div class="col-lg-4 col-sm-9">
					 			  <input class="form-control" type="text" name="shop" id="shop" value="">
					
							</div>
						</div>
						
					</div>
				</form>
			</div>
		</div>
	</div>
	<%@ include file="/static/common/footer.txt"%>
	<script type="text/javascript">
	function submitHandler() {
		 var editurl ='<%=path%>/JjPicking/distributeLeaflets.do';
		 $.operate.distributeLeaflets(editurl, $('#editform').serialize());
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