/**
  @fileoverview
  各种打开新窗口、增删改查操作、分页操作的封装，包括打开窗口的操作以及AJAX操作，用于统一操作和简化代码。<br>
  ShowDetailOp 从列表中打开明细窗口，弹出窗口方式。<br>
  AjaxOp 异步操作类，新增保存、修改保存、删除等都用AJAX方式。<br>
  BatchOp 批量操作<br>
  AjaxFormOp 需要进行大数据量提交或者上传文件时使用<br>
  
  @author 林小松
*/
var ajaxLoadingBar;
var pageInfo;


/**
获取通用的ajax处理进度条，以便进行显示或者隐藏控制。
@return ajaxLoadingBar
*/
function getAjaxLoadingBar(){
    /*if(!ajaxLoadingBar){
        ajaxLoadingBar=$.createXpBar('系统正在处理中......');
    }
    return ajaxLoadingBar;*/
}  
//判断是否被跨域引用，跨域应用将不使用默认的弹窗方式。
$_crossDomain=false;
//简单判断是否为跨域嵌入，通过是否能访问top进行判断
try{
	tw= top.window;
}catch(ex){//异常判断为跨域
	$_crossDomain=true;
}

/*20131012
由于IE9以后不允许重载window.close方法，改为增加自定义的方法$close.
要求页面不再调用window.close方法。
*/
window.$close=function(force){
	if(window.isBusy && !force){
		if(confirm('页面正在处理中，是否立即关闭？')){
			window.$close(true);
		}
		return;
	}
	if(!$_crossDomain && window.frameElement && window.frameElement.closeDialog){
		window.frameElement.closeDialog(window.returnValue);
	} else {
		window.close();
	}
}

/**TODO  应该可以优化对话框生成方式，避免每次生成整个对话框的框架，实现重用提高速度。目前的速度比原始对话框低一些。*/
/**
    @class
	<pre>
    打开明细窗口操作对象，属性可以在创建对象时一次性传入，也可以创建对象后设置相应属性。
    属性设置完成后，调用execute()方法执行操作。
    execute方法的参数为event，用于兼容firefox。以便支持当前位置弹出窗口的需求，即非居中的情况。
    居中时可不传入event参数。
	</pre>
	<pre>
	  属性说明：
	  url:弹出窗口页面URL
	  width:宽度
	  height:高度
	  onsuccess:成功回调操作，即返回值不为空的时候回调。分页查询页面默认执行查询。唯一参数为返回值。
	  oncancel:取消回调，无参数
	  showMask:是否显示遮盖层，默认显示
	  center：是否居中显示，默认居中
	  immediate:是否立即执行操作打开窗口，默认false
	</pre>
    <pre>
    如果明细窗口返回true或者返回值不为空则调用onsuccess回调。
    明细窗口无返回值当做点击【取消】
    要求在明细窗口中根据需要设定window.returnValue，表示明细操作是否成功。
   示例1：
   var detailOP = new ShowDetailOp();
   detailOP.url='delReport.do?reporId=1111111';
   detailOP.onsuccess=function(returnValue){document.forms[0].submit()};
   detailOP.oncancel=function(){};
   detailOP.width=500;
   detailOP.height=300;
   detailOP.execute();
   
   实例2：在列表中批量使用方式
   先建好一个操作对象，每次点击时执行指定的url即可。
   &lt;a href="#" onclick="detailOP.execute('reportUpdatePage.do?reporId=1234')"&gt;[修改]&lt;/a&gt;
   </pre>
    @param url 明细窗口路径
    @param width 宽度
    @param height 高度
    @param immediate 是否立即执行，默认false。
 */
