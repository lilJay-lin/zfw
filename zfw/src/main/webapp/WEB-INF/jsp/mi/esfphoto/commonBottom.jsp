<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<script>
		var inDetail = false;
		var inAdd = false;
		var inEdit = false;
		$("#cancle").on("click",function(){
			if(inDetail || window.confirm("确定不保存返回？")){
				window.history.back(-1);
			}
		});
	</script>