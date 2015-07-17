<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../inc/top.jsp" %>
<!DOCTYPE html>
<html>
<head>
<c:set var="headKeywords" value="${sp.tags}"/>
<c:set var="headTitle" value="${sp.name}"/>
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
			<div class="cent">商铺详情</div>
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
							<li><a href="${ctx }/sp/${id}/photos">
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
						<c:if test="${topImgs != null and fn:length(topImgs) != 0 }">
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
				<span class="f18 pd10">${sp.name }</span><span class="flor f14">热线电话：<span
						class="f999">${sp.phoneNum }</span></span>
				<div class="pdY5">
				<span class="f14 f999 "><fmt:formatDate pattern="yyyy/MM/dd" value="${sp.updateDate }" />${dateDesc }</span>
				</div>
			</div>
		</section>
		<section class=" pd10 f14 mt10 bdt bdb lh105">
			<div class="detailMsg">
				<p>
					面积：<span class="f999">约${sp.grossFloorArea}㎡</span>
				</p>
				<p>
					装修：<span class="f999">${sp.decorationStatus }</span>
				</p>
				<p>
					区域：<span class="f999">${sp.region }</span>
				</p>
				<p>
					类别：<span class="f999">${sp.type }</span>
				</p>
				<c:if test="${sp.rentOrSale == '出租' or sp.rentOrSale == '不限'}">
					<p>
						在租：<span class="fc00">${sp.rental }元/月</span>
					</p>
				</c:if>
				<c:if test="${sp.rentOrSale == '出售' or sp.rentOrSale == '不限'}">
					<p>
						在售：<span class="fc00">${sp.totalPrice }万/元</span>
					</p>
				</c:if>
				<p>
					物业费：<span class="f999">${sp.propertyFee }元/平方米·月</span>
				</p>
				<div class="l"></div>
				<h3>地址</h3>
				<p class="f999">${sp.address }</p>
				<div class="l"></div>
				<h3>描述</h3>
				<p class="f999">${sp.introduction }</p>

			</div>
		</section>
		
		<c:if test="${panos != null and fn:length(panos) != 0 }">
		<section class="mt10 bdt bdb">
			<h3 class="pd10 f16">全景观赏</h3>
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
						<c:if test="${panos != null and fn:length(panos) != 0 }">
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