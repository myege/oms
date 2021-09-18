<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%

String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String userRole = request.getSession().getAttribute("userRole").toString();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="<%=path%>/jquery-easyui-1.4.1/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=path%>/jquery-easyui-1.4.1/themes/icon.css">
	<script type="text/javascript" src="<%=path%>/jquery-easyui-1.4.1/jquery.min.js"></script>
    <script type="text/javascript" src="<%=path%>/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=path%>/jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="<%=path%>/js/printer.js"></script>
	<script type="text/javascript" src="<%=path%>/js/pageload.js"></script>
	<script type="text/javascript" src="<%=path%>/js/tools.js"></script>
</head>
<body>
	<script type="text/javascript">
		
	function getMailNoStraight(){
		$.messager.confirm('系统提示','确定要拿单号吗?',function(r){
        	if (r){
					$("#f_getMailNoStraight").submit();
        	}
		})
	}

	</script>
		
		  <form id="f_getMailNoStraight" method="post" action="<%=basePath%>orderBonded/getMailNoStraight.do?">
	    	<table cellpadding="5" bolder="1px">
	    		<tr>
	    			<td>快递名称：
						<select class="easyui-combobox" id="icarrier" name="carrier" data-options="editable:false,panelHeight:'auto'" >	
							<option value="">请选择</option>
							<option value="STO">申通</option>
							<option value="HTO">汇通</option>		 	
						 </select>
	    			</td>
	    			<td>
	    				数量：<input class="easyui-textbox" id="icount" name="count" ></input>
	    			</td>
	    			<td>
	    				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton" onclick="getMailNoStraight()" iconcls="icon-save">获取</a> 
	    			</td>
	    		</tr>

	    	</table>
	    </form>

		
</body>
</html>