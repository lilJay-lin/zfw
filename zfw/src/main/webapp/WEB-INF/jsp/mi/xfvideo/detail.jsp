<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%request.setAttribute("ctx", request.getContextPath());%>
<%@include file="../../inc/taglib.jsp" %>
<!DOCTYPE html>
<html>
	<head>
         <%@include file="../inc/header.jsp" %>
		<title>后台管理</title>
	</head>
	<body>
		
		<!-- 头部导航条开始     -->
		<%@include file="../inc/nav.jsp" %>
		<!-- 头部导航条结束     -->
		<div class="clearfix"></div>
		
		<div class="container">
			<!-- 右边内容区域开始     -->
			<div class="main skin">
				<div class="content">
					<div class="box">
						<div class="box-hd">
							<h2>楼盘视频详情</h2>
						</div>
						<%@include file="aeCommonBody.jsp" %>
					</div>
				</div>
			</div>
			<!-- 右边内容区域结束     -->
			
			<!-- 左边侧边栏区域开始     -->
			<div class="slider skin">
				<div class="clearfix">&nbsp;</div>
				<div class="clearfix">&nbsp;</div>
				<%@include file="../inc/left.jsp" %>
			</div>
			
			<!-- 左边侧边栏区域结束     -->
		</div>
		
		<!-- 底部区域开始     -->
		<%@include file="../inc/footer.jsp" %>
		<!-- 底部区域结束     -->
		
	</body>
	<script>
		
		function initVideoData(){
			var id = $("#videoId").val();
			var getVideoUrl = "${ctx}/mi/xfvideo/"+id;
			$.ajax({
				type:"get",
				url:getVideoUrl,
				async:true,
				dataType:"json",
				success:function(data){
					if(data){
						var video = data.video;
						for(var i in video){
							var ele = $("[name="+i+"]");
							if(ele[0]){
								ele.val(video[i]);
								ele.attr("readonly","readonly");
								ele.attr("disabled","disabled");
							}
						}
						var preImageUrl = video["preImageUrl"];
						if(preImageUrl){
							$(".control-img").find("img").attr("src",preImageUrl);
						}
					}
				},
				error:function(){
					alert("获取楼盘视频信息失败");
				}
			});
		}
		initVideoData();
		$("#submit").hide();
		$("#cancle").on("click",function(){
			window.location.href = "${ctx}/mi/xf/${repId}/edit";
		});
		$(".uploader").hide();
	</script>
</html>