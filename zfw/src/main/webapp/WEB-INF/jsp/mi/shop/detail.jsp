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
						<div class="box-hd" onclick="openCloseDetail('js-shop-detail-container')">
							<h2>商铺信息</h2>
						</div>
						<%@include file="aeCommonBody.jsp" %>
					</div>
					<div class="box">
						<div class="box-hd" onclick="openCloseDetail('js-shop-image-container')" >
							<h2>商铺图片信息</h2>
						</div>
						<%@include file="shopPhotoList.jsp" %>
					</div>
					<div class="box">
						<div class="box-hd" onclick="openCloseDetail('js-shop-panos-container')">
							<h2>商铺全景信息</h2>
						</div>
						<%@include file="panoList.jsp" %>
					</div>
					<div class="form-actions">
			  			<button type="reset" class="btn cancle" >返回</button>
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
	<script type="text/x-handlebars" id="photo-template">
		{{#each this}}
		<tr>
			<td><img src="{{contentUrl}}" style="width:100px"></td>
			<td>{{name}}</td>
			<td>{{description}}</td>
		</tr>
		{{/each}}
	</script>
	<script type="text/x-handlebars" id="pano-template">
		{{#each this}}
		<tr>
			<td><img src="{{preImageUrl}}" style="width:100px"></td>
			<td>{{name}}</td>
			<td>{{contentUrl}}</td>
			<td>{{description}}</td>
		</tr>
		{{/each}}
	
	</script>		
	</body>
	<%@include file="aeCommonBottom.jsp" %>
	<script>
	openCloseDetail('js-shop-detail-container');
	$(".js-edit-operation").hide();
	function initShopData(){
		var id = $("#shopId").val();
		var getDataUrl = "${ctx}/mi/shop/"+id;
		$.ajax({
			type:"get",
			url:getDataUrl,
			async:true,
			dataType:"json",
			success:function(data){
				if(data){
					var shop = data.shop;
					for(var i in shop){
						var ele = $("[name="+i+"]");
						if(ele[0]){
							ele.val(shop[i]);
							ele.attr("readonly","readonly");
							ele.attr("disabled","disabled");
						}
					}
					if(!!shop.preImageUrl){
						$(".control-user-img").attr("src",shop.preImageUrl)
					}
				}
			},
			error:function(){
				alert("获取商铺信息失败");
			}
		});
	}
		//initShopData();
		$("#submit").hide();
		$(".uploader").hide();
	</script>
</html>