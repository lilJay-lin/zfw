<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../inc/top.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@include file="../inc/header.jsp" %>
</head>
<body>
	<div class="main">
		<header>
			<div class="left">
				<a href="${ctx }/index" class="hLogo"></a>
			</div>
			<div class="cent">新房</div>
			<div class="show_redrict head-icon">
				<a class="icon-nav" id="show_redrict" href="javascript:void(0);"
					onclick="hideOrOpenNav()"> <span><i></i>
						<p>导航</p></span>
				</a>
			</div>
		</header>
		<%@include file="../inc/nav.jsp" %>
		<form class="search0620_new flexbox" name="wapSearchForm" action=""
			onsubmit="return false;" method="get" autocomplete="off">
			<div class="searbox_new">
				<div class="ipt" id="wapdsy_D01_09">
					<input id="S_searchtext" type="search" name="q" value=""
						placeholder="楼盘名/地名" autocomplete="off"><a
						href="javascript:;" class="off" style="display: none;"></a>
				</div>
				<a href="javascript:;" id="wapdsy_D01_18" class="btn" rel="nofollow"><i></i></a>
			</div>
			<a id="wapxfsy_D01_07" href="map.html"
				class="mapbtn">地图</a>
		</form>

		<section class="whitebg bdt bdb">
			<div class="searchCriteria">
				<ul id="criteriaList" class="criteriaList">
					<li class="spread">
						<p>
							<span class="js-selected"> 不限 </span>
							<span class="js-sift-condition none"> 不限</span>
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
							<span class="js-selected"> 不限 </span>
							<span class="js-sift-condition none"> 不限</span>
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
							<span class="js-selected"> 不限 </span>
							<span class="js-sift-condition none"> 不限</span>
							<span class="js-sift-condition" data-condition="1"> 一居 </span>
							<span class="js-sift-condition" data-condition="2"> 二居 </span>
							<span class="js-sift-condition" data-condition="3"> 三居 </span>
							<span class="js-sift-condition" data-condition="4"> 四居 </span>
							<span class="js-sift-condition" data-condition="5"> 五居 </span>
							<span class="js-sift-condition" data-condition="6"> 五居以上 </span>
						</p> <strong>户型</strong> <em class="arrowDown"></em>
					</li>
					<li class="notImportant none">
						<p>
							<span class="js-selected"> 不限 </span>
							<span class="js-sift-condition none"> 不限</span>
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
					<li class="notImportant none">
						<p>
							<span class="js-selected"> 不限 </span>
							<span class="js-sift-condition none"> 不限</span>
							<span class="js-sift-condition" data-condition="1"> 在售 </span>
							<span class="js-sift-condition" data-condition="2"> 待售 </span>
							<span class="js-sift-condition" data-condition="3"> 售完 </span>
						</p> <strong>销售</strong> 
					</li>
					<li class="notImportant none">
						<p>
							<span class="js-selected">楼盘 </span> <span
								class="js-sift-condition none" data-condition="1">楼盘 </span> <span
								class="js-sift-condition" data-condition="2">户型</span>
						</p> <strong>类型</strong>
					</li>
				</ul>

				<div id="listMore" class="down" onclick="spreadList()">
					<span class="icon"></span> <span class="txt">展开更多查询条件</span>
				</div>
			</div>
		</section>
		<div class="resultWarp whitebg mt10 bdt">
			<h5 id="search_result_num">
				<h5>共搜索到61个楼盘</h5>
			</h5>

			<span> <select id="search_condition_order">
					<option value="default">默认排序</option>
					<option value="11">价格由高到低</option>
					<option value="10">价格由低到高</option>
					<option value="16">开盘时间由近到远</option>
			</select>
			</span>
		</div>
		<section class="homeList whitebg bdb">
			<ul id="itemList">
				<li><a id="" href="/xf/bj/1010710185.htm">
						<div class="img">
							<img
								src="http://i3.sinaimg.cn/hs/2010/0901/S18375T1283345502659.jpg">
						</div>
						<div class="txt">
							<p class="x-intro">
							<h2>一杯澜</h2>
							<span class="new">20000元/平</span>
							</p>
							<p>北戴河新区国际娱乐中心东3公里（原碧海蓝天度假村）</p>

							<div class="stag">
								<span class="t1">户型：二居(<span class="t2">1</span>) 三居(<span
									class="t2">2</span>)
								</span>
							</div>

						</div>
				</a></li>
				<li><a id="" href="/xf/bj/1010710185.htm">
						<div class="img">
							<img
								src="http://i3.sinaimg.cn/hs/2010/0901/S18375T1283345502659.jpg">
						</div>
						<div class="txt">
							<p class="x-intro">
							<h2>一杯澜</h2>
							<span class="new">20000元/平</span>
							</p>
							<p>北戴河新区国际娱乐中心东3公里（原碧海蓝天度假村）</p>

							<div class="stag">
								<span class="t1">户型：二居(<span class="t2">1</span>) 三居(<span
									class="t2">2</span>)
								</span>
							</div>

						</div>
				</a></li>
				<li><a id="" href="/xf/bj/1010710185.htm">
						<div class="img">
							<img
								src="http://i3.sinaimg.cn/hs/2010/0901/S18375T1283345502659.jpg">
						</div>
						<div class="txt">
							<p class="x-intro">
							<h2>一杯澜</h2>
							<span class="new">20000元/平</span>
							</p>
							<p>北戴河新区国际娱乐中心东3公里（原碧海蓝天度假村）</p>

							<div class="stag">
								<span class="t1">户型：二居(<span class="t2">1</span>) 三居(<span
									class="t2">2</span>)
								</span>
							</div>

						</div>
				</a></li>
			</ul>
		</section>
		<div id="drag" class="cenBtn">
			<a class="draginner" href="javascript:void(0);">查看更多楼盘</a>
		</div>
		<%@include file="../inc/footer.jsp" %>
		<%@include file="../inc/goHead.jsp" %>
	</div>
