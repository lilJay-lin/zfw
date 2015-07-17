<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<script src="${ctx }/assets/tools/jquery-ui/jquery-ui.min.js"></script>
  	<link rel="stylesheet" href="${ctx }/assets/tools/jquery-ui/jquery-ui.min.css">
		<script>


		/*
		 *百度地图
		 */
			marker.enableDragging();   
			map.addEventListener("click", setPosition);
			marker.addEventListener("dragend",getLocation);
			getLocation();
		  function getLocation(){
			    var point2 = marker.getPosition();
				$("#longitude").val(point2.lng);
				$("#latitude").val(point2.lat);
		  }
		  function setPosition(e){
			  marker.setPosition(e.point);
			  getLocation();
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
		        url:'${ctx}/mi/xq/uploadImg',
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
		        },
				complete:function(){
					uploading =!1;
					$(".uploader-loading").hide();
				}
		    });
		});

		function getRCData(){
			var rc = {
				id:"",
				name:"",
				address:"",
				onSaleDate:"",
				propertyType:"",
				buildingType:"",
				householdNum:"",
				floorAreaRatio:"",
				greenRate:"",
				parkingSpaceNum:"",
				propertyYears:"",
				propertyCompany:"",
				propertyFee:"",
				introduction:"",
				surrounding:"",
				traffic:"",
				region:"",
				longitude:"",
				latitude:"",
				tags:"",
				active:"",
				description:"",
				priority:"",
				preImageUrl:""
			};
		   for(var i in rc){
		   		var value = $("[name="+i+"]").val();
		   		if(i=="buildingType"){
		   			var buildingTypes = [];
					$("[name=buildingType]").each(function(){
						if(this.checked){
							buildingTypes.push($(this).val());
						}
					});
					value = buildingTypes.join(",");
		   		}
				if(i=="onSaleDate"){
					value = $("[name="+i+"]").datepicker( "getDate" );
				}
		   		rc[i]=value;
		   }
		   return rc;
		}

		$("#submit").click(function(){
			if(!!uploading){
				alert("图像正在上传，请稍后..");
				return ;
			}
			var btn=$(this);
			var form = $(".form");
			var res = form.validate();
			if(res){
				var url;
				var rc = getRCData();
				if(inEdit){
					url = "${ctx}/mi/xq/"+$("#rcId").val();
				}else{
					url = "${ctx}/mi/xq/add";
				}
			btn.attr("disabled","disabled");
			btn.addClass("disabled");
			   $.ajax({
			   	type:"POST",
			   	url:url,
			   	async:true,
			   	data:rc,
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
			   				window.location.href="${ctx}/mi/xq";
			   			}
			   		}
			   	},
			   	error:function(){
					if(inEdit){
				   		alert("修改小区失败!");
					}else{
				   		alert("新增小区失败!");
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