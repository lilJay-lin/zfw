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
				<a href="${ctx }/user/zf" class="back"><i></i></a>
			</div>
			<div class="cent">管理租房</div>
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
							<a href="javascript:void(0)" id="refreshBtn" onclick="refreshZF()">刷新房源</a> <a href="javascript:void(0)" id="delBtn" onclick="deleteZF()">删除房源</a>
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
	<input type="hidden" id="zfId" value="${zf.id }">
</body>
<%@include file="../../inc/bottom.jsp" %>
<script>


function inputOnKeyup(event, element) {
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
	var btn = $("#submit");
	if (btn.hasClass("disabled")) {
		return false;
	}
	btn.addClass("disabled");
	btn.val("提交中...");
	
	var rhId = $("#zfId").val();
	var xqName = $("#xqName").val();
	var xqId = $("#xqName").attr("dataId");
	var roomNum = $("#roomNum").val();
	var hallNum = $("#hallNum").val();
	var toiletNum = $("#toiletNum").val();
	var grossFloorArea = $("#grossFloorArea").val();
	var forward = $("#forward").val();
	var decorationStatus = $("#decorationStatus").val();
	var leaseWay = $("#leaseWay").val();
	var facilityBed = $("#facilityBed").hasClass("active");
	var facilityBroadband = $("#facilityBroadband").hasClass("active");
	var facilityTv = $("#facilityTv").hasClass("active");
	var facilityWasher = $("#facilityWasher").hasClass("active");
	var facilityRefrigerator = $("#facilityRefrigerator").hasClass("active");
	var facilityAirConditioner = $("#facilityAirConditioner").hasClass("active");
	var facilityHeating = $("#facilityHeating").hasClass("active");
	var facilityHeater = $("#facilityHeater").hasClass("active");
	var curFloor = $("#curFloor").val();
	var totalFloor = $("#totalFloor").val();
	var rental = $("#rental").val();
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
		url: "${ctx}/user/zf/json/update",
		data: {id:rhId,residenceCommunityName:xqName,residenceCommunityId:xqId,roomNum:roomNum,hallNum:hallNum,toiletNum:toiletNum,grossFloorArea:grossFloorArea,forward:forward,decorationStatus:decorationStatus,leaseWay:leaseWay,facilityBed:facilityBed,facilityBroadband:facilityBroadband,facilityTv:facilityTv,facilityWasher:facilityWasher,facilityRefrigerator:facilityRefrigerator,facilityAirConditioner:facilityAirConditioner,facilityHeating:facilityHeating,facilityHeater:facilityHeater,curFloor:curFloor,totalFloor:totalFloor,rental:rental,phoneNum:phoneNum,name:name,address:address,introduction:introduction,preImageUrl:preImageUrl,imgUrls:imgUrls},
		dataType: "json",
		success: function (data) {
			if(data.success){
				alert("修改成功");
				top.location = "${ctx}/user/zf";
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
	function checkImgType(element){
		   var filePath=$(element).val();
		   var extStart=filePath.lastIndexOf(".");
		   var ext=filePath.substring(extStart,filePath.length).toUpperCase();
		   if(ext!=".PNG"&&ext!=".GIF"&&ext!=".JPG"&&ext!=".JPEG"){
			   return "图片限于png,gif,jpg,jpeg格式";
		   }else{
				if(element.files[0].size>20*1024*1024){
					return "图片最大支持20M";
				}
		   }
		   return null;
		}
	$(':file').change(function(){
		var errorStr = checkImgType(this);
		if(errorStr){
			alert(errorStr);
			return;
		}
		refreshImgTips();
		var str = '<dd><img src="${ctx}/assets/img/loading.gif" class="imgClass"><a href="javascript:void(0);" class="del" onclick="delImg(this)"></a></dd>';
		var imgObj =  $(str);
		$(this).parents("dd:first").before(imgObj);
		
		var formData = new FormData($("#uploadForm")[0]);	
	    $.ajax({
	        type:'POST',
	        url:'${ctx}/user/zf/uploadImg',
	        data: formData,
	        async: true,
	        cache: false,
			dataType : "json",
	        contentType: false,
	        processData: false,
	        success: function (data) {
				if(data.success){
					var final_url = data.imgPath;	
					imgObj.children(".imgClass").attr("src",final_url);
				}else{
					alert(data.msg);
				}
	        },
	        error: function (data) {
				alert("上传失败");
				imgObj.remove();
	        }
	    });
	});
	
	initData();
}); 
	function initData(){

		if("${zf.id}"){
			$("#zfId").val("${zf.id}");
		}
		if("${zf.residenceCommunityId}"){
			$("#xqName").attr("dataId","${zf.residenceCommunityId}");
		}
		if("${zf.residenceCommunityName}"){
			$("#xqName").val("${zf.residenceCommunityName}");
		}
		if("${zf.roomNum}"){
			$("#roomNum").val("${zf.roomNum}");
		}
		if("${zf.hallNum}"){
			$("#hallNum").val("${zf.hallNum}");
		}
		if("${zf.toiletNum}"){
			$("#toiletNum").val("${zf.toiletNum}");
		}
		if("${zf.grossFloorArea}"){
			$("#grossFloorArea").val("${zf.grossFloorArea}");
		}
		if("${zf.forward}"){
			$("#forward").val("${zf.forward}");
		}
		if("${zf.decorationStatus}"){
			$("#decorationStatus").val("${zf.decorationStatus}");
		}
		if("${zf.leaseWay}"){
			$("#leaseWay").val("${zf.leaseWay}");
		}

		if("${zf.facilityBed}"=="true"){
			$("#facilityBed").addClass("active");
		}else{
			$("#facilityBed").removeClass("active");
		}
		if("${zf.facilityBroadband}"=="true"){
			$("#facilityBroadband").addClass("active");
		}else{
			$("#facilityBroadband").removeClass("active");
		}
		if("${zf.facilityTv}"=="true"){
			$("#facilityTv").addClass("active");
		}else{
			$("#facilityTv").removeClass("active");
		}
		if("${zf.facilityWasher}"=="true"){
			$("#facilityWasher").addClass("active");
		}else{
			$("#facilityWasher").removeClass("active");
		}
		if("${zf.facilityRefrigerator}"=="true"){
			$("#facilityRefrigerator").addClass("active");
		}else{
			$("#facilityRefrigerator").removeClass("active");
		}
		if("${zf.facilityAirConditioner}"=="true"){
			$("#facilityAirConditioner").addClass("active");
		}else{
			$("#facilityAirConditioner").removeClass("active");
		}
		if("${zf.facilityHeating}"=="true"){
			$("#facilityHeating").addClass("active");
		}else{
			$("#facilityHeating").removeClass("active");
		}
		if("${zf.facilityHeater}"=="true"){
			$("#facilityHeater").addClass("active");
		}else{
			$("#facilityHeater").removeClass("active");
		}

		if("${zf.curFloor}"){
			$("#curFloor").val("${zf.curFloor}");
		}
		if("${zf.totalFloor}"){
			$("#totalFloor").val("${zf.totalFloor}");
		}
		if("${zf.rental}"){
			$("#rental").val("${zf.rental}");
		}
		if("${zf.phoneNum}"){
			$("#phoneNum").val("${zf.phoneNum}");
		}
		if("${zf.name}"){
			$("#name").val("${zf.name}");
		}
		if("${zf.address}"){
			$("#address").val("${zf.address}");
		}
		if("${zf.introduction}"){
			$("#introduction").val("${zf.introduction}");
		}
		var btn = $("#submit");
		btn.val("确定修改");
		
		refreshImgTips();
	}
	
	function refreshImgTips(){
		if($(".imgClass").length>0){
			$("#note").addClass("none");
		}else{
			$("#note").removeClass("none");
		}
	}
	
	function delImg(element){
		var flag = confirm("确定删除图片？");
		if(flag){
			var ele = $(element);
			ele.parents("dd:first").remove();
			refreshImgTips();
		}
	}
	
	function refreshZF(){

		var btn = $("#refreshBtn");
		if (btn.hasClass("disabled")) {
			return false;
		}
		btn.addClass("disabled");
		btn.html("刷新中...");
		
		$.ajax({
			type: "POST",
			async: true,
			url: "${ctx}/user/zf/json/refresh",
			data: {id:$("#zfId").val()},
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
	
	function deleteZF(){
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
				url: "${ctx}/user/zf/json/del",
				data: {id:$("#zfId").val()},
				dataType: "json",
				success: function (data) {
					if(data.success){
						alert("删除成功");
						top.location = "${ctx}/user/zf";
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