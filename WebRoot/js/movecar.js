// JavaScript Document
//选择省简称
function chk(){ 
	var obj = document.getElementById("dress"); 
	var strsel = obj.options[obj.selectedIndex].text; 
	var carD = document.getElementById("carD");
	
	carD.innerHTML = strsel;
	} 
	
//select
function gets_id(objName){
 if(document.getElementById){
  return eval('document.getElementById("' + objName + '")');
 }else if(document.layers){
  return eval("document.layers['" + objName +"']");
 }else{
  return eval('document.all.' + objName);
 }
}
//打开DIV层
function disp_cc()
{
 if(gets_id('hh').style.display=='none')
 {
  gets_id('hh').style.display='';
 }
 else
 {
  gets_id('hh').style.display='none';
 }
}
//赋值
function gets_value(str)
{
 gets_id('carID').innerHTML=str; 
 gets_id('hh').style.display='none';
}
//车牌省简称
function gets_values(str)
{
 gets_id('carD').innerHTML=str; 
}
/*时间进度环*/

		var canvas = document.getElementById("canvas");
		var context = canvas.getContext("2d");
		var apply_s = document.querySelector('.apply_s');
		var apply_f = document.querySelector('.apply_f');
		var cancel = document.getElementsByName('cancel');
		var com = document.getElementsByName('comment');
		var again = document.getElementsByName('again');
		
		function drawbg(){
			context.beginPath();
			context.moveTo(185.9,187.1);
			context.arc(300,150,120,Math.PI*0.9,Math.PI*2.6,false);
			//context.lineTo(180,150);
			context.save();
			
			var lineCap = ["butt","round","square"];
			context.strokeStyle = "#aaa";
			context.lineWidth = 12;
			context.lineCap = lineCap[1];
			context.stroke();
		}
		var i = 0;
		function draw(e){
			context.beginPath();
			context.moveTo(185.9,187.1);			
			var k = Math.PI*((2.6-0.9)*i/600+0.9);			
			context.arc(300,150,120,Math.PI*0.9,k,false);
			//context.lineTo(180,150);
			//context.save();			
			var lineCap = ["butt","round","square"];
			context.strokeStyle = "#ff7e2e";
			context.lineWidth = 8;
			context.lineCap = lineCap[1];
			context.stroke();
			//console.log(i);
			i++;
			if(i >= 599){
				i = 0;
				setTimeout (clear,50);	
				setTimeout(drawbg,52);	
			};
			//传进e = true即呼叫成功
			calling(e);		
		}
		//控制时间停止，呼叫切换
		//i即控制时间变量
		function calling(e){	
			if(i == 40){									
					if(e){
						apply_s.style.display ='block';
						cancel.item(0).style.display ='none';
						com.item(0).style.display = 'block';
						stopTime();
						window.clearInterval(timer);						
					}else{
						apply_f.style.display ='block';
						again.item(0).style.display = 'block';
						cancel.item(0).style.display ='none';
						stopTime();
						window.clearInterval(timer);
					}
				}
		}		
		function clear(){
			var width = canvas.width,
				height = canvas.height;
			context.clearRect(0,0,width,height);
		}			
		function time(){
					var text = second;
					context.fillStyle = "#666";
					context.font = "100 60px 'Arial' ";
					context.fillText(text,200,240);		
				}		 	
			var child = document.getElementById('datetime').getElementsByTagName('span');			
			var time = null;				
			function Time(){				
				var t = document.getElementById('datetime').getElementsByTagName('span')[3].innerHTML;
				var hours = child.item(0).innerHTML
				var minute = child.item(1).innerHTML
				var second = child.item(2).innerHTML
				var milliscond = child.item(3).innerHTML
				var width = canvas.width,
					height = canvas.height;
					context.clearRect(180,196,150,60);
				var text = minute+':'+second;
					context.fillStyle = "#666";
					context.font = "100 60px 'Arial' ";
					context.fillText(text,180,246);					
					
				if(milliscond>98){
					if(second<9){
						child.item(2).innerHTML = '0'+(parseInt(second)+1)
					}else{
						child.item(2).innerHTML = parseInt(second)+1
					}
					if(second>58){
						if(minute<9){
							child.item(1).innerHTML = '0'+(parseInt(minute)+1)
						}else{
							child.item(1).innerHTML = parseInt(minute)+1
						}
						if(minute>58){
							if(hours<9){
								child.item(0).innerHTML = '0'+(parseInt(hours)+1)
							}else{
								child.item(0).innerHTML = parseInt(hours)+1
							}
							child.item(1).innerHTML = '00'
						}
						child.item(2).innerHTML = '00'
					}
					child.item(3).innerHTML = 00
					t=0
				}
				else{					
					t++
					if(t<10&&milliscond<10){
						child.item(3).innerHTML = '0'+t
					}else{
						child.item(3).innerHTML =t
					}					
				}
				//console.log(t);
			}
			function startTime(){
				time = window.setInterval(Time,10);				
			}
			function stopTime(){				
				window.clearInterval(time);
				time = null;				
			}
			function resetTime(){
				document.getElementById('datetime').innerHTML = "<span>00</span>:<span>00</span>:<span>00</span>:<span>00</span>";							
			}			
			

