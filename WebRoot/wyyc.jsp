<%@page import="com.dragon.service.impl.ParkReportImpl"%>
<%@page import="com.dragon.service.ParkReportService"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.dragon.service.SMSService"%>
<%@page import="com.dragon.service.impl.SMSServiceImpl"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>我要移车</title>
<link rel="stylesheet" href="./css/jquery.mobile-1.4.5.css">
<link rel="stylesheet" href="./css/jquery.mobile.icons.min.css" />
<link rel="stylesheet" href="./css/yichegou.min.css" />
<link href="css/wemove.css" rel="stylesheet" />
<link rel="stylesheet" href="./css/zxx.lib.css" />
<link rel="stylesheet" type="text/css" href="./css/xcConfirm.css" />
<link rel="stylesheet" type="text/css" href="./css/xcComfirm.css" />
<script src="./js/jquery-1.9.1.js"></script>
<script src="./js/xcConfirm.js"></script>
<script src="./js/xcComfirms.js"></script>
<script src="./js/custorToast.js"></script>
<script src="js/operator.js"></script>
<script src="./js/html5.js"></script>
<script src="./js/css3-mediaqueries.js"></script>


</head>
<body>

<!--移车index-->
<div data-role="page" id="move_index" style="background-color:#f2f2f4;" data-ajax="false">
    <div data-role="content" id="content_index"> 
       	<div data-role="navbar" >
          <ul>                
            <li><a href="#" id="movecar" class="ui-btn-active  ui-state-persist " data-theme="d">车牌号移车</a></li>
            <li><a href="#move_hotline"id="movecar1"  data-transition="none" data-theme="d">移车服务热线</a></li>
          </ul>
        </div>
         <form method="post" action="parkReport.do" id="frm_carnum">
	    	<div data-role="fieldcontain" id="num_box"> 
	            <div id="search">
	              <div class="input_box">  
	                <div id="input">
	                <input type="text" placeholder="请输入车牌号" name="cphm" id="cphm" style="text-transform:uppercase;"/></div>
	                <a href="" class="camera" id="camera"></a>
	              </div> 
	                <a href="#page03"><div id = "carD" class="cardress">闽</div></a>
	             </div>        	
	                <textarea placeholder="短信留言（非必填）" id="liuyan" name="message"></textarea>
	               	<a href="" id="tel" >
	               	<button type="submit" data-inline="true"  id="confirm" >
	               	呼叫车主
	               	</button>
	               	</a>
	               	<input type="hidden" name="userMobilePhone"  id="userMobilePhone">
           </div>
      </form>
	</div>
		<div data-role="footer" data-position="fixed" id="footer_index" class="footer">
			<span><a href="#page_inform_success" class="flink" id="first_btn" onclick="playlist(this)">观看汪汪移车视频</a></span>
		</div>
