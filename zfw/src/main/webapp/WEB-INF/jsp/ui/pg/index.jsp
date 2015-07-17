<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../inc/top.jsp" %>
<!DOCTYPE html>
<html>
<head>
<c:set var="headTitle" value="二手房评估"/>
<%@include file="../inc/header.jsp" %>
</head>
<body>
	<div class="main">
		<header>
			<div class="left">
				<a href="${ctx }/index" class="back"><i></i></a>
			</div>
			<div class="cent">二手房评估</div>
			<div class="show_redrict head-icon">
				<a class="icon-nav" id="show_redrict" href="javascript:void(0);"
					onclick="hideOrOpenNav()"> <span><i></i>
						<p>导航</p></span>
				</a>
			</div>
		</header>
		<%@include file="../inc/nav.jsp" %>
			<section class="mForm">
				<form onsubmit="return submitForm()">
				<dl>
					<dt>
						&nbsp;&nbsp;小<span style="margin: 0 1em;"></span>区&nbsp;：
					</dt>
					<dd>
						<div class="dropbox">
							<input type="text" id="xqName" name="xqName" class="ipt-text"
								dataId="" searching="false" searchingName="" autocomplete="off"
								resultContainer="xq_search_result" placeholder="请选择下拉提示"
								onkeyup="return inputOnKeyup(event,this)"
								onFocus="return inputOnFocus(event,this)"
								onblur="return inputOnBlus(event,this)" required>
							<div class="dropcont" id="js_sea" style="display: block;">
								<ul id="xq_search_result" class="none"
									style="border-top: 0; border-bottom: 0">
								</ul>
							</div>
						</div>
					</dd>
				</dl>
				<dl>
					<dt>
						楼<span style="margin: 0 1em;"></span>层：
					</dt>
					<dd>
						<input id="curFloor" type="text" placeholder="填写楼层" pattern="^[1-9][0-9]*$" required autocomplete="off"
							class="ipt-text referprice" >
					</dd>
				</dl>
				<dl>
					<dt>
						朝<span style="margin: 0 1em;"></span>向：
					</dt>
					<dd>
						<select name="forward" id="forward" class="referprice">
							<option value="南北">南北</option>
							<option value="南">南</option>
							<option value="东南">东南</option>
							<option value="东">东</option>
							<option value="西南">西南</option>
							<option value="北">北</option>
							<option value="西">西</option>
							<option value="东西">东西</option>
							<option value="东北">东北</option>
							<option value="西北">西北</option>
						</select>
					</dd>
				</dl>
				<dl>
					<dt>
						面<span style="margin: 0 1em;"></span>积：
					</dt>
					<dd>
						<input id="grossFloorArea" type="text" placeholder="填写面积" class="ipt-text referprice" autocomplete="off"
							  pattern="^[0-9]+\.{0,1}[0-9]{0,2}$" required>㎡
					</dd>
				</dl>
				<c:if test="${results != null and fn:length(results) != 0 }">
				<div class="none" id="moreinfo">
			       	<c:forEach items="${results}" var="t" varStatus="status">
						<dl>
							<dt>${t.name }：</dt>
							<dd>
							<c:choose>
								<c:when test="${t.type == '单选'}">
									<select class="js-radio-type">
			       						<c:forEach items="${t.params}" var="t2" varStatus="status2">
											<option value="${t2.value }">${t2.name }</option>
			       						</c:forEach>
									</select>
								</c:when>
								<c:otherwise>
		       						<c:forEach items="${t.params}" var="t2" varStatus="status2">
									<input type="checkbox" class="js-multi-type" value="${t2.value }">${t2.name } 
		       						</c:forEach>
								</c:otherwise>
							</c:choose>
							</dd>
						</dl>
			       	</c:forEach>
				</div>
				<div class="cfjlm pdY10 bdbt" id="loadfj">
					<a href="javascript:void(0);" class="loadmore" onclick="showMore()">继续填写，评估更准确</a>
				</div>
				</c:if>
			</section>
			<section class="pd10 bdt bdb mt10 none" id="js-pg-result">
<!-- 					<p class="f18">评估结果：<span><span class="f999 flor f14">&nbsp;34758元/平</span><span class="fc00 flor f18">420万/套</span></span></p> -->
<!-- 					<div class="l"></div> -->
<!-- 					<div > -->
<!-- 						<span style="width:48%;display:inline-block">小区在售二手房 <span class="fc00">15套</span></span> -->
<!-- 						<span style="width:48%;display:inline-block">小区均价  <span class="fc00">34758元/平</span></span> -->
<!-- 						<span style="width:48%;display:inline-block">小区出租房 <span class="fc00">42套</span></span> -->
<!-- 					</div> -->
			</section>
			<div class="cfj-btnBox mt10">
				<input type="submit" id="showSubmitBtn" class="formbtn02" value="评估我的房子">
			</div>
			</form>
		<%@include file="../inc/footer.jsp" %>
		<%@include file="../inc/goHead.jsp" %>
	</div>
