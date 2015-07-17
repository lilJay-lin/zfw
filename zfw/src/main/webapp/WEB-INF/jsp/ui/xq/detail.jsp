<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../inc/top.jsp" %>
<!DOCTYPE html>
<html>
<head>
<c:set var="headKeywords" value="${rc.tags}"/>
<c:set var="headTitle" value="${rc.name}"/>
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
			<div class="cent">小区详情</div>
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
							<li><a href="${ctx }/xq/${id}/photos">
								<img src='${t.contentUrl }'>
								</a></li>
						</c:forEach>
					</ul>
					<a href="javascript:void(0);" id="btn_prev" class="btn_prev"></a> <a
						href="javascript:void(0);" id="btn_next" class="btn_next"></a>
				</div>
				<div class="flicking_con">
					<div class="flicking_inner">
						<c:if test="${topImgs != null and fn:length(topImgs) != 0 }">
				       		<c:forEach items="${topImgs}" var="t" varStatus="status">
				       			<a href=""></a>
				       		</c:forEach>
						</c:if>
					</div>
				</div>
			</div>
		</c:if>
		<section class="xqbox"><div class="f18 xqtit"> ${rc.name }</div>
		<div class="xqtit">
		<a href="${ctx}/esf/${rc.id }------/search">
		<span class="arr-rt flor f12">约${rc.shhNum }套</span> 售<span style="margin:0 1em;">
		</span>价：<span class="fc00">${rc.shhAveragePrice }元/平</span></a></div><div class="xqtit">
		<a href="${ctx}/zf/${rc.id }------/search">
		<span class="arr-rt flor f12">约${rc.rhNum }套</span> 租<span style="margin:0 1em;">
		</span>金：<span class="fc00">${rc.rhAveragePrice }元/月</span></a></div></section>
		<section class=" pd10 f14 mt10 bdt bdb lh105">
			<div class="detailMsg">
				<p>
					建筑年代：<span class="f999"><fmt:formatDate pattern="yyyy年" value="${rc.onSaleDate }" /></span>
				</p>
				<p>
					物业类型：<span class="f999">${rc.propertyType }</span>
				</p>
				<p>
					建筑类别：<span class="f999">${rc.buildingType }</span>
				</p>
				<p>
					住<span style="margin-right: .5em;"></span>户<span
						style="margin-right: .5em;"></span>数：<span class="f999">${rc.householdNum }户</span>
				</p>
				<p>
					容<span style="margin-right: .5em;"></span>积<span
						style="margin-right: .5em;"></span>率：<span class="f999">${rc.floorAreaRatio }</span>
				</p>
				<p>
					绿<span style="margin-right: .5em;"></span>化<span
						style="margin-right: .5em;"></span>率：<span class="f999"><fmt:formatNumber type="percent" value="${rc.greenRate}" /></span>
				</p>
				<p>
					停<span style="margin-right: .5em;"></span>车<span
						style="margin-right: .5em;"></span>位：<span class="f999">共${rc.parkingSpaceNum }个</span>
				</p>
				<p>
					产权年限：<span class="f999">${rc.propertyYears }年</span>
				</p>
				<p>
					物<span style="margin-right: .5em;"></span>业<span
						style="margin-right: .5em;"></span>费： <span class="f999">${rc.propertyFee }元/平方米·月</span>
				</p>
				<div class="l"></div>
				<h3>楼盘介绍</h3>
				<p class="f999">${rc.introduction }</p>

				<div class="l"></div>
				<h3>周边配套</h3>
				<p class="f999">${rc.surrounding }</p>

				<div class="l"></div>
				<h3>交通配套</h3>
				<p class="f999">${rc.traffic }</p>
			</div>
		</section>
		
		<section class=" pd10 mt10 bdt bdb f14">
			<h2 class="f16">位置</h2>
			<p class="pdY5">
				<a href="${ctx }/xq/esf/${rc.id}/map"
					class="f000"><span class="f999"> ${rc.address }
				</span></a>
			</p>
			<div style="position: relative;">
				<a href="${ctx }/xq/esf/${rc.id}/map">
					<img
					src="http://api.map.baidu.com/staticimage?width=300&height=130&zoom=14&markers=${rc.longitude },${rc.latitude }&markerStyles=s,"
					width="100%" alt=""> <span class="maptxt">${rc.name }</span>
				</a>
			</div>
		</section>
		
		<%@include file="../inc/footer.jsp" %>
		<%@include file="../inc/goHead.jsp" %>
	</div>
</body>
<%@include file="../inc/bottom.jsp" %>
<script>
	$(function() {
		
		$(".imgTouchSlider").each(function(){
			initSlider(this);
		})

		$(window).resize(function() {
			fitSize();
		});
		fitSize();
	});

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

</script>
</html>