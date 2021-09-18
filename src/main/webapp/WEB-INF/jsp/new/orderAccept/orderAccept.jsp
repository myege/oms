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
<title>面单打印（未打印）</title>
<%@ include file="/static/common/head.txt"%>
</head>
<body class="gray-bg">
	<div class="container-div">
		<div class="row">
			<div class="col-sm-12 search-collapse">
				<form id="data-form">
					<div class="select-list">
						<ul>
							<li>
								<div class="input-group"><!--保持内联，消除边框，类似于form-inline-->
		 						<div class="input-group-btn"><!--不换行，与相邻元素内联，包含dropdown-->
			  						<select id="serachname" name="serachname" class="selectpicker show-tick form-control" data-max-options="3" data-live-search="false">
										<option value="1">订单号</option>
										<option value="2">快递单号</option>
									</select>
		  							</div>
									<input type="text" class="form-control" id="getValue" placeholder="请输入关键词" style="height: 34px" onkeydown='if(event.keyCode==13){return false;}'/>
									<span class="input-group-btn"><!--不换行，与相邻元素内联-->
		      						<a class="btn btn-primary" id="search">
		      							<i class="fa fa-search"></i>
		      							&nbsp;搜索
		      						</a>
									</span>
								</div>
						</li>
								
									&nbsp;&nbsp;&nbsp;&nbsp;
							<li>	
									<div class="input-group"><!--保持内联，消除边框，类似于form-inline-->
									<div class="input-group-btn">
														<select id=expressTemplateId name="expressTemplateId" class="selectpicker show-tick form-control" data-max-options="3" data-live-search="false">
															<option value="1" selected>申通100*180</option>
															<option value="2" >汇通100*180</option>
															<option value="3">申通100*150</option>
											
														<!-- 	<option value="5">代收货款180</option> -->
															<option value="6">代收货款150</option>
															<option value="7">顺丰快递模板</option>
															<option value="8" >百事带LOGO</option>
															<option value="9" >EMS</option>
															<option value="10">圆通100*180</option>
														<!--<option value="4">誉名100*150</option>
															<option value="3">顺丰快递模板</option>
											
															<option value="4">誉名100*150</option>
															<!-- <option value="3">顺丰快递模板</option>
															<option value="4">中通快递模板</option>
															<option value="5">圆通快递模板</option>
															<option value="6">韵达快递模板</option>
															<option value="7">EMS模板</option> -->
														</select>
													</div>
														<span class="input-group-btn"><!--不换行，与相邻元素内联-->
														<a class="btn btn-primary" id="order_accept_btn_2" onclick="selectExpress()">
							      							<i class="fa fa-download"></i>&nbsp;打印
							      						</a>
							      						</span>
								</div>
								</li>
								
						</ul>
					</div>
				</form>
			</div>
			<div class="btn-group-sm" role="group">
											
			</div>
			<div class="col-sm-12 select-table table-striped">
			<button type="button" id="order_bonded_btn_14" style="margin-top: 10px;" class="btn btn-success" onclick="$.operate.importModel('50%','50%','<%=path%>/import/importOrderAccept.jsp')">
												<i class="fa fa-download"></i> 导入
											</button>
											<a style='color:blue' href='<%=path%>/xls/orderAccept.xlsx'>下载订单模板</a>
											<button type="button" id="order_bonded_btn_14" style="margin-top: 10px;" class="btn btn-warning" onclick="removeuser()">
												  删除
											</button>
				<table id="bootstrap-table"></table>
			</div>
		</div>
	</div>
	<%@ include file="/static/common/footer.txt"%>
<script type="text/javascript">
/**删除*/
function removeuser(){  
		var row = $("#bootstrap-table").bootstrapTable('getSelections');//返回选中多行  
		if(row.length==0){  
			$.modal.alertWarning("请至少选择一行数据!");  
	    	return false;  
		}
		var expressNumbers = [];  
		for (var i = 0; i < row.length; i++){
       	expressNumbers.push(row[i].expressNumber); //然后把单个id循环放到ids的数组中  
	    }
	    var expressNumber = expressNumbers.join(",");
	    $.modal.confirm('确定要删除这<font color=red>'+row.length+'条订单吗?',function(){
	    	layerMsg("删除中...");   
	    	$.post('<%=basePath%>orderAccept/deleteOrderAccept.do',{expressNumber:expressNumber}, function(result){
	    		layer.close(layer.index);   
	    		if (result!=null){
	    			layer.msg("删除成功", {
          			  icon: 1,
          			  time: 700 //2秒关闭（如果不配置，默认是3秒）
          			}, function(index){
          			  //do something
          				$("#bootstrap-table").bootstrapTable("refresh", {
    					    silent: true //静态刷新
    					});
          		})
                   } else {
                	   layer.msg("删除失败", {
             			  icon: 5,
             			  time: 1500 //2秒关闭（如果不配置，默认是3秒）
             			}, function(index){
             			  //do something
             				$("#bootstrap-table").bootstrapTable("refresh", {
       					    silent: true //静态刷新
       					});
             		})
                   }
               },'json');
	});
	}

	//打印
function selectExpress(){
    
    	var expressTemplateId =  $('#expressTemplateId option:selected') .val();
    
    	if(expressTemplateId == 1) {
    		printExpressSto();
    	} else if(expressTemplateId == 2) {
    		printExpressHto();
    	} else if(expressTemplateId == 3) {
    		printExpressSto150();
    	} else if(expressTemplateId == 4) {
    		printExpressYuMing();
    	} else if(expressTemplateId == 5) {
    		printExpressStoDS180();
    	} else if(expressTemplateId == 6) {
    		printExpressStoDS150();
    	} else if(expressTemplateId == 7) {
    		printExpressSf();
    	} else if(expressTemplateId == 8) {
    		printExpressHto2();
    	} else if(expressTemplateId == 9) {
    		printExpressEMS();
    	} else if(expressTemplateId == 10) {
    		printExpressYTO();
    	} else{
    		alert("模板构建中，暂不支持！！！");
    	}
    }

