<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../inc/top.jsp" %>
<!DOCTYPE html>
<html>
<head>
<c:set var="headKeywords" value="租房"/>
<c:set var="headTitle" value="租房"/>
<%@include file="../inc/header.jsp" %>
</head>
<body>
	<div class="main">
		<header>
			<div class="left">
				<a href="${ctx }/index" class="hLogo"></a>
			</div>
			<div class="cent">租房</div>
			<div class="show_redrict head-icon">
				<a class="icon-nav" id="show_redrict" href="javascript:void(0);"
					onclick="hideOrOpenNav()"> <span><i></i>
						<p>导航</p></span>
				</a>
			</div>
		</header>
		<%@include file="../inc/nav.jsp" %>
		<form class="search0620_new flexbox js-removed-in-residenceCommunityId" name="wapSearchForm" action=""
			onsubmit="return false;" method="get" autocomplete="off">
			<div class="searbox_new">
				<div class="ipt">
					<input type="search" name="keyword" id="keyWord" value="${keyWord }"
						placeholder="小区名/地名" autocomplete="off">
						<a href="javascript:;" class="off" style="display: none;"></a>
				</div>
				<a href="javascript:;" id="searchBtn" onclick="searchAction()" class="btn" rel="nofollow"><i></i></a>
			</div>
			<a href="${ctx }/xq/zf/map"
				class="mapbtn">地图</a>
		</form>

		<section class="whitebg bdt bdb">
			<div class="searchCriteria">
				<ul id="criteriaList" class="criteriaList">
					<li class="spread  js-removed-in-residenceCommunityId">
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
							<span class="js-selected"  data-name="rental" data-condition=""> 不限 </span>
							<span class="js-sift-condition none" data-condition=""> 不限</span>
							<span class="js-sift-condition" data-condition="1:499"> 500以下</span>
							<span class="js-sift-condition" data-condition="500:999"> 500-999</span>
							<span class="js-sift-condition" data-condition="1000:1999"> 1000-1999</span>
							<span class="js-sift-condition" data-condition="2000:2999"> 2000-2999</span>
							<span class="js-sift-condition" data-condition="3000:4999"> 3000-4999</span>
							<span class="js-sift-condition" data-condition="5000:1000000"> 5000以上</span>
						</p> <strong>租金</strong> <em class="arrowDown"></em>
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
					<li class="notImportant none">
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
				</ul>

				<div id="listMore" class="down" onclick="spreadList()">
					<span class="icon"></span> <span class="txt">展开更多查询条件</span>
				</div>
			</div>
		</section>
		<div class="resultWarp whitebg mt10 bdt">
			<h5 id="search_result_num">
				<h5>共搜索到${total }个出租房源</h5>
			</h5>

			<span> <select id="search_condition_order">
					<option value="default">默认排序</option>
					<option value="priceFromHigh">价格由高到低</option>
					<option value="priceFromLow">价格由低到高</option>
					<option value="onUpdateFromNear">更新时间由近到远</option>
					<option value="onUpdateFromFar">更新时间由远到近</option>
			</select>
			</span>
		</div>
		<section class="homeList whitebg bdb">
			<ul id="itemList">
       			<c:forEach items="${results}" var="t" varStatus="status">
				<li><a id="${t.id}" href="${ctx}/zf/${t.id }/detail">
						<div class="img">
							<img src="${t.preImageUrl }">
						</div>
						<div class="txt">
							<p class="x-intro">
							<h2>${t.name }</h2>
							<span class="new">${t.rental }元/月</span>
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
		<c:choose>
			<c:when test="${total!=0}">
				<div id="drag" class="cenBtn">
					<a class="draginner" href="javascript:void(0);">查看更多出租房源</a>
				</div>
			</c:when>
			<c:otherwise>
				<div class="searchNo">
			    	<p class="f14">暂未搜索到符合条件的出租房源。</p>
				</div>
			</c:otherwise>
		</c:choose>
		<%@include file="../inc/footer.jsp" %>
		<%@include file="../inc/goHead.jsp" %>
	</div>
	<input type="hidden" id="total" value="${total }">
	<input type="hidden" id="residenceCommunityId" value="${residenceCommunityId }">
