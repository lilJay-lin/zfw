<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="box-cnt js-rep-ht-container" style="display:none">
	<div class="datatable" id="htList">
		<div class="datatabls-filter">
			<label> <!--搜索：--> <input type="text" class="js-search-text"  placeholder="名称"/>
				<input type="button" class="btn js-search-btn" value="搜索"/>
			</label>
		</div>
		<table class="datatable-table">
			<thead>
				<th><input type="checkbox" id="selectAll" /></th>
				<th>名称</th>
				<th>描述</th>
				<th>优先级</th>
				<th>销售</th>
				<th>价格</th>
				<th>最后修改时间</th>
				<th>操作</th>
			</thead>
			<tbody class="page-data-list">
			</tbody>
		</table>
		<div class="datatable-toolbar js-edit-content">
			<div class="toolbar">
				<select id="batch_option">
					<option value="del" selected="selected">删除</option>
				</select> <a class="btn" href="javascript:;" onclick="htBatchOperation(this);">批量操作</a>
				<a class="btn" href="${ctx}/mi/${repId}/hx/add">新增</a>
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
		var htList = $("#htList");
		var htCheckList = htList.find(".page-data-list");
		var htDeling = false;
	  	//checkbox 全选
	  	htList.find("#selectAll").on("change",function(){
	  		if($(this).is(":checked")){
	  			htCheckList.find("input[type='checkbox']").prop("checked","checked");
	  		}else{
	  			htCheckList.find("input[type='checkbox']").prop("checked",false);
	  		}
	  	});
	  	
	  	/*
	  	 * 批量操作
	  	 */
	  	function htBatchOperation(e){
	  		var htIds = "";
	  		htCheckList.find("input[type='checkbox']").each(function(idx,item){
	  			if($(item).is(":checked")){
	  			htIds==""?htIds=$(item).val():htIds+="/"+$(item).val();
	  			}
	  		});
	  		if(htIds == ""){
	  			alert("请选择需要处理的户型");
	  		}
	  		delHt(e,htIds);
	  	}
	  	function delHt(e,htIds){
	  		if(htDeling){
	  			alert("正在删除户型,请稍后再操作");
	  			return;
	  		}
	  		htDeling = true;
	  		var url = "${ctx}/mi/hx/batchDel";
	  		$.ajax({
	  			type:"post",
	  			data:{"htIds":htIds},
	  			url:url,
	  			async:true,
	  			dataType:"json",
	  			success:function(data){
	  				if(data){
	  					if(data.success){
	  						alert(data.msg);
	  						htPage.reloadPage();
	  					}else{
	  						alert(data.msg);
	  					}
	  				}
	  			},
	  			error:function(){
	  				alert("删除失败");
	  			},
	  			complete:function(){
	  				htDeling = false;
	  			}
	  		});
	  	}
	  	
	  	
	  	/*
	  	 * 分页
	  	 * 
	  	 */
	  	var htPage = new Page({
	  			container:"#htList",
	  			template:"#ht-template",
	  			url:"${ctx}/mi/${repId}/hx/page/",
	  			data:{pagesize:10}
	  	});
	  	
	  	htList.find(".js-search-btn").click(function(){
	  		var name = htList.find(".js-search-text").val();
	  		htPage.setData({"name":name});
	  		htPage.init();
	  	});
	</script>