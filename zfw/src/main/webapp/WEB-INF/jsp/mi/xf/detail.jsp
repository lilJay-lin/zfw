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
					<div class="box">
						<div class="box-hd" onclick="openCloseREPDetail('js-rep-ht-container')">
							<h2>楼盘户型</h2>
						</div>
						<%@include file="htList.jsp" %>
					</div>
					<div class="box">
						<div class="box-hd" onclick="openCloseREPDetail('js-rep-photos-container')">
							<h2>楼盘相册</h2>
						</div>
						<%@include file="photoList.jsp" %>
					</div>
					<div class="box">
						<div class="box-hd" onclick="openCloseREPDetail('js-rep-panos-container')">
							<h2>楼盘全景</h2>
						</div>
						<%@include file="panoList.jsp" %>
					</div>
					<div class="box">
						<div class="box-hd" onclick="openCloseREPDetail('js-rep-videos-container')">
							<h2>楼盘视频</h2>
						</div>
						<%@include file="videoList.jsp" %>
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
	
// 	$(".js-rep-detail-container").show();
// 	initREPData();
	openCloseREPDetail("js-rep-detail-container");
	
	$("#submit").hide();
	$("#cancle").on("click",function(){
		window.location.href = "${ctx}/mi/xf";
	});
	$(".uploader").hide();
	$(".js-relation-select-box").hide();
	$(".js-edit-content").hide();
	function openCloseREPDetail(clazz){
		var ele = $("."+clazz);
		if(ele.is(':hidden')){
			ele.show();
		}else{
			ele.hide();
		}
		if(!ele.attr("first")){
			ele.attr("first",2);
			if("js-rep-detail-container"==clazz){
				initREPData();
			}else if("js-rep-panos-container"==clazz){
				panoPage.init();
			}else if("js-rep-photos-container"==clazz){
			  	photoPage.init();
			}else if("js-rep-videos-container"==clazz){
			  	videoPage.init();
			}else if("js-rep-ht-container"==clazz){
			  	htPage.init();
			}
		}
	}
	
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
	<script type="text/x-handlebars" id="pano-template">
			{{#each this}}
			<tr>
				<td>
					<input type="checkbox" value="{{id}}"/>
				</td>
				<td><img src="{{preImageUrl}}" style="width:100px"></td>
				<td>{{name}}</td>
				<td>{{contentUrl}}</td>
				<td>{{description}}</td>
				<td>
					<a class="btn btn-info" href="${ctx}/mi/${repId}/xfpano/{{id}}/detail">
						<i class="icon-zoom-in "></i>                                            
					</a>
				</td>
			</tr>
			{{/each}}
		</script>
		<script type="text/x-handlebars" id="photo-template">
			{{#each this}}
			<tr>
				<td>
					<input type="checkbox" value="{{id}}"/>
				</td>
				<td><img src="{{contentUrl}}" style="width:100px"></td>
				<td>{{name}}</td>
				<td>{{type}}</td>
				<td>{{description}}</td>
				<td>
					<a class="btn btn-info" href="${ctx}/mi/${repId}/xfphoto/{{id}}/detail">
						<i class="icon-zoom-in "></i>                                            
					</a>
				</td>
			</tr>
			{{/each}}
		</script>
		<script type="text/x-handlebars" id="video-template">
			{{#each this}}
			<tr>
				<td>
					<input type="checkbox" value="{{id}}"/>
				</td>
				<td><img src="{{preImageUrl}}" style="width:100px"></td>
				<td>{{name}}</td>
				<td>{{contentUrl}}</td>
				<td>{{description}}</td>
				<td>
					<a class="btn btn-info" href="${ctx}/mi/${repId}/xfvideo/{{id}}/detail">
						<i class="icon-zoom-in "></i>                                            
					</a>
				</td>
			</tr>
			{{/each}}
		</script>
		<script type="text/x-handlebars" id="ht-template">
			{{#each this}}
			<tr>
				<td>
					<input type="checkbox" value="{{id}}"/>
				</td>
				<td>{{name}}</td>
				<td>{{description}}</td>
				<td>{{priority}}</td>
				<td>{{saleStatus}}</td>
				<td>{{averagePrice}}</td>
				<td>{{updateDate}}</td>
				<td>
					<a class="btn btn-info" href="${ctx}/mi/hx/{{id}}/detail">
						<i class="icon-zoom-in "></i>                                            
					</a>
				</td>
			</tr>
			{{/each}}
		</script>
</html>