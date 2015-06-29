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
			<a href="javascript:void(0);" onclick="hideOrOpenNav(this)" id="open_conditionwrap" style="display: block;"><div class="ico-lp" ><i></i><p class="ico-lp-text">过滤</p></div></a>
				<a class="icon-nav" id="show_redrict" href="javascript:void(0);"
					onclick="hideOrOpenNav()"> <span><i></i>
						<p>导航</p></span>
				</a>
			</div>
		</header>
		<%@include file="../inc/nav.jsp" %>
		<section class="whitebg newNav none" id="criteriaForm">
			<div class="searchCriteria">
				<ul id="criteriaList" class="criteriaList">
					<li class="spread">
						<p>
							<span class="js-selected" data-name="region" data-condition=""> 不限 </span>
							<span class="js-sift-condition none" data-condition=""> 不限</span>
							<span class="js-sift-condition" data-condition="城东"> 城东 </span>
							<span class="js-sift-condition" data-condition="城西"> 城西 </span>
							<span class="js-sift-condition" data-condition="城中"> 城中 </span>
							<span class="js-sift-condition" data-condition="四会"> 四会 </span>
							<span class="js-sift-condition" data-condition="高要"> 高要 </span>
							<span class="js-sift-condition" data-condition="广宁"> 广宁 </span>
							<span class="js-sift-condition" data-condition="怀集"> 怀集 </span>
							<span class="js-sift-condition" data-condition="封开"> 封开 </span>
							<span class="js-sift-condition" data-condition="德庆"> 德庆 </span>
							<span class="js-sift-condition" data-condition="鼎湖"> 鼎湖 </span>
						</p> <strong>区域</strong> <em class="arrowUp"></em>
					</li>
					<li>
						<p>
							<span class="js-selected"  data-name="averagePrice" data-condition=""> 不限 </span>
							<span class="js-sift-condition none" data-condition=""> 不限</span>
							<span class="js-sift-condition" data-condition="1:4999"> 4999或以下</span>
							<span class="js-sift-condition" data-condition="5000:5999"> 5000-5999</span>
							<span class="js-sift-condition" data-condition="6000:6999"> 6000-6999</span>
							<span class="js-sift-condition" data-condition="7000:7999"> 7000-7999</span>
							<span class="js-sift-condition" data-condition="8000:9999"> 8000-9999</span>
							<span class="js-sift-condition" data-condition="10000:1000000"> 10000或以上</span>
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
							<span class="js-sift-condition" data-condition="1:49"> 49或以下</span>
							<span class="js-sift-condition" data-condition="50:69"> 50-69 </span>
							<span class="js-sift-condition" data-condition="70:89"> 70-89 </span>
							<span class="js-sift-condition" data-condition="90:109"> 90-109 </span>
							<span class="js-sift-condition" data-condition="110:129"> 110-129</span>
							<span class="js-sift-condition" data-condition="130:149"> 130-149</span>
							<span class="js-sift-condition" data-condition="150:199"> 150-199</span>
							<span class="js-sift-condition" data-condition="200:10000"> 200或以上</span>
						</p> <strong>面积</strong> <em class="arrowDown"></em>
					</li>
					<li >
						<p>
							<span class="js-selected" data-name="saleStatus" data-condition=""> 不限 </span>
							<span class="js-sift-condition none" data-condition=""> 不限</span>
							<span class="js-sift-condition" data-condition="在售"> 在售 </span>
							<span class="js-sift-condition" data-condition="待售"> 待售 </span>
							<span class="js-sift-condition" data-condition="售完"> 售完 </span>
						</p> <strong>销售</strong> 
					</li>
				</ul>
					
					<a href="javascript:searchBtnClick();" class="formbtn02 mt10 mb10" >筛选</a>
			</div>
		</section>
			<div id="allmap" ></div>
			<div id="pos" class="map-pos"></div>
	</div>
	<input type="hidden" id="defaultLon" value="${rep.longitude}">
	<input type="hidden" id="defaultLat" value="${rep.latitude}">
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
	});

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
		});
	}
	function hideOrOpenNav(element) {
		var navObj = $(".newNav");
		var shadow = $(".popShadow");
		if(element && $(element).attr("id")=="open_conditionwrap"){
			shadow.addClass("none");
			navObj.each(function(){
				var obj = $(this);
				if(obj.attr("id")=="criteriaForm"){
					if(obj.hasClass("none")){
						obj.removeClass("none");
					}else{
						obj.addClass("none");
					}
				}else{
					obj.addClass("none");
				}
			});
		}else{
			navObj.each(function(){
				var obj = $(this);
				if(obj.attr("id")=="criteriaForm"){
					obj.addClass("none");
				}else{
					if(obj.hasClass("none")){
						obj.removeClass("none");
						shadow.removeClass("none");
					}else{
						obj.addClass("none");
						shadow.addClass("none");
					}
				}
			});
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
		element.siblings(".js-selected").attr("data-condition",element.attr("data-condition"));
	}
	
	
	var queryConditions = new Object();
	var queryingConditions = new Object();
	var toQueryConditions = new Object();
	resetToQueryConditions();
	
	var reSearching = false;
	var mapLabelList = [];
	var mp;
	
	var moved = false;
	var targetId;
	function initMap(){
		// 百度地图API功能
		var defaultLon = $("#defaultLon").val();
		var defaultLat = $("#defaultLat").val();
		var defaultPoint = new BMap.Point(112.475947,23.060117);
		if(defaultLon && defaultLat){
			var defaultPoint = new BMap.Point(defaultLon,defaultLat);
		}
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
		});
		if(!defaultLon){
			getLocation();
		}
	}

	function reSearch(){
		if(reSearching){
			return;
		}
		reSearching = true;
		resetQueryingConditions();
		
		var url = "${ctx}/xf/json/{keyWord}-{region}-{averagePrice}-{roomNum}-{grossFloorArea}-{saleStatus}-{resultType}-{bound}-{orderBy}-{targetPage}-{pageSize}/search";
		url = bindSearchData(url);
		$.ajax({
			url : url,
			dataType:'json',
			success: function(json){
				if(json.success){
			 		mp.clearOverlays();
					for(var i=0;i<json.results.length;i++){
						var result = json.results[i];
					    var overlay = new ComplexCustomOverlay(new BMap.Point(result.longitude,result.latitude), result.name,result.id);
						 mp.addOverlay(overlay);
					}
				}else if(json.msg){
					alert(json.msg);
				}
			},
			error:function(e){
				alert("搜索失败！");
			},
			complete:function(e){
				reSearching = false;
				if(queryConditionChanged()){
					reSearch();
				}
			}
		}); 
	}
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
			});        
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
      div.style.fontSize = "12px";
      var span = this._span = document.createElement("span");
      div.appendChild(span);
      span.appendChild(document.createTextNode(this._text));      
      var that = this;

      var arrow = this._arrow = document.createElement("div");
      arrow.style.background = "url(${ctx}/assets/img/ui/icons/map_label.png) 0px -20px no-repeat";
      arrow.style.position = "absolute";
      arrow.style.width = "11px";
      arrow.style.height = "10px";
      arrow.style.top = "22px";
      arrow.style.left = "10px";
      arrow.style.overflow = "hidden";
      div.appendChild(arrow);
      
      div.addEventListener("touchend",function(){
    	  if(!moved && targetId){
    		  top.location='${ctx}/xf/'+targetId+'/detail';
    	  }
      });
      div.addEventListener("touchmove",function(){
    	  moved = true;
      });
      
      div.addEventListener("touchstart",function(){
    	  moved = false;
    	  targetId = that._dataId;
      });
      mp.getPanes().labelPane.appendChild(div);
      
      return div;
    };
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
    };
	
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
	function bindSearchData(url){
		
		var region = queryingConditions["region"];
		var averagePrice = queryingConditions["averagePrice"];
		var roomNum = queryingConditions["roomNum"];
		var grossFloorArea = queryingConditions["grossFloorArea"];
		var saleStatus = queryingConditions["saleStatus"];
		var swPoint = mp.getBounds().getSouthWest();
		var nePoint = mp.getBounds().getNorthEast();
		var bound = swPoint.lng+":"+swPoint.lat+":"+nePoint.lng+":"+nePoint.lat;
		
		url = url.replace("{keyWord}","");
		url = url.replace("{region}",region);
		url = url.replace("{averagePrice}",averagePrice);
		url = url.replace("{roomNum}",roomNum);
		url = url.replace("{grossFloorArea}",grossFloorArea);
		url = url.replace("{saleStatus}",saleStatus);
		url = url.replace("{resultType}","楼盘");
		url = url.replace("{orderBy}","");

		url = url.replace("{targetPage}",0);
		url = url.replace("{pageSize}",500);
		url = url.replace("{bound}",bound);
		return url;
	}
	function searchBtnClick(){
		resetToQueryConditions();
		reSearch();
		$("#criteriaForm").addClass("none");
	}
</script>
</html>