<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/x-handlebars-template" id="rep-template">
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
	
		<script>
		/*
		 *分页功能 
		 */
	  	var page = new Page({
	  			container:"#repInfo",
	  			template:"#rep-template",
	  			url:"${ctx}/mi/xf/page/"
	  	});
	  	page.init();	  	
	  	$("#search-rep").click(function(){
	  		var name = encodeURIComponent($("#searchbyname").val());
	  		page.setData({"name":name});
	  		page.init();
	  	});
		/*
		 * 返回
		 */
		$(".cancle").on("click",function(){
				window.location.href = "${ctx}/mi/info";
		});
		
		function template(id,data){
			var tpl = Handlebars.compile($(id).html());
			return tpl(data);
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
		        url:'${ctx}/mi/info/uploadImg',
		        data: formData,
		        async: true,
		        cache: false,
				dataType : "json",
		        contentType: false,
		        processData: false,
		        success: function (data) {
					if(data.success){
						var final_url = data.imgPath;
						$("[name='preImageUrl']").val(final_url);
						$(".control-img").find("img").attr("src",final_url);
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
		</script>