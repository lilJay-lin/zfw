<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../inc/top.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@include file="../../inc/header.jsp" %>
</head>
<body>
	<div class="main whitebg">
		<header>
			<div class="left">
				<a href="${ctx }/user/esf" class="back"><i></i></a>
			</div>
			<div class="cent">管理二手房</div>
			<div class="show_redrict head-icon">
				<a class="icon-nav" id="show_redrict" href="javascript:void(0);"
					onclick="hideOrOpenNav()"> <span><i></i>
						<p>导航</p></span>
				</a>
			</div>
		</header>
		<%@include file="../../inc/nav.jsp" %>
		
		<div class="wapForm">
			<dl>
				<span class="pd10">房源有效期：<span id="deadline">${deadline }</span></span>
			</dl>
			<div class="btns">
							<a href="javascript:void(0)" id="refreshBtn" onclick="refreshESF()">刷新房源</a> <a href="javascript:void(0)" id="delBtn" onclick="deleteESF()">删除房源</a>
			</div>
			<div class="formIntro multi_preview_add addpic mt10">
				<div class="item" id="add_pic">
					<div class="grade-addpic bbsAddPic" id="bbsAddPic"
						style="float: left; margin-right: 14px; display: inline-block;">
						<dl>
							<c:if test="${images != null and fn:length(images) != 0 }">
				       			<c:forEach items="${images}" var="t" varStatus="status">
				       				<dd><img src="${t.contentUrl }" class="imgClass"><a href="javascript:void(0);" class="del" onclick="delImg(this)"></a></dd>
				       			</c:forEach>
			       			</c:if>
							<dd class="add">
								<form id="uploadForm" method= "post" enctype ="multipart/form-data">
									<input type="file" name="theFile" id="theFile" accept="image/*"
										class="upload-input">
								</form>
							</dd>
						</dl>
					</div>
					<p class="rightWord f14 f999" id="note">上传图片电话量将提升50%
						第一张为默认封面图</p>
				</div>
			</div>
		</div>
		<%@include file="commonBody.jsp" %>
		
		<%@include file="../../inc/footer.jsp" %>
		<%@include file="../../inc/goHead.jsp" %>
	</div>
	<input type="hidden" id="esfId" value="${esf.id }">
</body>
<%@include file="../../inc/bottom.jsp" %>
<%@include file="commonBottom.jsp" %>
<script>

