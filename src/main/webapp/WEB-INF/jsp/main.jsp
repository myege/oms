<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String userRole = request.getSession().getAttribute("userRole").toString();
String name = request.getSession().getAttribute("name").toString();
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="<%=path%>/jquery-easyui-1.4.1/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=path%>/jquery-easyui-1.4.1/themes/icon.css">
	<script type="text/javascript" src="<%=path%>/jquery-easyui-1.4.1/jquery.min.js"></script>
    <script type="text/javascript" src="<%=path%>/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=path%>/jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="<%=path%>/js/printer.js"></script>
	<script type="text/javascript" src="<%=path%>/js/pageload.js"></script>
	<script type="text/javascript" src="<%=path%>/js/tools.js"></script>
	

	<script src="<%=path%>/js/menu.js" type="text/javascript"></script>
	<link rel="stylesheet" type="text/css" href="<%=path%>/css/style.css" />
	
	<script type="text/javascript">
	
		function users(title){
			if($("#tt").tabs("exists",title)){
				$("#tt").tabs("select",title);
			}else{
				$('#tt').tabs('add',{
				    title:title, 
				    iconCls:'icon-add', 
				    href:'<%=path%>/users/init.do?pageName=user/users', 
				    closable:true
				}); 
			}
		}
	
		function orderPush(title){
			if($("#tt").tabs("exists",title)){
				$("#tt").tabs("select",title);
			}else{
				$('#tt').tabs('add',{    
				    title:title, 
				    iconCls:'icon-add', 
				    href:'<%=path%>/orderPush/init.do?pageName=orderPush', 
				    closable:true
				}); 
			}
		}
		
		function orderAccept(title,tz){
			$("#tt").tabs("close","已打印");
			
			if($("#tt").tabs("exists",title)){
				$("#tt").tabs("select",title);
			}else{
				$('#tt').tabs('add',{    
				    title:title, 
				    tabs:'dy',
				    iconCls:'icon-add',
				    href:'<%=path%>/orderAccept/init.do?pageName=orderAccept&tz='+tz,
				    closable:true
				});  
			}
		}

		function orderAccept1(title,tz){
			$("#tt").tabs("close","未打印");
			if($("#tt").tabs("exists",title)){
				$("#tt").tabs("select",title);
			}else{
				$('#tt').tabs('add',{    
				    title:title, 
				    iconCls:'icon-add',
				    href:'<%=path%>/orderAccept/init.do?pageName=orderAccept&tz='+tz,
				    closable:true
				});  
			}
		}

		function waybill(title,tz){
			$("#tt").tabs("close","已签收");
			if($("#tt").tabs("exists",title)){
				$("#tt").tabs("select",title);
			}else{
				$('#tt').tabs('add',{    
				    title:title, 
				    iconCls:'icon-add',
				    href:'<%=path%>/waybill/init.do?pageName=waybill&tz='+tz,
				    closable:true   
				});  
			}
		}
		
		function waybill1(title,tz){
			$("#tt").tabs("close","未签收");
			if($("#tt").tabs("exists",title)){
				$("#tt").tabs("select",title);
			}else{
				$('#tt').tabs('add',{    
				    title:title, 
				    iconCls:'icon-add',
				    href:'<%=path%>/waybill/init.do?pageName=waybill&tz='+tz,
				    closable:true   
				});  
			}
		}
		
		function orderBonded2(title,tz){
			$("#tt").tabs("close","保税完结订单");
			$("#tt").tabs("close","保税取消订单");
			if($("#tt").tabs("exists",title)){
				$("#tt").tabs("select",title);
			}else{
				$('#tt').tabs('add',{    
				    title:title, 
				    iconCls:'icon-add',
				    href:'<%=path%>/orderBonded/init.do?pageName=orderBonded&tz='+tz,    
				    closable:true   
				});  
			}
		}
		
		function orderBonded3(title,tz){
			$("#tt").tabs("close","保税完结订单");
			$("#tt").tabs("close","保税订单");			
			if($("#tt").tabs("exists",title)){
				$("#tt").tabs("select",title);
			}else{
				$('#tt').tabs('add',{    
				    title:title, 
				    iconCls:'icon-add',
				    href:'<%=path%>/orderBonded/init.do?pageName=orderBonded&tz='+tz,    
				    closable:true   
				});  
			}
		}
		
		function orderBonded1(title,tz){
			$("#tt").tabs("close","保税订单");
			$("#tt").tabs("close","保税取消订单");
			if($("#tt").tabs("exists",title)){
				$("#tt").tabs("select",title);
			}else{
				$('#tt').tabs('add',{    
				    title:title, 
				    iconCls:'icon-add',
				    href:'<%=path%>/orderBonded/init.do?pageName=orderBonded&tz='+tz,    
				    closable:true   
				});  
			}
		}
		
		function orderAudit(title,tz){
			$("#tt").tabs("close","订单保税(系统导入)");
			$("#tt").tabs("close","订单保税(审核完结)");
			$("#tt").tabs("close","订单保税(完结未WMS)");
			$("#tt").tabs("close","订单保税(取消订单)");
			$("#tt").tabs("close","订单保税(异常订单)");
			if($("#tt").tabs("exists",title)){
				$("#tt").tabs("select",title);
			}else{
				$('#tt').tabs('add',{    
				    title:title, 
				    iconCls:'icon-add',
				    href:'<%=path%>/orderBonded/init.do?pageName=orderAudit&tz='+tz,    
				    closable:true   
				});  
			}
		}
		
		function orderAudit2(title,tz){
			$("#tt").tabs("close","订单保税(平台推送)");
			$("#tt").tabs("close","订单保税(审核完结)");
			$("#tt").tabs("close","订单保税(完结未WMS)");
			$("#tt").tabs("close","订单保税(取消订单)");
			$("#tt").tabs("close","订单保税(异常订单)");
			if($("#tt").tabs("exists",title)){
				$("#tt").tabs("select",title);
			}else{
				$('#tt').tabs('add',{    
				    title:title, 
				    iconCls:'icon-add',
				    href:'<%=path%>/orderBonded/init.do?pageName=orderAudit&tz='+tz,    
				    closable:true   
				});  
			}
		}
		
		function orderAudit3(title,tz){
			$("#tt").tabs("close","订单保税(系统导入)");
			$("#tt").tabs("close","订单保税(平台推送)");
			$("#tt").tabs("close","订单保税(审核完结)");
			$("#tt").tabs("close","订单保税(取消订单)");
			$("#tt").tabs("close","订单保税(异常订单)");
			if($("#tt").tabs("exists",title)){
				$("#tt").tabs("select",title);
			}else{
				$('#tt').tabs('add',{    
				    title:title, 
				    iconCls:'icon-add',
				    href:'<%=path%>/orderBonded/init.do?pageName=orderAudit&tz='+tz,    
				    closable:true   
				});  
			}
		}
		
		function orderAudit4(title,tz){
			$("#tt").tabs("close","订单保税(平台推送)");
			$("#tt").tabs("close","订单保税(系统导入)");
			$("#tt").tabs("close","订单保税(完结未WMS)");
			$("#tt").tabs("close","订单保税(审核完结)");
			$("#tt").tabs("close","订单保税(异常订单)");
			if($("#tt").tabs("exists",title)){
				$("#tt").tabs("select",title);
			}else{
				$('#tt').tabs('add',{    
				    title:title, 
				    iconCls:'icon-add',
				    href:'<%=path%>/orderBonded/init.do?pageName=orderAudit&tz='+tz,    
				    closable:true   
				});  
			}
		}
		
		function orderAudit5(title,tz){
			$("#tt").tabs("close","订单保税(平台推送)");
			$("#tt").tabs("close","订单保税(系统导入)");
			$("#tt").tabs("close","订单保税(完结未WMS)");
			$("#tt").tabs("close","订单保税(审核完结)");
			$("#tt").tabs("close","订单保税(取消订单)");
			if($("#tt").tabs("exists",title)){
				$("#tt").tabs("select",title);
			}else{
				$('#tt').tabs('add',{    
				    title:title, 
				    iconCls:'icon-add',
				    href:'<%=path%>/orderBonded/init.do?pageName=orderAudit&tz='+tz,    
				    closable:true   
				});  
			}
		}
		
		function orderAudit1(title,tz){
			$("#tt").tabs("close","订单保税(平台推送)");
			$("#tt").tabs("close","订单保税(系统导入)");
			$("#tt").tabs("close","订单保税(完结未WMS)");
			$("#tt").tabs("close","订单保税(取消订单)");
			if($("#tt").tabs("exists",title)){
				$("#tt").tabs("select",title);
			}else{
				$('#tt').tabs('add',{    
				    title:title, 
				    iconCls:'icon-add',
				    href:'<%=path%>/orderBonded/init.do?pageName=orderAudit&tz='+tz,    
				    closable:true   
				});  
			}
		}
		

		
		function inventory(title){
			
			if($("#tt").tabs("exists",title)){
				$("#tt").tabs("select",title);
			}else{
				$('#tt').tabs('add',{    
				    title:title, 
				    iconCls:'icon-add',
				    href:'<%=path%>/inventory/init.do?pageName=inventory',    
				    closable:true   
				});  
			}
		} 
		
		function inventory(title){
			
			if($("#tt").tabs("exists",title)){
				$("#tt").tabs("select",title);
			}else{
				$('#tt').tabs('add',{    
				    title:title, 
				    iconCls:'icon-add',
				    href:'<%=path%>/inventory/init.do?pageName=inventory',    
				    closable:true   
				});  
			}
		} 
		
		function orderMail(title,tz){
			$("#tt").tabs("close","订单管理(直邮完结)");
			if($("#tt").tabs("exists",title)){
				$("#tt").tabs("select",title);
			}else{
				$('#tt').tabs('add',{    
				    title:title, 
				    iconCls:'icon-add',
				    href:'<%=path%>/orderMail/init.do?pageName=orderMail&tz='+tz,    
				    closable:true   
				});  
			}
		}
		
		function orderMail1(title,tz){
			$("#tt").tabs("close","订单管理(直邮未完结)");
			if($("#tt").tabs("exists",title)){
				$("#tt").tabs("select",title);
			}else{
				$('#tt').tabs('add',{    
				    title:title, 
				    iconCls:'icon-add',
				    href:'<%=path%>/orderMail/init.do?pageName=orderMail&tz='+tz,    
				    closable:true   
				});  
			}
		}
		
		function orderMailAudit(title,tz){
			$("#tt").tabs("close","订单直邮(完结)");
			$("#tt").tabs("close","订单直邮(待运抵)");
			$("#tt").tabs("close","订单直邮(异常)");
			if($("#tt").tabs("exists",title)){
				$("#tt").tabs("select",title);
			}else{
				$('#tt').tabs('add',{    
				    title:title, 
				    iconCls:'icon-add',
				    href:'<%=path%>/orderMail/init.do?pageName=orderMailAudit&tz='+tz,    
				    closable:true   
				});  
			}
		}
		
		function orderMailAudit1(title,tz){
			$("#tt").tabs("close","订单直邮(审核)");
			$("#tt").tabs("close","订单直邮(待运抵)");
			$("#tt").tabs("close","订单直邮(异常)");
			if($("#tt").tabs("exists",title)){
				$("#tt").tabs("select",title);
			}else{
				$('#tt').tabs('add',{    
				    title:title, 
				    iconCls:'icon-add',
				    href:'<%=path%>/orderMail/init.do?pageName=orderMailAudit&tz='+tz,    
				    closable:true   
				});  
			}
		}
		
		function orderMailAudit2(title,tz){
			$("#tt").tabs("close","订单直邮(审核)");
			$("#tt").tabs("close","订单直邮(完结)");
			$("#tt").tabs("close","订单直邮(异常)");
			if($("#tt").tabs("exists",title)){
				$("#tt").tabs("select",title);
			}else{
				$('#tt').tabs('add',{    
				    title:title, 
				    iconCls:'icon-add',
				    href:'<%=path%>/orderMail/init.do?pageName=orderMailAudit&tz='+tz,    
				    closable:true   
				});  
			}
		}
		
		function orderMailAudit3(title,tz){
			$("#tt").tabs("close","订单直邮(审核)");
			$("#tt").tabs("close","订单直邮(完结)");
			$("#tt").tabs("close","订单直邮(待运抵)");
			if($("#tt").tabs("exists",title)){
				$("#tt").tabs("select",title);
			}else{
				$('#tt').tabs('add',{    
				    title:title, 
				    iconCls:'icon-add',
				    href:'<%=path%>/orderMail/init.do?pageName=orderMailAudit&tz='+tz,    
				    closable:true   
				});  
			}
		}
		
		
		function receiveHghzData(title,tz){
			$("#tt").tabs("close", "订单回执(已处理)");
			if($("#tt").tabs("exists", title)){
				$("#tt").tabs("select", title);
			}else{
				$('#tt').tabs('add',{    
				    title:title, 
				    iconCls:'icon-add',
				    href:'<%=path%>/receiveHghzData/init.do?pageName=receiveHghzData&tz='+tz,    
				    closable:true   
				});  
			}
		}
		
		function receiveHghzData1(title,tz){
			$("#tt").tabs("close", "订单回执(未处理)");
			if($("#tt").tabs("exists", title)){
				$("#tt").tabs("select", title);
			}else{
				$('#tt').tabs('add',{    
				    title:title, 
				    iconCls:'icon-add',
				    href:'<%=path%>/receiveHghzData/init.do?pageName=receiveHghzData&tz='+tz,    
				    closable:true   
				});  
			}
		}
		
		function tinventory(title){
			if($("#tt").tabs("exists",title)){
				$("#tt").tabs("select",title);
			}else{
				$('#tt').tabs('add',{    
				    title:title, 
				    iconCls:'icon-add',
				    href:'<%=path%>/tinventory/init.do?pageName=tinventory',    
				    closable:true   
				});  
			}
		}
		
		function materiel(title){
			if($("#tt").tabs("exists",title)){
				$("#tt").tabs("select",title);
			}else{
				$('#tt').tabs('add',{    
				    title:title, 
				    iconCls:'icon-add',
				    href:'<%=path%>/materiel/init.do?pageName=materiel',    
				    closable:true   
				});  
			}
		}
		
		function item(title){
			if($("#tt").tabs("exists",title)){
				$("#tt").tabs("select",title);
			}else{
				$('#tt').tabs('add',{    
				    title:title, 
				    iconCls:'icon-add',
				    href:'<%=path%>/item/init.do?pageName=item',    
				    closable:true   
				});  
			}
		}

		function godownEntry(title){
			if($("#tt").tabs("exists",title)){
				$("#tt").tabs("select",title);
			}else{
				$('#tt').tabs('add',{    
				    title:title, 
				    iconCls:'icon-add',
				    href:'<%=path%>/godownEntry/init.do?pageName=godownEntry',    
				    closable:true   
				});  
			}
		}
		
		function godownEntryCheck(title){
			if($("#tt").tabs("exists",title)){
				$("#tt").tabs("select",title);
			}else{
				$('#tt').tabs('add',{    
				    title:title, 
				    iconCls:'icon-add',
				    href:'<%=path%>/godownEntryCheck/init.do?pageName=godownEntryCheck',    
				    closable:true   
				});  
			}
		}
		
		function entryBill(title){
			if($("#tt").tabs("exists",title)){
				$("#tt").tabs("select",title);
			}else{
				$('#tt').tabs('add',{    
				    title:title, 
				    iconCls:'icon-add',
				    href:'<%=path%>/entryBill/init.do?pageName=entryBill',    
				    closable:true   
				});  
			}
		}
		
		function entryBillList(title){
			if($("#tt").tabs("exists",title)){
				$("#tt").tabs("select",title);
			}else{
				$('#tt').tabs('add',{    
				    title:title, 
				    iconCls:'icon-add',
				    href:'<%=path%>/entryBillList/init.do?pageName=entryBillList',    
				    closable:true   
				});  
			}
		}
		
		function imEntryBillList(title){
			if($("#tt").tabs("exists",title)){
				$("#tt").tabs("select",title);
			}else{
				$('#tt').tabs('add',{    
				    title:title, 
				    iconCls:'icon-add',
				    href:'<%=path%>/imEntryBillList/init.do?pageName=imEntryBillList',    
				    closable:true   
				});  
			}
		}
		
		function nuclearRelease(title){
			if($("#tt").tabs("exists",title)){
				$("#tt").tabs("select",title);
			}else{
				$('#tt').tabs('add',{    
				    title:title, 
				    iconCls:'icon-add',
				    href:'<%=path%>/nuclearRelease/init.do?pageName=nuclearRelease',    
				    closable:true   
				});  
			}
		}
		
		function orderCheck(title){
			if($("#tt").tabs("exists",title)){
				$("#tt").tabs("select",title);
			}else{
				$('#tt').tabs('add',{    
				    title:title, 
				    iconCls:'icon-add',
				    href:'<%=path%>/orderCheck/init.do?pageName=orderCheck',    
				    closable:true   
				});  
			}
		}
		
		function wmsmerger(title){
			if($("#tt").tabs("exists",title)){
				$("#tt").tabs("select",title);
			}else{
				$('#tt').tabs('add',{    
				    title:title, 
				    iconCls:'icon-add',
				    href:'<%=path%>/merger/init.do?pageName=merger',    
				    closable:true   
				});  
			}
		}
		
		function wmsactualreceive(title){
			if($("#tt").tabs("exists",title)){
				$("#tt").tabs("select",title);
			}else{
				$('#tt').tabs('add',{    
				    title:title, 
				    iconCls:'icon-add',
				    href:'<%=path%>/actualreceive/init.do?pageName=actualreceive',    
				    closable:true   
				});  
			}
		}
		
		function wmsinout(title){
			if($("#tt").tabs("exists",title)){
				$("#tt").tabs("select",title);
			}else{
				$('#tt').tabs('add',{    
				    title:title, 
				    iconCls:'icon-add',
				    href:'<%=path%>/inout/init.do?pageName=inout',    
				    closable:true   
				});  
			}
		}
		
		function wmsinoutforzt(title){
			if($("#tt").tabs("exists",title)){
				$("#tt").tabs("select",title);
			}else{
				$('#tt').tabs('add',{    
				    title:title, 
				    iconCls:'icon-add',
				    href:'<%=path%>/inout/init.do?pageName=inoutforzt',    
				    closable:true   
				});  
			}
		}
		
		
		
		function wmsinventory(title){
			if($("#tt").tabs("exists",title)){
				$("#tt").tabs("select",title);
			}else{
				$('#tt').tabs('add',{    
				    title:title, 
				    iconCls:'icon-add',
				    href:'<%=path%>/winventory/init.do?pageName=winventory',    
				    closable:true   
				});  
			}
		}
		
		function planingReceipts(title){
			if($("#tt").tabs("exists",title)){
				$("#tt").tabs("select",title);
			}else{
				$('#tt').tabs('add',{    
				    title:title, 
				    iconCls:'icon-add',
				    href:'<%=path%>/planingReceipts/init.do?pageName=planingReceipts',    
				    closable:true   
				});  
			}
		}
		
		function stockDelete(title){
			if($("#tt").tabs("exists",title)){
				$("#tt").tabs("select",title);
			}else{
				$('#tt').tabs('add',{    
				    title:title, 
				    iconCls:'icon-add',
				    href:'<%=path%>/stockDelete/init.do?pageName=stockDelete',    
				    closable:true   
				});  
			}
		}
		
		function taxes(title){
			if($("#tt").tabs("exists",title)){
				$("#tt").tabs("select",title);
			}else{
				$('#tt').tabs('add',{    
				    title:title, 
				    iconCls:'icon-add',
				    href:'<%=path%>/taxes/init.do?pageName=taxes',    
				    closable:true   
				});  
			}
		}
		function collect(title){
			$("#tt").tabs("close","保税结算");
			if($("#tt").tabs("exists",title)){
				$("#tt").tabs("select",title);
			}else{
				$('#tt').tabs('add',{    
				    title:title, 
				    iconCls:'icon-add',
				    href:'<%=path%>/collect/init.do?pageName=collect',    
				    closable:true   
				});  
			}
		}
		
		function beLzd(title){
			if($("#tt").tabs("exists",title)){
				$("#tt").tabs("select",title);
			}else{
				$('#tt').tabs('add',{    
				    title:title, 
				    iconCls:'icon-add',
				    href:'<%=path%>/beLzd/init.do?pageName=beLzd',    
				    closable:true   
				});  
			}
		}
		function bsjs(title){
			$("#tt").tabs("close","直邮结算");
			if($("#tt").tabs("exists",title)){
				$("#tt").tabs("select",title);
			}else{
				$('#tt').tabs('add',{    
				    title:title, 
				    iconCls:'icon-add',
				    href:'<%=path%>/collect/init.do?pageName=bsjs',    
				    closable:true   
				});  
			}
		}
		
		function orderOut(title){
			$("#tt").tabs("close",'出口完结订单');
			$("#tt").tabs("close",'出口保税订单');
			$("#tt").tabs("close",'出口保税完结订单');
			$("#tt").tabs("close",'出口保税订单STO');
			$("#tt").tabs("close",'出口保税商品规则');
			if($("#tt").tabs("exists",title)){
				$("#tt").tabs("select",title);
			}else{
				$('#tt').tabs('add',{    
				    title:title, 
				    iconCls:'icon-add',
				    href:'<%=path%>/orderOut/init.do?pageName=orderOut',    
				    closable:true   
				});  
			}
		}
		function orderOut_1(title){
			$("#tt").tabs("close",'出口保税完结订单');
			$("#tt").tabs("close",'出口订单');
			$("#tt").tabs("close",'出口完结订单');
			$("#tt").tabs("close",'出口流转单');
			$("#tt").tabs("close",'出口订单STO');
			$("#tt").tabs("close",'出口商品规则');
			if($("#tt").tabs("exists",title)){
				$("#tt").tabs("select",title);
			}else{
				$('#tt').tabs('add',{    
				    title:title, 
				    iconCls:'icon-add',
				    href:'<%=path%>/orderOutBS/init.do?pageName=orderOut_1',    
				    closable:true   
				});  
			}
		}
		
		
		function orderOutSTO(title){
			$("#tt").tabs("close",'出口保税订单');
			$("#tt").tabs("close",'出口保税完结订单');
			$("#tt").tabs("close",'出口保税订单STO');
			$("#tt").tabs("close",'出口保税商品规则');
			if($("#tt").tabs("exists",title)){
				$("#tt").tabs("select",title);
			}else{
				$('#tt').tabs('add',{    
				    title:title, 
				    iconCls:'icon-add',
				    href:'<%=path%>/orderOutSTO/init.do?pageName=orderOutSTO',    
				    closable:true
				});  
			}
		}
		
		function orderOutSTO_1(title){
			$("#tt").tabs("close",'出口订单');
			$("#tt").tabs("close",'出口完结订单');
			$("#tt").tabs("close",'出口流转单');
			$("#tt").tabs("close",'出口订单STO');
			$("#tt").tabs("close",'出口商品规则');
			if($("#tt").tabs("exists",title)){
				$("#tt").tabs("select",title);
			}else{
				$('#tt').tabs('add',{    
				    title:title, 
				    iconCls:'icon-add',
				    href:'<%=path%>/orderOutSTO/init.do?pageName=orderOutSTO_1',    
				    closable:true   
				});  
			}
		}
		
		
		function orderOutRule(title){
			$("#tt").tabs("close",'出口保税订单');
			$("#tt").tabs("close",'出口保税完结订单');
			$("#tt").tabs("close",'出口保税订单STO');
			$("#tt").tabs("close",'出口保税商品规则');
			if($("#tt").tabs("exists",title)){
				$("#tt").tabs("select",title);
			}else{
				$('#tt').tabs('add',{    
				    title:title, 
				    iconCls:'icon-add',
				    href:'<%=path%>/orderOutRule/init.do?pageName=orderOutRule',    
				    closable:true   
				});  
			}
		}
		
		function orderOutRule_1(title){
			$("#tt").tabs("close",'出口订单');
			$("#tt").tabs("close",'出口完结订单');
			$("#tt").tabs("close",'出口流转单');
			$("#tt").tabs("close",'出口订单STO');
			$("#tt").tabs("close",'出口商品规则');
			if($("#tt").tabs("exists",title)){
				$("#tt").tabs("select",title);
			}else{
				$('#tt').tabs('add',{    
				    title:title, 
				    iconCls:'icon-add',
				    href:'<%=path%>/orderOutRuleBS/init.do?pageName=orderOutRule_1',    
				    closable:true   
				});  
			}
		}
		
		function orderOutEnd(title){
			$("#tt").tabs("close",'出口保税订单');
			$("#tt").tabs("close",'出口保税完结订单');
			$("#tt").tabs("close",'出口保税订单STO');
			$("#tt").tabs("close",'出口保税商品规则');
			$("#tt").tabs("close",'出口订单');
			if($("#tt").tabs("exists",title)){
				$("#tt").tabs("select",title);
			}else{
				$('#tt').tabs('add',{    
				    title:title, 
				    iconCls:'icon-add',
				    href:'<%=path%>/orderOut/init.do?pageName=orderOutEnd',    
				    closable:true   
				});  
			}
		}
		
		function orderOutEnd_1(title){
			$("#tt").tabs("close",'出口订单');
			$("#tt").tabs("close",'出口完结订单');
			$("#tt").tabs("close",'出口订单STO');
			$("#tt").tabs("close",'出口流转单');
			$("#tt").tabs("close",'出口商品规则');
			$("#tt").tabs("close",'出口保税订单');
			if($("#tt").tabs("exists",title)){
				$("#tt").tabs("select",title);
			}else{
				$('#tt').tabs('add',{    
				    title:title, 
				    iconCls:'icon-add',
				    href:'<%=path%>/orderOutBS/init.do?pageName=orderOutEnd_1',    
				    closable:true   
				});  
			}
		}
		
		function beLzdOut(title){
			$("#tt").tabs("close",'出口保税订单');
			$("#tt").tabs("close",'出口保税完结订单');
			$("#tt").tabs("close",'出口保税订单STO');
			$("#tt").tabs("close",'出口保税商品规则');
			if($("#tt").tabs("exists",title)){
				$("#tt").tabs("select",title);
			}else{
				$('#tt').tabs('add',{    
				    title:title, 
				    iconCls:'icon-add',
				    href:'<%=path%>/beLzdOut/init.do?pageName=beLzdOut',    
				    closable:true   
				});  
			}
		}
		
		function beLzdOut_1(title){
			$("#tt").tabs("close",'出口订单');
			$("#tt").tabs("close",'出口完结订单');
			$("#tt").tabs("close",'出口流转单');
			$("#tt").tabs("close",'出口订单STO');
			$("#tt").tabs("close",'出口商品规则');
			if($("#tt").tabs("exists",title)){
				$("#tt").tabs("select",title);
			}else{
				$('#tt').tabs('add',{    
				    title:title, 
				    iconCls:'icon-add',
				    href:'<%=path%>/beLzdOut/init.do?pageName=beLzdOut_1',    
				    closable:true   
				});  
			}
		}
		
		
		
		function cost(title){
			if($("#tt").tabs("exists",title)){
				$("#tt").tabs("select",title);
			}else{
				$('#tt').tabs('add',{    
				    title:title, 
				    iconCls:'icon-add',
				    href:'<%=path%>/cost/init.do?pageName=cost',    
				    closable:true   
				});  
			}
		}
		
		function itemForZy(title){
			$("#tt").tabs("close","出口商品");
			if($("#tt").tabs("exists",title)){
				$("#tt").tabs("select",title);
			}else{
				$('#tt').tabs('add',{    
				    title:title, 
				    iconCls:'icon-add',
				    href:'<%=path%>/itemForZy/init.do?pageName=itemForZy',    
				    closable:true   
				});  
			}
		}
		
		function itemForGK(title){
			$("#tt").tabs("close","出口商品");
			if($("#tt").tabs("exists",title)){
				$("#tt").tabs("select",title);
			}else{
				$('#tt').tabs('add',{    
				    title:title, 
				    iconCls:'icon-add',
				    href:'<%=path%>/itemForGKAction/init.do?pageName=itemForGK',    
				    closable:true   
				});  
			}
		}
		
		function itemForCk(title){
			$("#tt").tabs("close","直邮商品");
			if($("#tt").tabs("exists",title)){
				$("#tt").tabs("select",title);
			}else{
				$('#tt').tabs('add',{    
				    title:title, 
				    iconCls:'icon-add',
				    href:'<%=path%>/itemForCk/init.do?pageName=itemForCk',    
				    closable:true   
				});  
			}
		}
		
		function changeOdd(title){
			if($("#tt").tabs("exists",title)){
				$("#tt").tabs("select",title);
			}else{
				$('#tt').tabs('add',{    
				    title:title, 
				    iconCls:'icon-add',
				    href:'<%=path%>/changeOdd/init.do?pageName=changeOdd',    
				    closable:true   
				});  
			}
		}
		
		function orderCommoditie(title){
			if($("#tt").tabs("exists",title)){
				$("#tt").tabs("select",title);
			}else{
				$('#tt').tabs('add',{    
				    title:title, 
				    iconCls:'icon-add',
				    href:'<%=path%>/orderCommoditie/init.do?pageName=orderCommoditie',    
				    closable:true   
				});  
			}
		}
		
		function itemForSj(title){
			if($("#tt").tabs("exists",title)){
				$("#tt").tabs("select",title);
			}else{
				$('#tt').tabs('add',{    
				    title:title, 
				    iconCls:'icon-add',
				    href:'<%=path%>/itemForSj/init.do?pageName=itemForSj',    
				    closable:true   
				});  
			}
		}
		
		function orderBatch(title){
			if($("#tt").tabs("exists",title)){
				$("#tt").tabs("select",title);
			}else{
				$('#tt').tabs('add',{    
				    title:title, 
				    iconCls:'icon-add',
				    href:'<%=path%>/orderBatch/init.do?pageName=orderBatch',    
				    closable:true   
				});  
			}
		}
		
		
		
		function bond(title){
			if($("#tt").tabs("exists",title)){
				$("#tt").tabs("select",title);
			}else{
				$('#tt').tabs('add',{    
				    title:title, 
				    iconCls:'icon-add',
				    href:'<%=path%>/bond/init.do?pageName=bond',    
				    closable:true   
				});  
			}
		}
		function orderBond(title){
			if($("#tt").tabs("exists",title)){
				$("#tt").tabs("select",title);
			}else{
				$('#tt').tabs('add',{    
				    title:title, 
				    iconCls:'icon-add',
				    href:'<%=path%>/orderBond/init.do?pageName=orderBond',    
				    closable:true   
				});  
			}
		}
		
		function orderBondedRule(title){
			if($("#tt").tabs("exists",title)){
				$("#tt").tabs("select",title);
			}else{
				$('#tt').tabs('add',{    
				    title:title, 
				    iconCls:'icon-add',
				    href:'<%=path%>/orderBondedRule/init.do?pageName=orderBondedRule',    
				    closable:true   
				});  
			}
		}
		
		
		function gylFinance(title,tz){
			$("#tt").tabs("close", "供应链金融(已确认)");
			if($("#tt").tabs("exists", title)){
				$("#tt").tabs("select", title);
			}else{
				$('#tt').tabs('add',{    
				    title:title, 
				    iconCls:'icon-add',
				    href:'<%=path%>/gylFinance/init.do?pageName=gylFinance&tz='+tz,    
				    closable:true   
				});  
			}
		}
		
		function gylFinance1(title,tz){
			$("#tt").tabs("close", "供应链金融(未确认)");
			if($("#tt").tabs("exists", title)){
				$("#tt").tabs("select", title);
			}else{
				$('#tt').tabs('add',{    
				    title:title, 
				    iconCls:'icon-add',
				    href:'<%=path%>/gylFinance/init.do?pageName=gylFinance&tz='+tz,    
				    closable:true   
				});  
			}
		}
		
		
		function circulaTion(title,tz){
			$("#tt").tabs("close","流转操作(直邮)");
			if($("#tt").tabs("exists",title)){
				$("#tt").tabs("select",title);
			}else{
				$('#tt').tabs('add',{    
				    title:title, 
				    iconCls:'icon-add',
				    href:'<%=path%>/circulaTion/init.do?pageName=circulaTion&tz='+tz,    
				    closable:true   
				});  
			}
		}
		
		function circulaTion1(title,tz){
			$("#tt").tabs("close","流转操作(保税)");
			if($("#tt").tabs("exists",title)){
				$("#tt").tabs("select",title);
			}else{
				$('#tt').tabs('add',{    
				    title:title, 
				    iconCls:'icon-add',
				    href:'<%=path%>/circulaTion/init.do?pageName=circulaTion&tz='+tz,    
				    closable:true   
				});  
			}
		}
		
		
		function end_waybill(title){
			if($("#tt").tabs("exists", title)){
				$("#tt").tabs("select", title);
			}else{
				$('#tt').tabs('add',{    
				    title:title, 
				    iconCls:'icon-add',
				    href:'<%=path%>/end_waybill/init.do?pageName=end_waybill',    
				    closable:true   
				});  
			}
		}
		function endCompany(title){
			if($("#tt").tabs("exists", title)){
				$("#tt").tabs("select", title);
			}else{
				$('#tt').tabs('add',{    
				    title:title, 
				    iconCls:'icon-add',
				    href:'<%=path%>/endCompany/init.do?pageName=endCompany',    
				    closable:true   
				});  
			}
		}
		function endHeader(title){
			if($("#tt").tabs("exists", title)){
				$("#tt").tabs("select", title);
			}else{
				$('#tt').tabs('add',{    
				    title:title, 
				    iconCls:'icon-add',
				    href:'<%=path%>/endHeader/init.do?pageName=endHeader',    
				    closable:true   
				});  
			}
		}
	
		
		function orderPh2(title,tz){
			$("#tt").tabs("close","普货完结订单1");
			if($("#tt").tabs("exists",title)){
				$("#tt").tabs("select",title);
			}else{
				$('#tt').tabs('add',{    
				    title:title, 
				    iconCls:'icon-add',
				    href:'<%=path%>/orderPh/init.do?pageName=orderPh',    
				    closable:true   
				});  
			}
		}
		function countRule(title,tz){
			$("#tt").tabs("close","结算规则");
			if($("#tt").tabs("exists",title)){
				$("#tt").tabs("select",title);
			}else{
				$('#tt').tabs('add',{    
				    title:title, 
				    iconCls:'icon-add',
				    href:'<%=path%>/countRule/init.do?pageName=countRuleUi',    
				    closable:true   
				});  
			}
		}
		 function countM(title,tz){
				$("#tt").tabs("close","结算");
				if($("#tt").tabs("exists",title)){
					$("#tt").tabs("select",title);
				}else{
					$('#tt').tabs('add',{    
					    title:title, 
					    iconCls:'icon-add',
					    href:'<%=path%>/countOrder/init.do?pageName=countM',  
					    closable:true   
					});  
				}
			} 
			
			
			function stojs(title){
				if($("#tt").tabs("exists",title)){
					$("#tt").tabs("select",title);
				}else{
					$('#tt').tabs('add',{    
					    title:title, 
					    iconCls:'icon-add',
					    href:'<%=path%>/stojs/init.do?pageName=stojs',    
					    closable:true   
					});  
				}
			}
			
			function address(title){
				if($("#tt").tabs("exists",title)){
					$("#tt").tabs("select",title);
				}else{
					$('#tt').tabs('add',{    
					    title:title, 
					    iconCls:'icon-add',
					    href:'<%=path%>/area/init.do?pageName=address',    
					    closable:true   
					});  
				}
			}
			
			function webport(title){
				if($("#tt").tabs("exists",title)){
					$("#tt").tabs("select",title);
				}else{
					$('#tt').tabs('add',{    
					    title:title, 
					    iconCls:'icon-add',
					    href:'<%=path%>/webzu/init.do?pageName=webPort',    
					    closable:true   
					});  
				}
			}
			function timedtask(title){
				if($("#tt").tabs("exists",title)){
					$("#tt").tabs("select",title);
				}else{
					$('#tt').tabs('add',{    
					    title:title, 
					    iconCls:'icon-add',
					    href:'<%=path%>/timedTask/init.do?pageName=timedTask',    
					    closable:true   
					});  
				}
			}
			
			function customsDeposit(title){
				if($("#tt").tabs("exists",title)){
					$("#tt").tabs("select",title);
				}else{
					$('#tt').tabs('add',{    
					    title:title, 
					    iconCls:'icon-add',
					    href:'<%=path%>/customsDeposit/init.do?pageName=customsDeposit',    
					    closable:true   
					});  
				}
			}
			
			function customsDepositSku(title){
				if($("#tt").tabs("exists",title)){
					$("#tt").tabs("select",title);
				}else{
					$('#tt').tabs('add',{    
					    title:title, 
					    iconCls:'icon-add',
					    href:'<%=path%>/customsDepositSku/init.do?pageName=customsDepositSku',    
					    closable:true   
					});  
				}
			}
			
			
			function customsParam(title){
				if($("#tt").tabs("exists",title)){
					$("#tt").tabs("select",title);
				}else{
					$('#tt').tabs('add',{    
					    title:title, 
					    iconCls:'icon-add',
					    href:'<%=path%>/customsParam/init.do?pageName=customsParam',    
					    closable:true   
					});  
				}
			}
			
			
			function ytobill(title,tz){
				console.log(1);
				$("#tt").tabs("close","YTO已签收");
				if($("#tt").tabs("exists",title)){
					$("#tt").tabs("select",title);
					console.log(2);
				}else{
					console.log(tz);
					$('#tt').tabs('add',{    
					    title:title, 
					    iconCls:'icon-add',
					    href:'<%=path%>/ytoBillAction/init.do?pageName=YtoBill&tz='+tz,
					    closable:true   
					});  
				}
			}
			
			function ytobill1(title,tz){
				$("#tt").tabs("close","YTO未签收");
				if($("#tt").tabs("exists",title)){
					$("#tt").tabs("select",title);
				}else{
					$('#tt').tabs('add',{    
					    title:title, 
					    iconCls:'icon-add',
					    href:'<%=path%>/ytoBillAction/init.do?pageName=YtoBill&tz='+tz,
					    closable:true   
					});  
				}
			}
			function outshipto(title,tz){
				if($("#tt").tabs("exists",title)){
					$("#tt").tabs("select",title);
				}else{
					$('#tt').tabs('add',{    
					    title:title, 
					    iconCls:'icon-add',
					    href:'<%=path%>/outshipto/init.do?pageName=outshipto',
					    closable:true   
					});  
				}
			}
			
	</script>
