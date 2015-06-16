<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../inc/top.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@include file="../inc/header.jsp" %>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=44843934aa23b524f4456723fea8dbdf"></script>
</head>
<body>
	<div class="main">
		<header>
			<div class="left">
				<a href="${ctx }/index" class="hLogo"></a>
			</div>
			<div class="cent">新房地图</div>
			<div class="show_redrict head-icon">
			<a href="javascript:spreadList();" id="open_conditionwrap" style="display: block;"><div class="ico-lp" ><i></i><p class="ico-lp-text">过滤</p></div></a>
				<a class="icon-nav" id="show_redrict" href="javascript:void(0);"
					onclick="hideOrOpenNav()"> <span><i></i>
						<p>导航</p></span>
				</a>
			</div>
		</header>
		<%@include file="../inc/nav.jsp" %>
		<div id="newNav" class="newNav none">
			<div class="nav-box mt10">
				<div class="nav-tit">
					<a href="index.html"><span id="userinfo">个人中心</span></a> <strong>频道导航</strong>
				</div>
				<div class="nav-menu">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tbody>
							<tr>
								<td width="24%"><a href="../index.html">首页</a></td>
								<td width="24%"><a href="/xf/bj.html">新房</a></td>
								<td width="24%"><a href="/esf/bj/">二手房</a></td>
								<td width="24%"><a href="/zf/bj/">租房</a></td>
							</tr>
							<tr>
								<td><a href="/jiaju/bj.html">商铺</a></td>
								<td><a href="/zixun/bj.html#tt">写字楼</a></td>
								<td><a href="/jiaju/bj.html">仓库</a></td>
								<td><a href="/zixun/bj.html#tt">厂房</a></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>

		</div>

		<section class="whitebg newNav none" id="criteriaForm">
			<div class="searchCriteria">
				<ul id="criteriaList" class="criteriaList">
					<li class="spread">
						<p>
							<span class="js-selected" data-name="region" data-condition=""> 不限 </span>
							<span class="js-sift-condition none" data-condition=""> 不限</span>
							<span class="js-sift-condition" data-condition="cd"> 城东 </span>
							<span class="js-sift-condition" data-condition="cx"> 城西 </span>
							<span class="js-sift-condition" data-condition="cz"> 城中 </span>
							<span class="js-sift-condition" data-condition="sh"> 四会 </span>
							<span class="js-sift-condition" data-condition="gy"> 高要 </span>
							<span class="js-sift-condition" data-condition="gn"> 广宁 </span>
							<span class="js-sift-condition" data-condition="hj"> 怀集 </span>
							<span class="js-sift-condition" data-condition="fk"> 封开 </span>
							<span class="js-sift-condition" data-condition="dq"> 德庆 </span>
							<span class="js-sift-condition" data-condition="dh"> 鼎湖 </span>
						</p> <strong>区域</strong> <em class="arrowUp"></em>
					</li>
					<li>
						<p>
							<span class="js-selected"  data-name="averagePrice" data-condition=""> 不限 </span>
							<span class="js-sift-condition none" data-condition=""> 不限</span>
							<span class="js-sift-condition" data-condition="1:4999"> 5000以下</span>
							<span class="js-sift-condition" data-condition="5000:5999"> 5000-6000</span>
							<span class="js-sift-condition" data-condition="6000:6999"> 6000-7000</span>
							<span class="js-sift-condition" data-condition="7000:7999"> 7000-8000</span>
							<span class="js-sift-condition" data-condition="8000:9999"> 8000-10000</span>
							<span class="js-sift-condition" data-condition="10000:1000000"> 10000以上</span>
						</p> <strong>价格</strong> <em class="arrowDown"></em>
					</li>
					<li>
						<p>
							<span class="js-selected"  data-name="roomNum" data-condition=""> 不限 </span>
							<span class="js-sift-condition none" data-condition=""> 不限</span>
							<span class="js-sift-condition" data-condition="1"> 一居 </span>
							<span class="js-sift-condition" data-condition="2"> 二居 </span>
							<span class="js-sift-condition" data-condition="3"> 三居 </span>
							<span class="js-sift-condition" data-condition="4"> 四居 </span>
							<span class="js-sift-condition" data-condition="5"> 五居 </span>
							<span class="js-sift-condition" data-condition="6"> 五居以上 </span>
						</p> <strong>户型</strong> <em class="arrowDown"></em>
					</li>
					<li >
						<p>
							<span class="js-selected"  data-name="grossFloorArea" data-condition=""> 不限 </span>
							<span class="js-sift-condition none" data-condition=""> 不限</span>
							<span class="js-sift-condition" data-condition="1:49"> 50以下</span>
							<span class="js-sift-condition" data-condition="50:69"> 50-70 </span>
							<span class="js-sift-condition" data-condition="70:89"> 70-90 </span>
							<span class="js-sift-condition" data-condition="90:109"> 90-110 </span>
							<span class="js-sift-condition" data-condition="110:129"> 110-130</span>
							<span class="js-sift-condition" data-condition="130:149"> 130-150</span>
							<span class="js-sift-condition" data-condition="150:199"> 150-200</span>
							<span class="js-sift-condition" data-condition="200:10000"> 200以上</span>
						</p> <strong>面积</strong> <em class="arrowDown"></em>
					</li>
					<li >
						<p>
							<span class="js-selected" data-condition="saltStatus" data-condition=""> 不限 </span>
							<span class="js-sift-condition none" data-condition=""> 不限</span>
							<span class="js-sift-condition" data-condition="1"> 在售 </span>
							<span class="js-sift-condition" data-condition="2"> 待售 </span>
							<span class="js-sift-condition" data-condition="3"> 售完 </span>
						</p> <strong>销售</strong> 
					</li>
				</ul>
					
					<a href="javascript:searchBtnClick();" class="formbtn02 mt10 mb10" >筛选</a>
			</div>
		</section>
			<div id="allmap" ></div>
			<div id="pos" class="map-pos"></div>
	</div>
