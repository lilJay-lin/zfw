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
							<h2>厂房/仓库图片详情</h2>
						</div>
						<%@include file="aeCommonBody.jsp" %>
					</div>
					<div class="form-actions">
					  <button type="reset" class="btn" id="detail-cancle">返回</button>
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
		/*
		 * 返回
		 */
		$("#detail-cancle").on("click",function(){
				window.location.href = "${ctx}/mi/cfck/${warehouseId}/edit";
		});
	
		function initImageData(){
			var id = $("#imageId").val();
			var getImageUrl = "${ctx}/mi/cfckphoto/"+id;
			$.ajax({
				type:"get",
				url:getImageUrl,
				async:true,
				dataType:"json",
				success:function(data){
					if(data){
						var image = data.image;
						for(var i in image){
							var ele = $("[name="+i+"]");
							if(ele[0]){
								ele.val(image[i]);
								ele.attr("readonly","readonly");
								ele.attr("disabled","disabled");
							}
						}
						var contentUrl = image["contentUrl"];
						if(contentUrl){
							$(".control-user-img").attr("src",contentUrl);
						}
					}
				},
				error:function(){
					alert("获取厂房/仓库图片信息失败");
				}
			});
		}
		initImageData();
		$("#submit").hide();
		$("#cancle").on("click",function(){
			window.location.href = "${ctx}/mi/cfck/${warehouseId}/edit";
		});
		$(".uploader").hide();
	</script>
</html>