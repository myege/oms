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
<title>冻结</title>
<%@ include file="/static/common/head.txt"%>
</head>
<body class="white-bg">
	<div class="row">
	<form id="editform">
		<input name="id" id="id"  type="hidden"  value="${orderBond.id }"/>
		<div class="col-xs-12">
			<label class="col-sm-3 control-label ">公司编码：</label>
			<input class="form-control bg-warning" readonly="readonly" id="companybm" name="companybm" type="text" value="${orderBond.companybm }"/>
		</div>
		<div class="col-xs-12">
			<label class="col-sm-3 control-label ">剩余保证金：</label>
			<input class="form-control bg-warning" type="text" id="surplusMamey"  name="surplusMamey" readonly="readonly"  value="${orderBond.surplusMamey }"/>
		</div>
		<div class="col-xs-12">
			<label class="col-sm-3 control-label ">原冻结金额：</label>
			<input class="form-control bg-warning" type="text" id="frostMamey" name="frostMamey" value="${orderBond.frostMamey }" readonly="readonly"/>
		</div>
		
		<div class="col-xs-12">
			<label class="col-sm-3 control-label ">需要冻结金额：</label>
			<input class="form-control bg-warning" type="number" min="0" id="updateMamey" autoComplete='off' name="updateMamey"/>
		</div>
		
		<div class="col-xs-12 col-sm-8">
			<label class="col-sm-3 control-label ">现冻结金额：</label>
			<input class="form-control bg-warning" type="text" id="showMoney1" name="showMoney" readonly="readonly" />
		</div>
		<div class="col-xs-12 col-sm-8">
			<label class="col-sm-3 control-label ">现剩余金额：</label>
			<input class="form-control bg-warning" type="text" id="showMoney2" name="showMoney" readonly="readonly" />
		</div>
		</form>
	</div>
<%@ include file="/static/common/footer.txt"%>
<script type="text/javascript">
		$(function(){
			$('#updateMamey').bind('input propertychange', function() {  
				var a = $("#frostMamey").val();
				var b = $("#updateMamey").val();
				var sum = Number(a)+Number(b);
				
			    $('#showMoney1').val(sum);  
			});
			$('#updateMamey').bind('input propertychange', function() {  
				var a = $("#surplusMamey").val();
				var b = $("#updateMamey").val();
				var sum = Number(a)-Number(b);
				
			    $('#showMoney2').val(sum);  
			});
		})
		 $("#editform").validate({
        	rules:{
        		updateMamey:{
        			required:true,
        		},
        	}, 
        	messages: {
                "updateMamey": {
                    remote: "金额不能为空"
                },
            }
        });
		
		function submitHandler() {
				var editurl ='<%=path%>/orderBond/frost.do';
				if ($.validate.form()) {
						$.operate.save(editurl, $('#editform').serialize());
	        		
				}
	    }
	</script>
</body>
</html>