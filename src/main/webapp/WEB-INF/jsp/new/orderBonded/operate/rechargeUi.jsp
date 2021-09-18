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
<title>充值</title>
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
			<label class="col-sm-3 control-label ">原总保证金：</label>
			<input class="form-control bg-warning" type="text" id="bmoney" readonly="readonly"  value="${orderBond.bondManey }"/>
		</div>
		<div class="col-xs-12">
			<label class="col-sm-3 control-label ">充值金额：</label>
			<input class="form-control bg-warning" type="number" min="0" id="bondManey" autoComplete='off' name="bondManey"/>
		</div>
		<div class="col-xs-12 col-sm-8">
			<label class="col-sm-3 control-label ">充值后金额：</label>
			<input class="form-control bg-warning" type="text" id="showMoney" name="showMoney" readonly="readonly" />
		</div>
		</form>
	</div>
<%@ include file="/static/common/footer.txt"%>
<script type="text/javascript">
		$(function(){
			$('#bondManey').bind('input propertychange', function() {  
				var a = $("#bondManey").val();
				var b = $("#bmoney").val();
				var sum = Number(a)+Number(b);
				
			    $('#showMoney').val(sum);  
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
				var editurl ='<%=path%>/orderBond/updeteBond.do';
				if ($.validate.form()) {
						$.operate.save(editurl, $('#editform').serialize());
	        		
				}
	    }
	</script>
</body>
</html>