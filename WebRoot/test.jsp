<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'test.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script>
/*			$.ajax({
			url   	 : "parkReport.do?cphm=" + cphm,
			type  	 : "post",
			async 	 : false, //默认为true 异步   
			dataType : "json",
			cache 	 : false,
			success  : function(data) {
//				alert("分别"+"cphm : "+data.cphm+", userPhoneNumber : "+data.userPhoneNumber); 
//				$("#tel").attr("herf", "tel:" + data.userPhoneNumber);
				window.location.href = "tel:"+data.userPhoneNumber;
					
			},
			error : function(data) {
				alert('error'+data);
			}
			}); 
*/	
	function validateUserInfo() {
        var username = document.getElementById("username").value;
        var password = document.getElementById("password").value;
        if (!username) {
            alert("请输入账号！");
        } else if (!password) {
            alert("请输入密码！");
        } else {
            if (username == "admin" && password == "admin2014") {
                document.getElementById("frm").submit();
            } else {
                alert("账号或密码错误！");
                cancle();
            }
        }
    }
</script>
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	<!-- 
	$(function(){
				$.ajax({
					url     : 'parkReport.do?cphm=' + cphm,
					success	: function(){
						alert("first ajax success");
						if(null == reportMsg){
							var bln = window.confirm("该车主留有一条报备消息，是否显示");
							if (bln === true) {
								window.confirm(reportMsg);
								}
						}
						$("#confirm").click(function() {
							alert("cofirm 提示")
							$.ajax({
							url   : 'parkReport.do?cphm=' + cphm,
							type  : 'post',
							data  : user,
							async : false, //默认为true 异步   
							error : function() {
								alert('error');
							},
							success : function() {
								alert("success");
								var tel = data.tel;
								$("#tel").attr("herf", "tel:" + tel);
								alert("tel:"+tel);
							}
							
						}); 
						$("#camera").click(function() {
							var txt = "车牌识别正在研发中，后续版本将更新";
							window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.confirm);
						});	
				}); -->
</head>

<body>
	<form action="" method="post">
		用户名: <input type="text" name="username" /> <input type="submit" value="submit"/>

		密码: <input type="password" name="userpasswd" />
		<button id="submit" >submit</button>
		<a href="tel:123" id="test">aaa</a>
		<a href="tel:123"></a>

	</form>
	<script>
	$(function(){
		$("#submit").click(function(){
			$("#test").click();
		})
		$("#confirm").click(function() {
			/* var reportMsg = $("#reportMsg").val();
			var cphm      = $("#cphm").val();
			alert("reportMsg : "+reportMsg);
			if(null == reportMsg){
			var bln = window.confirm("该车主留有一条报备消息，是否显示");
			if (bln === true) {
				window.confirm(reportMsg);
				}
			} */
		//	var userPhoneNumber = $("#userPhoneNumber").val();
		//	alert(userPhoneNumber);
			var cphm = $("#cphm").val();
			$("#frm_carnum").serialize();
			var tel = <%= (String)session.getAttribute("userPhoneNumber")%>;
			$.ajax({
			url   : "parkReport.do?cphm=" + cphm,
			type  : "post",
			async : true, //默认为true 异步   
			cache : false,
			error : function() {
				alert('error');
			},
			success : function() {
			//   alert("success");
			//	var tel = data.tel;
			//	$("#tel").attr("herf", "tel:" + tel);
				alert("tel:"+tel); 
			}
			}); 
		}); 
		}); 
		
	</script>
	
				$.ajax({
					url   	 : "reg.do?openId="+openId+"&updateName="+updateName+"&updateSex="+updateSex+"&updateMobile="+updateMobile,
					
					type  	 : "post",
					async 	 : false, //默认为true 异步   
					dataType : "json",
					data 	 : {
								"openId"		: openId,	
								"updateName" 	: updateName,
								"updateMobile"  : updateMobile,
								"updateSex" 	: updateSex
					},
					cache 	 : false,
					success  : function(data) {
						alert("update user  success");
					},
					error : function(data) {
						alert(url);
						alert("update  user error");
					}
				});   		


<script>
	/* 	new Toast({
				context : $('body'),
				message : '用户名不能为空'
			}).show(); 
		*/
		/* 车牌号码校验 */
		 function checkCarNumber(str) {
			var Str = $("#cphm").val().toUpperCase();
			RegularExp =/^[A-Z][A-Z0-9]{5}$/;
			if (RegularExp.test(Str)) {
				return true;
			} else {
				window.wxcs.xcComfirm("您输入的车牌号码有误，请确认后再次输入",window.wxcs.xcComfirm.typeEnum.custom);
				return false;
			}
		}
		 /* 手机号码校验 */
		 function checkMobile(str) {
		 	var Str = $("userMobilePhone").val();
		 	RegularExp = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
		 	if (RegularExp.test(Str)) {
		 		return true;
		 	}else{
		 		window.wxcs.xcComfirm("您所输入的手机格式不正确，请确认后再次输入",window.wxcs.xcComfirm.typeEnum.custom);
		 		return false;
		 	}
		 }
		$('#userName').blur(function() {
			var userName = $("#userName").val();
			if (!userName || typeof(userName)!="undefined" || userName!=0){
				window.wxcs.xcComfirm("您还没未输入用户名",window.wxcs.xcComfirm.typeEnum.custom);
			}
		})
		$('#mobilePhone').blur(function() {
			var mobilePhone = $("#mobilePhone").val();
			if (!mobilePhone || typeof(mobilePhone)!="undefined" || mobilePhone!=0){
				window.wxcs.xcComfirm("您还没未输入手机号",window.wxcs.xcComfirm.typeEnum.custom);
			}
			checkMobile(mobilePhone);
		})
		$('#cphm').blur(function() {
			var cphm = $("#cphm").val();
			if (!cphm || typeof(cphm)!="undefined" || cphm!=0){
				window.wxcs.xcComfirm("您还没未输入车牌号",window.wxcs.xcComfirm.typeEnum.custom);
			}
			checkCarNumber(cphm);
		})
</script>

</body>
</html>