</head>

<body class="easyui-layout">
	<div id="cc" class="easyui-layout" style="width:100%;height:100%;">   
	    <div data-options="region:'north',border:false" style="height:100px;">
	    	<%-- <img alt="logo" src="<%=path%>/images/newlogo.png" style="padding: 20px 0 0 20px;"> --%>
	    	 <img alt="ZT-OMS" style="padding: 20px 0 0 20px;">
	    	<div align="right">
	    		欢迎您：<%=name%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	    	</div>
	    	<div align="right">
	    		<a href="<%=path%>/login/loginOut.do" >退出系统</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	    	</div>
	    	<c:if test="${userName eq 'admin' }">
	    	<script type="text/javascript">
	    		</script>
	    		<c:if test="${numForOBE!=0 || numForOME!=0}">
	    		<div align="right">
	    			<c:if test="${numForOBE!=0}">
	    				保税异常单：<font color='red'>${numForOBE}</font>
	    			</c:if>
	    			<c:if test="${numForOME!=0}">
	    				直邮异常单：<font color='red'>${numForOME}</font>
	    			</c:if>
	    			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	    		</div>
	    		</c:if>
	    		<c:if test="${fn:length(warningCustomsDeposit)!=0 }">
	    			<script type="text/javascript">
	    				$.messager.alert('系统提示',"存在担保金所剩余额不多！");
	    			</script> 
	    			<div align="right">
	    				<c:forEach items="${warningCustomsDeposit }" var="item">
	    					担保企业为<font color='red'>${item.companyname}</font>担保金不足&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp<br/>
	    				</c:forEach>
	    			</div>
	    		</c:if>
	    	</c:if>
	    </div>   
	    <div data-options="region:'west',title:'菜单',split:true" style="width:200px;">
	    	<ul id="menu">
				<c:if test='<%=userRole.contains("bonded")%>'>
					<li>
						<a href="#">保税业务</a>
						<ul>
							<li><a href="javascript:void(0);"  data-options="plain:true" onclick="orderBonded2('保税订单','4')">保税订单</a></li>
							<li><a href="javascript:void(0);"  data-options="plain:true" onclick="orderBonded1('保税完结订单','9')">完结订单</a></li>
							<li><a href="javascript:void(0);"  data-options="plain:true" onclick="orderBonded3('保税取消订单','8')">取消订单</a></li>
							<li><a href="javascript:void(0);"  data-options="plain:true" onclick="orderBond('保税保证金')">保证金</a></li>							
						</ul>
					</li>
				</c:if>
				<c:if test='<%=userRole.contains("mail")%>'>
					<li>
						<a href="#">直邮业务</a>
						<ul>
							<li><a href="javascript:void(0);"  data-options="plain:true" onclick="orderMail('订单管理(直邮未完结)','0')">未完结订单</a> </li>							
							<li><a href="javascript:void(0);"  data-options="plain:true" onclick="orderMail1('订单管理(直邮完结)','9')">已完结订单</a> </li>

						<!-- 	<li><a href="javascript:void(0);"  data-options="plain:true" onclick="orderBatch('订单批次')">订单批次</a> </li> -->

						   <!--  <li><a href="javascript:void(0);"  data-options="plain:true" onclick="orderBatch('订单批次')">订单批次</a> </li> -->

							<li><a href="javascript:void(0);"  data-options="plain:true" onclick="bond('保证金')">保证金</a></li>

							<!--<li><a href="javascript:void(0);"  data-options="plain:true" onclick="cost('直邮结算')">直邮结算</a></li>  -->

						</ul>
					</li>
				</c:if>
				<c:if test='<%=userRole.contains("bondedAudit")%>'>
					<li>
						<a href="#">保税审单</a>
						<ul>
							<li><a href="javascript:void(0);" data-options="plain:true" onclick="orderAudit('订单保税(平台推送)','0')">保税审单(平台推送)</a></li>
							<li><a href="javascript:void(0);" data-options="plain:true" onclick="orderAudit2('订单保税(系统导入)','1')">保税审单(系统导入)</a></li>
							<li><a href="javascript:void(0);" data-options="plain:true" onclick="orderAudit3('订单保税(完结未WMS)','2')">保税审单(完结未WMS)</a></li>
							<li><a href="javascript:void(0);" data-options="plain:true" onclick="orderAudit1('订单保税(审核完结)','9')">保税完结</a></li>
							<li><a href="javascript:void(0);" data-options="plain:true" onclick="orderAudit4('订单保税(取消订单)','8')">取消订单</a></li>
							<li><a href="javascript:void(0);" data-options="plain:true" onclick="orderAudit5('订单保税(异常订单)','7')">异常订单</a></li>
							<li><a href="javascript:void(0);"  data-options="plain:true" onclick="orderBondedRule('快递公司规则')">快递公司规则</a></li>
							
						</ul>
					</li>
				</c:if>
				<c:if test='<%=userRole.contains("mailAudit")%>'>
					<li>
						<a href="#">直邮审单</a>
						<ul>
							<li><a href="javascript:void(0);" data-options="plain:true" onclick="orderMailAudit('订单直邮(审核)','0')">直邮审核</a></li>
							<li><a href="javascript:void(0);"  data-options="plain:true" onclick="orderMailAudit2('订单直邮(待运抵)','2')">直邮待运抵</a> </li>
							<li><a href="javascript:void(0);" data-options="plain:true" onclick="orderMailAudit1('订单直邮(完结)','9')">直邮完结</a></li>
							<li><a href="javascript:void(0);" data-options="plain:true" onclick="orderMailAudit3('订单直邮(异常)','7')">直邮异常</a></li>
						</ul>
					</li>
				</c:if>
				<c:if test='<%=userRole.contains("receiceHghz")%>'>
					<li>
						<a href="#">订单回执</a>
						<ul>
							<li><a href="javascript:void(0);" data-options="plain:true" onclick="receiveHghzData('订单回执(未处理)','0')">未处理</a></li>
							<li><a href="javascript:void(0);" data-options="plain:true" onclick="receiveHghzData1('订单回执(已处理)','1')">已处理</a></li>
							
						</ul>
					</li>
				</c:if>
				<c:if test='<%=userRole.contains("accept")%>'>
					<li>
						<a href="#">物流轨迹</a>
						<ul>
							<li><a href="javascript:void(0);"  data-options="plain:true" onclick="waybill('未签收','0')">未签收</a></li>
							<li><a href="javascript:void(0);"  data-options="plain:true" onclick="waybill1('已签收','1')">已签收</a></li>
							
						</ul>
					</li>
					<li>
						<a href="#">圆通物流轨迹</a>
						<ul>
							<li><a href="javascript:void(0);"  data-options="plain:true" onclick="ytobill('YTO未签收','0')">未签收</a></li>
							<li><a href="javascript:void(0);"  data-options="plain:true" onclick="ytobill1('YTO已签收','1')">已签收</a></li>
							
						</ul>
					</li>
					<li>
						<a href="#">面单打印</a>
						<ul>
							<li><a href="javascript:void(0);"  data-options="plain:true" onclick="orderAccept('未打印','0')" >未打印</a></li>
							<li><a href="javascript:void(0);"  data-options="plain:true" onclick="orderAccept1('已打印','1')" >已打印</a></li>
						</ul>
					</li>
				</c:if>
				
					<li>
						<a href="#">商品信息</a>
						<ul>
						
							<li><a href="javascript:void(0);"  data-options="plain:true" onclick="itemForGK('商品管控')">商品管控</a></li>
						
							<c:if test='<%=userRole.contains("item")%>'>
							<li><a href="javascript:void(0);"  data-options="plain:true" onclick="itemForZy('直邮商品')">直邮商品</a></li>
							<li><a href="javascript:void(0);"  data-options="plain:true" onclick="item('保税商品')">保税商品</a></li>
							</c:if>
							<c:if test='<%=userRole.contains("cksp")%>'>
							<li><a href="javascript:void(0);"  data-options="plain:true" onclick="itemForCk('出口商品')">出口商品</a></li>
							</c:if>
							<c:if test='<%=userRole.contains("item")%>'>
							<li><a href="javascript:void(0);"  data-options="plain:true" onclick="itemForSj('上架商品')">上架商品</a></li>
							<li><a href="javascript:void(0);"  data-options="plain:true" onclick="tinventory('库存管理')">库存管理</a></li>
							<li><a href="javascript:void(0);"  data-options="plain:true" onclick="materiel('物料管理')">物料管理</a></li>
							<li><a href="javascript:void(0);"  data-options="plain:true" onclick="orderCommoditie('WMS耗材管理')">WMS耗材管理</a></li>
							</c:if>
						</ul>
					</li>
			
				<c:if test='<%=userRole.contains("cz_rk")%>'>
					<li>
						<a href="#">曌通场站</a>
						<ul>
							<li><a href="javascript:void(0);"  data-options="plain:true" onclick="godownEntry('到货理货')">到货理货</a></li>
							<li><a href="javascript:void(0);"  data-options="plain:true" onclick="godownEntryCheck('状态复合')">状态复合</a></li>
						<!-- 	<li><a href="javascript:void(0);"  data-options="plain:true" onclick="entryBill('出口商品')">出口商品</a></li>
							<li><a href="javascript:void(0);"  data-options="plain:true" onclick="entryBillList('出口清单界面')">出口清单界面</a></li>
							<li><a href="javascript:void(0);"  data-options="plain:true" onclick="imEntryBillList('进口个人物品申报单界面')">进口个人物品申报单界面</a></li> -->
						</ul>
					</li>
				</c:if>
				<c:if test='<%=userRole.contains("cz_fh")%>'>
					<li>
						<a href="#">复核称重</a>
						<ul>
							<li><a href="javascript:void(0);"  data-options="plain:true" onclick="orderCheck('复核称重')">复核称重</a></li>
						</ul>
					</li>
					<li>
						<a href="#">出区核放</a>
						<ul>
							<li><a href="javascript:void(0);"  data-options="plain:true" onclick="nuclearRelease('出区核放')">出区核放</a></li>
						</ul>
					</li>
				</c:if>
				<c:if test='<%=userRole.contains("user")%>'>
					<li>
						<a href="#">用户管理</a>
						<ul>
							<li><a href="javascript:void(0);"  data-options="plain:true" onclick="users('用户')">用户</a></li>
						</ul>
					</li>
				</c:if>
				<c:if test='<%=userRole.contains("tsjgq")%>'>
					<li>
						<a href="#">特殊监管</a>
						<ul>
							<li><a href="javascript:void(0);"  data-options="plain:true" onclick="planingReceipts('计划入库单')">计划入库单</a></li>
							<li><a href="javascript:void(0);"  data-options="plain:true" onclick="wmsactualreceive('核放实收')">实收入库单</a></li>
							<li><a href="javascript:void(0);"  data-options="plain:true" onclick="wmsinout('出入库记录')">出入库记录</a></li>
							<li><a href="javascript:void(0);"  data-options="plain:true" onclick="wmsinoutforzt('出入库统计')">出入库统计</a></li>
							<li><a href="javascript:void(0);"  data-options="plain:true" onclick="wmsinventory('库存记录')">库存记录</a></li>
							<li><a href="javascript:void(0);"  data-options="plain:true" onclick="wmsmerger('归并管理')">归并管理</a></li>
							 <li><a href="javascript:void(0);"  data-options="plain:true" onclick="stockDelete('删单申请')">删单申请</a></li> 
						</ul>
					</li>
				</c:if>
				<c:if test='<%=userRole.contains("TJ")%>'>
					<li>
						<a href="#">统计</a>
						<ul>
							<li><a href="javascript:void(0);"  data-options="plain:true" onclick="taxes('税金')">税金</a></li>
							
						</ul>
					</li>
				</c:if>
			 	<c:if test='<%=userRole.contains("co")%>'>
					<li>
						<a href="#">快递换单</a>
						<ul>
							<li><a href="javascript:void(0);"  data-options="plain:true" onclick="changeOdd('换单管理')">换单管理</a></li>
						</ul>
					</li>
				</c:if>
				<%-- <c:if test='<%=userRole.contains("cir")%>'>
					<li>
						<a href="#">流转操作</a>
						<ul>
							<li><a href="javascript:void(0);"  data-options="plain:true" onclick="circulaTion('流转操作(保税)','0')">流转操作保税</a></li>
							<li><a href="javascript:void(0);"  data-options="plain:true" onclick="circulaTion1('流转操作(直邮)','9')">流转操作直邮</a></li>
						</ul>
					</li>
				</c:if> --%>
				<c:if test='<%=userRole.contains("gylFinance")%>'>
					<li>
						<a href="#">供应链金融</a>
						<ul>
							<li><a href="javascript:void(0);" data-options="plain:true" onclick="gylFinance('供应链金融(未确认)','0')">未确认</a></li>
							<li><a href="javascript:void(0);" data-options="plain:true" onclick="gylFinance1('供应链金融(已确认)','1')">已确认</a></li>
						</ul>
					</li>
				</c:if>
				
					<li>
						<a href="#">申通集成</a>
						<ul>
						<c:if test='<%=userRole.contains("stjc")%>'>
							<li><a href="javascript:void(0);"  data-options="plain:true" onclick="end_waybill('单号发放')">单号发放</a></li>
							<li><a href="javascript:void(0);"  data-options="plain:true" onclick="endCompany('账户开通')">账户开通</a></li>
							</c:if>
								<c:if test='<%=userRole.contains("stj2")%>'>
							<li><a href="javascript:void(0);"  data-options="plain:true" onclick="endHeader('运单管理')">运单管理</a></li>
							</c:if>
						</ul>
					</li>
				
				<c:if test='<%=userRole.contains("zyjs")%>'>
					<li>
						<a href="#">进口流转</a>
						<ul>
							<%-- <c:if test='<%=userRole.contains("collect")%>'>
								<li><a href="javascript:void(0);"  data-options="plain:true" onclick="collect('直邮结算')">直邮结算</a></li>
							</c:if> --%>
							<c:if test='<%=userRole.contains("lzd")%>'>
								<li><a href="javascript:void(0);"  data-options="plain:true" onclick="beLzd('流转单')">流转单</a></li>
							</c:if>		
							<%--  <c:if test='<%=userRole.contains("bsjs")%>'>
								<li><a href="javascript:void(0);"  data-options="plain:true" onclick="bsjs('保税结算')">保税结算</a></li>
							</c:if>	 --%>	 				
						</ul>
					</li>
				</c:if>
			<c:if test='<%=userRole.contains("mmmm")%>'>
					<li>
						<a href="#">普货业务</a>
						<ul>
							<li><a href="javascript:void(0);"  data-options="plain:true" onclick="orderPh1('普货完结订单1','0')">普货订单</a></li>
							<li><a href="javascript:void(0);"  data-options="plain:true" onclick="orderPh2('普货完结订单2','9')">完结订单xx</a></li>
						</ul>
					</li>
				</c:if>
				
				<c:if test='<%=userRole.contains("ckyw")%>'>
					<li>
						<a href="#">出口业务</a>
						<ul>
							<li><a href="javascript:void(0);"  data-options="plain:true" onclick="orderOut('出口订单')">出口订单</a></li>
							<!-- <li><a href="javascript:void(0);"  data-options="plain:true" onclick="outshipto('出口运抵')">出口运抵</a></li> -->
							<li><a href="javascript:void(0);"  data-options="plain:true" onclick="orderOutEnd('出口完结订单')">出口完结订单</a></li>
							<li><a href="javascript:void(0);"  data-options="plain:true" onclick="beLzdOut('出口流转单')">出口流转单</a></li>
							<!-- <li><a href="javascript:void(0);"  data-options="plain:true" onclick="orderOutSTO('出口订单STO')">出口STO</a></li>
							<li><a href="javascript:void(0);"  data-options="plain:true" onclick="orderOutRule('出口商品规则')">出口商品规则</a></li> -->
						</ul>
					</li>
				</c:if>
				
				<c:if test='<%=userRole.contains("ckbsyw")%>'>
					<li>
						<a href="#">出口保税业务</a>
						<ul>
							<li><a href="javascript:void(0);"  data-options="plain:true" onclick="orderOut_1('出口保税订单')">出口保税订单</a></li>
							<li><a href="javascript:void(0);"  data-options="plain:true" onclick="orderOutEnd_1('出口保税完结订单')">出口保税完结订单</a></li>
