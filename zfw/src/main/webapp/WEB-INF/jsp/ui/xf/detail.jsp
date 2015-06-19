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
			<div class="cent">楼盘详情</div>
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
							<li><a href="${ctx }/xf/${id}/photos">
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
				<span class="f18 pd10">${rep.name }</span>
				<div class="pdY5">
					<span class="fc00 f14">均价：21314元/平方米</span><span class="flor f14">热线电话：<span
						class="f999">${rep.tel }</span></span>
				</div>
				<p class="f14">
					地址：<span class="f999">${rep.address }</span>
				</p>
			</div>
		</section>
		<section class=" pd10 f14 mt10 bdt bdb lh105">
			<div>
				<h2 class="f16 pdt5">楼盘详情</h2>

				<p>
					开盘时间：<span class="f999"><fmt:formatDate pattern="yyyy年MM月dd日" value="${rep.onSaleDate }" /></span>
				</p>
				<p>
					入住时间：<span class="f999"><fmt:formatDate pattern="yyyy年MM月dd日" value="${rep.onReadyDate }" /></span>
				</p>
				<div class="l"></div>
				<p>
					物业类型：<span class="f999">${rep.propertyType }</span>
				</p>
				<p>
					建筑类别：<span class="f999">${rep.buildingType }</span>
				</p>
				<p>
					装修状况：<span class="f999">${rep.decorationStatus }</span>
				</p>
				<p>
					住<span style="margin-right: .5em;"></span>户<span
						style="margin-right: .5em;"></span>数：<span class="f999">${rep.householdNum }户</span>
				</p>
				<p>
					容<span style="margin-right: .5em;"></span>积<span
						style="margin-right: .5em;"></span>率：<span class="f999">${rep.floorAreaRatio }</span>
				</p>
				<p>
					绿<span style="margin-right: .5em;"></span>化<span
						style="margin-right: .5em;"></span>率：<span class="f999"><fmt:formatNumber type="percent" value="${rep.greenRate}" /></span>
				</p>
				<p>
					停<span style="margin-right: .5em;"></span>车<span
						style="margin-right: .5em;"></span>位：<span class="f999">共${rep.parkingSpaceNum }个</span>
				</p>
				<p>
					产权年限：<span class="f999">${rep.propertyYears }年</span>
				</p>
				<div class="l"></div>
				<p>
					开<span style="margin-right: .5em;"></span>发<span
						style="margin-right: .5em;"></span>商： <span class="f999">${rep.developer }</span>
				</p>
				<p>
					预售许可证：<span class="f999">${rep.preSalePermit }</span>
				</p>
				<p>
					物业公司： <span class="f999">${rep.propertyCompany }</span>
				</p>
				<p>
					物<span style="margin-right: .5em;"></span>业<span
						style="margin-right: .5em;"></span>费： <span class="f999">${rep.propertyFee }元/平方米·月</span>
				</p>
				<div class="l"></div>
				<h3>楼盘介绍</h3>
				<p class="f999">${rep.introduction }</p>

				<div class="l"></div>
				<h3>周边配套</h3>
				<p class="f999">${rep.surrounding }</p>

				<div class="l"></div>
				<h3>交通配套</h3>
				<p class="f999">${rep.traffic }</p>
			</div>
		</section>
		
		<c:if test="${hxs != null and fn:length(hxs) > 1 }">
		<section class="mt10 homeList bdb">
			<ul>
				<c:if test="${oneRoomHTs != null and fn:length(oneRoomHTs) > 1 }">
				<h3 class="f999 f16 pdY5">一居</h3>
		       		<c:forEach items="${oneRoomHTs}" var="t" varStatus="status">
						<li><a href="${ctx }/hx/${t.id}/detail">
								<div class="img">
									<img src="${t.preImageUrl }">
								</div>
								<div class="txt">
									<p class="x-intro"></p>
									<h2>${t.name }</h2>
									<span class="new">${t.saleStatus }</span>
									<p></p>
									<p><c:if test="${t.roomNum != null and t.roomNum != '0' }">
											${t.roomNum}室
										</c:if><c:if test="${t.hallNum != null and t.hallNum != '0' }">
											${t.hallNum}厅
										</c:if><c:if test="${t.toiletNum != null and t.toiletNum != '0' }">
											${t.toiletNum}卫
										</c:if><c:if test="${t.kitchenNum != null and t.kitchenNum != '0' }">
											${t.kitchenNum}厨
										</c:if><c:if test="${t.grossFloorArea != null and t.grossFloorArea != '0' }">
											约${t.grossFloorArea}㎡
										</c:if></p>
								</div>
						</a></li>
		       		</c:forEach>
				</c:if>
				<c:if test="${twoRoomHTs != null and fn:length(twoRoomHTs) > 1 }">
				<h3 class="f999 f16 pdY5">二居</h3>
		       		<c:forEach items="${twoRoomHTs}" var="t" varStatus="status">
						<li><a href="${ctx }/hx/${t.id}/detail">
								<div class="img">
									<img src="${t.preImageUrl }">
								</div>
								<div class="txt">
									<p class="x-intro"></p>
									<h2>${t.name }</h2>
									<span class="new">${t.saleStatus }</span>
									<p></p>
									<p><c:if test="${t.roomNum != null and t.roomNum != '0' }">
											${t.roomNum}室
										</c:if><c:if test="${t.hallNum != null and t.hallNum != '0' }">
											${t.hallNum}厅
										</c:if><c:if test="${t.toiletNum != null and t.toiletNum != '0' }">
											${t.toiletNum}卫
										</c:if><c:if test="${t.kitchenNum != null and t.kitchenNum != '0' }">
											${t.kitchenNum}厨
										</c:if><c:if test="${t.grossFloorArea != null and t.grossFloorArea != '0' }">
											约${t.grossFloorArea}㎡
										</c:if></p>
								</div>
						</a></li>
		       		</c:forEach>
				</c:if>
				<c:if test="${threeRoomHTs != null and fn:length(threeRoomHTs) > 1 }">
				<h3 class="f999 f16 pdY5">三居</h3>
		       		<c:forEach items="${threeRoomHTs}" var="t" varStatus="status">
						<li><a href="${ctx }/hx/${t.id}/detail">
								<div class="img">
									<img src="${t.preImageUrl }">
								</div>
								<div class="txt">
									<p class="x-intro"></p>
									<h2>${t.name }</h2>
									<span class="new">${t.saleStatus }</span>
									<p></p>
									<p><c:if test="${t.roomNum != null and t.roomNum != '0' }">
											${t.roomNum}室
										</c:if><c:if test="${t.hallNum != null and t.hallNum != '0' }">
											${t.hallNum}厅
										</c:if><c:if test="${t.toiletNum != null and t.toiletNum != '0' }">
											${t.toiletNum}卫
										</c:if><c:if test="${t.kitchenNum != null and t.kitchenNum != '0' }">
											${t.kitchenNum}厨
										</c:if><c:if test="${t.grossFloorArea != null and t.grossFloorArea != '0' }">
											约${t.grossFloorArea}㎡
										</c:if></p>
								</div>
						</a></li>
		       		</c:forEach>
				</c:if>
				<c:if test="${fourRoomHTs != null and fn:length(fourRoomHTs) > 1 }">
				<h3 class="f999 f16 pdY5">四居</h3>
		       		<c:forEach items="${fourRoomHTs}" var="t" varStatus="status">
						<li><a href="${ctx }/hx/${t.id}/detail">
								<div class="img">
									<img src="${t.preImageUrl }">
								</div>
								<div class="txt">
									<p class="x-intro"></p>
									<h2>${t.name }</h2>
									<span class="new">${t.saleStatus }</span>
									<p></p>
									<p><c:if test="${t.roomNum != null and t.roomNum != '0' }">
											${t.roomNum}室
										</c:if><c:if test="${t.hallNum != null and t.hallNum != '0' }">
											${t.hallNum}厅
										</c:if><c:if test="${t.toiletNum != null and t.toiletNum != '0' }">
											${t.toiletNum}卫
										</c:if><c:if test="${t.kitchenNum != null and t.kitchenNum != '0' }">
											${t.kitchenNum}厨
										</c:if><c:if test="${t.grossFloorArea != null and t.grossFloorArea != '0' }">
											约${t.grossFloorArea}㎡
										</c:if></p>
								</div>
						</a></li>
		       		</c:forEach>
				</c:if>
				<c:if test="${fiveRoomHTs != null and fn:length(fiveRoomHTs) > 1 }">
				<h3 class="f999 f16 pdY5">五居</h3>
		       		<c:forEach items="${fiveRoomHTs}" var="t" varStatus="status">
						<li><a href="${ctx }/hx/${t.id}/detail">
								<div class="img">
									<img src="${t.preImageUrl }">
								</div>
								<div class="txt">
									<p class="x-intro"></p>
									<h2>${t.name }</h2>
									<span class="new">${t.saleStatus }</span>
									<p></p>
									<p><c:if test="${t.roomNum != null and t.roomNum != '0' }">
											${t.roomNum}室
										</c:if><c:if test="${t.hallNum != null and t.hallNum != '0' }">
											${t.hallNum}厅
										</c:if><c:if test="${t.toiletNum != null and t.toiletNum != '0' }">
											${t.toiletNum}卫
										</c:if><c:if test="${t.kitchenNum != null and t.kitchenNum != '0' }">
											${t.kitchenNum}厨
										</c:if><c:if test="${t.grossFloorArea != null and t.grossFloorArea != '0' }">
											约${t.grossFloorArea}㎡
										</c:if></p>
								</div>
						</a></li>
		       		</c:forEach>
				</c:if>
				<c:if test="${overFiveRoomHTs != null and fn:length(overFiveRoomHTs) > 1 }">
				<h3 class="f999 f16 pdY5">五居以上</h3>
		       		<c:forEach items="${overFiveRoomHTs}" var="t" varStatus="status">
						<li><a href="${ctx }/hx/${t.id}/detail">
								<div class="img">
									<img src="${t.preImageUrl }">
								</div>
								<div class="txt">
									<p class="x-intro"></p>
									<h2>${t.name }</h2>
									<span class="new">${t.saleStatus }</span>
									<p></p>
									<p><c:if test="${t.roomNum != null and t.roomNum != '0' }">
											${t.roomNum}室
										</c:if><c:if test="${t.hallNum != null and t.hallNum != '0' }">
											${t.hallNum}厅
										</c:if><c:if test="${t.toiletNum != null and t.toiletNum != '0' }">
											${t.toiletNum}卫
										</c:if><c:if test="${t.kitchenNum != null and t.kitchenNum != '0' }">
											${t.kitchenNum}厨
										</c:if><c:if test="${t.grossFloorArea != null and t.grossFloorArea != '0' }">
											约${t.grossFloorArea}㎡
										</c:if></p>
								</div>
						</a></li>
		       		</c:forEach>
				</c:if>
			</ul>
		</section>
		</c:if>
		<section class=" pd10 mt10 bdt bdb f14">
			<h2 class="f16">位置</h2>
			<p class="pdY5">
				<a id="wapxfxqy_B02_27"
					href="/map/?c=map&amp;a=xfMapbyId&amp;city=gz&amp;xqid=2811346746"
					class="f000">地址：<span class="f999"> ${rep.address }
				</span></a>
			</p>
			<div id="wapxfxqy_B02_27" style="position: relative;">
				<a href="${ctx }/xf/map/${rep.id}">
					<img
					src="http://api.map.baidu.com/staticimage?width=300&height=130&zoom=14&markers=${rep.longitude },${rep.latitude }&markerStyles=s,"
					width="100%" alt=""> <span class="maptxt">${rep.name }</span>
				</a>
			</div>
		</section>
		
		<c:if test="${videos != null and fn:length(videos) != 0 }">
		<section class="mt10 bdt bdb">
			<h3 class="pd10 f16">视频看房</h3>
			<div class="imgTouchSlider">
				<div class="main_image">
					<ul>
		       			<c:forEach items="${videos}" var="t" varStatus="status">
								<li><a href="${t.contentUrl }">
								<div class="playBtn"></div><img
								src='${t.preImageUrl }'>
								</a></li>
		       			</c:forEach>
					</ul>
					<a href="javascript:void(0);" id="btn_prev" class="btn_prev"></a> <a
						href="javascript:void(0);" id="btn_next" class="btn_next"></a>
				</div>
				<div class="flicking_con">
					<div class="flicking_inner">
						<c:if test="${videos != null and fn:length(videos) > 1 }">
				       		<c:forEach items="${videos}" var="t" varStatus="status">
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
		
		<c:if test="${infoList != null and fn:length(infoList) != 0 }">
			<section class="NewsList mt10">
				<div class="zd-tip" style="display: none;">
					<span></span>
				</div>
				<div class="homeTitle clearfix">
					<h1>相关资讯</h1>
				</div>
				<ul id="itemList">
	       			<c:forEach items="${infoList}" var="t" varStatus="status">
						<li><a href="${ctx }/info/${t.id}/detail" >
								<div class="bord">
									<div class="lt">
										<img src="${t.preImageUrl }">
									</div>
									<div class="rt">
										<h3>${t.name }</h3>
										<p>${t.summary }</p>
									</div>
								</div>
						</a></li>
	       			</c:forEach>
				</ul>
			</section>
			<div id="drag" class="cenBtn">
				<a class="draginner" href="javascript:void(0);">查看更多资讯</a>
			</div>
		</c:if>
		<input type="hidden" id="totalInfo" value="${totalInfo }">
		<input type="hidden" id="realEstateProjectId" value="${rep.id }">
		<%@include file="../inc/footer.jsp" %>
		<%@include file="../inc/goHead.jsp" %>
	</div>
