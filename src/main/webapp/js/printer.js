var barCodePrinterName = 'BTP-6200I(U) 1';
var generalPrinterName = '';
var labelPrinterName = '';

/**
 * WMS 通用打印方法
 */
var stsPrinter = {
		
	/**
	 * 打印机控件实例
	 */
	obj : null,
	
	/**
	 * 打印机控件初始化
	 */
	init : function(){
		
		var printerDivHtml = document.getElementById('printerDiv').innerHTML;
		if(stsPrinter.isNull(printerDivHtml)){
			//printerDivHtml = '<embed id="LODOP_EM" type="application/x-print-lodop" width=0 height=0></embed>';
			printerDivHtml += '<object id="LODOP_OB" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0>'; 
			printerDivHtml += '	<embed id="LODOP_EM" type="application/x-print-lodop"  width=0 height=0 pluginspage="install_lodop32.exe" />';
			printerDivHtml += '</object>';
		}
		
		document.getElementById("printerDiv").innerHTML = printerDivHtml;
		
		
	},
	
	/**
	 * 获取打印机控件实例
	 * @returns
	 */
	getPlugin : function(){
		
		if(stsPrinter.obj == null){
			stsPrinter.init();
			//var plugin = document.getElementById("LODOP_OB");
			//if(stsPrinter.isNull(plugin)){
			//	plugin = document.getElementById("LODOP_EM");
			//}
			
			var plugin = getLodop(document.getElementById('LODOP_OB'),document.getElementById('LODOP_EM'));
			
			if(stsPrinter.isNull(plugin)){
				MessageBox.Show("未能正常获取打印控件实例。");
			}
			
			plugin.SET_LICENSES("申通沃德（上海）供应链管理有限公司萧山分公司","5A7E186775D17C979595FEC07623ECDE","","");
			
			stsPrinter.obj = plugin;
			
			return stsPrinter.obj;
			
		}else{
			return stsPrinter.obj;
		}
		
		return stsPrinter.obj;
		
	},
	
	/**
	 * 无法获取打印机时的提示
	 */
	noPlugin : function(){
		alert("无法获取打印控件，请联系系统管理员处理。");
	},
	
	/**
	 * 获取所有打印机序号及名称
	 */
	getPrinterNames : function(){
		var printer = stsPrinter.getPlugin();
		
		var pdCount = printer. GET_PRINTER_COUNT ();
		if(pdCount <= 0){
			//alert('没有安装任何打印机。');
			return [];//null;
		}else{
			
			var printers = [];
			for(var i=0;i<pdCount;i++){
				var strPName=printer. GET_PRINTER_NAME(i);
				printers[i] = strPName;
			}
			
			return printers;
		}
	},
	
	
	/**
	 * 凭名称获取打印机序号
	 * @param printerName
	 */
	getPrinterIndexByName : function(printerName){
		var printers = stsPrinter.getPrinterNames();
		for(var i=0;i<printers.length;i++){
			if(printers[i] == printerName){
				return i;
				break;
			}
		}
		return -1;
	},

	/* XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX 标签打印 XXXXXXXXXXXXXXXXXXXXXXX */
		
	/**
	 * 打印小标签，如：商品条码、容器条码、耗材条码、货架条码、库位条码
	 * @param taskName 任务名称
	 * @param barCode 条码字符串
	 * @param bottomText 除条码字符串内容外，额外显示的文字
	 */
	printBarCode : function(taskName,barCode,bottomText){
		
		stsPrinter.getPlugin();
		
		if(stsPrinter.isNull(stsPrinter.obj)){
			stsPrinter.noPlugin();
			return false;
		}
		
		stsPrinter.obj.PRINT_INIT(taskName);
		var printerIndex = stsPrinter.getPrinterIndexByName(barCodePrinterName);
		if(printerIndex < 0){
			//alert('打印机未安装或名称指定错误。');
			//return;
		}

		//alert(printerIndex);
		
		//stsPrinter.obj.SET_PRINTER_INDEX(printerIndex);
		//stsPrinter.obj.SET_PRINT_PAGESIZE(1,180,108,"打印小标签");
		stsPrinter.obj.PRINT_INITA(0, 0, 230, 170, "");
		stsPrinter.obj.ADD_PRINT_BARCODE(29, 20, 187, 65, "Code39", barCode);
		stsPrinter.obj.SET_PRINT_STYLEA(0,"FontSize",8);
		stsPrinter.obj.ADD_PRINT_TEXT(110, 20, 168, 23, bottomText);
		stsPrinter.obj.SET_PRINT_STYLEA(0, "FontSize", 9);
		stsPrinter.obj.SET_PRINT_STYLEA(0, "Alignment", 2);
		stsPrinter.obj.SET_PRINT_STYLEA(0, "Bold", 1);
		stsPrinter.obj.SELECT_PRINTER();
		stsPrinter.obj.PREVIEW();
		//stsPrinter.obj.PRINT();
	},
		
	/**
	 * 打印容器条形码
	 * @param containerBarCode 容器条码，不可为空
	 * @param containerName 容器名称，可为空
	 */
	printContainerBarCode : function(containerBarCode, containerName) {
		if (stsPrinter.isNull(containerBarCode)) {
			alert('容器条码不能为空。');
			return;
		}
		
		
		stsPrinter.getPlugin();
		
		if(stsPrinter.isNull(stsPrinter.obj)){
			stsPrinter.noPlugin();
			return false;
		}
		
		stsPrinter.obj.PRINT_INITA(0,0,550,370,"打印容器条码");
		
		var printerIndex = stsPrinter.getPrinterIndexByName(barCodePrinterName);
		if(printerIndex < 0){
			//alert('打印机未安装或名称指定错误。');
			//return;
		}
		
		
		stsPrinter.obj.SET_PRINTER_INDEX(printerIndex);
		stsPrinter.obj.SET_PRINT_PAGESIZE(1,550,370,"");
		stsPrinter.obj.ADD_PRINT_BARCODE(23,20,250,85,"Code39",containerBarCode);
		stsPrinter.obj.PRINT();
		//stsPrinter.obj.PREVIEW();
		
		//stsPrinter.printBarCode('打印商品条码',containerBarCode,containerName);
	},
	
	/**
	 * 打印容器条形码
	 * @param containerBarCode 容器条码，不可为空
	 * @param containerName 容器名称，可为空
	 */
	printContainerBarCodes : function(containerBarCodes, containerNames) {
		
		if (containerBarCodes.length == 0) {
			alert('容器条码不能为空。');
			return;
		}
		
		stsPrinter.getPlugin();
		
		if(stsPrinter.isNull(stsPrinter.obj)){
			stsPrinter.noPlugin();
			return false;
		}
		
		stsPrinter.obj.PRINT_INITA(0,0,550,370,"打印容器条码");
		
		var printerIndex = stsPrinter.getPrinterIndexByName(barCodePrinterName);
		if(printerIndex < 0){
			//alert('打印机未安装或名称指定错误。');
			//return;
		}
		
		
		stsPrinter.obj.SET_PRINTER_INDEX(printerIndex);
		//stsPrinter.obj.SET_PRINT_PAGESIZE(1,550,370,"");
		
		for(var i=0;i<containerBarCodes.length;i++){
		    var containerBarCode = containerBarCodes[i];
			stsPrinter.obj.NewPage();
			stsPrinter.obj.ADD_PRINT_BARCODE(23,20,250,85,"Code39",containerBarCode);
		
		}
		
		//stsPrinter.obj.PRINT();
		stsPrinter.obj.PREVIEW();
		
		//stsPrinter.printBarCode('打印商品条码',containerBarCode,containerName);
	},
	
	/**
	 * 扫描耗材条形码
	 * @param commoditieBarCode 耗材条码，不可为空
	 * @param commoditieName 耗材名称，可为空
	 */
	printCommoditieBarCode : function(commoditieBarCode,commoditieName){
		if (stsPrinter.isNull(commoditieBarCode)) {
			alert('耗材条码不能为空。');
			return;
		}
		stsPrinter.printBarCode('打印耗材条码',commoditieBarCode,commoditieName);
	},
	
	/**
	 * 打印货架条码
	 * @param shelfBarCode 货架条码
	 * @param scaleStartToEnd 起止刻度，如：010 - 200
	 */
	printWarehouseShelfBarCode : function(shelfBarCode,scaleStartToEnd){
		if (stsPrinter.isNull(shelfBarCode)) {
			alert('耗材条码不能为空。');
			return;
		}
		
		//shelfBarCode = shelfBarCode.replace("","");
		
		stsPrinter.printBarCode('打印货架条码',shelfBarCode,scaleStartToEnd);
	},
	
	/**
	 * 打印货架库位刻度条码
	 * @param scaleBarCode 货架库位刻度条码
	 */
	printWarehouseShelfScaleBarCode : function(seriesNum,scaleBarCode){
		if(stsPrinter.isNull(scaleBarCode) || stsPrinter.isNull(seriesNum)){
			alert("序号和条码不能为空。");
			return;
		}
		
		stsPrinter.getPlugin();
		
		if(stsPrinter.isNull(stsPrinter.obj)){
			stsPrinter.noPlugin();
			return false;
		}
		
		var printerIndex = null;
		if(stsPrinter.isNull(printerIndex)){
			printerIndex = stsPrinter.getPrinterIndexByName(barCodePrinterName);
			if(printerIndex < 0){
				//alert('打印机未安装或名称指定错误。');
				//return;
			}
		}
		
		//stsPrinter.obj.SET_PRINTER_INDEX(printerIndex);
		
		var snStr = seriesNum.toString();
		
		if(seriesNum < 10) snStr = "00" + snStr;
		else snStr = "0" + snStr;
		
		var layNumStr = scaleBarCode.substring(6,7);
		
		var newSeriesNum = snStr;//snStr.substring(0,1) + "\n" + snStr.substring(1,2) + "\n" + snStr.substring(2,3);
	
		layNumStr += " 层 " + newSeriesNum;
		
		//alert(layNumStr);
		
		// 中标签
//		stsPrinter.obj.PRINT_INITA(0,0,222,142,"");
//		stsPrinter.obj.ADD_PRINT_BARCODE(52,24,202,81,"",scaleBarCode);
//		stsPrinter.obj.ADD_PRINT_TEXT(8,-2,223,48,layNumStr);
//		stsPrinter.obj.SET_PRINT_STYLEA(0,"FontName","微软雅黑");
//		stsPrinter.obj.SET_PRINT_STYLEA(0,"FontSize",24);
//		stsPrinter.obj.SET_PRINT_STYLEA(0,"Alignment",2);
//		stsPrinter.obj.SET_PRINT_STYLEA(0,"LetterSpacing",5);
		
		//mod by hbj 2014-9-19
		stsPrinter.obj.PRINT_INITA(0,0,370,145,"打印库位刻度");
		stsPrinter.obj.SET_PRINTER_INDEX(printerIndex);
		stsPrinter.obj.ADD_PRINT_BARCODE(58,42,308,77,"",scaleBarCode);
		stsPrinter.obj.ADD_PRINT_TEXT(12,43,267,44,layNumStr);
		stsPrinter.obj.SET_PRINT_STYLEA(0,"FontName","微软雅黑");
		stsPrinter.obj.SET_PRINT_STYLEA(0,"FontSize",26);
		stsPrinter.obj.SET_PRINT_STYLEA(0,"Alignment",2);
		stsPrinter.obj.SET_PRINT_STYLEA(0,"Bold",1);
		
		//stsPrinter.obj.PRINT_INITA(0,0,222,148,"");
		//stsPrinter.obj.ADD_PRINT_TEXT(14,-5,40,112,newSeriesNum);
		//stsPrinter.obj.SET_PRINT_STYLEA(0,"FontName","微软雅黑");
		//stsPrinter.obj.SET_PRINT_STYLEA(0,"FontSize",19);
		//stsPrinter.obj.SET_PRINT_STYLEA(0,"Alignment",2);
		//stsPrinter.obj.SET_PRINT_STYLEA(0,"Bold",1);
		//stsPrinter.obj.ADD_PRINT_BARCODE(14,38,175,106,"",scaleBarCode);
		//stsPrinter.obj.PREVIEW();
		stsPrinter.obj.PRINT();
		//stsPrinter.obj.PREVIEW();
	},
	
	
	/**
	 * 打印库位条码
	 */
	printWarehouseCellBarCode : function(barCode){
		if(stsPrinter.isNull(barCode)){
			alert("库位条码不能为空。");
			return;
		}
		
		// 格式如：[库区,1位大写字母]-[通道,3位数字]-[层数，1位数字]-[起始刻度，3位数字]-[结束刻度,3位数字]
		var codes = barCode.split('-');
		if(codes.length != 5){
			alert("库位码格式不正确。");
			return;
		}
		
		var seriesNumber = parseInt(codes[codes.length - 2]);
		var scaleCode = "";
		for(var i=0;i<codes.length-1;i++){
			scaleCode += (scaleCode == "" ? "" : "-") + codes[i];
		}

		//alert(seriesNumber);
		//alert(scaleCode);
		
		stsPrinter.printWarehouseShelfScaleBarCode(seriesNumber,scaleCode);
		
		//stsPrinter.printBarCode('打印库位条码',barCode,'');
	},
	
	/**
	 * 打印商品条码
	 * @param goodsSkuBarCode 商品条码
	 * @param goodsName 商品名称，可为空
	 */
	printGoodsSkuBarCode : function(goodsSkuBarCode,goodsName){
		if(stsPrinter.isNull(goodsSkuBarCode)){
			alert("商品条码不能为空。");
			return;
		}
		stsPrinter.printBarCode('打印商品条码',goodsSkuBarCode,goodsName);
	},
	
	/**
	 * 打印快递电子面单
	 * 
	 * 入参：
	 * jsonData 要打印的数据对象
	 * 		.expressNumber : 快递单号
	 * 		.printDate : 打印日期，例：2014-08-12
	 * 		.weight:重量
	 * 		.employeeName:业务员
	 * 		.employeeCode:工号
	 * 		.posterName : 寄件人姓名
	 * 		.posterTel : 寄件人电话
	 * 		.receiverName : 收件人姓名
	 * 		.receiverTel:收件人电话
	 * 		.receiveAdd:收件人地址
	 * 		.receivePostCode:收件人所在地邮编
	 * 		.areaName:地区
	 * 		.goodsCount:商品件数
	 * 		.seller:商家名称
	 * 
	 * 
	 * printerIndex 打印机序号
	 * 
	 * 
	 * template:打印模板对象
	 * 		.code:模板代码
	 * 		.name:模板名称
	 * 		.data:模板内容
	 * 		.printerName:打印机名称
	 * 		.seller:商家编号
	 * 		.express:快递公司编号
	 * 		.paperName:纸张名称
	 * 		.regionWidth:打印区域宽度
	 * 		.regionHeight:打印区域高度
	 */
	printExpressFull : function(jsonDatas,printerIndex,template){

		stsPrinter.getPlugin();
		
		if(stsPrinter.isNull(stsPrinter.obj)){
			stsPrinter.noPlugin();
			return false;
		}
		
		if(stsPrinter.isNull(printerIndex)){
			printerIndex = stsPrinter.getPrinterIndexByName(labelPrinterName);
			if(printerIndex < 0){
				//alert('打印机未安装或名称指定错误。');
				//return;
			}
		}
		
		//alert(template.data);

		//alert(printerIndex);
		
		//分析 width 和 height,并从模板中去掉 print_inita 这一行
		//取出第一行（第一行是初始化代码）
		var codeLine = template.data.split(')')[0];
		var params = codeLine .split(',');
		template.regionWidth = params[2];
		template.regionHeight = params[3];
		
		template.data = template.data.substring(codeLine.length + 2,template.data.length);
		
		//格式化变量
		template.data = stsPrinter.formatRT(template.data);
		template.data = template.data.replace(/LODOP./ig,'stsPrinter.obj.');
		
		stsPrinter.obj.PRINT_INITA(0,0,template.regionWidth,template.regionHeight,template.name);
		

		
		
		//stsPrinter.obj.PRINT_INIT('打印快递单');
		if(!stsPrinter.obj.SET_PRINTER_INDEX(printerIndex)){
			//alert('选择的打印机准备失败。');
			//return;
		}
		
		//alert(template.data);
		//return;
		
		var printData = "";
		
		

		for(var i=0;i<jsonDatas.length;i++){

			var jsonData = jsonDatas[i];

			if(stsPrinter.isNull(jsonData.weight)){
				jsonData.weight = 0;
			}
			
			
			
			//jsonData.areaStr = jsonData.areaStr.replace(/省/g,'').replace(/自治区/g,'');

			//stsPrinter.obj.SET_PRINT_PAGESIZE(1,550,380,"expressTemplate");
			
			//if((i % 10 == 0) || i == (jsonDatas.length - 1)){
					

				
			//}
			
			if(i > 0) stsPrinter.obj.NewPage();
			
			//printData += "stsPrinter.obj.NewPage();";
			
			stsPrinter.obj.SET_PRINT_PAGESIZE(1,template.regionWidth * 2.9,template.regionHeight * 2.9,"");
			
			eval(template.data);
			//printData += template.data;

			/*
			stsPrinter.obj.ADD_PRINT_BARCODE(29,11,205,55,"Code39",jsonData.expressNumber);
			stsPrinter.obj.ADD_PRINT_TEXT(32,225,124,52,jsonData.payTime);
			stsPrinter.obj.SET_PRINT_STYLEA(0,"FontSize",12);
			stsPrinter.obj.ADD_PRINT_TEXT(104,10,94,36,"寄件方：");
			stsPrinter.obj.SET_PRINT_STYLEA(0,"FontSize",11);
			stsPrinter.obj.ADD_PRINT_TEXT(104,95,255,37,jsonData.storeName);
			stsPrinter.obj.SET_PRINT_STYLEA(0,"FontSize",11);
			stsPrinter.obj.ADD_PRINT_TEXT(191,10,345,61,jsonData.receiveAdd);
			stsPrinter.obj.SET_PRINT_STYLEA(0,"FontSize",11);
			stsPrinter.obj.SET_PRINT_STYLEA(0,"Bold",1);
			stsPrinter.obj.ADD_PRINT_TEXT(145,10,95,40,"收件方：");
			stsPrinter.obj.SET_PRINT_STYLEA(0,"FontSize",12);
			stsPrinter.obj.ADD_PRINT_TEXT(145,96,252,40,jsonData.receiverName + "   " + jsonData.receiverTel);
			stsPrinter.obj.SET_PRINT_STYLEA(0,"FontSize",12);
			stsPrinter.obj.SET_PRINT_STYLEA(0,"Bold",1);
			stsPrinter.obj.ADD_PRINT_TEXT(260,10,346,61,jsonData.areaStr);
			stsPrinter.obj.SET_PRINT_STYLEA(0,"FontName","黑体");
			stsPrinter.obj.SET_PRINT_STYLEA(0,"FontSize",32);
			stsPrinter.obj.SET_PRINT_STYLEA(0,"Bold",1);
			stsPrinter.obj.ADD_PRINT_TEXT(418,10,100,40,"收件方：");
			stsPrinter.obj.SET_PRINT_STYLEA(0,"FontSize",12);
			stsPrinter.obj.ADD_PRINT_TEXT(418,109,252,40,jsonData.receiverName + "   " + jsonData.receiverTel);
			stsPrinter.obj.SET_PRINT_STYLEA(0,"FontSize",12);
			stsPrinter.obj.ADD_PRINT_BARCODE(357,9,183,50,"Code39",jsonData.expressNumber);
			stsPrinter.obj.ADD_PRINT_TEXT(500,10,277,45,jsonData.orderRemark);
			stsPrinter.obj.SET_PRINT_STYLEA(0,"FontSize",12);
			stsPrinter.obj.SET_PRINT_STYLEA(0,"Alignment",2);
			stsPrinter.obj.SET_PRINT_STYLEA(0,"Bold",1);
			stsPrinter.obj.ADD_PRINT_TEXT(480,107,255,30,jsonData.storeName);
			stsPrinter.obj.SET_PRINT_STYLEA(0,"FontSize",12);
			stsPrinter.obj.SET_PRINT_STYLEA(0,"Bold",1);
			stsPrinter.obj.ADD_PRINT_TEXT(480,10,100,30,"寄件方：");
			stsPrinter.obj.SET_PRINT_STYLEA(0,"FontSize",12);
			stsPrinter.obj.ADD_PRINT_TEXT(444,10,354,40,jsonData.receiveAdd);
			stsPrinter.obj.SET_PRINT_STYLEA(0,"FontSize",12);
			stsPrinter.obj.ADD_PRINT_TEXT(480,288,74,40,jsonData.allGoodsCount);
			stsPrinter.obj.SET_PRINT_STYLEA(0,"FontSize",15);
			stsPrinter.obj.SET_PRINT_STYLEA(0,"Alignment",3);
			stsPrinter.obj.SET_PRINT_STYLEA(0,"Bold",1);
			stsPrinter.obj.ADD_PRINT_TEXT(355,194,165,78,jsonData.allBarCode);
			stsPrinter.obj.SET_PRINT_STYLEA(0,"FontSize",8);
			stsPrinter.obj.SET_PRINT_STYLEA(0,"Alignment",3);
			stsPrinter.obj.SET_PRINT_STYLEA(0,"LineSpacing",-2);
			stsPrinter.obj.SET_PRINT_STYLEA(0,"LetterSpacing",-1); */
			
			//eval('stsPrinter.obj.ADD_PRINT_BARCODE(29,11,205,55,"Code39",jsonData.expressNumber);			stsPrinter.obj.ADD_PRINT_TEXT(32,225,124,52,jsonData.payTime);			stsPrinter.obj.SET_PRINT_STYLEA(0,"FontSize",12);			stsPrinter.obj.ADD_PRINT_TEXT(104,10,94,36,"寄件方：");			stsPrinter.obj.SET_PRINT_STYLEA(0,"FontSize",11);			stsPrinter.obj.ADD_PRINT_TEXT(104,95,255,37,jsonData.storeName);			stsPrinter.obj.SET_PRINT_STYLEA(0,"FontSize",11);			stsPrinter.obj.ADD_PRINT_TEXT(191,10,345,61,jsonData.receiveAdd);			stsPrinter.obj.SET_PRINT_STYLEA(0,"FontSize",11);			stsPrinter.obj.SET_PRINT_STYLEA(0,"Bold",1);			stsPrinter.obj.ADD_PRINT_TEXT(145,10,95,40,"收件方：");			stsPrinter.obj.SET_PRINT_STYLEA(0,"FontSize",12);			stsPrinter.obj.ADD_PRINT_TEXT(145,96,252,40,jsonData.receiverName + "   " + jsonData.receiverTel);			stsPrinter.obj.SET_PRINT_STYLEA(0,"FontSize",12);			stsPrinter.obj.SET_PRINT_STYLEA(0,"Bold",1);			stsPrinter.obj.ADD_PRINT_TEXT(260,10,346,61,jsonData.areaStr);			stsPrinter.obj.SET_PRINT_STYLEA(0,"FontName","黑体");			stsPrinter.obj.SET_PRINT_STYLEA(0,"FontSize",32);			stsPrinter.obj.SET_PRINT_STYLEA(0,"Bold",1);			stsPrinter.obj.ADD_PRINT_TEXT(418,10,100,40,"收件方：");			stsPrinter.obj.SET_PRINT_STYLEA(0,"FontSize",12);			stsPrinter.obj.ADD_PRINT_TEXT(418,109,252,40,jsonData.receiverName + "   " + jsonData.receiverTel);			stsPrinter.obj.SET_PRINT_STYLEA(0,"FontSize",12);			stsPrinter.obj.ADD_PRINT_BARCODE(357,9,183,50,"Code39",jsonData.expressNumber);			stsPrinter.obj.ADD_PRINT_TEXT(500,10,277,45,jsonData.orderRemark);			stsPrinter.obj.SET_PRINT_STYLEA(0,"FontSize",12);			stsPrinter.obj.SET_PRINT_STYLEA(0,"Alignment",2);			stsPrinter.obj.SET_PRINT_STYLEA(0,"Bold",1);			stsPrinter.obj.ADD_PRINT_TEXT(480,107,255,30,jsonData.storeName);			stsPrinter.obj.SET_PRINT_STYLEA(0,"FontSize",12);			stsPrinter.obj.SET_PRINT_STYLEA(0,"Bold",1);			stsPrinter.obj.ADD_PRINT_TEXT(480,10,100,30,"寄件方：");			stsPrinter.obj.SET_PRINT_STYLEA(0,"FontSize",12);			stsPrinter.obj.ADD_PRINT_TEXT(444,10,354,40,jsonData.receiveAdd);			stsPrinter.obj.SET_PRINT_STYLEA(0,"FontSize",12);			stsPrinter.obj.ADD_PRINT_TEXT(480,288,74,40,jsonData.allGoodsCount);			stsPrinter.obj.SET_PRINT_STYLEA(0,"FontSize",15);			stsPrinter.obj.SET_PRINT_STYLEA(0,"Alignment",3);			stsPrinter.obj.SET_PRINT_STYLEA(0,"Bold",1);			stsPrinter.obj.ADD_PRINT_TEXT(355,194,165,78,jsonData.allBarCode);			stsPrinter.obj.SET_PRINT_STYLEA(0,"FontSize",8);			stsPrinter.obj.SET_PRINT_STYLEA(0,"Alignment",3);			stsPrinter.obj.SET_PRINT_STYLEA(0,"LineSpacing",-2);			stsPrinter.obj.SET_PRINT_STYLEA(0,"LetterSpacing",-1);');
			
			
			//EMS
			//stsPrinter.obj.PRINT_INITA(0,0,800,463,"");
			//stsPrinter.obj.ADD_PRINT_TEXT(98,103,70,20,"{寄件人}");
			//stsPrinter.obj.ADD_PRINT_TEXT(100,261,100,20,"{寄件人电话}");
			//stsPrinter.obj.ADD_PRINT_TEXT(143,99,309,40,"{寄件人地址}");
//			stsPrinter.obj.ADD_PRINT_TEXT(215,106,95,20,"{收件人}");
//			stsPrinter.obj.ADD_PRINT_TEXT(212,264,145,22,"{收件人电话}");
//			stsPrinter.obj.ADD_PRINT_TEXT(257,101,323,40,"{收件地址}");
//			stsPrinter.obj.ADD_PRINT_TEXT(298,106,306,50,"{地区}");
//			stsPrinter.obj.SET_PRINT_STYLEA(0,"FontName","黑体");
//			stsPrinter.obj.SET_PRINT_STYLEA(0,"FontSize",33);
//			stsPrinter.obj.ADD_PRINT_TEXT(19,413,196,23,"{付款时间}");
//			stsPrinter.obj.ADD_PRINT_TEXT(350,251,180,93,"{SKU明细}");
//			stsPrinter.obj.ADD_PRINT_TEXT(361,77,70,41,"{商品总件数}");
//			stsPrinter.obj.SET_PRINT_STYLEA(0,"FontSize",21);
		}
		
		//eval(printData);

		//2014-12-02 before
		/*
		stsPrinter.obj.ADD_PRINT_BARCODE(27,26,163,42,"",jsonData.expressNumber);
		stsPrinter.obj.ADD_PRINT_TEXT(1,260,100,20,jsonData.printDate);
		stsPrinter.obj.ADD_PRINT_TEXT(33,232,100,20,jsonData.weight + "g");
		stsPrinter.obj.ADD_PRINT_TEXT(92,229,64,20,jsonData.employeeName);
		stsPrinter.obj.ADD_PRINT_TEXT(92,297,69,20,jsonData.employeeCode);
		stsPrinter.obj.SET_PRINT_STYLEA(0,"Alignment",3);
		stsPrinter.obj.ADD_PRINT_TEXT(149,27,337,29,jsonData.posterName + "    " + jsonData.posterTel);
		stsPrinter.obj.SET_PRINT_STYLEA(0,"FontSize",12);
		stsPrinter.obj.ADD_PRINT_TEXT(200,28,336,32,jsonData.receiverName + "    " + jsonData.receiverTel);
		stsPrinter.obj.SET_PRINT_STYLEA(0,"FontSize",12);
		stsPrinter.obj.SET_PRINT_STYLEA(0,"Bold",1);
		stsPrinter.obj.ADD_PRINT_TEXT(247,31,200,73,jsonData.receiveAdd);
		stsPrinter.obj.SET_PRINT_STYLEA(0,"FontSize",12);
		stsPrinter.obj.SET_PRINT_STYLEA(0,"Bold",1);
		stsPrinter.obj.ADD_PRINT_BARCODE(348,35,169,37,"Code39",jsonData.expressNumber);
		stsPrinter.obj.ADD_PRINT_TEXT(354,240,100,20,jsonData.printDate);
		stsPrinter.obj.ADD_PRINT_TEXT(427,30,174,20,jsonData.posterName + " " + jsonData.posterTel);
		stsPrinter.obj.ADD_PRINT_TEXT(489,31,175,20,jsonData.receiverName + " " + jsonData.receiverTel);
		stsPrinter.obj.ADD_PRINT_TEXT(410,231,61,20,jsonData.employeeName);
		stsPrinter.obj.ADD_PRINT_TEXT(409,292,69,20,jsonData.employeeCode); */
		
		//alert('1');
		//stsPrinter.obj.PREVIEW();
		stsPrinter.obj.PRINT();
	},
	
	
	
	/**
	 * 打印HTML表格
	 * @param html
	 */
	printHtmlTable : function(html,printerIndex,title){
		
		stsPrinter.getPlugin();
		
//		var printer = stsPrinter.getPlugin();
//		
		if(stsPrinter.isNull(stsPrinter.obj)){
			stsPrinter.noPlugin();
			return false;
		}
		
		stsPrinter.obj.PRINT_INIT(0,0,770,1020,"打印HTML内容");
		
		if(stsPrinter.isNull(printerIndex)){
			//printerIndex = stsPrinter.obj.getPrinterIndexByName(generalPrinterName);
			//if(printerIndex < 0){
			//	alert('打印机未安装或名称指定错误。');
			//	return;
			//}
		}
		
		//stsPrinter.obj.SET_PRINTER_INDEX(printerIndex);
		
		stsPrinter.obj.ADD_PRINT_HTML(30,20,740,988,html);
		stsPrinter.obj.PREVIEW();
	},

	printHtmlTables : function(htmls,printerIndex,title,extJSCode){
		
		stsPrinter.getPlugin();
		
		if(stsPrinter.isNull(stsPrinter.obj)){
			stsPrinter.noPlugin();
			return false;
		}
		
		stsPrinter.obj.PRINT_INIT(0,0,770,1020,"打印HTML内容");
		
		if(stsPrinter.isNull(printerIndex)){
			//printerIndex = stsPrinter.obj.getPrinterIndexByName(generalPrinterName);
			//if(printerIndex < 0){
			//	alert('打印机未安装或名称指定错误。');
			//	return;
			//}
		}
		
		//stsPrinter.obj.SET_PRINTER_INDEX(printerIndex);
		
		for(var i=0;i<htmls.length;i++){
			var html = htmls[i];
			stsPrinter.obj.NewPage();
			stsPrinter.obj.ADD_PRINT_HTM(30,20,740,988,html);
			if(extJSCode) eval(extJSCode[i]);
		}
		
		stsPrinter.obj.PREVIEW();
	},
	
	/**
	 * 设计打印模板
	 * @param id 模板编号
	 * @param template 模板内容
	 * @returns
	 */
	design : function(id,template){
		return template;
	},
	
	/**
	 * 获取打印模板
	 * 
	 * 入参：
	 * 
	 * templateCode:模板编号（可选）
	 * express:快递编号（必填）
	 * seller:商家（可选）
	 * category:模板分类（可用于区分是否电子面单，可选）
	 */
	getTemplate : function(templateCode,express,seller,category){
		
		var param = "code="+ templateCode +"&express="+ express +"&seller="+ seller +"&category=" + category;
		Ext.Ajax.request({
			url : ctx + '/printTemplate/findByParam?' + param,
			method : 'post',
			async : true,
			success : function(o){
				var jsonStr = Ext.decode(o.responseText);
			}
			
		});
		
	},
	
	/**
	 * 检查变量是否为空
	 * @param inVar 要检查的变量
	 * @returns {Boolean} 如果为空返回true
	 */
	isNull : function(inVar){
		if(inVar=='' || inVar==undefined || inVar==null){
			 return true;
		}else{
			return false;
		}
	},
	
	/**
	 * 打印模板变量对象表
	 */
	RT : null,
	
	/**
	 * 初始化打印模板变量
	 */
	initRT : function(){
		var rt = new Map();
		rt.put("{创建时间}","jsonData.createTime");
		rt.put("{外部订单号}","jsonData.edbOID");
		rt.put("{出库单号}","jsonData.orderNumber");
		rt.put("{快递单号}","jsonData.expressNumber");
		rt.put("{快递条码}","jsonData.expressNumber");
		rt.put("{寄件人}","jsonData.storeName");
		rt.put("{寄件人电话}","jsonData.posterTel");
		rt.put("{寄件地址}","jsonData.postAdd");
		rt.put("{收件人}","jsonData.receiverName");
		rt.put("{收件人电话}","jsonData.receiverTel");
		rt.put("{收件地址}","jsonData.receiveAdd");
		rt.put("{付款时间}","jsonData.payTime");
		rt.put("{收件地址邮编}","jsonData.receivePostCode");
		rt.put("{地区}","jsonData.areaStr");
		rt.put("{国家}","jsonData.country");
		rt.put("{省}","jsonData.province");
		rt.put("{市}","jsonData.city");
		rt.put("{区/县}","jsonData.region");
		rt.put("{商品总件数}","jsonData.allGoodsCount");
		rt.put("{SKU明细}","jsonData.allBarCode");
		rt.put("{商家名称}","jsonData.seller");
		rt.put("{店铺名称}","jsonData.storeName");
		rt.put("{重量}","jsonData.weight");
		rt.put("{页码序号}","jsonData.seriesNumber");
		rt.put("{收件人} {收件人电话}","jsonData.receiverName + \"\" + jsonData.receiverTel");
		rt.put("{备注}","jsonData.orderRemark");
		rt.put("自定义文本内容（可修改）","");
		rt.put("{商品名称}","jsonData.allGoodsNameStr");
		rt.put("{客服备注}","jsonData.sellerRemark");
		rt.put("{商品规格}","jsonData.allSpecification");
		
		stsPrinter.RT = rt;
	},
	
	/**
	 * 格式化打印模板中的变量
	 */
	formatRT : function (template){
		if(stsPrinter.RT == null) stsPrinter.initRT();
		var keys = stsPrinter.RT.keys();
		for(var i=0;i<keys.length;i++){
			var regExp = new RegExp('"'+ keys[i] +'"', 'gi');
			template = template.replace(regExp,stsPrinter.RT.get(keys[i]));
			
			regExp = new RegExp('\''+ keys[i] +'\'', 'gi');
			template = template.replace(regExp,stsPrinter.RT.get(keys[i]));
			
			regExp = new RegExp('`'+ keys[i] +'`', 'gi');
			template = template.replace(regExp,stsPrinter.RT.get(keys[i]));
		}
		return template;
	}
	
};