</body>
<%@include file="../inc/bottom.jsp" %>
<script>
var total=10;
var allowLoad = true;
var curp = 2;

	$(function() {
		initEvent();
	})
	
	function initEvent() {
		initScrollEvent();
		$("#criteriaList").children("li").children("em").click(function() {
			spreadItem(this);
		});
		$("#criteriaList").children("li").children("p").children(
				".js-sift-condition").click(function() {
			selectItem(this);
		})
	}
	function initScrollEvent(){
		$("body").scrollTop(1);
		if(total<=1){
			$('#drag').hide();
		    allowLoad = false;
		}
		//滚动到页面底部时，自动加载更多
		var scrollFlag=false;
		window.addEventListener("scroll",function scrollHandler(){
			
			var scrollh = $(document).height();
			var bua = navigator.userAgent.toLowerCase();
			if(scrollFlag){
				if(bua.indexOf('iphone') != -1 || bua.indexOf('ios') != -1){
					scrollh = scrollh-140;
				}else{
					scrollh = scrollh-80;
				}
			}
			var c=document.documentElement.clientHeight || document.body.clientHeight, t=$(document).scrollTop();
			if(allowLoad != false && ($(document).scrollTop() + $(window).height()) >= scrollh){
				load();
			}
		},100); 
		$('#drag').click(function () {
			load();
		});
	}
	function hideOrOpenNav() {
		var navObj = $(".newNav");
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
		var btn = $("#listMore");
		if (btn.hasClass("down")) {
			btn.removeClass("down");
			btn.addClass("up");
			btn.find(".txt").html("收起更多查询条件");
			$("#criteriaList").children("li.notImportant").removeClass("none");
		} else {
			btn.removeClass("up");
			btn.addClass("down");
			btn.find(".txt").html("展开更多查询条件");
			$("#criteriaList").children("li.notImportant").addClass("none");
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
	function load(){
		var draginner=$('.draginner');
		if(draginner.hasClass("loading")){
			return;
		}
		allowLoad = false;
		draginner.css('padding-left','10px');
		draginner.addClass("loading");
		draginner.html("正在加载...");
		setTimeout(function(){
			var str = '<li><a id="" href="/xf/bj/1010710185.htm"> <div class="img"> <img src="http://i3.sinaimg.cn/hs/2010/0901/S18375T1283345502659.jpg"> </div> <div class="txt"> <p class="x-intro"> <h2>一杯澜</h2> <span class="new">20000元/平</span> </p> <p>北戴河新区国际娱乐中心东3公里（原碧海蓝天度假村）</p> <div class="stag"> <span class="t1">户型：二居(<span class="t2">1</span>) 三居(<span class="t2">2</span>)</span></div></div></a></li>';
			$('#itemList').append(str);
			$('#itemList').append(str);
			$('#itemList').append(str);
			$('#itemList').append(str);
			$('#itemList').append(str);
			draginner.css('padding-left','0px');
			draginner.removeClass("loading");
			draginner.html("查看更多楼盘");
			curp=curp+1;
			allowLoad = true;
			if(curp>total){
				$('#drag').hide();
				allowLoad = false;
			}
		},1000);
	}
</script>
</html>