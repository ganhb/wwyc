// JavaScript Document

/* 长按*/
/*$.fn.longpress = function(fn){
		var time = null;
		var ths = this;

		for(var i=0; i<ths.length; i++){
			ths[i].addEventListener('touchstart', function(event){
				time = setTimeout(fn, 800);
			}, false)
			ths[i].addEventListener('touchend', function(event){
				clearTimeout(time);
			}, false)
		}
	}

	$('.new_car img').longpress(function(){
		alert(1);
	})*/


/*显示获取方式*/
$(".new_car img").click(function(){
			if($(this).attr('src') == 'img/check_b.png'){
			$(this).attr('src','img/check_f.png');
			}else{
			$(this).attr('src','img/check_b.png');	
			}
		})
/*选择获取方式*/

$(".select_d").click(function(){
					$(".sq").toggle();
					$(".sq span").click(function(){
						var txt = $(this).text();
						$(".way").text(txt);
						$(".sq").hide();
					})	
				})
				$(".sq span").eq(0).click(function(){
					
					$(".li_dr").show();
					$(".submit_btn1").show();
					$(".submit_btn").hide();
					}).siblings(this).click(function(){					
						$(".li_dr").hide();
						$(".submit_btn").show();
						$(".submit_btn1").hide();
					})
					
//省份市县模拟数据
	var address = [
		{"cno":10,"pname":"福建",
						  "children":[
			{"cno":1010,"pname":"福州",
								"children":[
				{"cno":101010,"pname":"鼓楼区"},
				{"cno":101020,"pname":"仓山区"},
				{"cno":101030,"pname":"台江区"}
											]
			},
			
			{"cno":1020,"pname":"厦门",
								"children":[
				{"cno":102010,"pname":"思明区"},
				{"cno":102020,"pname":"湖里区"},
				{"cno":102030,"pname":"集美区"}
											]
			
			},
			{"cno":1030,"pname":"泉州",
								"children":[
				{"cno":103010,"pname":"鲤城区	"},
				{"cno":103020,"pname":"丰泽区"},
				{"cno":103030,"pname":"洛江区"}
											]
			}
									   ]
		},
		
		{"cno":20,"pname":"浙江",
						  "children":[
			{"cno":2010,"pname":"杭州",
								"children":[
				{"cno":201010,"pname":"西湖区"},
				{"cno":201020,"pname":"拱墅区"},
				{"cno":201030,"pname":"上城区"}
											]
			},
			
			{"cno":2020,"pname":"宁波",
								"children":[
				{"cno":202010,"pname":"海曙区"},
				{"cno":202020,"pname":"江东区"},
				{"cno":202030,"pname":"余姚区"}
											]
			
			},
			{"cno":2030,"pname":"温州",
								"children":[
				{"cno":203010,"pname":"鹿城区	"},
				{"cno":203020,"pname":"龙湾区"},
				{"cno":203030,"pname":"洞头区"}
											]
			}
									   ]
		},
		
		{"cno":10,"pname":"江西",
						  "children":[
			{"cno":1010,"pname":"南昌",
								"children":[
				{"cno":101010,"pname":"东湖区"},
				{"cno":101020,"pname":"西湖区"},
				{"cno":101030,"pname":"湾里区"}
											]
			},
			
			{"cno":1020,"pname":"景德镇",
								"children":[
				{"cno":102010,"pname":"珠山区"},
				{"cno":102020,"pname":"昌平区"},
				{"cno":102030,"pname":"乐平区"}
											]
			
			},
			{"cno":1030,"pname":"九江",
								"children":[
				{"cno":103010,"pname":"浔阳区	"},
				{"cno":103020,"pname":"庐山区"},
				{"cno":103030,"pname":"瑞昌市"}
											]
			}
									   ]
		}		
		];
	var plist = [
		{"pno":10,"pname":"大家电","children":[
			{"pno":1010,"pname":"冰箱","children":[
				{"pno":101010,"pname":"单开门"},
				{"pno":101020,"pname":"双开门"},
				{"pno":101030,"pname":"三开门","children":[
					{"pno":10103010,"pname":"国产三开门"},
					{"pno":10103010,"pname":"进口三开门"},
					{"pno":10103010,"pname":"平行进口三开门"}
				]}
			]},
			{"pno":1020,"pname":"洗衣机"},
			{"pno":1030,"pname":"电视机"}
		]},
		{"pno":20,"pname":"小家电","children":[
			{"pno":2010,"pname":"刮胡刀"},
			{"pno":2020,"pname":"电饭锅"},
			{"pno":2030,"pname":"手电筒"}
		]},
		{"pno":30,"pname":"书籍"},
		{"pno":30,"pname":"食品"}
	];
	
	var div = document.querySelector('#productList');
	
	function createSelect(plist){
			//创建Select，并根据数据添加option
			var select =document.createElement('select');
			select.setAttribute('data-role','none');
			var option = new Option('- 请选择 -',-1);
			
			select.add(option);
			for(var i=0;i<plist.length;i++){
				var p = plist[i];//产品类别对象
				var option = new Option(p.pname,p.cno);
				select.add(option);				
				}
				//把新建的select添加为div的子元素
				div.appendChild(select);
				//为select绑定选项改变事件的处理方法
				select.onchange = function(){
					//只有用户选择了某项，其后的select都应该删除
					while(this.nextSibling){
						div.removeChild(this.nextSibling);
						
						}
						if(this.selectedIndex > 0){
							var children = plist[this.selectedIndex - 1].children;
							console.log(children);
							if(children){
								createSelect(children);//
															}
							}
					}
		}
		//createSelect(plist);
		createSelect(address);
				
//车牌号，省份，市县选项的显示隐藏
                   /*	$(".carID").click(function(){
						
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
						$(".carID span").text(txt);
						
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
						
					})*/
					
					/*$(".province").click(function(){
						
						$(".pro1").toggle();
						$(".pm").toggle();
						$(".pro1").toggleClass('zindex');
						$(".pm").click(function(){
							$(this).hide();				
							$(".pro1").hide().removeClass('zindex');
								
						});
						
						$(".pro1 li").click(function(){
						var txt = $(this).text();
						$(".province span").text(txt);
						
						});
						
					})
					
					$(".city").click(function(){
						
						$(".cities").toggle();
						$(".pm").toggle();
						$(".cities").toggleClass('zindex');
						$(".pm").click(function(){
							$(this).hide();				
							$(".cities").hide().removeClass('zindex');
								
						});
						
						$(".cities li").click(function(){
						var txt = $(this).text();
						$(".city span").text(txt);
						
						});
						
					})
					
					$(".county").click(function(){
						
						$(".counties").toggle();
						$(".pm").toggle();
						$(".counties").toggleClass('zindex');
						$(".pm").click(function(){
							$(this).hide();				
							$(".counties").hide().removeClass('zindex');
								
						});
						
						$(".counties li").click(function(){
						var txt = $(this).text();
						$(".county span").text(txt);
						
						});
						
					})*/
$(".sq span").on('tap',function(){
	$(this).addClass('hover').siblings().removeClass('hover');
	})

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