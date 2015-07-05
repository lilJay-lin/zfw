<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../inc/top.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@include file="../../inc/header.jsp" %>
</head>
<body>
	<div class="main">
		<header>
			<div class="left">
				<a href="${ctx }/user" class="back"><i></i></a>
			</div>
			<div class="cent">我的二手房</div>
			<div class="show_redrict head-icon">
				<a class="icon-nav" id="show_redrict" href="javascript:void(0);"
					onclick="hideOrOpenNav()"> <span><i></i>
						<p>导航</p></span>
				</a>
			</div>
		</header>
		<%@include file="../../inc/nav.jsp" %>
		<div class="pc-content-list whitebg">
			<a class="pc-content-item" href="${ctx }/user/esf/add">
				<div class="bor">
					<span class="flol icon esf"></span> <span class="flol">发布二手房</span>
				</div>
			</a>
		</div>
		<c:choose>
			<c:when test="${total!=0}">
				<div class="resultWarp whitebg mt10 bdt">
					<h5 id="search_result_num">
						<h5>已发布${total }个出售信息</h5>
					</h5>
				</div>
				<section class="homeList whitebg bdb">
					<ul id="itemList">
		       			<c:forEach items="${results}" var="t" varStatus="status">
						<li><a id="${t.id}" href="${ctx}/user/esf/${t.id }/manage">
								<div class="img">
									<img src="${t.preImageUrl }">
								</div>
								<div class="txt">
									<p class="x-intro">
									<h2>${t.name }</h2>
									<span class="new"><c:if test="${t.outOfDate }">已过期</c:if></span>
									</p>
									<p><c:if test="${t.roomNum != null and t.roomNum != '0' }">
											${t.roomNum}室
										</c:if><c:if test="${t.hallNum != null and t.hallNum != '0' }">
											${t.hallNum}厅
										</c:if><c:if test="${t.toiletNum != null and t.toiletNum != '0' }">
											${t.toiletNum}卫
										</c:if><c:if test="${t.grossFloorArea != null and t.grossFloorArea != '0' }">
											约${t.grossFloorArea}㎡
										</c:if></p>
		
									<div class="stag">
										<span class="t1">
											${t.residenceCommunityName }
										</span>
									</div>
								</div>
						</a></li>
		       			</c:forEach>
					</ul>
				</section>
				<div id="drag" class="cenBtn">
					<a class="draginner" href="javascript:void(0);">查看更多</a>
				</div>
			</c:when>
			<c:otherwise>
				<div class="searchNo">
			    	<p class="f14">还没发布房源信息。</p>
				</div>
			</c:otherwise>
		</c:choose>
		<%@include file="../../inc/footer.jsp" %>
		<%@include file="../../inc/goHead.jsp" %>
	</div>
	<input type="hidden" id="total" value="${total }">
	<input type="hidden" id="residenceCommunityId" value="${residenceCommunityId }">
</body>
<%@include file="../../inc/bottom.jsp" %>
<script>
var total=Math.ceil($("#total").val()/5);
var allowLoad = true;
var curp = 1;

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
		var url = "${ctx}/user/esf/json/{targetPage}-{pageSize}/search";
		url = url.replace("{targetPage}",curp+1);
		url = url.replace("{pageSize}",5);
		$.ajax({
			url : url,
			dataType:'json',
			success: function(json){
				if(json.success){
					for(var i=0;i<json.results.length;i++){
						var result = json.results[i];
						var str = '<li><a id="{id}" href="${ctx}/user/esf/{id}/manage"> <div class="img"> <img src="{preImageUrl}"> </div> <div class="txt"> <p class="x-intro"> <h2>{name}</h2> <span class="new">{outOfDate}</span> </p> <p>{numMsgStr}</p> <div class="stag"> <span class="t1">{residenceCommunityName} </div> </div> </a></li>';
						var numMsgStr = "";
						if(result.roomNum && result.roomNum>0){
							numMsgStr+=result.roomNum+'室';
						}
						if(result.hallNum && result.hallNum>0){
							numMsgStr+=' '+result.hallNum+'厅';
						}
						if(result.toiletNum && result.toiletNum>0){
							numMsgStr+=' '+result.toiletNum+'卫';
						}
						if(result.grossFloorArea && result.grossFloorArea>0){
							numMsgStr+=' 约'+parseFloat(result.grossFloorArea).toFixed(2); +'㎡';
						}
						var outOfDate = "";
						if(result.outOfDate){
							outOfDate = "已过期";
						}
						str = str.replace(/{id}/g,result.id);
						str = str.replace(/{preImageUrl}/g,result.preImageUrl);
						str = str.replace(/{name}/g,result.name);
						str = str.replace(/{outOfDate}/g,outOfDate);
						str = str.replace(/{numMsgStr}/g,numMsgStr);
						str = str.replace(/{residenceCommunityName}/g,result.residenceCommunityName);
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
				draginner.html("查看更多");
				
				allowLoad = true;
				if(curp+1>=total){
					$('#drag').hide();
					allowLoad = false;
				}
			}
		}); 
	}
</script>
</html>