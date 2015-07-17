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
			if("js-shop-detail-container"==clazz){
				initShopData();
			}else if("js-shop-image-container"==clazz){
				photoPage.init();
			}else if("js-shop-panos-container"==clazz){
				panoPage.init();
			}
		}
	}
	
	function initShopData(){
		var id = $("#shopId").val();
		var getDataUrl = "${ctx}/mi/shop/"+id;
		$.ajax({
			type:"get",
			url:getDataUrl,
			async:true,
			dataType:"json",
			success:function(data){
				if(data){
					var shop = data.shop;
					for(var i in shop){
						var ele = $("[name="+i+"]");
						if(ele[0]){
							if(i=="grossFloorArea" || i=="propertyFee"){
								var num = Number(shop[i]);
								if(num){
									ele.val(Math.round(num*100)/100);
								}
							}else if(i=="outOfDate"){
								ele.val(String(shop[i]));
								continue;
							}else {
								ele.val(shop[i]);
							}
							if(inDetail){
								ele.attr("readonly","readonly");
								ele.attr("disabled","disabled");
							}
						}
					}
					if(!!shop.preImageUrl){
						$(".control-user-img").attr("src",shop.preImageUrl);
					}
				}
			},
			error:function(){
				alert("获取商铺信息失败");
			}
		});
	}
</script>