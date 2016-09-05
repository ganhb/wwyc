<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>我要移车</title>
<link rel="stylesheet" href="css/jquery.mobile-1.4.5.css">
<link rel="stylesheet" href="css/jquery.mobile.icons.min.css" />
<link rel="stylesheet" href="css/yichegou.min.css" />
<link href="css/wemove_apply.css" rel="stylesheet"/>
<script src="js/html2canvas.js"></script>
    
</head>
<body>
<!-- 通过后台获取数据 -->
<%
	String userCpsf = (String) session.getAttribute("userCpsf");
	String userCphm = (String) session.getAttribute("userCphm");
	String list = (String) session.getAttribute("list");	
	if(null == userCpsf || null == userCphm){
		userCpsf = "";
		userCphm = "";
	}
 %>
<!--生成移车贴-->
    <div data-role="page" id="page_ccard" style="background:#f2f2f4;" >
  		
      <div data-role="content" id="content_ccard">
        
        
        <div class="add_newCar">                 
          <c:foreach items="${list}" var="list" varStatus="status">
              <div class="new_car"><img width="20" src="img/check_b.png"/></div>               		
               <div class="new_calling" id="img_content">
                <img src="img/calling_card.png"  id="DemoImg" border="0"/>
               	 <span class="phone_num">400-8208888</span>
                 <span class="car_num">${list.getCpsf()} ${list.getCphm()}</span>
              </div> 
          </c:foreach>    
			 <!-- 新增按钮 -->               
           	 <div class="add_new"><a href="#page_apply"></a></div> 
        </div>
       
      </div><!-- end of content --> 
  		<div data-role="footer" id="footer_ccard" data-position="fixed">
      	  <div align="center">
      	  	<a class="download" data-role="none" id="download_img"> 
      	  		<input type="button" value="下载" data-theme="d"/>
      	  	</a>
      	  </div>
        </div>
  		<button id="test" value="test">
  </div>


 	<!--提交成功-->
    <div data-role="page" id="page_apply_s" style="background:#f2f2f4;">
        <div data-role="content" id="content_apply_s">
             <h3 class="success">提交申请成功</h3>
   			 <span id="time" style="display:none;">3</span> 
        </div>
   </div>
<!--申请移车名片-->
      <div data-role="page" id="page_apply" data-theme="d">
         <div data-role="content" id="content_apply">
         <div class="pm" style="display:none;"></div>
         	<p class="con_t">为了享受便捷的移车服务，请认真填写一下信息</p>
            
            <form method="post" class="mgpd0" id="apply_form">
         	
            	<ul data-role="listview" class="bgw"  >
                	<li><label><span class="mainc">*</span>姓名:</label><div class="input_for"><input name="userName" id="userName"/></div> </li>
                   <li><label><span class="mainc">*</span>手机:</label><div class="input_for"><input placeholder="11位手机号码" name="userMobile" id="userMobile"/></div></li>
                    <li style=" padding-left:;"><label><span class="mainc">*</span>验证码:</label><div class="input_for codes"><input placeholder="手机验证码" name="authCode" id="authCode" /></div> <div onClick="change_code()"  class="code_btn">获取验证码</div>
                    
                    <div class="code_btnW" style="display:none;">59秒后重新发送</div></li>
                    
                    
                   <li class="d_box"><label><span class="mainc">*</span>车牌号:</label>
                       
                       <select class="car" data-role="none" id="userCpsf">
                       		<option>闽</option>
                            <option>浙</option>
                            <option>赣</option>
                       </select>
                       <select class="car" data-role="none" id="userCphm">
                       		<option>A</option>
                            <option>B</option>
                            <option>C</option>
                       </select>
                      
                   <div class="input_for"><input/></div></li>
                     <li><label><span class="mainc"></span>获取方式:</label><span class=" select_d"><span class="way">请选择  </span>		</span></li>
                     <li class="sq" style="display:none;" name="userMethod" id="userMethod">
						 <span>申请邮寄</span><br/><span>自主打印</span>
					 </li>
                      <li class="li_dr" style="display:none;"><label><span class="mainc">*</span>地址:</label>
                        <div id="productList"> </div>
                     
                     
                      </li>
                     
                       <li class="li_dr" style="display:none;"><div class="input_for detl_dr"><input placeholder="详细地址"/></div></li>
                	  
                </ul>
            
            <a href="#page_ccard" class="submit_btn"> <button type="submit" data-theme="d" >提交</button></a>
            <a href="#page_apply_s" class="submit_btn1"> <button type="submit" data-theme="d" >提交</button></a>
    	</form>
         </div> 
              
    </div>
  
<script src="js/jquery-1.11.1.js"></script>
<script src="js/jquery.mobile-1.4.5.js"></script>
<script src="js/apply.js"></script>
<script>
	/*长按事件*/
	$(function(){
		$("#img_content").bind("taphold", tapholdHandler){
			function tapholdHandler(event){
				alert("haha laozioK");
			}
		}
	})
	/*下载按钮事件*/
	$("#download_img").click(function(){
		html2canvas($("#content"), {
        	onrendered: function(canvas) {
          	    document.body.appendChild(canvas);//出现
        	}
		});
	});
	/* +号按钮 */
	$("#add_new_card").click(function() {
		$.mobile.changePage("#page_apply", {
			transition : "slideup"
		});
	})
	$("#userSubmit").click(function() {
		var userName = $("#userName").val();
		var userMobile = $("#userMobile").val();
		var authCode = $("#authCode").val();
		var userCpsf = $("#userCpsf option:selected").text();
		var userCphm = $("#userCphm").val();
		var userMethod = $("#userMethod").text();
		$.ajax({
			url : "userCard.do?username=" + userName + "&mobile=" + mobile,
			type : "post",
			async : true,
			dataType : "json",
			data : {
				"userName" : userName,
				"userMobile" : userMobile,
				"authCode" : authCode,
				"userCpsf" : userCpsf,
				"userCphm" : userCphm,
				"userMethod" : userMethod
			},
			cache : false,
			success : function(data) {
				alert("success\n");
			},
			error : function(data) {
				alert("error\n");
			}
		})
	})
</script>

</body>
</html>

