<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
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
		<script src="./js/jquery-1.9.1.js"></script>
		<script src="./js/xcConfirm.js"></script>
	</head>
	<body>

		<!--2016-6-20-->




		<!--移车index-->
		<div data-role="page" id="move_index"
			style="background-color: #f2f2f4;">
			<!--
			<div data-role="header" id="header_index" class="header"
				data-position="fixed">
				<a href="#page_me" data-transition="none" data-direction="reverse"><img
						src="img/arrow_back.png" /> </a>
				<h1>
					我要移车
				</h1>
			</div>
			-->
			<div data-role="content" id="content_index">
				<div data-role="navbar">
					<ul>
						<li>
							<a href="#" id="movecar" class="ui-btn-active  ui-state-persist "
								data-theme="d">车牌号移车</a>
						</li>
						<li>
							<a href="#move_hotline" id="movecar1" data-transition="none"
								data-theme="d">全国统一移车热线</a>
						</li>
					</ul>
				</div>
				<form method="post" action="" id="frm_carnum">
					<div data-role="fieldcontain" id="num_box">
						<div id="search">

							<div class="input_box">
								<div id="input">
									<input type="text" placeholder="请输入/拍摄车牌号" />
								</div>
								<a href="#page04" class="camera"></a>
							</div>
							<a href="#page03"><div id="carD" class="cardress">
									闽
								</div> </a>
						</div>
					<form action="callSMS.do" method="post">
						open_id:<%=request.getSession().getAttribute("openId")%>
						<textarea placeholder="短信留言（非必填）" id="message" name="message">
						</textarea>
					
							<button type="submit" data-inline="true" id="confirm">
								呼叫车主
							</button> 
					</form>
			<!-- 判断注释 <a href="#page_inform_s"></a> -->
						

					</div>
				</form>


			</div>
			<div data-role="footer" data-position="fixed" id="footer_index"
				class="footer">

				<span><a href="#page_inform_success" class="flink">关于移车狗>></a>
				</span>

			</div>

		</div>
		<!--pagetow-->

		<div data-role="page" id="page_inform_s"
			style="background-color: #f2f2f4;">
			<div data-role="header" data-position="fixed" id="header_num_s"
				class="header">
				<a href="#move_index" data-direction="reverse"><img
						src="img/arrow_back.png" /> </a>
				<h1>
					车牌号移车
				</h1>
			</div>
			<div data-role="content" id="content_num_s">
				<p id="inform_code">
					【移车狗】已将通过callcenter向车主呼叫移车请求了，请耐心等待...
				</p>
				<!--	<img src="img/jindu_blur.png"/>-->
				<div id="stage" style="margin: 0 auto; width: 320px; height: 320px;">
					<canvas id="canvas" width="320" height="320">您的浏览器不支持Canvas</canvas>
				</div>
				<script>
	var canvas = document.getElementById("canvas");
	var context = canvas.getContext("2d");
	function draw() {

		context.beginPath();
		context.moveTo(160, 160);
		context.fillStyle = "#cacaca";
		context.arc(160, 160, 158, Math.PI * 0, Math.PI * 2, true);
		context.closePath();
		context.fill();

	}
	draw();
	var i = 0
	function draw1() {
		var canvas = document.getElementById("canvas");
		var context = canvas.getContext("2d");

		var star = new Date(), finish;

		context.beginPath();
		context.moveTo(160, 160);
		context.fillStyle = "#f79c1b";
		context.strokeStyle = "#f79c1b"
		context.arc(160, 160, 158, Math.PI * 0, Math.PI * 2 * i / 60, true);
		context.closePath();
		context.fill();
		//context.globalCompositeOperation ="destination-out";
		context.beginPath();
		context.moveTo(160, 160);
		context.fillStyle = "red";
		context.arc(160, 160, 146, Math.PI * 0, Math.PI * 2 * i / 60, true);
		context.closePath();
		context.fill();
		context.stroke();
		console.log(i);
		if (i = 60) {
			i = 0;
		}
		finish = +new Date();
		setTimeout(draw1, (1000 / 60) - (finish - star));
	}
	draw1();
