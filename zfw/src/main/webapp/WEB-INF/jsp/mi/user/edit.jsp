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
							<h2>编辑用户</h2>
						</div>
						<div class="box-cnt">
							<div class="form" >
								<fieldset>
									<input type="hidden" name="publicExponent" id="publicExponent" value="${publicExponent }" />
									<input type="hidden" name="modulus" id="modulus" value="${modulus }"  />
									<input type="hidden" id="userid" name ="id" value="${userid}" />
									<input type="hidden" id="salt" name ="salt" value="${salt}" />
									<div class="control-group">
										<label class="control-label">图像</label>
										<div class="control control-img-box">
											<img class="control-user-img" />
										</div>
									</div>
									<form enctype="multipart/form-data" id="uploadForm">
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
												<img src="${ctx}/assets/img/loading.gif"  />
											</div>
										</div>
									</div>
									</form>
									<div class="control-group">
										<label class="control-label">姓名</label>
										<div class="control">
											<input type="text" name="name" max="32" min="4" maxlength="32" error="用户名长度4~32只能包含小写字母、数字、下划线并以小写字母开头" 
					patterns = "^[a-z]([a-zA-Z0-9_]){3,31}$"  placeholder="输入用户名"  />
											<span class="help-inline"></span>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">密码</label>
										<div class="control">
											<input type="text"  name="pwd" id="pwd" max="32" min="6"  maxlength="32"  error="密码长度6~32只能包含大小写字母、数字、部分特殊符号 !@#$%^&*()" 
					require="require" require_msg ="密码不能为空" patterns = "^[A-Za-z0-9!@#$%\^&\*\(\)]*$" placeholder="输入密码"  />
											<input type="hidden"  name="password" id="password"/>
											<span class="help-inline"></span>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">邮箱</label>
										<div class="control">
											<input type="text" name="email" placeholder="输入邮箱" max="32" maxlength="32" patterns="^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+$" />
											<span class="help-inline"></span>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">手机号码</label>
										<div class="control">
											<input type="text" name="phoneNum" placeholder="输入手机号码" max="11" maxlength="11"  patterns="^1[0-9]{10}$"  error="手机号码格式不正确"/>
											<span class="help-inline"></span>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">状态</label>
										<div class="control">
											<select name="locked">
												<option value="false" checked>正常</option>
												<option value="true">锁定</option>
											</select>
										</div>
									</div>
									
									<div class="control-group">
										<label class="control-label">描述</label>
										<div class="control">
											<textarea name="description" maxlength="200" max="200" error="描述最大长度为200"></textarea>
											<span class="help-inline"></span>
										</div>
									</div>
									
									<div class="box box-inline">
										<div class="box-hd">
											<h2>添加角色</h2>
										</div>
										<div class="box-cnt">
											<div class="datatable" id="roleinfo">
												<div class="datatabls-filter">
													<!--搜索：-->
													<input type="text" id="searchbyname"/>
													<input type="button" class="btn btn-primary" value="搜索" id="search-role" />
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
											<h2>已担当角色</h2>
										</div>
										<div class="box-cnt">
											<ul class="relation">
												
											</ul>
											<div class="clearfix"></div>
										</div>
									</div>
									
									<div class="form-actions">
									  <button type="button" class="btn btn-primary" id="submit">保存</button>
									  <button type="reset" class="btn cancle">返回</button>
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
		var originalRelation = [];
		var addRelation = [];
		var delRelation = [];
		$(".datatable-table").on("click",".btn-add-relation",function(){
			var id = $(this).data("id");
			var name = $(this).data("name");
			var didx = $.inArray(id,delRelation);//不在已删除
			var oidx = $.inArray(id,originalRelation);
			didx>-1&&delRelation.splice(didx,1);
			if(oidx>-1&&didx==-1||$.inArray(id,addRelation)>-1){//原来有，未删除不加
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
			var aidx = $.inArray(id,addRelation);//不在已删除
			aidx>-1&&addRelation.splice(aidx,1);
			if($.inArray(id,delRelation)==-1){//del中没有，则存入
				delRelation.push(id);
			}
			$(this).parent().parent().remove();
			return false;
		})
		
//		
//		function mixRelationOperation(originalRelation,addRelation,delRelation){
//			var add = [];
//			var del = [];
//			
//			for(var i in addRelation){
//				if(!originalRelation[i]){
//					add.push(i)
//				}
//			}
//			for(var i in delRelation){
//				if(originalRelation[i]){
//					del.push(i)
//				}
//			}
//			var addroles ="",
//			delroles = "";
//			if(add.length>0){
//				addroles = add.join("/")
//			}
//			if(del.length>0){
//				delroles = del.join("/")
//			}
//			return {"addroles":addroles,"delroles":delroles}
//		}
		
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
		 * 处理密码，密码加密，设置到password
		 */
		var resetPwd= false;
		$("#pwd").focus(function(){
			$(this).val("");
		})
		$("#pwd").change(function(){
			$("#password").val(RSAEncrypt($(this).val()));
			resetPwd = true;
		})
		
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
			var formData = new FormData($("#uploadForm")[0]);	
			$(".uploader-loading").show();
			uploading =!0;
		    $.ajax({
		        type:'POST',
		        url:'${ctx}/mi/user/uploadHeadImg',
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
					}else{
						alert(data.msg);
					}
		        },
		        error: function (data) {
					alert("上传失败");
		        },
		        complete:function(){
					uploading =!1;
					$(".uploader-loading").hide();
		        }
		    });
		});
		$("#submit").click(function(){
			var btn = $(this);
			var form = $(".form");
			if(uploading){
				alert("图像正在上传，请稍后..");
				return ;
			}
			var res = form.validate();
			var userid = $("#userid").val();
			if(res){
			
				var user = {
					id:userid,
					name:"",
					email:"",
					phoneNum:"",
					headImgUrl:"",
					password:"",
					locked:false,
					description:"",
					salt:""
				}
				
			   for(var i in user){
			   		var value = form.find("[name="+i+"]").val();
			   		user[i]=value;
			   }
			   if(user.name=="" && user.phoneNum =="" && user.email ==""){
			   		alert("姓名、邮箱和手机号码不能全部为空");
			   		return false;
			   }
			   var relation = {addroles:"",delroles:''};//mixRelationOperation(originalRelation,addRelation,delRelation);
			   if(addRelation.length>0){
			   		relation.addroles = addRelation.join("/")
			   }
			   if(delRelation.length>0){
			   		relation.delroles = delRelation.join("/")
			   }
				var publicExponent = document.getElementById("publicExponent").value;
				var modulus = document.getElementById("modulus").value;
			   var url = "${ctx}/mi/user/"+userid;
			   btn.attr("disabled","disabled");
			   btn.addClass("disabled");
			   $.ajax({
			   	type:"POST",
			   	url:url,
			   	async:true,
			   	dataType:"json",
			   	data:$.extend(relation,user,{"publicExponent":publicExponent,"modulus":modulus,"resetPwd":resetPwd}),
			   	success:function(data){
		   			var name = data.field;
		   			if(!data.success){
		   				if(name){
		   					var p = form.find("input[name='"+name+"']");
		   					p.length>0&&(p.focus(),p.next(".help-inline").html(data.msg),p.next(".help-inline").show())
		   				}else{
		   					alert(data.msg)
		   				}
						btn.prop("disabled","false");
						btn.removeClass("disabled");
						$("body").scrollTop(0);
		   			}else{
		   				alert(data.msg);
		   				window.location.href="${ctx}/mi/users";
			   		}
			   	},
			   	error:function(){
			   		alert("修改用户失败!");
			   	},
			   	complete:function(){
					btn.prop("disabled","");
					btn.removeClass("disabled");
			   	}
			   });
			   
			}else{
				$("body").scrollTop(0);
			}
		})
		
		
		/*
		 * 编辑页面，渲染页面数据
		 * 
		 */
		var id = $("#userid").val();
		var getUserUrl = "${ctx}/mi/user/"+id;
		$.ajax({
			type:"get",
			url:getUserUrl,
			async:true,
			dataType:"json",
			success:function(data){
				if(data){
					var user = data.user;
					var relationroles = data.relationroles;
					
					var form = $(".form");
					for(var i in user){
						var e = form.find("[name="+i+"]");
						e.length>0&&e.val(user[i]);
					}
					if(!!user.headImgUrl){
						$(".control-user-img").attr("src",user.headImgUrl)
					}
					if(relationroles){
						for(var i=0,l = relationroles.length;i<l;i++){
							originalRelation.push(relationroles[i].id);
						}
						$(".relation").append(template("#relation-info-template",relationroles))
					}
				}
			},
			error:function(){
				
			}
		});
		
		
		/*
		 * 返回
		 */
		$(".cancle").on("click",function(){
				window.location.href = "${ctx}/mi/users";
		})
	</script>
</html>
