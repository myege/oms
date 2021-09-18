<%@page contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>订单导入</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="/jquery/jquery-2.1.1.js"></script>
<script type="text/javascript">
	/* $(document).ready(function(){
		//加载商家
		$.ajax({
			type:"POST",
			url:"/seller/find",
			dataType:"json",
			cache:true,
			data:{start:"0",limit:"999999"},
			success:function(data, textStatus){
				var sellerArr = data.datas;
				var selectObj = $("#sellerNumber");
				selectObj.empty();
				selectObj.append("<option value='0'>选择商家</option>");
				for(var i=0;i<sellerArr.length;i++){
					selectObj.append("<option value='"+sellerArr[i].number+"'>"+sellerArr[i].unit.name+"</option>");
				}
			}
		});	
	}); */
</script>

</head>
<body>

<form id="importStorageForm" method="post" enctype="multipart/form-data" action="<%=path%>/endheader/importOrder.do">
	<!-- <select id="sellerNumber" name="sellerNumber">
		<option value="0">正在加载商家...</option>
	</select> -->
	<input type="file" id="excelFile" name="excelFile">
	<input type="submit" value="上传并导入" id="btnSubmit" name="btnSubmit">
</form>

</body>
</html>