function ShowDetailOp(url,width, height,immediate) {
	this.addParam = function(paramName,paramValue){
		this.url += '&' + paramName + '=' + paramValue;
	}
    /** 弹出窗口显示内容URL  */
    this.url = url;
    /** 弹出窗口宽度 */
    this.width=parseInt(width||600);
    /** 弹出窗口高度 */
    this.height=parseInt(height||450);
    /*是否居中，默认居中，否则在当前位置显示。*/
    this.center=true;
    /*左上角坐标，默认自动计算*/
    this.left=null;
    /*左上角坐标，默认自动计算*/
    this.top=null;
    /*是否遮盖非对话内容,默认true*/
    this.showMask=true;
    /** 成功回调函数，接口function(returnValue)，returnValue为弹出窗口返回值 
	兼容通过callback赋值*/
    this.onsuccess=null;
    /** 关闭窗口或者取消，即无返回值时的回调函数 */
    this.oncancel=null;
    /** 需要传递到弹出窗口的数据 */
    this.data=null;
    /** 是否允许调整窗口 */
    this.resizable='yes';
    /** 操作执行方法，弹出窗口 */
    this.execute=function(event){
    	if(window.isBusy){
    		return;
    	}
    	window.isBusy=true;
    	var t1=new Date().getTime();
        var dlgHeight=this.height;
        var status='no';
        //计算窗口位置，以便支持ff和ie下都居中显示
        /**
        var dialogLeft = Math.round((window.screen.width-this.width)/2);
        var dialogTop = Math.round((window.screen.height-this.height)/2);
        */
		//默认回调函数
		var defaultCallback=function(rt){
			if (pageInfo){
					pageInfo.totalCount=0;
					setTimeout(refreshPage,10);
			}
		}
		var onsuccess=this.onsuccess||this.callback||defaultCallback;
		var oncancel=this.oncancel;
        if(!$_crossDomain){
	        var dialogLeft = Math.round((top.$(top).width()-this.width)/2);
	        var dialogTop = Math.round((top.$(top).height()-this.height)/2);
	        //判断top是否滚动
	        
	        dialogLeft+=top.$(top).scrollLeft();
	        dialogTop+=top.$(top).scrollTop();
	        debug('dialogLeft:'+dialogLeft+',dialogTop:'+dialogTop);
	        //非居中定位处理问题
	        if(!this.center && event!=null){
	        	debug('not center');
	        	/**TODO 在IFRAME内部使用还有问题*/
	        	dialogLeft=this.left|event.clientX;
	        	dialogTop=this.top|event.clientY;
	        }
	       // alert('dialogLeft:'+dialogLeft+',dialogTop:'+dialogTop);
	        //修正IE6和fireFox的窗口高度问题
	        /*
	        if ( $.browser.msie && /6.0/.test(navigator.userAgent) ) {
	            dlgHeight+=25;
	        } else if($.browser.mozilla){
	            //alert('FIREFOX HEGIHT FIXED');
	            dlgHeight+=20;
	            dialogTop+=30;
	        }
	        */
	        /**var rt = window.showModalDialog(execUrl||this.url, this.data, 
	            'dialogWidth:' + this.width + 'px;dialogHeight:' + dlgHeight + 'px;'+
	            'dialogLeft:' + dialogLeft + 'px;dialogTop:' + dialogTop + 'px;'+
	            'help:no;unadorned:no;location:no;resizable:'+this.resizable+';status:no;center:yes;scroll:no;');
	        */
	        var dialog=top.$('<div class="dialog"></div>');
	        $(top.document.body).append(dialog);
	        //top.debug('dialog appendto body');
	
	        var dialogOuter=top.$('<div class="dialogOuter"></div>');
	        dialog.append(dialogOuter);
	        //top.debug('dialogOuter create');
	        //20131226 增加窗口边框，高度宽度再增加
	        dialogOuter.css({
	          top:(this.top||dialogTop)+'px',
	        	left:(this.left||dialogLeft)+'px',
	        	height:(this.height+30+7)+'px',
	        	width:(this.width+2+7)+'px'
	        })
	        var dialogTitle=top.$('<table class="dialogTitle" style="width:100%;"><tr><td>正在加载中......</td><td class="button" width="18" height="23"></td></tr></table>');
	        dialogTitle.css({
	        	height:'30px',
	        	width:'100%'
	        })
	        dialogOuter.append(dialogTitle);
	        //top.debug('dialogTitle create');
	
	        var ifram = top.$('<iframe class="dialogFrame" frameborder="0" ALLOWTRANSPARENCY="false" ></iframe>');
	        ifram.bind('load',function(event){top.$('td:eq(0)',dialogTitle).html(top.$(this)[0].contentWindow.document.title)});
	        ifram[0].src=this.url;
	        ifram[0].data=this.data;
	        ifram.css({
	        	height:this.height+'px',
	        	width:this.width+'px'
	        })
	        //top.debug('iframe create');
	
	        dialogOuter.append(ifram);
	                
	        var dialogMask;
	        if(this.showMask){
	        	//alert(this.width+','+this.height)
	        	var maskFrame = '<iframe src="about:blank" style="z-index:1000;position:static;left:0px; top:0px;width:100%;height:100%;filter:alpha(opacity=0)"></iframe>';
		        if($('.dialogMask',top.document).length==0){
		        	dialogMask = top.$('<div class="dialogMask" style="display:none"></div>');
			        top.$(top.document.body).append(dialogMask);
			        dialogMask.css({height:top.$(top).height(),width:'100%'})
			        if($.browser.msie && $.browser.version<7){//IE6加上iframe遮罩层，避免SELECT控件显示在上层窗口
			        	dialogMask.append(top.$(maskFrame))
			        }
			    } else {
		        	dialogMask = $('<div class="dialogMask" style="display:none"></div>');
		        	$(document.body).append(dialogMask);
			        dialogMask.css({height:$(window).height()+'px',width:'100%'})
			        if($.browser.msie && $.browser.version<7){
			        	dialogMask.append($(maskFrame))
			        }
			    }
		        dialogMask.show();
		        dialogMask.click(function(event){top.debug('click mask.....');event.preventDefault();});
	        }
	        dialogOuter.show();
	        /**TODO  */
	        top.$(dialogOuter).draggable();
	        
	        ifram[0].closeDialog=function(rt){
	   	  	    dialog.hide();
	   	  	    if(dialogMask) dialogMask.remove();
		   	    if(rt!=null){
		   	  		onsuccess(rt);
		   	  	}	else if(oncancel!=null) {
		   	  		oncancel(rt);
		   	  	}
	        	//关闭窗口，尝试减少内存泄露
	        	ifram[0].contentWindow.document.write('');
	        	ifram[0].contentWindow.document.close();
		   	    dialog.remove();
		   	    window.isBusy=false;
	        }
	        //关闭按钮事件
	        top.$('.button',dialogTitle).click(function(){ifram[0].closeDialog()});
	        t1=new Date().getTime()-t1;
	        top.debug('弹出窗口耗时:'+t1+'毫秒');
        } else {
        	var rt = window.showModalDialog(this.url, this.data, 
    	            'dialogWidth:' + this.width + 'px;dialogHeight:' + dlgHeight + 'px;'+
    	            'dialogLeft:' + dialogLeft + 'px;dialogTop:' + dialogTop + 'px;'+
    	            'help:no;unadorned:no;location:no;resizable:'+this.resizable+';status:no;center:yes;scroll:no;');
        	if(rt!=null){
	   	  		onsuccess(rt);
	   	  	}	else if(oncancel!=null) {
	   	  		oncancel(rt);
	   	  	}
        	window.isBusy=false;
        }
    }
    if(immediate){
        this.execute();
    }
    return this;
}

