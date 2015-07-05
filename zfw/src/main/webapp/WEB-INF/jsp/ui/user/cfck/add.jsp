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
				<a href="${ctx }/user/cfck" class="back"><i></i></a>
			</div>
			<div class="cent">发布厂房仓库</div>
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

function submitForm() {
	var btn = $("#submit");
	if (btn.hasClass("disabled")) {
		return false;
	}
	btn.addClass("disabled");
	btn.val("提交中...");
	
	var grossFloorArea = $("#grossFloorArea").val();
	var region = $("#region").val();
	var type = $("#type").val();
	
	var rental = $("#rental").val();
	var totalPrice = $("#totalPrice").val();
	var phoneNum = $("#phoneNum").val();
	var name = $("#name").val();
	var address = $("#address").val();
	var introduction = $("#introduction").val();
	var rentOrSale = "";
	
	if(!totalPrice && !rental){
		alert("出租和出售不能同时为空");
		btn.removeClass("disabled");
		btn.val("立即发布");
		return false;
	}
	if(totalPrice && rental){
		rentOrSale = "不限";
	}else if(totalPrice){
		rentOrSale = "出售";
	}else{
		rentOrSale = "出租";
	}
	
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
		url: "${ctx}/user/cfck/json/add",
		data: {grossFloorArea:grossFloorArea,region:region,type:type,rental:rental,totalPrice:totalPrice,rentOrSale:rentOrSale,phoneNum:phoneNum,name:name,address:address,introduction:introduction,preImageUrl:preImageUrl,imgUrls:imgUrls},
		dataType: "json",
		success: function (data) {
			if(data.success){
				alert("发布成功");
				top.location = "${ctx}/user/cfck";
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
	        url:'${ctx}/user/cfck/uploadImg',
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