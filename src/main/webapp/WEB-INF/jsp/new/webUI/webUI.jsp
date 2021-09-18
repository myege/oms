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
<title>首页</title>
<%@ include file="/static/common/head.txt"%>
</head>
<body class="gray-bg">
<div class="col-sm-12 select-table table-striped">
		<table class="table">
	<thead>
	    <tr>
	        <th>标识</th>
	        <th>标题</th>
	        <th>图片</th>
	        <th>修改</th>
	    </tr>
    </thead>
    <tbody>
    <tr>
        <td>001</td>
        <td>第一张图片 </td>
        <td>  <img src="<%=path %>/images/wp1_1.jpg"height="150" width="150" alt="第一张图片" /></td>
       <td> <a class="btn btn-primary btn-edit"  onclick="editImgText(600,600,'oneFont')">修改内容</a></td>
     
    </tr>
    <tr>
        <td>002</td>
        <td>第二张图片 </td>
        <td>  <img src="<%=path %>/images/wp1_2.jpg" height="150" width="150"alt="第二张图片" /></td>
        <td> <a class="btn btn-primary btn-edit"  onclick="editImgText(600,600,'towFont')">修改内容</a></td>
    </tr>
    <tr>
        <td>003</td>
      	<td>第三张图片 </td>
        <td> <img src="<%=path %>/images/wp1_3.jpg" height="150" width="150"alt="第三张图片" /></td>
       <td> <a class="btn btn-primary btn-edit"  onclick="editImgText(600,600,'threeFont')">修改内容</a></td>
    </tr>
     <tr>
        <td>004</td>
      	<td>第四张图片 </td>
      <td> <img src="<%=path %>/images/wp1_4.jpg"height="150" width="150" alt="第四张图片" /></td>
        <td> <a class="btn btn-primary btn-edit"  onclick="editImgText(600,600,'fourFont')">修改内容</a></td>
    </tr>
    </tbody>
    </table>
    
</div>
	<%@ include file="/static/common/footer.txt"%>
<script type="text/javascript">
function editImgText(width,height,code){
layer.open({
	  title: '第一页修改',
	  type: 2,
	  closeBtn: 2,
	  content:'<%=path %>/Welcome/webOneIndexUi.do?oneCode='+code,
	  btn: ['保存', '取消'],
	  isOutAnim : true,
	  maxmin  : true,
	  anim : 2 ,
	  area : [width+'px',height+'px'],
	  yes: function(index, layero){
		  var iframeWin = layero.find('iframe')[0];
	          iframeWin.contentWindow.submitHandler();
	  },
	  btn2: function(index, layero){
		  layer.close(index);
	  }
});
}

</script>

</body>
</html>