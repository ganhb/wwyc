<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<title>个人中心</title>
		<link rel="stylesheet" href="css/jquery.mobile-1.4.5.css">
		<link rel="stylesheet" href="css/jquery.mobile.icons.min.css" />
		<link rel="stylesheet" href="css/yichegou.min.css" />
		<link href="css/wemove.css" rel="stylesheet" />
		<link rel="stylesheet" href="css/zxx.lib.css" />
		<link rel="stylesheet" type="text/css" href="css/xcConfirm.css" />
		<script src="js/jquery-1.9.1.js"></script>
		<script src="js/xcConfirm.js"></script>
	</head>
	<body>
		<!--个人中心页-->
		<div data-role="page" id="page_me">
			<!--<div data-role="header" data-position="fixed" id="header_me">
				<a href="#" data-direction="reverse"><img
						src="img/arrow_back.png" />
				</a>
				<h1>
					个人中心
				</h1>
			</div>
			--><div data-role="content" id="content_me">
				<!--头像块-->
				
				<div class="me_message">

					<div class="me_photo">
						<div class="photo">
							<a href="#"><img class="mew" src="img/me.png" />
							</a>
						</div>
						<a href="#">V3</a>
					</div>

					<div class="myname">
						<p>
						<% String userNames = (String) session.getAttribute("userName"); %>
							HI！<%=userNames%>;
						</p>
						<p>
							您是V3会员，距离升级还有：53经验值
						</p>
						<div class="level"></div>
						<p>
							27/80
						</p>
					</div>
				</div>
				<!--内容资料-->
				<div class="myscore" id="myscore" data-role="navbar">
					<ul>
						<li>
							<a href="#page_score" data-theme="d" class="score"
								data-role="button" data-corners="false">
								<p>
									我的积分 180
								</p>
								<p>
									如何获取积分？
								</p> </a>
						</li>
						<li>
							<a href="#page_leave_word" class="baobei" data-theme="d"
								data-role="button" data-corners="false">
								<p class="lh36">
									自主报备
								</p> </a>
						</li>
					</ul>
				</div>

				<h2 class="peo_data">
					个人资料
				</h2>
				<ul class="move_fun" id="move_fun" data-role="listview"
					data-theme="d">

					<li data-icon="none">
						<a href="#page_sign" data-transition="none">我的车辆</a>
					</li>
					<li data-icon="none">
						<a href="#page_mycard" data-transition="none">移车名片</a>
					</li>
					<li data-icon="none">
						<a href="#page_history" data-transition="none">移车记录</a>
					</li>
				</ul>




			</div>
			<div data-role="footer" id="footer_me" data-position="fixed">
				<div class="footer_nav" data-role="navbar">
					<ul>
						<li>
							<a href="#" data-transition="none" data-theme="d"><span
								class="blur focus"></span><span>个人中心</span>
							</a>
						</li>
						<li>
							<a href="#move_index" data-transition="none"><span
								class="blur"></span><span>服务中心</span>
							</a>
						</li>
						<li>
							<a href="#move_share" data-transition="none"><span
								class="blur"></span><span>晒图</span>
							</a>
						</li>
					</ul>
				</div>

			</div>
		</div>
		<!--2016-06-14-->




		<!--移车index-->
		<div data-role="page" id="move_index">
			<!--<div data-role="header" id="header_index" class="header"
				data-position="fixed">
				<a href="#page_me" data-transition="none" data-direction="reverse"><img
						src="img/arrow_back.png" />
				</a>
				<h1>
					我要移车
				</h1>
			</div>
			--><div data-role="content" id="content_index">
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
				<!-- 车牌号移车 -->
				<form method="post" action="" id="frm_carnum">
					<div data-role="fieldcontain" id="num_box">
						<div id="search">

							<div class="input_box">
								<div id="input">
									<!--<textarea rows="1" cols="1" placeholder="请输入/拍摄车牌号"></textarea>-->
									<input type="text" placeholder="请输入/拍摄车牌号" />
								</div>
								<a href="#page04" class="camera"></a>
							</div>
							<a href="#page03"><div id="carD" class="cardress">
									闽
								</div>
							</a>
						</div>


						<textarea placeholder="短信留言（非必填）" id="liuyan"></textarea>
						<a href="#page_inform_s"><button type="submit"
								data-inline="true" id="confirm">
								呼叫车主
							</button>
						</a>

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

		<div data-role="page" id="page_inform_s"><!--
			<div data-role="header" data-position="fixed" id="header_num_s"
				class="header">
				<a href="#move_index" data-direction="reverse"><img
						src="img/arrow_back.png" />
				</a>
				<h1>
					车牌号移车
				</h1>
			</div>
			--><div data-role="content" id="content_num_s">
				<p id="inform_code">
					【移车狗】已将通过callcenter向车主呼叫移车请求了，请耐心等待...
				</p>



				<img src="img/jindu_blur.png" />
				<form method="post" action="" id="frm_cancel">
					<a href="#move_index">
						<button type="submit" class="cancel">
							取消
						</button>
					</a>
				</form>
			</div>

			<div data-role="footer" id="footer_num_s" class="footer"
				data-position="fixed">
			</div>
			<div data-role="footer" data-position="fixed" id="footer_inform_s"
				class="footer">
				<span><a href="#" class="flink">关于移车狗>></a>
				</span>

			</div>
		</div>
		<!--获取积分页-->
		<div data-role="page" id="page_getscore">
			<!--<div data-role="header" data-position="fixed" id="header_getscore">
				<a href="#page_score" data-transition="none"
					data-direction="reverse"><img src="img/arrow_back.png" />
				</a>
				<h1>
					获取积分
				</h1>
			</div>
			--><div data-role="content" id="content_getscore">

				<h2 class="peo_data">
					行为积分
				</h2>

				<ul class="move_fun fun_nav" data-role="navbar">

					<li>
						<a href="#" class="tsw" data-transition="none"><img
								src="img/sign_lit.png" />注册会员</a>
					</li>
					<li>
						<a href="#" data-transition="none"><img
								src="img/carnum_lit.png" />登记车牌号</a>
					</li>
					<li>
						<a href="#" data-transition="none"><img
								src="img/share_lit.png" />晒图</a>
					</li>
				</ul>

				<h2 class="peo_data">
					网购积分
				</h2>
				<ul class="move_fun fun_nav" data-role="navbar">

					<li>
						<a href="#"><img src="img/carserve_lit.png" />参与汽车服务</a>
					</li>

				</ul>




			</div>

		</div>
		<!--我的车辆注册页-->
		<div data-role="page" id="page_sign">
			<!--<div data-role="header" data-position="fixed" id="header_sign">
				<a href="#page_me" data-transition="none" data-rel="back"
					data-direction="reverse"><img src="img/arrow_back.png" />
				</a>
				<h1>
					我的车辆
				</h1>
			</div>
			--><div data-role="content" id="content_sign">
				<%
					String userName = request.getParameter("userName");
					session.setAttribute("userName",userName);
				 %>
				<form method="post" action="reg.do" id="mycar">
					<div data-role="fieldcontain">
						<ul data-role="listview">
							<li>
								<label for="fullname" >
									姓名:
								</label>
								<textarea name="userName"></textarea>
						
							</li>
							<li>
								<label for="photonum">
									手机:
								</label>
								<textarea name="mobilePhone" placeholder="11位手机号码"></textarea>
							</li>
							<li class="carID">
								<label for="carID">
									车牌:
								</label>
								<span id="carID" name="cpsf" onClick="disp_cc()" >闽</span>
								<textarea name="cphm"></textarea>
							</li>
						</ul>

						<div id="hh" style="display: none" class="child">
							<ul>
								<!--可循环-->
								<li class="lis" id="lis" onClick="gets_value('闽')">
									闽
								</li>
								<li class="lis" onClick="gets_value('浙')">
									浙
								</li>
								<li class="lis" onClick="gets_value('湘')">
									湘
								</li>
								<li class="lis" onClick="gets_value('赣')">
									赣
								</li>
							</ul>
							<!--<span onclick="hh.style.display='none'" class=close><a href="#">关闭</a></span>-->
						</div>
						   <button type="submit" >确定</button>
					</div>
				</form>

			</div>

		</div>
		
		
		
		<!--我的积分页-->
		<div data-role="page" id="page_score">
			<div data-role="header" data-position="fixed" id="header_score">
				<a href="#page_me" data-transition="none" data-direction="reverse"><img
						src="img/arrow_back.png" />
				</a>
				<h1>
					积分
				</h1>
			</div>
			<div data-role="content" id="content_score">
				<div class="score_yu">
					<p>
						积分余额
						<br />
						<span>180</span>
					</p>
				</div>
				<a href="#page_getscore" class="btn_sco" data-theme="d"
					data-transition="none" data-role="button">获取积分</a>
				<div class="score_t">
					积分明细
				</div>
				<table class="score_con">
					<tr>
						<td>
							项目积分：
							<br />
							<span>2016/02/03</span>
						</td>
						<td>
							<span>66</span> 积分
						</td>
						<td>
							已领取
						</td>
					</tr>
					<tr>
						<td>
							项目积分：
							<br />
							<span>2016/02/03</span>
						</td>
						<td>
							<span>66</span> 积分
						</td>
						<td>
							已领取
						</td>
					</tr>
				</table>
			</div>

		</div>
		
		
		
		<!--移车名片页-->
		<div data-role="page" id="page_mycard">
			<div data-role="header" data-position="fixed" id="header_mycard">
				<a href="#page_me" data-transition="none" data-direction="reverse"><img
						src="img/arrow_back.png" />
				</a>
				<h1>
					我的移车名片
				</h1>
			</div>
			<div data-role="content" id="content_mycard">
				<div class="qr_code">
					<img src="img/code_p.png" />
				</div>
				<button onClick="">
					下载移车名片
				</button>

			</div>

		</div>
		
		
		
		
		<!--移车记录页-->
		<div data-role="page" id="page_history">
			<div data-role="header" data-position="fixed" id="header_history">
				<a href="#page_me" data-transition="none" data-direction="reverse"><img
						src="img/arrow_back.png" />
				</a>
				<h1>
					我的移车记录
				</h1>
			</div>
			<div data-role="content" id="content_history">

				<table>
					<tr>
						<td class="col_1">
							20
							<span>5月</span>
						</td>
						<td class="col_2">
							二维码移车
							<span>移车地点：</span><span>被移车辆车牌号：</span>
						</td>
						<td class="col_3">
							<br />
							思明区天宝大厦
							<br />
							闽D9984K
						</td>
						<td class="col_4">
							<br />
							已向车主发送信息
						</td>
					</tr>
					<tr>
						<td class="col_1">
							10
							<span>4月</span>
						</td>
						<td class="col_2">
							车牌号移车
							<span>移车地点：</span><span>被移车辆车牌号：</span>
						</td>
						<td class="col_3">
							<br />
							思明区天宝大厦
							<br />
							闽D9984K
						</td>
						<td class="col_4 competed">
							已完成
						</td>
					</tr>
					<tr>
						<td class="col_1">
							06
							<span>2月</span>
						</td>
						<td class="col_2">
							车牌号移车
							<span>移车地点：</span><span>被移车辆车牌号：</span>
						</td>
						<td class="col_3">
							<br />
							思明区天宝大厦
							<br />
							闽D9984K
						</td>
						<td class="col_4 competed">
							已完成
						</td>
					</tr>
					<tr>
						<td class="col_1">
							11
							<span>1月</span>
						</td>
						<td class="col_2">
							二维码移车
							<span>移车地点：</span><span>被移车辆车牌号：</span>
						</td>
						<td class="col_3">
							<br />
							思明区天宝大厦
							<br />
							闽D9984K
						</td>
						<td class="col_4 competed">
							已完成
						</td>
					</tr>
					<tr>
						<td class="col_1">
							01
							<span>1月</span>
						</td>
						<td class="col_2">
							二维码移车
							<span>移车地点：</span><span>被移车辆车牌号：</span>
						</td>
						<td class="col_3">
							<br />
							思明区天宝大厦
							<br />
							闽D9984K
						</td>
						<td class="col_4 competed">
							已完成
						</td>
					</tr>
					<tr>
					</tr>


				</table>




			</div>
		</div>
		
		
		
		
		<!--晒图-->
		<div data-role="page" id="move_share">
			<div data-role="header" id="header_share">
				<a href="#page_me" data-transition="none" data-direction="reverse"><img
						src="img/arrow_back.png" />
				</a>
				<h1>
					晒图
				</h1>
				<a href="#" data-role="button" class="ui-btn-right"><img
						src="img/camera_w.png" />
				</a>
			</div>
			<div data-role="content" id="content_share">
				<!--1-->
				<div class="photo_box">
					<div class="photo_s">
						<span class="hp1">Sue</span>
						<span class="ht1">21</span>
						<img src="img/share01.png">
						<p class="info1">
							晒各种图...
						</p>
					</div>
					<div class="photo_s">
						<span class="hp1">Sue</span>
						<span class="ht1">21</span>
						<img src="img/share03.png">
						<p class="info1">
							晒各种图...
						</p>
					</div>


					<div class="photo_s">
						<span class="hp1">Sue</span>
						<span class="ht1">21</span>
						<img src="img/share03.png">
						<p class="info1">
							晒各种图...
						</p>
					</div>
				</div>
				<!--2-->
				<div class="photo_box">
					<div class="photo_s">
						<span class="hp1">Sue</span>
						<span class="ht1">21</span>
						<img src="img/share02.png">
						<p class="info1">
							晒各种图...
						</p>
					</div>

					<div class="photo_s">
						<span class="hp1">Sue</span>
						<span class="ht1">21</span>
						<img src="img/share04.png">
						<p class="info1">
							晒各种图...
						</p>
					</div>

					<div class="photo_s">
						<span class="hp1">Sue</span>
						<span class="ht1">21</span>
						<img src="img/share01.png">
						<p class="info1">
							晒各种图...
						</p>
					</div>

				</div>
			</div>
			<div data-role="footer" id="footer_share" data-position="fixed">
				<div class="footer_nav" data-role="navbar">
					<ul>
						<li>
							<a href="#page_me" data-transition="none" data-theme="d"><span
								class="blur"></span><span>个人中心</span>
							</a>
						</li>
						<li>
							<a href="#move_index" data-transition="none"><span
								class="blur"></span><span>服务中心</span>
							</a>
						</li>
						<li>
							<a href="#" data-transition="none"><span class="blur focus"></span><span>晒图</span>
							</a>
						</li>

					</ul>

				</div>


			</div>


		</div>
		
		
		
		
		
		<!--移车热线-->
		<div data-role="page" id="move_hotline">
			<div data-role="header" id="header_hotline" data-position="fixed">
				<a href="#page_me" data-transition="none" data-direction="reverse"><img
						src="img/arrow_back.png" />
				</a>
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
						拨通热线
					</button>
				</a>


			</div>
			<div data-role="footer" data-position="fixed" id="footer_hotline"
				class="footer">

				<span><a href="#" class="flink">关于移车狗>></a>
				</span>

			</div>
		</div>
		
		
		
		
		<!--page03选择省简称-->
		<div data-role="page" id="page03">
			<div data-role="header" id="header_page03">
				<a href="#move_index" data-role="button" class=""><img
						src="img/arrow_back.png" />
				</a>
				<h1>
					请选择省份简称
				</h1>
				<a href="#move_index" data-role="button" class="ui-btn-right">完成</a>
			</div>
			<div data-role="content" id="content_page03">
				<!--<form method="post" action="" id="dress_dig"  >
            	<select onChange="chk()" id="dress" data-native-menu="false"data-theme="d" >
                	<option  value="01">闽</option>
                    <option value="02">浙</option>
                    <option value="03">赣</option>
                
                </select>                
            </form>-->

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
		<div data-role="page" id="page04">
			<div data-role="header" id="header_page04">
				<a href="#move_index" data-role="button" class=""><img
						src="img/arrow_back.png" />
				</a>
				<h1>
					请拍下车牌号
				</h1>
				<a href="#move_index" data-role="button" class="ui-btn-right">完成</a>
			</div>
			<div data-role="content">


			</div>
		</div>

		<!--inform-success确认收到请求-->

		<div data-role="page" id="page_inform_success">
			<div data-role="header" data-position="fixed" id="header_inform_s"
				class="header">
				<a href="#move_index" data-direction="reverse"><img
						src="img/arrow_back.png" />
				</a>
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
					</button>
				</a>
			</div>
			<div data-role="footer" id="footer_num_s" class="footer"
				data-position="fixed">
				<span><a href="#" class="flink">关于移车狗>></a>
				</span>
			</div>
		</div>

		<!--评论车主-->

		<div data-role="page" id="page_comment">
			<div data-role="header" data-position="fixed" id="header_comment"
				class="header">
				<a href="#page_inform_success" data-direction="reverse"><img
						src="img/arrow_back.png" />
				</a>
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


$(document).on("pageshow","#move_index",function(){
					var txt=  "将采集您的实时位置，确认开启GPS功能";
					window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.confirm);


});
	</script>


	</body>
</html>