</body>
<%@include file="../inc/bottom.jsp" %>
<script>
$(function() {
	initEvent();

	$(window).resize(function() {
		fitSize();
	});
	fitSize();
	initMap();
})

	function fitSize() {
		var bw = $("body").width();
		if (bw > 640) {
			bw = 640;
		}
		var bh = $("body").height();
		$("#allmap").width(bw);
		$("#allmap").height(bh-44);
	}
function initEvent() {
	$("#criteriaList").children("li").children("em").click(function() {
		spreadItem(this);
	});
	$("#criteriaList").children("li").children("p").children(
			".js-sift-condition").click(function() {
		selectItem(this);
	})
}
	function hideOrOpenNav() {
		var navObj = $("#newNav");
		var shadow = $(".popShadow");
		if (navObj.hasClass("none")) {
			navObj.removeClass("none");
			shadow.removeClass("none");
		} else {
			navObj.addClass("none");
			shadow.addClass("none");
		}
	}
	function spreadList() {
		var obj = $("#criteriaForm");
		if(obj.hasClass("none")){
			obj.removeClass("none");
		}else{
			obj.addClass("none");
		}
	}
	function spreadItem(ele) {
		var element = $(ele);
		var li = element.parents("li:first");
		if (element.hasClass("arrowDown")) {
			element.removeClass("arrowDown");
			element.addClass("arrowUp");
			li.addClass("spread");
		} else {
			element.addClass("arrowDown");
			element.removeClass("arrowUp");
			li.removeClass("spread");
		}
	}
	function selectItem(ele) {
		var element = $(ele);
		element.siblings(".js-sift-condition").removeClass("none");
		element.addClass("none");
		element.siblings(".js-selected").html(element.html());
	}
	
	
	var queryConditions = new Object();
	var queryingConditions = new Object();
	var toQueryConditions = new Object();
	
	var reSearching = false;
	var mapLabelList = [];
	var mp;
	
	var moved = false;
	var targetId;
	function initMap(){
		// 百度地图API功能
		var defaultPoint = new BMap.Point(112.475947,23.060117);
		mp = new BMap.Map("allmap");
		mp.setMinZoom(12);
		mp.ondragend = function(){
			reSearch();
		};
		mp.onzoomend = function(){
			reSearch();
		};
		mp.centerAndZoom(defaultPoint, 15);

		
		$("#pos").click(function(){
			getLocation();
		})
		initTestData();
		getLocation();
	}
	function initTestData(){
		//112.42-112.52 23.05-23.10
		for(var i=0;i<100;i++){
			var lon = Math.random();
			var lat = Math.random();
			while(lon<0.05 || lon>0.1){
				lon = Math.random();
			}
			while(lat<0.42 || lat>0.52){
				lat = Math.random();
			}
			lon+=23;
			lat+=112;
		    var myCompOverlay = new ComplexCustomOverlay(new BMap.Point(lat,lon), "name"+i,"id"+i);
// 			var myCompOverlay = new BMap.Marker(new BMap.Point(lat,lon));
		    mapLabelList.push(myCompOverlay);
// 			 mp.addOverlay(myCompOverlay);
		}
	}

	function reSearch(){
		if(reSearching){
			return;
		}
		reSearching = true;
		setTimeout(function(){
			var swPoint = mp.getBounds().getSouthWest();
			var nePoint = mp.getBounds().getNorthEast();
			var tempList = new Array();
	 		mp.clearOverlays();
// 	 		mapLabelList = [];
// 	 		initTestData();
			for(var i=0;i<mapLabelList.length;i++){
				var point = mapLabelList[i]._point;
// 				var point = mapLabelList[i].point;
				if(point.lat<=nePoint.lat && point.lng<=nePoint.lng && point.lat>swPoint.lat && point.lng>swPoint.lng){
					tempList.push(mapLabelList[i]);
					 mp.addOverlay(mapLabelList[i]);
				}
			}
// 			var overlays = mp.getOverlays();
// //	 		var delList = new Array();
// 			for(var j=0;j<overlays.length;j++){
// 				var existed = false;
// 				for(var k=0;k<tempList.length;k++){
// 					if(overlays[j]._dataId==tempList[k]._dataId){
// 						existed = true;
// 						break;
// 					}
// 				}
// 				if(!existed){
// //	 				delList.push(overlays[j]);
// 					overlays[j].hide();
// // 					mp.removeOverlay(overlays[j]);
// 				}else{
// 					overlays[j].show();
// 				}
// 			}
// 			var markerClusterer = new BMapLib.MarkerClusterer(mp, {markers:mapLabelList});
// 			var options = {  
// 					pageCapacity:100,
// 				      onSearchComplete: function(results){      
// 				          if (local.getStatus() == BMAP_STATUS_SUCCESS){      
// 				                // 判断状态是否正确      
// 				                var s = [];      
// 				                for (var i = 0; i < results.getCurrentNumPois(); i ++){      
// 				                    s.push(results.getPoi(i).title + ", " + results.getPoi(i).address); 
// 				                    addIcon(results.getPoi(i).point);    
// 				                }      
				                
// // 				             document.getElementById("log").innerHTML = s.join("<br>");      
// 				          }      
// 				      }      
// 				 };      
// 				var local = new BMap.LocalSearch(mp, options);  
// // 				var local = new BMap.LocalSearch(mp,{
// // 					renderOptions:{map: mp}
// // 				});       
// 				local.searchInBounds("公园",mp.getBounds());
			reSearching = false;
		},500);
//			var pStart = swPoint;
//			var pEnd = nePoint;
//			var bs = new BMap.Bounds(pStart,pEnd);   //自己规定范围
//			ll.searchInBounds("银行", bs);
//			44843934aa23b524f4456723fea8dbdf
//			url = "http://api.map.baidu.com/place/v2/search?q=饭店&region=北京&output=json&ak=E4805d16520de693a3fe707cdc962045";
//				$.ajax({
//					url : url,
//					dataType:'json',
//// 					data:{params:params},
//					success: function(json){
//			 			alert(json);
//					},
//					error:function(e){
//					},
//					complete:function(e){
//						reSearching = false;
//					}
//				}); 
//			ll.search("公交车站");
//			alert(swPoint.lng+"_"+swPoint.lat +"_"+nePoint.lng+"_"+nePoint.lat);
// 		if(mapLabelList.length>0){
// 			for(var i=0;i<mapLabelList.length;i++){
// 				mp.removeOverlay(mapLabelList[i]);
// 			}
// //				mapLabelList = new Array();
// 		}else{
// 		    var myCompOverlay = new ComplexCustomOverlay(new BMap.Point(112.511736,23.082917), "敏捷城","11111");
// 		    mp.addOverlay(myCompOverlay);
// //			    mapLabelList.push(myCompOverlay);
// 		}
	}
