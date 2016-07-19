<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>我要移车</title>
<link rel="stylesheet" href="css/jquery.mobile-1.4.5.css">
<link rel="stylesheet" href="css/jquery.mobile.icons.min.css" />
<link rel="stylesheet" href="css/yichegou.min.css" />
<link href="css/wemove.css" rel="stylesheet"/>
<link rel="stylesheet" href="css/zxx.lib.css"/>
<link rel="stylesheet" type="text/css" href="css/xcConfirm.css"/>
<script src="js/jquery-1.9.1.js"></script>
<script src="js/xcConfirm.js"></script>      
</head>
<body>
<!--移车地图首页-->
      <div data-role="page" id="index" data-theme="d">
         <div  data-role="header"  data-position="fixed" id="index_header"  >
                <a href="#"  data-direction="reverse"><img src="img/index_arrow.png"/></a>
                <h1>首页</h1>               
         </div>
         <div data-role="content" id="index_content">
         	<div class="con_box">
         		<p>数据更新频度</p>
                <img src="img/map.png"/>
                <img src="img/map_shadow.png"/>
                <img src="img/tip.png"/>
            </div>
            
            <div class="new_data">
            <p>今日已为<span class="gc0 fs18 fm0"> 2011 </span>位用户提供了服务</p>
            <p>采集全国车主通讯录<span class="gc0 fs18 fm0"> 120661234 </span>条</p>
            </div>
            
         </div> 
         <div data-role="footer" id="footer_map" data-position="fixed" class="cen bd_none pb10">
            <span class="ftlogo ftw mo db fs16 fm0" ><span class="gc0 fs16 fm0"> 移车狗 </span><span class="gc05">您移车的好帮手</span></span>
            <span><a href="#"  class="gcb"> 关于移车狗 </a></span>
            </div>
    </div>
</body>
</html>