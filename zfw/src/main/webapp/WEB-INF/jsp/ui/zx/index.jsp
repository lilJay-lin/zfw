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
			<div class="cent">资讯</div>
			<div class="show_redrict head-icon">
				<a class="icon-nav" id="show_redrict" href="javascript:void(0);"
					onclick="hideOrOpenNav()"> <span><i></i>
						<p>导航</p></span>
				</a>
			</div>
		</header>
		<%@include file="../inc/nav.jsp" %>
		
		<c:if test="${ads != null and fn:length(ads) != 0 }">
			<div class="imgTouchSlider">
				<div class="main_image">
					<ul>
		       			<c:forEach items="${ads}" var="t" varStatus="status">
								<li><a href="${t.contentUrl }"><img src='${t.preImageUrl }'>
								</a></li>
		       			</c:forEach>
					</ul>
					<a href="javascript:void(0);" id="btn_prev" class="btn_prev"></a> <a
						href="javascript:void(0);" id="btn_next" class="btn_next"></a>
				</div>
				<div class="flicking_con">
					<div class="flicking_inner">
						<c:if test="${ads != null and fn:length(ads) != 0 }">
				       		<c:forEach items="${ads}" var="t" varStatus="status">
				       			<a href=""></a>
				       		</c:forEach>
						</c:if>
					</div>
				</div>
			</div>
		</c:if>
		<div class="mLogin">
			<div class="mTab flexbox whitebg">
				<a href="javascript:void(0)" class="active" id="changeTypeBtnFC" onclick="setInfoType('房产',this)">房产</a> <a href="javascript:void(0)" id="changeTypeBtnZH" onclick="setInfoType('综合',this)">综合</a>
			</div>
		</div>
		<section class="NewsList">
			<ul id="itemList">
       			<c:forEach items="${results}" var="t" varStatus="status">
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

		<c:choose>
			<c:when test="${total!=0}">
				<div id="drag" class="cenBtn">
					<a class="draginner" href="javascript:void(0);">查看更多资讯</a>
				</div>
			</c:when>
			<c:otherwise>
				<div class="searchNo">
			    	<p class="f14">暂未搜索到符合条件的资讯。</p>
				</div>
			</c:otherwise>
		</c:choose>
		<%@include file="../inc/footer.jsp" %>
		<%@include file="../inc/goHead.jsp" %>
	</div>
	<input type="hidden" id="infoType" value="${type }">
	<input type="hidden" id="total" value="${total }">
</body>
<%@include file="../inc/bottom.jsp" %>
<script>
var total=Math.ceil($("#total").val()/5);
var allowLoad = true;
var curp = 1;

	$(function() {
		if($("#infoType").val()=="综合"){
			$("#changeTypeBtnFC").removeClass("active");
			$("#changeTypeBtnZH").addClass("active");
		}else{
			$("#infoType").val("房产");
		}
		
		$(".imgTouchSlider").each(function(){
			initSlider(this);
		})
		$(window).resize(function() {
			fitSize();
		});
		fitSize();
		initScrollEvent();
	})
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
		$(".main_image").height(bw /3);

		$(".imgTouchSlider").each(function(){
			var fw = $(this).find(".flicking_inner a").length*21;
			$(this).find(".flicking_inner").css("left", (bw - fw) * 0.5 + "px");
		})
	}
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
		
		var url = "${ctx}/info/json/{type}-{targetPage}-{pageSize}/search";
		url = url.replace("{type}",$("#infoType").val());
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
		
	}
	
	function setInfoType(type,element){
		top.location = "${ctx}/info/"+type+"/type";
	}


</script>
</html>