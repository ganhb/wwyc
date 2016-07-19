// JavaScript Document
//选择省简称
function chk(){ 
	var obj = document.getElementById("dress"); 
	var strsel = obj.options[obj.selectedIndex].text; 
	var carD = document.getElementById("carD");
	
	carD.innerHTML = strsel;
	} 
	
	
//导航nav

			  	/*	var footer = document.getElementById("footer_me");
					var lis = footer.childNodes[1].childNodes[1].childNodes;
					
					function filterSpace(Nodes){
						var ret =[];
						for(var i =0; i<Nodes.length;i++){
							if(Nodes[i].nodeType == 3 && /^\s+$/.test(Nodes[i].nodeValue)){
								Nodes[i].parentNode.removeChild(Nodes[i]);
								}
							}
							return Nodes;
						}
					var lisd = filterSpace(lis);
					var person = lisd[0].childNodes[0].childNodes[0];
					var car = lisd[1].childNodes[0].childNodes[0];
					var share = lisd[2].childNodes[0].childNodes[0];
					console.log(person.className);
					console.log(person.className.indexOf("focus"))
			  		function focus_btn(){
						if (person.className.indexOf("focus") == -1){
							person.className = "blur focus"
							car.className = "blur";
							share.className = "blur";
							}
						}
					function focus_btn0(){
						if (car.className.indexOf("focus") == -1){
							person.className = "blur"
							car.className = "blur focus";
							share.className = "blur";
							}
						}
					function focus_btn1(){
						if (share.className.indexOf("focus") == -1){
							person.className = "blur"
							car.className = "blur";
							share.className = "blur focus";
							}
						}*/
			  
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