/**
    @class
	<pre>
    AJAX操作对象，用于统一AJAX操作。
    属性可以在创建对象时传入，也可以创建对象后设置相应属性。
    【构造参数】同时也是对象属性
    url 操作路径，必须
    confirmMessage 操作确认提示信息，可选。
    callback 成功时的回调函数，可选
    是否立即执行操作，默认false。可选。
    【其他属性】
    validate操作验证函数：用于操作前的验证。不符合则返回false,否则返回true。返回false则不执行操作。
    error回调函数：用于出错时的自定义错误提示,function(XMLHttpRequest, textStatus, errorThrown){}
    checkName用于批量操作时的数据提交，checkbox的name，支持多个逗号分隔。自动生成提交参数。
    selectMessage批量操作选择提示，配合checkName使用。默认“请选择操作对象”
    postData提交的参数，可以是对象或者函数。函数需要返回数据对象。
    【方法说明】
    操作对象的执行方法 execute(execUrl,execMsg)
    execUrl 执行url，可选.默认创建对象时指定的url
    execMsg 执行确认提示信息，可选。默认为创建时指定的提示信息，无则不提示。
    【其他说明】
    规范，ajax操作返回对象应包含status表示操作成功与否的状态：error表示错误。
    【使用示例】
   示例1：有操作确认提示
   var op = new AjaxOp();
   op.url='delReport.do?reporId=1111111';
   op.message='您确认要删除【XXXXX】报表吗？';
   op.callback=function(data){document.forms[0].submit()};
   op.execute();
   
   示例2：立即执行方式
   function callback(data){document.forms[0].submit()}
   &lt;a href="#" onclick="AjaxOp('delReport.do?reportId=111','您确认要删除【XXXXX】报表吗？',callback,true)"&gt;[删除]&lt;/a&gt;
   
   示例3：多次执行
   var delOp = new AjaxOp();
   delOp.callback=function(data){document.forms[0].submit()};
   &lt;a href="#" onclick="delOp.execute('delReport.do?reportId=111','您确认要删除【XXXXX】报表吗？')"&gt;[删除]&lt;/a&gt;
   &lt;a href="#" onclick="delOp.execute('delReport.do?reportId=222','您确认要删除【YYYYY】报表吗？')"&gt;[删除]&lt;/a&gt;
   
   示例4：无确认提示操作
   var saveOp = new AjaxOp(url);
   saveOp.callback=function(){alert('保存成功')};
   saveOp.execute();
   
   示例5：批量操作
   var batchOp = new AjaxOp('report.do?method=delete','您确认要删除选择的报表吗？',callback);
   batchOp.checkName='reportIds';
   batchOp.selectMessage='请选择报表';
   batchOp.execute();
   </pre>
*/
function AjaxOp(url,confirmMessage,callback,immediate){
    /** ajax请求的url */
    this.url=url;
    /** ajax操作成功的回调函数，接口function(data){};data为返回的json对象 */
    this.callback=callback;
    /** 出错回调 */
    this.onError=null;
    /** ajax操作的确认消息 */
    this.confirmMessage=confirmMessage;
    /** ajax操作前的验证函数，返回false则不提交请求 */
    this.validate=null;
    /** ajax请求提交数据 */
    this.postData=null;
    /** 通过复选框批量提交数据的复选框名字 */
    this.checkName=null;
    /** 复选框未选择的提示信息 */
    this.selectMessage=null;
    /** 是否异步请求 */
    this.async=true;
    /** 
     <pre>请求数据类型,默认json.
    "xml": 返回 XML 文档，可用 jQuery 处理。
    "html": 返回纯文本 HTML 信息；包含 script 元素。
    "script": 返回纯文本 JavaScript 代码。不会自动缓存结果。除非设置了"cache"参数
    "json": 返回 JSON 数据 。
    "jsonp": JSONP 格式。使用 JSONP 形式调用函数时，如 "myurl?callback=?" jQuery 将自动替换 ? 为正确的函数名，以执行回调函数。
    "text": 返回纯文本字符串
    </pre>
    */
    this.dataType = 'json';
    /** 执行方法
	@param execUrl 执行url，可选.默认创建对象时指定的url
	@param execMsg 执行确认提示信息，可选。默认为创建时指定的提示信息，无则不提示。
	*/
    this.execute=function(execUrl,execMsg){
        if(this.validate){//执行前校验
            if(this.validate()==false){
                return;
            }
        }
        var callback=this.callback;
        var onError=this.onError;
        //处理提交参数
        var data={}
        if(this.postData){
            if($.isFunction(this.postData)){
                $.extend(data,this.postData())
            }else{
                $.extend(data,this.postData)
            }
        }
        //处理批量提交参数，必须选择了对象才提交
        var selCount = 0;
        if(this.checkName != null){
            var checkNames = this.checkName.split(',');
            for(var i=0;i<checkNames.length;i++){
                selCount += selectCount(checkNames[i]);
                data[checkNames[i]]=selectValues(checkNames[i]);
            }
            if(selCount==0 ){
                alert(this.selectMessage||'请选择操作对象。');
                return;
            }
        }
        if(execMsg||this.confirmMessage){//操作确认提示信息
            if(!confirm(execMsg||this.confirmMessage)){
                return;
            }
        }
        
        //执行AJAX操作
       // getAjaxLoadingBar().showBar();
        $.ajax({
            type: "POST",
            cache: false,
            url: this.url,
            async: this.async,
            dataType:this.dataType,
            data:data,
            success: function(data){
              //getAjaxLoadingBar().hideBar();
            	if(callback) {
                    callback(data);
                }
              /*if(data.status && data.status=='error'){
                  alert(data.message||'操作失败！');
                  if(onError){
                      onError(data);
                  }
              }else if(data.status && data.status=='noRule'){
                  alert(data.message||'你没有权限！');
              } else {
                  if(data.message){
                      alert(data.message);
                  }
                  if(callback) {
                      callback(data);
                  }
              }*/
            },
            error:function(XMLHttpRequest, textStatus, errorThrown){
             // getAjaxLoadingBar().hideBar();
          //    alert('访问量太大了，服务器目前不给力，请再试一次：'+textStatus+errorThrown);
              onError();
            }
        }); 
    }
    if(immediate){//立即执行方式
        this.execute();
    }
    return this;
}

