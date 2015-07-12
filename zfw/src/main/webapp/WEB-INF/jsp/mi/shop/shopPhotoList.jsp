<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="box-cnt js-shop-image-container" style="display:none">
	<div class="datatable" id="photoList">
		<div class="datatabls-filter js-edit-operation">
			<label> 
			<!--搜索：--> 
				<input type="text" class="js-search-text"  placeholder="名称"/>
				<input type="button" class="btn js-search-btn" value="搜索"/>
			</label>
		</div>
		<table class="datatable-table">
			<thead>
				<th class="js-edit-operation"><input type="checkbox" id="selectAll" /></th>
				<th>预览</th>
				<th>名称</th>
				<th>描述</th>
				<th class="js-edit-operation">操作</th>
			</thead>
			<tbody class="page-data-list">
			</tbody>
		</table>
		<div class="datatable-toolbar js-edit-operation">
			<div class="toolbar">
				<select id="batch_option">
					<option value="del" selected="selected">删除</option>
				</select> <a class="btn" href="javascript:;" onclick="photoBatchOperation(this);">批量操作</a>
				<a class="btn" href="${ctx}/mi/${shopId}/spphoto/add">新增</a>
			</div>
		</div>
		<div class="datatable-footer">
			<div class="datatable-info">共0条</div>
			<div class="center">
				<div class="datatable-pagination">
					<ul class="pagination">
	
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>
<script>
		var photoList = $("#photoList");
		var photoCheckList = photoList.find(".page-data-list");
		var photoDeling = false;
	  	//checkbox 全选
	  	photoList.find("#selectAll").on("change",function(){
	  		if($(this).is(":checked")){
	  			photoCheckList.find("input[type='checkbox']").prop("checked","checked");
	  		}else{
	  			photoCheckList.find("input[type='checkbox']").prop("checked",false);
	  		}
	  	});
	  	
	  	/*
	  	 * 批量操作
	  	 */
	  	function photoBatchOperation(e){
	  		var imageIds = "";
	  		photoCheckList.find("input[type='checkbox']").each(function(idx,item){
	  			if($(item).is(":checked")){
	  			imageIds==""?imageIds=$(item).val():imageIds+="/"+$(item).val();
	  			}
	  		});
	  		if(imageIds == ""){
	  			alert("请选择需要处理的图片");
	  		}	
	  		delPhoto(e,imageIds);
	  	}
	  	function delPhoto(e,imageIds){
	  		if(photoDeling){
	  			alert("正在删除图片,请稍后再操作");
	  			return;
	  		}
	  		var obj = {};
	  		obj.delFlag = true;
	  		photoDeling = true;
	  		var url = "${ctx}/mi/spphotos";
	  		$.ajax({
	  			type:"post",
	  			data:$.extend({"imageIds":imageIds},obj),
	  			url:url,
	  			async:true,
	  			dataType:"json",
	  			success:function(data){
	  				if(data){
	  					if(data.success){
	  						alert(data.msg);
	  						photoPage.reloadPage();
	  					}else{
	  						alert(data.msg);
	  					}
	  				}
	  			},
	  			error:function(){
	  				alert("删除失败");
	  			},
	  			complete:function(){
	  				photoDeling = false;
	  			}
	  		});
	  	}
	  	
	  	
	  	/*
	  	 * 分页
	  	 * 
	  	 */
	  	var photoPage = new Page({
	  			container:"#photoList",
	  			template:"#photo-template",
	  			url:"${ctx}/mi/${shopId}/spphoto/page/",
	  			data:{pagesize:10}
	  	});
	  	
	  	photoList.find(".js-search-btn").click(function(){
	  		var name = photoList.find(".js-search-text").val();
	  		photoPage.setData({"name":name});
	  		photoPage.init();
	  	});
	</script>