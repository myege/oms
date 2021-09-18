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
	<form id="editform" onsubmit="return false;">
		<div class="col-xs-12">
			<label class="col-sm-3 control-label ">物流单号：</label>
			<input class="form-control bg-warning" type="text" id="bill" name="bill" onkeydown='if(event.keyCode==13){submitHandler();}'/>
		</div>
		
			<div class="col-sm-12">
				<label class="col-sm-3 control-label ">当前单量：</label>
				<span>
					<h1 id="showNum">
						0
					</h1>
				</span>
			</div>
		
		<div class="col-sm-12 select-table table-striped">
			<table id="bootstrap-table"></table>
		</div>
		</form>
	</div>
<%@ include file="/static/common/footer.txt"%>
<script type="text/javascript">
document.getElementById("bill").focus();
$(function(){
	var options = {
			//初始显示所有地址
			url : '',
			//排序字段名
			sortName : "fhtime",
			//查询条件
			queryParams : queryParams,
			//导入框名
			modalName: "商品",
			//显示字段
			columns : [{field:'column',checkbox:true,align : 'center'},
				{
					field : 'bill',
					title : '物流单号',
					align : 'center'
					
				}, {
					field : 'pick',
					title : '拣货单号',
					align : 'center'
				},{
					field : 'fhtime',
					title : '发货时间',
					align : 'center',
					formatter:function(value){
              			return formatDate(value);
              		}	
				}, {
					field : 'pickname',
					title : '拣货员名称',
					align : 'center'
				}]
			}
	//进入初始化表格方法
		//加载时间控件
//			$.operate.time("time3");
//			$.operate.time("time4");
		$.table.initSmall(options);
});

//查询条件
function queryParams(params){
		var temp = $("#data-form").serializeJsonObject();
        temp["rows"] = params.limit;                        //页面大小
        temp["page"] = (params.offset / params.limit) + 1;  //页码
        temp["sort"] = params.sort;                         //排序列名
        temp["sortOrder"] = params.order;                   //排位命令（desc，asc） 
        //特殊格式的条件处理
        return temp;
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




		$("#editform").validate({
			rules:{
				bill:{
        			required:true,
        		}
			}
		})
		function submitHandler() {
				if ($.validate.form()) {
					var row = $("#bootstrap-table").bootstrapTable('getSelections');
					if(row.length > 0){
						for(var i=0;i<row.length;i++){
							if(bill == row[0].bill){
								layer.msg(bill+"该物流单号已经扫描过了！！", {
		        	        		  icon: 2,
		  	              			  time: 2000 //2秒关闭（如果不配置，默认是3秒）
		  	              			}, function(index){
		  	              		})
								return false;
							}
						}
					}
					var bill = $("#bill").val();
					 $.post('<%=basePath%>consignment/inserDelivery.do',{bill:bill}, function(result){
	                       if(result.consignmentQueryVo.code=="500"){
	                    	   document.getElementById("bill").value = "";
	                    	   layer.msg(result.consignmentQueryVo.erroMsg);
	                    	   return false;
	                       }else{
	                    	   document.getElementById("showNum").innerText =  parseInt(document.getElementById("showNum").innerText)+ parseInt(1);
	                    	   document.getElementById("bill").value = "";
	                    	   $('#bootstrap-table').bootstrapTable('insertRow',{
	                    		   index:1,
	                    		   row:{
	                    			   bill:result.consignmentQueryVo.delivery.bill,
	                    			   pick:result.consignmentQueryVo.delivery.pick,
	                    			   fhtime:result.consignmentQueryVo.delivery.fhtime,
	                    			   pickname:result.consignmentQueryVo.delivery.pickname,
	                    			}
	                    	   });
	                    	 }
	                   },'json');
					
				}
	    }
	</script>
</body>
</html>