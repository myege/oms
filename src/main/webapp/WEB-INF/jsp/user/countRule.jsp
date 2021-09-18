<%@page import="java.math.BigDecimal"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String userRole = request.getSession().getAttribute("userRole").toString();

%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>结算规则</title>
</head>
<body>
<script type="text/javascript">
$(function(){
	$('#Cost_csgd').datagrid({
	     idField    :'id',
	     url        :'<%=path%>/cost/findAll.do',
	        columns    :[
	           [
			     {field : 'id',checkbox : true,align : 'center'},
				 {field :'name', title :'结算规则', width :60, align :'center'},
				 {field :'code', title :'结算方式', width :60, align :'center'},
				 {field :'rule', title :'结算条件', width :60, align :'center'},
				]
	         ],
	            fitColumns :true,
	            pagination :true,
	            pageSize   :50,
	            pageList   :[10,50],    
		});
	});
		  


</script>

<div id="showCountRule"></div>
	
</body>
</html>