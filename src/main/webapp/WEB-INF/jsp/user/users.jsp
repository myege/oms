<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% 
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String userRole = request.getSession().getAttribute("userRole").toString();
%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>用户管理</title>
</head>
<body>
	<div>
	
	<!-- 用户注册 -->

	<div id="add_a" class="easyui-dialog" title="Basic Dialog"  data-options="modal:true,closed:true,iconCls:'icon-remove'"   style="width:600px;height:370px" >
		    		
		<table>
		<tr><td>用户账号：<input  id='userName' name='userName' class="e" type="text" style="font-size:13px;width:100px"/><span  id="userName_msg"></span>&emsp;&emsp;&emsp;&emsp;</td>
		    		<td>真实姓名：<input  id='name' name='name' class="e" type="text" style="font-size:13px;width:100px"/><span  id="name_msg"></span></td></tr>
		    		<tr><td>电子邮箱：<input  id='email' name='email' class="e" type="text"  style="font-size:13px;width:100px"/><span  id="email_msg"></span>&emsp;&emsp;&emsp;&emsp;</td>
		    		<td>手机号码：<input  id='phone' name='phone' class="e" type="text" style="font-size:13px;width:100px"/><span  id="phone_msg"></span></td>
		    		<tr><td>真实年龄：<input  id='age' name='age' class="e" type="text"  style="font-size:13px;width:100px"/><span  id="age_msg"></span>&emsp;&emsp;&emsp;&emsp;</td>
		    		<td>安全密码：<input  id='password' name='password' class="e" type="password" style="font-size:13px;width:100px" /><span  id="password_msg"></span></td></tr>
		    		<tr><td>确认密码：<input  id='repwd' name='repwd' class="e" type="password" style="font-size:13px;width:100px"/><span   id="repwd_msg"></span></td></tr> 
	    	</table>
	    	<br>
	    	<br>

	    	&nbsp;&nbsp;&nbsp;&nbsp;
	    	<input type="button" value="确定"  onclick="addUsers();">
	    	&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button" value="重置" onclick="clean();">
	    	<br>
   </div> 
	<!-- 密码修改 -->
	<div id="update_a" class="easyui-dialog" title="Basic Dialog" data-options="modal:true,closed:true,iconCls:'icon-remove'" style="width:400px;height:200px;padding:10px">
	    	<table>   
	    			<input type="hidden" id="id" name="id" value="" >
		    		<tr><td>新密码：&nbsp;&nbsp;&nbsp;&nbsp;<input  id='pwd' name='pwd' class="e" type="password" /><span  id="pwd_msg"></span></td></tr>
		    		<tr><td>确认密码：<input  id='up_pwd' name='up_pwd' class="e" type="password" /><span  id="up_pwd_msg"></span></td></tr>
	    	</table>
	    	&nbsp;&nbsp;&nbsp;&nbsp;
	    	<input type="button"  value="确定"  onclick="updateUsers();">
	    	&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button"  value="重置"  onclick="clean();">
	    <br>
	</div>
	
	<!-- 权限修改 -->
	<div id="power_a" class="easyui-dialog" title="Basic Dialog" data-options="modal:true,closed:true,iconCls:'icon-remove'" style="width:400px;height:200px;padding:10px">
	    			<input type="hidden" id="ida" name="ida" value="">
					<div style="color:#99BBE8;background:#fafafa;padding:5px;">选择权限</div>
					<input type="checkbox" name="userR" value="accept,bonded,item,export,import,delete" id="bonded"><span>保税业务</span>
					<input type="checkbox" name="userR" value="accept,mail,import,export,delete" id="mail"><span>直邮业务</span>
					<input type="checkbox" name="userR" value="import,ckyw,ppdd,delete,cksp" id="ckyw"><span>出口业务</span><br><br><br><br>
	    	&nbsp;&nbsp;&nbsp;&nbsp;
	    	<input type="Submit" value="添加"  onclick="power();">
			&nbsp;&nbsp;&nbsp;&nbsp; 
			<input type="button" id="chall" value="重置" onclick="cleanAll()">
			&nbsp;&nbsp;&nbsp;&nbsp; 
			<input onclick="cleanP()" type="button" value="权限清空">
	    <br>
	</div>
	<c:if test='<%=userRole.contains("add")%>'>
			&nbsp;&nbsp;&nbsp;
			<a id="users_btn_1" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="add()">用户注册</a>
	</c:if>
	<c:if test='<%=userRole.contains("edit")%>'>
			&nbsp;&nbsp;&nbsp;
			<a id="users_btn_2" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onclick="edituser()">密码修改</a>
	</c:if>
    &nbsp;&nbsp;&nbsp;
    <input id="users_a" ></input> 
		<div id="users_b" style="width:120px"> 
			<div data-options="name:1,iconCls:'icon-ok'">用户名</div> 
			<div data-options="name:2,iconCls:'icon-ok'" >姓名</div> 
			<div data-options="name:3,iconCls:'icon-ok'" >邮箱</div> 
		</div>
	<c:if test='<%=userRole.contains("delete")%>'>
		    &nbsp;&nbsp;&nbsp;
		    <a id="users_btn_4" class="easyui-linkbutton" data-options="iconCls:'icon-no'" onclick="deleteUsers()">删除</a>
    </c:if>
    <c:if test='<%=userRole.contains("edit")%>'>
		    &nbsp;&nbsp;&nbsp;
		    <a id="users_btn_5" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="po()">用户权限</a>
    </c:if>
    &nbsp;&nbsp;&nbsp; 
    <a id="users_btn_6" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" onclick="refresh()">刷新</a>
    <div id="users_yh"  style="OVERFLOW:auto;WIDTH:1715px;HEIGHT:755px"></div>
    <script type="text/javascript">
    $(function () {
    	$('#users_yh').datagrid({ 
            idField    :'id',
            url        :'<%=path%>/users/findAll.do',
            columns    :[
                [
					{field :'id',checkbox : true,align :'center'},
                    {field :'name', title :'姓名', width :30, align :'center'},
                    {field :'age', title :'年龄', width :30, align :'center'}, 
                    {field :'phone', title :'手机号码', width :15, align :'center'},
                    {field :'email', title :'邮箱', width :15, align :'center'},
                    {field :'userName', title :'用户名', width :30, align :'center'},
                    {field :'password', title :'密码', width :15, align :'center'},
                    {field :'userRole',title :'权限', width : 30,align :'center'},
                ]
            ],
            pagination :true,
            pageSize   :50,
            pageList   :[10,50],
            fitColumns :true,
	    });
    }); 
    $(".e").blur(function(){
		var v = $.trim($(this).val());
		//访问元素属性
		var id = $(this).attr("id");
		
		if(v == ""){
			$("#"+id+"_msg").text("必填");
			//样式
			$("#"+id+"_msg").css("color","blue");
		}else{
			$("#"+id+"_msg").text("");
		}
    	
    	 $("#userName").blur(checkUname);//判断用户账号是否存在
    	
    	 $("#email").blur(checkEmail);//判断用户邮箱是否存在
    	 
    	 $("#phone").blur(checkPhone);//判断用户手机号码是否存在 
    	 
    	 $("#password").blur(checkPassword);//判断密码长度及格式
    	 
    	 $("#repwd").blur(checkPwd);//确认密码校对
    	 
    });
    
    /**
    注册用户
    */
    function add(){
    	$('#add_a').dialog('open').dialog('setTitle','注册新用户！');//调用弹窗
	}
    
    function checkBtn(){
    	var s = $("#btnSubmit").removeAttr("disabled");
    }
    /* ----验证用户名是否存在----- */
     function checkUname(){
    	 // 利用正则表达式对输入数据匹配
    	var userName = $.trim($("#userName").val());
    		if(userName !="" || userName !=null ){
    			$.post('<%=basePath%>users/uname.do',{userName:userName}, function(result){
    				 if(result == null || result==""){
    					}else{
   						    $("#userName_msg").text("*该账号已被使用");
   	 					    $("#userName_msg").css("color","red");
   	 					    return false;
    					}
		           },'json');
    		}
    	}
     /* ---验证邮箱存在---- */
     function checkEmail(){
    	 // 利用正则表达式对输入数据匹配
    	 var email = document.getElementById('email').value.trim();
         var expr =  /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
         var em = email.match(expr); 
         if(em==null || em==""){
        	 $("#email_msg").text("*邮箱格式有误");
        	 $("#email_msg").css("color","red");
        	 return false;
         }
     				$.post('<%=basePath%>users/uname.do',{email:email}, function(result){
     					 if(result == null || result==""){
     					}else{
     						$("#email_msg").text("*该邮箱已被使用");
     	 					$("#email_msg").css("color","red");
     					}
 		            },'json');
     		}
     /*----验证手机号码存在----  */
     function checkPhone(){
    	 	// 利用正则表达式对输入数据匹配
     		var str = document.getElementById('phone').value.trim();
            var expr =  /^(13[0-9]|15[0-9]|17[678]|18[0-9]|14[57])[0-9]{8}$/;
            if(str.match(expr)==null || str.match(expr)==""){
             $("#phone_msg").text("*号码格式有误");
           	 $("#phone_msg").css("color","red");
           	 return false;
            }	
     				$.post('<%=basePath%>users/uname.do',{phone:str}, function(result){
     					 if(result == null || result==""){
     					}else{
     					    $("#phone_msg").text("*该号码已被使用");
     	 					$("#phone_msg").css("color","red");
     					}
 		            },'json');
     		}
     /*-----验证密码符合格式-----  */
    function checkPassword()
    {
   		var str = document.getElementById('password').value.trim();
		var expr =/^(?=.{6,12}$)(?![0-9]+$)(?!.*(.).*\1)[0-9a-zA-Z]+$/;// 利用正则表达式对输入数据匹配
		var r = str.match(expr); 
	    if(str.length<6 || str.length>12){    //判断密码长度6-12位数，且密码为数字或字母字符 
	    	$("#password_msg").text("*密码格式有误");
	   	 	$("#password_msg").css("color","red");
	   	 	return false;
	    }
    }
     /*----验证两次密码一致性-----  */
    function checkPwd(){
    	  if($("#repwd").val()!=$("#password").val() ) {   //校验两次密码是否输入一致
    		$("#repwd_msg").text("*确认两次密码一致");
    		$("#repwd_msg").css("color","red");
    		return false;
    	}
    }
   	/*----提交-----  */
    function addUsers(){
    	    /*---------  */
	    	 if($("#userName_msg").text() == "*该账号已被使用") {//判断用户名验证条件在正确时可以被获取数据
    			return false;
    		}else{
    			var userName = $("#userName").val();//获取数据
    		}
    	    /*----------  */
	    	 if($("#email_msg").text()=="*邮箱格式有误"){//判断邮箱验证条件在正确时可以被获取数据
	    		 return false;
	    	 }else if($("#email_msg").text() == "*该邮箱已被使用"){
    			return false;
    		}else{
    			var email = $('#email').val();//获取数据
    		}
    	    /* ---------- */
	        if($("#phone_msg").text() == "*号码格式有误"){//判断手机号码验证条件在正确时可以被获取数据
	    			return false;
	    	}else if($("#phone_msg").text() == "*该号码已被使用"){
    			return false;
    		}else{
    			var phone = $('#phone').val();//获取数据
    		}
    	    /*-----------  */
	        if($("#password_msg").text()=="*密码格式有误"){ //判断输入密码验证条件在正确时可以被获取数据
    			return false;
    		} else if(($("#repwd").text()=="必填") || ($("#repwd").val()=="") || ($("#repwd").val()==null) || ($("#repwd_msg").text()=="*确认两次密码一致")){
    			return false;
    	    } else{
    			var password = $('#password').val();//获取数据
    		}
    	    /*-----------  */
	        var name = $('#name').val();//获取数据
	    	if(name =="" || name ==null ){
    			return false;
    		}
	        /*-----------  */
	    	var age = $('#age').val();//获取数据
	    	if(age =="" || age ==null ){
    			return false;
    		}
	    	//利用post传值到后台
    		$.post('<%=basePath%>users/addUsers.do',{userName:userName,name:name,email:email,phone:phone,age:age,password:password}, function(result){
    			//后台返回值进行对比
    			if (result!=null){
	    				var s = document.getElementsByTagName("input");//对input框获取框的类型对其清空
	    		        for(var i = 0;i<s.length;i++){
	    		           if(s[i].type == "text" || s[i].type == "password" ){
	    		              s[i].value = "";
	    		           }
	    		        }
    	                $('#add_a').dialog('close');        // close the dialog
    	          		$('#users_yh').datagrid('reload'); 
    	                    $.messager.show({
    	                        title: '提示',
    	                        msg: '注册成功！'
    	                    });
		                }
		            },'json');
		    }; 
		/*---清空重置输入框---  */
	    function clean(){
	    	// 利用正则表达式对输入数据匹配
	        var a = document.getElementsByTagName("input");//对input框获取框的类型对其清空
	        for(var i = 0;i<a.length;i++){
	           if(a[i].type == "text" || a[i].type == "password" ){
	              a[i].value = "";
	           }
	        }
	      }
		    
	    
    /**
    密码修改
    */
    function edituser(){//弹窗事件
    		var row = $('#users_yh').datagrid('getSelections');//返回选中多行  
    		if(row.length==0){  
    			 $.messager.confirm('系统提示','请选择行进行修改！')
   	  	    	return false;  
   	  		}
    		var row = $('#users_yh').datagrid('getSelections');//返回选中多行  
	  	    	var ids=[];  
	  	    	for (var i = 0; i < row.length; i++){
		              	ids.push(row[i].id);             //然后把单个id循环放到ids的数组中  
	  	    }
	  	    	var id =ids.join(",");
	  	    	var v = document.getElementById("id");
 				v.value = id;
            $('#update_a').dialog('open').dialog('setTitle','密码修改！');
    }
    /* ---提交修改的密码--- */
    function updateUsers(){
	    	var str = document.getElementById('pwd').value.trim();
	    	var expr =/^(?=.{6,12}$)(?![0-9]+$)(?!.*(.).*\1)[0-9a-zA-Z]+$/;// 利用正则表达式对输入数据匹配
	    	var r = str.match(expr); 
	        if(str.length<6 || str.length>12){    //判断密码长度6-12位数，且密码为数字或字母字符 
	        	  pwd_msg.innerHTML="*输入6-12位字母和数字";
	       	 	  $("#pwd_msg").css("color","red");
	       	  		 return false; 
	        }
	    	if($("#up_pwd").val()!=$("#pwd").val()) {   //校验两次密码是否输入一致
	    		  up_pwd_msg.innerHTML="*确认两次密码填写一致！";
	    		  $("#up_pwd_msg").css("color","red");
		    	    return false;
		    }
    	    var id = $('#id').val();
    	    var pwd = $('#pwd').val();
    	    $.post('<%=basePath%>users/updateUsers.do',{id:id,pwd:pwd}, function(result){
                if (result!=null){
                	var s = document.getElementsByTagName("input");//对input框获取框的类型对其清空
    		        for(var i = 0;i<s.length;i++){
    		           if(s[i].type == "text" || s[i].type == "password" ){
    		              s[i].value = "";
    		           }
    		        }
                	$('#update_a').dialog('close'); // close the dialog
          			$('#users_yh').datagrid('reload'); 
                    $.messager.show({
                        title: '提示',
                        msg: '修改成功！'
                    });
                } 
               },'json');
	  	}

    
    /**
    删除用户
    */
   	function deleteUsers(){
   	  		var row = $('#users_yh').datagrid('getSelections');//返回选中多行  
   	  		if(row.length==0){  
   	  		 	$.messager.confirm('系统提示','请选择行数据删除！')
	  	    	return false;
   	  		}
   	  	    	var ids=[];  
   	  	    	for (var i = 0; i < row.length; i++){                        
   		              	ids.push(row[i].id); //然后把单个id循环放到ids的数组中  
   	  	    }
   	  	    	var id =ids.join(",");
   	  	    		 $.messager.confirm('系统提示','确定要删除这<font color=red>'+row.length+'条数据吗?',function(r){
   	  	                if (r){
   	  	                    $.post('<%=basePath%>users/deleteUsers.do',{id:id}, function(result){
   	  	                        if (result!=null){
   	  	                        	$('#users_yh').datagrid('clearSelections');
   	  	                            $('#users_yh').datagrid('load');    // reload the user data
   	  	                            $.messager.show({
   	  	                                title: '提示',
   	  	                                msg: '删除成功'
   	  	                            });
   	  	                        } 
   	  	                    },'json');
   	  	                }
   	  	            });
   	  			}
    
    
    /**
    用户权限
    */
    function po(){//提示弹窗
			var row = $('#users_yh').datagrid('getSelections');//返回选中多行  
			if(row.length==0){  
				 $.messager.confirm('系统提示','请选择行数据修改权限！')
		  	    	return false;  
		  		}
			var row = $('#users_yh').datagrid('getSelections');//返回选中多行  
		    	var ids=[];  
		    	for (var i = 0; i < row.length; i++){
	              	ids.push(row[i].id); //然后把单个id循环放到ids的数组中  
		    }
		    	var id =ids.join(",");
		    	var v = document.getElementById("ida");
				v.value = id;
	        $('#power_a').dialog('open').dialog('setTitle','权限管理！');
	}
    /* ---重置--- */
    function cleanAll(){
    	if ($("#chall").attr("checked")) {  
            $(":checkbox").attr("checked", true);  
        } else {  
            $(":checkbox").attr("checked", false);  
        }  
    }
    /* ---提交--- */
    function power(){
    		var obj=document.getElementsByName('userR'); //选择所有name="'test'"的对象，返回数组
    		//取到对象数组后，我们来循环检测它是不是被选中
	    	var s='';
	    	for(var i=0; i<obj.length; i++){
	    			if(obj[i].checked)
	    			s+=obj[i].value+','; //如果选中，将value添加到变量s中
	    	}//那么现在来检测s的值就知道选中的复选框的值了
	    	if(s==null || s==""){//判断是否选中权限按钮，未选中停止
	    		$.messager.confirm('系统提示','请选择权限！')
	    		return false;
	    	}
	    	var ida = $('#ida').val();   //获取id值
			 $.post('<%=basePath%>users/power.do',{ida:ida,s:s}, function(result){
		        if (result!=null){
		        $('#power_a').dialog('close');        // close the dialog
		  		$('#users_yh').datagrid('reload'); 
		            $.messager.show({
		                title: '提示',
		                msg: '修改成功！'
		            });
		        }  
		       },
		       'json');
	}
    
    
    /**
    清除权限
    */
    function cleanP(){
    	var ida = $('#ida').val();   //获取id值
		$.post('<%=basePath%>users/cleanPower.do',{ida:ida}, function(result){
	        if (result!=null){//返回值进行对比
	        $('#power_a').dialog('close');        
	  		$('#users_yh').datagrid('reload'); 
	            $.messager.show({
	                title: '提示',
	                msg: '修改成功！'
	            });
	        } 
	       },
	       'json');
    }
    
   	 
   	/**
   	查询
   	*/    
   	$('#users_a').searchbox({ 
	    	searcher : function(value,name) {
			var Url='<%=path%>/users/query.do';
			var param={};
			param.paramName=name;
			param.paramValue=value;
			$('#users_yh').datagrid({
	             idField    :'id',
	             url        :Url,
	             method:'post',
	             queryParams:param,
	             columns    :[
	                 [
							{field :'id',checkbox : true,align :'center'},
							{field :'name', title :'姓名', width :30, align :'center'},
							{field :'age', title :'年龄', width :30, align :'center'},
							{field :'phone', title :'手机号码', width :15, align :'center'},
							{field :'email', title :'邮箱', width :15, align :'center'},
							{field :'userName', title :'用户名', width :30, align :'center'},
							{field :'password', title :'密码', width :15, align :'center'},
							{field :'userRole',title :'权限', width : 30,align :'center'}, 
	                	]
	             ],
	         
	             fitColumns :true,
	             pagination :true,
	             pageSize   :50,
	             pageList   :[20,50],
	             
	    	 });
   			unSelect('#users_yh');
		},
	    	width:300,
	    	menu:'#users_b', 
	    	prompt:'请输入信息进行查找' 
	 }); 
   	
   	
   	/**
   	刷新
   	*/
   	function refresh(){
	    $('#users_yh').datagrid('reload');
 	}
   	
	    		
    </script>
</body>
</html>