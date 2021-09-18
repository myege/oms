(function($){  
    $.fn.serializeJson=function(){  
        var serializeObj={};  
        var array=this.serializeArray();  
        var str=this.serialize();  
        $(array).each(function(){  
            if(serializeObj[this.name]){  
                if($.isArray(serializeObj[this.name])){  
                    serializeObj[this.name].push(this.value);  
                }else{  
                    serializeObj[this.name]=[serializeObj[this.name],this.value];  
                }  
            }else{  
                serializeObj[this.name]=this.value;   
            }  
        });  
        return serializeObj;  
    };  
})(jQuery); 

//********模式窗口类***********

function modalwindow(title,width,height,url){
	this.title=title;
	this.width=width;
	this.height=height;
	this.url=url;
	var id = new Date().getMilliseconds();
	this.windowid="win_"+id;
	this.iframeid="iframe_"+id;

}
modalwindow.prototype.createWindow = function(){
	var active = document.createElement("div");
	active.id="active";
	document.body.appendChild(active);
	active.innerHTML="<div id="+this.windowid+" class=\"easyui-window\" modal='true' closed='true'  iconCls=\"icon-save\" ><iframe scrolling='yes' style='overflow-x:hidden;' id='"+this.iframeid+"' src='"+this.url+"' frameborder='0' width='100%' height='100%'></iframe></div>";

	$('#'+this.windowid).window({
		title: this.title,
		width: this.width,
		modal: true,
		shadow: false,
		minimizable:false,
		collapsible:false,
		closed: true,
		height: this.height
	});
	this.open();

};
modalwindow.prototype.open = function(){
	$('#'+this.windowid).window('open');
};
modalwindow.prototype.close = function(){
	$('#'+this.windowid).window('close');
};

//模式窗口单一实例
var modalwindow_singleobj;
//创建模式窗口
function createmodalwindow(title,width,height,url){
	modalwindow_singleobj = new modalwindow(title,width,height,url);
	modalwindow_singleobj.createWindow();

}
//关闭模式窗口
function closemodalwindow(){
	if(modalwindow_singleobj){
		modalwindow_singleobj.close();
	}
}

Date.prototype.format = function (fmt) { //author: meizz 
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "H+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

//日期类型转成"yyyy-MM-dd hh:mm:ss"
function formatDate4(value){
	//return new Date(value).toLocaleString().replace(/年|月/g, "-").replace(/日/g, " ");
	if(value==null){
		return '';
	}
	var date = new Date(value);
	return date.format("hh:mm前");
}
function formatDate5(value){
	//return new Date(value).toLocaleString().replace(/年|月/g, "-").replace(/日/g, " ");
	if(value==null){
		return '';
	}
	var date = new Date(value);
	return date.format("MM月dd日");
}


function formatDate(value){
	//return new Date(value).toLocaleString().replace(/年|月/g, "-").replace(/日/g, " ");
	if(value==null || value==''){
		return '';
	}
	var date = new Date(value);
	return date.format("yyyy-MM-dd HH:mm:ss");
}
//日期类型转成"yyyy-MM-dd hh:mm:ss"
/*function formatDate(value){
 	var date = new Date(value);
	var year = date.getFullYear();
	var month = date.getMonth()+1;
	var day = date.getDate();
	var hour = date.getHours();
	var minute = date.getMinutes();
	var second = date.getSeconds();
	var dStr = year +"-"+month +"-"+day+" "+hour+":"+minute+":"+second;		
	return dStr;
}*/
//日期类型转成"yyyy-MM-dd"
function formatDate2(value){
	var date = new Date(value);
var year = date.getFullYear();
var month = date.getMonth()+1;
var day = date.getDate();
var dStr = year +"-"+month +"-"+day	;					
return dStr;
}

//日期类型转成"yyyy/MM/dd"
function formatDate3(value){
	var date = new Date(value);
var year = date.getFullYear();
var month = date.getMonth()+1;
var day = date.getDate();
var dStr = year +"/"+month +"/"+day	;					
return dStr;
}