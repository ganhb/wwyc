<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>汪汪移车</title>
<link rel="stylesheet" href="css/jquery.mobile-1.4.5.css">
<link rel="stylesheet" href="css/jquery.mobile.icons.min.css" />
<link rel="stylesheet" href="css/yichegou.min.css" />
<link href="css/wemove_index.css" rel="stylesheet" />
<script src="js/video.min.js"></script>
</head>
<body>
	<div data-role="page" id="index" data-theme="d">
		<div data-role="content" id="index_content">
			<div class="con_box">
				<img src="img/move_index.png" style="margin-top:38px;"/>
			</div>
			<div class="new_data" >
				<p style="font-size:21px;font-weight:bold">【汪汪移车】车主出行好帮手</p>
				<p>为车主出行解决移车、泊车难题</p>
			</div>
			<div class="btn_in" id="btn_in" style="margin-top:18px;">
				<button id="mov_btn" onclick="playlist(this)" style="height:55px">
					<span style="padding-left:0px;float:left;border:0px solid red;position:absolute;line-height:50px;height:49px;margin-top:-15px">观看汪汪移车视频</span>
					<span>&nbsp;</span>
				</button>
			</div>
           <div class="pm"></div>
            <video style="width:100%; display:none; position:fixed; top:30%; left:50%; transform:translate(-50%,0);" id="myVideo" src="mov/mov_btn.mp4"  controls >
				<source src="mov/mov_btn.mp4" type="audio/mp4"></source>
			</video>
            
		</div>
	</div>
	
	<div data-role="page" id="mov_page" data-theme="d">
		<div data-role="content" id="mov_content">
        	
			<!--<video id="myVideo" src="mov/mov_btn.mp4" autoplay controls loop>
				<source src="mov/mov_btn.mp4" type="audio/mp4"></source>
			</video>-->
		</div>
	</div>
	<script src="js/jquery-1.11.1.js"></script>
	<script src="js/jquery.mobile-1.4.5.js"></script>
	<script>
		
		function playlist(button) {
			var MV = document.getElementById("myVideo");
			//	var MV = $("#myVideo");
			//	var MV = $("#myVideo").val();
			//alert("button.id : " + button.id);
			//MV.src = "mov/" + button.id + ".mov";
			$(".pm").css('display','block');
			$("#myVideo").show().addClass("zindex");
			
			$(".pm").click(function(){
				$(this).css('display','none');
				$("#myVideo").hide();
				MV.load();
				})
			//alert(MV.src);
			MV.play();
		}
		/* var video = document.getElementById("myVideo");
		function playList(){
			video.src = "mov/" + button.id + ".mov";
			video.play(); 
		} */
	</script>
</body>
</html>

