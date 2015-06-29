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
			<div class="cent">发布租房</div>
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
		btn.val("立即发布");
		return false;
	}
	$.ajax({
		type: "POST",
		async: true,
		url: "${ctx}/user/zf/json/add",
		data: {residenceCommunityName:xqName,residenceCommunityId:xqId,roomNum:roomNum,hallNum:hallNum,toiletNum:toiletNum,grossFloorArea:grossFloorArea,forward:forward,decorationStatus:decorationStatus,leaseWay:leaseWay,facilityBed:facilityBed,facilityBroadband:facilityBroadband,facilityTv:facilityTv,facilityWasher:facilityWasher,facilityRefrigerator:facilityRefrigerator,facilityAirConditioner:facilityAirConditioner,facilityHeating:facilityHeating,facilityHeater:facilityHeater,curFloor:curFloor,totalFloor:totalFloor,rental:rental,phoneNum:phoneNum,name:name,address:address,introduction:introduction,preImageUrl:preImageUrl,imgUrls:imgUrls},
		dataType: "json",
		success: function (data) {
			if(data.success){
				alert("发布成功");
				top.location = "${ctx}/user/zf";
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
$(function(){
	function checkImgType(element){
		   var filePath=$(element).val();
		   var extStart=filePath.lastIndexOf(".");
		   var ext=filePath.substring(extStart,filePath.length).toUpperCase();
		   if(ext!=".PNG"&&ext!=".GIF"&&ext!=".JPG"){
			   return "图片限于png,gif,jpg格式";
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
}); 


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
</script>
</html>