/*var CreatedOKLodop7766=null;

function getLodop(oOBJECT,oEMBED){
*//**************************
  本函数根据浏览器类型决定采用哪个页面元素作为Lodop对象：
  IE系列、IE内核系列的浏览器采用oOBJECT，
  其它浏览器(Firefox系列、Chrome系列、Opera系列、Safari系列等)采用oEMBED,
  如果页面没有相关对象元素，则新建一个或使用上次那个,避免重复生成。
  64位浏览器指向64位的安装程序install_lodop64.exe。
**************************//*
    var strHtmInstall="<br><font color='#FF00FF'>打印控件未安装!点击这里<a href='E:/apache-tomcat-7.0.57/webapps/ztz/plugin/lodop/install_lodop32.exe' target='_self'>执行安装</a>,安装后请刷新页面或重新进入。</font>";
    var strHtmUpdate="<br><font color='#FF00FF'>打印控件需要升级!点击这里<a href='E:/apache-tomcat-7.0.57/webapps/ztz/plugin/lodop/install_lodop32.exe' target='_self'>执行升级</a>,升级后请重新进入。</font>";
    var strHtm64_Install="<br><font color='#FF00FF'>打印控件未安装!点击这里<a href='E:/apache-tomcat-7.0.57/webapps/ztz/plugin/lodop/install_lodop64.exe' target='_self'>执行安装</a>,安装后请刷新页面或重新进入。</font>";
    var strHtm64_Update="<br><font color='#FF00FF'>打印控件需要升级!点击这里<a href='E:/apache-tomcat-7.0.57/webapps/ztz/plugin/lodop/install_lodop64.exe' target='_self'>执行升级</a>,升级后请重新进入。</font>";
    var strHtmFireFox="<br><br><font color='#FF00FF'>（注意：如曾安装过Lodop旧版附件npActiveXPLugin,请在【工具】->【附加组件】->【扩展】中先卸它）</font>";
    var strHtmChrome="<br><br><font color='#FF00FF'>(如果此前正常，仅因浏览器升级或重安装而出问题，需重新执行以上安装）</font>";
    var LODOP;		
	try{	
	     //=====判断浏览器类型:===============
	     var isIE	 = (navigator.userAgent.indexOf('MSIE')>=0) || (navigator.userAgent.indexOf('Trident')>=0);
	     var is64IE  = isIE && (navigator.userAgent.indexOf('x64')>=0);
	     //=====如果页面有Lodop就直接使用，没有则新建:==========
	     if (oOBJECT!=undefined || oEMBED!=undefined) { 
               	 if (isIE) 
	             LODOP=oOBJECT; 
	         else 
	             LODOP=oEMBED;
	     } else { 
		 if (CreatedOKLodop7766==null){
          	     LODOP=document.createElement("object"); 
		     LODOP.setAttribute("width",0); 
                     LODOP.setAttribute("height",0); 
		     LODOP.setAttribute("style","position:absolute;left:0px;top:-100px;width:0px;height:0px;");  		     
                     if (isIE) LODOP.setAttribute("classid","clsid:2105C259-1E0C-4534-8141-A753534CB4CA"); 
		     else LODOP.setAttribute("type","application/x-print-lodop");
		     document.documentElement.appendChild(LODOP); 
	             CreatedOKLodop7766=LODOP;		     
 	         } else 
                     LODOP=CreatedOKLodop7766;
	     };
	     //=====判断Lodop插件是否安装过，没有安装或版本过低就提示下载安装:==========
	     if ((LODOP==null)||(typeof(LODOP.VERSION)=="undefined")) {
	             if (navigator.userAgent.indexOf('Chrome')>=0)
	            	 Ext.MessageBox.alert("提示",strHtmChrome,function(){});
	                 //document.documentElement.innerHTML=strHtmChrome+document.documentElement.innerHTML;
	             	
	             if (navigator.userAgent.indexOf('Firefox')>=0)
	            	 Ext.MessageBox.alert("提示",strHtmFireFox,function(){});
	                 //document.documentElement.innerHTML=strHtmFireFox+document.documentElement.innerHTML;
	             
	             if (is64IE) 
	            	 Ext.MessageBox.alert("提示",strHtm64_Install,function(){});
	            	 //document.write(strHtm64_Install); 
	             else
	            	 if (isIE)   
	            		 Ext.MessageBox.alert("提示",strHtmInstall,function(){});
	            		 //document.write(strHtmInstall);    
	            	 else
	            		 Ext.MessageBox.alert("提示",strHtmInstall,function(){});
	            		 //document.documentElement.innerHTML=strHtmInstall+document.documentElement.innerHTML;
	             return LODOP;
	     } else 
	     if (LODOP.VERSION<"6.1.9.3") {
	            // if (is64IE) document.write(strHtm64_Update); else
	            // if (isIE) document.write(strHtmUpdate); else
	             //document.documentElement.innerHTML=strHtmUpdate+document.documentElement.innerHTML;
	             //Ext.MessageBox.alert("提示",strHtmUpdate,function(){});
	    	     return LODOP;
	     };
	     //=====如下空白位置适合调用统一功能(如注册码、语言选择等):====	     
		//LODOP.SET_LICENSES("","394101451001069811011355115108","","");
	     //============================================================	     
	     return LODOP; 
	} catch(err) {
	     if (is64IE){	
            //document.documentElement.innerHTML="Error:"+strHtm64_Install+document.documentElement.innerHTML;
	     	Ext.MessageBox.alert("错误",strHtm64_Install,function(){});
	     }else{
            //document.documentElement.innerHTML="Error:"+strHtmInstall+document.documentElement.innerHTML;
            Ext.MessageBox.alert("错误",strHtmInstall,function(){});
	     }
	     
	     return LODOP; 
	};
}*/