</script>

				<form method="post" action="" id="frm_cancel">
					<a href="#move_index">
						<button type="submit" class="cancel">
							取消
						</button> </a>
				</form>
			</div>

			<div data-role="footer" id="footer_num_s" class="footer"
				data-position="fixed">
				<!--	<p><span><a href="#">注册</a></span>【移车狗】会员<br/>领取专属您的移车二维码牌照</p>-->
			</div>
			<div data-role="footer" data-position="fixed" id="footer_inform_s"
				class="footer">
				<span><a href="#" class="flink">关于移车狗>></a> </span>

			</div>
		</div>

		<!--移车热线-->
		<div data-role="page" id="move_hotline"
			style="background-color: #f2f2f4;">
			<div data-role="header" id="header_hotline" data-position="fixed">
				<a href="#page_me" data-transition="none" data-direction="reverse"><img
						src="img/arrow_back.png" /> </a>
				<h1>
					我要移车
				</h1>
			</div>
			<div data-role="content" id="content_hotline">
				<div data-role="navbar">
					<ul>
						<li>
							<a href="#move_index" id="movecar2" data-transition="none"
								data-theme="d">车牌号移车</a>
						</li>
						<li>
							<a href="#" id="movecar3"
								class="ui-btn-active  ui-state-persist " data-theme="d">全国统一移车热线</a>
						</li>
					</ul>
				</div>
				<a href="#"><button type="" class="p12" data-inline="true"
						id="confirm1">
						<a href="tel:400-100-123" style="font-color: white">拨通热线</a>
					</button> </a>

				</hr>
				<form action="">
					<input type="submit" name="submit" value="签到">
				</form>
			</div>
			<div data-role="footer" data-position="fixed" id="footer_hotline"
				class="footer">

				<span><a href="#" class="flink">关于移车狗>></a> </span>

			</div>
		</div>
		<!--page03选择省简称-->
		<div data-role="page" id="page03" style="background-color: #f2f2f4;">
			<div data-role="header" id="header_page03">
				<a href="#move_index" data-role="button" class=""><img
						src="img/arrow_back.png" /> </a>
				<h1>
					请选择省份简称
				</h1>
				<a href="#move_index" data-role="button" class="ui-btn-right">完成</a>
			</div>
			<div data-role="content" id="content_page03">


				<ul data-role="listview">
					<li>
						<a href="#" onClick="gets_values('闽')">闽</a>
					</li>
					<li>
						<a href="#" onClick="gets_values('浙')">浙</a>
					</li>
					<li>
						<a href="#" onClick="gets_values('赣')">赣</a>
					</li>

				</ul>


			</div>
		</div>
		<!--camera-->
		<div data-role="page" id="page04" style="background-color: #f2f2f4;">
			<div data-role="header" id="header_page04">
				<a href="#move_index" data-role="button" class=""><img
						src="img/arrow_back.png" /> </a>
				<h1>
					请拍下车牌号
				</h1>
				<a href="#move_index" data-role="button" class="ui-btn-right">完成</a>
			</div>
			<div data-role="content">


			</div>
		</div>

		<!--inform-success确认收到请求-->

		<div data-role="page" id="page_inform_success"
			style="background-color: #f2f2f4;">
			<div data-role="header" data-position="fixed" id="header_inform_s"
				class="header">
				<a href="#move_index" data-direction="reverse"><img
						src="img/arrow_back.png" /> </a>
				<h1>
					车牌号移车
				</h1>
			</div>
			<div data-role="content" id="content_inform_s" class="content_s">

				<p id="inform_code">
					【移车狗】已将通过callcenter向车主呼叫移车请求了，请耐心等待...
				</p>
				<div class="timers">
					<img class="jindu" src="img/jindu.png" />
					<p class="time">
						0:43:00
					</p>
				</div>
				<p align="center">
					【移车狗】确认车主收到您的请求
				</p>
				<a href="#page_comment">
					<button type="submit" data-theme="d" class="cancel wm">
						评论车主
					</button> </a>
			</div>
			<div data-role="footer" id="footer_num_s" class="footer"
				data-position="fixed">
				<span><a href="#" class="flink">关于移车狗>></a> </span>
			</div>
		</div>

		<!--评论车主-->

		<div data-role="page" id="page_comment"
			style="background-color: #f2f2f4;">
			<div data-role="header" data-position="fixed" id="header_comment"
				class="header">
				<a href="#page_inform_success" data-direction="reverse"><img
						src="img/arrow_back.png" /> </a>
				<h1>
					评论车主
				</h1>
			</div>
			<div data-role="content" id="content_comment">
				<div data-role="navbar">
					<ul>
						<li>
							<a href="#" id="com01" class="ui-btn-active  ui-state-persist "
								data-theme="d">我的评论</a>
						</li>
						<li>
							<a href="#" id="com02" class="" data-theme="d">他人评论</a>
						</li>
					</ul>

				</div>
				<div class="comment01">
					<p class="ctitle">
						评论一下车主吧
					</p>
					<div class="star">
						<img src="img/star_f.png" />
						<img src="img/star_b.png" />
						<img src="img/star_b.png" />
						<img src="img/star_b.png" />
						<img src="img/star_b.png" />
					</div>
					<p class="atime">
						车主到达时间
					</p>
					<div class="choose">
						请选择
					</div>
					<ul class="list_time" style="display: none;">
						<li class="first">
							<div data-role="navbar">
								<ul>
									<li>
										<a href="#" id="cancel" data-theme="d"
											style="text-align: left;">取消</a>
									</li>
									<li>
										<a href="#" id="ok" data-theme="d" style="text-align: right;">确定</a>
									</li>
								</ul>


							</div>

						</li>
						<li>
							5分钟
						</li>
						<li>
							10分钟
						</li>
						<li>
							15分钟
						</li>
						<li>
							20分钟
						</li>
						<li>
							25分钟
						</li>
						<li>
							30分钟
						</li>
					</ul>
				</div>
				<button id="com_btn" data-theme="d" type="submit">
					提交评论获得100积分
				</button>
			</div>
			<div data-role="footer" id="footer_comment" class="footer"
				data-position="fixed">

			</div>
		</div>

		<script src="js/jquery-1.11.1.js"></script>
		<script src="js/movecar.js"></script>
		<script src="js/jquery.mobile-1.4.5.js"></script>
		<script>
	$(document).on("pageshow", "#move_index", function() {
		var txt = "将采集您的实时位置，确认开启GPS功能";
		window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.confirm);

	});
</script>


	</body>
</html>

