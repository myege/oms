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
<title>编辑自动审单</title>
<%@ include file="/static/common/head.txt"%>
<body class="gray-bg">
	<div class="container-div">
		<div class="row">
			<div class="col-sm-12">
				<form method="post" class="form-horizontal" id="editform">
				<input type="hidden" value="${autoCheckList.id}" name="id">
					<div class="box-body">
						<div class="form-group">
							<label for="blogLocale" class="col-lg-1 col-sm-2 control-label">公司编码</label>
							<div class="col-lg-4 col-sm-9">
								<select class="form-control" id="companybm" name="companybm" onchange="valCompanyName();">
								<option value="">请选择</option>
								<c:forEach items="${companys}" var="company">
								<option value="${company.companybm}"
								  <c:if test="${company.companybm==autoCheckList.companybm}">selected</c:if>>
								${company.companybm}</option>
								</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="blogLocale" class="col-lg-1 col-sm-2 control-label">公司名称</label>
							<div class="col-lg-4 col-sm-9">
								<input type="text" class="form-control" id="companyName"
									name="companyName" value="${autoCheckList.companyName}" readonly="readonly">
							</div>
						</div>
						<div class="form-group">
							<label for="blogLocale" class="col-lg-1 col-sm-2 control-label">自动捞单</label>
							<div class="col-lg-4 col-sm-9">
								<select class="form-control" name="autoGainOrder">
								<option value="0" ${autoCheckList.autoGainOrder==0?"selected='selected'":''}>关闭</option>
								<option value="1" ${autoCheckList.autoGainOrder==1?"selected='selected'":''}>开启</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="blogLocale" class="col-lg-1 col-sm-2 control-label">订单预审</label>
							<div class="col-lg-4 col-sm-9">
								<select class="form-control" name="orderPreview">
								<option value="0" ${autoCheckList.orderPreview==0?"selected='selected'":''}>关闭</option>
								<option value="1" ${autoCheckList.orderPreview==1?"selected='selected'":''}>开启</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="blogLocale" class="col-lg-1 col-sm-2 control-label">下发平台</label>
							<div class="col-lg-4 col-sm-9">
								<select class="form-control" name="downPlatform">
								<option value="0" ${autoCheckList.downPlatform==0?"selected='selected'":''}>关闭</option>
								<option value="1" ${autoCheckList.downPlatform==1?"selected='selected'":''}>开启</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="blogLocale" class="col-lg-1 col-sm-2 control-label">获取流水</label>
							<div class="col-lg-4 col-sm-9">
								<select class="form-control" name="getStream">
								<option value="0" ${autoCheckList.getStream==0?"selected='selected'":''}>关闭</option>
								<option value="1" ${autoCheckList.getStream==1?"selected='selected'":''}>开启</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="blogLocale" class="col-lg-1 col-sm-2 control-label">抓取单号</label>
							<div class="col-lg-4 col-sm-9">
								<select class="form-control" name="grabNumbers">
								<option value="0" ${autoCheckList.grabNumbers==0?"selected='selected'":''}>关闭</option>
								<option value="1" ${autoCheckList.grabNumbers==1?"selected='selected'":''}>开启</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="blogLocale" class="col-lg-1 col-sm-2 control-label">订单报送</label>
							<div class="col-lg-4 col-sm-9">
								<select class="form-control" name="orderSubmitted">
								<option value="0" ${autoCheckList.orderSubmitted==0?"selected='selected'":''}>关闭</option>
								<option value="1" ${autoCheckList.orderSubmitted==1?"selected='selected'":''}>开启</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="blogLocale" class="col-lg-1 col-sm-2 control-label">物流报送</label>
							<div class="col-lg-4 col-sm-9">
								<select class="form-control" name="logisticsSubmitted">
								<option value="0" ${autoCheckList.logisticsSubmitted==0?"selected='selected'":''}>关闭</option>
								<option value="1" ${autoCheckList.logisticsSubmitted==1?"selected='selected'":''}>开启</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="blogLocale" class="col-lg-1 col-sm-2 control-label">清单报送</label>
							<div class="col-lg-4 col-sm-9">
								<select class="form-control" name="listSubmitted">
								<option value="0" ${autoCheckList.listSubmitted==0?"selected='selected'":''}>关闭</option>
								<option value="1" ${autoCheckList.listSubmitted==1?"selected='selected'":''}>开启</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="blogLocale" class="col-lg-1 col-sm-2 control-label">下发仓库</label>
							<div class="col-lg-4 col-sm-9">
								<select class="form-control" name="downWarehouse">
								<option value="0" ${autoCheckList.downWarehouse==0?"selected='selected'":''}>关闭</option>
								<option value="1" ${autoCheckList.downWarehouse==1?"selected='selected'":''}>开启</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="blogLocale" class="col-lg-1 col-sm-2 control-label">自动完结</label>
							<div class="col-lg-4 col-sm-9">
								<select class="form-control" name="automaticCompletion">
								<option value="0" ${autoCheckList.automaticCompletion==0?"selected='selected'":''}>关闭</option>
								<option value="1" ${autoCheckList.automaticCompletion==1?"selected='selected'":''}>开启</option>
								</select>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<%@ include file="/static/common/footer.txt"%>
	<script type="text/javascript">
	$("#editform").validate({
		rules:{
			companyName:{
    			required:true,
    		}
		}
	});
	
	function valCompanyName(){
		var companybm=$("#companybm").val();
		$.ajax({
			url:"<%=path%>/autochecklist/getCompanyName.do",
			type:"post",
			dataType:"json",
			data:{companybm:companybm},
			success:function(data){
				$("#companyName").val(data.companyName);
			}
		});
	}
	function submitHandler() {
		var editurl ='<%=path%>/autochecklist/edit.do';
		if ($("#editform").valid()) {
		$.operate.save(editurl, $('#editform').serialize());
		}
    }
	</script>
</body>
</html>