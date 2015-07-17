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
							<h2>新增用户</h2>
						</div>
						<div class="box-cnt">
							<div class="form">
								<fieldset>
									<input type="hidden" name="publicExponent" id="publicExponent" value="${publicExponent }" />
									<input type="hidden" name="modulus" id="modulus" value="${modulus }"  />
									<div class="control-group">
										<label class="control-label">图像</label>
										<div class="control control-img-box">
											<img class="control-user-img" />
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">姓名</label>
										<div class="control">
											<input type="text" name="name" max="16" min="4" maxlength="16" error="用户名长度4~16只能包含小写字母、数字、下划线并以小写字母开头" 
					patterns = "^[a-z]([a-zA-Z0-9_]){3,15}$"  placeholder="输入用户名"  />
											<span class="help-inline"></span>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">密码</label>
										<div class="control">
											<input type="text"  name="pwd" id="pwd" max="32" min="6"  error="密码长度6~32只能包含大小写字母、数字、部分特殊符号 !@#$%^&*()" 
					require="require" require_msg ="密码不能为空" patterns = "^[A-Za-z0-9\!\@\#\$\%\^\&\*\(\)]*$" placeholder="输入密码"  />
											<input type="hidden" name="password" id="password"/>
											<span class="help-inline"></span>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">邮箱</label>
										<div class="control">
											<input type="text" name="email"  pattern="^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+$" />
											<span class="help-inline"></span>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">手机号码</label>
										<div class="control">
											<input type="text" name="phoneNum"   patterns="^1[0-9]{10}$"  error="手机号码格式不正确"/>
											<span class="help-inline"></span>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">状态</label>
										<div class="control">
											<select name="locked" disabled="disabled">
												<option value="false" checked>正常</option>
												<option value="true">锁定</option>
											</select>
										</div>
									</div>
									<form enctype="multipart/form-data" method="post" id="uploadForm">
									<div class="control-group">
										<label class="control-label">上传图像</label>
										<div class="control">
											<div class="uploader">
												<input type="hidden" name="headImgUrl" />
												<input type="file" name="theFile" accept="image/*"/>
												<span class="filename" style="-webkit-user-select: none;">没有选择文件...</span>
												<span class="action" style="-webkit-user-select: none;">选择</span>
											</div>
										</div>
										<div class="control uploader-loading" style="display: none;">
											<div class="loading">
												<img src="${ctx}/assets/img/loading.gif"/>
											</div>
										</div>
									</div>
									</form>
									<div class="control-group">
										<label class="control-label">描述</label>
										<div class="control">
											<textarea name="description"></textarea>
										</div>
									</div>
									
									<div class="box box-inline">
										<div class="box-hd">
											<h2>添加关联关系</h2>
										</div>
										<div class="box-cnt">
											<div class="datatable" id="roleinfo">
												<div class="datatabls-filter">
													<!--搜索：-->
													<input type="text" id="searchbyname"/>
													<input type="button" class="btn btn-primary" value="搜索" id="search-role"/>
												</div>
												<table class="datatable-table">
													<thead>
														<tr>
															<th>角色</th>
															<th>描述</th>
															<th class="operation">操作</th>
														</tr>
													</thead>
													<tbody class="page-data-list">
														
													</tbody>
												</table>
												<div class="datatable-footer">
													<div class="datatable-info">
														<div>共32条 当前展示第1条到第10条</div>
													</div>
													<div class="center">
														<div class="datatable-pagination">
															<ul class="pagination">
																
															</ul>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
									
									<div class="box box-inline">
										<div class="box-hd">
											<h2>已选关联关系</h2>
										</div>
										<div class="box-cnt">
											<ul class="relation">
												
											</ul>
											<div class="clearfix"></div>
										</div>
									</div>
									
									<div class="form-actions">
									  <button type="button" class="btn btn-primary" id="submit">保存</button>
									  <button type="reset" class="btn" id="cancle">返回</button>
									</div>
								</fieldset>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- 右边内容区域结束     -->
			
			
			
			<!-- 左边侧边栏区域开始     -->
			<div class="slider skin">
				<div class="clearfix">&nbsp</div>
				<div class="clearfix">&nbsp</div>
				<%@include file="../inc/left.jsp" %>
			</div>
			
			<!-- 左边侧边栏区域结束     -->
		</div>
		
		<!-- 底部区域开始     -->
		<div class="footer">
			<p>@copyright-------------------</p>
		</div>
		<!-- 底部区域结束     -->
		<script type="text/x-handlebars-template" id="role-template">
		{{#each this}}
		<tr>
			<td>{{name}}</td>
			<td>{{desciption}}</td>
			<td>
				<a class="btn btn-info btn-add-relation" href="javascript:;" data-id="{{id}}" data-name="{{name}}">
					<i class="icon-plus "></i>                                            
				</a>
			</td>
		</tr>
		{{/each}}
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
	</body>
	<%
	    request.setAttribute("encrypUrl", request.getContextPath()
						+ "/assets/tools/encryption");
	%>
	<script type="text/javascript" src="${encrypUrl}/RSA.js"></script>
	<script type="text/javascript" src="${encrypUrl}/BigInt.js"></script>
	<script type="text/javascript" src="${encrypUrl}/Barrett.js"></script>
	<script type="text/javascript" src="${encrypUrl}/md5.js"></script>
	<script>
		function RSAEncrypt(pwd) {
			var thisPwd = hex_md5(pwd);
			setMaxDigits(130);
			var publicExponent = document.getElementById("publicExponent").value;
			var modulus = document.getElementById("modulus").value;
			var key = new RSAKeyPair(publicExponent, "", modulus);
			result = encodeURIComponent(encryptedString(key, encodeURIComponent(thisPwd)));
			return result;
		}
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
		/*
		 * 
		 * 新增关联
		 * 
		 */
		var addRelation = [];
		var delRelation = [];
		$(".datatable-table").on("click",".btn-add-relation",function(){
			var id = $(this).data("id");
			var name = $(this).data("name");
			if($.inArray(id,addRelation)>-1){
				return;
			}
			addRelation.push(id);
			$(".relation").append(template("#relation-info-template",{"id":id,"name":name}))
			return false;
		})
		
		/*
		 * 删除关联
		 */
		$(".relation").on("click",".btn-remove-relation",function(){
			var id = $(this).data("id");
			var idx = $.inArray(id,addRelation);
			if(idx>-1){
				addRelation.splice(idx,1)
			}
			$(this).parent().parent().remove();
			return false;
		})
		
		
		function template(id,data){
			var tpl = Handlebars.compile($(id).html());
			return tpl(data);
		}
		
		/*
		 *分页功能 
		 */
	  	var page = new Page({
	  			container:"#roleinfo",
	  			template:"#role-template",
	  			url:"${ctx}/mi/roles/page/",
	  			data:{"all":true}
	  	})
	  	page.init();	  	
	  	$("#search-role").click(function(){
	  		var name = encodeURIComponent($("#searchbyname").val());
	  		page.setData({"name":name})
	  		page.init();
	  	})
		
		/*
		 * 保存
		 * 
		 */
		/*
		 * 图片上传
		 */
		var uploading = !1;
		$(":file").change(function(){
			if(!!uploading){
				alert("图像正在上传，请稍后..");
				return ;
			}
			var errorStr = checkImgType(this);
			if(errorStr){
				alert(errorStr);
				return;
			}
			var self = $(this);
//			self.attr("disabled","disabled");
			var formData = new FormData($("#uploadForm")[0]);
			$(".uploader-loading").show();
			uploading = !0;
		    $.ajax({
		        type:'post',
		        url:'${ctx}/user/uploadHeadImg',
		        data: formData,
		        async: true,
		        cache: false,
				dataType : "json",
		        contentType: false,
		        processData: false,
		        success: function (data) {
					if(data.success){
						var final_url = data.imgPath;
						$("input[name='headImgUrl']").val(final_url);
						$(".control-user-img").attr("src",final_url);
//						resetHeadImgUrl(final_url);
					}else{
						alert(data.msg);
					}
		        },
		        error: function (data) {
					alert("上传失败");
		        },
		        complete:function(){
//					self.attr("disabled","");
					uploading =!1;
					$(".uploader-loading").hide();
		        }
		    });
		})
		/*
		 * 处理密码，密码加密，设置到password
		 */
		$("#pwd").change(function(){
			$("#password").val(RSAEncrypt($(this).val()))
		})
		$("#submit").click(function(){
			var btn=$(this);
			var form = $(".form");
			if(uploading){
				alert("图像正在上传，请稍后..");
				return ;
			}
			var res = form.validate();
			if(res){
				var user = {
					name:"",
					email:"",
					phoneNum:"",
					headImgUrl:"",
					password:"",
					locked:false,
					description:"",
					delFlag:false
				}
			   for(var i in user){
			   		form.find("[name="+i+"]").length>0&&(user[i]=form.find("[name="+i+"]").val());
			   }
			   
			   var roles = addRelation.join("/");
				var publicExponent = document.getElementById("publicExponent").value;
				var modulus = document.getElementById("modulus").value;
			   var data = {"roles":roles,"publicExponent":publicExponent,"modulus":modulus};
			   var url = "${ctx}/mi/user";
			btn.attr("disabled","disabled");
			   $.ajax({
			   	type:"POST",
			   	url:url,
			   	async:true,
			   	data:$.extend(data,user),
			   	dataType:"json",
			   	success:function(data){
			   		if(data){
			   			if(!data.success){
			   				var name = data.field;
			   				if(name){
			   					var p = form.find("input[name='"+name+"']");
			   					p.length>0&&(p.focus(),p.next(".help-inline").html(data.msg),p.next(".help-inline").show())
			   				}else{
			   					alert(data.msg)
			   				}
			   				btn.removeAttr("disabled");
							$("body").scrollTop(0);
			   			}else{
			   				alert(data.msg)
			   				window.location.href="${ctx}/mi/users";
			   			}
			   		}
			   	},
			   	error:function(){
					btn.prop("disabled","false");
					btn.removeClass("disabled");
			   		alert("新增用户失败!");
			   	}
			   });
			   
			}else{
				$("body").scrollTop(0);
			}
		})
		
		/*
		 * 返回
		 */
		$("#cancle").on("click",function(){
				window.location.href = "${ctx}/mi/users";
		})
	</script>
</html>
