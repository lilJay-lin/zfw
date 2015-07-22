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
			<div class="cent">发布二手房</div>
			<div class="show_redrict head-icon">
				<a class="icon-nav" id="show_redrict" href="javascript:void(0);"
					onclick="hideOrOpenNav()"> <span><i></i>
						<p>导航</p></span>
				</a>
			</div>
		</header>
		<%@include file="../../inc/nav.jsp" %>
		
		<div class="formIntro multi_preview_add addpic">
			<div class="item" id="add_pic">
				<div class="grade-addpic bbsAddPic" id="bbsAddPic"
					style="float: left; margin-right: 14px; display: inline-block;">
					<dl>
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
		<%@include file="commonBody.jsp" %>
		<%@include file="../../inc/footer.jsp" %>
		<%@include file="../../inc/goHead.jsp" %>
	</div>
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
		btn.val("立即发布");
		return false;
	}
	$.ajax({
		type: "POST",
		async: true,
		url: "${ctx}/user/esf/json/add",
		data: {residenceCommunityName:xqName,residenceCommunityId:xqId,roomNum:roomNum,hallNum:hallNum,toiletNum:toiletNum,insideArea:insideArea,grossFloorArea:grossFloorArea,forward:forward,decorationStatus:decorationStatus,curFloor:curFloor,totalFloor:totalFloor,totalPrice:totalPrice,phoneNum:phoneNum,name:name,address:address,introduction:introduction,preImageUrl:preImageUrl,imgUrls:imgUrls},
		dataType: "json",
		success: function (data) {
			if(data.success){
				alert("发布成功");
				top.location = "${ctx}/user/esf";
			}else{
				alert(data.msg);
			}
		},
		error: function (data) {
			alert("发布失败，请稍后尝试！");
		},
		complete:function(data){
			btn.removeClass("disabled");
			btn.val("立即发布");
		}
	});
	return false;
}
</script>
</html>