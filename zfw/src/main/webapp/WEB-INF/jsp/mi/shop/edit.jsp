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
							<h2>编辑商铺</h2>
						</div>
						<%@include file="aeCommonBody.jsp" %>
					</div>
					<div class="box">
						<div class="box-hd" onclick="openCloseDetail('js-shop-image-container')">
							<h2>编辑商铺图片</h2>
						</div>
						<%@include file="shopPhotoList.jsp" %>
					</div>
					<div class="box">
						<div class="box-hd" onclick="openCloseDetail('js-shop-panos-container')">
							<h2>管理商铺全景</h2>
						</div>
						<%@include file="panoList.jsp" %>
					</div>
					<div class="form-actions">
					  <button type="button" class="btn btn-primary" id="submit">保存</button>
					  <button type="reset" class="btn" id="cancle">返回</button>
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
			<td>
				<input type="checkbox" value="{{id}}"/>
			</td>
			<td><img src="{{contentUrl}}" style="width:100px"></td>
			<td>{{name}}</td>
			<td>{{description}}</td>
			<td>
				<a class="btn btn-info" href="${ctx}/mi/${shopId}/spphoto/{{id}}/detail">
					<i class="icon-zoom-in "></i>                                            
				</a>
				<a class="btn btn-info" href="${ctx}/mi/${shopId}/spphoto/{{id}}/edit">
					<i class="icon-edit "></i>                                            
				</a>
				<a class="btn btn-danger" href="javascript:;" onclick="delPhoto(this,'{{id}}');return false;" data-id="{{id}}">
					<i class="icon-trash "></i> 
				</a>
			</td>
		</tr>
		{{/each}}
	
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
				<a class="btn btn-info" href="${ctx}/mi/${shopId}/sppano/{{id}}/detail">
					<i class="icon-zoom-in "></i>                                            
				</a>
				<a class="btn btn-info" href="${ctx}/mi/${shopId}/sppano/{{id}}/edit">
					<i class="icon-edit "></i>                                            
				</a>
				<a class="btn btn-danger" href="javascript:;" onclick="delPano(this,'{{id}}');return false;" data-id="{{id}}">
					<i class="icon-trash "></i> 
				</a>
			</td>
		</tr>
		{{/each}}
	
	</script>
	</body>
	<%@include file="aeCommonBottom.jsp" %>
	<script>
		$("#submit").click(function(){
			var btn=$(this);
			var form = $(".form");
			if(!!uploading){
				alert("图像正在上传，请稍后..");
				return ;
			}
			var res = form.validate();
			if(res){
				var data = getSaveData();
			   var url = "${ctx}/mi/shop/${shopId}";
			btn.attr("disabled","disabled");
			btn.addClass("disabled");
			   $.ajax({
			   	type:"POST",
			   	url:url,
			   	async:true,
			   	data:data,
			   	dataType:"json",
			   	success:function(data){
			   		if(data){
			   			if(!data.success){
			   				var name = data.field;
			   				if(name){
			   					var p = form.find("[name='"+name+"']");
			   					p.length>0&&(p.focus(),p.next(".help-inline").html(data.msg),p.next(".help-inline").show());
			   				}else{
			   					alert(data.msg);
			   				}
							$("body").scrollTop(0);
			   			}else{
			   				alert(data.msg);
//			   				window.location.href="${ctx}/mi/shop/${repId}/edit";
			   			}
			   		}
			   	},
			   	error:function(){
			   		btn.removeAttr("disabled");
			   		alert("新增商铺失败!");
			   	},
			   	complete:function(){
			   		btn.removeAttr("disabled");
			   	}
			   });
			}else{
				$("body").scrollTop(0);
			}
		});
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
							$("[name="+i+"]")[0]&&$("[name="+i+"]").val(shop[i]);
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
	</script>
</html>