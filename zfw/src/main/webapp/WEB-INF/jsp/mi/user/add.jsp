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
							<form class="form">
								<fieldset>
									<input type="hidden" name="publicExponent" id="publicExponent" value="${publicExponent }" />
									<input type="hidden" name="modulus" id="modulus" value="${modulus }"  />
									<div class="control-group">
										<label class="control-label">姓名</label>
										<div class="control error">
											<input type="text" name="name" max="16" min="4" maxlength="16" error="用户名长度4~16只能包含小写字母、数字、下划线并以小写字母开头" 
					patterns = "^[a-z]([a-zA-Z0-9_]){3,15}$" require="require" require_msg ="用户名不能为空"  placeholder="输入用户名"  />
											<span class="help-inline"></span>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">密码</label>
										<div class="control error">
											<input type="text"  name="pwd" id="pwd" max="32" min="6"  error="密码长度6~32只能包含大小写字母、数字、部分特殊符号 !@#$%^&*()" 
					require="require" require_msg ="密码不能为空" patterns = "^[A-Za-z0-9\!\@\#\$\%\^\&\*\(\)]*$" placeholder="输入密码"  />
											<input type="hidden" name="password" id="password"/>
											<span class="help-inline"></span>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">邮箱</label>
										<div class="control error">
											<input type="text" name="email" require="require" requrie_msg = "邮箱不能为空" error="邮箱格式不正确" pattern="^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+$" />
											<span class="help-inline"></span>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">手机号码</label>
										<div class="control error">
											<input type="text" name="phoneNum"  require="require"  patterns="^1[0-9]{10}$"  error="手机号码格式不正确"/>
											<span class="help-inline"></span>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">状态</label>
										<div class="control error">
											<select name="locked" disabled="disabled">
												<option value="false" checked>正常</option>
												<option value="true">锁定</option>
											</select>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">上传图片</label>
										<div class="control error">
											<div class="uploader">
												<input type="file" name="headImgUrl"/>
												<span class="filename" style="-webkit-user-select: none;">没有选择文件...</span>
												<span class="action" style="-webkit-user-select: none;">选择</span>
											</div>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">描述</label>
										<div class="control error">
											<textarea name="description">
												
											</textarea>
										</div>
									</div>
									
									<div class="box box-inline">
										<div class="box-hd">
											<h2>添加关联关系</h2>
										</div>
										<div class="box-cnt">
											<div class="datatable">
												<div class="datatabls-filter">
													<label>
														<!--搜索：-->
														<input type="text" />
														<input type="button" class="btn" value="搜索" />
													</label>
												</div>
												<table class="datatable-table">
													<thead>
														<tr>
															<th>角色</th>
															<th>描述</th>
															<th>操作</th>
														</tr>
													</thead>
													<tbody id="rolelist">
														
													</tbody>
												</table>
												<div class="datatable-footer">
													<div class="datatable-info">
														<div>共32条 当前展示第1条到第10条</div>
													</div>
													<div class="center">
														<div class="datatable-pagination">
															<ul id="pagination">
																
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
							</form>
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
		function getPage(index){
			var p = index || 1;
			var url = "${ctx}/mi/roles/page/"+p;
			$.ajax({
				type:"GET",
				url:url,
				dataType:"json",
				async:true,
//				data:{pagesize:"2"},
				success:function(data){
					var items = data.items;
					var pageinfo = data.pageinfo;
					$("#rolelist").html(template("#role-template",items));
					pagination(pageinfo);
//					$("#datatable-toolbar").html(template("#pagination_tpl",pageinfo))
				},
				error:function(){
					
				}
			});
		}
		function prePage(){
			var page = $("#pagination").data("curpage")||0;
			page = (page-1>0?page-1:1);
			getPage(page)
		}
		function nextPage(){
			var page = $("#pagination").data("curpage")||0;
			var totalpage = $("#pagination").data("totalpage")||0;
			page = (page+1>totalpage?totalpage:(page+1));
			getPage(page)
		}
		
		function pagination(pageinfo){
			var pagesize = pageinfo.pagesize||10;
			var start = (pageinfo.curpage-1)*pagesize + 1;
			var end = pageinfo.curpage*pagesize >=pageinfo.totalrows?pageinfo.totalrows:pageinfo.curpage*pagesize;
			$(".datatable-info").html("共"+pageinfo.totalrows+"条 当前展示第"+start+"条到第"+end+"条");
			var html = '<li class="prev disabled"><a href="javascript:;" onclick="prePage()">← 上一页</a>'
			for(var i=0;i<pageinfo.totalpage;i++){
				html+='<li '+(i+1==pageinfo.curpage?'class="active"':'')+'><a href="javascript:;" onclick="getPage('+(i+1)+')">'+(i+1)+'</a></li>';
			}
			html +='<li class="next"><a href="javascript:;" onclick="nextPage()">下一页 → </a></li>';
			$("#pagination").html(html);
			$("#pagination").data("curpage",pageinfo.curpage);
			$("#pagination").data("totalpage",pageinfo.totalpage);
		}
		getPage();
		
		/*
		 * 保存
		 * 
		 */
		/*
		 * 处理密码，密码加密，设置到password
		 */
		$("#pwd").change(function(){
			$("#password").val(RSAEncrypt($(this).val()))
		})
		$("#submit").click(function(){
			var btn=$(this);
			var form = $(".form");
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
			   		var value = form.find("input[name="+i+"]").val();
			   		if(!value){
			   			i == "locked"?(value=false):(value="")
			   		}
			   		user[i]=value;
			   }
			   
			   var roles = addRelation.join("/");
				var publicExponent = document.getElementById("publicExponent").value;
				var modulus = document.getElementById("modulus").value;
			   var data = {"roles":roles,"publicExponent":publicExponent,"modulus":modulus};
			   var url = "${ctx}/mi/user";
			btn.attr("disabled","disabled");
			btn.addClass("disabled");
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
							btn.prop("disabled","false");
							btn.removeClass("disabled");
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
			   
			}
		})
		
		/*
		 * 返回
		 */
		$("#cancle").on("click",function(){
			if(window.confirm("是否确定返回？")){
				window.location.href = "${ctx}/mi/users";
			}
		})
	</script>
</html>