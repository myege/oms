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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>选择时间</title>
 
<%@ include file="/static/common/head.txt"%>

</head>
<body>
	<!-- <form id="editform"> -->
	<table align="center"   style="height: 10% ;"><!--  margin-top: 5px;valign="center"  -->
		<tr style="border: 0px;">
			<td >
				<input type="text" id="startTime" name="startTime" style="width:270px;"
					class="form-control" placeholder="请输入提单号"/>
			</td>
			
		</tr>
	</table>
		
	<!-- </form> -->
	<form id="editform" action="<%=basePath%>szqgorder/submitStratTimeEndTime2.do" method="post">
				
					<input type="hidden" id="startTime1" name="startTime1">
					<input type="hidden" id="endTime1" name="endTime1">
			</form>

<%@ include file="/static/common/footer.txt"%>
	
	<script type="text/javascript">
		$(function(){
			//加载时间控件
		//	$.operate.time("startTime");
		//	$.operate.time("endTime");
		});
	
		$("#editform").validate({
			rules:{
				totalMailNo_cz:{
	    			required:true,
	    		},
	    		cpNo_cz:{
	    			required:true,
	    		}
			}
		});
		function submitHandler() {
				<%-- var editurl ='<%=path %>/orderBonded/submitStratTimeEndTime.do';
				if ($.validate.form()) {
					$.operate.save_OrderMail_2(editurl, $('#editform').serialize());
				} --%>
				
				var startTime = $("#startTime").val();
				var endTime = $("#endTime").val();
				var flag = true;
				if(startTime == null || startTime == 0){
					$.modal.alertWarning("请填写提单号");
		   	    	return flag = false; 
				}
				/* if(endTime == null || endTime == 0){
					$.modal.alertWarning("请选填结束时间");
		   	    	return flag = false; 
				} */
				if(flag){
					
					var v = document.getElementById("startTime1");
					v.value = startTime;
					var v1 = document.getElementById("endTime1");
					v1.value = endTime;
					var f = document.getElementById("editform");
					f.submit(); 
					
					
					<%-- $.ajax({  
						// get请求地址  
						url: '<%=path %>/orderBonded/submitStratTimeEndTime2.do',  
						dataType: "json", 
						data:{
							"startTime" : startTime,
							"endTime" : endTime
						},
						success: function (result) {
							if(result == "0"){
								//先得到当前iframe层的索引
								var index = parent.layer.getFrameIndex(window.name);
								//在执行关闭
								parent.layer.close(index);
							}else{
								$.modal.alertWarning("保存时间失败");
							}
						}  
					}); --%>
				}
	    }
	</script>
</body>
</html>
