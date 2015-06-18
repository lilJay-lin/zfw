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
				
				<c:choose>
					<c:when test="${resultType=='楼盘'}">
					<input type="search" name="keyword" id="keyWord" value="${keyWord }"
						placeholder="楼盘名/地名" autocomplete="off">
					</c:when>
					<c:otherwise>
					<input type="search" name="keyword" id="keyWord" value="${keyWord }"
						placeholder="户型名/楼盘名" autocomplete="off">
					</c:otherwise>
				</c:choose>
						<a href="javascript:;" class="off" style="display: none;"></a>
				</div>
				<a href="javascript:;" id="searchBtn" onclick="searchAction()" class="btn" rel="nofollow"><i></i></a>
			</div>
			<a id="wapxfsy_D01_07" href="${ctx }/xf/map"
				class="mapbtn">地图</a>
		</form>

		<section class="whitebg bdt bdb">
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
					<li class="notImportant none">
						<p>
							<span class="js-selected" data-name="resultType" data-condition="楼盘">楼盘 </span> <span
								class="js-sift-condition none" data-condition="楼盘">楼盘 </span> <span
								class="js-sift-condition" data-condition="户型">户型</span>
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
				<h5>共搜索到${total }个楼盘</h5>
			</h5>

			<span> <select id="search_condition_order">
					<option value="default">默认排序</option>
					<option value="priceFromHigh">价格由高到低</option>
					<option value="priceFromLow">价格由低到高</option>
					<option value="onSaleFromNear">开盘时间由近到远</option>
					<option value="onSaleFromFar">开盘时间由远到近</option>
			</select>
			</span>
		</div>
		<section class="homeList whitebg bdb">
			<ul id="itemList">
			
				<c:choose>
					<c:when test="${resultType=='楼盘'}">
		       			<c:forEach items="${results}" var="t" varStatus="status">
						<li><a id="${t.id}" href="${ctx}/xf/${t.id }/detail">
								<div class="img">
									<img
										src="${t.preImageUrl }">
								</div>
								<div class="txt">
									<p class="x-intro">
									<h2>${t.name }</h2>
									<span class="new">${t.averagePrice }元/平</span>
									</p>
									<p>${t.address }</p>
		
									<div class="stag">
										<span class="t1">户型：
										<c:if test="${t.oneRoomNum != null and t.oneRoomNum != '0' }">
										一居(<span class="t2">${t.oneRoomNum }</span>) 
										</c:if>
										<c:if test="${t.twoRoomNum != null and t.twoRoomNum != '0' }">
											二居(<span class="t2">${t.twoRoomNum }</span>) 
										</c:if>
										<c:if test="${t.threeRoomNum != null and t.threeRoomNum != '0' }">
											三居(<span class="t2">${t.threeRoomNum }</span>) 
										</c:if>
										<c:if test="${t.fourRoomNum != null and t.fourRoomNum != '0' }">
											四居(<span class="t2">${t.fourRoomNum }</span>) 
										</c:if>
										<c:if test="${t.fiveRoomNum != null and t.fiveRoomNum != '0' }">
											五居(<span class="t2">${t.fiveRoomNum }</span>) 
										</c:if>
										<c:if test="${t.overFiveRoomNum != null and t.overFiveRoomNum != '0' }">
											五居以上(<span class="t2">${t.overFiveRoomNum }</span>) 
										</c:if>
									</div>
								</div>
						</a></li>
		       			</c:forEach>
					</c:when>
					<c:otherwise>
		       			<c:forEach items="${results}" var="t" varStatus="status">
						<li><a id="${t.id}" href="${ctx}/hx/${t.id }/detail">
								<div class="img">
									<img
										src="${t.preImageUrl }">
								</div>
								<div class="txt">
									<p class="x-intro">
									<h2>${t.name }</h2>
									<span class="new">${t.averagePrice }元/平</span>
									</p>
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
		
									<div class="stag">
										<span class="t1">
											${t.realEstateProjectName }
										</span>
									</div>
								</div>
						</a></li>
		       			</c:forEach>
					</c:otherwise>
				</c:choose>
			</ul>
		</section>
		<c:choose>
			<c:when test="${total!=0}">
				<div id="drag" class="cenBtn">
					<a class="draginner" href="javascript:void(0);">查看更多${resultType }</a>
				</div>
			</c:when>
			<c:otherwise>
				<div class="searchNo">
			    	<p class="f14">暂未搜索到符合条件的${resultType }。</p>
				</div>
			</c:otherwise>
		</c:choose>
		<%@include file="../inc/footer.jsp" %>
		<%@include file="../inc/goHead.jsp" %>
	</div>
