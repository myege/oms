(function($){
	$.extend({
		table : {
			_options : {},
			_option : {},
			_params  : {},
			init : function(options){
				$.table._options = options;
				$.table._option = options;
				$.table._params  = $.common.isEmpty(options.queryParams) ? $.table.queryParams : options.queryParams;
				$('#bootstrap-table').bootstrapTable({
					 url: options.url,                                   // 请求后台的URL（*）
	                 contentType: "application/x-www-form-urlencoded",   // 编码类型
	                 method: 'post',                                     // 请求方式（*）
	                 cache: false,                                       // 是否使用缓存
	                // striped: _striped,                                  // 是否显示行间隔色
	                 sortable: true,                                     // 是否启用排序
	                 sortStable: true,                                   // 设置为 true 将获得稳定的排序
	                 showRefresh: true,                  //是否显示刷新按钮
	                 pageNumber: 1,                                      // 初始化加载第一页，默认第一页
	                 pageSize: 50,                                       // 每页的记录行数（*） 
	                 pageList: [50, 100, 150,200,500],                             // 可供选择的每页的行数（*）
	                // escape: _escape,                                    // 转义HTML字符串
	                 showFooter: true,                            // 是否显示表尾
	                 iconSize: 'outline',                                // 图标大小：undefined默认的按钮尺寸 xs超小按钮sm小按钮lg大按钮
	     	         toolbar: '#toolbar',                                // 指定工作栏
	                 sidePagination: "server",                           // 启用服务端分页
	                 uniqueId: "id",									 //每一行的唯一标识，一般为主键列
	                 sortName: options.sortName,						 // 排序名称
	                 detailFormatter:"detailFormatter",
	                 showFooter:false,
	                 sortOrder: "desc",                   				 // 排序方式
	                 pagination : true, 								 // 是否分页
	                 paginationVAlign:'bottom',
	                 height: 600,
	     			 queryParams : $.table._params,						 //查询条件
	     			 columns : options.columns,							 //表数据
	     			 
	     			onDblClickRow : options.onDblClickRow				//双击行事件
				})
			},//父子表
			initDetailView: function(options){
				$.table._options = options;
				$.table._option = options;
				$.table._params  = $.common.isEmpty(options.queryParams) ? $.table.queryParams : options.queryParams;
				$('#bootstrap-table').bootstrapTable({
					 url: options.url,                                   // 请求后台的URL（*）
	                 contentType: "application/x-www-form-urlencoded",   // 编码类型
	                 method: 'post',                                     // 请求方式（*）
	                 cache: false,                                       // 是否使用缓存
	                 //singleSelect :true,									//禁止多选
	                // striped: _striped,                                  // 是否显示行间隔色
	                 sortable: true,                                     // 是否启用排序
	                 sortStable: true,                                   // 设置为 true 将获得稳定的排序
	                 showRefresh: true,                  //是否显示刷新按钮
	                 pageNumber: 1,                                      // 初始化加载第一页，默认第一页
	                 pageSize: 50,                                       // 每页的记录行数（*） 
	                 pageList: [50, 100, 150,200,500],                             // 可供选择的每页的行数（*）
	                // escape: _escape,                                    // 转义HTML字符串
	                 showFooter: true,                            // 是否显示表尾
	                 iconSize: 'outline',                                // 图标大小：undefined默认的按钮尺寸 xs超小按钮sm小按钮lg大按钮
	     	         toolbar: '#toolbar',                                // 指定工作栏
	                 sidePagination: "server",                           // 启用服务端分页
	                 uniqueId: "id",									 //每一行的唯一标识，一般为主键列
	                 sortName: options.sortName,						 // 排序名称
	                 detailFormatter:"detailFormatter",
	                 showFooter:false,
	                 sortOrder: "desc",                   				 // 排序方式
	                 pagination : true, 								 // 是否分页
	                 paginationVAlign:'bottom',
	                 height: 600,
	     			 queryParams : $.table._params,						 //查询条件
	     			 columns : options.columns,							 //表数据
	     			 detailView:true,
	     			onExpandRow : options.onExpandRow,		
				})
			},
			//低高度的表格显示
			initSmall : function(options){
				$.table._options = options;
				$.table._option = options;
				$.table._params  = $.common.isEmpty(options.queryParams) ? $.table.queryParams : options.queryParams;
				$('#bootstrap-table').bootstrapTable({
					 url: options.url,                                   // 请求后台的URL（*）
	                 contentType: "application/x-www-form-urlencoded",   // 编码类型
	                 method: 'post',                                     // 请求方式（*）
	                 cache: false,                                       // 是否使用缓存
	                // striped: _striped,                                  // 是否显示行间隔色
	                 sortable: true,                                     // 是否启用排序
	                 sortStable: true,                                   // 设置为 true 将获得稳定的排序
	                // showRefresh: true,                  //是否显示刷新按钮
	                // pageNumber: 1,                                      // 初始化加载第一页，默认第一页
	                 //pageSize: 10,                                       // 每页的记录行数（*） 
	                // pageList: [10, 20, 30,40,50],                             // 可供选择的每页的行数（*）
	                // escape: _escape,                                    // 转义HTML字符串
	                 showFooter: true,                            // 是否显示表尾
	                 iconSize: 'outline',                                // 图标大小：undefined默认的按钮尺寸 xs超小按钮sm小按钮lg大按钮
	     	         toolbar: '#toolbar',                                // 指定工作栏
	                 sidePagination: "server",                           // 启用服务端分页
	                 uniqueId: "id",									 //每一行的唯一标识，一般为主键列
	                 sortName: options.sortName,						 // 排序名称
	                 detailFormatter:"detailFormatter",
	                 showFooter:false,
	                 sortOrder: "desc",                   				 // 排序方式
	               //  pagination : true, 								 // 是否分页
	               //  paginationVAlign:'bottom',
	                 height: 480,
	     			 //queryParams : $.table._params,						 //查询条件
	     			 columns : options.columns,							 //表数据
	     			 
	     			onDblClickRow : options.onDblClickRow				//双击行事件
				})
			},
			 // 查询条件
            queryParams: function(params) {
            	return {
        			// 传递参数查询参数
            		rows:        params.limit,
        			page:        params.offset / params.limit + 1,
        			searchValue:    params.search,
        			orderByColumn:  params.sort,
        			isAsc:          params.order
        		}; 
            },
            refresh: function() {
                $("#bootstrap-table").bootstrapTable('refresh', {
                    silent: true
                });
            },
         // 导出数据
    		exportExcel: function(formId) {
    			var currentId = $.common.isEmpty(formId) ? $('form').attr('id') : formId;
    			$.modal.loading("正在导出数据，请稍后...");
    			$.post($.table._option.exportUrl, $("#" + currentId).serializeArray(), function(result) {
    				if (result.code == web_status.SUCCESS) {
    			        window.location.href = ctx + "common/download?fileName=" + result.msg + "&delete=" + true;
    				} else {
    					$.modal.alertError(result.msg);
    				}
    				$.modal.closeLoading();
    			});
    		},
    		 // 查询表格指定列值
            selectColumns: function(column) {
            	return $.map($('#bootstrap-table').bootstrapTable('getSelections'), function (row) {
        	        return row[column];
        	    });
            },
            // 查询表格首列值
            selectFirstColumns: function() {
            	return $.map($('#bootstrap-table').bootstrapTable('getSelections'), function (row) {
        	        return row[$.table._option.columns[1].field];
        	    });
            },
		},
		operate : {
			// 提交数据
        	submit: function(url, type, dataType, data) {
            	var config = {
        	        url: url,
        	        type: type,
        	        dataType: dataType,
        	        data: data,
        	        beforeSend: function () {
        	        	$.modal.loading("正在处理中，请稍后...");
        	        },
        	        success: function(result) {
        	        	$.operate.ajaxSuccess(result);
        	        }
        	    };
        	    $.ajax(config)
            },
			// post请求传输
            post: function(url, data) {
            	$.operate.submit(url, "post", "json", data);
            },
			//添加Modal显示
			add:function(width,height){
				var url = $.table._options.addUrl;
				layer.open({
      	    	  title: '新增',
      	    	  type: 2,
      	    	  closeBtn: 2,
      	    	  content: url,
      	    	  btn: ['保存', '取消'],
      	    	  isOutAnim : true,
      	    	  maxmin  : true,
      	    	  anim : 2 ,
      	    	  area : [width,height],
      	    	  yes: function(index, layero){
      	    		  	//按钮【按钮一】的回调
      	    		  var iframeWin = layero.find('iframe')[0];
            	          iframeWin.contentWindow.submitHandler();
      	    	  },
      	    	  btn2: function(index, layero){
      	    		  	//按钮【按钮二】的回调
      	    		  layer.close(index);
      	    		  //return false 开启该代码可禁止点击该按钮关闭
      	    	  }
      	    });      
			},
			//导入Modal显示
			importModel:function(width,height,URL){
				var url = URL;
				layer.open({
      	    	  title: '导入',
      	    	  type: 2,
      	    	  closeBtn: 2,
      	    	  content: url,
      	    	  btn: ['保存', '取消'],
      	    	  isOutAnim : true,
      	    	  maxmin  : true,
      	    	  anim : 2 ,
      	    	  area : [width,height],
      	    	  yes: function(index, layero){
      	    		  	//按钮【按钮一】的回调
      	    		  var iframeWin = layero.find('iframe')[0];
            	          iframeWin.contentWindow.submitHandler();
      	    	  },
      	    	  btn2: function(index, layero){
      	    		  	//按钮【按钮二】的回调
      	    		  layer.close(index);
      	    		  //return false 开启该代码可禁止点击该按钮关闭
      	    	  }
      	    });      
			},
			//修改Modal显示
			edit:function(width,height){
				var row = $("#bootstrap-table").bootstrapTable('getSelections');
        	    
        	    if (row.length == 0) {
        			$.modal.alertWarning("请至少选择一条记录");
        			return;
        		}
        	    if(row.length > 1 ){
        	    	$.modal.alertWarning("请选择一条记录");
        			return;
        	    }
        	    var id = row[0].id;
        	    url = $.table._options.editUrl+"?id="+id;
        	    layer.open({
        	    	  title: '修改',
        	    	  type: 2,
        	    	  closeBtn: 2,
        	    	  content: url,
        	    	  btn: ['保存', '取消'],
        	    	  isOutAnim : true,
        	    	  maxmin  : true,
        	    	  anim : 2 ,
        	    	  area : [width,height],
        	    	  yes: function(index, layero){
        	    		  	//按钮【按钮一】的回调
        	    		  var iframeWin = layero.find('iframe')[0];
              	          iframeWin.contentWindow.submitHandler();
        	    	  },
        	    	  btn2: function(index, layero){
        	    		  	//按钮【按钮二】的回调
        	    		  layer.close(index);
        	    		  //return false 开启该代码可禁止点击该按钮关闭
        	    	  }
        	    });       
			},
		
			//进入显示根据ID查询值的页面的方法
			showTableById:function(width,height,url){
				layer.open({
      	    	  title: '查看详情',
      	    	  type: 2,
      	    	  closeBtn: 2,
      	    	  content: url,
      	    	  btn: ['关闭'],
      	    	  isOutAnim : true,
      	    	  maxmin  : true,
      	    	  anim : 2 ,
      	    	  area : [width,height],
      	    	  btn2: function(index, layero){
      	    		  	//按钮【按钮二】的回调
      	    		  layer.close(index);
      	    		  //return false 开启该代码可禁止点击该按钮关闭
      	    	  }
      	    });
			},
			
			//同页面多处修改Modal显示
			editForMoreUrl:function(width,height,url){
				var row = $("#bootstrap-table").bootstrapTable('getSelections');
        	    if (row.length == 0) {
        			$.modal.alertWarning("请至少选择一条记录");
        			return;
        		}
        	    if(row.length > 1 ){
        	    	$.modal.alertWarning("请选择一条记录");
        			return;
        	    }
        	    var id = row[0].id;
        	    var oneUrl = url+"?id="+id;
        	    layer.open({
        	    	  title: '修改',
        	    	  type: 2,
        	    	  closeBtn: 2,
        	    	  content: oneUrl,
        	    	  btn: ['保存', '取消'],
        	    	  isOutAnim : true,
        	    	  maxmin  : true,
        	    	  anim : 2 ,
        	    	  area : [width,height],
        	    	  yes: function(index, layero){
        	    		  	//按钮【按钮一】的回调
        	    		  var iframeWin = layero.find('iframe')[0];
              	          iframeWin.contentWindow.submitHandler();
        	    	  },
        	    	  btn2: function(index, layero){
        	    		  	//按钮【按钮二】的回调
        	    		  layer.close(index);
        	    		  //return false 开启该代码可禁止点击该按钮关闭
        	    	  }
        	    });       
			},
			//打开页面 无按钮
			openModelNoBtn : function(width,height,url,title){
				layer.open({
      	    	  title: title,
      	    	  type: 2,
      	    	  closeBtn: 2,
      	    	  content: url,
      	    	  isOutAnim : true,
      	    	  maxmin  : true,
      	    	  anim : 2 ,
      	    	  area : [width,height],
      	    	  cancel : function(index){
      	    		  // 你点击右上角 X 取消后要做什么
      	    		$.table.refresh();
      	    	  }
      	      })
			},
			//打开页面 有按钮
			openModel : function(width,height,url,title,btnName){
				layer.open({
      	    	  title: title,
      	    	  type: 2,
      	    	  closeBtn: 2,
      	    	  content: url,
      	    	  btn: [btnName, '取消'],
      	    	  isOutAnim : true,
      	    	  maxmin  : true,
      	    	  anim : 2 ,
      	    	  area : [width,height],
      	    	 yes: function(index, layero){
 	    		  	//按钮【按钮一】的回调
 	    		  var iframeWin = layero.find('iframe')[0];
       	          iframeWin.contentWindow.submitHandler();
      	    	 },
      	    	 btn2: function(index, layero){
 	    		  	//按钮【按钮二】的回调
 	    		  layer.close(index);
 	    		  //return false 开启该代码可禁止点击该按钮关闭
      	    	 }
      	      })
			},
			//派单打开页面
			openDistribute : function(width,height,url,title,btnName){
				var row = $("#bootstrap-table").bootstrapTable('getSelections');
				/* if (row.length == 0) {
	        			$.modal.alertWarning("请至少选择一条记录");
	        			return;
	        		}*/
				var picks = "";
				/*for(var i = 0 ; i < row.length ; i++ ){
					if(row[i].pickname!=null){
						$.modal.alertWarning("请选择未派单的数据");
						return;
					}
					if(i==0){
						picks=row[i].pick;
					}else{
						picks+=","+row[i].pick;
					}
				}*/
				layer.open({
      	    	  title: title,
      	    	  type: 2,
      	    	  closeBtn: 2,
      	    	  content: url+"?picks="+picks,
      	    	  btn: [btnName, '取消'],
      	    	  isOutAnim : true,
      	    	  maxmin  : true,
      	    	  anim : 2 ,
      	    	  area : [width,height],
      	    	 yes: function(index, layero){
 	    		  	//按钮【按钮一】的回调
 	    		  var iframeWin = layero.find('iframe')[0];
       	          iframeWin.contentWindow.submitHandler();
      	    	 },
      	    	 btn2: function(index, layero){
 	    		  	//按钮【按钮二】的回调
 	    		  layer.close(index);
 	    		  //return false 开启该代码可禁止点击该按钮关闭
      	    	 }
      	      })
			},
			//删除方法
			del : function(){
				var row = $("#bootstrap-table").bootstrapTable('getSelections');
				if (row.length == 0) {
        			$.modal.alertWarning("请至少选择一条记录");
        			return;
        		}
				
				var ids = "";
				for(var i = 0 ; i < row.length ; i++ ){
					if(i==0){
						ids=row[i].id;
					}else{
						ids+=","+row[i].id;
					}
				}
				var url = $.table._options.deleteUrl;
				$.modal.confirm("确定删除这"+row.length+"条数据吗？", function() {
					$.operate.delAjax(url,ids);
				})
			},
			//时间控件使用
			time:function(id){
				laydate.render({
					  elem: '#'+id
					  ,type: 'datetime'
					}); 
			},
			//保存修改方法
			save:function(url,param){
				var config = {
	        	        url: url,
	        	        type: "post",
	        	        dataType: "json",
	        	        data: param,
	        	        success: function(result) {
	        	        	console.log(result);
	        	        	if(result >0){
	        	        		$.operate.successCallback("保存成功");
	        	        	}else{
	        	        		$.modal.msgError("保存失败");
	        	        		return;
	        	        	}
	        	        }
	        	    };
	        	    $.ajax(config)
			},
			//拣货派单方法
			distributeLeaflets:function(url,param){
				var config = {
	        	        url: url,
	        	        type: "post",
	        	        dataType: "json",
	        	        data: param,
	        	        success: function(result) {
	        	        	if(result.code ==0){
	        	        		$.operate.successCallback("保存成功");
	        	        	}else{
	        	        		$.modal.msgError("保存失败");
	        	        		return;
	        	        	}
	        	        }
	        	    };
	        	    $.ajax(config)
			},
			//保存修改方法
			import_save:function(url,param){
				$.layerMsg("导入中...");
				var config = {
	        	        url: url,
	        	        type: "post",
	        	        dataType: "json",
	        	        data: param,
	        	        processData: false,
	        	        contentType: false,
	        	        success: function(result) {
	        	        	layer.close(layer.index);
	        	        	if(result.code == 1){
	        	        		$.operate.successCallback(result.msg);
	        	        	}else{
	        	        		$.operate.errorCallback_orderBonded(result.msg);
	        	        		return;
	        	        	}
	        	        }
	        	    };
	        	    $.ajax(config)
			},
			//保税保存方法
			save_OrderBonded : function(url,param){
				var config = {
	        	        url: url,
	        	        type: "post",
	        	        dataType: "json",
	        	        data: param,
	        	        success: function(result) {
	        	        	if(result >0){
	        	        		$.operate.successCallback("保存成功");
	        	        	}else{
	        	        		$.modal.msgError("保存失败");
	        	        		return;
	        	        	}
	        	        }
	        	    };
	        	    $.ajax(config)
			},
			//直邮保存方法
			save_OrderMail : function(url,param){
				$.layerMsg("推送中...");
				var config = {
	        	        url: url,
	        	        type: "post",
	        	        //dataType: "json",
	        	        data: param,
	        	        success: function(result) {
	        	        	layer.close(layer.index);
	        	        	if(result == "推送成功!"){
	        	        		layer.msg(result, {
	        	        		  icon: 1 ,
	  	              			  time: 700 //2秒关闭（如果不配置，默认是3秒）
	  	              			}, function(index){
	  	              				window.parent.location.reload();
	  	            				layer.close(index); 
	  	              			})
	        	        	}else{
	        	        		layer.msg(result, {
	        	        		  icon: 2,
	  	              			  time: 1200 //2秒关闭（如果不配置，默认是3秒）
	  	              			}, function(index){
	  	              			})
	        	        	}
	        	        	
	        	        }
	        	    };
	        	    $.ajax(config)
			},
			
			save_OrderMail_2 : function(url,param){
				$.layerMsg("修改中...");
				var config = {
	        	        url: url,
	        	        type: "post",
	        	        //dataType: "json",
	        	        data: param,
	        	        success: function(result) {
	        	        	layer.close(layer.index);
	        	        	if(result == "修改成功!"){
	        	        		layer.msg(result, {
	        	        		  icon: 1 ,
	  	              			  time: 700 //2秒关闭（如果不配置，默认是3秒）
	  	              			}, function(index){
	  	              				window.parent.location.reload();
	  	            				layer.close(index); 
	  	              			})
	        	        	}else{
	        	        		layer.msg(result, {
	        	        		  icon: 2,
	  	              			  time: 1200 //2秒关闭（如果不配置，默认是3秒）
	  	              			}, function(index){
	  	              			})
	        	        	}
	        	        	
	        	        }
	        	    };
	        	    $.ajax(config)
			},
			 // 保存结果弹出msg刷新table表格
            ajaxSuccess: function (result) {
            	if (result.code == web_status.SUCCESS) {
                	$.modal.msgSuccess(result.msg);
            		$.table.refresh();
                } else {
                	$.modal.alertError(result.msg);
                }
            	$.modal.closeLoading();
            },
          //直邮导出
			export_OrderMail : function(url,param){
				$.layerMsg("导出中...");
				var config = {
	        	        url: url,
	        	        type: "post",
	        	        //dataType: "json",
	        	        data: param,
	        	        success: function(result) {
	        	        	layer.close(layer.index);
	        	        	if(result == "finish"){
	        	        		layer.msg("导出成功", {
		        	        		  icon: 1 ,
		  	              			  time: 1000 //2秒关闭（如果不配置，默认是3秒）
		  	              			}, function(index){
		  	            				layer.close(index); 
		  	              			})
	        	        	}else{
	        	        		layer.msg("导出失败", {
		        	        		  icon: 5 ,
		  	              			  time: 2000 //2秒关闭（如果不配置，默认是3秒）
		  	              			}, function(index){
		  	            				layer.close(index); 
		  	              		})
	        	        	}
	        	        }
	        	    };
	        	    $.ajax(config)
			},
			//删除AJAX
			delAjax : function(url,param){
				var config = {
						 	url: url,
		        	        type: "post",
		        	        dataType: "json",
		        	        data: {"ids":param},
		        	        success: function(result) {
		        	        	if(result >0){
		        	        		$.operate.success("删除成功");
		        	        	}else{
		        	        		$.modal.msgError("删除失败");
		        	        		return;
		        	        	}
		        	        }
				};
				 $.ajax(config)
			},
			 // 删除信息
            remove: function(id) {
            	$.modal.confirm("确定删除该条" + $.table._option.modalName + "记录吗？", function() {
	            	var url = $.common.isEmpty(id) ? $.table._option.removeUrl : $.table._option.removeUrl.replace("{id}", id);
	            	var data = { "ids": id };
	            	$.operate.submit(url, "post", "json", data);
            	});
            },
            // 批量删除信息
            removeAll: function() {
            	var row = $("#bootstrap-table").bootstrapTable('getSelections');
				if (row.length == 0) {
        			$.modal.alertWarning("请至少选择一条记录");
        			return;
        		}
				var ids = "";
				for(var i = 0 ; i < row.length ; i++ ){
					if(i==0){
						ids=row[i].id;
					}else{
						ids+=","+row[i].id;
					}
				}
        		$.modal.confirm("确认要删除选中的" + row.length + "条数据吗?", function() {
        			var url = $.table._option.removeUrl;
    				var data = { "ids": ids };
        			$.operate.submit(url, "post", "json", data);
        		});
            },
            // 计划入库单删除
            planRemoveAll: function() {
            	var row = $("#bootstrap-table").bootstrapTable('getSelections');
				if (row.length == 0) {
        			$.modal.alertWarning("请至少选择一条记录");
        			return;
        		}
				var ids = "";
				for(var i = 0 ; i < row.length ; i++ ){
					if(i==0){
						ids=row[i].id;
					}else{
						ids+=","+row[i].id;
					}
				}
        		$.modal.confirm("确认要删除选中的" + row.length + "条数据吗?", function() {
        			var url = $.table._option.removeUrl;
    				$.post(url,{
           				"id":ids
           			},function(result){
           				if (result!=null){
	                  		$.operate.success("删除成功");
	                    }
                	}
                );
        		});
            },
            //计划入库单推送
            pushPr:function(){
            	var row = $("#bootstrap-table").bootstrapTable('getSelections');
				if (row.length == 0) {
        			$.modal.alertWarning("请至少选择一条记录");
        			return;
        		}
				 var ids = ""; 
			 	    for (var i = 0; i < row.length; i++){
			 	    	if(row[i].flag == undefined || row[i].flag =='处理失败' || row[i].flag == "" ){
			 	    		var ids = ids + row[i].id +",";                     
			 	    	}else{
			 	    		layer.msg("包含已推送成功数据!", {icon: 2});
			 	    		return false; 
			 	    	}
			       	}
			       	ids = ids.substring(0, ids.length-1); 
			       	var url = $.table._option.pushPrUrl;
			       	console.log(url);
			       	$.modal.confirm("确定要推送这"+row.length+"条数据吗？", function() {
			       		$.modal.loading("正在推送数据，请稍后...");
			       		$.post(url,{
	           				"ids":ids 
	           			},function(result){
		                  	if (result == '1'){ 
		                  		$.operate.success("推送成功");
		                  		$.modal.closeLoading();
		                    } else { 
		                    	layer.open({
		                    		  title:"失败结果", 
		                    		  type: 1,
		                    		  area: ['500px', '300px'],
		                    		  content: result //这里content是一个普通的String
		                    		  ,cancel: function(){ 
		                    			  $.table.refresh();
			                    	}
		                    		});
		                    	$.modal.closeLoading();
		                    }
	                	}
	                );
			       	});
            },
          //实收入库单推送
            actts:function(){
            	var row = $("#bootstrap-table").bootstrapTable('getSelections');
				if (row.length == 0) {
        			$.modal.alertWarning("请至少选择一条记录");
        			return;
        		}
				 var ids = ""; 
			 	    for (var i = 0; i < row.length; i++){
			 	    	if(row[i].flag == undefined || row[i].flag =='处理失败' || row[i].flag == "" ){
			 	    		var ids = ids + row[i].jobFormId +",";                     
			 	    	}else{
			 	    		layer.msg("包含已推送成功数据!", {icon: 2});
			 	    		return false; 
			 	    	}
			       	}
			       	ids = ids.substring(0, ids.length-1); 
			       	var url = $.table._option.pushPrUrl;
			       	console.log(url);
			       	$.modal.confirm("确定要推送这"+row.length+"条数据吗？", function() {
			       		$.modal.loading("正在推送数据，请稍后...");
			       		$.post(url,{
	           				"ids":ids 
	           			},function(result){
		                  	if (result == '1'){ 
		                  		$.operate.success("推送成功");
		                  		$.modal.closeLoading();
		                    } else { 
		                    	layer.open({
		                    		  title:"失败结果", 
		                    		  type: 1,
		                    		  area: ['500px', '300px'],
		                    		  content: result //这里content是一个普通的String
		                    		  ,cancel: function(){ 
		                    			  $.table.refresh();
			                    	}
		                    	});
		                    	$.modal.closeLoading();
		                    }
	                	}
	                );
			       	});
            },
			// 成功回调执行事件(无窗口刷新)
            success: function(result) {
            		layer.msg(result, {
            			  icon: 1,
            			  time: 700 //2秒关闭（如果不配置，默认是3秒）
            			}, function(index){
            			  //do something
            				  $("#bootstrap-table").bootstrapTable("refresh", {
            					    silent: true //静态刷新
            					});
            	})
            },
			// 成功回调执行事件（父窗体静默更新）
            successCallback: function(result) {
            		
            		layer.msg(result, {
            			  icon: 1,
            			  time: 2000 //2秒关闭（如果不配置，默认是3秒）
            			}, function(index){
            			  //do something
            				window.parent.location.reload();
            				layer.close(index); 
            	})
            },
    		//保税成功回调执行
    		errorCallback_orderBonded : function(result){
    			layer.msg(result, {
      			  icon: 5,
      			  time: 3000 //2秒关闭（如果不配置，默认是3秒）
      			}, function(index){
      			  //do something
    				window.parent.location.reload();
    				layer.close(index); 
    	})
    		},
		},
		layerMsg:function(text){
    		layer.open({
    	    	  type: 1,
    	    	  closeBtn: 0,
    	    	  content: text,
    	    	  area : ['25%','25%'],
    	    }); 
    	},
		   // 校验封装处理
        validate: {
        	// 判断返回标识是否唯一 false 不存在 true 存在
        	unique: function (value) {
            	if (value == "0") {
                    return true;
                }
                return false;
            },
            // 表单验证
            form: function (formId) {
            	var currentId = $.common.isEmpty(formId) ? $('form').attr('id') : formId;
                return $("#" + currentId).validate().form();
            }
        },
		   // 弹出层封装处理
    	modal: {
    		// 显示图标
    		icon: function(type) {
            	var icon = "";
        	    if (type == modal_status.WARNING) {
        	        icon = 0;
        	    } else if (type == modal_status.SUCCESS) {
        	        icon = 1;
        	    } else if (type == modal_status.FAIL) {
        	        icon = 2;
        	    } else {
        	        icon = 3;
        	    }
        	    return icon;
            },
    		// 消息提示
            msg: function(content, type) {
            	if (type != undefined) {
                    layer.msg(content, { icon: $.modal.icon(type), time: 1000, shift: 5 });
                } else {
                    layer.msg(content);
                }
            },
            // 错误消息
            msgError: function(content) {
            	$.modal.msg(content, modal_status.FAIL);
            },
            // 成功消息
            msgSuccess: function(content) {
            	$.modal.msg(content, modal_status.SUCCESS);
            },
            // 警告消息
            msgWarning: function(content) {
            	$.modal.msg(content, modal_status.WARNING);
            },
    		// 弹出提示
            alert: function(content, type) {
        	    layer.alert(content, {
        	        icon: $.modal.icon(type),
        	        title: "系统提示",
        	        btn: ['确认'],
        	        btnclass: ['btn btn-primary'],
        	    });
            },
            // 消息提示并刷新父窗体
            msgReload: function(msg, type) {
            	layer.msg(msg, {
            	    icon: $.modal.icon(type),
            	    time: 500,
            	    shade: [0.1, '#8F8F8F']
            	},
            	function() {
            	    $.modal.reload();
            	});
            },
            // 错误提示
            alertError: function(content) {
            	$.modal.alert(content, modal_status.FAIL);
            },
            // 成功提示
            alertSuccess: function(content) {
            	$.modal.alert(content, modal_status.SUCCESS);
            },
            // 警告提示
            alertWarning: function(content) {
            	$.modal.alert(content, modal_status.WARNING);
            },
            // 关闭窗体
            close: function () {
            	var index = parent.layer.getFrameIndex(window.name);
                parent.layer.close(index);
            },
            // 关闭全部窗体
            closeAll: function () {
                layer.closeAll();
            },
            // 确认窗体
            confirm: function (content, callBack) {
            	layer.confirm(content, {
        	        icon: 3,
        	        title: "系统提示",
        	        btn: ['确认', '取消'],
        	        btnclass: ['btn btn-primary', 'btn btn-danger'],
        	    }, function (index) {
        	    	layer.close(index);
        	        callBack(true);
        	    });
            },
            // 弹出层指定宽度
            open: function (title, url, width, height) {
            	//如果是移动端，就使用自适应大小弹窗
            	if (navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)) {
            	    width = 'auto';
            	    height = 'auto';
            	}
            	if ($.common.isEmpty(title)) {
                    title = false;
                };
                if ($.common.isEmpty(url)) {
                    url = "/404.html";
                };
                if ($.common.isEmpty(width)) {
                	width = 800;
                };
                if ($.common.isEmpty(height)) {
                	height = ($(window).height() - 50);
                };
            	layer.open({
            		type: 2,
            		area: [width + 'px', height + 'px'],
            		fix: false,
            		//不固定
            		maxmin: true,
            		shade: 0.3,
            		title: title,
            		content: url,
            		btn: ['确定', '关闭'],
            	    // 弹层外区域关闭
            		shadeClose: true,
            		yes: function(index, layero) {
            	        var iframeWin = layero.find('iframe')[0];
            	        iframeWin.contentWindow.submitHandler();
            	    },
            	    cancel: function(index) {
            	        return true;
            	    }
            	});
            },
            // 弹出层指定参数选项
            openOptions: function (options) {
            	var _url = $.common.isEmpty(options.url) ? "/404.html" : options.url; 
            	var _title = $.common.isEmpty(options.title) ? "系统窗口" : options.title; 
                var _width = $.common.isEmpty(options.width) ? "800" : options.width; 
                var _height = $.common.isEmpty(options.height) ? ($(window).height() - 50) : options.height;
                layer.open({
                    type: 2,
            		maxmin: true,
                    shade: 0.3,
                    title: _title,
                    fix: false,
                    area: [_width + 'px', _height + 'px'],
                    content: _url,
                    shadeClose: true,
                    btn: ['<i class="fa fa-check"></i> 确认', '<i class="fa fa-close"></i> 关闭'],
                    yes: function (index, layero) {
                        options.callBack(index, layero)
                    }, cancel: function () {
                        return true;
                    }
                });
            },
            // 弹出层全屏
            openFull: function (title, url, width, height) {
            	//如果是移动端，就使用自适应大小弹窗
            	if (navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)) {
            	    width = 'auto';
            	    height = 'auto';
            	}
            	if ($.common.isEmpty(title)) {
                    title = false;
                };
                if ($.common.isEmpty(url)) {
                    url = "/404.html";
                };
                if ($.common.isEmpty(width)) {
                	width = 800;
                };
                if ($.common.isEmpty(height)) {
                	height = ($(window).height() - 50);
                };
                var index = layer.open({
            		type: 2,
            		area: [width + 'px', height + 'px'],
            		fix: false,
            		//不固定
            		maxmin: true,
            		shade: 0.3,
            		title: title,
            		content: url,
            		btn: ['确定', '关闭'],
            		// 弹层外区域关闭
            		shadeClose: true,
            		yes: function(index, layero) {
            	        var iframeWin = layero.find('iframe')[0];
            	        iframeWin.contentWindow.submitHandler();
            	    },
            	    cancel: function(index) {
            	        return true;
            	    }
            	});
                layer.full(index);
            },
            // 禁用按钮
            disable: function() {
	        	$("a[class*=layui-layer-btn]", window.parent.document).addClass("layer-disabled");
            },
            // 启用按钮
            enable: function() {
            	$("a[class*=layui-layer-btn]", window.parent.document).removeClass("layer-disabled");
            },
    		// 打开遮罩层
            loading: function (message) {
            	$.blockUI({ message: '<div class="loaderbox"><div class="loading-activity"></div> ' + message + '</div>' });
            },
            // 关闭遮罩层
            closeLoading: function () {
            	setTimeout(function(){
            		$.unblockUI();
            	}, 50);
            },
            // 重新加载
            reload: function () {
            	parent.location.reload();
            }
        },
		// 通用方法封装处理
    	common: {
    		// 判断字符串是否为空
            isEmpty: function (value) {
                if (value == null || this.trim(value) == "") {
                    return true;
                }
                return false;
            },
            // 判断一个字符串是否为非空串
            isNotEmpty: function (value) {
            	return !$.common.isEmpty(value);
            },
            // 是否显示数据 为空默认为显示
            visible: function (value) {
                if ($.common.isEmpty(value) || value == true) {
                    return true;
                }
                return false;
            },
            // 空格截取
            trim: function (value) {
                if (value == null) {
                    return "";
                }
                return value.toString().replace(/(^\s*)|(\s*$)|\r|\n/g, "");
            },
            // 指定随机数返回
            random: function (min, max) {
                return Math.floor((Math.random() * max) + min);
            },
            startWith: function(value, start) {
                var reg = new RegExp("^" + start);
                return reg.test(value)
            },
            endWith: function(value, end) {
                var reg = new RegExp(end + "$");
                return reg.test(value)
            }
        }
		
	})	
})(jQuery);

function createTime(v){
	if(v==null){
		 return "-";
	}
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
    var str = y+"-"+m+"-"+d+" "+h+":"+M;
    return str;
}
/** 消息状态码 */
web_status = {
    SUCCESS: 0,
    FAIL: 500
};

/** 弹窗状态码 */
modal_status = {
    SUCCESS: "成功",
    FAIL: "失败",
    WARNING: "警告"
};