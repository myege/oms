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
<title>发货</title>
<%@ include file="/static/common/head.txt"%>
</head>
<body class="gray-bg">			
			<div class="btn-group-sm hidden-xs" id="toolbar" role="group">
				<a class="btn btn-success" onclick="$.operate.openModelNoBtn('90%','90%','<%=path%>/consignment/consignmentEditUi.do','发货')">
					发货
				</a>
			</div>

			<div class="col-sm-12 select-table table-striped">
				<table id="bootstrap-table" name="bt"></table>
			</div>
		</div>
	</div>
	<%@ include file="/static/common/footer.txt"%>
<script type="text/javascript">
	$(function(){
		var options = {
				//初始显示所有地址
				url : '<%=path%>/consignment/findAll.do',
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
// 			$.operate.time("time3");
// 			$.operate.time("time4");
			$.table.init(options);
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
	</script>

</body>
</html>