/*
SQLyog Ultimate
MySQL - 5.7.24-log : Database - oms
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`oms` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `oms`;

/*Table structure for table `address` */

DROP TABLE IF EXISTS `address`;

CREATE TABLE `address` (
  `id` int(255) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `receiveProvince` varchar(255) DEFAULT NULL COMMENT '省份',
  `receiveCity` varchar(255) DEFAULT NULL COMMENT '市',
  `receiveCounty` varchar(255) DEFAULT NULL COMMENT '区县',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `annotation` */

DROP TABLE IF EXISTS `annotation`;

CREATE TABLE `annotation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `jobFormId` varchar(250) NOT NULL COMMENT '编号',
  `datatime` datetime DEFAULT NULL COMMENT '时间',
  `userId` int(15) DEFAULT NULL COMMENT '用户ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `annotation_sku` */

DROP TABLE IF EXISTS `annotation_sku`;

CREATE TABLE `annotation_sku` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `jobFormId` varchar(250) NOT NULL COMMENT '编号',
  `mailno` varchar(20) NOT NULL COMMENT '物流单号',
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `be_accessory` */

DROP TABLE IF EXISTS `be_accessory`;

CREATE TABLE `be_accessory` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `itemname` varchar(50) DEFAULT NULL,
  `resfilename` varchar(50) DEFAULT NULL,
  `tarfilename` varchar(50) DEFAULT NULL,
  `lzdid` int(11) DEFAULT NULL,
  `createtime` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `BEACCESSORY_FK_LZDID` (`lzdid`) USING BTREE,
  CONSTRAINT `be_accessory_ibfk_1` FOREIGN KEY (`lzdid`) REFERENCES `be_lzd` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `be_category` */

DROP TABLE IF EXISTS `be_category`;

CREATE TABLE `be_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `categoryName` varchar(20) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `value` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `be_hc` */

DROP TABLE IF EXISTS `be_hc`;

CREATE TABLE `be_hc` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `pm` varchar(50) DEFAULT NULL COMMENT '品名',
  `ddbh` varchar(50) DEFAULT NULL COMMENT '订单编号',
  `ydbh` varchar(50) DEFAULT NULL COMMENT '运单编号',
  `spjs` int(11) DEFAULT NULL COMMENT '商品件数',
  `sjr` varchar(10) DEFAULT NULL COMMENT '收件人',
  `qpm` varchar(20) DEFAULT NULL COMMENT '气泡膜',
  `qzd` varchar(20) DEFAULT NULL COMMENT '气柱袋',
  `qzddj` varchar(20) DEFAULT NULL COMMENT '气柱袋单价',
  `zxgg` varchar(20) DEFAULT NULL COMMENT '纸箱规格',
  `zxggdj` varchar(20) DEFAULT NULL COMMENT '纸箱规格单价',
  `drsj` datetime DEFAULT NULL COMMENT '导入时间',
  `pramid` int(11) DEFAULT NULL COMMENT 'be_hz的id',
  `hj` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `hc_hz_id` (`pramid`),
  CONSTRAINT `be_hc_ibfk_1` FOREIGN KEY (`pramid`) REFERENCES `be_hz` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `be_hz` */

DROP TABLE IF EXISTS `be_hz`;

CREATE TABLE `be_hz` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tydh` varchar(50) DEFAULT NULL COMMENT '提/运单号',
  `sj` varchar(50) DEFAULT NULL COMMENT '时间',
  `sf` varchar(20) DEFAULT NULL COMMENT '税费',
  `qgf` varchar(20) DEFAULT NULL COMMENT '清单费',
  `hcf` varchar(20) DEFAULT NULL COMMENT '耗材费',
  `yf` varchar(20) DEFAULT NULL COMMENT '运费',
  `pttsf` varchar(20) DEFAULT NULL,
  `zt` varchar(100) DEFAULT NULL COMMENT '状态',
  `fdh` varchar(50) DEFAULT NULL,
  `ywbh` varchar(20) DEFAULT NULL,
  `js` int(11) DEFAULT '0',
  `tj` varchar(20) DEFAULT NULL,
  `bz` varchar(100) DEFAULT NULL,
  `mz` varchar(20) DEFAULT NULL,
  `sj2` varchar(20) DEFAULT NULL COMMENT '商家',
  `jjzl` varchar(20) DEFAULT NULL,
  `bh` varchar(20) DEFAULT NULL,
  `wtrq` varchar(50) DEFAULT '0000-00-00 00:00:00',
  `mdg` varchar(20) DEFAULT NULL,
  `hj` varchar(20) DEFAULT NULL,
  `tpcc` varchar(50) DEFAULT NULL COMMENT '托盘尺寸',
  `ds` varchar(20) DEFAULT NULL COMMENT '单数',
  `bsflag` int(11) DEFAULT NULL COMMENT '保税结算flag 为1就是保税结算',
  `kssj` varchar(50) DEFAULT NULL COMMENT '保税开始时间',
  `jssj` varchar(50) DEFAULT NULL COMMENT '保税结束时间 ',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `be_lzd` */

DROP TABLE IF EXISTS `be_lzd`;

CREATE TABLE `be_lzd` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `khmc` varchar(50) DEFAULT NULL COMMENT '客户名称',
  `ddka` varchar(50) DEFAULT NULL COMMENT '到达口岸',
  `tdh` varchar(50) DEFAULT NULL COMMENT '提单号',
  `js` int(11) DEFAULT NULL COMMENT '件数',
  `ds` int(11) DEFAULT NULL COMMENT '单数',
  `tdsj` varchar(50) DEFAULT NULL COMMENT '托单时间',
  `qysj` varchar(50) DEFAULT NULL COMMENT '起运时间',
  `sjdhsj` varchar(50) DEFAULT NULL COMMENT '实际到货时间',
  `zgsj` varchar(50) DEFAULT NULL COMMENT '转关时间',
  `qgsj` varchar(50) DEFAULT NULL COMMENT '清关时间',
  `wjsj` varchar(50) DEFAULT NULL,
  `flag` int(11) NOT NULL COMMENT '1->正在执行中 0->已完结',
  `fjmc` varchar(500) DEFAULT NULL COMMENT '附件',
  `qdbglj` varchar(100) DEFAULT NULL COMMENT '清单',
  `lhbglj` varchar(100) DEFAULT NULL COMMENT '领货报告',
  `chbglj` varchar(100) DEFAULT NULL COMMENT '出货报告',
  `fjlj` varchar(100) DEFAULT NULL,
  `qdbgmc` varchar(50) DEFAULT NULL,
  `lhbgmc` varchar(50) DEFAULT NULL,
  `chbgmc` varchar(50) DEFAULT NULL,
  `zl` varchar(20) DEFAULT NULL COMMENT '重量',
  `tpcc` varchar(50) DEFAULT NULL COMMENT '托盘尺寸',
  `clxx` varchar(50) DEFAULT NULL COMMENT '车辆信息',
  `ywbh` varchar(50) DEFAULT NULL,
  `bgbgmc` varchar(500) DEFAULT NULL,
  `fdh` varchar(50) DEFAULT NULL,
  `chsj` varchar(50) DEFAULT NULL,
  `createTime` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `be_pttsf` */

DROP TABLE IF EXISTS `be_pttsf`;

CREATE TABLE `be_pttsf` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `khddh` varchar(50) DEFAULT NULL COMMENT '客户订单号',
  `sjr` varchar(50) DEFAULT NULL COMMENT '收件人',
  `spsl` int(11) DEFAULT NULL COMMENT '商品数量',
  `spzj` varchar(20) DEFAULT NULL COMMENT '商品总价',
  `spmc` varchar(100) DEFAULT NULL COMMENT '商品名称',
  `kddh` varchar(50) DEFAULT NULL COMMENT '快递单号',
  `ptsjsyf` varchar(20) DEFAULT NULL COMMENT '平台数据使用费',
  `sbf` varchar(20) DEFAULT NULL COMMENT '申报费',
  `drsj` varchar(50) DEFAULT NULL COMMENT '导入时间',
  `pramid` int(11) DEFAULT NULL COMMENT 'be_hz的id',
  PRIMARY KEY (`id`),
  KEY `pttsf_hz_id` (`pramid`),
  CONSTRAINT `be_pttsf_ibfk_1` FOREIGN KEY (`pramid`) REFERENCES `be_hz` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `be_qgf` */

DROP TABLE IF EXISTS `be_qgf`;

CREATE TABLE `be_qgf` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `rq` datetime DEFAULT NULL COMMENT '日期',
  `ydh` varchar(50) DEFAULT NULL COMMENT '运单号',
  `tpsl` varchar(10) DEFAULT NULL COMMENT '托盘数量',
  `hdf` varchar(20) DEFAULT NULL COMMENT '换单费',
  `fbf` varchar(20) DEFAULT NULL COMMENT '分驳费',
  `jgcf` varchar(20) DEFAULT NULL COMMENT '监管车费',
  `bgbjf` varchar(20) DEFAULT NULL COMMENT '报关报检费',
  `zf` varchar(20) DEFAULT NULL COMMENT '杂费',
  `bb` varchar(20) DEFAULT NULL COMMENT 'B报',
  `jyjyf` varchar(20) DEFAULT NULL COMMENT '检验检疫费',
  `bgf` varchar(20) DEFAULT NULL COMMENT '报关费',
  `ldf` varchar(20) DEFAULT NULL COMMENT '联单费',
  `baf` varchar(20) DEFAULT NULL COMMENT '备案费',
  `hff` varchar(20) DEFAULT NULL COMMENT '核放费',
  `cyf` varchar(20) DEFAULT NULL COMMENT '查验费',
  `hj` varchar(20) DEFAULT NULL COMMENT '合计',
  `czsj` datetime DEFAULT NULL COMMENT '操作时间',
  `pramid` int(11) NOT NULL COMMENT 'be_hz的id',
  `hdfSm` varchar(50) DEFAULT NULL,
  `fbfSm` varchar(50) DEFAULT NULL,
  `jgcfSm` varchar(50) DEFAULT NULL,
  `bgbjfSm` varchar(50) DEFAULT NULL,
  `zfSm` varchar(50) DEFAULT NULL,
  `bbSm` varchar(50) DEFAULT NULL,
  `jyjyfSm` varchar(50) DEFAULT NULL,
  `bgfSm` varchar(50) DEFAULT NULL,
  `ldfSm` varchar(50) DEFAULT NULL,
  `bafSm` varchar(50) DEFAULT NULL,
  `hffSm` varchar(50) DEFAULT NULL,
  `cyfSm` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `qdf_hz_id` (`pramid`),
  CONSTRAINT `be_qgf_ibfk_1` FOREIGN KEY (`pramid`) REFERENCES `be_hz` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `be_sf` */

DROP TABLE IF EXISTS `be_sf`;

CREATE TABLE `be_sf` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `xh` int(11) DEFAULT NULL COMMENT '序号',
  `sbdh` varchar(50) DEFAULT NULL COMMENT '申报单号',
  `sbsj` datetime DEFAULT NULL COMMENT '申报时间',
  `fydh` varchar(50) DEFAULT NULL COMMENT '分运单号',
  `sdbh` varchar(50) DEFAULT NULL COMMENT '税单编号',
  `fxsj` datetime DEFAULT NULL COMMENT '放行时间',
  `zfrzjh` varchar(50) DEFAULT NULL COMMENT '支付人证件号',
  `zfrxm` varchar(50) DEFAULT NULL COMMENT '支付人姓名',
  `zzs` varchar(20) DEFAULT NULL COMMENT '增值税',
  `dsptmc` varchar(100) DEFAULT NULL COMMENT '电商平台名称',
  `dsqymc` varchar(100) DEFAULT NULL COMMENT '电商企业名称',
  `ddh` varchar(50) DEFAULT NULL COMMENT '订单号',
  `sjbm` varchar(50) DEFAULT NULL COMMENT '商家编码',
  `drsj` datetime DEFAULT NULL COMMENT '导入时间',
  `pramid` int(11) NOT NULL COMMENT '汇总be_hz的id字段',
  `sbje` varchar(20) DEFAULT NULL,
  `bscjsj` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `sf_hz_pramid` (`pramid`),
  CONSTRAINT `be_sf_ibfk_1` FOREIGN KEY (`pramid`) REFERENCES `be_hz` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `be_upload` */

DROP TABLE IF EXISTS `be_upload`;

CREATE TABLE `be_upload` (
  `id` varchar(10) NOT NULL DEFAULT '',
  `name` varchar(20) DEFAULT NULL,
  `address` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `be_yf` */

DROP TABLE IF EXISTS `be_yf`;

CREATE TABLE `be_yf` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `xh` int(11) DEFAULT NULL COMMENT '序号',
  `jjkh` varchar(50) DEFAULT NULL COMMENT '寄件客户',
  `jjrq` date DEFAULT NULL COMMENT '寄件日期',
  `ydbh` varchar(50) DEFAULT NULL COMMENT '运单编号',
  `mdd` varchar(50) DEFAULT NULL COMMENT '目的地',
  `sjzl` varchar(20) DEFAULT NULL COMMENT '实际重量',
  `jfzl` varchar(20) DEFAULT NULL COMMENT '计费重量',
  `ysyf` varchar(20) DEFAULT NULL COMMENT '应收运费',
  `bz` varchar(50) DEFAULT NULL COMMENT '备注',
  `xz` varchar(20) DEFAULT NULL COMMENT '续重',
  `drsj` varchar(50) DEFAULT NULL COMMENT '导入时间',
  `pramid` int(11) DEFAULT NULL COMMENT 'be_hz的id',
  PRIMARY KEY (`id`),
  KEY `yf_hz_id` (`pramid`),
  CONSTRAINT `be_yf_ibfk_1` FOREIGN KEY (`pramid`) REFERENCES `be_hz` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `bond` */

DROP TABLE IF EXISTS `bond`;

CREATE TABLE `bond` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `bondMoney` varchar(200) DEFAULT NULL COMMENT '保证金',
  `companybm` varchar(200) DEFAULT NULL COMMENT '公司编码',
  `companyName` varchar(200) DEFAULT NULL COMMENT '公司名称',
  `userId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `brand` */

DROP TABLE IF EXISTS `brand`;

CREATE TABLE `brand` (
  `id` int(255) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `brand_id` varchar(255) DEFAULT NULL COMMENT '商品分类   对应分类表',
  `brand_name` varchar(255) DEFAULT NULL COMMENT '商品品牌   对应品牌表',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `calendar` */

DROP TABLE IF EXISTS `calendar`;

CREATE TABLE `calendar` (
  `datelist` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `cat` */

DROP TABLE IF EXISTS `cat`;

CREATE TABLE `cat` (
  `id` int(255) NOT NULL,
  `cat_id` varchar(255) DEFAULT NULL,
  `cat_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `changeodd` */

DROP TABLE IF EXISTS `changeodd`;

CREATE TABLE `changeodd` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `orderNumber` varchar(50) DEFAULT NULL COMMENT '订单编号',
  `expressCode` varchar(50) DEFAULT NULL COMMENT '快递公司编码',
  `expressNumber` varchar(50) NOT NULL COMMENT '快递单号',
  `buyerNickName` varchar(50) DEFAULT NULL COMMENT '昵称（买家）',
  `buyerName` varchar(50) DEFAULT NULL COMMENT '真实姓名（买家）',
  `buyerIdCard` varchar(50) DEFAULT NULL COMMENT '身份证号码（买家）',
  `buyerProvince` varchar(50) DEFAULT NULL COMMENT '省（买家）',
  `buyerCity` varchar(50) DEFAULT NULL COMMENT '市（买家）',
  `buyerArea` varchar(50) DEFAULT NULL COMMENT '区县（买家）',
  `buyerAddress` varchar(200) DEFAULT NULL COMMENT '详细地址（买家）',
  `buyerPostCode` varchar(50) DEFAULT NULL COMMENT '邮政编码（买家）',
  `buyerCountry` varchar(50) DEFAULT NULL COMMENT '国家（买家）',
  `buyerTel` varchar(50) DEFAULT NULL COMMENT '联系电话（买家）',
  `printType` int(1) DEFAULT '0' COMMENT '打印状态：0未打印、1已打印',
  `zyName` varchar(50) DEFAULT NULL COMMENT '转运名称',
  `zyNumber` varchar(50) DEFAULT NULL COMMENT '转运号码',
  `sender` varchar(50) DEFAULT NULL COMMENT '寄件人姓名',
  `senderTel` varchar(50) DEFAULT NULL COMMENT '寄件人电话',
  `senderAddress` varchar(200) DEFAULT NULL COMMENT '寄件人地址',
  `isDeleted` int(1) DEFAULT '0' COMMENT '删除状态：0、未删除1、已删除',
  `userId` int(11) DEFAULT NULL COMMENT '用户ID',
  `newexpressNumber` varchar(50) DEFAULT NULL COMMENT '换(新)单号',
  `nweight` double(30,2) DEFAULT NULL COMMENT '货物重量',
  `newnum` int(50) DEFAULT NULL COMMENT '总件数',
  `pnname` varchar(255) DEFAULT NULL COMMENT '品名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `companforexpress` */

DROP TABLE IF EXISTS `companforexpress`;

CREATE TABLE `companforexpress` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bm` varchar(10) DEFAULT NULL COMMENT '快递编码',
  `logisticsCode` varchar(30) DEFAULT NULL COMMENT '备案编码',
  `logisticsName` varchar(100) DEFAULT NULL COMMENT '快递名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `company` */

DROP TABLE IF EXISTS `company`;

CREATE TABLE `company` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `companytszt` varchar(250) DEFAULT NULL COMMENT '推送主体编码',
  `companybm` varchar(255) DEFAULT NULL COMMENT '公司编码',
  `companyName` varchar(250) DEFAULT NULL COMMENT '电商平台名称',
  `companyCode` varchar(255) DEFAULT NULL COMMENT '电商平台代码',
  `payCompanyCode` varchar(20) DEFAULT NULL COMMENT '支付公司编码',
  `eCommerceName` varchar(255) DEFAULT NULL COMMENT '电商企业名称',
  `eCommerceCode` varchar(255) DEFAULT NULL COMMENT '电商企业编码',
  `logisCompanyName` varchar(255) DEFAULT NULL COMMENT '物流企业名称',
  `logisCompanyCode` varchar(250) DEFAULT NULL COMMENT '物流企业编号',
  `declareCompanyName` varchar(250) DEFAULT NULL COMMENT '申报企业名称',
  `declareCompanyCode` varchar(250) DEFAULT NULL COMMENT '申报企业代码',
  `applicationFormNo` varchar(250) DEFAULT NULL COMMENT '申请单编号',
  `internalAreaCompanyNo` varchar(250) DEFAULT NULL COMMENT '区内企业代码',
  `internalAreaCompanyName` varchar(250) DEFAULT NULL COMMENT '区内企业名称',
  `assureCode` varchar(250) DEFAULT NULL COMMENT '担保企业编号',
  `customsField` varchar(250) DEFAULT NULL COMMENT '监管场所代码',
  `declPort` varchar(250) DEFAULT NULL COMMENT '申报地海关代码 ',
  `accountBookNo` varchar(20) DEFAULT NULL COMMENT '账册编号',
  `country` varchar(3) DEFAULT NULL COMMENT '起运国',
  `sendName` varchar(50) DEFAULT NULL COMMENT '发件人',
  `sendTel` varchar(20) DEFAULT NULL COMMENT '发件人电话',
  `sendAddress` varchar(250) DEFAULT NULL COMMENT '发件人地址',
  `userId` int(255) DEFAULT NULL COMMENT '用户ID',
  `isauto` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `company_yf` */

DROP TABLE IF EXISTS `company_yf`;

CREATE TABLE `company_yf` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `companytszt` varchar(250) DEFAULT NULL COMMENT '推送主体编码',
  `companybm` varchar(255) DEFAULT NULL COMMENT '公司编码',
  `companyName` varchar(250) DEFAULT NULL COMMENT '电商平台名称',
  `companyCode` varchar(255) DEFAULT NULL COMMENT '电商平台代码',
  `payCompanyCode` varchar(20) DEFAULT NULL COMMENT '支付公司编码',
  `eCommerceName` varchar(255) DEFAULT NULL COMMENT '电商企业名称',
  `eCommerceCode` varchar(255) DEFAULT NULL COMMENT '电商企业编码',
  `logisCompanyName` varchar(255) DEFAULT NULL COMMENT '物流企业名称',
  `logisCompanyCode` varchar(250) DEFAULT NULL COMMENT '物流企业编号',
  `declareCompanyName` varchar(250) DEFAULT NULL COMMENT '申报企业名称',
  `declareCompanyCode` varchar(250) DEFAULT NULL COMMENT '申报企业代码',
  `applicationFormNo` varchar(250) DEFAULT NULL COMMENT '申请单编号',
  `internalAreaCompanyNo` varchar(250) DEFAULT NULL COMMENT '区内企业代码',
  `internalAreaCompanyName` varchar(250) DEFAULT NULL COMMENT '区内企业名称',
  `assureCode` varchar(250) DEFAULT NULL COMMENT '担保企业编号',
  `customsField` varchar(250) DEFAULT NULL COMMENT '监管场所代码',
  `declPort` varchar(250) DEFAULT NULL COMMENT '申报地海关代码 ',
  `accountBookNo` varchar(20) DEFAULT NULL COMMENT '账册编号',
  `country` varchar(3) DEFAULT NULL COMMENT '起运国',
  `sendName` varchar(50) DEFAULT NULL COMMENT '发件人',
  `sendTel` varchar(20) DEFAULT NULL COMMENT '发件人电话',
  `sendAddress` varchar(250) DEFAULT NULL COMMENT '发件人地址',
  `userId` int(255) DEFAULT NULL COMMENT '用户ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `companyforout` */

DROP TABLE IF EXISTS `companyforout`;

CREATE TABLE `companyforout` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `itemCode` varchar(255) DEFAULT '' COMMENT '商家编码',
  `itemName` varchar(255) DEFAULT '' COMMENT '商家名称',
  `companyCode` varchar(255) DEFAULT '' COMMENT '企业备案编号',
  `companyName` varchar(255) DEFAULT '' COMMENT '企业名称',
  `shippingName` varchar(255) DEFAULT '' COMMENT '物流名称',
  `payName` varchar(255) DEFAULT '' COMMENT '支付方式名称',
  `ownerCode` varchar(255) DEFAULT '' COMMENT '发货单位编码',
  `ownerName` varchar(255) DEFAULT '' COMMENT '发货单位名称',
  `agentCode` varchar(255) DEFAULT '' COMMENT '经营单位编码',
  `agentName` varchar(255) DEFAULT '' COMMENT '经营单位名称',
  `customsCode` varchar(255) DEFAULT '' COMMENT '申报关区',
  `logisCompanyName` varchar(255) DEFAULT '' COMMENT '物流公司名称',
  `logisCompanyCode` varchar(255) DEFAULT '' COMMENT '物流公司备案编号',
  `internalAreaCompanyNo` varchar(255) DEFAULT '' COMMENT '区内企业备案编号',
  `internalAreaCompanyName` varchar(255) DEFAULT '' COMMENT '区内企业备案名称',
  `declareCode` varchar(255) DEFAULT NULL COMMENT '申报单位编码',
  `declareName` varchar(255) DEFAULT NULL COMMENT '申报单位',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `companyforzy` */

DROP TABLE IF EXISTS `companyforzy`;

CREATE TABLE `companyforzy` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `companybm` varchar(255) DEFAULT NULL COMMENT '公司编码',
  `companytszt` varchar(250) DEFAULT NULL COMMENT '推送编码',
  `companyName` varchar(250) DEFAULT NULL COMMENT '电商平台名称',
  `companyCode` varchar(255) DEFAULT NULL COMMENT '电商平台代码',
  `payCompanyCode` varchar(20) DEFAULT NULL COMMENT '支付公司编码',
  `accountBookNo` varchar(20) DEFAULT NULL COMMENT '支付',
  `eCommerceName` varchar(255) DEFAULT NULL COMMENT '电商企业名称',
  `eCommerceCode` varchar(255) DEFAULT NULL COMMENT '电商企业编码',
  `logisCompanyName` varchar(255) DEFAULT NULL COMMENT '物流企业名称',
  `logisCompanyCode` varchar(250) DEFAULT NULL COMMENT '物流企业编号',
  `declareCompanyName` varchar(250) DEFAULT NULL COMMENT '申报企业名称',
  `declareCompanyCode` varchar(250) DEFAULT NULL COMMENT '申报企业代码',
  `applicationFormNo` varchar(250) DEFAULT NULL COMMENT '申请单编号',
  `internalAreaCompanyNo` varchar(250) DEFAULT NULL COMMENT '区内企业代码',
  `internalAreaCompanyName` varchar(250) DEFAULT NULL COMMENT '区内企业名称',
  `assureCode` varchar(250) DEFAULT NULL COMMENT '担保企业编号',
  `customsField` varchar(250) DEFAULT NULL COMMENT '监管场所代码',
  `declPort` varchar(250) DEFAULT NULL COMMENT '申报地海关代码 ',
  `country` varchar(3) DEFAULT NULL COMMENT '起运国',
  `sendName` varchar(50) DEFAULT NULL COMMENT '发件人',
  `sendTel` varchar(20) DEFAULT NULL COMMENT '发件人电话',
  `sendAddress` varchar(250) DEFAULT NULL COMMENT '发件人地址',
  `userId` int(255) DEFAULT NULL COMMENT '用户ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `companyforzy_yf` */

DROP TABLE IF EXISTS `companyforzy_yf`;

CREATE TABLE `companyforzy_yf` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `companybm` varchar(255) DEFAULT NULL COMMENT '公司编码',
  `companytszt` varchar(250) DEFAULT NULL COMMENT '推送编码',
  `companyName` varchar(250) DEFAULT NULL COMMENT '电商平台名称',
  `companyCode` varchar(255) DEFAULT NULL COMMENT '电商平台代码',
  `payCompanyCode` varchar(20) DEFAULT NULL COMMENT '支付公司编码',
  `accountBookNo` varchar(20) DEFAULT NULL COMMENT '支付',
  `eCommerceName` varchar(255) DEFAULT NULL COMMENT '电商企业名称',
  `eCommerceCode` varchar(255) DEFAULT NULL COMMENT '电商企业编码',
  `logisCompanyName` varchar(255) DEFAULT NULL COMMENT '物流企业名称',
  `logisCompanyCode` varchar(250) DEFAULT NULL COMMENT '物流企业编号',
  `declareCompanyName` varchar(250) DEFAULT NULL COMMENT '申报企业名称',
  `declareCompanyCode` varchar(250) DEFAULT NULL COMMENT '申报企业代码',
  `applicationFormNo` varchar(250) DEFAULT NULL COMMENT '申请单编号',
  `internalAreaCompanyNo` varchar(250) DEFAULT NULL COMMENT '区内企业代码',
  `internalAreaCompanyName` varchar(250) DEFAULT NULL COMMENT '区内企业名称',
  `assureCode` varchar(250) DEFAULT NULL COMMENT '担保企业编号',
  `customsField` varchar(250) DEFAULT NULL COMMENT '监管场所代码',
  `declPort` varchar(250) DEFAULT NULL COMMENT '申报地海关代码 ',
  `country` varchar(3) DEFAULT NULL COMMENT '起运国',
  `sendName` varchar(50) DEFAULT NULL COMMENT '发件人',
  `sendTel` varchar(20) DEFAULT NULL COMMENT '发件人电话',
  `sendAddress` varchar(250) DEFAULT NULL COMMENT '发件人地址',
  `userId` int(255) DEFAULT NULL COMMENT '用户ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `cost` */

DROP TABLE IF EXISTS `cost`;

CREATE TABLE `cost` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `companyName` varchar(250) DEFAULT NULL COMMENT '公司名称',
  `name` varchar(250) DEFAULT NULL COMMENT '管理人',
  `attn` varchar(250) DEFAULT NULL,
  `commissionDate` date DEFAULT NULL COMMENT '委托时间',
  `totanumber` varchar(200) DEFAULT NULL COMMENT '总运单号',
  `purpose` varchar(200) DEFAULT NULL COMMENT '始发站/目的港',
  `number` int(50) DEFAULT NULL COMMENT '件数',
  `volume` double(50,3) DEFAULT NULL COMMENT '体积',
  `serviceNumber` varchar(200) DEFAULT NULL COMMENT '业务编号',
  `branchMailNo` varchar(200) DEFAULT NULL COMMENT '分运单号',
  `fightNumber` varchar(255) DEFAULT NULL COMMENT '航班号',
  `itemWeight` double(50,3) DEFAULT NULL COMMENT '计价重量',
  `remark` varchar(250) DEFAULT NULL COMMENT '备注',
  `userId` int(11) DEFAULT NULL,
  `isTj` int(1) DEFAULT '0',
  `totalAmount` double(50,2) DEFAULT NULL COMMENT '总金额',
  `isPayment` int(1) DEFAULT '0' COMMENT '0待支付，1已支付',
  `totalTax` double(50,2) DEFAULT NULL COMMENT '总税率',
  `surplusFunds` double(50,2) DEFAULT NULL COMMENT '剩余资金',
  `alreadyUsedFunds` double(50,2) DEFAULT NULL COMMENT '已用资金',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `cost_sku` */

DROP TABLE IF EXISTS `cost_sku`;

CREATE TABLE `cost_sku` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `costId` int(11) DEFAULT NULL,
  `freightPrice` double(200,3) DEFAULT NULL COMMENT '运费单价',
  `freightNumber` double(50,3) DEFAULT NULL COMMENT '运费数量',
  `freightMoney` double(50,3) DEFAULT NULL COMMENT '运费金额',
  `freightRemark` varchar(200) DEFAULT NULL COMMENT '运费备注',
  `operationPrice` double(50,3) DEFAULT NULL COMMENT '操作单价',
  `operationNumber` int(50) DEFAULT NULL COMMENT '操作数量',
  `operationMoney` double(50,3) DEFAULT NULL COMMENT '操作金额',
  `operationRemark` varchar(200) DEFAULT NULL COMMENT '操作费备注',
  `groundPrice` double(50,3) DEFAULT NULL COMMENT '地面清关单价',
  `groundNumber` double(50,3) DEFAULT NULL COMMENT '地面清关数量',
  `groundMoney` double(50,3) DEFAULT NULL COMMENT '地面清关金额',
  `groundRemark` varchar(200) DEFAULT NULL COMMENT '地面清关备注',
  `taxPrice` double(50,3) DEFAULT NULL COMMENT '税单价',
  `taxNumber` double(50,3) DEFAULT NULL COMMENT '税数量',
  `taxMoney` double(50,3) DEFAULT NULL COMMENT '税金额',
  `taxRemark` varchar(200) DEFAULT NULL COMMENT '税备注',
  `platformPrice` double(50,3) DEFAULT NULL COMMENT '平台数据单价',
  `platformNumber` double(50,3) DEFAULT NULL COMMENT '平台数据数量',
  `platformMoney` double(50,3) DEFAULT NULL COMMENT '平台数据费金额',
  `platformRemark` varchar(200) DEFAULT NULL COMMENT '平台数据备注',
  `total` double(50,3) DEFAULT NULL COMMENT '合计',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `cs` */

DROP TABLE IF EXISTS `cs`;

CREATE TABLE `cs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `time` datetime DEFAULT NULL,
  `ydh` varchar(20) DEFAULT NULL,
  `zl` varchar(20) DEFAULT NULL,
  `mdd` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4926775 DEFAULT CHARSET=utf8;

/*Table structure for table `cz_godownentry` */

DROP TABLE IF EXISTS `cz_godownentry`;

CREATE TABLE `cz_godownentry` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `totalMailNo` varchar(255) DEFAULT NULL COMMENT '总运单号',
  `pc` varchar(250) DEFAULT NULL COMMENT '批次',
  `username` varchar(255) DEFAULT NULL COMMENT '创建人',
  `createdatatime` datetime DEFAULT NULL COMMENT '创建时间',
  `userid` varchar(255) DEFAULT NULL COMMENT '用户id',
  `sum` int(1) DEFAULT NULL COMMENT '扫描总数量',
  `success` int(1) DEFAULT NULL COMMENT '成功数量',
  `fail` int(1) DEFAULT NULL COMMENT '失败数量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=248 DEFAULT CHARSET=utf8;

/*Table structure for table `cz_godownentry_check` */

DROP TABLE IF EXISTS `cz_godownentry_check`;

CREATE TABLE `cz_godownentry_check` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `totalMailNo` varchar(255) DEFAULT NULL COMMENT '总运单号',
  `pc` varchar(250) DEFAULT NULL COMMENT '批次',
  `username` varchar(255) DEFAULT NULL COMMENT '创建人',
  `createdatatime` datetime DEFAULT NULL COMMENT '创建时间',
  `userid` varchar(255) DEFAULT NULL COMMENT '用户id',
  `sum` int(1) DEFAULT NULL COMMENT '扫描总数量',
  `success` int(1) DEFAULT NULL COMMENT '成功数量',
  `fail` int(1) DEFAULT NULL COMMENT '失败数量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=187 DEFAULT CHARSET=utf8;

/*Table structure for table `cz_godownentry_check_sku` */

DROP TABLE IF EXISTS `cz_godownentry_check_sku`;

CREATE TABLE `cz_godownentry_check_sku` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `billcode` varchar(255) DEFAULT NULL COMMENT '分运单号',
  `status` varchar(250) DEFAULT NULL COMMENT '状态',
  `mailStatus` int(1) DEFAULT NULL COMMENT '直邮表状态(1、放行2、布控3、申报失败)',
  `totalMailNo` varchar(100) DEFAULT NULL COMMENT '总运单号',
  `pc` int(11) DEFAULT NULL COMMENT '批次',
  `createdatatime` datetime DEFAULT NULL COMMENT '创建时间',
  `inflag` varchar(250) DEFAULT NULL COMMENT '出库标记',
  `outflag` varchar(250) DEFAULT NULL COMMENT '出库标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=52668 DEFAULT CHARSET=utf8;

/*Table structure for table `cz_godownentry_sku` */

DROP TABLE IF EXISTS `cz_godownentry_sku`;

CREATE TABLE `cz_godownentry_sku` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `billcode` varchar(255) DEFAULT NULL COMMENT '分运单号',
  `status` varchar(250) DEFAULT NULL COMMENT '状态',
  `totalMailNo` varchar(100) DEFAULT NULL COMMENT '总运单号',
  `pc` varchar(50) DEFAULT NULL COMMENT '批次',
  `totalid` int(11) DEFAULT NULL COMMENT 'cz_godownEntry的id',
  `createdatatime` datetime DEFAULT NULL COMMENT '创建时间',
  `inflag` varchar(250) DEFAULT NULL COMMENT '进库标记',
  `outflag` varchar(250) DEFAULT NULL COMMENT '出库标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=45563 DEFAULT CHARSET=utf8;

/*Table structure for table `cz_nuclearrelease` */

DROP TABLE IF EXISTS `cz_nuclearrelease`;

CREATE TABLE `cz_nuclearrelease` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `totalMailNo` varchar(255) DEFAULT NULL COMMENT '总运单号',
  `pc` varchar(250) DEFAULT NULL COMMENT '批次',
  `username` varchar(255) DEFAULT NULL COMMENT '创建人',
  `createdatatime` date DEFAULT NULL COMMENT '创建时间',
  `userid` varchar(255) DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `cz_nuclearrelease_sku` */

DROP TABLE IF EXISTS `cz_nuclearrelease_sku`;

CREATE TABLE `cz_nuclearrelease_sku` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `billcode` varchar(255) DEFAULT NULL COMMENT '分运单号',
  `status` varchar(250) DEFAULT NULL COMMENT '状态',
  `totalMailNo` varchar(100) DEFAULT NULL COMMENT '总运单号',
  `totalid` int(11) DEFAULT NULL COMMENT 'cz_godownEntry的id',
  `createdatatime` date DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `cz_ordercheck` */

DROP TABLE IF EXISTS `cz_ordercheck`;

CREATE TABLE `cz_ordercheck` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `totalMailNo` varchar(255) DEFAULT NULL COMMENT '总运单号',
  `pc` varchar(250) DEFAULT NULL COMMENT '批次',
  `username` varchar(255) DEFAULT NULL COMMENT '创建人',
  `createdatatime` date DEFAULT NULL COMMENT '创建时间',
  `userid` varchar(255) DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `cz_ordercheck_sku` */

DROP TABLE IF EXISTS `cz_ordercheck_sku`;

CREATE TABLE `cz_ordercheck_sku` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `billcode` varchar(255) DEFAULT NULL COMMENT '分运单号',
  `status` varchar(250) DEFAULT NULL COMMENT '状态',
  `totalMailNo` varchar(100) DEFAULT NULL COMMENT '总运单号',
  `totalid` int(11) DEFAULT NULL COMMENT 'cz_godownEntry的id',
  `createdatatime` date DEFAULT NULL COMMENT '创建时间',
  `weight` double(10,3) DEFAULT '0.000' COMMENT '重量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `end_company_toka` */

DROP TABLE IF EXISTS `end_company_toka`;

CREATE TABLE `end_company_toka` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `companyid` varchar(255) DEFAULT NULL,
  `customsCode` varchar(255) DEFAULT NULL,
  `company` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `end_header_bg` */

DROP TABLE IF EXISTS `end_header_bg`;

CREATE TABLE `end_header_bg` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ordercode` varchar(255) DEFAULT NULL,
  `netWeight` varchar(255) DEFAULT NULL,
  `grossWeight` varchar(255) DEFAULT NULL,
  `goodsName` varchar(255) DEFAULT NULL,
  `sendArea` varchar(255) DEFAULT NULL,
  `consignee` varchar(255) DEFAULT NULL,
  `consigneeTel` varchar(255) DEFAULT NULL,
  `worth` varchar(255) DEFAULT NULL,
  `billcode` varchar(255) DEFAULT NULL,
  `packNo` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `creatdatatime` datetime DEFAULT NULL,
  `flag` varchar(255) DEFAULT NULL,
  `consigneeAddress` varchar(255) DEFAULT NULL,
  `consigneeArea` varchar(255) DEFAULT NULL,
  `bg` varchar(50) DEFAULT NULL,
  `tovipdpt` varchar(50) DEFAULT NULL,
  `totalWayBill` varchar(50) DEFAULT NULL COMMENT '总运单号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `end_waybill_pool_test` */

DROP TABLE IF EXISTS `end_waybill_pool_test`;

CREATE TABLE `end_waybill_pool_test` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `company` varchar(255) DEFAULT NULL,
  `head` varchar(255) DEFAULT NULL,
  `begin` varchar(255) DEFAULT NULL,
  `end` varchar(255) DEFAULT NULL,
  `sum` int(25) DEFAULT NULL,
  `createdatetime` datetime DEFAULT NULL,
  `qj` varchar(255) DEFAULT NULL,
  `allhead` varchar(255) DEFAULT NULL,
  `allend` varchar(255) DEFAULT NULL,
  `startDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `express_options` */

DROP TABLE IF EXISTS `express_options`;

CREATE TABLE `express_options` (
  `optionName` varchar(255) DEFAULT NULL,
  `optionValue` varchar(2555) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `goods_changeodd` */

DROP TABLE IF EXISTS `goods_changeodd`;

CREATE TABLE `goods_changeodd` (
  `id` int(255) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `changeoId` int(255) DEFAULT NULL COMMENT 'change表id',
  `expressNumber` varchar(255) DEFAULT NULL COMMENT '快递单号',
  `orderNumber` varchar(255) DEFAULT NULL COMMENT '订单号',
  `pnname` varchar(255) DEFAULT NULL COMMENT '货物名称',
  `nweight` varchar(255) DEFAULT NULL COMMENT '货物重量',
  `newnum` int(255) DEFAULT NULL COMMENT '货物数量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8362 DEFAULT CHARSET=utf8;

/*Table structure for table `indentstate` */

DROP TABLE IF EXISTS `indentstate`;

CREATE TABLE `indentstate` (
  `id` int(255) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `indentId` varchar(255) DEFAULT NULL COMMENT '订单号',
  `date` varchar(255) DEFAULT NULL COMMENT '时间',
  `tx` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Table structure for table `item` */

DROP TABLE IF EXISTS `item`;

CREATE TABLE `item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `internalNumber` int(50) DEFAULT NULL COMMENT '内部编号',
  `itemSKU` varchar(50) DEFAULT NULL COMMENT '商品编码',
  `itemName` varchar(200) DEFAULT NULL COMMENT '商品名称',
  `itemSpec` varchar(50) DEFAULT NULL COMMENT '规格',
  `unitWeight` double(12,4) DEFAULT NULL COMMENT '重量',
  `unitDesc` varchar(50) DEFAULT '' COMMENT '单位',
  `companyCode` varchar(50) DEFAULT NULL COMMENT '商家编码',
  `hscode` varchar(50) DEFAULT NULL COMMENT '海关备案编码',
  `oneUnitDesc` varchar(50) DEFAULT '' COMMENT '第一计量单位',
  `twoUnitDesc` varchar(50) DEFAULT '' COMMENT '第二计量单位',
  `country` varchar(50) DEFAULT NULL COMMENT '原产国',
  `countryOfOrigin` varchar(50) DEFAULT NULL COMMENT '商品原产地代码',
  `xz` varchar(50) DEFAULT NULL COMMENT '料件性质',
  `xh` varchar(50) DEFAULT NULL COMMENT '账册项号',
  `productRecordNo` varchar(200) DEFAULT NULL COMMENT '产品国检备案编号',
  `listPrice` decimal(10,0) DEFAULT NULL COMMENT '标价/售价',
  `costPrice` decimal(10,0) DEFAULT NULL COMMENT '成本价',
  `createDateTime` datetime DEFAULT NULL,
  `vendorItemCode` varchar(50) DEFAULT NULL COMMENT '厂商货号',
  `remark` varchar(200) DEFAULT NULL COMMENT '商品备注',
  `userId` int(50) DEFAULT NULL,
  `taxRate` double(20,4) DEFAULT NULL COMMENT '税率',
  `firstCount` double(10,4) DEFAULT NULL COMMENT '第一数量',
  `secondCount` double(10,4) DEFAULT NULL COMMENT '第二数量',
  `itemcode` varchar(50) DEFAULT NULL,
  `wmsType` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_ITEM_COMPANY` (`itemSKU`,`companyCode`) USING BTREE,
  KEY `IDX_ITEM_ITEMCODE` (`itemSKU`) USING BTREE,
  KEY `IDX_ITEM_COMPANYCODE` (`companyCode`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=35508 DEFAULT CHARSET=utf8;

/*Table structure for table `item_copy` */

DROP TABLE IF EXISTS `item_copy`;

CREATE TABLE `item_copy` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `internalNumber` int(50) DEFAULT NULL COMMENT '内部编号',
  `itemSKU` varchar(50) DEFAULT NULL COMMENT '商品编码',
  `itemName` varchar(200) DEFAULT NULL COMMENT '商品名称',
  `itemSpec` varchar(50) DEFAULT NULL COMMENT '规格',
  `unitWeight` double(12,4) DEFAULT NULL COMMENT '重量',
  `unitDesc` varchar(50) DEFAULT '' COMMENT '单位',
  `companyCode` varchar(50) DEFAULT NULL COMMENT '商家编码',
  `hscode` varchar(50) DEFAULT NULL COMMENT '海关备案编码',
  `oneUnitDesc` varchar(50) DEFAULT '' COMMENT '第一计量单位',
  `twoUnitDesc` varchar(50) DEFAULT '' COMMENT '第二计量单位',
  `country` varchar(50) DEFAULT NULL COMMENT '原产国',
  `countryOfOrigin` varchar(50) DEFAULT NULL COMMENT '商品原产地代码',
  `xz` varchar(50) DEFAULT NULL COMMENT '料件性质',
  `xh` varchar(50) DEFAULT NULL COMMENT '账册项号',
  `productRecordNo` varchar(20) DEFAULT NULL COMMENT '产品国检备案编号',
  `listPrice` decimal(10,0) DEFAULT NULL COMMENT '标价/售价',
  `costPrice` decimal(10,0) DEFAULT NULL COMMENT '成本价',
  `createDateTime` datetime DEFAULT NULL,
  `vendorItemCode` varchar(50) DEFAULT NULL COMMENT '厂商货号',
  `remark` varchar(200) DEFAULT NULL COMMENT '商品备注',
  `userId` int(50) DEFAULT NULL,
  `taxRate` double(20,4) DEFAULT NULL COMMENT '税率',
  `firstCount` int(10) DEFAULT NULL COMMENT '第一数量',
  `secondCount` int(10) DEFAULT NULL COMMENT '第二数量',
  `itemcode` varchar(50) DEFAULT NULL,
  `wmsType` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_ITEM_COMPANY` (`itemSKU`,`companyCode`) USING BTREE,
  KEY `IDX_ITEM_ITEMCODE` (`itemSKU`) USING BTREE,
  KEY `IDX_ITEM_COMPANYCODE` (`companyCode`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=18851 DEFAULT CHARSET=utf8;

/*Table structure for table `item_forck` */

DROP TABLE IF EXISTS `item_forck`;

CREATE TABLE `item_forck` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `genre` varchar(255) DEFAULT NULL COMMENT '商品分类',
  `itemSKU` varchar(50) DEFAULT NULL COMMENT '商品编码',
  `itemName` varchar(200) DEFAULT NULL COMMENT '商品名称',
  `itemSpec` varchar(50) DEFAULT NULL COMMENT '规格',
  `unitWeight` double(12,4) DEFAULT NULL COMMENT '重量',
  `unitDesc` varchar(50) DEFAULT NULL COMMENT '单位',
  `companyCode` varchar(50) DEFAULT NULL COMMENT '商家编码',
  `excise` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '消费税率',
  `vat` decimal(10,2) DEFAULT '0.00' COMMENT '增值税率',
  `hscode` varchar(50) DEFAULT NULL COMMENT '海关备案编码',
  `oneUnitDesc` varchar(50) DEFAULT '' COMMENT '第一计量单位',
  `twoUnitDesc` varchar(50) DEFAULT '' COMMENT '第二计量单位',
  `country` varchar(50) DEFAULT NULL COMMENT '原产国',
  `countryOfOrigin` varchar(50) DEFAULT NULL COMMENT '商品原产地代码',
  `listPrice` decimal(10,0) DEFAULT NULL COMMENT '标价/售价',
  `costPrice` decimal(10,0) DEFAULT NULL COMMENT '成本价',
  `createDateTime` datetime DEFAULT NULL,
  `vendorItemCode` varchar(50) DEFAULT NULL COMMENT '厂商货号',
  `remark` varchar(200) DEFAULT NULL COMMENT '商品备注',
  `xz` varchar(50) DEFAULT NULL COMMENT '料件性质',
  `xh` varchar(50) DEFAULT NULL COMMENT '账册项号',
  `userId` int(50) DEFAULT NULL,
  `internalNumber` int(50) DEFAULT NULL COMMENT '内部编号',
  `taxRate` double(20,4) DEFAULT NULL COMMENT '税率',
  `firstCount` int(10) DEFAULT '1' COMMENT '第一数量',
  `secondCount` int(10) DEFAULT NULL COMMENT '第二数量',
  `productRecordNo` varchar(20) DEFAULT '12' COMMENT '产品国检备案编号',
  PRIMARY KEY (`id`),
  KEY `IDX_ITEM_COMPANY` (`itemSKU`,`companyCode`) USING BTREE,
  KEY `IDX_ITEM_ITEMCODE` (`itemSKU`) USING BTREE,
  KEY `IDX_ITEM_COMPANYCODE` (`companyCode`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=32727 DEFAULT CHARSET=utf8;

/*Table structure for table `item_forsj` */

DROP TABLE IF EXISTS `item_forsj`;

CREATE TABLE `item_forsj` (
  `id` int(255) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `goods_sn` varchar(255) DEFAULT NULL COMMENT '商品货号',
  `goods_name` varchar(255) DEFAULT NULL COMMENT '商品名称',
  `codeTs` varchar(255) DEFAULT NULL COMMENT 'hs编码',
  `tradeCurr` varchar(255) DEFAULT NULL COMMENT '成交币制',
  `goodsUnit` varchar(255) DEFAULT NULL COMMENT '申报计量单位',
  `firstUnit` varchar(255) DEFAULT NULL COMMENT '第一单位',
  `secondUnit` varchar(255) DEFAULT NULL COMMENT '第二单位（非必填）',
  `goodsModel` varchar(255) DEFAULT NULL COMMENT '商品规格型号',
  `cat_id` varchar(255) DEFAULT NULL COMMENT '商品分类   对应分类表',
  `brand_id` varchar(255) DEFAULT NULL COMMENT '商品品牌   对应品牌表',
  `shop_price` varchar(20) DEFAULT NULL COMMENT '本店售价',
  `goods_desc` varchar(255) DEFAULT NULL COMMENT '商品描述',
  `goods_thumb` varchar(255) DEFAULT NULL COMMENT '商品图片',
  `goods_number` varchar(100) DEFAULT NULL COMMENT '商品库存',
  `is_on_sale` int(1) DEFAULT NULL COMMENT '上架状态，1上架0不上架',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `item_forzy` */

DROP TABLE IF EXISTS `item_forzy`;

CREATE TABLE `item_forzy` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `genre` varchar(255) DEFAULT NULL COMMENT '商品分类',
  `itemSKU` varchar(50) DEFAULT NULL COMMENT '商品编码',
  `itemName` varchar(200) DEFAULT NULL COMMENT '商品名称',
  `itemSpec` varchar(50) DEFAULT NULL COMMENT '规格',
  `unitWeight` double(12,4) DEFAULT NULL COMMENT '重量',
  `unitDesc` varchar(50) DEFAULT NULL COMMENT '单位',
  `companyCode` varchar(50) DEFAULT NULL COMMENT '商家编码',
  `excise` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '消费税率',
  `vat` decimal(10,2) DEFAULT '0.00' COMMENT '增值税率',
  `hscode` varchar(50) DEFAULT NULL COMMENT '海关备案编码',
  `oneUnitDesc` varchar(50) DEFAULT '' COMMENT '第一计量单位',
  `twoUnitDesc` varchar(50) DEFAULT '' COMMENT '第二计量单位',
  `country` varchar(50) DEFAULT NULL COMMENT '原产国',
  `countryOfOrigin` varchar(50) DEFAULT NULL COMMENT '商品原产地代码',
  `listPrice` decimal(10,0) DEFAULT NULL COMMENT '标价/售价',
  `costPrice` decimal(10,0) DEFAULT NULL COMMENT '成本价',
  `createDateTime` datetime DEFAULT NULL,
  `vendorItemCode` varchar(50) DEFAULT NULL COMMENT '厂商货号',
  `remark` varchar(200) DEFAULT NULL COMMENT '商品备注',
  `xz` varchar(50) DEFAULT NULL COMMENT '料件性质',
  `xh` varchar(50) DEFAULT NULL COMMENT '账册项号',
  `userId` int(50) DEFAULT NULL,
  `internalNumber` int(50) DEFAULT NULL COMMENT '内部编号',
  `taxRate` double(20,4) DEFAULT NULL COMMENT '税率',
  `firstCount` int(10) DEFAULT '1' COMMENT '第一数量',
  `secondCount` int(10) DEFAULT NULL COMMENT '第二数量',
  `productRecordNo` varchar(20) DEFAULT '12' COMMENT '产品国检备案编号',
  PRIMARY KEY (`id`),
  KEY `IDX_ITEM_COMPANY` (`itemSKU`,`companyCode`) USING BTREE,
  KEY `IDX_ITEM_ITEMCODE` (`itemSKU`) USING BTREE,
  KEY `IDX_ITEM_COMPANYCODE` (`companyCode`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=44562 DEFAULT CHARSET=utf8;

/*Table structure for table `log_file` */

DROP TABLE IF EXISTS `log_file`;

CREATE TABLE `log_file` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `residualMargin` double(200,2) DEFAULT NULL COMMENT '剩余保证金',
  `usedMargin` double(200,2) DEFAULT NULL COMMENT '已用保证金',
  `bondMoney` double(200,2) DEFAULT NULL COMMENT '保证金',
  `companybm` varchar(50) DEFAULT NULL COMMENT '公司编码',
  `companyName` varchar(50) DEFAULT NULL COMMENT '公司名称',
  `userId` int(11) DEFAULT NULL,
  `frostMamey` varchar(255) DEFAULT NULL COMMENT '冻结',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=105 DEFAULT CHARSET=utf8;

/*Table structure for table `log_filesku` */

DROP TABLE IF EXISTS `log_filesku`;

CREATE TABLE `log_filesku` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `companybm` varchar(255) DEFAULT NULL,
  `creatTime` varchar(255) DEFAULT NULL,
  `txLogisticID` varchar(255) DEFAULT NULL,
  `mailNo` varchar(255) DEFAULT NULL,
  `bondManey` varchar(255) DEFAULT NULL,
  `payMamey` varchar(255) DEFAULT NULL,
  `syMamey` varchar(255) DEFAULT NULL,
  `upMamey` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=77241 DEFAULT CHARSET=utf8;

/*Table structure for table `log_inventory` */

DROP TABLE IF EXISTS `log_inventory`;

CREATE TABLE `log_inventory` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `txLogisticID` varchar(255) DEFAULT NULL COMMENT '客户订单号',
  `itemsku` varchar(250) DEFAULT NULL COMMENT 'sku',
  `itemName` varchar(255) DEFAULT NULL,
  `itemCount` int(50) DEFAULT NULL COMMENT '数量',
  `Stocklot` varchar(50) DEFAULT '' COMMENT '库存批次',
  `hz` varchar(20) DEFAULT NULL COMMENT '货主',
  `motion` varchar(20) DEFAULT '' COMMENT '分配库存：0，还原库存：1',
  `createDate` datetime DEFAULT NULL,
  `type` varchar(20) DEFAULT NULL COMMENT '状态',
  `remark` varchar(400) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=821003 DEFAULT CHARSET=utf8;

/*Table structure for table `log_inventory_action` */

DROP TABLE IF EXISTS `log_inventory_action`;

CREATE TABLE `log_inventory_action` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `itemsku` varchar(255) DEFAULT NULL COMMENT '物品SKU',
  `action` varchar(255) DEFAULT NULL COMMENT '状态',
  `reason` varchar(255) DEFAULT NULL COMMENT '原因',
  `number` int(10) DEFAULT NULL COMMENT '数量',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `userId` int(10) DEFAULT NULL COMMENT '用户ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `log_wms_inout` */

DROP TABLE IF EXISTS `log_wms_inout`;

CREATE TABLE `log_wms_inout` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) DEFAULT NULL COMMENT '操作人id',
  `content` text COMMENT '操作内容',
  `createTime` datetime DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `stojs` */

DROP TABLE IF EXISTS `stojs`;

CREATE TABLE `stojs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `company` varchar(100) DEFAULT NULL COMMENT '商家',
  `number` varchar(10) DEFAULT NULL COMMENT '编号',
  `qdzt` varchar(1) DEFAULT NULL COMMENT '清单状态默认0 未导入 ，1 已导入',
  `bbzt` varchar(1) DEFAULT NULL COMMENT '报表状态 默认0 未生成，1已生成',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `stojs_b` */

DROP TABLE IF EXISTS `stojs_b`;

CREATE TABLE `stojs_b` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Pra` varchar(100) DEFAULT NULL,
  `AD` varchar(100) DEFAULT NULL,
  `AW` varchar(100) DEFAULT NULL,
  `AM` varchar(100) DEFAULT NULL,
  `BD` varchar(100) DEFAULT NULL,
  `BW` varchar(100) DEFAULT NULL,
  `BM` varchar(100) DEFAULT NULL,
  `CD` varchar(100) DEFAULT NULL,
  `CW` varchar(100) DEFAULT NULL,
  `CM` varchar(100) DEFAULT NULL,
  `DD` varchar(100) DEFAULT NULL,
  `DW` varchar(100) DEFAULT NULL,
  `DM` varchar(100) DEFAULT NULL,
  `AMGG` varchar(100) DEFAULT NULL,
  `BMGG` varchar(100) DEFAULT NULL,
  `CMGG` varchar(100) DEFAULT NULL,
  `DMGG` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `stojs_bb` */

DROP TABLE IF EXISTS `stojs_bb`;

CREATE TABLE `stojs_bb` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT NULL COMMENT 'stojs的id',
  `Province` varchar(50) DEFAULT NULL COMMENT '省',
  `line1dl` int(20) DEFAULT NULL COMMENT '0-0.5kg 单量',
  `line1weight` varchar(20) DEFAULT NULL COMMENT '0-0.5重量',
  `line2dl` int(20) DEFAULT NULL COMMENT '0.5-3单量',
  `line2weight` varchar(20) DEFAULT NULL COMMENT '0.5-3重量',
  `line3dl` int(20) DEFAULT NULL COMMENT '3-单量',
  `line3weight` varchar(20) DEFAULT NULL COMMENT '3以上重量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `stojs_qd` */

DROP TABLE IF EXISTS `stojs_qd`;

CREATE TABLE `stojs_qd` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT NULL COMMENT 'stosj的id',
  `txLogisticID` varchar(30) DEFAULT NULL COMMENT '客户订单号',
  `mailNo` varchar(30) DEFAULT NULL COMMENT '运单号',
  `weight` varchar(10) DEFAULT NULL COMMENT '重量',
  `worth` varchar(20) DEFAULT NULL COMMENT '金额',
  `Province` varchar(50) DEFAULT NULL COMMENT '省',
  `City` varchar(50) DEFAULT NULL COMMENT '市',
  `Address` varchar(200) DEFAULT NULL COMMENT '地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `stojs_qd_copy` */

DROP TABLE IF EXISTS `stojs_qd_copy`;

CREATE TABLE `stojs_qd_copy` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT NULL COMMENT 'stosj的id',
  `txLogisticID` varchar(30) DEFAULT NULL COMMENT '客户订单号',
  `mailNo` varchar(30) DEFAULT NULL COMMENT '运单号',
  `weight` varchar(10) DEFAULT NULL COMMENT '重量',
  `worth` varchar(20) DEFAULT NULL COMMENT '金额',
  `Province` varchar(50) DEFAULT NULL COMMENT '省',
  `City` varchar(50) DEFAULT NULL COMMENT '市',
  `Address` varchar(200) DEFAULT NULL COMMENT '地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `szqg_agreement` */

DROP TABLE IF EXISTS `szqg_agreement`;

CREATE TABLE `szqg_agreement` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` varchar(20) DEFAULT NULL COMMENT '网关版本',
  `appid` varchar(32) DEFAULT NULL COMMENT '提交时间',
  `personPriKey` varchar(1000) DEFAULT NULL COMMENT 'personPriKey',
  `merchantId` varchar(50) DEFAULT NULL COMMENT '商户ID',
  `merchantName` varchar(50) DEFAULT NULL COMMENT '商户名称',
  `commitTime` datetime DEFAULT NULL COMMENT '提交时间',
  `lb` varchar(20) DEFAULT NULL COMMENT '11',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;

/*Table structure for table `t_autochecklist` */

DROP TABLE IF EXISTS `t_autochecklist`;

CREATE TABLE `t_autochecklist` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `companybm` varchar(255) DEFAULT NULL COMMENT '公司编码',
  `companyName` varchar(255) DEFAULT NULL COMMENT '公司名称',
  `autoGainOrder` int(11) DEFAULT '0' COMMENT '自动捞单 默认为0 为关闭；1  为开启    ',
  `orderPreview` int(11) DEFAULT '0' COMMENT '订单预审',
  `downPlatform` int(11) DEFAULT '0' COMMENT '下发平台',
  `getStream` int(11) DEFAULT '0' COMMENT '获取流水',
  `grabNumbers` int(11) DEFAULT '0' COMMENT '抓取单号',
  `orderSubmitted` int(11) DEFAULT '0' COMMENT '订单报送',
  `logisticsSubmitted` int(11) DEFAULT '0' COMMENT '物流报送',
  `listSubmitted` int(11) DEFAULT '0' COMMENT '清单报送',
  `downWarehouse` int(11) DEFAULT '0' COMMENT '下发仓库',
  `automaticCompletion` int(11) DEFAULT '0' COMMENT '自动完结',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `t_buyer_limit` */

DROP TABLE IF EXISTS `t_buyer_limit`;

CREATE TABLE `t_buyer_limit` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `nameId` varchar(20) DEFAULT NULL,
  `totalUsed` double(10,3) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `t_countorder` */

DROP TABLE IF EXISTS `t_countorder`;

CREATE TABLE `t_countorder` (
  `id` int(255) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `startDate` varchar(255) DEFAULT NULL COMMENT '清关日期',
  `businessNo` varchar(255) DEFAULT NULL COMMENT '业务编号',
  `allmailNo` varchar(255) DEFAULT NULL COMMENT '总运单号',
  `mailNo` varchar(255) DEFAULT NULL COMMENT '分运单号',
  `origin` varchar(255) DEFAULT NULL COMMENT '始发站/目的港',
  `flow` varchar(255) DEFAULT NULL COMMENT '航班/船次',
  `sum` varchar(255) DEFAULT NULL COMMENT '件数',
  `weigth` varchar(255) DEFAULT NULL COMMENT '重',
  `bulk` varchar(255) DEFAULT NULL COMMENT '体积',
  `beizu` varchar(255) DEFAULT NULL COMMENT '备注',
  `payDate` varchar(255) DEFAULT NULL COMMENT '支付日期',
  `company` varchar(255) DEFAULT NULL COMMENT '商家',
  `state` varchar(1) NOT NULL COMMENT '是否结算',
  `createData` varchar(255) DEFAULT NULL COMMENT '新增时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `t_countrule` */

DROP TABLE IF EXISTS `t_countrule`;

CREATE TABLE `t_countrule` (
  `id` int(255) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) DEFAULT NULL COMMENT '结算名称',
  `code` varchar(25) DEFAULT NULL COMMENT '结算方式',
  `zgf` varchar(255) DEFAULT NULL COMMENT '转关费',
  `qgf` varchar(255) DEFAULT NULL COMMENT '清关费',
  `czf` varchar(255) DEFAULT NULL COMMENT '操作费',
  `ptf` varchar(255) DEFAULT NULL COMMENT '平台数据分析',
  `tallage` varchar(255) DEFAULT NULL COMMENT '税',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `t_countsku` */

DROP TABLE IF EXISTS `t_countsku`;

CREATE TABLE `t_countsku` (
  `id` int(255) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ruleId` varchar(10) DEFAULT NULL COMMENT '规则id',
  `worthSum` varchar(25) DEFAULT NULL COMMENT '总价值',
  `zgfw` varchar(15) DEFAULT NULL COMMENT '转关费',
  `qgfw` varchar(15) DEFAULT NULL COMMENT '清关费',
  `czfw` varchar(15) DEFAULT NULL COMMENT '操作费',
  `ptfw` varchar(15) DEFAULT NULL COMMENT '平台费',
  `tallagew` varchar(15) DEFAULT NULL COMMENT '税',
  `weigthSum` varchar(15) DEFAULT NULL COMMENT '重',
  `countSum` int(15) DEFAULT NULL COMMENT '单量',
  `allMaillNo` varchar(15) DEFAULT NULL COMMENT '总运单号',
  `zgf` varchar(15) DEFAULT NULL COMMENT '转关费',
  `czf` varchar(15) DEFAULT NULL COMMENT '操作费',
  `qgf` varchar(15) DEFAULT NULL COMMENT '清关费',
  `sui` varchar(15) DEFAULT NULL COMMENT '税',
  `ptf` varchar(15) DEFAULT NULL COMMENT '平台费',
  `zong` varchar(255) DEFAULT NULL COMMENT '总',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `t_customs_deposit` */

DROP TABLE IF EXISTS `t_customs_deposit`;

CREATE TABLE `t_customs_deposit` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `companyName` varchar(255) DEFAULT NULL COMMENT '担保企业名称',
  `companyCode` varchar(255) DEFAULT NULL COMMENT '担保企业编码',
  `totalMoney` varchar(30) DEFAULT NULL COMMENT '总金额',
  `surplusMoney` varchar(30) DEFAULT NULL COMMENT '担保所剩金额',
  `usedMoney` varchar(30) DEFAULT NULL COMMENT '担保已用金额',
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `t_customs_deposit_sku` */

DROP TABLE IF EXISTS `t_customs_deposit_sku`;

CREATE TABLE `t_customs_deposit_sku` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `orderNo` varchar(50) DEFAULT NULL COMMENT '订单号',
  `mailNo` varchar(50) DEFAULT NULL COMMENT '运单号',
  `totalMoney` varchar(30) DEFAULT NULL COMMENT '订单总金额',
  `tax` varchar(30) DEFAULT NULL COMMENT '税费',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `companyCode` varchar(255) DEFAULT NULL COMMENT '担保企业编码',
  PRIMARY KEY (`id`),
  KEY `I_CUSTOMS_DEPOSIT_SKU_ORDERNO` (`orderNo`) USING BTREE,
  KEY `I_CUSTOMS_DEPOSIT_SKU_MAILNO` (`mailNo`) USING BTREE,
  KEY `I_CUSTOMS_DEPOSIT_SKU_COMPANYCODE` (`companyCode`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `t_customs_param` */

DROP TABLE IF EXISTS `t_customs_param`;

CREATE TABLE `t_customs_param` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `hscode` varchar(30) DEFAULT NULL COMMENT '海关备案编号',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `licenceCode` varchar(30) DEFAULT NULL COMMENT '许可证代码',
  `ordinaryRate` varchar(10) DEFAULT NULL COMMENT '普通税率',
  `preferentialRate` varchar(10) DEFAULT NULL COMMENT '优惠税率',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `exportRate` varchar(10) DEFAULT NULL COMMENT '出口税率',
  `consumptionRate` varchar(10) DEFAULT NULL COMMENT '消费税率',
  `valueAddedRate` varchar(10) DEFAULT NULL COMMENT '增值税率',
  `oneUnitDesc` varchar(10) DEFAULT NULL COMMENT '第一法定单位',
  `twoUnitDesc` varchar(10) DEFAULT NULL COMMENT '第二法定单位',
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `t_dict_area` */

DROP TABLE IF EXISTS `t_dict_area`;

CREATE TABLE `t_dict_area` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` int(11) NOT NULL COMMENT '系统自动生成的唯一编号，用于外键关联，如：1001010101',
  `code` varchar(10) DEFAULT NULL COMMENT '行政区划代码',
  `name` varchar(100) NOT NULL COMMENT '地区名称',
  `shortName` varchar(50) DEFAULT NULL COMMENT '简称',
  `telephoneCode` varchar(4) DEFAULT NULL COMMENT '电话区号',
  `postCode` varchar(6) DEFAULT NULL COMMENT '邮政编码',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `sequenceNumber` int(11) DEFAULT NULL,
  `isDeleted` tinyint(4) DEFAULT '0' COMMENT '是否已删除，0：否，1：是',
  `creatorCode` varchar(255) NOT NULL COMMENT '创建人代码',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  `lastEditorCode` varchar(255) DEFAULT NULL COMMENT '最后修改人代码',
  `lastEditTime` datetime DEFAULT NULL COMMENT '最后修改时间',
  `origId` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Number` (`number`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='地区字典表';

/*Table structure for table `t_goods_accept` */

DROP TABLE IF EXISTS `t_goods_accept`;

CREATE TABLE `t_goods_accept` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `orderId` int(11) DEFAULT NULL COMMENT '订单表主键ID',
  `expressNumber` varchar(20) DEFAULT NULL COMMENT '快递单号',
  `goodsName` varchar(250) NOT NULL COMMENT '物品名称',
  `goodsType` varchar(50) DEFAULT NULL COMMENT '物品品名',
  `weight` double DEFAULT NULL COMMENT '重量',
  `price` decimal(15,2) DEFAULT NULL COMMENT '单价',
  `num` int(11) DEFAULT NULL COMMENT '数量',
  `totalPrice` decimal(15,2) DEFAULT NULL COMMENT '总价',
  `goodsSpec` varchar(20) DEFAULT NULL COMMENT '商品规格',
  `goodsUnit` varchar(10) DEFAULT NULL COMMENT '计量单位',
  `goodsDutyNumber` varchar(20) DEFAULT NULL COMMENT '物品行邮税号',
  `goodsHS` varchar(20) DEFAULT NULL COMMENT '物品HS',
  `fromCountryCode` varchar(10) DEFAULT NULL COMMENT '起运国代码',
  `goodsSKU` varchar(20) DEFAULT NULL COMMENT '物品SKU',
  `isDeleted` int(1) DEFAULT '0' COMMENT '删除状态：0、未删除1、已删除',
  `userId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `t_goods_push` */

DROP TABLE IF EXISTS `t_goods_push`;

CREATE TABLE `t_goods_push` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `orderId` int(11) DEFAULT NULL COMMENT '订单表主键ID',
  `expressNumber` varchar(20) DEFAULT NULL COMMENT '快递单号',
  `goodsName` varchar(100) NOT NULL COMMENT '物品名称',
  `goodsType` varchar(50) DEFAULT NULL COMMENT '物品品名',
  `weight` double DEFAULT NULL COMMENT '重量',
  `price` decimal(15,2) DEFAULT NULL COMMENT '单价',
  `num` int(11) DEFAULT NULL COMMENT '数量',
  `totalPrice` decimal(15,2) DEFAULT NULL COMMENT '总价',
  `goodsSpec` varchar(20) DEFAULT NULL COMMENT '商品规格',
  `goodsUnit` varchar(10) DEFAULT NULL COMMENT '计量单位',
  `goodsDutyNumber` varchar(20) DEFAULT NULL COMMENT '物品行邮税号',
  `goodsHS` varchar(20) DEFAULT NULL COMMENT '物品HS',
  `fromCountryCode` varchar(10) DEFAULT NULL COMMENT '起运国代码',
  `goodsSKU` varchar(20) DEFAULT NULL COMMENT '物品SKU',
  `userId` int(11) DEFAULT NULL COMMENT '用户ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `t_idcard` */

DROP TABLE IF EXISTS `t_idcard`;

CREATE TABLE `t_idcard` (
  `id` varchar(250) NOT NULL DEFAULT '' COMMENT '身份证表标识列',
  `userName` varchar(250) DEFAULT NULL COMMENT '姓名',
  `userId` varchar(250) DEFAULT NULL COMMENT '身份证号',
  `verification` int(11) DEFAULT NULL COMMENT '验证情况 0：”验证为通过“   1：“验证已通过”',
  `dateTime` varchar(250) DEFAULT NULL COMMENT '验证时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `t_inventory` */

DROP TABLE IF EXISTS `t_inventory`;

CREATE TABLE `t_inventory` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `itemsku` varchar(250) DEFAULT NULL COMMENT 'sku',
  `itemName` varchar(255) DEFAULT NULL,
  `totality` int(20) DEFAULT '0' COMMENT '总库存数',
  `surplusInventory` int(50) DEFAULT '0' COMMENT '可用库存',
  `usedInventory` int(50) DEFAULT '0' COMMENT '已用库存',
  `preUsedInventory` int(50) DEFAULT '0' COMMENT '占用库存',
  `warningInventory` int(50) DEFAULT '0' COMMENT '预警库存',
  `pc` varchar(250) DEFAULT NULL COMMENT '入库批次',
  `date` datetime DEFAULT NULL COMMENT '入库时间',
  `merchant` varchar(50) DEFAULT NULL COMMENT '商家编码',
  `createDate` datetime DEFAULT NULL COMMENT '创建时间',
  `overduedate` date DEFAULT NULL,
  `userId` int(50) DEFAULT NULL,
  `storage` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `t_inventory_sku` */

DROP TABLE IF EXISTS `t_inventory_sku`;

CREATE TABLE `t_inventory_sku` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `itemsku` varchar(255) DEFAULT NULL COMMENT '商品SKu',
  `itemName` varchar(255) DEFAULT NULL COMMENT '商品名字',
  `pc` varchar(255) DEFAULT NULL COMMENT '入库批次',
  `date` varchar(255) DEFAULT NULL COMMENT '转让时间',
  `merchant` varchar(255) DEFAULT NULL COMMENT '转让商家',
  `merchantx` varchar(255) DEFAULT NULL COMMENT '接受商家',
  `updateInventory` varchar(255) DEFAULT NULL COMMENT '转让库存',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `t_materiel` */

DROP TABLE IF EXISTS `t_materiel`;

CREATE TABLE `t_materiel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `materielsku` varchar(250) DEFAULT NULL COMMENT 'sku',
  `materielName` varchar(250) DEFAULT NULL COMMENT '物料名称',
  `totality` int(20) DEFAULT '0' COMMENT '总库存数',
  `usedInventory` int(50) DEFAULT '0' COMMENT '已用库存',
  `preUsedInventory` int(50) DEFAULT '0' COMMENT '占用库存',
  `surplusInventory` int(50) DEFAULT NULL COMMENT '可用库存',
  `pc` varchar(250) DEFAULT NULL COMMENT '入库批次',
  `date` datetime DEFAULT NULL COMMENT '入库时间',
  `userId` int(11) DEFAULT NULL,
  `merchant` varchar(50) DEFAULT NULL COMMENT '商家编码',
  `createDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `t_order_accept` */

DROP TABLE IF EXISTS `t_order_accept`;

CREATE TABLE `t_order_accept` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `orderNumber` varchar(50) DEFAULT NULL COMMENT '订单编号',
  `expressCode` varchar(50) DEFAULT NULL COMMENT '快递公司编码',
  `expressNumber` varchar(50) NOT NULL COMMENT '快递单号',
  `buyerNickName` varchar(50) DEFAULT NULL COMMENT '昵称（买家）',
  `buyerName` varchar(50) DEFAULT NULL COMMENT '真实姓名（买家）',
  `buyerIdCard` varchar(50) DEFAULT NULL COMMENT '身份证号码（买家）',
  `buyerProvince` varchar(50) DEFAULT NULL COMMENT '省（买家）',
  `buyerCity` varchar(50) DEFAULT NULL COMMENT '市（买家）',
  `buyerArea` varchar(50) DEFAULT NULL COMMENT '区县（买家）',
  `buyerAddress` varchar(200) DEFAULT NULL COMMENT '详细地址（买家）',
  `buyerPostCode` varchar(50) DEFAULT NULL COMMENT '邮政编码（买家）',
  `buyerCountry` varchar(50) DEFAULT NULL COMMENT '国家（买家）',
  `buyerTel` varchar(50) DEFAULT NULL COMMENT '联系电话（买家）',
  `printType` int(1) DEFAULT '0' COMMENT '打印状态：0未打印、1已打印',
  `zyName` varchar(50) DEFAULT NULL COMMENT '转运名称',
  `zyNumber` varchar(50) DEFAULT NULL COMMENT '转运号码',
  `sender` varchar(50) DEFAULT NULL COMMENT '寄件人姓名',
  `senderTel` varchar(50) DEFAULT NULL COMMENT '寄件人电话',
  `senderAddress` varchar(200) DEFAULT NULL COMMENT '寄件人地址',
  `isDeleted` int(1) DEFAULT '0' COMMENT '删除状态：0、未删除1、已删除',
  `userId` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `t_order_batch` */

DROP TABLE IF EXISTS `t_order_batch`;

CREATE TABLE `t_order_batch` (
  `id` int(255) NOT NULL AUTO_INCREMENT COMMENT '主键Id',
  `batchNumber` varchar(200) DEFAULT NULL COMMENT '批次号',
  `leadTime` datetime DEFAULT NULL COMMENT '导入时间',
  `leadOrderNumber` int(200) DEFAULT NULL COMMENT '导入订单数',
  `leadOrderTotal` double(50,3) DEFAULT NULL,
  `leadOrderTax` double(50,3) DEFAULT NULL COMMENT '导入订单税费',
  `isTax` int(1) DEFAULT '0' COMMENT '是否支付税费 0是未支付，1是已支付',
  `userId` int(20) DEFAULT '0',
  `zfTime` datetime DEFAULT NULL COMMENT '支付时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `t_order_bonded` */

DROP TABLE IF EXISTS `t_order_bonded`;

CREATE TABLE `t_order_bonded` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `receiveMan` varchar(250) DEFAULT NULL COMMENT '收件人姓名',
  `receiveManPhone` varchar(250) DEFAULT NULL COMMENT '收件人电话',
  `receiveManAddress` varchar(255) DEFAULT NULL COMMENT '收件人地址',
  `receiveProvince` varchar(255) DEFAULT NULL COMMENT '收件人省',
  `receiveCity` varchar(255) DEFAULT NULL COMMENT '收件市',
  `receiveCounty` varchar(255) DEFAULT NULL COMMENT '收件区县',
  `txLogisticID` varchar(255) DEFAULT NULL COMMENT '客户订单号',
  `itemName` varchar(255) DEFAULT NULL COMMENT '主要商品',
  `itemWeight` double(50,3) DEFAULT NULL COMMENT '重量',
  `itemCount` int(50) DEFAULT NULL COMMENT '总数量',
  `carrier` varchar(50) DEFAULT NULL COMMENT '快递',
  `mailNo` varchar(150) DEFAULT NULL COMMENT '运单号',
  `markDestination` varchar(250) DEFAULT NULL COMMENT '格口',
  `billProvideSiteName` varchar(250) DEFAULT NULL COMMENT '始发中心',
  `billProvideSiteCode` varchar(50) DEFAULT NULL COMMENT '始发编码',
  `worth` double(50,3) DEFAULT NULL COMMENT '总价值',
  `preEntryNumber` varchar(500) DEFAULT NULL COMMENT '预录入编号',
  `tradeCountry` varchar(250) DEFAULT NULL COMMENT '启运国（地区）',
  `buyerIdNumber` varchar(250) DEFAULT NULL COMMENT '订购人证件号码',
  `buyerName` varchar(250) DEFAULT NULL COMMENT '订购人姓名',
  `feeAmount` double(250,3) DEFAULT '0.000' COMMENT '运费',
  `insureAmount` double(250,3) DEFAULT '0.000' COMMENT '保费',
  `payCompany` varchar(250) DEFAULT '' COMMENT '支付公司编码',
  `payNumber` varchar(250) DEFAULT '' COMMENT '支付流水号',
  `ordercode` varchar(250) DEFAULT '' COMMENT '平台订单号',
  `pc` varchar(20) DEFAULT NULL COMMENT '备注',
  `isPushToWms` int(1) DEFAULT '0' COMMENT '是否已推送到WMS(0、未推送1、已推送)',
  `ispost` int(1) DEFAULT '0' COMMENT '推送网趣平台',
  `isCustoms` int(1) DEFAULT '0' COMMENT '是否报关',
  `auditstatus` int(1) DEFAULT '0',
  `userId` int(10) DEFAULT NULL,
  `sendWarehouse` varchar(50) DEFAULT NULL COMMENT '发件仓库',
  `merchantNum` varchar(50) DEFAULT NULL COMMENT '商家编码',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `vipts` varchar(2) DEFAULT NULL COMMENT 'STOvip客户推送',
  `isPushDd` int(1) DEFAULT '0' COMMENT '推送订单(0、未推送1、已推送)',
  `isPushQd` int(1) DEFAULT '0' COMMENT '推送清单(0、未推送1、已推送)',
  `tj_sj` varchar(255) DEFAULT NULL COMMENT '统计税金',
  `status_hc` varchar(255) DEFAULT NULL COMMENT '状态回传标志',
  `customsTax` varchar(20) DEFAULT '0' COMMENT '海关税',
  `valueAddedTax` varchar(20) DEFAULT '' COMMENT '增值税',
  `consumptionTax` varchar(20) DEFAULT '' COMMENT '消费税',
  `invtNo` varchar(50) DEFAULT '',
  `returncode` int(1) DEFAULT '0' COMMENT '回执状态 0空着,1不接受申报，3查验，9放行',
  `returnInfo` varchar(2000) DEFAULT NULL COMMENT '回执内容',
  `kucstate` int(1) DEFAULT NULL COMMENT '库存状态',
  `mark` int(1) DEFAULT NULL,
  `disbursed` int(1) DEFAULT '0' COMMENT '扣除担保金标示 1为已扣除',
  `ebpCode` varchar(50) DEFAULT '',
  `ebcCode` varchar(50) DEFAULT '',
  `ebpName` varchar(60) DEFAULT '',
  `ebcName` varchar(60) DEFAULT '',
  `payName` varchar(60) DEFAULT '',
  `logisticsName` varchar(60) DEFAULT '',
  `logisticsCode` varchar(50) DEFAULT '',
  `discount` varchar(30) DEFAULT '0',
  `totalprice` varchar(30) DEFAULT '0',
  `buyerTelephone` varchar(20) DEFAULT '' COMMENT '购买人电话',
  PRIMARY KEY (`id`),
  KEY `I_ORDER_BONDED_TXLOGISTICID` (`txLogisticID`) USING BTREE,
  KEY `I_ORDER_BONDED_MAILNO` (`mailNo`) USING BTREE,
  KEY `I_ORDER_BONDED_AUDITSTATUS` (`auditstatus`) USING BTREE,
  KEY `I_ORDER_BONDED_MARK` (`mark`) USING BTREE,
  KEY `I_ORDER_BONDED_createTime` (`createTime`) USING BTREE,
  KEY `isPushToWms` (`isPushToWms`)
) ENGINE=InnoDB AUTO_INCREMENT=1000408805 DEFAULT CHARSET=utf8;

/*Table structure for table `t_order_bonded_rule` */

DROP TABLE IF EXISTS `t_order_bonded_rule`;

CREATE TABLE `t_order_bonded_rule` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `province` varchar(20) DEFAULT NULL COMMENT '省份',
  `weight` double(20,0) DEFAULT NULL COMMENT '重量',
  `carrier` varchar(20) DEFAULT NULL COMMENT '快递公司编码',
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `t_order_bonded_sku` */

DROP TABLE IF EXISTS `t_order_bonded_sku`;

CREATE TABLE `t_order_bonded_sku` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `txLogisticID` varchar(255) DEFAULT NULL COMMENT '客户订单号',
  `itemsku` varchar(250) DEFAULT NULL COMMENT 'sku',
  `itemName` varchar(255) DEFAULT NULL,
  `itemWeight` double(20,3) DEFAULT '0.000' COMMENT '重量(kg)',
  `itemCount` int(50) DEFAULT NULL COMMENT '数量',
  `unitPrice` double(50,3) DEFAULT '0.000' COMMENT '单价',
  `allPrice` double(250,3) DEFAULT '0.000' COMMENT '总价',
  `hz` varchar(20) DEFAULT NULL COMMENT '货主',
  `internalNumber` int(50) DEFAULT NULL COMMENT '内部编号',
  `s_sj` varchar(255) DEFAULT NULL COMMENT '单类商品税金',
  `pc` varchar(20) DEFAULT NULL COMMENT '库存扣减批次',
  `url` varchar(250) DEFAULT NULL COMMENT '商品链接地址',
  `itemNo` varchar(60) DEFAULT '',
  `gcode` varchar(60) DEFAULT '',
  `hsCode` varchar(15) DEFAULT '',
  `gmodel` varchar(1024) DEFAULT '',
  `barCode` varchar(30) DEFAULT '',
  `country` varchar(3) DEFAULT '',
  `unit` varchar(3) DEFAULT '',
  `unit1` varchar(3) DEFAULT '',
  `unit2` varchar(3) DEFAULT '',
  `qty1` varchar(10) DEFAULT '',
  `qty2` varchar(10) DEFAULT '',
  `qname` varchar(255) DEFAULT '',
  PRIMARY KEY (`Id`),
  KEY `I_ORDER_BONDEDSKU_TXLOGISTICID` (`txLogisticID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2510855 DEFAULT CHARSET=utf8;

/*Table structure for table `t_order_bondmoney` */

DROP TABLE IF EXISTS `t_order_bondmoney`;

CREATE TABLE `t_order_bondmoney` (
  `userId` varchar(255) DEFAULT NULL COMMENT '用户名',
  `frostMamey` varchar(255) DEFAULT NULL COMMENT '冻结金额',
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bondManey` varchar(255) DEFAULT NULL COMMENT '总保证金',
  `companybm` varchar(255) DEFAULT NULL COMMENT '公司编号',
  `companyName` varchar(255) DEFAULT NULL COMMENT '公司名称',
  `surplusMamey` varchar(255) DEFAULT NULL COMMENT '剩余保证金',
  `useManey` varchar(255) DEFAULT NULL COMMENT '已用保证金',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `t_order_bondmoney_sku` */

DROP TABLE IF EXISTS `t_order_bondmoney_sku`;

CREATE TABLE `t_order_bondmoney_sku` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `companybm` varchar(255) DEFAULT NULL COMMENT '公司编码',
  `creatTime` varchar(255) DEFAULT NULL COMMENT '新增时间',
  `txLogisticID` varchar(255) DEFAULT NULL COMMENT '客户订单号',
  `mailNo` varchar(255) DEFAULT NULL COMMENT '运单号',
  `bondManey` varchar(255) DEFAULT NULL COMMENT '总金额',
  `payMamey` varchar(255) DEFAULT NULL COMMENT '应付金',
  `syMamey` varchar(255) DEFAULT NULL,
  `upMamey` varchar(255) DEFAULT NULL COMMENT '操作的金额',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `t_order_commoditie` */

DROP TABLE IF EXISTS `t_order_commoditie`;

CREATE TABLE `t_order_commoditie` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `expressNum` varchar(30) DEFAULT NULL COMMENT '快递单号',
  `commoditieCode` varchar(30) DEFAULT NULL COMMENT '耗材编号',
  `commoditieName` varchar(200) DEFAULT NULL COMMENT '耗材名称',
  `commoditieNum` int(11) DEFAULT NULL COMMENT '耗材数量',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `t_order_ds` */

DROP TABLE IF EXISTS `t_order_ds`;

CREATE TABLE `t_order_ds` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `txlogisticid` varchar(30) DEFAULT NULL COMMENT '订单号',
  `mailno` varchar(30) DEFAULT NULL COMMENT '运单号',
  `lx` varchar(30) DEFAULT NULL COMMENT '订单类型',
  `payinfo` text COMMENT '支付报文',
  `payback` text COMMENT '支付回执',
  `createtime` datetime DEFAULT NULL,
  `vid` varchar(100) DEFAULT NULL COMMENT '支付id',
  PRIMARY KEY (`id`),
  KEY `ddh` (`txlogisticid`) USING HASH
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `t_order_mail` */

DROP TABLE IF EXISTS `t_order_mail`;

CREATE TABLE `t_order_mail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `receiveMan` varchar(250) DEFAULT NULL COMMENT '收件人姓名',
  `receiveManPhone` varchar(250) DEFAULT NULL COMMENT '收件人电话',
  `receiveManAddress` varchar(255) DEFAULT NULL COMMENT '收件人地址',
  `receiveProvince` varchar(255) DEFAULT NULL COMMENT '收件人省',
  `receiveCity` varchar(255) DEFAULT NULL COMMENT '收件市',
  `receiveCounty` varchar(255) DEFAULT NULL COMMENT '收件区县',
  `txLogisticID` varchar(255) DEFAULT NULL COMMENT '客户订单号',
  `itemName` varchar(255) DEFAULT NULL COMMENT '主要商品',
  `itemWeight` double(50,3) DEFAULT NULL COMMENT '重量',
  `itemCount` int(50) DEFAULT NULL COMMENT '总数量',
  `carrier` varchar(50) DEFAULT NULL COMMENT '快递',
  `mailNo` varchar(150) DEFAULT NULL COMMENT '运单号',
  `markDestination` varchar(250) DEFAULT NULL COMMENT '格口',
  `billProvideSiteName` varchar(250) DEFAULT NULL COMMENT '始发中心',
  `billProvideSiteCode` varchar(50) DEFAULT NULL COMMENT '始发编码',
  `worth` double(50,3) DEFAULT NULL COMMENT '总价值',
  `preEntryNumber` varchar(250) DEFAULT NULL COMMENT '预录入编号',
  `tradeCountry` varchar(250) DEFAULT NULL COMMENT '启运国（地区）',
  `buyerIdNumber` varchar(250) DEFAULT NULL COMMENT '订购人证件号码',
  `buyerName` varchar(250) DEFAULT NULL COMMENT '订购人姓名',
  `feeAmount` double(250,3) DEFAULT '0.000' COMMENT '运费',
  `insureAmount` double(250,3) DEFAULT '0.000' COMMENT '保费',
  `payNumber` varchar(250) DEFAULT '' COMMENT '支付流水号',
  `ordercode` varchar(250) DEFAULT '' COMMENT '平台订单号',
  `pc` varchar(20) DEFAULT NULL COMMENT '备注',
  `isPushDd` int(1) DEFAULT '0' COMMENT '推送订单(0、未推送1、已推送)',
  `isPushQd` int(1) DEFAULT '0' COMMENT '推送清单(0、未推送1、已推送)',
  `ispost` int(1) DEFAULT '0' COMMENT '推送网趣平台(0、未推送1、已推送)',
  `isCustoms` int(1) DEFAULT '0' COMMENT '是否报关(0、未报关1、已报关)',
  `isPushCz` int(1) DEFAULT '0' COMMENT '是否推送场站(0、未推送1、已推送)',
  `auditstatus` int(1) DEFAULT '0' COMMENT '审核状态(0、未审核1、已审核未匹配2、已审核已匹配)',
  `userId` int(10) DEFAULT NULL COMMENT '用户ID',
  `sendWarehouse` varchar(50) DEFAULT NULL COMMENT '发件仓库',
  `merchantNum` varchar(50) DEFAULT NULL COMMENT '商家编码',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `totalMailNo` varchar(255) DEFAULT NULL COMMENT '提运单号',
  `fightNumber` varchar(255) DEFAULT NULL COMMENT '航班号',
  `status` int(1) DEFAULT '0' COMMENT '状态编码',
  `statusname` varchar(255) DEFAULT NULL COMMENT '状态编码',
  `receivecode` varchar(50) DEFAULT NULL COMMENT '收件人编码',
  `destinationPort` varchar(50) DEFAULT NULL COMMENT '指运港',
  `hs` varchar(50) DEFAULT NULL COMMENT '物品HS',
  `unitname` varchar(50) DEFAULT NULL COMMENT '物品计量单位名称',
  `receiveCountr` varchar(50) DEFAULT NULL COMMENT '收件人国家',
  `classname` varchar(255) DEFAULT NULL COMMENT '物品品名',
  `parcelnumber` varchar(50) DEFAULT NULL COMMENT '行邮税号',
  `standard` varchar(250) DEFAULT NULL COMMENT '物品规格',
  `tovipdpt` varchar(255) DEFAULT NULL COMMENT 'vip大客户平台状态',
  `batchNumber` varchar(200) DEFAULT '' COMMENT '批次号',
  `customsTax` varchar(20) DEFAULT '' COMMENT '海关税',
  `valueAddedTax` varchar(20) DEFAULT '' COMMENT '增值税',
  `consumptionTax` varchar(20) DEFAULT '' COMMENT '消费税',
  `invtNo` varchar(50) DEFAULT '',
  `isPrint` int(1) DEFAULT '0' COMMENT '是否推送打印(0、未推送1、已推送)',
  `returncode` int(1) DEFAULT '0' COMMENT '回执状态 0 空着,1不接受申报，2待运抵，3查验，9放行',
  `returnInfo` varchar(200) DEFAULT '' COMMENT '回执内容',
  `disbursed` int(1) DEFAULT NULL COMMENT '扣除担保金标示 1为已扣除',
  PRIMARY KEY (`id`),
  KEY `I_ORDER_MAIL_TOTALMAILNO` (`totalMailNo`) USING BTREE,
  KEY `I_ORDER_MAIL_TXLOGISTICID` (`txLogisticID`) USING BTREE,
  KEY `I_ORDER_MAIL_MAILNO` (`mailNo`) USING BTREE,
  KEY `I_ORDER_MAIL_AUDITSTATUS` (`auditstatus`) USING BTREE,
  KEY `I_ORDER_MAIL_RETURNCODE` (`returncode`) USING BTREE,
  KEY `I_ORDER_MAIL_createTime` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `t_order_mail_sku` */

DROP TABLE IF EXISTS `t_order_mail_sku`;

CREATE TABLE `t_order_mail_sku` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `txLogisticID` varchar(255) DEFAULT NULL COMMENT '客户订单号',
  `itemsku` varchar(250) DEFAULT NULL COMMENT 'sku',
  `itemName` varchar(255) DEFAULT NULL,
  `itemWeight` double(20,3) DEFAULT '0.000' COMMENT '重量(kg)',
  `itemCount` int(50) DEFAULT NULL COMMENT '数量',
  `unitPrice` double(10,3) DEFAULT '0.000' COMMENT '单价',
  `allPrice` double(250,3) DEFAULT '0.000' COMMENT '总价',
  `hz` varchar(20) DEFAULT NULL COMMENT '货主',
  `excise` decimal(10,3) DEFAULT '0.000' COMMENT '消费税',
  `vat` decimal(10,3) DEFAULT '0.000' COMMENT '增值税',
  `consolidatedTax` decimal(10,3) DEFAULT '0.000' COMMENT '综合税',
  `batchNumber` varchar(200) DEFAULT NULL COMMENT '批次号',
  `itemClass` varchar(255) DEFAULT NULL COMMENT '商品品名',
  `standard` varchar(255) DEFAULT NULL COMMENT '商品规格',
  `unitname` varchar(255) DEFAULT NULL COMMENT '计量单位名称',
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `I_ORDER_MAIL_SKU_TXLOGISTICID` (`txLogisticID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `t_order_out` */

DROP TABLE IF EXISTS `t_order_out`;

CREATE TABLE `t_order_out` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `orderNo` varchar(60) DEFAULT NULL COMMENT '订单号',
  `shippingName` varchar(60) DEFAULT NULL COMMENT '物流名称',
  `invoiceNo` varchar(1024) DEFAULT NULL COMMENT '运单号',
  `payTime` varchar(20) DEFAULT NULL COMMENT '支付时间',
  `shippingTime` varchar(20) DEFAULT NULL COMMENT '发货时间',
  `goodsNum` int(11) DEFAULT NULL COMMENT '订单的商品总数',
  `currency` varchar(20) DEFAULT NULL COMMENT '下单时所选的货币',
  `rate` varchar(20) DEFAULT NULL COMMENT '汇率',
  `orderTotalAmount` varchar(20) DEFAULT NULL COMMENT '订单总金额',
  `paypalTrade` varchar(64) DEFAULT NULL COMMENT 'Paypal交易号',
  `realShippingId` varchar(10) DEFAULT NULL COMMENT '真实发货方式',
  `consignee` varchar(60) DEFAULT NULL COMMENT '收货人',
  `consigneeCountry` varchar(20) DEFAULT NULL COMMENT '收货人国家',
  `consigneeAddress` varchar(255) DEFAULT NULL COMMENT '收件人详细地址',
  `consigneeTel` varchar(60) DEFAULT NULL COMMENT '收货人电话',
  `consigneeEmail` varchar(60) DEFAULT NULL COMMENT '收货人email地址',
  `wayBillNo` varchar(50) DEFAULT NULL COMMENT '运单号',
  `destinationCountry` varchar(20) DEFAULT NULL COMMENT '运抵国',
  `totalPrice` varchar(20) DEFAULT NULL COMMENT '总价',
  `packageNo` int(11) DEFAULT NULL COMMENT '件数',
  `grossWeight` varchar(20) DEFAULT NULL COMMENT '毛重',
  `postMode` varchar(10) DEFAULT NULL COMMENT '物流方式',
  `netWeight` varchar(20) DEFAULT NULL COMMENT '净重',
  `taxMode` varchar(10) DEFAULT NULL COMMENT '退税方式',
  `exchangeRate` varchar(20) DEFAULT NULL COMMENT 'exchangeRate',
  `totalWayBill` varchar(100) DEFAULT NULL COMMENT '主运单号',
  `goodsUnit` varchar(10) DEFAULT NULL COMMENT '成交单位',
  `warehouseCode` varchar(20) DEFAULT NULL COMMENT '仓库代码',
  `warehouseName` varchar(50) DEFAULT NULL COMMENT '仓库名称',
  `locationCode` varchar(20) DEFAULT NULL COMMENT '储位编码',
  `locationType` varchar(10) DEFAULT NULL COMMENT '储位编码',
  `goodsModel` varchar(255) DEFAULT NULL COMMENT '商品型号',
  `dutyMode` varchar(10) DEFAULT NULL COMMENT '征免方式',
  `goodsUnit1` varchar(10) DEFAULT NULL COMMENT '法定单位',
  `goodsAmount1` varchar(20) DEFAULT NULL COMMENT '法定数量',
  `goodsAmount2` varchar(20) DEFAULT NULL COMMENT '第二法定数量',
  `goodsUnit2` varchar(10) DEFAULT NULL COMMENT '第二法定单位',
  `createTime` varchar(20) DEFAULT NULL,
  `userId` int(20) DEFAULT NULL COMMENT '用户ID',
  `itemCode` varchar(50) DEFAULT NULL COMMENT '商家编码',
  `status` int(1) DEFAULT NULL COMMENT '状态0未处理1已报关2退单3进入绿色通道4流水线自动放行5.自动完结9已完结',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `t_order_out_log` */

DROP TABLE IF EXISTS `t_order_out_log`;

CREATE TABLE `t_order_out_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `wayBillNo` varchar(50) DEFAULT NULL COMMENT '运单号',
  `approveResult` varchar(10) DEFAULT NULL COMMENT '状态编码',
  `approveComment` varchar(250) DEFAULT NULL COMMENT '回执',
  `createTime` datetime DEFAULT NULL COMMENT '接收时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `t_order_out_sku` */

DROP TABLE IF EXISTS `t_order_out_sku`;

CREATE TABLE `t_order_out_sku` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `goodsName` varchar(255) DEFAULT NULL,
  `goodsNo` varchar(50) DEFAULT NULL,
  `weight` varchar(20) DEFAULT NULL,
  `goodsAmount` varchar(20) DEFAULT NULL,
  `unitPrice` varchar(20) DEFAULT NULL,
  `totalPrice` varchar(20) DEFAULT NULL,
  `createTime` varchar(20) DEFAULT NULL,
  `specifications` varchar(30) DEFAULT '' COMMENT '商品规格',
  `orderno` varchar(50) DEFAULT NULL COMMENT '订单号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `t_order_push` */

DROP TABLE IF EXISTS `t_order_push`;

CREATE TABLE `t_order_push` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `orderNumber` varchar(20) DEFAULT NULL COMMENT '订单编号',
  `expressCode` varchar(20) DEFAULT NULL COMMENT '快递公司编码',
  `expressNumber` varchar(20) NOT NULL COMMENT '快递单号',
  `buyerNickName` varchar(20) DEFAULT NULL COMMENT '昵称（买家）',
  `buyerName` varchar(20) DEFAULT NULL COMMENT '真实姓名（买家）',
  `buyerIdCard` varchar(18) DEFAULT NULL COMMENT '身份证号码（买家）',
  `buyerProvince` varchar(20) DEFAULT NULL COMMENT '省（买家）',
  `buyerCity` varchar(20) DEFAULT NULL COMMENT '市（买家）',
  `buyerArea` varchar(20) DEFAULT NULL COMMENT '区县（买家）',
  `buyerAddress` varchar(50) DEFAULT NULL COMMENT '详细地址（买家）',
  `buyerPostCode` varchar(10) DEFAULT NULL COMMENT '邮政编码（买家）',
  `buyerCountry` varchar(20) DEFAULT NULL COMMENT '国家（买家）',
  `buyerTel` varchar(20) DEFAULT NULL COMMENT '联系电话（买家）',
  `pushType` int(1) DEFAULT '0' COMMENT '推送状态：0未推送、1已推送',
  `userId` int(11) DEFAULT NULL COMMENT '用户ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `t_receive_hghz_data` */

DROP TABLE IF EXISTS `t_receive_hghz_data`;

CREATE TABLE `t_receive_hghz_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '接收海关回执数据表',
  `contentData` mediumtext COMMENT '解析后content数据',
  `isHandle` int(1) DEFAULT '0' COMMENT '是否处理(0、未处理1、已处理)',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `1` (`id`) USING HASH
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `t_receive_ht_data` */

DROP TABLE IF EXISTS `t_receive_ht_data`;

CREATE TABLE `t_receive_ht_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '接收海关回执数据表',
  `statusName` varchar(50) DEFAULT NULL COMMENT '报关状态',
  `detailInfo` text COMMENT '报关结果原因',
  `expressNo` varchar(50) DEFAULT NULL COMMENT '运单号',
  `isSuccess` varchar(50) DEFAULT NULL COMMENT '是否成功',
  `isHandle` int(1) DEFAULT '0' COMMENT '是否处理(0、未处理1、已处理)',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `t_users` */

DROP TABLE IF EXISTS `t_users`;

CREATE TABLE `t_users` (
  `id` int(50) NOT NULL AUTO_INCREMENT,
  `userName` varchar(20) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `userRole` varchar(255) DEFAULT NULL,
  `changOdd` int(1) DEFAULT NULL COMMENT '0为直邮1为保税',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=89 DEFAULT CHARSET=utf8;

/*Table structure for table `t_waybill` */

DROP TABLE IF EXISTS `t_waybill`;

CREATE TABLE `t_waybill` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `expressCode` varchar(250) DEFAULT 'STO' COMMENT '快递公司编码',
  `expressNumber` varchar(250) DEFAULT NULL COMMENT '快递单号',
  `acceptTime` datetime DEFAULT NULL COMMENT '收件时间',
  `acceptState` varchar(250) DEFAULT NULL COMMENT '收件状态',
  `endTime` datetime DEFAULT NULL COMMENT '尾时间',
  `endState` varchar(250) DEFAULT NULL COMMENT '尾状态',
  `signTime` datetime DEFAULT NULL COMMENT '签收时间',
  `signState` varchar(250) DEFAULT NULL COMMENT '签收状态',
  `isSign` int(10) DEFAULT '0' COMMENT '默认0未签收，1签收',
  `business` varchar(250) DEFAULT NULL COMMENT '商家',
  `userId` int(10) DEFAULT NULL COMMENT '用户ID',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `t_ytogj_tracking` */

DROP TABLE IF EXISTS `t_ytogj_tracking`;

CREATE TABLE `t_ytogj_tracking` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `waybillNo` varchar(50) DEFAULT NULL COMMENT '运单号',
  `orderNo` varchar(50) DEFAULT NULL COMMENT '订单号',
  `shipperName` varchar(50) DEFAULT NULL COMMENT '寄件人',
  `shipperTel` varchar(50) DEFAULT NULL COMMENT '寄件人电话',
  `shipperAddress` varchar(255) DEFAULT NULL COMMENT '寄件人地址',
  `consigneeName` varchar(50) DEFAULT NULL COMMENT '收件人',
  `consigneeTel` varchar(50) DEFAULT NULL COMMENT '收件人电话',
  `consigneeAddress` varchar(255) DEFAULT NULL COMMENT '收件人地址',
  `isSigned` int(1) DEFAULT NULL COMMENT '是否签收',
  `deliveryTime` varchar(20) DEFAULT NULL COMMENT '发货时间',
  `createTime` datetime DEFAULT NULL,
  `signTime` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `I_YTOGJ_TRACKING_WAYBILLNO` (`waybillNo`) USING BTREE,
  KEY `I_YTOGJ_TRACKING_ORDERNO` (`orderNo`) USING BTREE,
  KEY `I_YTOGJ_TRACKING_CREATETIME` (`createTime`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `t_ytogj_tracking_sku` */

DROP TABLE IF EXISTS `t_ytogj_tracking_sku`;

CREATE TABLE `t_ytogj_tracking_sku` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `waybillNo` varchar(50) DEFAULT NULL COMMENT '运单号',
  `orderNo` varchar(50) DEFAULT NULL COMMENT '订单号',
  `eventCode` varchar(10) DEFAULT NULL COMMENT '走件code',
  `eventDetail` varchar(255) DEFAULT NULL COMMENT '走件详细',
  `eventLocation` varchar(100) DEFAULT NULL COMMENT '轨迹发生地',
  `eventOperater` varchar(20) DEFAULT NULL COMMENT '操作人',
  `eventOperaterPhone` varchar(50) DEFAULT NULL COMMENT '操作人电话',
  `city` varchar(20) DEFAULT NULL COMMENT '城市',
  `nextCity` varchar(20) DEFAULT NULL COMMENT '下一站城市',
  `eventTime` varchar(20) DEFAULT NULL COMMENT '发生时间',
  `isPushed` int(1) DEFAULT NULL COMMENT '是否推送数据到圆通',
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `I_YTOGJ_TRACKING_WAYBILLNO` (`waybillNo`) USING BTREE,
  KEY `I_YTOGJ_TRACKING_ORDERNO` (`orderNo`) USING BTREE,
  KEY `I_YTOGJ_TRACKING_ISPUSHED` (`isPushed`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `test` */

DROP TABLE IF EXISTS `test`;

CREATE TABLE `test` (
  `orderNo` varchar(255) NOT NULL,
  `mailno` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `timedtask` */

DROP TABLE IF EXISTS `timedtask`;

CREATE TABLE `timedtask` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `company` varchar(30) DEFAULT NULL COMMENT '商家',
  `shenPi` int(1) DEFAULT NULL COMMENT '自动审批',
  `chuku` int(1) DEFAULT NULL COMMENT '自动出库',
  `getlshao` int(1) DEFAULT NULL COMMENT '自动获取支付流水',
  `yund` int(1) DEFAULT NULL COMMENT '运单申报',
  `dind` int(1) DEFAULT NULL COMMENT '订单',
  `qind` int(1) DEFAULT NULL COMMENT '清单',
  `isPushToWms` int(1) DEFAULT NULL COMMENT '推送wms',
  `getyun` int(1) DEFAULT NULL COMMENT '匹配运单',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `webui` */

DROP TABLE IF EXISTS `webui`;

CREATE TABLE `webui` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL COMMENT '标识',
  `data` text COMMENT '内容',
  `begCode` varchar(255) DEFAULT NULL COMMENT '创建时间',
  `isimg` int(1) DEFAULT NULL COMMENT '是否是图片',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `webzu` */

DROP TABLE IF EXISTS `webzu`;

CREATE TABLE `webzu` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `url` varchar(100) DEFAULT NULL COMMENT 'URL',
  `company` varchar(10) DEFAULT NULL COMMENT '商家',
  `bm` varchar(20) DEFAULT NULL COMMENT '条件编码',
  `tab` varchar(10) DEFAULT NULL COMMENT '表名',
  `tj` varchar(100) DEFAULT NULL COMMENT '条件',
  `code` varchar(10) DEFAULT NULL COMMENT '标识',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `wms_actual_receive` */

DROP TABLE IF EXISTS `wms_actual_receive`;

CREATE TABLE `wms_actual_receive` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `jobFormId` varchar(250) NOT NULL COMMENT '核放单编号',
  `datatime` datetime DEFAULT NULL COMMENT '时间',
  `flag` varchar(250) DEFAULT NULL COMMENT '口岸回执',
  `userId` int(15) DEFAULT NULL COMMENT '用户ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `wms_actual_receive_sku` */

DROP TABLE IF EXISTS `wms_actual_receive_sku`;

CREATE TABLE `wms_actual_receive_sku` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `jobFormId` varchar(250) NOT NULL COMMENT '核放单编号',
  `sourceNo` varchar(20) NOT NULL COMMENT '料号',
  `itemNo` varchar(250) NOT NULL DEFAULT '' COMMENT '项号',
  `itemType` varchar(200) NOT NULL COMMENT '料件性质',
  `receiveAmount` varchar(250) NOT NULL COMMENT '实收数量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `wms_inout` */

DROP TABLE IF EXISTS `wms_inout`;

CREATE TABLE `wms_inout` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `inOutSeq` varchar(250) NOT NULL COMMENT '出入库记录流水号',
  `inOutNo` varchar(50) NOT NULL COMMENT '出入库记录编号',
  `manualId` varchar(250) NOT NULL DEFAULT '' COMMENT '账册编号',
  `sourceNo` varchar(200) NOT NULL COMMENT '料号',
  `itemType` varchar(250) NOT NULL COMMENT '料件性质',
  `inOutFlag` varchar(200) NOT NULL COMMENT '出入库标志',
  `inOutAmount` varchar(250) DEFAULT '' COMMENT '出入库数量',
  `inOutTime` varchar(20) NOT NULL COMMENT '出入库时间',
  `wayBillNo` varchar(250) DEFAULT '' COMMENT '运单编号',
  `jobFormId` varchar(50) NOT NULL COMMENT '核放单编号',
  `datatime` datetime DEFAULT NULL,
  `flag` varchar(20) DEFAULT NULL COMMENT '口岸回执',
  `userId` int(20) DEFAULT NULL,
  `flag_zc` int(1) DEFAULT '0' COMMENT '0 未拉取 1已拉取',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `wms_inout_forzt` */

DROP TABLE IF EXISTS `wms_inout_forzt`;

CREATE TABLE `wms_inout_forzt` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `manualId` varchar(30) DEFAULT NULL COMMENT '账册编号',
  `sourceNo` varchar(30) DEFAULT NULL COMMENT '料号',
  `A1` int(11) DEFAULT NULL COMMENT '正常进库（先进后报）',
  `A2` int(11) DEFAULT NULL COMMENT '正常进库（区间转入）',
  `A3` int(11) DEFAULT NULL COMMENT '正常进库（区内转入）',
  `A4` int(11) DEFAULT NULL COMMENT '正常进库（二线进区）（保税出口）',
  `A5` int(11) DEFAULT NULL COMMENT '正常进库（一线进境）',
  `A6` int(11) DEFAULT NULL COMMENT '正常进库（二线进区）',
  `A7` int(11) DEFAULT NULL COMMENT '正常进库（分送集报进区）',
  `B` int(11) DEFAULT NULL COMMENT '库内调库转入（换货号）',
  `C` int(11) DEFAULT NULL COMMENT '退货入库(保税进口)',
  `C2` int(11) DEFAULT NULL COMMENT '退货入库',
  `D` int(11) DEFAULT NULL COMMENT '盘盈核增',
  `E` int(11) DEFAULT NULL COMMENT '从展示仓转入',
  `F1` int(11) DEFAULT NULL COMMENT '正常出库（快递出区）',
  `F2` int(11) DEFAULT NULL COMMENT '正常出库（一线退运出境）',
  `F3` int(11) DEFAULT NULL COMMENT '正常出库（区间转出）',
  `F4` int(11) DEFAULT NULL COMMENT '正常出库（区内转出）',
  `F5` int(11) DEFAULT NULL COMMENT '正常出库（快递出境）（保税出口）',
  `F6` int(11) DEFAULT NULL COMMENT '正常出库（一线转出）',
  `F7` int(11) DEFAULT NULL COMMENT '正常出库（二线转出）',
  `F8` int(11) DEFAULT NULL COMMENT '正常出库（分送集报出区)',
  `G` int(11) DEFAULT NULL COMMENT '库内调库转出（换货号）',
  `H` int(11) DEFAULT NULL COMMENT '商检取样',
  `I` int(11) DEFAULT NULL COMMENT '盘亏核减',
  `J` int(11) DEFAULT NULL COMMENT '残次品出库',
  `K` int(11) DEFAULT NULL COMMENT '转出到展示仓库',
  `L` int(11) DEFAULT NULL COMMENT '退货出库',
  `creattime` datetime DEFAULT NULL COMMENT '创建时间',
  `endtime` datetime DEFAULT NULL COMMENT '最后操作时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `wms_inventory` */

DROP TABLE IF EXISTS `wms_inventory`;

CREATE TABLE `wms_inventory` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `manualId` varchar(250) NOT NULL DEFAULT '' COMMENT '账册编号',
  `sourceNo` varchar(200) NOT NULL COMMENT '料号',
  `itemType` varchar(250) NOT NULL COMMENT '料件性质',
  `goodsQuantity` int(20) NOT NULL COMMENT '库存数量',
  `goodsUnit` varchar(250) DEFAULT '' COMMENT '单位',
  `storageLocation` varchar(250) DEFAULT NULL COMMENT '库位',
  `inventoryTime` varchar(20) DEFAULT NULL COMMENT '库存申报时间',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `datatime` datetime DEFAULT NULL COMMENT '创建时间',
  `flag` varchar(20) DEFAULT NULL COMMENT '口岸回执',
  `userId` int(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `wms_merger` */

DROP TABLE IF EXISTS `wms_merger`;

CREATE TABLE `wms_merger` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `companyCode` varchar(20) DEFAULT NULL COMMENT '企业海关十位数编码',
  `manualId` varchar(250) DEFAULT NULL COMMENT '账册编号',
  `itemNo` varchar(20) DEFAULT NULL COMMENT '料件项号',
  `itemType` varchar(250) DEFAULT '0' COMMENT '料件性质',
  `sourceNo` varchar(200) DEFAULT NULL COMMENT '料号',
  `goodsName` varchar(250) DEFAULT NULL COMMENT '商品名称',
  `goodsNo` varchar(200) DEFAULT NULL COMMENT '商品编码',
  `goodsSpec` varchar(250) DEFAULT '' COMMENT '商品规格、型号',
  `currencyType` varchar(10) DEFAULT '' COMMENT '币制',
  `declareUnit` varchar(250) DEFAULT NULL COMMENT '申报计量单位',
  `useFlag` varchar(10) DEFAULT NULL COMMENT '空：正常 0:停用 1:消除',
  `actionType` varchar(5) DEFAULT NULL COMMENT 'C:新增 M:修改 D:删除',
  `unit1` varchar(10) DEFAULT NULL COMMENT '第一单位',
  `unit2` varchar(10) DEFAULT NULL COMMENT '第二单位',
  `datatime` datetime DEFAULT NULL,
  `flag` varchar(20) DEFAULT NULL COMMENT '口岸回执',
  `userId` int(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `wms_planing_receipts` */

DROP TABLE IF EXISTS `wms_planing_receipts`;

CREATE TABLE `wms_planing_receipts` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `planingReceiptsId` varchar(250) NOT NULL COMMENT '计划入库单编号',
  `manualId` varchar(20) NOT NULL COMMENT '账册编码',
  `customsCode` varchar(250) NOT NULL DEFAULT '' COMMENT '关区代码',
  `companyCode` varchar(200) NOT NULL COMMENT '企业海关十位编码',
  `companyName` varchar(250) NOT NULL COMMENT '企业名称',
  `grossWeight` double(200,4) NOT NULL DEFAULT '0.0000' COMMENT '毛重',
  `netWeight` double(250,4) NOT NULL DEFAULT '0.0000' COMMENT '净重',
  `amount` int(30) NOT NULL DEFAULT '0' COMMENT '件数',
  `wrapType` int(250) NOT NULL COMMENT '包装种类',
  `port` int(10) NOT NULL COMMENT '申报关区',
  `providerCode` varchar(20) NOT NULL COMMENT '厂商编码',
  `type` varchar(20) NOT NULL COMMENT '业务类别',
  `datatime` datetime DEFAULT NULL,
  `flag` varchar(50) DEFAULT NULL COMMENT '口岸回执',
  `userId` int(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `wms_planing_receipts_sku` */

DROP TABLE IF EXISTS `wms_planing_receipts_sku`;

CREATE TABLE `wms_planing_receipts_sku` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `planingReceiptsId` varchar(250) NOT NULL COMMENT '计划入库单编号',
  `sourceNo` varchar(20) NOT NULL COMMENT '料号',
  `itemNo` int(10) NOT NULL COMMENT '项号',
  `itemType` varchar(200) NOT NULL COMMENT '料件性质',
  `goodsNo` varchar(200) NOT NULL COMMENT '商品编码',
  `declareAmount` varchar(250) DEFAULT '' COMMENT '申报数量',
  `totalPrice` double(20,4) NOT NULL COMMENT '申报总价',
  `price` double(20,4) DEFAULT NULL COMMENT '单价',
  `countryCode` varchar(10) NOT NULL COMMENT '原产国',
  `currency` varchar(5) NOT NULL COMMENT '币制',
  `datatime` datetime DEFAULT NULL,
  `userId` int(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `wms_stock_delete` */

DROP TABLE IF EXISTS `wms_stock_delete`;

CREATE TABLE `wms_stock_delete` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stockDeleteId` varchar(250) NOT NULL COMMENT '删单申请单编号',
  `manualId` varchar(250) NOT NULL DEFAULT '' COMMENT '账册编号',
  `customsCode` varchar(200) NOT NULL COMMENT '关区代码',
  `roleCode` varchar(250) NOT NULL COMMENT 'WMS系统权限代码',
  `companyCode` varchar(200) NOT NULL COMMENT '企业海关十位编码',
  `inOutFlag` varchar(250) DEFAULT '' COMMENT '出入库类型',
  `reason` varchar(250) DEFAULT NULL COMMENT '删单原因',
  `datatime` datetime DEFAULT NULL COMMENT '创建时间',
  `flag` varchar(550) DEFAULT NULL COMMENT '口岸回执',
  `userId` int(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `wms_stock_delete_sku` */

DROP TABLE IF EXISTS `wms_stock_delete_sku`;

CREATE TABLE `wms_stock_delete_sku` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stockDeleteId` varchar(250) NOT NULL COMMENT '删单申请单编号',
  `inOutSeq` varchar(250) NOT NULL DEFAULT '' COMMENT '出入库单流水号',
  `datatime` datetime DEFAULT NULL COMMENT '创建时间',
  `flag` varchar(550) DEFAULT NULL COMMENT '口岸回执',
  `userId` int(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
