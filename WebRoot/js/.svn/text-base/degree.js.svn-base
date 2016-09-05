//省份市县模拟数据
	var address = [
		{"cno":10,"pname":"福建",
						  "children":[
			{"cno":1010,"pname":"福州",
								"children":[
				{"cno":101010,"pname":"鼓楼区"},
				{"cno":101020,"pname":"仓山区"},
				{"cno":101030,"pname":"马尾区"},
				{"cno":101040,"pname":"晋安区"},
				{"cno":101050,"pname":"台江区"},
				{"cno":101060,"pname":"福清市"}
											]
			},
			
			{"cno":1020,"pname":"厦门",
								"children":[
				{"cno":102010,"pname":"思明区"},
				{"cno":102020,"pname":"湖里区"},
				{"cno":102030,"pname":"集美区"},
				{"cno":102040,"pname":"同安区"},
				{"cno":102050,"pname":"海沧区"},
				{"cno":102060,"pname":"翔安区"}
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
	
	function createSelect(plist , id){
			//创建Select，并根据数据添加option
			var div = document.getElementById(id);
			var select =document.createElement('select');
			select.setAttribute('data-role','none');
			var option = new Option('请选择',-1);
			
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
								createSelect(children,id);//
															}
							}
					}
		}
		//createSelect(plist);
		createSelect(address,'productList');
		createSelect(address,'productList1');