var CreatedOKLodop7766=null;

//====判断是否需要安装CLodop云打印服务器:====
function needCLodop(){
  try{
	var ua=navigator.userAgent;
	if (ua.match(/Windows\sPhone/i) !=null) return true;
	if (ua.match(/iPhone|iPod/i) != null) return true;
	if (ua.match(/Android/i) != null) return true;
	if (ua.match(/Edge\D?\d+/i) != null) return true;
	
	var verTrident=ua.match(/Trident\D?\d+/i);
	var verIE=ua.match(/MSIE\D?\d+/i);
	var verOPR=ua.match(/OPR\D?\d+/i);
	var verFF=ua.match(/Firefox\D?\d+/i);
	var x64=ua.match(/x64/i);
	if ((verTrident==null)&&(verIE==null)&&(x64!==null)) 
		return true; else
	if ( verFF !== null) {
		verFF = verFF[0].match(/\d+/);
		if ((verFF[0]>= 42)||(x64!==null)) return true;
	} else 
	if ( verOPR !== null) {
		verOPR = verOPR[0].match(/\d+/);
		if ( verOPR[0] >= 32 ) return true;
	} else 
	if ((verTrident==null)&&(verIE==null)) {
		var verChrome=ua.match(/Chrome\D?\d+/i);		
		if ( verChrome !== null ) {
			verChrome = verChrome[0].match(/\d+/);
			if (verChrome[0]>=42) return true;
		};
	};
      return false;
  } catch(err) {return true;};
};

