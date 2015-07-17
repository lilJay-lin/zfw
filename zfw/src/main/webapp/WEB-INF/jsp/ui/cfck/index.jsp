<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../inc/top.jsp" %>
<!DOCTYPE html>
<html>
<head>
<c:set var="headKeywords" value="厂房仓库"/>
<c:set var="headTitle" value="厂房仓库"/>
<%@include file="../inc/header.jsp" %>
</head>
<body>
	<div class="main">
		<header>
			<div class="left">
				<a href="${ctx }/index" class="hLogo"></a>
			</div>
			<div class="cent">厂房仓库</div>
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
						placeholder="厂房仓库名/地名" autocomplete="off">
						<a href="javascript:;" class="off" style="display: none;"></a>
				</div>
				<a href="javascript:;" id="searchBtn" onclick="searchAction()" class="btn" rel="nofollow"><i></i></a>
			</div>
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
							<span class="js-selected" data-name="grossFloorArea" data-condition=""> 不限 </span>
							<span class="js-sift-condition none" data-condition=""> 不限</span>
							<span class="js-sift-condition" data-condition="1:299"> 300以下</span>
							<span class="js-sift-condition" data-condition="300:599"> 300-599 </span>
							<span class="js-sift-condition" data-condition="600:899"> 600-899 </span>
							<span class="js-sift-condition" data-condition="900:1199"> 900-1199 </span>
							<span class="js-sift-condition" data-condition="1200:1499"> 1200-1499</span>
							<span class="js-sift-condition" data-condition="1500:1799"> 1500-1799</span>
							<span class="js-sift-condition" data-condition="1800:2099"> 1800-2099</span>
							<span class="js-sift-condition" data-condition="2100:10000"> 2100以上</span>
						</p> <strong>面积</strong> <em class="arrowDown"></em>
					</li>
					<li>
						<p>
							<span class="js-selected" data-name="type" data-condition=""> 不限 </span>
							<span class="js-sift-condition none" data-condition=""> 不限</span>
							<span class="js-sift-condition" data-condition="厂房"> 厂房</span>
							<span class="js-sift-condition" data-condition="仓库"> 仓库 </span>
						</p> <strong>类别</strong> <em class="arrowDown"></em>
					</li>
					<li class="notImportant none">
						<p>
							<span class="js-selected" id="rosValue" data-name="rentOrSale" data-condition="出租"> 出租</span>
							<span class="js-sift-condition none" data-condition="出租"> 出租</span>
							<span class="js-sift-condition" data-condition="出售"> 出售 </span>
						</p> <strong>租售</strong> <em class="arrowDown"></em>
					</li>
					<li class="notImportant none">
						<p>
							<span class="js-selected" data-name="rental" data-condition=""> 不限 </span>
							<span class="js-sift-condition none" data-condition=""> 不限</span>
							<span class="js-sift-condition" data-condition="1:499"> 500以下</span>
							<span class="js-sift-condition" data-condition="500:999"> 500-999</span>
							<span class="js-sift-condition" data-condition="1000:1999"> 1000-1999</span>
							<span class="js-sift-condition" data-condition="2000:2999"> 2000-2999</span>
							<span class="js-sift-condition" data-condition="3000:4999"> 3000-4999</span>
							<span class="js-sift-condition" data-condition="5000:1000000"> 5000以上</span>
						</p> <strong>租金</strong> <em class="arrowDown"></em>
					</li>
					<li class="notImportant none">
						<p>
							<span class="js-selected" data-name="totalPrice" data-condition=""> 不限 </span>
							<span class="js-sift-condition none" data-condition=""> 不限</span>
							<span class="js-sift-condition" data-condition="1:49"> 50以下</span>
							<span class="js-sift-condition" data-condition="50:69"> 50-69 </span>
							<span class="js-sift-condition" data-condition="70:89"> 70-89 </span>
							<span class="js-sift-condition" data-condition="90:109"> 90-109 </span>
							<span class="js-sift-condition" data-condition="110:129"> 110-129</span>
							<span class="js-sift-condition" data-condition="130:149"> 130-149</span>
							<span class="js-sift-condition" data-condition="150:199"> 150-199</span>
							<span class="js-sift-condition" data-condition="200:10000"> 200以上</span>
						</p> <strong>总价</strong> <em class="arrowDown"></em>
					</li>
				</ul>

				<div id="listMore" class="down" onclick="spreadList()">
					<span class="icon"></span> <span class="txt">展开更多查询条件</span>
				</div>
			</div>
		</section>
		<div class="resultWarp whitebg mt10 bdt">
			<h5 id="search_result_num">
				<h5>共搜索到${total }个厂房仓库</h5>
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
				<li><a id="${t.id}" href="${ctx}/cfck/${t.id }/detail">
						<div class="img">
							<img src="${t.preImageUrl }">
						</div>
						<div class="txt">
							<p class="x-intro">
							<h2>${t.name }</h2>
							<c:choose>
								<c:when test="${rentOrSale!=null and rentOrSale == '出售'}">
									<span class="new">${t.totalPrice }万</span>
								</c:when>
								<c:otherwise>
									<span class="new">${t.rental }元/月</span>
								</c:otherwise>
							</c:choose>
							</p>
							<p>${t.grossFloorArea}㎡</p>
							
							<div class="stag">
								<span class="t1">
									${t.address }
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
					<a class="draginner" href="javascript:void(0);">查看更多厂房仓库</a>
				</div>
			</c:when>
			<c:otherwise>
				<div class="searchNo">
			    	<p class="f14">暂未搜索到符合条件的厂房仓库。</p>
				</div>
			</c:otherwise>
		</c:choose>
		<%@include file="../inc/footer.jsp" %>
		<%@include file="../inc/goHead.jsp" %>
	</div>
	<input type="hidden" id="total" value="${total }">
	<input type="hidden" id="rentOrSale" value="${rentOrSale }">
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
		if("${grossFloorArea}"){
			setItemValue("grossFloorArea","${grossFloorArea}");
		}
		if("${type}"){
			setItemValue("type","${type}");
		}
		if("${rentOrSale}"){
			setItemValue("rentOrSale","${rentOrSale}");
		}
		if("${rental}"){
			setItemValue("rental","${rental}");
		}
		if("${totalPrice}"){
			setItemValue("totalPrice","${totalPrice}");
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
		
		var eleDataName = element.siblings(".js-selected").attr("data-name");
		var rosValue = $("#rosValue").attr("data-condition");
		if((eleDataName=="rental" && rosValue=="出售") || (eleDataName=="totalPrice" && rosValue=="出租")){
			return;
		}
		
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
		var url = "${ctx}/cfck/json/{keyWord}-{region}-{grossFloorArea}-{type}-{rentOrSale}-{rental}-{totalPrice}-{orderBy}-{targetPage}-{pageSize}/search";
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
						var str = '<li><a id="{id}" href="${ctx}/cfck/{id}/detail"> <div class="img"> <img src="{preImageUrl}"> </div> <div class="txt"> <p class="x-intro"> <h2>{name}</h2> <span class="new">{price}</span> </p> <p>{grossFloorArea}</p> <div class="stag"> <span class="t1">{address} </div> </div> </a></li>';
						var price = "";
						if($("#rentOrSale").val() && $("#rentOrSale").val() == "出售"){
							price = result.totalPrice+'万';
						}else{
							price = result.rental+'元/月';
						}
						var grossFloorArea = "";
						if(result.grossFloorArea && result.grossFloorArea>0){
							grossFloorArea =' 约'+parseFloat(result.grossFloorArea).toFixed(2); +'㎡';
						}
						str = str.replace(/{id}/g,result.id);
						str = str.replace(/{preImageUrl}/g,result.preImageUrl);
						str = str.replace(/{name}/g,result.name);
						str = str.replace(/{price}/g,price);
						str = str.replace(/{grossFloorArea}/g,grossFloorArea);
						str = str.replace(/{address}/g,result.address);
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
				draginner.html("查看更多厂房仓库");
				
				allowLoad = true;
				if(curp+1>=total){
					$('#drag').hide();
					allowLoad = false;
				}
			}
		}); 
	}
	function searchAction(){
		var url = "${ctx}/cfck/{keyWord}-{region}-{grossFloorArea}-{type}-{rentOrSale}-{rental}-{totalPrice}-{orderBy}/search";
		url = bindSearchData(url);
		top.location.href = url;
	}
	function bindSearchData(url){
		var keyWord = $("#keyWord").val();
		keyWord = keyWord.replace(/([^A-Za-z0-9\u4e00-\u9fa5\(\)_])+/g,"");
		var region = $("[data-name='region']").attr("data-condition");
		var grossFloorArea = $("[data-name='grossFloorArea']").attr("data-condition");
		var type = $("[data-name='type']").attr("data-condition");
		var rentOrSale = $("[data-name='rentOrSale']").attr("data-condition");
		var rental = $("[data-name='rental']").attr("data-condition");
		var totalPrice = $("[data-name='totalPrice']").attr("data-condition");
		var orderBy = $("#search_condition_order").val();
		
		url = url.replace("{keyWord}",keyWord);
		url = url.replace("{region}",region);
		url = url.replace("{grossFloorArea}",grossFloorArea);
		url = url.replace("{type}",type);
		url = url.replace("{rentOrSale}",rentOrSale);
		url = url.replace("{rental}",rental);
		url = url.replace("{totalPrice}",totalPrice);
		url = url.replace("{orderBy}",orderBy);
		return url;
	}
</script>
</html>