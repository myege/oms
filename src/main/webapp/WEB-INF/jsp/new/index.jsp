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
<title>后台首页</title>
<%@ include file="/static/common/head.txt"%>
<!-- Morris -->
<link href="<%=path %>/static/css/plugins/morris/morris-0.4.3.min.css" rel="stylesheet">
</head>
<body>
<body class="gray-bg">
    <div class="wrapper wrapper-content">
        <div class="row">
            <div class="col-sm-3">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <span class="label label-success pull-right">今日</span>
                        <h5>订单</h5>
                    </div>
                    <div class="ibox-content">
                        <h1 class="no-margins">${orderToday}</h1>
                        <div class="stat-percent font-bold text-success">
                        </div>
                        <small>订单</small>
                    </div>
                </div>
            </div>
            <div class="col-sm-3">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <span class="label label-info pull-right">全年</span>
                        <h5>订单</h5>
                    </div>
                    <div class="ibox-content">
                        <h1 class="no-margins">${orderYear}</h1>
                        <div class="stat-percent font-bold text-info">
                        </div>
                        <small>订单</small>
                    </div>
                </div>
            </div>
            <div class="col-sm-3">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <span class="label label-primary pull-right">全年</span>
                        <h5>异常订单</h5>
                    </div>
                    <div class="ibox-content">
                        <h1 class="no-margins">${exceptionOrderYear }</h1>
                        <div class="stat-percent font-bold text-navy">
                        </div>
                        <small>订单</small>
                    </div>
                </div>
            </div>
            <div class="col-sm-3">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <span class="label label-danger pull-right">全年</span>
                        <h5>总金额</h5>
                    </div>
                    <div class="ibox-content">
                        <h1 class="no-margins">${totalSumYear }</h1>
                        <div class="stat-percent font-bold text-danger">
                        </div>
                        <small>金额</small>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>订单</h5>
                    </div>
                    <div class="ibox-content">
                        <div class="row">
                            <div class="col-sm-9">
                                <div class="flot-chart">
                                    <div class="flot-chart-content" id="main"></div>
                                </div>
                            </div>
                            <div class="col-sm-3">
                                <ul class="stat-list">
                                    <li>
                                        <h2 class="no-margins">${lastYearOrderNumber }</h2>
                                        <small>最近一年订单数</small>
                                        <div class="stat-percent">
                                        </div>
                                        <div class="progress progress-mini">
                                            <div style="width: 100%;" class="progress-bar"></div>
                                        </div>
                                    </li>
                                    <li>
                                        <h2 class="no-margins ">${lastMonthOrderNumber }</h2>
                                        <small>本月订单数</small>
                                        <div class="stat-percent">
                                        </div>
                                        <div class="progress progress-mini">
                                            <div style="width: 100%;" class="progress-bar"></div>
                                        </div>
                                    </li>
                                    <li>
                                        <h2 class="no-margins ">${lastMonthTotalSum }</h2>
                                        <small>本月总金额</small>
                                        <div class="stat-percent">
                                        </div>
                                        <div class="progress progress-mini">
                                            <div style="width: 100%;" class="progress-bar"></div>
                                        </div>
                                    </li>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
      </div>
<%@ include file="/static/common/footer.txt"%>
<script type="text/javascript" src="<%=path%>/static/js/plugins/echarts/echarts.min.js"></script>
<script type="text/javascript">
// 基于准备好的dom，初始化echarts实例
var myChart = echarts.init(document.getElementById('main'));
// 指定图表的配置项和数据
myChart.setOption({
         tooltip: {},
         legend: {
             data:['保税','直邮']
         },//侧边工具
         toolbox: {
             feature: {
                 dataView: {show: true, readOnly: false},
                 magicType: {show: true, type: ['line', 'bar']},
                 restore: {show: true},
                 saveAsImage: {show: true}
             }
         },//底部栏
         xAxis: {
        	 //"1号","2号","3号","4号","5号","6号","7号","8号","9号","10号","11号","12号","13号","14号","15号","16号","17号","18号","19号","20号","21号","22号","23号","24号","25号","26号","27号","28号","29号","30号","31号"
        	 data: []
         },
         yAxis: [{
             type: 'value',
             name: '订单数',
         }],
         series: [{
             name: '保税',
             type: 'bar',
             data: [],
             itemStyle:{
            	normal:{
            	 color:'#4ad2ff'
            	 } 
         	},
         },{
             name: '直邮',
             type: 'bar',
             data: [],
             itemStyle:{
             	normal:{
             	 color:'#00C5CD'
             	 } 
          	},
         }]
});

myChart.showLoading();    //数据加载完之前先显示一段简单的loading动画
var bonded=[];//保税数组
var mail=[];//直邮数组
var date=[];
$.ajax({
    type : "post",
    async : true,            //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
    url : "<%=path%>/admin/findbonded.do",    
    dataType : "json",        //返回数据形式为json
    success : function(result) {
    	for (var i = 0; i < result.bondeds.length; i++) {
    		bonded.push(result.bondeds[i].num);
    		mail.push(result.mails[i].num)
    		date.push(createTime(result.mails[i].ddate));
		}
    	  myChart.hideLoading();    //隐藏加载动画
    	  myChart.setOption({        //加载数据图表
              xAxis: {
                  data: date
              },
              series: [{
                  name: '保税',
                  type: 'bar',
                  data: bonded,
                  itemStyle:{
                 	normal:{
                 	 color:'#4ad2ff'
                 	 } 
              	},
              },{
                  name: '直邮',
                  type: 'bar',
                  data: mail,
                  itemStyle:{
                  	normal:{
                  	 color:'#00C5CD'
                  	 } 
               	},
              }]
          });
    }
});

function createTime(v){
	var date = new Date(v);
    var y = date.getFullYear();
    var m = date.getMonth()+1;
    m = m<10?'0'+m:m;
    var d = date.getDate();
    d = d<10?("0"+d):d;
    var h = date.getHours();
    h = h<10?("0"+h):h;
    var M = date.getMinutes();
    M = M<10?("0"+M):M;
    var str = y+"-"+m+"-"+d;
    return str;
}
</script>
</body>
</html>