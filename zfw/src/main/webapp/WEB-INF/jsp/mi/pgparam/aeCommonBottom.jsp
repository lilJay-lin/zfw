<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
		<script>
	  	
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
		        url:'${ctx}/mi/pgparam/uploadImg',
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
		function getAPData(){
			var ap = {
				id:"",
				name:"",
				value:"",
				description:"",
				assessmentItemId:""
			};
		   for(var i in ap){
		   		var value = $("[name="+i+"]").val();
		   		ap[i]=value;
		   }
		   return ap;
		}

		$("#submit").click(function(){
			var btn=$(this);
			var form = $(".form");
			var res = form.validate();
			if(res){
				var url;
				var ap = getAPData();
				if(inEdit){
					url = "${ctx}/mi/pgparam/"+$("#apId").val();
				}else{
					url = "${ctx}/mi/pgparam/add";
				}
			btn.attr("disabled","disabled");
			btn.addClass("disabled");
			   $.ajax({
			   	type:"POST",
			   	url:url,
			   	async:true,
			   	data:ap,
			   	dataType:"json",
			   	success:function(data){
			   		if(data){
			   			if(!data.success){
			   				var name = data.field;
			   				if(name){
			   					var p = form.find("[name='"+name+"']");
			   					p.length>0&&(p.focus(),showerror(p,data.msg));
			   				}else{
			   					alert(data.msg);
			   				}
			   			}else{
			   				alert(data.msg);
// 			   				window.location.href="${ctx}/mi/pgitem/${aiId}/edit";
							window.history.back(-1);
			   			}
			   		}
			   	},
			   	error:function(){
					if(inEdit){
				   		alert("修改评估参数失败!");
					}else{
				   		alert("新增评估参数失败!");
					}
			   	},
			   	complete:function(){
					btn.removeAttr("disabled");
					btn.removeClass("disabled");
			   	}
			   });
			}else{
				$("body").scrollTop(0);
			}
		});
		</script>