/**
    @class
    <pre>
    批量删除操作对象，属性可以在创建对象时一次性传入，也可以创建对象后设置相应属性。
    属性设置完成后，调用execute()方法执行操作。
    规范，ajax操作返回对象应包含status表示操作成功与否的状态：error表示错误。
    
   示例：
   var delOp = new BatchDeleteOp();
   delOp.url='delReport.do';
   delOp.checkName='resIds';
   delOp.selMsg='请选择要删除的报表。';
   delOp.delMsg='您确认要删除选中的报表吗？';
   delOp.callback=function(data){document.forms[0].submit()};
   </pre>
	@extends  AjaxOp
    @param url 删除操作的url
    @param checkName 复选框名字，支持同时传递多个复选框名字，逗号分隔
    @param selMsg 未选择提示信息
    @param delMsg 删除提示信息
    @param callback 删除成功回调函数，function(data){}
    @param immediate 是否立即执行，默认false。
   
*/
function BatchDeleteOp(url,checkName,selMsg,delMsg,callback,immediate){
    /** 删除操作的请求url */
    this.url=url;
    /** 批量删除的选择框名字 */
    this.checkName=checkName;
    /** 未选择提示信息 */
    this.selMsg=selMsg;
    /** 删除确认信息 */
    this.delMsg=delMsg;
    /** 删除操作成功回调函数,参照AjaxOp的回调函数 */
    this.callback=callback;
    /** 执行方法 */
    this.execute=function(){
        var op=new AjaxOp(this.url,this.delMsg,this.callback); 
        op.checkName=this.checkName;
        op.selectMessage=this.selMsg;
        op.execute();
    }
    if(immediate){
        this.execute();
    }
    return this;
}
/**
    @class
    简单删除操作对象，属性可以在创建对象时一次性传入，也可以创建对象后设置相应属性。
    调用execute()方法执行操作,execute方法支持调用时传入url方式，默认执行操作对象的url。支持删除提示信息。
    <pre>
    规范，ajax操作返回对象应包含status表示操作成功与否的状态：error表示错误。

   示例1：
   var delOp = new DeleteOp();
   delOp.url='delReport.do?reporId=1111111';
   delOp.message='您确认要删除【XXXXX】报表吗？';
   delOp.callback=function(data){document.forms[0].submit()};
   delOp.execute();
   
   实例2：立即执行方式
   function callback(data){document.forms[0].submit()}
   &lt;a href="#" onclick="DeleteOp('delReport.do?reportId=111','您确认要删除【XXXXX】报表吗？',callback,true)"&gt;[删除]&lt;/a&gt;
   
   实例3：多次执行
   var delOp = new DeleteOp();
   delOp.callback=function(data){document.forms[0].submit()};
   &lt;a href="#" onclick="delOp.execute('delReport.do?reportId=111','您确认要删除【XXXXX】报表吗？')">[删除]&lt;/a&gt;
   &lt;a href="#" onclick="delOp.execute('delReport.do?reportId=222','您确认要删除【YYYYY】报表吗？')">[删除]&lt;/a&gt;
   </pre>
    @extends  AjaxOp
    @param url 删除操作路径
    @param message 删除提示信息
    @param callback 删除成功时的回调函数
    @immediate 是否立即执行操作，默认false。

*/
function DeleteOp(url,message,callback,immediate){
	
    /** 删除操作的请求url */
    this.url=url;
    /** 删除提示信息 */
    this.message=message;
    /** 删除成功回调函数,参照AjaxOp的回调函数 */
    this.callback=callback;
    /** 执行方法 */
    this.execute=function(){
        var op=new AjaxOp(this.url,this.message,this.callback);
        op.execute();
    }
    this.addParam = function(paramName,paramValue){
		this.url += '&' + paramName + '=' + paramValue;
	}
    if(immediate){
        this.execute();
    }
    return this;
}


