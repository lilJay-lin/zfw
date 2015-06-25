<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../inc/top.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@include file="../inc/header.jsp" %>
</head>
<body>
	<div class="main whitebg">
		<header>
			<div class="left">
				<a href="javascript:void(0);" onclick="history.back(-1);"
					class="back"><i></i></a>
			</div>
			<div class="cent">个人信息</div>
		</header>
		<div class="mf-id">
			<div class="userhead clearfix">
				<div class="flol">头像</div>
				<div id="userphoto" class="flor serImg"
					style="margin-left: 92px; width: 45px; height: 45px; background: url(${headImgUrl}) no-repeat left center; background-size: 45px 45px;">
					<form id="uploadForm" method= "post" enctype ="multipart/form-data">
					
					<input id="commit_contract" class="multi_preview" type="file" name = "theFile"
						accept="image/*"
						style="background: transparent; -webkit-appearance: none; border: 0; opacity: 0; filter: alpha(opacity = 0);">
					</form>
				</div>
			</div>

			<div class="content clearfix">
				<div class="flol">手机认证</div>
				<div class="flor f14 f999 name"><shiro:principal></shiro:principal></div>
			</div>

			<div class="content clearfix">
				<div class="flol">登录密码</div>
				<a href="${ctx }/user/resetPwd" class="flor">修改</a>
			</div>

			<a href="${ctx }/user/logout"
				class="formbtn02">退 出 登 录</a>
		</div>
	</div>
</body>
<%@include file="../inc/bottom.jsp" %>
<script>
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
		$(".serImg").attr("style","margin-left:92px;width:45px; height:45px; background:url(${ctx}/assets/img/loading.gif) no-repeat left center;background-size:30px 30px;");
		$(".multi_preview").attr("readonly","readonly");
		
		var formData = new FormData($("#uploadForm")[0]);	
	    $.ajax({
	        type:'POST',
	        url:'${ctx}/user/uploadHeadImg',
	        data: formData,
	        async: false,
	        cache: false,
			dataType : "json",
	        contentType: false,
	        processData: false,
	        success: function (data) {
				$(".multi_preview").removeAttr("readonly");
				if(data.success){
					var final_url = data.imgPath;	
					$(".serImg").attr("style","margin-left:92px;width:45px; height:45px; background:url("+final_url+") no-repeat left center;background-size:45px 45px;");
					resetHeadImgUrl(final_url);
				}else{
					alert(data.msg);
				}
	        },
	        error: function (data) {
				alert("上传失败");
	        }
	    });
	});
	}); 
	
function resetHeadImgUrl(str){
var url = "${ctx}/user/resetHeadImgUrl";
	$.ajax({
		type : "POST",
		async : true,
		url : url,
		data : {
			headImgUrl : str
		},
		dataType : "json",
		timeout : 10000,
		success : function(data) {
			if(data.success){
				alert("上传头像成功");
			}else{
				alert(data.msg);
			}
		},
		error : function(data){
			alert("上传头像失败");
		}
	});
}	
</script>
</html>