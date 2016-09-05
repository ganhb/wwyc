// JavaScript Document
/*
 *	右击菜单	
 *  使用前，需确保导入jquery
 *	【使用】
 *	 var contextMenu=new ContextMenu("targetId","MenuId",clickEvent);
 *   contextMenu.useDefaultStyle();//启用样式
 *	 contextMenu.bindRightClick();//绑定右击时间
 *   【html界面的菜单格式】
 *   <div id="menuId"> //若使用自定义样式，请确保有{display:none; position:absolute;} 样式
 *	 	<ul>
 *			<li id='look'>查看<li>
 *		</ul>
 *	 </div>
 *			
 *  【自定义样式】
 *  方法一：
 *   var contextMenu=new ContextMenu("targetId","MenuId",clickEvent);
 *	 contextMenu.defaultStyle={
 *	 	menu:{//自定义名称
 *	 			name:"#MenuId"//需与菜单id对应，
 *				style:"position:absolute;display:none;"  
 *	 	}
 *		//最终样式会生成在head标签中以<style type="text/css">#MenuId{position:absolute;display:none;}</style>形式生成
 *	 }
 * 方法二：
 *   直接在.css文件中或者html中设置样式，请确保有{display:none; position:absolute;} 样式
 *
 *  @param targetId 绑定的目标id
 *	@param MenuId 菜单的id
 *	@param clickEvent 菜单点击事件集合，格式如下
 *					  var clickEvent={
 *						  	look:function(){alert("你点击了look")} //其中look点击事件源的id
 *						  }
 *
 */
function ContextMenu(MenuId,targetId,clickEvent,isRelationMenus){
	 	var _this=this;
		var className="contextMenu_class";
		this.getClassName=function(){
			return className;
		}
		this.rightClickCallback=function(event){};
		this.setClassName=function(name){
			className=(name!=null&&typeof(name)=='string'&&$.trim(name)!='')?name:className;
			alert(className);
		}
		
		this.targetId=targetId?targetId:null;
		this.isRelationMenus=((isRelationMenus!=null)&&(typeof(isRelationMenus)=='boolean'))?isRelationMenus:false;
		this.MenuId=MenuId?MenuId:"contextMenu_Id";
		var _clickEvent=clickEvent?clickEvent:{};
		this.getTargetId=function(){return $("#"+targetId);}
		this.getMenuId=function(){return $("#"+MenuId);}
		this.setClickEvent=function(clickEvent){_clickEvent=clickEvent?clickEvent:_clickEvent};
		this.getClickEvent=function(){return _clickEvent};
		
		this.setRelationMenu=function(){
				if(_this.getMenuId().length>0)
				var classContent=_this.getMenuId().attr("class");
				classContent=$.trim(classContent);
				var classContents=classContent.split(" ");
				var isExistence=false;
				$.each(classContents,function(index,value){
					if(value==className){
						isExistence=true;
						return false;
					}
				})
				if(!isExistence){
					if($.isEmptyObject(_this.getMenuId().attr("class"))){
						_this.getMenuId().attr("class",className);
					}else{
						_this.getMenuId().attr("class",classContent+" "+className);
					}
				}
			}
		this.defaultStyle={
			menu:{
					name:"."+className,
					style:"position:absolute;display:none;min-width:100px;max-width:120px;background-color:#f0f0f0;border: 1px solid #979797;padding:3px;z-index:9999999999;"
			},
			menu_ul:{
					name:"."+className+" ul",
					style:"line-height: 1em;margin: 0px;list-style-type: none;padding: 0px 0px 0px 0px;-moz-user-select:none;-webkit-user-select:none;-ms-user-select:none;/*IE10*/-khtml-user-select:none;user-select:none;"
			},
			menu_li:{
					name:"."+className+" li",
					style:"padding:5px;padding-left:10px;overflow: hidden;text-overflow: ellipsis;-o-text-overflow: ellipsis;white-space: nowrap;font-family:'Microsoft YaHei',微软雅黑,'MicrosoftJhengHei',华文细黑,STHeiti,MingLiu;font-size:.75em;"						     	 	},
			menu_li_hover:{
					name:"."+className+" li:hover",
					style:"background-color: #edf1f6;border: 1px solid #accff7;cursor:default;"
			},
		}
		var setStyle=new SetStyle(_this.defaultStyle,"style_defaultStyle_"+className);
		this.useDefaultStyle=function(){
				_this.setRelationMenu();
				setStyle.init();
			}
		
		this.hideMenu=function(){
			if(_this.isRelationMenus){
				$("."+className).css("display","none");
			}
			_this.getMenuId().css("display","none");
			$("*").unbind("mousedown",_this.onBodyDown);
		}
		this.showMenu=function(event){
			var x=event.pageX;
			var y=event.pageY;
			_this.getMenuId().css("display","block");
			_this.getMenuId().css("top",y+"px");
			_this.getMenuId().css("left",x+"px");
			$("*").bind("mousedown",_this.onBodyDown);
			_this.rightClickCallback(event);
		}
		this.onBodyDown = function(event) {
			
			
			if (!(event.target.id == _this.targetId||$(event.target).parents("#"+_this.targetId).length > 0||event.target.id == _this.MenuId||$(event.target).parents("#"+_this.MenuId).length > 0)||($(event.target).parents("#"+_this.targetId).length > 0&&event.which!=3)) {
				_this.hideMenu();
			}
		}
		this.bindRightClick=function(target){
				var _target=target?target:_this.targetId;
				var selector=$.getSelector(_target);
				if(_target==null||_target==""||typeof(selector)=='string'||selector.length==0){
					return;
				}
				$(document).bind("contextmenu",function(event){
						if(event.target.id == selector.attr("id")||$(event.target).parents(selector).length > 0||event.target.id == _this.MenuId||$(event.target).parents("#"+_this.MenuId).length > 0){
							return false;
						}
						return true;
						
					});
				selector.mousedown(function(event){
					if(event.which==3){
						_this.showMenu(event);
					}
					
				});
				_this.bindEvent();
		}
		this.bindEvent=function(){
			if(_this.isRelationMenus){
				$.each(_this.getClickEvent(),function(index,value){
					if($.type($.getSelector(index))!='string'&&$.getSelector(index).length>0){
						$.getSelector(index).click(function(e){
							_this.hideMenu();
							value(e,_this.targetId);
						});
					}
				});
			}else{
				$.each(_this.getClickEvent(),function(index,value){
					if($("#"+index).length>0){
						 	$("#"+index).click(function(e){
								_this.hideMenu();
								value(e,_this.targetId);
							});
					}
				});
				
			}
		}
		
		this.unbindEvent=function(){
			
			if(_this.isRelationMenus){
				$.each(_this.getClickEvent(),function(index,value){
					if($.type($.getSelector(index))!='string'&&$.getSelector(index).length>0){
						$.getSelector(index).unbind("click");	
					}
				});
			}else{
				$.each(_this.getClickEvent(),function(index,value){
					if($("#"+index).length>0){
						$("#"+index).unbind("click");	 
					}
				});
			}
			
		}
}
/**
 * 使用前置:无
 * 【使用】
 * 	var setStyle=new SetStyle(defaultStyle);
 *	setStyle.init();//初始化后即可在head文件中生成style样式.
 * 
 * @param defaultStyle 样式集合
 *
 *  【defaultStyle格式】
 *	 defaultStyle={
 *	 	dragon:{
 *	 			name:"#dragon",
 *				style:"margin: 0px;padding: 0px;",
 *	 		   }
 *	 }
 */	 
