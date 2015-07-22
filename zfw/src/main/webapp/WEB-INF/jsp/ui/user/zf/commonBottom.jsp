<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script>
function inputOnKeyup(event, element) {
	nameChange(element);
}
function inputOnFocus(event, element) {
	var ele = $(element);
	var container = $("#" + ele.attr("resultContainer"));
	container.removeClass("none");
	nameChange(element);
}
function inputOnBlus(event, element) {
	var ele = $(element);
	var container = $("#" + ele.attr("resultContainer"));
	setTimeout(function(){
		container.addClass("none");
		
	},200);
}
function nameChange(element) {
	var ele = $(element);
	if (ele.attr("searching") == "true" || ele.val() == "") {
		return;
	}
	ele.attr("searching", "true");
	ele.attr("searchingName", ele.val());
	var url = "${ctx}/xq/json/{name}/search";
	url = url.replace("{name}",ele.attr("searchingName"));
	$.ajax({
		type: "GET",
		async: true,
		url: url,
		dataType: "json",
		success: function (data) {
			if(data.success){
				var results = data.results;
				if(results!=null){
					var container = $("#" + ele.attr("resultContainer"));
					container.empty();
					for (var i = 0; i < results.length; i++) {
						var result = results[i];
						var str = ' <li class="li-loudong" dataId="'+result.id+'"  onclick="selectName(this)">' + result.name + '</li>';
						container.append(str);
					}
				}
				ele.attr("searching", "false");
				if (ele.attr("searchingName") != ele.val()) {
					nameChange(element);
				}
			}else{
				alert(data.msg);
			}
		},
		error: function (data) {
			alert("查询失败，请稍后尝试！");
		}
	});
}
function selectName(element) {
	var ele = $(element);
	$("#xqName").attr("dataId",ele.attr("dataId"));
	$("#xqName").val(ele.html());
}

$(function(){
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
	$(':file').change(function(){
		var errorStr = checkImgType(this);
		if(errorStr){
			alert(errorStr);
			return;
		}
		refreshImgTips();
		var str = '<dd><img src="${ctx}/assets/img/loading.gif" class="imgClass"><a href="javascript:void(0);" class="del" onclick="delImg(this)"></a></dd>';
		var imgObj =  $(str);
		$(this).parents("dd:first").before(imgObj);
		
		var formData = new FormData($("#uploadForm")[0]);	
	    $.ajax({
	        type:'POST',
	        url:'${ctx}/user/zf/uploadImg',
	        data: formData,
	        async: true,
	        cache: false,
			dataType : "json",
	        contentType: false,
	        processData: false,
	        success: function (data) {
				if(data.success){
					var final_url = data.imgPath;	
					imgObj.children(".imgClass").attr("src",final_url);
				}else{
					alert(data.msg);
				}
	        },
	        error: function (data) {
				alert("上传失败");
				imgObj.remove();
	        }
	    });
	});
}); 


	function refreshImgTips(){
		if($(".imgClass").length>0){
			$("#note").addClass("none");
		}else{
			$("#note").removeClass("none");
		}
	}
	function delImg(element){
		var flag = confirm("确定删除图片？");
		if(flag){
			var ele = $(element);
			ele.parents("dd:first").remove();
			refreshImgTips();
		}
	}
</script>