/**
    @class
    批量操作对象-无弹出窗口，属性可以在创建对象时一次性传入，也可以创建对象后设置相应属性。
    属性设置完成后，调用execute()方法执行操作。
    <pre>
    规范，ajax操作返回对象应包含status表示操作成功与否的状态：error表示错误。
    
   示例：
   var delOp = new BatchOp();
   delOp.url='delReport.do';
   delOp.checkName='resIds';
   delOp.selMsg='请选择要移动的目录或资源。';
   delOp.confirmMsg='您确认要移动选中的目录或资源吗？';
   delOp.callback=function(data){document.forms[0].submit()};
   </pre>
    @extends  AjaxOp
    @param url 删除操作的url
    @param checkName 复选框名字，支持同时传递多个复选框名字，逗号分隔
    @param selMsg 未选择提示信息
    @param confirmMsg 执行操作前给与的提示信息
    @param callback 操作成功回调函数，function(data){}
    @param immediate 是否立即执行，默认false。
*/
function BatchOp(url,checkName,selMsg,confirmMsg,callback,immediate){
    /** 批量操作url */
    this.url=url;
    /** 批量选择的复选框 */
    this.checkName=checkName;
    /**　未选择提示信息　*/
    this.selMsg=selMsg;
    /** 操作确认提示信息 */
    this.confirmMsg=confirmMsg;
    /** 操作成功回调函数，参考AjaxOp的回调*/
    this.callback=callback;
    /** 执行方法 */
    this.execute=function(){
        var op=new AjaxOp(this.url,this.confirmMsg,this.callback); 
        op.checkName=this.checkName;
        op.selectMessage=this.selMsg;
        op.execute();
    }
    if(immediate){
        this.execute();
    }
    return this;
}