</body>
<%@include file="../inc/bottom.jsp" %>
<script>
var total=Math.ceil($("#totalInfo").val()/5);
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
		if(total<=1 || curp+1>=total){
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
		if(curp+1>=total){
			allowLoad = false;
			$('#drag').hide();
			return;
		}
		allowLoad = false;
		draginner.css('padding-left','10px');
		draginner.addClass("loading");
		draginner.html("正在加载...");
		
		var url = "${ctx}/info/json/{realEstateProjectId}/{targetPage}-{pageSize}/search";
		url = url.replace("{realEstateProjectId}",$("#realEstateProjectId").val());
		url = url.replace("{targetPage}",curp+1);
		url = url.replace("{pageSize}",5);
		$.ajax({
			url : url,
			dataType:'json',
			success: function(json){
				if(json.success){
					for(var i=0;i<json.results.length;i++){
						var result = json.results[i];
						var str = ' <li ><a href="${ctx}/info/{id}/detail" > <div class="bord"> <div class="lt"> <img alt="" src="{preImageUrl}"> </div> <div class="rt"> <h3>{name}</h3> <p>{summary}</p> </div> </div> </a></li>';
						str = str.replace(/{id}/g,result.id);
						str = str.replace(/{preImageUrl}/g,result.preImageUrl);
						str = str.replace(/{name}/g,result.name);
						str = str.replace(/{summary}/g,result.summary);
						$('#itemList').append(str);
					}
				}else if(json.msg){
					alert(json.msg);
				}
				curp=curp+1;
			},
			error:function(e){
				alert("搜索失败！");
			},
			complete:function(e){
				draginner.css('padding-left','0px');
				draginner.removeClass("loading");
				draginner.html("查看更多资讯");
				
				allowLoad = true;
				if(curp+1>=total){
					$('#drag').hide();
					allowLoad = false;
				}
			}
		});
		
// 		setTimeout(function(){
// 			var str = ' <li ><a href="http://www.fang.com/news/2015-06-09/16194688.htm" id="wapdsy_D04_23"> <div class="bord"> <div class="lt"> <img data-original="http://imgs.soufun.com/viewimage/news/2015_06/09/news/1433810092437_000/120x120.jpg" alt="" src="http://www.pdfangchan.com/upload/house/2011-12-19/2011121915571257_120x90.jpg"> </div> <div class="rt"> <h3>京拟用闲置地建养老设施</h3> <p>北京拟利用国有企业闲置土地，建32个养老设施。</p> </div> </div> </a></li>';
// // 			var str = '<li><a id="" href="${ctx }/hx/${t.id}/detail"> <div class="img"> <img src="http://i3.sinaimg.cn/hs/2010/0901/S18375T1283345502659.jpg"> </div> <div class="txt"> <p class="x-intro"> <h2>一杯澜</h2> <span class="new">20000元/平</span> </p> <p>北戴河新区国际娱乐中心东3公里（原碧海蓝天度假村）</p> <div class="stag"> <span class="t1">户型：二居(<span class="t2">1</span>) 三居(<span class="t2">2</span>)</span></div></div></a></li>';
// 			$('#itemList').append(str);
// 			$('#itemList').append(str);
// 			$('#itemList').append(str);
// 			$('#itemList').append(str);
// 			$('#itemList').append(str);
// 			draginner.css('padding-left','0px');
// 			draginner.removeClass("loading");
// 			draginner.html("查看更多资讯");
// 			curp=curp+1;
// 			allowLoad = true;
// 			if(curp>total){
// 				$('#drag').hide();
// 				allowLoad = false;
// 			}
// 		},1000);
	}

</script>
</html>