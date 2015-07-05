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
			<div class="cent">二手房详情</div>
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
							<li><a href="${ctx }/esf/${id}/photos">
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
				<span class="f18 pd10">${esf.name }</span><span class="flor f14">热线电话：<span
						class="f999">${esf.phoneNum }</span></span>
				<div class="pdY5">
				<span class="f14 f999 "><fmt:formatDate pattern="yyyy/MM/dd" value="${esf.updateDate }" />${dateDesc }</span>
					<span class="fc00 f14 flor">参考价：${esf.totalPrice }万元</span>
				</div>
			</div>
		</section>
		<section class=" pd10 f14 mt10 bdt bdb lh105">
			<div class="detailMsg">

				<p>
					户型：<span class="f999"><c:if test="${esf.roomNum != null and esf.roomNum != '0' }">
											${esf.roomNum}室
										</c:if><c:if test="${esf.hallNum != null and esf.hallNum != '0' }">
											${esf.hallNum}厅
										</c:if><c:if test="${esf.toiletNum != null and esf.toiletNum != '0' }">
											${esf.toiletNum}卫
										</c:if></span>
				</p>
				<p>
					小区：
					<c:if test="${rc != null}">
					<a href="${ctx }/xq/${rc.id}/detail">
					</c:if>
					<span >${esf.residenceCommunityName }</span>
					<c:if test="${rc != null}">
					</a>
					</c:if>
				</p>
				<p>
					面积：<span class="f999">约${esf.grossFloorArea}㎡</span>
				</p>
				<p>
					装修：<span class="f999">${esf.decorationStatus }</span>
				</p>
				<p>
					朝向：<span class="f999">${esf.forward }</span>
				</p>
				<p>
					楼层：<span class="f999">${esf.curFloor }/${esf.totalFloor }层</span>
				</p>
				<div class="l"></div>
				<h3>描述</h3>
				<p class="f999">${esf.introduction }</p>

			</div>
		</section>
		
		<c:if test="${rc != null}">
		<section class=" pd10 mt10 bdt bdb f14">
			<h2 class="f16">位置</h2>
			<p class="pdY5">
				<a href="${ctx }/xq/esf/${rc.id}/map"
					class="f000"><span class="f999"> ${rc.address }
				</span></a>
			</p>
			
			<c:if test="${rc.longitude != null && rc.latitude !=null}">
			<div style="position: relative;">
				<a href="${ctx }/xq/esf/${rc.id}/map">
					<img
					src="http://api.map.baidu.com/staticimage?width=300&height=130&zoom=14&markers=${rc.longitude },${rc.latitude }&markerStyles=s,"
					width="100%" alt=""> <span class="maptxt">${rc.name }</span>
				</a>
			</div>
			</c:if>
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