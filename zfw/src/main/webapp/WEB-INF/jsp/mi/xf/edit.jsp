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
							<h2>编辑楼盘</h2>
						</div>
						<%@include file="aeCommonBody.jsp" %>
					</div>
					<div class="box">
						<div class="box-hd" onclick="openCloseREPDetail('js-rep-ht-container')">
							<h2>管理楼盘户型</h2>
						</div>
						<%@include file="htList.jsp" %>
					</div>
					<div class="box">
						<div class="box-hd" onclick="openCloseREPDetail('js-rep-photos-container')">
							<h2>管理楼盘相册</h2>
						</div>
						<%@include file="photoList.jsp" %>
					</div>
					<div class="box">
						<div class="box-hd" onclick="openCloseREPDetail('js-rep-panos-container')">
							<h2>管理楼盘全景</h2>
						</div>
						<%@include file="panoList.jsp" %>
					</div>
					<div class="box">
						<div class="box-hd" onclick="openCloseREPDetail('js-rep-videos-container')">
							<h2>管理楼盘视频</h2>
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
	<%@include file="aeCommonBottom.jsp" %>
	<script>
// 	$(".js-rep-detail-container").hide();
// 	$(".js-rep-ht-container").hide();
// 	$(".js-rep-photos-container").hide();
// 	$(".js-rep-panos-container").hide();
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
	
		$("#submit").click(function(){
			var btn=$(this);
			var form = $(".form");
			var res = form.validate();
			var repId = $("#repId").val();
			if(res){
				var rep = getREPData();
				var relation = {addUserRelations:addUserRelation.join("/"),delUserRelations:delUserRelation.join("/"),addInfoRelations:addInfoRelation.join("/"),delInfoRelations:delInfoRelation.join("/")};
			   var url = "${ctx}/mi/xf/"+repId;
			btn.attr("disabled","disabled");
			btn.addClass("disabled");
			   $.ajax({
			   	type:"POST",
			   	url:url,
			   	async:true,
			   	data:$.extend(relation,rep),
			   	dataType:"json",
			   	success:function(data){
			   		if(data){
			   			if(!data.success){
			   				var name = data.field;
			   				if(name){
			   					var p = form.find("input[name='"+name+"']");
			   					p.length>0&&(p.focus(),p.next(".help-inline").html(data.msg),p.next(".help-inline").show());
			   				}else{
			   					alert(data.msg);
			   				}
							btn.prop("disabled","false");
							btn.removeClass("disabled");
			   			}else{
			   				alert(data.msg);
			   				window.location.href="${ctx}/mi/xf";
			   			}
			   		}
			   	},
			   	error:function(){
					btn.prop("disabled","false");
					btn.removeClass("disabled");
			   		alert("修改楼盘失败!");
			   	}
			   });
			}else{
				$("body").scrollTop(0);
			}
		});
		function initREPData(){
			/*
			 * 编辑页面，渲染页面数据
			 * 
			 */
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
							if(i=="onSaleDate" || i=="onReadyDate"){
								$("[name="+i+"]").datepicker( "setDate", rep[i] );
								continue;
							}
							$("[name="+i+"]")[0]&&$("[name="+i+"]").val(rep[i]);
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
							for(var i=0,l = relationUserList.length;i<l;i++){
								originalUserRelation.push(relationUserList[i].id);
							}
							$("#user-relation").append(template("#relation-user-template",relationUserList));
						}
						if(relationInfoList){
							for(var i=0,l = relationInfoList.length;i<l;i++){
								originalInfoRelation.push(relationInfoList[i].id);
							}
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
					<a class="btn btn-info" href="${ctx}/mi/${repId}/xfpano/{{id}}/edit">
						<i class="icon-edit "></i>                                            
					</a>
					<a class="btn btn-danger" href="javascript:;" onclick="delPano(this,'{{id}}');return false;" data-id="{{id}}">
						<i class="icon-trash "></i> 
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
					<a class="btn btn-info" href="${ctx}/mi/${repId}/xfphoto/{{id}}/edit">
						<i class="icon-edit "></i>                                            
					</a>
					<a class="btn btn-danger" href="javascript:;" onclick="delPhoto(this,'{{id}}');return false;" data-id="{{id}}">
						<i class="icon-trash "></i> 
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
					<a class="btn btn-info" href="${ctx}/mi/${repId}/xfvideo/{{id}}/edit">
						<i class="icon-edit "></i>                                            
					</a>
					<a class="btn btn-danger" href="javascript:;" onclick="delVideo(this,'{{id}}');return false;" data-id="{{id}}">
						<i class="icon-trash "></i> 
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
					<a class="btn btn-info" href="${ctx}/mi/hx/{{id}}/edit">
						<i class="icon-edit "></i>                                            
					</a>
					<a class="btn btn-danger" href="javascript:;" onclick="delHt(this,'{{id}}');return false;" data-id="{{id}}">
						<i class="icon-trash "></i> 
					</a>
				</td>
			</tr>
			{{/each}}
		</script>
</html>