function SetStyle(defaultStyle,cssId){
	var _this=this;
	this.cssId=cssId?cssId:null;
	this.defaultStyle=defaultStyle?defaultStyle:{};
	this.init=function(){
		var strStyle="";
		for(var style in _this.defaultStyle){
			if(_this.defaultStyle[style].name!=null&&_this.defaultStyle[style].name!=""){
				strStyle=strStyle+_this.defaultStyle[style].name+"{"+_this.defaultStyle[style].style+"}";	
			}
			
		}
		addCss(strStyle,_this.cssId);
	}
	this.getStyle=function(name){
		
		return  _this.defaultStyle[name].style;	
	}
	var addCss= function(cssText,id){
		if($("#"+id).length>0){
			return ;
		}
		var style = document.createElement('style'),  //创建一个style元素
        head = document.head || document.getElementsByTagName('head')[0]; //获取head元素
    	style.type = 'text/css'; //这里必须显示设置style元素的type属性为text/css，否则在ie中不起作用
    	if(id!=null&&id!=''){
    		style.id=id;
    	}
    	if(style.styleSheet){ //IE
        var func = function(){
            try{ //防止IE中stylesheet数量超过限制而发生错误
                style.styleSheet.cssText = cssText;
            }catch(e){
			}
        }
        //如果当前styleSheet还不能用，则放到异步中则行
        if(style.styleSheet.disabled){
            setTimeout(func,10);
        }else{
            func();
        }
    }else{
        //w3c浏览器中只要创建文本节点插入到style元素中就行了
        var textNode = document.createTextNode(cssText);
        style.appendChild(textNode);
    }
    head.appendChild(style); //把创建的style元素插入到head中    
	}
	this.setStyle=function(selectorId,styleName,isAppend){
		var Append=typeof(isAppend)=='boolean'?isAppend:true;
		try{
			var selector=$.getSelector(selectorId); 
			if(Append){
				var style=selector.attr("style")?selector.attr("style"):"";
				selector.attr("style",style+_this.getStyle(styleName));
			}else{
				selector.attr("style",_this.getStyle(styleName));
			}
		}catch (e){
			throw(e);
		}
		
	}
}
/*
 * 获得选择器
 *
 * @param defaultStyle el String | Element | Selector
 */
$.getSelector=function(el){
	if($.type(el)=='object'){
		return el
	}else if($("#"+el).length!=0){
		return $("#"+el);
	}else if($("."+el).length!=0){
		return $("."+el);
	}else if($(el).length!=0){
		return $(el);
	}
		throw("name="+el+',No selector found:没有找到对应的选择器' );
}
/*
 *
 * @param defaultStyle el String | Element | Selector
 */
