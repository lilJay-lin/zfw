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
						<div class="box-hd" onclick="openCloseDetail('js-nl-detail-container')">
							<h2>商铺信息</h2>
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
	/*
	 * 返回
	 */
	$("#detail-cancle").on("click",function(){
			window.location.href = "${ctx}/mi/nl";
	});
	function initnlData(){
		var id = $("#nlId").val();
		var getDataUrl = "${ctx}/mi/nl/"+id;
		$.ajax({
			type:"get",
			url:getDataUrl,
			async:true,
			dataType:"json",
			success:function(data){
				if(data){
					var nl = data.nl;
					for(var i in nl){
						var ele = $("[name="+i+"]");
						if(ele[0]){
							ele.val(nl[i]);
							ele.attr("readonly","readonly");
							ele.attr("disabled","disabled");
						}
					}
				}
			},
			error:function(){
				alert("获取电商团购信息失败");
			}
		});
	}
	initnlData();
		$("#submit").hide();
		$(".uploader").hide();
	</script>
</html>