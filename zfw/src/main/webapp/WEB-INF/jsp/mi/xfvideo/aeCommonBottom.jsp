<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	
		<script>
		/*
		 * 返回
		 */
		$("#cancle").on("click",function(){
			if(window.confirm("确定不保存返回？")){
// 				window.location.href = "${ctx}/mi/xf/${repId}/edit";
				window.history.back(-1);
			}
		});
		
		
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

		$(":file").change(function(){
			var errorStr = checkImgType(this);
			if(errorStr){
				alert(errorStr);
				return;
			}
			var formData = new FormData($("#uploadForm")[0]);	
		    $.ajax({
		        type:'POST',
		        url:'${ctx}/mi/xfvideo/uploadImg',
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
						$(".control-img").find("img").attr("src",final_url);
					}else{
						alert(data.msg);
					}
		        },
		        error: function (data) {
					alert("上传失败");
		        }
		    });
		});
		
		function getVideoData(){
			var video = {
				id:"",
				name:"",
				description:"",
				contentUrl:"",
				preImageUrl:"",
				realEstateProjectId:""
			};
		   for(var i in video){
		   		var value = $("[name="+i+"]").val();
		   		video[i]=value;
		   }
		   return video;
		}
		</script>