</body>
<%@include file="../inc/bottom.jsp" %>
<script>
var total=Math.ceil($("#total").val()/5);
var allowLoad = true;
var curp = 1;

	$(function() {
		initEvent();
		if("${region}"){
			setItemValue("region","${region}");
		}
		if("${rental}"){
			setItemValue("rental","${rental}");
		}
		if("${roomNum}"){
			setItemValue("roomNum","${roomNum}");
		}
		if("${grossFloorArea}"){
			setItemValue("grossFloorArea","${grossFloorArea}");
		}
		if("${orderBy}"){
			$("#search_condition_order").val("${orderBy}");
		}
		if("${residenceCommunityId}"){
			$(".js-removed-in-residenceCommunityId").hide();
		}
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
		$("#search_condition_order").change(function(){
			searchAction();
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
		element.siblings(".js-selected").attr("data-condition",element.attr("data-condition"));
		searchAction();
	}
	function setItemValue(dataName,dataCondition){
		if(dataCondition){
			var element = $("[data-name='"+dataName+"']");
			element.siblings().each(function(){
				if($(this).attr("data-condition")==dataCondition){
					$(this).addClass("none");
					element.html($(this).html());
				}else{
					$(this).removeClass("none");
				}
			});
			element.attr("data-condition",dataCondition);
		}
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
		var url = "${ctx}/zf/json/{residenceCommunityId}-{keyWord}-{region}-{rental}-{roomNum}-{grossFloorArea}-{orderBy}-{targetPage}-{pageSize}/search";
		url = bindSearchData(url);
		url = url.replace("{targetPage}",curp+1);
		url = url.replace("{pageSize}",5);
		$.ajax({
			url : url,
			dataType:'json',
			success: function(json){
				if(json.success){
					for(var i=0;i<json.results.length;i++){
						var result = json.results[i];
						var str = '<li><a id="{id}" href="${ctx}/zf/{id}/detail"> <div class="img"> <img src="{preImageUrl}"> </div> <div class="txt"> <p class="x-intro"> <h2>{name}</h2> <span class="new">{rental}元/月</span> </p> <p>{numMsgStr}</p> <div class="stag"> <span class="t1">{residenceCommunityName} </div> </div> </a></li>';
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
						str = str.replace(/{id}/g,result.id);
						str = str.replace(/{preImageUrl}/g,result.preImageUrl);
						str = str.replace(/{name}/g,result.name);
						str = str.replace(/{rental}/g,result.rental);
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
				draginner.html("查看更多出租房源");
				
				allowLoad = true;
				if(curp+1>=total){
					$('#drag').hide();
					allowLoad = false;
				}
			}
		}); 
	}
	function searchAction(){
		var url = "${ctx}/zf/{residenceCommunityId}-{keyWord}-{region}-{rental}-{roomNum}-{grossFloorArea}-{orderBy}/search";
		url = bindSearchData(url);
		top.location.href = url;
	}
	function bindSearchData(url){
		
		var residenceCommunityId = "";
		if($("#residenceCommunityId").val()){
			residenceCommunityId = $("#residenceCommunityId").val();
		}
		var keyWord = $("#keyWord").val();
		keyWord = keyWord.replace(/([^A-Za-z0-9\u4e00-\u9fa5\(\)_])+/g,"");
		var region = $("[data-name='region']").attr("data-condition");
		var rental = $("[data-name='rental']").attr("data-condition");
		var roomNum = $("[data-name='roomNum']").attr("data-condition");
		var grossFloorArea = $("[data-name='grossFloorArea']").attr("data-condition");
		var orderBy = $("#search_condition_order").val();
		url = url.replace("{residenceCommunityId}",residenceCommunityId);
		url = url.replace("{keyWord}",keyWord);
		url = url.replace("{region}",region);
		url = url.replace("{rental}",rental);
		url = url.replace("{roomNum}",roomNum);
		url = url.replace("{grossFloorArea}",grossFloorArea);
		url = url.replace("{orderBy}",orderBy);
		return url;
	}
</script>
</html>