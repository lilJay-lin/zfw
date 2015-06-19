<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../inc/top.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@include file="../inc/header.jsp" %>
<script type="text/javascript"
	src="${ctx }/assets/js/jquery.event.drag-1.5.min.js"></script>
<script src="${ctx }/assets/js/jquery.touchSlider.js"></script>
</head>
<body>
	<div class="main">
		<header>
			<div class="left">
				<a href="${ctx }/index" class="hLogo"></a>
			</div>
			<div class="cent">户型详情</div>
			<div class="show_redrict head-icon">
				<a class="icon-nav" id="show_redrict" href="javascript:void(0);"
					onclick="hideOrOpenNav()"> <span><i></i>
						<p>导航</p></span>
				</a>
			</div>
		</header>
		<%@include file="../inc/nav.jsp" %>
		
		<c:if test="${topImgs != null and fn:length(topImgs) != 0 }">
			<div class="imgTouchSlider">
				<div class="main_image">
					<ul>
			       		<c:forEach items="${topImgs}" var="t" varStatus="status">
							<li><a href="${ctx }/hx/${id}/photos">
								<c:if test="${t.type == 'video' }">
									<div class="playBtn"></div>
								</c:if>
								<c:if test="${t.type == 'pano' or t.type == 'ring' }">
									<div class="play360Btn"></div>
								</c:if>
								<img src='${t.imgUrl }'>
								</a></li>
						</c:forEach>
					</ul>
					<a href="javascript:void(0);" id="btn_prev" class="btn_prev"></a> <a
						href="javascript:void(0);" id="btn_next" class="btn_next"></a>
				</div>
				<div class="flicking_con">
					<div class="flicking_inner">
						<c:if test="${topImgs != null and fn:length(topImgs) > 1 }">
				       		<c:forEach items="${topImgs}" var="t" varStatus="status">
				       			<a href=""></a>
				       		</c:forEach>
						</c:if>
					</div>
				</div>
			</div>
		</c:if>
		<section class=" pd10 bdb">
			<div>
				<span class="f18 pd10">${hx.name }</span>
				<div class="pdY5">
					<span class="fc00 f14">参考价：<fmt:formatNumber value="${hx.averagePrice*hx.grossFloorArea*0.0001 }"  type="number"  maxFractionDigits="2"/>万元</span><span class="flor f14">热线电话：<span
						class="f999">${rep.tel }</span></span>
				</div>
			</div>
		</section>
		<section class=" pd10 f14 mt10 bdt bdb lh105">
			<div>
				<h2 class="f16 pdt5">户型详情</h2>

				<p>
					户型：<span class="f999">
					<c:if test="${hx.roomNum != null and hx.roomNum != '0' }">
											${hx.roomNum}室
										</c:if><c:if test="${hx.hallNum != null and hx.hallNum != '0' }">
											${hx.hallNum}厅
										</c:if><c:if test="${hx.toiletNum != null and hx.toiletNum != '0' }">
											${hx.toiletNum}卫
										</c:if><c:if test="${hx.kitchenNum != null and hx.kitchenNum != '0' }">
											${hx.kitchenNum}厨
										</c:if>
										</span>
				</p>
				<p>
					面积：<span class="f999">${hx.grossFloorArea }㎡</span>
				</p>
				<p>
					装修：<span class="f999">${rep.decorationStatus }</span>
				</p>
				<div class="l"></div>
				<p>
					楼盘：<a href="${ctx }/xf/${rep.id}/detail"><span >${rep.name }</span></a>
				</p>
				<p>
					销售：<span class="f999">${hx.saleStatus }</span>
				</p>
				<div class="l"></div>
				<h3>描述</h3>
				<p class="f999">${hx.description }</p>

			</div>
		</section>
		
		<c:if test="${rings != null and fn:length(rings) != 0 }">
		<section class="mt10 bdt bdb">
			<h3 class="pd10 f16">三维看房</h3>
			<div class="imgTouchSlider">
				<div class="main_image">
					<ul>
		       			<c:forEach items="${rings}" var="t" varStatus="status">
								<li><a href="${t.contentUrl }">
								<div class="play360Btn"></div><img
								src='${t.preImageUrl }'>
								</a></li>
		       			</c:forEach>
					</ul>
					<a href="javascript:void(0);" id="btn_prev" class="btn_prev"></a> <a
						href="javascript:void(0);" id="btn_next" class="btn_next"></a>
				</div>
				<div class="flicking_con">
					<div class="flicking_inner">
						<c:if test="${rings != null and fn:length(rings) > 1 }">
				       		<c:forEach items="${rings}" var="t" varStatus="status">
				       			<a href=""></a>
				       		</c:forEach>
						</c:if>
					</div>
				</div>
			</div>
		</section>
		</c:if>
		
		<c:if test="${panos != null and fn:length(panos) != 0 }">
		<section class="mt10 bdt bdb">
			<h3 class="pd10 f16">全景看房</h3>
			<div class="imgTouchSlider">
				<div class="main_image">
					<ul>
		       			<c:forEach items="${panos}" var="t" varStatus="status">
								<li><a href="${t.contentUrl }">
								<div class="play360Btn"></div><img
								src='${t.preImageUrl }'>
								</a></li>
		       			</c:forEach>
					</ul>
					<a href="javascript:void(0);" id="btn_prev" class="btn_prev"></a> <a
						href="javascript:void(0);" id="btn_next" class="btn_next"></a>
				</div>
				<div class="flicking_con">
					<div class="flicking_inner">
						<c:if test="${panos != null and fn:length(panos) > 1 }">
				       		<c:forEach items="${panos}" var="t" varStatus="status">
				       			<a href=""></a>
				       		</c:forEach>
						</c:if>
					</div>
				</div>
			</div>
		</section>
		</c:if>
		<%@include file="../inc/footer.jsp" %>
	</div>