/**
    @class
	<pre>
    AJAX提交FORM操作。
    属性可以在创建对象时一次性传入，也可以创建对象后设置相应属性。
    该操作支持文件上传操作。
    调用execute()方法执行操作,execute方法支持调用时传入url方式，默认执行操作form的action。
    规范，ajax操作返回对象应包含status表示操作成功与否的状态：error表示错误。
    
   示例1：
   var saveOp = new AjaxFormOp();
    saveOp.form=document.reportForm;
    saveOp.opName='保存报表';
    saveOp.beforeSubmit=function(a,f,o){
        return validateForm();
    }
    saveOp.success=function(data){
        if(!data.status || data.status=='success'){
            alert(data.message));
            window.returnValue=data;
            window.close();
         } else{
            alert('操作失败:'+data.message);
         }
    };
    saveOp.execute();
   
   实例2：立即执行方式
   function validateForm(){return true};
   function callback(data){document.forms[0].submit()};
   &lt;button onclick="AjaxFormOp('#reportForm','保存报表',validateForm,callback,true)"&gt;保存&lt;/button&gt;
   </pre>
   
	
    @param theForm 操作的FORM对象，可以传入form对象或者jQuery选择form的表达式。可选，默认为页面的第一个form。
    @param opName 操作名称。可选，用于提示。
    @param beforeSubmit 提交前的回调函数，用于特殊的form校验。返回false则不提交。可选，默认无。
    @param success 操作成功时的回调函数。可选，默认提示操作信息。
    @immediate 是否立即执行操作， 可选,默认false。
*/
function AjaxFormOp(theForm,opName,beforeSubmit,success,immediate){
    /** 提交的form对象 */
    this.form = theForm;
    /** 提交前的附加验证函数，返回false则不提交 */
    this.beforeSubmit=beforeSubmit;
    /** 成功返回的回调函数:function(data) 
	也兼容通过callback属性赋值
	*/
    this.success=success;
    /**@private 返回错误的处理函数 */
    this.error=function(XMLHttpRequest, textStatus, errorThrown){
        window.currentOpForm.isBusy=false;
        getAjaxLoadingBar().hideBar();
        alert('操作出错了。'+errorThrown);
    }
    /** 执行方法 */
    this.execute=function(submitUrl){
        if(this.form==null){
            this.form=document.forms[0];
        }
        window.currentOpForm=this.form;
        if(window.currentOpForm.isBusy){
            alert('系统正忙，请不要连续提交。');
            return;
        }
        window.currentOpForm.isBusy=true;
        var v_opName=this.opName||'操作';
        var defaultSuccess=function(data){
            if(!data.status || data.status=='success'){
                alert(v_opName+'成功'+(data.message?':'+data.message:'!'));
            } else{
                alert(v_opName+'失败'+(data.message?':'+data.message:'!'));
            }
        }
        var onsuccess = this.success||this.callback||defaultSuccess;
        var options={
            type:"POST",
            dataType:'json',
            success: function(data){
                        window.currentOpForm.isBusy=false;
                        getAjaxLoadingBar().hideBar();
                        if(data){
                            //无权限提示
                             if(data.status && data.status=='noRule'){
                                alert(data.message);
                             //出错提示
                             }else if(data.status && data.status =='error'){
                                alert(v_opName+'失败'+(data.message?':'+data.message:'!'));
                             } else{  
                                onsuccess(data);
                            }
                        }
                    },
            error:this.error
        }
        if(submitUrl!=null){
            $(this.form)[0].action=submitUrl;
        }
        if(ValidateUtil.validateForm(this.form)){
            if(this.beforeSubmit){
                if(this.beforeSubmit()!=false){
                    getAjaxLoadingBar().showBar();
                    $(this.form).ajaxSubmit(options);
                } else {
                    window.currentOpForm.isBusy=false;
                }
            } else {
                $(this.form).ajaxSubmit(options);
            }
            
        } else {
            window.currentOpForm.isBusy=false;
        }
    }
    if(immediate){
        this.execute();
    }
    return this;
}



