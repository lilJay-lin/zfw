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

		$(":file").change(function(){
			var errorStr = checkImgType(this);
			if(errorStr){
				alert(errorStr);
				return;
			}
			var formData = new FormData($("#uploadForm")[0]);	
		    $.ajax({
		        type:'POST',
		        url:'${ctx}/mi/esf/uploadImg',
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
		function getSHHData(){
			var shh = {
				id:"",
				name:"",
				phoneNum:"",
				totalPrice:"",
				grossFloorArea:"",
				roomNum:"",
				hallNum:"",
				toiletNum:"",
				forward:"",
				curFloor:"",
				totalFloor:"",
				decorationStatus:"",
				address:"",
				introduction:"",
				outOfDate:"",
				tags:"",
				description:"",
				priority:"",
				preImageUrl:"",
				residenceCommunityId:"",
				residenceCommunityName:""
			};
		   for(var i in shh){
		   		var value = $("[name="+i+"]").val();
				if(i=="onSaleDate"){
					value = $("[name="+i+"]").datepicker( "getDate" );
				}
		   		shh[i]=value;
		   }
		   return shh;
		}

		$("#submit").click(function(){
			var btn=$(this);
			var form = $(".form");
			var res = form.validate();
			if(res){
				var url;
				var shh = getSHHData();
				if(inEdit){
					url = "${ctx}/mi/esf/"+$("#shhId").val();
				}else{
					url = "${ctx}/mi/esf/add";
				}
			btn.attr("disabled","disabled");
			btn.addClass("disabled");
			   $.ajax({
			   	type:"POST",
			   	url:url,
			   	async:true,
			   	data:shh,
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
			   			}else{
			   				alert(data.msg);
			   				window.location.href="${ctx}/mi/xq/${rcId}/edit";
			   			}
			   		}
			   	},
			   	error:function(){
					if(inEdit){
				   		alert("修改二手房失败!");
					}else{
				   		alert("新增二手房失败!");
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