// 	function addIcon(point,imgUrl){
// 		var myIcon = new BMap.Icon("http://developer.baidu.com/map/jsdemo/img/fox.gif", new BMap.Size(21,21));
// 		var marker2 = new BMap.Marker(point,{icon:myIcon});  // 创建标注
// 		mp.addOverlay(marker2);              // 将标注添加到地图中
// 	}
	function getLocation()
	  {
		//alert(navigator.geolocation);
	  if (navigator.geolocation)
	    {
	    navigator.geolocation.getCurrentPosition(showPosition);
	    }
	  else{mp.centerAndZoom(defaultPoint, 15);}
	  }
	function showPosition(position)
	  {
		
		var point = new BMap.Point(position.coords.longitude,position.coords.latitude);
		var geoc = new BMap.Geocoder();    
		
			geoc.getLocation(point, function(rs){
				var addComp = rs.addressComponents;
				if(addComp.city=="肇庆市"){
					mp.centerAndZoom(point, 15);
				}else{
					alert("目前只支持肇庆地区");
					mp.centerAndZoom(defaultPoint, 15);
				}
				reSearch();
// 	 			alert(addComp.province + ", " + addComp.city + ", " + addComp.district + ", " + addComp.street + ", " + addComp.streetNumber);
			});        
//	 	var point = new BMap.Point(112.475902,23.060233);
//	 	var mk = new BMap.Marker(point);
//	 	mp.addOverlay(mk);
//	 	mp.panTo(point);
//	 	mp.centerAndZoom(point, 15);
//	 	alert('您的位置：'+point.lng+','+point.lat+"_112.475902,23.060233");
	  }

	// 复杂的自定义覆盖物
    function ComplexCustomOverlay(point, text, dataId){
      this._point = point;
      this._text = text;
      this._dataId = dataId;
    }
    ComplexCustomOverlay.prototype = new BMap.Overlay();
    ComplexCustomOverlay.prototype.initialize = function(map){
      this._map = map;
      var div = this._div = document.createElement("div");
      div.style.position = "absolute";
      div.style.zIndex = BMap.Overlay.getZIndex(this._point.lat);
      div.style.backgroundColor = "#63B8FF";
      div.style.border = "1px solid #60a0c3";
      div.style.color = "white";
      div.style.height = "18px";
      div.style.padding = "2px";
      div.style.lineHeight = "18px";
      div.style.whiteSpace = "nowrap";
      div.style.MozUserSelect = "none";
      div.style.fontSize = "12px"
      var span = this._span = document.createElement("span");
      div.appendChild(span);
      span.appendChild(document.createTextNode(this._text));      
      var that = this;

      var arrow = this._arrow = document.createElement("div");
      arrow.style.background = "url(../../../img/ui/icons/map_label.png) 0px -20px no-repeat";
      arrow.style.position = "absolute";
      arrow.style.width = "11px";
      arrow.style.height = "10px";
      arrow.style.top = "22px";
      arrow.style.left = "10px";
      arrow.style.overflow = "hidden";
      div.appendChild(arrow);
      
      div.addEventListener("touchend",function(){
    	  if(!moved && targetId){
    		  top.location='detail.html?dataId='+targetId;
    	  }
      })
      div.addEventListener("touchmove",function(){
    	  moved = true;
      })
      
      div.addEventListener("touchstart",function(){
    	  moved = false;
    	  targetId = that._dataId;
      })
      mp.getPanes().labelPane.appendChild(div);
      
      return div;
    }
    ComplexCustomOverlay.prototype.draw = function(){
      var map = this._map;
      if(map.getZoom()<15){
    	  this._span.className = "none";
    	  this._arrow.className = "none";
//     	  this._div.style.border = "0px none";
    	  this._div.style.border = "2px solid #60a0c3";
    	  this._div.style.width = "2px";
    	  this._div.style.height = "2px";
    	  this._div.style.borderRadius = "5px";
      }else{
    	  this._span.className = "";
    	  this._arrow.className = "";
    	  this._div.style.width = "auto";
    	  this._div.style.borderRadius = "0px";
    	  this._div.style.border = "1px solid #60a0c3";
    	  this._div.style.height = "18px";
      }
      var pixel = map.pointToOverlayPixel(this._point);
      this._div.style.left = pixel.x - parseInt(this._arrow.style.left) + "px";
      this._div.style.top  = pixel.y - 30 + "px";
    }
	
	function resetToQueryConditions(){
		$(".js-selected").each(function(){
			toQueryConditions[$(this).attr("data-name")] = $(this).attr("data-condition");
		});
	}
	function resetQueryingConditions(){
		$(".js-selected").each(function(){
			queryingConditions[$(this).attr("data-name")] = toQueryConditions[$(this).attr("data-name")];
		});
	}
	function queryConditionChanged(){
		$(".js-selected").each(function(){
			if(toQueryConditions[$(this).attr("data-name")] != queryingConditions[$(this).attr("data-name")]){
				return true;
			}
		});
		return false;
	}
	function searchBtnClick(){
		resetToQueryConditions();
		reSearch();
		spreadList();
	}
</script>
</html>