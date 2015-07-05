<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>  
<html>  
<head>  
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />  
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />  

<!-- <script src="http://apps.bdimg.com/libs/jquery/2.1.1/jquery.min.js"></script> -->
<script src="http://apps.bdimg.com/libs/jquery/1.9.1/jquery.min.js"></script>

<title>一只通房产</title>  
<!-- <link href="http://img2s.soufun.com/map/new_m/baidu/img/css.css?v=20130328" rel="stylesheet" type="text/css"> -->
<style type="text/css">  
html{height:100%}  
body{min-width:1200px;min-height:500px;height:100%;margin:0px;padding:0px;font: 12px "宋体", Arial;}  
div{
margin: 0;
padding: 0;
border: 0;}
#baiduMapContainer{height:80%}
#onSaleSelect{
	position: fixed;
	right:0px;
	top:100px;
	opacity:0.6;
	background-color: black;
}
#onSaleSelect .onSaleSelectBtn{
	margin: 10px 10px 10px 10px;
	font-size:12px;
	font-family:Lucida Grande, Verdana, Lucida, Arial;
}
#onSaleSelect input{
	vertical-align:middle;
	margin: 0 5px 0 5px;
	cursor:pointer;
}
#priceSelect{
	width:100%;
	min-height:20%;
	float:left;
	background-color: #515368;
}
#priceSelectHead{
	padding-top:6px;
	padding-left:10px;
	font-size:20px;
	font-family:Lucida Grande, Verdana, Lucida, Arial;
	height:36px;
	background-color: #3b3c4f;
}
#priceSelectHead div{
	float:left;
	margin:0px 6px 0 6px;
	vertical-align:middle;
	display: inline;
	color: #515368;
}
#priceSelectHead span{
	float:left;
	padding:2px 6px 2px 6px;
	vertical-align:middle;
	display: inline;
	cursor:pointer;
	color:white;
}
#priceSelectHead #totalRealEstate{
	float:left;
	cursor:default;
}

#priceSelectHead .selected{
	background-color: #649fd7;
}

#priceSelectHead #gotoListDisplay{
	float:right;
	color:white;
	vertical-align:middle;
	display: inline;
	cursor:pointer;
}
#priceSelectBody .linkBtn{
	float:left;
	color:white;
	font-size:16px;
	font-family:Lucida Grande, Verdana, Lucida, Arial;
	padding:5px 10px 0px 10px;
	margin:2px 0px 2px 10px;
}
#priceSelectBody .odd{
	color:yellow;
}
#priceSelectBody a:hover {
	color:red;
}
#priceSelectFoot a{
	margin-right:5px;
	float:right;
	color:white;
	font-size:20px;
	font-weight:bold;
	font-family:Lucida Grande, Verdana, Lucida, Arial;
}
a:link,a:visited,a:hover{
	text-decoration:none;  /*超链接无下划线*/
}