/*呼叫进度成功和失败*/
var timer = null;
$(document).on("pageshow","#page_inform_s",function(){
	startTime();
	drawbg();
	e = true;//e为true即呼叫成功，否则呼叫失败
	timer = window.setInterval('draw('+e+')',100);			
$(document).ready(function(){
  	$("#header_num_s a:eq(0),#frm_cancel a:eq(0),#frm_cancel a:eq(1)").click(function(event){
    	i = 0;			
		window.clearInterval(timer);		
		resetTime();
		stopTime();
		clear();
  		});
	$("#frm_cancel a:eq(2)").click(function(event){
    	history.go(0);
  		});
	});	
});
/*时间进度环 end*/
/*定位提醒*/
$(document).on("pageshow","#move_index",function(){
					var txt=  "您好"+"<br/>"+"将采集您的实时位置，确认开启GPS功能 将采集您的实时位置，确认开启GPS功能 将采集您的实时位置，确认开启GPS功能 将采集您的实时位置，确认开启GPS功能 将采集您的实时位置，确认开启GPS功能 将采集您的实时位置，确认开启GPS功能 ";
					window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.confirm);
});
/*点赞红心*/

$(".ht1").click(function(){
			$(this).toggleClass('ht2');
			var likeN = $(this).text();
			if($(this).attr('class') == 'ht1 ht2'){
				console.log(1);
				likeN++;
				}else{
					console.log(0);
					likeN--;
					}
			$(this).text(likeN);						
		})
/*评论星星*/
$(document).on("pageshow","#page_comment",function(){
				$(".star img").attr('src','img/star_b.png');	
				$(".choose").text('请选择');

	});
	
