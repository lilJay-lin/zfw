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
	        url:'${ctx}/mi/cfck/uploadImg',
	        data: formData,
	        async: true,
	        cache: false,
			dataType : "json",
	        contentType: false,
	        processData: false,
	        success: function (data) {
				if(data.success){
					var final_url = data.imgPath;
					$("input[name='preImageUrl']").val(final_url);
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
	
	function getSaveData(){
		var warehouse = {
			id:"",
			name:"",
			region:"",
			phoneNum:"",
			rental:"",
			totalPrice:"",
			grossFloorArea:"",
			decorationStatus:"",
			address:"",
			introduction:"",
			rentOrSale:"",
			propertyFee:"",
			type:"",
			outOfDate:"",
			description:"",
			tags:"",
			priority:"",
			preImageUrl:""
		};
	   for(var i in warehouse){
	   		var value = $("[name="+i+"]").val();
	   		warehouse[i]=value;
	   }
	   return warehouse;
	}
	
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
			var url;
			if(inEdit){
				url = "${ctx}/mi/cfck/${warehouseId}";
			}else{
				url = "${ctx}/mi/cfck";
			}
		btn.attr("disabled","disabled");
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
		   				window.location.href="${ctx}/mi/cfck";
		   			}
		   		}
		   	},
		   	error:function(){
				if(inEdit){
			   		alert("更新厂房仓库失败!");
				}else{
			   		alert("新增厂房仓库失败!");
				}
		   	},
		   	complete:function(){
		   		btn.removeAttr("disabled");
		   	}
		   });
		}else{
			$("body").scrollTop(0);
		}
	});
</script>