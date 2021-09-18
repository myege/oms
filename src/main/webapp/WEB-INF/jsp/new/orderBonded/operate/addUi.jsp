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
		<div class="col-xs-12">
			<label class="col-sm-3 control-label ">商家编码：</label>
			<select  id='companybm' name='companybm' class="selectpicker show-tick form-control" data-max-options="3" data-live-search="false">
			</select>
		</div>
		<div class="col-xs-12">
			<label class="col-sm-3 control-label ">充值金额：</label>
			<input class="form-control bg-warning" type="number" min="0" id="bondManey" autoComplete='off' name="bondManey"/>
		</div>
		<div class="col-xs-12">
			<label class="col-sm-3 control-label ">创建用户：</label>
			<select id='userId' name='userId' class="selectpicker show-tick form-control" data-max-options="3" data-live-search="false">
			</select>
		</div>
		</form>
	</div>
<%@ include file="/static/common/footer.txt"%>
<script type="text/javascript">
		$(function(){
			$.ajax({  
				// get请求地址  
				    url: '<%=path %>/orderBond/findByCompany.do',  
				    dataType: "json",  
				    success: function (data) {
				    	console.log(data);
				        for (var i = 0; i < data.length; i++) {  
				            $('#companybm').append("<option value=" + data[i].companybm + ">" + data[i].companybm + "</option>");  
				        }  
				  
				  
				        // 缺一不可  
				        $('#companybm').selectpicker('refresh');  
				        $('#companybm').selectpicker('render');  
				    }  
				});
			$.ajax({  
				// get请求地址  
				    url: '<%=path %>/orderBond/findUserId.do',  
				    dataType: "json",  
				    success: function (data) {
				    	console.log(data);
				        for (var i = 0; i < data.length; i++) {  
				            $('#userId').append("<option  value=" + data[i].id + ">" + data[i].name + "</option>");  
				        }  
				  
				  
				        // 缺一不可  
				        $('#userId').selectpicker('refresh');  
				        $('#userId').selectpicker('render');  
				    }  
				});
		})
		
		  $("#editform").validate({
        	rules:{
        		bondManey:{
        			required:true,
        		},
        	}, 
        	messages: {
                "bondManey": {
                    remote: "金额不能为空"
                },
            }
        });
		
		function submitHandler() {
				var editurl ='<%=path%>/orderBond/save.do';
				if ($.validate.form()) {
						$.operate.save(editurl, $('#editform').serialize());
	        		
				}
	    }
	</script>
</body>
</html>