<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<script src="${ctx }/assets/tools/jquery-ui/jquery-ui.min.js"></script>
  	<link rel="stylesheet" href="${ctx }/assets/tools/jquery-ui/jquery-ui.min.css">
		<script type="text/x-handlebars-template" id="user-template">
		{{#each this}}
		<tr>
			<td>{{name}}</td>
			<td>{{phoneNum}}</td>
			<td>{{desciption}}</td>
			<td>
				<a class="btn btn-info btn-add-relation" href="javascript:;" data-id="{{id}}" data-name="{{name}}">
					<i class="icon-plus "></i>                                            
				</a>
			</td>
		</tr>
		{{/each}}
		</script>
		
		<script type="text/x-handlebars-template" id="relation-user-template">
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
		<script type="text/x-handlebars-template" id="info-template">
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
		  
		/*
		 *分页功能 
		 */
	  	var userPage = new Page({
	  			container:"#userInfo",
	  			template:"#user-template",
	  			url:"${ctx}/mi/users/page/"
	  	});
	  	userPage.init();	  	
	  	$("#search-user-btn").click(function(){
	  		var name = encodeURIComponent($("#search-user-text").val());
	  		userPage.setData({"name":name});
	  		userPage.init();
	  	});

	  	var infoPage = new Page({
	  			container:"#zxInfo",
	  			template:"#info-template",
	  			url:"${ctx}/mi/info/page/"
	  	});
	  	infoPage.init();	  	
	  	$("#search-info-btn").click(function(){
	  		var name = encodeURIComponent($("#search-info-text").val());
	  		infoPage.setData({"name":name});
	  		infoPage.init();
	  	});
		/*
		 * 返回
		 */
		$("#cancle").on("click",function(){
			if(window.confirm("确定不保存返回？")){
				window.location.href = "${ctx}/mi/xf";
			}
		});
		
		/*
		 * 
		 * 新增关联
		 * 
		 */
		var originalUserRelation = [];
		var addUserRelation = [];
		var delUserRelation = [];
		$("#userInfo").find(".datatable-table").on("click",".btn-add-relation",function(){
			var id = $(this).data("id");
			var name = $(this).data("name");
			var didx = $.inArray(id,delUserRelation);//不在已删除
			var oidx = $.inArray(id,originalUserRelation);
			didx>-1&&delUserRelation.splice(didx,1);
			if(oidx>-1&&didx==-1||$.inArray(id,addUserRelation)>-1){//原来有，未删除不加
				return;
			}
			addUserRelation.push(id);
			$("#user-relation").append(template("#relation-user-template",{"id":id,"name":name}));
			return false;
		});

		var originalInfoRelation = [];
		var addInfoRelation = [];
		var delInfoRelation = [];
		$("#zxInfo").find(".datatable-table").on("click",".btn-add-relation",function(){
			var id = $(this).data("id");
			var name = $(this).data("name");
			var didx = $.inArray(id,delInfoRelation);//不在已删除
			var oidx = $.inArray(id,originalInfoRelation);
			didx>-1&&delInfoRelation.splice(didx,1);
			if(oidx>-1&&didx==-1||$.inArray(id,addInfoRelation)>-1){//原来有，未删除不加
				return;
			}
			addInfoRelation.push(id);
			$("#info-relation").append(template("#relation-info-template",{"id":id,"name":name}));
			return false;
		});
		
		
		/*
		 * 删除关联
		 */
		 $("#user-relation").on("click",".btn-remove-relation",function(){
			var id = $(this).data("id");
			var aidx = $.inArray(id,addUserRelation);//不在已删除
			aidx>-1&&addUserRelation.splice(aidx,1);
			if($.inArray(id,delUserRelation)==-1){//del中没有，则存入
				delUserRelation.push(id);
			}
			$(this).parent().parent().remove();
			return false;
		});
		 $("#info-relation").on("click",".btn-remove-relation",function(){
			var id = $(this).data("id");
			var aidx = $.inArray(id,addInfoRelation);//不在已删除
			aidx>-1&&addInfoRelation.splice(aidx,1);
			if($.inArray(id,delInfoRelation)==-1){//del中没有，则存入
				delInfoRelation.push(id);
			}
			$(this).parent().parent().remove();
			return false;
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

		$(":file").change(function(){
			var errorStr = checkImgType(this);
			if(errorStr){
				alert(errorStr);
				return;
			}
			var formData = new FormData($("#uploadForm")[0]);	
		    $.ajax({
		        type:'POST',
		        url:'${ctx}/mi/xf/uploadImg',
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
		
		function getREPData(){
			var rep = {
				id:"",
				name:"",
				address:"",
				tel:"",
				onSaleDate:"",
				onReadyDate:"",
				propertyType:"",
				buildingType:"",
				decorationStatus:"",
				householdNum:"",
				floorAreaRatio:"",
				greenRate:"",
				parkingSpaceNum:"",
				propertyYears:"",
				developer:"",
				preSalePermit:"",
				propertyCompany:"",
				propertyFee:"",
				introduction:"",
				surrounding:"",
				traffic:"",
				region:"",
				longitude:"",
				latitude:"",
				averagePrice:"",
				tags:"",
				description:"",
				priority:"",
				preImageUrl:""
			};
		   for(var i in rep){
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
				if(i=="onSaleDate" || i=="onReadyDate"){
					value = $("[name="+i+"]").datepicker( "getDate" );
				}
		   		rep[i]=value;
		   }
		   return rep;
		}
		
		  $(function() {
		    $( "#onSaleDate" ).datepicker({
		    	dateFormat: 'yy-mm-dd' 
		    });
		    $( "#onReadyDate" ).datepicker({
		    	dateFormat: "yy-mm-dd"
		    });
		  });
		</script>