$(".star img").click(function(){
						var n = $(".star img").index(this);						
							for(var i = n;i >= 0; i--){
								$(".star img").eq(i).attr('src','img/star_f.png');
							};
							for(var i = n; i < 5; i++){
								$(".star img").eq(i+1).attr('src','img/star_b.png');
								}
						})
					
	/*评论到达时间*/
	$(".choose").click(function(){
					$(".list_time").fadeToggle();
					})
				$(".fs30").click(function(){
					$(".list_time").hide();
					})
				$(".ct").click(function(){
					var k = $(".ct").index(this);
					var time = $(".ct").eq(k).html();
					$(".ct").eq(k).addClass("focus_ct").siblings().removeClass("focus_ct");
					$("#ok").click(function(){
						$(".choose").text(time);
						})
					})
				
	//我的基本信息 选项的显示隐藏
                   	$(".proID").click(function(){
						
						$(".pro").toggle();
						$(".pm").toggle();
						//$(".pm").toggleClass('zindex');
						$(".pro").toggleClass('zindex');
						$(".pm").click(function(){
							$(this).hide();				
							$(".pro").hide().removeClass('zindex');
								
						});
						
						$(".pro li").click(function(){
						var txt = $(this).text();
						$(".proID span").text(txt);
						
						});
						
					})
					
					
					$(".dressID").click(function(){
						
						$(".eng").toggle();
						$(".pm").toggle();
						$(".eng").toggleClass('zindex');
						$(".pm").click(function(){
							$(this).hide();				
							$(".eng").hide().removeClass('zindex');
								
						});
						
						$(".eng li").click(function(){
						var txt = $(this).text();
						$(".dressID span").text(txt);
						
						});
						
					})
					
			//基本信息修改
			
			$(document).ready(function(){
				$(".write_n").change(function(){
					var txt = $(this).val();
					
					$(".xingM").text('姓名：'+'  '+txt);
				});
				
				$("[name ='gender' ]").change(function(){
					var txt = $(this).val();					
					$('.xingB').text('性别：'+'  ' +txt);
					
				})
				
				$(".phoneN").change(function(){
					var txt = $(this).val();
										
					$('.shouJ').text('联系方式：'+'  ' +txt);
					
				})	
				function newMe(){
					if(this.addEventListener){
					元素对象.addEventListener(
						"事件名",方法对象,是否在捕获阶段触发);
					}else{
					元素对象.attachEvent("事件处理函数名",方法对象);
					}
					
					
					}
				
			
			});
		function newMessage(){
			location.hash ="#page_my_message";
			
			}
	/*验证码*/
				function change_code(){
				
				$(".code_btn").hide();
				$(".code_btnW").show();				
				timer=setInterval(calc,1000);
				timer2 = setTimeout(stop,59000);				
				var n = 59;
				$(".code_btnW").text( n+'秒重新发送') ;
				
			}
			 
             function calc(){
				var h1= document.querySelector(".code_btnW");
			 	var n=parseInt(h1.innerHTML);//5
				n--;//4
				n >= 9 ? h1.innerHTML=n+h1.innerHTML.substring(2):h1.innerHTML=n+h1.innerHTML.substring(1);
				//4+秒后自动关闭
			}
			
			var timer=null;
			var timer2=null;
			
			function stop(){
				clearInterval(timer);
				clearTimeout(timer2);
				timer=null;
				timer2=null;
				$(".code_btnW").hide();
				$(".code_btn").show();
				
			}
			
			
			function change_code1( ){
				
				$(".code_btn1").html('59秒重新发送');
				$(".code_btn1").addClass("code_btnW1");
				
								
				timer=setInterval(calc1,1000);
				timer2 = setTimeout(stop1,59000);				
								
			}
			 
             function calc1(){
				
			 	var n= parseInt($(".code_btn1").html());//5
				n--;//4
				$(".code_btn1").html(n+'秒重新发送');
				//4+秒后自动关闭
			}
			
			var timer=null;
			var timer2=null;
			
			function stop1(){
				clearInterval(timer);
				clearTimeout(timer2);
				timer=null;
				timer2=null;
				$(".code_btn1").removeClass("code_btnW1").html("获取验证码");
				
			}
			/*我的车辆 增加信息*/
			$(".more_a").click(function(){
				$(".more_add").toggle();
				if($(".more_a").html() == "∨∨"){
					
					
					$(this).html("&and;&and;");					 
				}else{
					console.log(0);
					$(this).html("&or;&or;");
					
				}	
			})
	/*我的车辆新创建*/
	function newACar(){
	//确认必填信息非空
	//验证填的信息符合规则
	//获取所需信息
	
	/*if($("#name").val() == ""){
		$("#name").focus();		
		}else if( $("#phone").val() == "" ){
			
			$("#phone").focus();			
			
		}else if($("#phone").val() != ""){			
			checkPhone();		
		}else if($("#code").val() == "" ){
			$("#code").focus();	
			alert(0);		
		}else if($("#carNum").val() == "" ){
			$("#carNum").focus();
		}*/
		
			//alert("success");
			//location.hash="#page_car_add"
			
	 
	switch( true ){
		case $("#name").val() == "":
			$("#name").focus();
			break;
		case $("#phone").val() == "":
			$("#phone").focus();
			break;
		case ! checkPhone():
			
			break;
		case $("#code").val() == "":
			
			$("#code").focus();
			break;
		case $("#carNum").val() == "":
			
			$("#carNum").focus();
			break;		
		case ! checkCarNum():
		
			break;
		default:
			
			newone();
			location.hash="#page_car_add"
			
		}
	//实现页面跳转
	}
	
	function checkPhone(){
		var sPhone = document.getElementById("phone").value ;		
			if(!(/^1[3|4|5|7|8]\d{9}$/.test(sPhone))){			
				alert("输入的手机号格式不正确");				
				return false;		
			}	
			return true;
	}
	
	
	function checkCarNum(){
	// var re= ;	 
	 //^[\u4e00-\u9fa5]{1}[A-Z]{1}[A-Z_0-9]{5}$/;
	 var scarnum = document.getElementById("carNum").value;
		if(!(/[A-Z0-9]{5}/.test(scarnum))){
              alert("输入的车牌号格式不正确");
              return false;
            }
			return true;
	}
	/*创建新车牌信息*/
	function newone(){
				//获取姓名
				var name = $("#name").val();
				//手机
				var phoneNum = $("#phone").val();
				var phonehide = phoneNum.substring(0,3) + "****" + phoneNum.substr(-5,4);				
				//车牌号
				var carId ="车牌号：" + $(".provincename").val()+ $(".cityname").val() +" " + $("#carNum").val();
				//新建节点				
				var carNum = $("#carNum").val();
				var newtr = document.createElement("tr");
				var newtd =document.createElement("td");
				    newtd.className = "my_car"
				var newa = document.createElement("a");
					newa.style.position = "relative";
				var newspan = document.createElement("span");
					newspan.className = "my_mess";
				var newfs18 = document.createElement("span");
					newfs18.className = "fs18";
				var txt = document.createTextNode(name);
					
				//var txt2 = document.createTextNode();
				var newbr = document.createElement("br");
				var newbr1 = document.createElement("br");
				var txt3 = document.createTextNode(phonehide);
				var txt4 = document.createTextNode(carId);
				
				var addTr = document.getElementById("addNewCar");
				
				var addTable = document.querySelector(".add_newCon");
					addTr.parentNode.insertBefore(newtr,addTr);
					newtr.appendChild(newtd);
					newtd.appendChild(newa);
					newa.appendChild(newspan);
					newspan.appendChild(newfs18);					
					newfs18.appendChild(txt);
					
					newspan.appendChild(newbr);
					newspan.appendChild(txt3);
					newspan.appendChild(newbr1);
					newspan.appendChild(txt4);		
					
				}
	/*创建添加新车辆*/
	function add_new_c(){
			var addC1 = document.querySelector(".addC1");
			var addC2 = document.querySelector(".addC2");
			var addC3 = document.querySelector(".addC3");
			var addC4 = document.querySelector(".addC4");
			var newRow = document.createElement('tr');
			
			var td1 = newRow.insertCell(newRow.cells.length);
			td1.innerHTML=addC1.innerHTML;
			td1.setAttribute('class','tar');
			var td2 = newRow.insertCell(newRow.cells.length);
			td2.innerHTML=addC2.innerHTML +"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+ addC3.innerHTML;
			td2.setAttribute('class','pd3');
			var td3 = newRow.insertCell(newRow.cells.length);
			td3.innerHTML=addC4.value;
			
			
			
			var add_new = document.getElementById('add_new_c');
			add_new.parentNode.insertBefore(newRow,add_new);
		
		}
       /*创建新手机号码*/
        function add_new_p(){
			var add1 = document.querySelector(".add1");
			var add2 = document.querySelector(".add2");
			var newRow = document.createElement('tr');
			
			var td1 = newRow.insertCell(newRow.cells.length);
			td1.innerHTML=add1.innerHTML;
			td1.setAttribute('class','tar');
			var td2 = newRow.insertCell(newRow.cells.length);
			td2.innerHTML=add2.value;
			td2.setAttribute('class','pdl1');
			var add_new = document.getElementById('add_new_p');
			add_new.parentNode.insertBefore(newRow,add_new);
		
		}
		
				$(".lit").mouseover(function(){
					$(this).addClass("fbtn").siblings().removeClass("fbtn");
					})
        /*20150825*/
		
		function renew_car(){
			location.replace("#page_sign");	
		}