function submitForm() {
	var btn = $("#submit");
	if (btn.hasClass("disabled")) {
		return false;
	}
	btn.addClass("disabled");
	btn.val("提交中...");
	
	var shhId = $("#esfId").val();
	var xqName = $("#xqName").val();
	var xqId = $("#xqName").attr("dataId");
	var roomNum = $("#roomNum").val();
	var hallNum = $("#hallNum").val();
	var toiletNum = $("#toiletNum").val();
	var grossFloorArea = $("#grossFloorArea").val();
	var insideArea = $("#insideArea").val();
	var forward = $("#forward").val();
	var decorationStatus = $("#decorationStatus").val();
	var curFloor = $("#curFloor").val();
	var totalFloor = $("#totalFloor").val();
	var totalPrice = $("#totalPrice").val();
	var phoneNum = $("#phoneNum").val();
	var name = $("#name").val();
	var address = $("#address").val();
	var introduction = $("#introduction").val();
	
	var imgs = $(".imgClass");
	var preImageUrl = "";
	var imgUrls = "";
	imgs.each(function(index){
		if(index==0){
			preImageUrl = $(this).attr("src");
		}
		if(index+1==imgs.length){
			imgUrls+=$(this).attr("src");
		}else{
			imgUrls+=$(this).attr("src")+",";
		}
	});
	if(imgUrls.indexOf("${ctx}/assets/img/loading.gif")!=-1){
		alert("图片仍在上传中");
		btn.removeClass("disabled");
		btn.val("确定修改");
		return false;
	}
	$.ajax({
		type: "POST",
		async: true,
		url: "${ctx}/user/esf/json/update",
		data: {id:shhId,residenceCommunityName:xqName,residenceCommunityId:xqId,roomNum:roomNum,hallNum:hallNum,toiletNum:toiletNum,insideArea:insideArea,grossFloorArea:grossFloorArea,forward:forward,decorationStatus:decorationStatus,curFloor:curFloor,totalFloor:totalFloor,totalPrice:totalPrice,phoneNum:phoneNum,name:name,address:address,introduction:introduction,preImageUrl:preImageUrl,imgUrls:imgUrls},
		dataType: "json",
		success: function (data) {
			if(data.success){
				alert("修改成功");
				top.location = "${ctx}/user/esf";
			}else{
				alert(data.msg);
			}
		},
		error: function (data) {
			alert("修改失败，请稍后尝试！");
		},
		complete:function(data){
			btn.removeClass("disabled");
			btn.val("确定修改");
		}
	});
	return false;
}
$(function(){
	initData();
}); 
	function initData(){

		if("${esf.id}"){
			$("#esfId").val("${esf.id}");
		}
		if("${esf.residenceCommunityId}"){
			$("#xqName").attr("dataId","${esf.residenceCommunityId}");
		}
		if("${esf.residenceCommunityName}"){
			$("#xqName").val("${esf.residenceCommunityName}");
		}
		if("${esf.roomNum}"){
			$("#roomNum").val("${esf.roomNum}");
		}
		if("${esf.hallNum}"){
			$("#hallNum").val("${esf.hallNum}");
		}
		if("${esf.toiletNum}"){
			$("#toiletNum").val("${esf.toiletNum}");
		}
		if("${esf.grossFloorArea}"){
			$("#grossFloorArea").val("${esf.grossFloorArea}");
		}
		if("${esf.insideArea}"){
			$("#insideArea").val("${esf.insideArea}");
		}
		if("${esf.forward}"){
			$("#forward").val("${esf.forward}");
		}
		if("${esf.decorationStatus}"){
			$("#decorationStatus").val("${esf.decorationStatus}");
		}
		if("${esf.curFloor}"){
			$("#curFloor").val("${esf.curFloor}");
		}
		if("${esf.totalFloor}"){
			$("#totalFloor").val("${esf.totalFloor}");
		}
		if("${esf.totalPrice}"){
			$("#totalPrice").val("${esf.totalPrice}");
		}
		if("${esf.phoneNum}"){
			$("#phoneNum").val("${esf.phoneNum}");
		}
		if("${esf.name}"){
			$("#name").val("${esf.name}");
		}
		if("${esf.address}"){
			$("#address").val("${esf.address}");
		}
		if("${esf.introduction}"){
			$("#introduction").val("${esf.introduction}");
		}
		var btn = $("#submit");
		btn.val("确定修改");
		
		refreshImgTips();
		assessment();
	}
	
	function refreshESF(){

		var btn = $("#refreshBtn");
		if (btn.hasClass("disabled")) {
			return false;
		}
		btn.addClass("disabled");
		btn.html("刷新中...");
		
		$.ajax({
			type: "POST",
			async: true,
			url: "${ctx}/user/esf/json/refresh",
			data: {id:$("#esfId").val()},
			dataType: "json",
			success: function (data) {
				if(data.success){
					alert("刷新成功");
					$("#deadline").html(data.time);
				}else{
					alert(data.msg);
				}
			},
			error: function (data) {
				alert("刷新失败，请稍后尝试！");
			},
			complete:function(data){
				btn.removeClass("disabled");
				btn.html("刷新房源");
			}
		});
	}
	
	function deleteESF(){
		var flag = confirm("确定删除房源？");
		if(flag){
			var btn = $("#delBtn");
			if (btn.hasClass("disabled")) {
				return false;
			}
			btn.addClass("disabled");
			btn.html("删除中...");
			
			$.ajax({
				type: "POST",
				async: true,
				url: "${ctx}/user/esf/json/del",
				data: {id:$("#esfId").val()},
				dataType: "json",
				success: function (data) {
					if(data.success){
						alert("删除成功");
						top.location = "${ctx}/user/esf";
					}else{
						alert(data.msg);
					}
				},
				error: function (data) {
					alert("删除失败，请稍后尝试！");
				},
				complete:function(data){
					btn.removeClass("disabled");
					btn.html("删除房源");
				}
			});
		}
	}
</script>
</html>