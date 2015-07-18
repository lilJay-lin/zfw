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
			if("js-warehouse-detail-container"==clazz){
				initWarehouseData();
			}else if("js-warehouse-image-container"==clazz){
				photoPage.init();
			}else if("js-warehouse-panos-container"==clazz){
				panoPage.init();
			}
		}
	}
	
	function initWarehouseData(){
		var id = $("#warehouseId").val();
		var getDataUrl = "${ctx}/mi/cfck/"+id;
		$.ajax({
			type:"get",
			url:getDataUrl,
			async:true,
			dataType:"json",
			success:function(data){
				if(data){
					var warehouse = data.warehouse;
					for(var i in warehouse){
						var ele = $("[name="+i+"]");
						if(ele[0]){
							if(inDetail){
								ele.attr("readonly","readonly");
								ele.attr("disabled","disabled");
							}
							if(i=="grossFloorArea" || i=="propertyFee"){
								var num = Number(warehouse[i]);
								if(num){
									ele.val(Math.round(num*100)/100);
								}
							}else if(i=="outOfDate"){
								ele.val(String(warehouse[i]));
								continue;
							}else {
								ele.val(warehouse[i]);
							}
						}
					}
					if(!!warehouse.preImageUrl){
						$(".control-user-img").attr("src",warehouse.preImageUrl);
					}
				}
			},
			error:function(){
				alert("获取厂房仓库信息失败");
			}
		});
	}
</script>