.mapFinddingCanvasLabelStyleBlue{color:white;height:40px;overflow:Visible;cursor:pointer;position:relative;}
.mapFinddingCanvasLabelStyleBlue .s1 {height:28px;width:5px;background: url(<%=request.getContextPath()%>/img/bmap/blueBtn/left.png) no-repeat 1px top;}
.mapFinddingCanvasLabelStyleBlue .s2 {margin-left:2px;margin-top:-28px;white-space:nowrap;background: url(<%=request.getContextPath()%>/img/bmap/blueBtn/middle.png) repeat-x;height:28px;line-height:28px;color:#fff;text-align:center;padding:0 10px;font-size:12px;font-family:Lucida Grande, Verdana, Lucida, Arial;} 
.mapFinddingCanvasLabelStyleBlue .s2 span{display:none}
.mapFinddingCanvasLabelStyleBlue .s3 {height:28px;width:5px;background: url(<%=request.getContextPath()%>/img/bmap/blueBtn/right.png) no-repeat;_filter: progid:DXImageTransform.Microsoft.AlphaImageLoader(src='<%=request.getContextPath()%>/img/bmap/blueBtn/right.png');_background-image:none;} 
.mapFinddingCanvasLabelStyleBlue .s4 {clear: both; font-size:1px; width:1px; height:0; visibility: hidden; line-height:0;}
.mapFinddingCanvasLabelStyleBlue .s5 {position:absolute;left:1px;top:23px;width:26px;height:17px;background: url(<%=request.getContextPath()%>/img/bmap/blueBtn/bottom.png) no-repeat;filter: progid:DXImageTransform.Microsoft.AlphaImageLoader(src='<%=request.getContextPath()%>/img/bmap/blueBtn/bottom.png');_background-image:none;} 

.mapFinddingCanvasLabelStyleYellow{color:white;height:40px;overflow:Visible;cursor:pointer;position:relative;}
.mapFinddingCanvasLabelStyleYellow .s1 {height:28px;width:5px;background: url(<%=request.getContextPath()%>/img/bmap/yellowBtn/left.png) no-repeat 1px top;}
.mapFinddingCanvasLabelStyleYellow .s2 {margin-left:2px;margin-top:-28px;white-space:nowrap;background: url(<%=request.getContextPath()%>/img/bmap/yellowBtn/middle.png) repeat-x;height:28px;line-height:28px;color:#fff;text-align:center;padding:0 10px;font-size:12px;font-family:Lucida Grande, Verdana, Lucida, Arial;} 
.mapFinddingCanvasLabelStyleYellow .s2 span{display:none}
.mapFinddingCanvasLabelStyleYellow .s3 {height:28px;width:5px;background: url(<%=request.getContextPath()%>/img/bmap/yellowBtn/right.png) no-repeat;filter: progid:DXImageTransform.Microsoft.AlphaImageLoader(src='<%=request.getContextPath()%>/img/bmap/yellowBtn/right.png');_background-image:none;}
.mapFinddingCanvasLabelStyleYellow .s4 {clear: both; font-size:1px; width:1px; height:0; visibility: hidden; line-height:0;}
.mapFinddingCanvasLabelStyleYellow .s5 {position:absolute;left:1px;top:23px;width:26px;height:17px;background: url(<%=request.getContextPath()%>/img/bmap/yellowBtn/bottom.png) no-repeat;filter: progid:DXImageTransform.Microsoft.AlphaImageLoader(src='<%=request.getContextPath()%>/img/bmap/yellowBtn/bottom.png');_background-image:none;}

.mapFinddingCanvasLabelStylePurple{color:white;height:40px;overflow:Visible;cursor:pointer;position:relative;}
.mapFinddingCanvasLabelStylePurple .s1 {height:28px;width:5px;background: url(<%=request.getContextPath()%>/img/bmap/purpleBtn/left.png) no-repeat 1px top;}
.mapFinddingCanvasLabelStylePurple .s2 {margin-left:2px;margin-top:-28px;white-space:nowrap;background: url(<%=request.getContextPath()%>/img/bmap/purpleBtn/middle.png) repeat-x;height:28px;line-height:28px;color:#fff;text-align:center;padding:0 10px;font-size:12px;font-family:Lucida Grande, Verdana, Lucida, Arial;} 
.mapFinddingCanvasLabelStylePurple .s2 span{display:none}
.mapFinddingCanvasLabelStylePurple .s3 {height:28px;width:5px;background: url(<%=request.getContextPath()%>/img/bmap/purpleBtn/right.png) no-repeat;filter: progid:DXImageTransform.Microsoft.AlphaImageLoader(src='<%=request.getContextPath()%>/img/bmap/purpleBtn/right.png');_background-image:none;}
.mapFinddingCanvasLabelStylePurple .s4 {clear: both; font-size:1px; width:1px; height:0; visibility: hidden; line-height:0;}
.mapFinddingCanvasLabelStylePurple .s5 {position:absolute;left:1px;top:23px;width:26px;height:17px;background: url(<%=request.getContextPath()%>/img/bmap/purpleBtn/bottom.png) no-repeat;filter: progid:DXImageTransform.Microsoft.AlphaImageLoader(src='<%=request.getContextPath()%>/img/bmap/purpleBtn/bottom.png');_background-image:none;}

.mapFinddingCanvasLabelStyleOrange{color:white;height:40px;overflow:Visible;cursor:pointer;position:relative;z-index:1;}
.mapFinddingCanvasLabelStyleOrange .s1 {height:28px;width:5px;background: url(<%=request.getContextPath()%>/img/bmap/orangeBtn/left.png) no-repeat 1px top;}
.mapFinddingCanvasLabelStyleOrange .s2 {margin-left:2px;margin-top:-28px;white-space:nowrap;background: url(<%=request.getContextPath()%>/img/bmap/orangeBtn/middle.png) repeat-x;height:28px;line-height:28px;color:#fff;text-align:center;padding:0 10px;font-size:12px;font-family:Lucida Grande, Verdana, Lucida, Arial;} 
.mapFinddingCanvasLabelStyleOrange .s2 img{margin-left:5px;}
.mapFinddingCanvasLabelStyleOrange .s2 span{display: inline;}
.mapFinddingCanvasLabelStyleOrange .s3 {height:28px;width:5px;background: url(<%=request.getContextPath()%>/img/bmap/orangeBtn/right.png) no-repeat;filter: progid:DXImageTransform.Microsoft.AlphaImageLoader(src='<%=request.getContextPath()%>/img/bmap/orangeBtn/right.png');_background-image:none;}
.mapFinddingCanvasLabelStyleOrange .s4 {clear: both; font-size:1px; width:1px; height:0; visibility: hidden; line-height:0;}
.mapFinddingCanvasLabelStyleOrange .s5 {position:absolute;left:1px;top:23px;width:26px;height:17px;background: url(<%=request.getContextPath()%>/img/bmap/orangeBtn/bottom.png) no-repeat;filter: progid:DXImageTransform.Microsoft.AlphaImageLoader(src='<%=request.getContextPath()%>/img/bmap/orangeBtn/bottom.png');_background-image:none;}


</style>  
<script type="text/javascript" src="http://api.map.baidu.com/api?v=1.5&ak=44843934aa23b524f4456723fea8dbdf"></script>
</head>  
   
<body>  
<div id="baiduMapContainer"></div>
<div id="onSaleSelect">
	<div class="onSaleSelectBtn">
		<input type="checkbox" name="onSale" id="onSaleState" checked="checked"/><span style="color:#009ACD">在售</span>
	</div>
	<div class="onSaleSelectBtn">
		<input type="checkbox" name="onSale" id="forSaleState"/><span style="color:#FFC125">待售</span>
	</div>
	<div class="onSaleSelectBtn">
		<input type="checkbox" name="onSale" id="sellOutState"/><span style="color:#8B2252">售完</span>
	</div>
</div>
<div id="priceSelect">
	<div id="priceSelectHead">
		<span id="totalRealEstate">共5个楼盘</span><div class="priceSelectRailing">|</div>
		<span name="priceType" value="0" class="selected">全部楼盘</span><div class="priceSelectRailing">|</div>
		<span name="priceType" value="1" >4000元以下</span><div class="priceSelectRailing">|</div>
		<span name="priceType" value="2" >4000~5000</span><div class="priceSelectRailing">|</div>
		<span name="priceType" value="3" >5000~6000</span><div class="priceSelectRailing">|</div>
		<span name="priceType" value="4" >6000~8000</span><div class="priceSelectRailing">|</div>
		<span name="priceType" value="5" >8000元以上</span>
		<a id="gotoListDisplay" href="http://baidu.com" target="_blank">→切换到列表模式</a>
	</div>
	<div id="priceSelectBody">
		
	</div>
	<div id="priceSelectFoot">
<!-- 		<input type="hidden" id="curPageFlag" value="0"/> -->
		<a href="javascript:void(0);" id="nextPageBtn">下一页</a>
		<a href="javascript:void(0);" id="prePageBtn">上一页</a>
	</div>
</div>
<script type="text/javascript">
//定义自定义覆盖物的构造函数  
function SquareOverlay(obj){        
	var center = new BMap.Point(obj.location[0], obj.location[1]);    
	this._center = center;    
	this._obj = obj;
}    
SquareOverlay.prototype = new BMap.Overlay();
SquareOverlay.prototype.initialize = function(map){    
	 this._map = map;        
	 var div = createPanesDiv(this._obj);
	 map.getPanes().markerPane.appendChild(div);      
	 this._div = div;      
	 return div;    
};
function createPanesDiv(obj){
	var name = obj.title;
	var price = "|均价{price}元/平方米";
	if(obj.price && obj.price>0){
		price = price.replace("{price}",obj.price);
	}else{
		price = "|暂无数据";
	}
	var onSale = obj.on_sale;
	var id = obj.content_id;
	var url = "http://baidu.com?id={id}";
	url = url.replace("{id}",id);
	
	var div = document.createElement("a"); 
	div.setAttribute("name",name);
	div.setAttribute("price",price);
	div.setAttribute("onSale",onSale);
	div.setAttribute("id",id);
// 	div.setAttribute("href",url);
// 	div.setAttribute("target","_blank");
	div.style.position = "absolute";    
	if(onSale==0){
		div.className = "mapFinddingCanvasLabelStyleBlue";
	}else if(onSale==1){
		div.className = "mapFinddingCanvasLabelStyleYellow";
	}else{
		div.className = "mapFinddingCanvasLabelStylePurple";
	}    
// 	var contentDiv = document.createElement("div");
// 	if(onSale==0){
// 		contentDiv.className = "mapFinddingCanvasLabelStyleBlue";
// 	}else if(onSale==1){
// 		contentDiv.className = "mapFinddingCanvasLabelStyleYellow";
// 	}else{
// 		contentDiv.className = "mapFinddingCanvasLabelStylePurple";
// 	}
	var tableStr = '<table cellpadding="0" cellspacing="0" border="0"><tr><td class="s1">&nbsp;</td><td class="s2">{name}<span>{price}</span></td><td class="s3">&nbsp;</td></tr><tr><td colspan="3" class="s5"></td></tr></table>';
	tableStr = tableStr.replace("{name}", name);
	tableStr = tableStr.replace("{price}",price);
// 	contentDiv.innerHTML = tableStr;
// 	div.appendChild(contentDiv);
	div.innerHTML = tableStr;
	$(div).mousemove(function(event){
		event.currentTarget.className = "mapFinddingCanvasLabelStyleOrange";
	});
	$(div).mouseout(function(event){
		var os = event.currentTarget.getAttribute("onSale");
		if(os==0){
			event.currentTarget.className = "mapFinddingCanvasLabelStyleBlue";
		}else if(os==1){
			event.currentTarget.className = "mapFinddingCanvasLabelStyleYellow";
		}else{
			event.currentTarget.className = "mapFinddingCanvasLabelStylePurple";
		}
	});
	$(div).click(function(event){
		window.open(url,"_blank");
	});
// 	div.onmouseover = function(event){
// 		event.currentTarget.className = "mapFinddingCanvasLabelStyleOrange";
// 	};
// 	div.onmouseout = function(event){
// 		var os = event.currentTarget.getAttribute("onSale");
// 		if(onSale==0){
// 			event.currentTarget.className = "mapFinddingCanvasLabelStyleBlue";
// 		}else if(onSale==1){
// 			event.currentTarget.className = "mapFinddingCanvasLabelStyleYellow";
// 		}else{
// 			event.currentTarget.className = "mapFinddingCanvasLabelStylePurple";
// 		}
// 	};
	return div;
}
SquareOverlay.prototype.draw = function(){    
 var position = this._map.pointToOverlayPixel(this._center);    
 this._div.style.left = position.x  + "px";    
 this._div.style.top = position.y-40 + "px";    
};

//实现显示方法    
SquareOverlay.prototype.show = function(){    
 if (this._div){    
   this._div.style.display = "";    
 }    
};
// 实现隐藏方法  
SquareOverlay.prototype.hide = function(){    
	 if (this._div){    
		this._div.style.display = "none";    
	 }    
};

//添加自定义方法   
SquareOverlay.prototype.toggle = function(){    
 if (this._div){    
   if (this._div.style.display == ""){    
     this.hide();    
   }    
   else {    
     this.show();    
   }    
 }    
};

var curPage = 0;
var pageSize = 50;
var overLayArr = new Array();
var reSearching = false;
var map = new BMap.Map("baiduMapContainer");  
$(function(){
	map.enableScrollWheelZoom();
	var point = new BMap.Point(112.479, 23.066);    
	map.centerAndZoom(point,14); 
	map.ondragend = function(){
		reSearch();
	};
	map.onzoomend = function(){
		reSearch();
	};

	$("#priceSelectHead").find("[name=priceType]").click(function(){
		selectPrice($(this).attr("value"));
		reSearch();
	});
	$("#onSaleSelect").find("[name=onSale]").click(function(){
		reSearch();
	});
	$("#nextPageBtn").click(function(){
		reSearch(curPage+1);
	});
	$("#prePageBtn").click(function(){
		reSearch(curPage-1);
	});
	reSearch();
});

function selectPrice(value){
	$("#priceSelectHead").find("[name=priceType]").each(function(){
		if($(this).attr("value")==value){
			this.className = "selected";
		}else{
			this.className = "";
		}
	});
}

function reSearch(page){
	if(reSearching){
		return;
	}
	reSearching = true;
	if(page){
		curPage = page;
	}else{
		curPage = 0;
	}
	var url = "<%=request.getContextPath()%>/industry/realEstate/json/bmap/list";
	var swPoint = map.getBounds().getSouthWest();
	var nePoint = map.getBounds().getNorthEast();
	var params = "&bounds="+swPoint.lng+","+swPoint.lat+";"+nePoint.lng+","+nePoint.lat+"&page_index="+curPage+"&page_size="+pageSize;

	var tempS;
// 	var oss = $("#onSaleState").attr('checked');
// 	var fss = $("#forSaleState").attr('checked');
// 	var sos = $("#sellOutState").attr('checked');
	var oss = $("#onSaleState")[0].checked;
	var fss = $("#forSaleState")[0].checked;
	var sos = $("#sellOutState")[0].checked;
	var onSaleArr = new Array();
	if(oss){
		onSaleArr.push(0);
	}
	if(fss){
		onSaleArr.push(1);
	}
	if(sos){
		onSaleArr.push(2);
	}
	if(onSaleArr.length==1){
		var num = onSaleArr.pop();
		tempS = "on_sale:["+num+","+num+"]";
	}else if(onSaleArr.length>1){
		tempS = "on_sale:["+onSaleArr.join()+"]";
		
	}
	var priceType = $("#priceSelectHead").find(".selected :first").attr("value");
	var tempP;
	if(priceType==1){
		tempP="price:0,4000";
	}else if(priceType==2){
		tempP="price:4000,5000";
	}else if(priceType==3){
		tempP="price:5000,6000";
	}else if(priceType==4){
		tempP="price:6000,8000";
	}else if(priceType==5){
		tempP="price:8000,99999";
	}
	if(tempS || tempP){
		params+="&filter=";
		if(tempS){
			params+=tempS;
			if(tempP){
				params+="|";
			}
		}
		if(tempP){
			params+=tempP;
		}
	}
	params = encodeURI(params);
// 	alert(params);
	
	$.ajax({
		url : url,
		dataType:'json',
		data:{params:params},
		success: function(json){
// 			alert(json.total);
			clearOverLay();
			addOverLay(json);
		},
		error:function(e){
		},
		complete:function(e){
			reSearching = false;
		}
	}); 
}

function clearOverLay(){
	while(overLayArr.length>0){
		map.removeOverlay(overLayArr.pop());
	}
	$("#priceSelectBody").empty();
}
function addOverLay(json){
	var contents;
	var total = json.total;
	if(total==0){
		contents = new Array()
	}else{
		contents = json.contents;
	}
	for(var i=0;i<contents.length;i++){
		var mySquare = new SquareOverlay(contents[i]);
		map.addOverlay(mySquare);
		overLayArr.push(mySquare);
		var tcn; 
		if(i%4==0){
			tcn = "linkBtn odd";
		}else{
			tcn = "linkBtn";
		}
		$("#priceSelectBody").append("<a href='http://www.baidu.com' target='_blank' class='"+tcn+"'>"+contents[i].title+"</a>");
	}
// 	for(var k=0;k<pageSize;k++){
// 		var tcn; 
// 		if(k%4==0){
// 			tcn = "linkBtn odd";
// 		}else{
// 			tcn = "linkBtn";
// 		}
// 		$("#priceSelectBody").append("<a href='http://www.baidu.com' class='"+tcn+"'>"+contents[k%contents.length].title+"</a>");
// 	}
// 	$("#totalRealEstate").html("共"+max+"个楼盘");
	$("#prePageBtn").hide();
	$("#nextPageBtn").hide();
	if(total>pageSize){
		var totalPage = Math.ceil(total/pageSize);
		if(curPage>0){
			$("#prePageBtn").show();
		}
		if(curPage<totalPage-1){
			$("#nextPageBtn").show();
		}
	}
	$("#totalRealEstate").html("共"+total+"个楼盘");
}
</script>  
</body>  
</html>