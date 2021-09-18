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
<title>直邮已完结订单</title>
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
										<option value="">--请选择查询条件--</option>
										<option value="txLogisticID">客户订单号</option>
										<option value="receiveMan">收件人姓名</option>
										<option value="mailNo">运单号</option>
										<option value="totalMailNo">提运单号</option>
										<option value="fightNumber">航班号</option>
										<option value="payNumber">支付流水号</option>
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
							<!-- <li>
								<div class="input-group">保持内联，消除边框，类似于form-inline
									<div class="input-group-btn">不换行，与相邻元素内联，包含dropdown
										<select id="express_template" name="express_template" class="selectpicker show-tick form-control" data-max-options="3" data-live-search="false">
											<option value="1" selected>申通100*180</option>
											<option value="3" selected>申通100*150</option>
											<option value="7">顺丰100*150</option>
										</select>
									</div>
									<a id="order_accept_btn_2" class="btn btn-warning"  onclick="selectExpress()"><i class="fa fa-download"></i>打印快递单</a>
								</div>
							</li> -->
						</ul>
					</div>
				</form>
			</div>
			
			<div class="btn-group-sm hidden-xs" id="toolbar" role="group">
				 <a class="btn btn-warning" onclick="$.operate.importModel('50%','50%','<%=path%>/import/importOrderMail.jsp')"> <i class="fa fa-download"></i> 导入
				</a>
				<a style='color:blue' href='<%=path%>/xls/orderMail.xlsx'>下载直邮模板</a>
				 <a class="btn btn-primary btn-edit" onclick="exportOrderMail();"> <i class="fa fa-download"></i>
					导出
				</a>
				<!--  <a id="order_mail_btn_9" class="btn btn-warning" onclick="carrsf()"><i class="fa fa-plus"></i> 匹配运单号（STO为申通，SF为顺丰）</a> -->
				<a class="btn btn-danger btn-del" onclick="$.operate.del()"> <i class="fa fa-remove"></i>
					删除
				</a>
			</div>

			<div class="col-sm-12 select-table table-striped">
				<table id="bootstrap-table" name="bt"></table>
			</div>
		</div>
	</div>
	<form id="formidOrderMail" action="<%=path%>/orderMail/exportOrderMail.do" method="post">
	    <input type="hidden" name="orderMailed" id="orderMailed" value="">
	    <input type="hidden" name="value" id="value" value="">
	    <input type="hidden" name="name" id="name" value="">
	    <input type="hidden" name="auditstatus" id="auditstatus" value="">
    </form>
	<%@ include file="/static/common/footer.txt"%>
