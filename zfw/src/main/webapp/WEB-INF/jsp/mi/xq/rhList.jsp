<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="box-cnt js-rc-rh-container" style="display:none">
	<div class="datatable" id="rhList">
		<div class="datatabls-filter">
			<!--搜索：--> 
			<input type="text" class="js-search-text"  placeholder="名称"/>
			<input type="button" class="btn js-search-btn" value="搜索"/>
		</div>
		<table class="datatable-table">
			<thead>
				<th class="js-not-detail checkarea"><input type="checkbox" id="selectAll" /></th>
				<th class="name">名称</th>
				<th class="description">描述</th>
				<th>优先级</th>
				<th>价格</th>
				<th class="time">最后修改时间</th>
				<th class="operation">操作</th>
			</thead>
			<tbody class="page-data-list">
			</tbody>
		</table>
		<div class="datatable-toolbar js-not-detail">
			<div class="toolbar">
				<select id="batch_option">
					<option value="del" selected="selected">删除</option>
				</select> <a class="btn btn-primary" href="javascript:;" onclick="rhBatchOperation(this);">批量操作</a>
				<a class="btn btn-primary" href="${ctx}/mi/${rcId}/zf/add">新增</a>
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
		var rhList = $("#rhList");
		var rhCheckList = rhList.find(".page-data-list");
		var rhDeling = false;
	  	//checkbox 全选
	  	rhList.find("#selectAll").on("change",function(){
	  		if($(this).is(":checked")){
	  			rhCheckList.find("input[type='checkbox']").prop("checked","checked");
	  		}else{
	  			rhCheckList.find("input[type='checkbox']").prop("checked",false);
	  		}
	  	});
	  	
	  	/*
	  	 * 批量操作
	  	 */
	  	function rhBatchOperation(e){
	  		var rhIds = "";
	  		rhCheckList.find("input[type='checkbox']").each(function(idx,item){
	  			if($(item).is(":checked")){
	  			rhIds==""?rhIds=$(item).val():rhIds+="/"+$(item).val();
	  			}
	  		});
	  		if(rhIds == ""){
	  			alert("请选择需要处理的租房");
				return ;
	  		}
	  		delRh(e,rhIds);
	  	}
	  	function delRh(e,rhIds){
	  		if(!window.confirm("确认删除?")){
	  			return ;
	  		}
	  		if(rhDeling){
	  			alert("正在删除租房,请稍后再操作");
	  			return;
	  		}
	  		rhDeling = true;
	  		var url = "${ctx}/mi/zf/batchDel";
	  		$.ajax({
	  			type:"post",
	  			data:{"rhIds":rhIds,"rcId":"${rcId}"},
	  			url:url,
	  			async:true,
	  			dataType:"json",
	  			success:function(data){
	  				if(data){
	  					if(data.success){
	  						alert(data.msg);
	  						rhPage.reloadPage();
	  					}else{
	  						alert(data.msg);
	  					}
	  				}
	  			},
	  			error:function(){
	  				alert("删除失败");
	  			},
	  			complete:function(){
	  				rhDeling = false;
	  			}
	  		});
	  	}
	  	
	  	
	  	/*
	  	 * 分页
	  	 * 
	  	 */
	  	var rhPage = new Page({
	  			container:"#rhList",
	  			template:"#rh-template",
	  			url:"${ctx}/mi/${rcId}/zf/page/",
	  			data:{pagesize:10}
	  	});
	  	
	  	rhList.find(".js-search-btn").click(function(){
	  		var name = rhList.find(".js-search-text").val();
	  		rhPage.setData({"name":name});
	  		rhPage.init();
	  	});
	</script>