</body>
<%@include file="../inc/bottom.jsp" %>
<script>
function inputOnKeyup(event, element) {
	var ele = $(element);
	var container = $("#" + ele.attr("resultContainer"));
	container.removeClass("none");
	nameChange(element);
}
function inputOnFocus(event, element) {
	var ele = $(element);
	var container = $("#" + ele.attr("resultContainer"));
	container.removeClass("none");
	nameChange(element);
}
function inputOnBlus(event, element) {
	var ele = $(element);
	var container = $("#" + ele.attr("resultContainer"));
	setTimeout(function(){
		container.addClass("none");
		
	},200);
}
function nameChange(element) {
	var ele = $(element);
	if (ele.attr("searching") == "true" || ele.val() == "") {
		return;
	}
	ele.attr("searching", "true");
	ele.attr("searchingName", ele.val());
	var url = "${ctx}/xq/json/{name}/search";
	url = url.replace("{name}",ele.attr("searchingName"));
	$.ajax({
		type: "GET",
		async: true,
		url: url,
		dataType: "json",
		success: function (data) {
			if(data.success){
				var results = data.results;
				if(results!=null){
					var container = $("#" + ele.attr("resultContainer"));
					container.empty();
					for (var i = 0; i < results.length; i++) {
						var result = results[i];
						var str = ' <li class="li-loudong" dataId="'+result.id+'"  onclick="selectName(this)">' + result.name + '</li>';
						container.append(str);
					}
				}
				ele.attr("searching", "false");
				if (ele.attr("searchingName") != ele.val()) {
					nameChange(element);
				}else if(results.length==1 && results[0].name==ele.val()){
					container.children("li").click();
					container.addClass("none");
				}
			}else{
				alert(data.msg);
			}
		},
		error: function (data) {
			alert("查询失败，请稍后尝试！");
		}
	});
}
function selectName(element) {
	var ele = $(element);
	$("#xqName").attr("dataId",ele.attr("dataId"));
	$("#xqName").val(ele.html());
}

function submitForm() {
	var btn = $("#showSubmitBtn");
	if (btn.hasClass("disabled")) {
		return false;
	}
	btn.addClass("disabled");
	btn.val("评估中...");
	
	
	var xqName = $("#xqName").val();
	var xqId = $("#xqName").attr("dataId");
	var grossFloorArea = $("#grossFloorArea").val();
	var forward = $("#forward").val();
	var curFloor = $("#curFloor").val();

	var plusValue = 0;
	if(!$("#moreinfo").hasClass("none")){
		$(".js-radio-type").each(function(){
			plusValue+=Number($(this).val());
		});
		$(".js-multi-type").each(function(){
			if(this.checked){
				plusValue+=Number($(this).val());
			}
		});
	}

	var pgResult = $("#js-pg-result");
	pgResult.addClass("none");
	$.ajax({
		type: "GET",
		async: true,
		url: "${ctx}/pg/analyse",
		data: {residenceCommunityName:xqName,residenceCommunityId:xqId,grossFloorArea:grossFloorArea,forward:forward,curFloor:curFloor,plusValue:plusValue},
		dataType: "json",
		success: function (data) {
			if(data.success){
				var str = '<p class="f18">评估结果：<span><span class="f999 flor f14">&nbsp;{price}元/平</span><span class="fc00 flor f18">{totalPrice}万/套</span></span></p><div class="l"></div><div ><span style="width:48%;display:inline-block">小区在售二手房 <span class="fc00">{esfTotalNum}套</span></span><span style="width:48%;display:inline-block">小区均价  <span class="fc00">{averagePrice}元/平</span></span><span style="width:48%;display:inline-block">小区出租房 <span class="fc00">{zfTotalNum}套</span></span></div>'
				str = str.replace(/{price}/g,data.price);
				str = str.replace(/{totalPrice}/g,data.totalPrice);
				str = str.replace(/{esfTotalNum}/g,data.esfTotalNum);
				str = str.replace(/{zfTotalNum}/g,data.zfTotalNum);
				str = str.replace(/{averagePrice}/g,data.averagePrice);
				
				pgResult.empty();
				pgResult.append(str);
				pgResult.removeClass("none");
			}else{
				alert(data.msg);
			}
		},
		error: function (data) {
			alert("评估失败，请稍后尝试！");
		},
		complete:function(data){
			btn.removeClass("disabled");
			btn.val("评估我的房子");
		}
	});
	return false;
}

function showMore() {
	$("#loadfj").addClass("none");
	$("#moreinfo").removeClass("none");
}
</script>
</html>