//====页面引用CLodop云打印必须的JS文件：====
if (needCLodop()) {
	var head = document.head || document.getElementsByTagName("head")[0] || document.documentElement;
	var oscript = document.createElement("script");
	oscript.src ="http://localhost:8000/CLodopfuncs.js?priority=1";
	head.insertBefore( oscript,head.firstChild );

	//引用双端口(8000和18000）避免其中某个被占用：
	oscript = document.createElement("script");
	oscript.src ="http://localhost:18000/CLodopfuncs.js?priority=0";
	head.insertBefore( oscript,head.firstChild );
};
//====获取LODOP对象的主过程：====
function getLodop(oOBJECT,oEMBED){
     var strHtmInstall="<br><font color='#FF00FF'>打印控件未安装!点击这里<a href='http://113.10.155.131/install_lodop32.zip' target='_self'>执行安装</a>,安装后请刷新页面或重新进入。</font>";
    var strHtmUpdate="<br><font color='#FF00FF'>打印控件需要升级!点击这里<a href='http://113.10.155.131/install_lodop32.zip' target='_self'>执行升级</a>,升级后请重新进入。</font>";
    var strHtm64_Install="<br><font color='#FF00FF'>打印控件未安装!点击这里<a href='http://113.10.155.131/install_lodop64.zip' target='_self'>执行安装</a>,安装后请刷新页面或重新进入。</font>";
    var strHtm64_Update="<br><font color='#FF00FF'>打印控件需要升级!点击这里<a href='http://113.10.155.131/install_lodop64.zip' target='_self'>执行升级</a>,升级后请重新进入。</font>";
    var strHtmFireFox="<br><br><font color='#FF00FF'>（注意：如曾安装过Lodop旧版附件npActiveXPLugin,请在【工具】->【附加组件】->【扩展】中先卸它）</font>";
    var strHtmChrome="<br><br><font color='#FF00FF'>(如果此前正常，仅因浏览器升级或重安装而出问题，需重新执行以上安装）</font>";
    var strCLodopInstall="<br><font color='#FF00FF'>CLodop云打印服务(localhost本地)未安装启动!点击这里<a href='http://192.168.1.232:8123/ztz/plugin/lodop/CLodop_Setup.zip' target='_self'>执行安装</a>,安装后请刷新页面。</font>";
    var strCLodopUpdate="<br><font color='#FF00FF'>CLodop云打印服务需升级!点击这里<a href='http://192.168.1.232:8123/ztz/plugin/lodop/CLodop_Setup.zip' target='_self'>执行升级</a>,升级后请刷新页面。</font>";
    var LODOP;
    try{
        var isIE = (navigator.userAgent.indexOf('MSIE')>=0) || (navigator.userAgent.indexOf('Trident')>=0);
        if (needCLodop()) {
            try{ 
            	LODOP=getCLodop();
            } catch(err) {};
		    if (!LODOP && document.readyState!=="complete") {
		    	alert("C-Lodop没准备好，请稍后再试！"); 
		    	return;
		    };
            if (!LODOP) {
            	if (isIE) document.write(strCLodopInstall); 
            	else
            		document.documentElement.innerHTML=strCLodopInstall+document.documentElement.innerHTML;
            	return;
            } else {
	        	if (CLODOP.CVERSION<"2.0.9.0") { 
	        		if (isIE) document.write(strCLodopUpdate); 
	        		else
	        			document.documentElement.innerHTML=strCLodopUpdate+document.documentElement.innerHTML;
	        	};
	        	if (oEMBED && oEMBED.parentNode) oEMBED.parentNode.removeChild(oEMBED);
	        	if (oOBJECT && oOBJECT.parentNode) oOBJECT.parentNode.removeChild(oOBJECT);	
            };
        } else {
            var is64IE  = isIE && (navigator.userAgent.indexOf('x64')>=0);
            //=====如果页面有Lodop就直接使用，没有则新建:==========
            if (oOBJECT!=undefined || oEMBED!=undefined) {
                if (isIE) LODOP=oOBJECT; else  LODOP=oEMBED;
            } else if (CreatedOKLodop7766==null){
                LODOP=document.createElement("object");
                LODOP.setAttribute("width",0);
                LODOP.setAttribute("height",0);
                LODOP.setAttribute("style","position:absolute;left:0px;top:-100px;width:0px;height:0px;");
                if (isIE) LODOP.setAttribute("classid","clsid:2105C259-1E0C-4534-8141-A753534CB4CA");
                else LODOP.setAttribute("type","application/x-print-lodop");
                document.documentElement.appendChild(LODOP);
                CreatedOKLodop7766=LODOP;
             } else LODOP=CreatedOKLodop7766;
            //=====Lodop插件未安装时提示下载地址:==========
            if ((LODOP==null)||(typeof(LODOP.VERSION)=="undefined")) {
                 if (navigator.userAgent.indexOf('Chrome')>=0)
                     document.documentElement.innerHTML=strHtmChrome+document.documentElement.innerHTML;
                 if (navigator.userAgent.indexOf('Firefox')>=0)
                     document.documentElement.innerHTML=strHtmFireFox+document.documentElement.innerHTML;
                 if (is64IE) document.write(strHtm64_Install); else
                 if (isIE)   document.write(strHtmInstall);    else
                     document.documentElement.innerHTML=strHtmInstall+document.documentElement.innerHTML;
                 return LODOP;
            };
        };
        if (LODOP.VERSION<"6.2.1.5") {
            if (needCLodop())
            document.documentElement.innerHTML=strCLodopUpdate+document.documentElement.innerHTML; else
            if (is64IE) document.write(strHtm64_Update); else
            if (isIE) document.write(strHtmUpdate); else
            document.documentElement.innerHTML=strHtmUpdate+document.documentElement.innerHTML;
            return LODOP;
        };
        //===如下空白位置适合调用统一功能(如注册语句、语言选择等):===
        LODOP.SET_LICENSES("","13528A153BAEE3A0254B9507DCDE2839","","");
        //===========================================================
        return LODOP;
    } catch(err) {alert("getLodop出错:"+err);};
};