///////////////////////////////////////////////

/**
<pre>
    检查是否有选中复选框，没有则提示。
    提示信息可以默认
    没有选中记录返回0，否则返回选中对象个数.
</pre>    
    @param checkName 复选框名
*/
function selectCount(checkName){
    var chks = $('input[type=checkbox][name='+checkName+']:checked');
    if(chks==null || chks.length==0){
        return 0;
    }
    return chks.length;
}
/**
<pre>
    获取复选框的选中对象的值，结果为一个数组。
    没有选中则返回空数组
</pre>    
    @param checkName 复选框名
*/
function selectValues(checkName){
    var chks = $('input[type=checkbox][name='+checkName+']:checked');
    var values = [];
    $.each(chks,function(i,n){
                    values.push(n.value);
                }
           )
    return values;
}

/**
	用指定的URL刷新模式对话框
	如果是IE避免在模式窗口弹出新窗口
*/
function refreshDialog(url){
	if(window.isBusy){
		return;
	}
    if($.browser.msie){
        var   a=document.createElement("a");   
        a.href=url;   
        document.body.appendChild(a);   
        a.click();
    } else {
        window.location.assign(url);
    }
}

/**以下为分页操作脚本 */

/**
第一页
*/
function gotoFirstPage(){
  gotoPage(1);
}
/**
下一页
*/
function gotoNextPage(){
  gotoPage(pageInfo.pageIndex+1);
}
/**
上一页
*/
function gotoPrePage(){
  gotoPage(pageInfo.pageIndex-1);
}
/**
最后一页
*/
function gotoLastPage(){
  gotoPage(pageInfo.pageCount);
}

/**
刷新当前页面
*/
function refreshPage(){
    gotoPage(pageInfo.pageIndex);
}

