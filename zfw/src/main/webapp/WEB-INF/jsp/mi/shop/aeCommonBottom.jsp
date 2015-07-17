<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	
<script>
/*
 * 返回
 */
$(".cancle").on("click",function(){
		window.location.href = "${ctx}/mi/shop";
});

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
			if(typeof initShopData !="undefined") initShopData();
		}else if("js-shop-image-container"==clazz){
			if(typeof photoPage !="undefined") photoPage.init();
		}else if("js-shop-panos-container"==clazz){
			if(typeof panoPage != "undefined") panoPage.init();
		}
	}
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
        url:'${ctx}/mi/shop/uploadImg',
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
	var shop = {
		id:"",
		name:"",
		phoneNum:"",
		rental:"",
		region:"",
		totalPrice:"",
		grossFloorArea:"",
		decorationStatus:"",
		address:"",
		introduction:"",
		rentOrSale:"",
		propertyFee:"",
		type:"",
		tags:"",
		priority:"",
		preImageUrl:""
	};
   for(var i in shop){
   		var value = $("[name="+i+"]").val();
   		shop[i]=value;
   }
   return shop;
}

rentOrSale($("[name=rentOrSale]").val());
$("[name=rentOrSale]").change(function(){
	rentOrSale($(this).val())
})
function rentOrSale(val){
	if(val == "出租"){
		$(".js-control-group-totalPrice").hide();
		$(".js-control-group-rental").show();
	}else{
		$(".js-control-group-totalPrice").show();
		$(".js-control-group-rental").hide();
	}
}


</script>