</body>
<%@include file="../inc/bottom.jsp" %>
<script>
var total=2;
var allowLoad = true;
var curp = 1;
	$(function() {
		initScrollEvent();
		
		$(".imgTouchSlider").each(function(){
			initSlider(this);
		})

		$(window).resize(function() {
			fitSize();
		});
		fitSize();
	});

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
	function initSlider(element){

		var sliderObj = $(element).children(".main_image");
		var flickObj = $(element).find(".flicking_con a");
		
		var dragBln = false;
		sliderObj.touchSlider(
				{
					flexible : true,
					speed : 200,
					paging : flickObj,
					counter : function(e) {
						flickObj.removeClass("on")
								.eq(e.current - 1).addClass("on");
					}
				});
		sliderObj.bind("mousedown", function() {
			dragBln = false;
		})
		sliderObj.bind("dragstart", function() {
			dragBln = true;
		})
		sliderObj.click(function() {
			if (dragBln) {
				return false;
			}
		})
		var timer = setInterval(function() {
			sliderObj[0].animate(-1,true);
		}, 5000);
		$(element).hover(function() {
			clearInterval(timer);
		}, function() {
			timer = setInterval(function() {
				sliderObj[0].animate(-1,true);
			}, 5000);
		})
		sliderObj.bind("touchstart", function() {
			clearInterval(timer);
		}).bind("touchend", function() {
			timer = setInterval(function() {
				sliderObj[0].animate(-1,true);
			}, 5000);
		})
	}
	function fitSize() {
		var bw = $("body").width();
		if (bw > 640) {
			bw = 640;
		}
		$(".main_image").height(bw * 0.75);

		$(".imgTouchSlider").each(function(){
			var fw = $(this).find(".flicking_inner a").length*21;
			$(this).find(".flicking_inner").css("left", (bw - fw) * 0.5 + "px");
		})
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
			var str = ' <li ><a href="http://www.fang.com/news/2015-06-09/16194688.htm" id="wapdsy_D04_23"> <div class="bord"> <div class="lt"> <img data-original="http://imgs.soufun.com/viewimage/news/2015_06/09/news/1433810092437_000/120x120.jpg" alt="" src="http://www.pdfangchan.com/upload/house/2011-12-19/2011121915571257_120x90.jpg"> </div> <div class="rt"> <h3>京拟用闲置地建养老设施</h3> <p>北京拟利用国有企业闲置土地，建32个养老设施。</p> </div> </div> </a></li>';
// 			var str = '<li><a id="" href="/xf/bj/1010710185.htm"> <div class="img"> <img src="http://i3.sinaimg.cn/hs/2010/0901/S18375T1283345502659.jpg"> </div> <div class="txt"> <p class="x-intro"> <h2>一杯澜</h2> <span class="new">20000元/平</span> </p> <p>北戴河新区国际娱乐中心东3公里（原碧海蓝天度假村）</p> <div class="stag"> <span class="t1">户型：二居(<span class="t2">1</span>) 三居(<span class="t2">2</span>)</span></div></div></a></li>';
			$('#itemList').append(str);
			$('#itemList').append(str);
			$('#itemList').append(str);
			$('#itemList').append(str);
			$('#itemList').append(str);
			draginner.css('padding-left','0px');
			draginner.removeClass("loading");
			draginner.html("查看更多资讯");
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