<!-- 							<li><a href="javascript:void(0);"  data-options="plain:true" onclick="beLzdOut_1('出口保税流转单')">出口保税流转单</a></li> -->
							<li><a href="javascript:void(0);"  data-options="plain:true" onclick="orderOutSTO_1('出口保税订单STO')">出口保税STO</a></li>
							<!-- <li><a href="javascript:void(0);"  data-options="plain:true" onclick="orderOutRule_1('出口保税商品规则')">出口保税商品规则</a></li> -->
						</ul>
					</li>
				</c:if>
				
				<c:if test='<%=userRole.contains("collect")%>'>
					<li>
						<a href="#">直邮结算</a>
						<ul>
							<li><a href="javascript:void(0);"  data-options="plain:true" onclick="countRule('结算规则')">结算规则</a></li>
							<li><a href="javascript:void(0);"  data-options="plain:true" onclick="countM('直邮结算')">直邮结算</a></li>
						</ul>
					</li>
				</c:if>
				<c:if test='<%=userRole.contains("stjs")%>'>
					<li>
						<a href="#">快递结算</a>
						<ul>
							<li><a href="javascript:void(0);"  data-options="plain:true" onclick="stojs('快递结算')">快递结算</a></li>
						</ul>
					</li>
				</c:if>
				
				<c:if test='<%=userRole.contains("tt")%>'>
					<li>
						<a href="#">省市区划分</a>
						<ul>
							<li><a href="javascript:void(0);" data-options="plain:true" onclick="address('省市区菜单')">省市区菜单</a></li>
						</ul>
					</li>
				</c:if>
				<c:if test='<%=userRole.contains("jk")%>'>
					<li>
						<a href="#">接口</a>
						<ul>
							<li><a href="javascript:void(0);" data-options="plain:true" onclick="webport('接口')">接口</a></li>
							<li><a href="javascript:void(0);" data-options="plain:true" onclick="timedtask('定时任务')">定时任务</a></li>
						</ul>
					</li>
				</c:if>
				<c:if test='<%=userRole.contains("customsDeposit")%>'>
					<li>
						<a href="#">海关担保金</a>
						<ul>
							<li><a href="javascript:void(0);"  data-options="plain:true" onclick="customsDeposit('担保金')">担保金</a></li>
							<li><a href="javascript:void(0);"  data-options="plain:true" onclick="customsDepositSku('担保金明细')">担保金明细</a></li>
							<li><a href="javascript:void(0);"  data-options="plain:true" onclick="customsParam('海关编码参数')">海关编码参数</a></li>
						</ul>
					</li>
				</c:if>
			</ul>
	    </div>
	   
	    	<%-- <div id="aa" class="easyui-accordion" data-options="fit:true,border:false"> 
	    		<c:if test='<%=userRole.contains("push")%>'>
	    	
		    		<div class="22" title="订单管理(推送)" data-options="iconCls:'icon-save',selected:true" style="overflow:auto;padding:11px;height:100px!important" >   
				        <div>
				        	<a href="javascript:void(0);" class="easyui-linkbutton"  data-options="plain:true" onclick="orderPush('订单管理(推送)')">订单管理(推送)</a>  
		    			</div>
		    		</div> 
	    		</c:if>
	    		<c:if test='<%=userRole.contains("accept")%>'>
		    		<div title="辅助" data-options="iconCls:'icon-save',selected:true" style="overflow:auto;padding:10px;">  
		    			<div>
				        	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true" onclick="orderAccept('订单打印')">面单打印</a> 
				        </div> 
				        <div> 
				        	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true" onclick="waybill('物流轨迹')">物流状态</a>  
		    			</div>
		    		</div>
	    		</c:if>  
	    	
	    	
    			
			</div>  --%>
	      
		<div data-options="region:'center',border:false,plain:true">
	    	<div id="tt" class="easyui-tabs" fit=true>   
			    <div title="首页" style="text-align: center;font-size: 24px;">   
			        <p>欢迎来到ZT-OMS系统</p>    
			    </div>   
			</div> 
		</div>   
		
	</div>  
	<div style="height: 100px;text-align: center;font-size: 12px;">
		<p>版权所有,转载请著名出处</p>
	</div> 
	<object id="LODOP_OB" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0> 
       	<embed id="LODOP_EM" type="application/x-print-lodop" width=0 height=0></embed>
	</object>
</body>
</html>