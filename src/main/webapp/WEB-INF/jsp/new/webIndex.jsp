<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html lang="zh-cn">
 <head> 
  <meta charset="UTF-8" /> 
  <title>${total }</title>
  <meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=no" /> 
  <link href="<%=path %>/css/bootstrap.min.css" rel="stylesheet" /> 
  <link href="<%=path %>/css/index.min.css" rel="stylesheet" /> 
  <!--[if lt IE 9]>
    <script src="js/html5shiv.min.js"></script>
    <![endif]--> 
  <script src="<%=path %>/js/jquery.min.js"></script> 
  <script src="<%=path %>/js/index.min.js"></script> 
 </head> 
 <body> 
  <header> 
   <div class="logo"> 
   <%--  <img src="${loginImg }" alt="logo" class="img-responsive ori" /> --%>
   </div> 
   <nav class="menu"> 
    <ul class="list-inline"> 
     <li class="active"><a>首页</a></li> 
     <li><a>业务</a></li> 
     <li><a>案例</a></li>  
      <li><a>客户</a></li>  
     <li><a>品质</a></li> 
     <li><a>增值</a></li> 
     <li><a>关于</a></li> 
     <li><a>联系</a></li> 
    </ul> 
   </nav> 
   <div class="hotline"> 
    <%-- <a href="<%=path %>" title="免费咨询热线"><span>${hotline }</span></a> --%>
   	<a href="<%=path %>" title="登入"><span>登录</span></a>
    <u></u> 
   </div> 
   <div class="menu-icon"> 
    <a href="tel:${hotline }" title="点击直拨热线"><span class="glyphicon glyphicon-earphone"></span></a> 
    <span class="glyphicon glyphicon-th-large"></span> 
   </div> 
  </header> 
  <div class="welcome">
   <p><u>welcome</u></p>
  </div> 
  <section class="video"> 
   <div class="swiper-container"> 
    <div class="swiper-wrapper"> 
     <div class="swiper-slide nth1"> 
      <div class="box"> 
       <div class="left"></div> 
       <div class="right"> 
        <span>${oneFont1}</span>
        <i></i> 
        <p>${oneFont2}</p> 
       </div> 
      </div> 
     </div> 
     <div class="swiper-slide nth2"> 
      <div class="box"> 
       <span>${towFont1 }</span>
       <i></i> 
       <p>${towFont2 }</p> 
      </div> 
     </div> 
     <div class="swiper-slide nth3"> 
      <div class="box"> 
       <div class="top">
      ${threeFont2 }
       </div> 
       <div class="mid"></div> 
       <div class="bottom">
       ${threeFont2 }
       </div> 
      </div> 
     </div> 
     <div class="swiper-slide nth4"> 
      <div class="box"> 
       <div class="top">
        <span>${fourFont1 }</span>
        <i></i>
       </div> 
       <div class="bottom">
        ${fourFont2 }
       </div> 
      </div> 
     </div> 
    </div> 
   </div> 
   <div class="innerBox"> 
    <div class="news"> 
     <span>NEWS :</span> 
     <a href="article.html" title="更多文章动态" class="more" target="_blank">more</a> 
     <ul> 
      <li><a href="article_detail.html?id=48.html" target="_blank" title="钱都砸去哪里了，为什么我的网站推广没有效果？">钱都砸去哪里了，为什么我的网站推广没 ...</a></li> 
      <li><a href="article_detail.html?id=76.html" target="_blank" title="终于等到你！千助新版Logo正式启用">终于等到你！千助新版Logo正式启用</a></li> 
      <li><a href="article_detail.html?id=75.html" target="_blank" title="值得参考！功能型网站建设架构的全方位规划">值得参考！功能型网站建设架构的全方位 ...</a></li> 
      <li><a href="article_detail.html?id=74.html" target="_blank" title="苹果一手缔造的行业“移动互联网”营收或超其自身市值">苹果一手缔造的行业“移动互联网”营收 ...</a></li> 
      <li><a href="article_detail.html?id=73.html" target="_blank" title="留白设计是一种高端艺术，要懂得在网页设计中运用负空间">留白设计是一种高端艺术，要懂得在网页 ...</a></li> 
     </ul> 
    </div> 
    <div class="guide"></div> 
    <a class="movedown"></a> 
   </div> 
  </section> 
  <section class="business"> 
   <div class="box"> 
    <div class="caption"> 
     <i></i>
     <span>我们能做什么</span> 
     <br class="clear" /> 
    </div> 
    <ul class="items list-inline"> 
     <li class="pc"> <i></i><strong>${pcText1 }</strong> <p>${pcText2 }</p> </li> 
     <li class="mobi"> <i></i><strong>${mobiText1 }</strong> <p>${mobiText2 }</p> </li> 
     <li class="sys"> <i></i><strong>${sysText1 }</strong> <p>${sysText2 }</p> </li> 
     <li class="app"> <i></i><strong>${appText1 }</strong> <p>${appText2 }</p> </li> 
     <li class="host"> <i></i><strong>${hostText1 }</strong> <p>${hostText2 }</p> </li> 
    </ul> 
   </div> 
  </section> 
  <section class="cases"> 
   <div class="box"> 
    <div class="caption"> 
     <i></i>
     <span>${casesTital}</span> 
     <br class="clear" /> 
    </div> 
    <div class="swiper-container items"> 
     <div class="swiper-wrapper"> 
      <div class="swiper-slide"> 
       <a href="cases_detail.html" target="_blank"> <img src="${casesOne1 }" alt="img1" /> <p>${casesOne2}</p></a> 
      </div> 
      <div class="swiper-slide"> 
       <a href="cases_detail.html" target="_blank"> <img src="${casesTow1 }" alt="img2" /> <p>${casesTow2}</p></a> 
      </div> 
      <div class="swiper-slide"> 
       <a href="cases_detail.html" target="_blank"> <img src="${casesThree1 }" alt="img3" /> <p>${casesThree2}</p></a> 
      </div> 
     </div> 
    </div> 
    <a href="cases.html" title="欣赏更多网站建设案例" class="more" target="_blank">MORE</a> 
   </div> 
  </section> 
  <section class="clients"> 
   <div class="box"> 
    <div class="caption"> 
     <i></i>
     <span>标题</span> 
     <br class="clear" /> 
    </div> 
    <ul class="items list-inline"> 
     <li class="cctv"></li>
     <li class="unicom"></li>
     <li class="tsinghua"></li>
     <li class="cas"></li>
     <li class="sipo"></li>
     <li class="apple"></li>
     <li class="das"></li>
     <li class="hunantv"></li>
     <li class="sino"></li>
     <li class="report"></li>
     <li class="gedu"></li>
     <li class="bgg"></li>
     <li class="bsec"></li>
     <li class="huadan"></li>
     <li class="zd"></li> 
     
    </ul> 
   </div> 
  </section> 
  <section class="quality"> 
   <div class="box"> 
    <div class="caption"> 
     <i></i>
     <span>${qualityTotal}</span> 
     <br class="clear" /> 
    </div> 
    <div class="swiper-container items"> 
     <div class="swiper-wrapper"> 
      <div class="swiper-slide nth1"> 
       <ul class="list-inline"> 
        <li class="mobi"><span>响应式手机网站建设</span></li>
        <li class="pad"><span>响应式平板网站建设</span></li>
        <li class="pc"><span>响应式PC网站建设</span></li> 
       </ul> 
       <p>${qualityText1 }</p> 
      </div> 
      <div class="swiper-slide nth2"> 
       <ul class="list-inline"> 
        <li class="ie"><span>兼容微软IE浏览器的网页设计</span></li>
        <li class="chrome"><span>兼容谷歌Chrome浏览器的网站设计</span></li>
        <li class="firefox"><span>兼容火狐Firefox浏览器的网页设计</span></li>
        <li class="safari"><span>兼容苹果Safari浏览器的网站设计</span></li> 
       </ul> 
       <p>${qualityText2 }</p> 
      </div> 
      <div class="swiper-slide nth3"> 
       <ul class="list-inline"> 
        <li class="windows"><span>跨windows平台网站制作</span></li>
        <li class="ios"><span>跨ios平台网站制作</span></li>
        <li class="andriod"><span>跨andriod平台网站制作</span></li> 
       </ul> 
       <p>${qualityText3 }</p> 
      </div> 
     </div> 
    </div> 
    <a href="case.html" class="lookall" target="_blank">你以为看到了网站的全部？</a> 
   </div> 
  </section> 
  <section class="marketing"> 
   <div class="box"> 
    <div class="caption"> 
     <i></i>
     <span>${marketingTotal }</span> 
     <br class="clear" /> 
    </div> 
    <ul class="items list-inline"> 
     <li class="se"> <i></i><strong>${marketingText1 }</strong> </li> 
      <li class="weixin"> <i></i><strong>${marketingText2 }</strong> </li> 
      <li class="sms"> <i></i><strong>${marketingText3 }</strong> </li> 
      <li class="pay"> <i></i><strong>${marketingText4 }</strong> </li> 
      <li class="bbs"> <i></i><strong>${marketingText5 }</strong> </li> 
    </ul> 
   </div> 
  </section> 
  <section class="aboutus"> 
   <ul class="menu">
    <li>思想</li>
    <li>关于</li>
    <li>荣誉</li>
   </ul> 
  <div class="swiper-container items"> 
    <div class="swiper-wrapper"> 
     <div class="swiper-slide nth1"> 
      <strong>${aboutusText1 }</strong> 
      <p>${aboutusText4 }</p> 
      <u>${aboutusText5 }</u> 
     </div> 
     <div class="swiper-slide nth2"> 
      <strong>${aboutusText2 }</strong> 
      <p>${aboutusText6 }</p> 
      <p>${aboutusText7 }</p> 
     </div> 
     <div class="swiper-slide nth3"> 
      <strong>${aboutusText3 }</strong> 
      <ul> 
       <li>${aboutusText8 }<u>-</u>${aboutusText9 }</li> 
       <li>${aboutusText10 }<u>-</u>${aboutusText11 }</li> 
       <li>${aboutusText12 }<u>-</u>${aboutusText13 }</li> 
       <li>${aboutusText14 }<u>-</u>${aboutusText15 }</li> 
       <li>${aboutusText16 }<u>-</u>${aboutusText17 }</li> 
      </ul> 
     </div> 
    </div> 
   </div> 
   <table class="exp"> 
    <tbody>
     <tr> 
      <td>${aboutusNum1 }</td> 
      <td>${aboutusNum2 }</td> 
      <td>${aboutusNum3 }</td> 
      <td>${aboutusNum4 }</td> 
      <td>${aboutusNum5 }</td> 
     </tr> 
    </tbody>
   </table> 
  </section> 
  <section class="contact"> 
   <div class="box"> 
    <div class="above"> 
     <div class="wechat">
      <img src="${wechat_code }" alt="扫描关注千助曌通公众账号" />
     </div> 
     <div class="left"> 
     <a href="#" title="免费咨询热线"><span>${hotline }</span></a>
      <p>${company }<u>&middot;</u>${city }<u></u>${park }<br />联系电话：${hotline2 }<br />地址：${address }<br />邮编：${zip }<a href="#" target="_blank" class="job">[ 工作机会 ]</a></p> 
     </div> 
     <div class="right">
      Email：${Email }
      <br />中文域名：${domainName }
      <br />Copyright ${Copyright }
   
     </div> 
    </div> 
   </div> 
  </section> 
  <div class="dock"> 
   <ul class="icons"> 
    <li class="up"><i></i></li> 
    <li class="im"> <i></i><p>网站建设咨询<br />在线沟通，请点我<a href="#" target="_blank">在线咨询</a></p> </li> 
    <li class="tel"> <i></i><p>建站咨询热线：<br />${hotline }<br />售后电话：<br />${hotline2}</p> </li> 
    <li class="wechat"> <i></i><p><img src="${wechat_code }" alt="扫描关注网站建设微信公众账号" /></p> </li> 
    <li class="down"><i></i></li> 
   </ul> 
   <a class="switch"></a> 
  </div>   
 </body>