/**
到第几页
*/
function gotoPage(pageIndex){
    if(!pageIndex){
       alert('请输入页码!');
       $('#pageIndex')[0].focus();
       return;
    }
    if(pageIndex>1 && pageIndex>pageInfo.pageCount){
       alert('页码无效:有效范围为1到'+pageInfo.pageCount);
       $('#pageIndex').val('');
       $('#pageIndex')[0].focus();
       return;
    }
    pageUrl=window.location.pathname+pageInfo.pageUrl;
    pageUrl+='&pageInfo.pageSize='+pageInfo.pageSize+'&pageInfo.totalCount='+pageInfo.totalCount;
    pageUrl+='&pageInfo.pageIndex='+pageIndex;
    pageUrl+='&pageInfo.sortField=${pageInfo.sortField}';
    pageUrl+='&pageInfo.sortDirect=${pageInfo.sortDirect}';
    pageUrl=pageUrl.process({pageInfo:pageInfo});
    changePageCallback(encodeURI(pageUrl));
}
/**
@private
改变分页记录数
*/
function changePageSize(pageSize){
    pageUrl=window.location.pathname+pageInfo.pageUrl;
    pageUrl+='&pageInfo.totalCount=${pageInfo.totalCount}';
    pageUrl+='&pageInfo.pageIndex=1&pageInfo.pageSize='+pageSize;
    pageUrl+='&pageInfo.sortField=${pageInfo.sortField}';
    pageUrl+='&pageInfo.sortDirect=${pageInfo.sortDirect}';
    //pageUrl=pageUrl.process({pageInfo:pageInfo});
    changePageCallback(pageUrl);
}
/**
改变页面排序
*/
function changePageSort(sortField,sortDirect){
    pageUrl=window.location.pathname+pageInfo.pageUrl;
    pageUrl+='&pageInfo.totalCount=${pageInfo.totalCount}';
    pageUrl+='&pageInfo.pageIndex=${pageInfo.pageInfo}';
    pageUrl+='&pageInfo.pageSize=${pageInfo.pageSize}';
    pageUrl+='&pageInfo.sortField='+sortField;
    pageUrl+='&pageInfo.sortDirect='+sortDirect;
    //pageUrl=pageUrl.process({pageInfo:pageInfo});
    changePageCallback(pageUrl);
}
/**
@private
导出到EXCEL文件，配合导出到EXCEL的JXLS模板，需要自定义EXCEL模板。
*/
function exportPageExcel(fileName){
    pageSize=pageInfo.pageSize;
    if(pageInfo.pageCount>1 && confirm('确定要导出所有记录吗？取消则仅导出当前页记录。')){
      if(pageSize>10000){
          pageSize=10000;
          alert('记录超过10000条，系统最多允许导出10000条。请缩小查询范围，重新查询后导出。');
      } else {
          pageSize=-1;
      }
    }
    pageUrl=window.location.pathname+pageInfo.pageUrl+'&exportToExcel=true';
    pageUrl+='&pageInfo.totalCount=${pageInfo.totalCount}';
    pageUrl+='&pageInfo.pageIndex=${pageInfo.pageIndex}&pageInfo.pageSize='+pageSize;
    pageUrl+='&pageInfo.sortField=${pageInfo.sortField}';
    pageUrl+='&pageInfo.sortDirect=${pageInfo.sortDirect}';
    pageUrl=pageUrl.process({pageInfo:pageInfo});
    if(fileName){
    	pageUrl+='&exportFileName='+fileName+'.xls';
    }
    if(pageUrl.indexOf(':')<0 && pageUrl.charAt(0)!='/'){
      pageUrl='/'+pageUrl;
    }
    exportFile(pageUrl);
}
/**
    导出文件，避免在对话框中导出的问题
*/
function exportFile(url){
    
    //判断你是否IE浏览器，是否在对话框
    //if(!$.browser.msie || top.dialogHeight!=null || top.opener!=null){
	if(top.dialogHeight!=null){
        var exportWindow = window.open('exportFile.jsp','exportWindow');
        window.exportUrl=encodeURI(url);
        //exportWindow.location=url;
    } else {
        window.location=encodeURI(url);
    }
}

/**
@private
改变分页页面的最终执行函数，解决了IE下在showModalDialog中会弹出新窗口的问题。
*/
function changePageCallback(url){
    //alert(url);
    if(url.indexOf(':')<0 && url.charAt(0)!='/'){
      url='/'+url;
    }
    url=url.process({pageInfo:pageInfo});
    refreshDialog(url);
}
//20110113 为避免某些情况下分页排序的代码被执行两次，将以下代码从pageInfo.jsp中分离纳入本js
$(function(){
    if(!window.pageInfo){
        return;
    }
    //分页跳转输入回车提交
    $('#pageIndex').keypress(function(event){
        if(event.keyCode==13) {
            gotoPage($('#pageIndex').val());
            return false;
        }
    })
    //初始化排序字段样式
    var sortField = $('th[sortField='+pageInfo.sortField+']').attr('sort',pageInfo.sortDirect);
    if(pageInfo.sortField){
        //$('#sortDirect').remove();
        if(pageInfo.sortDirect=='DESC'){
            sortField.addClass('sortDown').removeClass('sortUp').append('<span id="sortDirect">↓</span>');
        } else {
            sortField.addClass('sortUp').removeClass('sortDown').append('<span id="sortDirect">↑</span>');
        }
    }
})