</div>

	<!--移车热线-->
	<div data-role="page" id="move_hotline" data-ajax="false">
		<div data-role="content" id="content_hotline">
			<div data-role="navbar">
				<ul>
					<li><a href="#move_index" id="movecar2" data-transition="none" data-theme="d">车牌号移车</a></li>
					<li><a href="#" id="movecar3" class="ui-btn-active  ui-state-persist " data-theme="d">移车服务热线</a></li>
				</ul>
			</div>
			<a href="#">
				<button class="p12" data-inline="true" id="confirm1">拨通热线</button>
				<script>
					$("#confirm1").click(
						function() {
							var txt = "目前免费热线还未开通哦，请期待!";
							window.wxcs.xcComfirm(txt,window.wxcs.xcComfirm.typeEnum.custom);
						});
				</script>
			</a>
		</div>
		<div data-role="footer" data-position="fixed" id="footer_hotline" class="footer">
			<span><a href="#" class="flink" id="mov_btn" onClick="playlist(this)">观看汪汪移车视频</a></span>
		</div>
	</div>
	<!--page03选择省简称-->
	<div data-role="page" id="page03" data-ajax="false">
		<div data-role="header" id="header_page03">
			<a href="#move_index" data-role="button" class=""><img src="img/arrow_back.png" /></a> <a href="#move_index" data-role="button" class="ui-btn-right"></a>
		</div>
		<div data-role="content" id="content_page03">
			<ul data-role="listview" name="sfjc">
				<li><a href="#" onClick="gets_values('闽')">闽</a></li>
				<li><a href="#" onClick="gets_values('浙')">浙</a></li>
			</ul>
		</div>
	</div>
	
	<!-- 视频播放界面 -->
	<div data-role="page" id="mov_page" data-theme="d" data-ajax="false">
			<div data-role="content" id="mov_content">
				<video id="myVideo" src="mov/mov_btn.mp4" autoplay controls loop>
				<source src="mov/mov_btn.mp4" type="audio/mp4"></source>
			</video>
		</div>
	</div>	
	
	<script src="js/movecar.js"></script>
	<script src="js/jquery.mobile-1.4.5.js"></script>
	<script src="js/operator.js"></script>
	<script type="text/javascript">
 	$(function(){
 		var temp = 0;
		$("#confirm").click(function() {
			
		   var txt = "该车主在停车时已登记临时停车报备留言，留言信息如下:";
		   var txt1 = "（该信息三小时内有效，是否继续呼叫车主？）";
		   var cphm = $("#cphm").val();
		   var message = $("#liuyan").val();
		   
		   var op = new AjaxOp();
		   op.dataType = "json";
		   op.url = "parkReport.do?cphm="+cphm;
		   op.callback=function(data){
				$("#userMobilePhone").val(data.userPhoneNumber);
				checkMobile(data.userPhoneNumber);

				if("null" == data.userPhoneNumberSecond){
					var resultMsg = data.resultMsg;
						if(0 == data.userPhoneNumber.length){
							window.wxcs.xcComfirm("该车主未进行登记，找不到号码",window.wxcs.xcComfirm.typeEnum.custom);
						}
						if(0 == temp){
							if(0 != resultMsg.length){
							var msg = window.wxc.xcConfirm(txt+"<br>"+"<h4>"+resultMsg+"</h4>"+"<h5>"+txt1+"</h5>",window.wxc.xcConfirm.typeEnum.warning);	
							}
							temp++;
						}else{
						window.location.href = "tel:"+data.userPhoneNumber;
						}
				}else{
					window.wxcs.xcComfirm(	 	"<span style=\"font-size:14px\">该车辆采集到2条记录,请您选择以下号码联系 : </span>"+"<br>"
											+	"<button id=\"tel1\" style=\"background:#f79c1b;border-radius:3px;border:0px;line-height: 3.0rem; color:white;font-weight:bold;width:100%\">"+"号 码 一"+"</button>"+"<div style=\"border:3px solid #fff\"></div>"
											+	"<button id=\"tel2\" style=\"background:#f79c1b;border-radius:3px;border:0px;line-height: 3.0rem; color:white;font-weight:bold;width:100%\">"+"号 码 二"+"</button>",window.wxcs.xcComfirm.typeEnum.success);
					$("#tel1").click(function(){
						window.location.href = "tel:"+data.userPhoneNumber;
					})
					$("#tel2").click(function(){
						window.location.href = "tel:"+data.userPhoneNumberSecond;
					})
				}				
		   		}
		   op.onError = function(){
		   }
		   op.execute(); 
		   
		}); 
		$("#camera").click(
			function() {
				var txt = "车牌识别正在研发中,后续版本将持续更新";
				window.wxcs.xcComfirm(txt,window.wxcs.xcComfirm.typeEnum.custom);
				
			});
			
		$("#cphm").blur(function() {
			var cphm = $("#cphm").val().toUpperCase();
			
		})
		});
		
		function playlist(a) {
			var MV = document.getElementById("myVideo");
			
			$("#mov_btn").click(function(){
				MV.play();
				})
			$("#first_btn").click(function(){
				MV.play();
				})
		}
		
		/* 车牌号码校验 */
		 function checkNumber(str) {
			var Str = $("#cphm").val().toUpperCase();
			RegularExp =/^[A-Z][A-Z0-9]{5}$/;
			
			if (RegularExp.test(Str)) {
				return true;
			} else {
				window.wxcs.xcComfirm("您还未输入车牌号或所输入的车牌号码有误，请确认后再次输入",window.wxcs.xcComfirm.typeEnum.custom);
				return false;
			}
		}
		 /* 手机号码校验 */
		 function checkMobile(str) {
//		 	RegularExp = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
			RegularExp = /^1[34578]\d{9}$/;
		 	if (RegularExp.test(str)) {
		 		return true;
		 	}else{
		 		window.wxcs.xcComfirm("该车牌号在采集信息时所预留手机号码格式有误，后期我们将尽快处理",window.wxcs.xcComfirm.typeEnum.custom);
		 		return false;
		 	}
		 }
	</script>
</body>
</html>