</body>
<%@include file="../inc/bottom.jsp" %>
<script>
var total=Math.ceil(${total}/5);
var allowLoad = true;
var curp = 1;

	$(function() {
		initEvent();
		if("${region}"){
			setItemValue("region","${region}");
		}
		if("${averagePrice}"){
			setItemValue("averagePrice","${averagePrice}");
		}
		if("${roomNum}"){
			setItemValue("roomNum","${roomNum}");
		}
		if("${grossFloorArea}"){
			setItemValue("grossFloorArea","${grossFloorArea}");
		}
		if("${saleStatus}"){
			setItemValue("saleStatus","${saleStatus}");
		}
		if("${resultType}"){
			setItemValue("resultType","${resultType}");
		}
		if("${orderBy}"){
			$("#search_condition_order").val("${orderBy}");
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
		if(curp>=total){
			allowLoad = false;
			$('#drag').hide();
			return;
		}
		allowLoad = false;
		draginner.css('padding-left','10px');
		draginner.addClass("loading");
		draginner.html("正在加载...");
		var url = "${ctx}/xf/json/{keyWord}-{region}-{averagePrice}-{roomNum}-{grossFloorArea}-{saleStatus}-{resultType}-{bound}-{orderBy}-{targetPage}-{pageSize}/search";
		url = bindSearchData(url);
		url = url.replace("{bound}","");
		url = url.replace("{targetPage}",curp+1);
		url = url.replace("{pageSize}",5);
		$.ajax({
			url : url,
			dataType:'json',
			success: function(json){
				if(json.success){
					$(".searchResultNum").html(json.num);
					for(var i=0;i<json.results.length;i++){
						var result = json.results[i];

						<c:choose>
							<c:when test="${resultType=='楼盘'}">
								var str = '<li><a id="{id}" href="${ctx}/xf/{id}/detail"> <div class="img"> <img src="{preImageUrl}"> </div> <div class="txt"> <p class="x-intro"> <h2>{name}</h2> <span class="new">{averagePrice}元/平</span> </p> <p>{address}</p> <div class="stag"> <span class="t1">户型：{roomNumStr} </div> </div> </a></li>';
								var roomNumStr = "";
								if(result.oneRoomNum && result.oneRoomNum>0){
									roomNumStr+='一居(<span class="t2">'+result.oneRoomNum+'</span>) ';
								}
								if(result.twoRoomNum && result.twoRoomNum>0){
									roomNumStr+='二居(<span class="t2">'+result.twoRoomNum+'</span>) ';
								}
								if(result.threeRoomNum && result.threeRoomNum>0){
									roomNumStr+='三居(<span class="t2">'+result.threeRoomNum+'</span>) ';
								}
								if(result.fourRoomNum && result.fourRoomNum>0){
									roomNumStr+='四居(<span class="t2">'+result.fourRoomNum+'</span>) ';
								}
								if(result.fiveRoomNum && result.fiveRoomNum>0){
									roomNumStr+='五居(<span class="t2">'+result.fiveRoomNum+'</span>) ';
								}
								if(result.overFiveRoomNum && result.overFiveRoomNum>0){
									roomNumStr+='五居以上(<span class="t2">'+result.overFiveRoomNum+'</span>) ';
								}
								str = str.replace(/{id}/g,result.id);
								str = str.replace(/{preImageUrl}/g,result.preImageUrl);
								str = str.replace(/{name}/g,result.name);
								str = str.replace(/{averagePrice}/g,result.averagePrice);
								str = str.replace(/{address}/g,result.address);
								str = str.replace(/{roomNumStr}/g,roomNumStr);
								$('#itemList').append(str);
							</c:when>
							<c:otherwise>
								var str = '<li><a id="{id}" href="${ctx}/hx/{id}/detail"> <div class="img"> <img src="{preImageUrl}"> </div> <div class="txt"> <p class="x-intro"> <h2>{name}</h2> <span class="new">{averagePrice}元/平</span> </p> <p>{numMsgStr}</p> <div class="stag"> <span class="t1">{realEstateProjectName} </div> </div> </a></li>';
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
								if(result.kitchenNum && result.kitchenNum>0){
									numMsgStr+=' '+result.kitchenNum+'厨';
								}
								if(result.grossFloorArea && result.grossFloorArea>0){
									numMsgStr+=' 约'+result.grossFloorArea+'㎡';
								}
								str = str.replace(/{id}/g,result.id);
								str = str.replace(/{preImageUrl}/g,result.preImageUrl);
								str = str.replace(/{name}/g,result.name);
								str = str.replace(/{averagePrice}/g,result.averagePrice);
								str = str.replace(/{numMsgStr}/g,numMsgStr);
								str = str.replace(/{realEstateProjectName}/g,result.realEstateProjectName);
								$('#itemList').append(str);
							</c:otherwise>
						</c:choose>
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
				draginner.html("查看更多${resultType }");
				
				allowLoad = true;
				if(curp+1>=total){
					$('#drag').hide();
					allowLoad = false;
				}
			}
		}); 
	}
	function searchAction(){
		var url = "${ctx}/xf/{keyWord}-{region}-{averagePrice}-{roomNum}-{grossFloorArea}-{saleStatus}-{resultType}-{orderBy}/search";
		url = bindSearchData(url);
		top.location.href = url;
	}
	function bindSearchData(url){
		var keyWord = $("#keyWord").val();
		var region = $("[data-name='region']").attr("data-condition");
		var averagePrice = $("[data-name='averagePrice']").attr("data-condition");
		var roomNum = $("[data-name='roomNum']").attr("data-condition");
		var grossFloorArea = $("[data-name='grossFloorArea']").attr("data-condition");
		var saleStatus = $("[data-name='saleStatus']").attr("data-condition");
		var resultType = $("[data-name='resultType']").attr("data-condition");
		var orderBy = $("#search_condition_order").val();
		url = url.replace("{keyWord}",keyWord);
		url = url.replace("{region}",region);
		url = url.replace("{averagePrice}",averagePrice);
		url = url.replace("{roomNum}",roomNum);
		url = url.replace("{grossFloorArea}",grossFloorArea);
		url = url.replace("{saleStatus}",saleStatus);
		url = url.replace("{resultType}",resultType);
		url = url.replace("{orderBy}",orderBy);
		return url;
	}
</script>
</html>