$.setElementWidth=function(el,witdh){
		var selector=$.getSelector(el);
		if(selector!=null&&typeof(selector)!='string'){
			selector.css("width",witdh);
		}else{
			throw "该元素不存在";
			
		}
	}
$.Assert={
	isSelector:function(el){ 
		if(el==null||typeof(el)!="object"){ 
			throw("error:"+el+",This is not a selector:这不是一个选择器");
		}
		return true;
	}	
}
$.Align={
	horizontally:function(el){
		var selector=$.getSelector(el);
		$.Assert.isSelector(selector);
		var parent=selector.parent();
		var parentWidth=parent.width();
		var selectorWidth=selector.width();
		selector.css("left",((parentWidth-selectorWidth)/2)+"px");
	},
	vetically:function(el){
		var selector=$.getSelector(el);
		$.Assert.isSelector(selector);
		var parent=selector.parent();
		var parentHeight=parent.height();
		var selectorHeight=selector.height();
		selector.css("top",((parentHeight-selectorHeight)/2)+"px");
		
	},
	center:function(el){
	
	}
}
function Toast(text,time,maxWidth){
	var _this=this;
	this.text=text?text:"";
	this.maxWidth=maxWidth?maxWidth:null;
	this.time=time?time:3000;
	this.selecorId="contextMenu_Toast"+new Date().getTime();
	this.showTime=parseInt(_this.time/5);
	this.stayTime=parseInt((_this.time/5)*3);
	this.hideTime=parseInt((_this.time/5)*2);
	var setParameter=function(text,time,maxWidth){
		_this.text=text!=null?text:_this.text;
		_this.maxWidth=maxWidth?maxWidth:_this.maxWidth;
		_this.time=(time!=null&&$.type(parseInt(time))=='number')?parseInt(time):_this.time;
		_this.showTime=parseInt(_this.time/5);
		_this.stayTime=parseInt((_this.time/5)*3);
		_this.hideTime=parseInt((_this.time/5)*2);
	}
	var selector=null;
	var toastStyle={
		toast:{
			name:"toast",
			style:"position: fixed;top:100px;opacity:0;background-color: rgba(0,0,0,0.8);padding-bottom:15px;padding-top:15px;padding-left:10px;padding-right:10px;z-index: 9999;border: 1px solid #000;-moz-border-radius: 6px;-webkit-border-radius:6px;border-radius:6px;cursor: pointer;-webkit-user-select:none;-moz-user-select:none; -ms-user-select:none; user-select:none;",
		},
		toast_span:{
			name:"toast_span",
			style:"color:#fff;font-family:'Microsoft YaHei',微软雅黑,'MicrosoftJhengHei',华文细黑,STHeiti,MingLiu;font-size:1em;"
		}
	}
	var style=new SetStyle(toastStyle);
	var createShow=function(){
		
		$.getSelector("body").append("<div id='"+_this.selecorId+"'><span>"+_this.text+"</span></div>");
		style.setStyle(_this.selecorId,"toast");//设置样式
		style.setStyle(_this.selecorId+" span","toast_span");//设置样式
		$.getSelector(_this.selecorId).click(function(){
			$.getSelector(_this.selecorId).css("display","none");
		});
		selector=$.getSelector(_this.selecorId);
		if(_this.maxWidth!=null&&$.type(parseInt(_this.maxWidth))=='number'){
			selector.css("max-width",parseInt(_this.maxWidth)+"px");
		}
		$.Align.horizontally(_this.selecorId);
		var parentHeight=$(document).height();
		selector.css("top",parentHeight*0.1+"px");
		
	}
	var destroyToast=function(){
		selector.remove();
		selector=null;
	}
	this.show=function(text,time,maxWidth){
		setParameter(text,time,maxWidth);
		createShow();//生成吐司html代码
		selector.animate({opacity:'1'},_this.showTime,function(){
			 setTimeout(function () {selector.animate({opacity:"0"},_this.hideTime);setTimeout(function(){destroyToast()},_this.hideTime);}, _this.stayTime);
			}
		);
		
	}
	this.shake=function(text,time,maxWidth){
		setParameter(text,time,maxWidth);
		createShow();
		if(_this.maxWidth==null||_this.maxWidth==''){
			selector.children("span").css("white-space","nowrap");;
		}
		selector.animate({opacity:'1'},_this.showTime,function(){
			selector.effect('shake', { times: 3 }, 500);
			if(_this.stayTime<=800){
				alert("800");
				setTimeout(function(){
					selector.animate({opacity:"0"},_this.hideTime);setTimeout(function(){destroyToast()},_this.hideTime);
			 		},800);
			}else{
				setTimeout(function(){
					selector.animate({opacity:"0"},_this.hideTime);setTimeout(function(){destroyToast()},_this.hideTime);
			 	},_this.stayTime);
			}
			});
		
	}
	
}
