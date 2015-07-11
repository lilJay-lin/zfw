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
						<div class="box-hd" onclick="openCloseREPDetail('js-rep-detail-container')">
							<h2>楼盘详情</h2>
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
		
		<script type="text/x-handlebars-template" id="relation-user-template">
			{{#this}}
			<li>
				<div class="relation-info">
					<span>
						{{name}}
					</span>
					<a class="btn btn-rel btn-remove-relation"  data-id="{{id}}">
						<i class="icon-remove"></i>
					</a>
				</div>
			</li>
			{{/this}}
		</script>
		<script type="text/x-handlebars-template" id="relation-info-template">
			{{#this}}
			<li>
				<div class="relation-info">
					<span>
						{{name}}
					</span>
					<a class="btn btn-rel btn-remove-relation"  data-id="{{id}}">
						<i class="icon-remove"></i>
					</a>
				</div>
			</li>
			{{/this}}
		</script>
	<script>
	
	function template(id,data){
		var tpl = Handlebars.compile($(id).html());
		return tpl(data);
	}
	
	$(".js-rep-detail-container").show();
	initREPData();
	$("#submit").hide();
	$("#cancle").on("click",function(){
		window.location.href = "${ctx}/mi/xf";
	});
	$(".uploader").hide();
	$(".js-relation-select-box").hide();
	
		function initREPData(){
			var id = $("#repId").val();
			var getRepUrl = "${ctx}/mi/xf/"+id;
			$.ajax({
				type:"get",
				url:getRepUrl,
				async:true,
				dataType:"json",
				success:function(data){
					if(data){
						var rep = data.rep;
						var relationUserList = data.relationUserList;
						var relationInfoList = data.relationInfoList;
						for(var i in rep){
							var ele = $("[name="+i+"]");
							if(ele[0]){
								ele.attr("readonly","readonly");
								ele.attr("disabled","disabled");
							}
							if(i=="buildingType"){
								var buildingTypes = rep["buildingType"];
								if(buildingTypes){
									var bts = buildingTypes.split(",");
									$("[name=buildingType]").each(function(){
										if($.inArray($(this).val(),bts)>-1){
											this.checked = true;
										}
									});
								}
								continue;
							}
							if(i=="greenRate" || i=="floorAreaRatio" || i=="propertyFee"){
								var num = Number(rep[i]);
								if(num){
									$("[name="+i+"]").val(Math.round(num*100)/100);
								}
								continue;
							}
							if(ele[0]){
								ele.val(rep[i]);
							}
						}
						var preImageUrl = rep["preImageUrl"];
						if(preImageUrl){
							$(".control-img").find("img").attr("src",preImageUrl);
						}
						var lon = rep["longitude"];
						var lat = rep["latitude"];
						if(lon && lat){
							setTimeout(function(){
								var tp = new BMap.Point(lon,lat);
								marker.setPosition(tp);
								map.centerAndZoom(tp, 15);
							}, 1000);
						}
						if(relationUserList){
							$("#user-relation").append(template("#relation-user-template",relationUserList));
						}
						if(relationInfoList){
							$("#info-relation").append(template("#relation-info-template",relationInfoList));
						}
					}
				},
				error:function(){
					alert("获取楼盘信息失败");
				}
			});
		}
	</script>
</html>