//遮罩
function layerMsg(text){
	layer.open({
    	  type: 1,
    	  closeBtn: 0,
    	  content: text,
    	  area : ['10%','10%'],
    }); 
}
	/**
	 进行查询 刷新表格 进入 initTable（） 然后根据条件查询  
	**/
	$('#search').click(function(){
		 var ps = $("#bootstrap-table").bootstrapTable("getOptions").pageSize;//获取分页的pagesize
		$('#bootstrap-table').bootstrapTable('refreshOptions',{pageNumber:1,pageSize:ps});//刷新表格  还原到第一页
	})
	
	$(function(){
		var options = {
				//初始显示所有地址
				url : '<%=path%>/orderAccept/findExpressNumber.do',
				//删除地址     
				//排序字段名
				sortName : "createTime",
				//查询条件
				queryParams : queryParams,
				//导入框名
				modalName: "商品",
				//显示字段
				columns : [{field:'column',checkbox:true,align : 'center'},
					{field :'orderNumber', title :'订单号', align :'center'},
	              	{field :'expressCode', title :'快递公司编号', align :'center'},
	              	{field :'expressNumber', title :'快递单号',  align :'center'},
	              	{field :'buyerName', title :'收件人姓名',  align :'left'},
	              	{field :'buyerProvince', title :'收件人省份', align :'center'},
	              	{field :'buyerCity', title :'收件人城市', align :'center'},
	              	{field :'buyerArea', title :'收件人地区',  align :'center'},
	              	{field :'buyerAddress', title :'收件人详细地址',  align :'left'},
	              	{field :'buyerTel', title :'收件人联系电话',  align :'center'},
	              	{field :'sender', title :'寄件人', align :'center'},
	              	{field :'senderTel', title :'寄件人电话', align :'center'},
	              	{field :'senderAddress', title :'寄件人地址',  align :'center'},
	              	{field :'printType', title :'打印状态',  align :'center',formatter:function(value){
					          	if(value==1){
				 		       		return '已打印';
				 	          	}else{
				 	         		return '未打印';
				 	            }
	 	                	}
	 	                },]
				}
		//进入初始化表格方法
			//加载时间控件
// 			$.operate.time("time3");
// 			$.operate.time("time4");
			$.table.init(options);
	});
	
	//查询条件
	function queryParams(params){
			var value = $("#getValue").val();
			var name = $('#serachname option:selected') .val();
			var temp = $("#data-form").serializeJsonObject();
            temp["rows"] = params.limit;                        //页面大小
            temp["page"] = (params.offset / params.limit) + 1;  //页码
            temp["sort"] = params.sort;                         //排序列名
            temp["sortOrder"] = params.order;                   //排位命令（desc，asc） 
            //特殊格式的条件处理
            temp["paramValue"] = $("#getValue").val();
            temp["paramName"] = name;
            temp["type"] = "0";
	        return temp;
	}
	
	
	<!--  -----------------------------------打印模板区域-------------------------------------   -->
	  function printExpressYTO(){
	    	var clsArr = $("#bootstrap-table").bootstrapTable('getSelections');
			var ids = "";
			if (clsArr.length == 0) {
				$.modal.alertWarning('没有选择');
			} else {
				var now = new Date();
				var LODOP = getLodop(document.getElementById('LODOP_OB'),document.getElementById('LODOP_EM'));
				//var LODOP = document.getElementById('LODOP_EM');
				LODOP.PRINT_INITA(0,0,400,688,"");
				for (var i=0; i<clsArr.length; i++) {
					ids += clsArr[i].id + ',';
					LODOP.NewPageA();
					  var buyerProvince = "";
					var buyerCity = "";
					var buyerArea = "";
					if(clsArr[i].buyerProvince != undefined) {
						buyerProvince = clsArr[i].buyerProvince;
					}
					if(clsArr[i].buyerCity != undefined) {
						buyerCity = clsArr[i].buyerCity;
					}
					if(clsArr[i].buyerArea != undefined) {
						buyerArea = clsArr[i].buyerArea;
					}
					
					var goodsArr = clsArr[i].goodsList;
					var num = 0;
					var goodsName;
					var weight = 0;
					for (var j=0; j<goodsArr.length; j++) {
						if(j>0){
							goodsName =goodsName + "\r\n" + goodsArr[j].goodsName;
						}else{
							goodsName =  goodsArr[j].goodsName;
						}
						num = num + goodsArr[j].num;
						weight = weight + goodsArr[j].weight;
					}

					
					LODOP.ADD_PRINT_RECT(420,0,400,1,0,1);
					LODOP.ADD_PRINT_RECT(534,0,400,1,0,1);
					LODOP.ADD_PRINT_RECT(53,0,400,1,0,1);
					LODOP.ADD_PRINT_RECT(148,36,364,1,0,1);
					LODOP.ADD_PRINT_RECT(204,0,36,1,0,1);
					LODOP.ADD_PRINT_RECT(204,36,364,1,0,1);
					LODOP.ADD_PRINT_RECT(250,0,400,1,0,1);
					LODOP.ADD_PRINT_RECT(338,80,240,83,0,1);
					LODOP.ADD_PRINT_RECT(420,0,120,1,0,1);
					LODOP.ADD_PRINT_RECT(420,120,280,1,0,1);
					LODOP.ADD_PRINT_RECT(457,0,36,1,0,1);
					LODOP.ADD_PRINT_RECT(457,36,284,1,0,1);
					LODOP.ADD_PRINT_RECT(457,320,1,77,0,1);
					LODOP.ADD_PRINT_RECT(495,0,36,1,0,1);
					LODOP.ADD_PRINT_RECT(495,36,284,1,0,1);
					LODOP.ADD_PRINT_RECT(567,0,200,1,0,1);
					LODOP.ADD_PRINT_RECT(567,200,200,1,0,1);
					LODOP.ADD_PRINT_RECT(590,0,36,1,0,1);
					LODOP.ADD_PRINT_RECT(590,36,364,1,0,1);
					LODOP.ADD_PRINT_RECT(639,0,36,1,0,1);
					LODOP.ADD_PRINT_RECT(639,36,364,1,0,1);
					LODOP.ADD_PRINT_TEXT(68,74,264,39,clsArr[i].zyNumber);
					LODOP.SET_PRINT_STYLEA(0,"FontSize",22);
					LODOP.SET_PRINT_STYLEA(0,"Bold",1);
					LODOP.SET_PRINT_STYLEA(0,"Horient",2);
					LODOP.ADD_PRINT_BARCODE(115,145,248,31,"128Auto",clsArr[i].zyName);
					LODOP.SET_PRINT_STYLEA(0,"ShowBarText",0);
					LODOP.ADD_PRINT_TEXT(154,42,88,21,clsArr[i].buyerName);
					LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
					LODOP.SET_PRINT_STYLEA(0,"Bold",1);
					LODOP.ADD_PRINT_TEXT(154,150,125,22,clsArr[i].buyerTel);
					LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
					LODOP.SET_PRINT_STYLEA(0,"Bold",1);
					LODOP.ADD_PRINT_TEXT(173,41,347,27,buyerArea+clsArr[i].buyerAddress);
					LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
					LODOP.SET_PRINT_STYLEA(0,"Bold",1);
					LODOP.ADD_PRINT_TEXT(209,42,88,20,clsArr[i].sender);
					LODOP.ADD_PRINT_TEXT(209,149,127,20,clsArr[i].senderTel);
					LODOP.ADD_PRINT_TEXT(228,42,346,30,clsArr[i].senderAddress);
					LODOP.ADD_PRINT_RECT(110,0,400,1,0,1);
					LODOP.ADD_PRINT_BARCODE(267,37,320,65,"128Auto",clsArr[i].expressNumber);
					LODOP.ADD_PRINT_TEXT(341,5,72,14,now.getFullYear()+"-"+now.getMonth()+1+"-"+now.getDate());
					LODOP.ADD_PRINT_TEXT(356,5,73,14,now.getHours()+":"+now.getMinutes()+":"+now.getSeconds());
					LODOP.SET_PRINT_STYLEA(0,"Bold",1);
					LODOP.ADD_PRINT_TEXT(373,5,62,15,"打印时间");
					LODOP.SET_PRINT_STYLEA(0,"Bold",1);
					LODOP.ADD_PRINT_TEXT(391,5,88,15,"数量："+num);
					LODOP.ADD_PRINT_TEXT(408,5,83,15,"重量："+weight);
					LODOP.ADD_PRINT_TEXT(402,269,48,20,"签收栏");
					LODOP.ADD_PRINT_BARCODE(430,128,267,25,"128Auto",clsArr[i].expressNumber);
					LODOP.ADD_PRINT_RECT(148,0,100,1,0,1);
					LODOP.ADD_PRINT_RECT(338,0,400,1,0,1);
					LODOP.ADD_PRINT_TEXT(461,42,88,22,clsArr[i].buyerName);
					LODOP.ADD_PRINT_TEXT(461,146,88,20,clsArr[i].buyerTel);
					LODOP.ADD_PRINT_TEXT(479,42,271,20,clsArr[i].buyerAddress);
					LODOP.ADD_PRINT_TEXT(501,42,88,20,clsArr[i].sender);
					LODOP.ADD_PRINT_TEXT(501,146,88,20,clsArr[i].senderTel);
					LODOP.ADD_PRINT_TEXT(519,42,269,20,clsArr[i].senderAddress);
					LODOP.ADD_PRINT_RECT(457,320,80,1,0,1);
					LODOP.ADD_PRINT_TEXT(542,7,100,25,"圆通速递");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",16);
					LODOP.SET_PRINT_STYLEA(0,"Bold",1);
					LODOP.ADD_PRINT_TEXT(571,7,218,20,"打印时间："+now.getFullYear()+"-"+now.getMonth()+1+"-"+now.getDate()+"  "+now.getHours()+":"+now.getMinutes()+":"+now.getSeconds());
					LODOP.ADD_PRINT_TEXT(571,237,160,20,"数量："+num+"  重量："+weight);
					LODOP.ADD_PRINT_TEXT(595,42,88,20,clsArr[i].buyerName);
					LODOP.ADD_PRINT_TEXT(595,146,88,20,clsArr[i].buyerTel);
					LODOP.ADD_PRINT_TEXT(614,42,356,30,clsArr[i].buyerAddress);
					LODOP.ADD_PRINT_TEXT(647,42,88,20,clsArr[i].sender);
					LODOP.ADD_PRINT_TEXT(647,146,88,20,clsArr[i].senderTel);
					LODOP.ADD_PRINT_TEXT(666,42,356,30,clsArr[i].senderAddress);
					LODOP.ADD_PRINT_TEXT(542,209,190,25,clsArr[i].expressNumber);
					LODOP.SET_PRINT_STYLEA(0,"FontSize",13);
					LODOP.SET_PRINT_STYLEA(0,"Bold",1);
					LODOP.ADD_PRINT_BARCODE(342,319,88,83,"QRCode",clsArr[i].zyNumber+"-"+clsArr[i].expressNumber);
					LODOP.ADD_PRINT_BARCODE(465,319,100,71,"QRCode",clsArr[i].zyNumber+"-"+clsArr[i].expressNumber);
					LODOP.ADD_PRINT_TEXT(341,81,243,15,"快件送达收件人地址，经收件人或收件人（寄");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
					LODOP.ADD_PRINT_TEXT(164,8,30,30,"收");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
					LODOP.SET_PRINT_STYLEA(0,"Bold",1);
					LODOP.ADD_PRINT_TEXT(210,8,30,30,"寄");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
					LODOP.SET_PRINT_STYLEA(0,"Bold",1);
					LODOP.ADD_PRINT_TEXT(462,8,30,30,"收");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
					LODOP.SET_PRINT_STYLEA(0,"Bold",1);
					LODOP.ADD_PRINT_TEXT(502,8,30,30,"寄");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
					LODOP.SET_PRINT_STYLEA(0,"Bold",1);
					LODOP.ADD_PRINT_TEXT(598,8,30,30,"收");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
					LODOP.SET_PRINT_STYLEA(0,"Bold",1);
					LODOP.ADD_PRINT_TEXT(651,8,30,30,"寄");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
					LODOP.SET_PRINT_STYLEA(0,"Bold",1);
					LODOP.ADD_PRINT_TEXT(354,82,243,15,"件人）允许的代收人签字，视为送达。您的签");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
					LODOP.ADD_PRINT_TEXT(369,82,243,15,"字代表您已验收此包裹，并已确认商品信息无");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
					LODOP.ADD_PRINT_TEXT(385,81,243,15,"误、包装完好、没有划痕、破损等表面质量问");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
					LODOP.ADD_PRINT_TEXT(399,81,126,15,"题。");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",8);


				}
				var printResult = LODOP.PREVIEW();
				//var printResult = LODOP.PRINT();
				//var printResult = LODOP.PRINT_DESIGN();
					$.post('<%=path%>/orderAccept/updatePrintType.do', {
						ids : ids
					}, function(result) {
						alert(result);
						$("#bootstrap-table").bootstrapTable("refresh", {silent: true });
					});
				
			}
	    }
	    
	    function printExpressSto(){
			var clsArr = $("#bootstrap-table").bootstrapTable('getSelections');
			var ids = "";
			if (clsArr.length == 0) {
				$.modal.alertWarning('没有选择');
			} else {
				var LODOP = getLodop(document.getElementById('LODOP_OB'),document.getElementById('LODOP_EM'));
				//var LODOP = document.getElementById('LODOP_EM');
				LODOP.PRINT_INITA(0,0,400,800,"");
				for (var i=0; i<clsArr.length; i++) {
					ids += clsArr[i].id + ',';
					LODOP.NewPageA();
					LODOP.ADD_PRINT_TEXT("6.61mm",250,120,35,"标准快件");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",20);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.ADD_PRINT_TEXT(60,250,120,20,"精彩生活·快递欢乐");
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
				    LODOP.ADD_PRINT_RECT(80,0,400,1,0,1);
					  var buyerProvince = "";
					var buyerCity = "";
					var buyerArea = "";
					if(clsArr[i].buyerProvince != undefined) {
						buyerProvince = clsArr[i].buyerProvince;
					}
					if(clsArr[i].buyerCity != undefined) {
						buyerCity = clsArr[i].buyerCity;
					}
					if(clsArr[i].buyerArea != undefined) {
						buyerArea = clsArr[i].buyerArea;
					}
					LODOP.ADD_PRINT_TEXT(80,20,348,50,buyerProvince + buyerCity);
					LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",32);
					//LODOP.ADD_PRINT_RECT(132,0,400,1,0,1);
					LODOP.ADD_PRINT_TEXT(125,20,177,30,buyerArea);
					LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",32);
					LODOP.ADD_PRINT_RECT(165,0,400,1,0,1);
					LODOP.ADD_PRINT_TEXT(185,10,30,40,"收\n件");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.ADD_PRINT_RECT(165,40,1,140,0,1);
					LODOP.ADD_PRINT_RECT(245,0,290,1,0,1);
					LODOP.ADD_PRINT_TEXT(168,100,190,20,clsArr[i].buyerName);
					LODOP.ADD_PRINT_TEXT(188,90,200,20,clsArr[i].buyerTel);
					LODOP.ADD_PRINT_TEXT(188,45,60,20,"电话：");
					LODOP.ADD_PRINT_TEXT(208,45,60,20,"地址：");
					LODOP.ADD_PRINT_TEXT(208,90,200,40,clsArr[i].buyerAddress);
					LODOP.ADD_PRINT_RECT(165,290,1,140,0,1);
					LODOP.ADD_PRINT_TEXT(168,290,110,20,"服  务");
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.ADD_PRINT_RECT(188,290,110,1,0,1);
					LODOP.ADD_PRINT_TEXT(255,10,30,40,"寄\n件");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.ADD_PRINT_RECT(305,0,400,1,0,1);
					LODOP.ADD_PRINT_TEXT(248,100,190,20,clsArr[i].sender);
					LODOP.ADD_PRINT_TEXT(268,45,60,20,"电话：");
					LODOP.ADD_PRINT_TEXT(268,90,190,20,clsArr[i].senderTel);
					LODOP.ADD_PRINT_TEXT(288,45,240,20,"地址：" + clsArr[i].senderAddress);
					LODOP.ADD_PRINT_BARCODE(312,50,300,50,"128Auto",clsArr[i].expressNumber);
					LODOP.ADD_PRINT_RECT(365,0,400,1,0,1);
					LODOP.ADD_PRINT_TEXT(368,20,190,60,"订单号："+clsArr[i].orderNumber);
					LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
					LODOP.ADD_PRINT_RECT(365,210,1,60,0,1);
					LODOP.ADD_PRINT_TEXT(375,215,60,20,"签收人：");
					LODOP.ADD_PRINT_TEXT(400,215,60,20,"时间：");
					LODOP.ADD_PRINT_BARCODE("117mm","40.01mm","60.01mm","12.01mm","128Auto",clsArr[i].expressNumber);
					LODOP.ADD_PRINT_RECT(491,"0mm","109.99mm","0.26mm",0,1);
					LODOP.ADD_PRINT_TEXT(505,10,30,"12.01mm","收\n件");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.ADD_PRINT_RECT("129.99mm",40,1,200,0,1);
					LODOP.ADD_PRINT_RECT(570,0,400,1,0,1);
					LODOP.ADD_PRINT_TEXT(510,45,60,20,"电话：");
					LODOP.ADD_PRINT_TEXT(530,45,60,20,"地址：");
					LODOP.ADD_PRINT_TEXT(493,100,200,20,clsArr[i].buyerName);
					LODOP.ADD_PRINT_TEXT(510,90,200,20,clsArr[i].buyerTel);
					LODOP.ADD_PRINT_TEXT(530,90,200,40,clsArr[i].buyerAddress);
					LODOP.ADD_PRINT_TEXT(580,10,30,40,"寄\n件");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.ADD_PRINT_RECT(630,0,400,1,0,1);
					LODOP.ADD_PRINT_TEXT(573,100,200,20,clsArr[i].sender);
					LODOP.ADD_PRINT_TEXT(593,45,60,20,"电话：");
					LODOP.ADD_PRINT_TEXT(593,90,200,20,clsArr[i].senderTel);
					LODOP.ADD_PRINT_TEXT(613,45,60,20,"地址：");
					LODOP.ADD_PRINT_TEXT(613,90,300,20,clsArr[i].senderAddress);
					LODOP.ADD_PRINT_TEXT("10mm",150,100,"7.99mm","已验视");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.ADD_PRINT_BARCODE(372,321,146,66,"QRCode",clsArr[i].expressNumber);
					LODOP.ADD_PRINT_TEXT(640,10,30,40,"内件");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					var goodsArr = clsArr[i].goodsList;
					var num = 0;
					var goodsName;
					for (var j=0; j<goodsArr.length; j++) {
						if(j>0){
							goodsName =goodsName + "\r\n" + goodsArr[j].goodsName;
						}else{
							goodsName =  goodsArr[j].goodsName;
						}
						num = num + goodsArr[j].num;
					}
					LODOP.ADD_PRINT_TEXT(635,155,235,60,goodsName);
					LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
					LODOP.ADD_PRINT_TEXT(635,117,60,20,"品名：");
					LODOP.ADD_PRINT_TEXT(635,70,40,20,num);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.ADD_PRINT_TEXT(168,45,60,20,"收件人：");
					LODOP.ADD_PRINT_TEXT(248,45,60,20,"寄件人：");
					LODOP.ADD_PRINT_TEXT(493,45,60,20,"收件人：");
					LODOP.ADD_PRINT_TEXT(573,45,60,20,"寄件人：");
					LODOP.ADD_PRINT_TEXT(635,35,60,20,"总件数：");
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.ADD_PRINT_RECT(630,115,1,60,0,1);
					LODOP.ADD_PRINT_TEXT(23,149,98,20,"第"+ (i+1) + "单");  
				}
				var printResult = LODOP.PREVIEW();
				//var printResult = LODOP.PRINT();
				//var printResult = LODOP.PRINT_DESIGN();
					$.post('<%=path%>/orderAccept/updatePrintType.do', {
						ids : ids
					}, function(result) {
						alert(result);
						$("#bootstrap-table").bootstrapTable("refresh", {silent: true });
					});
				
			}
		};
		
		function printExpressHto(){
			var clsArr = $("#bootstrap-table").bootstrapTable('getSelections');
			var ids = "";
			if (clsArr.length == 0) {
				$.modal.alertWarning('没有选择');
			} else {
				
				var LODOP = getLodop(document.getElementById('LODOP_OB'),document.getElementById('LODOP_EM'));
				LODOP.PRINT_INITA(0,0,400,800,"");
				for (var i=0; i<clsArr.length; i++) {
					ids += clsArr[i].id + ',';
					LODOP.NewPageA();
					LODOP.ADD_PRINT_TEXT("6.61mm",250,120,35,"标准快件");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",20);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.ADD_PRINT_TEXT(60,250,120,20,"精彩生活·快递欢乐");
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.ADD_PRINT_RECT(80,0,400,1,0,1);
					var buyerProvince = "";
					var buyerCity = "";
					var buyerArea = "";
					if(clsArr[i].buyerProvince != undefined) {
						buyerProvince = clsArr[i].buyerProvince;
					}
					if(clsArr[i].buyerCity != undefined) {
						buyerCity = clsArr[i].buyerCity;
					}
					if(clsArr[i].buyerArea != undefined) {
						buyerArea = clsArr[i].buyerArea;
					}
					LODOP.ADD_PRINT_TEXT(87,20,348,50,clsArr[i].zyNumber);
					LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",22);
					LODOP.ADD_PRINT_RECT(132,0,400,1,0,1);
					LODOP.ADD_PRINT_TEXT(135,20,177,30,clsArr[i].zyName);
					LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",15);
					LODOP.ADD_PRINT_RECT(165,0,400,1,0,1);
					LODOP.ADD_PRINT_TEXT(185,10,30,40,"收\n件");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.ADD_PRINT_RECT(165,40,1,140,0,1);
					LODOP.ADD_PRINT_RECT(245,0,290,1,0,1);
					LODOP.ADD_PRINT_TEXT(168,100,190,20,clsArr[i].buyerName);
					LODOP.ADD_PRINT_TEXT(188,90,200,20,clsArr[i].buyerTel);
					LODOP.ADD_PRINT_TEXT(188,45,60,20,"电话：");
					LODOP.ADD_PRINT_TEXT(208,45,60,20,"地址：");
					LODOP.ADD_PRINT_TEXT(208,90,200,40,buyerProvince + buyerCity + buyerArea+clsArr[i].buyerAddress);
					LODOP.ADD_PRINT_RECT(165,290,1,140,0,1);
					LODOP.ADD_PRINT_TEXT(168,290,110,20,"服  务");
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.ADD_PRINT_RECT(188,290,110,1,0,1);
					LODOP.ADD_PRINT_TEXT(255,10,30,40,"寄\n件");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.ADD_PRINT_RECT(305,0,400,1,0,1);
					LODOP.ADD_PRINT_TEXT(248,100,190,20,clsArr[i].sender);
					LODOP.ADD_PRINT_TEXT(268,45,60,20,"电话：");
					LODOP.ADD_PRINT_TEXT(268,90,190,20,clsArr[i].senderTel);
					LODOP.ADD_PRINT_TEXT(288,45,240,20,"地址：" + clsArr[i].senderAddress);
					LODOP.ADD_PRINT_BARCODE(312,50,300,50,"128Auto",clsArr[i].expressNumber);
					LODOP.ADD_PRINT_RECT(365,0,400,1,0,1);
					LODOP.ADD_PRINT_TEXT(368,20,190,60,"订单号："+clsArr[i].orderNumber);
					LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
					LODOP.ADD_PRINT_RECT(365,210,1,60,0,1);
					LODOP.ADD_PRINT_TEXT(375,215,60,20,"签收人：");
					LODOP.ADD_PRINT_TEXT(400,215,60,20,"时间：");
					LODOP.ADD_PRINT_BARCODE("117mm","40.01mm","60.01mm","12.01mm","128Auto",clsArr[i].expressNumber);
					LODOP.ADD_PRINT_RECT(491,"0mm","109.99mm","0.26mm",0,1);
					LODOP.ADD_PRINT_TEXT(505,10,30,"12.01mm","收\n件");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.ADD_PRINT_RECT("129.99mm",40,1,200,0,1);
					LODOP.ADD_PRINT_RECT(570,0,400,1,0,1);
					LODOP.ADD_PRINT_TEXT(510,45,60,20,"电话：");
					LODOP.ADD_PRINT_TEXT(530,45,60,20,"地址：");
					LODOP.ADD_PRINT_TEXT(493,100,200,20,clsArr[i].buyerName);
					LODOP.ADD_PRINT_TEXT(510,90,200,20,clsArr[i].buyerTel);
					LODOP.ADD_PRINT_TEXT(530,90,200,40,clsArr[i].buyerAddress);
					LODOP.ADD_PRINT_TEXT(580,10,30,40,"寄\n件");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.ADD_PRINT_RECT(630,0,400,1,0,1);
					LODOP.ADD_PRINT_TEXT(573,100,200,20,clsArr[i].sender);
					LODOP.ADD_PRINT_TEXT(593,45,60,20,"电话：");
					LODOP.ADD_PRINT_TEXT(593,90,200,20,clsArr[i].senderTel);
					LODOP.ADD_PRINT_TEXT(613,45,60,20,"地址：");
					LODOP.ADD_PRINT_TEXT(613,90,300,20,clsArr[i].senderAddress);
					LODOP.ADD_PRINT_TEXT("10mm",150,100,"7.99mm","已验视");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.ADD_PRINT_BARCODE(372,321,146,66,"QRCode",clsArr[i].expressNumber);
					LODOP.ADD_PRINT_TEXT(640,10,30,40,"内件");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					var goodsArr = clsArr[i].goodsList;
					var num = 0;
					var goodsName;
					for (var j=0; j<goodsArr.length; j++) {
						if(j>0){
							goodsName =goodsName + "\r\n" + goodsArr[j].goodsName;
						}else{
							goodsName =  goodsArr[j].goodsName;
						}
						num = num + goodsArr[j].num;
					}
					LODOP.ADD_PRINT_TEXT(635,155,235,60,goodsName);
					LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
					LODOP.ADD_PRINT_TEXT(635,117,60,20,"品名：");
					LODOP.ADD_PRINT_TEXT(635,70,40,20,num);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.ADD_PRINT_TEXT(168,45,60,20,"收件人：");
					LODOP.ADD_PRINT_TEXT(248,45,60,20,"寄件人：");
					LODOP.ADD_PRINT_TEXT(493,45,60,20,"收件人：");
					LODOP.ADD_PRINT_TEXT(573,45,60,20,"寄件人：");
					LODOP.ADD_PRINT_TEXT(635,35,60,20,"总件数：");
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.ADD_PRINT_RECT(630,115,1,60,0,1);
					LODOP.ADD_PRINT_TEXT(23,149,98,20,"第"+ (i+1) + "单");

				}
				var printResult = LODOP.PREVIEW();
					$.post('<%=path%>/orderAccept/updatePrintType.do', {
						ids : ids
					}, function(result) {
						alert(result);
						$("#bootstrap-table").bootstrapTable("refresh", {silent: true });
					});
				
			}
		};
		
		function printExpressHto2(){
			var clsArr = $("#bootstrap-table").bootstrapTable('getSelections');
			var ids = "";
			if (clsArr.length == 0) {
				$.modal.alertWarning('没有选择');
			} else {
				var LODOP = getLodop(document.getElementById('LODOP_OB'),document.getElementById('LODOP_EM'));
				LODOP.PRINT_INITA(0,0,400,800,"");
				for (var i=0; i<clsArr.length; i++) {
					ids += clsArr[i].id + ',';
					LODOP.NewPageA();
					LODOP.ADD_PRINT_TEXT("6.61mm",250,120,35,"标准快件");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",20);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.ADD_PRINT_TEXT(60,250,120,20,"精彩生活·快递欢乐");
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.ADD_PRINT_RECT(80,0,400,1,0,1);
					var buyerProvince = "";
					var buyerCity = "";
					var buyerArea = "";
					if(clsArr[i].buyerProvince != undefined) {
						buyerProvince = clsArr[i].buyerProvince;
					}
					if(clsArr[i].buyerCity != undefined) {
						buyerCity = clsArr[i].buyerCity;
					}
					if(clsArr[i].buyerArea != undefined) {
						buyerArea = clsArr[i].buyerArea;
					}
					LODOP.ADD_PRINT_TEXT(87,20,348,50,buyerProvince +" "+ buyerCity +" "+ buyerArea);
					LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",22)
					LODOP.ADD_PRINT_TEXT(87,20,348,50,clsArr[i].zyNumber);
					LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",22);
					LODOP.ADD_PRINT_RECT(132,0,400,1,0,1);
					LODOP.ADD_PRINT_TEXT(135,20,177,30,clsArr[i].zyName);
					LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",15);
					LODOP.ADD_PRINT_RECT(165,0,400,1,0,1);
					LODOP.ADD_PRINT_TEXT(185,10,30,40,"收\n件");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.ADD_PRINT_RECT(165,40,1,140,0,1);
					LODOP.ADD_PRINT_RECT(245,0,290,1,0,1);
					LODOP.ADD_PRINT_TEXT(168,100,190,20,clsArr[i].buyerName);
					LODOP.ADD_PRINT_TEXT(188,90,200,20,clsArr[i].buyerTel);
					LODOP.ADD_PRINT_TEXT(188,45,60,20,"电话：");
					LODOP.ADD_PRINT_TEXT(208,45,60,20,"地址：");
					LODOP.ADD_PRINT_TEXT(208,90,200,40,buyerProvince + buyerCity + buyerArea+clsArr[i].buyerAddress);
					LODOP.ADD_PRINT_RECT(165,290,1,140,0,1);
					LODOP.ADD_PRINT_TEXT(168,290,110,20,"服  务");
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.ADD_PRINT_RECT(188,290,110,1,0,1);
					LODOP.ADD_PRINT_TEXT(255,10,30,40,"寄\n件");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.ADD_PRINT_RECT(305,0,400,1,0,1);
					LODOP.ADD_PRINT_TEXT(248,100,190,20,clsArr[i].sender);
					LODOP.ADD_PRINT_TEXT(268,45,60,20,"电话：");
					LODOP.ADD_PRINT_TEXT(268,90,190,20,clsArr[i].senderTel);
					LODOP.ADD_PRINT_TEXT(288,45,240,20,"地址：" + clsArr[i].senderAddress);
					LODOP.ADD_PRINT_BARCODE(312,50,300,50,"128Auto",clsArr[i].expressNumber);
					LODOP.ADD_PRINT_RECT(365,0,400,1,0,1);
					LODOP.ADD_PRINT_TEXT(368,20,190,60,"订单号："+clsArr[i].orderNumber);
					LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
					LODOP.ADD_PRINT_RECT(365,210,1,60,0,1);
					LODOP.ADD_PRINT_TEXT(375,215,60,20,"签收人：");
					LODOP.ADD_PRINT_TEXT(400,215,60,20,"时间：");
					LODOP.ADD_PRINT_BARCODE("117mm","40.01mm","60.01mm","12.01mm","128Auto",clsArr[i].expressNumber);
					LODOP.ADD_PRINT_RECT(491,"0mm","109.99mm","0.26mm",0,1);
					LODOP.ADD_PRINT_TEXT(505,10,30,"12.01mm","收\n件");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.ADD_PRINT_RECT("129.99mm",40,1,200,0,1);
					LODOP.ADD_PRINT_RECT(570,0,400,1,0,1);
					LODOP.ADD_PRINT_TEXT(510,45,60,20,"电话：");
					LODOP.ADD_PRINT_TEXT(530,45,60,20,"地址：");
					LODOP.ADD_PRINT_TEXT(493,100,200,20,clsArr[i].buyerName);
					LODOP.ADD_PRINT_TEXT(510,90,200,20,clsArr[i].buyerTel);
					LODOP.ADD_PRINT_TEXT(530,90,200,40,clsArr[i].buyerAddress);
					LODOP.ADD_PRINT_TEXT(580,10,30,40,"寄\n件");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.ADD_PRINT_RECT(630,0,400,1,0,1);
					LODOP.ADD_PRINT_TEXT(573,100,200,20,clsArr[i].sender);
					LODOP.ADD_PRINT_TEXT(593,45,60,20,"电话：");
					LODOP.ADD_PRINT_TEXT(593,90,200,20,clsArr[i].senderTel);
					LODOP.ADD_PRINT_TEXT(613,45,60,20,"地址：");
					LODOP.ADD_PRINT_TEXT(613,90,300,20,clsArr[i].senderAddress);
					LODOP.ADD_PRINT_TEXT("10mm",150,100,"7.99mm","已验视");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.ADD_PRINT_BARCODE(372,321,146,66,"QRCode",clsArr[i].expressNumber);
					LODOP.ADD_PRINT_TEXT(640,10,30,40,"内件");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					var goodsArr = clsArr[i].goodsList;
					var num = 0;
					var goodsName;
					for (var j=0; j<goodsArr.length; j++) {
						if(j>0){
							goodsName =goodsName + "\r\n" + goodsArr[j].goodsName;
						}else{
							goodsName =  goodsArr[j].goodsName;
						}
						num = num + goodsArr[j].num;
					}
					LODOP.ADD_PRINT_TEXT(635,155,235,60,goodsName);
					LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
					LODOP.ADD_PRINT_TEXT(635,117,60,20,"品名：");
					LODOP.ADD_PRINT_TEXT(635,70,40,20,num);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.ADD_PRINT_TEXT(168,45,60,20,"收件人：");
					LODOP.ADD_PRINT_TEXT(248,45,60,20,"寄件人：");
					LODOP.ADD_PRINT_TEXT(493,45,60,20,"收件人：");
					LODOP.ADD_PRINT_TEXT(573,45,60,20,"寄件人：");
					LODOP.ADD_PRINT_TEXT(635,35,60,20,"总件数：");
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.ADD_PRINT_RECT(630,115,1,60,0,1);
					LODOP.ADD_PRINT_TEXT(23,149,98,20,"第"+ (i+1) + "单");
					LODOP.ADD_PRINT_IMAGE(5,8,125,75,"<img border='0' src='<%=basePath%>/images/baishi2.png'/>");
					LODOP.SET_PRINT_STYLEA(0,"Stretch",1);

				}
				var printResult = LODOP.PREVIEW();
					$.post('<%=path%>/orderAccept/updatePrintType.do', {
						ids : ids
					}, function(result) {
						alert(result);
						$("#bootstrap-table").bootstrapTable("refresh", {silent: true });
					});
				
			}
		};
		
		function printExpressYuMing(){
			var clsArr = $("#bootstrap-table").bootstrapTable('getSelections');
			var ids = "";
			if (clsArr.length == 0) {
				$.modal.alertWarning('没有选择');
			} else {
				var LODOP = getLodop(document.getElementById('LODOP_OB'),document.getElementById('LODOP_EM'));
				LODOP.PRINT_INITA(0,0,400,800,"");
				for (var i=0; i<clsArr.length; i++) {
					ids += clsArr[i].id + ',';
					LODOP.NewPageA();LODOP.ADD_PRINT_TEXT(20,250,120,30,"标准快件");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",20);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.ADD_PRINT_TEXT(50,250,120,20,"精彩生活·快递欢乐");
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.ADD_PRINT_RECT(70,0,400,1,0,1);
					LODOP.ADD_PRINT_RECT(155,0,400,1,0,1);
					LODOP.ADD_PRINT_TEXT(165,10,30,40,"收\n件");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.ADD_PRINT_RECT(155,40,1,115,0,1);
					LODOP.ADD_PRINT_RECT(220,0,290,1,0,1);
					LODOP.ADD_PRINT_TEXT(158,100,190,20,clsArr[i].buyerName);
					LODOP.ADD_PRINT_TEXT(173,90,200,20,clsArr[i].buyerTel);
					LODOP.ADD_PRINT_TEXT(173,45,60,20,"电话：");
					LODOP.ADD_PRINT_TEXT(188,45,60,20,"地址：");
					LODOP.ADD_PRINT_TEXT(188,90,200,40,clsArr[i].buyerAddress);
					LODOP.ADD_PRINT_RECT(155,290,1,115,0,1);
					LODOP.ADD_PRINT_TEXT(222,10,30,40,"寄\n件");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.ADD_PRINT_TEXT(222,100,190,20,clsArr[i].sender);
					LODOP.ADD_PRINT_TEXT(235,45,60,20,"电话：");
					LODOP.ADD_PRINT_TEXT(235,90,190,20,clsArr[i].senderTel);
					LODOP.ADD_PRINT_TEXT(250,45,240,20,"地址：" + clsArr[i].senderAddress);
					LODOP.ADD_PRINT_BARCODE(280,63,300,45,"128Auto",clsArr[i].expressNumber);
					LODOP.ADD_PRINT_BARCODE("95.01mm",200,190,"12.01mm","128Auto",clsArr[i].expressNumber);
					LODOP.ADD_PRINT_RECT("100.01mm","0mm",190,"0.26mm",0,1);
					LODOP.ADD_PRINT_TEXT("103mm",10,30,"12.01mm","收\n件");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.ADD_PRINT_RECT("100.01mm",40,1,200,0,1);
					LODOP.ADD_PRINT_RECT("119.99mm",0,400,1,0,1);
					LODOP.ADD_PRINT_TEXT("105.01mm",45,60,20,"电话：");
					LODOP.ADD_PRINT_TEXT("109.99mm",45,60,20,"地址：");
					LODOP.ADD_PRINT_TEXT("100.44mm",100,90,20,clsArr[i].buyerName);
					LODOP.ADD_PRINT_TEXT("105.01mm",90,100,20,clsArr[i].buyerTel);
					LODOP.ADD_PRINT_TEXT("109.99mm",90,261,40,clsArr[i].buyerAddress);
					LODOP.ADD_PRINT_TEXT("123.45mm",10,30,40,"寄\n件");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.ADD_PRINT_RECT("136.68mm",0,400,1,0,1);
					LODOP.ADD_PRINT_TEXT("121.6mm",100,200,20,clsArr[i].sender);
					LODOP.ADD_PRINT_TEXT("126.89mm",45,60,20,"电话：");
					LODOP.ADD_PRINT_TEXT("126.89mm",90,200,20,clsArr[i].senderTel);
					LODOP.ADD_PRINT_TEXT("132.19mm",45,60,20,"地址：");
					LODOP.ADD_PRINT_TEXT("132.19mm",90,300,20,clsArr[i].senderAddress);
					LODOP.ADD_PRINT_TEXT(40,148,100,"7.99mm","已验视");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.ADD_PRINT_TEXT("139.33mm",10,30,40,"内件");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					var goodsArr = clsArr[i].goodsList;
					var num = 0;
					var goodsName;
					for (var j=0; j<goodsArr.length; j++) {
						if(j>0){
							goodsName =goodsName + "\r\n" + goodsArr[j].goodsName;
						}else{
							goodsName =  goodsArr[j].goodsName;
						}
						num = num + goodsArr[j].num;
					}
					LODOP.ADD_PRINT_TEXT("138.01mm",155,235,60,goodsName);
					LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
					LODOP.ADD_PRINT_TEXT("138.01mm",117,60,20,"品名：");
					LODOP.ADD_PRINT_TEXT("138.01mm",75,40,20,num);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.ADD_PRINT_TEXT(158,45,60,20,"收件人：");
					LODOP.ADD_PRINT_TEXT(222,45,60,20,"寄件人：");
					LODOP.ADD_PRINT_TEXT("100.44mm",45,60,20,"收件人：");
					LODOP.ADD_PRINT_TEXT("121.6mm",45,60,20,"寄件人：");
					LODOP.ADD_PRINT_TEXT("138.01mm",40,60,20,"总件数：");
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.ADD_PRINT_RECT("136.68mm",115,1,60,0,1);
					LODOP.ADD_PRINT_TEXT(20,149,98,20,"第"+ (i+1) + "单");
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					var buyerProvince = "";
					var buyerCity = "";
					var buyerArea = "";
					if(clsArr[i].buyerProvince != undefined) {
						buyerProvince = clsArr[i].buyerProvince;
					}
					if(clsArr[i].buyerCity != undefined) {
						buyerCity = "-" + clsArr[i].buyerCity;
					}
					if(clsArr[i].buyerArea != undefined) {
						buyerArea = clsArr[i].buyerArea;
					}
					LODOP.ADD_PRINT_TEXT(73,32,380,40,buyerProvince+buyerCity);
					LODOP.SET_PRINT_STYLEA(0,"FontSize",32);
					//LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					/* LODOP.ADD_PRINT_TEXT(73,190,100,40,buyerCity);
					/* LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.ADD_PRINT_TEXT(73,190,100,40,buyerCity);
					LODOP.SET_PRINT_STYLEA(0,"FontSize",32);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.ADD_PRINT_TEXT(73,140,40,40,"-"); 
					LODOP.SET_PRINT_STYLEA(0,"FontSize",32);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);*/
					LODOP.ADD_PRINT_TEXT(115,31,380,40,buyerArea);
					LODOP.SET_PRINT_STYLEA(0,"FontSize",32);
					//LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.ADD_PRINT_TEXT(160,295,60,20,"签收人：");
					LODOP.ADD_PRINT_TEXT(195,295,60,20,"时间：");
					LODOP.ADD_PRINT_IMAGE(20,10,100,40,"<img src='/ztz/images/yuminglogo.png'/> ");
					LODOP.SET_PRINT_STYLEA(0,"Stretch",1);

				}
				var printResult = LODOP.PREVIEW();
					$.post('<%=path%>/orderAccept/updatePrintType.do', {
						ids : ids
					}, function(result) {
						alert(result);
						$("#bootstrap-table").bootstrapTable("refresh", {silent: true });
					});
				
			}
		};
		
		function printExpressSto150(){
			var clsArr = $("#bootstrap-table").bootstrapTable('getSelections');
			var ids = "";
			if (clsArr.length == 0) {
				$.modal.alertWarning('没有选择');
			} else {
				var LODOP = getLodop(document.getElementById('LODOP_OB'),document.getElementById('LODOP_EM'));
				LODOP.PRINT_INITA(0,0,400,800,"");
				for (var i=0; i<clsArr.length; i++) {
					ids += clsArr[i].id + ',';
					LODOP.NewPageA();
					LODOP.ADD_PRINT_TEXT(20,250,120,30,"标准快件");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",20);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.ADD_PRINT_TEXT(50,250,120,20,clsArr[i].orderNumber);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.ADD_PRINT_RECT(70,0,400,1,0,1);
					LODOP.ADD_PRINT_RECT(155,0,400,1,0,1);
					LODOP.ADD_PRINT_TEXT(165,10,30,40,"收\n件");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.ADD_PRINT_RECT(155,40,1,115,0,1);
					LODOP.ADD_PRINT_RECT(220,0,290,1,0,1);
					LODOP.ADD_PRINT_TEXT(158,100,190,20,clsArr[i].buyerName);
					LODOP.ADD_PRINT_TEXT(173,90,200,20,clsArr[i].buyerTel);
					LODOP.ADD_PRINT_TEXT(173,45,60,20,"电话：");
					LODOP.ADD_PRINT_TEXT(188,45,60,20,"地址：");
					LODOP.ADD_PRINT_TEXT(188,90,200,40,clsArr[i].buyerAddress);
					LODOP.ADD_PRINT_RECT(155,290,1,115,0,1);
					LODOP.ADD_PRINT_TEXT(222,10,30,40,"寄\n件");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.ADD_PRINT_TEXT(222,100,190,20,clsArr[i].sender);
					LODOP.ADD_PRINT_TEXT(235,45,60,20,"电话：");
					LODOP.ADD_PRINT_TEXT(235,90,190,20,clsArr[i].senderTel);
					LODOP.ADD_PRINT_TEXT(250,45,240,20,"地址：" + clsArr[i].senderAddress);
					LODOP.ADD_PRINT_BARCODE(280,63,300,45,"128Auto",clsArr[i].expressNumber);
					LODOP.ADD_PRINT_BARCODE("95.01mm",200,190,"12.01mm","128Auto",clsArr[i].expressNumber);
					LODOP.ADD_PRINT_RECT("100.01mm","0mm",190,"0.26mm",0,1);
					LODOP.ADD_PRINT_TEXT("103mm",10,30,"12.01mm","收\n件");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.ADD_PRINT_RECT("100.01mm",40,1,200,0,1);
					LODOP.ADD_PRINT_RECT("119.99mm",0,400,1,0,1);
					LODOP.ADD_PRINT_TEXT("105.01mm",45,60,20,"电话：");
					LODOP.ADD_PRINT_TEXT("109.99mm",45,60,20,"地址：");
					LODOP.ADD_PRINT_TEXT("100.44mm",100,90,20,clsArr[i].buyerName);
					LODOP.ADD_PRINT_TEXT("105.01mm",90,100,20,clsArr[i].buyerTel);
					LODOP.ADD_PRINT_TEXT("109.99mm",90,261,40,clsArr[i].buyerAddress);
					LODOP.ADD_PRINT_TEXT("123.45mm",10,30,40,"寄\n件");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.ADD_PRINT_RECT("136.68mm",0,400,1,0,1);
					LODOP.ADD_PRINT_TEXT("121.6mm",100,200,20,clsArr[i].sender);
					LODOP.ADD_PRINT_TEXT("126.89mm",45,60,20,"电话：");
					LODOP.ADD_PRINT_TEXT("126.89mm",90,200,20,clsArr[i].senderTel);
					LODOP.ADD_PRINT_TEXT("132.19mm",45,60,20,"地址：");
					LODOP.ADD_PRINT_TEXT("132.19mm",90,300,20,clsArr[i].senderAddress);
					LODOP.ADD_PRINT_TEXT(40,148,100,"7.99mm","已验视");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.ADD_PRINT_TEXT("139.33mm",10,30,40,"内件");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					var goodsArr = clsArr[i].goodsList;
					var num = 0;
					var goodsName;
					for (var j=0; j<goodsArr.length; j++) {
						if(j>0){
							goodsName =goodsName + "\r\n" + goodsArr[j].goodsName;
						}else{
							goodsName =  goodsArr[j].goodsName;
						}
						num = num + goodsArr[j].num;
					}
					LODOP.ADD_PRINT_TEXT("138.01mm",155,235,60,goodsName);
					LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
					LODOP.ADD_PRINT_TEXT("138.01mm",117,60,20,"品名：");
					LODOP.ADD_PRINT_TEXT("138.01mm",75,40,20,num);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.ADD_PRINT_TEXT(158,45,60,20,"收件人：");
					LODOP.ADD_PRINT_TEXT(222,45,60,20,"寄件人：");
					LODOP.ADD_PRINT_TEXT("100.44mm",45,60,20,"收件人：");
					LODOP.ADD_PRINT_TEXT("121.6mm",45,60,20,"寄件人：");
					LODOP.ADD_PRINT_TEXT("138.01mm",40,60,20,"总件数：");
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.ADD_PRINT_RECT("136.68mm",115,1,60,0,1);
					LODOP.ADD_PRINT_TEXT(20,149,98,20,"第"+ (i+1) + "单");
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					var buyerProvince = "";
					var buyerCity = "";
					var buyerArea = "";
					if(clsArr[i].buyerProvince != undefined) {
						buyerProvince = clsArr[i].buyerProvince;
					}
					if(clsArr[i].buyerCity != undefined) {
						buyerCity = "-" + clsArr[i].buyerCity;
					}
					if(clsArr[i].buyerArea != undefined) {
						buyerArea = clsArr[i].buyerArea;
					}
					LODOP.ADD_PRINT_TEXT(73,32,380,40,buyerProvince+buyerCity);
					LODOP.SET_PRINT_STYLEA(0,"FontSize",32);
					/*LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.ADD_PRINT_TEXT(73,190,100,40,buyerCity);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.ADD_PRINT_TEXT(73,190,100,40,buyerCity);
					LODOP.SET_PRINT_STYLEA(0,"FontSize",32);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
				 	LODOP.ADD_PRINT_TEXT(73,140,40,40,"-");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",32);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);*/
					LODOP.ADD_PRINT_TEXT(115,31,380,40,buyerArea);
					LODOP.SET_PRINT_STYLEA(0,"FontSize",32);
					//LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.ADD_PRINT_TEXT(160,295,60,20,"签收人：");
					LODOP.ADD_PRINT_TEXT(195,295,60,20,"时间：");
									
				}
				var printResult = LODOP.PREVIEW();
					$.post('<%=path%>/orderAccept/updatePrintType.do', {
						ids : ids
					}, function(result) {
						alert(result);
						$("#bootstrap-table").bootstrapTable("refresh", {silent: true });
					});
				
			}
		};
		
		function printExpressStoDS180(){
			var clsArr = $("#bootstrap-table").bootstrapTable('getSelections');
			var ids = "";
			if (clsArr.length == 0) {
				$.modal.alertWarning('没有选择');
			} else {
				var LODOP = getLodop(document.getElementById('LODOP_OB'),document.getElementById('LODOP_EM'));
				LODOP.PRINT_INITA(0,0,400,800,"");
				for (var i=0; i<clsArr.length; i++) {
					ids += clsArr[i].id + ',';
					LODOP.NewPageA();
					LODOP.ADD_PRINT_TEXT("6.88mm",232,154,45,"代收货款");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",22);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.SET_PRINT_STYLEA(0,"Bold",1);
					LODOP.ADD_PRINT_RECT(80,0,400,1,0,1);
					LODOP.ADD_PRINT_RECT(165,0,400,1,0,1);
					LODOP.ADD_PRINT_TEXT(185,10,30,40,"收\n件");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.ADD_PRINT_RECT(165,40,1,140,0,1);
					LODOP.ADD_PRINT_RECT(245,0,280,1,0,1);
					LODOP.ADD_PRINT_TEXT(168,100,190,20,clsArr[i].buyerName);
					LODOP.ADD_PRINT_TEXT(188,90,200,20,clsArr[i].buyerTel);
					LODOP.ADD_PRINT_TEXT(188,45,60,20,"电话：");
					LODOP.ADD_PRINT_TEXT(208,45,60,20,"地址：");
					LODOP.ADD_PRINT_TEXT(208,90,190,40,clsArr[i].buyerAddress);
					LODOP.ADD_PRINT_RECT(165,280,1,140,0,1);
					LODOP.ADD_PRINT_TEXT(168,290,110,20,"服  务");
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.ADD_PRINT_RECT(188,280,120,1,0,1);
					LODOP.ADD_PRINT_TEXT(255,10,30,40,"寄\n件");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.ADD_PRINT_RECT(305,0,400,1,0,1);
					LODOP.ADD_PRINT_TEXT(248,100,190,20,clsArr[i].sender);
					LODOP.ADD_PRINT_TEXT(268,45,60,20,"电话：");
					LODOP.ADD_PRINT_TEXT(268,90,190,20,clsArr[i].senderTel);
					LODOP.ADD_PRINT_TEXT(288,45,240,20,"地址：" + clsArr[i].senderAddress);
					LODOP.ADD_PRINT_BARCODE(312,50,300,50,"128Auto",clsArr[i].expressNumber);
					LODOP.ADD_PRINT_RECT(365,0,400,1,0,1);
					LODOP.ADD_PRINT_TEXT(368,20,190,60,"您对此单的签收，代表您已验收，确认商品信息无误，包装完好，没有划痕、破损等表面质量问题");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
					LODOP.ADD_PRINT_RECT(365,210,1,60,0,1);
					LODOP.ADD_PRINT_TEXT(375,215,60,20,"签收人：");
					LODOP.ADD_PRINT_TEXT(400,215,60,20,"时间：");
					LODOP.ADD_PRINT_BARCODE("116.73mm","34.42mm","60.01mm","12.01mm","128Auto",clsArr[i].expressNumber);
					LODOP.ADD_PRINT_RECT(491,"0mm","109.99mm","0.26mm",0,1);
					LODOP.ADD_PRINT_TEXT(505,10,30,"12.01mm","收\n件");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.ADD_PRINT_RECT("129.99mm",40,1,200,0,1);
					LODOP.ADD_PRINT_RECT(570,0,400,1,0,1);
					LODOP.ADD_PRINT_TEXT(510,45,60,20,"电话：");
					LODOP.ADD_PRINT_TEXT(530,45,60,20,"地址：");
					LODOP.ADD_PRINT_TEXT(493,100,200,20,clsArr[i].buyerName);
					LODOP.ADD_PRINT_TEXT(510,90,200,20,clsArr[i].buyerTel);
					LODOP.ADD_PRINT_TEXT(530,90,200,40,clsArr[i].buyerAddress);
					LODOP.ADD_PRINT_TEXT(580,10,30,40,"寄\n件");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.ADD_PRINT_RECT(630,0,400,1,0,1);
					LODOP.ADD_PRINT_TEXT(573,100,200,20,clsArr[i].sender);
					LODOP.ADD_PRINT_TEXT(593,45,60,20,"电话：");
					LODOP.ADD_PRINT_TEXT(593,90,200,20,clsArr[i].senderTel);
					LODOP.ADD_PRINT_TEXT(613,45,60,20,"地址：");
					LODOP.ADD_PRINT_TEXT(613,90,300,20,clsArr[i].senderAddress);
					LODOP.ADD_PRINT_BARCODE(372,321,146,66,"QRCode",clsArr[i].expressNumber);
					LODOP.ADD_PRINT_TEXT(640,10,30,40,"内件");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					var goodsArr = clsArr[i].goodsList;
					var num = 0;
					var goodsName;
					for (var j=0; j<goodsArr.length; j++) {
						if(j>0){
							goodsName =goodsName + "\r\n" + goodsArr[j].goodsName;
						}else{
							goodsName =  goodsArr[j].goodsName;
						}
						num = num + goodsArr[j].num;
					}
					LODOP.ADD_PRINT_TEXT(635,155,235,60,goodsName);
					LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
					LODOP.ADD_PRINT_TEXT(635,117,60,20,"品名：");
					LODOP.ADD_PRINT_TEXT(635,75,40,20,num);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.ADD_PRINT_TEXT(168,45,60,20,"收件人：");
					LODOP.ADD_PRINT_TEXT(248,45,60,20,"寄件人：");
					LODOP.ADD_PRINT_TEXT(493,45,60,20,"收件人：");
					LODOP.ADD_PRINT_TEXT(573,45,60,20,"寄件人：");
					LODOP.ADD_PRINT_TEXT(635,40,60,20,"总件数：");
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.ADD_PRINT_RECT(630,115,1,60,0,1);
					LODOP.ADD_PRINT_TEXT(36,136,98,20,"");
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					var buyerProvince = "";
					var buyerCity = "";
					var buyerArea = "";
					if(clsArr[i].buyerProvince != undefined) {
						buyerProvince = clsArr[i].buyerProvince;
					}
					if(clsArr[i].buyerCity != undefined) {
						buyerCity = clsArr[i].buyerCity;
					}
					if(clsArr[i].buyerArea != undefined) {
						buyerArea = clsArr[i].buyerArea;
					}
					LODOP.ADD_PRINT_TEXT(83,30,138,40,buyerProvince);
					LODOP.SET_PRINT_STYLEA(0,"FontSize",32);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.ADD_PRINT_TEXT(83,170,227,40,buyerCity);
					LODOP.SET_PRINT_STYLEA(0,"FontSize",32);
					LODOP.ADD_PRINT_TEXT(125,30,351,40,buyerArea);
			
					LODOP.SET_PRINT_STYLEA(0,"FontSize",32);
					LODOP.ADD_PRINT_TEXT(665,320,58,27,"已验视");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",11);
					LODOP.ADD_PRINT_TEXT(193,280,80,20,"代收金额：");
					LODOP.SET_PRINT_STYLEA(0,"Bold",1);
					LODOP.ADD_PRINT_TEXT(228,280,70,20,"预约配送：");
					LODOP.ADD_PRINT_TEXT(248,280,70,20,"声明价值：");
					LODOP.ADD_PRINT_TEXT(268,280,70,20,"保价费用：");
					LODOP.ADD_PRINT_TEXT(288,280,70,20,"计费重量：");
					LODOP.ADD_PRINT_TEXT(210,290,80,20,"￥299.00元");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
					LODOP.SET_PRINT_STYLEA(0,"Bold",1);
				}
				LODOP.PREVIEW();
				$("#bootstrap-table").bootstrapTable("refresh", {silent: true });
			}
		};
		
		function printExpressStoDS150(){
			var clsArr = $("#bootstrap-table").bootstrapTable('getSelections');
			var ids = "";
			if (clsArr.length == 0) {
				$.modal.alertWarning('没有选择');
			} else {
				var LODOP = getLodop(document.getElementById('LODOP_OB'),document.getElementById('LODOP_EM'));
				LODOP.PRINT_INITA(0,0,400,800,"");
				for (var i=0; i<clsArr.length; i++) {
					ids += clsArr[i].id + ',';
					var worth="0";
					var sendC="";
					var sendtl="";
					var oid=clsArr[i].orderNumber;
				 var oid2=oid.substring(0,oid.length-4);

					if(oid2.indexOf("258") >= 0){
						worth="258";
					}else if(oid2.indexOf("299") >= 0){
						worth="299";
					}else if(oid2.indexOf("499") >= 0){
						worth="499";
					}else if(oid2.indexOf("298") >= 0){
						worth="298";
					}else if(oid2.indexOf("598") >= 0){
						worth="598";
					}else if(oid2.indexOf("599") >= 0){
						worth="599";
					}else if(oid2.indexOf("399") >= 0){
						worth="399";
					}
					
	/* 				if(oid.indexOf("HQ") >= 0){
						sendC="环球购物保税监管仓";
						sendtl="17315448051";
					} */
					if(oid.indexOf("ZRYZYG") >= 0){
						sendC="央广购物海关监管仓";
						sendtl="0512-66617776";
					}else if(oid.indexOf("ZRYZHXG") >= 0){
						sendC="好享购海关监管仓";
						sendtl="0512-66617776";
					}else if(oid.indexOf("ZRYZHQ") >= 0){
						sendC="环球购物海关监管仓";
						sendtl="0512-66617776";
					}else if(oid.indexOf("JY") >= 0){
						sendC="家有购物保税监管仓";
						sendtl="17315448051";
					}else if(oid.indexOf("HMS") >= 0){
						sendC="好买手保税监管仓";
						sendtl="17315448051";
					}else if(oid.indexOf("HX") >= 0){
						sendC="好享购保税监管仓";
						sendtl="051266617776";
					}else if(oid.indexOf("JJG") >= 0){
						sendC="家家购保税监管仓";
						sendtl="17315448051";
					}else if(oid.indexOf("KL") >= 0){
						sendC="快乐购保税监管仓";
						sendtl="051266617776";
					}else if(oid.indexOf("YG") >= 0){
						sendC="优购物保税监管仓";
						sendtl="17315448051";
					}else if(oid.indexOf("FS") >= 0){
						sendC="风尚购物海关监管仓";
						sendtl="051266617776";
					}
					
					LODOP.NewPageA();
					LODOP.ADD_PRINT_TEXT(20,250,120,45,"代收货款");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",19);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.SET_PRINT_STYLEA(0,"Bold",1);
					LODOP.ADD_PRINT_RECT(70,0,400,1,0,1);
					LODOP.ADD_PRINT_RECT(155,0,400,1,0,1);
					LODOP.ADD_PRINT_TEXT(165,10,30,40,"收\n件");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.ADD_PRINT_RECT(155,40,1,115,0,1);
					LODOP.ADD_PRINT_RECT(220,0,290,1,0,1);
					LODOP.ADD_PRINT_TEXT(158,100,190,20,clsArr[i].buyerName);
					LODOP.ADD_PRINT_TEXT(173,90,200,20,clsArr[i].buyerTel);
					LODOP.ADD_PRINT_TEXT(173,45,60,20,"电话：");
					LODOP.ADD_PRINT_TEXT(188,45,60,20,"地址：");
					LODOP.ADD_PRINT_TEXT(188,90,200,40,clsArr[i].buyerAddress);
					LODOP.ADD_PRINT_RECT(155,290,1,115,0,1);
					LODOP.ADD_PRINT_TEXT(222,10,30,40,"寄\n件");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.ADD_PRINT_TEXT(222,100,190,20,sendC);
					LODOP.ADD_PRINT_TEXT(235,45,60,20,"电话：");
					LODOP.ADD_PRINT_TEXT(235,90,190,20,sendtl);
					LODOP.ADD_PRINT_TEXT(250,45,240,20,"地址：" + clsArr[i].senderAddress);
					LODOP.ADD_PRINT_BARCODE(280,63,300,45,"128Auto",clsArr[i].expressNumber);
					LODOP.ADD_PRINT_BARCODE("95.01mm",200,190,"12.01mm","128Auto",clsArr[i].expressNumber);
					LODOP.ADD_PRINT_RECT("100.01mm","0mm",190,"0.26mm",0,1);
					LODOP.ADD_PRINT_TEXT("103mm",10,30,"12.01mm","收\n件");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.ADD_PRINT_RECT("100.01mm",40,1,200,0,1);
					LODOP.ADD_PRINT_RECT("119.99mm",0,400,1,0,1);
					LODOP.ADD_PRINT_TEXT("105.01mm",45,60,20,"电话：");
					LODOP.ADD_PRINT_TEXT("109.99mm",45,60,20,"地址：");
					LODOP.ADD_PRINT_TEXT("100.44mm",100,90,20,clsArr[i].buyerName);
					LODOP.ADD_PRINT_TEXT("105.01mm",90,100,20,clsArr[i].buyerTel);
					LODOP.ADD_PRINT_TEXT("109.99mm",90,261,40,clsArr[i].buyerAddress);
					LODOP.ADD_PRINT_TEXT("123.45mm",10,30,40,"寄\n件");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.ADD_PRINT_RECT("136.68mm",0,400,1,0,1);
					LODOP.ADD_PRINT_TEXT("121.6mm",100,200,20,sendC);
					LODOP.ADD_PRINT_TEXT("126.89mm",45,60,20,"电话：");
					LODOP.ADD_PRINT_TEXT("126.89mm",90,200,20,sendtl);
					LODOP.ADD_PRINT_TEXT("132.19mm",45,60,20,"地址：");
					LODOP.ADD_PRINT_TEXT("132.19mm",90,300,20,clsArr[i].senderAddress);
					LODOP.ADD_PRINT_TEXT(553,322,63,"6.67mm","已验视");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.ADD_PRINT_TEXT("139.33mm",10,30,40,"内件");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					var goodsArr = clsArr[i].goodsList;
					var num = 0;
					var goodsName;
					for (var j=0; j<goodsArr.length; j++) {
						if(j>0){
							goodsName =goodsName + "\r\n" + goodsArr[j].goodsName;
						}else{
							goodsName =  goodsArr[j].goodsName;
						}
						num = num + goodsArr[j].num;
					}
					LODOP.ADD_PRINT_TEXT("138.01mm",155,235,60,goodsName);
					LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
					LODOP.ADD_PRINT_TEXT("138.01mm",117,60,20,"品名：");
					LODOP.ADD_PRINT_TEXT("138.01mm",75,40,20,num);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.ADD_PRINT_TEXT(158,45,60,20,"收件人：");
					LODOP.ADD_PRINT_TEXT(222,45,60,20,"寄件人：");
					LODOP.ADD_PRINT_TEXT("100.44mm",45,60,20,"收件人：");
					LODOP.ADD_PRINT_TEXT("121.6mm",45,60,20,"寄件人：");
					LODOP.ADD_PRINT_TEXT("138.01mm",40,60,20,"总件数：");
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.ADD_PRINT_RECT("136.68mm",115,1,60,0,1);
					//LODOP.ADD_PRINT_TEXT(31,141,98,20,"{页码序号}");
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					var buyerProvince = "";
					var buyerCity = "";
					var buyerArea = "";
					if(clsArr[i].buyerProvince != undefined) {
						buyerProvince = clsArr[i].buyerProvince;
					}
					if(clsArr[i].buyerCity != undefined) {
						buyerCity = clsArr[i].buyerCity;
					}
					if(clsArr[i].buyerArea != undefined) {
						buyerArea = clsArr[i].buyerArea;
					}
					LODOP.ADD_PRINT_TEXT(73,15,150,40,buyerProvince);
					LODOP.SET_PRINT_STYLEA(0,"FontSize",32);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.ADD_PRINT_TEXT(73,170,227,40,buyerCity);
					LODOP.SET_PRINT_STYLEA(0,"FontSize",32);
					LODOP.ADD_PRINT_TEXT(115,25,348,40,buyerArea);
					LODOP.SET_PRINT_STYLEA(0,"FontSize",32);
					LODOP.ADD_PRINT_TEXT(160,295,75,20,"代收金额：");
					LODOP.SET_PRINT_STYLEA(0,"Bold",1);
					LODOP.ADD_PRINT_TEXT(180,300,80,20,"￥"+worth+"元");
					LODOP.SET_PRINT_STYLEA(0,"Bold",1);
				}
				LODOP.PREVIEW();
				$("#bootstrap-table").bootstrapTable("refresh", {silent: true });
			}
		};
		
		function printExpressSf(){
			var clsArr = $("#bootstrap-table").bootstrapTable('getSelections');
			var ids = "";
			if (clsArr.length == 0) {
				$.modal.alertWarning('没有选择');
			} else {
				var LODOP = getLodop(document.getElementById('LODOP_OB'),document.getElementById('LODOP_EM'));
				LODOP.PRINT_INITA(0,0,400,800,"");
				for (var i=0; i<clsArr.length; i++) {
					ids += clsArr[i].id + ',';
					LODOP.NewPageA();
					LODOP.ADD_PRINT_RECT("12.99mm",1,"100.01mm",1,0,1);
					LODOP.ADD_PRINT_TEXT(1,"45.01mm","30mm","12.99mm","E 加 急");
					LODOP.SET_PRINT_STYLEA(0,"FontName","微软雅黑");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",24);
					LODOP.SET_PRINT_STYLEA(0,"Bold",1);
					LODOP.ADD_PRINT_RECT("32.99mm",1,"100.01mm",1,0,1);
					LODOP.ADD_PRINT_BARCODE("16.01mm","5mm","65.01mm","16.01mm","128Auto",clsArr[i].expressNumber);
					LODOP.ADD_PRINT_RECT("12.99mm","70.01mm",1,"20mm",0,1);
					LODOP.ADD_PRINT_RECT("20mm","70.01mm","30mm",1,0,1);
					LODOP.ADD_PRINT_TEXT("14mm","70.09mm","30mm","6.01mm","云仓隔日");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",13);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.SET_PRINT_STYLEA(0,"Bold",1);
					LODOP.ADD_PRINT_TEXT("21.99mm","70.09mm","30mm","7.99mm","代收货款");
					LODOP.SET_PRINT_STYLEA(0,"FontName","微软雅黑");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",13);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.SET_PRINT_STYLEA(0,"Bold",1);
					LODOP.ADD_PRINT_TEXT("27.99mm","70.01mm","30mm","7.01mm","￥0000.0");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.SET_PRINT_STYLEA(0,"Bold",1);
					LODOP.ADD_PRINT_RECT("46.99mm",1,"100.01mm",1,0,1);
					LODOP.ADD_PRINT_RECT("32.99mm","10mm",1,"36.01mm",0,1);
					LODOP.ADD_PRINT_RECT("60.01mm",1,"100.01mm",1,0,1);
					LODOP.ADD_PRINT_RECT("69mm",1,"100.01mm",1,0,1);
					LODOP.ADD_PRINT_TEXT(126,39,"90.01mm",53,clsArr[i].buyerPostCode);
					LODOP.SET_PRINT_STYLEA(0,"FontName","微软雅黑");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",29);
					LODOP.SET_PRINT_STYLEA(0,"Bold",1);
					LODOP.ADD_PRINT_TEXT("34mm",1,"10mm","14mm","目\n的\n地");
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.SET_PRINT_STYLEA(0,"Bold",1);
					LODOP.ADD_PRINT_TEXT("48mm",1,"10mm","12.99mm","收\n件\n人\n");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.SET_PRINT_STYLEA(0,"Bold",1);
					LODOP.ADD_PRINT_TEXT("48mm","10.11mm","18.02mm","6.01mm",clsArr[i].buyerName);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.ADD_PRINT_TEXT("61.01mm",1,"10mm","9mm","寄\n件\n人\n");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",7);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.SET_PRINT_STYLEA(0,"Bold",1);
					LODOP.ADD_PRINT_TEXT("61.01mm","10.11mm","21.8mm","5mm",clsArr[i].sender);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.ADD_PRINT_RECT("80.99mm",1,"75.01mm",1,0,1);
					LODOP.ADD_PRINT_RECT("60.01mm","75.01mm",1,"30mm",0,1);
					LODOP.ADD_PRINT_RECT("80.99mm","10mm",1,"9mm",0,1);
					LODOP.ADD_PRINT_TEXT("61.01mm","75.09mm","25mm","9mm","定时派送\n自寄 自取");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.ADD_PRINT_TEXT("70.01mm",1,"75.01mm","12.01mm","付款方式：月结寄付      计费重量：0KG         包装费用：00元\n月结帐号：5716000265     声明价值：0000元        运费：00元\n第三方地区：0000          保价费用：00元          费用合计：00元\n实际重量：0KG           定时派送：0000/00/00-15:00-16:00");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",6);
					LODOP.ADD_PRINT_TEXT("81.99mm",1,"10mm","9mm","托寄物");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",7);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.SET_PRINT_STYLEA(0,"Bold",1);
					LODOP.ADD_PRINT_RECT("80.99mm","55.01mm",1,"9mm",0,1);
					LODOP.ADD_PRINT_TEXT("80.99mm","55.11mm","20mm","9mm","收件员:\n寄件日期:\n派件员:");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",7);
					LODOP.ADD_PRINT_TEXT("69mm","75.09mm","25mm","21.01mm","签名：\n\n\n        \n      月  日");
					LODOP.ADD_PRINT_RECT("105.01mm",1,"100.01mm",1,0,1);
					LODOP.ADD_PRINT_RECT("90.01mm","25mm",1,"15mm",0,1);
					LODOP.ADD_PRINT_BARCODE("90.99mm","27.99mm","65.01mm","12.99mm","128Auto",clsArr[i].expressNumber);
					LODOP.ADD_PRINT_RECT("114.99mm",1,"100.01mm",1,0,1);
					LODOP.ADD_PRINT_RECT("124.99mm",1,"100.01mm",1,0,1);
					LODOP.ADD_PRINT_RECT("105.01mm","10mm",1,"20mm",0,1);
					LODOP.ADD_PRINT_TEXT("115.99mm",1,"10mm","10mm","收\n件\n人\n");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",7);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.SET_PRINT_STYLEA(0,"Bold",1);
					LODOP.ADD_PRINT_TEXT("105.99mm",1,"10mm","10mm","寄\n件\n人\n");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",7);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.SET_PRINT_STYLEA(0,"Bold",1);
					LODOP.ADD_PRINT_TEXT("115.99mm","10.11mm","15.72mm","5mm",clsArr[i].buyerName);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.ADD_PRINT_TEXT("105.99mm","10.11mm","30mm","5mm",clsArr[i].sender);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.ADD_PRINT_TEXT(181,99,88,23,clsArr[i].buyerTel);
					LODOP.ADD_PRINT_TEXT(198,45,340,28,clsArr[i].buyerAddress);
					LODOP.ADD_PRINT_TEXT(231,124,147,19,clsArr[i].senderTel);
					LODOP.ADD_PRINT_TEXT(248,45,234,19,clsArr[i].senderAddress);
					LODOP.ADD_PRINT_TEXT(402,158,210,19,clsArr[i].senderTel);
					LODOP.ADD_PRINT_TEXT(414,45,336,19,clsArr[i].senderAddress);
					LODOP.ADD_PRINT_TEXT("115.99mm",94,87,19,clsArr[i].buyerTel);
					LODOP.ADD_PRINT_TEXT(451,45,337,19,clsArr[i].buyerAddress);
					
					var buyerProvince = "";
					var buyerCity = "";
					var buyerArea = "";
					if(clsArr[i].buyerProvince != undefined) {
						buyerProvince = clsArr[i].buyerProvince;
					}
					if(clsArr[i].buyerCity != undefined) {
						buyerCity = clsArr[i].buyerCity;
					}
					if(clsArr[i].buyerArea != undefined) {
						buyerArea = clsArr[i].buyerArea;
					}
					
					LODOP.ADD_PRINT_TEXT(181,190,45,20,buyerProvince);
					LODOP.ADD_PRINT_TEXT(181,237,46,19,buyerCity);
					LODOP.ADD_PRINT_TEXT(181,284,89,20,buyerArea);
					LODOP.ADD_PRINT_TEXT(438,185,45,20,buyerProvince);
					LODOP.ADD_PRINT_TEXT(438,232,46,19,buyerCity);
					LODOP.ADD_PRINT_TEXT(438,281,89,20,buyerArea);
					LODOP.ADD_PRINT_TEXT(478,5,57,30,"订单号：");
					LODOP.ADD_PRINT_TEXT(478,65,205,30,clsArr[i].orderNumber);
					var goodsArr = clsArr[i].goodsList;
					var num = 0;
					var goodsName;
					for (var j=0; j<goodsArr.length; j++) {
						if(j>0){
							goodsName =goodsName + "\r\n" + goodsArr[j].goodsName;
						}else{
							goodsName =  goodsArr[j].goodsName;
						}
						num = num + goodsArr[j].num;
					}
					LODOP.ADD_PRINT_TEXT(478,259,70,30,"商品总数：");
					LODOP.ADD_PRINT_TEXT(478,326,37,30,num);
					LODOP.ADD_PRINT_TEXT("133.01mm",4,50,30,"品名：");
					LODOP.ADD_PRINT_TEXT("133.01mm",54,308,"15mm",goodsName);
					LODOP.ADD_PRINT_RECT(498,1,"100.01mm",1,0,1);
				}
				var printResult = LODOP.PREVIEW();
					$.post('<%=path%>/orderAccept/updatePrintType.do', {
						ids : ids
					}, function(result) {
						alert(result);
						$("#bootstrap-table").bootstrapTable("refresh", {silent: true });
					});
			}
		};
		
		
		function printExpressEMS(){
			var clsArr = $("#bootstrap-table").bootstrapTable('getSelections');
			var ids = "";
			if (clsArr.length == 0) {
				$.modal.alertWarning('没有选择');
			} else {
				var LODOP = getLodop(document.getElementById('LODOP_OB'),document.getElementById('LODOP_EM'));
				//var LODOP = document.getElementById('LODOP_EM');
				LODOP.PRINT_INITA(0,0,400,800,"");
				for (var i=0; i<clsArr.length; i++) {
					ids += clsArr[i].id + ',';
					LODOP.NewPageA();
					LODOP.ADD_PRINT_TEXT(10,32,67,38,"EMS");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",24);
					LODOP.SET_PRINT_STYLEA(0,"Bold",1);
					LODOP.ADD_PRINT_TEXT(45,4,134,38,"标准快递");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",22);
					LODOP.SET_PRINT_STYLEA(0,"Bold",1);
					LODOP.SET_PRINT_STYLEA(0,"FontSize",20);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					//LODOP.ADD_PRINT_TEXT(60,250,120,20,"精彩生活·快递欢乐");
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
				    LODOP.ADD_PRINT_RECT(80,0,400,1,0,1);
					  var buyerProvince = "";
					var buyerCity = "";
					var buyerArea = "";
					if(clsArr[i].buyerProvince != undefined) {
						buyerProvince = clsArr[i].buyerProvince;
					}
					if(clsArr[i].buyerCity != undefined) {
						buyerCity = clsArr[i].buyerCity;
					}
					if(clsArr[i].buyerArea != undefined) {
						buyerArea = clsArr[i].buyerArea;
					}
					LODOP.ADD_PRINT_TEXT(80,20,348,50,buyerProvince + buyerCity);
					LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",32);
					//LODOP.ADD_PRINT_RECT(132,0,400,1,0,1);
					LODOP.ADD_PRINT_TEXT(125,20,177,30,buyerArea);
					LODOP.SET_PRINT_STYLEA(0,"FontName","黑体");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",32);
					LODOP.ADD_PRINT_RECT(165,0,400,1,0,1);
					LODOP.ADD_PRINT_TEXT(185,10,30,40,"收\n件");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.ADD_PRINT_RECT(165,40,1,140,0,1);
					LODOP.ADD_PRINT_RECT(245,0,290,1,0,1);
					LODOP.ADD_PRINT_TEXT(168,100,190,20,clsArr[i].buyerName);
					LODOP.ADD_PRINT_TEXT(188,90,200,20,clsArr[i].buyerTel);
					LODOP.ADD_PRINT_TEXT(188,45,60,20,"电话：");
					LODOP.ADD_PRINT_TEXT(208,45,60,20,"地址：");
					LODOP.ADD_PRINT_TEXT(208,90,200,40,clsArr[i].buyerAddress);
					LODOP.ADD_PRINT_RECT(165,290,1,140,0,1);
					LODOP.ADD_PRINT_TEXT(168,290,110,20,"服  务");
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.ADD_PRINT_RECT(188,290,110,1,0,1);
					LODOP.ADD_PRINT_TEXT(255,10,30,40,"寄\n件");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.ADD_PRINT_RECT(305,0,400,1,0,1);
					LODOP.ADD_PRINT_TEXT(248,100,190,20,clsArr[i].sender);
					LODOP.ADD_PRINT_TEXT(268,45,60,20,"电话：");
					LODOP.ADD_PRINT_TEXT(268,90,190,20,clsArr[i].senderTel);
					LODOP.ADD_PRINT_TEXT(288,45,240,20,"地址：" + clsArr[i].senderAddress);
					LODOP.ADD_PRINT_BARCODE(312,50,300,50,"128Auto",clsArr[i].expressNumber);
					LODOP.ADD_PRINT_RECT(365,0,400,1,0,1);
					LODOP.ADD_PRINT_TEXT(368,20,190,60,"订单号："+clsArr[i].orderNumber);
					LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
					LODOP.ADD_PRINT_RECT(365,210,1,60,0,1);
					LODOP.ADD_PRINT_TEXT(375,215,60,20,"签收人：");
					LODOP.ADD_PRINT_TEXT(400,215,60,20,"时间：");
					LODOP.ADD_PRINT_BARCODE(441,130,227,45,"128Auto",clsArr[i].expressNumber);
					LODOP.ADD_PRINT_RECT(491,0,416,1,0,1);
					LODOP.ADD_PRINT_TEXT(505,10,30,45,"收\n件");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.ADD_PRINT_RECT(491,40,1,200,0,1);
					LODOP.ADD_PRINT_RECT(570,0,400,1,0,1);
					LODOP.ADD_PRINT_TEXT(510,45,60,20,"电话：");
					LODOP.ADD_PRINT_TEXT(530,45,60,20,"地址：");
					LODOP.ADD_PRINT_TEXT(493,100,200,20,clsArr[i].buyerName);
					LODOP.ADD_PRINT_TEXT(510,90,200,20,clsArr[i].buyerTel);
					LODOP.ADD_PRINT_TEXT(530,90,200,40,clsArr[i].buyerAddress);
					LODOP.ADD_PRINT_TEXT(580,10,30,40,"寄\n件");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.ADD_PRINT_RECT(630,0,400,1,0,1);
					LODOP.ADD_PRINT_TEXT(573,100,200,20,clsArr[i].sender);
					LODOP.ADD_PRINT_TEXT(593,45,60,20,"电话：");
					LODOP.ADD_PRINT_TEXT(593,90,200,20,clsArr[i].senderTel);
					LODOP.ADD_PRINT_TEXT(613,45,60,20,"地址：");
					LODOP.ADD_PRINT_TEXT(613,90,300,20,clsArr[i].senderAddress);
					LODOP.ADD_PRINT_TEXT(43,154,197,30,"沈鑫鑫已验视");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.ADD_PRINT_BARCODE(372,321,146,66,"QRCode",clsArr[i].expressNumber);
					LODOP.ADD_PRINT_TEXT(640,10,30,40,"内件");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					var goodsArr = clsArr[i].goodsList;
					var num = 0;
					var goodsName;
					for (var j=0; j<goodsArr.length; j++) {
						if(j>0){
							goodsName =goodsName + "\r\n" + goodsArr[j].goodsName;
						}else{
							goodsName =  goodsArr[j].goodsName;
						}
						num = num + goodsArr[j].num;
					}
					LODOP.ADD_PRINT_TEXT(635,155,235,60,goodsName);
					LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
					LODOP.ADD_PRINT_TEXT(635,117,60,20,"品名：");
					LODOP.ADD_PRINT_TEXT(635,70,40,20,num);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.ADD_PRINT_TEXT(168,45,60,20,"收件人：");
					LODOP.ADD_PRINT_TEXT(248,45,60,20,"寄件人：");
					LODOP.ADD_PRINT_TEXT(493,45,60,20,"收件人：");
					LODOP.ADD_PRINT_TEXT(573,45,60,20,"寄件人：");
					LODOP.ADD_PRINT_TEXT(635,35,60,20,"总件数：");
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.ADD_PRINT_RECT(630,115,1,60,0,1);
					LODOP.ADD_PRINT_TEXT(23,149,98,20,"第"+ (i+1) + "单");  
				}
				var printResult = LODOP.PREVIEW();
				//var printResult = LODOP.PRINT();
				//var printResult = LODOP.PRINT_DESIGN();
					$.post('<%=path%>/orderAccept/updatePrintType.do', {
						ids : ids
					}, function(result) {
						alert(result);
						$("#bootstrap-table").bootstrapTable("refresh", {silent: true });
					});
				
			}
		}
	
	<!--  --------------------------------------END----------------------------------------   -->
	
    //自定义函数处理queryParams的批量增加
    $.fn.serializeJsonObject = function () {
        var json = {};
        var form = this.serializeArray();
        $.each(form, function () {
            if (json[this.name]) {
                if (!json[this.name].push) {
                    json[this.name] = [json[this.name]];
                }
                json[this.name].push();
            } else {
                json[this.name] = this.value || '';
            }
        });
        return json;
    }
	</script>

</body>
</html>