<script type="text/javascript">
	//打印
	function selectExpress(){
		 	var expressTemplateId = $('#express_template option:selected').val();
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
	    	} else {
	    		alert("模板构建中，暂不支持！！！");
	    	}
	    }
	//匹配运单号
	 function carrsf() {
		 var row = $("#bootstrap-table").bootstrapTable('getSelections');
	   		if(row.length==0){  
	   			$.modal.alertWarning("请至少选择一条记录");
	   	    	return false;  
	   		}
	   		var ids = ""; 
	 	    for (var i = 0; i < row.length; i++){
				var ids = ids + row[i].id +",";
				
				/* if(row[i].carrier != 'SF'){
		   			$.messager.confirm('系统提示',"快递为SF才能进行匹配！");
		   			return false;
		   		} */
				if(row[i].mailNo != undefined && row[i].mailNo != ""){
					$.modal.alertWarning("快递运单号为空的订单才能进行匹配！");
		   			return false;
		   		}
	       	}
	       	ids = ids.substring(0, ids.length-1); 
	       	$.modal.confirm('确定要匹配这<font color=red>'+row.length+'条订单吗?',function(){
	       		layerMsg("匹配中...");
	       			$.post('<%=basePath%>orderMail/matchMailNoSf.do',{
	           				"ids":ids 
	           			},
	           			function(result){
	           				layer.close(layer.index);
		                  	if (result == 1){ 
		                  		layer.msg("匹配成功", {
		                			  icon: 1,
		                			  time: 700 //2秒关闭（如果不配置，默认是3秒）
		                			}, function(index){
		                			  //do something
		                				$("#bootstrap-table").bootstrapTable("refresh", {
		          					    silent: true //静态刷新
		          					});
		                		})
		                    } else { 
		                    	layer.msg("匹配失败", {
		                			  icon: 5,
		                			  time: 1500 //2秒关闭（如果不配置，默认是3秒）
		                			}, function(index){
		                			  //do something
		                				$("#bootstrap-table").bootstrapTable("refresh", {
		          					    silent: true //静态刷新
		          					});
		                		})
		                    }
	                	}
	                );
	       	}); 
		}



	/**
	 进行查询 刷新表格 进入 initTable（） 然后根据条件查询  
	**/
	$('#search').click(function(){
		 var ps = $("#bootstrap-table").bootstrapTable("getOptions").pageSize;//获取分页的pagesize
		$('#bootstrap-table').bootstrapTable('refreshOptions',{pageNumber:1,pageSize:ps});//刷新表格  还原到第一页
	})
	 function exportOrderMail() {
			
		 var row = $("#bootstrap-table").bootstrapTable('getSelections');
		 var ids = "";
		 var value = $("#getValue").val();
		 var name = $('#serachname option:selected') .val();	
		 if (row.length == 0) {
			 layer.open({
				  content: '确认导出?'
				  ,btn: ['确认', '取消']
				  ,yes: function(index, layero){
				    //按钮【按钮一】的回调
					  ids = "cxqbdc";	
		 				var v = document.getElementById("orderMailed");
		 				v.value = ids;
		 				//搜索值	
		 				var v1 = document.getElementById("value");
		 				v1.value = value; 
		 			    //搜索类型
		 			    var v2 = document.getElementById("name");
		 				v2.value = name;
		 				
		 				var v3 = document.getElementById("auditstatus");
		 				v3.value = 4;
		 				
		 				var f = document.getElementById("formidOrderMail");
		 				f.submit(); 
		 				layer.close(index);
				  }
				  ,btn2: function(index, layero){
				    //按钮【按钮二】的回调
				      layer.close(index);
				    //return false 开启该代码可禁止点击该按钮关闭
				  }
				});
		  }else{ 
			  var ids;
			  for (var i = 0; i < row.length; i++){
				  if(i==0){
					  ids =  row[i].id;
				  }
      			ids = ids + ","+row[i].id ;
           }
			  layer.open({
				  content: '确认导出?'
				  ,btn: ['确认', '取消']
				  ,yes: function(index, layero){
				    //按钮【按钮一】的回调
					  console.log("in 1.0");
		 				var v = document.getElementById("orderMailed");
		 				v.value = ids;
		 				
		 				var v3 = document.getElementById("auditstatus");
		 				v3.value = 4;
		 				
		 				var f = document.getElementById("formidOrderMail");
		 				f.submit(); 
		 				layer.close(index);
				  }
				  ,btn2: function(index, layero){
				    //按钮【按钮二】的回调
				      layer.close(index);
				    //return false 开启该代码可禁止点击该按钮关闭
				  }
				});
			  }
        }  
	
	$(function(){
		var options = {
				//初始显示所有地址
				url : '<%=path%>/orderMail/findAll.do',
				//进入修改页面的地址
				editUrl : '<%=path%>/item/editUi.do',
				//进入添加页面的地址
				addUrl : '<%=path%>/item/addUi.do',
				//导入地址
				importUrl : '<%=path%>/item/importItem.do',
				//删除地址     
				deleteUrl : '<%=path%>/orderMail/deleteOrderMail.do',
				//排序字段名
				sortName : "createTime",
				//查询条件
				queryParams : queryParams,
				//导入框名
				modalName: "商品",
				//显示字段
				columns : [{field:'column',checkbox:true,align : 'center'},
					{
						field : 'receiveMan',
						title : '收件人姓名',
						align : 'center'
						
					}, {
						field : 'receiveManPhone',
						title : '收件人电话',
						align : 'center'
					}, {
						field : 'receiveProvince',
						title : '收件人省',
						align : 'center'
					}, {
						field : 'receiveCity',
						title : '收件人市',
						align : 'center'
					}, {
						field : 'receiveCounty',
						title : '收件人区县',
						align : 'center'
					}, {
						field : 'txLogisticID',
						title : '客户订单号',
						align : 'center'
					}, {
						field : 'itemWeight',
						title : '重量',
						align : 'center'
					}, {
						field : 'itemCount',
						title : '总数量',
						align : 'center'
					}, {
						field : 'carrier',
						title : '快递',
						align : 'center'
					}, {
						field : 'mailNo',
						title : '运单号',
						align : 'center'
					}, {
						field : 'worth',
						title : '总价值',
						align : 'center'
					}, {
						field : 'tradeCountry',
						title : '启运国',
						align : 'center'
					},   {
						field : 'buyerIdNumber',
						title : '订购人证件号码',
						align : 'center'
					}, {
						field : 'buyerName',
						title : '订购人姓名',
						align : 'center'
					}, {
						field : 'payNumber',
						title : '支付流水号',
						align : 'center'
					},
					{
						field : 'totalMailNo',
						title : '提运单号',
						align : 'center'
					},
					{
						field : 'fightNumber',
						title : '航班号',
						align : 'center'
					},
					{
						field : 'destinationPort',
						title : '指运港',
						align : 'center'
					},
					{
						field : 'pc',
						title : '备注',
						align : 'center'
					
					}],
		               onDblClickRow: function (row) {
		            	   layer.open({
		           	    	  title: row.txLogisticID+'的订单详情',
		           	    	  type: 2,
		           	    	  closeBtn: 2,
		           	    	  content: '<%=path%>/orderMail/findInfoUi.do?txLogisticID='+row.txLogisticID,
		           	    	  isOutAnim : true,
		           	    	  maxmin  : true,
		           	    	  anim : 2 ,
		           	    	  area : ['80%','95%']
		           	    	}); 
			            },
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
            temp[name] = $("#getValue").val();
            temp["auditstatus"] = "9";
	        return temp;
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
    
    function printExpressSto150(){
		var clsArr = $("#bootstrap-table").bootstrapTable('getSelections');
		var ids = "";
		if (clsArr.length == 0) {
			$.modal.alertWarning("请至少选择一条记录");
			return false;
		} else {
			if(clsArr.length>50){
				$.modal.alertWarning("每次少于50单");
				return false;
			}else{
			var LODOP = getLodop(document.getElementById('LODOP_OB'),document.getElementById('LODOP_EM'));
			LODOP.PRINT_INITA(0,0,400,800,"");
			
			for (var i=0; i<clsArr.length; i++) {
				ids += clsArr[i].id + ',';
				LODOP.NewPageA();
				LODOP.ADD_PRINT_TEXT(20,250,120,30,"标准快件");
				LODOP.SET_PRINT_STYLEA(0,"FontSize",20);
				LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
				LODOP.ADD_PRINT_TEXT(50,250,120,20,clsArr[i].txLogisticID);
				LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
				LODOP.ADD_PRINT_RECT(70,0,400,1,0,1);
				LODOP.ADD_PRINT_RECT(155,0,400,1,0,1);
				LODOP.ADD_PRINT_TEXT(165,10,30,40,"收\n件");
				LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
				LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
				LODOP.ADD_PRINT_RECT(155,40,1,115,0,1);
				LODOP.ADD_PRINT_RECT(220,0,290,1,0,1);
				LODOP.ADD_PRINT_TEXT(158,100,190,20,clsArr[i].receiveMan);
				LODOP.ADD_PRINT_TEXT(173,90,200,20,clsArr[i].receiveManPhone);
				LODOP.ADD_PRINT_TEXT(173,45,60,20,"电话：");
				LODOP.ADD_PRINT_TEXT(188,45,60,20,"地址：");
				LODOP.ADD_PRINT_TEXT(188,90,200,40,clsArr[i].receiveManAddress);
				LODOP.ADD_PRINT_RECT(155,290,1,115,0,1);
				LODOP.ADD_PRINT_TEXT(222,10,30,40,"寄\n件");
				LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
				LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
				LODOP.ADD_PRINT_TEXT(222,100,190,20,"寄件人直邮");
				LODOP.ADD_PRINT_TEXT(235,45,60,20,"电话：");
				LODOP.ADD_PRINT_TEXT(235,90,190,20,"电话 88888888");
				LODOP.ADD_PRINT_TEXT(250,45,240,20,"地址：海外直邮");
				LODOP.ADD_PRINT_BARCODE(280,63,300,45,"128Auto",clsArr[i].mailNo);
				LODOP.ADD_PRINT_BARCODE("95.01mm",200,190,"12.01mm","128Auto",clsArr[i].mailNo);
				LODOP.ADD_PRINT_RECT("100.01mm","0mm",190,"0.26mm",0,1);
				LODOP.ADD_PRINT_TEXT("103mm",10,30,"12.01mm","收\n件");
				LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
				LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
				LODOP.ADD_PRINT_RECT("100.01mm",40,1,200,0,1);
				LODOP.ADD_PRINT_RECT("119.99mm",0,400,1,0,1);
				LODOP.ADD_PRINT_TEXT("105.01mm",45,60,20,"电话：");
				LODOP.ADD_PRINT_TEXT("109.99mm",45,60,20,"地址：");
				LODOP.ADD_PRINT_TEXT("100.44mm",100,90,20,clsArr[i].receiveMan);
				LODOP.ADD_PRINT_TEXT("105.01mm",90,100,20,clsArr[i].receiveManPhone);
				LODOP.ADD_PRINT_TEXT("109.99mm",90,261,40,clsArr[i].receiveManAddress);
				LODOP.ADD_PRINT_TEXT("123.45mm",10,30,40,"寄\n件");
				LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
				LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
				LODOP.ADD_PRINT_RECT("136.68mm",0,400,1,0,1);
				LODOP.ADD_PRINT_TEXT("121.6mm",100,200,20,"寄件人直邮");
				LODOP.ADD_PRINT_TEXT("126.89mm",45,60,20,"电话：");
				LODOP.ADD_PRINT_TEXT("126.89mm",90,200,20,"电话 88888888");
				LODOP.ADD_PRINT_TEXT("132.19mm",45,60,20,"地址：");
				LODOP.ADD_PRINT_TEXT("132.19mm",90,300,20,"海外直邮");
				LODOP.ADD_PRINT_TEXT(40,148,100,"7.99mm","已验视");
				LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
				LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
				LODOP.ADD_PRINT_TEXT("139.33mm",10,30,40,"内件");
				LODOP.SET_PRINT_STYLEA(0,"FontSize",12);
				LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
			
				LODOP.ADD_PRINT_TEXT("138.01mm",155,235,60,clsArr[i].itemName);
				LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
				LODOP.ADD_PRINT_TEXT("138.01mm",117,60,20,"主要商品：");
				LODOP.ADD_PRINT_TEXT("138.01mm",75,40,20,clsArr[i].itemCount);
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
				if(clsArr[i].receiveProvince != undefined) {
					buyerProvince = clsArr[i].receiveProvince;
				}
				if(clsArr[i].receiveCity != undefined) {
					buyerCity = "-" + clsArr[i].receiveCity;
				}
				if(clsArr[i].receiveCounty != undefined) {
					buyerArea = clsArr[i].receiveCounty;
				}
				LODOP.ADD_PRINT_TEXT(73,32,380,40,buyerProvince+buyerCity);
				LODOP.SET_PRINT_STYLEA(0,"FontSize",32);
				
				LODOP.ADD_PRINT_TEXT(115,31,380,40,buyerArea);
				LODOP.SET_PRINT_STYLEA(0,"FontSize",32);
	
				LODOP.ADD_PRINT_TEXT(160,295,60,20,"签收人：");
				LODOP.ADD_PRINT_TEXT(195,295,60,20,"时间：");
								
			}
			var printResult = LODOP.PREVIEW();
			if(printResult > 0) {
				$.post('<%=path%>/orderAccept/updatePrintType.do', {
					ids : ids
				}, function(result) {
					alert(result);
				});
			}
			
		}}
	};
	function printExpressSf(){
		var clsArr = $("#bootstrap-table").bootstrapTable('getSelections');
		var ids = "";
		if (clsArr.length == 0) {
			$.modal.alertWarning("请至少选择一条记录");
		} else {
			if(clsArr.length>50){
				$.modal.alertWarning("请至少选择一条记录");
			}else{
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
				LODOP.ADD_PRINT_BARCODE("16.01mm","5mm","65.01mm","16.01mm","128Auto",clsArr[i].mailNo);
				LODOP.ADD_PRINT_RECT("12.99mm","70.01mm",1,"20mm",0,1);
				LODOP.ADD_PRINT_RECT("20mm","70.01mm","30mm",1,0,1);
				LODOP.ADD_PRINT_TEXT("14mm","70.09mm","30mm","6.01mm","云仓隔日");
				LODOP.SET_PRINT_STYLEA(0,"FontSize",13);
				LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
				LODOP.SET_PRINT_STYLEA(0,"Bold",1);
				LODOP.ADD_PRINT_TEXT("21.99mm","70.09mm","30mm","7.99mm","");
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
			
				if(clsArr[i].markDestination!=undefined){
				LODOP.ADD_PRINT_TEXT(126,39,"90.01mm",53,clsArr[i].markDestination+clsArr[i].receiveProvince+clsArr[i].receiveCity);
				}else{
					LODOP.ADD_PRINT_TEXT(126,39,"90.01mm",53,clsArr[i].receiveProvince+clsArr[i].receiveCity);
				}
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
				LODOP.ADD_PRINT_TEXT("48mm","10.11mm","18.02mm","6.01mm",clsArr[i].receiveMan);
				LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
				LODOP.ADD_PRINT_TEXT("61.01mm",1,"10mm","9mm","寄\n件\n人\n");
				LODOP.SET_PRINT_STYLEA(0,"FontSize",7);
				LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
				LODOP.SET_PRINT_STYLEA(0,"Bold",1);
				LODOP.ADD_PRINT_TEXT("61.01mm","10.11mm","21.8mm","5mm","海外直邮");
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
				LODOP.ADD_PRINT_BARCODE("90.99mm","27.99mm","65.01mm","12.99mm","128Auto",clsArr[i].mailNo);
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
				LODOP.ADD_PRINT_TEXT("115.99mm","10.11mm","15.72mm","5mm",clsArr[i].receiveMan);
				LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
				LODOP.ADD_PRINT_TEXT("105.99mm","10.11mm","30mm","5mm","海外直邮");
				LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
				LODOP.ADD_PRINT_TEXT(181,99,88,23,clsArr[i].receiveManPhone);
				LODOP.ADD_PRINT_TEXT(198,45,340,28,clsArr[i].receiveManAddress);
				LODOP.ADD_PRINT_TEXT(231,124,147,19,"78888888");
				LODOP.ADD_PRINT_TEXT(248,45,234,19,"海外");
				LODOP.ADD_PRINT_TEXT(402,158,210,19,"78888888");
				LODOP.ADD_PRINT_TEXT(414,45,336,19,"海外");
				LODOP.ADD_PRINT_TEXT("115.99mm",94,87,19,clsArr[i].receiveMan);
				LODOP.ADD_PRINT_TEXT(451,45,337,19,clsArr[i].receiveManAddress);
				
				var buyerProvince = "";
				var buyerCity = "";
				var buyerArea = "";
				if(clsArr[i].receiveProvince != undefined) {
					buyerProvince = clsArr[i].receiveProvince;
				}
				if(clsArr[i].receiveCity != undefined) {
					buyerCity = clsArr[i].receiveCity;
				}
				if(clsArr[i].receiveCounty != undefined) {
					buyerArea = clsArr[i].receiveCounty;
				}
				
				LODOP.ADD_PRINT_TEXT(181,190,45,20,buyerProvince);
				LODOP.ADD_PRINT_TEXT(181,237,46,19,buyerCity);
				LODOP.ADD_PRINT_TEXT(181,284,89,20,buyerArea);
				LODOP.ADD_PRINT_TEXT(438,185,45,20,buyerProvince);
				LODOP.ADD_PRINT_TEXT(438,232,46,19,buyerCity);
				LODOP.ADD_PRINT_TEXT(438,281,89,20,buyerArea);
				LODOP.ADD_PRINT_TEXT(478,5,57,30,"订单号：");
				LODOP.ADD_PRINT_TEXT(478,65,205,30,clsArr[i].txLogisticID);
			
				LODOP.ADD_PRINT_TEXT(478,259,70,30,"商品总数：");
				LODOP.ADD_PRINT_TEXT(478,326,37,30,clsArr[i].itemCount);
				LODOP.ADD_PRINT_TEXT("133.01mm",4,50,30,"主要商品：");
				LODOP.ADD_PRINT_TEXT("133.01mm",54,308,"15mm",clsArr[i].itemName);
				LODOP.ADD_PRINT_RECT(498,1,"100.01mm",1,0,1);
			}
			var printResult = LODOP.PREVIEW();
			if(printResult > 0) {
				$.post('<%=path%>/orderAccept/updatePrintType.do', {
					ids : ids
				}, function(result) {
					alert(result);
				});
			}
		}}
	};
	</script>

</body>
</html>