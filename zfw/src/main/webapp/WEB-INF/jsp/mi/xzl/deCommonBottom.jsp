<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script>
	function openCloseDetail(clazz){
		var ele = $("."+clazz);
		if(ele.is(':hidden')){
			ele.show();
		}else{
			ele.hide();
		}
		if(!ele.attr("first")){
			ele.attr("first",2);
			if("js-ob-detail-container"==clazz){
				initObData();
			}else if("js-ob-image-container"==clazz){
				photoPage.init();
			}else if("js-ob-panos-container"==clazz){
				panoPage.init();
			}
		}
	}
	
	function initObData(){
		var id = $("#officeBuildingId").val();
		var getDataUrl = "${ctx}/mi/xzl/"+id;
		$.ajax({
			type:"get",
			url:getDataUrl,
			async:true,
			dataType:"json",
			success:function(data){
				if(data){
					var ob = data.officeBuilding;
					for(var i in ob){
						var ele = $("[name="+i+"]");
						if(ele[0]){
							if(i=="grossFloorArea" || i=="propertyFee"){
								var num = Number(ob[i]);
								if(num){
									ele.val(Math.round(num*100)/100);
								}
							}else if(i=="outOfDate"){
								ele.val(String(ob[i]));
								continue;
							}else {
								ele.val(ob[i]);
							}
							if(inDetail){
								ele.attr("readonly","readonly");
								ele.attr("disabled","disabled");
							}
						}
					}
					if(ob.preImageUrl){
						$(".control-user-img").attr("src",ob.preImageUrl);
					}
				}
			},
			error:function(){
				alert("获取写字楼信息失败");
			}
		});
	}
</script>